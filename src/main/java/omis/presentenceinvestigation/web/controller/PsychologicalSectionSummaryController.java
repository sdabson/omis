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
import omis.presentenceinvestigation.domain.PsychologicalSectionSummary;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummaryDocument;
import omis.presentenceinvestigation.domain.PsychologicalSectionSummaryNote;
import omis.presentenceinvestigation.service.PsychologicalSectionSummaryService;
import omis.presentenceinvestigation.web.controller.delegate.PresentenceInvestigationRequestSummaryModelDelegate;
import omis.presentenceinvestigation.web.form.DocumentTagItem;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.form.PsychologicalSectionSummaryDocumentItem;
import omis.presentenceinvestigation.web.form.PsychologicalSectionSummaryForm;
import omis.presentenceinvestigation.web.form.PsychologicalSectionSummaryNoteItem;
import omis.presentenceinvestigation.web.validator.PsychologicalSectionSummaryFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * PsychologicalSectionSummaryController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Mar 2, 2017)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/presentenceInvestigation/psychologicalSummary/")
@PreAuthorize("hasRole('USER')")
public class PsychologicalSectionSummaryController {
	
	private static final String EDIT_VIEW_NAME =
			"/presentenceInvestigation/psychologicalSummary/edit";
	
	private static final String
			PSYCHOLOGICAL_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME =
				"/presentenceInvestigation/psychologicalSummary/includes/"
				+ "psychologicalSectionSummaryNoteTableRow";
	
	private static final String
			PSYCHOLOGICAL_SECTION_SUMMARY_DOCUMENT_ITEM_CONTENT_VIEW_NAME =
			"/presentenceInvestigation/psychologicalSummary/includes/"
			+ "psychologicalSectionSummaryDocumentItemContent";
	
	private static final String
			DOCUMENT_TAG_ITEM_CONTENT_VIEW_NAME =
			"/presentenceInvestigation/psychologicalSummary/includes/"
			+ "psychologicalSectionSummaryDocumentTagItemContent";
	
	private static final String
			PSYCHOLOGICAL_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME =
			"/presentenceInvestigation/psychologicalSummary/includes/"
			+ "psychologicalSectionSummaryActionMenu";
	
	private static final String
			PSYCHOLOGICAL_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME =
			"/presentenceInvestigation/psychologicalSummary/includes/"
			+ "psychologicalSectionSummaryNoteItemsActionMenu";
	
	private static final String
			PSYCHOLOGICAL_SECTION_SUMMARY_DOCUMENT_ITEMS_ACTION_MENU_VIEW_NAME =
			"/presentenceInvestigation/psychologicalSummary/includes/"
			+ "psychologicalSectionSummaryDocumentItemsActionMenu";
	
	
	
	
	private static final String PSYCHOLOGICAL_SECTION_SUMMARY_MODEL_KEY =
			"psychologicalSectionSummary";
	
	private static final String
			PSYCHOLOGICAL_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY =
			"psychologicalSectionSummaryNoteItem";
	
	private static final String
			PSYCHOLOGICAL_SECTION_SUMMARY_DOCUMENT_ITEM_MODEL_KEY =
			"psychologicalSectionSummaryDocumentItem";
	
	private static final String DOCUMENT_TAG_ITEM_MODEL_KEY = "documentTagItem";
	
	private static final String
			PSYCHOLOGICAL_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY =
			"psychologicalSectionSummaryNoteItemIndex";
	
	private static final String
			PSYCHOLOGICAL_SECTION_SUMMARY_DOCUMENT_ITEM_INDEX_MODEL_KEY =
			"psychologicalSectionSummaryDocumentItemIndex";
	
	private static final String DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY =
			"documentTagItemIndexes";
	
	private static final String DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY =
			"documentTagItemIndex";
	
	private static final String FORM_MODEL_KEY =
			"psychologicalSectionSummaryForm";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY =
			"presentenceInvestigationRequest";
	
	/* REDIRECT VIEW NAMES */
	
	private static final String HOME_REDIRECT =
			"redirect:/presentenceInvestigation/home.html?"
			+ "presentenceInvestigationRequest=%d";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"psychologicalSectionSummaryEntity.exists";
	
	private static final String ERROR_WRITING_FILE_MSG = 
			"Error writing file: %s";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.presentenceinvestigation.msgs.form";
	
	/* Service */
	
	@Autowired
	@Qualifier("psychologicalSectionSummaryService")
	private PsychologicalSectionSummaryService psychologicalSectionSummaryService;
	
	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("psychologicalSectionSummaryPropertyEditorFactory")
	private PropertyEditorFactory
				psychologicalSectionSummaryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("psychologicalSectionSummaryNotePropertyEditorFactory")
	private PropertyEditorFactory
				psychologicalSectionSummaryNotePropertyEditorFactory;
	
	@Autowired
	@Qualifier("psychologicalSectionSummaryDocumentPropertyEditorFactory")
	private PropertyEditorFactory
				psychologicalSectionSummaryDocumentPropertyEditorFactory;
	
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
	@Qualifier("psychologicalSectionSummaryFormValidator")
	private PsychologicalSectionSummaryFormValidator
			psychologicalSectionSummaryFormValidator;
	
	
	/**
	 * Default Constructor for PsychologicalSectionSummaryController
	 */
	public PsychologicalSectionSummaryController() {
	}
	
	
	/**
	 * Displays the ModelAndView for editing a PsychologicalSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView for editing a PsychologicalSectionSummary
	 * @throws DuplicateEntityFoundException - When a PsychologicalSectionSummary
	 * already exists for the presentence investigation
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PSYCHOLOGICAL_SECTION_SUMMARY_VIEW') or "
			+ "hasRole('PSYCHOLOGICAL_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest)
						throws DuplicateEntityFoundException{
		
		PsychologicalSectionSummary psychologicalSectionSummary =
				this.psychologicalSectionSummaryService
				.findPsychologicalSectionSummaryByPresentenceInvestigation(
						presentenceInvestigationRequest);
		
		if(psychologicalSectionSummary == null){
			psychologicalSectionSummary =
					this.psychologicalSectionSummaryService
					.createPsychologicalSectionSummary(
							presentenceInvestigationRequest);
		}
		
		return this.prepareEditMav(psychologicalSectionSummary,
				this.prepareForm(psychologicalSectionSummary));
	}
	
	/**
	 * Saves a PsychologicalSectionSummary, Notes, and Documents and returns to the
	 * PresentenceInvestigationRequest Home screen
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param form - PsychologicalSectionSummaryForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - returns to PresentenceInvestigationRequest Home 
	 * on successful save, or back to PsychologicalSectionSummary on form error
	 * @throws DuplicateEntityFoundException - When a PsychologicalSectionSummaryNote
	 * already exists for the PsychologicalSectionSummary with provided
	 * description and date, or a PsychologicalSectionSummaryDocument already exists
	 * with provided document and PsychologicalSectionSummary
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PSYCHOLOGICAL_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest,
			final PsychologicalSectionSummaryForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.psychologicalSectionSummaryFormValidator
				.validate(form, bindingResult);
		
		PsychologicalSectionSummary psychologicalSectionSummary =
				this.psychologicalSectionSummaryService
				.findPsychologicalSectionSummaryByPresentenceInvestigation(
						presentenceInvestigationRequest);
		
		if(bindingResult.hasErrors()){
			return this.prepareEditMav(psychologicalSectionSummary, form);
		}
		else{
			if(form.getPsychologicalSectionSummaryNoteItems() != null){
				for(PsychologicalSectionSummaryNoteItem item :
					form.getPsychologicalSectionSummaryNoteItems()){
					if(PresentenceInvestigationItemOperation.CREATE.equals(
							item.getItemOperation())){
						this.psychologicalSectionSummaryService
							.createPsychologicalSectionSummaryNote(
									item.getDescription(), item.getDate(),
									psychologicalSectionSummary);
					}
					else if(PresentenceInvestigationItemOperation.UPDATE
							.equals(item.getItemOperation())){
						this.psychologicalSectionSummaryService
							.updatePsychologicalSectionSummaryNote(
									item.getPsychologicalSectionSummaryNote(),
									item.getDescription(), item.getDate());
					}
					else if(PresentenceInvestigationItemOperation.REMOVE
							.equals(item.getItemOperation())){
						this.psychologicalSectionSummaryService
							.removePsychologicalSectionSummaryNote(
									item.getPsychologicalSectionSummaryNote());
					}
				}
			}
			
			if(form.getPsychologicalSectionSummaryDocumentItems() != null){
				for(PsychologicalSectionSummaryDocumentItem item :
					form.getPsychologicalSectionSummaryDocumentItems()){
					
					if(PresentenceInvestigationItemOperation.CREATE.equals(
							item.getItemOperation())){
						final String fileExtension = item.getFileExtension();
						this.documentFilenameGenerator
								.setExtension(fileExtension);
						final String filename =
								this.documentFilenameGenerator.generate();
						Document document = this.psychologicalSectionSummaryService
								.createDocument(item.getFileDate(),
										filename, item.getFileExtension(),
										item.getTitle());
						this.presentenceInvestigationDocumentPersister
								.persist(document, item.getData());
						
						this.psychologicalSectionSummaryService
							.createPsychologicalSectionSummaryDocument(
								document, psychologicalSectionSummary);
						
						if(item.getDocumentTagItems() != null){
							for(DocumentTagItem tagItem :
								item.getDocumentTagItems()){
								if(PresentenceInvestigationItemOperation
										.CREATE.equals(tagItem.getItemOperation())){
									this.psychologicalSectionSummaryService
										.createDocumentTag(document,
												tagItem.getName());
								}
							}
						}
					}
					if(PresentenceInvestigationItemOperation.UPDATE.equals(
							item.getItemOperation())){
						Document document = this.psychologicalSectionSummaryService
								.updateDocument(item.getDocument(),
										item.getFileDate(), item.getTitle());
						this.psychologicalSectionSummaryService
							.updatePsychologicalSectionSummaryDocument(
								item.getPsychologicalSectionSummaryDocument(),
								document);
						if(item.getDocumentTagItems() != null){
							for(DocumentTagItem tagItem :
								item.getDocumentTagItems()){
								if(PresentenceInvestigationItemOperation
										.CREATE.equals(tagItem.getItemOperation())){
									this.psychologicalSectionSummaryService
										.createDocumentTag(document,
												tagItem.getName());
								}
								if(PresentenceInvestigationItemOperation
										.UPDATE.equals(tagItem.getItemOperation())){
									this.psychologicalSectionSummaryService
										.updateDocumentTag(
											tagItem.getDocumentTag(),
											tagItem.getName());
								}
								if(PresentenceInvestigationItemOperation
										.REMOVE.equals(tagItem.getItemOperation())){
									this.psychologicalSectionSummaryService
										.removeDocumentTag(tagItem.getDocumentTag());
								}
							}
						}
					}
					if(PresentenceInvestigationItemOperation.REMOVE.equals(
							item.getItemOperation())){
						for(DocumentTag tag :
								this.psychologicalSectionSummaryService
								.findDocumentTagsByDocument(item.getDocument())){
							this.psychologicalSectionSummaryService
								.removeDocumentTag(tag);
						}
						this.psychologicalSectionSummaryService
							.removePsychologicalSectionSummaryDocument(
								item.getPsychologicalSectionSummaryDocument());
						this.presentenceInvestigationDocumentRemover.remove(
								item.getDocument().getFilename());
						this.psychologicalSectionSummaryService.removeDocument(
								item.getDocument());
					}
				}
			}
		}
		
		return new ModelAndView(String.format(HOME_REDIRECT,
				presentenceInvestigationRequest.getId()));
		
	}
	
	/**
	 * Displays a PsychologicalSectionSummaryNote item row
	 * @param psychologicalSectionSummaryNoteItemIndex - Integer
	 * @return ModelAndView - PsychologicalSectionSummaryNote item row
	 */
	@RequestMapping(value = "createPsychologicalSectionSummaryNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayPsychologicalSectionSummaryNoteItem(@RequestParam(
			value = "psychologicalSectionSummaryNoteItemIndex", required = true)
				final Integer psychologicalSectionSummaryNoteItemIndex){
		ModelMap map = new ModelMap();
		
		PsychologicalSectionSummaryNoteItem noteItem =
				new PsychologicalSectionSummaryNoteItem();
		
		noteItem.setItemOperation(
				PresentenceInvestigationItemOperation.CREATE);
		
		map.addAttribute(PSYCHOLOGICAL_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY,
				noteItem);
		map.addAttribute(
				PSYCHOLOGICAL_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
				psychologicalSectionSummaryNoteItemIndex);
		
		
		return new ModelAndView(
				PSYCHOLOGICAL_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME, map);
	}
	
	/**
	 * Displays a PsychologicalSectionSummaryDocument item
	 * @param psychologicalSectionSummaryDocumentItemIndex - Integer
	 * @return ModelAndView - PsychologicalSectionSummary item  content
	 */
	@RequestMapping(value = "createPsychologicalSectionSummaryDocumentItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayPsychologicalSectionSummaryDocumentItem(@RequestParam(
			value = "psychologicalSectionSummaryDocumentItemIndex", required = true)
				final Integer psychologicalSectionSummaryDocumentItemIndex){
		ModelMap map = new ModelMap();
		
		PsychologicalSectionSummaryDocumentItem noteItem =
				new PsychologicalSectionSummaryDocumentItem();
		
		noteItem.setItemOperation(
				PresentenceInvestigationItemOperation.CREATE);
		
		map.addAttribute(PSYCHOLOGICAL_SECTION_SUMMARY_DOCUMENT_ITEM_MODEL_KEY,
				noteItem);
		map.addAttribute(
				PSYCHOLOGICAL_SECTION_SUMMARY_DOCUMENT_ITEM_INDEX_MODEL_KEY,
				psychologicalSectionSummaryDocumentItemIndex);
		
		
		return new ModelAndView(
				PSYCHOLOGICAL_SECTION_SUMMARY_DOCUMENT_ITEM_CONTENT_VIEW_NAME, map);
	}
	
	/**
	 * Displays a DocumentTag item
	 * @param psychologicalDocumentItemIndex - Integer
	 * @param documentTagItemIndex - Integer
	 * @return ModelAndView - DocumentTag item content
	 */
	@RequestMapping(value = "createDocumentTagItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayDocumentTagItemRow(@RequestParam(
			value = "psychologicalSectionSummaryDocumentItemIndex", required = true)
				final Integer psychologicalDocumentItemIndex,
			@RequestParam(value = "documentTagItemIndex", required = true)
					final Integer documentTagItemIndex) {
		ModelMap map = new ModelMap();
		DocumentTagItem tagItem = new DocumentTagItem();
		tagItem.setItemOperation(PresentenceInvestigationItemOperation
				.CREATE);
		map.addAttribute(PSYCHOLOGICAL_SECTION_SUMMARY_DOCUMENT_ITEM_INDEX_MODEL_KEY,
				psychologicalDocumentItemIndex);
		map.addAttribute(DOCUMENT_TAG_ITEM_MODEL_KEY, tagItem);
		map.addAttribute(DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY,
				documentTagItemIndex);
		return new ModelAndView(DOCUMENT_TAG_ITEM_CONTENT_VIEW_NAME,
				map);
	}
	
	
	
	
	
	
	
	/**
	 * Displays the PsychologicalSectionSummary Action Menu
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView - PsychologicalSectionSummary Action Menu
	 */
	@RequestMapping(value="/psychologicalSectionSummaryActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayPsychologicalSectionSummaryActionMenu(
			@RequestParam(value="presentenceInvestigationRequest",
			required = true)
			final PresentenceInvestigationRequest
					presentenceInvestigationRequest){
		ModelMap map = new ModelMap();
		
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				presentenceInvestigationRequest);
		
		return new ModelAndView(
				PSYCHOLOGICAL_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the PsychologicalSectionSummaryNote Items Action Menu
	 * @return ModelAndView - PsychologicalSectionSummaryNote Items Action Menu
	 */
	@RequestMapping(value="/psychologicalSectionSummaryNoteItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayPsychologicalSectionSummaryNoteItemsActionMenu(){
		return new ModelAndView(
				PSYCHOLOGICAL_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME);
	}
	
	
	/**
	 * Displays the PsychologicalSectionSummaryDocument Items Action Menu
	 * @return ModelAndView - PsychologicalSectionSummaryDocument Items Action Menu
	 */
	@RequestMapping(value="/psychologicalSectionSummaryDocumentItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayPsychologicalSectionSummaryDocumentItemsActionMenu(){
		return new ModelAndView(
				PSYCHOLOGICAL_SECTION_SUMMARY_DOCUMENT_ITEMS_ACTION_MENU_VIEW_NAME);
	}
	
	
	
	
	
	
	
	/**
	 * Prepares a ModelAndView for editing a PsychologicalSectionSummary
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary to be
	 * edited
	 * @param form - PsychologicalSectionSummaryForm
	 * @return ModelAndView for editing a PsychologicalSectionSummary
	 */
	private ModelAndView prepareEditMav(
			final PsychologicalSectionSummary psychologicalSectionSummary,
			final PsychologicalSectionSummaryForm form){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(PSYCHOLOGICAL_SECTION_SUMMARY_MODEL_KEY,
				psychologicalSectionSummary);
		map.addAttribute(FORM_MODEL_KEY, form);
		map.addAttribute(PSYCHOLOGICAL_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
				(form.getPsychologicalSectionSummaryNoteItems() != null ?
						form.getPsychologicalSectionSummaryNoteItems().size()
						: 0));
		map.addAttribute(
				PSYCHOLOGICAL_SECTION_SUMMARY_DOCUMENT_ITEM_INDEX_MODEL_KEY,
				(form.getPsychologicalSectionSummaryDocumentItems() != null ?
						form.getPsychologicalSectionSummaryDocumentItems().size()
						: 0));
		if(form.getPsychologicalSectionSummaryDocumentItems() != null){
			List<Integer> tagIndexes = new ArrayList<Integer>();
			for(int i = 0; i < form.getPsychologicalSectionSummaryDocumentItems()
					.size(); i++){
				if(form.getPsychologicalSectionSummaryDocumentItems().get(i)
						!= null){
					tagIndexes.add(i,
							(form.getPsychologicalSectionSummaryDocumentItems()
								.get(i).getDocumentTagItems() != null ?
								form.getPsychologicalSectionSummaryDocumentItems()
							.get(i).getDocumentTagItems().size() : 0));
				}
				else{
					tagIndexes.add(i, 0);
				}
			}
			map.addAttribute(DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY, tagIndexes);
		}
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				psychologicalSectionSummary.getPresentenceInvestigationRequest());
		
		
		this.offenderSummaryModelDelegate.add(map, (Offender)
				psychologicalSectionSummary.getPresentenceInvestigationRequest()
				.getDocket().getPerson());;
		this.presentenceInvestigationRequestSummaryModelDelegate.add(map,
				psychologicalSectionSummary.getPresentenceInvestigationRequest());
				
				
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Populates a PsychologicalSectionSummaryForm from a 
	 * PsychologicalSectionSummary 
	 * @param psychologicalSectionSummary - PsychologicalSectionSummary to
	 * populate the form
	 * @return populated PsychologicalSectionSummaryForm
	 */
	private PsychologicalSectionSummaryForm prepareForm(
			final PsychologicalSectionSummary psychologicalSectionSummary){
		
		PsychologicalSectionSummaryForm form =
				new PsychologicalSectionSummaryForm();
		
		List<PsychologicalSectionSummaryNote> psychologicalSectionSummaryNotes =
			this.psychologicalSectionSummaryService
			.findPsychologicalSectionSummaryNotesByPsychologicalSectionSummary(
					psychologicalSectionSummary);
		List<PsychologicalSectionSummaryDocument>
			psychologicalSectionSummaryDocuments =
			this.psychologicalSectionSummaryService
			.findPsychologicalSectionSummaryDocumentsByPsychologicalSectionSummary(
					psychologicalSectionSummary);
			
		
		List<PsychologicalSectionSummaryNoteItem> noteItems =
				new ArrayList<PsychologicalSectionSummaryNoteItem>();
		List<PsychologicalSectionSummaryDocumentItem> documentItems =
				new ArrayList<PsychologicalSectionSummaryDocumentItem>();
		
		for(PsychologicalSectionSummaryNote note :
					psychologicalSectionSummaryNotes){
			PsychologicalSectionSummaryNoteItem item =
					new PsychologicalSectionSummaryNoteItem();
			item.setDate(note.getDate());
			item.setDescription(note.getDescription());
			item.setPsychologicalSectionSummaryNote(note);
			item.setItemOperation(PresentenceInvestigationItemOperation.UPDATE);
			
			noteItems.add(item);
		}
		for(PsychologicalSectionSummaryDocument psyDocument :
					psychologicalSectionSummaryDocuments){
			PsychologicalSectionSummaryDocumentItem item =
					new PsychologicalSectionSummaryDocumentItem();
			
			List<DocumentTagItem> tagItems = new ArrayList<DocumentTagItem>();
			List<DocumentTag> tags = this.psychologicalSectionSummaryService
					.findDocumentTagsByDocument(psyDocument.getDocument());
			for(DocumentTag tag : tags){
				DocumentTagItem tagItem = new DocumentTagItem();
				tagItem.setDocumentTag(tag);
				tagItem.setName(tag.getName());
				tagItem.setItemOperation(
						PresentenceInvestigationItemOperation.UPDATE);
				
				tagItems.add(tagItem);
			}
			
			item.setDocument(psyDocument.getDocument());
			item.setDocumentTagItems(tagItems);
			item.setFileDate(psyDocument.getDocument().getDate());
			item.setFileExtension(psyDocument.getDocument().getFileExtension());
			item.setFilename(psyDocument.getDocument().getFilename());
			item.setTitle(psyDocument.getDocument().getTitle());
			item.setPsychologicalSectionSummaryDocument(psyDocument);
			item.setItemOperation(PresentenceInvestigationItemOperation.UPDATE);
			item.setData(this.presentenceInvestigationDocumentRetriever
					.retrieve(item.getDocument()));
			
			documentItems.add(item);
		}
		
		form.setPsychologicalSectionSummaryDocumentItems(documentItems);
		form.setPsychologicalSectionSummaryNoteItems(noteItems);
		
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
		binder.registerCustomEditor(PsychologicalSectionSummary.class, 
				this.psychologicalSectionSummaryPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(PsychologicalSectionSummaryNote.class, 
				this.psychologicalSectionSummaryNotePropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(PsychologicalSectionSummaryDocument.class, 
				this.psychologicalSectionSummaryDocumentPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(Document.class, 
				this.documentPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(DocumentTag.class, 
				this.documentTagPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
	
}
