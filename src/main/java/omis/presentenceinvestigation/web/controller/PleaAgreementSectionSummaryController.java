package omis.presentenceinvestigation.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.io.DocumentPersister;
import omis.document.io.DocumentRetriever;
import omis.document.io.impl.DocumentFilenameGenerator;
import omis.exception.DuplicateEntityFoundException;
import omis.io.FileRemover;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummary;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummaryAssociableDocument;
import omis.presentenceinvestigation.domain.PleaAgreementSectionSummaryNote;
import omis.presentenceinvestigation.service.PleaAgreementSectionSummaryService;
import omis.presentenceinvestigation.web.controller.delegate.PresentenceInvestigationRequestSummaryModelDelegate;
import omis.presentenceinvestigation.web.form.DocumentTagItem;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.form.PleaAgreementSectionSummaryAssociableDocumentItem;
import omis.presentenceinvestigation.web.form.PleaAgreementSectionSummaryForm;
import omis.presentenceinvestigation.web.form.PleaAgreementSectionSummaryNoteItem;
import omis.presentenceinvestigation.web.validator.PleaAgreementSectionSummaryFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * PleaAgreementSectionSummaryController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Apr 13, 2017)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/presentenceInvestigation/pleaAgreementSummary/")
@PreAuthorize("hasRole('USER')")
public class PleaAgreementSectionSummaryController {
	
	/* View Names */
	
	private static final String EDIT_VIEW_NAME =
			"/presentenceInvestigation/pleaAgreementSummary/edit";
	
	private static final String
			PLEA_AGREEMENT_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME =
				"/presentenceInvestigation/pleaAgreementSummary/includes/"
				+ "pleaAgreementSectionSummaryNoteTableRow";
	
	private static final String
		PLEA_AGREEMENT_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_ITEM_CONTENT_VIEW_NAME =
			"/presentenceInvestigation/pleaAgreementSummary/includes/"
			+ "pleaAgreementSectionSummaryAssociableDocumentItemContent";
	
	private static final String
			DOCUMENT_TAG_ITEM_CONTENT_VIEW_NAME =
			"/presentenceInvestigation/pleaAgreementSummary/includes/"
			+ "pleaAgreementSectionSummaryAssociableDocumentTagItemContent";
	
	private static final String
			PLEA_AGREEMENT_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME =
			"/presentenceInvestigation/pleaAgreementSummary/includes/"
			+ "pleaAgreementSectionSummaryActionMenu";
	
	private static final String
			PLEA_AGREEMENT_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME =
			"/presentenceInvestigation/pleaAgreementSummary/includes/"
			+ "pleaAgreementSectionSummaryNoteItemsActionMenu";
	
	/* Model Keys */
	
	private static final String PLEA_AGREEMENT_SECTION_SUMMARY_MODEL_KEY =
			"pleaAgreementSectionSummary";
	
	private static final String
			PLEA_AGREEMENT_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY =
			"pleaAgreementSectionSummaryNoteItem";
	
	private static final String
			PLEA_AGREEMENT_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_ITEM_MODEL_KEY =
			"pleaAgreementSectionSummaryAssociableDocumentItem";
	
	private static final String DOCUMENT_TAG_ITEM_MODEL_KEY = "documentTagItem";
	
	private static final String
			PLEA_AGREEMENT_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY =
			"pleaAgreementSectionSummaryNoteItemIndex";
	
	private static final String
		PLEA_AGREEMENT_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_ITEM_INDEX_MODEL_KEY =
			"pleaAgreementSectionSummaryAssociableDocumentItemIndex";
	
	private static final String DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY =
			"documentTagItemIndexes";
	
	private static final String DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY =
			"documentTagItemIndex";
	
	private static final String FORM_MODEL_KEY =
			"pleaAgreementSectionSummaryForm";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY =
			"presentenceInvestigationRequest";
	
	/* REDIRECT VIEW NAMES */
	
	private static final String HOME_REDIRECT =
			"redirect:/presentenceInvestigation/home.html?"
			+ "presentenceInvestigationRequest=%d";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"pleaAgreementSectionSummaryEntity.exists";
	
	private static final String ERROR_WRITING_FILE_MSG = 
			"Error writing file: %s";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.presentenceinvestigation.msgs.form";
	
	/* Service */
	
	@Autowired
	@Qualifier("pleaAgreementSectionSummaryService")
	private PleaAgreementSectionSummaryService pleaAgreementSectionSummaryService;
	

	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("pleaAgreementSectionSummaryPropertyEditorFactory")
	private PropertyEditorFactory
				pleaAgreementSectionSummaryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("pleaAgreementSectionSummaryNotePropertyEditorFactory")
	private PropertyEditorFactory
				pleaAgreementSectionSummaryNotePropertyEditorFactory;
	
	@Autowired
	@Qualifier("pleaAgreementSectionSummaryAssociableDocumentPropertyEditorFactory")
	private PropertyEditorFactory
				pleaAgreementSectionSummaryAssociableDocumentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentPropertyEditorFactory")
	private PropertyEditorFactory documentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentTagPropertyEditorFactory")
	private PropertyEditorFactory documentTagPropertyEditorFactory;
	
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
	
	@Autowired
	private DocumentFilenameGenerator documentFilenameGenerator;
	
	@Autowired
	@Qualifier("presentenceInvestigationDocumentPersister")
	private DocumentPersister presentenceInvestigationDocumentPersister;
	
	@Autowired
	@Qualifier("presentenceInvestigationDocumentRetriever")
	private DocumentRetriever presentenceInvestigationDocumentRetriever;
	
	@Autowired
	@Qualifier("presentenceInvestigationDocumentRemover")
	private FileRemover presentenceInvestigationDocumentRemover;
	
	/* Validator */
	
	@Autowired
	@Qualifier("pleaAgreementSectionSummaryFormValidator")
	private PleaAgreementSectionSummaryFormValidator
		pleaAgreementSectionSummaryFormValidator;
	
	/**
	 * Default Constructor for PleaAgreementSectionSummaryController
	 */
	public PleaAgreementSectionSummaryController() {
	}
	
	
	/**
	 * Displays the ModelAndView for editing a PleaAgreementSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView for editing a PleaAgreementSectionSummary
	 * @throws DuplicateEntityFoundException - When a PleaAgreementSectionSummary
	 * already exists for the presentence investigation
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PLEA_AGREEMENT_SECTION_SUMMARY_VIEW') or "
			+ "hasRole('PLEA_AGREEMENT_SECTION_SUMMARY_EDIT') or "
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
	 * Saves a PleaAgreementSectionSummary, Notes, and Documents and returns to the
	 * PresentenceInvestigationRequest Home screen
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param form - PleaAgreementSectionSummaryForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - returns to PresentenceInvestigationRequest Home 
	 * on successful save, or back to PleaAgreementSectionSummary on form error
	 * @throws DuplicateEntityFoundException - When a PleaAgreementSectionSummaryNote
	 * already exists for the PleaAgreementSectionSummary with provided
	 * description and date, or a PleaAgreementSectionSummaryAssociableDocument
	 * already exists with provided document and PleaAgreementSectionSummary
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PLEA_AGREEMENT_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest,
			final PleaAgreementSectionSummaryForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.pleaAgreementSectionSummaryFormValidator
				.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return this.prepareEditMav(presentenceInvestigationRequest, form);
		}
		else{
			
			PleaAgreementSectionSummary pleaAgreementSectionSummary =
				this.pleaAgreementSectionSummaryService
				.findPleaAgreementSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
			
			if(pleaAgreementSectionSummary == null){
				pleaAgreementSectionSummary =
						this.pleaAgreementSectionSummaryService
						.createPleaAgreementSectionSummary(
								presentenceInvestigationRequest,
								form.getSummary());
			}
			else{
				pleaAgreementSectionSummary = 
						this.pleaAgreementSectionSummaryService
						.updatePleaAgreementSectionSummary(
								pleaAgreementSectionSummary, form.getSummary());
			}
			
			if(form.getPleaAgreementSectionSummaryNoteItems() != null){
				for(PleaAgreementSectionSummaryNoteItem item :
					form.getPleaAgreementSectionSummaryNoteItems()){
					if(PresentenceInvestigationItemOperation.CREATE.equals(
							item.getItemOperation())){
						this.pleaAgreementSectionSummaryService
							.createPleaAgreementSectionSummaryNote(
									pleaAgreementSectionSummary,
									item.getDescription(), item.getDate());
					}
					else if(PresentenceInvestigationItemOperation.UPDATE
							.equals(item.getItemOperation())){
						this.pleaAgreementSectionSummaryService
							.updatePleaAgreementSectionSummaryNote(
									item.getPleaAgreementSectionSummaryNote(),
									item.getDescription(), item.getDate());
					}
					else if(PresentenceInvestigationItemOperation.REMOVE
							.equals(item.getItemOperation())){
						this.pleaAgreementSectionSummaryService
							.removePleaAgreementSectionSummaryNote(
									item.getPleaAgreementSectionSummaryNote());
					}
				}
			}
			
			if(form.getPleaAgreementSectionSummaryAssociableDocumentItems()
					!= null){
				for(PleaAgreementSectionSummaryAssociableDocumentItem item :
					form.getPleaAgreementSectionSummaryAssociableDocumentItems()){
					
					if(PresentenceInvestigationItemOperation.CREATE.equals(
							item.getItemOperation())){
						final String fileExtension = item.getFileExtension();
						this.documentFilenameGenerator
								.setExtension(fileExtension);
						final String filename =
								this.documentFilenameGenerator.generate();
						Document document =
								this.pleaAgreementSectionSummaryService
								.createDocument(item.getFileDate(),
										filename, item.getFileExtension(),
										item.getTitle());
						this.presentenceInvestigationDocumentPersister
								.persist(document, item.getData());
						
						this.pleaAgreementSectionSummaryService
							.createPleaAgreementSectionSummaryAssociableDocument(
								pleaAgreementSectionSummary, document);
						
						if(item.getDocumentTagItems() != null){
							for(DocumentTagItem tagItem :
								item.getDocumentTagItems()){
								if(PresentenceInvestigationItemOperation
										.CREATE.equals(tagItem.getItemOperation())){
									this.pleaAgreementSectionSummaryService
										.createDocumentTag(document,
												tagItem.getName());
								}
							}
						}
					}
					if(PresentenceInvestigationItemOperation.UPDATE.equals(
							item.getItemOperation())){
						Document document = this.pleaAgreementSectionSummaryService
								.updateDocument(item.getDocument(),
										item.getFileDate(), item.getTitle());
						this.pleaAgreementSectionSummaryService
						.updatePleaAgreementSectionSummaryAssociableDocument(
							item.getPleaAgreementSectionSummaryAssociableDocument(),
							document);
						if(item.getDocumentTagItems() != null){
							for(DocumentTagItem tagItem :
								item.getDocumentTagItems()){
								if(PresentenceInvestigationItemOperation
										.CREATE.equals(tagItem.getItemOperation())){
									this.pleaAgreementSectionSummaryService
										.createDocumentTag(document,
												tagItem.getName());
								}
								if(PresentenceInvestigationItemOperation
										.UPDATE.equals(tagItem.getItemOperation())){
									this.pleaAgreementSectionSummaryService
										.updateDocumentTag(
											tagItem.getDocumentTag(),
											tagItem.getName());
								}
								if(PresentenceInvestigationItemOperation
										.REMOVE.equals(tagItem.getItemOperation())){
									this.pleaAgreementSectionSummaryService
										.removeDocumentTag(tagItem.getDocumentTag());
								}
							}
						}
					}
					if(PresentenceInvestigationItemOperation.REMOVE.equals(
							item.getItemOperation())){
						for(DocumentTag tag
								: this.pleaAgreementSectionSummaryService
								.findDocumentTagsByDocument(item.getDocument())){
							this.pleaAgreementSectionSummaryService
								.removeDocumentTag(tag);
						}
						this.pleaAgreementSectionSummaryService
							.removePleaAgreementSectionSummaryAssociableDocument(
							item.getPleaAgreementSectionSummaryAssociableDocument());
						this.presentenceInvestigationDocumentRemover.remove(
								item.getDocument().getFilename());
						this.pleaAgreementSectionSummaryService.removeDocument(
								item.getDocument());
					}
				}
			}
		}
		
		return new ModelAndView(String.format(HOME_REDIRECT,
				presentenceInvestigationRequest.getId()));
		
	}
	
	/**
	 * Displays a PleaAgreementSectionSummaryNote item row
	 * @param pleaAgreementSectionSummaryNoteItemIndex - Integer
	 * @return ModelAndView - PleaAgreementSectionSummaryNote item row
	 */
	@RequestMapping(value = "createPleaAgreementSectionSummaryNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayPleaAgreementSectionSummaryNoteItem(@RequestParam(
			value = "pleaAgreementSectionSummaryNoteItemIndex", required = true)
				final Integer pleaAgreementSectionSummaryNoteItemIndex){
		ModelMap map = new ModelMap();
		
		PleaAgreementSectionSummaryNoteItem noteItem =
				new PleaAgreementSectionSummaryNoteItem();
		
		noteItem.setItemOperation(
				PresentenceInvestigationItemOperation.CREATE);
		
		map.addAttribute(PLEA_AGREEMENT_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY,
				noteItem);
		map.addAttribute(
				PLEA_AGREEMENT_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
				pleaAgreementSectionSummaryNoteItemIndex);
		
		
		return new ModelAndView(
				PLEA_AGREEMENT_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME, map);
	}
	
	/**
	 * Displays a PleaAgreementSectionSummaryAssociableDocument item
	 * @param pleaAgreementSectionSummaryAssociableDocumentItemIndex - Integer
	 * @return ModelAndView - PleaAgreementSectionSummary item  content
	 */
	@RequestMapping(value =
			"createPleaAgreementSectionSummaryAssociableDocumentItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayPleaAgreementSectionSummaryAssociableDocumentItem(
			@RequestParam(
			value = "pleaAgreementSectionSummaryAssociableDocumentItemIndex",
			required = true)
			final Integer pleaAgreementSectionSummaryAssociableDocumentItemIndex){
		ModelMap map = new ModelMap();
		
		PleaAgreementSectionSummaryAssociableDocumentItem associableDocumentItem =
				new PleaAgreementSectionSummaryAssociableDocumentItem();
		
		associableDocumentItem.setItemOperation(
				PresentenceInvestigationItemOperation.CREATE);
		
		map.addAttribute(
				PLEA_AGREEMENT_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_ITEM_MODEL_KEY,
				associableDocumentItem);
		map.addAttribute(
			PLEA_AGREEMENT_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_ITEM_INDEX_MODEL_KEY,
			pleaAgreementSectionSummaryAssociableDocumentItemIndex);
		
		
		return new ModelAndView(
			PLEA_AGREEMENT_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_ITEM_CONTENT_VIEW_NAME,
			map);
	}
	
	/**
	 * Displays a DocumentTag item
	 * @param pleaAgreementDocumentItemIndex - Integer
	 * @param documentTagItemIndex - Integer
	 * @return ModelAndView - DocumentTag item content
	 */
	@RequestMapping(value = "createDocumentTagItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayDocumentTagItemRow(@RequestParam(
			value = "pleaAgreementSectionSummaryAssociableDocumentItemIndex",
				required = true) final Integer pleaAgreementDocumentItemIndex,
			@RequestParam(value = "documentTagItemIndex", required = true)
					final Integer documentTagItemIndex) {
		ModelMap map = new ModelMap();
		DocumentTagItem tagItem = new DocumentTagItem();
		tagItem.setItemOperation(PresentenceInvestigationItemOperation
				.CREATE);
		map.addAttribute(
			PLEA_AGREEMENT_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_ITEM_INDEX_MODEL_KEY,
			pleaAgreementDocumentItemIndex);
		map.addAttribute(DOCUMENT_TAG_ITEM_MODEL_KEY, tagItem);
		map.addAttribute(DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY,
				documentTagItemIndex);
		return new ModelAndView(DOCUMENT_TAG_ITEM_CONTENT_VIEW_NAME,
				map);
	}
	
	
	
	
	/* Action Menus */
	
	
	/**
	 * Displays the PleaAgreementSectionSummary Action Menu
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView - PleaAgreementSectionSummary Action Menu
	 */
	@RequestMapping(value="/pleaAgreementSectionSummaryActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayPleaAgreementSectionSummaryActionMenu(
			@RequestParam(value="presentenceInvestigationRequest",
			required = true)
			final PresentenceInvestigationRequest
					presentenceInvestigationRequest){
		ModelMap map = new ModelMap();
		
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				presentenceInvestigationRequest);
		
		return new ModelAndView(
				PLEA_AGREEMENT_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the PleaAgreementSectionSummaryNote Items Action Menu
	 * @return ModelAndView - PleaAgreementSectionSummaryNote Items Action Menu
	 */
	@RequestMapping(value="/pleaAgreementSectionSummaryNoteItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayPleaAgreementSectionSummaryNoteItemsActionMenu(){
		return new ModelAndView(
				PLEA_AGREEMENT_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME);
	}
	
	
	/* Helper Methods */
	
	
	/**
	 * Prepares a ModelAndView for editing a PleaAgreementSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * from which the associated PleaAgreementSummary will be found for editing
	 * @param form - PleaAgreementSectionSummaryForm
	 * @return ModelAndView for editing a PleaAgreementSectionSummary
	 */
	private ModelAndView prepareEditMav(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final PleaAgreementSectionSummaryForm form){
		
		ModelMap map = new ModelMap();
		
		PleaAgreementSectionSummary pleaAgreementSectionSummary =
				this.pleaAgreementSectionSummaryService
				.findPleaAgreementSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
		
		map.addAttribute(PLEA_AGREEMENT_SECTION_SUMMARY_MODEL_KEY,
				pleaAgreementSectionSummary);
		map.addAttribute(FORM_MODEL_KEY, form);
		map.addAttribute(
				PLEA_AGREEMENT_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
				(form.getPleaAgreementSectionSummaryNoteItems() != null ?
						form.getPleaAgreementSectionSummaryNoteItems().size()
						: 0));
		map.addAttribute(
			PLEA_AGREEMENT_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_ITEM_INDEX_MODEL_KEY,
			(form.getPleaAgreementSectionSummaryAssociableDocumentItems() != null ?
				form.getPleaAgreementSectionSummaryAssociableDocumentItems().size()
					: 0));
		if(form.getPleaAgreementSectionSummaryAssociableDocumentItems() != null){
			List<Integer> tagIndexes = new ArrayList<Integer>();
			for(int i = 0;
				i < form.getPleaAgreementSectionSummaryAssociableDocumentItems()
					.size(); i++){
				if(form.getPleaAgreementSectionSummaryAssociableDocumentItems()
						.get(i) != null){
					tagIndexes.add(i,
						(form.getPleaAgreementSectionSummaryAssociableDocumentItems()
						.get(i).getDocumentTagItems() != null ?
						form.getPleaAgreementSectionSummaryAssociableDocumentItems()
						.get(i).getDocumentTagItems().size() : 0));
				}
				else{
					tagIndexes.add(i, 0);
				}
			}
			map.addAttribute(DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY, tagIndexes);
		}
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				presentenceInvestigationRequest);
		
		
		this.offenderSummaryModelDelegate.add(map,
				(Offender) presentenceInvestigationRequest.getDocket().getPerson());
		this.presentenceInvestigationRequestSummaryModelDelegate.add(map,
				presentenceInvestigationRequest);
				
				
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Populates a PleaAgreementSectionSummaryForm from a 
	 * PleaAgreementSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * from which the associated PleaAgreementSummary will be found
	 * @return populated PleaAgreementSectionSummaryForm
	 */
	private PleaAgreementSectionSummaryForm prepareForm(
			final PresentenceInvestigationRequest presentenceInvestigationRequest){
		
		PleaAgreementSectionSummaryForm form =
				new PleaAgreementSectionSummaryForm();
		
		PleaAgreementSectionSummary pleaAgreementSectionSummary =
				this.pleaAgreementSectionSummaryService
				.findPleaAgreementSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
		
		if(pleaAgreementSectionSummary != null){
			form.setSummary(pleaAgreementSectionSummary.getSummary());
			
			List<PleaAgreementSectionSummaryNote> pleaAgreementSectionSummaryNotes =
				this.pleaAgreementSectionSummaryService
				.findPleaAgreementSectionSummaryNotesByPleaAgreementSectionSummary(
						pleaAgreementSectionSummary);
			List<PleaAgreementSectionSummaryAssociableDocument>
				pleaAgreementSectionSummaryAssociableDocuments =
				this.pleaAgreementSectionSummaryService
				.findPleaAgreementSectionSummaryAssociableDocumentsByPleaAgreementSectionSummary(
						pleaAgreementSectionSummary);
				
			
			List<PleaAgreementSectionSummaryNoteItem> noteItems =
				new ArrayList<PleaAgreementSectionSummaryNoteItem>();
			List<PleaAgreementSectionSummaryAssociableDocumentItem> documentItems =
				new ArrayList<PleaAgreementSectionSummaryAssociableDocumentItem>();
			
			for(PleaAgreementSectionSummaryNote note :
						pleaAgreementSectionSummaryNotes){
				PleaAgreementSectionSummaryNoteItem item =
						new PleaAgreementSectionSummaryNoteItem();
				item.setDate(note.getDate());
				item.setDescription(note.getDescription());
				item.setPleaAgreementSectionSummaryNote(note);
				item.setItemOperation(
						PresentenceInvestigationItemOperation.UPDATE);
				
				noteItems.add(item);
			}
			for(PleaAgreementSectionSummaryAssociableDocument pleaDocument :
						pleaAgreementSectionSummaryAssociableDocuments){
				PleaAgreementSectionSummaryAssociableDocumentItem item =
						new PleaAgreementSectionSummaryAssociableDocumentItem();
				
				List<DocumentTagItem> tagItems = new ArrayList<DocumentTagItem>();
				List<DocumentTag> tags = this.pleaAgreementSectionSummaryService
						.findDocumentTagsByDocument(pleaDocument.getDocument());
				for(DocumentTag tag : tags){
					DocumentTagItem tagItem = new DocumentTagItem();
					tagItem.setDocumentTag(tag);
					tagItem.setName(tag.getName());
					tagItem.setItemOperation(
							PresentenceInvestigationItemOperation.UPDATE);
					
					tagItems.add(tagItem);
				}
				
				item.setDocument(pleaDocument.getDocument());
				item.setDocumentTagItems(tagItems);
				item.setFileDate(pleaDocument.getDocument().getDate());
				item.setFileExtension(pleaDocument.getDocument()
						.getFileExtension());
				item.setFilename(pleaDocument.getDocument().getFilename());
				item.setTitle(pleaDocument.getDocument().getTitle());
				item.setPleaAgreementSectionSummaryAssociableDocument(
						pleaDocument);
				item.setItemOperation(
						PresentenceInvestigationItemOperation.UPDATE);
				item.setData(this.presentenceInvestigationDocumentRetriever
						.retrieve(item.getDocument()));
				
				documentItems.add(item);
			}
			
			form.setPleaAgreementSectionSummaryAssociableDocumentItems(
					documentItems);
			form.setPleaAgreementSectionSummaryNoteItems(noteItems);
		}
		
		return form;
	}
	
	
	
	/** Retrieves document file.
	 * @param document - document
	 * @param httpServletResponse - HTTP Servlet response. */
	@RequestMapping(value = "retrieveFile.html", method = RequestMethod.GET)
	@PreAuthorize("(hasRole('DOCUMENT_VIEW') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public void retrieveFile(
			@RequestParam(value = "document", required = true) 
			final Document document, 
			final HttpServletResponse httpServletResponse) {
		final byte[] bytes = this.presentenceInvestigationDocumentRetriever
				.retrieve(document);
		httpServletResponse.setContentType("application/octet-stream");
		httpServletResponse.setHeader("Content-Disposition", 
				"attachment; filename=\"" + document.getFilename() + "\"");
		try {
			OutputStream outputStream = httpServletResponse.getOutputStream();
			outputStream.write(bytes);
			outputStream.flush();
			outputStream.close();
		} catch (IOException ioException) {
			throw new RuntimeException(String.format(ERROR_WRITING_FILE_MSG, 
					document.getFilename()));
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
		binder.registerCustomEditor(PleaAgreementSectionSummary.class, 
				this.pleaAgreementSectionSummaryPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(PleaAgreementSectionSummaryNote.class, 
				this.pleaAgreementSectionSummaryNotePropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(PleaAgreementSectionSummaryAssociableDocument.class, 
				this.pleaAgreementSectionSummaryAssociableDocumentPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(Document.class, 
				this.documentPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(DocumentTag.class, 
				this.documentTagPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
	
}
