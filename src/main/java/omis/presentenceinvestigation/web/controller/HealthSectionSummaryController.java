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
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.presentenceinvestigation.domain.HealthRating;
import omis.presentenceinvestigation.domain.HealthSectionSummary;
import omis.presentenceinvestigation.domain.HealthSectionSummaryDocumentAssociation;
import omis.presentenceinvestigation.domain.HealthSectionSummaryNote;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.service.HealthSectionService;
import omis.presentenceinvestigation.web.controller.delegate.PresentenceInvestigationRequestSummaryModelDelegate;
import omis.presentenceinvestigation.web.form.DocumentTagItem;
import omis.presentenceinvestigation.web.form.HealthSectionSummaryDocumentAssociationItem;
import omis.presentenceinvestigation.web.form.HealthSectionSummaryForm;
import omis.presentenceinvestigation.web.form.HealthSectionSummaryNoteItem;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationItemOperation;
import omis.presentenceinvestigation.web.validator.HealthSectionSummaryFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Health section summary controller.
 *
 * @author Sheronda Vaughn
 * @author Josh Divine
 * @version 0.1.1 (Oct 24, 2018)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/presentenceInvestigation/healthSummary/")
@PreAuthorize("hasRole('USER')")
public class HealthSectionSummaryController {

	/* View Names */
	
	private static final String EDIT_VIEW_NAME 
			= "/presentenceInvestigation/healthSummary/edit";
	
	private static final String
			HEALTH_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME 
			= "/presentenceInvestigation/healthSummary/includes/"
				+ "healthSectionSummaryNoteTableRow";
	
	private static final String
		HEALTH_SECTION_SUMMARY_DOCUMENT_ITEM_ASSOCIATION_CONTENT_VIEW_NAME 
			= "/presentenceInvestigation/healthSummary/includes/"
			+ "healthSectionSummaryDocumentAssociationItemContent";
	
	private static final String
			DOCUMENT_TAG_ITEM_CONTENT_VIEW_NAME 
			= "/presentenceInvestigation/healthSummary/includes/"
			+ "healthSectionSummaryDocumentTagItemContent";
	
	private static final String
			HEALTH_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME 
			= "/presentenceInvestigation/healthSummary/includes/"
			+ "healthSectionSummaryActionMenu";
	
	private static final String
			HEALTH_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME 
			= "/presentenceInvestigation/healthSummary/includes/"
					+ "healthSectionSummaryNoteItemsActionMenu";
	
	private static final String 
		HEALTH_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEMS_MODEL_KEY
			= "/presentenceInvestigation/healthSummary/includes/"
				+ "healthSectionSummaryDocumentAsociationItemsActionMenu";
	
	/* Model Keys */
	
	private static final String HEALTH_SECTION_SUMMARY_MODEL_KEY 
		= "healthSectionSummary";
	
	private static final String HEALTH_SECTION_SUMMARY_FORM_MODEL_KEY 
		= "healthSectionSummaryForm";
	
	private static final String
			HEALTH_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY 
			= "healthSectionSummaryNoteItem";
	
	private static final String
			HEALTH_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_MODEL_KEY 
			= "healthSectionSummaryDocumentAssociationItem";
	
	private static final String DOCUMENT_TAG_ITEM_MODEL_KEY = "documentTagItem";
	
	private static final String
			HEALTH_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY 
			= "healthSectionSummaryNoteItemIndex";
	
	private static final String
			HEALTH_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_INDEX_MODEL_KEY 
			= "healthSectionSummaryDocumentAssociationItemIndex";
	
	private static final String DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY 
			= "documentTagItemIndexes";
	
	private static final String DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY 
			= "documentTagItemIndex";
		
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY 
			= "presentenceInvestigationRequest";
	
	/* REDIRECT VIEW NAMES */
	
	private static final String HOME_REDIRECT 
			= "redirect:/presentenceInvestigation/home.html?"
			+ "presentenceInvestigationRequest=%d";
	
	/* Message Keys */
	
	private static final String ENTITY_EXISTS_MESSAGE_KEY 
			= "healthSectionSummaryEntity.exists";
	
	private static final String ERROR_WRITING_FILE_MSG  
			= "Error writing file: %s";
	
	/* Bundles */
	
	private static final String ERROR_BUNDLE_NAME 
			= "omis.presentenceinvestigation.msgs.form";
	
	/* Service */
	
	@Autowired
	@Qualifier("healthSectionService")
	private HealthSectionService healthSectionService;
	

	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("healthSectionSummaryPropertyEditorFactory")
	private PropertyEditorFactory
				healthSectionSummaryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("healthSectionSummaryNotePropertyEditorFactory")
	private PropertyEditorFactory
				healthSectionSummaryNotePropertyEditorFactory;
	
	@Autowired
	@Qualifier("healthSectionSummaryDocumentAssociationPropertyEditorFactory")
	private PropertyEditorFactory
				healthSectionSummaryDocumentAssociationPropertyEditorFactory;
	
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
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory 
					offenderPropertyEditorFactory;
	
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
	@Qualifier("healthSectionSummaryFormValidator")
	private HealthSectionSummaryFormValidator
		healthSectionSummaryFormValidator;
		
	/** Instantiates an implementation of HealthSectionSummaryController */
	public HealthSectionSummaryController() {
		// Default constructor.
	}
	
	/**
	 * Returns a model and view to edit a health section summary.
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return model and view
	 * @throws DuplicateEntityFoundException
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('HEALTH_SECTION_SUMMARY_VIEW') or "
			+ "hasRole('HEALTH_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "presentenceInvestigationRequest",
			required = true) 
			final PresentenceInvestigationRequest 
			presentenceInvestigationRequest) 
					throws DuplicateEntityFoundException {
		return this.prepareEditMav(presentenceInvestigationRequest, 
				this.prepareForm(presentenceInvestigationRequest));
	}

	/**
	 * Returns a model and view of health section summary of 
	 * presentence investigation request.
	 *
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @param form form
	 * @param result result
	 * @return model and view
	 * @throws DuplicateEntityFoundException
	 */
	@RequestMapping(value="/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('HEALTH_SECTION_SUMMARY_EDIT') or "
			+ "hasRole('ADMIN')")
	public ModelAndView save(
			@RequestParam(value = "presentenceInvestigationRequest",
			required = true) final PresentenceInvestigationRequest 
			presentenceInvestigationRequest, 
			final HealthSectionSummaryForm form, 
			final BindingResult result) 
					throws DuplicateEntityFoundException {
		this.healthSectionSummaryFormValidator.validate(form, result);
		HealthSectionSummary summary = this.healthSectionService
				.findHealthSectionSummaryByPresentenceInvestigationRequest(
						presentenceInvestigationRequest);
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(presentenceInvestigationRequest, 
					form, result);			
		}
		
		if (summary == null) {			
			summary = this.healthSectionService.createHealthSectionSummary(
					presentenceInvestigationRequest, form.getRating());
		} else {
			summary = this.healthSectionService.updateHealthSectionSummary(
					summary, form.getRating());
		}		
		
		if (form.getHealthSectionSummaryNoteItems() != null) {
			for (HealthSectionSummaryNoteItem item
					: form.getHealthSectionSummaryNoteItems()) {
				if (PresentenceInvestigationItemOperation.CREATE.equals(
						item.getItemOperation())) {
					this.healthSectionService.createHealthSectionSummaryNote(
							summary, item.getDescription(), item.getDate());
				} else if (PresentenceInvestigationItemOperation.UPDATE.equals(
						item.getItemOperation())) {
					if (this.isNoteChanged(
							item.getHealthSectionSummaryNote(), item.getDate(), 
							item.getDescription())) {					
					this.healthSectionService.updateHealthSectionSummaryNote(
							item.getHealthSectionSummaryNote(), summary, 
							item.getDescription(), item.getDate());
					}
				} else if (PresentenceInvestigationItemOperation.REMOVE.equals(
						item.getItemOperation())) {
					this.healthSectionService.removeHealthSectionSummaryNote(
							item.getHealthSectionSummaryNote());
				} else {
					throw new UnsupportedOperationException(
							"Operation not supported: " 
									+ item.getItemOperation());
				}
			}
		}		
		if(form.getHealthSectionSummaryDocumentAssociationItems() != null) {
			for(HealthSectionSummaryDocumentAssociationItem item :
				form.getHealthSectionSummaryDocumentAssociationItems()) {				
				if(PresentenceInvestigationItemOperation.CREATE.equals(
						item.getItemOperation())){
					final String fileExtension = item.getFileExtension();
					this.documentFilenameGenerator
							.setExtension(fileExtension);
					final String filename =
							this.documentFilenameGenerator.generate();
					item.setFilename(filename);
					Document document = this.healthSectionService
							.createDocument(item.getFileDate(),
									item.getTitle(), filename, 
									fileExtension);
					this.presentenceInvestigationDocumentPersister
							.persist(document, item.getData());
					
					this.healthSectionService
					.createHealthSectionSummaryDocumentAssociation(
							summary, document);
					
					if(item.getDocumentTagItems() != null) {
						for(DocumentTagItem tagItem :
							item.getDocumentTagItems()){
							if(PresentenceInvestigationItemOperation
									.CREATE.equals(tagItem.getItemOperation())){
								this.healthSectionService
									.createDocumentTag(document,
											tagItem.getName());
							}
						}
					}
				}
				if(PresentenceInvestigationItemOperation.UPDATE.equals(
						item.getItemOperation())){
					Document document = this.healthSectionService
							.updateDocument(item.getDocument(),
									item.getFileDate(), item.getTitle(), 
									item.getFileExtension());
					this.healthSectionService
					.updateHealthSectionSummaryDocumentAssociation(
							item.getHealthSectionSummaryDocumentAssociation(), 
							summary, document);
					if(item.getDocumentTagItems() != null){
						for(DocumentTagItem tagItem :
							item.getDocumentTagItems()){
							if(PresentenceInvestigationItemOperation
									.CREATE.equals(tagItem.getItemOperation())){
								this.healthSectionService
									.createDocumentTag(document,
											tagItem.getName());
							}
							if(PresentenceInvestigationItemOperation
									.UPDATE.equals(tagItem.getItemOperation())){
								this.healthSectionService
									.updateDocumentTag(
										tagItem.getDocumentTag(), document,
										tagItem.getName());
							}
							if(PresentenceInvestigationItemOperation
									.REMOVE.equals(tagItem.getItemOperation())){
								this.healthSectionService
									.removeDocumentTag(
											tagItem.getDocumentTag());
							}
						}
					}
				}
				if(PresentenceInvestigationItemOperation.REMOVE.equals(
						item.getItemOperation())){
					for(DocumentTag tag
							: this.healthSectionService
							.findDocumentTagsByDocument(item.getDocument())){
						this.healthSectionService
							.removeDocumentTag(tag);
					}
					this.healthSectionService
						.removeHealthSectionSummaryDocumentAssociation(
							item.getHealthSectionSummaryDocumentAssociation());
					this.presentenceInvestigationDocumentRemover.remove(
							item.getDocument().getFilename());
					this.healthSectionService.removeDocument(
							item.getDocument());
				}
			}
		}
		
		return new ModelAndView(String.format(HOME_REDIRECT, 
				presentenceInvestigationRequest.getId()));
	}
	
	/**
	 * Returns a model and view of new health section summary note item.
	 *
	 * @param healthSectionSummaryNoteItemIndex health section summary note 
	 * item index
	 * @return model and view
	 */
	@RequestMapping(value = "createHealthSectionSummaryNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView createHealthSectionSummaryNoteItem(@RequestParam(
			value = "healthSectionSummaryNoteItemIndex", required = true)
			final Integer healthSectionSummaryNoteItemIndex) {
		ModelMap map = new ModelMap();
		HealthSectionSummaryNoteItem item = new HealthSectionSummaryNoteItem();
		item.setItemOperation(PresentenceInvestigationItemOperation.CREATE);
		map.addAttribute(HEALTH_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY, 
				healthSectionSummaryNoteItemIndex);
		map.addAttribute(HEALTH_SECTION_SUMMARY_NOTE_ITEM_MODEL_KEY, item);
		
		return new ModelAndView(
				HEALTH_SECTION_SUMMARY_NOTE_ITEM_ROW_VIEW_NAME, map);
	}
	
	/**
	 * Returns a model and view of new health section summary document 
	 * association item.
	 *
	 * @param healthSectionSummaryNoteItemIndex health section summary document 
	 * association item index
	 * @return model and view
	 */
	@RequestMapping(
			value = "/createHealthSectionSummaryDocumentAssociationItem.html",
			method = RequestMethod.GET)
	public ModelAndView createHealthSectionSummaryDocumentAssociationItem(
			@RequestParam(
					value = "healthSectionSummaryDocumentAssociationItemIndex", 
			required = true)
			final Integer healthSectionSummaryDocumentAssociationItemIndex) {
		ModelMap map = new ModelMap();
		HealthSectionSummaryDocumentAssociationItem item 
		= new HealthSectionSummaryDocumentAssociationItem();
		item.setItemOperation(PresentenceInvestigationItemOperation.CREATE);
		map.addAttribute(
				HEALTH_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_INDEX_MODEL_KEY, 
				healthSectionSummaryDocumentAssociationItemIndex);
		map.addAttribute(
				HEALTH_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_MODEL_KEY, 
				item);
		
		return new ModelAndView(
				HEALTH_SECTION_SUMMARY_DOCUMENT_ITEM_ASSOCIATION_CONTENT_VIEW_NAME, 
				map);
	}
	
	/**
	 * Returns a model and view of new health section summary document 
	 * association item.
	 *
	 * @param healthSectionSummaryNoteItemIndex health section summary document 
	 * association item index
	 * @return model and view
	 */
	@RequestMapping(
			value = "/createDocumentTagItem.html",
			method = RequestMethod.GET)
	public ModelAndView createHealthSectionSummaryDocumentTagItem(
			@RequestParam(
					value = "healthSectionSummaryDocumentAssociationItemIndex", 
			required = true)
			final Integer healthSectionSummaryDocumentAssociationItemIndex,
			@RequestParam(value = "documentTagItemIndex", required = true)
			final Integer documentTagItemIndex) {
		ModelMap map = new ModelMap();
		DocumentTagItem item = new DocumentTagItem();
		item.setItemOperation(PresentenceInvestigationItemOperation.CREATE);
		map.addAttribute(
				DOCUMENT_TAG_ITEM_INDEX_MODEL_KEY, documentTagItemIndex);
		map.addAttribute(
				DOCUMENT_TAG_ITEM_MODEL_KEY, item);
		map.addAttribute(
				HEALTH_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_INDEX_MODEL_KEY, 
				healthSectionSummaryDocumentAssociationItemIndex);
		
		return new ModelAndView(DOCUMENT_TAG_ITEM_CONTENT_VIEW_NAME, map);
	}
	
	/**
	 * Returns a model and view of health section summary action menu.
	 *
	 * @param presentenceInvestigationRequest presentence investigation request
	 * @return model and view
	 */
	@RequestMapping(value = "/healthSectionSummaryActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView healthSectionSummaryActionMenu(@RequestParam(
			value = "presentenceInvestigationRequest", required = true)
			final PresentenceInvestigationRequest 
			presentenceInvestigationRequest) {
		ModelMap map = new ModelMap();
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY, 
				presentenceInvestigationRequest);
		return new ModelAndView(
				HEALTH_SECTION_SUMMARY_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Return model and view of health section summary note items action menu.
	 *
	 * @return model and view
	 */
	@RequestMapping(value = "/healthSectionSummaryNoteItemsActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView healthSectionSummaryNoteItemsActionMenu() {
		return new ModelAndView(
				HEALTH_SECTION_SUMMARY_NOTE_ITEMS_ACTION_MENU_VIEW_NAME);
	}
	
	/**
	 * Return model and view of health section summary document association
	 * items action menu.
	 *
	 * @return model and view
	 */
	@RequestMapping(value 
			= "/healthSectionSummaryDocumentAssociationItemsActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView 
	healthSectionSummaryDocumentAssociationItemsActionMenu() {
		return new ModelAndView(
				HEALTH_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEMS_MODEL_KEY);
	}
	
	/* Helper methods. */
	
	/**
	 * Prepares health section summary form for edit.
	 *
	 *
	 * @param summary summary 
	 * @param form form
	 * @return association
	 */
	private ModelAndView prepareEditMav(
			final PresentenceInvestigationRequest request,
			final HealthSectionSummaryForm form) {
		
		ModelMap map = new ModelMap();		
		HealthSectionSummary summary = this.healthSectionService
				.findHealthSectionSummaryByPresentenceInvestigationRequest(
						request);
		
		int documentIndex;
		List<Integer> tagIndexes = new ArrayList<Integer>();
		if (form.getHealthSectionSummaryDocumentAssociationItems() == null) {
			documentIndex = 0;
		} else {
			documentIndex 
			= form.getHealthSectionSummaryDocumentAssociationItems().size();
		}
		if (form.getHealthSectionSummaryDocumentAssociationItems() != null) {
			for (int i = 0; 
					i < form.getHealthSectionSummaryDocumentAssociationItems()
					.size(); i++) {
				if (form.getHealthSectionSummaryDocumentAssociationItems()
						.get(i) != null) {
					if (form.getHealthSectionSummaryDocumentAssociationItems()
							.get(i).getDocumentTagItems() != null) {
						tagIndexes.add(i, form
							.getHealthSectionSummaryDocumentAssociationItems()
							.get(i).getDocumentTagItems().size()) ;
					} else {			
						tagIndexes.add(i, 0);
					} 
				}
			}
		}
		int noteIndex;
		if (form.getHealthSectionSummaryNoteItems() == null) {
			noteIndex = 0;
		} else {
			noteIndex 
			= form.getHealthSectionSummaryNoteItems().size();
		}
		map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY, 
				request);
		map.addAttribute("ratings", HealthRating.values());
		this.offenderSummaryModelDelegate.add(map, (Offender) 
				request.getPerson());
		this.presentenceInvestigationRequestSummaryModelDelegate.add(
				map, request);
		map.addAttribute(HEALTH_SECTION_SUMMARY_MODEL_KEY, summary);
		map.addAttribute(HEALTH_SECTION_SUMMARY_FORM_MODEL_KEY, form);
		map.addAttribute(
				HEALTH_SECTION_SUMMARY_DOCUMENT_ASSOCIATION_ITEM_INDEX_MODEL_KEY, 
				documentIndex);
		map.addAttribute(DOCUMENT_TAG_ITEM_INDEXES_MODEL_KEY, tagIndexes);
		map.addAttribute(HEALTH_SECTION_SUMMARY_NOTE_ITEM_INDEX_MODEL_KEY, 
				noteIndex);	
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Redisplays model and view of health section summary form.
	 *
	 *
	 * @param summary summary
	 * @param form form
	 * @param result result
	 * @return model and view
	 */
	private ModelAndView prepareRedisplayMav(
			final PresentenceInvestigationRequest request,
			final HealthSectionSummaryForm form, final BindingResult result) {
		ModelAndView mav = this.prepareEditMav(request, form);
		mav.addObject(
				BindingResult
				.MODEL_KEY_PREFIX + HEALTH_SECTION_SUMMARY_FORM_MODEL_KEY, 
				result);
		return mav;
	}
	
	/**
	 * Populates the health section summary form.
	 *
	 * @param summary summary
	 * @return health section summary form
	 */
	private HealthSectionSummaryForm prepareForm(
			final PresentenceInvestigationRequest request) {	
		
		HealthSectionSummaryForm form = new HealthSectionSummaryForm();
		
		HealthSectionSummary summary = this.healthSectionService
				.findHealthSectionSummaryByPresentenceInvestigationRequest(
						request);
		
		if (summary != null) {
			form.setRating(summary.getRating());
		}		
		
		List<HealthSectionSummaryNote> notes 
		= this.healthSectionService
		.findHealthSectionSummaryNotesByHealthSectionSummary(summary);
		List<HealthSectionSummaryNoteItem> noteItems 
		= new ArrayList<HealthSectionSummaryNoteItem>();		
		
		for (HealthSectionSummaryNote note : notes) {
			HealthSectionSummaryNoteItem item 
			= new HealthSectionSummaryNoteItem();
			item.setDate(note.getDate());
			item.setDescription(note.getDescription());
			item.setHealthSectionSummaryNote(note);
			item.setItemOperation(PresentenceInvestigationItemOperation.UPDATE);			
			noteItems.add(item);
		}		
		List<HealthSectionSummaryDocumentAssociation> documents 
		= this.healthSectionService
		.findHealthSectionSummaryDocumentAssociationByHealthSummary(summary);
		List<HealthSectionSummaryDocumentAssociationItem> documentItems 
		= new ArrayList<HealthSectionSummaryDocumentAssociationItem>();
		for (HealthSectionSummaryDocumentAssociation document : documents) {
			HealthSectionSummaryDocumentAssociationItem item 
			= new HealthSectionSummaryDocumentAssociationItem();
			List<DocumentTagItem> tagItems = new ArrayList<DocumentTagItem>();
			List<DocumentTag> tags = this.healthSectionService
					.findDocumentTagsByDocument(document.getDocument());
			for (DocumentTag tag : tags) {
				DocumentTagItem tagItem = new DocumentTagItem();
				tagItem.setDocumentTag(tag);
				tagItem.setName(tag.getName());
				tagItem.setItemOperation(
						PresentenceInvestigationItemOperation.UPDATE);				
				tagItems.add(tagItem);
			}
			item.setDocument(document.getDocument());
			item.setDocumentTagItems(tagItems);
			item.setFileDate(document.getDocument().getDate());
			item.setFileExtension(document.getDocument().getFileExtension());
			item.setFilename(document.getDocument().getFilename());
			item.setTitle(document.getDocument().getTitle());
			item.setHealthSectionSummaryDocumentAssociation(document);
			item.setItemOperation(PresentenceInvestigationItemOperation.UPDATE);
			item.setData(this.presentenceInvestigationDocumentRetriever
					.retrieve(item.getDocument()));
			documentItems.add(item);
		}
		
		form.setHealthSectionSummaryDocumentAssociationItems(documentItems);
		form.setHealthSectionSummaryNoteItems(noteItems);
		
		return form;
	}
	
	/*
	 * Returns whether the health section summary note has different
	 * values than the supplied value and date.
	 * 
	 * @param note health section summary note
	 * @param date date
	 * @param value value
	 * @return whether note information is changed
	 */
	private boolean isNoteChanged(final HealthSectionSummaryNote note,
			final Date date, final String value) {
		if(!note.getDescription().equals(value)) {
			return true;
		}
		if(!(note.getDate().getTime() == date.getTime())) {
			return true;
		}
		return false;
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
		binder.registerCustomEditor(HealthSectionSummary.class, 
				this.healthSectionSummaryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(HealthSectionSummaryNote.class, 
				this.healthSectionSummaryNotePropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				HealthSectionSummaryDocumentAssociation.class, 
				this
				.healthSectionSummaryDocumentAssociationPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(PresentenceInvestigationRequest.class, 
				this.presentenceInvestigationRequestPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(Document.class, 
				this.documentPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(DocumentTag.class, 
				this.documentTagPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
	}
}