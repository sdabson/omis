package omis.health.web.controller;

import java.util.Collections;
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
import omis.datatype.DateRange;
import omis.exception.BusinessException;
import omis.facility.domain.Facility;
import omis.health.domain.InternalReferral;
import omis.health.domain.InternalReferralReason;
import omis.health.domain.InternalReferralStatusReason;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkRequirement;
import omis.health.domain.LabWorkRequirementRequest;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderLevel;
import omis.health.domain.ReferralLocationDesignator;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkResults;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.report.UnitReportService;
import omis.health.service.InternalReferralScheduler;
import omis.health.validator.ScheduleInternalReferralFormValidator;
import omis.health.web.controller.delegate.HealthControllerDelegate;
import omis.health.web.controller.delegate.InternalReferralControllerDelegate;
import omis.health.web.controller.delegate.LabWorkControllerDelegate;
import omis.health.web.controller.delegate.ProviderScheduleDelegate;
import omis.health.web.form.InternalProviderScheduleDayItem;
import omis.health.web.form.LabWorkAppointmentItem;
import omis.health.web.form.ScheduleInternalReferralForm;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;

/** 
 * Reschedule internal referral.
 * 
 * @author Ryan Johns
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.0 (May 6, 2014)
 * @since OMIS 3.0 
 */
@Controller
@RequestMapping("/health/referral")
@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class RescheduleInternalReferralController {
	
	/* Model Keys. */
	
	private static final String INTERNAL_PROVIDER_SCHEDULE_DAY_ITEMS_MODEL_KEY
		= "internalProviderScheduleDayItems";

	private static final String START_DATE_MODEL_KEY = "startDate";

	private static final String END_DATE_MODEL_KEY = "endDate";

	private static final String FACILITY_MODEL_KEY = "facility";

	private static final String PROVIDER_ASSIGNMENTS_MODEL_KEY =
			"providerAssignments";

	private static final String REFERRAL_DESIGNATIONS_MODEL_KEY =
			"referralDesignationLocations";

	private static final String REASONS_MODEL_KEY = "reasons";

	private static final String PROVIDER_LEVELS_MODEL_KEY = "providerLevels";

	private static final String OPERATION_MODEL_KEY = "operation";
	
	private static final String STATUS_REASONS_MODEL_KEY = "statusReasons";

	private static final String UNIT_ABBREVIATION_MODEL_KEY
		= "unitAbbreviation";
	
	private static final String PROVIDERS_ON_DATES_MODEL_KEY
		= "providersOnDates";

	/* Helpers. */
	
	@Autowired
	@Qualifier("providerScheduleDelegate")
	private ProviderScheduleDelegate providerScheduleDelegate;

	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;

	@Autowired
	private InternalReferralScheduler internalReferralScheduler;

	@Autowired
	private HealthControllerDelegate healthControllerDelegate;

	@Autowired
	private InternalReferralControllerDelegate
	internalReferralControllerDelegate;
	
	@Autowired
	@Qualifier("labWorkControllerDelegate")
	private LabWorkControllerDelegate labWorkControllerDelegate;
	
	/* Property Editor Factories. */

	@Autowired
	private PropertyEditorFactory internalReferralPropertyEditorFactory;

	@Autowired
	private PropertyEditorFactory providerAssignmentPropertyEditorFactory;

	@Autowired
	private CustomDateEditorFactory customDateEditorFactory;

	@Autowired
	private PropertyEditorFactory facilityPropertyEditorFactory;

	@Autowired
	private PropertyEditorFactory internalReferralReasonPropertyEditorFactory;
	
	@Autowired
	private PropertyEditorFactory
	internalReferralStatusReasonPropertyEditorFactory;

	@Autowired
	private PropertyEditorFactory providerLevelPropertyEditorFactory;
	
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
	@Qualifier("labWorkRequirementRequestPropertyEditorFactory")
	private PropertyEditorFactory 
	labWorkRequirementRequestPropertyEditorFactory;
	
	/* Report services. */
	
	@Autowired
	@Qualifier("unitReportService")
	private UnitReportService unitReportService;
	
	/* Validators. */
	
	@Autowired
	private ScheduleInternalReferralFormValidator
	scheduleInternalReferralFormValidator;

	/** Displays a screen to reschedule referrals.
	 * @param internalReferral to reschedule.
	 * @param facility facility.
	 * @return model and view to reschedule internal referral. */
	@RequestContentMapping(nameKey = "rescheduleRequestScreenName",
			descriptionKey = "rescheduleRequestScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/internal/reschedule.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('INTERNAL_REFERRAL_SCHEDULE_VIEW')")
	public ModelAndView create(
			@RequestParam(value = "internalReferral", required = true)
				final InternalReferral internalReferral,
			@RequestParam(value = "facility", required = true)
				final Facility facility) {
		final ScheduleInternalReferralForm scheduleInternalReferralForm =
				new ScheduleInternalReferralForm();
		scheduleInternalReferralForm.setLocationDesignator(internalReferral
				.getLocationDesignator());
		scheduleInternalReferralForm.setReason(internalReferral.getReason());
		scheduleInternalReferralForm.setNotes(internalReferral
				.getSchedulingNotes());
		scheduleInternalReferralForm.setOffender(internalReferral
				.getOffenderAppointmentAssociation().getOffender());
		scheduleInternalReferralForm.setProviderLevel(internalReferral
				.getProviderLevel());
		scheduleInternalReferralForm.setProviderAssignment(this
				.internalReferralScheduler.findProviderByInternalReferral(
						internalReferral));
		scheduleInternalReferralForm.setScheduleDate(this
				.internalReferralScheduler
				.findHealthAppointmentByInternalReferral(internalReferral)
				.getDate());
		scheduleInternalReferralForm.setStatusReasonRequired(true);
		final Offender offender = internalReferral
				.getOffenderAppointmentAssociation().getOffender();
		final ModelMap map = this.prepareCreateMap(new Date(), facility,
				scheduleInternalReferralForm, offender);
		Map<Long, List<ProviderAssignment>> providersOnDates
			= new HashMap<Long, List<ProviderAssignment>>();
		List<LabWorkRequirement> requirements = this.internalReferralScheduler
				.findLabWorkRequirements(internalReferral);
		int currentLabWorkItemIndex = 0;
		for (LabWorkRequirement requirement : requirements) {
			LabWorkAppointmentItem item = new LabWorkAppointmentItem();
			LabWork labWork = requirement.getLabWork();
			item.setLabWork(labWork);
			item.setDate(labWork.getOffenderAppointmentAssociation()
					.getAppointment().getDate());
			item.setLabWorkCategory(labWork.getLabWorkCategory());
			item.setSchedulingNotes(labWork.getSchedulingNotes());
			item.setSampleNotes(labWork.getSampleNotes());
			item.setSampleLab(labWork.getSampleLab());
			if (labWork.getResults() != null) {
				item.setResultsLab(labWork.getResults().getLab());
				item.setResultsDate(labWork.getResults().getDate());
				item.setResultsNotes(labWork.getResults().getNotes());
			}
			if (labWork.getSampleRestrictions() != null) {
				item.setNoLeaky(labWork.getSampleRestrictions().getNoLeaky());
				item.setNothingPerOral(labWork.getSampleRestrictions()
						.getNothingPerOral());
				item.setNoMeds(labWork.getSampleRestrictions().getNoMeds());
			}
			if (labWork.getOrder() != null) {
				item.setOrderDate(labWork.getOrder().getDate());
				item.setByProvider(labWork.getOrder().getByAssignment());
			}
			item.setProcess(true);
			if (item.getOrderDate() != null) {
				List<ProviderAssignment> providers = 
						this.labWorkControllerDelegate.findProviders(
								facility, item.getOrderDate());
				providersOnDates.put(item.getOrderDate().getTime(), 
						providers);
			}
			scheduleInternalReferralForm.getLabWork().add(item);
			currentLabWorkItemIndex++;
		}
		this.internalReferralControllerDelegate.prepareLabWorkItemRow(map);
		map.addAttribute(this.internalReferralControllerDelegate
				.getCurrentLabWorkIndexModelKey(), currentLabWorkItemIndex);
		map.addAttribute(PROVIDERS_ON_DATES_MODEL_KEY, providersOnDates);
		return new ModelAndView(this.internalReferralControllerDelegate
				.getScheduleInternalReferralFormViewName(), map);
	}

	/** Reschedules internal referral.
	 * @param internalReferral internal referral.
	 * @param form form.
	 * @param result binding result.
	 * @param facility facility.
	 * @return model and view. */
	@RequestContentMapping(nameKey = "createRescheduleReferralScreenName",
			descriptionKey = "createRescheduleReferralDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/internal/reschedule.html",
		method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('INTERNAL_REFERRAL_SCHEDULE_EDIT')")
	public ModelAndView save(
			@RequestParam(value = "internalReferral", required = true)
				final InternalReferral internalReferral,
			final ScheduleInternalReferralForm form,
			final BindingResult result,
			@RequestParam(value = "facility", required = true)
				final Facility facility) throws BusinessException {
		this.scheduleInternalReferralFormValidator.validate(form, result);
		if (result.hasErrors()) {
			final Date scheduleDate;
			if (form.getScheduleDate() != null) {
				scheduleDate = form.getScheduleDate();
			} else {
				scheduleDate = new Date();
			}
			final Offender offender = internalReferral
					.getOffenderAppointmentAssociation().getOffender();
			final ModelMap map = this.prepareCreateMap(scheduleDate, facility,
					form, offender);
			map.addAttribute(BindingResult.MODEL_KEY_PREFIX
					+ this.internalReferralControllerDelegate
					.getScheduleInternalReferralFormModelKey(), result);
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
		InternalReferral newReferral = this.internalReferralScheduler
				.reschedule(internalReferral,
				form.getStatusReason(), form.getScheduleDate(),
				form.getReason(), form.getProviderLevel(),
				form.getProviderAssignment(), form.getNotes(),
				form.getLocationDesignator());
		for (LabWorkAppointmentItem item : form.getLabWork()) {
			if (item.getProcess() != null && item.getProcess()) {
				if (item.getLabWork() != null) {
					this.internalReferralScheduler.updateLabWork(
							newReferral, item.getLabWork(), 
							item.getLabWorkCategory(), item.getSampleLab(), 
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
							newReferral, item.getLabWorkCategory(), 
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
			} else if (item.getProcess() != null) {
				this.internalReferralScheduler.removeLabWork(internalReferral,  
						item.getLabWork());
			}
		}
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(facility,
						null, newReferral
							.getOffenderAppointmentAssociation()
								.getAppointment().getDate(),
							  newReferral
							.getOffenderAppointmentAssociation()
								.getAppointment().getDate(), null, null));
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
	
	private ModelMap prepareCreateMap(
			final Date currentDate, final Facility facility,
			final ScheduleInternalReferralForm form,
			final Offender offender) {
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
		map.put(REASONS_MODEL_KEY, this.internalReferralScheduler
				.findReasons());
		map.put(PROVIDER_LEVELS_MODEL_KEY, this.internalReferralScheduler
				.findProviderLevels());
		map.put(OPERATION_MODEL_KEY,
				ScheduleInternalReferralOperation.RESCHEDULE);
		map.put(STATUS_REASONS_MODEL_KEY,
				this.internalReferralScheduler.findRescheduleStatusReasons());
		this.offenderSummaryModelDelegate.add(map, offender);
		String unitAbbreviation = this.unitReportService
				.findUnitAbbreviation(offender, currentDate);
		int currentLabWorkIndex;
		if (form.getLabWork() == null) {
			currentLabWorkIndex = 0;
		} else {
			currentLabWorkIndex = form.getLabWork().size();
		}
		map.addAttribute(this.internalReferralControllerDelegate
				.getCurrentLabWorkIndexModelKey(), currentLabWorkIndex);
		map.put(UNIT_ABBREVIATION_MODEL_KEY, unitAbbreviation);
		return map;
	}
	
	/** Init Binder.
	 * @param binder binder. */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Facility.class,
				this.facilityPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(InternalReferral.class,
				this.internalReferralPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(ProviderAssignment.class, this
				.providerAssignmentPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Date.class, this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(InternalReferralReason.class,
				this.internalReferralReasonPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(ProviderLevel.class,
				this.providerLevelPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(InternalReferralStatusReason.class,
				this.internalReferralStatusReasonPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Lab.class,
				this.labPropertyEditorFactory
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
				LabWorkRequirementRequest.class,
				this.labWorkRequirementRequestPropertyEditorFactory
				.createPropertyEditor());
	}
}