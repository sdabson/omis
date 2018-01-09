package omis.need.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.need.domain.CasePlanObjective;
import omis.need.domain.NeedDomain;
import omis.need.domain.ObjectivePriority;
import omis.need.domain.ObjectiveSource;
import omis.need.report.CasePlanObjectiveReportService;
import omis.need.report.CasePlanObjectiveSummary;
import omis.need.report.NeedDomainSummaryReportService;
import omis.need.service.CasePlanObjectiveService;
import omis.need.web.form.CasePlanObjectiveForm;
import omis.need.web.validator.CasePlanObjectiveFormValidator;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderReportService;
import omis.person.domain.Person;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

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

/**
 * Case plan objective controller.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 10, 2015)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/need/casePlanObjective")
@PreAuthorize("hasRole('ADMIN')"
		+ " or (hasRole('USER') and hasRole('NEED_MODULE'))")
public class CasePlanObjectiveController {
	
	/* Redirect URLs. */
	
	private static final String LIST_REDIRECT_URL = 
			"redirect:list.html?offender=%d";
	
	/* View names. */
	
	private static final String LIST_VIEW_NAME = "/need/list";
	
	private static final String EDIT_VIEW_NAME = "/need/edit";
	
	private static final String CASE_PLAN_OBJECTIVES_ACTION_MENU
		= "/need/includes/casePlanObjectivesActionMenu";
	
	private static final String CASE_PLAN_OBJECTIVE_ACTION_MENU
		= "/need/includes/casePlanObjectiveActionMenu";
	
	private static final String CASE_PLAN_OBJECTIVE_ROW_ACTION_MENU
		= "/need/includes/casePlanObjectiveRowActionMenu";
	
	/* Model keys. */

	private static final String OFFENDER_SUMMARY_MODEL_KEY = "offenderSummary";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String CASE_PLAN_OBJECTIVE_SUMMARIES_MODEL_KEY
		= "casePlanObjectiveSummaries";
	
	private static final String NEED_DOMAIN_MODEL_KEY
		= "needDomain";
	
	private static final String NEED_DOMAIN_SUMMARIES_MODEL_KEY
		= "needDomainSummaries";
	
	private static final String CASE_PLAN_OBJECTIVE_FORM_MODEL_KEY 
		= "casePlanObjectiveForm";
	
	private static final String NEED_DOMAINS_MODEL_KEY = "needDomains";
	
	private static final String OBJECTIVE_SOURCES_MODEL_KEY 
		= "objectiveSources";
	
	private static final String OBJECTIVE_PRIORITIES_MODEL_KEY
		= "objectivePriorities";
	
	private static final String CASE_PLAN_OBJECTIVE_MODEL_KEY
		= "casePlanObjective";
	
	/* Services. */
	
	@Autowired
	@Qualifier("casePlanObjectiveService")
	private CasePlanObjectiveService casePlanObjectiveService;
	
	@Autowired
	@Qualifier("casePlanObjectiveReportService")
	private CasePlanObjectiveReportService casePlanObjectiveReportService;
	
	@Autowired
	@Qualifier("needDomainSummaryReportService")
	private NeedDomainSummaryReportService
	needDomainSummaryReportService;
	
	@Autowired
	@Qualifier("offenderReportService")
    private OffenderReportService offenderReportService;
	
	/* Property Editor Factories. */
	
	@Autowired
	@Qualifier("objectivePriorityPropertyEditorFactory")
	private PropertyEditorFactory objectivePriorityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("needDomainPropertyEditorFactory")
	private PropertyEditorFactory needDomainPropertyEditorFactory;
	
	@Autowired
	@Qualifier("casePlanObjectivePropertyEditorFactory")
	private PropertyEditorFactory casePlanObjectivePropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	/* Message keys. */
	
	private static final String CASE_PLAN_OBJECTIVE_EXISTS_MESSAGE_KEY
		= "casePlanObjectiveExists";
	
	/* Bundles. */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.need.msgs.form";
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("casePlanObjectiveFormValidator")
	private CasePlanObjectiveFormValidator casePlanObjectiveFormValidator;
	

	/* Report names. */
	
	private static final String CASE_PLAN_OBJECTIVE_LISTING_REPORT_NAME 
		= "/CaseManagement/CasePlanObjective/Case_Plan_Objective_Listing";

	private static final String CASE_PLAN_OBJECTIVE_DETAILS_REPORT_NAME 
		= "/CaseManagement/CasePlanObjective/Case_Plan_Objective_Details";

	/* Report parameter names. */
	
	private static final String CASE_PLAN_OBJECTIVE_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String CASE_PLAN_OBJECTIVE_DETAILS_ID_REPORT_PARAM_NAME 
		= "CASE_PLAN_ID";
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;

	/**
	 * Instantiates a default instance of case plan objective controller.
	 */
	public CasePlanObjectiveController() {
		//Default constructor.
	}
	

	/**
	 * Display the case plan needs screen for the specified offender.
	 * 
	 * @param offender offender
	 * @return model and view for case plan needs
	 */
	@RequestMapping(value = "list.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('NEED_MODULE')")
	public ModelAndView list(@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_SUMMARY_MODEL_KEY,
				this.offenderReportService.summarizeOffender(offender));
		map.addAttribute(NEED_DOMAIN_SUMMARIES_MODEL_KEY, 
				this.needDomainSummaryReportService
				.summarizeDomainsByOffender(offender));
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/**
	 * Displays the case plan needs screen for the specified offender and
	 * criminogenic domain. If no domain is specified, all domains needs are
	 * shown.
	 * 
	 * @param offender offender
	 * @param domain criminogenic domain
	 * @return model and view for case plan needs
	 */
	@RequestMapping(value = "list.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('NEED_MODULE')")
	public ModelAndView list(@RequestParam(value = "offender", required = true)
				final Offender offender, 
			@RequestParam(value = "domain", required = false)
				final NeedDomain domain) {
		ModelMap map = new ModelMap();
		final List<CasePlanObjectiveSummary> summaries;
		if (domain != null) {
			summaries = this.casePlanObjectiveReportService
					.findCasePlanObjectiveSummariesByDomain(offender, domain);
			map.addAttribute(NEED_DOMAIN_MODEL_KEY, domain);
		} else {
			summaries = this.casePlanObjectiveReportService
					.findCasePlanObjectiveSummariesByOffender(offender);
		}
		map.addAttribute(CASE_PLAN_OBJECTIVE_SUMMARIES_MODEL_KEY, summaries);
		map.addAttribute(OFFENDER_SUMMARY_MODEL_KEY,
				this.offenderReportService.summarizeOffender(offender));
		map.addAttribute(NEED_DOMAIN_SUMMARIES_MODEL_KEY, 
				this.needDomainSummaryReportService
				.summarizeDomainsByOffender(offender));
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/**
	 * Shows the edit screen for case plan objective to create a new objective.
	 * 
	 * @return model and view to create a new case plan objective
	 */
	@RequestMapping(value = "create.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('EDIT_CASE_PLAN_OBJECTIVE')")
	public ModelAndView create(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();	
		CasePlanObjectiveForm form = new CasePlanObjectiveForm();
		return this.prepareEditMav(form, map, offender);
	}
	
	/**
	 * Saves a case plan objective for the specified offender.
	 * 
	 * @param offender offender
	 * @param form case plan objective form
	 * @param result binding result
	 * @return model and view for list case plan objectives
	 * @throws DuplicateEntityFoundException thrown when a duplicate case plan
	 * objective is found
	 */
	@RequestMapping(value = "create.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('EDIT_CASE_PLAN_OBJECTIVE')")
	public ModelAndView save(@RequestParam(value = "offender", required = true)
			final Offender offender, CasePlanObjectiveForm form,
			BindingResult result) throws DuplicateEntityFoundException {
		this.casePlanObjectiveFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX 
					+ CASE_PLAN_OBJECTIVE_FORM_MODEL_KEY, result);
			return this.prepareEditMav(form, map, offender);
		}
		this.casePlanObjectiveService.create(offender, form.getIdentifiedDate(),
				form.getName(), form.getComment(), form.getSource(),
				form.getPriority(), form.getDomain(), form.getOffenderAgrees(),
				form.getStaffMember());
		return new ModelAndView(String.format(LIST_REDIRECT_URL,
				offender.getId()));
	}
	
	/**
	 * Shows the edit screen for case plan objective to edit the specified 
	 * case plan objective.
	 * 
	 * @param objective case plan objective
	 * @return model and view for edit case plan objective
	 */
	@RequestMapping(value = "edit.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('EDIT_CASE_PLAN_OBJECTIVE')")
	public ModelAndView edit(
			@RequestParam(value = "objective", required = true)
				final CasePlanObjective objective)	{
		ModelMap map = new ModelMap();
		CasePlanObjectiveForm form = new CasePlanObjectiveForm();
		this.populateCasePlanObjectiveForm(objective, form);
		map.addAttribute(CASE_PLAN_OBJECTIVE_MODEL_KEY, objective);
		return this.prepareEditMav(form, map, objective.getOffender());
	}
	
	/**
	 * Updates the specified case plan objective.
	 * 
	 * @param objective case plan objective
	 * @param form case plan objective form
	 * @param result binding result
	 * @return model and view to redirect to case plan objective list screen
	 * @throws DuplicateEntityFoundException thrown when a duplicate case
	 * plan objective is found
	 */
	@RequestMapping(value = "edit.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('EDIT_CASE_PLAN_OBJECTIVE')")
	public ModelAndView update(
			@RequestParam(value = "objective", required = true)
				final CasePlanObjective objective, CasePlanObjectiveForm form,
			BindingResult result) throws DuplicateEntityFoundException {
		this.casePlanObjectiveFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX 
					+ CASE_PLAN_OBJECTIVE_FORM_MODEL_KEY, result);
			map.addAttribute(CASE_PLAN_OBJECTIVE_MODEL_KEY, objective);
			return this.prepareEditMav(form, map, objective.getOffender());
		}
		this.casePlanObjectiveService.update(objective,
				form.getIdentifiedDate(), form.getName(), form.getComment(),
				form.getSource(), form.getPriority(), form.getDomain(),
				form.getOffenderAgrees(), form.getStaffMember());
		return new ModelAndView(String.format(LIST_REDIRECT_URL,
				objective.getOffender().getId()));
	}
	
	/**
	 * Removes the specified case plan objective, and redirects to the case
	 * plan objective list screen.
	 * 
	 * @param objective case plan objective
	 * @return model and view to redirect to case plan objective list screen
	 */
	@RequestMapping(value = "remove.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('EDIT_CASE_PLAN_OBJECTIVE')")
	public ModelAndView remove(@RequestParam(value = "objective", required = true)
				final CasePlanObjective objective) {
		this.casePlanObjectiveService.remove(objective);
		return new ModelAndView(String.format(LIST_REDIRECT_URL,
				objective.getOffender().getId()));
	}
	
	/**
	 * Shows the case plan objectives action menu.
	 * 
	 * @param offender offender
	 * @return model and view for case plan objectives action menu
	 */
	@RequestMapping(value = "needsActionMenu.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('NEED_MODULE')")
	public ModelAndView showCasePlanObjectivesActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(CASE_PLAN_OBJECTIVES_ACTION_MENU, map);
	}
	
	/**
	 * Shows the case plan objective action menu.
	 * 
	 * @param offender offender
	 * @return model and view for case plan objective action menu
	 */
	@RequestMapping(value = "casePlanObjectiveActionMenu.html")
	@PreAuthorize("hasRole('ADMIN') or hasRole('NEED_MODULE')")
	public ModelAndView showCasePlanObjectiveActionMenu(
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(CASE_PLAN_OBJECTIVE_ACTION_MENU, map);
	}
	
	/**
	 * Shows the case plan objectives row action menu.
	 * 
	 * @param objective case plan objective
	 * @return model and view for case plan objectives row action menu
	 */
	@RequestMapping(value = "objectiveRowActionMenu.html")
	@PreAuthorize("hasRole('ADMIN') or hasRole('EDIT_CASE_PLAN_OBJECTIVE')")
	public ModelAndView showObjectiveRowActionMenu(
			@RequestParam(value = "objective", required = true)
				final CasePlanObjective objective) {
		ModelMap map = new ModelMap();
		map.addAttribute(CASE_PLAN_OBJECTIVE_MODEL_KEY, objective);
		return new ModelAndView(CASE_PLAN_OBJECTIVE_ROW_ACTION_MENU, map);
	}
	
	/* Exception handler methods. */
	
	/**
	 * Handles duplicate entity found exceptions.
	 * 
	 * @param exception duplicate entity found exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				CASE_PLAN_OBJECTIVE_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/* Helper methods. */
	
	/*
	 * Populates the specified map with attributes needed for editing a case
	 * plan objective, then returns it as part of a model and view.
	 * 
	 * @param form case plan objective form
	 * @param map model map
	 * @param offender offender
	 * @return model and view for editing a new or existing case plan
	 */
	private ModelAndView prepareEditMav(final CasePlanObjectiveForm form,
			final ModelMap map, final Offender offender) {
		map.addAttribute(NEED_DOMAINS_MODEL_KEY,
				this.casePlanObjectiveService.findNeedDomains());
		map.addAttribute(OBJECTIVE_PRIORITIES_MODEL_KEY,
				this.casePlanObjectiveService.findPriorities());
		map.addAttribute(OBJECTIVE_SOURCES_MODEL_KEY,
				ObjectiveSource.values());
		map.addAttribute(OFFENDER_SUMMARY_MODEL_KEY,
				this.offenderReportService.summarizeOffender(offender));
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(CASE_PLAN_OBJECTIVE_FORM_MODEL_KEY, form);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/*
	 * Populates the specified case plan objective form with information
	 * from the specified case plan objective.
	 * 
	 * @param objective case plan objective
	 * @param form case plan objective form
	 * @return populated case plan objective form
	 */
	private CasePlanObjectiveForm populateCasePlanObjectiveForm(
			final CasePlanObjective objective,
			final CasePlanObjectiveForm form) {
		form.setComment(objective.getComment());
		form.setDomain(objective.getDomain());
		form.setIdentifiedDate(objective.getIdentificationDate());
		form.setName(objective.getName());
		form.setOffenderAgrees(objective.getOffenderAgrees());
		form.setPriority(objective.getPriority());
		form.setStaffMember(objective.getStaffMember());
		form.setSource(objective.getSource());
		return form;
	}
	
	/* Reports. */
	
	/**
	 * Returns the report for the specified offenders case plan objectives.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/casePlanObjectiveListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('NEED_MODULE') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportCasePlanObjectiveListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(CASE_PLAN_OBJECTIVE_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				CASE_PLAN_OBJECTIVE_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified case plan objective.
	 * 
	 * @param objective case plan objective
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/casePlanObjectiveDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('NEED_MODULE') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportCasePlanObjectiveDetails(@RequestParam(
			value = "objective", required = true)
			final CasePlanObjective objective,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(CASE_PLAN_OBJECTIVE_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(objective.getId()));
		byte[] doc = this.reportRunner.runReport(
				CASE_PLAN_OBJECTIVE_DETAILS_REPORT_NAME,
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
		binder.registerCustomEditor(
				ObjectivePriority.class,
				this.objectivePriorityPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				NeedDomain.class,
				this.needDomainPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				CasePlanObjective.class,
				this.casePlanObjectivePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(
				Person.class,
				this.personPropertyEditorFactory
				.createPropertyEditor());
	}
}