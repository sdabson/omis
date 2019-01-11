package omis.presentenceinvestigation.web.controller;

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
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummary;
import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.JailAdjustmentSectionSummaryService;
import omis.presentenceinvestigation.web.controller.delegate.PresentenceInvestigationRequestSummaryModelDelegate;
import omis.presentenceinvestigation.web.form.JailAdjustmentSectionSummaryForm;
import omis.presentenceinvestigation.web.form.JailAdjustmentSectionSummaryNoteItem;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.validator.JailAdjustmentSectionSummaryFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Jail adjustment section summary controller.
 * 
 * @author Annie Wahl
 * @author Josh Divine
 * @version 0.1.1 (Oct 24, 2018)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/presentenceInvestigation/jailAdjustmentSummary/")
@PreAuthorize("hasRole('USER')")
public class JailAdjustmentSectionSummaryController {
	
	/* View names */
	
	private static final String EDIT_VIEW_NAME =
			"/presentenceInvestigation/jailAdjustmentSummary/edit";
	
	private static final String
			JAIL_ADJUSTMENT_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME =
			"/presentenceInvestigation/jailAdjustmentSummary/includes/"
			+ "jailAdjustmentSectionSummaryNoteTableRow";
	
	private static final String
			JAIL_ADJUSTMENT_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME =
			"/presentenceInvestigation/jailAdjustmentSummary/includes/"
			+ "jailAdjustmentSectionSummaryActionMenu";
	
	private static final String
			JAIL_ADJUSTMENT_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME =
			"/presentenceInvestigation/jailAdjustmentSummary/includes/"
			+ "jailAdjustmentSectionSummaryNoteItemsActionMenu";
	
	/* Model Keys */
	
	private static final String JAIL_ADJUSTMENT_SECTION_SUMMARY_MODEL_KEY =
			"jailAdjustmentSectionSummary";
	
	private static final String JAIL_ADJUSTMENT_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY
			= "jailAdjustmentSectionSummaryNoteItem";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY =
			"presentenceInvestigationRequest";
	
	private static final String
			JAIL_ADJUSTMENT_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY =
					"jailAdjustmentSectionSummaryNoteItemIndex";
	
	private static final String FORM_MODEL_KEY =
			"jailAdjustmentSectionSummaryForm";
	
	/* REDIRECT VIEW NAMES */
	
	private static final String HOME_REDIRECT =
			"redirect:/presentenceInvestigation/home.html?"
			+ "presentenceInvestigationRequest=%d";
	
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"jailAdjustmentSectionSummaryNote.exists";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.presentenceinvestigation.msgs.form";
	
	/* Service */
	
	@Autowired
	@Qualifier("jailAdjustmentSectionSummaryService")
	private JailAdjustmentSectionSummaryService
				jailAdjustmentSectionSummaryService;
	
	
	
	
	@Autowired
	@Qualifier("jailAdjustmentSectionSummaryPropertyEditorFactory")
	private PropertyEditorFactory jailAdjustmentSectionSummaryPropertyEditorFactory;

	@Autowired
	@Qualifier("jailAdjustmentSectionSummaryNotePropertyEditorFactory")
	private PropertyEditorFactory jailAdjustmentSectionSummaryNotePropertyEditorFactory;
	

	@Autowired
	@Qualifier("presentenceInvestigationRequestPropertyEditorFactory")
	PropertyEditorFactory presentenceInvestigationRequestPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestSummaryModelDelegate")
	private PresentenceInvestigationRequestSummaryModelDelegate
			presentenceInvestigationRequestSummaryModelDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	
	/* Validator */
	
	@Autowired
	@Qualifier("jailAdjustmentSectionSummaryFormValidator")
	private JailAdjustmentSectionSummaryFormValidator
			jailAdjustmentSectionSummaryFormValidator;
	
	/**
	 * 
	 */
	public JailAdjustmentSectionSummaryController() {
	}
	
	/**
	 * Displays the ModelAndView for editing a JailAdjustmentSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView for editing a JailAdjustmentSectionSummary
	 * @throws DuplicateEntityFoundException - When a JailAdjustmentSectionSummary
	 * already exists for the presentence investigation
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('JAIL_ADJUSTMENT_SECTTION_SUMMARY_VIEW') or "
			+ "hasRole('JAIL_ADJUSTMENT_SECTTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest)
						throws DuplicateEntityFoundException{
		
		JailAdjustmentSectionSummary jailAdjustmentSectionSummary =
				this.jailAdjustmentSectionSummaryService
			.findJailAdjustmentSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
		
		if(jailAdjustmentSectionSummary == null){
			jailAdjustmentSectionSummary =
					this.jailAdjustmentSectionSummaryService
					.createJailAdjustmentSectionSummary(
							presentenceInvestigationRequest);
		}
		
		return this.prepareEditMav(jailAdjustmentSectionSummary, 
				this.prepareForm(jailAdjustmentSectionSummary));
	}
	
	/**
	 * Saves a JailAdjustmentSectionSummary and Notes and returns to the
	 * PresentenceInvestigationRequest Home screen
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param form - JailAdjustmentSectionSummaryForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - returns to PresentenceInvestigationRequest Home 
	 * on successful save, or back to JailAdjustmentSectionSummary on form error
	 * @throws DuplicateEntityFoundException - When a JailAdjustmentSectionSummaryNote
	 * already exists for the JailAdjustmentSectionSummary with provided
	 * description and date
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('JAIL_ADJUSTMENT_SECTTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest,
			final JailAdjustmentSectionSummaryForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.jailAdjustmentSectionSummaryFormValidator
				.validate(form, bindingResult);
		
		JailAdjustmentSectionSummary jailAdjustmentSectionSummary =
				this.jailAdjustmentSectionSummaryService
			.findJailAdjustmentSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
		
		if(bindingResult.hasErrors()){
			return this.prepareEditMav(jailAdjustmentSectionSummary, form);
		}
		else{
			for(JailAdjustmentSectionSummaryNoteItem noteItem :
				form.getJailAdjustmentSectionSummaryNoteItems()){
				if(PresentenceInvestigationItemOperation.CREATE.equals(
						noteItem.getItemOperation())){
					this.jailAdjustmentSectionSummaryService
						.createJailAdjustmentSectionSummaryNote(
								noteItem.getDescription(), noteItem.getDate(),
								jailAdjustmentSectionSummary);
				}
				else if(PresentenceInvestigationItemOperation.UPDATE
						.equals(noteItem.getItemOperation())){
					this.jailAdjustmentSectionSummaryService
						.updateJailAdjustmentSectionSummaryNote(
								noteItem.getJailAdjustmentSectionSummaryNote(),
								noteItem.getDescription(), noteItem.getDate());
				}
				else if(PresentenceInvestigationItemOperation.REMOVE
						.equals(noteItem.getItemOperation())){
					this.jailAdjustmentSectionSummaryService
						.removeJailAdjustmentSectionSummaryNote(
								noteItem.getJailAdjustmentSectionSummaryNote());
				}
			}
			
			return new ModelAndView(String.format(HOME_REDIRECT,
					presentenceInvestigationRequest.getId()));
		}
	}
	
	
	
	
	
	
	/**
	 * Displays a JailAdjustmentSectionSummaryNote item row
	 * @param jailAdjustmentSectionSummaryNoteItemIndex - Integer
	 * @return ModelAndView - JailAdjustmentSectionSummaryNote item row
	 */
	@RequestMapping(value = "createJailAdjustmentSectionSummaryNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayJailAdjustmentSectionSummaryNoteItem(@RequestParam(
			value = "jailAdjustmentSectionSummaryNoteItemIndex", required = true)
				final Integer jailAdjustmentSectionSummaryNoteItemIndex){
		ModelMap map = new ModelMap();
		
		JailAdjustmentSectionSummaryNoteItem noteItem =
				new JailAdjustmentSectionSummaryNoteItem();
		
		noteItem.setItemOperation(
				PresentenceInvestigationItemOperation.CREATE);
		
		map.addAttribute(JAIL_ADJUSTMENT_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY,
				noteItem);
		map.addAttribute(
				JAIL_ADJUSTMENT_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
				jailAdjustmentSectionSummaryNoteItemIndex);
		
		
		return new ModelAndView(
				JAIL_ADJUSTMENT_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME, map);
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Displays the JailAdjustmentSectionSummary Action Menu
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView - JailAdjustmentSectionSummary Action Menu
	 */
	@RequestMapping(value="/jailAdjustmentSectionSummaryActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayJailAdjustmentSectionSummaryActionMenu(
			@RequestParam(value="presentenceInvestigationRequest",
			required = true)
			final PresentenceInvestigationRequest
					presentenceInvestigationRequest){
		ModelMap map = new ModelMap();
		
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				presentenceInvestigationRequest);
		
		return new ModelAndView(
				JAIL_ADJUSTMENT_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the JailAdjustmentSectionSummaryNote Items Action Menu
	 * @return ModelAndView - JailAdjustmentSectionSummaryNote Items Action Menu
	 */
	@RequestMapping(value="/jailAdjustmentSectionSummaryNoteItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayJailAdjustmentSectionSummaryNoteItemsActionMenu(){
		return new ModelAndView(
				JAIL_ADJUSTMENT_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME);
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * Prepares a ModelAndView for editing a JailAdjustmentSectionSummary
	 * @param jailAdjustmentSectionSummary - JailAdjustmentSectionSummary to be
	 * edited
	 * @param form - JailAdjustmentSectionSummaryForm
	 * @return ModelAndView for editing a JailAdjustmentSectionSummary
	 */
	private ModelAndView prepareEditMav(JailAdjustmentSectionSummary
			jailAdjustmentSectionSummary, JailAdjustmentSectionSummaryForm form){
		ModelMap map = new ModelMap();
		
		map.addAttribute(JAIL_ADJUSTMENT_SECTION_SUMMARY_MODEL_KEY,
				jailAdjustmentSectionSummary);
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				jailAdjustmentSectionSummary.getPresentenceInvestigationRequest());
		map.addAttribute(FORM_MODEL_KEY, form);
		map.addAttribute(
				JAIL_ADJUSTMENT_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
				form.getJailAdjustmentSectionSummaryNoteItems().size());
		this.offenderSummaryModelDelegate.add(map,
				(Offender) jailAdjustmentSectionSummary
					.getPresentenceInvestigationRequest().getPerson());
		this.presentenceInvestigationRequestSummaryModelDelegate.add(map,
				jailAdjustmentSectionSummary.getPresentenceInvestigationRequest());
		
		
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Populates a JailAdjustmentSectionSummaryForm from a 
	 * JailAdjustmentSectionSummary 
	 * @param jailAdjustmentSectionSummary - JailAdjustmentSectionSummary to
	 * populate the form
	 * @return populated JailAdjustmentSectionSummaryForm
	 */
	private JailAdjustmentSectionSummaryForm prepareForm(
			JailAdjustmentSectionSummary jailAdjustmentSectionSummary){
		List<JailAdjustmentSectionSummaryNote> adjustmentSectionSummaryNotes =
				this.jailAdjustmentSectionSummaryService
			.findJailAdjustmentSectionSummaryNotesByJailAdjustmentSectionSummary(
					jailAdjustmentSectionSummary);
		
		List<JailAdjustmentSectionSummaryNoteItem> noteItems =
				new ArrayList<JailAdjustmentSectionSummaryNoteItem>();
		
		JailAdjustmentSectionSummaryForm form =
				new JailAdjustmentSectionSummaryForm();
		
		for(JailAdjustmentSectionSummaryNote note :
					adjustmentSectionSummaryNotes){
			JailAdjustmentSectionSummaryNoteItem item =
					new JailAdjustmentSectionSummaryNoteItem();
			item.setDate(note.getDate());
			item.setDescription(note.getDescription());
			item.setJailAdjustmentSectionSummaryNote(note);
			item.setItemOperation(
					PresentenceInvestigationItemOperation.UPDATE);
			
			noteItems.add(item);
		}
		
		form.setJailAdjustmentSectionSummaryNoteItems(noteItems);
		
		return form;
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
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(JailAdjustmentSectionSummary.class, 
				this.jailAdjustmentSectionSummaryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(JailAdjustmentSectionSummaryNote.class, 
				this.jailAdjustmentSectionSummaryNotePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(PresentenceInvestigationRequest.class, 
				this.presentenceInvestigationRequestPropertyEditorFactory.
					createPropertyEditor());
	}
}