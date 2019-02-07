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

import omis.assessment.domain.RatingNote;
import omis.assessment.service.RatingNoteService;
import omis.assessment.web.form.AssessmentItemOperation;
import omis.assessment.web.form.RatingNoteForm;
import omis.assessment.web.form.RatingNoteItem;
import omis.assessment.web.validator.RatingNoteFormValidator;
import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.questionnaire.domain.AdministeredQuestionnaire;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Rating Note Controller.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Apr 18, 2018)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/assessment/rating/notes")
@PreAuthorize("hasRole('USER')")
public class RatingNoteController {
	

	/* View Names */
	
	private static final String EDIT_VIEW_NAME =
			"/assessment/rating/notes/edit";
	
	private static final String RATING_NOTE_ITEMS_ACTION_MENU_VIEW_NAME =
			"/assessment/rating/notes/includes/ratingNoteItemsActionMenu";
	
	private static final String RATING_NOTE_ITEM_ROW_VIEW_NAME =
			"/assessment/rating/notes/includes/ratingNoteItemTableRow";
	
	/* Model Keys */

	private static final String ADMINISTERED_QUESTIONNAIRE_MODEL_KEY =
			"administeredQuestionnaire";
	
	private static final String RATING_NOTE_ITEM_INDEX_MODEL_KEY =
			"ratingNoteItemIndex";
	
	private static final String RATING_NOTE_ITEM_MODEL_KEY =
			"ratingNoteItem";
	
	private static final String FORM_MODEL_KEY = "ratingNoteForm";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"ratingNote.exists";

	private static final String ERROR_BUNDLE_NAME = "omis.assessment.msgs.form";
	
	
	/* Services */
	
	@Autowired
	@Qualifier("ratingNoteService")
	private RatingNoteService ratingNoteService;
	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("administeredQuestionnairePropertyEditorFactory")
	private PropertyEditorFactory
				administeredQuestionnairePropertyEditorFactory;
	
	@Autowired
	@Qualifier("ratingNotePropertyEditorFactory")
	private PropertyEditorFactory ratingNotePropertyEditorFactory;

	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Validator */
	
	@Autowired
	@Qualifier("ratingNoteFormValidator")
	private RatingNoteFormValidator ratingNoteFormValidator;
	
	/**
	 * Default constructor for Rating Note Controller.
	 */
	public RatingNoteController() {
	}
	
	/**
	 * Returns the Model and View for the Rating Note edit/list screen.
	 * 
	 * @param administeredQuestionnaire - Administered Questionnaire
	 * @return Model and View for the Rating Note edit/list screen.
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('RATING_NOTE_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(@RequestParam(
			value = "administeredQuestionnaire", required = true)
			final AdministeredQuestionnaire administeredQuestionnaire) {
		return this.prepareEditMav(administeredQuestionnaire);
	}
	
	/**
	 * Processes Rating Note Items and returns to the Rating Note
	 * edit/list screen.
	 * 
	 * @param administeredQuestionnaire - Administered Questionnaire
	 * @param form - Rating Note Form
	 * @param bindingResult - Binding Result
	 * @return Model and View for the Rating Note edit/list screen.
	 * @throws DuplicateEntityFoundException - When an Rating Note already
	 * exists with the provided date and description for the specified
	 * Administered Questionnaire.
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('RATING_NOTE_CREATE') or "
			+ "hasRole('RATING_NOTE_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(@RequestParam(
			value = "administeredQuestionnaire", required = true)
			final AdministeredQuestionnaire administeredQuestionnaire,
			final RatingNoteForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException {
		this.ratingNoteFormValidator.validate(form, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return this.prepareEditMav(administeredQuestionnaire, form);
		} else {
			this.processNotes(form.getRatingNoteItems(),
					administeredQuestionnaire);
			return this.prepareEditMav(administeredQuestionnaire);
		}
	}
	
	/**
	 * Returns the model and view for a Rating Note Item Row.
	 * 
	 * @param ratingNoteItemIndex - Integer Rating Note Item Index
	 * @return ModelAndView - model and view for a Rating Note Item Row.
	 */
	@RequestMapping(value = "createRatingNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayRatingNoteItemRow(@RequestParam(
			value = "ratingNoteItemIndex", required = true)
			final Integer ratingNoteItemIndex) {
		ModelMap map = new ModelMap();
		RatingNoteItem noteItem = new RatingNoteItem();
		noteItem.setItemOperation(AssessmentItemOperation.CREATE);
		map.addAttribute(RATING_NOTE_ITEM_MODEL_KEY, noteItem);
		map.addAttribute(RATING_NOTE_ITEM_INDEX_MODEL_KEY,
				ratingNoteItemIndex);
		return new ModelAndView(RATING_NOTE_ITEM_ROW_VIEW_NAME, map);
	}
	
	/**
	 * Returns a model and view for assessment note items action menu.
	 * 
	 * @return ModelAndView - model and view for assessment note items
	 * action menu
	 */
	@RequestMapping(value = "ratingNoteItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayRatingNoteItemsActionMenu() {
		ModelMap map = new ModelMap();	
		return new ModelAndView(
				RATING_NOTE_ITEMS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns a Model and View to show current Rating Notes for the 
	 * provided Administered Questionnaire.
	 * 
	 * @param administeredQuestionnaire - Administered Questionnaire
	 * @return Model and View to show current Rating Notes for the 
	 * provided Administered Questionnaire.
	 */
	private ModelAndView prepareEditMav(
			final AdministeredQuestionnaire administeredQuestionnaire) {
		RatingNoteForm form = new RatingNoteForm();
		
		List<RatingNoteItem> items = new ArrayList<RatingNoteItem>();
		for (RatingNote note : this.ratingNoteService
				.findRatingNotesByAdministeredQuestionnaire(
						administeredQuestionnaire)) {
			RatingNoteItem item = new RatingNoteItem();
			item.setRatingNote(note);
			item.setDate(note.getDate());
			item.setDescription(note.getDescription());
			item.setItemOperation(AssessmentItemOperation.UPDATE);
			items.add(item);
		}
		form.setRatingNoteItems(items);
		return this.prepareEditMav(administeredQuestionnaire, form);
	}

	/**
	 * Returns a Model and View to show the Rating Notes for the provided
	 * Administered Questionnaire by Rating Note Items on the provided
	 * Rating Note Form.
	 * 
	 * @param administeredQuestionnaire - Administered Questionnaire
	 * @param form - Rating Note Form
	 * @return Model and View to show the Rating Notes for the provided
	 * Administered Questionnaire by Rating Note Items on the provided
	 * Rating Note Form.
	 */
	private ModelAndView prepareEditMav(
			final AdministeredQuestionnaire administeredQuestionnaire,
			final RatingNoteForm form) {
		ModelMap map = new ModelMap();
		map.addAttribute(ADMINISTERED_QUESTIONNAIRE_MODEL_KEY,
				administeredQuestionnaire);
		map.addAttribute(FORM_MODEL_KEY, form);
		map.addAttribute(RATING_NOTE_ITEM_INDEX_MODEL_KEY,
				form.getRatingNoteItems().size());
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Processes Assment Note Items for creation/updating/removal of Rating
	 * Notes.
	 * 
	 * @param ratingNoteItems - Rating Note Items to process.
	 * @param administeredQuestionnaire - Administered Questionnaire associated
	 * with the Rating Notes
	 * @throws DuplicateEntityFoundException - When an Rating Note already
	 * exists with the provided date and description for the specified
	 * Administered Questionnaire.
	 */
	private void processNotes(
			final List<RatingNoteItem> ratingNoteItems,
			final AdministeredQuestionnaire administeredQuestionnaire)
					throws DuplicateEntityFoundException {
		for (RatingNoteItem item : ratingNoteItems) {
			if (AssessmentItemOperation.CREATE.equals(
					item.getItemOperation())) {
				this.ratingNoteService.createRatingNote(item.getDate(),
						item.getDescription(), administeredQuestionnaire);
			} else if (AssessmentItemOperation.UPDATE.equals(
					item.getItemOperation())) {
				if (this.isNoteChanged(item.getRatingNote(), item.getDate(),
						item.getDescription())) {
					this.ratingNoteService.updateRatingNote(
						item.getRatingNote(),
						item.getDate(), item.getDescription());
				}
			} else if (AssessmentItemOperation.REMOVE.equals(
					item.getItemOperation())) {
				this.ratingNoteService.removeRatingNote(
						item.getRatingNote());
			}
		}
	}
	
	/**
	 * Checks if an Rating Note has been changed and returns true if it
	 * has.
	 * 
	 * @param note - Rating Note to check for change
	 * @param date - Note Date from Form
	 * @param description - Note Description from Form
	 * @return Boolean - true if either the form's note date or value is
	 * different from the original Note's, false otherwise.
	 */
	private boolean isNoteChanged(final RatingNote note,
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
		binder.registerCustomEditor(RatingNote.class,
				this.ratingNotePropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(AdministeredQuestionnaire.class,
				this.administeredQuestionnairePropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
	
}
