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
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;
import omis.presentenceinvestigation.domain.PresentenceInvestigationDocketAssociation;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.VictimSectionSummary;
import omis.presentenceinvestigation.domain.VictimSectionSummaryNote;
import omis.presentenceinvestigation.service.PresentenceInvestigationRequestService;
import omis.presentenceinvestigation.service.VictimSectionSummaryService;
import omis.presentenceinvestigation.web.controller.delegate.PresentenceInvestigationRequestSummaryModelDelegate;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.form.VictimDocketDocumentAssociationItemOperation;
import omis.presentenceinvestigation.web.form.VictimSectionSummaryDocketAssociationItem;
import omis.presentenceinvestigation.web.form.VictimSectionSummaryForm;
import omis.presentenceinvestigation.web.form.VictimSectionSummaryNoteItem;
import omis.presentenceinvestigation.web.validator.VictimSectionSummaryFormValidator;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.VictimDocketAssociation;
import omis.victim.domain.VictimDocumentAssociation;
import omis.victim.report.VictimAssociationReportService;
import omis.victim.report.VictimDocumentAssociationSummary;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * VictimSectionSummaryController.java
 * 
 *@author Annie Jacques
 *@author Trevor Isles
 *@version 0.1.1 (August 31, 2017)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/presentenceInvestigation/victimSummary/")
@PreAuthorize("hasRole('USER')")
public class VictimSectionSummaryController {
	
/* View Names */
	
	private static final String EDIT_VIEW_NAME
			= "/presentenceInvestigation/victimSummary/edit";
	
	private static final String
		VICTIM_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME
			= "/presentenceInvestigation/victimSummary/includes/"
			+ "victimSectionSummaryNoteTableRow";
	
	private static final String VICTIM_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME
			= "/presentenceInvestigation/victimSummary/includes/"
			+ "victimSectionSummaryActionMenu";
	
	private static final String
		VICTIM_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME
			= "/presentenceInvestigation/victimSummary/includes/"
			+ "victimSectionSummaryNoteItemsActionMenu";
	
	private static final String VICTIM_DOCUMENTS_ROW_ACTION_MENU_VIEW_NAME
			= "/presentenceInvestigation/victimSummary/includes/"
			+ "victimDocumentsRowActionMenu";
	
	private static final String VICTIMS_ACTION_MENU_VIEW_NAME
		= "/presentenceInvestigation/victimSummary/includes/victimsActionMenu";
	
	private static final String
		VICTIM_DOCUMENT_ASSOCIATION_SUMMARIES_VIEW_NAME 
			= "/presentenceInvestigation/victimSummary/includes/"
					+ "documentListTableBodyContent";
	
	/* Model Keys */
	
	private static final String VICTIM_SECTION_SUMMARY_MODEL_KEY =
			"victimSectionSummary";
	
	private static final String
		VICTIM_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY =
			"victimSectionSummaryNoteItem";
	
	private static final String
		VICTIM_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY =
			"victimSectionSummaryNoteItemIndex";
	
	private static final String VICTIM_DOCUMENT_ASSOCIATION_MODEL_KEY
		= "victimDocumentAssociation";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String FORM_MODEL_KEY = "victimSectionSummaryForm";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY =
			"presentenceInvestigationRequest";
	
	private static final String
		VICTIM_SECTION_SUMMARY_DOCKET_ASSOCIATION_ITEM_INDEX_MODEL_KEY 
			= "victimSectionSummaryDocketAssociationItemIndex";
	
	private static final String
		VICTIM_DOCUMENT_ASSOCIATION_SUMMARIES_MODEL_KEY 
			= "victimDocumentAssociationSummaries";
	
	private static final String PERSON_MODEL_KEY = "victim";
	
	/* REDIRECT VIEW NAMES */
	
	private static final String HOME_REDIRECT =
			"redirect:/presentenceInvestigation/home.html?"
			+ "presentenceInvestigationRequest=%d";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"victimSectionSummaryEntity.exists";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.presentenceinvestigation.msgs.form";
	
	/* Service */
	
	@Autowired
	@Qualifier("victimSectionSummaryService")
	private VictimSectionSummaryService victimSectionSummaryService;
	

	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("victimSectionSummaryPropertyEditorFactory")
	private PropertyEditorFactory victimSectionSummaryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("victimSectionSummaryNotePropertyEditorFactory")
	private PropertyEditorFactory
		victimSectionSummaryNotePropertyEditorFactory;
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestPropertyEditorFactory")
	PropertyEditorFactory presentenceInvestigationRequestPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditoryFactory; 
	
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
	
	@Autowired
	@Qualifier("victimAssociationReportService")
	private VictimAssociationReportService victimAssociationReportService; 
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestService")
	private PresentenceInvestigationRequestService 
			presentenceInvestigationRequestService;
	
	/* Validator */
	
	@Autowired
	@Qualifier("victimSectionSummaryFormValidator")
	private VictimSectionSummaryFormValidator
		victimSectionSummaryFormValidator;
	
	/**
	 * Default Constructor for VictimSectionSummaryController
	 */
	public VictimSectionSummaryController() {
	}
	
	/**
	 * Displays the ModelAndView for editing a VictimSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView for editing a VictimSectionSummary
	 * @throws DuplicateEntityFoundException - When a VictimSectionSummary
	 * already exists for the presentence investigation
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('VICTIM_SECTION_SUMMARY_VIEW') or "
			+ "hasRole('VICTIM_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest)
						throws DuplicateEntityFoundException{
		return this.prepareEditMav(presentenceInvestigationRequest,
				this.prepareForm(presentenceInvestigationRequest));
	}
	
	/**
	 * Saves a VictimSectionSummary, Notes, and Documents and returns to the
	 * PresentenceInvestigationRequest Home screen
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param form - VictimSectionSummaryForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - returns to PresentenceInvestigationRequest Home 
	 * on successful save, or back to VictimSectionSummary on form error
	 * @throws DuplicateEntityFoundException - When a VictimSectionSummaryNote
	 * already exists for the VictimSectionSummary with provided
	 * description and date, or a VictimSectionSummaryDocumentAssociation
	 * already exists with provided document and VictimSectionSummary
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('VICTIM_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest,
			final VictimSectionSummaryForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.victimSectionSummaryFormValidator
				.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return this.prepareEditMav(presentenceInvestigationRequest, form);
		}
		else{
			
			VictimSectionSummary victimSectionSummary =
				this.victimSectionSummaryService
				.findVictimSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
			
			if(victimSectionSummary == null){
				victimSectionSummary =
						this.victimSectionSummaryService
						.createVictimSectionSummary(
							presentenceInvestigationRequest, form.getText());
			}
			else{
				victimSectionSummary = 
						this.victimSectionSummaryService
						.updateVictimSectionSummary(
								victimSectionSummary, form.getText());
			}
			
			for(VictimSectionSummaryNoteItem item :
				form.getVictimSectionSummaryNoteItems()){
				if(PresentenceInvestigationItemOperation.CREATE.equals(
						item.getItemOperation())){
					this.victimSectionSummaryService
						.createVictimSectionSummaryNote(
								victimSectionSummary, item.getDescription(),
								item.getDate());
				}
				else if(PresentenceInvestigationItemOperation.UPDATE
						.equals(item.getItemOperation())){
					this.victimSectionSummaryService
						.updateVictimSectionSummaryNote(
								item.getVictimSectionSummaryNote(),
								item.getDescription(), item.getDate());
				}
				else if(PresentenceInvestigationItemOperation.REMOVE
						.equals(item.getItemOperation())){
					this.victimSectionSummaryService
						.removeVictimSectionSummaryNote(
								item.getVictimSectionSummaryNote());
				}
			}
			for(VictimSectionSummaryDocketAssociationItem associationItem : 
				form.getVictimSectionSummaryDocketAssociationItems()){
				if(VictimDocketDocumentAssociationItemOperation.INCLUDE.equals(
						associationItem.getItemOperation())){
					this.victimSectionSummaryService
						.createVictimDocketAssociation(associationItem
								.getVictimAssociation(), associationItem
								.getVictimDocketAssociation().getDocket());
				}
			}
		}
		
		return new ModelAndView(String.format(HOME_REDIRECT,
				presentenceInvestigationRequest.getId()));
		
	}
	
	/**
	 * Displays a VictimSectionSummaryNote item row
	 * @param victimSectionSummaryNoteItemIndex - Integer
	 * @return ModelAndView - VictimSectionSummaryNote item row
	 */
	@RequestMapping(value = "createVictimSectionSummaryNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayVictimSectionSummaryNoteItem(@RequestParam(
			value = "victimSectionSummaryNoteItemIndex", required = true)
				final Integer victimSectionSummaryNoteItemIndex){
		ModelMap map = new ModelMap();
		
		VictimSectionSummaryNoteItem noteItem =
				new VictimSectionSummaryNoteItem();
		
		noteItem.setItemOperation(
				PresentenceInvestigationItemOperation.CREATE);
		
		map.addAttribute(VICTIM_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY,
				noteItem);
		map.addAttribute(
				VICTIM_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
				victimSectionSummaryNoteItemIndex);
		
		
		return new ModelAndView(
				VICTIM_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME, map);
	}

	/* Action Menus */
	
	
	/**
	 * Displays the VictimSectionSummary Action Menu
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView - VictimSectionSummary Action Menu
	 */
	@RequestMapping(value="/victimSectionSummaryActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayVictimSectionSummaryActionMenu(
			@RequestParam(value="presentenceInvestigationRequest",
			required = true)
			final PresentenceInvestigationRequest
					presentenceInvestigationRequest){
		ModelMap map = new ModelMap();
		
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				presentenceInvestigationRequest);
		
		return new ModelAndView(
				VICTIM_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the VictimSectionSummaryNote Items Action Menu
	 * @return ModelAndView - VictimSectionSummaryNote Items Action Menu
	 */
	@RequestMapping(value="/victimSectionSummaryNoteItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayVictimSectionSummaryNoteItemsActionMenu(){
		return new ModelAndView(
				VICTIM_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME);
	}
	
	/**
	 * Returns model and view for victim documents row action menu
	 * @param offender - Offender
	 * @return ModelAndView
	 */
	@RequestMapping(value="/victimDocumentsRowActionMenu.html",
			method=RequestMethod.GET)
	public ModelAndView displayVictimDocumentsRowActionMenu(
			@RequestParam(value="victimDocumentAssociation", required = true)
				final VictimDocumentAssociation victimDocumentAssociation) {
		ModelMap map = new ModelMap();
		
		map.addAttribute(VICTIM_DOCUMENT_ASSOCIATION_MODEL_KEY, 
				victimDocumentAssociation);
		return new ModelAndView(
				VICTIM_DOCUMENTS_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns model and view for victims action menu.
	 * @param offender - Offender
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/victimsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayVictimsActionMenu(@RequestParam(
			value = "offender", required = true) final Offender offender) {
		ModelMap map = new ModelMap();
		
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(VICTIMS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "/refresh.html", method = RequestMethod.GET)
	public ModelAndView displayVictimDocuments(@RequestParam(value = "victim", 
		required = true) final Person victim) {
		ModelMap map = new ModelMap();
		
		List<VictimDocumentAssociationSummary> victimDocumentAssociations 
			= this.victimAssociationReportService
				.findDocumentAssociationSummariesByVictim(victim);
		
		map.addAttribute(PERSON_MODEL_KEY, victim);
		map.addAttribute(VICTIM_DOCUMENT_ASSOCIATION_SUMMARIES_MODEL_KEY, 
				victimDocumentAssociations);
		
		return new ModelAndView(
				VICTIM_DOCUMENT_ASSOCIATION_SUMMARIES_VIEW_NAME, map);
	}
	
	
	/* Helper Methods */
	
	
	/**
	 * Prepares a ModelAndView for editing a VictimSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * from which the associated VictimSummary will be found for editing
	 * @param form - VictimSectionSummaryForm
	 * @return ModelAndView for editing a VictimSectionSummary
	 */
	private ModelAndView prepareEditMav(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final VictimSectionSummaryForm form){
		
		ModelMap map = new ModelMap();
		
		VictimSectionSummary victimSectionSummary =
				this.victimSectionSummaryService
				.findVictimSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
		for(int i = 0;
				i < form.getVictimSectionSummaryDocketAssociationItems().size();
				i++){
			if(form.getVictimSectionSummaryDocketAssociationItems().get(i)
					!= null){
				form.getVictimSectionSummaryDocketAssociationItems().get(i)
					.setVictimAssociationSummary(
						this.victimAssociationReportService
						.summarizeVictimAssociation(
							form.getVictimSectionSummaryDocketAssociationItems()
							.get(i).getVictimAssociation()));
			}
		}
		
		
		map.addAttribute(VICTIM_SECTION_SUMMARY_MODEL_KEY,
				victimSectionSummary);
		map.addAttribute(OFFENDER_MODEL_KEY, (Offender)
				presentenceInvestigationRequest.getPerson());
		map.addAttribute(FORM_MODEL_KEY, form);
		map.addAttribute(
				VICTIM_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
				(form.getVictimSectionSummaryNoteItems().size()));
		map.addAttribute(
				VICTIM_SECTION_SUMMARY_DOCKET_ASSOCIATION_ITEM_INDEX_MODEL_KEY, 
				(form.getVictimSectionSummaryDocketAssociationItems().size()));

		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				presentenceInvestigationRequest);
		
		this.offenderSummaryModelDelegate.add(map, (Offender)
				presentenceInvestigationRequest.getPerson());
		this.presentenceInvestigationRequestSummaryModelDelegate.add(map,
				presentenceInvestigationRequest);
				
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Populates a VictimSectionSummaryForm from a 
	 * VictimSectionSummary
	 * @param request - PresentenceInvestigationRequest
	 * from which the associated VictimSummary will be found
	 * @return populated VictimSectionSummaryForm
	 */
	private VictimSectionSummaryForm prepareForm(
			final PresentenceInvestigationRequest request){
		
		VictimSectionSummaryForm form =
				new VictimSectionSummaryForm();
		VictimSectionSummary victimSectionSummary =
				this.victimSectionSummaryService
				.findVictimSectionSummaryByPresentenceInvestigationRequest(
						request);
		/* Create an item for each victim association for the specified
		 * offender in the request */
		List<VictimSectionSummaryDocketAssociationItem> docketAssociationItems =
			new ArrayList<VictimSectionSummaryDocketAssociationItem>();
		for (VictimAssociation assoc : this.victimSectionSummaryService
				.findVictimsByOffender((Offender) request.getPerson())) {
			
			VictimSectionSummaryDocketAssociationItem vdaItem 
				= new VictimSectionSummaryDocketAssociationItem();
			vdaItem.setPerson(assoc.getRelationship().getSecondPerson());
			vdaItem.setVictimAssociation(assoc);
			//Lookup VictimDocketAssociation for second person in assoc, populate
			//itemOperation depending on whether a VictimDocketAssociation exists or not
			List<PresentenceInvestigationDocketAssociation> dockets = 
					this.presentenceInvestigationRequestService
					.findPresentenceInvestigationDocketAssociationsByPresentenceInvestigationRequest(
							request);
			VictimDocketDocumentAssociationItemOperation itemOperation =
					VictimDocketDocumentAssociationItemOperation.EXCLUDE;
			for (PresentenceInvestigationDocketAssociation docket : dockets) {
				List<VictimDocketAssociation> docketAssociations =
						this.victimSectionSummaryService
						.findVictimAssociationByDocket(docket.getDocket());
				for(VictimDocketAssociation docketAssociation : docketAssociations){
					if(docketAssociation.getVictim().equals(assoc.getRelationship()
							.getSecondPerson())){
						itemOperation =
								VictimDocketDocumentAssociationItemOperation.EXISTS;
						vdaItem.setVictimDocketAssociation(docketAssociation);
						break;
					}
				}
				if (VictimDocketDocumentAssociationItemOperation.EXISTS.equals(
						itemOperation)) {
					break;
				}
			}
			vdaItem.setItemOperation(itemOperation);
			
			docketAssociationItems.add(vdaItem);
			
		}
		if(victimSectionSummary != null){
			form.setText(victimSectionSummary.getText());
			List<VictimSectionSummaryNote> victimSectionSummaryNotes =
				this.victimSectionSummaryService
				.findVictimSectionSummaryNotesByVictimSectionSummary(
						victimSectionSummary);
			List<VictimSectionSummaryNoteItem> noteItems =
				new ArrayList<VictimSectionSummaryNoteItem>();
			for(VictimSectionSummaryNote note : victimSectionSummaryNotes){
				VictimSectionSummaryNoteItem item =
						new VictimSectionSummaryNoteItem();
				item.setDate(note.getDate());
				item.setDescription(note.getDescription());
				item.setVictimSectionSummaryNote(note);
				item.setItemOperation(
						PresentenceInvestigationItemOperation.UPDATE);
				noteItems.add(item);
				/* Lookup victim docket associations for any of the victim
				 * associations for this offender*/
			}
			form.setVictimSectionSummaryNoteItems(noteItems);
		}
			
		form.setVictimSectionSummaryDocketAssociationItems(
				docketAssociationItems);
		
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
				this.presentenceInvestigationRequestPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(VictimSectionSummary.class, 
				this.victimSectionSummaryPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(VictimSectionSummaryNote.class, 
				this.victimSectionSummaryNotePropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(Person.class, 
				this.personPropertyEditoryFactory.createPropertyEditor());
		
	}
	
}
