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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.paroleboardcondition.domain.ParoleBoardAgreement;
import omis.paroleboardcondition.service.ParoleBoardConditionService;
import omis.paroleboardcondition.web.form.ConditionItem;
import omis.paroleboardcondition.web.form.ParoleBoardAgreementConditionSelectForm;
import omis.paroleboardcondition.web.form.ParoleBoardConditionItemOperation;
import omis.paroleboardcondition.web.validator.ParoleBoardAgreementConditionSelectFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Parole Board Agreement Condition Controller.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 20, 2017)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/paroleBoardCondition")
@PreAuthorize("hasRole('USER')")
public class ParoleBoardAgreementConditionController {
	
	/* View Names */
	
	private static final String EDIT_VIEW_NAME = "/paroleBoardCondition/select";
	
	private static final String
		PAROLE_BOARD_CONDITIONS_SELECT_ACTION_MENU_VIEW_NAME =
				"/paroleBoardCondition/includes/"
				+ "paroleBoardConditionsSelectActionMenu";
	
	private static final String LIST_REDIRECT_VIEW_NAME =
			"redirect:/paroleBoardCondition/list.html?offender=%d";
	
	/* Model Keys */
	
	private static final String CONDITION_SELECT_FORM_MODEL_KEY =
			"paroleBoardAgreementConditionSelectForm";
	
	private static final String PAROLE_BOARD_AGREEMENT_MODEL_KEY =
			"paroleBoardAgreement";
	
	private static final String CONDITION_GROUP_MODEL_KEY = "conditionGroup";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Message Keys */
	
	private static final String CONDITION_EXISTS_MESSAGE_KEY =
			"paroleBoardCondition.exists";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.paroleboardcondition.msgs.form";
	
	/* Delegates. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("paroleBoardConditionService")
	private ParoleBoardConditionService paroleBoardConditionService;
	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("conditionGroupPropertyEditorFactory")
	private PropertyEditorFactory conditionGroupPropertyEditorFactory;
	
	@Autowired
	@Qualifier("paroleBoardAgreementPropertyEditorFactory")
	private PropertyEditorFactory paroleBoardAgreementPropertyEditorFactory;
	
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
	
	/* Validator. */
	
	@Autowired
	@Qualifier("paroleBoardAgreementConditionSelectFormValidator")
	private ParoleBoardAgreementConditionSelectFormValidator formValidator;
	
	/**
	 * Default Constructor for ParoleBoardAgreementConditionController.
	 */
	public ParoleBoardAgreementConditionController() {
	}
	
	/**
	 * Returns the Model and View for adding Standard Conditions to a
	 * Parole Board Agreement.
	 * 
	 * @param paroleBoardAgreement - Parole Board Agreement
	 * @return ModelAndView - Model and View for adding Standard Conditions to a
	 * Parole Board Agreement.
	 */
	@RequestMapping(value = "/addStandard.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PAROLE_BOARD_CONDITION_VIEW') or "
			+ "hasRole('ADMIN')")
	public ModelAndView addStandardConditions(
			@RequestParam(value = "paroleBoardAgreement", required = true)
				final ParoleBoardAgreement paroleBoardAgreement) {
		return prepareEditMav(paroleBoardAgreement, null);
	}

	
	
	/**
	 * Returns the Model and View for adding Special Conditions to a
	 * Parole Board Agreement.
	 * 
	 * @param paroleBoardAgreement - Parole Board Agreement
	 * @param conditionGroup - Condition Group
	 * @return ModelAndView - Model and View for adding Special Conditions to a
	 * Parole Board Agreement.
	 */
	@RequestMapping(value = "/addSpecialGroup.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PAROLE_BOARD_CONDITION_VIEW') or "
			+ "hasRole('ADMIN')")
	public ModelAndView addSpecialConditions(
			@RequestParam(value = "paroleBoardAgreement", required = true)
				final ParoleBoardAgreement paroleBoardAgreement,
			@RequestParam(value = "conditionGroup", required = true)
				final ConditionGroup conditionGroup) {
		return prepareEditMav(paroleBoardAgreement, conditionGroup);
	}
	
	/**
	 * Processes Standard Conditions for a Parole Board Agreement and returns to
	 * the Board Agreements List screen.
	 * 
	 * @param paroleBoardAgreement - Parole Board Agreement
	 * @param form - Parole Board Agreement Condition Select Form
	 * @param bindingResult - Binding Result
	 * @return ModelAndView - returns to the Board Agreements List screen on
	 * successful condition processing, or back to the condition selection
	 * screen on form error.
	 * @throws DuplicateEntityFoundException - When a Condition already exists
	 */
	@RequestMapping(value = "/addStandard.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PAROLE_BOARD_CONDITION_VIEW') or "
			+ "hasRole('ADMIN')")
	public ModelAndView processStandardConditions(
			@RequestParam(value = "paroleBoardAgreement", required = true)
				final ParoleBoardAgreement paroleBoardAgreement,
				final ParoleBoardAgreementConditionSelectForm form,
				final BindingResult bindingResult)
						throws DuplicateEntityFoundException {
		this.formValidator.validate(form, bindingResult);
		if (bindingResult.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(PAROLE_BOARD_AGREEMENT_MODEL_KEY,
					paroleBoardAgreement);
			map.addAttribute(CONDITION_SELECT_FORM_MODEL_KEY, form);
			map.addAttribute(OFFENDER_MODEL_KEY,
					paroleBoardAgreement.getAgreement().getOffender());
			this.offenderSummaryModelDelegate.add(map,
					paroleBoardAgreement.getAgreement().getOffender());
			return new ModelAndView(EDIT_VIEW_NAME, map);
		} else {
			this.processItems(form, paroleBoardAgreement);
			return new ModelAndView(String.format(LIST_REDIRECT_VIEW_NAME,
					paroleBoardAgreement.getAgreement().getOffender().getId()));
		}
	}
	
	/**
	 * Processes Special Conditions for a Parole Board Agreement and returns to
	 * the Board Agreements List screen.
	 * 
	 * @param paroleBoardAgreement - Parole Board Agreement
	 * @param conditionGroup - Condition Group
	 * @param form - Parole Board Agreement Condition Select Form
	 * @param bindingResult - Binding Result
	 * @return ModelAndView - returns to the Board Agreements List screen on
	 * successful condition processing, or back to the condition selection
	 * screen on form error.
	 * @throws DuplicateEntityFoundException - When a Condition already exists
	 */
	@RequestMapping(value = "/addSpecialGroup.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('PAROLE_BOARD_CONDITION_VIEW') or "
			+ "hasRole('ADMIN')")
	public ModelAndView processSpecialConditions(
			@RequestParam(value = "paroleBoardAgreement", required = true)
				final ParoleBoardAgreement paroleBoardAgreement,
			@RequestParam(value = "conditionGroup", required = true)
				final ConditionGroup conditionGroup,
				final ParoleBoardAgreementConditionSelectForm form,
				final BindingResult bindingResult)
						throws DuplicateEntityFoundException {
		this.formValidator.validate(form, bindingResult);
		if (bindingResult.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(PAROLE_BOARD_AGREEMENT_MODEL_KEY,
					paroleBoardAgreement);
			map.addAttribute(CONDITION_SELECT_FORM_MODEL_KEY, form);
			map.addAttribute(CONDITION_GROUP_MODEL_KEY, conditionGroup);
			map.addAttribute(OFFENDER_MODEL_KEY,
					paroleBoardAgreement.getAgreement().getOffender());
			this.offenderSummaryModelDelegate.add(map,
					paroleBoardAgreement.getAgreement().getOffender());
			return new ModelAndView(EDIT_VIEW_NAME, map);
		} else {
			this.processItems(form, paroleBoardAgreement);
			return new ModelAndView(String.format(LIST_REDIRECT_VIEW_NAME,
					paroleBoardAgreement.getAgreement().getOffender().getId()));
		}
	}

	/**
	 * Returns the Model And View for the Parole Board Conditions Select
	 * Action Menu.
	 * 
	 * @param offender - Offender
	 * @return ModelAndView - Model And View for the Parole Board Conditions
	 * Select Action Menu.
	 */
	@RequestMapping(value = "/paroleBoardConditionsSelectActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayParoleBoardConditionsSelectActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(
				PAROLE_BOARD_CONDITIONS_SELECT_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Prepares the Model and View for selecting Conditions for a Parole Board
	 * Agreement.
	 * 
	 * @param paroleBoardAgreement - Parole Board Agreement
	 * @param conditionGroup - Condition Group, leave <code>null</code> to
	 * prepare the view for Standard Conditions.
	 * @return ModelAndView - Model and View for selecting Conditions for a
	 * Parole Board Agreement.
	 */
	private ModelAndView prepareEditMav(
			final ParoleBoardAgreement paroleBoardAgreement,
			final ConditionGroup conditionGroup) {
		ModelMap map = new ModelMap();
		ParoleBoardAgreementConditionSelectForm form =
				new ParoleBoardAgreementConditionSelectForm();
		List<ConditionItem> conditionItems = new ArrayList<ConditionItem>();
		List<ConditionClause> conditionClauses;
		List<Condition> conditions = this.paroleBoardConditionService
				.findConditionsByAgreement(paroleBoardAgreement.getAgreement());
		ConditionCategory conditionCategory;
		if (conditionGroup != null) {
			conditionCategory = ConditionCategory.SPECIAL;
			map.addAttribute(CONDITION_GROUP_MODEL_KEY, conditionGroup);
			conditionClauses = this.paroleBoardConditionService
					.findConditionClausesByConditionGroup(conditionGroup);
		} else {
			conditionCategory = ConditionCategory.STANDARD;
			conditionClauses = this.paroleBoardConditionService
					.findConditionClausesByCategory(paroleBoardAgreement
							.getCategory());
		}
		
		for (ConditionClause conditionClause : conditionClauses) {
			ConditionItem item = new ConditionItem();
			item.setConditionClause(conditionClause);
			item.setConditionCategory(conditionCategory);
			item.setClause(conditionClause.getDescription());
			item.setActive(false);
			item.setItemOperation(ParoleBoardConditionItemOperation.CREATE);
			for (Condition condition : conditions) {
				if (condition.getConditionClause().equals(conditionClause)) {
					if (conditionGroup != null) {
						item.setActive(true);
						item.setClause(condition.getClause());
					} else {
						//If not waived, condition is active.
						//If waived, condition is not active.
						item.setActive(!condition.getWaived());
					}
					item.setCondition(condition);
					item.setItemOperation(
							ParoleBoardConditionItemOperation.UPDATE);
					break;
				}
			}
			conditionItems.add(item);
		}
		form.setConditionCategory(conditionCategory);
		form.setConditionItems(conditionItems);
		
		map.addAttribute(PAROLE_BOARD_AGREEMENT_MODEL_KEY,
				paroleBoardAgreement);
		map.addAttribute(CONDITION_SELECT_FORM_MODEL_KEY, form);
		map.addAttribute(OFFENDER_MODEL_KEY,
				paroleBoardAgreement.getAgreement().getOffender());
		this.offenderSummaryModelDelegate.add(map,
				paroleBoardAgreement.getAgreement().getOffender());
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Processes Condition Items from a Parole Board Agreement Condition Select
	 * Form for creation, updating, or removal.
	 * 
	 * @param form - Parole Board Agreement Condition Select Form
	 * @param paroleBoardAgreement - Parole Board Agreement
	 * @throws DuplicateEntityFoundException - When a Condition already exists
	 */
	private void processItems(
			final ParoleBoardAgreementConditionSelectForm form,
			final ParoleBoardAgreement paroleBoardAgreement)
					throws DuplicateEntityFoundException {
		Agreement agreement = paroleBoardAgreement.getAgreement();
		if (form.getConditionItems() != null) {
			if (ConditionCategory.STANDARD.equals(
					form.getConditionCategory())) {
				for (ConditionItem standardConditionItem
						: form.getConditionItems()) {
					ParoleBoardConditionItemOperation operation =
							standardConditionItem.getItemOperation();
					if (operation != null) {
						Boolean waived = (!standardConditionItem.getActive());
						if (operation.equals(
								ParoleBoardConditionItemOperation.UPDATE)) {
							this.paroleBoardConditionService.updateCondition(
									standardConditionItem.getCondition(),
									standardConditionItem.getConditionClause(),
									standardConditionItem.getConditionClause()
										.getDescription(),
									ConditionCategory.STANDARD, waived);
						} else if (operation.equals(
								ParoleBoardConditionItemOperation.CREATE)) {
							this.paroleBoardConditionService
								.createCondition(agreement,
									standardConditionItem.getConditionClause()
										.getDescription(),
									standardConditionItem.getConditionClause(),
									ConditionCategory.STANDARD, waived);
						}
					}
				}
			} else if (ConditionCategory.SPECIAL.equals(
					form.getConditionCategory())) {
				for (ConditionItem specialConditionItem
						: form.getConditionItems()) {
					if (specialConditionItem.getItemOperation() != null) {
						if (specialConditionItem.getItemOperation().equals(
								ParoleBoardConditionItemOperation.UPDATE)) {
							if (specialConditionItem.getActive()) {
								this.paroleBoardConditionService
									.updateCondition(
									specialConditionItem.getCondition(),
									specialConditionItem.getCondition()
										.getConditionClause(),
									specialConditionItem.getClause(),
									ConditionCategory.SPECIAL, false);
							} else {
								this.paroleBoardConditionService
									.removeCondition(
										specialConditionItem.getCondition());
							}
						} else if (
								specialConditionItem.getItemOperation().equals(
								ParoleBoardConditionItemOperation.CREATE)) {
							if (specialConditionItem.getActive()) {
								this.paroleBoardConditionService
									.createCondition(
										paroleBoardAgreement.getAgreement(),
										specialConditionItem.getClause(),
										specialConditionItem
											.getConditionClause(),
										ConditionCategory.SPECIAL, false);
							}
						} else if (
								specialConditionItem.getItemOperation().equals(
								ParoleBoardConditionItemOperation.REMOVE)) {
							this.paroleBoardConditionService.removeCondition(
									specialConditionItem.getCondition());
						}
					}
				}
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
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				CONDITION_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
					exception);
	}
	
	/** Init binder.
	 * @param binder - web data binder. */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(ConditionGroup.class, 
				this.conditionGroupPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Condition.class, 
				this.conditionPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(ConditionClause.class, 
				this.conditionClausePropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(ParoleBoardAgreement.class, 
				this.paroleBoardAgreementPropertyEditorFactory.
					createPropertyEditor());
	
	}
}
