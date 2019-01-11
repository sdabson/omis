/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.staff.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Collections;
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
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.config.util.FeatureToggles;
import omis.datatype.DateRange;
import omis.demographics.domain.EyeColor;
import omis.demographics.domain.HairColor;
import omis.demographics.domain.Height;
import omis.demographics.domain.PersonDemographics;
import omis.demographics.domain.Sex;
import omis.demographics.domain.Weight;
import omis.io.FileRemover;
import omis.io.FilenameGenerator;
import omis.location.domain.Location;
import omis.media.domain.Photo;
import omis.media.exception.PhotoExistsException;
import omis.media.io.PhotoPersister;
import omis.media.io.PhotoRetriever;
import omis.organization.domain.OrganizationDivision;
import omis.person.domain.Person;
import omis.person.exception.PersonExistsException;
import omis.person.exception.PersonIdentityExistsException;
import omis.person.exception.PersonNameExistsException;
import omis.staff.domain.StaffAssignment;
import omis.staff.domain.StaffPhotoAssociation;
import omis.staff.domain.StaffTitle;
import omis.staff.exception.StaffAssignmentExistsException;
import omis.staff.exception.StaffPhotoAssociationExistsException;
import omis.staff.service.StaffAssignmentService;
import omis.staff.web.form.StaffAssignmentForm;
import omis.staff.web.form.StaffAssignmentPersonOperation;
import omis.staff.web.validator.StaffAssignmentFormValidator;
import omis.supervision.domain.SupervisoryOrganization;
import omis.web.controller.delegate.BusinessExceptionHandlerDelegate;

/**
 * Staff assignment controller.
 * 
 * @author Sheronda Vaughn
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.0.1 (Aug 21, 2018)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/staffAssignment")
@PreAuthorize("hasRole('USER')")
public class StaffAssignmentController {

	/* View names. */
	
	private static final String EDIT_VIEW_NAME = "staff/assignment/edit";
	
	private static final String LOCATION_OPTIONS_VIEW_NAME
		= "location/includes/locations";
	
	private static final String ORGANIZATION_DIVISION_OPTIONS_VIEW_NAME
		= "organization/includes/divisions";
	
	private static final String
	STAFF_ASSIGNMENT_CREATE_EDIT_SCREEN_ACTION_MENU_VIEW_NAME
		= "staff/includes/staffCreateEditActionMenu";
	
	/* Redirects. */
	
	private static final String LIST_REDIRECT
		= "redirect:/staffSearch/staffSearch.html";

	/* Model keys. */
	
	private static final String STAFF_ASSIGNMENT_FORM_MODEL_KEY
		= "staffAssignmentForm";

	private static final String SUPERVISORY_ORGANIZATIONS_MODEL_KEY
		= "supervisoryOrganizations";

	private static final String STAFF_TITLES_MODEL_KEY = "staffTitles";

	private static final String EYE_COLORS_MODEL_KEY = "eyeColors";

	private static final String HAIR_COLORS_MODEL_KEY = "hairColors";

	private static final String LOCATIONS_MODEL_KEY = "locations";
	
	private static final String ORGANIZATION_DIVISIONS_MODEL_KEY
		= "organizationDivisions";

	private static final String STAFF_ASSIGNMENT_MODEL_KEY = "staffAssignment";

	private static final String SUFFIXES_MODEL_KEY = "suffixes";
	
	private static final String SEXES_MODEL_KEY = "sexes";
	
	private static final String STAFF_PHOTO_ASSOCIATION_MODEL_KEY
		= "staffPhotoAssociation";
	
	private static final String PHOTO_DATA_MODEL_KEY = "photoData";
	private static final String ALLOW_ENHANCED_IMAGE_EDITOR_MODEL_KEY
	= "allowEnhancedImageEditor";
	
	private static final String PERSON_EXISTS_EXCEPTION_MESSAGE_KEY
	= "person.Conflicts";
	
	private static final String STAFF_PHOTO_ASSOCIATION_EXISTS_EXCEPTION_MESSAGE_KEY
	= "staffPhotoAssociation.Conflicts";
	
	private static final String PHOTO_EXISTS_EXCEPTION_MESSAGE_KEY
	= "photo.Conflicts";
	
	private static final String STAFF_ASSIGNMENT_EXISTS_EXCEPTION_MESSAGE_KEY
	= "staffAssignment.Conflicts";
	
	private static final String PERSON_NAME_EXISTS_EXCEPTION_MESSAGE_KEY
	= "personName.Conflicts";
	
	private static final String PERSON_IDENTITY_EXISTS_EXCEPTION_MESSAGE_KEY
	= "personIdentity.Conflicts";
	
	/* Message bundle */
	
	private static final String STAFF_ASSIGNMENT_ERROR_BUNDLE_NAME
		= "omis.staff.msgs.form";
	
	/* Service. */
	
	@Autowired
	@Qualifier("staffAssignmentService")
	private StaffAssignmentService staffAssignmentService;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("staffAssignmentFormValidator")
	private StaffAssignmentFormValidator staffAssignmentFormValidator;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("staffAssignmentPropertyEditorFactory")
	private PropertyEditorFactory staffAssignmentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("staffPhotoAssociationPropertyEditorFactory")
	private PropertyEditorFactory staffPhotoAssociationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("eyeColorPropertyEditorFactory")
	private PropertyEditorFactory eyeColorPropertyEditorFactory;
	
	@Autowired
	@Qualifier("hairColorPropertyEditorFactory")
	private PropertyEditorFactory hairColorPropertyEditorFactory;
	
	@Autowired
	@Qualifier("locationPropertyEditorFactory")
	private PropertyEditorFactory locationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("staffTitlePropertyEditorFactory")
	private PropertyEditorFactory staffTitlePropertyEditorFactory;
	
	@Autowired
	@Qualifier("supervisoryOrganizationPropertyEditorFactory")
	private PropertyEditorFactory supervisoryOrganizationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("organizationDivisionPropertyEditorFactory")
	private PropertyEditorFactory organizationDivisionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	/* Photo I/O delegates. */
	
	@Autowired
	@Qualifier("staffPhotoRetriever")
	private PhotoRetriever photoRetriever;
	
	@Autowired
	@Qualifier("staffPhotoPersister")
	private PhotoPersister photoPersister;
	
	@Autowired
	@Qualifier("staffPhotoRemover")
	private FileRemover staffPhotoRemover;
	
	@Autowired
	@Qualifier("photoFilenameGenerator")
	private FilenameGenerator photoFilenameGenerator;
	
	/* Feature toggle repository. */
	
	@Autowired
	@Qualifier("featureToggles")
	private FeatureToggles featureToggles;
	
	/* Delegate */
	
	@Autowired
	@Qualifier("businessExceptionHandlerDelegate")
	private BusinessExceptionHandlerDelegate
		businessExceptionHandlerDelegate;
	
	/* Constructors. */
	
	/** Instantiates a controller for staff assignments. */
	public StaffAssignmentController() {
		//Default instantiation
	}

	/* URL invokable methods. */
	
	/**
	 * Shows screen to create staff assignment.
	 * 
	 * @return screen to create staff assignment
	 */
	@PreAuthorize("hasRole('STAFF_ASSIGNMENT_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	public ModelAndView create() {
		StaffAssignmentForm staffAssignmentForm = new StaffAssignmentForm();
		staffAssignmentForm.setPersonOperation(
				StaffAssignmentPersonOperation.CREATE);
		staffAssignmentForm.setNewPhoto(true);
		ModelAndView mav = this.prepareMav(staffAssignmentForm);
		return mav;
	}
	
	/**
	 * Creates staff assignment.
	 * 
	 * @param staffAssignmentForm staff assignment form
	 * @param result binding result
	 * @return redirect to listing screen on success
	 * @throws PersonExistsException if person exists
	 * @throws StaffPhotoAssociationExistsException if staff photo association
	 * exists
	 * @throws PhotoExistsException if photo exists
	 * @throws StaffAssignmentExistsException if staff assignment exists
	 */
	@PreAuthorize("hasRole('STAFF_ASSIGNMENT_CREATE') or hasRole('ADMIN')")
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	public ModelAndView save(
				final StaffAssignmentForm staffAssignmentForm,
				final BindingResult result) 
			throws PersonExistsException,
				StaffPhotoAssociationExistsException,
				PhotoExistsException,
				StaffAssignmentExistsException {
		
		// Validates fields
		this.staffAssignmentFormValidator.validate(staffAssignmentForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplayMav(
					staffAssignmentForm, result);
			if (staffAssignmentForm.getPhotoData() != null) {
				mav.addObject(PHOTO_DATA_MODEL_KEY,
						this.reencodePhotoData(
										staffAssignmentForm.getPhotoData()));
			}
			return mav;
		}
		
		// Creates a staff member or uses an existing one
		// Other operations to associate staff member are not allowed
		// on creation of assignment
		Person staffMember;
		if (StaffAssignmentPersonOperation.CREATE
				.equals(staffAssignmentForm.getPersonOperation())) {
			 staffMember = this.staffAssignmentService.createStaffMember(
					staffAssignmentForm.getLastName(), 
					staffAssignmentForm.getFirstName(), 
					staffAssignmentForm.getMiddleName(), 
					staffAssignmentForm.getSuffix(),
					staffAssignmentForm.getBirthDate(), 
					staffAssignmentForm.getSex());
		} else if (StaffAssignmentPersonOperation.USE_EXISTING
				.equals(staffAssignmentForm.getPersonOperation())) {
			staffMember = staffAssignmentForm.getStaffMember();
		} else {
			throw new UnsupportedOperationException(String.format(
					"Cannot %s person on creation of staff assignment",
					staffAssignmentForm.getPersonOperation()));
		}
		
		// Associated demographics if any demographics fields are supplied
		if (staffAssignmentForm.getEyeColor() != null
				|| staffAssignmentForm.getHairColor() != null
				|| staffAssignmentForm.getHeightFeet() != null
				|| staffAssignmentForm.getHeightInches() != null
				|| staffAssignmentForm.getWeightPounds() != null) {
			Height height = new Height(
				 staffAssignmentForm.getHeightFeet(),
				 staffAssignmentForm.getHeightInches());
			Weight weight = new Weight(
				 staffAssignmentForm.getWeightPounds());
			this.staffAssignmentService.associateDemographics(staffMember,
				 staffAssignmentForm.getEyeColor(),
				 staffAssignmentForm.getHairColor(),
				 height, weight);
		}

		// Associates and persists photo if date and none-empty data is supplied
		if (staffAssignmentForm.getPhotoDate() != null
				&& staffAssignmentForm.getPhotoData() != null
				&& staffAssignmentForm.getPhotoData().length > 0) {
			String photoFilename = this.photoFilenameGenerator.generate();
			StaffPhotoAssociation photoAssociation
				= this.staffAssignmentService.associatePhoto(
					staffMember, staffAssignmentForm.getPhotoDate(),
					photoFilename);
			this.photoPersister.persist(
					photoAssociation.getPhoto(),
					this.decodePhotoData(staffAssignmentForm.getPhotoData()));
		} else if (staffAssignmentForm.getPhotoDate() != null
				|| (staffAssignmentForm.getPhotoData() != null
					&& staffAssignmentForm.getPhotoData().length > 0)) {
			
			// Errors out if one of either required photo field is supplied
			throw new IllegalArgumentException(
					"All photo fields must be supplied");
		}
		
		// Creates staff assignment
		this.staffAssignmentService.createStaffAssignment(staffMember,
				staffAssignmentForm.getSupervisoryOrganization(),
				staffAssignmentForm.getLocation(),
				staffAssignmentForm.getOrganizationDivision(),
				new DateRange(staffAssignmentForm.getStartDate(),
						staffAssignmentForm.getEndDate()),
				staffAssignmentForm.getTitle(),
				staffAssignmentForm.getSupervisory(),
				staffAssignmentForm.getStaffId());
		
		// Redirects to search screen
		return new ModelAndView(LIST_REDIRECT);
	}
	
	/**
	 * Shows screen to edit staff assignment.
	 * 
	 * @param staffAssignment staff assignment
	 * @return screen to edit staff assignment
	 */
	@PreAuthorize("hasRole('STAFF_ASSIGNMENT_VIEW') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(value = "staffAssignment", required = true)
				final StaffAssignment staffAssignment) {
		StaffAssignmentForm staffAssignmentForm = new StaffAssignmentForm();
		staffAssignmentForm.setPersonOperation(
				StaffAssignmentPersonOperation.UPDATE);
		staffAssignmentForm.setLastName(
				staffAssignment.getStaffMember().getName().getLastName());
		staffAssignmentForm.setFirstName(
				staffAssignment.getStaffMember().getName().getFirstName());
		staffAssignmentForm.setMiddleName(
				staffAssignment.getStaffMember().getName().getMiddleName());
		staffAssignmentForm.setSuffix(
				staffAssignment.getStaffMember().getName().getSuffix());
		if (staffAssignment.getStaffMember().getIdentity() != null) {
			staffAssignmentForm.setBirthDate(
					staffAssignment.getStaffMember().getIdentity()
						.getBirthDate());
			staffAssignmentForm.setSex(
					staffAssignment.getStaffMember().getIdentity().getSex());
		}
		PersonDemographics demographics = this.staffAssignmentService
				.findDemographics(staffAssignment.getStaffMember());
		if (demographics != null) {
			if (demographics.getAppearance() != null) {
				staffAssignmentForm.setEyeColor(demographics.getAppearance()
						.getEyeColor());
				staffAssignmentForm.setHairColor(demographics.getAppearance()
						.getHairColor());
			}
			if (demographics.getPhysique() != null) {
				if (demographics.getPhysique().getHeight() != null) {
					staffAssignmentForm.setHeightFeet(
							demographics.getPhysique().getHeight().getFeet());
					staffAssignmentForm.setHeightInches(
							demographics.getPhysique().getHeight().getInches());
				}
				if (demographics.getPhysique().getWeight() != null) {
					staffAssignmentForm.setWeightPounds(
							demographics.getPhysique().getWeight().getPounds());
				}
			}
		}
		StaffPhotoAssociation photoAssociation = this.staffAssignmentService
				.findPhotoAssociation(staffAssignment.getStaffMember());
		if (photoAssociation != null) {
			staffAssignmentForm.setPhotoDate(
					photoAssociation.getPhoto().getDate());
		}
		staffAssignmentForm.setStaffId(staffAssignment.getStaffId());
		staffAssignmentForm.setStartDate(
				DateRange.getStartDate(staffAssignment.getDateRange()));
		staffAssignmentForm.setEndDate(
				DateRange.getEndDate(staffAssignment.getDateRange()));
		staffAssignmentForm.setTitle(staffAssignment.getTitle());
		staffAssignmentForm.setSupervisoryOrganization(
				staffAssignment.getSupervisoryOrganization());
		staffAssignmentForm.setLocation(staffAssignment.getLocation());
		staffAssignmentForm.setOrganizationDivision(
				staffAssignment.getOrganizationDivision());
		staffAssignmentForm.setSupervisory(staffAssignment.getSupervisory());
		ModelAndView mav = this.prepareMav(staffAssignmentForm);
		mav.addObject(STAFF_ASSIGNMENT_MODEL_KEY,
				staffAssignment);
		StaffPhotoAssociation staffPhotoAssociation
			= this.staffAssignmentService
				.findPhotoAssociation(staffAssignment.getStaffMember());
		if (staffPhotoAssociation != null) {
			byte[] photoData = this.photoRetriever
					.retrieve(staffPhotoAssociation.getPhoto());
			mav.addObject(PHOTO_DATA_MODEL_KEY,
					this.encodePhotoData(photoData));
			mav.addObject(
					STAFF_PHOTO_ASSOCIATION_MODEL_KEY, staffPhotoAssociation);
		}
		return mav;
	}
	
	/**
	 * Updates staff assignment.
	 * 
	 * @param staffAssignment staff assignment to update
	 * @param staffAssignmentForm staff assignment form
	 * @param result binding result
	 * @return redirect to screen to search staff assignments
	 * @throws PersonExistsException if person exists
	 * @throws PersonNameExistsException if person name exists
	 * @throws PersonIdentityExistsException if person identity exists
	 * @throws StaffPhotoAssociationExistsException if staff photo association
	 * exists
	 * @throws PhotoExistsException if photo exists
	 * @throws StaffAssignmentExistsException if staff assignment exists
	 */
	@PreAuthorize("hasRole('STAFF_ASSIGNMENT_EDIT') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	public ModelAndView update(
			@RequestParam(value = "staffAssignment", required = true)
				final StaffAssignment staffAssignment,
			final StaffAssignmentForm staffAssignmentForm,
			final BindingResult result)
					throws PersonExistsException,
						PersonNameExistsException,
						PersonIdentityExistsException,
						StaffPhotoAssociationExistsException,
						PhotoExistsException,
						StaffAssignmentExistsException {
		
		// Validates form
		this.staffAssignmentFormValidator.validate(staffAssignmentForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplayMav(
					staffAssignmentForm, result);
			mav.addObject(STAFF_ASSIGNMENT_MODEL_KEY, staffAssignment);
			StaffPhotoAssociation staffPhotoAssociation
				= this.staffAssignmentService
					.findPhotoAssociation(staffAssignment.getStaffMember());
			mav.addObject(
					STAFF_PHOTO_ASSOCIATION_MODEL_KEY, staffPhotoAssociation);
			if (staffAssignmentForm.getPhotoData() != null) {
				mav.addObject(PHOTO_DATA_MODEL_KEY,
						this.reencodePhotoData(
									staffAssignmentForm.getPhotoData()));
			}
			return mav;
		}
		
		// Only updating existing staff member is allowed
		if (StaffAssignmentPersonOperation.UPDATE.equals(
				staffAssignmentForm.getPersonOperation())) {
			this.staffAssignmentService.updateStaffMember(
					staffAssignment.getStaffMember(),
					staffAssignmentForm.getLastName(),
					staffAssignmentForm.getFirstName(),
					staffAssignmentForm.getMiddleName(),
					staffAssignmentForm.getSuffix(),
					staffAssignmentForm.getBirthDate(),
					staffAssignmentForm.getSex());
		} else {
			throw new UnsupportedOperationException(String.format(
					"Cannot %s person on update of staff assignment",
					staffAssignmentForm.getPersonOperation()));
		}
		
		// Associates demographics
		Height height = new Height(
				staffAssignmentForm.getHeightFeet(),
				staffAssignmentForm.getHeightInches());
		Weight weight = new Weight(
				staffAssignmentForm.getWeightPounds());
		this.staffAssignmentService.associateDemographics(
				staffAssignment.getStaffMember(),
				staffAssignmentForm.getEyeColor(),
				staffAssignmentForm.getHairColor(),
				height, weight);
		
		// Associates photo
		StaffPhotoAssociation photoAssociation = this.staffAssignmentService
				.findPhotoAssociation(staffAssignment.getStaffMember());
		if (photoAssociation != null) {
			
			// Updates existing photo
			Photo photo = this.staffAssignmentService.updatePhoto(
					photoAssociation.getPhoto(),
					staffAssignmentForm.getPhotoDate());
			this.photoPersister.persist(photo, 
					this.decodePhotoData(staffAssignmentForm.getPhotoData()));
		} else {
			
			// Associates and persists phot if date and none-empty data is
			// supplied
			if (staffAssignmentForm.getPhotoDate() != null
					&& staffAssignmentForm.getPhotoData() != null
					&& staffAssignmentForm.getPhotoData().length > 0) {
				String photoFilename = this.photoFilenameGenerator.generate();
				photoAssociation
					= this.staffAssignmentService.associatePhoto(
							staffAssignment.getStaffMember(),
							staffAssignmentForm.getPhotoDate(),
							photoFilename);
				this.photoPersister.persist(photoAssociation.getPhoto(), 
						this.decodePhotoData(staffAssignmentForm
								.getPhotoData()));
			} else if (staffAssignmentForm.getPhotoDate() != null
					|| (staffAssignmentForm.getPhotoData() != null
							&& staffAssignmentForm.getPhotoData().length > 0)) {
				
				// Errors out if one of either photo field is supplied
				throw new IllegalArgumentException("All photo fields required");
			}
		}
		
		// Updates staff assignment
		this.staffAssignmentService.updateStaffAssignment(staffAssignment,
				staffAssignmentForm.getSupervisoryOrganization(),
				staffAssignmentForm.getLocation(),
				staffAssignmentForm.getOrganizationDivision(),
				new DateRange(staffAssignmentForm.getStartDate(),
						staffAssignmentForm.getEndDate()),
				staffAssignmentForm.getTitle(),
				staffAssignmentForm.getSupervisory(),
				staffAssignmentForm.getStaffId());
		
		// Redirects to search screen
		return new ModelAndView(LIST_REDIRECT);
	}
	
	/**
	 * Removes staff assignment.
	 * 
	 * @param staffAssignment staff assignment to remove
	 * @return redirect to screen to search staff assignments
	 */
	@PreAuthorize("hasRole('STAFF_ASSIGNMENT_REMOVE') or hasRole('ADMIN')")
	@RequestMapping(value = "/remove.html", method = RequestMethod.GET)
	public ModelAndView remove(
			@RequestParam(value = "staffAssignment", required = true)
				final StaffAssignment staffAssignment) {
		StaffPhotoAssociation association = this.staffAssignmentService
				.findPhotoAssociation(staffAssignment.getStaffMember());
		String photoFilename;
		if (association != null) {
			photoFilename = association.getPhoto().getFilename();
		} else {
			photoFilename = null;
		}
		this.staffAssignmentService.remove(staffAssignment);
		if (photoFilename != null) {
			this.staffPhotoRemover.remove(photoFilename);
		}
		return new ModelAndView(LIST_REDIRECT);
	}	
	
	/**
	 * Returns a view for an action menu on create/edit screen
	 * 
	 * @return model and view for an action menu
	 */
	@RequestMapping(
		value = "/staffAssignmentCreateEditScreenActionMenu.html",
		method = RequestMethod.GET)
	public ModelAndView staffAssignmentCreateActionMenu() {
		ModelMap map = new ModelMap();
		return new ModelAndView(
			STAFF_ASSIGNMENT_CREATE_EDIT_SCREEN_ACTION_MENU_VIEW_NAME,
			map);
	}
	
	/**
	 * Returns locations by supervisory organization if supervisory organization
	 * is supplied; otherwise returns an empty collection.
	 * 
	 * @param supervisoryOrganization supervisory organization
	 * @return locations by supervisory organization if supervisory organization
	 * is supplied; otherwise returns an empty collection
	 */
	@RequestMapping(value = "/findLocationsBySupervisoryOrganization.html",
			method = RequestMethod.GET)
	public ModelAndView findLocationsBySupervisoryOrganization(
			@RequestParam(value = "supervisoryOrganization", required = false) 
				final SupervisoryOrganization supervisoryOrganization) {
		ModelMap map = new ModelMap();
		List<Location> locations;
		if (supervisoryOrganization != null) {
			locations = this.staffAssignmentService
					.findLocationsBySupervisoryOrganization(
							supervisoryOrganization);
		} else {
			locations = Collections.emptyList();
		}
		map.addAttribute(LOCATIONS_MODEL_KEY, locations);
		return new ModelAndView(LOCATION_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns divisions by supervisory organization if supervisory organization
	 * is supplied; otherwise returns an empty collection.
	 * 
	 * @param supervisoryOrganization
	 * @return
	 */
	@RequestMapping(value = "/findDivisionsBySupervisoryOrganization.html",
			method = RequestMethod.GET)
	public ModelAndView findDivisionsBySupervisoryOrganization(
			@RequestParam(value = "supervisoryOrganization", required = false)
				final SupervisoryOrganization supervisoryOrganization) {
		ModelMap map = new ModelMap();
		List<OrganizationDivision> divisions;
		if (supervisoryOrganization != null) {
			divisions = this.staffAssignmentService
					.findDivisionsBySupervisoryOrganization(
							supervisoryOrganization);
		} else {
			divisions = Collections.emptyList();
		}
		map.addAttribute(ORGANIZATION_DIVISIONS_MODEL_KEY, divisions);
		return new ModelAndView(ORGANIZATION_DIVISION_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Handles {@code PersonExistsException}.
	 * 
	 * @param PersonExistsException thrown when person already exists
	 * @return screen to handle {@code PersonExistsException}
	 */
	@ExceptionHandler(PersonExistsException.class)
	public ModelAndView handlePersonExistsException(
		final PersonExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
			PERSON_EXISTS_EXCEPTION_MESSAGE_KEY,
			STAFF_ASSIGNMENT_ERROR_BUNDLE_NAME, exception);
	}
	
	/**
	 * Handles {@code PhotoExistsException}.
	 * 
	 * @param PhotoExistsException thrown when photo already exists
	 * @return screen to handle
	 * {@code PhotoExistsException}
	 */
	@ExceptionHandler(PhotoExistsException.class)
	public ModelAndView handlePhotoExistsException(
		final PhotoExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
			PHOTO_EXISTS_EXCEPTION_MESSAGE_KEY,
			STAFF_ASSIGNMENT_ERROR_BUNDLE_NAME, exception);
	}
	
	/**
	 * Handles {@code StaffAssignmentExistsException}.
	 * 
	 * @param StaffAssignmentExistsException thrown when staff
	 * assignment already exists
	 * @return screen to handle {@code StaffAssignmentExistsException}
	 */
	@ExceptionHandler(StaffAssignmentExistsException.class)
	public ModelAndView handleStaffAssignmentExistsException(
		final StaffAssignmentExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
			STAFF_ASSIGNMENT_EXISTS_EXCEPTION_MESSAGE_KEY,
			STAFF_ASSIGNMENT_ERROR_BUNDLE_NAME, exception);
	}
	
	/**
	 * Handles {@code StaffPhotoAssociationExistsException}.
	 * 
	 * @param StaffPhotoAssociationExistsException thrown when staff
	 * photo association already exists
	 * @return screen to handle {@code StaffPhotoAssociationExistsException}
	 */
	@ExceptionHandler(StaffPhotoAssociationExistsException.class)
	public ModelAndView handleStaffPhotoAssociationExistsException(
		final StaffPhotoAssociationExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
			STAFF_PHOTO_ASSOCIATION_EXISTS_EXCEPTION_MESSAGE_KEY,
			STAFF_ASSIGNMENT_ERROR_BUNDLE_NAME, exception);
	}
	
	/**
	 * Handles {@code PersonNameExistsException}.
	 * 
	 * @param PErsonNameExistsException thrown when person name
	 * already exists
	 * @return screen to handle {@code PersonNameExistsException}
	 */
	@ExceptionHandler(PersonNameExistsException.class)
	public ModelAndView handlePersonNameExistsException(
		final PersonNameExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
			PERSON_NAME_EXISTS_EXCEPTION_MESSAGE_KEY,
			STAFF_ASSIGNMENT_ERROR_BUNDLE_NAME, exception);
	}
	
	/**
	 * Handles {@code PersonIdentityExistsException}.
	 * 
	 * @param PersonIdentityExistsException thrown when person identity
	 * already exists
	 * @return screen to handle {@code PersonIdentityExistsException}
	 */
	@ExceptionHandler(PersonIdentityExistsException.class)
	public ModelAndView handlePersonIdentityExistsException(
		final PersonIdentityExistsException exception) {
		return this.businessExceptionHandlerDelegate.prepareModelAndView(
			PERSON_IDENTITY_EXISTS_EXCEPTION_MESSAGE_KEY,
			STAFF_ASSIGNMENT_ERROR_BUNDLE_NAME, exception);
	}
	
	/* Init binders. */
	
	/**
	 * Registers property editors.
	 * 
	 * @param dataBinder data binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(StaffAssignment.class,
				this.staffAssignmentPropertyEditorFactory
					.createPropertyEditor());
		dataBinder.registerCustomEditor(StaffPhotoAssociation.class,
				this.staffPhotoAssociationPropertyEditorFactory
					.createPropertyEditor());
		dataBinder.registerCustomEditor(Date.class,
				this.customDateEditorFactory
					.createCustomDateOnlyEditor(true));
		dataBinder.registerCustomEditor(EyeColor.class,
				this.eyeColorPropertyEditorFactory
					.createPropertyEditor());
		dataBinder.registerCustomEditor(HairColor.class,
				this.hairColorPropertyEditorFactory
					.createPropertyEditor());
		dataBinder.registerCustomEditor(SupervisoryOrganization.class,
				this.supervisoryOrganizationPropertyEditorFactory
					.createPropertyEditor());
		dataBinder.registerCustomEditor(StaffTitle.class,
				this.staffTitlePropertyEditorFactory
					.createPropertyEditor());
		dataBinder.registerCustomEditor(Location.class,
				this.locationPropertyEditorFactory
					.createPropertyEditor());
		dataBinder.registerCustomEditor(OrganizationDivision.class,
				this.organizationDivisionPropertyEditorFactory
					.createPropertyEditor());
		dataBinder.registerCustomEditor(Person.class,
				this.personPropertyEditorFactory
					.createPropertyEditor());
		dataBinder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
	
	/* Helper methods. */
	
	// Returns edit screen model and view
	private ModelAndView prepareMav(
			final StaffAssignmentForm staffAssignmentForm) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(STAFF_ASSIGNMENT_FORM_MODEL_KEY, staffAssignmentForm);
		mav.addObject(SUFFIXES_MODEL_KEY,
				this.staffAssignmentService
					.findSuffixes());
		mav.addObject(SEXES_MODEL_KEY, Sex.values());
		mav.addObject(SUPERVISORY_ORGANIZATIONS_MODEL_KEY,
				this.staffAssignmentService.findSupervisoryOrganizations());
		mav.addObject(STAFF_TITLES_MODEL_KEY,
				this.staffAssignmentService.findTitles());
		mav.addObject(EYE_COLORS_MODEL_KEY,
				this.staffAssignmentService.findEyeColors());
		mav.addObject(HAIR_COLORS_MODEL_KEY,
				this.staffAssignmentService.findHairColors());
		if (staffAssignmentForm.getSupervisoryOrganization() != null) {
			mav.addObject(LOCATIONS_MODEL_KEY,
				this.staffAssignmentService
					.findLocationsBySupervisoryOrganization(
							staffAssignmentForm.getSupervisoryOrganization()));
			mav.addObject(ORGANIZATION_DIVISIONS_MODEL_KEY,
					this.staffAssignmentService
						.findDivisionsBySupervisoryOrganization(
							staffAssignmentForm.getSupervisoryOrganization()));
		}
		if(this.getAllowEnhancedImageEditor()) {
			mav.addObject(ALLOW_ENHANCED_IMAGE_EDITOR_MODEL_KEY, true);
		}
		return mav;
	}
	
	// Returns redisplayed edit screen model and view
	private ModelAndView prepareRedisplayMav(
			final StaffAssignmentForm staffAssignmentForm,
			final BindingResult result) {
		ModelAndView mav = this.prepareMav(staffAssignmentForm);
		mav.addObject(
			BindingResult.MODEL_KEY_PREFIX + STAFF_ASSIGNMENT_FORM_MODEL_KEY,
			result);
		if(this.getAllowEnhancedImageEditor()) {
			mav.addObject(ALLOW_ENHANCED_IMAGE_EDITOR_MODEL_KEY, true);
		}
		return mav;
	}
	
	// Returns encoded photo data as a string - not null safe
	private String encodePhotoData(final byte[] photoData) {
		try {
			return new String(Base64.getEncoder().encode(photoData), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Returns decoded photo data - not null safe
	private byte[] decodePhotoData(final byte[] photoData) {
			return Base64.getDecoder().decode(photoData);
	}
	
	// Re-encodes photo data - not null safe
	private String reencodePhotoData(final byte[] photoData) {
		return this.encodePhotoData(this.decodePhotoData(photoData));
	}
	
	//Returns whether enhanced image editing is allowed via feature toggle.
	private boolean getAllowEnhancedImageEditor() {
		return this.featureToggles
				.get("offenderphoto" ,"allowEnhancedImageEditor");
	}
}