package omis.health.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.datatype.DateRange;
import omis.facility.domain.Facility;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.LabWorkReferralStatusReason;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.report.UnitReportService;
import omis.health.service.LabWorkReferralScheduler;
import omis.health.validator.ScheduleLabWorkReferralFormValidator;
import omis.health.web.controller.delegate.HealthControllerDelegate;
import omis.health.web.form.LabWorkSampleItem;
import omis.health.web.form.ReferralType;
import omis.health.web.form.ScheduleLabWorkReferralForm;
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

/**
 * Controller for rescheduling lab work referrals.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (Aug 12, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/referral/labWork")
@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class RescheduleLabWorkReferralController {

	/* View names. */
	
	private static final String EDIT_FORM_VIEW_NAME 
		= "/health/referral/labWork/schedule/edit";
	
	/* Model Keys. */

	private static final String LAB_FORM_MODEL_KEY 
		= "scheduleLabWorkReferralForm";
	
	private static final String LAB_WORK_REFERRAL_MODEL_KEY = "labWorkReferral";
	
	private static final String WEEK_START_DATE_MODEL_KEY = "weekStartDate";
	
	private static final String SCHEDULE_LAB_WORK_REFERRAL_OPERATION_MODEL_KEY
		= "operation";
	
	private static final String LAB_WORK_SAMPLE_ITEM_INDEX_MODEL_KEY 
		= "labWorkSampleItemIndex";
	
	private static final String UNIT_ABBREVIATION_MODEL_KEY
		= "unitAbbreviation";
	
	private static final String FACILITY_MODEL_KEY = "facility";
	
	private static final String LABS_MODEL_KEY = "labs";
	
	private static final String LAB_WORK_CATEGORIES_MODEL_KEY 
	= "labWorkCategories";
	
	private static final String PROVIDERS_ON_DATES_MODEL_KEY
		= "providersOnDates";
	
	private static final String STATUS_REASONS_MODEL_KEY
		= "statusReasons";
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("healthControllerDelegate")
	private HealthControllerDelegate healthControllerDelegate;
	
	/* Property Editor Factories. */
	
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
	@Qualifier("labWorkReferralStatusReasonPropertyEditorFactory")
	private PropertyEditorFactory 
	labWorkReferralStatusReasonPropertyEditorFactory;
	
	/* Services. */
	
	@Autowired
	@Qualifier("labWorkReferralScheduler")
	private LabWorkReferralScheduler labWorkReferralScheduler;
	
	/* Report Services. */

	@Autowired
	@Qualifier("unitReportService")
	private UnitReportService unitReportService;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("scheduleLabWorkReferralFormValidator")
	ScheduleLabWorkReferralFormValidator scheduleLabWorkReferralFormValidator;
	
	/**
	 * Instantiates a default instance of reschedule lab work referral 
	 * controller.
	 */
	public RescheduleLabWorkReferralController() {
		//Default constructor.
	}
	
	/**
	 * Presents the screen for rescheduling a lab work referral with information
	 * about the existing lab work referral and it's corresponding lab work
	 * sample items.
	 * 
	 * @param labWorkReferral lab work referral
	 * @return model and view for lab work referral rescheduling
	 */
	@RequestMapping(value="/reschedule.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('LAB_WORK_REFERRAL_SCHEDULE_VIEW')")
	public ModelAndView create(@RequestParam(value="labWorkReferral", 
		required = true) final LabWorkReferral labWorkReferral) {
		throw new UnsupportedOperationException(
				"Rescheduling Lab work is not yet supported.");
		//TODO: add reschedule lab functionality.
//		ModelMap map = new ModelMap();
//		final ScheduleLabWorkReferralForm form = 
//				new ScheduleLabWorkReferralForm();
//		this.populateForm(form, labWorkReferral);
//		map.addAttribute(LAB_WORK_SAMPLE_ITEM_INDEX_MODEL_KEY, 
//				this.assignSampleIndexValue(form.getLabWorkSampleItems()));
//		this.prepareRescheduleMav(map, form, labWorkReferral, 
//				labWorkReferral.getOffenderAppointmentAssociation()
//				.getAppointment().getFacility(), 
//				ScheduleLabWorkReferralOperation.RESCHEDULE);
//		return new ModelAndView(EDIT_FORM_VIEW_NAME, map);
	}
	
	/**
	 * Reschedules the specified lab work referral and associated lab work.
	 * 
	 * @param labWorkReferral lab work referral
	 * @param form
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/reschedule.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('LAB_WORK_REFERRAL_SCHEDULE_EDIT')")
	public ModelAndView save(
			@RequestParam(value = "labWorkReferral", required = true) 
			final LabWorkReferral labWorkReferral,
			final ScheduleLabWorkReferralForm form, final BindingResult 
			result) {
		this.scheduleLabWorkReferralFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(LAB_WORK_SAMPLE_ITEM_INDEX_MODEL_KEY, 
					this.assignSampleIndexValue(form.getLabWorkSampleItems()));
			this.prepareRescheduleMav(map, form, labWorkReferral, 
					labWorkReferral.getOffenderAppointmentAssociation()
					.getAppointment().getFacility(), 
					ScheduleLabWorkReferralOperation.RESCHEDULE);
			return new ModelAndView(EDIT_FORM_VIEW_NAME, map);
		}
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(labWorkReferral
						.getOffenderAppointmentAssociation().getAppointment()
						.getFacility(), labWorkReferral
						.getOffenderAppointmentAssociation()
						.getOffender(), null, null, ReferralType.LAB, null));
	}
	
	/* Helper Methods */
	
	/*
	 * Prepares a model and view with values needed to reschedule  an existing 
	 * lab work.
	 */
	private ModelAndView prepareRescheduleMav(final ModelMap map, 
			final ScheduleLabWorkReferralForm form, 
			final LabWorkReferral labWorkReferral, final Facility facility, 
			final ScheduleLabWorkReferralOperation operation) {
		this.addOffenderSummary(map, labWorkReferral
				.getOffenderAppointmentAssociation().getOffender(), new Date());
		map.addAttribute(FACILITY_MODEL_KEY, facility);
		map.addAttribute(LAB_FORM_MODEL_KEY, form);
		map.addAttribute(LAB_WORK_REFERRAL_MODEL_KEY, labWorkReferral);
		map.addAttribute(SCHEDULE_LAB_WORK_REFERRAL_OPERATION_MODEL_KEY, 
				operation);
		map.addAttribute(STATUS_REASONS_MODEL_KEY, 
				this.labWorkReferralScheduler.findRescheduleStatusReasons());
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
	
	/* Adds offender summary to model and view. */
	private void addOffenderSummary(final ModelMap map,
			final Offender offender, final Date date) {
		this.offenderSummaryModelDelegate.add(map, offender);
		String unitAbbreviation = this.unitReportService
				.findUnitAbbreviation(offender, date);
		map.addAttribute(UNIT_ABBREVIATION_MODEL_KEY, unitAbbreviation);
	}
	
	/*
	 * Prepares closed text options for a lab work form.
	 */
	private ModelMap prepareClosedText(final ModelMap map) {
		map.addAttribute(LABS_MODEL_KEY, this.labWorkReferralScheduler
				.findLabs());
		map.addAttribute(LAB_WORK_CATEGORIES_MODEL_KEY, 
				this.labWorkReferralScheduler.findLabWorkCategories());
		map.addAttribute(WEEK_START_DATE_MODEL_KEY, DateRange.findWeeklyRange(
				new Date()).getStartDate());
		return map;
	}
	
	/*
	 * Assigns an appropriate value to lab work sample index depending on the
	 * set of specified lab work sample items.
	 */
	private int assignSampleIndexValue(List<LabWorkSampleItem> sampleItems) {
		final int labWorkSampleIndex;
		if (sampleItems == null) {
			labWorkSampleIndex = 0;
		} else {
			labWorkSampleIndex = sampleItems.size();
		}
		return labWorkSampleIndex;
	}
	
	/*
	 * Populates a schedule lab work referral form with values from the 
	 * specified lab work referral.
	 */
	@SuppressWarnings("unused")
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
				Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(
				LabWorkReferralStatusReason.class,
				this.labWorkReferralStatusReasonPropertyEditorFactory
				.createPropertyEditor());
	}
}