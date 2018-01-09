package omis.physicalfeature.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.exception.DuplicateEntityFoundException;
import omis.io.FileRemover;
import omis.io.FilenameGenerator;
import omis.media.domain.Photo;
import omis.media.io.PhotoPersister;
import omis.media.io.PhotoRetriever;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.physicalfeature.domain.FeatureClassification;
import omis.physicalfeature.domain.PhysicalFeature;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;
import omis.physicalfeature.domain.PhysicalFeatureAssociationNote;
import omis.physicalfeature.domain.PhysicalFeaturePhotoAssociation;
import omis.physicalfeature.service.PhysicalFeatureAssociationService;
import omis.physicalfeature.validator.PhysicalFeatureAssociationFormValidator;
import omis.physicalfeature.web.form.PhotoItemOperation;
import omis.physicalfeature.web.form.PhysicalFeatureAssociationForm;
import omis.physicalfeature.web.form.PhysicalFeatureAssociationNoteItem;
import omis.physicalfeature.web.form.PhysicalFeatureAssociationNoteItemOperation;
import omis.physicalfeature.web.form.PhysicalFeaturePhotoItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for distinguishing physical features.
 * 
 * @author Joel Norris
 * @author Sheronda Vaughn
 * @version 0.1.1 (Sep 07, 2016)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/physicalFeature")
@PreAuthorize("hasRole('USER')")
public class PhysicalFeatureController {

	/* Redirect URLs. */
	
	private static final String LIST_REDIRECT = "redirect:list.html?offender=";
	
	/* View names. */	
	
	private static final String EDIT_VIEW_NAME = "physicalFeature/edit";
	
	private static final String PHYSICAL_FEATURE_PHOTO_ROW_VIEW_NAME =
			"physicalFeature/includes/physicalFeaturePhotoTableRow";
	
	private static final String FEATURE_CONTENT_VIEW_NAME =
			"physicalFeature/includes/featureContent";
	
	private static final String
	PHYSICAL_FEATURE_ASSOCIATION_NOTE_ITEMS_ACTION_MENU_VIEW_NAME
	= "physicalFeature/includes/physicalFeatureAssociationNoteItemsActionMenu";
	
	private static final String 
	PHYSICAL_FEATURE_ASSOCIATION_NOTE_ITEM_VIEW_NAME
	= "physicalFeature/includes/physicalFeatureAssociationNoteItemTableRow";
	
	private static final String 
	PHYSICAL_FEATURE_ASSOCIATION_ACTION_MENU_VIEW_NAME
	= "physicalFeature/includes/physicalFeatureAssociationActionMenu"; 

	private static final String 
	PHYSICAL_FEATURE_ASSOCIATION_PHOTO_ITEMS_ACTION_MENU_VIEW_NAME
	= "physicalFeature/includes/physicalFeatureAssociationPhotoItemsActionMenu";
	
	private static final String
	PHYSICAL_FEATURE_ASSOCIATIONS_ACTION_MENU_VIEW_NAME
		= "physicalFeature/includes/physicalFeatureAssociationsActionMenu";
	
	private static final String
	PHYSICAL_FEATURE_ASSOCIATIONS_ROW_ITEM_ACTION_MENU_VIEW_NAME
	= "physicalFeature/includes/physicalFeatureAssociationsRowItemActionMenu";
	
	/* Model Keys. */
	
	private static final String PHYSICAL_FEATURES_MODEL_KEY = 
			"physicalFeatures";
	
	private static final String PHYSICAL_FEATURE_ASSOCIATION_FORM_MODEL_KEY = 
			"physicalFeatureAssociationForm";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String PHYSICAL_FEATURE_PHOTO_INDEX_MODEL_KEY = 
			"physicalFeaturePhotoIndex";
	
	private static final String CURRENT_PHYSICAL_FEATURE_PHOTO_INDEX_MODEL_KEY =
			"currentPhysicalFeaturePhotoIndex";
	
	private static final String 
	CURRENT_PHYSICAL_FEATURE_NOTE_ITEM_INDEX_MODEL_KEY
		= "currentPhysicalFeatureAssociationNoteItemIndex";
	
	private static final String PHYSICAL_FEATURE_ASSOCIATION_MODEL_KEY =
			"physicalFeatureAssociation";
	
	private static final String FEATURE_CLASSIFICATIONS_MODEL_KEY = 
			"featureClassifications";
	
	private static final String PHYSICAL_FEATURE_NOTE_ITEM_INDEX_MODEL_KEY 
		= "physicalFeatureAssociationNoteItemIndex";
	
	private static final String PHYSICAL_FEATURE_ASSOCIATION_NOTE_ITEM_MODEL_KEY
		= "physicalFeatureAssociationNoteItem";
	
	private static final String PHOTO_ITEM_MODEL_KEY = "photoItem";
	
	private static final String OTHER_PHOTOS_COUNT_MODEL_KEY
		= "otherPhotosCount";
	
	/* Services. */
	
	@Autowired
	@Qualifier("physicalFeatureAssociationService")
	private PhysicalFeatureAssociationService physicalFeatureAssociationService;
	
	/*	Validators */
	
	@Autowired
	@Qualifier("physicalFeatureAssociationFormValidator")
	private PhysicalFeatureAssociationFormValidator 
	physicalFeatureAssociationFormValidator;
	
	/* File Retrievers */
	
	@Autowired
	@Qualifier("physicalFeatureAssociationPhotoRetriever")
	private PhotoRetriever physicalFeatureAssociationPhotoRetriever;
	
	/* File Persisters */
	
	@Autowired
	@Qualifier("physicalFeatureAssociationPhotoPersister")
	private PhotoPersister physicalFeatureAssociationPhotoPersister;
	
	/* File Remover */
	
	@Autowired
	@Qualifier("physicalFeatureAssociationPhotoRemover")
	private FileRemover physicalFeatureAssociationPhotoRemover;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("featureClassificationPropertyEditorFactory")
	private PropertyEditorFactory featureClassificationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("physicalFeaturePropertyEditorFactory")
	private PropertyEditorFactory physicalFeaturePropertyEditorFactory;
	
	@Autowired
	@Qualifier("physicalFeatureAssociationPropertyEditorFactory")
	private PropertyEditorFactory 
	physicalFeatureAssociationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("physicalFeaturePhotoAssociationPropertyEditorFactory")
	private PropertyEditorFactory 
	physicalFeaturePhotoAssociationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("photoPropertyEditorFactory")
	private PropertyEditorFactory photoPropertyEditorFactory; 
	
	@Autowired
	@Qualifier("physicalFeatureAssociationNotePropertyEditorFactory")
	private PropertyEditorFactory
	physicalFeatureAssociationNotePropertyEditorFactory;


	/* Helpers. */
	
	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("photoFilenameGenerator")
	private FilenameGenerator photoFilenameGenerator;
	
	/**
	 * Instantiates a default instance of physical feature controller.
	 */
	public PhysicalFeatureController() {
		//Default constructor.
	}
		
	/**
	 * Displays the physical feature association form for the purpose of 
	 * creating a new physical feature association.
	 * 
	 * @param offender offender
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "scarsMarksCreateScreenName",
			descriptionKey = "scarsMarksCreateScreenDescription",
			messageBundle = "omis.physicalfeature.msgs.physicalfeature",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PHYSICAL_FEATURE_CREATE') or hasRole('ADMIN')")
	public ModelAndView create(@RequestParam(value = "offender", 
			required = true) final Offender offender) {
		ModelMap map = new ModelMap();
		PhysicalFeatureAssociationForm form = 
				new PhysicalFeatureAssociationForm();
		int currentPhysicalFeaturePhotoIndex = 0;
		int currentPhysicalFeatureAssociationNoteItemIndex = 0;
		map.addAttribute(CURRENT_PHYSICAL_FEATURE_PHOTO_INDEX_MODEL_KEY,
				currentPhysicalFeaturePhotoIndex);
		map.addAttribute(CURRENT_PHYSICAL_FEATURE_NOTE_ITEM_INDEX_MODEL_KEY,
				currentPhysicalFeatureAssociationNoteItemIndex);
		return this.prepareEditMav(map, offender, form);
	}
	
	/**
	 * Saves a new physical feature association for the specified offender with
	 * values from the specified physical feature association form.
	 * 
	 * @param form physical feature association form
	 * @param offender offender
	 * @param result binding result
	 * @return model and view
	 * @throws DuplicateEntityFoundException 
	 */
	@RequestContentMapping(nameKey = "scarsMarksSaveName",
			descriptionKey = "scarsMarksSaveDescription",
			messageBundle = "omis.physicalfeature.msgs.physicalfeature",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PHYSICAL_FEATURE_CREATE') or hasRole('ADMIN')")
	public ModelAndView save(final PhysicalFeatureAssociationForm form, 
			@RequestParam(value = "offender", required = true)
			final Offender offender, final BindingResult result) 
		throws DuplicateEntityFoundException {
		this.physicalFeatureAssociationFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX 
					+ PHYSICAL_FEATURE_ASSOCIATION_FORM_MODEL_KEY, result);
			final int currentPhysicalFeaturePhotoIndex;
			if (form.getPhotoItems() == null) {
				currentPhysicalFeaturePhotoIndex = 0;
			} else {
				currentPhysicalFeaturePhotoIndex = form.getPhotoItems().size();
			}
			final int currentPhysicalFeatureAssociationNoteItemIndex;
			if (form.getNoteItems() == null) {
				currentPhysicalFeatureAssociationNoteItemIndex = 0;
			} else {
				currentPhysicalFeatureAssociationNoteItemIndex 
					= form.getNoteItems().size();
			}
			map.addAttribute(CURRENT_PHYSICAL_FEATURE_PHOTO_INDEX_MODEL_KEY,
					currentPhysicalFeaturePhotoIndex);
			map.addAttribute(CURRENT_PHYSICAL_FEATURE_NOTE_ITEM_INDEX_MODEL_KEY,
					currentPhysicalFeatureAssociationNoteItemIndex);
			this.offenderSummaryModelDelegate.add(map, offender);
			return this.prepareEditMav(map, offender, form);
		}
		PhysicalFeatureAssociation pFAssociation = this
				.physicalFeatureAssociationService.create(form.getFeature(),
						form.getDescription(), form.getOriginationDate(), 
								offender);
		this.processPhotoItems(form.getPhotoItems(), pFAssociation);
		this.processPhysicalFeatureAssociationNoteItems(form.getNoteItems(),
				pFAssociation);
		return new ModelAndView(LIST_REDIRECT + offender.getId());
	}
	
	/**
	 * Displays the physical feature association form for the purpose of editing
	 * an existing physical feature association.
	 * 
	 * @param pFAssociation physical feature association
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "scarsMarksEditScreenName",
			descriptionKey = "scarsMarksEditScreenDescription",
			messageBundle = "omis.physicalfeature.msgs.physicalfeature",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PHYSICAL_FEATURE_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(@RequestParam(value = "physicalFeatureAssociation",
			required = true)final PhysicalFeatureAssociation pFAssociation) {
		ModelMap map = new ModelMap();
		PhysicalFeatureAssociationForm form = 
				new PhysicalFeatureAssociationForm();
		List<PhysicalFeaturePhotoAssociation> physicalFeaturePhotoAssociations 
			= this.physicalFeatureAssociationService
			.findPhotoAssociationsByPhysicalFeatureAssociation(pFAssociation);
		List<PhysicalFeaturePhotoItem> photoItems
			= new ArrayList<PhysicalFeaturePhotoItem>();
		int currentPhysicalFeaturePhotoIndex = 0;
		if (physicalFeaturePhotoAssociations.size() != 0) {
			currentPhysicalFeaturePhotoIndex = physicalFeaturePhotoAssociations
					.size();
			int count = 0;
			for (PhysicalFeaturePhotoAssociation photoAssociation 
					: physicalFeaturePhotoAssociations) {
				PhysicalFeaturePhotoItem newItem
					= new PhysicalFeaturePhotoItem();
				newItem.setPhoto(photoAssociation.getPhoto());
				newItem.setDate(photoAssociation.getPhoto().getDate());
				newItem.setOperation(PhotoItemOperation.UPDATE);
				newItem.setPhotoAssociation(photoAssociation);
				photoItems.add(count, newItem);
				count++;
			}
			form.setPhotoItems(photoItems);
		}
		List<PhysicalFeatureAssociationNote> notes 
			= this.physicalFeatureAssociationService
			.findNotesByPhysicalFeatureAssociation(pFAssociation);
		List<PhysicalFeatureAssociationNoteItem> noteItems 
			= new ArrayList<PhysicalFeatureAssociationNoteItem>();
		int currentPhysicalFeatureAssociationNoteItemIndex = 0;
		if (notes.size() != 0) {
			currentPhysicalFeatureAssociationNoteItemIndex = notes.size();
			int count = 0;
			for (PhysicalFeatureAssociationNote note : notes) {
				PhysicalFeatureAssociationNoteItem newItem
					= new PhysicalFeatureAssociationNoteItem();
				newItem.setNote(note.getNote());
				newItem.setDate(note.getDate());
				newItem.setOperation(
						PhysicalFeatureAssociationNoteItemOperation.UPDATE);
				newItem.setPhysicalFeatureAssociationNote(note);
				noteItems.add(count, newItem);
				count++;
			}
			form.setNoteItems(noteItems);
		}
		map.addAttribute(CURRENT_PHYSICAL_FEATURE_PHOTO_INDEX_MODEL_KEY,
				currentPhysicalFeaturePhotoIndex);
		map.addAttribute(CURRENT_PHYSICAL_FEATURE_NOTE_ITEM_INDEX_MODEL_KEY,
				currentPhysicalFeatureAssociationNoteItemIndex);
		Offender offender = pFAssociation.getOffender();
		this.preparePFAssociationForm(form, pFAssociation);
		map.addAttribute(PHYSICAL_FEATURE_ASSOCIATION_MODEL_KEY, pFAssociation);
		return this.prepareEditMav(map, offender, form);
	}
	
	/**
	 * Updates an existing physical feature association with information from 
	 * the specified physical feature association form.
	 * 
	 * @param pFAssociation physical feature association
	 * @param form physical feature association form
	 * @param result binding result
	 * @return model and view
	 * @throws DuplicateEntityFoundException 
	 */
	@RequestContentMapping(nameKey = "scarsMarksUpdateName",
			descriptionKey = "scarsMarksUpdateDescription",
			messageBundle = "omis.physicalfeature.msgs.physicalfeature",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PHYSICAL_FEATURE_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "physicalFeatureAssociation", required = true)
				final PhysicalFeatureAssociation pFAssociation,
			final PhysicalFeatureAssociationForm form, 
			final BindingResult result) throws DuplicateEntityFoundException {
		this.physicalFeatureAssociationFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX 
					+ PHYSICAL_FEATURE_ASSOCIATION_FORM_MODEL_KEY, result);
			final int currentPhysicalFeaturePhotoIndex;
			if (form.getPhotoItems() == null) {
				currentPhysicalFeaturePhotoIndex = 0;
			} else {
				currentPhysicalFeaturePhotoIndex = form.getPhotoItems().size();
			}
			final int currentPhysicalFeatureAssociationNoteItemIndex;
			if (form.getNoteItems() == null) {
				currentPhysicalFeatureAssociationNoteItemIndex = 0;
			} else {
				currentPhysicalFeatureAssociationNoteItemIndex 
					= form.getNoteItems().size();
			}
			map.addAttribute(CURRENT_PHYSICAL_FEATURE_PHOTO_INDEX_MODEL_KEY,
					currentPhysicalFeaturePhotoIndex);
			map.addAttribute(CURRENT_PHYSICAL_FEATURE_NOTE_ITEM_INDEX_MODEL_KEY,
					currentPhysicalFeatureAssociationNoteItemIndex);
			map.addAttribute(PHYSICAL_FEATURE_ASSOCIATION_MODEL_KEY, 
					pFAssociation);
			return this.prepareEditMav(map, pFAssociation.getOffender(), form);
		}
		this.physicalFeatureAssociationService.update(pFAssociation, 
				form.getFeature(), form.getDescription(),
				form.getOriginationDate(), pFAssociation.getOffender());
		this.processPhysicalFeatureAssociationNoteItems(form.getNoteItems(),
				pFAssociation);
		this.processPhotoItems(form.getPhotoItems(), pFAssociation);

		return new ModelAndView(LIST_REDIRECT + pFAssociation.getOffender()
			.getId());
	}
	
	/**
	 * Removes the specified physical feature association (and all associated 
	 * physical feature photo associations), then supplies the model and view 
	 * required to re-display the physical feature association list table via 
	 * ajax.
	 * 
	 * @param pFAssociation physical feature association
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "scarsMarksRemoveName",
			descriptionKey = "scarsMarksRemoveDescription",
			messageBundle = "omis.physicalfeature.msgs.physicalfeature",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PHYSICAL_FEATURE_REMOVE') or hasRole('ADMIN')")
	public String remove(@RequestParam(value = "physicalFeatureAssociation",
			required = true)final PhysicalFeatureAssociation pFAssociation) {
		Offender offender = pFAssociation.getOffender();
		List<PhysicalFeaturePhotoAssociation> pFPhotoAssociations = this
				.physicalFeatureAssociationService
				.findPhotoAssociationsByFeatureAssociation(pFAssociation);
		for (PhysicalFeaturePhotoAssociation pAssoc : pFPhotoAssociations) {
			this.physicalFeatureAssociationService
				.removePhysicalFeaturePhotoAssociation(pAssoc);
			this.physicalFeatureAssociationService
				.removePhoto(pAssoc.getPhoto());
			this.physicalFeatureAssociationPhotoRemover.remove(
					pAssoc.getPhoto().getFilename());
		}
		this.physicalFeatureAssociationService.remove(pFAssociation);
		return LIST_REDIRECT + offender.getId();
	}
	
	/**
	 * Ajax request that adds a physical feature photo to the physical feature 
	 * association form.
	 * 
	 * @param physicalFeaturePhotoIndex 
	 * physical feature association photo index
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "addPhysicalFeaturePhotoName",
			descriptionKey = "addPhysicalFeaturePhotoDescription",
			messageBundle = "omis.physicalfeature.msgs.physcialfeature",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/addPhysicalFeaturePhoto.html", 
			method = RequestMethod.GET)
	public ModelAndView addPhysicalFeaturePhoto(@RequestParam(
			value = "offenderPhysicalFeaturePhotoIndex", required = true)
			final int physicalFeaturePhotoIndex) {
		ModelMap map = new ModelMap();
		PhysicalFeaturePhotoItem photoItem = new PhysicalFeaturePhotoItem();
		photoItem.setOperation(PhotoItemOperation.CREATE);
		map.addAttribute(PHOTO_ITEM_MODEL_KEY, photoItem);
		map.addAttribute(PHYSICAL_FEATURE_PHOTO_INDEX_MODEL_KEY,
				physicalFeaturePhotoIndex);
		return new ModelAndView(PHYSICAL_FEATURE_PHOTO_ROW_VIEW_NAME,
				map);
	}
	
	/**
	 * Displays the photo data of the specified content type for the specified
	 * photo.
	 * 
	 * @param photo photo
	 * @param contentType content type
	 * @return response entity byte array
	 */
	@RequestMapping(value = "/displayPhoto.html")
	public ResponseEntity<byte[]> displayPhysicalFeauturePhoto(
			@RequestParam(value = "photo", 
			required = true) final Photo photo, 
			@RequestParam(value = "contentType", required = true) 
			final String contentType) {
		byte[] photoData;
		if (photo != null) {
			photoData = this.physicalFeatureAssociationPhotoRetriever
					.retrieve(photo);	
		} else {
			photoData = this.physicalFeatureAssociationPhotoRetriever
					.retrieve("NotSet.jpg");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", contentType);
		return new ResponseEntity<byte[]>(photoData, headers, HttpStatus.OK);
	}
	
	/**
	 * Retrieves a new set of values via AJAX for the "feature" drop down menu
	 * and redisplays that drop down to show only those features with the 
 	 * currently selected feature classification.
	 * @param featureClassification feature classification
	 * @return model and view
	 */
	@RequestMapping(value = "/featureClassificationEvent.html", 
			method = RequestMethod.GET)
	public ModelAndView featureClassificationEvent(@RequestParam(
			value = "featureClassification", required = true) 
		final FeatureClassification featureClassification) {
		ModelMap map = new ModelMap();
		List<PhysicalFeature> physicalFeatures = 
				new ArrayList<PhysicalFeature>();
		if (featureClassification != null) {
			physicalFeatures = this.physicalFeatureAssociationService
					.findPhysicalFeatureByClassification(featureClassification);
		} else {
			physicalFeatures = this.physicalFeatureAssociationService
					.findAllPhysicalFeatures();
		}
		map.addAttribute(PHYSICAL_FEATURES_MODEL_KEY, physicalFeatures);
		return new ModelAndView(FEATURE_CONTENT_VIEW_NAME, map);
	}
	
	/**
	 * Displays the action menu for physical feature association note items.
	 * 
	 * @return model and view for physical feature association note items 
	 * action menu
	 */
	@RequestMapping(
			value = "physicalFeatureAssociaitonNoteItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayPhysicalFeatureAssociationNoteItemsActionMenu() {
		ModelMap map = new ModelMap();
		return new ModelAndView(
				PHYSICAL_FEATURE_ASSOCIATION_NOTE_ITEMS_ACTION_MENU_VIEW_NAME,
				map);
	}
	
	/**
	 * Displays the physical feature association note item.
	 * 
	 * @param currentPhysicalFeatureAssociationNoteItemIndex current physical
	 * feature association note item index
	 * @return model and view to display physical feature association note item
	 */
	@RequestMapping(value = "createPhysicalFeatureAssociationNoteItem.html",
			method = RequestMethod.GET)
	public ModelAndView displayPhysicalFeatureAssociationNoteItem(
			@RequestParam(
					value = "currentPhysicalFeatureAssociationNoteItemIndex",
					required = true)
					final Integer currentPhysicalFeatureAssociationNoteItemIndex
					) {
		ModelMap map = new ModelMap();
		PhysicalFeatureAssociationNoteItem item = 
				new PhysicalFeatureAssociationNoteItem();
		item.setOperation(PhysicalFeatureAssociationNoteItemOperation.CREATE);
		map.addAttribute(PHYSICAL_FEATURE_ASSOCIATION_NOTE_ITEM_MODEL_KEY,
				item);
		map.addAttribute(PHYSICAL_FEATURE_NOTE_ITEM_INDEX_MODEL_KEY,
				currentPhysicalFeatureAssociationNoteItemIndex);
		return new ModelAndView(
				PHYSICAL_FEATURE_ASSOCIATION_NOTE_ITEM_VIEW_NAME, map);
	}
	
	/**
	 * Displays the physical feature association action menu.
	 * @param offender offender
	 * @return model and view to display physical feature association action
	 * menu
	 */
	@RequestMapping(value = "physicalFeatureAssociaitonActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayPhysicalFeatureAssociationActionMenu(
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(
				PHYSICAL_FEATURE_ASSOCIATION_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the action menu for physical feature association photo items.
	 * 
	 * @return model and view for physical feature association photo items 
	 * action menu
	 */
	@RequestMapping(
			value = "physicalFeatureAssociaitonPhotoItemsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayPhysicalFeatureAssociationPhotoItemsActionMenu()
		{
		ModelMap map = new ModelMap();
		return new ModelAndView(
				PHYSICAL_FEATURE_ASSOCIATION_PHOTO_ITEMS_ACTION_MENU_VIEW_NAME,
				map);
	}
	
	/**
	 * Displays the action menu for physical feature associations.
	 * 
	 * @return model and view for physical feature associations action menu
	 */
	@RequestMapping(
			value = "physicalFeatureAssociaitonsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayPhysicalFeatureAssociationsActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(OTHER_PHOTOS_COUNT_MODEL_KEY,
				this.physicalFeatureAssociationService
				.countOtherPhotosByOffender(offender));
		return new ModelAndView(
				PHYSICAL_FEATURE_ASSOCIATIONS_ACTION_MENU_VIEW_NAME,
				map);
	}
	
	/**
	 * Displays the physical feature association row item action menu.
	 * 
	 * @param physicalFeatureAssociation physical feature association
	 * @return model and view for physical feature association row item action
	 * menu.
	 */
	@RequestMapping(
			value = "physicalFeatureAssociaitonsRowItemActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayPhysicalFeatureAssociationsRowItemActionMenu(
			@RequestParam(value = "physicalFeatureAssociation", required = true)
				final PhysicalFeatureAssociation physicalFeatureAssociation) {
		ModelMap map = new ModelMap();
		map.addAttribute(PHYSICAL_FEATURE_ASSOCIATION_MODEL_KEY
				, physicalFeatureAssociation);
		return new ModelAndView(
				PHYSICAL_FEATURE_ASSOCIATIONS_ROW_ITEM_ACTION_MENU_VIEW_NAME,
				map);
	}
	
	/* Helper methods. */
	
	
	/*
	 * Processes the specified list of physical feature photo items according
	 * to their operation values.
	 * 
	 * @param items physical feature photo items
	 * @param physicalFeatureAssociation physical feature association
	 * @throws DuplicateEntityFoundException thrown when a duplicate photo item
	 * is found.
	 */
	private void processPhotoItems(final List<PhysicalFeaturePhotoItem> items,
			final PhysicalFeatureAssociation physicalFeatureAssociation)
		throws DuplicateEntityFoundException {
		if (items != null) {
			for (PhysicalFeaturePhotoItem item : items) {
					if (PhotoItemOperation.CREATE
							.equals(item.getOperation())) {
						Photo photo = this.physicalFeatureAssociationService
								.createPhoto(item.getDate(),
										this.photoFilenameGenerator.generate());
						PhysicalFeaturePhotoAssociation association = 
								this.physicalFeatureAssociationService
								.createPhysicalFeaturePhotoAssociation(
									physicalFeatureAssociation,
									physicalFeatureAssociation.getOffender(),
									photo);
						this.physicalFeatureAssociationPhotoPersister.persist(
								association.getPhoto(), item.getPhotoData());
					} else if (PhotoItemOperation.UPDATE
							.equals(item.getOperation())) {
						this.physicalFeatureAssociationService
						.updatePhotoAssociation(item.getPhotoAssociation(), 
								item.getDate());
					} else if (PhotoItemOperation.REMOVE
							.equals(item.getOperation())) {
						this.physicalFeatureAssociationService
						.removePhysicalFeaturePhotoAssociation(
								item.getPhotoAssociation());
					}
			}
		}
	}
	
	/*
	 * Prepares a model and view with attributes needed to display list of
	 * all the specified offender's physical feature associations.
	 */
	/*private ModelAndView prepareListMav(final ModelMap map, 
			final Offender offender) {
		List<PhysicalFeatureAssociation> offenderFeatures = this
				.physicalFeatureAssociationService
				.findPhysicalFeaturesAssociationsByOffender(offender);
		map.addAttribute(PHYSICAL_FEATURE_ASSOCIATIONS_MODEL_KEY, 
				offenderFeatures);
		this.offenderSummaryModelDelegate.add(map, offender);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}*/
	
	/*
	 * Prepare the specified physical feature association form with information 
	 * from the specified physical feature association .
	 */
	private PhysicalFeatureAssociationForm preparePFAssociationForm(
			final PhysicalFeatureAssociationForm form, 
			final PhysicalFeatureAssociation pFAssociation) {
		form.setOriginationDate(pFAssociation.getOriginationDate());
		form.setDescription(pFAssociation.getDescription());
		form.setFeature(pFAssociation.getFeature());
		form.setFeatureClassification(pFAssociation.getFeature()
				.getFeatureClassification());
		return form;
	}
	
	/*
	 * Prepares a model and view with the necessary attribute to display the 
	 * edit screen for a physical feature association.
	 */
	private ModelAndView prepareEditMav(final ModelMap map, 
			final Offender offender, 
			final PhysicalFeatureAssociationForm form) {
		List<PhysicalFeature> physicalFeatures = 
				new ArrayList<PhysicalFeature>();
		if (form.getFeatureClassification() != null) {
			physicalFeatures = this.physicalFeatureAssociationService
					.findPhysicalFeatureByClassification(
							form.getFeatureClassification());
		} else {
			physicalFeatures = this.physicalFeatureAssociationService
					.findAllPhysicalFeatures();
		}
		List<FeatureClassification> featureClassifications = 
				this.physicalFeatureAssociationService
				.findFeatureClassifications();
		map.addAttribute(FEATURE_CLASSIFICATIONS_MODEL_KEY, 
				featureClassifications);
		map.addAttribute(PHYSICAL_FEATURES_MODEL_KEY, physicalFeatures);
		this.offenderSummaryModelDelegate.add(map, offender);
		map.addAttribute(PHYSICAL_FEATURE_ASSOCIATION_FORM_MODEL_KEY, form);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/*
	 * Processes the specified list of physical feature association note items
	 * according to their specified operation.
	 * 
	 * @param items list of physical feature association note items
	 * @param featureAssociation physical feature association
	 */
	private void processPhysicalFeatureAssociationNoteItems(
			final List<PhysicalFeatureAssociationNoteItem> items,
			final PhysicalFeatureAssociation featureAssociation) {
		if (items != null) {
			for (PhysicalFeatureAssociationNoteItem item: items) {
				if (PhysicalFeatureAssociationNoteItemOperation.CREATE
						.equals(item.getOperation())) {
					this.physicalFeatureAssociationService.createNote(
							featureAssociation, item.getNote(), item.getDate());
				} else if (PhysicalFeatureAssociationNoteItemOperation.UPDATE
						.equals(item.getOperation())) {
					if(this.isNoteChanged(
							item.getPhysicalFeatureAssociationNote(),
							item.getDate(), item.getNote())) {
						this.physicalFeatureAssociationService.updateNote(
								item.getPhysicalFeatureAssociationNote(), 
								item.getNote(), item.getDate());
					}
				} else if (PhysicalFeatureAssociationNoteItemOperation.REMOVE
						.equals(item.getOperation())) {
					this.physicalFeatureAssociationService.removeNote(
							item.getPhysicalFeatureAssociationNote());
				}
			}
		}
	}
	
	/*
	 * Returns whether the physical feature association note has different
	 * values than the supplied value and date.
	 * 
	 * @param note physical feature association note
	 * @param date date
	 * @param value value
	 * @return whether note information is changed
	 */
	private boolean isNoteChanged(final PhysicalFeatureAssociationNote note,
			final Date date, final String value) {
		if(!note.getNote().equals(value)) {
			return true;
		}
		if(!(note.getDate().getTime() == date.getTime())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				FeatureClassification.class,
				this.featureClassificationPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				PhysicalFeatureAssociation.class,
				this.physicalFeatureAssociationPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				PhysicalFeaturePhotoAssociation.class,
				this.physicalFeaturePhotoAssociationPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				PhysicalFeature.class,
				this.physicalFeaturePropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		
		binder.registerCustomEditor(
				byte[].class,
				new ByteArrayMultipartFileEditor());
		
		binder.registerCustomEditor(
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		
		binder.registerCustomEditor(
				Photo.class, 
				this.photoPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				PhysicalFeatureAssociationNote.class,
				this.physicalFeatureAssociationNotePropertyEditorFactory
				.createPropertyEditor());
	}
}