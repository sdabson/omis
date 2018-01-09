package omis.commitstatus.web.controller;

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

import omis.audit.domain.VerificationMethod;
import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.commitstatus.domain.CommitStatus;
import omis.commitstatus.domain.CommitStatusTerm;
import omis.commitstatus.exception.CommitStatusTermConflictException;
import omis.commitstatus.exception.CommitStatusTermExistsAfterException;
import omis.commitstatus.exception.CommitStatusTermExistsException;
import omis.commitstatus.report.CommitStatusReportService;
import omis.commitstatus.report.CommitStatusTermSummary;
import omis.commitstatus.service.CommitStatusTermService;
import omis.commitstatus.web.form.CommitStatusForm;
import omis.commitstatus.web.validator.CommitStatusFormValidator;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.relationship.exception.ReflexiveRelationshipException;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.residence.exception.PrimaryResidenceExistsException;
import omis.residence.exception.ResidenceStatusConflictException;
import omis.user.domain.UserAccount;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for Commit Status.
 * 
 * @author Yidong Li
 * @version 0.1.3 (June 7, 2017)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/commitStatus")
@PreAuthorize("hasRole('USER')")
public class CommitStatusController {
	/* views */
	private static final String EDIT_VIEW_NAME = "commitStatus/edit";
	private static final String LIST_VIEW_NAME = "commitStatus/list";
	private static final String COMMIT_STATUS_LIST_ACTION_MENU_VIEW_NAME
		= "commitStatus/includes/commitStatusListScreenActionMenu";
	private static final String COMMIT_STATUS_EDIT_ACTION_MENU_VIEW_NAME
		= "commitStatus/includes/commitStatusCreateEditScreenActionMenu";
	private static final String COMMIT_STATUS_ROW_ACTION_MENU_VIEW_NAME
		= "commitStatus/includes/commitStatusRowActionMenuNew";
	
	/* model keys */
	private static final String COMMIT_STATUSES_MODEL_KEY="commitStatuses";
	private static final String COMMIT_STATUS_TERM_SUMMARIES_MODEL_KEY 
		= "commitStatusTermSummaries";
	private static final String COMMIT_STATUS_FORM_MODEL_KEY 
		= "commitStatusForm";
	private static final String COMMIT_STATUS_TERM_MODEL_KEY= "commitStatusTerm";
	private static final String OFFENDER_MODEL_KEY = "offender";
	private static final String FLAG_MODEL_KEY = "flag";
	
	/* Message key. */
	private static final String COMMIT_STATUS_TERM_EXISTS_MESSAGE_KEY 
		= "commitstatustermalreadyexist";
	private static final String COMMIT_STATUS_TERM_EXISTS_AFTER_MESSAGE_KEY 
		= "commitstatustermexistafter";
	
	private static final String COMMIT_STATUS_TERM_CONFLICT_MESSAGE_KEY
		= "commitstatustermconflict";
						
	/* Redirects. */
	private static final String LIST_REDIRECT
		= "redirect:/commitStatus/list.html?offender=%d";
	
	/* Bundles. */
	private static final String ERROR_BUNDLE_NAME = "omis.commitstatus.msgs.form";
	
	/* Property editor. */
	@Autowired
	@Qualifier("commitStatusPropertyEditorFactory")
	private PropertyEditorFactory commitStatusPropertyEditorFactory;
	
	@Autowired
	@Qualifier("commitStatusTermPropertyEditorFactory")
	private PropertyEditorFactory commitStatusTermPropertyEditorFactory;
	
	@Autowired
	@Qualifier("verificationMethodPropertyEditorFactory")
	private PropertyEditorFactory verificationMethodPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/* Services. */
	@Autowired
	@Qualifier("commitStatusTermService")
	private CommitStatusTermService commitStatusTermService;
	
	@Autowired
	@Qualifier("commitStatusReportService")
	private CommitStatusReportService commitStatusReportService;
	
	/* Helpers. */ 
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Validator */
	@Autowired
	@Qualifier("commitStatusFormValidator")
	private CommitStatusFormValidator commitStatusFormValidator;
	
/* Report names. */
	
	private static final String COMMIT_STATUS_LISTING_REPORT_NAME 
		= "/Legal/CommitStatus/Commit_Status_Listing";

	private static final String COMMIT_STATUS_DETAILS_REPORT_NAME 
		= "/Legal/CommitStatus/Commit_Status_Details";

	/* Report parameter names. */
	
	private static final String COMMIT_STATUS_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String COMMIT_STATUS_DETAILS_ID_REPORT_PARAM_NAME 
		= "COMMIT_STATUS_ID";
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/* Constructor. */
	/** Instantiates a default commit status controller. */
	public CommitStatusController() {
		// Default instantiation
	}
	
	/**
	 * Displays screen to create a new commit status term.
	 * 
	 * @param offender offender for whom to create a new family association
	 * @return model and view to create a new family association
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('COMMIT_STATUS_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(
		@RequestParam(value = "offender", required = true) 
		final Offender offender) {
		CommitStatusForm commitStatusForm = new CommitStatusForm();
		return this.prepareEditMav(commitStatusForm, offender, true);
	}
	
	/**
	 * Displays a list of commit status terms.
	 * 
	 * @param offender offender
	 * @return view to display the list of commit status terms
	 */
	@RequestMapping(value="/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('COMMIT_STATUS_LIST') or hasRole('ADMIN')")
	public ModelAndView list(@RequestParam(value = "offender", required = true) 
		final Offender offender) {
		List<CommitStatusTermSummary> commitStatusTermSummaries
			= this.commitStatusReportService.summarizeByOffender(offender);
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		mav.addObject(COMMIT_STATUS_TERM_SUMMARIES_MODEL_KEY,
			commitStatusTermSummaries);

		mav.addObject(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/**
	 * Saves a new created commit status term.
	 * 
	 * @param offender offender
	 * @param commitStatusForm commit status form
	 * @return redirect to list employment history by offender
	 * @throws DuplicateEntityFoundException if the vehicle association exists
	 * @throws ReflexiveRelationshipException 
	 * @throws ResidenceStatusConflictException 
	 * @throws PrimaryResidenceExistsException 
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('COMMIT_STATUS_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
		@RequestParam(value = "offender", required = true)
		final Offender offender,
		final CommitStatusForm commitStatusForm,
		final BindingResult result) throws CommitStatusTermExistsException, 
			CommitStatusTermConflictException, 
			CommitStatusTermExistsAfterException {
		this.commitStatusFormValidator.validate(commitStatusForm, result);
		if(result.hasErrors()){
			return this.prepareRedisplayEditMav(commitStatusForm, offender, 
				result);
		}
		DateRange dateRange = new DateRange();
		dateRange.setStartDate(commitStatusForm.getStartDate());
		dateRange.setEndDate(commitStatusForm.getEndDate());
		this.commitStatusTermService.create(offender, 
			commitStatusForm.getCommitStatus(), dateRange);
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}
	
	/** Edit an existing commit status term. 
	 * @param commitStatusTerm commit status term.
	 * @param offender offender
	 * @return edited commit status term */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('COMMIT_STATUS_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(
		@RequestParam(value = "commitStatusTerm", required = true)
		final CommitStatusTerm commitStatusTerm){
		CommitStatusForm commitStatusForm
			= new CommitStatusForm();
		commitStatusForm.setCommitStatus(commitStatusTerm.getStatus());
		commitStatusForm.setEndDate(commitStatusTerm.getDateRange()
			.getEndDate());
		commitStatusForm.setStartDate(commitStatusTerm.getDateRange()
			.getStartDate());
		ModelAndView mav = this.prepareEditMav(commitStatusForm, 
				commitStatusTerm.getOffender(), false);
		mav.addObject(COMMIT_STATUS_TERM_MODEL_KEY, commitStatusTerm);
		return mav;
	}
	
	/**
	 * Updates an existing commit status term.
	 * 
	 * @param commitStatusForm commit status form
	 * @param commitStatusTerm commit status term
	 * @param result binding result
	 * @return redirect to list of commit status terms
	 * @throws CommitStatusTermExistsException,CommitStatusTermConflictException, 
	 * CommitStatusTermExistsAfterException
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('COMMIT_STATUS_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
		@RequestParam(value = "commitStatusTerm", required = true)
		final CommitStatusTerm commitStatusTerm,
		final CommitStatusForm commitStatusForm,
		final BindingResult result) throws CommitStatusTermExistsException, 
		CommitStatusTermConflictException, CommitStatusTermExistsAfterException{	
		this.commitStatusFormValidator.validate(commitStatusForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayEditMav(commitStatusForm, 
				commitStatusTerm.getOffender(),	result);
		}
		DateRange dateRange = new DateRange();
		dateRange.setStartDate(commitStatusForm.getStartDate());
		dateRange.setEndDate(commitStatusForm.getEndDate());
		this.commitStatusTermService.update(commitStatusTerm, 
			commitStatusForm.getCommitStatus(), dateRange);
		return new ModelAndView(String.format(LIST_REDIRECT, 
			commitStatusTerm.getOffender().getId()));	
	}
	
	/**
	 * Removes an existing commit status term.
	 * 
	 * @param commitStatusTerm commit status term
	 * @param offender offender
	 * @return redirect to a list commit status terms
	 */
	@RequestMapping("/remove.html")
	@PreAuthorize("hasRole('COMMIT_STATUS_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
		@RequestParam(value = "commitStatusTerm", required = true)
			final CommitStatusTerm commitStatusTerm) {
		this.commitStatusTermService.remove(commitStatusTerm);
		return new ModelAndView(String.format(LIST_REDIRECT, 
			commitStatusTerm.getOffender().getId()));
	}
	
	private ModelAndView prepareEditMav(
		final CommitStatusForm commitStatusForm,
		final Offender offender, final Boolean flag){
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		List<CommitStatus> commitStatuses = this.commitStatusTermService
			.findStatuses();
		mav.addObject(COMMIT_STATUSES_MODEL_KEY, commitStatuses);
		mav.addObject(COMMIT_STATUS_FORM_MODEL_KEY, commitStatusForm);
		mav.addObject(FLAG_MODEL_KEY, flag);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return new ModelAndView(EDIT_VIEW_NAME, mav.getModelMap());
	}	
	
	// Prepares redisplay edit model and view
	private ModelAndView prepareRedisplayEditMav(
		final CommitStatusForm commitStatusForm,
		final Offender offender, final BindingResult result) {
		ModelAndView mav = this.prepareEditMav(commitStatusForm, offender, false);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
			+ COMMIT_STATUS_FORM_MODEL_KEY, result);
		return mav;
	}	
	
	/**
	 * Returns a view for action menu pertaining to the 
	 * specified offender on list screen.
	 * 
	 * @param offender offender
	 * @return model and view for family associations action menu
	 */
	@RequestMapping(value = "/commitStatusListActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView commitStatusListActionMenu(@RequestParam(
		value = "offender",	required = true) final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(COMMIT_STATUS_LIST_ACTION_MENU_VIEW_NAME, map);
	}	
	
	/**
	 * Returns a view for action menu pertaining to the 
	 * specified offender on edit screen.
	 * 
	 * @param offender offender
	 * @return model and view for action menu on edit screen
	 */
	@RequestMapping(value = "/commitStatusEditActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView commitStatusEditActionMenu(@RequestParam(
		value = "offender",	required = true) final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(COMMIT_STATUS_EDIT_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns a view for action menu pertaining to the 
	 * specified offender on each row.
	 * 
	 * @param offender offender
	 * @return model and view for action menu for each row
	 */
	@RequestMapping(value = "/commitStatusRowActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView commitStatusRowActionMenu(@RequestParam(
		value = "commitStatusTerm",	required = true) final CommitStatusTerm term){
		ModelMap map = new ModelMap();
		map.addAttribute(COMMIT_STATUS_TERM_MODEL_KEY, term);
		return new ModelAndView(COMMIT_STATUS_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Handles commit status term exists exception.
	 * 
	 * @param commitStatusTermExistsException commit status term exists exception
	 * @return model and view to handle commit status term exists exception
	 */
	@ExceptionHandler(CommitStatusTermExistsException.class)
	public ModelAndView handleCommitStatusTermExistsException(
			final CommitStatusTermExistsException commitStatusTermExistsException) {
		return this.businessExceptionHandlerDelegate
				.prepareModelAndView(COMMIT_STATUS_TERM_EXISTS_MESSAGE_KEY,
					ERROR_BUNDLE_NAME, commitStatusTermExistsException);
	}
	
	/**
	 * Handles commit status term exists after exception.
	 * 
	 * @param CommitStatusTermExistsAfterException commit status term exists
	 * After exception
	 * @return model and view to handle commit status term exists after exception
	 */
	@ExceptionHandler(CommitStatusTermExistsAfterException.class)
	public ModelAndView handleCommitStatusTermExistsAfterException(
		final CommitStatusTermExistsAfterException 
		commitStatusTermExistsAfterException) {
		return this.businessExceptionHandlerDelegate
			.prepareModelAndView(COMMIT_STATUS_TERM_EXISTS_AFTER_MESSAGE_KEY,
			ERROR_BUNDLE_NAME, commitStatusTermExistsAfterException);
	}
	
	/**
	 * Handles commit status term conflict exception.
	 * 
	 * @param commitStatusTermConflictException commit status term exists
	 * After exception
	 * @return model and view to handle commit status term exists after exception
	 */
	@ExceptionHandler(CommitStatusTermConflictException.class)
	public ModelAndView handleCommitStatusTermConflictException(
		final CommitStatusTermConflictException 
		commitStatusTermConflictException) {
		return this.businessExceptionHandlerDelegate
			.prepareModelAndView(COMMIT_STATUS_TERM_CONFLICT_MESSAGE_KEY,
			ERROR_BUNDLE_NAME, commitStatusTermConflictException);
	}


	/* Reports. */
	
	/**
	 * Returns the report for the specified offenders commit status.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/commitStatusListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('COMMIT_STATUS_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportCommitStatusListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(COMMIT_STATUS_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				COMMIT_STATUS_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified commit status.
	 * 
	 * @param commitStatusTerm commit status term
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/commitStatusDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('COMMIT_STATUS_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportCommitStatusDetails(@RequestParam(
			value = "commitStatusTerm", required = true)
			final CommitStatusTerm commitStatusTerm,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(COMMIT_STATUS_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(commitStatusTerm.getId()));
		byte[] doc = this.reportRunner.runReport(
				COMMIT_STATUS_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(CommitStatus.class,
			this.commitStatusPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(CommitStatusTerm.class,
			this.commitStatusTermPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Offender.class,
			this.offenderPropertyEditorFactory.createOffenderPropertyEditor());
		binder.registerCustomEditor(UserAccount.class,
			this.userAccountPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(VerificationMethod.class,
			this.verificationMethodPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Date.class, "startDate",
			this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "endDate",
			this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Date.class,
			this.customDateEditorFactory.createCustomDateOnlyEditor(true));
	}
}