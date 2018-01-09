package omis.victim.web.controller;

import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.contact.domain.Contact;
import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.VictimNote;
import omis.victim.domain.VictimNoteCategory;
import omis.victim.service.VictimNoteService;
import omis.victim.web.controller.delegate.VictimSummaryModelDelegate;
import omis.victim.web.form.VictimNoteForm;
import omis.victim.web.validator.VictimNoteFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to manage victim notes.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 26, 2015)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/victim/note")
public class ManageVictimNoteController {
	
	/* View names. */
	
	private static final String VIEW_NAME = "victim/note/edit";

	/* Redirects. */
	
	private static final String LIST_REDIRECT
		= "redirect:/victim/note/list.html?victim=%d";
	
	/* Action menu view names. */
	
	private static final String ACTION_MENU_VIEW_NAME
		= "victim/note/includes/victimNoteActionMenu";
	
	/* Model keys. */
	
	private static final String VICTIM_NOTE_FORM_MODEL_KEY = "victimNoteForm";

	private static final String CATEGORIES_MODEL_KEY = "categories";
	
	private static final String ASSOCIATIONS_MODEL_KEY = "associations";
	
	private static final String VICTIM_NOTE_MODEL_KEY = "victimNote";

	/* Message keys. */
	
	private static final String DUPLICATE_ENTITY_FOUND_MESSAGE_KEY
		= "victimNote.exists";

	private static final String ERROR_BUNDLE_NAME = "omis.victim.msgs.form";
	
	/* Services. */
	
	@Autowired
	@Qualifier("victimNoteService")
	private VictimNoteService victimNoteService;
	
	/* Helpers. */
	@Autowired
	@Qualifier("victimSummaryModelDelegate")
	private VictimSummaryModelDelegate victimSummaryModelDelegate;
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	/* Validators. */

	@Autowired
	@Qualifier("victimNoteFormValidator")
	private VictimNoteFormValidator victimNoteFormValidator;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("victimNotePropertyEditorFactory")
	private PropertyEditorFactory victimNotePropertyEditorFactory;
	
	@Autowired
	@Qualifier("victimNoteCategoryPropertyEditorFactory")
	private PropertyEditorFactory victimNoteCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("victimAssociationPropertyEditorFactory")
	private PropertyEditorFactory victimAssociationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("contactPropertyEditorFactory")
	private PropertyEditorFactory contactPropertyEditorFactory;
	
	/* Constructor. */
	
	/**
	 * Instantiates default controller to manage victim notes.
	 */
	public ManageVictimNoteController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows screen to create victim note.
	 * 
	 * @param victim victim for whom to create note
	 * @return screen to create victim note
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('VICTIM_NOTE_CREATE')")
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	public ModelAndView create(
			@RequestParam(value = "victim", required = true)
				final Person victim) {
		VictimNoteForm victimNoteForm = new VictimNoteForm();
		ModelAndView mav = this.prepareEditMav(victimNoteForm, victim);
		return mav;
	}
	
	/**
	 * Shows screen to update victim note.
	 * 
	 * @param victimNote victim note to update
	 * @return screen to update victim note
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('VICTIM_NOTE_VIEW')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "note", required = true)
				final VictimNote victimNote) {
		VictimNoteForm victimNoteForm = new VictimNoteForm();
		victimNoteForm.setCategory(victimNote.getCategory());
		victimNoteForm.setAssociation(victimNote.getAssociation());
		victimNoteForm.setDate(victimNote.getDate());
		victimNoteForm.setValue(victimNote.getValue());
		ModelAndView mav = this.prepareEditMav(
				victimNoteForm, victimNote.getVictim());
		mav.addObject(VICTIM_NOTE_MODEL_KEY, victimNote);
		return mav;
	}
	
	/**
	 * Creates victim note.
	 * 
	 * @param victim victim for whom to create note
	 * @param victimNoteForm form for victim note
	 * @param result result
	 * @return redirect to screen to list notes for victim
	 * @throws DuplicateEntityFoundException if victim note exists
	 */
	@PreAuthorize(
			"hasRole('ADMIN') or hasRole('VICTIM_NOTE_CREATE')")
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	public ModelAndView save(
			@RequestParam(value = "victim", required = true)
				final Person victim,
			final VictimNoteForm victimNoteForm,
			final BindingResult result) throws DuplicateEntityFoundException {
		this.victimNoteFormValidator.validate(victimNoteForm, result);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(victimNoteForm, result, victim);
		}
		this.victimNoteService.create(victim, victimNoteForm.getCategory(),
				victimNoteForm.getAssociation(), victimNoteForm.getDate(),
				victimNoteForm.getValue());
		return new ModelAndView(String.format(LIST_REDIRECT, victim.getId()));
	}
	
	/**
	 * Updates victim note.
	 * 
	 * @param victimNote victim note to update
	 * @param victimNoteForm form for victim note
	 * @param result result
	 * @return redirect to screen to list notes for victim of note
	 * @throws DuplicateEntityFoundException if victim note exists
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('VICTIM_NOTE_EDIT')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam(value = "note", required = true)
				final VictimNote victimNote,
			final VictimNoteForm victimNoteForm,
			final BindingResult result)
					throws DuplicateEntityFoundException {
		this.victimNoteFormValidator.validate(victimNoteForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplayMav(
					victimNoteForm, result, victimNote.getVictim());
			mav.addObject(VICTIM_NOTE_MODEL_KEY, victimNote);
			return mav;
		}
		this.victimNoteService.update(victimNote, victimNoteForm.getCategory(),
				victimNoteForm.getAssociation(), victimNoteForm.getDate(),
				victimNoteForm.getValue());
		return new ModelAndView(String.format(LIST_REDIRECT,
				victimNote.getVictim().getId()));
	}
	
	/**
	 * Removes victim note.
	 * 
	 * @param victimNote victim note to remove
	 * @return redirect to screen to list notes for victim of note 
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('VICTIM_NOTE_REMOVE')")
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	public ModelAndView remove(
			@RequestParam(value = "note", required = true)
				final VictimNote victimNote) {
		Person victim = victimNote.getVictim();
		this.victimNoteService.remove(victimNote);
		return new ModelAndView(String.format(LIST_REDIRECT, victim.getId()));
	}
	
	/* Exceptions handlers. */
	
	/**
	 * Handles {@code DuplicateEntityFoundException}.
	 * 
	 * @param duplicateEntityFoundException exception to handle
	 * @return screen to handle {@code DuplicateEntityFoundException}
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException
				duplicateEntityFoundException) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				DUPLICATE_ENTITY_FOUND_MESSAGE_KEY, ERROR_BUNDLE_NAME,
				duplicateEntityFoundException);
	}
	
	/* Helper methods. */
	
	// Prepares model and view for edit screen
	private ModelAndView prepareEditMav(
			final VictimNoteForm victimNoteForm,
			final Person victim) {
		List<VictimNoteCategory> categories = this.victimNoteService
				.findCategories();
		List<VictimAssociation> associations
			= this.victimNoteService.findAssociationsForVictim(victim);
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(VICTIM_NOTE_FORM_MODEL_KEY, victimNoteForm);
		mav.addObject(CATEGORIES_MODEL_KEY, categories);
		mav.addObject(ASSOCIATIONS_MODEL_KEY, associations);
		this.victimSummaryModelDelegate.add(mav.getModelMap(), victim);
		return mav;
	}
	
	// Prepares redisplay model and view
	private ModelAndView prepareRedisplayMav(
			final VictimNoteForm victimNoteForm,
			final BindingResult result,
			final Person victim) {
		ModelAndView mav = this.prepareEditMav(victimNoteForm, victim);
		mav.addObject(
				BindingResult.MODEL_KEY_PREFIX + VICTIM_NOTE_FORM_MODEL_KEY);
		return mav;
	}
	
	/* Action menus. */
	
	/**
	 * Shows action menu for victim note.
	 * 
	 * @param victim victim
	 * @return action menu for victim note
	 */
	@RequestMapping(
			value = "/victimNoteActionMenu.html", method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "victim", required = true)
				final Person victim) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		this.victimSummaryModelDelegate.add(mav.getModelMap(), victim);
		return mav;
	}
	
	/* Init binders. */
	
	/**
	 * Registers property editor factories.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void registerPropertyEditorFactories(
			final WebDataBinder binder) {
		binder.registerCustomEditor(Person.class,
				this.personPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(VictimNote.class,
				this.victimNotePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(VictimNoteCategory.class,
				this.victimNoteCategoryPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Contact.class,
				this.contactPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(VictimAssociation.class,
				this.victimAssociationPropertyEditorFactory
					.createPropertyEditor());
	}
}