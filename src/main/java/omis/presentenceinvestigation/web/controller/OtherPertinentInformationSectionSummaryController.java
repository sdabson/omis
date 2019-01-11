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
import omis.presentenceinvestigation.domain.OtherPertinentInformationSectionSummary;
import omis.presentenceinvestigation.domain.OtherPertinentInformationSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.OtherPertinentInformationSectionSummaryService;
import omis.presentenceinvestigation.web.controller.delegate.PresentenceInvestigationRequestSummaryModelDelegate;
import omis.presentenceinvestigation.web.form.OtherPertinentInformationSectionSummaryForm;
import omis.presentenceinvestigation.web.form.OtherPertinentInformationSectionSummaryNoteItem;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.validator.OtherPertinentInformationSectionSummaryFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Other pertinent information section summary controller.
 * 
 * @author Annie Wahl
 * @author Josh Divine
 * @version 0.1.1 (Oct 24, 2018)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/presentenceInvestigation/otherPertinentInformationSummary/")
@PreAuthorize("hasRole('USER')")
public class OtherPertinentInformationSectionSummaryController {

	/* View Names */
	
	private static final String EDIT_VIEW_NAME =
			"/presentenceInvestigation/otherPertinentInformationSummary/edit";
	
	private static final String
		OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME =
			"/presentenceInvestigation/otherPertinentInformationSummary/includes/"
			+ "otherPertinentInformationSectionSummaryNoteTableRow";
	
	private static final String
		OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME =
			"/presentenceInvestigation/otherPertinentInformationSummary/includes/"
			+ "otherPertinentInformationSectionSummaryActionMenu";
	
	private static final String
	OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME =
			"/presentenceInvestigation/otherPertinentInformationSummary/includes/"
			+ "otherPertinentInformationSectionSummaryNoteItemsActionMenu";
	
	/* Model Keys */
	
	private static final String
		OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_MODEL_KEY =
			"otherPertinentInformationSectionSummary";
	
	private static final String
		OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY =
			"otherPertinentInformationSectionSummaryNoteItem";
	
	private static final String
		OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY =
			"otherPertinentInformationSectionSummaryNoteItemIndex";
	
	private static final String FORM_MODEL_KEY =
			"otherPertinentInformationSectionSummaryForm";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY =
			"presentenceInvestigationRequest";
	
	/* REDIRECT VIEW NAMES */
	
	private static final String HOME_REDIRECT =
			"redirect:/presentenceInvestigation/home.html?"
			+ "presentenceInvestigationRequest=%d";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"otherPertinentInformationSectionSummaryEntity.exists";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.presentenceinvestigation.msgs.form";
	
	/* Service */
	
	@Autowired
	@Qualifier("otherPertinentInformationSectionSummaryService")
	private OtherPertinentInformationSectionSummaryService
		otherPertinentInformationSectionSummaryService;

	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("otherPertinentInformationSectionSummaryPropertyEditorFactory")
	private PropertyEditorFactory
		otherPertinentInformationSectionSummaryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("otherPertinentInformationSectionSummaryNotePropertyEditorFactory")
	private PropertyEditorFactory
		otherPertinentInformationSectionSummaryNotePropertyEditorFactory;
	
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
	@Qualifier("otherPertinentInformationSectionSummaryFormValidator")
	private OtherPertinentInformationSectionSummaryFormValidator
		otherPertinentInformationSectionSummaryFormValidator;
	
	/**
	 * Default Constructor for OtherPertinentInformationSectionSummaryController
	 */
	public OtherPertinentInformationSectionSummaryController() {
	}
	
	/**
	 * Displays the ModelAndView for editing a
	 * OtherPertinentInformationSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView for editing a OtherPertinentInformationSectionSummary
	 * @throws DuplicateEntityFoundException - When a
	 * OtherPertinentInformationSectionSummary already exists for the
	 * presentence investigation
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_VIEW') or "
			+ "hasRole('OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest presentenceInvestigationRequest)
						throws DuplicateEntityFoundException{
		return this.prepareEditMav(presentenceInvestigationRequest,
				this.prepareForm(presentenceInvestigationRequest));
	}
	
	/**
	 * Saves a OtherPertinentInformationSectionSummary and Notes and returns to the
	 * PresentenceInvestigationRequest Home screen
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param form - OtherPertinentInformationSectionSummaryForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - returns to PresentenceInvestigationRequest Home 
	 * on successful save, or back to OtherPertinentInformationSectionSummary
	 * on form error
	 * @throws DuplicateEntityFoundException - When a
	 * OtherPertinentInformationSectionSummaryNote already exists for the
	 * OtherPertinentInformationSectionSummary with provided description and date
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest,
			final OtherPertinentInformationSectionSummaryForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.otherPertinentInformationSectionSummaryFormValidator
				.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return this.prepareEditMav(presentenceInvestigationRequest, form);
		}
		else{
			
			OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary =
				this.otherPertinentInformationSectionSummaryService
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
			
			if(otherPertinentInformationSectionSummary == null){
				otherPertinentInformationSectionSummary =
						this.otherPertinentInformationSectionSummaryService
						.createOtherPertinentInformationSectionSummary(
								presentenceInvestigationRequest,
								form.getDescription());
			}
			else{
				otherPertinentInformationSectionSummary = 
						this.otherPertinentInformationSectionSummaryService
						.updateOtherPertinentInformationSectionSummary(
								otherPertinentInformationSectionSummary,
								form.getDescription());
			}
			
			if(form.getOtherPertinentInformationSectionSummaryNoteItems() != null){
				for(OtherPertinentInformationSectionSummaryNoteItem item :
					form.getOtherPertinentInformationSectionSummaryNoteItems()){
					if(PresentenceInvestigationItemOperation.CREATE.equals(
							item.getItemOperation())){
						this.otherPertinentInformationSectionSummaryService
							.createOtherPertinentInformationSectionSummaryNote(
									otherPertinentInformationSectionSummary,
									item.getDescription(), item.getDate());
					}
					else if(PresentenceInvestigationItemOperation.UPDATE
							.equals(item.getItemOperation())){
						this.otherPertinentInformationSectionSummaryService
							.updateOtherPertinentInformationSectionSummaryNote(
								item.getOtherPertinentInformationSectionSummaryNote(),
								item.getDescription(), item.getDate());
					}
					else if(PresentenceInvestigationItemOperation.REMOVE
							.equals(item.getItemOperation())){
						this.otherPertinentInformationSectionSummaryService
							.removeOtherPertinentInformationSectionSummaryNote(
							item.getOtherPertinentInformationSectionSummaryNote());
					}
				}
			}
		}
		
		return new ModelAndView(String.format(HOME_REDIRECT,
				presentenceInvestigationRequest.getId()));
		
	}
	
	/**
	 * Displays a OtherPertinentInformationSectionSummaryNote item row
	 * @param otherPertinentInformationSectionSummaryNoteItemIndex - Integer
	 * @return ModelAndView - OtherPertinentInformationSectionSummaryNote item row
	 */
	@RequestMapping(value =
			"createOtherPertinentInformationSectionSummaryNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayOtherPertinentInformationSectionSummaryNoteItem(
			@RequestParam(
			value = "otherPertinentInformationSectionSummaryNoteItemIndex",
			required = true)
				final Integer
				otherPertinentInformationSectionSummaryNoteItemIndex){
		ModelMap map = new ModelMap();
		
		OtherPertinentInformationSectionSummaryNoteItem noteItem =
				new OtherPertinentInformationSectionSummaryNoteItem();
		
		noteItem.setItemOperation(
				PresentenceInvestigationItemOperation.CREATE);
		
		map.addAttribute(
				OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY,
				noteItem);
		map.addAttribute(
			OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
				otherPertinentInformationSectionSummaryNoteItemIndex);
		
		
		return new ModelAndView(
			OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME,
			map);
	}
	
	
	/* Action Menus */
	
	
	/**
	 * Displays the OtherPertinentInformationSectionSummary Action Menu
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView - OtherPertinentInformationSectionSummary Action Menu
	 */
	@RequestMapping(value="/otherPertinentInformationSectionSummaryActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayOtherPertinentInformationSectionSummaryActionMenu(
			@RequestParam(value="presentenceInvestigationRequest",
			required = true)
			final PresentenceInvestigationRequest
					presentenceInvestigationRequest){
		ModelMap map = new ModelMap();
		
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				presentenceInvestigationRequest);
		
		return new ModelAndView(
				OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME,
				map);
	}
	
	/**
	 * Displays the OtherPertinentInformationSectionSummaryNote Items Action Menu
	 * @return ModelAndView - OtherPertinentInformationSectionSummaryNote Items
	 * Action Menu
	 */
	@RequestMapping(value=
			"/otherPertinentInformationSectionSummaryNoteItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView
		displayOtherPertinentInformationSectionSummaryNoteItemsActionMenu(){
		return new ModelAndView(
		OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME);
	}
	
	
	/* Helper Methods */
	
	
	/**
	 * Prepares a ModelAndView for editing a OtherPertinentInformationSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * from which the associated OtherPertinentInformationSummary will be found
	 * for editing
	 * @param form - OtherPertinentInformationSectionSummaryForm
	 * @return ModelAndView for editing a OtherPertinentInformationSectionSummary
	 */
	private ModelAndView prepareEditMav(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final OtherPertinentInformationSectionSummaryForm form){
		
		ModelMap map = new ModelMap();
		
		OtherPertinentInformationSectionSummary
			otherPertinentInformationSectionSummary =
				this.otherPertinentInformationSectionSummaryService
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
		
		map.addAttribute(OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_MODEL_KEY,
				otherPertinentInformationSectionSummary);
		map.addAttribute(FORM_MODEL_KEY, form);
		map.addAttribute(
			OTHER_PERTINENT_INFORMATION_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
			form.getOtherPertinentInformationSectionSummaryNoteItems().size());
		
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				presentenceInvestigationRequest);
		
		this.offenderSummaryModelDelegate.add(map, (Offender)
				presentenceInvestigationRequest.getPerson());
		this.presentenceInvestigationRequestSummaryModelDelegate.add(map,
				presentenceInvestigationRequest);
				
				
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Populates a OtherPertinentInformationSectionSummaryForm from a 
	 * OtherPertinentInformationSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * from which the associated OtherPertinentInformationSummary will be found
	 * @return populated OtherPertinentInformationSectionSummaryForm
	 */
	private OtherPertinentInformationSectionSummaryForm prepareForm(
			final PresentenceInvestigationRequest presentenceInvestigationRequest){
		
		OtherPertinentInformationSectionSummaryForm form =
				new OtherPertinentInformationSectionSummaryForm();
		
		OtherPertinentInformationSectionSummary
			otherPertinentInformationSectionSummary =
				this.otherPertinentInformationSectionSummaryService
				.findByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
		
		if(otherPertinentInformationSectionSummary != null){
			form.setDescription(otherPertinentInformationSectionSummary
					.getDescription());
			
			List<OtherPertinentInformationSectionSummaryNote>
				otherPertinentInformationSectionSummaryNotes =
				this.otherPertinentInformationSectionSummaryService
				.findSectionSummaryNotesByOtherPertinentInformationSectionSummary(
						otherPertinentInformationSectionSummary);
			
			List<OtherPertinentInformationSectionSummaryNoteItem> noteItems =
				new ArrayList<OtherPertinentInformationSectionSummaryNoteItem>();
			
			for(OtherPertinentInformationSectionSummaryNote note :
						otherPertinentInformationSectionSummaryNotes){
				OtherPertinentInformationSectionSummaryNoteItem item =
						new OtherPertinentInformationSectionSummaryNoteItem();
				item.setDate(note.getDate());
				item.setDescription(note.getDescription());
				item.setOtherPertinentInformationSectionSummaryNote(note);
				item.setItemOperation(
						PresentenceInvestigationItemOperation.UPDATE);
				
				noteItems.add(item);
			}
			form.setOtherPertinentInformationSectionSummaryNoteItems(noteItems);
		}
		
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
		binder.registerCustomEditor(PresentenceInvestigationRequest.class, 
				this.presentenceInvestigationRequestPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(OtherPertinentInformationSectionSummary.class, 
				this.otherPertinentInformationSectionSummaryPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(OtherPertinentInformationSectionSummaryNote
				.class, 
			this.otherPertinentInformationSectionSummaryNotePropertyEditorFactory.
				createPropertyEditor());
	}
}