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
package omis.assessment.web.controller;

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

import omis.assessment.domain.AssessmentCategoryOverride;
import omis.assessment.domain.AssessmentCategoryOverrideNote;
import omis.assessment.domain.AssessmentCategoryScore;
import omis.assessment.domain.AssessmentRating;
import omis.assessment.domain.CategoryOverrideReason;
import omis.assessment.service.AssessmentService;
import omis.assessment.web.form.AssessmentCategoryOverrideForm;
import omis.assessment.web.form.AssessmentCategoryOverrideNoteItem;
import omis.assessment.web.form.AssessmentItemOperation;
import omis.assessment.web.validator.AssessmentCategoryOverrideFormValidator;
import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.staff.domain.StaffAssignment;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Assessment Category Override Controller.
 * 
 *@author Annie Wahl 
 *@version 0.1.1 (Jan 31, 2019)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/assessment/rating")
@PreAuthorize("hasRole('USER')")
public class AssessmentCategoryOverrideController {
	
	/* View names. */

	private static final String EDIT_VIEW_NAME = "assessment/rating/edit";

	private static final String
		ASSESSMENT_CATEGORY_OVERRIDE_NOTE_ITEM_ROW_VIEW_NAME =
			"/assessment/rating/includes/"
			+ "assessmentCategoryOverrideNoteItemTableRow";
	
	private static final String LIST_REDIRECT =
			"redirect:/assessment/rating/list.html?"
			+ "administeredQuestionnaire=%d";
	
	/* Action menus. */
	
	private static final String ACTION_MENU_VIEW_NAME =
			"assessment/rating/includes/assessmentCategoryOverrideActionMenu";
	
	private static final String
		ASSESSMENT_CATEGORY_OVERRIDE_NOTE_ITEMS_ACTION_MENU_VIEW_NAME =
			"assessment/rating/includes/"
			+ "assessmentCategoryOverrideNoteItemsActionMenu";
	
	/* Model Keys */
	
	private static final String ADMINISTERED_QUESTIONNAIRE_MODEL_KEY =
			"administeredQuestionnaire";
	
	private static final String ASSESSMENT_CATEGORY_SCORE_MODEL_KEY =
			"assessmentCategoryScore";
	
	private static final String ASSESSMENT_CATEGORY_OVERRIDE_MODEL_KEY =
			"assessmentCategoryOverride";
	
	private static final String RATINGS_MODEL_KEY = "overrideRatings";

	private static final String REASONS_MODEL_KEY = "overrideReasons";
	
	private static final String FORM_MODEL_KEY =
			"assessmentCategoryOverrideForm";
	
	private static final String
		ASSESSMENT_CATEGORY_OVERRIDE_NOTE_ITEM_INDEX_MODEL_KEY =
			"assessmentCategoryOverrideNoteItemIndex";
	
	private static final String
		ASSESSMENT_CATEGORY_OVERRIDE_NOTE_ITEM_MODEL_KEY =
			"assessmentCategoryOverrideNoteItem";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"assessmentCategoryOverride.exists";

	private static final String ERROR_BUNDLE_NAME = "omis.assessment.msgs.form";

	
	/* Property Editor Factories */

	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("administeredQuestionnairePropertyEditorFactory")
	private PropertyEditorFactory
		administeredQuestionnairePropertyEditorFactory;
	
	@Autowired
	@Qualifier("assessmentCategoryScorePropertyEditorFactory")
	private PropertyEditorFactory assessmentCategoryScorePropertyEditorFactory;
	
	@Autowired
	@Qualifier("assessmentRatingPropertyEditorFactory")
	private PropertyEditorFactory assessmentRatingPropertyEditorFactory;
	
	@Autowired
	@Qualifier("assessmentCategoryOverridePropertyEditorFactory")
	private PropertyEditorFactory
		assessmentCategoryOverridePropertyEditorFactory;

	@Autowired
	@Qualifier("assessmentCategoryOverrideNotePropertyEditorFactory")
	private PropertyEditorFactory
		assessmentCategoryOverrideNotePropertyEditorFactory;
	
	@Autowired
	@Qualifier("categoryOverrideReasonPropertyEditorFactory")
	private PropertyEditorFactory categoryOverrideReasonPropertyEditorFactory;
	
	@Autowired
	@Qualifier("staffAssignmentPropertyEditorFactory")
	private PropertyEditorFactory staffAssignmentPropertyEditorFactory;

	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Validator */
	
	@Autowired
	@Qualifier("assessmentCategoryOverrideFormValidator")
	private AssessmentCategoryOverrideFormValidator
		assessmentCategoryOverrideFormValidator;
	
	/* Service */
	
	@Autowired
	@Qualifier("assessmentService")
	private AssessmentService assessmentService;
	
	/**
	 * Default constructor for Assessment Category Override Controller.
	 */
	public AssessmentCategoryOverrideController() {
	}
	
	/**
	 * Returns the model and view for creating an Assessment Category Override. 
	 * 
	 * @param assessmentCategoryScore - Assessment Category Score
	 * @return Model and view for creating an Assessment Category Override.
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ASSESSMENT_CATEGORY_OVERRIDE_CREATE') or "
			+ "hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "assessmentCategoryScore", required = true)
			final AssessmentCategoryScore assessmentCategoryScore) {
		return this.prepareEditMav(assessmentCategoryScore);
	}
	
	/**
	 * Creates an Assessment Category Override and returns the model and view
	 * for the Assessment Rating list screen.
	 * 
	 * @param assessmentCategoryScore - Assessment Category Score
	 * @param form - Assessment Category Override Form
	 * @param bindingResult - Binding Result
	 * @return Model and view for the Assessment Rating list screen after
	 * successful creation of Assessment Category Override, or back to creation
	 * screen on form error.
	 * @throws DuplicateEntityFoundException - When a Assessment Category
	 * Override already exists with the given properties.
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ASSESSMENT_CATEGORY_OVERRIDE_CREATE') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "assessmentCategoryScore", required = true)
			final AssessmentCategoryScore assessmentCategoryScore,
			final AssessmentCategoryOverrideForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException {
		this.assessmentCategoryOverrideFormValidator.validate(
				form, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return this.prepareEditMav(assessmentCategoryScore, form);
		} else {
			AssessmentCategoryOverride assessmentCategoryOverride =
					this.assessmentService.createAssessmentCategoryOverride(
					assessmentCategoryScore, form.getOverrideRating(),
					form.getOverrideReason(), form.getOverrideDate(), null,
					form.getAuthorizedBy());
			this.processNotes(form.getAssessmentCategoryOverrideNoteItems(),
					assessmentCategoryOverride);
			
			return new ModelAndView(String.format(LIST_REDIRECT,
					assessmentCategoryScore
					.getAdministeredQuestionnaire().getId()));
		}
	}
	
	
	/**
	 * Returns the model and view for editing an Assessment Category Override.
	 * 
	 * @param assessmentCategoryOverride - Assessment Category Override to edit.
	 * @return Model and view for editing an Assessment Category Override.
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ASSESSMENT_CATEGORY_OVERRIDE_VIEW') or "
			+ "hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "assessmentCategoryOverride", required = true)
			final AssessmentCategoryOverride assessmentCategoryOverride) {
		return this.prepareEditMav(assessmentCategoryOverride);
	}
	
	/**
	 * Updates an Assessment Category Override and returns the model and view
	 * for the Assessment Rating list screen.
	 * 
	 * @param assessmentCategoryOverride - Assessment Category Override
	 * @param form - Assessment Category Override Form
	 * @param bindingResult - Binding Result
	 * @return Model and view for the Assessment Rating list screen after
	 * successful update of Assessment Category Override, or back to creation
	 * screen on form error.
	 * @throws DuplicateEntityFoundException - When a Assessment Category
	 * Override already exists with the given properties.
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ASSESSMENT_CATEGORY_OVERRIDE_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "assessmentCategoryOverride", required = true)
			final AssessmentCategoryOverride assessmentCategoryOverride,
			final AssessmentCategoryOverrideForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException {
		this.assessmentCategoryOverrideFormValidator.validate(
				form, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return this.prepareEditMav(assessmentCategoryOverride, form);
		} else {
			this.processNotes(form.getAssessmentCategoryOverrideNoteItems(),
					assessmentCategoryOverride);
			this.assessmentService.updateAssessmentCategoryOverride(
					assessmentCategoryOverride,
					assessmentCategoryOverride.getAssessmentCategoryScore(),
					form.getOverrideRating(), form.getOverrideReason(),
					form.getOverrideDate(), form.getNotes(),
					form.getAuthorizedBy());
			
			return new ModelAndView(String.format(LIST_REDIRECT,
					assessmentCategoryOverride.getAssessmentCategoryScore()
					.getAdministeredQuestionnaire().getId()));
		}
	}
	
	/**
	 * Removes the specified Assessment Category Override.
	 * 
	 * @param assessmentCategoryOverride assessment category override to remove
	 * @return  @return Model and view for the Assessment Rating list screen
	 * after successful removal of Assessment Category Override
	 */
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ASSESSMENT_CATEGORY_OVERRIDE_REMOVE') or "
			+ "hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "assessmentCategoryOverride", required = true)
			final AssessmentCategoryOverride assessmentCategoryOverride) {
		for (AssessmentCategoryOverrideNote note : this.assessmentService
			.findAssessmentCategoryOverrideNotesByAssessmentCategoryOverride(
					assessmentCategoryOverride)) {
			this.assessmentService.removeAssessmentCategoryOverrideNote(note);
		}
		this.assessmentService.removeAssessmentCategoryOverride(
				assessmentCategoryOverride);
		
		return new ModelAndView(String.format(LIST_REDIRECT,
				assessmentCategoryOverride.getAssessmentCategoryScore()
				.getAdministeredQuestionnaire().getId()));
	}

	/**
	 * Returns the model and view for a Assessment Category Override Note
	 * Item Row.
	 * 
	 * @param assessmentCategoryOverrideNoteItemIndex - Integer Assessment
	 * Category OverrideNote Item Index
	 * @return ModelAndView - model and view for a Assessment Category Override
	 * Note Item Row.
	 */
	@RequestMapping(value = "createAssessmentCategoryOverrideNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayAssessmentCategoryOverrideNoteItemRow(
			@RequestParam(value = "assessmentCategoryOverrideNoteItemIndex",
			required = true)
			final Integer assessmentCategoryOverrideNoteItemIndex) {
		ModelMap map = new ModelMap();
		AssessmentCategoryOverrideNoteItem noteItem =
				new AssessmentCategoryOverrideNoteItem();
		noteItem.setItemOperation(AssessmentItemOperation.CREATE);
		map.addAttribute(ASSESSMENT_CATEGORY_OVERRIDE_NOTE_ITEM_MODEL_KEY,
				noteItem);
		map.addAttribute(ASSESSMENT_CATEGORY_OVERRIDE_NOTE_ITEM_INDEX_MODEL_KEY,
				assessmentCategoryOverrideNoteItemIndex);
		return new ModelAndView(
				ASSESSMENT_CATEGORY_OVERRIDE_NOTE_ITEM_ROW_VIEW_NAME, map);
	}
	
	/* Action Menus */
	
	/**
	 * Returns the model and view for the assessment category override
	 * action menu.
	 * 
	 * @param assessmentCategoryScore - Assessment Category Score
	 * @return Model and view for the assessment category override
	 * action menu.
	 */
	@RequestMapping(value = "/assessmentCategoryOverrideActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayAssessmentCategoryOverrideActionMenu(
			@RequestParam(value = "assessmentCategoryScore", required = true)
			final AssessmentCategoryScore assessmentCategoryScore) {
		ModelMap map = new ModelMap();
		map.addAttribute(ADMINISTERED_QUESTIONNAIRE_MODEL_KEY,
				assessmentCategoryScore.getAdministeredQuestionnaire());
		return new ModelAndView(ACTION_MENU_VIEW_NAME, map);
	}

	/**
	 * Returns a model and view for assessment category override note items
	 * action menu.
	 * 
	 * @return ModelAndView - model and view for assessment category override
	 * note items action menu
	 */
	@RequestMapping(
			value = "assessmentCategoryOverrideNoteItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayAssessmentCategoryOverrideNoteItemsActionMenu() {
		ModelMap map = new ModelMap();
		return new ModelAndView(
				ASSESSMENT_CATEGORY_OVERRIDE_NOTE_ITEMS_ACTION_MENU_VIEW_NAME,
				map);
	}
	
	/*Helper Methods */

	/**
	 * Processes Assessment Note Items for creation/updating/removal of
	 * Assessment Category Override Notes.
	 * 
	 * @param assessmentCategoryOverrideNoteItems - Assessment Category Override
	 * Note Items to process.
	 * @param assessmentCategoryOverride - Assessment Category Override
	 * with the Assessment Category Override Notes
	 * @throws DuplicateEntityFoundException - When an Assessment Category
	 * Override Note already exists with the provided date and description for
	 * the specified Assessment Category Override.
	 */
	private void processNotes(
			final List<AssessmentCategoryOverrideNoteItem>
					assessmentCategoryOverrideNoteItem,
			final AssessmentCategoryOverride assessmentCategoryOverride)
					throws DuplicateEntityFoundException {
		for (AssessmentCategoryOverrideNoteItem item
				: assessmentCategoryOverrideNoteItem) {
			if (AssessmentItemOperation.CREATE.equals(
					item.getItemOperation())) {
				this.assessmentService.createAssessmentCategoryOverrideNote(
						assessmentCategoryOverride, item.getDescription(),
						item.getDate());
			} else if (AssessmentItemOperation.UPDATE.equals(
					item.getItemOperation())) {
				if (this.isNoteChanged(item.getAssessmentCategoryOverrideNote(),
						item.getDate(), item.getDescription())) {
					this.assessmentService.updateAssessmentCategoryOverrideNote(
						item.getAssessmentCategoryOverrideNote(),
						item.getDescription(), item.getDate());
				}
			} else if (AssessmentItemOperation.REMOVE.equals(
					item.getItemOperation())) {
				this.assessmentService.removeAssessmentCategoryOverrideNote(
						item.getAssessmentCategoryOverrideNote());
			}
		}
	}
	
	/**
	 * Checks if an Assessment Category Override Note has been changed and
	 * returns true if it has.
	 * 
	 * @param note - Assessment Category Override Note to check for change
	 * @param date - Note Date from Form
	 * @param description - Note Description from Form
	 * @return Boolean - true if either the form's note date or value is
	 * different from the original Note's, false otherwise.
	 */
	private boolean isNoteChanged(final AssessmentCategoryOverrideNote note,
			final Date date, final String description) {
		if (!note.getDescription().equals(description)) {
			return true;
		}
		if (!(note.getDate().getTime() == date.getTime())) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param assessmentCategoryScore
	 * @return
	 */
	private ModelAndView prepareEditMav(
			final AssessmentCategoryScore assessmentCategoryScore) {
		return this.prepareEditMav(assessmentCategoryScore,
				new AssessmentCategoryOverrideForm());
	}

	/**
	 * @param assessmentCategoryScore
	 * @param assessmentCategoryOverrideForm
	 * @return
	 */
	private ModelAndView prepareEditMav(
			final AssessmentCategoryScore assessmentCategoryScore,
			final AssessmentCategoryOverrideForm form) {
		ModelMap map = new ModelMap();
		map.addAttribute(ASSESSMENT_CATEGORY_OVERRIDE_NOTE_ITEM_INDEX_MODEL_KEY,
				form.getAssessmentCategoryOverrideNoteItems().size());
		map.addAttribute(RATINGS_MODEL_KEY, this.assessmentService
				.findAssessmentRatingsByCategoryAndQuestionnaireType(
						assessmentCategoryScore.getRatingCategory(),
						assessmentCategoryScore.getAdministeredQuestionnaire()
						.getQuestionnaireType()));
		map.addAttribute(REASONS_MODEL_KEY, this.assessmentService
				.findCategoryOverrideReasonsByRatingCategory(
						assessmentCategoryScore.getRatingCategory()));
		map.addAttribute(FORM_MODEL_KEY, form);
		map.addAttribute(ASSESSMENT_CATEGORY_SCORE_MODEL_KEY,
				assessmentCategoryScore);
		
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	private ModelAndView prepareEditMav(
			final AssessmentCategoryOverride assessmentCategoryOverride) {
		AssessmentCategoryOverrideForm form =
				new AssessmentCategoryOverrideForm();
		
		form.setOverrideDate(assessmentCategoryOverride.getDate());
		form.setAuthorizedBy(assessmentCategoryOverride
				.getApprovedStaffAssignment());
		form.setOverrideRating(assessmentCategoryOverride
				.getAssessmentRating());
		form.setOverrideReason(assessmentCategoryOverride.getReason());
		form.setNotes(assessmentCategoryOverride.getNotes());
		List<AssessmentCategoryOverrideNoteItem> items =
				new ArrayList<AssessmentCategoryOverrideNoteItem>();
		for (AssessmentCategoryOverrideNote note : this.assessmentService
			.findAssessmentCategoryOverrideNotesByAssessmentCategoryOverride(
					assessmentCategoryOverride)) {
			AssessmentCategoryOverrideNoteItem item =
					new AssessmentCategoryOverrideNoteItem();
			item.setAssessmentCategoryOverrideNote(note);
			item.setDate(note.getDate());
			item.setDescription(note.getDescription());
			item.setItemOperation(AssessmentItemOperation.UPDATE);
			items.add(item);
		}
		form.setAssessmentCategoryOverrideNoteItems(items);
		return this.prepareEditMav(assessmentCategoryOverride, form);
	}
	
	/**
	 * @param assessmentCategoryOverride
	 * @param form
	 * @return
	 */
	private ModelAndView prepareEditMav(
			final AssessmentCategoryOverride assessmentCategoryOverride,
			final AssessmentCategoryOverrideForm form) {
		ModelMap map = new ModelMap();
		map.addAttribute(FORM_MODEL_KEY, form);
		map.addAttribute(ASSESSMENT_CATEGORY_OVERRIDE_NOTE_ITEM_INDEX_MODEL_KEY,
				form.getAssessmentCategoryOverrideNoteItems().size());
		map.addAttribute(ASSESSMENT_CATEGORY_SCORE_MODEL_KEY,
				assessmentCategoryOverride.getAssessmentCategoryScore());
		map.addAttribute(ASSESSMENT_CATEGORY_OVERRIDE_MODEL_KEY,
				assessmentCategoryOverride);
		map.addAttribute(RATINGS_MODEL_KEY, this.assessmentService
				.findAssessmentRatingsByCategoryAndQuestionnaireType(
						assessmentCategoryOverride.getAssessmentCategoryScore()
						.getRatingCategory(), assessmentCategoryOverride
						.getAssessmentCategoryScore()
						.getAdministeredQuestionnaire()
						.getQuestionnaireType()));
		map.addAttribute(REASONS_MODEL_KEY, this.assessmentService
				.findCategoryOverrideReasonsByRatingCategory(
						assessmentCategoryOverride.getAssessmentCategoryScore()
						.getRatingCategory()));
		return new ModelAndView(EDIT_VIEW_NAME, map);
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
				ENTITY_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME,
					exception);
	}
	
	/* InitBinder */
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder - web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(AdministeredQuestionnaire.class,
				this.administeredQuestionnairePropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(AssessmentCategoryScore.class,
				this.assessmentCategoryScorePropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(AssessmentCategoryOverride.class,
				this.assessmentCategoryOverridePropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(AssessmentCategoryOverrideNote.class,
				this.assessmentCategoryOverrideNotePropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(CategoryOverrideReason.class,
				this.categoryOverrideReasonPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(StaffAssignment.class,
				this.staffAssignmentPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(AssessmentRating.class,
				this.assessmentRatingPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
}
