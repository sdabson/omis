package omis.workrestriction.web.controller;

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

import omis.audit.domain.AuthorizationSignature;
import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.workrestriction.web.form.ItemOperation;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.user.domain.UserAccount;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;
import omis.workrestriction.domain.WorkRestriction;
import omis.workrestriction.domain.WorkRestrictionCategory;
import omis.workrestriction.domain.WorkRestrictionNote;
import omis.workrestriction.service.WorkRestrictionService;
import omis.workrestriction.web.form.NoteItem;
import omis.workrestriction.web.form.WorkRestrictionForm;
import omis.workrestriction.web.validator.WorkRestrictionFormValidator;

/**
 * WorkRestrictionController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 17, 2016)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/workRestriction/")
@PreAuthorize("hasRole('USER')")
public class WorkRestrictionController {
	
	/* View Names */
	
	private static final String EDIT_VIEW_NAME = "/workRestriction/edit";
	
	private static final String WORK_RESTRICTION_ACTION_MENU_VIEW_NAME
		= "workRestriction/includes/workRestrictionActionMenu";
	
	private static final String USER_ACCOUNTS_VIEW_NAME
		= "user/json/userAccounts";
	
	private static final String NOTE_ITEM_ROW_VIEW_NAME 
		= "workRestriction/includes/noteItemTableRow";
	
	private static final String NOTE_ITEMS_ACTION_MENU_VIEW_NAME 
		= "/workRestriction/includes/noteItemsActionMenu";
	
	/* Model Keys */
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String FORM_MODEL_KEY = "workRestrictionForm";
	
	private static final String CATEGORIES_MODEL_KEY = "categories";
	
	private static final String WORK_RESTRICTION_MODEL_KEY = "workRestriction";
	
	private static final String USER_ACCOUNTS_MODEL_KEY = "userAccounts";
	
	private static final String NOTE_ITEM_INDEX_MODEL_KEY = "noteItemIndex";
	
	private static final String NOTE_ITEM_MODEL_KEY = "noteItem";
	
	/* Redirect View Name */
	
	private static final String LIST_REDIRECT 
		= "redirect:/workRestriction/list.html?offender=%d";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY
		= "entity.exists";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME
		= "omis.workrestriction.msgs.form";
	
	/* Service */

	@Autowired
	@Qualifier("workRestrictionService")
	private WorkRestrictionService workRestrictionService;
	
	/* Property Editors */
	
	@Autowired
	@Qualifier("workRestrictionPropertyEditorFactory")
	private PropertyEditorFactory workRestrictionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("workRestrictionNotePropertyEditorFactory")
	private PropertyEditorFactory workRestrictionNotePropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Helpers */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	WorkRestrictionFormValidator formValidator;
	
	/* Constructor */
	
	/**
	 * Default Controller 
	 */
	public WorkRestrictionController() {
		//Nope.
	}
	
	/* Model and Views */
	
	
	/**
	 * Returns a view for creating a work restriction for specified offender
	 * @param offender - specified offender
	 * @return ModelAndView - view for creating a work restriction for 
	 * specified offender
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('WORK_RESTRICTION_CREATE') or "
			+ "hasRole('ADMIN')")
	public ModelAndView create(@RequestParam(
			value = "offender", required=true) final Offender offender){
		return prepareEditMav(
				offender, new WorkRestrictionForm(),  new ModelMap());
	}
	
	/**
	 * Attempts to save a work restriction and return to the list view
	 * @param offender - specified offender
	 * @param form - work restriction form
	 * @param bindingResult - binding result
	 * @return ModelAndView - back to the create view on errors, or to the
	 * work restrictions list view on successful save
	 * @throws DuplicateEntityFoundException - when work restriction already
	 * exists with given category for the offender
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('WORK_RESTRICTION_CREATE') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(@RequestParam(
			value = "offender", required=true) final Offender offender,
			final WorkRestrictionForm form, final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		this.formValidator.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return prepareEditMav(offender, form,  new ModelMap());
		}
		else{
			WorkRestrictionCategory category = null;
			for(WorkRestrictionCategory categoryIn 
					: this.workRestrictionService.findAllCategories()){
				if(categoryIn.getName().equals(form.getCategory())){
					category = categoryIn;
				}
			}
			
				WorkRestriction workRestriction = 
						this.workRestrictionService.create(offender, category, 
						new AuthorizationSignature(form.getAuthorizedByUser(), 
								form.getAuthorizationDate()), form.getNotes());
				
				this.processNoteItems(form.getNoteItems(), workRestriction);
				
				return new ModelAndView(String.format(
						LIST_REDIRECT, offender.getId()));
		}
	}
	
	/**
	 * Returns a view to edit an existing work restriction
	 * @param workRestriction - work restriction to edit
	 * @return ModelAndView - view to edit existing work restriction
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('WORK_RESTRICTION_VIEW') or "
			+ "hasRole('ADMIN')")
	public ModelAndView edit(@RequestParam(
			value="workRestriction", required=true) 
			final WorkRestriction workRestriction){
		WorkRestrictionForm form = new WorkRestrictionForm();
		List<WorkRestrictionNote> notes = this.workRestrictionService
				.findNotes(workRestriction);
		List<NoteItem> noteItems = new ArrayList<NoteItem>();
		
		for(WorkRestrictionNote note : notes){
			NoteItem noteItem = new NoteItem();
			noteItem.setDate(note.getDate());
			noteItem.setValue(note.getValue());
			noteItem.setNote(note);
			noteItem.setOperation(ItemOperation.UPDATE);
			noteItems.add(noteItem);
		}
		
		form.setNoteItems(noteItems);
		form.setAuthorizationDate(
				workRestriction.getAuthorizationSignature().getDate());
		form.setAuthorizedByUser(
				workRestriction.getAuthorizationSignature().getUserAccount());
		form.setCategory(workRestriction.getCategory().getName());
		form.setNotes(workRestriction.getNotes());
		
		ModelMap map = new ModelMap();
		map.addAttribute(WORK_RESTRICTION_MODEL_KEY, workRestriction);
		return prepareEditMav(workRestriction.getOffender(), form,  map);
	}
	
	/**
	 * Attempts to update specified work restriction 
	 * @param workRestriction - work restriction to update
	 * @param form - work restriction form
	 * @param bindingResult - binding result
	 * @return ModelAndView - Back to edit screen on errors, or back to 
	 * work restrictions list view on successful update
	 * @throws DuplicateEntityFoundException - when work restriction already
	 * exists with given category for the offender
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('WORK_RESTRICTION_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView update(@RequestParam(
			value="workRestriction", required=true) 
			final WorkRestriction workRestriction, 
			final WorkRestrictionForm form, final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.formValidator.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			ModelMap map = new ModelMap();
			map.addAttribute(WORK_RESTRICTION_MODEL_KEY, workRestriction);
			return prepareEditMav(workRestriction.getOffender(), form,  map);
		}
		else{
			WorkRestrictionCategory category = null;
			for(WorkRestrictionCategory categoryIn 
					: this.workRestrictionService.findAllCategories()){
				if(categoryIn.getName().equals(form.getCategory())){
					category = categoryIn;
				}
			}
			
			this.processNoteItems(form.getNoteItems(), workRestriction);
			
			this.workRestrictionService.update(workRestriction, category, 
					new AuthorizationSignature(form.getAuthorizedByUser(), 
							form.getAuthorizationDate()), form.getNotes());
			
			return new ModelAndView(String.format(
					LIST_REDIRECT, workRestriction.getOffender().getId()));
		}
	}
	
	/**
	 * Removes specified work restriction
	 * @param workRestriction - work restriction to remove
	 * @return ModelAndView - back to work restrictions list view
	 */
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('WORK_RESTRICTION_REMOVE') or "
			+ "hasRole('ADMIN')")
	public ModelAndView remove(@RequestParam(
			value="workRestriction", required=true) 
			final WorkRestriction workRestriction){
		List<WorkRestrictionNote> notes = this.workRestrictionService
				.findNotes(workRestriction);
		
		for(WorkRestrictionNote note : notes){
			this.workRestrictionService.removeNote(note);
		}
		
		this.workRestrictionService.remove(workRestriction);
		
		
		return new ModelAndView(String.format(
				LIST_REDIRECT, workRestriction.getOffender().getId()));
	}	
	
	/**
	 * Displays a  note item row.
	 * 
	 * @return model and view for note item row
	 */
	@RequestMapping(value = "createNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayNoteItemRow(@RequestParam(
			value = "noteItemIndex", required = true)
			final Integer noteItemIndex) {
		ModelMap map = new ModelMap();
		NoteItem noteItem = new NoteItem();
		noteItem.setOperation(ItemOperation.CREATE);
		map.addAttribute(NOTE_ITEM_MODEL_KEY, noteItem);
		map.addAttribute(NOTE_ITEM_INDEX_MODEL_KEY, noteItemIndex);
		return new ModelAndView(NOTE_ITEM_ROW_VIEW_NAME, map);
	}
	
	
	/* Action Menu Views */
	
	/**
	 * Displays action menu for work restriction screen
	 * @param offender - offender associated with work restriction
	 * @return ModelAndView - view for action menu for work restriction screen
	 */
	@RequestMapping(value="/workRestrictionActionMenu.html", 
			method=RequestMethod.GET)
	public ModelAndView displayWorkRestrictionActionMenu(@RequestParam(
			value = "offender", required=true) final Offender offender){
		
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(WORK_RESTRICTION_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the action menu for note items.
	 * 
	 * @return model and view for note items action menu
	 */
	@RequestMapping(value = "noteItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayNoteItemsActionMenu() {
		ModelMap map = new ModelMap();	
		return new ModelAndView(
				NOTE_ITEMS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/* JSON */
	
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
			= this.workRestrictionService.findUserAccounts(query);
		ModelAndView mav = new ModelAndView(USER_ACCOUNTS_VIEW_NAME);
		mav.addObject(USER_ACCOUNTS_MODEL_KEY, userAccounts);
		return mav;
	}
	
	
	/* Helpers */
	
	/**
	 * Returns a prepared model and view for editing a work restriction
	 * @param offender - offender associated with work restriction
	 * @param form - work restriction form
	 * @param map - modelMap
	 * @return ModelAndView - for editing a work restriction
	 */
	public ModelAndView prepareEditMav(final Offender offender, 
			final WorkRestrictionForm form, final ModelMap map){
		List<WorkRestrictionCategory> categories 
			= this.workRestrictionService.findAllCategories();
		
		final Integer noteIndex;
		if (form.getNoteItems() != null) {
			noteIndex = form.getNoteItems().size(); 
		} else {
			noteIndex = 0;
		}
		
		
		this.offenderSummaryModelDelegate.add(map, offender);
		map.addAttribute(NOTE_ITEM_INDEX_MODEL_KEY, noteIndex);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(CATEGORIES_MODEL_KEY, categories);
		map.addAttribute(FORM_MODEL_KEY, form);
		
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Processes the specified list of note items
	 * according to their specified operation values.
	 * 
	 * @param items note items
	 * @param education education term
	 * @throws DuplicateEntityFoundException thrown when a duplicate education
	 * note is found
	 */
	private void processNoteItems(
			final List<NoteItem> items,
			final WorkRestriction workRestriction)
		throws DuplicateEntityFoundException {
		if (items != null) {
			for (NoteItem item 
					: items) {
				if (ItemOperation.CREATE
						.equals(item.getOperation())) {
					this.workRestrictionService.createNote(workRestriction, 
							item.getValue(), item.getDate());
				} else if (ItemOperation.UPDATE
						.equals(item.getOperation())) {
					if(this.isNoteChanged(item.getNote(), item.getDate(),
								item.getValue())) {
						this.workRestrictionService.updateNote(
								item.getNote(), item.getValue(), item.getDate());
					}
				} else if (ItemOperation.REMOVE
						.equals(item.getOperation())) {
					this.workRestrictionService.removeNote(
							item.getNote());
				}
			}
		}
	}
	
	/**
	 * Checks if a Work Restriction Note has been changed and returns true if it has
	 * @param note - Work Restriction Note to check for change
	 * @param date - Note Date from Form
	 * @param value - Note Value from Form
	 * @return Boolean - true if either the form's note date or value is different
	 * from the original Note's, false otherwise.
	 */
	private boolean isNoteChanged(final WorkRestrictionNote note,
			final Date date, final String value) {
		if(!note.getValue().equals(value)) {
			return true;
		}
		if(!(note.getDate().getTime() == date.getTime())) {
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
		return this.businessExceptionHandlerDelegate.prepareModelAndView
		(ENTITY_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/* InitBinder */
	
	/**
	 * Sets up and registers property editors
	 * 
	 * @param binder - web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder){
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(WorkRestriction.class, 
				this.workRestrictionPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(WorkRestrictionNote.class, 
				this.workRestrictionNotePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(UserAccount.class, 
				this.userAccountPropertyEditorFactory.createPropertyEditor());
	}
	

}
