package omis.courtcasecondition.web.controller;

import java.util.ArrayList;
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
import omis.condition.domain.Agreement;
import omis.condition.domain.Condition;
import omis.condition.domain.ConditionCategory;
import omis.condition.domain.ConditionClause;
import omis.condition.domain.ConditionGroup;
import omis.condition.domain.ConditionTitle;
import omis.courtcasecondition.domain.CourtCaseAgreement;
import omis.courtcasecondition.domain.CourtCaseAgreementCategory;
import omis.courtcasecondition.service.CourtCaseAgreementConditionService;
import omis.courtcasecondition.web.form.ConditionItem;
import omis.courtcasecondition.web.form.ConditionItemOperation;
import omis.courtcasecondition.web.form.ConditionSelectForm;
import omis.courtcasecondition.web.validator.ConditionSelectFormValidator;
import omis.docket.domain.Docket;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.user.domain.UserAccount;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/** Controller for court case condition related operations.
 * @author Jonny Santy
 * @author Annie Jacques
 * @version 0.1.1 (Oct 4, 2017)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/courtCaseCondition/condition/")
@PreAuthorize("hasRole('USER')")
public class CourtCaseConditionController {
	
	/* View names. */
	
	private static final String SELECT_VIEW_NAME =
			"/courtCaseCondition/conditions/select";

	/* Action menu view names. */
	
	private static final String 
		COURT_CASE_CONDITIONS_ROW_ITEM_ACTION_MENU_VIEW_NAME =
				"/courtCaseCondition/includes/courtCaseConditionsRowActionMenu";
	
	private static final String 
		COURT_CASE_CONDITIONS_GROUP_ROW_ITEM_ACTION_MENU_VIEW_NAME =
			"/courtCaseCondition/includes/courtCaseConditionsGroupRowActionMenu";
	
	private static final String 
		COURT_CASE_CONDITIONS_STANDARD_ROW_ITEM_ACTION_MENU_VIEW_NAME =
		"/courtCaseCondition/includes/courtCaseConditionsStandardRowActionMenu";
	
	private static final String CONDITIONS_SELECT_ACTION_MENU_VIEW_NAME =
			"/courtCaseCondition/conditions/includes/conditionsSelectActionMenu";
	
	/* Redirect view names. */
	
	private static final String LIST_REDIRECT =
			"redirect:/courtCaseCondition/list.html?offender=%d";
	
	/* Model keys. */
	
	private static final String CONDITION_SELECT_FORM_MODEL_KEY =
			"conditionSelectForm";
	
	private static final String CONDITION_GROUPS_MODEL_KEY = "conditionGroups";
	
	private static final String CONDITION_GROUP_MODEL_KEY = "conditionGroup";
	
	private static final String COURT_CASE_AGREEMENT_MODEL_KEY =
			"courtCaseAgreement";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Message Keys */
	
	private static final String COURT_CASE_CONDITION_EXISTS_MESSAGE_KEY
		= "courtCaseCondition.exists";
	
	/* Report Names */
	
	private static final String CONDITIONS_PROBATION_PAROLE_REPORT_NAME
		= "/CaseManagement/P_P_Sign_Up/Conditions_of_Probation_Parole";
	
	private static final String SENTENCING_CONDITIONS_LISTING_REPORT_NAME
		= "/Legal/SentencingConditions/Sentencing_Conditions_Listing";
	
	private static final String SENTENCING_CONDITION_DETAILS_REPORT_NAME
		= "/Legal/SentencingConditions/Sentencing_Condition_Details";
	
	
	private static final String CONDITIONS_PNP_REPORT_NAME
		= "/CaseManagement/P_P_Sign_Up/Conditions_of_Probation_Parole_for_Docket";	
	
	/* Report Parameters */	
	
	private static final String CONDITIONS_PROBATION_PAROLE_ID_REPORT_PARAM_NAME
		= "DOC_ID";
	
	private static final String SENTENCING_CONDITIONS_LISTING_ID_REPORT_PARAM_NAME
		= "DOC_ID";
	
	private static final String SENTENCING_CONDITION_DETAILS_ID_REPORT_PARAM_NAME
		= "COURT_CASE_AGREEMENT_ID";

	private static final String CONDITIONS_PNP_ID_REPORT_PARAM_NAME
	= "COURT_CASE_AGREEMENT_ID";
	
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.courtcasecondition.msgs.form";
	
	/* Property editors. */
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("conditionGroupPropertyEditorFactory")
	private  PropertyEditorFactory conditionGroupPropertyEditorFactory;
	
	@Autowired
	@Qualifier("courtCaseAgreementPropertyEditorFactory")
	private PropertyEditorFactory courtCaseAgreementPropertyEditorFactory;
	
	@Autowired
	@Qualifier("docketPropertyEditorFactory")
	private PropertyEditorFactory docketPropertyEditorFactory;

	@Autowired
	@Qualifier("courtCaseAgreementCategoryPropertyEditorFactory")
	private PropertyEditorFactory courtCaseAgreementCategoryPropertyEditorFactory;

	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("conditionPropertyEditorFactory")
	private PropertyEditorFactory conditionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("conditionClausePropertyEditorFactory")
	private PropertyEditorFactory conditionClausePropertyEditorFactory;
	
	@Autowired
	@Qualifier("conditionTitlePropertyEditorFactory")
	private PropertyEditorFactory conditionTitlePropertyEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Service. */
	
	@Autowired
	private CourtCaseAgreementConditionService courtCaseConditionService;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("conditionSelectFormValidator")
	private ConditionSelectFormValidator conditionSelectFormValidator;
	
	/**
	 * Default Constructor for CourtCaseConditionController
	 */
	public CourtCaseConditionController() {
	}
	
	/**
	 * Displays the ModelAndView for creating/editing Standard Conditions to a
	 * Court Case Agreement
	 * @param courtCaseAgreement - Court Case Agreement conditions are being
	 * created/edited for
	 * @return ModelAndView for creating/editing Standard Conditions to a
	 * Court Case Agreement
	 */
	@RequestMapping(value = "/addStandard.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('COURT_CASE_CONDITION_VIEW') or "
			+ "hasRole('ADMIN')")
	public ModelAndView addStandardConditions(
			@RequestParam(value = "courtCaseAgreement", required = true)
				final CourtCaseAgreement courtCaseAgreement) {
		ConditionSelectForm form = new ConditionSelectForm();
		List<ConditionItem> items = new ArrayList<ConditionItem>();
		List<ConditionClause> conditionClauses = this.courtCaseConditionService
				.findConditionClausesByCourtCaseAgreementCategory(
						courtCaseAgreement.getCourtCaseAgreementCategory());
		List<Condition> conditions = this.courtCaseConditionService
				.findConditionsByAgreement(courtCaseAgreement.getAgreement());
		for (ConditionClause conditionClause : conditionClauses) {
			ConditionItem item = new ConditionItem();
			item.setConditionClause(conditionClause);
			item.setConditionCategory(ConditionCategory.STANDARD);
			item.setActive(false);
			item.setOperation(ConditionItemOperation.CREATE);
			for(Condition condition : conditions) {
				if(condition.getConditionClause().equals(conditionClause)) {
					//If not waived, condition is active.
					//If waived, condition is not active.
					item.setActive(!condition.getWaived());
					item.setCondition(condition);
					item.setOperation(ConditionItemOperation.UPDATE);
					break;
				}
			}
			items.add(item);
		}
		form.setConditionItems(items);
		form.setConditionCategory(ConditionCategory.STANDARD);
		ModelMap map = new ModelMap();
		map.addAttribute(CONDITION_SELECT_FORM_MODEL_KEY, form);
		map.put(COURT_CASE_AGREEMENT_MODEL_KEY, courtCaseAgreement);
		this.offenderSummaryModelDelegate.add(map,
				courtCaseAgreement.getAgreement().getOffender());
		
		return new ModelAndView(SELECT_VIEW_NAME, map);
	}
	
	/**
	 * Displays the ModelAndView for creating/editing Special Conditions to a
	 * Court Case Agreement by the specified Condition Group
	 * @param courtCaseAgreement - Court Case Agreement conditions are being
	 * created/edited for
	 * @param conditionGroup - Condition Group to populate the list of special
	 * conditions from
	 * @return ModelAndView for creating/editing Special Conditions to a
	 * Court Case Agreement by the specified Condition Group
	 */
	@RequestMapping(value = "/addSpecialGroup.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('COURT_CASE_CONDITION_VIEW') or "
			+ "hasRole('ADMIN')")
	public ModelAndView addSpecialConditions(
			@RequestParam(value = "courtCaseAgreement", required = true)
				final CourtCaseAgreement courtCaseAgreement,
			@RequestParam(value = "conditionGroup", required = true)
				final ConditionGroup conditionGroup){
		ModelMap map = new ModelMap();
		ConditionSelectForm form = new ConditionSelectForm();
		
		List<ConditionItem> items = new ArrayList<ConditionItem>();
		List<ConditionClause> conditionClauses = this.courtCaseConditionService
			.findConditionClausesByConditionGroup(conditionGroup);
		List<Condition> conditions = this.courtCaseConditionService
				.findConditionsByAgreement(courtCaseAgreement.getAgreement());
		for(ConditionClause conditionClause : conditionClauses) {
			ConditionItem item = new ConditionItem();
			item.setConditionClause(conditionClause);
			item.setClause(conditionClause.getDescription());
			item.setActive(false);
			item.setOperation(ConditionItemOperation.CREATE);
			for(Condition condition : conditions) {
				if(condition.getConditionClause().equals(conditionClause)) {
					item.setClause(condition.getClause());
					item.setActive(true);
					item.setCondition(condition);
					item.setOperation(ConditionItemOperation.UPDATE);
					break;
				}
			}
			items.add(item);
		}
		form.setConditionItems(items);
		form.setConditionCategory(ConditionCategory.SPECIAL);
		map.addAttribute(CONDITION_SELECT_FORM_MODEL_KEY, form);
		map.addAttribute(CONDITION_GROUP_MODEL_KEY, conditionGroup);
		map.put(COURT_CASE_AGREEMENT_MODEL_KEY, courtCaseAgreement);
		this.offenderSummaryModelDelegate.add(map,
				courtCaseAgreement.getAgreement().getOffender());
		
		return new ModelAndView(SELECT_VIEW_NAME, map);
	}
	
	
	/**
	 * Processes standard conditions for creation/waiving for the specified
	 * Court Case Agreement
	 * @param courtCaseAgreement - Court Case Agreement conditions are being
	 * processed for
	 * @param form - ConditionSelectForm
	 * @param bindingResult - binding result
	 * @return ModelAndView - returns to the Court Case Agreement list view for
	 * the agreement's offender
	 * @throws DuplicateEntityFoundException - When a Condition already exists
	 * for the specified Agreement (should not happen from this view)
	 */
	@RequestMapping(value = "/addStandard.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('COURT_CASE_CONDITION_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView processStandardConditions(
			@RequestParam(value = "courtCaseAgreement", required = true)
				final CourtCaseAgreement courtCaseAgreement,
				final ConditionSelectForm form,
				final BindingResult bindingResult)
						throws DuplicateEntityFoundException {
		this.conditionSelectFormValidator.validate(form, bindingResult);
		if(bindingResult.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(CONDITION_SELECT_FORM_MODEL_KEY, form);
			map.put(COURT_CASE_AGREEMENT_MODEL_KEY, courtCaseAgreement);
			this.offenderSummaryModelDelegate.add(map,
					courtCaseAgreement.getAgreement().getOffender());
			return new ModelAndView(SELECT_VIEW_NAME, map);
		}
		else {
			this.processConditionItems(form, courtCaseAgreement);
		}
		
		return new ModelAndView(String.format(LIST_REDIRECT,
				courtCaseAgreement.getAgreement().getOffender().getId()));
	}
	
	/**
	 * Processes special conditions for creation/editting/removing for the
	 * specified Court Case Agreement
	 * @param courtCaseAgreement - Court Case Agreement conditions are being
	 * processed for
	 * @param conditionGroup - Condition Group of the special conditions
	 * @param form - ConditionSelectForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - Returns to the Court Case Agreement list view for
	 * the agreement's offender if no errors are present on the form
	 * @throws DuplicateEntityFoundException - When a Condition already exists
	 * for the specified Agreement
	 */
	@RequestMapping(value = "/addSpecialGroup.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('COURT_CASE_CONDITION_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView processSpecialConditions(
			@RequestParam(value = "courtCaseAgreement", required = true)
				final CourtCaseAgreement courtCaseAgreement,
			@RequestParam(value = "conditionGroup", required = true)
				final ConditionGroup conditionGroup,
				final ConditionSelectForm form,
				final BindingResult bindingResult)
						throws DuplicateEntityFoundException{
		this.conditionSelectFormValidator.validate(form, bindingResult);
		if(bindingResult.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(CONDITION_SELECT_FORM_MODEL_KEY, form);
			map.put(COURT_CASE_AGREEMENT_MODEL_KEY, courtCaseAgreement);
			map.addAttribute(CONDITION_GROUP_MODEL_KEY, conditionGroup);
			this.offenderSummaryModelDelegate.add(map,
					courtCaseAgreement.getAgreement().getOffender());
			return new ModelAndView(SELECT_VIEW_NAME, map);
		}
		else {
			this.processConditionItems(form, courtCaseAgreement);
		}
		
		return new ModelAndView(String.format(LIST_REDIRECT,
				courtCaseAgreement.getAgreement().getOffender().getId()));
	}
	
	/* Action menus. */
	
	/** Court case condition list item action menu.
	 * @param courtCaseAgreement - CourtCaseAgreement
	 * @return action menu. */
	@RequestMapping(value = "/courtCaseConditionsRowActionMenu.html", 
		method = RequestMethod.GET)
	public ModelAndView displayCourtCaseConditionsRowActionMenu(
			@RequestParam(value = "courtCaseAgreement", required = true) 
				final CourtCaseAgreement courtCaseAgreement) {
		final ModelMap map = new ModelMap();
		map.put(COURT_CASE_AGREEMENT_MODEL_KEY, courtCaseAgreement);
		List<ConditionGroup> conditionGroups = this.courtCaseConditionService
				.findUnusedConditionGroupsByAgreement(
						courtCaseAgreement.getAgreement());
		map.addAttribute(CONDITION_GROUPS_MODEL_KEY, conditionGroups);
		return new ModelAndView(
				COURT_CASE_CONDITIONS_ROW_ITEM_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the action menu for court case condition group rows
	 * 
	 * @param courtCaseAgreement - Court Case Condition
	 * @param conditionGroup - Condition Group
	 * @return ModelAndView - Displays the action menu for court case condition
	 * group rows
	 */
	@RequestMapping(value = "/courtCaseConditionsGroupRowActionMenu.html", 
			method = RequestMethod.GET)
		public ModelAndView displayCourtCaseConditionsGroupRowActionMenu(
			@RequestParam(value = "courtCaseAgreement", required = true) 
				final CourtCaseAgreement courtCaseAgreement,
			@RequestParam(value = "conditionGroup", required = true)
				final ConditionGroup conditionGroup) {
		final ModelMap map = new ModelMap();
		map.addAttribute(COURT_CASE_AGREEMENT_MODEL_KEY, courtCaseAgreement);
		map.addAttribute(CONDITION_GROUP_MODEL_KEY, conditionGroup);
		return new ModelAndView(
				COURT_CASE_CONDITIONS_GROUP_ROW_ITEM_ACTION_MENU_VIEW_NAME, 
				map);
	}
	
	/**
	 * Displays the action menu for court case conditions standard conditions
	 * row
	 * 
	 * @param courtCaseAgreement - Court Case Agreement
	 * @return ModelAndView - Displays the action menu for court case conditions
	 * standard conditions row
	 */
	@RequestMapping(value = "/courtCaseConditionsStandardRowActionMenu.html", 
			method = RequestMethod.GET)
		public ModelAndView displayCourtCaseConditionsStandardRowActionMenu(
			@RequestParam(value = "courtCaseAgreement", required = true) 
				final CourtCaseAgreement courtCaseAgreement) {
		final ModelMap map = new ModelMap();
		map.addAttribute(COURT_CASE_AGREEMENT_MODEL_KEY, courtCaseAgreement);
		return new ModelAndView(
				COURT_CASE_CONDITIONS_STANDARD_ROW_ITEM_ACTION_MENU_VIEW_NAME, 
				map);
	}
	
	/**
	 * Displays the action menu for the conditions select screen
	 * 
	 * @param courtCaseAgreement - Court Case Agreement
	 * @return ModelAndView - Displays the action menu for the conditions
	 * select screen
	 */
	@RequestMapping(value = "/conditionsSelectActionMenu.html", 
		method = RequestMethod.GET)
	public ModelAndView displayConditionsSelectActionMenu(
			@RequestParam(value = "courtCaseAgreement", required = true) 
				final CourtCaseAgreement courtCaseAgreement) {
		final ModelMap map = new ModelMap();
		map.put(COURT_CASE_AGREEMENT_MODEL_KEY, courtCaseAgreement);
		map.put(OFFENDER_MODEL_KEY,
				courtCaseAgreement.getAgreement().getOffender());
		return new ModelAndView(CONDITIONS_SELECT_ACTION_MENU_VIEW_NAME, map);
	}
	
	/* Reports. */
	
	/**
	 * Returns the listing report for the specified offenders sentencing conditions.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/sentencingConditionsListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('COURT_CASE_CONDITION_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportSentencingConditionsList(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(SENTENCING_CONDITIONS_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				SENTENCING_CONDITIONS_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* Reports. */
	
	/**
	 * Returns the detail report for the specified docket sentencing conditions.
	 * 
	 * @param courtCaseAgreement CourtCaseAgreement
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/sentencingConditionDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('COURT_CASE_CONDITION_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportSentencingConditionDetails(@RequestParam(
			value = "courtCaseAgreement", required = true)
			final CourtCaseAgreement courtCaseAgreement,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(SENTENCING_CONDITION_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(courtCaseAgreement.getId()));
		byte[] doc = this.reportRunner.runReport(
				SENTENCING_CONDITION_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the conditions of supervision report for the specified docket
	 * 
	 * @param courtCaseAgreement CourtCaseAgreement
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/conditionsPNPReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('COURT_CASE_CONDITION_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportConditionsPNP(@RequestParam(
			value = "courtCaseAgreement", required = true)
			final CourtCaseAgreement courtCaseAgreement,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(CONDITIONS_PNP_ID_REPORT_PARAM_NAME,
				Long.toString(courtCaseAgreement.getId()));
		byte[] doc = this.reportRunner.runReport(
				CONDITIONS_PNP_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
		
	/**
	 * Returns the report for the specified offenders conditions of supervision.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/probationParoleConditionsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('COURT_CASE_CONDITION_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProbationParoleConditions(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(CONDITIONS_PROBATION_PAROLE_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				CONDITIONS_PROBATION_PAROLE_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* HELPER METHODS */

	/**
	 * Processes ConditionItems for creation, updating, and removal
	 * @param form - CourtCaseAgreementForm
	 * @param courtCaseAgreement - CourtCaseAgreement associated with the items
	 * @throws DuplicateEntityFoundException - When a Condition already exists
	 * with the given Clause and ConditionClause for the given CourtCaseAgreement
	 */
	private void processConditionItems(
			final ConditionSelectForm form,
			final CourtCaseAgreement courtCaseAgreement)
					throws DuplicateEntityFoundException {
		Agreement agreement = courtCaseAgreement.getAgreement();
		if(form.getConditionItems() != null){
			if(ConditionCategory.STANDARD.equals(form.getConditionCategory())) {
				for(ConditionItem standardConditionItem
						: form.getConditionItems()){
					ConditionItemOperation operation =
							standardConditionItem.getOperation();
					if(operation != null){
						Boolean waived = (!standardConditionItem.getActive());
						if(operation.equals(ConditionItemOperation.UPDATE)){
							this.courtCaseConditionService.updateCondition(
									standardConditionItem.getCondition(),
									standardConditionItem.getConditionClause(),
									standardConditionItem.getConditionClause()
										.getDescription(),
									ConditionCategory.STANDARD, waived);
						}
						else if(operation.equals(ConditionItemOperation.CREATE)){
							this.courtCaseConditionService
								.createCondition(agreement,
									standardConditionItem.getConditionClause()
										.getDescription(),
									standardConditionItem.getConditionClause(),
									ConditionCategory.STANDARD, waived);
						}
					}
				}
			}
			else if(ConditionCategory.SPECIAL.equals(
					form.getConditionCategory())) {
				for(ConditionItem specialConditionItem
						: form.getConditionItems()) {
					if(specialConditionItem.getOperation()!=null) {
						if(specialConditionItem.getOperation().equals(
								ConditionItemOperation.UPDATE)) {
							if(specialConditionItem.getActive()) {
								this.courtCaseConditionService.updateCondition(
									specialConditionItem.getCondition(),
									specialConditionItem.getCondition()
										.getConditionClause(),
									specialConditionItem.getClause(),
									ConditionCategory.SPECIAL, false);
							}
							else {
								this.courtCaseConditionService.removeCondition(
										specialConditionItem.getCondition());
							}
						}
						else if(specialConditionItem.getOperation().equals(
								ConditionItemOperation.CREATE)) {
							if(specialConditionItem.getActive()) {
								this.courtCaseConditionService.createCondition(
									courtCaseAgreement.getAgreement(),
									specialConditionItem.getClause(),
									specialConditionItem.getConditionClause(),
									ConditionCategory.SPECIAL, false);
							}
						}
						else if(specialConditionItem.getOperation().equals(
								ConditionItemOperation.REMOVE)) {
							this.courtCaseConditionService.removeCondition(
									specialConditionItem.getCondition());
						}
					}
				}
			}
			else {
				//Error, Will Robinson, Error!
			}
		}
	}
	
	
	/**
	 * Handles duplicate entity found exceptions.
	 * 
	 * @param exception duplicate entity found exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView
		(COURT_CASE_CONDITION_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/** Init binder.
	 * @param binder - web data binder. */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(CourtCaseAgreement.class, 
				this.courtCaseAgreementPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(ConditionGroup.class, 
				this.conditionGroupPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(UserAccount.class, 
				this.userAccountPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Docket.class, 
				this.docketPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(CourtCaseAgreementCategory.class, 
				this.courtCaseAgreementCategoryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Condition.class, 
				this.conditionPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(ConditionClause.class, 
				this.conditionClausePropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(ConditionTitle.class, 
				this.conditionTitlePropertyEditorFactory.
					createPropertyEditor());
	}
}
