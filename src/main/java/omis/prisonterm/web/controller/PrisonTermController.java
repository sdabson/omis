package omis.prisonterm.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.io.DocumentPersister;
import omis.document.io.DocumentRetriever;
import omis.document.io.impl.DocumentFilenameGenerator;
import omis.document.web.form.DocumentTagItem;
import omis.document.web.form.DocumentTagOperation;
import omis.exception.DuplicateEntityFoundException;
import omis.io.FileRemover;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.prisonterm.domain.PrisonTerm;
import omis.prisonterm.domain.PrisonTermDocumentAssociation;
import omis.prisonterm.domain.PrisonTermStatus;
import omis.prisonterm.exception.ActivePrisonTermExistsException;
import omis.prisonterm.report.PrisonTermReportService;
import omis.prisonterm.report.PrisonTermSummary;
import omis.prisonterm.service.PrisonTermService;
import omis.prisonterm.web.form.PrisonTermForm;
import omis.prisonterm.web.validator.PrisonTermFormValidator;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.user.domain.UserAccount;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Prison term controller.
 * 
 * @author Trevor Isles
 * @author Josh Divine
 * @author Annie Wahl
 * @version 0.1.3 (Jan 30, 2019)
 * @since OMIS 3.0
 */

@Controller
@RequestMapping("/prisonTerm")
@PreAuthorize("hasRole('USER')")
public class PrisonTermController {

	/* Model keys. */
	
	private static final String PRISON_TERMS_MODEL_KEY = "prisonTerms";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String PRISON_TERM_MODEL_KEY = "prisonTerm";
	
	private static final String PRISON_TERM_FORM_MODEL_KEY = "prisonTermForm";
	
	private static final String PRISON_TERM_STATUS_MODEL_KEY 
		= "prisonTermStatus";
	
	private static final String USER_ACCOUNTS_MODEL_KEY = "userAccounts";
	
	private static final String SENTENCE_CALCULATION_MODEL_KEY =
			"sentenceCalculation";
	
	/* View names. */
	
	private static final String LIST_VIEW_NAME = "prisonTerm/list";
	
	private static final String EDIT_VIEW_NAME = "prisonTerm/edit";
	
	private static final String PRISON_TERM_ACTION_MENU_VIEW_NAME 
		= "prisonTerm/includes/prisonTermActionMenu";
	
	private static final String PRISON_TERMS_ACTION_MENU_VIEW_NAME
		= "prisonTerm/includes/prisonTermsActionMenu";
	
	private static final String USER_ACCOUNTS_VIEW_NAME 
		= "user/json/userAccounts";
	
	/* Redirects. */
	
	private static final String LIST_REDIRECT_VIEW_NAME
		= "redirect:/prisonTerm/list.html?offender=%d";
	
	/* Message keys. */
	
	private static final String PRISON_TERM_EXISTS_MESSAGE_KEY 
		= "prisonTerm.exists";
	
	private static final String ACTIVE_PRISON_TERM_EXISTS_MESSAGE_KEY
		= "activePrisonTerm.status.exists";
	
	private static final String ERROR_WRITING_FILE_MSG = 
			"Error writing file: %s";
	
	/* Message bundles. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.prisonterm.msgs.form";
	
	/* Report names. */
	
	private static final String PRISON_TERM_LISTING_REPORT_NAME 
		= "/Legal/PrisonTerms/Prison_Term_Listing";
	
	private static final String PRISON_TERM_DETAILS_REPORT_NAME 
		= "/Legal/PrisonTerms/Prison_Term_Details";
	
	/* Report parameter names. */
	
	private static final String PRISON_TERM_LISTING_ID_REPORT_PARAM_NAME =
			"DOC_ID";
	
	private static final String PRISON_TERM_DETAILS_ID_REPORT_PARAM_NAME =
			"PRISON_TERM_ID";
	
	/* Services. */	
	
	@Autowired
	@Qualifier("prisonTermService")
	private PrisonTermService prisonTermService;
	
	@Autowired
	@Qualifier("prisonTermReportService")
	private PrisonTermReportService prisonTermReportService;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("prisonTermPropertyEditorFactory")
	private PropertyEditorFactory prisonTermPropertyEditorFactory;

	@Autowired
	@Qualifier("prisonTermDocumentAssociationPropertyEditorFactory")
	private PropertyEditorFactory
			prisonTermDocumentAssociationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;

	@Autowired
	@Qualifier("documentPropertyEditorFactory")
	private PropertyEditorFactory documentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentTagPropertyEditorFactory")
	private PropertyEditorFactory documentTagPropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentFilenameGenerator")
	private DocumentFilenameGenerator documentFilenameGenerator;
	
	@Autowired
	@Qualifier("prisonTermDocumentPersister")
	private DocumentPersister prisonTermDocumentPersister;
	
	@Autowired
	@Qualifier("prisonTermDocumentRetriever")
	private DocumentRetriever prisonTermDocumentRetriever;
	
	@Autowired
	@Qualifier("prisonTermDocumentRemover")
	private FileRemover prisonTermDocumentRemover;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("prisonTermFormValidator")
	private PrisonTermFormValidator prisonTermFormValidator;
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;

	/* Constructor. */
	
	/** Instantiates a default controller for prison terms. */
	public PrisonTermController() {
		//Default instantiation
	}
	
	/* Screens. */
	
	/**
	 * Displays a list of prison terms for an offender.
	 * 
	 * @param offender offender whose prison terms to list
	 * @return model and view to a list of prison terms
	 */
	@RequestContentMapping(nameKey = "prisonTermListingScreenName",
			descriptionKey = "prisonTermListingScreenDescription",
			messageBundle = "omis.prisonterm.msgs.prisonTerm",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PRISON_TERM_LIST') or hasRole('ADMIN')"
			+ " or hasRole('PRISON_TERM_VERIFY')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		List<PrisonTermSummary> prisonTerms;
		if (this.hasRole("PRISON_TERM_VERIFY") || this.hasRole("ADMIN")) {
			prisonTerms = this.prisonTermReportService
						.summarizeByOffender(offender);
		} else {
			prisonTerms = this.prisonTermReportService
					.findActiveVerifiedTermsByOffender(offender);
		}
		
		
		mav.addObject(PRISON_TERMS_MODEL_KEY, prisonTerms);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/**
	 * Displays a form allowing a new prison term to be created.
	 * 
	 * @param offender offender from whom to create a new prison term
	 * @param effectiveDate effective date of the prison term
	 * @return model and view to screen that allows new prison term to
	 * be created
	 */
	@RequestContentMapping(nameKey = "prisonTermCreateScreenName",
			descriptionKey = "prisonTermCreateScreenDescription",
			messageBundle = "omis.prisonterm.msgs.prisonTerm",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html", 
			method = RequestMethod.GET)
	@PreAuthorize("(hasRole('PRISON_TERM_CREATE')"
			+ " and hasRole('PRISON_TERM_VERIFY')) or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "effectiveDate", required = false)
				final Date effectiveDate) {
		PrisonTermForm prisonTermForm = new PrisonTermForm();
		if (effectiveDate != null) {
			prisonTermForm.setActionDate(effectiveDate);
		} else {
			prisonTermForm.setActionDate(new Date());
		}
		return this.prepareEditMav(prisonTermForm, offender);
	}
	
	/**
	 * Saves a new prison term.
	 * 
	 * @param offender offender for whom to create the prison term
	 * @param effectiveDate effective date of the prison term
	 * @param form form containing prison term information
	 * @param result binding result
	 * @return model and view to redirect to list URL
	 * @throws DuplicateEntityFoundException if an attempt to save a duplicate
	 * prison term is attempted  
	 * @throws ActivePrisonTermExistsException 
	 */
	@RequestContentMapping(nameKey = "prisonTermCreateSubmitName",
			descriptionKey = "prisonTermCreateSubmitDescription",
			messageBundle = "omis.prisonterm.msgs.prisonTerm",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/create.html",
			method = RequestMethod.POST)
	@PreAuthorize("(hasRole('PRISON_TERM_CREATE')"
			+ " and hasRole('PRISON_TERM_VERIFY')) or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "effectiveDate", required = false)
				final Date effectiveDate, 
			final PrisonTermForm form,
			final BindingResult result)
					throws DuplicateEntityFoundException,
						ActivePrisonTermExistsException {
		this.prisonTermFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareEditMav(form, offender);
			mav.addObject(BindingResult.MODEL_KEY_PREFIX 
					+ PRISON_TERM_MODEL_KEY, result);
			return mav;
		}
		PrisonTerm prisonTerm = this.prisonTermService.create(offender, 
				form.getActionDate(),
				form.getPreSentenceCredits(),
				form.getSentenceDate(),
				form.getSentenceTermYears(), 
				form.getSentenceTermDays(),
				form.getParoleEligibilityDate(), 
				form.getProjectedDischargeDate(),
				form.getMaximumDischargeDate(), 
				form.getStatus(),
				form.getSentenceToFollow(),
				form.getComments(),
				form.getVerificationUser(), 
				form.getVerificationDate(),
				null);
		
		if (form.getData() != null && form.getData().length > 0) {
			final String fileExtension = form.getFileExtension();
			this.documentFilenameGenerator
					.setExtension(fileExtension);
			final String filename =
					this.documentFilenameGenerator.generate();
			Document document = this.prisonTermService
					.createDocument(form.getDate(), filename, fileExtension,
							form.getTitle());
			this.prisonTermDocumentPersister.persist(
					document, form.getData());
			this.processDocumentTags(form.getDocumentTagItems(), document);
			
			PrisonTermDocumentAssociation sentenceCalculation =
					this.prisonTermService.createPrisonTermDocumentAssociation(
							document, prisonTerm);
			
			this.prisonTermService.update(prisonTerm,
					prisonTerm.getActionDate(),
					prisonTerm.getPreSentenceCredits(),
					prisonTerm.getSentenceDate(),
					prisonTerm.getSentenceTermYears(),
					prisonTerm.getSentenceTermDays(),
					prisonTerm.getParoleEligibilityDate(),
					prisonTerm.getProjectedDischargeDate(),
					prisonTerm.getMaximumDischargeDate(),
					prisonTerm.getStatus(), prisonTerm.getSentenceToFollow(),
					prisonTerm.getComments(), prisonTerm.getVerificationUser(),
					prisonTerm.getVerificationDate(), sentenceCalculation);
		}
		
		return this.prepareListRedirect(prisonTerm.getOffender());
	}
	
	/**
	 * Displays a form allowing an existing prison term to be edited.
	 * 
	 * @param prisonTerm prison term to allow to be edited
	 * @return model and view to screen that allows existing prison term to be
	 * edited
	 */
	@RequestContentMapping(nameKey = "prisonTermEditScreenName",
			descriptionKey = "prisonTermScreenDescription",
			messageBundle = "omis.prisonterm.msgs.prisonTerm",
			screenType = RequestContentType.DETAIL_SCREEN)
	@PreAuthorize("hasRole('PRISON_TERM_EDIT') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html",
		method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "prisonTerm", required = true)
			final PrisonTerm prisonTerm) {
		PrisonTermForm form = new PrisonTermForm();
		form.setActionDate(prisonTerm.getActionDate());
		form.setPreSentenceCredits(prisonTerm
				.getPreSentenceCredits());
		form.setSentenceDate(prisonTerm.getSentenceDate());
		form.setSentenceTermYears(prisonTerm.getSentenceTermYears());
		form.setSentenceTermDays(prisonTerm.getSentenceTermDays());
		form.setParoleEligibilityDate(prisonTerm
				.getParoleEligibilityDate());
		form.setProjectedDischargeDate(prisonTerm
				.getProjectedDischargeDate());
		form.setMaximumDischargeDate(prisonTerm
				.getMaximumDischargeDate());
		form.setStatus(prisonTerm.getStatus());
		form.setSentenceToFollow(prisonTerm.getSentenceToFollow());
		form.setComments(prisonTerm.getComments());
		form.setVerificationUser(prisonTerm.getVerificationUser());
		form.setVerificationDate(prisonTerm.getVerificationDate());
		
		if (prisonTerm.getSentenceCalculation() != null) {
			List<DocumentTag> documentTags =
					this.prisonTermService.findDocumentTagsByDocument(
							prisonTerm.getSentenceCalculation()
							.getDocument());
			List<DocumentTagItem> documentTagItems =
					new ArrayList<DocumentTagItem>();
			for (DocumentTag tag : documentTags) {
				DocumentTagItem item = new DocumentTagItem();
				item.setDocumentTag(tag);
				item.setName(tag.getName());
				item.setDocumentTagOperation(DocumentTagOperation.UPDATE);
				
				documentTagItems.add(item);
			}
			form.setDate(prisonTerm.getSentenceCalculation()
					.getDocument().getDate());
			form.setDocument(prisonTerm.getSentenceCalculation().getDocument());
			form.setTitle(prisonTerm.getSentenceCalculation()
					.getDocument().getTitle());
			form.setFileExtension(prisonTerm.getSentenceCalculation()
					.getDocument().getFileExtension());
			form.setDocumentTagItems(documentTagItems);
			form.setData(this.prisonTermDocumentRetriever.retrieve(
					prisonTerm.getSentenceCalculation().getDocument()));
		}
		
		ModelAndView mav = this.prepareEditMav(form, prisonTerm.getOffender());
		mav.addObject(PRISON_TERM_MODEL_KEY, prisonTerm);
		mav.addObject(SENTENCE_CALCULATION_MODEL_KEY,
				prisonTerm.getSentenceCalculation());
		return mav;
	}
	
	/**
	 * Updates an existing prison term.
	 * 
	 * @param prisonTerm prison term to update
	 * @param form form containing prison term information
	 * @param result binding result
	 * @return model and view to redirect to list URL
	 * @throws DuplicateEntityFoundException if an attempt to save a duplicate
	 * prison term is attempted
	 * @throws ActivePrisonTermExistsException if an active prison term already
	 * exists.
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("(hasRole('PRISON_TERM_EDIT')"
			+ " and hasRole('PRISON_TERM_VERIFY')) or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "prisonTerm", required = true)
				final PrisonTerm prisonTerm,
				final PrisonTermForm form,
				final BindingResult result) 
						throws DuplicateEntityFoundException,
						ActivePrisonTermExistsException {
		this.prisonTermFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareEditMav(form,
					prisonTerm.getOffender());
			mav.addObject(BindingResult.MODEL_KEY_PREFIX 
					+ PRISON_TERM_MODEL_KEY, result);
			mav.addObject(SENTENCE_CALCULATION_MODEL_KEY,
					prisonTerm.getSentenceCalculation());
			return mav;
		}
		PrisonTermDocumentAssociation sentenceCalculation =
				prisonTerm.getSentenceCalculation();
		if (sentenceCalculation == null) {
			if (form.getData() != null && form.getData().length > 0) {
				final String fileExtension = form.getFileExtension();
				this.documentFilenameGenerator
						.setExtension(fileExtension);
				final String filename =
						this.documentFilenameGenerator.generate();
				Document document = this.prisonTermService
						.createDocument(form.getDate(), filename, fileExtension,
								form.getTitle());
				this.prisonTermDocumentPersister.persist(
						document, form.getData());
				
				sentenceCalculation = this.prisonTermService
						.createPrisonTermDocumentAssociation(
								document, prisonTerm);
			}
		} else if (form.getRemoveSentenceCalculation() != null
				&& form.getRemoveSentenceCalculation()) {
			this.prisonTermService.update(
					prisonTerm,
					form.getActionDate(),
					form.getPreSentenceCredits(),
					form.getSentenceDate(),
					form.getSentenceTermYears(),
					form.getSentenceTermDays(),
					form.getParoleEligibilityDate(),
					form.getProjectedDischargeDate(),
					form.getMaximumDischargeDate(),
					form.getStatus(),
					form.getSentenceToFollow(),
					form.getComments(),
					form.getVerificationUser(),
					form.getVerificationDate(),
					null);
			List<DocumentTag> documentTags =
					this.prisonTermService
						.findDocumentTagsByDocument(sentenceCalculation
								.getDocument());
			for (DocumentTag documentTag : documentTags) {
				this.prisonTermService
					.removeDocumentTag(documentTag);
			}
			this.prisonTermService
				.removePrisonTermDocumentAssociation(sentenceCalculation);
			this.prisonTermDocumentRemover.remove(sentenceCalculation
					.getDocument().getFilename());
			this.prisonTermService.removeDocument(sentenceCalculation
					.getDocument());
			sentenceCalculation = null;
			if (form.getReplaceData() != null
					&& form.getReplaceData().length > 0) {
				final String fileExtension = form.getFileExtension();
				this.documentFilenameGenerator
						.setExtension(fileExtension);
				final String filename =
						this.documentFilenameGenerator.generate();
				Document document = this.prisonTermService
						.createDocument(form.getDate(), filename, fileExtension,
								form.getTitle());
				this.prisonTermDocumentPersister.persist(
						document, form.getReplaceData());
				
				sentenceCalculation = this.prisonTermService
						.createPrisonTermDocumentAssociation(
								document, prisonTerm);
				
				if (form.getDocumentTagItems() != null) {
					Iterator<DocumentTagItem> documentTagIterator = 
							form.getDocumentTagItems().iterator();
					while (documentTagIterator.hasNext()) {
						final DocumentTagItem documentTagItem =
								documentTagIterator.next();
						if (DocumentTagOperation.CREATE.equals(
								documentTagItem.getDocumentTagOperation())) {
							this.prisonTermService.createDocumentTag(
									document, documentTagItem.getName());
						}
					}
				}
			}
		}
		
		this.prisonTermService.update(
				prisonTerm,
				form.getActionDate(),
				form.getPreSentenceCredits(),
				form.getSentenceDate(),
				form.getSentenceTermYears(),
				form.getSentenceTermDays(),
				form.getParoleEligibilityDate(),
				form.getProjectedDischargeDate(),
				form.getMaximumDischargeDate(),
				form.getStatus(),
				form.getSentenceToFollow(),
				form.getComments(),
				form.getVerificationUser(),
				form.getVerificationDate(),
				sentenceCalculation);
		return new ModelAndView(String.format(LIST_REDIRECT_VIEW_NAME, 
				prisonTerm.getOffender().getId()));
	}
	
	/**
	 * Removes an existing prison term.
	 * 
	 * @param prisonTerm prison term to remove
	 * @param offender offender
	 * @return redirect to listing screen
	 */
	@RequestContentMapping(nameKey = "prisonTermRemove",
			descriptionKey = "prisonTermRemoveDescription",
			messageBundle = "omis.prisonterm.msgs.prisonTerm",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/remove.html")
	@PreAuthorize("(hasRole('PRISON_TERM_REMOVE')"
			+ " and hasRole('PRISON_TERM_VERIFY')) or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "prisonTerm", required = true)
				final PrisonTerm prisonTerm,
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		if (prisonTerm.getSentenceCalculation() != null) {
			List<DocumentTag> documentTags =
					this.prisonTermService
						.findDocumentTagsByDocument(
								prisonTerm.getSentenceCalculation()
								.getDocument());
			for (DocumentTag documentTag : documentTags) {
				this.prisonTermService
					.removeDocumentTag(documentTag);
			}
			this.prisonTermService
				.removePrisonTermDocumentAssociation(prisonTerm
						.getSentenceCalculation());
			this.prisonTermDocumentRemover.remove(prisonTerm
					.getSentenceCalculation()
					.getDocument().getFilename());
			this.prisonTermService.removeDocument(
					prisonTerm.getSentenceCalculation().getDocument());
		}
		this.prisonTermService.remove(prisonTerm);
		return this.prepareListRedirect(offender);
	}
	
	/* AJAX invokable methods. */
		
	/**
	 * Searches user accounts.
	 * 
	 * @param query query
	 * @return user accounts as JSON
	 */
	@RequestMapping(value = "/searchUserAccounts.json",
			method = RequestMethod.GET)
	public ModelAndView searchUserAccounts(
			@RequestParam(value = "term", required = true)
				final String query) {
		List<UserAccount> userAccounts
			= this.prisonTermService.findUserAccounts(query.toUpperCase());
		ModelAndView mav = new ModelAndView(USER_ACCOUNTS_VIEW_NAME);
		mav.addObject(USER_ACCOUNTS_MODEL_KEY, userAccounts);
		return mav;
	}
	
	/* ActionMenus */
	
	/**
	 * Returns the content for the prison terms action menu. 
	 * 
	 * @param offender offender
	 * @param prisonTerm prison term
	 * @return prison terms action menu
	 */
	@RequestMapping(value = "prisonTermsActionMenu.html")
	public ModelAndView prisonTermsActionMenu(
			@RequestParam(value = "offender", required = false)
				final Offender offender,
			@RequestParam(value = "prisonTerm", required = false)
				final PrisonTerm prisonTerm) {
		ModelMap map = new ModelMap();
		if (offender != null) {
			map.addAttribute(OFFENDER_MODEL_KEY, offender);
		}
		if (prisonTerm != null) {
			map.addAttribute(PRISON_TERM_MODEL_KEY, prisonTerm);
			if (prisonTerm.getSentenceCalculation() != null) {
				map.addAttribute(SENTENCE_CALCULATION_MODEL_KEY,
						prisonTerm.getSentenceCalculation());
			}
		}
		return new ModelAndView(PRISON_TERMS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the content for the prison term action menu. 
	 * 
	 * @param offender offender
	 * @return prison term action menu
	 */
	@RequestMapping(
			value = "prisonTermActionMenu.html")
	public ModelAndView prisonTermActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(PRISON_TERM_ACTION_MENU_VIEW_NAME, map);
	}
		
	/* Reports. */
	
	/**
	 * Returns the report for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/prisonTermListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PRISON_TERM_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportPrisonTermListing(@RequestParam(
			value = "offender", required = true) final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(PRISON_TERM_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				PRISON_TERM_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified prison term.
	 * 
	 * @param prisonTerm prison term
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/prisonTermDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PRISON_TERM_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportPrisonTermDetails(@RequestParam(
			value = "prisonTerm", required = true) final PrisonTerm prisonTerm,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(PRISON_TERM_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(prisonTerm.getId()));
		byte[] doc = this.reportRunner.runReport(
				PRISON_TERM_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* Helper Methods */
	
	// Returns a model and view suitable for editing the specified prison term.
	private ModelAndView prepareEditMav(final PrisonTermForm prisonTermForm,
			final Offender offender) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(PRISON_TERM_FORM_MODEL_KEY, prisonTermForm);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		mav.addObject(PRISON_TERM_STATUS_MODEL_KEY, PrisonTermStatus.values());
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	private ModelAndView prepareListRedirect(final Offender offender) {
		return new ModelAndView(String.format(LIST_REDIRECT_VIEW_NAME, 
					offender.getId()));
	}
	
	/**
	 * Processes a list of documentTag items for creation, updating, or removal.
	 * 
	 * @param documentTagItems - List of DocumentTagItems
	 * @param document - Document for which the documentTags are being processed
	 * for
	 * @throws DuplicateEntityFoundException - When a document tag already
	 * exists with given name and document
	 */
	private void processDocumentTags(
			final List<DocumentTagItem> documentTagItems, 
			final Document document) throws DuplicateEntityFoundException {
		if (documentTagItems != null) {
			Iterator<DocumentTagItem> documentTagIterator = 
					documentTagItems.iterator();
			while (documentTagIterator.hasNext()) {
				final DocumentTagItem documentTagItem =
						documentTagIterator.next();
				final DocumentTagOperation documentTagOperation = 
						documentTagItem.getDocumentTagOperation();
				if (DocumentTagOperation.UPDATE.equals(
						documentTagOperation)) {
					this.prisonTermService.updateDocumentTag(
							documentTagItem.getDocumentTag(), 
							documentTagItem.getName());
				} else if (DocumentTagOperation.CREATE.equals(
						documentTagOperation)) {
					this.prisonTermService.createDocumentTag(
							document, documentTagItem.getName());
				} else if (DocumentTagOperation.REMOVE.equals(
						documentTagOperation)) {
					this.prisonTermService.removeDocumentTag(
							documentTagItem.getDocumentTag());
				}
			}
		}
	}
	
	// Returns whether the current user has the specified role
	private boolean hasRole(final String role) {
		for (GrantedAuthority authority :
				SecurityContextHolder.getContext().getAuthentication()
					.getAuthorities()) {
			if (role.equals(authority.getAuthority())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Retrieves document file.
	 * 
	 * @param document - document
	 * @param httpServletResponse - HTTP Servlet response. */
	@RequestMapping(value = "/retrieveFile.html",
			method = RequestMethod.GET)
	@PreAuthorize("(hasRole('DOCUMENT_VIEW') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public void retrieveFile(
			@RequestParam(value = "document", required = true) 
			final Document document, 
			final HttpServletResponse httpServletResponse) {
		final byte[] bytes = this.prisonTermDocumentRetriever
				.retrieve(document);
		httpServletResponse.setContentType("application/octet-stream");
		httpServletResponse.setHeader("Content-Disposition", 
				"attachment; filename=\"" + document.getFilename() + "\"");
		try {
			OutputStream outputStream = httpServletResponse.getOutputStream();
			outputStream.write(bytes);
			outputStream.flush();
			outputStream.close();
		} catch (IOException ioException) {
			throw new RuntimeException(String.format(ERROR_WRITING_FILE_MSG,
					document.getFilename()));
		}
	}
	
	/* Exception handler methods. */
	
	/**
	 * Handles duplicate entity found exceptions.
	 * 
	 * @param exception duplicate entity found exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundExceptionn(
			final DuplicateEntityFoundException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				PRISON_TERM_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				exception);
	}
	
	/**
	 * Handles active prison terms exceptions.
	 * 
	 * @param exception active prison terms exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(ActivePrisonTermExistsException.class)
	public ModelAndView handleActivePrisonTermExistsException(
			final ActivePrisonTermExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				ACTIVE_PRISON_TERM_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				exception);
	}
	
	/* Init binder. */
	
	/**
	 * Init binder.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(PrisonTerm.class,
				this.prisonTermPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(PrisonTermDocumentAssociation.class,
				this.prisonTermDocumentAssociationPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Date.class, this.datePropertyEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(UserAccount.class, 
				this.userAccountPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Document.class,
				this.documentPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(DocumentTag.class,
				this.documentTagPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		
	}
	
}
