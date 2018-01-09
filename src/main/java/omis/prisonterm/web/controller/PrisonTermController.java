package omis.prisonterm.web.controller;

import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.prisonterm.domain.PrisonTerm;
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
import omis.stg.exception.InvolvedOffenderRequiredException;
import omis.user.domain.UserAccount;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Prison term controller.
 * 
 * @author Trevor Isles
 * @author Josh Divine
 * @version 0.1.1 (Oct 17, 2017)
 * @since OMIS 3.0
 */

@Controller
@RequestMapping("/prisonTerm")
@PreAuthorize("hasRole('USER')")
public class PrisonTermController {

	/* Model keys. */
	
	private static final String PRISON_TERMS_MODEL_KEY = "prisonTerms";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String PRISON_TERM_MODEL_KEY ="prisonTerm";
	
	private static final String PRISON_TERM_FORM_MODEL_KEY = "prisonTermForm";
	
	private static final String PRISON_TERM_STATUS_MODEL_KEY 
		= "prisonTermStatus";
	
	private static final String USER_ACCOUNTS_MODEL_KEY = "userAccounts";
	
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
	
	/* Message bundles. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.prisonterm.msgs.form";
	
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
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory datePropertyEditorFactory;
	
	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	
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
	
	/* Report names. */
	
	private static final String PRISON_TERM_LISTING_REPORT_NAME 
		= "/Legal/PrisonTerms/Prison_Term_Listing";
	
	private static final String PRISON_TERM_DETAILS_REPORT_NAME 
		= "/Legal/PrisonTerms/Prison_Term_Details";
	
	/* Report parameter names. */
	
	private static final String PRISON_TERM_LISTING_ID_REPORT_PARAM_NAME
		= "DOC_ID";
	
	private static final String PRISON_TERM_DETAILS_ID_REPORT_PARAM_NAME
		= "PRISON_TERM_ID";

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
	@PreAuthorize("hasRole('PRISON_TERM_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		List<PrisonTermSummary> prisonTerms = this.prisonTermReportService
				.summarizeByOffender(offender);
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
	 * @return model and view to screen that allows new prison term to be created
	 */
	@RequestContentMapping(nameKey = "prisonTermCreateScreenName",
			descriptionKey = "prisonTermCreateScreenDescription",
			messageBundle = "omis.prisonterm.msgs.prisonTerm",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html", 
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PRISON_TERM_CREATE') or hasRole('ADMIN')")
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
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "prisonTerm", required = true)
			final PrisonTerm prisonTerm) {
		PrisonTermForm prisonTermForm = new PrisonTermForm();
		prisonTermForm.setActionDate(prisonTerm.getActionDate());
		prisonTermForm.setPreSentenceCredits(prisonTerm
				.getPreSentenceCredits()); 
		prisonTermForm.setSentenceDate(prisonTerm.getSentenceDate()); 
		prisonTermForm.setSentenceTermYears(prisonTerm.getSentenceTermYears()); 
		prisonTermForm.setSentenceTermDays(prisonTerm.getSentenceTermDays()); 
		prisonTermForm.setParoleEligibilityDate(prisonTerm
				.getParoleEligibilityDate());
		prisonTermForm.setProjectedDischargeDate(prisonTerm
				.getProjectedDischargeDate()); 
		prisonTermForm.setMaximumDischargeDate(prisonTerm
				.getMaximumDischargeDate()); 
		prisonTermForm.setStatus(prisonTerm.getStatus()); 
		prisonTermForm.setSentenceToFollow(prisonTerm.getSentenceToFollow()); 
		prisonTermForm.setComments(prisonTerm.getComments());
		prisonTermForm.setVerificationUser(prisonTerm.getVerificationUser());
		prisonTermForm.setVerificationDate(prisonTerm.getVerificationDate());
		
		ModelAndView mav = this.prepareEditMav(prisonTermForm, 
				prisonTerm.getOffender());
		mav.addObject(PRISON_TERM_MODEL_KEY, prisonTerm);
		return mav;
	}
	
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
	
	/**
	 * Saves a new prison term.
	 * 
	 * @param offender offender for whom to create the prison term
	 * @param effectiveDate effective date of the prison term
	 * @param prisonTermForm form containing prison term information
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
	@PreAuthorize("hasRole('PRISON_TERM_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "effectiveDate", required = false)
				final Date effectiveDate, 
			final PrisonTermForm prisonTermForm,
			final BindingResult result)
				throws DuplicateEntityFoundException, ActivePrisonTermExistsException {
		this.prisonTermFormValidator.validate(prisonTermForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareEditMav(prisonTermForm, offender);
			mav.addObject(BindingResult.MODEL_KEY_PREFIX 
					+ PRISON_TERM_MODEL_KEY, result);
			return mav;
		}
		
		PrisonTerm prisonTerm = this.prisonTermService.create(offender, 
				prisonTermForm.getActionDate(),
				prisonTermForm.getPreSentenceCredits(),
				prisonTermForm.getSentenceDate(),
				prisonTermForm.getSentenceTermYears(), 
				prisonTermForm.getSentenceTermDays(),
				prisonTermForm.getParoleEligibilityDate(), 
				prisonTermForm.getProjectedDischargeDate(),
				prisonTermForm.getMaximumDischargeDate(), 
				prisonTermForm.getStatus(),
				prisonTermForm.getSentenceToFollow(),
				prisonTermForm.getComments(),
				prisonTermForm.getVerificationUser(), 
				prisonTermForm.getVerificationDate());
		return this.prepareListRedirect(prisonTerm.getOffender());
	}
	
	/**
	 * Updates an existing prison term.
	 * 
	 * @param prisonTerm prison term to update
	 * @param prisonTermForm form containing prison term information
	 * @param result binding result
	 * @return model and view to redirect to list URL
	 * @throws DuplicateEntityFoundException if an attempt to save a duplicate
	 * prison term is attempted
	 * @throws ActivePrisonTermExistsException if an active prison term already
	 * exists.
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PRISON_TERM_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
				final PrisonTermForm prisonTermForm,
				final BindingResult result) 
						throws DuplicateEntityFoundException, 
						InvolvedOffenderRequiredException, ActivePrisonTermExistsException {
		this.prisonTermFormValidator.validate(prisonTermForm, 
				result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareEditMav(prisonTermForm, offender);
			mav.addObject(BindingResult.MODEL_KEY_PREFIX 
					+ PRISON_TERM_MODEL_KEY, result);
			return mav;
		}
		this.prisonTermService.update(
				prisonTermForm.getPrisonTerm(), 
				prisonTermForm.getActionDate(),
				prisonTermForm.getPreSentenceCredits(), 
				prisonTermForm.getSentenceDate(), 
				prisonTermForm.getSentenceTermYears(), 
				prisonTermForm.getSentenceTermDays(), 
				prisonTermForm.getParoleEligibilityDate(), 
				prisonTermForm.getProjectedDischargeDate(), 
				prisonTermForm.getMaximumDischargeDate(), 
				prisonTermForm.getStatus(), 
				prisonTermForm.getSentenceToFollow(), 
				prisonTermForm.getComments(),
				prisonTermForm.getVerificationUser(),
				prisonTermForm.getVerificationDate());
		return new ModelAndView(String.format(LIST_REDIRECT_VIEW_NAME, 
				offender.getId()));
	}
	
	/**
	 * Removes an existing prison term. 
	 * 
	 * @param prisonTerm prison term to remove
	 * @return redirect to listing screen
	 */
	@RequestContentMapping(nameKey = "prisonTermRemove",
			descriptionKey = "prisonTermRemoveDescription",
			messageBundle = "omis.prisonterm.msgs.prisonTerm",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/remove.html")
	@PreAuthorize("hasRole('PRISON_TERM_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "prisonTerm", required = true)
				final PrisonTerm prisonTerm,
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		this.prisonTermService.remove(prisonTerm);
		return this.prepareListRedirect(offender);
	}
	
	// Prepares security threat group activity screen redirect
		private ModelAndView prepareListRedirect(final Offender offender) {
			return new ModelAndView(String.format(LIST_REDIRECT_VIEW_NAME, 
					offender.getId()));
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
	
	/* ActionMenus */
	
	/**
	 * Returns the content for the prison terms action menu. 
	 * 
	 * @param offender offender
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
		binder.registerCustomEditor(Date.class, this.datePropertyEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(UserAccount.class, 
				this.userAccountPropertyEditorFactory
				.createPropertyEditor());
		
	}
	
}
