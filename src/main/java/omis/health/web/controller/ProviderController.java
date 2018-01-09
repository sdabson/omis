package omis.health.web.controller;

import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderSpecialty;
import omis.health.domain.ProviderTitle;
import omis.health.report.ProviderReportService;
import omis.health.report.ProviderSummary;
import omis.health.service.ProviderAssignmentManager;
import omis.health.validator.ProviderAssignmentFormValidator;
import omis.health.web.form.ProviderAssignmentForm;
import omis.health.web.form.ProviderType;
import omis.organization.domain.Organization;
import omis.person.domain.Person;
import omis.staff.exception.StaffAssignmentNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/** Provider controller for provider related operations.
 * 
 * @author Ryan Johns
 * @author Joel Norris
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 03, 2014)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/health/provider/")
@PreAuthorize("hasRole('ADMIN')"
		+ " or hasRole('HEALTH_ADMIN')"
		+ " or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class ProviderController {
	
	/* View names. */
	
	private static final String LIST_VIEW = "/health/provider/list";
	
	private static final String LIST_REDIRECT =	
			"redirect:/health/provider/list.html";
	
	private static final String PROVIDERS_ACTION_MENU =
			"/health/provider/includes/actionMenu";
	
	private static final String PROVIDER_ACTION_MENU =
			"/health/provider/includes/providerActionMenu";
	
	private static final String EDIT_VIEW_NAME =
			"/health/provider/edit";
	
	private static final String MEDICAL_FACILITY_CONTENT_VIEW_NAME = 
			"health/provider/includes/medicalFacility";
	
	private static final String PROVIDER_ASSIGNMENTS_VIEW_NAME =
			"health/provider/includes/assignmentOptions";
	
	/* Model keys. */
	
	private static final String PROVIDER_ASSIGNMENT_FORM_MODEL_KEY 
		= "providerAssignmentForm";
	
	private static final String TITLES_MODEL_KEY = "titles";
	
	private static final String FACILITY_MODEL_KEY = "facility";
	
	private static final String PROVIDER_ASSIGNMENT_MODEL_KEY
		= "providerAssignment";
	
	private static final String PROVIDER_LIST_MODEL_KEY
		= "providerList";
	
	private static final String PROVIDER_TYPES_MODEL_KEY
		= "providerTypes";
	
	private static final String MEDICAL_FACILITIES_MODEL_KEY 
		= "medicalFacilities";

	private static final String PROVIDER_ASSIGNMENTS_MODEL_KEY
		= "providerAssignments";

	private static final String DEFAULT_PROVIDER_ASSIGNMENT_MODEL_KEY
		= "defaultProviderAssignment";
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("facilityPropertyEditorFactory")
	private PropertyEditorFactory facilityPropertyEditorFactory;

	@Autowired
	@Qualifier("providerAssignmentPropertyEditorFactory")
	private PropertyEditorFactory providerAssignmentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("providerTitlePropertyEditorFactory")
	private PropertyEditorFactory providerTitlePropertyEditorFactory;
	
	@Autowired
	@Qualifier("organizationPropertyEditorFactory")
	private PropertyEditorFactory organizationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("providerSpecialtyPropertyEditorFactory")
	private PropertyEditorFactory providerSpecialtyPropertyEditorFactory;
	
	@Autowired
	@Qualifier("medicalFacilityPropertyEditorFactory")
	private PropertyEditorFactory medicalFacilityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;

	/* Report services. */
	
	@Autowired
	@Qualifier("providerReportService")
	private ProviderReportService providerReportService;
	
	/* Services. */
	
	@Autowired
	@Qualifier("providerAssignmentManager")
	private ProviderAssignmentManager providerAssignmentManager;
	
	/* Validators.*/
	
	@Autowired
	@Qualifier("providerAssignmentFormValidator")
	private ProviderAssignmentFormValidator providerAssignmentFormValidator;

	/* Constructor.*/
	
	/** Instantiates a default controller for providers. */
	public ProviderController() {
	//Default controller
	}
	
	/* Screens. */
	
	/** 
	 * Lists providers by facility, start date and end date.
	 * 
	 * @param facility facility provider is assigned.
	 * @param startDate date start date of range.
	 * @param endDate date end date of range.
	 * @return list of providers. */
	@RequestContentMapping(nameKey = "providerListScreenName",
			descriptionKey = "providerListScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.LISTING_SCREEN)
	@PreAuthorize("hasRole('ADMIN')"
			+ " or hasRole('HEALTH_ADMIN')"
			+ " or hasRole('PROVIDER_VIEW')")
	@RequestMapping("list.html")
	public ModelAndView list(
			@RequestParam(value = FACILITY_MODEL_KEY, required = true) final
			Facility facility,
			@RequestParam(value = "startDate", required = false)
			final Date startDate,
			@RequestParam(value = "endDate", required = false)
			final Date endDate) {
		final List<ProviderSummary> providers;
		if (startDate != null) {
			providers = this.providerReportService.findHealthProviders(
					facility, startDate, endDate);
		} else {
			providers = this.providerReportService.findHealthProviders(
					facility, new Date());
		}
		final ModelMap map = new ModelMap();
		map.put(PROVIDER_LIST_MODEL_KEY, providers);
		map.put(FACILITY_MODEL_KEY, facility);
		return new ModelAndView(LIST_VIEW, map);
	}

	/**
	 * Shows the edit form for creating a new provider.
	 * 
	 * @param facility facility
	 * @return model and view screen that allows new provider to be created
	 */
	@RequestMapping(value = "create.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')"
			+ " or hasRole('HEALTH_ADMIN')"
			+ " or hasRole('PROVIDER_VIEW')")
	public ModelAndView create(@RequestParam(value = "facility", 
		required = true) final Facility facility) {
		ModelMap map = new ModelMap();
		ProviderAssignmentForm form = new ProviderAssignmentForm();
		map.addAttribute(PROVIDER_ASSIGNMENT_FORM_MODEL_KEY, form);
		map.addAttribute(FACILITY_MODEL_KEY, facility);
		return this.prepareEditMav(map);
	}
	
	/**
	 * Displays a form allowing an existing provider to be edited.
	 * 
	 * @param assignment assignment 
	 * @return model and view to screen that allows existing provider to be
	 * edited
	 */
	@RequestContentMapping(nameKey = "providerEditScreenName",
			descriptionKey = "providerEditScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "edit.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('HEALTH_ADMIN')"
			+ " or hasRole('PROVIDER_VIEW')")
	public ModelAndView edit(@RequestParam(value = "assignment", 
				required = true)
			final  ProviderAssignment assignment) {
		ProviderAssignmentForm providerAssignmentForm = 
				new ProviderAssignmentForm();
		providerAssignmentForm.setProvider(assignment.getProvider());
		if (assignment.getDateRange() != null) {
			providerAssignmentForm.setStartDate(assignment.getDateRange()
					.getStartDate());
			providerAssignmentForm.setEndDate(assignment.getDateRange()
					.getEndDate());
		}
		if (assignment.getMedicalFacility() != null) {
			providerAssignmentForm.setMedicalFacility(
					assignment.getMedicalFacility());
			providerAssignmentForm.setProviderType(ProviderType.EXTERNAL);
		} else if (assignment.getContracted()) {
			providerAssignmentForm.setProviderType(ProviderType.CONTRACTED);
		} else {
			providerAssignmentForm.setProviderType(ProviderType.INTERNAL);
		}
		providerAssignmentForm.setTitle(assignment.getTitle());
		ModelMap map = new ModelMap();
		map.addAttribute(PROVIDER_ASSIGNMENT_MODEL_KEY, assignment);
		map.addAttribute(PROVIDER_ASSIGNMENT_FORM_MODEL_KEY, 
				providerAssignmentForm);
		map.addAttribute(FACILITY_MODEL_KEY, assignment.getFacility());
		return this.prepareEditMav(map);
	}
	
	/*
	 * Returns a model and view with the required items for editing an
	 * existing provider assignment.
	 */
	private ModelAndView prepareEditMav(final ModelMap map) {
		map.addAttribute(PROVIDER_TYPES_MODEL_KEY,
				ProviderType.values());
		map.addAttribute(TITLES_MODEL_KEY, 
				this.providerAssignmentManager.findTitles());
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/**
	 * Updates a provider.
	 * 
	 * @param providerAssignmentForm provider assignment form
	 * @param assignment provider assignment
	 * @param result binding results
	 * @return model and view to redirect to list URL
	 * @throws DateConflictException 
	 * @throws DuplicateEntityFoundException duplicate entity found exceptiion
	 */
	@RequestMapping(value = "edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')" + "or hasRole('UPDATE_PROVIDER')" 
	+ "or hasRole('VIEW_PROVIDER')")
	public ModelAndView update(@RequestParam(value = "assignment", 
			required = true) 
			final ProviderAssignment assignment,
			final ProviderAssignmentForm providerAssignmentForm, 
			final BindingResult result) throws DuplicateEntityFoundException, 
				DateConflictException {
		this.providerAssignmentFormValidator.validate(providerAssignmentForm, 
				result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX 
					+ PROVIDER_ASSIGNMENT_FORM_MODEL_KEY, result);
			map.addAttribute(PROVIDER_ASSIGNMENT_MODEL_KEY, assignment);
			if (providerAssignmentForm.getProviderType() 
					== ProviderType.EXTERNAL) {
				map.addAttribute(MEDICAL_FACILITIES_MODEL_KEY, 
						this.providerAssignmentManager
							.findMedicalFacilities());
			}
			map.addAttribute(FACILITY_MODEL_KEY, assignment.getFacility());
			return this.prepareEditMav(map);
		}
		ProviderAssignment updated = this.providerAssignmentManager
				.update(assignment, providerAssignmentForm.getTitle(), 
					new	DateRange(providerAssignmentForm.getStartDate(), 
							providerAssignmentForm.getEndDate()));
		return prepareListRedirect(updated.getFacility());
	}
			
	
	
	/**
	 * Saves a new provider.
	 * 
	 * @param facility facility
	 * @param providerAssignmentForm provider assignment form
	 * @param result binding results
	 * @return model and view to redirect to list URL
	 * @throws StaffAssignmentNotFoundException 
	 * @throws DuplicateEntityFoundException 
	 * @throws DateConflictException 
	 */
	@RequestMapping(value = "create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('HEALTH_ADMIN')"
			+ " or hasRole('PROVIDER_EDIT')") 
	public ModelAndView save(@RequestParam(value = "facility", required = true)
			final Facility facility,
			final ProviderAssignmentForm providerAssignmentForm, 
			final BindingResult result)
		throws DateConflictException,
					DuplicateEntityFoundException,
					StaffAssignmentNotFoundException {
		this.providerAssignmentFormValidator.validate(providerAssignmentForm, 
				result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(PROVIDER_ASSIGNMENT_FORM_MODEL_KEY, 
					providerAssignmentForm);
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX
					+ PROVIDER_ASSIGNMENT_FORM_MODEL_KEY, result);
			if (providerAssignmentForm.getProviderType() 
					== ProviderType.EXTERNAL) {
				map.addAttribute(MEDICAL_FACILITIES_MODEL_KEY, 
						this.providerAssignmentManager
						.findMedicalFacilities());
			}
			map.addAttribute(FACILITY_MODEL_KEY, facility);
			return this.prepareEditMav(map);
		}
		if (providerAssignmentForm.getProviderType() 
				== ProviderType.CONTRACTED) {
			this.providerAssignmentManager.assignContracted(
					providerAssignmentForm.getProvider(), 
					facility, 
					new DateRange(providerAssignmentForm.getStartDate(), 
							providerAssignmentForm.getEndDate()),
					providerAssignmentForm.getTitle());
		} else if (providerAssignmentForm.getProviderType() 
				== ProviderType.EXTERNAL) {
			this.providerAssignmentManager.assignExternal(
					providerAssignmentForm.getProvider(), 
					facility, providerAssignmentForm.getMedicalFacility(),
					new DateRange(providerAssignmentForm.getStartDate(), 
							providerAssignmentForm.getEndDate()), 
							providerAssignmentForm.getTitle());
		} else if (providerAssignmentForm.getProviderType() 
				== ProviderType.INTERNAL) {
			this.providerAssignmentManager
			.assign(providerAssignmentForm.getProvider(), facility, 
				new DateRange(providerAssignmentForm.getStartDate(), 
						providerAssignmentForm.getEndDate()),
				providerAssignmentForm.getTitle());
		} else {
			throw new UnsupportedOperationException(
					"Unknown operation: " 
							+ providerAssignmentForm.getProviderType());
		}
		return this.prepareListRedirect(facility);
	}
		
	// Prepares providers screen redirect
	private ModelAndView prepareListRedirect(
			final Facility facility) {
		return new ModelAndView(String.format(LIST_REDIRECT + "?facility=" 
			+ facility.getId()));
	}
	
	/**
	 * Displays the provider action menu.
	 * 
	 * @param providerAssignment provider assignment
	 * @return model and view
	 */
	@RequestMapping("providerActionMenu.html")
	public ModelAndView providerActionMenu(@RequestParam(value = "provider",
			required = true) final ProviderAssignment providerAssignment) {
		final ModelMap map = new ModelMap();
		map.put("provider", providerAssignment);

		return new ModelAndView(PROVIDER_ACTION_MENU, map);
	}

	/**
	 * Displays providers action menu.
	 * 
	 * @param facility facility
	 * @return model and view
	 */
	@RequestMapping("providersActionMenu.html")
	public ModelAndView providersActionMenu(
			@RequestParam(value = "facility", required = true)
			final Facility facility) {
		final ModelMap map = new ModelMap();
		map.put("facility", facility);

		return new ModelAndView(PROVIDERS_ACTION_MENU, map);
	}
	
	/**
	 * Returns the model and view for displaying medical facility content.
	 * 
	 * @return model and view.
	 */
	@RequestMapping("medicalFacilityContent.html")
	public ModelAndView showMedicalFacilityContent() {
		ModelMap map = new ModelMap();
		map.addAttribute(MEDICAL_FACILITIES_MODEL_KEY, 
				this.providerAssignmentManager.findMedicalFacilities());
		return new ModelAndView(MEDICAL_FACILITY_CONTENT_VIEW_NAME, map);
	}

	/**
	 * Returns options for assignments of external providers to medical facility
	 * on date.
	 * 
	 * @param medicalFacility medical facility
	 * @return options for assignment of external providers to medical facility
	 * on date
	 */
	@RequestMapping("findByMedicalFacility.html")
	public ModelAndView findByMedicalFacility(
			@RequestParam(value = "medicalFacility", required = true)
				final MedicalFacility medicalFacility,
			@RequestParam(value = "defaultProviderAssignment", required = false)
				final ProviderAssignment defaultProviderAssignment) {
		ModelMap map = new ModelMap();
		if (medicalFacility != null) {
			Date currentDate = new Date();
			List<ProviderAssignment> providerAssignments
				= this.providerAssignmentManager
					.findAssignmentsByMedicalFacility(
							medicalFacility, currentDate);
			map.addAttribute(PROVIDER_ASSIGNMENTS_MODEL_KEY,
						providerAssignments);
		}
		if (defaultProviderAssignment != null) {
			map.addAttribute(DEFAULT_PROVIDER_ASSIGNMENT_MODEL_KEY,
					defaultProviderAssignment);
		}
		return new ModelAndView(PROVIDER_ASSIGNMENTS_VIEW_NAME, map);
	}
	
	/** Init Binder.
	 * 
	 * @param binder binder 
	 */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Facility.class,
				this.facilityPropertyEditorFactory.createPropertyEditor());

		binder.registerCustomEditor(ProviderAssignment.class,
				this.providerAssignmentPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory
					.createCustomDateOnlyEditor(true));
		
		binder.registerCustomEditor(ProviderTitle.class,
				this.providerTitlePropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(Organization.class, 
				this.organizationPropertyEditorFactory.createPropertyEditor());
		
		binder.registerCustomEditor(ProviderSpecialty.class,
				this.providerSpecialtyPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(Person.class, 
				this.personPropertyEditorFactory.createPropertyEditor());
		
		binder.registerCustomEditor(MedicalFacility.class,
				this.medicalFacilityPropertyEditorFactory
					.createPropertyEditor());
		
		binder.registerCustomEditor(
				Organization.class,
				this.organizationPropertyEditorFactory
				.createPropertyEditor());
	}
}