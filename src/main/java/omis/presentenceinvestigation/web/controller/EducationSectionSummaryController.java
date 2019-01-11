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
import omis.education.domain.EducationAssociableDocument;
import omis.education.report.EducationDocumentSummary;
import omis.education.report.EducationSummary;
import omis.education.report.EducationSummaryReportService;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.presentenceinvestigation.domain.EducationSectionSummary;
import omis.presentenceinvestigation.domain.EducationSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.EducationSectionSummaryService;
import omis.presentenceinvestigation.web.controller.delegate.PresentenceInvestigationRequestSummaryModelDelegate;
import omis.presentenceinvestigation.web.form.EducationSectionSummaryForm;
import omis.presentenceinvestigation.web.form.EducationSectionSummaryNoteItem;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.validator.EducationSectionSummaryFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Education section summary controller.
 * 
 * @author Annie Wahl
 * @author Josh Divine 
 * @version 0.1.1 (Oct 24, 2018)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/presentenceInvestigation/educationSummary/")
@PreAuthorize("hasRole('USER')")
public class EducationSectionSummaryController {
	
	private static final String EDIT_VIEW_NAME =
			"/presentenceInvestigation/educationSummary/edit";
	
	private static final String
			EDUCATION_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME =
			"/presentenceInvestigation/educationSummary/includes/"
			+ "educationSectionSummaryNoteTableRow";
	
	private static final String REFRESHED_EDUCATIONS_LIST_VIEW_NAME =
			"/presentenceInvestigation/educationSummary/includes/"
			+ "listRefreshContent";
	
	private static final String
			EDUCATION_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME =
			"/presentenceInvestigation/educationSummary/includes/"
			+ "educationSectionSummaryActionMenu";
	
	private static final String
			EDUCATION_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME =
			"/presentenceInvestigation/educationSummary/includes/"
			+ "educationSectionSummaryNoteItemsActionMenu";
	
	private static final String EDUCATION_DOCUMENTS_ROW_ACTION_MENU_VIEW_NAME 
	= "/presentenceInvestigation/educationSummary/includes/educationDocumentsRowActionMenu";
	
	private static final String EDUCATIONS_ACTION_MENU_VIEW_NAME 
	= "/presentenceInvestigation/educationSummary/includes/educationsActionMenu";
	
	private static final String EDUCATION_SECTION_SUMMARY_MODEL_KEY =
			"educationSectionSummary";
	
	private static final String EDUCATION_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY
			= "educationSectionSummaryNoteItem";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY =
			"presentenceInvestigationRequest";
	
	private static final String SUMMARIES_MODEL_KEY = "summaries";
	
	private static final String DOCUMENT_SUMMARIES_MODEL_KEY =
			"educationDocumentSummaries";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String
			EDUCATION_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY =
					"educationSectionSummaryNoteItemIndex";
	
	private static final String EDUCATION_ASSOCIABLE_DOCUMENT_MODEL_KEY =
			"educationAssociableDocument";
	
	private static final String FORM_MODEL_KEY =
			"educationSectionSummaryForm";
	
	/* REDIRECT VIEW NAMES */
	
	private static final String HOME_REDIRECT =
			"redirect:/presentenceInvestigation/home.html?"
			+ "presentenceInvestigationRequest=%d";
	
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"educationSectionSummaryNote.exists";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.presentenceinvestigation.msgs.form";
	
	/* Services */
	
	@Autowired
	@Qualifier("educationSectionSummaryService")
	private EducationSectionSummaryService
				educationSectionSummaryService;
	
	@Autowired
	@Qualifier("educationSummaryReportService")
	private EducationSummaryReportService educationSummaryReportService;
	
	
	@Autowired
	@Qualifier("educationSectionSummaryPropertyEditorFactory")
	private PropertyEditorFactory educationSectionSummaryPropertyEditorFactory;

	@Autowired
	@Qualifier("educationSectionSummaryNotePropertyEditorFactory")
	private PropertyEditorFactory educationSectionSummaryNotePropertyEditorFactory;
	

	@Autowired
	@Qualifier("presentenceInvestigationRequestPropertyEditorFactory")
	private PropertyEditorFactory
		presentenceInvestigationRequestPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("educationAssociableDocumentPropertyEditorFactory")
	private PropertyEditorFactory educationAssociableDocumentPropertyEditorFactory;
	
	
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
	@Qualifier("educationSectionSummaryFormValidator")
	private EducationSectionSummaryFormValidator
			educationSectionSummaryFormValidator;
	
	/**
	 * 
	 */
	public EducationSectionSummaryController() {
	}
	
	/**
	 * Displays the ModelAndView for editing a EducationSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView for editing a EducationSectionSummary
	 * @throws DuplicateEntityFoundException - When a EducationSectionSummary
	 * already exists for the presentence investigation
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('EDUCATION_SECTION_SUMMARY_VIEW') or "
			+ "hasRole('EDUCATION_SECTION_SUMMARY_EDIT') or "
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
	 * Saves a EducationSectionSummary and Notes and returns to the
	 * PresentenceInvestigationRequest Home screen
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param form - EducationSectionSummaryForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - returns to PresentenceInvestigationRequest Home 
	 * on successful save, or back to EducationSectionSummary on form error
	 * @throws DuplicateEntityFoundException - When a EducationSectionSummaryNote
	 * already exists for the EducationSectionSummary with provided
	 * description and date
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('EDUCATION_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest,
			final EducationSectionSummaryForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.educationSectionSummaryFormValidator
				.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return this.prepareEditMav(presentenceInvestigationRequest, form);
		}
		else{
			EducationSectionSummary educationSectionSummary =
					this.educationSectionSummaryService
				.findEducationSectionSummaryByPresentenceInvestigationRequest(
							presentenceInvestigationRequest);
			
			if(educationSectionSummary == null){
				educationSectionSummary =
						this.educationSectionSummaryService
						.createEducationSectionSummary(
								presentenceInvestigationRequest, form.getText());
			}
			else{
				educationSectionSummary =
						this.educationSectionSummaryService
						.updateEducationSectionSummary(educationSectionSummary,
								form.getText());
			}
			
			for(EducationSectionSummaryNoteItem noteItem :
				form.getEducationSectionSummaryNoteItems()){
				if(PresentenceInvestigationItemOperation.CREATE.equals(
						noteItem.getItemOperation())){
					this.educationSectionSummaryService
						.createEducationSectionSummaryNote(
								noteItem.getDescription(), noteItem.getDate(),
								educationSectionSummary);
				}
				else if(PresentenceInvestigationItemOperation.UPDATE
						.equals(noteItem.getItemOperation())){
					this.educationSectionSummaryService
						.updateEducationSectionSummaryNote(
								noteItem.getEducationSectionSummaryNote(),
								noteItem.getDescription(), noteItem.getDate());
				}
				else if(PresentenceInvestigationItemOperation.REMOVE
						.equals(noteItem.getItemOperation())){
					this.educationSectionSummaryService
						.removeEducationSectionSummaryNote(
								noteItem.getEducationSectionSummaryNote());
				}
			}
			
			return new ModelAndView(String.format(HOME_REDIRECT,
					presentenceInvestigationRequest.getId()));
		}
	}
	
	
	
	
	
	
	/**
	 * Displays a EducationSectionSummaryNote item row
	 * @param educationSectionSummaryNoteItemIndex - Integer
	 * @return ModelAndView - EducationSectionSummaryNote item row
	 */
	@RequestMapping(value = "createEducationSectionSummaryNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayEducationSectionSummaryNoteItem(@RequestParam(
			value = "educationSectionSummaryNoteItemIndex", required = true)
				final Integer educationSectionSummaryNoteItemIndex){
		ModelMap map = new ModelMap();
		
		EducationSectionSummaryNoteItem noteItem =
				new EducationSectionSummaryNoteItem();
		
		noteItem.setItemOperation(
				PresentenceInvestigationItemOperation.CREATE);
		
		map.addAttribute(EDUCATION_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY,
				noteItem);
		map.addAttribute(
				EDUCATION_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
				educationSectionSummaryNoteItemIndex);
		
		
		return new ModelAndView(
				EDUCATION_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME, map);
	}
	
	/**
	 * Displays the education terms and documents list 
	 * @param offender - Offender
	 * @return ModelAndView - Education Terms and Documents list
	 */
	@RequestMapping(value = "refresh.html",
			method = RequestMethod.GET)
	public ModelAndView refreshEducationsList(@RequestParam(
			value = "offender", required = true)
				final Offender offender){
		ModelMap map = new ModelMap();
		
		List<EducationSummary> summaries = this.educationSummaryReportService
				.findByOffender(offender);
		List<EducationDocumentSummary> educationDocumentSummaries =
				this.educationSummaryReportService
				.findEducationDocumentSummariesByOffender(offender);
		
		map.addAttribute(SUMMARIES_MODEL_KEY, summaries);
		map.addAttribute(DOCUMENT_SUMMARIES_MODEL_KEY,
				educationDocumentSummaries);
		
		return new ModelAndView(REFRESHED_EDUCATIONS_LIST_VIEW_NAME, map);
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Displays the EducationSectionSummary Action Menu
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView - EducationSectionSummary Action Menu
	 */
	@RequestMapping(value="/educationSectionSummaryActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayEducationSectionSummaryActionMenu(
			@RequestParam(value="presentenceInvestigationRequest",
			required = true)
			final PresentenceInvestigationRequest
					presentenceInvestigationRequest){
		ModelMap map = new ModelMap();
		
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				presentenceInvestigationRequest);
		
		return new ModelAndView(
				EDUCATION_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the EducationSectionSummaryNote Items Action Menu
	 * @return ModelAndView - EducationSectionSummaryNote Items Action Menu
	 */
	@RequestMapping(value="/educationSectionSummaryNoteItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayEducationSectionSummaryNoteItemsActionMenu(){
		return new ModelAndView(
				EDUCATION_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME);
	}
	
	
	/**
	 * Returns model and view for education documents row action menu
	 * @param offender - offender
	 * @return ModelAndView
	 */
	@RequestMapping(value="/educationDocumentsRowActionMenu.html",
			method=RequestMethod.GET)
	public ModelAndView displayEducationDocumentsRowActionMenu(
			@RequestParam(value = "educationAssociableDocument", 
			required = true) final EducationAssociableDocument
				educationAssociableDocument){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(EDUCATION_ASSOCIABLE_DOCUMENT_MODEL_KEY, educationAssociableDocument);
		return new ModelAndView(
				EDUCATION_DOCUMENTS_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns model and view for educations action menu
	 * @param offender - offender
	 * @return ModelAndView
	 */
	@RequestMapping(value="/educationsActionMenu.html", 
			method=RequestMethod.GET)
	public ModelAndView displayEducationsActionMenu(@RequestParam(
			value = "offender", required = true) final Offender offender){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(EDUCATIONS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Prepares a ModelAndView for editing a EducationSectionSummary
	 * @param request - PresentenceInvestigationRequest associated with the
	 * EducationSectionSummary
	 * @param form - EducationSectionSummaryForm
	 * @return ModelAndView for editing a EducationSectionSummary
	 */
	private ModelAndView prepareEditMav(
			final PresentenceInvestigationRequest request,
			final EducationSectionSummaryForm form){
		ModelMap map = new ModelMap();
		EducationSectionSummary educationSectionSummary =
				this.educationSectionSummaryService
				.findEducationSectionSummaryByPresentenceInvestigationRequest(
						request);
		
		List<EducationSummary> summaries = this.educationSummaryReportService
				.findByOffender((Offender) request.getPerson());
		List<EducationDocumentSummary> educationDocumentSummaries =
				this.educationSummaryReportService
				.findEducationDocumentSummariesByOffender(
						(Offender)request.getPerson());
		
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY, request);
		map.addAttribute(OFFENDER_MODEL_KEY,
				(Offender) request.getPerson());
		map.addAttribute(SUMMARIES_MODEL_KEY, summaries);
		map.addAttribute(DOCUMENT_SUMMARIES_MODEL_KEY,
				educationDocumentSummaries);
		map.addAttribute(EDUCATION_SECTION_SUMMARY_MODEL_KEY,
				educationSectionSummary);
		map.addAttribute(FORM_MODEL_KEY, form);
		map.addAttribute(
				EDUCATION_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
				(form.getEducationSectionSummaryNoteItems() != null ?
						form.getEducationSectionSummaryNoteItems().size() : 0));
		this.offenderSummaryModelDelegate.add(map,
				(Offender) request.getPerson());
		this.presentenceInvestigationRequestSummaryModelDelegate.add(map,
				request);
		
		
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Populates a EducationSectionSummaryForm from a EducationSectionSummary 
	 * @param request - PresentenceInvestigationRequest to find the 
	 * EducationSectionSummary to populate the form
	 * @return populated EducationSectionSummaryForm
	 */
	private EducationSectionSummaryForm prepareForm(
			final PresentenceInvestigationRequest request){
		
		EducationSectionSummary educationSectionSummary =
				this.educationSectionSummaryService
				.findEducationSectionSummaryByPresentenceInvestigationRequest(
						request);
		EducationSectionSummaryForm form =
				new EducationSectionSummaryForm();
		
		if(educationSectionSummary != null){
			List<EducationSectionSummaryNote> adjustmentSectionSummaryNotes =
					this.educationSectionSummaryService
				.findNotesByEducationSectionSummary(
						educationSectionSummary);
			
			List<EducationSectionSummaryNoteItem> noteItems =
					new ArrayList<EducationSectionSummaryNoteItem>();
			
			
			
			form.setText(educationSectionSummary.getText());
			for(EducationSectionSummaryNote note :
						adjustmentSectionSummaryNotes){
				EducationSectionSummaryNoteItem item =
						new EducationSectionSummaryNoteItem();
				item.setDate(note.getDate());
				item.setDescription(note.getDescription());
				item.setEducationSectionSummaryNote(note);
				item.setItemOperation(
						PresentenceInvestigationItemOperation.UPDATE);
				
				noteItems.add(item);
			}
			
			form.setEducationSectionSummaryNoteItems(noteItems);
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
		binder.registerCustomEditor(EducationSectionSummary.class, 
				this.educationSectionSummaryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(EducationSectionSummaryNote.class, 
				this.educationSectionSummaryNotePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(PresentenceInvestigationRequest.class, 
				this.presentenceInvestigationRequestPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(EducationAssociableDocument.class, 
				this.educationAssociableDocumentPropertyEditorFactory
				.createPropertyEditor());
	}
}