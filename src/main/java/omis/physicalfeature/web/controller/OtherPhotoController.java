package omis.physicalfeature.web.controller;

import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.io.FileRemover;
import omis.media.domain.Photo;
import omis.media.io.PhotoRetriever;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;
import omis.physicalfeature.domain.PhysicalFeaturePhotoAssociation;
import omis.physicalfeature.domain.ProcessStatus;
import omis.physicalfeature.service.PhysicalFeatureAssociationService;
import omis.physicalfeature.service.PhysicalFeaturePhotoAssociationService;
import omis.physicalfeature.validator.OtherPhotosFormValidator;
import omis.physicalfeature.web.form.OtherPhotosForm;
import omis.physicalfeature.web.form.OtherPhysicalFeaturePhotoAssociationItem;

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
 * Controller for other photo wizard.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 26, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/otherPhoto")
@PreAuthorize("hasRole('USER')")
public class OtherPhotoController {

	/* Redirect URLs. */
	
	private static final String PHYSICAL_FEATURE_LIST_REDIRECT = 
			"redirect:../physicalFeature/list.html?offender=";
	
	/* View names */
	
	private static final String EDIT_VIEW_NAME = "otherPhoto/edit";
	
	private static final String OTHER_PHOTO_WIZARD_ACTION_MENU_VIEW_NAME
		= "otherPhoto/includes/otherPhotoWizardActionMenu";
	
	/* Model Keys. */
	
	private static final String OTHER_PHOTOS_FORM_MODEL_KEY = 
			"otherPhotosForm";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String PHYSICAL_FEATURE_ASSOCIATIONS_MODEL_KEY =
			"pFAssociations";
	
	/* Services */
	
	@Autowired
	@Qualifier("physicalFeatureAssociationService")
	private PhysicalFeatureAssociationService physicalFeatureAssociationService;
	
	@Autowired
	@Qualifier("physicalFeaturePhotoAssociationService")
	private PhysicalFeaturePhotoAssociationService 
	physicalFeaturePhotoAssociationService;
	
	/* Validators */
	
	@Autowired
	@Qualifier("otherPhotosFormValidator")
	private OtherPhotosFormValidator otherPhotosFormValidator;
	
	/* File Retrievers */
	
	@Autowired
	@Qualifier("physicalFeatureAssociationPhotoRetriever")
	private PhotoRetriever physicalFeaturePhotoAssociationRetriever;
	
	/* File Persisters */
	
	/* File Remover */
	
	@Autowired
	@Qualifier("physicalFeatureAssociationPhotoRemover")
	private FileRemover physicalFeatureAssociationPhotoRemover;
	
	/* Property editors */
	
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
	
	/* Instance factories */
	
	@Autowired
	@Qualifier("physicalFeatureAssociationInstanceFactory")
	private InstanceFactory<PhysicalFeatureAssociation> 
	physicalFeatureAssociationInstanceFactory;
	
	@Autowired
	@Qualifier("physicalFeaturePhotoAssociationInstanceFactory")
	private InstanceFactory<PhysicalFeaturePhotoAssociation>
	physicalFeaturePhotoAssociationInstanceFactory;

	/* Helpers. */
	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/**
	 * Instantiates a default other photo controller.
	 */
	public OtherPhotoController() {
		//Default constructor
	}
	
	/**
	 * Edit screen to display all existing {@code physical feature 
	 * associations} and all orphaned photos from {@code other photos} 
	 * conversion that are classified as being meant for SMT's (Scars, 
	 * Marks, and Tattoos) and allow for associations to be made.
	 * 
	 * @param offender offender
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "otherPhotosEditScreenName",
			descriptionKey = "otherPhotosEditScreenDescription",
			messageBundle = "omis.physicalfeature.msgs.otherphoto",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PHYSICAL_FEATURE_PHOTO_ASSOCIATION_VIEW') or hasRole('ADMIN')")
	public ModelAndView edit(@RequestParam(value = "offender",
			required = true) final Offender offender) {
		ModelMap map = new ModelMap();
		OtherPhotosForm form = new OtherPhotosForm();
		form.setOffender(offender);
		List<PhysicalFeatureAssociation> pFAssociations = this
				.physicalFeatureAssociationService
				.findPhysicalFeaturesAssociationsByOffender(offender);
		List<PhysicalFeaturePhotoAssociation> orphanedPhotoAssocs = this.
				physicalFeaturePhotoAssociationService
				.findOrphanedPhotoAssociations(offender);
		for (PhysicalFeaturePhotoAssociation photoAssociation 
				: orphanedPhotoAssocs) {
			OtherPhysicalFeaturePhotoAssociationItem item =
					new OtherPhysicalFeaturePhotoAssociationItem();
			item.setPhysicalFeaturePhotoAssociation(photoAssociation);
			form.getoPFPAItems().add(item);
		}
		map.addAttribute(PHYSICAL_FEATURE_ASSOCIATIONS_MODEL_KEY, 
				pFAssociations);
		this.offenderSummaryModelDelegate.add(map, offender);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(OTHER_PHOTOS_FORM_MODEL_KEY, form);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Submission of newly requested relationships between {@code physical 
	 * feature photo associations} and a {@code physical feature association}.
	 * 
	 * @param form other photos form
	 * @param result binding result
	 * @return model and view
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	@RequestContentMapping(nameKey = "otherPhotosUpdateName",
			descriptionKey = "otherPhotosUpdateDescription",
			messageBundle = "omis.physicalfeature.msgs.otherphoto",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('PHYSICAL_FEATURE_PHOTO_ASSOCIATION_EDIT') or hasRole('ADMIN')")
	public ModelAndView update(final OtherPhotosForm form,
			final BindingResult result) 
		throws DuplicateEntityFoundException {
		this.otherPhotosFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			Offender offender = form.getOffender();
			List<PhysicalFeatureAssociation> pFAssociations = this
					.physicalFeatureAssociationService
					.findPhysicalFeaturesAssociationsByOffender(offender);
			map.addAttribute(PHYSICAL_FEATURE_ASSOCIATIONS_MODEL_KEY, 
					pFAssociations);
			this.offenderSummaryModelDelegate.add(map, offender);
			map.addAttribute(OFFENDER_MODEL_KEY, offender);
			map.addAttribute(OTHER_PHOTOS_FORM_MODEL_KEY, form);
			return new ModelAndView(EDIT_VIEW_NAME, map);
		}
		PhysicalFeatureAssociation pFAssociation = form
				.getPhysicalFeatureAssociation();
		for (OtherPhysicalFeaturePhotoAssociationItem item 
				: form.getoPFPAItems()) {
			if (item.getStatus() == ProcessStatus.ASSOCIATE) {
				PhysicalFeaturePhotoAssociation pfpAssociation = 
						item.getPhysicalFeaturePhotoAssociation();
				this.physicalFeaturePhotoAssociationService
				.update(pFAssociation, pfpAssociation.getPerson(), 
						pfpAssociation.getPhoto(), pfpAssociation);
			}
			if (item.getStatus() == ProcessStatus.REMOVE) {
				PhysicalFeaturePhotoAssociation pfpAssociation =
						item.getPhysicalFeaturePhotoAssociation();
				this.physicalFeaturePhotoAssociationService.remove(
						pfpAssociation);
				this.physicalFeaturePhotoAssociationService
				.removePhysicalFeaturePhotoAssociationPhoto(pfpAssociation
						.getPhoto());
				this.physicalFeatureAssociationPhotoRemover.remove(
						pfpAssociation.getPhoto().getFilename());
			}
		}
		return new ModelAndView(PHYSICAL_FEATURE_LIST_REDIRECT 
				+ form.getOffender().getId());
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
			photoData = this.physicalFeaturePhotoAssociationRetriever
					.retrieve(photo);
		} else {
			photoData = this.physicalFeaturePhotoAssociationRetriever
					.retrieve("NotSet.jpg");
		}
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", contentType);
		return new ResponseEntity<byte[]>(photoData, headers, HttpStatus.OK);
	}
	
	/**
	 * Displays the other photo wizard action menu.
	 * 
	 * @param offender offender
	 * @return model and view for displaying action menu for other photo wizard
	 */
	@RequestMapping(value = "/displayOtherPhotoWizardActionMenu.html")
	public ModelAndView displayOtherPhotoWizardActionMenu(@RequestParam(
			value = "offender", required = true) final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(OTHER_PHOTO_WIZARD_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		
		binder.registerCustomEditor(
				PhysicalFeatureAssociation.class,
				this.physicalFeatureAssociationPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				PhysicalFeaturePhotoAssociation.class,
				this.physicalFeaturePhotoAssociationPropertyEditorFactory
				.createPropertyEditor());
		
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
	}
}