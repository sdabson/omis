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
package omis.hearingparticipant.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.boardhearing.domain.BoardHearing;
import omis.boardhearing.web.controller.delegate.BoardHearingSummaryModelDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.family.report.FamilySearchResult;
import omis.family.report.FamilySearchResultService;
import omis.hearingparticipant.domain.HearingParticipant;
import omis.hearingparticipant.domain.HearingParticipantCategory;
import omis.hearingparticipant.domain.HearingParticipantIntentCategory;
import omis.hearingparticipant.domain.HearingParticipantNote;
import omis.hearingparticipant.service.HearingParticipantService;
import omis.hearingparticipant.web.form.HearingParticipantForm;
import omis.hearingparticipant.web.form.HearingParticipantItemOperation;
import omis.hearingparticipant.web.form.HearingParticipantNoteItem;
import omis.hearingparticipant.web.validator.HearingParticipantFormValidator;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;
import omis.util.StringUtility;
import omis.victim.report.VictimSearchResult;
import omis.victim.report.VictimSearchResultService;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Hearing Participant Controller.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 17, 2018)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/hearingParticipant/")
@PreAuthorize("hasRole('USER')")
public class HearingParticipantController {
	
	/* View Names */
	
	private static final String EDIT_VIEW_NAME = "/hearingParticipant/edit";
	
	private static final String HEARING_PARTICIPANT_NOTE_ITEM_ROW_VIEW_NAME =
			"/hearingParticipant/includes/hearingParticipantNoteItemTableRow";
	
	private static final String HEARING_PARTICIPANT_ACTION_MENU_VIEW_NAME =
			"/hearingParticipant/includes/hearingParticipantActionMenu";
	
	private static final String
		HEARING_PARTICIPANT_NOTE_ITEMS_ACTION_MENU_VIEW_NAME =
			"/hearingParticipant/includes/"
			+ "hearingParticipantNoteItemsActionMenu";
	
	private static final String RESULTS_VIEW_NAME =
			"/hearingParticipant/includes/json/results";
	
	/* REDIRECT VIEW NAMES */
	
	private static final String LIST_REDIRECT =
			"redirect:/hearingParticipant/list.html?boardHearing=%d";
	
	/* Model Keys */
	
	private static final String HEARING_PARTICIPANT_MODEL_KEY =
			"hearingParticipant";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String HEARING_PARTICIPANT_NOTE_ITEM_MODEL_KEY =
			"hearingParticipantNoteItem";
	
	private static final String HEARING_PARTICIPANT_NOTE_ITEM_INDEX_MODEL_KEY =
			"hearingParticipantNoteItemIndex";
	
	private static final String BOARD_HEARING_MODEL_KEY = "boardHearing";
	
	private static final String HEARING_PARTICIPANT_CATEGORIES_MODEL_KEY =
			"categories";
	
	private static final String INTENT_CATEGORIES_MODEL_KEY =
			"intentCategories";
	
	private static final String FORM_MODEL_KEY = "hearingParticipantForm";
	
	private static final String RESULTS_MODEL_KEY = "results";

	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.hearingparticipant.msgs.form";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"entity.exists";

	
	
	/* Services */
	
	@Autowired
	@Qualifier("hearingParticipantService")
	private HearingParticipantService hearingParticipantService;
	
	@Autowired
	@Qualifier("victimSearchResultService")
	private VictimSearchResultService victimSearchResultService;
	
	@Autowired
	@Qualifier("familySearchResultService")
	private FamilySearchResultService familySearchResultService;
	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;

	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("hearingParticipantPropertyEditorFactory")
	private PropertyEditorFactory hearingParticipantPropertyEditorFactory;
	
	@Autowired
	@Qualifier("hearingParticipantIntentCategoryPropertyEditorFactory")
	private PropertyEditorFactory
		hearingParticipantIntentCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("hearingParticipantNotePropertyEditorFactory")
	private PropertyEditorFactory hearingParticipantNotePropertyEditorFactory;
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("boardHearingPropertyEditorFactory")
	private PropertyEditorFactory boardHearingPropertyEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("boardHearingSummaryModelDelegate")
	private BoardHearingSummaryModelDelegate boardHearingSummaryModelDelegate;
	
	/* Validator */
	
	@Autowired
	@Qualifier("hearingParticipantFormValidator")
	private HearingParticipantFormValidator hearingParticipantFormValidator;
	
	/**
	 * 
	 */
	public HearingParticipantController() {
	}
	
	/**
	 * Returns the model and View for creating a Hearing Participant.
	 * 
	 * @param boardHearing - Board Hearing
	 * @return ModelAndView - model and View for creating a Hearing Participant.
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('HEARING_PARTICIPANT_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(
			@RequestParam(value = "boardHearing", required = true)
				final BoardHearing boardHearing) {
		return this.prepareEditMav(boardHearing);
	}
	
	/**
	 * Creates a Hearing Participant and returns to the Hearing
	 * Participant list screen.
	 * 
	 * @param boardHearing - Board Hearing the Hearing Participant is being
	 * created for
	 * @param form - Hearing Participant Form
	 * @param bindingResult - Binding Result
	 * @return ModelAndView - redirects to the hearing participant list screen
	 * after successful update, or to the edit screen on form error.
	 * @throws DuplicateEntityFoundException - When a duplicate Hearing
	 * Participant or Hearing Participant Note already exist.
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('HEARING_PARTICIPANT_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "boardHearing", required = true)
				final BoardHearing boardHearing,
				final HearingParticipantForm form,
				final BindingResult bindingResult)
						throws DuplicateEntityFoundException {
		this.hearingParticipantFormValidator.validate(form, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return this.prepareEditMav(boardHearing, form, new ModelMap());
		} else {
			HearingParticipant hearingParticipant =
					this.hearingParticipantService.createHearingParticipant(
							boardHearing, form.getPerson(), form.getCategory(),
							form.getBoardApproved(), form.getWitnessed(),
							form.getFacilityApproved(), form.getIntent(),
							form.getComments());
			this.processItems(hearingParticipant,
					form.getHearingParticipantNoteItems());
			
			return new ModelAndView(String.format(LIST_REDIRECT,
					boardHearing.getId()));
		}
	}

	/**
	 * Returns the model and View for editing a Hearing Participant.
	 * 
	 * @param hearingParticipant - Hearing Participant to update.
	 * @return ModelAndView - model and View for editing a Hearing Participant.
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('HEARING_PARTICIPANT_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "hearingParticipant", required = true)
				final HearingParticipant hearingParticipant) {
		return this.prepareEditMav(hearingParticipant);
	}
	
	/**
	 * Updates the specified Hearing Participant and returns to the Hearing
	 * Participant list screen.
	 * 
	 * @param hearingParticipant - Hearing Participant being updated.
	 * @param form - Hearing Participant Form
	 * @param bindingResult - Binding Result
	 * @return ModelAndView - redirects to the hearing participant list screen
	 * after successful update, or to the edit screen on form error.
	 * @throws DuplicateEntityFoundException - When a duplicate Hearing
	 * Participant or Hearing Participant Note already exist.
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('HEARING_PARTICIPANT_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "hearingParticipant", required = true)
				final HearingParticipant hearingParticipant,
				final HearingParticipantForm form,
				final BindingResult bindingResult)
						throws DuplicateEntityFoundException {
		this.hearingParticipantFormValidator.validate(form, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return this.prepareEditMav(hearingParticipant, form);
		} else {
			this.hearingParticipantService.updateHearingParticipant(
					hearingParticipant, form.getPerson(), form.getCategory(),
					form.getBoardApproved(), form.getWitnessed(),
					form.getFacilityApproved(), form.getIntent(),
					form.getComments());
			this.processItems(hearingParticipant,
					form.getHearingParticipantNoteItems());
			return new ModelAndView(String.format(LIST_REDIRECT,
					hearingParticipant.getBoardHearing().getId()));
		}
	}
	

	/**
	 * Removes the specified Hearing Participant.
	 * 
	 * @param hearingParticipant - Hearing Participant to remove.
	 * @return ModelAndView - redirects to the Hearing Participant list screen
	 */
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('HEARING_PARTICIPANT_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
			@RequestParam(value = "hearingParticipant", required = true)
				final HearingParticipant hearingParticipant) {
		List<HearingParticipantNote> notes = this.hearingParticipantService
				.findHearingParticipantNotesByParticipant(hearingParticipant);
		for (HearingParticipantNote note : notes) {
			this.hearingParticipantService.removeHearingParticipantNote(note);
		}
		this.hearingParticipantService.removeHearingParticipant(
				hearingParticipant);
		return new ModelAndView(String.format(LIST_REDIRECT,
				hearingParticipant.getBoardHearing().getId()));
	}
	
	/**
	 * Displays a hearing participant note item row.
	 * 
	 * @param hearingParticipantNoteItemIndex - integer
	 * @return ModelAndView - view for a hearing participant note item row
	 */
	@RequestMapping(value = "createHearingParticipantNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayHearingParticipantNoteItemRow(@RequestParam(
			value = "hearingParticipantNoteItemIndex", required = true)
			final Integer hearingParticipantNoteItemIndex) {
		ModelMap map = new ModelMap();
		HearingParticipantNoteItem noteItem = new HearingParticipantNoteItem();
		noteItem.setItemOperation(HearingParticipantItemOperation.CREATE);
		map.addAttribute(HEARING_PARTICIPANT_NOTE_ITEM_MODEL_KEY, noteItem);
		map.addAttribute(HEARING_PARTICIPANT_NOTE_ITEM_INDEX_MODEL_KEY,
				hearingParticipantNoteItemIndex);
		return new ModelAndView(
				HEARING_PARTICIPANT_NOTE_ITEM_ROW_VIEW_NAME, map);
	}

	/* Action Menu Views */
	
	/**
	 * Returns the model and view for hearing participant action menu.
	 * 
	 * @param boardHearing - Board Hearing
	 * @return ModelAndView - model and view for hearing participant action menu
	 */
	@RequestMapping(value = "/hearingParticipantActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView displayHearingParticipantActionMenu(
			@RequestParam(value = "boardHearing", required = true)
			final BoardHearing boardHearing) {
		ModelMap map = new ModelMap();
		map.addAttribute(BOARD_HEARING_MODEL_KEY, boardHearing);
		
		return new ModelAndView(HEARING_PARTICIPANT_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns a model and view for hearing participant note items action menu.
	 * 
	 * @return ModelAndView - model and view for hearing participant note
	 * items action menu
	 */
	@RequestMapping(value = "hearingParticipantNoteItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayHearingParticipantNoteItemsActionMenu() {
		ModelMap map = new ModelMap();	
		return new ModelAndView(
				HEARING_PARTICIPANT_NOTE_ITEMS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/* JSON */
	
	/**
	 * Returns the view for Family search.
	 * 
	 * @param name - String name query
	 * @param offender - Offender
	 * @return ModelAndView - the view for a Family search.
	 * @throws IOException - IO Exception
	 */
	@RequestMapping(value = "/searchFamily.json", 
		method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView searchFamilyByName(
		@RequestParam(value = "term", required = false) 
			final String name,
		@RequestParam(value = "offender", required = true) 
		final Offender offender) throws IOException {
		List<FamilySearchResult> familySearchResults;
		if (StringUtility.hasContent(name)) {
			familySearchResults = this.familySearchResultService
					.findFamilySearchResultsByUnspecifiedAndOffender(
							name, offender);
		} else {
			familySearchResults = Collections.emptyList();
		}
		ModelAndView mav = new ModelAndView(RESULTS_VIEW_NAME);
		mav.addObject(RESULTS_MODEL_KEY, familySearchResults);
		return mav;
	}
	
	/**
	 * Returns the view for Victim search.
	 * 
	 * @param name - String name query
	 * @param offender - Offender
	 * @return ModelAndView - the view for a victim search.
	 * @throws IOException - IO Exception
	 */
	@RequestMapping(value = "/searchVictims.json", 
		method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView searchVictimsByName(
		@RequestParam(value = "term", required = false) 
			final String name,
		@RequestParam(value = "offender", required = true) 
			final Offender offender) throws IOException {
		List<VictimSearchResult> victimSearchResults;
		if (StringUtility.hasContent(name)) {
			victimSearchResults = this.victimSearchResultService
					.findVictimSearchResultsByUnspecifiedAndOffender(
							name, offender);
		} else {
			victimSearchResults = Collections.emptyList();
		}
		ModelAndView mav = new ModelAndView(RESULTS_VIEW_NAME);
		mav.addObject(RESULTS_MODEL_KEY, victimSearchResults);
		return mav;
	}
	
	/* Private helper methods */
	
	/**
	 * Prepares the model and view for creating/editing a Hearing Participant.
	 * 
	 * @param boardHearing - Board Hearing
	 * @param form - Hearing Participant Form
	 * @param map - Model Map
	 * @return ModelAndView - prepared Model and View for creating/editing a
	 * Hearing Participant.
	 */
	private ModelAndView prepareEditMav(final BoardHearing boardHearing,
			final HearingParticipantForm form, final ModelMap map) {
		map.addAttribute(BOARD_HEARING_MODEL_KEY, boardHearing);
		map.addAttribute(INTENT_CATEGORIES_MODEL_KEY,
				this.hearingParticipantService
				.findHearingParticipantIntentCategories());
		map.addAttribute(HEARING_PARTICIPANT_CATEGORIES_MODEL_KEY,
				HearingParticipantCategory.values());
		map.addAttribute(FORM_MODEL_KEY, form);
		map.addAttribute(HEARING_PARTICIPANT_NOTE_ITEM_INDEX_MODEL_KEY,
				form.getHearingParticipantNoteItems().size());
		map.addAttribute(OFFENDER_MODEL_KEY,
				boardHearing.getParoleEligibility().getOffender());
		this.offenderSummaryModelDelegate.add(map,
				boardHearing.getParoleEligibility().getOffender());
		this.boardHearingSummaryModelDelegate.add(map, boardHearing);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	private ModelAndView prepareEditMav(final BoardHearing boardHearing) {
		return this.prepareEditMav(boardHearing,
				new HearingParticipantForm(), new ModelMap());
	}
	
	private ModelAndView prepareEditMav(
			final HearingParticipant hearingParticipant) {
		return this.prepareEditMav(
				hearingParticipant, this.populateForm(hearingParticipant));
	}
	
	private ModelAndView prepareEditMav(
			final HearingParticipant hearingParticipant,
			final HearingParticipantForm form) {
		ModelMap map = new ModelMap();
		map.addAttribute(HEARING_PARTICIPANT_MODEL_KEY, hearingParticipant);
		return this.prepareEditMav(
				hearingParticipant.getBoardHearing(), form, map);
	}
	
	/**
	 * Populates a Hearing Participant Form from the given Hearing Participant.
	 * 
	 * @param hearingParticipant - Hearing Participant
	 * @return Populated Hearing Participant Form
	 */
	private HearingParticipantForm populateForm(
			final HearingParticipant hearingParticipant) {
		HearingParticipantForm form = new HearingParticipantForm();
		List<HearingParticipantNoteItem> items =
				new ArrayList<HearingParticipantNoteItem>();
		for (HearingParticipantNote note : this.hearingParticipantService
				.findHearingParticipantNotesByParticipant(hearingParticipant)) {
			HearingParticipantNoteItem item = new HearingParticipantNoteItem();
			item.setDate(note.getDate());
			item.setDescription(note.getDescription());
			item.setHearingParticipantNote(note);
			item.setItemOperation(HearingParticipantItemOperation.UPDATE);
			items.add(item);
		}
		form.setHearingParticipantNoteItems(items);
		form.setBoardApproved(hearingParticipant.getBoardApproved());
		form.setCategory(hearingParticipant.getCategory());
		form.setIntent(hearingParticipant.getIntent());
		form.setPerson(hearingParticipant.getPerson());
		form.setWitnessed(hearingParticipant.getWitness());
		form.setFacilityApproved(hearingParticipant.getFacilityApproved());
		form.setComments(hearingParticipant.getComments());
		
		return form;
	}
	
	/**
	 * Processes a list of Hearing Participant Note Items.
	 * 
	 * @param hearingParticipant - Hearing Participant the notes are for
	 * @param hearingParticipantNoteItems - List of Hearing Participant Note
	 * Items to process.
	 * @throws DuplicateEntityFoundException - When a HearingParticipantNote
	 * already exists with the given description and date for the specified
	 * HearingParticipant. 
	 */
	private void processItems(final HearingParticipant hearingParticipant,
			final List<HearingParticipantNoteItem>
				hearingParticipantNoteItems)
						throws DuplicateEntityFoundException {
		if (hearingParticipantNoteItems != null) {
			for (HearingParticipantNoteItem item
					: hearingParticipantNoteItems) {
				if (HearingParticipantItemOperation.CREATE.equals(
						item.getItemOperation())) {
					this.hearingParticipantService.createHearingParticipantNote(
							hearingParticipant, item.getDescription(),
							item.getDate());
				} else if (HearingParticipantItemOperation.UPDATE.equals(
						item.getItemOperation())) {
					if (this.isNoteChanged(item.getHearingParticipantNote(),
							item.getDate(), item.getDescription())) {
						this.hearingParticipantService
							.updateHearingParticipantNote(
								item.getHearingParticipantNote(),
								hearingParticipant, item.getDescription(),
								item.getDate());
					}
				} else if (HearingParticipantItemOperation.REMOVE.equals(
						item.getItemOperation())) {
					this.hearingParticipantService.removeHearingParticipantNote(
							item.getHearingParticipantNote());
				}
			}
		}
	}
	
	/**
	 * Checks if a Hearing Participant Note has been changed and returns true
	 * if it has.
	 * @param note - Hearing Participant Note to check for change
	 * @param date - Note Date from Form
	 * @param value - Note Value from Form
	 * @return Boolean - true if either the form's note date or value is
	 * different
	 * from the original Note's, false otherwise.
	 */
	private boolean isNoteChanged(final HearingParticipantNote note,
			final Date date, final String value) {
		if (!note.getDescription().equals(value)) {
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
				ENTITY_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, exception);
	}
	
	/* InitBinder */
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder - web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(HearingParticipant.class,
				this.hearingParticipantPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(HearingParticipant.class,
				this.hearingParticipantPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(HearingParticipantNote.class,
				this.hearingParticipantNotePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(HearingParticipantIntentCategory.class,
				this.hearingParticipantIntentCategoryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Person.class,
				this.personPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(BoardHearing.class,
				this.boardHearingPropertyEditorFactory
				.createPropertyEditor());
	}
}
