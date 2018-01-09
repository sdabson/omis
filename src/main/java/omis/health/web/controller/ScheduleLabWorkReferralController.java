package omis.health.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.HealthRequest;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.report.UnitReportService;
import omis.health.service.LabWorkManager;
import omis.health.service.LabWorkReferralScheduler;
import omis.health.validator.ScheduleLabWorkReferralFormValidator;
import omis.health.web.controller.delegate.HealthControllerDelegate;
import omis.health.web.form.DefaultLabWorkSelectionForm;
import omis.health.web.form.LabWorkSampleItem;
import omis.health.web.form.ReferralType;
import omis.health.web.form.ScheduleLabWorkReferralForm;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderReportService;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;

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

/**
 * Controller for scheduling lab work referrals.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 6, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/referral/labWork")
@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class ScheduleLabWorkReferralController {
	
	/* View names */
	
	private static final String EDIT_FORM_VIEW_NAME 
		= "/health/referral/labWork/schedule/edit";
	
	private static final String LAB_WORK_SAMPLE_ITEM_VIEW_NAME
		= "/health/referral/labWork/schedule/includes/labWorkSampleItem";
	
	private static final String DEFAULT_PROVIDER_ASSIGNMENT_OPTIONS_VIEW_NAME
		= "/health/referral/labWork/includes/defaultByProviderOptions";
	
	private static final String BY_PROVIDER_OPTIONS_VIEW_NAME
		= "/health/referral/labWork/includes/byProviderOptions";
	
	/* Model keys. */
	
	private static final String LAB_FORM_MODEL_KEY 
		= "scheduleLabWorkReferralForm";
	
	private static final String LABS_MODEL_KEY = "labs";
	
	private static final String FACILITY_MODEL_KEY = "facility";
	
	private static final String LAB_WORK_CATEGORIES_MODEL_KEY 
		= "labWorkCategories";
	
	private static final String LAB_WORK_REFERRAL_MODEL_KEY = "labWorkReferral";
	
	private static final String OFFENDER_SUMMARY_MODEL_KEY = "offenderSummary";
	
	private static final String WEEK_START_DATE_MODEL_KEY = "weekStartDate";
	
	private static final String SCHEDULE_LAB_WORK_REFERRAL_OPERATION_MODEL_KEY
		= "operation";
	
	private static final String LAB_WORK_SAMPLE_ITEM_INDEX_MODEL_KEY 
		= "labWorkSampleItemIndex";
	
	private static final String UNIT_ABBREVIATION_MODEL_KEY
		= "unitAbbreviation";
	
	private static final String HEALTH_REQUEST_MODEL_KEY
		= "healthRequest";
	
	private static final String PROVIDER_ASSIGNMENTS_MODEL_KEY
		= "providerAssignments";
	
	private static final String DEFAULT_BY_PROVIDER_ASSIGNMENTS_MODEL_KEY
		= "defaultByProviderAssignments";
	
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
	
	private static final String DEFAULT_NO_MEDS_MODEL_KEY = "defaultNoMeds";
	
	private static final String PROVIDERS_ON_DATES_MODEL_KEY
		= "providersOnDates";
	
	/* Services. */
	
	@Autowired
	@Qualifier("labWorkManager")
	private LabWorkManager labWorkManager;
	
	@Autowired
	@Qualifier("labWorkReferralScheduler")
	private LabWorkReferralScheduler labWorkReferralScheduler;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("healthControllerDelegate")
	private HealthControllerDelegate healthControllerDelegate;
	
	/* Report Services. */

	@Autowired
	@Qualifier("offenderReportService")
	private OffenderReportService offenderReportService;

	@Autowired
	@Qualifier("unitReportService")
	private UnitReportService unitReportService;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("facilityPropertyEditorFactory")
	private PropertyEditorFactory facilityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
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
	@Qualifier("labWorkPropertyEditorFactory")
	private PropertyEditorFactory labWorkPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labWorkReferralPropertyEditorFactory")
	private PropertyEditorFactory labWorkReferralPropertyEditorFactory;
	
	@Autowired
	@Qualifier("providerAssignmentPropertyEditorFactory")
	private PropertyEditorFactory providerAssignmentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("healthRequestPropertyEditorFactory")
	private PropertyEditorFactory healthRequestPropertyEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("scheduleLabWorkReferralFormValidator")
	private ScheduleLabWorkReferralFormValidator 
	scheduleLabWorkReferralFormValidator;
	
	/**
	 * Creates a new lab request.
	 * 
	 * @param facility facility
	 * @param defaultOffender default offender
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "createLabWorkScreenName",
			descriptionKey = "createLabWorkScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('LAB_WORK_CREATE')")
	public ModelAndView create(@RequestParam(value = "facility",
		required = true) final Facility facility,
		@RequestParam(value = "defaultOffender", required = false)
		final Offender defaultOffender) {
		ModelMap map = new ModelMap();
		ScheduleLabWorkReferralForm form = new ScheduleLabWorkReferralForm();
		map.addAttribute(LAB_WORK_SAMPLE_ITEM_INDEX_MODEL_KEY, 0);
		if (defaultOffender != null) {
			form.setOffenderRequired(false);
			this.addOffenderSummary(map, defaultOffender, new Date());
		} else {
			form.setOffenderRequired(true);
		}
		return this.prepareCreateMav(map, form, facility, 
				ScheduleLabWorkReferralOperation.SCHEDULE, null);
	}
	
	/**
	 * Displays screen to schedule a lab work referral with a request.
	 * 
	 * @param healthRequest health request
	 * @return model and view to schedule a new lab work referral with a request
	 */
	@RequestContentMapping(nameKey = "createLabWorkFromRequestScreenName",
			descriptionKey = "createLabWorkFromRequestScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/scheduleFromRequest.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('LAB_WORK_CREATE')")
	public ModelAndView createFromRequest(
			@RequestParam(value = "request", required = false) 
			final HealthRequest healthRequest) {
		ModelMap map = new ModelMap();
		ScheduleLabWorkReferralForm form = new ScheduleLabWorkReferralForm();
		form.setOffender(healthRequest.getOffender());
		map.addAttribute(HEALTH_REQUEST_MODEL_KEY, healthRequest);
		this.addOffenderSummary(map, healthRequest.getOffender(), new Date());
		form.setOffenderRequired(false);
		map.addAttribute(LAB_WORK_SAMPLE_ITEM_INDEX_MODEL_KEY, 0);
		return this.prepareCreateMav(map, form, healthRequest.getFacility(), 
				ScheduleLabWorkReferralOperation.SCHEDULE, null);
	}
	
	/**
	 * Saves a new lab work.
	 * 
	 * @param form lab work form
	 * @param facility facility
	 * @param defaultOffender default offender
	 * @param result binding result
	 * @return model and view
	 * @throws DuplicateEntityFoundException 
	 */
	@RequestContentMapping(nameKey = "saveLabWorkName",
			descriptionKey = "saveLabWorkDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('HEALTH_ADMIN')" 
			+ " or hasRole('LAB_WORK_CREATE')")
	public ModelAndView save(final ScheduleLabWorkReferralForm form, 
			@RequestParam(value = "facility", required = true)
			final Facility facility, @RequestParam(value = "defaultOffender", 
			required = false) final Offender defaultOffender,
			final BindingResult result) 
		throws DuplicateEntityFoundException {
		this.scheduleLabWorkReferralFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			final int labWorkSampleIndex = this.assignSampleIndexValue(
					form.getLabWorkSampleItems());
			map.addAttribute(LAB_WORK_SAMPLE_ITEM_INDEX_MODEL_KEY, 
					labWorkSampleIndex);
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX 
					+ LAB_FORM_MODEL_KEY, result);
			map.addAttribute(PROVIDERS_ON_DATES_MODEL_KEY, 
					this.prepareProvidersOnDates(facility, form));
			if (defaultOffender != null) {
				Date currentDate = new Date();
				this.addOffenderSummary(map, defaultOffender, currentDate);
			}
			return this.prepareCreateMav(map, form, facility,
					ScheduleLabWorkReferralOperation.SCHEDULE, null);
		}
		final Offender offender;
		if (form.getOffenderRequired()) {
			offender = form.getOffender();
		} else {
			offender = defaultOffender;
		}
		LabWorkReferral labWorkReferral = this.labWorkReferralScheduler
				.schedule(facility, offender, form.getSampleDate(),
						form.getSchedulingNotes());
		for (LabWorkSampleItem sampleItem : form.getLabWorkSampleItems()) {
			if (sampleItem.getProcess() != null && sampleItem.getProcess()) {
				this.labWorkReferralScheduler.addLabWork(labWorkReferral, 
					sampleItem.getLabWorkCategory(), sampleItem.getSampleDate(),
					sampleItem.getSampleLab(), sampleItem.getSampleNotes(), 
					sampleItem.getSampleTaken(), new LabWorkOrder(sampleItem
							.getByProvider(), sampleItem.getOrderDate()), 
					new LabWorkSampleRestrictions(
							sampleItem.getNothingPerOral(), 
							sampleItem.getNoLeaky(), 
							sampleItem.getNoMeds()), 
					sampleItem.getSchedulingNotes());
			}
		}
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(facility, 
						labWorkReferral.getOffenderAppointmentAssociation()
						.getOffender(), null, null, ReferralType.LAB, null));
	}
	
	/**
	 * Schedules the lab work referral from the specified health request.
	 * 
	 * @param healthRequest health request
	 * @param form schedule lab work referral form.
	 * @param result binding result
	 * @return model and view
	 * @throws DuplicateEntityFoundException if the lab work referral is
	 * already scheduled
	 */
	@RequestContentMapping(nameKey = "saveLabWorkFromRequestName",
			descriptionKey = "saveLabWorkFromRequestDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/scheduleFromRequest.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('HEALTH_ADMIN')" 
			+ " or hasRole('LAB_WORK_CREATE')")
	public ModelAndView saveFromRequest(
		@RequestParam(value = "request", required = false) 
		final HealthRequest healthRequest, 
		final ScheduleLabWorkReferralForm form, final BindingResult result) 
		throws DuplicateEntityFoundException {
		this.scheduleLabWorkReferralFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			final int labWorkSampleIndex = this.assignSampleIndexValue(
					form.getLabWorkSampleItems());
			map.addAttribute(LAB_WORK_SAMPLE_ITEM_INDEX_MODEL_KEY,
					labWorkSampleIndex);
			map.addAttribute(PROVIDERS_ON_DATES_MODEL_KEY, 
					this.prepareProvidersOnDates(
							healthRequest.getFacility(), form));
			this.addOffenderSummary(map, healthRequest.getOffender(), 
					new Date());
			return this.prepareCreateMav(map, form, healthRequest
					.getFacility(), ScheduleLabWorkReferralOperation.SCHEDULE, 
					null);
		}		
		LabWorkReferral labWorkReferral = this.labWorkReferralScheduler
				.scheduleFromRequest(healthRequest, form.getSampleDate(), 
						form.getSchedulingNotes());
		for (LabWorkSampleItem sampleItem : form.getLabWorkSampleItems()) {
			if (sampleItem.getProcess() != null && sampleItem.getProcess()) {
				this.labWorkReferralScheduler.addLabWork(labWorkReferral, 
					sampleItem.getLabWorkCategory(), sampleItem.getSampleDate(),
					sampleItem.getSampleLab(), sampleItem.getSampleNotes(), 
					sampleItem.getSampleTaken(), new LabWorkOrder(sampleItem
							.getByProvider(), sampleItem.getOrderDate()), 
					new LabWorkSampleRestrictions(
							sampleItem.getNothingPerOral(), 
							sampleItem.getNoLeaky(), 
							sampleItem.getNoMeds()), 
					sampleItem.getSchedulingNotes());
			}
		}
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(healthRequest
						.getFacility(), 
						labWorkReferral.getOffenderAppointmentAssociation()
						.getOffender(), null, null, ReferralType.LAB, null));
	}

	/**
	 * Preparation for a lab work to be edited.
	 * 
	 * @param labWorkReferral lab work referral
	 * @param facility facility
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "editLabWorkScreenName",
			descriptionKey = "editLabWorkScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/editSchedule.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('LAB_WORK_EDIT') or hasRole('LAB_WORK_VIEW')")
	public ModelAndView editSchedule(@RequestParam(value = "labWorkReferral", 
		required = true) final LabWorkReferral labWorkReferral, 
		@RequestParam(value = "facility", required = true) 
		final Facility facility) {
		ModelMap map = new ModelMap();
		ScheduleLabWorkReferralForm form = new ScheduleLabWorkReferralForm();
		this.populateForm(form, labWorkReferral);
		final int labWorkSampleIndex;
		if (form.getLabWorkSampleItems() == null) {
			labWorkSampleIndex = 0;
		} else {
			labWorkSampleIndex = form.getLabWorkSampleItems().size();
		}
		map.addAttribute(LAB_WORK_SAMPLE_ITEM_INDEX_MODEL_KEY, 
				labWorkSampleIndex);
		this.addOffenderSummary(map, labWorkReferral
				.getOffenderAppointmentAssociation().getOffender(), 
				new Date());
		return this.prepareEditMav(map, form, labWorkReferral, facility,
				ScheduleLabWorkReferralOperation.EDIT);
	}
	
	/**
	 * Updates an existing lab work with values from the specified form.
	 * 
	 * @param form lab work form
	 * @param labWorkReferral lab work referral
	 * @param facility facility
	 * @param result binding result
	 * @return model and view
	 * @throws DuplicateEntityFoundException duplicate entity found exception 
	 */
	@RequestContentMapping(nameKey = "updateLabWorkName",
			descriptionKey = "updateLabWorkDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.FORM_SUBMIT)
	@RequestMapping(value = "/editSchedule.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('LAB_WORK_EDIT')")
	public ModelAndView update(final ScheduleLabWorkReferralForm form, 
			@RequestParam(value = "labWorkReferral", required = true) 
			final LabWorkReferral labWorkReferral, 
			@RequestParam(value = "facility", required = true)
			final Facility facility, final BindingResult result) 
		throws DuplicateEntityFoundException {
		this.scheduleLabWorkReferralFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX 
					+ LAB_FORM_MODEL_KEY, result);
			final int labWorkSampleIndex;
			if (form.getLabWorkSampleItems() == null) {
				labWorkSampleIndex = 0;
			} else {
				labWorkSampleIndex = form.getLabWorkSampleItems().size();
			}
			map.addAttribute(LAB_WORK_SAMPLE_ITEM_INDEX_MODEL_KEY, 
					labWorkSampleIndex);
			return this.prepareEditMav(map, form, labWorkReferral, facility,
					ScheduleLabWorkReferralOperation.EDIT);
		}
		this.labWorkReferralScheduler.update(labWorkReferral, 
				form.getSampleDate(), form.getSchedulingNotes());
		for (LabWorkSampleItem sampleItem : form.getLabWorkSampleItems()) {
			if (sampleItem.getProcess() != null && sampleItem.getProcess()) {
				if (sampleItem.getLabWork() != null) {
						this.labWorkReferralScheduler.updateLabWork(
							labWorkReferral,
							sampleItem.getLabWork(), 
							sampleItem.getLabWorkCategory(),
							sampleItem.getSampleDate(),
							sampleItem.getSampleLab(), 
							sampleItem.getSampleNotes(),
							sampleItem.getSampleTaken(), new LabWorkOrder(
									sampleItem.getByProvider(), 
									sampleItem.getOrderDate()), 
							new LabWorkSampleRestrictions(
									sampleItem.getNothingPerOral(), 
									sampleItem.getNoLeaky(), 
									sampleItem.getNoMeds()), 
							sampleItem.getSchedulingNotes());
				} else {
						this.labWorkReferralScheduler.addLabWork(
							labWorkReferral, 
							sampleItem.getLabWorkCategory(), 
							sampleItem.getSampleDate(),
							sampleItem.getSampleLab(),
							sampleItem.getSampleNotes(), 
							sampleItem.getSampleTaken(), new LabWorkOrder(
									sampleItem.getByProvider(), 
									sampleItem.getOrderDate()), 
							new LabWorkSampleRestrictions(
									sampleItem.getNothingPerOral(), 
									sampleItem.getNoLeaky(), 
									sampleItem.getNoMeds()), 
							sampleItem.getSchedulingNotes());
				}
				
			} else {
				this.labWorkReferralScheduler.removeLabWork(labWorkReferral, 
						sampleItem.getLabWork());
			}
		}
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(facility, 
						labWorkReferral.getOffenderAppointmentAssociation()
						.getOffender(), null, null, ReferralType.LAB, null));
	}
	
	/**
	 * Add lab work sample item.
	 * 
	 * @param form default lab work selection form
	 * @return model and view for a new lab work sample item
	 */
	@RequestContentMapping(nameKey = "addLabWorkSampleItemName",
			descriptionKey = "addLabWorkSampleItemDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/addLabWorkSampleItem.html", 
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')"
		+ " or hasRole('HEALTH_ADMIN')"
		+ " or hasROle('LAB_WORK_SAMPLE_ITEM')")
	public ModelAndView addLabWorkSampleItem(
			final DefaultLabWorkSelectionForm form) {
		ModelMap map = new ModelMap();
		map.addAttribute(LAB_WORK_CATEGORIES_MODEL_KEY, 
				this.labWorkReferralScheduler.findLabWorkCategories());
		map.addAttribute(LABS_MODEL_KEY, this.labWorkReferralScheduler
				.findLabs());
		map.addAttribute(LAB_WORK_SAMPLE_ITEM_INDEX_MODEL_KEY, 
				form.getIndex());
		if (form.getDefaultOrderDate() != null) {
			map.addAttribute(DEFAULT_ORDER_DATE_MODEL_KEY, form
					.getDefaultOrderDate());
			map.addAttribute(PROVIDER_ASSIGNMENTS_MODEL_KEY, 
					this.labWorkReferralScheduler.findProviders(
							form.getFacility(), form.getDefaultOrderDate()));
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
		return new ModelAndView(LAB_WORK_SAMPLE_ITEM_VIEW_NAME, map);
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
			+ " or hasRole('REFERRAL_LAB_WORK_SCHEDULE_VIEW')")
	public ModelAndView addOrderedByOptions(@RequestParam(value = "facility",
			required = true)final Facility facility, 
			@RequestParam(value = "orderDate", required = true) 
			final Date orderDate) {
		ModelMap map = new ModelMap();
		if (orderDate != null) {
			List<ProviderAssignment> providers = this.labWorkReferralScheduler
					.findProviders(facility, orderDate);
			map.addAttribute(PROVIDER_ASSIGNMENTS_MODEL_KEY, providers);
		}
		return new ModelAndView(BY_PROVIDER_OPTIONS_VIEW_NAME, map);
	}
	
	/**
	 * Returns the provider assignments and view needed to add providers for
	 * selection when creating a new lab work within a lab work referral.
	 * 
	 * @param facility facility
	 * @param orderDate order date
	 * @return model and view
	 */
	@RequestMapping(value = "/addDefaultOrderedByProviderOptions.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('HEALTH_ADMIN')" 
			+ " or hasRole('REFERRAL_LAB_WORK_SCHEDULE_VIEW')")
	public ModelAndView addDefaultOrderedByOptions(@RequestParam(value 
			= "facility", required = true)final Facility facility, 
			@RequestParam(value = "orderDate", required = true) 
			final Date orderDate) {
		ModelMap map = new ModelMap();
		if (orderDate != null) {
			List<ProviderAssignment> providers = this.labWorkReferralScheduler
					.findProviders(facility, orderDate);
			map.addAttribute(
					DEFAULT_BY_PROVIDER_ASSIGNMENTS_MODEL_KEY, providers);
		}
		return new ModelAndView(DEFAULT_PROVIDER_ASSIGNMENT_OPTIONS_VIEW_NAME, 
				map);
	}
	/* Helper Methods. */
	
	/*
	 * Assigns an appropriate value to lab work sample index depending on the
	 * set of specified lab work sample items.
	 */
	private int assignSampleIndexValue(
			final List<LabWorkSampleItem> sampleItems) {
		final int labWorkSampleIndex;
		if (sampleItems == null) {
			labWorkSampleIndex = 0;
		} else {
			labWorkSampleIndex = sampleItems.size();
		}
		return labWorkSampleIndex;
	}
	
	/*
	 * Prepares a model and view with the necessary items to create a new lab
	 * work.
	 */
	private ModelAndView prepareCreateMav(final ModelMap map,
			final ScheduleLabWorkReferralForm form, final Facility facility,
			final ScheduleLabWorkReferralOperation operation, final Date date) {
		map.addAttribute(LAB_FORM_MODEL_KEY, form);
		map.addAttribute(FACILITY_MODEL_KEY, facility);
		map.addAttribute(SCHEDULE_LAB_WORK_REFERRAL_OPERATION_MODEL_KEY, 
				operation);
		if (date != null) {
			map.addAttribute(DEFAULT_BY_PROVIDER_ASSIGNMENTS_MODEL_KEY,
					this.labWorkReferralScheduler.findProviders(
							facility, date));
		}
		this.prepareClosedText(map);
		return new ModelAndView(EDIT_FORM_VIEW_NAME, map); 
	};
	

	/*
	 * Prepares a model and view with values needed to edit an existing lab
	 * work.
	 */
	private ModelAndView prepareEditMav(final ModelMap map, 
			final ScheduleLabWorkReferralForm form, 
			final LabWorkReferral labWorkReferral, final Facility facility, 
			final ScheduleLabWorkReferralOperation operation) {
		map.addAttribute(OFFENDER_SUMMARY_MODEL_KEY, this.offenderReportService
				.summarizeOffender(labWorkReferral
						.getOffenderAppointmentAssociation().getOffender()));
		map.addAttribute(FACILITY_MODEL_KEY, facility);
		map.addAttribute(LAB_FORM_MODEL_KEY, form);
		map.addAttribute(LAB_WORK_REFERRAL_MODEL_KEY, labWorkReferral);
		map.addAttribute(SCHEDULE_LAB_WORK_REFERRAL_OPERATION_MODEL_KEY, 
				operation);
		this.prepareClosedText(map);
		Map<Long, List<ProviderAssignment>> providersOnDates
			= new HashMap<Long, List<ProviderAssignment>>();
		for (LabWorkSampleItem item : form.getLabWorkSampleItems()) {
			if (item.getOrderDate() != null) {
				List<ProviderAssignment> providers = 
						this.labWorkReferralScheduler.findProviders(facility, 
								item.getOrderDate());
				providersOnDates.put(item.getOrderDate().getTime(), 
						providers);
			}
		}
		map.addAttribute(PROVIDERS_ON_DATES_MODEL_KEY, 
				this.prepareProvidersOnDates(facility, form));
		return new ModelAndView(EDIT_FORM_VIEW_NAME, map);
	}
	
	/*
	 * Returns a hash map of lists of providers keyed to a date in which the
	 * provider assignment overlaps.
	 */
	private Map<Long, List<ProviderAssignment>> 
	prepareProvidersOnDates(final Facility facility, 
			final ScheduleLabWorkReferralForm form) {
		Map<Long, List<ProviderAssignment>> providersOnDates
			= new HashMap<Long, List<ProviderAssignment>>();
		for (LabWorkSampleItem item : form.getLabWorkSampleItems()) {
			if (item.getOrderDate() != null) {
				List<ProviderAssignment> providers = 
						this.labWorkReferralScheduler.findProviders(facility, 
								item.getOrderDate());
				providersOnDates.put(item.getOrderDate().getTime(), 
						providers);
			}
		}
		return providersOnDates;
	}
	/*
	 * Populates a lab work form with values from the specified lab work.
	 */
	private ScheduleLabWorkReferralForm populateForm(
			final ScheduleLabWorkReferralForm form, 
			final LabWorkReferral labWorkReferral) {
		form.setOffender(labWorkReferral.getOffenderAppointmentAssociation()
				.getOffender());
		List<LabWork> labWorks = this.labWorkReferralScheduler
				.findLabWorks(labWorkReferral);
		List<LabWorkSampleItem> sampleItems = 
				new ArrayList<LabWorkSampleItem>();
		for (LabWork labWork : labWorks) {
			LabWorkSampleItem newSampleItem = new LabWorkSampleItem();
			newSampleItem.setLabWork(labWork);
			newSampleItem.setLabWorkCategory(labWork.getLabWorkCategory());
			newSampleItem.setSampleLab(labWork.getSampleLab());
			newSampleItem.setSampleNotes(labWork.getSampleNotes());
			newSampleItem.setSchedulingNotes(labWork.getSchedulingNotes());
			newSampleItem.setSampleDate(labWork
					.getOffenderAppointmentAssociation().getAppointment()
					.getDate());
			newSampleItem.setSampleTaken(labWork.getSampleTaken());
			newSampleItem.setProcess(true);
			sampleItems.add(newSampleItem);
			LabWorkSampleRestrictions restrictions = labWork
					.getSampleRestrictions();
			if (restrictions != null) {
				newSampleItem.setNoLeaky(restrictions.getNoLeaky());
				newSampleItem.setNoMeds(restrictions.getNoMeds());
				newSampleItem.setNothingPerOral(restrictions
						.getNothingPerOral());
			}
			LabWorkOrder order = labWork.getOrder();
			if (order != null) {
				newSampleItem.setByProvider(order.getByAssignment());
				newSampleItem.setOrderDate(order.getDate());
			}
		}
		form.setLabWorkSampleItems(sampleItems);
		form.setSampleDate(labWorkReferral.getOffenderAppointmentAssociation()
				.getAppointment().getDate());
		form.setSchedulingNotes(labWorkReferral.getSchedulingNotes());
		return form;
	}
	
	/*
	 * Prepares closed text options for a lab work form.
	 */
	private ModelMap prepareClosedText(final ModelMap map) {
		map.addAttribute(LABS_MODEL_KEY, this.labWorkReferralScheduler
				.findLabs());
		map.addAttribute(LAB_WORK_CATEGORIES_MODEL_KEY, 
				this.labWorkManager.findLabWorkCategories());
		map.addAttribute(WEEK_START_DATE_MODEL_KEY, DateRange.findWeeklyRange(
				new Date()).getStartDate());
		return map;
	}
	
	/* Adds offender summary to model and view. */
	private void addOffenderSummary(final ModelMap map,
			final Offender offender, final Date date) {
		this.offenderSummaryModelDelegate.add(map, offender);
		String unitAbbreviation = this.unitReportService
				.findUnitAbbreviation(offender, date);
		map.addAttribute(UNIT_ABBREVIATION_MODEL_KEY, unitAbbreviation);
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
				LabWork.class,
				this.labWorkPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				"startTime",
				this.customDateEditorFactory
				.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class,
				"endTime",
				this.customDateEditorFactory
				.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(
				LabWorkCategory.class,
				this.labWorkCategoryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				LabWorkReferral.class,
				this.labWorkReferralPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				ProviderAssignment.class,
				this.providerAssignmentPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				HealthRequest.class,
				this.healthRequestPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
}