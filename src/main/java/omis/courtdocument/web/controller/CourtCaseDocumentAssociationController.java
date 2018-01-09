package omis.courtdocument.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
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
import omis.courtcase.domain.CourtCase;
import omis.courtdocument.domain.CourtCaseDocumentAssociation;
import omis.courtdocument.domain.CourtDocumentCategory;
import omis.courtdocument.service.CourtCaseDocumentAssociationService;
import omis.courtdocument.validator.CourtCaseDocumentAssociationFormValidator;
import omis.courtdocument.web.form.CourtCaseDocumentAssociationForm;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.io.DocumentPersister;
import omis.document.io.impl.DocumentFilenameGenerator;
import omis.document.web.form.DocumentTagItem;
import omis.document.web.form.DocumentTagOperation;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;

/** Controller for court case documents actions.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 23, 2015)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/courtCase/document")
@PreAuthorize("(hasRole('USER') and hasRole('COURT_DOCUMENT_MODULE')) "
		+ "or hasRole('ADMIN')")
public class CourtCaseDocumentAssociationController {
	/** Document association type. */
	public static final String DOCUMENT_ASSOCIATION_TYPE = "courtCase";
	
	/* Views. */
	private static final String DOCUMENT_PROFILE_REDIRECT = 
			"redirect:/documents/profile.html"
			+ "?offender=%1$d"
			+ "&documentAssociationType=%2$s";
	
	//private static final String COURT_CASE_DOCUMENT_REDIRECT = "redirect:/courtCase/document/list.html"l
	private static final String EDIT_VIEW_NAME = "/courtDocument/edit";
	private static final String LIST_VIEW_NAME = 
			"/courtDocument/list";
	private static final String ACTION_MENU_VIEW_NAME = 
			"courtDocument/includes/actionMenu";
	private static final String COURT_CASE_DOCUMENT_ACTION_MENU_VIEW_NAME =
			"courtDocument/includes/documentActionMenu";
	private static final String CREATE_ACTION_MENU_VIEW_NAME =
			"courtDocument/includes/createActionMenu";
	
	
	/* Model Keys. */
	private static final String CATEGORIES_MODEL_KEY = 
			"categories";
	private static final String COURT_CASE_DOCUMENT_ASSOCIATION_FORM_MODEL_KEY =
			"form";
	private static final String COURT_CASES_MODEL_KEY = "courtCases";
	private static final String LIST_MODEL_KEY = 
			"courtCaseDocumentAssociationSummaries";
	private static final String COURT_CASE_DOCUMENT_ASSOCIATION_MODEL_KEY =
			"courtCaseDocumentAssociation";
	private static final String EDITING_MODEL_KEY = "editing";
	private static final String OFFENDER_MODEL_KEY = "offender";
	private static final String FILTER_MODEL_KEY = "filter";
	
	@Autowired private CourtCaseDocumentAssociationService 
		courtCaseDocumentAssociationService;
	@Autowired private CourtCaseDocumentAssociationFormValidator 
	courtCaseDocumentAssociationFormValidator;
	@Autowired private OffenderSummaryModelDelegate 
	offenderSummaryModelDelegate;
	@Autowired private DocumentFilenameGenerator documentFilenameGenerator;
	@Autowired 
	@Qualifier("documentPersistor")
	private DocumentPersister documentPersister;
	
	@Autowired private OffenderPropertyEditorFactory 
		offenderPropertyEditorFactory;
	@Autowired private PropertyEditorFactory courtCasePropertyEditorFactory;
	@Autowired private PropertyEditorFactory 
		courtDocumentCategoryPropertyEditorFactory;
	@Autowired private PropertyEditorFactory 
		courtCaseDocumentAssociationPropertyEditorFactory;
	@Autowired private PropertyEditorFactory
		documentTagPropertyEditorFactory;
	@Autowired private CustomDateEditorFactory customDateEditorFactory;
	
	/* Report names. */
	
	private static final String COURT_DOCUMENT_LISTING_REPORT_NAME
	    = "/Legal/CourtDocuments/Court_Document_Listing";
	
	private static final String COURT_DOCUMENT_DETAILS_REPORT_NAME
    = "/Legal/CourtDocuments/Court_Document_Details";	
	
	/* Report parameters. */
	
	private static final String COURT_DOCUMENT_LISTING_ID_REPORT_PARAM_NAME
	   = "OFFENDER_ID";
	
	private static final String COURT_DOCUMENT_DETAILS_ID_REPORT_PARAM_NAME
	   = "COURT_DOC_ID";	
	
	/* Report runners. */	
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;

	
	/** Constructor. */
	public CourtCaseDocumentAssociationController() { }
	
	/** Create view for court document association offender.
	 * @param offender - offender.
	 * @param filter - profile filter.
	 * @return court document association edit. */
	@RequestMapping(value = "create.html", method = RequestMethod.GET)
	@RequestContentMapping(nameKey = "courtCaseDocumentCreateScreenName",
		descriptionKey = "courtCaseDocumentCreateScreenDescription",
		messageBundle = "omis.document.msgs.courtCaseDocument",
		screenType = RequestContentType.DETAIL_SCREEN)
	@PreAuthorize("(hasRole('COURT_CASE_DOCUMENT_CREATE') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true) 
			final Offender offender,
			@RequestParam(value = "filter", required = false)
			final String filter) {
		ModelMap modelMap = new ModelMap();

		final CourtCaseDocumentAssociationForm courtCaseDocumentAssociationForm
			= new CourtCaseDocumentAssociationForm();
		this.prepareEditMap(modelMap, courtCaseDocumentAssociationForm, 
				offender);
		return new ModelAndView(EDIT_VIEW_NAME, modelMap);
	}
	
	/** Saves court case document association.
	 * association form.
	 * @param offender - offender.
	 * @param filter - profile filter.
	 * @param courtCaseDocumentAssociationForm - court case document.
	 * @param bindingResult - binding result.
	 * @return court case document association list.
	 * @throws DuplicateEntityFoundException - when document with given title
	 * exists or court document with given document exists. 
	 * @throws IOException - When file cannot be saved. */
	@RequestMapping(value = "create.html", method = RequestMethod.POST)
	@RequestContentMapping(nameKey = "courtCaseDocumentSaveScreenName",
		descriptionKey = "courtCaseDocumentSaveScreenDescription",
		messageBundle = "omis.document.msgs.courtCaseDocument",
		screenType = RequestContentType.LISTING_SCREEN)
	@PreAuthorize("(hasRole('COURT_CASE_DOCUMENT_SAVE') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "filter", required = false)
			final String filter,
			final CourtCaseDocumentAssociationForm 
			courtCaseDocumentAssociationForm,
			final BindingResult bindingResult)
	throws DuplicateEntityFoundException, IOException {
		final ModelAndView mav;
		this.courtCaseDocumentAssociationFormValidator.validate(
				courtCaseDocumentAssociationForm, bindingResult);
		if (bindingResult.hasErrors()) {
			ModelMap modelMap = new ModelMap();
			this.prepareErrorsMap(modelMap, bindingResult);
			this.prepareEditMap(modelMap, courtCaseDocumentAssociationForm, 
					offender);
			mav = new ModelAndView(EDIT_VIEW_NAME, modelMap);
		} else {
			final String fileExtension = courtCaseDocumentAssociationForm
					.getFileExtension();
			this.documentFilenameGenerator.setExtension(fileExtension);
			final String filename = this.documentFilenameGenerator.generate();
			final Document document = this.courtCaseDocumentAssociationService
					.createDocument(new Date(), 
							filename, fileExtension,
							courtCaseDocumentAssociationForm.getTitle());
			this.documentPersister.persist(document, 
					courtCaseDocumentAssociationForm.getData());
			CourtCaseDocumentAssociation courtCaseDocumentAssociation = 
					this.courtCaseDocumentAssociationService
					.createCourtCaseDocumentAssociation(
					courtCaseDocumentAssociationForm.getCourtCase(),
					document, courtCaseDocumentAssociationForm.getDate(), 
					courtCaseDocumentAssociationForm
					.getCourtDocumentCategory());
			this.processDocumentTags(courtCaseDocumentAssociationForm
					.getDocumentTagItems(), document);
			mav = new ModelAndView(String.format(DOCUMENT_PROFILE_REDIRECT, 
					courtCaseDocumentAssociation.getCourtCase().getDocket()
					.getPerson().getId(), this.determineProfileFilter(filter)));
		}
		return mav;
	}
	
	/** Edits court case document association.
	 * @param courtCaseDocumentAssociation - court case document association.
	 * @return court case document association edit. */
	@RequestMapping(value = "edit.html", method = RequestMethod.GET)
	@RequestContentMapping(nameKey = "courtCaseDocumentEditScreenName",
		descriptionKey = "courtCaseDocumentEditScreenDescription",
		messageBundle = "omis.document.msgs.courtCaseDocument",
		screenType = RequestContentType.DETAIL_SCREEN)
	@PreAuthorize("(hasRole('COURT_CASE_DOCUMENT_EDIT') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "courtCaseDocumentAssociation", 
				required = true)
			final CourtCaseDocumentAssociation courtCaseDocumentAssociation) {
		final ModelMap modelMap = new ModelMap();
		CourtCaseDocumentAssociationForm courtCaseDocumentAssociationForm =
				new CourtCaseDocumentAssociationForm();
	
		this.prepareForm(courtCaseDocumentAssociationForm, 
				courtCaseDocumentAssociation.getCourtCase(), 
				courtCaseDocumentAssociation.getDate(),
				courtCaseDocumentAssociation.getCategory(),
				courtCaseDocumentAssociation.getDocument().getTitle(), 
				this.courtCaseDocumentAssociationService
				.findDocumentTagsByDocument(courtCaseDocumentAssociation
						.getDocument()));
		this.prepareEditMap(modelMap, courtCaseDocumentAssociationForm, 
				(Offender) courtCaseDocumentAssociation.getCourtCase()
				.getDocket().getPerson(), courtCaseDocumentAssociation, 
				this.courtCaseDocumentAssociationService
				.findDocumentTagsByDocument(courtCaseDocumentAssociation
						.getDocument()));
		modelMap.put(EDITING_MODEL_KEY, true);
		return new ModelAndView(EDIT_VIEW_NAME, modelMap);
	}
	
	
	/** updates court case document association.
	 * @param courtCaseDocumentAssociation - court case document association.
	 * @param filter - profile filter.
	 * @param courtCaseDocumentAssociationForm - court case document association
	 * form.
	 * @param bindingResult - binding result. 
	 * @return document profile. 
	 * @throws DuplicateEntityFoundException - When court case document 
	 * association for court case and document already exists.*/
	@RequestMapping(value = "edit.html", method = RequestMethod.POST)
	@RequestContentMapping(nameKey = "courtCaseDocumentUpdateScreenName",
		descriptionKey = "courtCaseDocumentUpdateScreenDescription",
		messageBundle = "omis.document.msgs.courtCaseDocument",
		screenType = RequestContentType.DETAIL_SCREEN)
	@PreAuthorize("(hasRole('COURT_CASE_DOCUMENT_UPDATE') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public ModelAndView update(
		@RequestParam(value = "courtCaseDocumentAssociation", required = true)
		final CourtCaseDocumentAssociation 
			courtCaseDocumentAssociation,
			@RequestParam(value = "filter", required = false)
			final String filter,
			final CourtCaseDocumentAssociationForm 
				courtCaseDocumentAssociationForm, 
			final BindingResult bindingResult) 
					throws DuplicateEntityFoundException {
		final ModelAndView mav;
		this.courtCaseDocumentAssociationFormValidator.validate(
				courtCaseDocumentAssociationForm, bindingResult);
		if (bindingResult.hasErrors()) {
			ModelMap modelMap = new ModelMap();
			this.prepareErrorsMap(modelMap, bindingResult);
			this.prepareEditMap(modelMap, courtCaseDocumentAssociationForm, 
					(Offender) courtCaseDocumentAssociation.getCourtCase()
					.getDocket().getPerson());
			modelMap.put(COURT_CASE_DOCUMENT_ASSOCIATION_MODEL_KEY, 
					courtCaseDocumentAssociation);
			modelMap.put(EDITING_MODEL_KEY, true);
			mav = new ModelAndView(EDIT_VIEW_NAME, modelMap);
		} else {
			Document document = courtCaseDocumentAssociation.getDocument();
			this.courtCaseDocumentAssociationService.updateDocument(document, 
					courtCaseDocumentAssociationForm.getTitle(),
					courtCaseDocumentAssociationForm.getDate());
			this.courtCaseDocumentAssociationService
				.updateCourtCaseDocumentAssociation(
					courtCaseDocumentAssociation,
					courtCaseDocumentAssociationForm.getCourtCase(),
					document, courtCaseDocumentAssociationForm.getDate(), 
					courtCaseDocumentAssociationForm
					.getCourtDocumentCategory());
			this.processDocumentTags(
					courtCaseDocumentAssociationForm.getDocumentTagItems(), 
					document);
			mav = new ModelAndView(String.format(DOCUMENT_PROFILE_REDIRECT, 
					courtCaseDocumentAssociation.getCourtCase().getDocket()
					.getPerson().getId(), this.determineProfileFilter(filter)));
		}
		return mav;
	}
	
	/** Lists court case documents by offender.
	 * @param offender - offender.
	 * @return list of offenders court case documents. */
	@RequestMapping(value = "list.html", method = RequestMethod.GET)
	@RequestContentMapping(nameKey = "courtCaseDocumentListScreenName",
		descriptionKey = "courtCaseDocumentListScreenDescription",
		messageBundle = "omis.document.msgs.courtCaseDocument",
		screenType = RequestContentType.LISTING_SCREEN)
	@PreAuthorize("(hasRole('COURT_CASE_DOCUMENT_LIST') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public ModelAndView list(@RequestParam(value = "offender", required = true)
		final Offender offender) {
		ModelMap modelMap = new ModelMap();
		ModelAndView mav;
		this.prepareListMap(modelMap, offender);
		mav = new ModelAndView(LIST_VIEW_NAME, modelMap);
		return mav;
	}
	
	/** Removes court case document association.
	 * @param courtCaseDocumentAssociation - court case document association.
	 * @param filter - profile filter.
	 * @return document associations profile. */
	@RequestMapping(value = "remove.html", method = RequestMethod.GET)
	@RequestContentMapping(nameKey = "courtDocumentRemoveScreenName",
		descriptionKey = "courtDocumentRemoveScreenDescription",
		messageBundle = "omis.courtCase.msgs.document",
		screenType = RequestContentType.PROFILE_SCREEN)
	@PreAuthorize("(hasRole('DOCUMENT_REMOVE') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "courtCaseDocumentAssociation", 
			required = true)
			final CourtCaseDocumentAssociation courtCaseDocumentAssociation,
			@RequestParam(value = "filter", required = false)
			final String filter) {
		final ModelAndView mav;
		this.courtCaseDocumentAssociationService
		.removeCourtCaseDocumentAssociation(courtCaseDocumentAssociation);
		mav = new ModelAndView(String.format(DOCUMENT_PROFILE_REDIRECT, 
				courtCaseDocumentAssociation.getCourtCase().getDocket()
				.getPerson().getId(), this.determineProfileFilter(filter)));
		return mav;
	}
	
	/** court document profile action menu.
	 * @param offender - offender.
	 * @return action menu. */
	@RequestMapping(value = "actionMenu.html", method = RequestMethod.GET)
	@RequestContentMapping(nameKey = 
		"courtDocumentsProfileActionMenuScreenName",
		descriptionKey = "courtDocumentsProfileActionMenudScreenDescription",
		messageBundle = "omis.document.msgs.document",
		screenType = RequestContentType.AJAX_REQUEST)
	@PreAuthorize("(hasRole('DOCUMENT_PROFILE') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public ModelAndView actionMenu(
			@RequestParam(value = "offender", required = true) 
			final Offender offender) {
		final ModelAndView mav;
		final ModelMap modelMap = new ModelMap();
		this.prepareOffenderModel(modelMap, offender);
		mav = new ModelAndView(ACTION_MENU_VIEW_NAME, modelMap);
		return mav;
	}
	
	/** Court case document action menu.
	 * @param courtCaseDocumentAssociation - court case document association.
	 * @param filter - profile filter.
	 * @return action menu. */
	@RequestMapping(value = "documentActionMenu.html", 
			method = RequestMethod.GET)
	@RequestContentMapping(nameKey = "courtDocumentActionMenuScreenName",
		descriptionKey = "courtDocumentActionMenuScreenDescription",
		messageBundle = "omis.document.msgs.document",
		screenType = RequestContentType.AJAX_REQUEST)
	@PreAuthorize("(hasRole('DOCUMENT_PROFILE') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public ModelAndView documentActionMenu(
			@RequestParam(value = "courtCaseDocumentAssociation", 
			required = true)
			final CourtCaseDocumentAssociation courtCaseDocumentAssociation,
			@RequestParam(value = "filter", required = false)
			final String filter) {
		final ModelAndView mav;
		final ModelMap modelMap = new ModelMap();
		this.prepareFilterModel(modelMap, filter);
		modelMap.put(COURT_CASE_DOCUMENT_ASSOCIATION_MODEL_KEY, 
				courtCaseDocumentAssociation);
		mav = new ModelAndView(COURT_CASE_DOCUMENT_ACTION_MENU_VIEW_NAME, 
				modelMap);
		return mav;
	}
	
	/** Court document edit action menu.
	 * @param offender - offender.
	 * @return action menu. */
	@RequestMapping(value = "createActionMenu.html", method = RequestMethod.GET)
	@RequestContentMapping(nameKey = "courtDocumentCreateActionMenuScreenName",
		descriptionKey = "courtDocumentCreateActionMenuScreenDescription",
		messageBundle = "oims.document.msgs.document",
		screenType = RequestContentType.AJAX_REQUEST)
	@PreAuthorize("(hasRole('COURT_CASE_DOCUMENT_CREATE') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public ModelAndView createActionMenu(
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		final ModelAndView mav;
		final ModelMap modelMap = new ModelMap();
		this.prepareOffenderModel(modelMap, offender);
		mav = new ModelAndView(CREATE_ACTION_MENU_VIEW_NAME, modelMap);
		return mav;
	}
		
	
	/* Prepares form. */
	private void  prepareForm(
			final CourtCaseDocumentAssociationForm 
				courtCaseDocumentAssociationForm, 
			final CourtCase courtCase, final Date date, 
			final CourtDocumentCategory courtDocumentCategory, 
			final String title, final List<DocumentTag> documentTags) {
		courtCaseDocumentAssociationForm.setCourtCase(courtCase);
		courtCaseDocumentAssociationForm.setDate(date);
		courtCaseDocumentAssociationForm.setCourtDocumentCategory(
				courtDocumentCategory);
		courtCaseDocumentAssociationForm.setTitle(title);
		List<DocumentTagItem> documentTagItems = 
				new ArrayList<DocumentTagItem>();
		for (int x = 0; x < documentTags.size(); x++) {
			final DocumentTagItem documentTagItem = new DocumentTagItem();
			documentTagItem.setDocumentTag(documentTags.get(x));
			documentTagItem.setDocumentTagOperation(
					DocumentTagOperation.UPDATE);
			documentTagItem.setName(documentTags.get(x).getName());
			documentTagItems.add(documentTagItem);
		}
		courtCaseDocumentAssociationForm.setDocumentTagItems(documentTagItems);
	}
	
	/** prepares list model. */
	private void prepareListMap(final ModelMap modelMap, 
			final Offender offender) {
		this.prepareOffenderModel(modelMap, offender);
		this.prepareOffenderSummarModel(modelMap, offender);
		this.prepareFilterModel(modelMap, DOCUMENT_ASSOCIATION_TYPE);
		modelMap.put(LIST_MODEL_KEY, this
					.courtCaseDocumentAssociationService
					.findCourtCaseDocumentAssociationSummariesByOffender(
							offender));
			
	}
	
	/* Prepares model for view. */
	private void prepareEditMap(final ModelMap modelMap, 
			final CourtCaseDocumentAssociationForm 
				courtCaseDocumentAssociationForm, 
			final Offender offender) {
		modelMap.put(COURT_CASES_MODEL_KEY, 
				this.courtCaseDocumentAssociationService
					.findCourtCasesByOffender(offender));
		modelMap.put(CATEGORIES_MODEL_KEY, 
				this.courtCaseDocumentAssociationService.findCategories());
		modelMap.put(COURT_CASE_DOCUMENT_ASSOCIATION_FORM_MODEL_KEY, 
				courtCaseDocumentAssociationForm);
		this.prepareOffenderSummarModel(modelMap, offender);
	}
	
	/* Prepared model for view. */
	private void prepareEditMap(final ModelMap modelMap,
			final CourtCaseDocumentAssociationForm 
			courtCaseDocumentAssociationForm, final Offender offender, 
			final CourtCaseDocumentAssociation courtCaseDocumentAssociation,
			final List<DocumentTag> documentTags) {
		this.prepareEditMap(modelMap, courtCaseDocumentAssociationForm,
				offender);
		modelMap.put(COURT_CASE_DOCUMENT_ASSOCIATION_MODEL_KEY, 
				courtCaseDocumentAssociation);
	}
		
	
	/* Prepares errors for view. */
	private void prepareErrorsMap(final ModelMap modelMap, 
			final BindingResult bindingResult) {
		modelMap.addAttribute(BindingResult.MODEL_KEY_PREFIX 
				+ COURT_CASE_DOCUMENT_ASSOCIATION_FORM_MODEL_KEY, 
				bindingResult);
	}
	
	/* Prepares model with offender. */
	private void prepareOffenderModel(final ModelMap modelMap, 
			final Offender offender) {
		modelMap.put(OFFENDER_MODEL_KEY, offender);
	}
	
	/* Prepares offender summary model. */
	private void prepareOffenderSummarModel(final ModelMap modelMap, 
			final Offender offender) {
		this.offenderSummaryModelDelegate.add(modelMap, offender);
	}
	
	/** Prepares filter model. */
	private void prepareFilterModel(final ModelMap modelMap, 
			final String filter) {
		if (filter != null) {
			modelMap.put(FILTER_MODEL_KEY, filter);
		}
	}
	
	/* Determine filter value. */
	private String determineProfileFilter(final String filterValue) {
		final String filter;
		
		if (filterValue != null) {
			filter = filterValue;
		} else {
			filter = DOCUMENT_ASSOCIATION_TYPE;
		}
		return filter;
	}
	
	/* Process document tag items. */
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
				if (documentTagOperation == DocumentTagOperation.UPDATE) {
					this.courtCaseDocumentAssociationService.updateDocumentTag(
							documentTagItem.getDocumentTag(), 
							documentTagItem.getName());
				} else if (documentTagOperation == DocumentTagOperation.CREATE) {
					this.courtCaseDocumentAssociationService.createDocumentTag(
							document, documentTagItem.getName());
				} else if (documentTagOperation == DocumentTagOperation.REMOVE) {
					this.courtCaseDocumentAssociationService.removeTag(
							documentTagItem.getDocumentTag());
				}
			}
		}
	}

	/**
	 * Returns the report for the specified offenders associated court documents.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/courtDocumentListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("(hasRole('COURT_CASE_DOCUMENT_VIEW') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportCourtDocumentListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(COURT_DOCUMENT_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getId()));
		byte[] doc = this.reportRunner.runReport(
				COURT_DOCUMENT_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}

	/**
	 * Returns the report for the specified court document.
	 * 
	 * @param CourtCaseDocumentAssociation CourtCaseDocumentAssociation
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/courtDocumentDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("(hasRole('COURT_CASE_DOCUMENT_VIEW') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportCourtDocumentDetails(@RequestParam(
			value = "courtCaseDocumentAssociation", required = true)
			final CourtCaseDocumentAssociation courtCaseDocumentAssociation,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(COURT_DOCUMENT_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(courtCaseDocumentAssociation.getId()));
		byte[] doc = this.reportRunner.runReport(
				COURT_DOCUMENT_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
		
	
	/** Init binder. 
	 * @param binder - binder. */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(CourtCase.class,
				this.courtCasePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(CourtDocumentCategory.class, 
				this.courtDocumentCategoryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(CourtCaseDocumentAssociation.class,
				this.courtCaseDocumentAssociationPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Date.class, 
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(DocumentTag.class,
				this.documentTagPropertyEditorFactory.createPropertyEditor());
	}
		
}