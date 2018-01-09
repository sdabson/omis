package omis.presentenceinvestigation.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import omis.document.web.form.DocumentTagOperation;
import omis.exception.DuplicateEntityFoundException;
import omis.io.FileRemover;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.presentenceinvestigation.domain.CircumstanceOfOffense;
import omis.presentenceinvestigation.domain.OffenseSectionSummary;
import omis.presentenceinvestigation.domain.OffenseSectionSummaryAssociableDocument;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.OffenseSectionSummaryService;
import omis.presentenceinvestigation.web.controller.delegate.PresentenceInvestigationRequestSummaryModelDelegate;
import omis.presentenceinvestigation.web.form.DocumentTagItem;
import omis.presentenceinvestigation.web.form.OffenseSectionSummaryAssociableDocumentItem;
import omis.presentenceinvestigation.web.form.OffenseSectionSummaryForm;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.validator.OffenseSectionSummaryFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * OffenseSectionSummaryController.java
 * 
 *@author Annie Jacques 
 *@author Sheronda Vaughn
 *@version 0.1.0 (Apr 3, 2017)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/presentenceInvestigation/offenseSummary/")
@PreAuthorize("hasRole('USER')")
public class OffenseSectionSummaryController {
	
	/* View names. */
	
	private static final String BOOLEAN_VIEW_NAME = "common/json/booleanValue";
	
	private static final String EDIT_VIEW_NAME =
			"/presentenceInvestigation/offenseSummary/edit";
	
	private static final String
		OFFENSE_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_ITEM_CONTENT_VIEW_NAME =
				"/presentenceInvestigation/offenseSummary/includes/"
				+ "offenseSectionSummaryAssociableDocumentItemContent";
	
	private static final String DOCUMENT_TAG_ITEM_CONTENT_VIEW_NAME =
			"/presentenceInvestigation/offenseSummary/includes/"
			+ "offenseSectionSummaryDocumentTagItemContent";
	
	private static final String OFFENSE_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME =
			"/presentenceInvestigation/offenseSummary/includes/"
			+ "offenseSectionSummaryActionMenu";
	
	
	/* Model keys. */

	private static final String BOOLEAN_VALUE_MODEL_KEY = "booleanValue";
	
	private static final String OFFENSE_SECTION_SUMMARY_MODEL_KEY =
			"offenseSectionSummary";
	
	private static final String
		OFFENSE_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_ITEM_MODEL_KEY =
			"offenseSectionSummaryAssociableDocumentItem";
	
	private static final String
		OFFENSE_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_ITEM_INDEX_MODEL_KEY =
			"offenseSectionSummaryAssociableDocumentItemIndex";
	
	private static final String DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY =
			"documentTagItemIndexes";
	
	private static final String DOCUMENT_TAG_ITEM_MODEL_KEY = "documentTagItem";
	
	private static final String DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY =
			"documentTagItemIndex";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY =
			"presentenceInvestigationRequest";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String OFFENSE_SECTION_SUMMARY_FORM =
			"offenseSectionSummaryForm";
	
	/* REDIRECT VIEW NAMES */
	
	private static final String HOME_REDIRECT =
			"redirect:/presentenceInvestigation/home.html?"
			+ "presentenceInvestigationRequest=%d";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY =
			"offenseSectionSummaryEntity.exists";
	
	private static final String ERROR_WRITING_FILE_MSG = 
			"Error writing file: %s";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME =
			"omis.presentenceinvestigation.msgs.form";
	
	/* Service */
	
	@Autowired
	@Qualifier("offenseSectionSummaryService")
	private OffenseSectionSummaryService offenseSectionSummaryService;
	
	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("offenseSectionSummaryPropertyEditorFactory")
	private PropertyEditorFactory offenseSectionSummaryPropertyEditorFactory;

	@Autowired
	@Qualifier("offenseSectionSummaryAssociableDocumentPropertyEditorFactory")
	private PropertyEditorFactory
		offenseSectionSummaryAssociableDocumentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("circumstanceOfOffensePropertyEditorFactory")
	private PropertyEditorFactory circumstanceOfOffensePropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentPropertyEditorFactory")
	private PropertyEditorFactory documentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentTagPropertyEditorFactory")
	private PropertyEditorFactory documentTagPropertyEditorFactory;
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestPropertyEditorFactory")
	private PropertyEditorFactory
		presentenceInvestigationRequestPropertyEditorFactory;
	
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
	@Qualifier("offenseSectionSummaryFormValidator")
	private OffenseSectionSummaryFormValidator
		offenseSectionSummaryFormValidator;
	
	/**
	 * Default Constructor for OffenseSectionSummaryController
	 */
	public OffenseSectionSummaryController() {
	}
	
	/**
	 * Returns the view for creating/editing an OffenseSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView - View for creating/editing an OffenseSectionSummary
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENSE_SECTION_SUMMARY_VIEW') or "
			+ "hasRole('OFFENSE_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest){
		return this.prepareEditMav(presentenceInvestigationRequest,
				this.prepareForm(presentenceInvestigationRequest));
	}
	
	/**
	 * Saves an OffenseSectionSummary and related entities and returns to
	 * the PresentenceInvestigation Home screen
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param form - OffenseSectionSummary
	 * @param bindingResult - BindingResult
	 * @return ModelAndView - PresentenceInvestigation Home view on successful
	 * save, or back to OffenseSectionSummary edit view on form validation error
	 * @throws DuplicateEntityFoundException - When OffenseSectionSummary,
	 * OffenseSectionSummaryAssociableDocument, CircumstanceOfOffense, Document,
	 * or DocumentTag already exist with provided properties
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('OFFENSE_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value="presentenceInvestigationRequest",
			required=true)
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest,
			final OffenseSectionSummaryForm form,
			final BindingResult bindingResult) 
					throws DuplicateEntityFoundException{
		
		this.offenseSectionSummaryFormValidator.validate(form, bindingResult);
		
		if(bindingResult.hasErrors()){
			return this.prepareEditMav(presentenceInvestigationRequest, form);
		}
		else{
			OffenseSectionSummary offenseSectionSummary = 
					this.offenseSectionSummaryService
					.findOffenseSectionSummaryByPresentenceInvestigationRequest(
							presentenceInvestigationRequest);
			
			if(offenseSectionSummary == null){
				offenseSectionSummary = this.offenseSectionSummaryService
						.createOffenseSectionSummary(
								presentenceInvestigationRequest, form.getText());
			}
			else{
				offenseSectionSummary = this.offenseSectionSummaryService
						.updateOffenseSectionSummary(offenseSectionSummary,
								form.getText());
			}
			
			CircumstanceOfOffense circumstanceOfOffense =
					this.offenseSectionSummaryService
					.findCircumstanceOfOffenseByOffenseSectionSummary(
							offenseSectionSummary);
			
			if(circumstanceOfOffense == null){
				if(form.getFileExtension() != null 
						&& form.getFileExtension().trim().length() > 0){
					final String fileExtension = form.getFileExtension();
					this.documentFilenameGenerator
							.setExtension(fileExtension);
					final String filename =
							this.documentFilenameGenerator.generate();
					Document document = this.offenseSectionSummaryService
							.createDocument(form.getDate(), filename,
									fileExtension, form.getTitle());
					this.presentenceInvestigationDocumentPersister.persist(
							document, form.getData());
					this.processDocumentTags(form.getDocumentTagItems(),
							document);
					
					circumstanceOfOffense = this.offenseSectionSummaryService
							.createCircumstanceOfOffense(offenseSectionSummary,
									document, form.getChargeReason(),
									form.getInvolvementReason(),
									form.getCourtRecommendation());
				}
				else{
					circumstanceOfOffense = this.offenseSectionSummaryService
							.createCircumstanceOfOffense(offenseSectionSummary,
									null, form.getChargeReason(),
									form.getInvolvementReason(),
									form.getCourtRecommendation());
				}
			}
			else{
				if(circumstanceOfOffense.getDocument() == null){
					if(form.getFileExtension() != null 
							&& form.getFileExtension().trim().length() > 0){
						final String fileExtension = form.getFileExtension();
						this.documentFilenameGenerator
								.setExtension(fileExtension);
						final String filename =
								this.documentFilenameGenerator.generate();
						Document document = this.offenseSectionSummaryService
								.createDocument(form.getDate(), filename,
										fileExtension, form.getTitle());
						this.presentenceInvestigationDocumentPersister.persist(
								document, form.getData());
						this.processDocumentTags(form.getDocumentTagItems(),
								document);
						
						circumstanceOfOffense = this.offenseSectionSummaryService
								.updateCircumstanceOfOffense(circumstanceOfOffense,
										document, form.getChargeReason(),
										form.getInvolvementReason(),
										form.getCourtRecommendation());
					}
					else{
						circumstanceOfOffense = this.offenseSectionSummaryService
								.updateCircumstanceOfOffense(circumstanceOfOffense,
										null, form.getChargeReason(),
										form.getInvolvementReason(),
										form.getCourtRecommendation());
					}
				}
				else{
					Document document = this.offenseSectionSummaryService
							.updateDocument(circumstanceOfOffense.getDocument(),
									form.getDate(), form.getTitle());
					this.processDocumentTags(form.getDocumentTagItems(),
							document);
					circumstanceOfOffense = this.offenseSectionSummaryService
							.updateCircumstanceOfOffense(circumstanceOfOffense,
									document, form.getChargeReason(),
									form.getInvolvementReason(),
									form.getCourtRecommendation());
				}
			}
			
			if(form.getOffenseSectionSummaryAssociableDocumentItems() != null){
				for(OffenseSectionSummaryAssociableDocumentItem item :
					form.getOffenseSectionSummaryAssociableDocumentItems()){
					
					if(PresentenceInvestigationItemOperation.CREATE.equals(
							item.getItemOperation())){
						final String fileExtension = item.getFileExtension();
						this.documentFilenameGenerator
								.setExtension(fileExtension);
						final String filename =
								this.documentFilenameGenerator.generate();
						Document document = this.offenseSectionSummaryService
								.createDocument(item.getFileDate(),
										filename, item.getFileExtension(),
										item.getTitle());
						this.presentenceInvestigationDocumentPersister
								.persist(document, item.getData());
						
						this.offenseSectionSummaryService
							.createOffenseSectionSummaryAssociableDocument(
									offenseSectionSummary, document);
						
						if(item.getDocumentTagItems() != null){
							for(DocumentTagItem tagItem :
								item.getDocumentTagItems()){
								if(PresentenceInvestigationItemOperation
										.CREATE.equals(tagItem.getItemOperation())){
									this.offenseSectionSummaryService
										.createDocumentTag(document,
												tagItem.getName());
								}
							}
						}
					}
					if(PresentenceInvestigationItemOperation.UPDATE.equals(
							item.getItemOperation())){
						Document document = this.offenseSectionSummaryService
								.updateDocument(item.getDocument(),
										item.getFileDate(), item.getTitle());
						this.offenseSectionSummaryService
							.updateOffenseSectionSummaryAssociableDocument(
								item.getOffenseSectionSummaryAssociableDocument(),
								document);
						if(item.getDocumentTagItems() != null){
							for(DocumentTagItem tagItem :
								item.getDocumentTagItems()){
								if(PresentenceInvestigationItemOperation
										.CREATE.equals(
												tagItem.getItemOperation())){
									this.offenseSectionSummaryService
										.createDocumentTag(document,
												tagItem.getName());
								}
								if(PresentenceInvestigationItemOperation
										.UPDATE.equals(
												tagItem.getItemOperation())){
									this.offenseSectionSummaryService
										.updateDocumentTag(
											tagItem.getDocumentTag(),
											tagItem.getName());
								}
								if(PresentenceInvestigationItemOperation
										.REMOVE.equals(
												tagItem.getItemOperation())){
									this.offenseSectionSummaryService
										.removeDocumentTag(
												tagItem.getDocumentTag());
								}
							}
						}
					}
					if(PresentenceInvestigationItemOperation.REMOVE.equals(
							item.getItemOperation())){
						for(DocumentTag tag :
								this.offenseSectionSummaryService
								.findDocumentTagsByDocument(item.getDocument())){
							this.offenseSectionSummaryService
								.removeDocumentTag(tag);
						}
						this.offenseSectionSummaryService
							.removeOffenseSectionSummaryAssociableDocument(
								item.getOffenseSectionSummaryAssociableDocument());
						this.presentenceInvestigationDocumentRemover.remove(
								item.getDocument().getFilename());
						this.offenseSectionSummaryService.removeDocument(
								item.getDocument());
					}
					
				}
			}
			
			return new ModelAndView(String.format(HOME_REDIRECT,
					presentenceInvestigationRequest.getId()));
		}
	}
	
	/**
	 * Displays the view for an OffenseSectionSummaryAssociableDocumentItem row
	 * @param offenseSectionSummaryAssociableDocumentItemIndex - Integer
	 * @return ModelAndView - View for an
	 * OffenseSectionSummaryAssociableDocumentItem row
	 */
	@RequestMapping(value = "createOffenseSectionSummaryAssociableDocumentItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayOffenseSectionSummaryAssociableDocumentItem(
			@RequestParam(
			value = "offenseSectionSummaryAssociableDocumentItemIndex",
			required = true)
			final Integer offenseSectionSummaryAssociableDocumentItemIndex){
		ModelMap map = new ModelMap();
		
		OffenseSectionSummaryAssociableDocumentItem documentItem =
				new OffenseSectionSummaryAssociableDocumentItem();
		
		documentItem.setItemOperation(
				PresentenceInvestigationItemOperation.CREATE);
		
		map.addAttribute(
				OFFENSE_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_ITEM_MODEL_KEY,
				documentItem);
		map.addAttribute(
				OFFENSE_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_ITEM_INDEX_MODEL_KEY,
				offenseSectionSummaryAssociableDocumentItemIndex);
		
		return new ModelAndView(
			OFFENSE_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_ITEM_CONTENT_VIEW_NAME,
			map);
	}
	
	/**
	 * Displays the view for a Document Tag row for an
	 * OffenseSectionSummaryAssociableDocumentItem
	 * @param offenseSectionSummaryAssociableDocumentItemIndex - Integer
	 * @param documentTagItemIndex - Integer
	 * @return ModelAndView - View for a Document Tag row for an
	 * OffenseSectionSummaryAssociableDocumentItem
	 */
	@RequestMapping(value = "createDocumentTagItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayDocumentTagItemRow(@RequestParam(
			value = "offenseSectionSummaryAssociableDocumentItemIndex",
			required = true)
			final Integer offenseSectionSummaryAssociableDocumentItemIndex,
			@RequestParam(value = "documentTagItemIndex", required = true)
					final Integer documentTagItemIndex) {
		ModelMap map = new ModelMap();
		DocumentTagItem tagItem = new DocumentTagItem();
		
		tagItem.setItemOperation(PresentenceInvestigationItemOperation
				.CREATE);
		
		map.addAttribute(
				OFFENSE_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_ITEM_INDEX_MODEL_KEY,
				offenseSectionSummaryAssociableDocumentItemIndex);
		map.addAttribute(DOCUMENT_TAG_ITEM_MODEL_KEY, tagItem);
		map.addAttribute(DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY,
				documentTagItemIndex);
		
		return new ModelAndView(DOCUMENT_TAG_ITEM_CONTENT_VIEW_NAME,
				map);
	}
	
	/**
	 * Returns the ModelAndView for OffenseSectionSummary action menu
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return ModelAndView for OffenseSectionSummary action menu
	 */
	@RequestMapping(value="/offenseSectionSummaryActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayOffenseSectionSummaryActionMenu(
			@RequestParam(value="presentenceInvestigationRequest",
			required = true)
			final PresentenceInvestigationRequest
					presentenceInvestigationRequest){
		ModelMap map = new ModelMap();
		
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				presentenceInvestigationRequest);
		
		return new ModelAndView(
				OFFENSE_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns a prepared ModelAndView for creating/editing an
	 * OffenseSectionSummary
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @param form - OffenseSectionSummaryForm
	 * @return Prepared ModelAndView for creating/editing an
	 * OffenseSectionSummary
	 */
	private ModelAndView prepareEditMav(
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			final OffenseSectionSummaryForm form){
		ModelMap map = new ModelMap();
		
		OffenseSectionSummary offenseSectionSummary = 
				this.offenseSectionSummaryService
				.findOffenseSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
		
		map.addAttribute(OFFENDER_MODEL_KEY,
				(Offender) presentenceInvestigationRequest.getDocket().getPerson());
		map.addAttribute(OFFENSE_SECTION_SUMMARY_MODEL_KEY,
				offenseSectionSummary);
		map.addAttribute(OFFENSE_SECTION_SUMMARY_FORM, form);
		map.addAttribute(
				OFFENSE_SECTION_SUMMARY_ASSOCIABLE_DOCUMENT_ITEM_INDEX_MODEL_KEY,
				(form.getOffenseSectionSummaryAssociableDocumentItems() != null ?
						form.getOffenseSectionSummaryAssociableDocumentItems()
						.size() : 0));
		if(form.getOffenseSectionSummaryAssociableDocumentItems() != null){
			List<Integer> tagIndexes = new ArrayList<Integer>();
			for(int i = 0; i <
					form.getOffenseSectionSummaryAssociableDocumentItems()
					.size(); i++){
				if(form.getOffenseSectionSummaryAssociableDocumentItems().get(i)
						!= null){
					tagIndexes.add(i,
						(form.getOffenseSectionSummaryAssociableDocumentItems()
							.get(i).getDocumentTagItems() != null ?
							form.getOffenseSectionSummaryAssociableDocumentItems()
						.get(i).getDocumentTagItems().size() : 0));
				}
				else{
					tagIndexes.add(i, 0);
				}
			}
			map.addAttribute(DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY, tagIndexes);
		}
		map.addAttribute(
				DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY,
				(form.getDocumentTagItems() != null ?
						form.getDocumentTagItems().size() : 0));
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY,
				presentenceInvestigationRequest);
		
		this.offenderSummaryModelDelegate.add(map, (Offender)
				presentenceInvestigationRequest.getDocket().getPerson());
		this.presentenceInvestigationRequestSummaryModelDelegate.add(map,
				presentenceInvestigationRequest);
		
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Returns a prepared OffenseSectionSummaryForm
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 * @return Prepared OffenseSectionSummary Form
	 */
	private OffenseSectionSummaryForm prepareForm(
			final PresentenceInvestigationRequest presentenceInvestigationRequest){
		
		OffenseSectionSummaryForm form = new OffenseSectionSummaryForm();
		
		OffenseSectionSummary offenseSectionSummary =
				this.offenseSectionSummaryService
				.findOffenseSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
		
		if(offenseSectionSummary != null){
			List<OffenseSectionSummaryAssociableDocument>
				offenseSectionSummaryAssociableDocuments =
				this.offenseSectionSummaryService
				.findOffenseSectionSummaryAssociableDocumentsByOffenseSectionSummary(
						offenseSectionSummary);
			List<OffenseSectionSummaryAssociableDocumentItem> documentItems =
					new ArrayList<OffenseSectionSummaryAssociableDocumentItem>();
			for(OffenseSectionSummaryAssociableDocument associableDocument :
					offenseSectionSummaryAssociableDocuments){
				OffenseSectionSummaryAssociableDocumentItem item =
						new OffenseSectionSummaryAssociableDocumentItem();
				
				List<DocumentTagItem> tagItems = new ArrayList<DocumentTagItem>();
				List<DocumentTag> tags = this.offenseSectionSummaryService
						.findDocumentTagsByDocument(
								associableDocument.getDocument());
				
				for(DocumentTag tag : tags){
					DocumentTagItem tagItem = new DocumentTagItem();
					tagItem.setDocumentTag(tag);
					tagItem.setName(tag.getName());
					tagItem.setItemOperation(
							PresentenceInvestigationItemOperation.UPDATE);
					
					tagItems.add(tagItem);
				}
				
				item.setDocument(associableDocument.getDocument());
				item.setData(this.presentenceInvestigationDocumentRetriever
						.retrieve(associableDocument.getDocument()));
				item.setFileDate(associableDocument.getDocument().getDate());
				item.setFileExtension(associableDocument.getDocument()
						.getFileExtension());
				item.setFilename(associableDocument.getDocument().getFilename());
				item.setOffenseSectionSummaryAssociableDocument(
						associableDocument);
				item.setTitle(associableDocument.getDocument().getTitle());
				item.setItemOperation(
						PresentenceInvestigationItemOperation.UPDATE);
				item.setDocumentTagItems(tagItems);
				
				documentItems.add(item);
			}
			
			CircumstanceOfOffense circumstanceOfOffense =
					this.offenseSectionSummaryService
					.findCircumstanceOfOffenseByOffenseSectionSummary(
							offenseSectionSummary);
			
			if(circumstanceOfOffense != null){
				if(circumstanceOfOffense.getDocument() != null){
					List<omis.document.web.form.DocumentTagItem> tagItems =
							new ArrayList<omis.document.web.form.DocumentTagItem>();
					List<DocumentTag> tags = this.offenseSectionSummaryService
							.findDocumentTagsByDocument(
									circumstanceOfOffense.getDocument());
					
					for(DocumentTag tag : tags){
						omis.document.web.form.DocumentTagItem tagItem =
								new omis.document.web.form.DocumentTagItem();
						tagItem.setDocumentTag(tag);
						tagItem.setName(tag.getName());
						tagItem.setDocumentTagOperation(
								DocumentTagOperation.UPDATE);
						
						tagItems.add(tagItem);
					}
					
					form.setData(this.presentenceInvestigationDocumentRetriever
							.retrieve(circumstanceOfOffense.getDocument()));
					form.setDate(circumstanceOfOffense.getDocument().getDate());
					form.setDocument(circumstanceOfOffense.getDocument());
					form.setDocumentTagItems(tagItems);
					form.setFileExtension(circumstanceOfOffense.getDocument()
							.getFileExtension());
					form.setTitle(circumstanceOfOffense.getDocument().getTitle());
				}
				
				form.setChargeReason(circumstanceOfOffense.getStatement()
						.getChargeReason());
				form.setCourtRecommendation(circumstanceOfOffense.getStatement()
						.getCourtRecommendation());
				form.setInvolvementReason(circumstanceOfOffense.getStatement()
						.getInvolvementReason());
				
			}
			
			form.setText(offenseSectionSummary.getText());
			form.setOffenseSectionSummaryAssociableDocumentItems(documentItems);
		}
		
		return form;
	}
	
	/**
	 * Processes a list of documentTag items for creation, updating, or removal
	 * @param documentTagItems - List of DocumentTagItems
	 * @param document - Document for which the documentTags are being processed
	 * for
	 * @throws DuplicateEntityFoundException - When a document tag already exists
	 * with given name and document
	 */
	private void processDocumentTags(
			final List<omis.document.web.form.DocumentTagItem> documentTagItems, 
			final Document document) throws DuplicateEntityFoundException {
		if(documentTagItems != null){
			Iterator<omis.document.web.form.DocumentTagItem> documentTagIterator = 
					documentTagItems.iterator();
			while (documentTagIterator.hasNext()) {
				final omis.document.web.form.DocumentTagItem documentTagItem =
						documentTagIterator.next();
				final DocumentTagOperation documentTagOperation = 
						documentTagItem.getDocumentTagOperation();
				if (documentTagOperation == DocumentTagOperation.UPDATE) {
					this.offenseSectionSummaryService.updateDocumentTag(
							documentTagItem.getDocumentTag(), 
							documentTagItem.getName());
				} else if (documentTagOperation == DocumentTagOperation.CREATE) {
					this.offenseSectionSummaryService.createDocumentTag(
							document, documentTagItem.getName());
				} else if (documentTagOperation == DocumentTagOperation.REMOVE) {
					this.offenseSectionSummaryService.removeDocumentTag(
							documentTagItem.getDocumentTag());
				}
			}
		}
	}
	
	/**
	 * Reserved for extending session.
	 * 
	 * @return boolean
	 */
	@RequestMapping(value = "extendSession.html", method = RequestMethod.GET)
	public ModelAndView extendSession() {
		ModelMap map = new ModelMap();
		map.addAttribute(BOOLEAN_VALUE_MODEL_KEY, true);
		return new ModelAndView(BOOLEAN_VIEW_NAME, map);
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
		binder.registerCustomEditor(OffenseSectionSummary.class, 
				this.offenseSectionSummaryPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(OffenseSectionSummaryAssociableDocument.class, 
				this.offenseSectionSummaryAssociableDocumentPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(CircumstanceOfOffense.class, 
				this.circumstanceOfOffensePropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(Document.class, 
				this.documentPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(DocumentTag.class, 
				this.documentTagPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
}
