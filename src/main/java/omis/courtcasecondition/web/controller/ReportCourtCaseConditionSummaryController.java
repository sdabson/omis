package omis.courtcasecondition.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.condition.domain.Agreement;
import omis.condition.domain.ConditionCategory;
import omis.condition.domain.ConditionGroup;
import omis.condition.report.ConditionSummary;
import omis.courtcasecondition.domain.CourtCaseAgreement;
import omis.courtcasecondition.report.CourtCaseAgreementReportService;
import omis.courtcasecondition.report.CourtCaseAgreementSummary;
import omis.courtcasecondition.service.CourtCaseAgreementConditionService;
import omis.courtcasecondition.service.CourtCaseAgreementService;
import omis.courtcasecondition.web.form.CourtCaseConditionDateRangeForm;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;

 /**Controller to report Condition Summaries for a given Court Case 
 * @author Jonny Santy
 * @author Annie Jacques
 * @version 0.1.1 (Oct 6, 2017)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/courtCaseCondition")
@PreAuthorize("hasRole('USER')")
public class ReportCourtCaseConditionSummaryController {

	/* View names. */

	private static final String LIST_VIEW_NAME = "/courtCaseCondition/list";
	
	private static final String SHOW_CONDITIONS_VIEW_NAME =
			"/courtCaseCondition/includes/listTableConditionsBodyContent";
	
	/* Action menu view names. */
	private static final String COURT_CASE_CONDITIONS_ACTION_MENU_VIEW_NAME =
			"/courtCaseCondition/includes/courtCaseConditionsActionMenu";
	
	/* Model keys. */

	private static final String AGREEMENT_SUMMARIES_MODEL_KEY =
			"courtCaseAgreementSummaries";

	private static final String OFFENDER_MODEL_KEY = "offender";

	private static final String COURT_CASE_AGREEMENT_MODEL_KEY =
			"courtCaseAgreement";
	
	private static final String STANDARD_CONDITIONS_MODEL_KEY =
			"standardConditions";
	
	private static final String SPECIAL_CONDITIONS_MODEL_KEY =
			"specialConditions";
	
	private static final String CATEGORIES_MODEL_KEY = "categories";
	
	private static final String COURT_CASE_CONDITION_DATE_RANGE_FORM_MODEL_KEY =
			"courtCaseConditionDateRangeForm";

	/* Controller Model Delegates */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Services. */
	@Autowired
	@Qualifier("courtCaseAgreementReportService")
	private CourtCaseAgreementReportService courtCaseAgreementReportService;
	
	@Autowired
	@Qualifier("courtCaseAgreementService")
	private CourtCaseAgreementService courtCaseAgreementService;
	
	@Autowired
	@Qualifier("courtCaseAgreementConditionService")
	private CourtCaseAgreementConditionService courtCaseAgreementConditionService;
	
	/* Property editor factories. */
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("agreementPropertyEditorFactory")
	private PropertyEditorFactory agreementPropertyEditorFactory;
	
	@Autowired
	@Qualifier("courtCaseAgreementPropertyEditorFactory")
	private PropertyEditorFactory courtCaseAgreementPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Constructor. */
	
	/**
	 * Default controller for ReportCourtCaseConditionSummaryController
	 */
	public ReportCourtCaseConditionSummaryController() {
	}
	
	/**
	 * Displays a list of court case agreements by offender.
	 * 
	 * @param offender - offender
	 * @param effectiveDate - effective date, optional parameter
	 * @param startDate - start date, optional parameter
	 * @param endDate - end date, optional parameter
	 * @return ModelAndView - screen to display list of Court Case Agreements
	 * within the selected dates for the offender
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('COURT_CASE_CONDITION_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "date", required = false)
			final Date effectiveDate,
			@RequestParam(value = "startDate", required = false)
			final Date startDate,
		@RequestParam(value = "endDate", required = false)
			final Date endDate) {
		ModelMap map = new ModelMap();
		CourtCaseConditionDateRangeForm form =
				new CourtCaseConditionDateRangeForm();
		List<CourtCaseAgreementSummary> courtCaseAgreementSummaries =
				new ArrayList<CourtCaseAgreementSummary>();
		if(effectiveDate != null){
			form.setEffectiveDate(effectiveDate);
			form.setUsingEffectiveDate(true);
			courtCaseAgreementSummaries =
					this.courtCaseAgreementReportService.findByOffender(
							offender, effectiveDate);
		}
		else if (startDate != null || endDate != null) {
			form.setStartDate(startDate);
			form.setEndDate(endDate);
			form.setUsingEffectiveDate(false);
			courtCaseAgreementSummaries =
					this.courtCaseAgreementReportService.findByOffenderOnDates(
							offender, startDate, endDate);
		}
		else {
			form.setEffectiveDate(new Date());
			form.setUsingEffectiveDate(true);
			courtCaseAgreementSummaries =
					this.courtCaseAgreementReportService.findByOffender(
							offender, new Date());
		}
		
		map.addAttribute(COURT_CASE_CONDITION_DATE_RANGE_FORM_MODEL_KEY, form);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(AGREEMENT_SUMMARIES_MODEL_KEY, 
				courtCaseAgreementSummaries);
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/**
	 * Displays a list of court case agreements by offender for the user
	 * specified date(s)
	 * @param offender - Offender
	 * @param form - Court Case Condition Date Range Form
	 * @param bindingResult - Binding result
	 * @return ModelAndView - display of list of court case agreements by
	 * offender for the user specified date(s)
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('COURT_CASE_CONDITION_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
			final Offender offender, final CourtCaseConditionDateRangeForm form,
			final BindingResult bindingResult) {

		ModelMap map = new ModelMap();
		List<CourtCaseAgreementSummary> courtCaseAgreementSummaries =
				new ArrayList<CourtCaseAgreementSummary>();
		if(form.getUsingEffectiveDate()) {
			if(form.getEffectiveDate() != null) {
				courtCaseAgreementSummaries = this.courtCaseAgreementReportService
						.findByOffender(offender, form.getEffectiveDate());
			}
			else {
				form.setEffectiveDate(new Date());
				courtCaseAgreementSummaries = this.courtCaseAgreementReportService
						.findByOffender(offender, new Date());
			}
		}
		else {
			courtCaseAgreementSummaries = this.courtCaseAgreementReportService
					.findByOffenderOnDates(offender, form.getStartDate(),
							form.getEndDate());
		}
		
		map.addAttribute(COURT_CASE_CONDITION_DATE_RANGE_FORM_MODEL_KEY, form);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(AGREEMENT_SUMMARIES_MODEL_KEY, 
				courtCaseAgreementSummaries);
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}

	/* Action menus. */
	
	/**
	 * Displays the court case condition action menu.
	 * 
	 * @param person person
	 * @return model and view for court case condition action menu
	 */
	@RequestMapping(value="/courtCaseConditionsActionMenu.html",
			method=RequestMethod.GET)
	public ModelAndView displayCourtCaseConditionsActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(CATEGORIES_MODEL_KEY, this.courtCaseAgreementService
				.findAllCourtCaseAgreementCategories());
		return new ModelAndView(
				COURT_CASE_CONDITIONS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the AJAX content of an agreement's conditions
	 * 
	 * @param courtCaseAgreement - court case agreement
	 * @return ModelAndView - Content of an agreement's conditions
	 */
	@RequestMapping(value="/showConditions.html",
			method=RequestMethod.GET)
	public ModelAndView showConditions(
			@RequestParam(value = "courtCaseAgreement", required = true)
				final CourtCaseAgreement courtCaseAgreement) {
		HashMap<ConditionGroup, List<ConditionSummary>> specialConditions =
				new HashMap<ConditionGroup, List<ConditionSummary>>();
		List<ConditionSummary> standardConditions =
				this.courtCaseAgreementReportService
				.findConditionSummariesByAgreementAndConditionCategory(
						courtCaseAgreement.getAgreement(),
						ConditionCategory.STANDARD);
		List<ConditionGroup> conditionGroups =
				this.courtCaseAgreementConditionService
				.findUsedConditionGroupsByAgreement(
						courtCaseAgreement.getAgreement());
		
		for(ConditionGroup conditionGroup : conditionGroups) {
			List<ConditionSummary> conditionSummaries =
					this.courtCaseAgreementReportService
					.findConditionSummariesByAgreementAndConditionGroup(
							courtCaseAgreement.getAgreement(), conditionGroup);
			specialConditions.put(conditionGroup, conditionSummaries);
		}
		
		ModelMap map = new ModelMap();
		map.addAttribute(COURT_CASE_AGREEMENT_MODEL_KEY, courtCaseAgreement);
		map.addAttribute(STANDARD_CONDITIONS_MODEL_KEY, standardConditions);
		map.addAttribute(SPECIAL_CONDITIONS_MODEL_KEY, specialConditions);
		
		return new ModelAndView(SHOW_CONDITIONS_VIEW_NAME, map);
	}
	
	/* Init binder. */
	
	/**
	 * Sets up init binder.
	 * 
	 * @param binder init binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(Agreement.class, 
				this.agreementPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(CourtCaseAgreement.class, 
				this.courtCaseAgreementPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
}
