package omis.health.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.HealthRequest;
import omis.health.domain.InternalReferral;
import omis.health.domain.InternalReferralReason;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkRequirementRequest;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderLevel;
import omis.health.domain.ReferralLocationDesignator;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkResults;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.exception.HealthRequestFollowsUpMultipleReferralsException;
import omis.health.exception.ProviderScheduleException;
import omis.health.report.UnitReportService;
import omis.health.service.InternalReferralScheduler;
import omis.health.validator.ScheduleInternalReferralFormValidator;
import omis.health.web.controller.delegate.HealthControllerDelegate;
import omis.health.web.controller.delegate.InternalReferralControllerDelegate;
import omis.health.web.controller.delegate.LabWorkControllerDelegate;
import omis.health.web.controller.delegate.ProviderScheduleDelegate;
import omis.health.web.controller.delegate.ReferralSummaryControllerDelegate;
import omis.health.web.form.InternalProviderScheduleDayItem;
import omis.health.web.form.LabWorkAppointmentItem;
import omis.health.web.form.ReferralType;
import omis.health.web.form.ScheduleInternalReferralForm;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
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

/** Controller for scheduling internal referral related services.
 *
 * @author Ryan Johns
 * @author Stephen Abson
 * @author Joel Norris
 * @author Sheronda Vaughn
 * @version 0.1.0 (Apr 16, 2014)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/health/referral")
@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class ScheduleInternalReferralController {

	/* Model Keys. */

	private static final String START_DATE_MODEL_KEY = "startDate";

	private static final String END_DATE_MODEL_KEY = "endDate";

	private static final String FACILITY_MODEL_KEY = "facility";

	private static final String PROVIDER_ASSIGNMENTS_MODEL_KEY =
			"providerAssignments";

	private static final String HEALTH_REQUEST_MODEL_KEY = "healthRequest";

	private static final String REFERRAL_DESIGNATIONS_MODEL_KEY =
			"referralDesignationLocations";

	private static final String INTERNAL_PROVIDER_SCHEDULE_DAY_ITEMS_MODEL_KEY
		= "internalProviderScheduleDayItems";

	private static final String REASONS_MODEL_KEY = "reasons";

	private static final String PROVIDER_LEVELS_MODEL_KEY = "providerLevels";

	private static final String OPERATION_MODEL_KEY = "operation";

	private static final String CURRENT_LAB_WORK_INDEX_MODEL_KEY
		= "currentLabWorkIndex";

	private static final String UNIT_ABBREVIATION_MODEL_KEY
		= "unitAbbreviation";
	
	private static final String PROVIDERS_ON_DATES_MODEL_KEY
		= "providersOnDates";
	
	private static final String WEEK_START_DATE_MODEL_KEY = "weekStartDate";

	/* Services. */

	@Autowired
	@Qualifier("internalReferralScheduler")
	private InternalReferralScheduler internalReferralScheduler;

	/* Report service. */
	
	@Autowired
	@Qualifier("unitReportService")
	private UnitReportService unitReportService;

	/* Property Editors. */

	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;

	@Autowired
	@Qualifier("providerAssignmentPropertyEditorFactory")
	private PropertyEditorFactory providerAssignmentPropertyEditorFactory;

	@Autowired
	@Qualifier("facilityPropertyEditorFactory")
	private PropertyEditorFactory facilityPropertyEditorFactory;

	@Autowired
	@Qualifier("healthRequestPropertyEditorFactory")
	private PropertyEditorFactory healthRequestPropertyEditorFactory;

	@Autowired
	@Qualifier("internalReferralReasonPropertyEditorFactory")
	private PropertyEditorFactory internalReferralReasonPropertyEditorFactory;

	@Autowired
	@Qualifier("internalReferralPropertyEditorFactory")
	private PropertyEditorFactory internalReferralPropertyEditorFactory;

	@Autowired
	@Qualifier("providerLevelPropertyEditorFactory")
	private PropertyEditorFactory providerLevelPropertyEditorFactory;

	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labWorkCategoryPropertyEditorFactory")
	private PropertyEditorFactory labWorkCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labPropertyEditorFactory")
	private PropertyEditorFactory labPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labWorkPropertyEditorFactory")
	private PropertyEditorFactory labWorkPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labWorkRequirementRequestPropertyEditorFactory")
	private PropertyEditorFactory 
	labWorkRequirementRequestPropertyEditorFactory;

	/* Helpers. */

	@Autowired
	@Qualifier("healthControllerDelegate")
	private HealthControllerDelegate healthControllerDelegate;

	@Autowired
	@Qualifier("internalReferralControllerDelegate")
	private InternalReferralControllerDelegate
	internalReferralControllerDelegate;
	
	@Autowired
	@Qualifier("labWorkControllerDelegate")
	private LabWorkControllerDelegate labWorkControllerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;

	@Autowired
	@Qualifier("providerScheduleDelegate")
	private ProviderScheduleDelegate providerScheduleDelegate;
	
	@Autowired
	@Qualifier("referralSummaryControllerDelegate")
	private ReferralSummaryControllerDelegate referralSummaryControllerDelegate;

	/* Validators. */

	@Autowired
	@Qualifier("scheduleInternalReferralFormValidator")
	private ScheduleInternalReferralFormValidator
	scheduleInternalReferralFormValidator;

	/** Instantiates a controller for scheduling inside referrals. */
	public ScheduleInternalReferralController() {
		// Default instantiation
	}

	/**
	 * Displays screen to schedule an internal referral with a request.
	 *
	 * @param healthRequest health request
	 * @return model and view to scheduler inside referral with a request
	 * @throws HealthRequestFollowsUpMultipleReferralsException if the request
	 * follows up more than one referral
	 */
	@RequestContentMapping(nameKey = "scheduleRequestScreenName",
			descriptionKey = "scheduleRequestScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/internal/scheduleFromRequest.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('INTERNAL_REFERRAL_SCHEDULE_EDIT')")
	public ModelAndView createFromRequest(
			@RequestParam(value = "request", required = true)
				final HealthRequest healthRequest)
					throws HealthRequestFollowsUpMultipleReferralsException {
		final Date currentDate = new Date();
		final ScheduleInternalReferralForm form =
				new ScheduleInternalReferralForm();
		form.setScheduleDate(currentDate);
		form.setOffender(healthRequest.getOffender());
		form.setProviderLevel(healthRequest.getProviderLevel());
		final ModelMap map = this.prepareCreateMap(currentDate,
				healthRequest.getFacility(), form,
				ScheduleInternalReferralOperation.SCHEDULE);
		this.addOffenderSummary(map, healthRequest.getOffender(), currentDate);
		map.addAttribute(HEALTH_REQUEST_MODEL_KEY, healthRequest);
		this.referralSummaryControllerDelegate.addOriginalForActionRequest(
				map, healthRequest, currentDate);
		List<LabWorkRequirementRequest> labWorkRequirementRequests =
				this.internalReferralScheduler.findLabWorkRequirementRequests(
						healthRequest);
		Map<Long, List<ProviderAssignment>> providersOnDates
			= new HashMap<Long, List<ProviderAssignment>>();
		int currentLabWorkItemIndex = 0;
		for (LabWorkRequirementRequest request : labWorkRequirementRequests) {
			LabWorkAppointmentItem item = new LabWorkAppointmentItem();
			item.setDate(request.getSampleDate());
			item.setLabWorkCategory(request.getCategory());
			item.setSchedulingNotes(request.getSchedulingNotes());
			item.setSampleLab(request.getSampleLab());
			item.setResultsLab(request.getResultsLab());
			item.setLabWorkRequirementRequest(request);
			if (request.getOrder() != null) {
				item.setOrderDate(request.getOrder().getDate());
				item.setByProvider(request.getOrder().getByAssignment());
			}
			if (request.getSampleRestrictions() != null) {
				item.setNoLeaky(request.getSampleRestrictions().getNoLeaky());
				item.setNothingPerOral(request.getSampleRestrictions()
						.getNothingPerOral());
				item.setNoMeds(request.getSampleRestrictions().getNoMeds());
			}
			item.setProcess(true);
			form.getLabWork().add(item);
			if (item.getOrderDate() != null) {
				List<ProviderAssignment> providers = 
						this.labWorkControllerDelegate.findProviders(
								healthRequest.getFacility(), item
								.getOrderDate());
				providersOnDates.put(item.getOrderDate().getTime(), 
						providers);
			}
			currentLabWorkItemIndex++;
		}
		this.internalReferralControllerDelegate.prepareLabWorkItemRow(map);
		map.addAttribute(this.internalReferralControllerDelegate
				.getCurrentLabWorkIndexModelKey(), currentLabWorkItemIndex);
		map.addAttribute(PROVIDERS_ON_DATES_MODEL_KEY, providersOnDates);
		return new ModelAndView(this.internalReferralControllerDelegate
				.getScheduleInternalReferralFormViewName(), map);
	}

	/**
	 * Displays screen to schedule an internal referral without a request.
	 *
	 * @param facility facility
	 * @param defaultOffender offender
	 * @return model and view to schedule an inside referral without a request
	 */
	@RequestContentMapping(nameKey = "scheduleRequestScreenName",
			descriptionKey = "scheduleRequestScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(
			value = "/internal/schedule.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('INTERNAL_REFERRAL_SCHEDULE_EDIT')")
	public ModelAndView create(
			@RequestParam(value = "facility", required = true)
				final Facility facility, 
			@RequestParam(value = "defaultOffender", required = false)
				final Offender defaultOffender) {
		final Date currentDate = new Date();
		final ScheduleInternalReferralForm scheduleInternalReferralForm
			= new ScheduleInternalReferralForm();
		scheduleInternalReferralForm.setScheduleDate(currentDate);
		if (defaultOffender != null) {
			scheduleInternalReferralForm.setOffenderRequired(false);
		} else {
			scheduleInternalReferralForm.setOffenderRequired(true);
		}
		final ModelMap map = this.prepareCreateMap(currentDate,
				facility, scheduleInternalReferralForm,
				ScheduleInternalReferralOperation.SCHEDULE);
		if (defaultOffender != null) {
			this.addOffenderSummary(map, defaultOffender, currentDate);
		}
		return new ModelAndView(this.internalReferralControllerDelegate
				.getScheduleInternalReferralFormViewName(), map);
	}
	
	/*
	 * Returns a list of providers for the specified facility on the specified
	 * date for use in a lab work appointment item closed text option.
	 */
	private List<ProviderAssignment> populateByProviders(
			final Date orderDate, final Facility facility) {
		return this.labWorkControllerDelegate.findProviders(facility, 
							orderDate);
	}

	/**
	 * Schedules the internal referral.
	 *
	 * @param healthRequest health request
	 * @param form schedule request form
	 * @param result binding result
	 * @return model and view
	 * @throws DuplicateEntityFoundException if the internal referral is already
	 * scheduled
	 * @throws DateConflictException if the dates of the referral
	 * conflict with an existing referral
	 * @throws ProviderScheduleException if provider not scheduled for date.
	 * @throws HealthRequestFollowsUpMultipleReferralsException if the request
	 * follows up more than one referral
	 */
	@RequestContentMapping(nameKey = "createScheduleRequestScreenName",
			descriptionKey = "createScheduleReqeustScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/internal/scheduleFromRequest.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('INTERNAL_REFERRAL_SCHEDULE_EDIT')")
	public ModelAndView saveFromRequest(
			@RequestParam(value = "request", required = true)
			final HealthRequest healthRequest,
			final ScheduleInternalReferralForm form, final BindingResult result)
		throws DuplicateEntityFoundException, DateConflictException,
		ProviderScheduleException, HealthRequestFollowsUpMultipleReferralsException {
		this.scheduleInternalReferralFormValidator.validate(form, result);
		final Date currentDate = new Date();
		if (result.hasErrors()) {
			final Date scheduleDate;
			if (form.getScheduleDate() != null) {
				scheduleDate = form.getScheduleDate();
			} else {
				scheduleDate = currentDate;
			}
			final ModelMap map = this.prepareRedisplayMap(scheduleDate,
					healthRequest.getFacility(),
					ScheduleInternalReferralOperation.SCHEDULE,
					form, result);
			map.addAttribute(HEALTH_REQUEST_MODEL_KEY, healthRequest);
			this.internalReferralControllerDelegate.prepareLabWorkItemRow(map);
			this.referralSummaryControllerDelegate.addOriginalForActionRequest(
					map, healthRequest, currentDate);
			this.addOffenderSummary(map, healthRequest.getOffender(),
					currentDate);
			Map<Long, List<ProviderAssignment>> providersOnDates
				= new HashMap<Long, List<ProviderAssignment>>();
			for (LabWorkAppointmentItem item : form.getLabWork()) {
				if (item.getOrderDate() != null) {
					providersOnDates.put(item.getOrderDate().getTime(), 
							this.populateByProviders(item.getOrderDate(), 
							healthRequest.getFacility()));
				}
			}
			map.addAttribute(PROVIDERS_ON_DATES_MODEL_KEY, providersOnDates);
			return new ModelAndView(this.internalReferralControllerDelegate
					.getScheduleInternalReferralFormViewName(), map);
		}
		InternalReferral referral = 
				this.scheduleInternalReferralFromRequest(healthRequest,
				form.getScheduleDate(),	form.getReason(),
				form.getProviderLevel(),
				form.getProviderAssignment(), form.getNotes(),
				form.getLocationDesignator());
		this.scheduleRequiredLabWork(form.getLabWork(), referral);
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(
						healthRequest.getFacility(),
						healthRequest.getOffender(), null, null,
						ReferralType.INTERNAL_MEDICAL, null));
	}

	/**
	 * Schedules the internal referral.
	 *
	 * @param facility facility
	 * @param defaultOffender offender
	 * @param form form to schedule internal referrals
	 * @param result binding result
	 * @return redirect to facility health center
	 * @throws DuplicateEntityFoundException if the internal referral is already
	 * scheduled
	 * @throws DateConflictException if the dates of the referral
	 * conflict with an existing referral
	 * @throws ProviderScheduleException if provider is not scheduled for the
	 * date.
	 */
	@RequestContentMapping(nameKey = "scheduleRequestScreenName",
			descriptionKey = "scheduleRequestScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(
			value = "/internal/schedule.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('INTERNAL_REFERRAL_SCHEDULE_EDIT')")
	public ModelAndView save(
			@RequestParam(value = "facility", required = true)
				final Facility facility,
			@RequestParam(value = "defaultOffender", required = false)
				final Offender defaultOffender,
			final ScheduleInternalReferralForm form, final BindingResult result)
		throws DuplicateEntityFoundException, DateConflictException,
		ProviderScheduleException {
		this.scheduleInternalReferralFormValidator.validate(form, result);
		if (result.hasErrors()) {
			final Date scheduleDate;
			if (form.getScheduleDate() != null) {
				scheduleDate = form.getScheduleDate();
			} else {
				scheduleDate = new Date();
			}
			final ModelMap map = this.prepareRedisplayMap(
					scheduleDate, facility,
					ScheduleInternalReferralOperation.SCHEDULE,
					form, result);
			this.internalReferralControllerDelegate.prepareLabWorkItemRow(map);
			if (defaultOffender != null) {
				Date currentDate = new Date();
				this.addOffenderSummary(map, defaultOffender, currentDate);
			}
			Map<Long, List<ProviderAssignment>> providersOnDates
				= new HashMap<Long, List<ProviderAssignment>>();
			for (LabWorkAppointmentItem item : form.getLabWork()) {
				if (item.getOrderDate() != null) {
					providersOnDates.put(item.getOrderDate().getTime(), 
							this.populateByProviders(item.getOrderDate(), 
							facility));
				}
			}
			map.addAttribute(PROVIDERS_ON_DATES_MODEL_KEY, providersOnDates);
			return new ModelAndView(this.internalReferralControllerDelegate
					.getScheduleInternalReferralFormViewName(), map);
		}
		final Offender offender;
		if (form.getOffenderRequired()) {
			offender = form.getOffender();
		} else {
			offender = defaultOffender;
		}
		final InternalReferral referral = this.scheduleInternalReferral(
				offender, facility, form.getScheduleDate(), form.getReason(),
				form.getProviderLevel(), form.getProviderAssignment(),
				form.getNotes(), form.getLocationDesignator());
		this.scheduleRequiredLabWork(form.getLabWork(), referral);
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(
						facility, offender, null, null,
						ReferralType.INTERNAL_MEDICAL, null));
	}

	/**
	 * Displays screen to edit schedule of an existing internal referral.
	 *
	 * @param internalReferral internal referral
	 * @return screen to edit schedule of existing internal referral
	 */
	@RequestContentMapping(nameKey = "editScheduleRequestScreenName",
			descriptionKey = "editScheduleRequestScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(
			value = "/internal/editSchedule.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('INTERNAL_REFERRAL_SCHEDULE_EDIT')" +
			" or hasRole('INTERNAL_REFERRAL_SCHEDULE_VIEW')")
	public ModelAndView editSchedule(
			@RequestParam(value = "internalReferral", required = true)
				final InternalReferral internalReferral) {
		final ScheduleInternalReferralForm scheduleInternalReferralForm
			= new ScheduleInternalReferralForm();
		scheduleInternalReferralForm.setReason(internalReferral.getReason());
		scheduleInternalReferralForm.setLocationDesignator(
				internalReferral.getLocationDesignator());
		scheduleInternalReferralForm.setProviderLevel(
				internalReferral.getProviderLevel());
		final ProviderAssignment providerAssignment
			= this.internalReferralScheduler.findProviderByInternalReferral(
				internalReferral);
		scheduleInternalReferralForm.setProviderAssignment(providerAssignment);
		scheduleInternalReferralForm.setScheduleDate(
				internalReferral.getOffenderAppointmentAssociation()
				.getAppointment().getDate());
		scheduleInternalReferralForm.setNotes(
				internalReferral.getSchedulingNotes());
		List<LabWork> labWorks =  this
				.internalReferralScheduler.findLabWorks(
						internalReferral);
		List<LabWorkAppointmentItem> items = 
				new ArrayList<LabWorkAppointmentItem>();
		Map<Long, List<ProviderAssignment>> providersOnDates
			= new HashMap<Long, List<ProviderAssignment>>();
		if (labWorks.size() != 0) {
			int count = 0;
			for (LabWork labWork : labWorks) {
				LabWorkAppointmentItem item = new LabWorkAppointmentItem();
				item.setProcess(true);
				item.setLabWork(labWork);
				item.setDate(labWork
						.getOffenderAppointmentAssociation()
						.getAppointment().getDate());
				item.setLabWorkCategory(labWork
						.getLabWorkCategory());
				item.setSampleLab(labWork.getSampleLab());
				item.setSchedulingNotes(labWork.getSchedulingNotes());
				item.setSampleNotes(labWork.getSampleNotes());
				item.setSampleTaken(labWork.getSampleTaken());
				LabWorkResults results = labWork.getResults();
				if (results != null) {
					item.setResultsLab(results.getLab());
					item.setResultsNotes(results.getNotes());
					item.setResultsDate(results.getDate());
				}
				LabWorkOrder order = labWork.getOrder();
				if (order != null) {
					item.setOrderDate(order.getDate());
					item.setByProvider(order.getByAssignment());
				}
				LabWorkSampleRestrictions restrictions = labWork
						.getSampleRestrictions();
				if (restrictions != null) {
					item.setNothingPerOral(restrictions.getNothingPerOral());
					item.setNoLeaky(restrictions.getNoLeaky());
					item.setNoMeds(restrictions.getNoMeds());
				}
				items.add(count, item);
				List<ProviderAssignment> providers = 
						this.labWorkControllerDelegate.findProviders(
								item.getLabWork()
								.getOffenderAppointmentAssociation()
								.getAppointment().getFacility(), item
								.getOrderDate());
				if (item.getOrderDate() != null) {
					providersOnDates.put(item.getOrderDate().getTime(), 
							providers);
				}
				count++;
			}
			scheduleInternalReferralForm.setLabWork(items);
		}
		final Date currentDate = new Date();
		final ModelMap map = this.prepareCreateMap(currentDate,
			internalReferral.getOffenderAppointmentAssociation()
				.getAppointment().getFacility(), scheduleInternalReferralForm,
			ScheduleInternalReferralOperation.EDIT);
		this.internalReferralControllerDelegate.prepareLabWorkItemRow(map);
		map.addAttribute(PROVIDERS_ON_DATES_MODEL_KEY, providersOnDates);
		this.addOffenderSummary(map,
				internalReferral.getOffenderAppointmentAssociation()
					.getOffender(), currentDate);
		return new ModelAndView(this.internalReferralControllerDelegate
				.getScheduleInternalReferralFormViewName(), map);
	}

	/**
	 * Updates schedule of an existing referral.
	 *
	 * @param internalReferral internal referral
	 * @param form form
	 * @param result binding results
	 * @return redirect to referral center for facility of referral
	 * @throws DuplicateEntityFoundException if the updated referral is already
	 * scheduled
	 * @throws DateConflictException if the dates of the referral conflict with
	 * an existing referral
	 */
	@RequestContentMapping(nameKey = "updateScheduleRequestScreenName",
			descriptionKey = "updateScheduleRequestScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(
			value = "/internal/editSchedule.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('INTERNAL_REFERRAL_SCHEDULE_EDIT')")
	public ModelAndView updateSchedule(
			@RequestParam(value = "internalReferral", required = true)
				final InternalReferral internalReferral,
			final ScheduleInternalReferralForm form,
			final BindingResult result) throws DateConflictException,
				DuplicateEntityFoundException {
		final Facility facility = internalReferral
				.getOffenderAppointmentAssociation().getAppointment()
					.getFacility();
		final Date currentDate = new Date();
		this.scheduleInternalReferralFormValidator.validate(form, result);
		if (result.hasErrors()) {
			final Date scheduleDate;
			if (form.getScheduleDate() != null) {
				scheduleDate = form.getScheduleDate();
			} else {
				scheduleDate = currentDate;
			}
			final ModelMap map = this.prepareRedisplayMap(
					scheduleDate, facility,
					ScheduleInternalReferralOperation.EDIT, form, result);
			this.addOffenderSummary(map,
					internalReferral.getOffenderAppointmentAssociation()
						.getOffender(), currentDate);
			this.internalReferralControllerDelegate.prepareLabWorkItemRow(map);
			Map<Long, List<ProviderAssignment>> providersOnDates
				= new HashMap<Long, List<ProviderAssignment>>();
			for (LabWorkAppointmentItem item : form.getLabWork()) {
				if (item.getOrderDate() != null) {
					providersOnDates.put(item.getOrderDate().getTime(), 
							this.populateByProviders(item.getOrderDate(), 
							facility));
				}
			}
			map.addAttribute(PROVIDERS_ON_DATES_MODEL_KEY, providersOnDates);
			return new ModelAndView(this.internalReferralControllerDelegate
					.getScheduleInternalReferralFormViewName(), map);
		}
		this.internalReferralScheduler.updateSchedule(internalReferral,
				form.getScheduleDate(), form.getReason(),
				form.getProviderLevel(), form.getProviderAssignment(),
				form.getLocationDesignator(), form.getNotes());
		for (LabWorkAppointmentItem item : form.getLabWork()) {
			if (item.getProcess() != null && item.getProcess()) {
				if (item.getLabWork() != null) {
					this.internalReferralScheduler.updateLabWork(
							internalReferral, item.getLabWork(), 
							item.getLabWorkCategory(), 
							item.getSampleLab(), 
							item.getDate(), item.getSampleTaken(),
							item.getSampleNotes(), 
							new LabWorkResults(item.getResultsLab(), 
									item.getResultsDate(), 
									item.getResultsNotes()), 
							new LabWorkOrder(item.getByProvider(), 
									item.getOrderDate()),
							new LabWorkSampleRestrictions(
									item.getNothingPerOral(), 
									item.getNoLeaky(),
									item.getNoMeds()), 
							item.getSchedulingNotes());
				} else {
					this.internalReferralScheduler.scheduleLabWork(
							internalReferral, item.getLabWorkCategory(), 
							item.getSampleLab(), item.getDate(), 
							item.getSampleTaken(), item.getSampleNotes(),
							new LabWorkResults(item.getResultsLab(), 
									item.getResultsDate(), 
									item.getResultsNotes()), 
							new LabWorkOrder(item.getByProvider(), 
									item.getOrderDate()),
							new LabWorkSampleRestrictions(
									item.getNothingPerOral(), 
									item.getNoLeaky(),
									item.getNoMeds()), 
							item.getSchedulingNotes());
				}
			} else {
				this.internalReferralScheduler.removeLabWork(internalReferral, 
						item.getLabWork());
			}
		}
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(
						facility, internalReferral
							.getOffenderAppointmentAssociation().getOffender(),
						null, null,
						ReferralType.INTERNAL_MEDICAL, null));
	}

	/*
	 * Prepares a model map with attributes needed to create an inside referral.
	 */
	private ModelMap prepareCreateMap(
			final Date currentDate, final Facility facility,
			final ScheduleInternalReferralForm form,
			final ScheduleInternalReferralOperation operation) {
		final ModelMap map = new ModelMap();
		final List<ProviderAssignment> providerAssignments =
				this.internalReferralScheduler
					.findInternalProviderAssignmentsForFacility(
						facility, currentDate);
		final DateRange dateRange = DateRange.findWeeklyRange(currentDate);
		final List<InternalProviderScheduleDayItem>
		internalProviderScheduleDayItems;
		if (form.getProviderAssignment() != null) {
			internalProviderScheduleDayItems
				= this.providerScheduleDelegate
						.findInternalProviderScheduleDayItems(
							form.getProviderAssignment(), dateRange);
		} else {
			internalProviderScheduleDayItems = Collections.emptyList();
		}
		final List<InternalReferralReason> reasons
			= this.internalReferralScheduler.findReasons();
		final List<ProviderLevel> providerLevels
			= this.internalReferralScheduler.findProviderLevels();
		map.put(INTERNAL_PROVIDER_SCHEDULE_DAY_ITEMS_MODEL_KEY,
				internalProviderScheduleDayItems);
		map.put(this.internalReferralControllerDelegate
				.getScheduleInternalReferralFormModelKey(), form);
		map.put(START_DATE_MODEL_KEY, dateRange.getStartDate());
		map.put(END_DATE_MODEL_KEY, dateRange.getEndDate());
		map.put(FACILITY_MODEL_KEY, facility);
		map.put(PROVIDER_ASSIGNMENTS_MODEL_KEY, providerAssignments);
		map.put(REFERRAL_DESIGNATIONS_MODEL_KEY,
				ReferralLocationDesignator.values());
		map.put(REASONS_MODEL_KEY, reasons);
		map.put(PROVIDER_LEVELS_MODEL_KEY, providerLevels);
		map.put(OPERATION_MODEL_KEY, operation);
		map.addAttribute(this.labWorkControllerDelegate.getLabsModelKey(),
				this.internalReferralScheduler.findLabs());
		map.addAttribute(WEEK_START_DATE_MODEL_KEY, DateRange.findWeeklyRange(
				new Date()).getStartDate());
		int currentLabWorkIndex;
		if (form.getLabWork() == null) {
			currentLabWorkIndex = 0;
		} else {
			currentLabWorkIndex = form.getLabWork().size();
		}
		map.addAttribute(CURRENT_LAB_WORK_INDEX_MODEL_KEY, currentLabWorkIndex);
		return map;
	}

	/*
	 * Prepares a model map with attributes needed to create an inside referral.
	 */
	private ModelMap prepareRedisplayMap(
			final Date scheduleDate, final Facility facility,
			final ScheduleInternalReferralOperation operation,
			final ScheduleInternalReferralForm form,
			final BindingResult result) {
		final ModelMap map = this.prepareCreateMap(
				scheduleDate, facility, form, operation);
		this.internalReferralControllerDelegate.prepareLabWorkItemRow(map);
		map.addAttribute(BindingResult.MODEL_KEY_PREFIX
					+ this.internalReferralControllerDelegate
					.getScheduleInternalReferralFormModelKey(), result);
		return map;
	}

	/*
	 * Schedules and returns an internal referral.
	 */
	private InternalReferral scheduleInternalReferral(
			final Offender offender, final Facility facility,
			final Date scheduleDate,
			final InternalReferralReason reason,
			final ProviderLevel providerLevel,
			final ProviderAssignment providerAssignment,
			final String notes,
			final ReferralLocationDesignator locationDesignator)
		throws DuplicateEntityFoundException, DateConflictException,
		ProviderScheduleException {
		final InternalReferral internalReferral
			= this.internalReferralScheduler.schedule(
				offender, facility, scheduleDate, reason,
				providerLevel, providerAssignment, locationDesignator, notes);
		return internalReferral;
	}

	/*
	 * Schedules and returns an internal referral.
	 */
	private InternalReferral scheduleInternalReferralFromRequest(
			final HealthRequest request,
			final Date scheduleDate,
			final InternalReferralReason reason,
			final ProviderLevel providerLevel,
			final ProviderAssignment providerAssignment,
			final String notes,
			final ReferralLocationDesignator locationDesignator)
		throws DuplicateEntityFoundException, DateConflictException,
		ProviderScheduleException {
		final InternalReferral internalReferral
			= this.internalReferralScheduler.scheduleFromRequest(
				request, scheduleDate, reason, providerLevel,
				providerAssignment, locationDesignator, notes);
		return internalReferral;
	}
	
	

	/* Adds offender summary to model and view. */
	private void addOffenderSummary(final ModelMap map,
			final Offender offender, final Date date) {
		this.offenderSummaryModelDelegate.add(map, offender);
		String unitAbbreviation = this.unitReportService
				.findUnitAbbreviation(offender, date);
		map.addAttribute(UNIT_ABBREVIATION_MODEL_KEY, unitAbbreviation);
	}

	/*
	 * Creates required lab work for the specified internal referral from
	 * information found in the specified lab work appointment items.
	 */
	private void scheduleRequiredLabWork(
			final List<LabWorkAppointmentItem> items,
			final InternalReferral referral) 
		throws DuplicateEntityFoundException {
		for (LabWorkAppointmentItem item : items) {
			this.internalReferralScheduler.scheduleLabWork(referral, 
					item.getLabWorkCategory(), item.getSampleLab(), 
					item.getDate(), item.getSampleTaken(), 
					item.getSampleNotes(), 
					new LabWorkResults(item.getResultsLab(), 
							item.getResultsDate(), 
							item.getResultsNotes()), 
					new LabWorkOrder(item.getByProvider(), 
							item.getOrderDate()), 
					new LabWorkSampleRestrictions(item.getNothingPerOral(), 
							item.getNoLeaky(),
							item.getNoMeds()), 
							item.getSchedulingNotes());
		}
		this.labWorkControllerDelegate.scheduleRequiredLabWork(items, 
				referral.getOffenderAppointmentAssociation());
	}
	
	/**
	 * Init Binder.
	 *
	 * @param binder binder
	 */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(HealthRequest.class,
				this.healthRequestPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Facility.class,
				this.facilityPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(ProviderAssignment.class,
				this.providerAssignmentPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(InternalReferralReason.class,
				this.internalReferralReasonPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(ProviderLevel.class,
				this.providerLevelPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(InternalReferral.class,
				this.internalReferralPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(
				Lab.class,
				this.labPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				LabWorkCategory.class,
				this.labWorkCategoryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				LabWork.class,
				this.labWorkPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				LabWorkRequirementRequest.class,
				this.labWorkRequirementRequestPropertyEditorFactory
				.createPropertyEditor());
	}
}