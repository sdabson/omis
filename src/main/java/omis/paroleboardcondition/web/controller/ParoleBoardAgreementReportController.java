/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.paroleboardcondition.web.controller;

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
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.paroleboardcondition.domain.ParoleBoardAgreement;
import omis.paroleboardcondition.report.ParoleBoardAgreementReportService;
import omis.paroleboardcondition.report.ParoleBoardAgreementSummary;
import omis.paroleboardcondition.service.ParoleBoardConditionService;
import omis.paroleboardcondition.web.form.ParoleBoardAgreementDateRangeForm;

/**
 * Parole Board Agreement Report Controller.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 19, 2017)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/paroleBoardCondition")
@PreAuthorize("hasRole('USER')")
public class ParoleBoardAgreementReportController {
	
	/* View Names */
	
	private static final String LIST_VIEW_NAME = "/paroleBoardCondition/list";
	
	private static final String PAROLE_BOARD_AGREEMENTS_ACTION_MENU_VIEW_NAME =
			"/paroleBoardCondition/includes/paroleBoardAgreementsActionMenu";
	
	private static final String SHOW_CONDITIONS_VIEW_NAME =
			"/paroleBoardCondition/includes/listTableConditionsBodyContent";
	
	private static final String
		PAROLE_BOARD_AGREEMENTS_ROW_ACTION_MENU_VIEW_NAME =
			"/paroleBoardCondition/includes/paroleBoardAgreementsRowActionMenu";

	private static final String
		PAROLE_BOARD_CONDITIONS_GROUP_ROW_ACTION_MENU_VIEW_NAME =
			"/paroleBoardCondition/includes/"
			+ "paroleBoardConditionsGroupRowActionMenu";

	private static final String
		PAROLE_BOARD_CONDITIONS_STANDARD_ROW_ACTION_MENU_VIEW_NAME =
			"/paroleBoardCondition/includes/"
			+ "paroleBoardConditionsStandardRowActionMenu";
	
	
	/* Model Keys */
	
	private static final String
		PAROLE_BOARD_AGREEMENT_DATE_RANGE_FORM_MODEL_KEY =
			"paroleBoardAgreementDateRangeForm";
	
	private static final String AGREEMENT_SUMMARIES_MODEL_KEY =
			"paroleBoardAgreementSummaries";
	
	private static final String PAROLE_BOARD_AGREEMENT_MODEL_KEY =
			"paroleBoardAgreement";
	
	private static final String CONDITION_GROUP_MODEL_KEY = "conditionGroup";
	
	private static final String CONDITION_GROUPS_MODEL_KEY = "conditionGroups";
	
	private static final String CATEGORIES_MODEL_KEY = "categories";
	
	private static final String STANDARD_CONDITIONS_MODEL_KEY =
			"standardConditions";
	
	private static final String SPECIAL_CONDITIONS_MODEL_KEY =
			"specialConditions";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Controller Model Delegates */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("paroleBoardAgreementReportService")
	private ParoleBoardAgreementReportService paroleBoardAgreementReportService;
	
	@Autowired
	@Qualifier("paroleBoardConditionService")
	private ParoleBoardConditionService paroleBoardConditionService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("agreementPropertyEditorFactory")
	private PropertyEditorFactory agreementPropertyEditorFactory;
	
	@Autowired
	@Qualifier("paroleBoardAgreementPropertyEditorFactory")
	private PropertyEditorFactory paroleBoardAgreementPropertyEditorFactory;
	
	@Autowired
	@Qualifier("conditionGroupPropertyEditorFactory")
	private PropertyEditorFactory conditionGroupPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	/**
	 * Default Constructor for ParoleBoardAgreementReportController.
	 */
	public ParoleBoardAgreementReportController() {
	}
	
	/**
	 * Displays a list of parole board agreements by offender.
	 * 
	 * @param offender - offender
	 * @param effectiveDate - effective date, optional parameter
	 * @param startDate - start date, optional parameter
	 * @param endDate - end date, optional parameter
	 * @return ModelAndView - screen to display list of Parole Board Agreements
	 * within the selected dates for the offender
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PAROLE_BOARD_CONDITION_LIST') or hasRole('ADMIN')")
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
		ParoleBoardAgreementDateRangeForm form =
				new ParoleBoardAgreementDateRangeForm();
		List<ParoleBoardAgreementSummary> paroleBoardAgreementSummaries =
				new ArrayList<ParoleBoardAgreementSummary>();
		if (effectiveDate != null) {
			form.setEffectiveDate(effectiveDate);
			form.setUsingEffectiveDate(true);
			paroleBoardAgreementSummaries =
					this.paroleBoardAgreementReportService.findByOffender(
							offender, effectiveDate);
		} else if (startDate != null || endDate != null) {
			form.setStartDate(startDate);
			form.setEndDate(endDate);
			form.setUsingEffectiveDate(false);
			paroleBoardAgreementSummaries =
					this.paroleBoardAgreementReportService
						.findByOffenderOnDates(offender, startDate, endDate);
		} else {
			form.setEffectiveDate(new Date());
			form.setUsingEffectiveDate(true);
			paroleBoardAgreementSummaries =
					this.paroleBoardAgreementReportService.findByOffender(
							offender, new Date());
		}
		
		map.addAttribute(
				PAROLE_BOARD_AGREEMENT_DATE_RANGE_FORM_MODEL_KEY, form);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(AGREEMENT_SUMMARIES_MODEL_KEY, 
				paroleBoardAgreementSummaries);
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/**
	 * Displays a list of parole board agreements by offender for the user
	 * specified date(s).
	 * @param offender - Offender
	 * @param form - Parole Board Agreement Date Range Form
	 * @param bindingResult - Binding result
	 * @return ModelAndView - display of list of parole board agreements by
	 * offender for the user specified date(s)
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PAROLE_BOARD_CONDITION_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			final ParoleBoardAgreementDateRangeForm form,
			final BindingResult bindingResult) {

		ModelMap map = new ModelMap();
		List<ParoleBoardAgreementSummary> paroleBoardAgreementSummaries =
				new ArrayList<ParoleBoardAgreementSummary>();
		if (form.getUsingEffectiveDate()) {
			if (form.getEffectiveDate() != null) {
				paroleBoardAgreementSummaries =
						this.paroleBoardAgreementReportService
						.findByOffender(offender, form.getEffectiveDate());
			} else {
				form.setEffectiveDate(new Date());
				paroleBoardAgreementSummaries =
						this.paroleBoardAgreementReportService
						.findByOffender(offender, new Date());
			}
		} else {
			paroleBoardAgreementSummaries =
					this.paroleBoardAgreementReportService
					.findByOffenderOnDates(offender, form.getStartDate(),
							form.getEndDate());
		}
		
		map.addAttribute(PAROLE_BOARD_AGREEMENT_DATE_RANGE_FORM_MODEL_KEY,
				form);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(AGREEMENT_SUMMARIES_MODEL_KEY, 
				paroleBoardAgreementSummaries);
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	
	/* Action menus. */
	
	/**
	 * Displays the Parole Board Agreements action menu.
	 * 
	 * @param offender - Offender
	 * @return model and view for Parole Board Agreements action menu
	 */
	@RequestMapping(value = "/paroleBoardAgreementsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayParoleBoardAgreementsActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(CATEGORIES_MODEL_KEY, this.paroleBoardConditionService
				.findAllParoleBoardAgreementCategories());
		return new ModelAndView(
				PAROLE_BOARD_AGREEMENTS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the Parole Board Conditions row action menu.
	 * 
	 * @param paroleBoardAgreement - ParoleBoardAgreement
	 * @return ModelAndView - Parole Board Conditions row action menu.
	 */
	@RequestMapping(value = "/paroleBoardAgreementsRowActionMenu.html", 
		method = RequestMethod.GET)
	public ModelAndView displayParoleBoardConditionsRowActionMenu(
			@RequestParam(value = "paroleBoardAgreement", required = true) 
				final ParoleBoardAgreement paroleBoardAgreement) {
		final ModelMap map = new ModelMap();
		map.put(PAROLE_BOARD_AGREEMENT_MODEL_KEY, paroleBoardAgreement);
		List<ConditionGroup> conditionGroups = this.paroleBoardConditionService
				.findUnusedByAgreement(paroleBoardAgreement.getAgreement());
		map.addAttribute(CONDITION_GROUPS_MODEL_KEY, conditionGroups);
		return new ModelAndView(
				PAROLE_BOARD_AGREEMENTS_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the action menu for parole board condition group rows.
	 * 
	 * @param paroleBoardAgreement - Parole Board Agreement
	 * @param conditionGroup - Condition Group
	 * @return ModelAndView - Displays the action menu for parole board
	 * condition group rows
	 */
	@RequestMapping(value = "/paroleBoardConditionsGroupRowActionMenu.html", 
			method = RequestMethod.GET)
		public ModelAndView displayParoleBoardConditionsGroupRowActionMenu(
				@RequestParam(value = "paroleBoardAgreement", required = true) 
				final ParoleBoardAgreement paroleBoardAgreement,
			@RequestParam(value = "conditionGroup", required = true)
				final ConditionGroup conditionGroup) {
		final ModelMap map = new ModelMap();
		map.addAttribute(PAROLE_BOARD_AGREEMENT_MODEL_KEY,
				paroleBoardAgreement);
		map.addAttribute(CONDITION_GROUP_MODEL_KEY, conditionGroup);
		return new ModelAndView(
				PAROLE_BOARD_CONDITIONS_GROUP_ROW_ACTION_MENU_VIEW_NAME, 
				map);
	}
	
	/**
	 * Displays the action menu for parole board conditions standard conditions
	 * row.
	 * 
	 * @param paroleBoardAgreement - Parole Board Agreement
	 * @return ModelAndView - Displays the action menu for parole board
	 * conditions standard conditions row
	 */
	@RequestMapping(value = "/paroleBoardConditionsStandardRowActionMenu.html", 
			method = RequestMethod.GET)
		public ModelAndView displayParoleBoardConditionsStandardRowActionMenu(
			@RequestParam(value = "paroleBoardAgreement", required = true) 
				final ParoleBoardAgreement paroleBoardAgreement) {
		final ModelMap map = new ModelMap();
		map.addAttribute(PAROLE_BOARD_AGREEMENT_MODEL_KEY,
				paroleBoardAgreement);
		return new ModelAndView(
				PAROLE_BOARD_CONDITIONS_STANDARD_ROW_ACTION_MENU_VIEW_NAME, 
				map);
	}
	
	/* AJAX */
	
	/**
	 * Displays the AJAX content of an agreement's conditions.
	 * 
	 * @param paroleBoardAgreement - Parole Board Agreement
	 * @return ModelAndView - Content of an agreement's conditions
	 */
	@RequestMapping(value = "/showConditions.html",
			method = RequestMethod.GET)
	public ModelAndView showConditions(
			@RequestParam(value = "paroleBoardAgreement", required = true)
				final ParoleBoardAgreement paroleBoardAgreement) {
		HashMap<ConditionGroup, List<ConditionSummary>> specialConditions =
				new HashMap<ConditionGroup, List<ConditionSummary>>();
		List<ConditionSummary> standardConditions =
				this.paroleBoardAgreementReportService
				.findConditionSummariesByAgreementAndConditionCategory(
						paroleBoardAgreement.getAgreement(),
						ConditionCategory.STANDARD);
		List<ConditionGroup> conditionGroups =
				this.paroleBoardConditionService
					.findUsedByAgreement(paroleBoardAgreement.getAgreement());
		
		for (ConditionGroup conditionGroup : conditionGroups) {
			List<ConditionSummary> conditionSummaries =
					this.paroleBoardAgreementReportService
					.findConditionSummariesByAgreementAndConditionGroup(
							paroleBoardAgreement.getAgreement(),
							conditionGroup);
			specialConditions.put(conditionGroup, conditionSummaries);
		}
		
		ModelMap map = new ModelMap();
		map.addAttribute(PAROLE_BOARD_AGREEMENT_MODEL_KEY,
				paroleBoardAgreement);
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
		binder.registerCustomEditor(ParoleBoardAgreement.class, 
				this.paroleBoardAgreementPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(ConditionGroup.class, 
				this.conditionGroupPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
}
