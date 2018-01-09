package omis.specialneed.web.controller;

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
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.document.domain.Document;
import omis.document.domain.DocumentTag;
import omis.document.io.DocumentPersister;
import omis.document.io.DocumentRetriever;
import omis.document.io.impl.DocumentFilenameGenerator;
import omis.document.web.form.DocumentTagItem;
import omis.document.web.form.DocumentTagOperation;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.io.FileRemover;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedAssociableDocument;
import omis.specialneed.domain.SpecialNeedAssociableDocumentCategory;
import omis.specialneed.domain.SpecialNeedCategory;
import omis.specialneed.domain.SpecialNeedClassification;
import omis.specialneed.domain.SpecialNeedNote;
import omis.specialneed.domain.SpecialNeedSource;
import omis.specialneed.service.SpecialNeedService;
import omis.specialneed.web.form.SpecialNeedAssociableDocumentItemOperation;
import omis.specialneed.web.form.SpecialNeedForm;
import omis.specialneed.web.form.SpecialNeedNoteItem;
import omis.specialneed.web.form.SpecialNeedNoteItemOperation;
import omis.specialneed.web.validator.SpecialNeedFormValidator;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Controller for special need.
 * 
 * @author Joel Norris
 * @author Sheronda Vaughn
 * @author Josh Divine
 * @version 0.1.2 (Nov 1, 2017)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/specialNeed")
@PreAuthorize("hasRole('USER')")
public class ManageSpecialNeedController {
	
	/* Redirect URLs. */
	
	private static final String LIST_REDIRECT_URL = 
			"redirect:list.html?offender=%d";
	
	/* View names */
	
	private static final String EDIT_VIEW_NAME = "specialNeed/edit";
	
	private static final String SPECIAL_NEED_NOTES_ACTION_MENU_VIEW_NAME = 
			"specialNeed/includes/specialNeedNotesActionMenu";
	
	private static final String SPECIAL_NEED_NOTE_ACTION_MENU_VIEW_NAME = 
			"specialNeed/includes/specialNeedNoteActionMenu";
	
	private static final String 
		SPECIAL_NEED_NOTE_TABLE_ROW_ACTION_MENU_VIEW_NAME = 
			"specialNeed/includes/specialNeedNoteTableRow";

	private static final String SPECIAL_NEED_ACTION_MENU_VIEW_NAME = 
			"specialNeed/includes/specialNeedActionMenu";

	private static final String SPECIAL_NEED_CATEGORY_OPTIONS_VIEW_NAME = 
			"specialneed/includes/specialNeedCategoryOptions";
	
	/* Model Keys. */
	
	private static final String CATEGORIES_MODEL_KEY = "categories";
	
	private static final String SPECIAL_NEED_NOTE_ITEM_MODEL_KEY = 
			"specialNeedNote";
	
	private static final String CURRENT_NOTE_INDEX_MODEL_KEY = 
			"currentNoteIndex";

	private static final String NOTE_INDEX_MODEL_KEY = "noteIndex";

	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String SPECIAL_NEED_FORM_MODEL_KEY = "specialNeedForm";
	
	private static final String SPECIAL_NEED_MODEL_KEY = "specialNeed";
	
	private static final String SOURCES_MODEL_KEY = "sources";
	
	private static final String CLASSIFICATION_MODEL_KEY = "classification";
	
	private static final String DOCUMENT_CATEGORIES_MODEL_KEY = 
			"documentCategories";
	
	private static final String SPECIAL_NEED_ASSOCIABLE_DOCUMENT_MODEL_KEY =
			"specialNeedAssociableDocument";
	
	/* Services. */
	
	@Autowired
	@Qualifier("specialNeedService")
	private SpecialNeedService specialNeedService;
	
	/* Property editor factories. */
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("specialNeedPropertyEditorFactory")
	private PropertyEditorFactory specialNeedPropertyEditorFactory;
	
	@Autowired
	@Qualifier("specialNeedNotePropertyEditorFactory")
	private PropertyEditorFactory specialNeedNotePropertyEditorFactory;
	
	@Autowired
	@Qualifier("sourcePropertyEditorFactory")
	private PropertyEditorFactory sourcePropertyEditorFactory;
	
	@Autowired
	@Qualifier("categoryPropertyEditorFactory")
	private PropertyEditorFactory categoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("specialNeedInstanceFactory")
	private InstanceFactory<SpecialNeed> specialNeedInstanceFactory;
	
	@Autowired
	@Qualifier("classificationPropertyEditorFactory")
	private PropertyEditorFactory classificationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("specialNeedAssociableDocumentPropertyEditorFactory")
	private PropertyEditorFactory 
			specialNeedAssociableDocumentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("specialNeedAssociableDocumentCategoryPropertyEditorFactory")
	private PropertyEditorFactory 
			specialNeedAssociableDocumentCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentPropertyEditorFactory")
	private PropertyEditorFactory documentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("documentTagPropertyEditorFactory")
	private PropertyEditorFactory documentTagPropertyEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("specialNeedFormValidator")
	private SpecialNeedFormValidator specialNeedFormValidator;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate businessExceptionHandlerDelegate;

	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("documentFilenameGenerator")
	private DocumentFilenameGenerator documentFilenameGenerator;
	
	@Autowired
	@Qualifier("specialNeedDocumentPersister")
	private DocumentPersister specialNeedDocumentPersister;
	
	@Autowired
	@Qualifier("specialNeedDocumentRetriever")
	private DocumentRetriever specialNeedDocumentRetriever;
	
	@Autowired
	@Qualifier("specialNeedDocumentRemover")
	private FileRemover specialNeedDocumentRemover;
	
	/* Bundles. */
	
	private static final String ERROR_BUNDLE_NAME = 
			"omis.specialneed.msgs.form";
	
	/* Message keys. */
	
	private static final String SPECIAL_NEED_EXISTS_MESSAGE_KEY = 
			"specialNeed.exists";
	
	private static final String SPECIAL_NEED_DATE_CONFLICT_MESSAGE_KEY = 
			"specialNeed.dateConflict";
	
	private static final String ERROR_WRITING_FILE_MSG = 
			"Error writing file: %s";
	
	/**
	 * Instantiates a default instance of special need controller.
	 */
	public ManageSpecialNeedController() {
		//Default constructor.
	}
	
	/**
	 * Displays the special need form for viewing/editing a 
	 * specified special need.
	 * @param specialNeed special need
	 * @return the model and view
	 */
	@RequestContentMapping(nameKey = "specialNeedEditScreenName",
			descriptionKey = "specialNeedEditScreenDescription",
			messageBundle = "omis.specialneed.msgs.specialNeed",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('SPECIAL_NEED_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(@RequestParam(value = "specialNeed", 
		required = true) final SpecialNeed specialNeed) {
		ModelMap map = new ModelMap();
		SpecialNeedForm form = new SpecialNeedForm();
		prepareSpecialNeedEditForm(form, specialNeed);
		map.addAttribute(SPECIAL_NEED_MODEL_KEY, specialNeed);
		int currentNoteIndex = 0;
		List<SpecialNeedNoteItem> noteItems
			= new ArrayList<SpecialNeedNoteItem>();
		List<SpecialNeedNote> notes = this.specialNeedService
				.findNotes(specialNeed);
		for (SpecialNeedNote note : notes) {
			SpecialNeedNoteItem item = new SpecialNeedNoteItem();
			item.setDate(note.getDate());
			item.setOperation(SpecialNeedNoteItemOperation.UPDATE);
			item.setSpecialNeed(note.getSpecialNeed());
			item.setSpecialNeedNote(note);
			item.setValue(note.getValue());
			noteItems.add(currentNoteIndex, item);
			currentNoteIndex++;
		}
		form.setSpecialNeedNotes(noteItems);
		SpecialNeedAssociableDocument associableDocument = this
				.specialNeedService
				.findSpecialNeedAssociableDocumentBySpecialNeed(specialNeed);
		if (associableDocument != null) {
			List<DocumentTag> documentTags = this.specialNeedService
					.findDocumentTagsByDocument(
							associableDocument.getDocument());
			List<DocumentTagItem> documentTagItems = 
					new ArrayList<DocumentTagItem>();
			for(DocumentTag tag : documentTags){
				DocumentTagItem item = new DocumentTagItem();
				item.setDocumentTag(tag);
				item.setName(tag.getName());
				item.setDocumentTagOperation(DocumentTagOperation.UPDATE);
				documentTagItems.add(item);
			}
			form.setDocumentCategory(associableDocument.getCategory());
			form.setDate(associableDocument.getDocument().getDate());
			form.setDocument(associableDocument.getDocument());
			form.setDocumentTagItems(documentTagItems);
			form.setFileExtension(associableDocument.getDocument()
					.getFileExtension());
			form.setTitle(associableDocument.getDocument().getTitle());
			form.setData(this.specialNeedDocumentRetriever.retrieve(
					associableDocument.getDocument()));
			map.addAttribute(SPECIAL_NEED_ASSOCIABLE_DOCUMENT_MODEL_KEY, 
					associableDocument);
			form.setDocumentItemOperation(
					SpecialNeedAssociableDocumentItemOperation.UPDATE);
		} else {
			form.setDocumentItemOperation(
					SpecialNeedAssociableDocumentItemOperation.CREATE);
		}
		return prepareEditMav(map, form, specialNeed.getOffender(), 
				specialNeed.getClassification(), currentNoteIndex);
	}
	
	/**
	 * Submits the changes to the specified special need.
	 * 
	 * @param form form
	 * @param specialNeed special need
	 * @param specialNeedClassification special need classification
	 * @param result result
	 * @return the model and view
	 * @throws DuplicateEntityFoundException 
	 */
	@RequestContentMapping(nameKey = "specialNeedUpdateName",
			descriptionKey = "specialNeedUpdateDescription",
			messageBundle = "omis.specialneed.msgs.specialNeed",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('SPECIAL_NEED_EDIT') or hasRole('ADMIN')")
	public ModelAndView update( 
			@RequestParam(value = "specialNeed", required = true)
			final SpecialNeed specialNeed, 
			@RequestParam(value = "specialNeedClassification", required = true)
			final SpecialNeedClassification classification,
			final SpecialNeedForm form, final BindingResult result)
		throws DuplicateEntityFoundException {
		this.specialNeedFormValidator.validate(form, result);
		int noteIndexCount;
		if (form.getSpecialNeedNotes() == null) {
			noteIndexCount = 0;
		} else {
			noteIndexCount = form.getSpecialNeedNotes().size();
		}
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(SPECIAL_NEED_MODEL_KEY, specialNeed);
			map.addAttribute(SPECIAL_NEED_ASSOCIABLE_DOCUMENT_MODEL_KEY, 
					this.specialNeedService
					.findSpecialNeedAssociableDocumentBySpecialNeed(
							specialNeed));
			return prepareEditMav(map, form, specialNeed.getOffender(), 
					classification, noteIndexCount);
		}
		SpecialNeed updatedSpecialNeed = this.specialNeedService.update(
				specialNeed, form.getComment(), form.getStartDate(), 
				form.getEndDate(), form.getCategory(),
				form.getSource(), form.getSourceComment());
		form.getClassification();
		processSpecialNeedNotes(form.getSpecialNeedNotes(), updatedSpecialNeed);
		SpecialNeedAssociableDocument associableDocument = this
				.specialNeedService
				.findSpecialNeedAssociableDocumentBySpecialNeed(
						updatedSpecialNeed);
		if (associableDocument != null) {
			if (SpecialNeedAssociableDocumentItemOperation.CREATE.equals(
					form.getDocumentItemOperation())) {
				removeDocument(associableDocument);
				if (form.getDocumentCategory() != null) {
					createSpecialNeedAssociableDocument(form.getTitle(), 
							form.getDate(), form.getFileExtension(), 
							form.getData(), form.getDocumentTagItems(), 
							form.getDocumentCategory(), updatedSpecialNeed);
				}
			}
			if (SpecialNeedAssociableDocumentItemOperation.UPDATE.equals(
					form.getDocumentItemOperation())) {
				Document document = this.specialNeedService.updateDocument(
						associableDocument.getDocument(), form.getDate(),
						form.getTitle());
				this.processDocumentTags(form.getDocumentTagItems(), document);
				this.specialNeedService.updateSpecialNeedAssociableDocument(
						associableDocument, updatedSpecialNeed, document, 
						form.getDocumentCategory());
			} else if (SpecialNeedAssociableDocumentItemOperation.REMOVE.equals(
					form.getDocumentItemOperation())) {
				removeDocument(associableDocument);
			}
			
		} else {
			if (form.getDocumentCategory() != null) {
				createSpecialNeedAssociableDocument(form.getTitle(), 
						form.getDate(), form.getFileExtension(), form.getData(), 
						form.getDocumentTagItems(), form.getDocumentCategory(), 
						updatedSpecialNeed);
			}
		}
		Long offenderId = specialNeed.getOffender().getId();
		return new ModelAndView(String.format(LIST_REDIRECT_URL, offenderId));
	}

	/**
	 * Displays the special need form for the creation of a new special need.
	 * 
	 * @param offender offender
	 * @param specialNeedClassification special need classification
	 * @return the model and view
	 */
	@RequestContentMapping(nameKey = "specialNeedCreateScreenName",
			descriptionKey = "specialNeedCreateScreenDescription",
			messageBundle = "omis.specialneed.msgs.specialNeed",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('SPECIAL_NEED_CREATE') or hasRole('ADMIN')")
	public ModelAndView createSpecialNeed(@RequestParam(value = "offender", 
		required = true) final Offender offender, 
			@RequestParam(value = "specialNeedClassification", required = true)
			final SpecialNeedClassification classification) {
		int noteIndex = 0;
		ModelMap map = new ModelMap();
		SpecialNeedForm form = new SpecialNeedForm();
		form.setClassification(classification);
		form.setDocumentItemOperation(
				SpecialNeedAssociableDocumentItemOperation.CREATE);
		return prepareEditMav(map, form, offender, classification, noteIndex);
	}
	
	/**
	 * Submits the creation of a new special need for the specified offender.
	 * 
	 * @param form special need form
	 * @param offender offender
	 * @param specialNeedClassification special need classification
	 * @param result binding result
	 * @return the model and view
	 * @throws DuplicateEntityFoundException 
	 */
	@RequestContentMapping(nameKey = "specialNeedSaveName",
			descriptionKey = "specialNeedSaveDescription",
			messageBundle = "omis.specialneed.msgs.specialNeed",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('SPECIAL_NEED_CREATE') or hasRole('ADMIN')")
	public ModelAndView saveSpecialNeed( 
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			final SpecialNeedForm form, final BindingResult result)
		throws DuplicateEntityFoundException {
		this.specialNeedFormValidator.validate(form, result);
		int noteIndexCount;
		if (form.getSpecialNeedNotes() == null) {
			noteIndexCount = 0;
		} else {
			noteIndexCount = form.getSpecialNeedNotes().size();
		}
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			return prepareEditMav(map, form, offender, 
					form.getClassification(), noteIndexCount);			
		}
		SpecialNeed specialNeed = this.specialNeedService.create(
				form.getComment(), form.getStartDate(), form.getEndDate(),
				form.getClassification(), form.getCategory(), form.getSource(), 
				form.getSourceComment(), offender);
		processSpecialNeedNotes(form.getSpecialNeedNotes(), specialNeed);
		if (form.getDocumentCategory() != null) {
			createSpecialNeedAssociableDocument(form.getTitle(), form.getDate(),
					form.getFileExtension(), form.getData(), 
					form.getDocumentTagItems(), form.getDocumentCategory(), 
					specialNeed);
		}
		Long offenderId = specialNeed.getOffender().getId();
		return new ModelAndView(String.format(LIST_REDIRECT_URL, offenderId));
	}
	
	/**
	 * Removes the specified special need.
	 * 
	 * @param specialNeed special need
	 * @return redirect string
	 */
	@RequestContentMapping(nameKey = "specialNeedRemoveName",
			descriptionKey = "specialNeedRemoveDescription",
			messageBundle = "omis.specialneed.msgs.specialNeed",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('SPECIAL_NEED_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(@RequestParam(value = "specialNeed", 
		required = true) final SpecialNeed specialNeed) {
		List<SpecialNeedNote> specialNeedNotes 
			= this.specialNeedService.findNotes(specialNeed);
		for (SpecialNeedNote specialNeedNote : specialNeedNotes) {
			this.specialNeedService.removeNote(specialNeedNote);
		}
		Long offenderId = specialNeed.getOffender().getId();
		this.specialNeedService.remove(specialNeed);
		return new ModelAndView(String.format(LIST_REDIRECT_URL, offenderId));
	}

	/**
	 * Displays the special need category options based on the classification.
	 *
	 * @param classification classification
	 * @return special need categories
	 */
	@RequestMapping(value = "/showSpecialNeedCategoryOptions.html",
			method = RequestMethod.GET)
	public ModelAndView showSpecialNeedCategoryOptions(
			@RequestParam(value = "specialNeedClassification", required = true) 
			final SpecialNeedClassification classification) {
		ModelMap map = new ModelMap();
		map.addAttribute(CATEGORIES_MODEL_KEY, 
				this.specialNeedService.findCategories(classification));
		return new ModelAndView(
				SPECIAL_NEED_CATEGORY_OPTIONS_VIEW_NAME, map);
	}
	/**
	 * Displays the special need action menu.
	 * 
	 * @return model and view for special needs action menu
	 */
	@RequestMapping(value = "specialNeedActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView specialNeedActionMenu(
			@RequestParam(value = "offender",required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(SPECIAL_NEED_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns a view of the special need notes action menu.
	 * 
	 * @return model and view
	 */
	@RequestMapping(value = "/specialNeedNotesActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView specialNeedNotesActionMenu() {
		ModelMap map = new ModelMap();
		return new ModelAndView(SPECIAL_NEED_NOTES_ACTION_MENU_VIEW_NAME, map);	
	}
	
	/**
	 * Returns a view of the special need note action menu.
	 * 
	 * @param specialNeed special need
	 * @return model and view
	 */
	@RequestMapping(value = "/specialNeedNoteActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView specialNeedNoteActionMenu(
			@RequestParam(value = "specialNeed", required = true)
				final SpecialNeed specialNeed) {
		ModelMap map = new ModelMap();
		map.addAttribute(SPECIAL_NEED_MODEL_KEY, specialNeed);
		return new ModelAndView(SPECIAL_NEED_NOTE_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Creates a special need note to the special need in view.
	 * 
	 * @param noteIndex note index
	 * @return model and view to current special need form
	 */
	@RequestContentMapping(nameKey = "createSpecialNeedNoteName",
				descriptionKey = "createSpecialNeedNoteDescription", 
				messageBundle = "omis.specialneed.msgs.specialNeed",
				screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "createSpecialNeedNote.html",
				method = RequestMethod.GET)
	public ModelAndView createSpecialNeedNote(
			@RequestParam(value = "noteIndex", required = true)
			final int noteIndex) {		
		SpecialNeedNoteItem item = new SpecialNeedNoteItem();
		item.setOperation(SpecialNeedNoteItemOperation.CREATE);		
		ModelAndView mav = new ModelAndView(
				SPECIAL_NEED_NOTE_TABLE_ROW_ACTION_MENU_VIEW_NAME);	
		mav.addObject(SPECIAL_NEED_NOTE_ITEM_MODEL_KEY, item);
		mav.addObject(NOTE_INDEX_MODEL_KEY, noteIndex);
		return mav;
	}

	/* Exception handler methods. */
	
	/**
	 * Handles duplicate entity found exceptions.
	 * 
	 * @param exception duplicate entity found exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(DuplicateEntityFoundException.class)
	public ModelAndView handleDuplicateEntityFoundException(
			final DuplicateEntityFoundException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				SPECIAL_NEED_EXISTS_MESSAGE_KEY, ERROR_BUNDLE_NAME, 
				exception);
	}
	
	/**
	 * Handles date conflict exceptions.
	 * 
	 * @param exception date conflict exception
	 * @return model and view for displaying exception explanation
	 */
	@ExceptionHandler(DateConflictException.class)
	public ModelAndView handleDateConflictException(
			final DateConflictException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
				SPECIAL_NEED_DATE_CONFLICT_MESSAGE_KEY, 
				ERROR_BUNDLE_NAME, exception);
	}
	
	/* Helper methods. */
	
	/*
	 * Prepares the values for categories and sources drop down lists and
	 * returns a Model Map.
	*/
	private ModelMap prepareDropDownListSpecialNeed(final ModelMap map) {
		map.addAttribute(SOURCES_MODEL_KEY, this.specialNeedService
				.findSources());
		return map;
	}
	
	/*
	 * Prepares the model and view of the edit screen with the necessary model
	 * map values.
	*/
	private ModelAndView prepareEditMav(final ModelMap map, 
			final SpecialNeedForm form, final Offender offender, 
			final SpecialNeedClassification classification, 
			final int noteIndex) {		
		map.addAttribute(SPECIAL_NEED_FORM_MODEL_KEY, form);
	    this.prepareDropDownListSpecialNeed(map);		    
	    map.addAttribute(CLASSIFICATION_MODEL_KEY, classification);  
	    map.addAttribute(CATEGORIES_MODEL_KEY, this.specialNeedService
				.findCategories(classification));			    
		map.addAttribute(OFFENDER_MODEL_KEY, offender);		
		map.addAttribute(CURRENT_NOTE_INDEX_MODEL_KEY, noteIndex);
		map.addAttribute(DOCUMENT_CATEGORIES_MODEL_KEY, this.specialNeedService
				.findSpecialNeedAssociableDocumentCategories());
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/*
	 * Sets the known values of the specified Special Need into the 
	 * corresponding special need form.
	*/
	private void prepareSpecialNeedEditForm(final SpecialNeedForm form, 
			final SpecialNeed specialNeed) {
		form.setComment(specialNeed.getComment());
		form.setStartDate(specialNeed.getDateRange().getStartDate());
		form.setEndDate(specialNeed.getDateRange().getEndDate());
		form.setCategory(specialNeed.getCategory());
		form.setSource(specialNeed.getSource());
		form.setSourceComment(specialNeed.getSourceComment());
		form.setClassification(specialNeed.getClassification());
		
		SpecialNeedAssociableDocument associableDocument = 
				this.specialNeedService
				.findSpecialNeedAssociableDocumentBySpecialNeed(specialNeed);
		if (associableDocument != null) {
			Document document = associableDocument.getDocument();
			List<DocumentTag> documentTags = this.specialNeedService
					.findDocumentTagsByDocument(document);
			List<DocumentTagItem> documentTagItems = 
					new ArrayList<DocumentTagItem>();
			for(DocumentTag tag : documentTags){
				DocumentTagItem item = new DocumentTagItem();
				item.setDocumentTag(tag);
				item.setName(tag.getName());
				item.setDocumentTagOperation(DocumentTagOperation.UPDATE);
				documentTagItems.add(item);
			}
			
			form.setDocumentCategory(associableDocument.getCategory());
			form.setDate(associableDocument.getDocument().getDate());
			form.setDocument(associableDocument.getDocument());
			form.setDocumentTagItems(documentTagItems);
			form.setFileExtension(associableDocument.getDocument()
					.getFileExtension());
			form.setTitle(associableDocument.getDocument().getTitle());
			form.setData(this.specialNeedDocumentRetriever.retrieve(document));
		}
	}
	

	/**
	 * Processes a list of documentTag items for creation, updating, or removal.
	 * 
	 * @param documentTagItems document tag items
	 * @param document document
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	private void processDocumentTags(
			final List<DocumentTagItem> documentTagItems, 
			final Document document) throws DuplicateEntityFoundException {
		if(documentTagItems != null){
			Iterator<DocumentTagItem> documentTagIterator = 
					documentTagItems.iterator();
			while (documentTagIterator.hasNext()) {
				final DocumentTagItem documentTagItem = documentTagIterator.next();
				final DocumentTagOperation documentTagOperation = 
						documentTagItem.getDocumentTagOperation();
				if (documentTagOperation == DocumentTagOperation.UPDATE) {
					this.specialNeedService.updateDocumentTag(
							documentTagItem.getDocumentTag(), 
							documentTagItem.getName());
				} else if (documentTagOperation == DocumentTagOperation.CREATE) {
					this.specialNeedService.createDocumentTag(
							documentTagItem.getName(), document);
				} else if (documentTagOperation == DocumentTagOperation.REMOVE) {
					this.specialNeedService.removeDocumentTag(
							documentTagItem.getDocumentTag());
				}
			}
		}
	}
	
	/**
	 * Processes special need notes.
	 * 
	 * @param specialNeedNotes special need notes
	 * @param specialNeed special need
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	private void processSpecialNeedNotes(
			final List<SpecialNeedNoteItem> specialNeedNotes, 
			final SpecialNeed specialNeed)
			throws DuplicateEntityFoundException {
		for (SpecialNeedNoteItem specialNeedNoteItem : specialNeedNotes) {
			if (SpecialNeedNoteItemOperation.UPDATE.equals(
					specialNeedNoteItem.getOperation())) {
				this.specialNeedService.updateNote(
						specialNeedNoteItem.getSpecialNeedNote(), 
						specialNeedNoteItem.getDate(), 
						specialNeedNoteItem.getSpecialNeed(),
						specialNeedNoteItem.getValue());
			} else if (SpecialNeedNoteItemOperation.CREATE.equals(
					specialNeedNoteItem.getOperation())) {				
				this.specialNeedService.createNote(
						specialNeed, specialNeedNoteItem.getDate(), 
						specialNeedNoteItem.getValue());
			} else if (SpecialNeedNoteItemOperation.REMOVE.equals(
					specialNeedNoteItem.getOperation())) {
				this.specialNeedService.removeNote(
						specialNeedNoteItem.getSpecialNeedNote());
			} else {
				throw new UnsupportedOperationException(
						"Operation not supported: " 
								+ specialNeedNoteItem.getOperation());
			}
		}
	}
	
	/**
	 * Creates a new special need associable document.
	 *  
	 * @param title title
	 * @param date date
	 * @param fileExtension file extension
	 * @param data data
	 * @param documentTagItems document tag items
	 * @param category special need associable document category
	 * @param specialNeed special need
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	private void createSpecialNeedAssociableDocument(final String title, 
			final Date date, final String fileExtension, final byte[] data, 
			final List<DocumentTagItem> documentTagItems, 
			final SpecialNeedAssociableDocumentCategory category, 
			final SpecialNeed specialNeed)
			throws DuplicateEntityFoundException {
		this.documentFilenameGenerator.setExtension(fileExtension);
		String filename = this.documentFilenameGenerator.generate();
		Document document = this.specialNeedService.createDocument(date, 
				title, filename, fileExtension);
		this.specialNeedDocumentPersister.persist(document, data);
		this.processDocumentTags(documentTagItems, document);
		this.specialNeedService.createSpecialNeedAssociableDocument(specialNeed,
				document, category);
	}
	
	/**
	 * Removes the specified association and underlying document.
	 * 
	 * @param associableDocument special need associable document
	 */
	private void removeDocument(
			final SpecialNeedAssociableDocument associableDocument) {
		Document document = associableDocument.getDocument();
		List<DocumentTag> documentTags = this.specialNeedService
				.findDocumentTagsByDocument(document);
		for (DocumentTag documentTag : documentTags) {
			this.specialNeedService.removeDocumentTag(documentTag);	
		}
		this.specialNeedService.removeSpecialNeedAssociableDocument(
				associableDocument);
		this.specialNeedDocumentRemover.remove(document.getFilename());
		this.specialNeedService.removeDocument(document);
	}
	
	/** 
	 * Retrieves document file.
	 * 
	 * @param document document
	 * @param httpServletResponse HTTP Servlet response
	 */
	@RequestMapping(value = "retrieveFile.html", method = RequestMethod.GET)
	@PreAuthorize("(hasRole('DOCUMENT_VIEW') and hasRole('USER')) "
			+ "or hasRole('ADMIN')")
	public void retrieveFile(
			@RequestParam(value = "document", required = true) 
			final Document document, 
			final HttpServletResponse httpServletResponse) {
		final byte[] bytes = this.specialNeedDocumentRetriever.retrieve(
				document);
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
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(SpecialNeed.class,
				this.specialNeedPropertyEditorFactory.createPropertyEditor());
		
		binder.registerCustomEditor(SpecialNeedCategory.class, 
				this.categoryPropertyEditorFactory.createPropertyEditor());
		
		binder.registerCustomEditor(SpecialNeedClassification.class, 
				this.classificationPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(SpecialNeedSource.class, 
				this.sourcePropertyEditorFactory.createPropertyEditor());
		
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		
		binder.registerCustomEditor(SpecialNeedNote.class,
				this.specialNeedNotePropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(SpecialNeedAssociableDocument.class,
				this.specialNeedAssociableDocumentPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(SpecialNeedAssociableDocumentCategory.class,
				this.specialNeedAssociableDocumentCategoryPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(Document.class, 
				this.documentPropertyEditorFactory.createPropertyEditor());
		
		binder.registerCustomEditor(DocumentTag.class, 
				this.documentTagPropertyEditorFactory.createPropertyEditor());
		
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
}