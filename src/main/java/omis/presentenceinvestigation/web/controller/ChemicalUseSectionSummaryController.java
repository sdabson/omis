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
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummary;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummaryDocumentAssociation;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.ChemicalUseSectionSummaryService;
import omis.presentenceinvestigation.web.controller.delegate.PresentenceInvestigationRequestSummaryModelDelegate;
import omis.presentenceinvestigation.web.form.ChemicalUseSectionSummaryDocumentAssociationItem;
import omis.presentenceinvestigation.web.form.ChemicalUseSectionSummaryForm;
import omis.presentenceinvestigation.web.form.ChemicalUseSectionSummaryNoteItem;
import omis.presentenceinvestigation.web.form.DocumentTagItem;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.validator.ChemicalUseSectionSummaryFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * ChemicalUseSectionSummaryController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 6, 2017)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/presentenceInvestigation/chemicalUseSummary/")
@PreAuthorize("hasRole('USER')")
public class ChemicalUseSectionSummaryController {
	
	/* View Names */
	
	private static final String EDIT_VIEW_NAME =
			"/presentenceInvestigation/chemicalUseSummary/edit";
	
	private static final String
		CHEMICAL_USE_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME =
			"/presentenceInvestigation/chemicalUseSummary/includes/"
			+ "chemicalUseSectionSummaryNoteTableRow";
	
	private static final String
		CHEMICAL_USE_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_CONTENT_VIEW_NAME =
			"/presentenceInvestigation/chemicalUseSummary/includes/"
			+ "chemicalUseSectionSummaryDocumentAssociationItemContent";
	
	private static final String DOCUMENT_TAG_ITEM_CONTENT_VIEW_NAME =
			"/presentenceInvestigation/chemicalUseSummary/includes/"
			+ "chemicalUseSectionSummaryDocumentAssociationTagItemContent";
	
	private static final String CHEMICAL_USE_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME =
			"/presentenceInvestigation/chemicalUseSummary/includes/"
			+ "chemicalUseSectionSummaryActionMenu";
	
	private static final String
		CHEMICAL_USE_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME =
			"/presentenceInvestigation/chemicalUseSummary/includes/"
			+ "chemicalUseSectionSummaryNoteItemsActionMenu";
	
	/* Model Keys */
	
	private static final String CHEMICAL_USE_SECTION_SUMMARY_MODEL_KEY =
			"chemicalUseSectionSummary";
	
	private static final String
		CHEMICAL_USE_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY =
			"chemicalUseSectionSummaryNoteItem";
	
	private static final String
		CHEMICAL_USE_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_MODEL_KEY =
			"chemicalUseSectionSummaryDocumentAssociationItem";
	
	private static final String DOCUMENT_TAG_ITEM_MODEL_KEY = "documentTagItem";
	
	private static final String
		CHEMICAL_USE_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY =
			"chemicalUseSectionSummaryNoteItemIndex";
	
	private static final String
		CHEMICAL_USE_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_INDEX_MODEL_KEY =
			"chemicalUseSectionSummaryDocumentAssociationItemIndex";
	
	private static final String DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY =
			"documentTagItemIndexes";
	
	private static final String DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY =
			"documentTagItemIndex";
	
	private static final String FORM_MODEL_KEY = "chemicalUseSectionSummaryForm";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY =
			"presentenceInvestigationRequest";
	
	/* REDIRECT VIEW NAMES */
	
	private static final String HOME_REDIRECT =
			"redirect:/presentenceInvestigation/home.html?"
			+ "presentenceInvestigationRequest=%d";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"chemicalUseSectionSummaryEntity.exists";
	
	private static final String ERROR_WRITING_FILE_MSG = 
			"Error writing file: %s";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.presentenceinvestigation.msgs.form";
	
	/* Service */
	
	@Autowired
	@Qualifier("chemicalUseSectionSummaryService")
	private ChemicalUseSectionSummaryService chemicalUseSectionSummaryService;
	

	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("chemicalUseSectionSummaryPropertyEditorFactory")
	private PropertyEditorFactory chemicalUseSectionSummaryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("chemicalUseSectionSummaryNotePropertyEditorFactory")
	private PropertyEditorFactory
		chemicalUseSectionSummaryNotePropertyEditorFactory;
	
	@Autowired
	@Qualifier("chemicalUseSectionSummaryDocumentAssociationPropertyEditorFactory")
	private PropertyEditorFactory
		chemicalUseSectionSummaryDocumentAssociationPropertyEditorFactory;
	
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
	@Qualifier("chemicalUseSectionSummaryFormValidator")
	private ChemicalUseSectionSummaryFormValidator
		chemicalUseSectionSummaryFormValidator;
	
	/**
	 * Default Constructor for ChemicalUseSectionSummaryController
	 */
	public ChemicalUseSectionSummaryController() {
	}
	
	/**
	 * Displays the ModelAndView for editing a ChemicalUseSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView for editing a ChemicalUseSectionSummary
	 * @throws DuplicateEntityFoundException - When a ChemicalUseSectionSummary
	 * already exists for the presentence investigation
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CHEMICAL_USE_SECTION_SUMMARY_VIEW') or "
			+ "hasRole('CHEMICAL_USE_SECTION_SUMMARY_EDIT') or "
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
	 * Saves a ChemicalUseSectionSummary, Notes, and Documents and returns to the
	 * PresentenceInvestigationRequest Home screen
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param form - ChemicalUseSectionSummaryForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - returns to PresentenceInvestigationRequest Home 
	 * on successful save, or back to ChemicalUseSectionSummary on form error
	 * @throws DuplicateEntityFoundException - When a ChemicalUseSectionSummaryNote
	 * already exists for the ChemicalUseSectionSummary with provided
	 * description and date, or a ChemicalUseSectionSummaryDocumentAssociation
	 * already exists with provided document and ChemicalUseSectionSummary
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CHEMICAL_USE_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest,
			final ChemicalUseSectionSummaryForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.chemicalUseSectionSummaryFormValidator
				.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return this.prepareEditMav(presentenceInvestigationRequest, form);
		}
		else{
			
			ChemicalUseSectionSummary chemicalUseSectionSummary =
				this.chemicalUseSectionSummaryService
				.findChemicalUseSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
			
			if(chemicalUseSectionSummary == null){
				chemicalUseSectionSummary =
						this.chemicalUseSectionSummaryService
						.createChemicalUseSectionSummary(
								presentenceInvestigationRequest,
								form.getText());
			}
			else{
				chemicalUseSectionSummary = 
						this.chemicalUseSectionSummaryService
						.updateChemicalUseSectionSummary(
								chemicalUseSectionSummary, form.getText());
			}
			
			if(form.getChemicalUseSectionSummaryNoteItems() != null){
				for(ChemicalUseSectionSummaryNoteItem item :
					form.getChemicalUseSectionSummaryNoteItems()){
					if(PresentenceInvestigationItemOperation.CREATE.equals(
							item.getItemOperation())){
						this.chemicalUseSectionSummaryService
							.createChemicalUseSectionSummaryNote(
									item.getDescription(), item.getDate(),
									chemicalUseSectionSummary);
					}
					else if(PresentenceInvestigationItemOperation.UPDATE
							.equals(item.getItemOperation())){
						this.chemicalUseSectionSummaryService
							.updateChemicalUseSectionSummaryNote(
									item.getChemicalUseSectionSummaryNote(),
									item.getDescription(), item.getDate());
					}
					else if(PresentenceInvestigationItemOperation.REMOVE
							.equals(item.getItemOperation())){
						this.chemicalUseSectionSummaryService
							.removeChemicalUseSectionSummaryNote(
									item.getChemicalUseSectionSummaryNote());
					}
				}
			}
			
			if(form.getChemicalUseSectionSummaryDocumentAssociationItems()
					!= null){
				for(ChemicalUseSectionSummaryDocumentAssociationItem item :
					form.getChemicalUseSectionSummaryDocumentAssociationItems()){
					
					if(PresentenceInvestigationItemOperation.CREATE.equals(
							item.getItemOperation())){
						final String fileExtension = item.getFileExtension();
						this.documentFilenameGenerator
								.setExtension(fileExtension);
						final String filename =
								this.documentFilenameGenerator.generate();
						Document document =
								this.chemicalUseSectionSummaryService
								.createDocument(item.getFileDate(),
										filename, item.getFileExtension(),
										item.getTitle());
						this.presentenceInvestigationDocumentPersister
								.persist(document, item.getData());
						
						this.chemicalUseSectionSummaryService
							.createChemicalUseSectionSummaryDocumentAssociation(
								document, chemicalUseSectionSummary);
						
						if(item.getDocumentTagItems() != null){
							for(DocumentTagItem tagItem :
								item.getDocumentTagItems()){
								if(PresentenceInvestigationItemOperation
									.CREATE.equals(tagItem.getItemOperation())){
									this.chemicalUseSectionSummaryService
										.createDocumentTag(document,
												tagItem.getName());
								}
							}
						}
					}
					if(PresentenceInvestigationItemOperation.UPDATE.equals(
							item.getItemOperation())){
						Document document = this.chemicalUseSectionSummaryService
								.updateDocument(item.getDocument(),
										item.getFileDate(), item.getTitle());
						this.chemicalUseSectionSummaryService
						.updateChemicalUseSectionSummaryDocumentAssociation(
							item.getChemicalUseSectionSummaryDocumentAssociation(),
							document);
						if(item.getDocumentTagItems() != null){
							for(DocumentTagItem tagItem :
								item.getDocumentTagItems()){
								if(PresentenceInvestigationItemOperation
									.CREATE.equals(tagItem.getItemOperation())){
									this.chemicalUseSectionSummaryService
										.createDocumentTag(document,
												tagItem.getName());
								}
								if(PresentenceInvestigationItemOperation
									.UPDATE.equals(tagItem.getItemOperation())){
									this.chemicalUseSectionSummaryService
										.updateDocumentTag(
											tagItem.getDocumentTag(),
											tagItem.getName());
								}
								if(PresentenceInvestigationItemOperation
									.REMOVE.equals(tagItem.getItemOperation())){
									this.chemicalUseSectionSummaryService
									.removeDocumentTag(tagItem.getDocumentTag());
								}
							}
						}
					}
					if(PresentenceInvestigationItemOperation.REMOVE.equals(
							item.getItemOperation())){
						for(DocumentTag tag
								: this.chemicalUseSectionSummaryService
								.findDocumentTagsByDocument(item.getDocument())){
							this.chemicalUseSectionSummaryService
								.removeDocumentTag(tag);
						}
						this.chemicalUseSectionSummaryService
							.removeChemicalUseSectionSummaryDocumentAssociation(
							item.getChemicalUseSectionSummaryDocumentAssociation());
						this.presentenceInvestigationDocumentRemover.remove(
								item.getDocument().getFilename());
						this.chemicalUseSectionSummaryService.removeDocument(
								item.getDocument());
					}
				}
			}
		}
		
		return new ModelAndView(String.format(HOME_REDIRECT,
				presentenceInvestigationRequest.getId()));
		
	}
	
	/**
	 * Displays a ChemicalUseSectionSummaryNote item row
	 * @param chemicalUseSectionSummaryNoteItemIndex - Integer
	 * @return ModelAndView - ChemicalUseSectionSummaryNote item row
	 */
	@RequestMapping(value = "createChemicalUseSectionSummaryNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayChemicalUseSectionSummaryNoteItem(@RequestParam(
			value = "chemicalUseSectionSummaryNoteItemIndex", required = true)
				final Integer chemicalUseSectionSummaryNoteItemIndex){
		ModelMap map = new ModelMap();
		
		ChemicalUseSectionSummaryNoteItem noteItem =
				new ChemicalUseSectionSummaryNoteItem();
		
		noteItem.setItemOperation(
				PresentenceInvestigationItemOperation.CREATE);
		
		map.addAttribute(CHEMICAL_USE_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY,
				noteItem);
		map.addAttribute(
				CHEMICAL_USE_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
				chemicalUseSectionSummaryNoteItemIndex);
		
		
		return new ModelAndView(
				CHEMICAL_USE_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME, map);
	}
	
	/**
	 * Displays a ChemicalUseSectionSummaryDocumentAssociation item
	 * @param chemicalUseSectionSummaryDocumentAssociationItemIndex - Integer
	 * @return ModelAndView - ChemicalUseSectionSummary item  content
	 */
	@RequestMapping(value =
			"createChemicalUseSectionSummaryDocumentAssociationItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayChemicalUseSectionSummaryDocumentAssociationItem(
			@RequestParam(
			value = "chemicalUseSectionSummaryDocumentAssociationItemIndex",
			required = true)
			final Integer chemicalUseSectionSummaryDocumentAssociationItemIndex){
		ModelMap map = new ModelMap();
		
		ChemicalUseSectionSummaryDocumentAssociationItem associableDocumentItem =
				new ChemicalUseSectionSummaryDocumentAssociationItem();
		
		associableDocumentItem.setItemOperation(
				PresentenceInvestigationItemOperation.CREATE);
		
		map.addAttribute(
				CHEMICAL_USE_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_MODEL_KEY,
				associableDocumentItem);
		map.addAttribute(
			CHEMICAL_USE_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_INDEX_MODEL_KEY,
			chemicalUseSectionSummaryDocumentAssociationItemIndex);
		
		
		return new ModelAndView(
			CHEMICAL_USE_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_CONTENT_VIEW_NAME,
			map);
	}
	
	/**
	 * Displays a DocumentTag item
	 * @param chemicalUseDocumentItemIndex - Integer
	 * @param documentTagItemIndex - Integer
	 * @return ModelAndView - DocumentTag item content
	 */
	@RequestMapping(value = "createDocumentTagItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayDocumentTagItemRow(@RequestParam(
			value = "chemicalUseSectionSummaryDocumentAssociationItemIndex",
				required = true) final Integer chemicalUseDocumentItemIndex,
			@RequestParam(value = "documentTagItemIndex", required = true)
					final Integer documentTagItemIndex) {
		ModelMap map = new ModelMap();
		DocumentTagItem tagItem = new DocumentTagItem();
		tagItem.setItemOperation(PresentenceInvestigationItemOperation
				.CREATE);
		map.addAttribute(
			CHEMICAL_USE_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_INDEX_MODEL_KEY,
			chemicalUseDocumentItemIndex);
		map.addAttribute(DOCUMENT_TAG_ITEM_MODEL_KEY, tagItem);
		map.addAttribute(DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY,
				documentTagItemIndex);
		return new ModelAndView(DOCUMENT_TAG_ITEM_CONTENT_VIEW_NAME,
				map);
	}
	
	
	
	
	/* Action Menus */
	
	
	/**
	 * Displays the ChemicalUseSectionSummary Action Menu
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView - ChemicalUseSectionSummary Action Menu
	 */
	@RequestMapping(value="/chemicalUseSectionSummaryActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayChemicalUseSectionSummaryActionMenu(
			@RequestParam(value="presentenceInvestigationRequest",
			required = true)
			final PresentenceInvestigationRequest
					presentenceInvestigationRequest){
		ModelMap map = new ModelMap();
		
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				presentenceInvestigationRequest);
		
		return new ModelAndView(
				CHEMICAL_USE_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the ChemicalUseSectionSummaryNote Items Action Menu
	 * @return ModelAndView - ChemicalUseSectionSummaryNote Items Action Menu
	 */
	@RequestMapping(value="/chemicalUseSectionSummaryNoteItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayChemicalUseSectionSummaryNoteItemsActionMenu(){
		return new ModelAndView(
				CHEMICAL_USE_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME);
	}
	
	
	/* Helper Methods */
	
	
	/**
	 * Prepares a ModelAndView for editing a ChemicalUseSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * from which the associated ChemicalUseSummary will be found for editing
	 * @param form - ChemicalUseSectionSummaryForm
	 * @return ModelAndView for editing a ChemicalUseSectionSummary
	 */
	private ModelAndView prepareEditMav(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final ChemicalUseSectionSummaryForm form){
		
		ModelMap map = new ModelMap();
		
		ChemicalUseSectionSummary chemicalUseSectionSummary =
				this.chemicalUseSectionSummaryService
				.findChemicalUseSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
		
		map.addAttribute(CHEMICAL_USE_SECTION_SUMMARY_MODEL_KEY,
				chemicalUseSectionSummary);
		map.addAttribute(FORM_MODEL_KEY, form);
		map.addAttribute(
				CHEMICAL_USE_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
				(form.getChemicalUseSectionSummaryNoteItems() != null ?
						form.getChemicalUseSectionSummaryNoteItems().size()
						: 0));
		map.addAttribute(
			CHEMICAL_USE_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_INDEX_MODEL_KEY,
			(form.getChemicalUseSectionSummaryDocumentAssociationItems() != null ?
				form.getChemicalUseSectionSummaryDocumentAssociationItems().size()
					: 0));
		if(form.getChemicalUseSectionSummaryDocumentAssociationItems() != null){
			List<Integer> tagIndexes = new ArrayList<Integer>();
			for(int i = 0;
				i < form.getChemicalUseSectionSummaryDocumentAssociationItems()
					.size(); i++){
				if(form.getChemicalUseSectionSummaryDocumentAssociationItems()
						.get(i) != null){
					tagIndexes.add(i,
						(form.getChemicalUseSectionSummaryDocumentAssociationItems()
						.get(i).getDocumentTagItems() != null ?
						form.getChemicalUseSectionSummaryDocumentAssociationItems()
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
		
		
		this.offenderSummaryModelDelegate.add(map, (Offender)
				presentenceInvestigationRequest.getDocket().getPerson());
		this.presentenceInvestigationRequestSummaryModelDelegate.add(map,
				presentenceInvestigationRequest);
				
				
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Populates a ChemicalUseSectionSummaryForm from a 
	 * ChemicalUseSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * from which the associated ChemicalUseSummary will be found
	 * @return populated ChemicalUseSectionSummaryForm
	 */
	private ChemicalUseSectionSummaryForm prepareForm(
			final PresentenceInvestigationRequest presentenceInvestigationRequest){
		
		ChemicalUseSectionSummaryForm form =
				new ChemicalUseSectionSummaryForm();
		
		ChemicalUseSectionSummary chemicalUseSectionSummary =
				this.chemicalUseSectionSummaryService
				.findChemicalUseSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
		
		if(chemicalUseSectionSummary != null){
			form.setText(chemicalUseSectionSummary.getText());
			
			List<ChemicalUseSectionSummaryNote> chemicalUseSectionSummaryNotes =
				this.chemicalUseSectionSummaryService
				.findChemicalUseSectionSummaryNotesByChemicalUseSectionSummary(
						chemicalUseSectionSummary);
			List<ChemicalUseSectionSummaryDocumentAssociation>
				chemicalUseSectionSummaryDocumentAssociations =
				this.chemicalUseSectionSummaryService
	.findChemicalUseSectionSummaryDocumentAssociationsByChemicalUseSectionSummary(
						chemicalUseSectionSummary);
				
			
			List<ChemicalUseSectionSummaryNoteItem> noteItems =
				new ArrayList<ChemicalUseSectionSummaryNoteItem>();
			List<ChemicalUseSectionSummaryDocumentAssociationItem> documentItems =
				new ArrayList<ChemicalUseSectionSummaryDocumentAssociationItem>();
			
			for(ChemicalUseSectionSummaryNote note :
						chemicalUseSectionSummaryNotes){
				ChemicalUseSectionSummaryNoteItem item =
						new ChemicalUseSectionSummaryNoteItem();
				item.setDate(note.getDate());
				item.setDescription(note.getDescription());
				item.setChemicalUseSectionSummaryNote(note);
				item.setItemOperation(
						PresentenceInvestigationItemOperation.UPDATE);
				
				noteItems.add(item);
			}
			for(ChemicalUseSectionSummaryDocumentAssociation chemDocument :
						chemicalUseSectionSummaryDocumentAssociations){
				ChemicalUseSectionSummaryDocumentAssociationItem item =
						new ChemicalUseSectionSummaryDocumentAssociationItem();
				
				List<DocumentTagItem> tagItems = new ArrayList<DocumentTagItem>();
				List<DocumentTag> tags = this.chemicalUseSectionSummaryService
						.findDocumentTagsByDocument(chemDocument.getDocument());
				for(DocumentTag tag : tags){
					DocumentTagItem tagItem = new DocumentTagItem();
					tagItem.setDocumentTag(tag);
					tagItem.setName(tag.getName());
					tagItem.setItemOperation(
							PresentenceInvestigationItemOperation.UPDATE);
					
					tagItems.add(tagItem);
				}
				
				item.setDocument(chemDocument.getDocument());
				item.setDocumentTagItems(tagItems);
				item.setFileDate(chemDocument.getDocument().getDate());
				item.setFileExtension(chemDocument.getDocument()
						.getFileExtension());
				item.setFilename(chemDocument.getDocument().getFilename());
				item.setTitle(chemDocument.getDocument().getTitle());
				item.setChemicalUseSectionSummaryDocumentAssociation(
						chemDocument);
				item.setItemOperation(
						PresentenceInvestigationItemOperation.UPDATE);
				item.setData(this.presentenceInvestigationDocumentRetriever
						.retrieve(item.getDocument()));
				
				documentItems.add(item);
			}
			
			form.setChemicalUseSectionSummaryDocumentAssociationItems(
					documentItems);
			form.setChemicalUseSectionSummaryNoteItems(noteItems);
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
		binder.registerCustomEditor(ChemicalUseSectionSummary.class, 
				this.chemicalUseSectionSummaryPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(ChemicalUseSectionSummaryNote.class, 
				this.chemicalUseSectionSummaryNotePropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(ChemicalUseSectionSummaryDocumentAssociation
			.class, 
			this.chemicalUseSectionSummaryDocumentAssociationPropertyEditorFactory.
				createPropertyEditor());
		binder.registerCustomEditor(Document.class, 
				this.documentPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(DocumentTag.class, 
				this.documentTagPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
