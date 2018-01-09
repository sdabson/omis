package omis.health.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkResults;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.report.UnitReportService;
import omis.health.service.LabWorkManager;
import omis.health.validator.CreateLabWorksFormValidator;
import omis.health.validator.EditLabWorkFormValidator;
import omis.health.web.controller.delegate.HealthControllerDelegate;
import omis.health.web.form.CreateLabWorksForm;
import omis.health.web.form.DefaultLabWorkSelectionForm;
import omis.health.web.form.EditLabWorkForm;
import omis.health.web.form.LabWorkItem;
import omis.health.web.form.ReferralType;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;

/**
 * Controller for handling all lab work items.
 * <p>
 * This controller is meant to handle all aspects of a lab work for the initial
 * release of the health referral center in OMIS 3. It is planned that this
 * controller only remains in use so long as lab work referral related 
 * functionality is disabled.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sep 9, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/labWork/")
@PreAuthorize("hasRole('ADMIN')"
		+ " or hasRole('HEALTH_ADMIN')"
		+ " or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class LabWorkController {
	
	/* View names. */
	
	private static final String CREATE_VIEW_NAME = "/health/labWork/create";
	
	private static final String EDIT_VIEW_NAME = "/health/labWork/edit";
	
	private static final String LAB_WORK_ITEM_VIEW_NAME
		= "/health/labWork/includes/labWorkItem";
	
	private static final String BY_PROVIDER_OPTIONS_VIEW_NAME
		= "/health/labWork/includes/orderedByOptions";
	
	private static final String LAB_WORK_SCHEDULED_ROW_ACTION_MENU_VIEW =
			"/health/referral/includes/labWorkScheduledRowActionMenu";
	
	private static final String LAB_WORK_RESOLVED_ROW_ACTION_MENU_VIEW =
			"/health/referral/includes/labWorkResolvedRowActionMenu";
	
	/* Model keys. */
	
	private static final String CREATE_LAB_WORKS_FORM_MODEL_KEY
		= "createLabWorksForm";
	
	private static final String EDIT_LAB_WORK_FORM_MODEL_KEY
		= "editLabWorkForm";
	
	private static final String LABS_MODEL_KEY = "labs";
	
	private static final String LAB_WORK_CATEGORIES_MODEL_KEY
		= "labWorkCategories";
	
	private static final String PROVIDER_ASSIGNMENTS_MODEL_KEY
		= "providerAssignments";
	
	private static final String LAB_WORK_MODEL_KEY = "labWork";
	
	private static final String FACILITY_MODEL_KEY = "facility";
	
	private static final String DEFAULT_ORDER_DATE_MODEL_KEY
		= "defaultOrderDate";
	
	private static final String DEFAULT_ORDERED_BY_MODEL_KEY
		= "defaultOrderedBy";
	
	private static final String DEFAULT_SAMPLE_DATE_MODEL_KEY
		= "defaultSampleDate";
	
	private static final String DEFAULT_SAMPLE_LAB_MODEL_KEY
		= "defaultSampleLab";
	
	private static final String DEFAULT_NOTHING_PER_ORAL_MODEL_KEY
		= "defaultNothingPerOral";
	
	private static final String DEFAULT_NO_LEAKY_MODEL_KEY
		= "defaultNoLeaky";
	
	private static final String DEFAULT_NO_MEDS_MODEL_KEY
		= "defaultNoMeds";
	
	private static final String LAB_WORK_ITEM_INDEX_MODEL_KEY
		= "labWorkItemIndex";
	
	private static final String PROVIDERS_ON_DATES_MODEL_KEY
		= "providersOnDates";
	
	private static final String UNIT_ABBREVIATION_MODEL_KEY
		= "unitAbbreviation";
	
	private static final String DEFAULT_OFFENDER_MODEL_KEY = "defaultOffender";
	
	/* Services. */
	
	@Autowired
	@Qualifier("labWorkManager")
	private LabWorkManager labWorkManager;
	
	/* Report services. */
	
	@Autowired
	@Qualifier("unitReportService")
	private UnitReportService unitReportService;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("healthControllerDelegate")
	private HealthControllerDelegate healthControllerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("facilityPropertyEditorFactory")
	private PropertyEditorFactory facilityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labPropertyEditorFactory")
	private PropertyEditorFactory labPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labWorkCategoryPropertyEditorFactory")
	private PropertyEditorFactory labWorkCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("providerAssignmentPropertyEditorFactory")
	private PropertyEditorFactory providerAssignmentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labWorkPropertyEditorFactory")
	private PropertyEditorFactory labWorkPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("createLabWorksFormValidator")
	private CreateLabWorksFormValidator createLabWorksFormValidator;
	
	@Autowired
	@Qualifier("editLabWorkFormValidator")
	private EditLabWorkFormValidator editLabWorkFormValidator;
	/**
	 * Instantiates a default instance of lab work controller.
	 */
	public LabWorkController() {
		//Default constructor.
	}
	
	/* URL mapped methods. */
	
	@RequestMapping(value = "create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('LAB_WORK_CREATE')")
	public ModelAndView create(@RequestParam( value="facility", required = true)
			final Facility facility, @RequestParam(value = "defaultOffender", 
			required = false)final Offender offender) {
		ModelMap map = new ModelMap();
		CreateLabWorksForm form = new CreateLabWorksForm();
		if (offender != null) {
			form.setOffender(offender);
			form.setOffenderRequired(false);
			this.addOffenderSummary(map, offender, new Date());
			map.addAttribute(DEFAULT_OFFENDER_MODEL_KEY, offender);
		} else {
			form.setOffenderRequired(true);
		}
		map.addAttribute(LAB_WORK_ITEM_INDEX_MODEL_KEY, 0);
		map.addAttribute(FACILITY_MODEL_KEY, facility);
		map.addAttribute(CREATE_LAB_WORKS_FORM_MODEL_KEY, form);
		return new ModelAndView(CREATE_VIEW_NAME, this.prepareEditMap(map));
	}
	
	/**
	 * Saves lab work(s) from information found in the create lab works form's
	 * lab work items.
	 * 
	 * @param form create lab works form
	 * @param result binding result
	 * @return model and view
	 * @throws DuplicateEntityFoundException thrown when a duplicate lab work
	 * is found
	 */
	@RequestMapping(value = "create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('LAB_WORK_CREATE')")
	public ModelAndView save(CreateLabWorksForm form, 
			@RequestParam(value="facility", required = true) 
			final Facility facility, @RequestParam(value = "defaultOffender", 
			required = false)final Offender defaultOffender, 
			final BindingResult result) 
		throws DuplicateEntityFoundException {
		this.createLabWorksFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(LAB_WORK_ITEM_INDEX_MODEL_KEY, 
					this.assignLabWorkItemIndexValue(form.getLabWorkItems()));
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX 
					+ CREATE_LAB_WORKS_FORM_MODEL_KEY, result);
			map.addAttribute(FACILITY_MODEL_KEY, facility);
			map.addAttribute(CREATE_LAB_WORKS_FORM_MODEL_KEY, form);
			map.addAttribute(PROVIDERS_ON_DATES_MODEL_KEY, 
					this.prepareProvidersOnDates(facility, form));
			if (defaultOffender != null) {
				this.addOffenderSummary(map, defaultOffender, new Date());
				map.addAttribute(DEFAULT_OFFENDER_MODEL_KEY, defaultOffender);
			}
			return new ModelAndView(CREATE_VIEW_NAME, 
					this.prepareEditMap(map));
		}
		for (LabWorkItem item : form.getLabWorkItems()) {
			if (item.getProcess() != null && item.getProcess()) {
				this.labWorkManager.scheduleLabWork(form.getFacility(), 
						form.getOffender(), item.getLabWorkCategory(), 
						item.getSampleLab(), item.getSampleDate(), 
						item.getSampleTaken(), item.getSampleNotes(), 
						new LabWorkResults(item.getResultsLab(), 
								item.getResultsDate(), 
								item.getResultsNotes()), 
						new LabWorkOrder(item.getByProvider(), 
								item.getOrderDate()), 
						new LabWorkSampleRestrictions(item.getNothingPerOral(), 
								item.getNoLeaky(), item.getNoMeds()), 
						item.getSchedulingNotes());
			}
		}
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(facility, 
						form.getOffender(),  null,  null,  
						ReferralType.LAB, null));
	}
	
	/**
	 * Returns a view with a form for the purpose of editing the specified lab
	 * work.
	 * 
	 * @param labWork lab work
	 * @return model and view to edit lab work
	 */
	@RequestMapping(value = "edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('LAB_WORK_EDIT') or hasRole('LAB_WORK_VIEW')")
	public ModelAndView edit(@RequestParam(value = "labWork", required = true)
			final LabWork labWork, 
			@RequestParam(value="facility", required = true)
			final Facility facility) {
		ModelMap map = new ModelMap();
		EditLabWorkForm form = new EditLabWorkForm();
		map.addAttribute(EDIT_LAB_WORK_FORM_MODEL_KEY, 
				this.populateEditLabWorkForm(labWork, form));
		if (labWork.getOrder() != null) {
			map.addAttribute(PROVIDER_ASSIGNMENTS_MODEL_KEY, 
					this.labWorkManager.findProviders(
							labWork.getOffenderAppointmentAssociation()
							.getAppointment().getFacility(), 
							labWork.getOrder().getDate()));
		}
		map.addAttribute(FACILITY_MODEL_KEY, facility);
		map.addAttribute(LAB_WORK_MODEL_KEY, labWork);
		this.addOffenderSummary(map, labWork
				.getOffenderAppointmentAssociation().getOffender(), 
				new Date());
		return new ModelAndView(EDIT_VIEW_NAME, this.prepareEditMap(map));
	}
	
	/**
	 * Updates the specified lab work with values from the specified form.
	 * 
	 * @param form edit lab work form
	 * @param labWork lab work
	 * @param result binding result
	 * @return model and view for referral center
	 * @throws DuplicateEntityFoundException throws an exception if a duplicate
	 * lab work is found
	 */
	@RequestMapping(value = "edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('LAB_WORK_EDIT')")
	public ModelAndView update(final EditLabWorkForm form, 
			@RequestParam(value = "labWork", required = true) 
			final LabWork labWork,
			@RequestParam( value="facility", required = true)
			final Facility facility, final BindingResult result) 
		throws DuplicateEntityFoundException {
		this.editLabWorkFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(EDIT_LAB_WORK_FORM_MODEL_KEY, form);
			if (form.getOrderDate() != null) {
				map.addAttribute(PROVIDER_ASSIGNMENTS_MODEL_KEY,
							this.labWorkManager.findProviders(
									labWork.getOffenderAppointmentAssociation()
									.getAppointment().getFacility(), 
									form.getOrderDate()));
			}
			map.addAttribute(LAB_WORK_MODEL_KEY, labWork);
			map.addAttribute(FACILITY_MODEL_KEY, facility);
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX 
					+ EDIT_LAB_WORK_FORM_MODEL_KEY, result);
			this.addOffenderSummary(map, labWork
					.getOffenderAppointmentAssociation().getOffender(), 
					new Date());
			return new ModelAndView(EDIT_VIEW_NAME, this.prepareEditMap(map));
		}
		this.labWorkManager.updateLabWork(labWork, 
				form.getLabWorkCategory(), form.getSampleLab(), 
				form.getSampleDate(), form.getSampleTaken(), 
				form.getSampleNotes(), 
				new LabWorkResults(form.getResultsLab(), 
						form.getResultsDate(), 
						form.getResultsNotes()), 
				new LabWorkOrder(form.getByProvider(), form.getOrderDate()), 
				new LabWorkSampleRestrictions(form.getNothingPerOral(), 
						form.getNoLeaky(), form.getNoMeds()), 
						form.getSchedulingNotes());
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(facility, 
						labWork.getOffenderAppointmentAssociation()
						.getOffender(),  null,  null,  
						ReferralType.LAB, null));
	}
	
	/**
	 * Removes the specified lab work.
	 * 
	 * @param labWork lab work
	 * @param facility facility
	 * @return model and view for referral center.
	 */
	@RequestMapping(value = "remove.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('LAB_WORK_REMOVE')")
	public ModelAndView cancelLabWork(@RequestParam(value = "labWork", 
			required = true) final LabWork labWork, 
		@RequestParam(value = "facility", required = true) 
			final Facility facility) {
		Offender offender = labWork.getOffenderAppointmentAssociation()
				.getOffender(); 
		this.labWorkManager.removeLabWork(labWork);
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(facility, 
						offender,  null,  null,  
						ReferralType.LAB, null));
	}
	
	/**
	 * Adds a lab work item to a form for entering in information about a
	 * single lab work as part of a group of lab works.
	 * 
	 * @param form default lab work selection form
	 * @return model and view for a new lab work item
	 */
	@RequestMapping(value = "/addLabWorkItem.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')"
			+ " or hasRole('HEALTH_ADMIN')"
			+ " or hasRole('LAB_WORK_CREATE')")
	public ModelAndView addLabWorkItem(final DefaultLabWorkSelectionForm form) {
		ModelMap map = new ModelMap();
		if (form.getDefaultOrderDate() != null) {
			map.addAttribute(DEFAULT_ORDER_DATE_MODEL_KEY, 
					form.getDefaultOrderDate());
			map.addAttribute(PROVIDER_ASSIGNMENTS_MODEL_KEY,
					this.labWorkManager.findProviders(form.getFacility(), 
							form.getDefaultOrderDate()));
			map.addAttribute(DEFAULT_ORDERED_BY_MODEL_KEY,
					form.getDefaultOrderedBy());
		}
		map.addAttribute(DEFAULT_SAMPLE_DATE_MODEL_KEY,
				form.getDefaultSampleDate());
		map.addAttribute(DEFAULT_SAMPLE_LAB_MODEL_KEY,
				form.getDefaultSampleLab());
		map.addAttribute(DEFAULT_NOTHING_PER_ORAL_MODEL_KEY, 
				form.getDefaultNothingPerOral());
		map.addAttribute(DEFAULT_NO_LEAKY_MODEL_KEY, form.getDefaultNoLeaky());
		map.addAttribute(DEFAULT_NO_MEDS_MODEL_KEY, form.getDefaultNoMeds());
		map.addAttribute(LAB_WORK_ITEM_INDEX_MODEL_KEY, 
				form.getIndex());
		return new ModelAndView(LAB_WORK_ITEM_VIEW_NAME, 
				this.prepareEditMap(map));
	}
	
	/**
	 * Returns the provider assignments and view needed to add providers for
	 * selection when creating a new lab work within a lab work referral.
	 * 
	 * @param facility facility
	 * @param orderDate order date
	 * @return model and view
	 */
	@RequestMapping(value = "/addOrderedByProviderOptions.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('HEALTH_ADMIN')" 
			+ " or hasRole('LAB_WORK_CREATE')")
	public ModelAndView addOrderedByOptions(@RequestParam(value = "facility",
			required = true)final Facility facility, 
			@RequestParam(value = "orderDate", required = true) 
			final Date orderDate) {
		ModelMap map = new ModelMap();
		if (orderDate != null) {
			List<ProviderAssignment> providers = this.labWorkManager
					.findProviders(facility, orderDate);
			map.addAttribute(PROVIDER_ASSIGNMENTS_MODEL_KEY, providers);
		}
		return new ModelAndView(BY_PROVIDER_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Displays action menu for lab work scheduled rows.
	 * 
	 * @param facility facility
	 * @param labWork lab work
	 * @return action menu for lab work scheduled rows
	 */
	@RequestContentMapping(nameKey = "labWorkScheduledRowActionMenuName",
			descriptionKey = "labWorkScheduledRowActionMenuName",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/scheduledRowActionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('REFERRAL_CENTER')")
	public ModelAndView labWorkScheduledRowActionMenu(
			@RequestParam(value = "labWork", required = true)
				final LabWork labWork,
			@RequestParam (value = "facility", required = false)
				final Facility facility) {
		ModelMap map = new ModelMap();
		map.put("facility", facility);
		map.put("scheduled", labWork);
		return new ModelAndView(LAB_WORK_SCHEDULED_ROW_ACTION_MENU_VIEW,
				map);
	}
	
	/**
	 * Displays action menu for lab work resolved rows.
	 * 
	 * @param facility facility
	 * @param labWork lab work
	 * @return action menu for lab work resolved rows
	 */
	@RequestContentMapping(nameKey = "labWorkScheduledRowActionMenuName",
			descriptionKey = "labWorkScheduledRowActionMenuName",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/resolvedRowActionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('REFERRAL_CENTER')")
	public ModelAndView labWorkResolvedRowActionMenu(
			@RequestParam(value = "labWork", required = true)
				final LabWork labWork,
			@RequestParam (value = "facility", required = false)
				final Facility facility) {
		ModelMap map = new ModelMap();
		map.put("facility", facility);
		map.put("resolved", labWork);
		return new ModelAndView(LAB_WORK_RESOLVED_ROW_ACTION_MENU_VIEW,
				map);
	}
	/* Helper methods. */
	
	/* Adds offender summary to model and view. */
	private void addOffenderSummary(final ModelMap map,
			final Offender offender, final Date date) {
		this.offenderSummaryModelDelegate.add(map, offender);
		String unitAbbreviation = this.unitReportService
				.findUnitAbbreviation(offender, date);
		map.addAttribute(UNIT_ABBREVIATION_MODEL_KEY, unitAbbreviation);
	}
	
	/*
	 * Returns a hash map of lists of providers keyed to a date in which the
	 * provider assignment overlaps.
	 */
	private Map<Long, List<ProviderAssignment>> 
	prepareProvidersOnDates(final Facility facility, 
			final CreateLabWorksForm form) {
		Map<Long, List<ProviderAssignment>> providersOnDates
			= new HashMap<Long, List<ProviderAssignment>>();
		for (LabWorkItem item : form.getLabWorkItems()) {
			if (item.getOrderDate() != null) {
				List<ProviderAssignment> providers = 
						this.labWorkManager.findProviders(facility, 
								item.getOrderDate());
				providersOnDates.put(item.getOrderDate().getTime(), 
						providers);
			}
		}
		return providersOnDates;
	}

	/*
	 * Prepares a model map with options needed to create a new lab work, 
	 * or edit an existing one.
	 * 
	 * @param map model map
	 * @return model map
	 */
	private ModelMap prepareEditMap(final ModelMap map) {
		map.addAttribute(LABS_MODEL_KEY, this.labWorkManager.findLabs());
		map.addAttribute(LAB_WORK_CATEGORIES_MODEL_KEY, 
				this.labWorkManager.findLabWorkCategories());
		return map;
	}
	
	/*
	 * Populates the specified edit lab work form with properties from the
	 * specified lab work.
	 *  
	 * @param labWork lab work
	 * @param form edit lab work form
	 * @return populated edit lab work form
	 */
	private EditLabWorkForm populateEditLabWorkForm(final LabWork labWork, 
			final EditLabWorkForm form) {
		LabWorkOrder order = labWork.getOrder();
		LabWorkSampleRestrictions restrictions = 
				labWork.getSampleRestrictions();
		LabWorkResults results = labWork.getResults();
		if (order != null) {
			form.setOrderDate(order.getDate());
			form.setByProvider(order.getByAssignment());
		}
		if (restrictions != null) {
			form.setNothingPerOral(restrictions.getNothingPerOral());
			form.setNoLeaky(restrictions.getNoLeaky());
			form.setNoMeds(restrictions.getNoMeds());
		}
		if (results != null) {
			form.setResultsDate(results.getDate());
			form.setResultsLab(results.getLab());
			form.setResultsNotes(results.getNotes());
		}
		form.setSampleDate(labWork.getOffenderAppointmentAssociation()
				.getAppointment().getDate());
		form.setLabWorkCategory(labWork.getLabWorkCategory());
		form.setSampleLab(labWork.getSampleLab());
		form.setSampleNotes(labWork.getSampleNotes());
		form.setSchedulingNotes(labWork.getSchedulingNotes());
		form.setSampleTaken(labWork.getSampleTaken());
		return form;
	}
	
	/*
	 * Assigns the lab work item index according to the size of the specified 
	 * lab work item list. 
	 * 
	 * @param items list of lab work items
	 * @return int representing the lab work item index
	 */
	private int assignLabWorkItemIndexValue(final List<LabWorkItem> items) {
		final int labWorkIndex;
		if (items == null) {
			labWorkIndex = 0;
		} else {
			labWorkIndex = items.size();
		}
		return labWorkIndex;
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Facility.class,
				this.facilityPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(
				Lab.class,
				this.labPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				ProviderAssignment.class,
				this.providerAssignmentPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				LabWork.class,
				this.labWorkPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				LabWorkCategory.class,
				this.labWorkCategoryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
}