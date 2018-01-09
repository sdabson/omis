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
import omis.presentenceinvestigation.domain.FinancialSectionSummary;
import omis.presentenceinvestigation.domain.FinancialSectionSummaryDocumentAssociation;
import omis.presentenceinvestigation.domain.FinancialSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.FinancialSectionSummaryService;
import omis.presentenceinvestigation.web.controller.delegate.PresentenceInvestigationRequestSummaryModelDelegate;
import omis.presentenceinvestigation.web.form.DocumentTagItem;
import omis.presentenceinvestigation.web.form.FinancialSectionSummaryDocumentAssociationItem;
import omis.presentenceinvestigation.web.form.FinancialSectionSummaryForm;
import omis.presentenceinvestigation.web.form.FinancialSectionSummaryNoteItem;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.validator.FinancialSectionSummaryFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * FinancialSectionSummaryController.java
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (July 14, 2017)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/presentenceInvestigation/financialSummary/")
@PreAuthorize("hasRole('USER')")
public class FinancialSectionSummaryController {
	
	/* View Names */
	
	private static final String DOCUMENT_TAG_ITEM_CONTENT_VIEW_NAME
		= "/presentenceInvestigation/financialSummary/includes/"
		+ "financialSectionSummaryDocumentAssociationTagItemContent";
	
	private static final String EDIT_VIEW_NAME 
		= "/presentenceInvestigation/financialSummary/edit";
	
	private static final String
		FINANCIAL_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_CONTENT_VIEW_NAME
		= "/presentenceInvestigation/financialSummary/includes/"
		+ "financialSectionSummaryDocumentAssociationItemContent";
	
	private static final String FINANCIAL_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME
		= "/presentenceInvestigation/financialSummary/includes/"
		+ "financialSectionSummaryNoteTableRow";
	
	private static final String
	FINANCIAL_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME 
		= "/presentenceInvestigation/financialSummary/includes/"
		+ "financialSectionSummaryNoteItemsActionMenu";
	
	private static final String FINANCIAL_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME 
			= "/presentenceInvestigation/financialSummary/includes/"
			+ "financialSectionSummaryActionMenu";
	
	/* Model Keys */
	
	private static final String FORM_MODEL_KEY = "financialSectionSummaryForm";
	
	private static final String FINANCIAL_SECTION_SUMMARY_MODEL_KEY 
		= "financialSectionSummary";
	
	private static final String
		FINANCIAL_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_MODEL_KEY 
		= "financialSectionSummaryDocumentAssociationItem";
	
	private static final String
		FINANCIAL_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_INDEX_MODEL_KEY
		= "financialSectionSummaryDocumentAssociationItemIndex";
	
	private static final String DOCUMENT_TAG_ITEM_MODEL_KEY = "documentTagItem";
	
	private static final String DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY
			= "documentTagItemIndex";
	
	private static final String DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY
			= "documentTagItemIndexes";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY
		= "presentenceInvestigationRequest";
	
	private static final String FINANCIAL_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY
		= "financialSectionSummaryNoteItem";
	
	private static final String 
		FINANCIAL_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY
		= "financialSectionSummaryNoteItemIndex";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"financialSectionSummaryEntity.exists";
	
	private static final String ERROR_WRITING_FILE_MSG = 
			"Error writing file: %s";
	
	/* REDIRECT VIEW NAMES */
	
	private static final String HOME_REDIRECT =
			"redirect:/presentenceInvestigation/home.html?"
			+ "presentenceInvestigationRequest=%d";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.presentenceinvestigation.msgs.form";
	
	/* Service */
	
	@Autowired
	@Qualifier("financialSectionSummaryService")
	private FinancialSectionSummaryService financialSectionSummaryService;
	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("financialSectionSummaryPropertyEditorFactory")
	private PropertyEditorFactory financialSectionSummaryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("financialSectionSummaryDocumentAssociationPropertyEditorFactory")
	private PropertyEditorFactory 
		financialSectionSummaryDocumentAssociationPropertyEditorFactory;
	
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
	@Qualifier("financialSectionSummaryNotePropertyEditorFactory")
	private PropertyEditorFactory 
		financialSectionSummaryNotePropertyEditorFactory;
	
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
	@Qualifier("financialSectionSummaryFormValidator")
	private FinancialSectionSummaryFormValidator 
		financialSectionSummaryFormValidator;
	
	/**
	 * Default Constructor for FinancialSectionSummaryController
	 */
	public FinancialSectionSummaryController() {
	}
	
	/**
	 * Displays the ModelAndView for editing a FinancialSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView for editing a FinancialSexctionSummary
	 * @throws DuplicateEntityFoundException - When a FinancialSectionSummary
	 * already exists for the presentence investigation
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('FINANCIAL_SECTION_SUMMARY_VIEW') or "
			+ "hasRole('FINANCIAL_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest)
						throws DuplicateEntityFoundException{
		return this.prepareEditMav(presentenceInvestigationRequest,
				this.populateForm(presentenceInvestigationRequest));
	}
	
	/**
	 * Saves a FinancialSectionSummary and Documents and returns to the
	 * PresentenceInvestigationRequest Home screen
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param form - FinancialSectionSummaryForm
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - returns to PresentenceInvestigationRequest Home 
	 * on successful save, or back to FinancialSectionSummary on form error
	 * @throws DuplicateEntityFoundException - When a 
	 * FinancialSectionSummaryDocumentAssociation already exists with provided 
	 * document and FinancialSectionSummary
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('FINANCIAL_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest,
			final FinancialSectionSummaryForm form,
			final BindingResult bindingResult)
					throws DuplicateEntityFoundException{
		
		this.financialSectionSummaryFormValidator
				.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return this.prepareEditMav(presentenceInvestigationRequest, form);
		}
		else{
			
			FinancialSectionSummary financialSectionSummary =
				this.financialSectionSummaryService
				.findFinancialSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
			
			if(financialSectionSummary == null){
				financialSectionSummary =
						this.financialSectionSummaryService
						.createFinancialSectionSummary(
								presentenceInvestigationRequest,
								form.getText());
			}
			else{
				financialSectionSummary = this.financialSectionSummaryService
						.updateFinancialSectionSummary(
								financialSectionSummary, form.getText());
			}
			
			if(form.getFinancialSectionSummaryNoteItems() != null){
				for(FinancialSectionSummaryNoteItem item :
					form.getFinancialSectionSummaryNoteItems()){
					if(PresentenceInvestigationItemOperation.CREATE.equals(
							item.getItemOperation())){
						this.financialSectionSummaryService
							.createFinancialSectionSummaryNote(
									item.getDescription(), item.getDate(),
									financialSectionSummary);
					}
					else if(PresentenceInvestigationItemOperation.UPDATE
							.equals(item.getItemOperation())){
						this.financialSectionSummaryService
							.updateFinancialSectionSummaryNote(
									item.getFinancialSectionSummaryNote(),
									item.getDescription(), item.getDate());
					}
					else if(PresentenceInvestigationItemOperation.REMOVE
							.equals(item.getItemOperation())){
						this.financialSectionSummaryService
							.removeFinancialSectionSummaryNote(
									item.getFinancialSectionSummaryNote());
					}
				}
			}
			
			if(form.getFinancialSectionSummaryDocumentAssociationItems()
					!= null){
				for(FinancialSectionSummaryDocumentAssociationItem item :
					form.getFinancialSectionSummaryDocumentAssociationItems()){
					
					if(PresentenceInvestigationItemOperation.CREATE.equals(
							item.getItemOperation())){
						final String fileExtension = item.getFileExtension();
						this.documentFilenameGenerator
								.setExtension(fileExtension);
						final String filename =
								this.documentFilenameGenerator.generate();
						Document document = this.financialSectionSummaryService
								.createDocument(item.getFileDate(),
										filename, item.getFileExtension(),
										item.getTitle());
						this.presentenceInvestigationDocumentPersister
								.persist(document, item.getData());
						
						this.financialSectionSummaryService
							.createFinancialSectionSummaryDocumentAssociation(
								document, financialSectionSummary);
						
						if(item.getDocumentTagItems() != null){
							for(DocumentTagItem tagItem :
								item.getDocumentTagItems()){
								if(PresentenceInvestigationItemOperation
									.CREATE.equals(tagItem.getItemOperation())){
									this.financialSectionSummaryService
										.createDocumentTag(document,
												tagItem.getName());
								}
							}
						}
					}
					if(PresentenceInvestigationItemOperation.UPDATE.equals(
							item.getItemOperation())){
						Document document = this.financialSectionSummaryService
								.updateDocument(item.getDocument(),
										item.getFileDate(), item.getTitle());
						this.financialSectionSummaryService
						.updateFinancialSectionSummaryDocumentAssociation(
							item.getFinancialSectionSummaryDocumentAssociation(),
							document);
						if(item.getDocumentTagItems() != null){
							for(DocumentTagItem tagItem :
								item.getDocumentTagItems()){
								if(PresentenceInvestigationItemOperation
									.CREATE.equals(tagItem.getItemOperation())){
									this.financialSectionSummaryService
										.createDocumentTag(document,
												tagItem.getName());
								}
								if(PresentenceInvestigationItemOperation
									.UPDATE.equals(tagItem.getItemOperation())){
									this.financialSectionSummaryService
										.updateDocumentTag(
											tagItem.getDocumentTag(),
											tagItem.getName());
								}
								if(PresentenceInvestigationItemOperation
									.REMOVE.equals(tagItem.getItemOperation())){
									this.financialSectionSummaryService
									.removeDocumentTag(tagItem.getDocumentTag());
								}
							}
						}
					}
					if(PresentenceInvestigationItemOperation.REMOVE.equals(
							item.getItemOperation())){
						for(DocumentTag tag
								: this.financialSectionSummaryService
								.findDocumentTagsByDocument(item.getDocument())){
							this.financialSectionSummaryService
								.removeDocumentTag(tag);
						}
						this.financialSectionSummaryService
							.removeFinancialSectionSummaryDocumentAssociation(
							item.getFinancialSectionSummaryDocumentAssociation());
						this.presentenceInvestigationDocumentRemover.remove(
								item.getDocument().getFilename());
						this.financialSectionSummaryService.removeDocument(
								item.getDocument());
					}
				}
			}
		}
		
		return new ModelAndView(String.format(HOME_REDIRECT,
				presentenceInvestigationRequest.getId()));
		
	}
	
	/**
	 * Displays a FinancialSectionSummaryNote item row
	 * @param financialSectionSummaryNoteItemIndex - Integer
	 * @return ModelAndView - FinancialSectionSummaryNote item row
	 */
	@RequestMapping(value = "createFinancialSectionSummaryNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayFinancialSectionSummaryNoteItem(@RequestParam(
			value = "financialSectionSummaryNoteItemIndex", required = true)
				final Integer financialSectionSummaryNoteItemIndex){
		ModelMap map = new ModelMap();
		
		FinancialSectionSummaryNoteItem noteItem =
				new FinancialSectionSummaryNoteItem();
		
		noteItem.setItemOperation(
				PresentenceInvestigationItemOperation.CREATE);
		
		map.addAttribute(FINANCIAL_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY,
				noteItem);
		map.addAttribute(
				FINANCIAL_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
				financialSectionSummaryNoteItemIndex);
		
		
		return new ModelAndView(
				FINANCIAL_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME, map);
	}
	
	/**
	 * Displays a FinancialSectionSummaryDocumentAssociation item
	 * @param financialSectionSummaryDocumentAssociationItemIndex - Integer
	 * @return ModelAndView - FinancialSectionSummary item  content
	 */
	@RequestMapping(value =
			"createFinancialSectionSummaryDocumentAssociationItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayFinancialSectionSummaryDocumentAssociationItem(
			@RequestParam(
			value = "financialSectionSummaryDocumentAssociationItemIndex",
			required = true)
			final Integer financialSectionSummaryDocumentAssociationItemIndex){
		ModelMap map = new ModelMap();
		
		FinancialSectionSummaryDocumentAssociationItem associableDocumentItem =
				new FinancialSectionSummaryDocumentAssociationItem();
		
		associableDocumentItem.setItemOperation(
				PresentenceInvestigationItemOperation.CREATE);
		
		map.addAttribute(
				FINANCIAL_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_MODEL_KEY,
				associableDocumentItem);
		map.addAttribute(
			FINANCIAL_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_INDEX_MODEL_KEY,
			financialSectionSummaryDocumentAssociationItemIndex);
		
		
		return new ModelAndView(
			FINANCIAL_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_CONTENT_VIEW_NAME,
			map);
	}
	
	/**
	 * Displays a DocumentTag item
	 * @param financialDocumentItemIndex - Integer
	 * @param documentTagItemIndex - Integer
	 * @return ModelAndView - DocumentTag item content
	 */
	@RequestMapping(value = "createDocumentTagItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayDocumentTagItemRow(@RequestParam(
			value = "financialSectionSummaryDocumentAssociationItemIndex",
				required = true) final Integer financialDocumentItemIndex,
			@RequestParam(value = "documentTagItemIndex", required = true)
					final Integer documentTagItemIndex) {
		ModelMap map = new ModelMap();
		DocumentTagItem tagItem = new DocumentTagItem();
		tagItem.setItemOperation(PresentenceInvestigationItemOperation
				.CREATE);
		map.addAttribute(
			FINANCIAL_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_INDEX_MODEL_KEY,
			financialDocumentItemIndex);
		map.addAttribute(DOCUMENT_TAG_ITEM_MODEL_KEY, tagItem);
		map.addAttribute(DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY,
				documentTagItemIndex);
		return new ModelAndView(DOCUMENT_TAG_ITEM_CONTENT_VIEW_NAME,
				map);
	}
	
	/* Action Menus */
	
	/**
	 * Displays the FinancialSectionSummary Action Menu
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView - FinancialSectionSummary Action Menu
	 */
	@RequestMapping(value="/financialSectionSummaryActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayFinancialSectionSummaryActionMenu(
			@RequestParam(value="presentenceInvestigationRequest",
			required = true)
			final PresentenceInvestigationRequest
					presentenceInvestigationRequest){
		ModelMap map = new ModelMap();
		
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				presentenceInvestigationRequest);
		
		return new ModelAndView(
				FINANCIAL_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the FinancialSectionSummaryNote Items Action Menu
	 * @return ModelAndView - FinancialSectionSummaryNote Items Action Menu
	 */
	@RequestMapping(value="/financialSectionSummaryNoteItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayFinancialSectionSummaryNoteItemsActionMenu(){
		return new ModelAndView(
				FINANCIAL_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME);
	}
	
	/* Helper Methods */
	
	/**
	 * Prepares a ModelAndView for editing a FinancialSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * from which the associated FinancialSummary will be found for editing
	 * @param form - FinancialSectionSummaryForm
	 * @return ModelAndView for editing a FinancialSectionSummary
	 */
	private ModelAndView prepareEditMav(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final FinancialSectionSummaryForm form) {
		
		ModelMap map = new ModelMap();
		
		FinancialSectionSummary financialSectionSummary = 
				this.financialSectionSummaryService
				.findFinancialSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
		
		map.addAttribute(FINANCIAL_SECTION_SUMMARY_MODEL_KEY, 
				financialSectionSummary);
		map.addAttribute(FORM_MODEL_KEY, form);
		map.addAttribute(FINANCIAL_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY,
				(form.getFinancialSectionSummaryNoteItems() != null ?
						form.getFinancialSectionSummaryNoteItems().size()
						: 0));
		map.addAttribute(
			FINANCIAL_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_INDEX_MODEL_KEY, 
			(form.getFinancialSectionSummaryDocumentAssociationItems() != null ? 
				form.getFinancialSectionSummaryDocumentAssociationItems().size() 
				: 0));
		if(form.getFinancialSectionSummaryDocumentAssociationItems() != null) {
			List<Integer> tagIndexes = new ArrayList<Integer>();
			for(int i = 0;
					i < form.getFinancialSectionSummaryDocumentAssociationItems()
					.size(); i++) {
				if(form.getFinancialSectionSummaryDocumentAssociationItems()
						.get(i) != null) {
					tagIndexes.add(i, 
						(form.getFinancialSectionSummaryDocumentAssociationItems()
						.get(i).getDocumentTagItems() != null ?
						form.getFinancialSectionSummaryDocumentAssociationItems()
						.get(i).getDocumentTagItems().size() : 0));
				}
				else {
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
	 * Populates a FinancialSectionSummaryForm from a 
	 * FinancialSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * from which the associated FinancialSummary will be found
	 * @return populated FinancialSectionSummaryForm
	 */
	private FinancialSectionSummaryForm populateForm(
			final PresentenceInvestigationRequest 
				presentenceInvestigationRequest) {
		
		FinancialSectionSummaryForm form = new FinancialSectionSummaryForm();
		
		FinancialSectionSummary financialSectionSummary 
			= this.financialSectionSummaryService
			.findFinancialSectionSummaryByPresentenceInvestigationRequest(
					presentenceInvestigationRequest);
		
		if(financialSectionSummary != null) {
			form.setText(financialSectionSummary.getText());
			
			List<FinancialSectionSummaryNote> financialSectionSummaryNotes =
					this.financialSectionSummaryService
					.findFinancialSectionSummaryNotesByFinancialSectionSummary(
							financialSectionSummary);
				List<FinancialSectionSummaryDocumentAssociation>
					financialSectionSummaryDocumentAssociations =
					this.financialSectionSummaryService
		.findFinancialSectionSummaryDocumentAssociationsByFinancialSectionSummary(
							financialSectionSummary);
			
			List<FinancialSectionSummaryNoteItem> noteItems =
				new ArrayList<FinancialSectionSummaryNoteItem>();
			List<FinancialSectionSummaryDocumentAssociationItem> documentItems =
				new ArrayList<FinancialSectionSummaryDocumentAssociationItem>();
				
			for(FinancialSectionSummaryNote note :
						financialSectionSummaryNotes){
				FinancialSectionSummaryNoteItem item =
						new FinancialSectionSummaryNoteItem();
				item.setDate(note.getDate());
				item.setDescription(note.getDescription());
				item.setFinancialSectionSummaryNote(note);
				item.setItemOperation(
						PresentenceInvestigationItemOperation.UPDATE);
				
				noteItems.add(item);
			}
			
			for(FinancialSectionSummaryDocumentAssociation financialDocument 
					: financialSectionSummaryDocumentAssociations) {
				FinancialSectionSummaryDocumentAssociationItem item 
					= new FinancialSectionSummaryDocumentAssociationItem();
				
				List<DocumentTagItem> tagItems 
					= new ArrayList<DocumentTagItem>();
				List<DocumentTag> tags = this.financialSectionSummaryService
					.findDocumentTagsByDocument(financialDocument.getDocument());
				for(DocumentTag tag : tags) {
					DocumentTagItem tagItem = new DocumentTagItem();
					tagItem.setDocumentTag(tag);
					tagItem.setName(tag.getName());
					tagItem.setItemOperation(
							PresentenceInvestigationItemOperation.UPDATE);
					
					tagItems.add(tagItem);
				}
				
				item.setDocument(financialDocument.getDocument());
				item.setDocumentTagItems(tagItems);
				item.setFileDate(financialDocument.getDocument().getDate());
				item.setFileExtension(financialDocument.getDocument()
						.getFileExtension());
				item.setFilename(financialDocument.getDocument().getFilename());
				item.setTitle(financialDocument.getDocument().getTitle());
				item.setFinancialSectionSummaryDocumentAssociation(
						financialDocument);
				item.setItemOperation(
						PresentenceInvestigationItemOperation.UPDATE);				
				item.setData(this.presentenceInvestigationDocumentRetriever
						.retrieve(item.getDocument()));
				
				documentItems.add(item);
				
			}
			
			form.setFinancialSectionSummaryDocumentAssociationItems(
					documentItems);
			
			form.setFinancialSectionSummaryNoteItems(noteItems);
						
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
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(PresentenceInvestigationRequest.class, 
				this.presentenceInvestigationRequestPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(FinancialSectionSummary.class, 
				this.financialSectionSummaryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(FinancialSectionSummaryNote.class, 
				this.financialSectionSummaryNotePropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(FinancialSectionSummaryDocumentAssociation
				.class, 
				this.financialSectionSummaryDocumentAssociationPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Document.class, 
				this.documentPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(DocumentTag.class, 
				this.documentTagPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
	
}
