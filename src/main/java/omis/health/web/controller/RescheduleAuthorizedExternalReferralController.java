package omis.health.web.controller;

import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.ExternalReferral;
import omis.health.domain.ExternalReferralStatusReason;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.health.exception.ProviderScheduleException;
import omis.health.exception.WrongExternalReferralStatusReasonException;
import omis.health.service.ExternalReferralScheduler;
import omis.health.validator.ScheduleAuthorizedExternalReferralFormValidator;
import omis.health.web.controller.delegate.HealthControllerDelegate;
import omis.health.web.form.ScheduleAuthorizedExternalReferralForm;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to reschedule external referrals.
 * 
 * <p>This controller requires that external referrals be authorized (that is,
 * authorization details are not entered on rescheduling).
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Aug 8, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/referral/external/authorized")
@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class RescheduleAuthorizedExternalReferralController {
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME
		= "health/referral/external/authorized/schedule/edit";
	
	/* Model keys. */
	
	private static final String
	SCHEDULE_AUTHORIZED_EXTERNAL_REFERRAL_FORM_MODEL_KEY
		= "scheduleAuthorizedExternalReferralForm";
	
	private static final String FACILITY_MODEL_KEY = "facility";

	private static final String RESCHEDULE_STATUS_REASONS_MODEL_KEY
		= "rescheduleStatusReasons";
	
	private static final String OPERATION_MODEL_KEY = "operation";

	private static final String AUTHORIZATION_MODEL_KEY = "authorization";

	private static final String PROVIDER_ASSIGNMENTS_MODEL_KEY
		= "providerAssignments";

	/* Services. */
	
	@Autowired
	@Qualifier("externalReferralScheduler")
	private ExternalReferralScheduler externalReferralScheduler;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("externalReferralPropertyEditorFactory")
	private PropertyEditorFactory externalReferralPropertyEditorFactory;

	@Autowired
	@Qualifier("externalReferralStatusReasonPropertyEditorFactory")
	private PropertyEditorFactory
	externalReferralStatusReasonPropertyEditorFactory;
	
	@Autowired
	@Qualifier("providerAssignmentPropertyEditorFactory")
	private PropertyEditorFactory providerAssignmentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("scheduleAuthorizedExternalReferralFormValidator")
	private ScheduleAuthorizedExternalReferralFormValidator
	scheduleAuthorizedExternalReferralFormValidator;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("healthControllerDelegate")
	private HealthControllerDelegate healthControllerDelegate;
	
	/* Constructor. */
	
	/** Instantiates controller to reschedule external referrals. */
	public RescheduleAuthorizedExternalReferralController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays screen to reschedule an external referral.
	 * 
	 * @param externalReferral external referral to reschedule
	 * @return model and view for screen to reschedule and external referral
	 */
	@RequestMapping(value = "/reschedule.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('HEALTH_ADMIN')"
			+ " or (hasRole('USER')"
			+ " and hasRole('EXTERNAL_REFERRAL_RESCHEDULE'))")
	public ModelAndView edit(
			@RequestParam(value = "externalReferral", required = true)
				final ExternalReferral externalReferral) {
		ScheduleAuthorizedExternalReferralForm
		scheduleAuthorizedExternalReferralForm
			= new ScheduleAuthorizedExternalReferralForm();
		scheduleAuthorizedExternalReferralForm.setStatusReasonRequired(true);
		scheduleAuthorizedExternalReferralForm.setSchedulingNotes(
				externalReferral.getSchedulingNotes());
		scheduleAuthorizedExternalReferralForm.setDate(
				externalReferral.getOffenderAppointmentAssociation()
					.getAppointment().getDate());
		scheduleAuthorizedExternalReferralForm.setTime(
				externalReferral.getOffenderAppointmentAssociation()
					.getAppointment().getStartTime());
		ProviderAssignment providerAssignment = this.externalReferralScheduler
				.findProviderAssignment(externalReferral);
		scheduleAuthorizedExternalReferralForm.setProviderAssignment(
				providerAssignment);
		Date currentDate = new Date();
		ModelAndView mav = this.prepareEditMav(
				scheduleAuthorizedExternalReferralForm,
				externalReferral.getAuthorization().getRequest().getFacility(),
				externalReferral.getAuthorization().getRequest()
					.getMedicalFacility(),
				externalReferral.getAuthorization().getRequest().getOffender(),
				currentDate);
		mav.addObject(OPERATION_MODEL_KEY,
				ScheduleExternalReferralOperation.RESCHEDULE);
		mav.addObject(AUTHORIZATION_MODEL_KEY,
				externalReferral.getAuthorization());
		return mav;
	}
	
	/**
	 * Reschedules an external referral
	 * 
	 * @param externalReferral external referral to reschedule
	 * @param scheduleAuthorizedExternalReferralForm form from which to take
	 * rescheduling information
	 * @param result binding results
	 * @return redirect to facility center
	 * @throws ProviderScheduleException if a wrong status reason is provided
	 * @throws WrongExternalReferralStatusReasonException if a wrong status
	 * reason is provided
	 * @throws DuplicateEntityFoundException if external referral already exists
	 */
	@RequestMapping(value = "/reschedule.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('HEALTH_ADMIN')"
			+ " or (hasRole('USER')"
			+ " and hasRole('EXTERNAL_REFERRAL_RESCHEDULE'))")
	public ModelAndView update(
			@RequestParam(value = "externalReferral", required = true)
				final ExternalReferral externalReferral,
			final ScheduleAuthorizedExternalReferralForm
				scheduleAuthorizedExternalReferralForm,
			final BindingResult result)
					throws DuplicateEntityFoundException,
						WrongExternalReferralStatusReasonException,
						ProviderScheduleException {
		this.scheduleAuthorizedExternalReferralFormValidator.validate(
				scheduleAuthorizedExternalReferralForm, result);
		if (result.hasErrors()) {
			Date currentDate = new Date();
			ModelAndView mav = this.prepareRedisplayMav(
					scheduleAuthorizedExternalReferralForm, result,
				externalReferral.getAuthorization().getRequest().getFacility(),
				externalReferral.getAuthorization().getRequest()
					.getMedicalFacility(),
				externalReferral.getAuthorization().getRequest().getOffender(),
				currentDate);
			mav.addObject(OPERATION_MODEL_KEY,
					ScheduleExternalReferralOperation.RESCHEDULE);
			mav.addObject(AUTHORIZATION_MODEL_KEY,
					externalReferral.getAuthorization());
			return mav;
		}
		this.externalReferralScheduler.reschedule(externalReferral,
				scheduleAuthorizedExternalReferralForm.getStatusReason(),
				scheduleAuthorizedExternalReferralForm.getProviderAssignment(),
				scheduleAuthorizedExternalReferralForm.getDate(),
				scheduleAuthorizedExternalReferralForm.getTime(),
				scheduleAuthorizedExternalReferralForm.getSchedulingNotes());
		return new ModelAndView(
				this.healthControllerDelegate
					.prepareFacilityCenterRedirectWithParameter(
						externalReferral.getAuthorization().getRequest()
							.getFacility(), null,
						externalReferral.getOffenderAppointmentAssociation()
							.getAppointment().getDate(),
						externalReferral.getOffenderAppointmentAssociation()
							.getAppointment().getDate(), null, null));
	}
	
	/* Helper methods. */
	
	// Returns model and view to reschedule external referrals
	private ModelAndView prepareEditMav(
			final ScheduleAuthorizedExternalReferralForm
				scheduleAuthorizedExternalReferralForm,
			final Facility facility, final MedicalFacility medicalFacility,
			final Offender offender, final Date date) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(SCHEDULE_AUTHORIZED_EXTERNAL_REFERRAL_FORM_MODEL_KEY,
				scheduleAuthorizedExternalReferralForm);
		mav.addObject(FACILITY_MODEL_KEY, facility);
		List<ExternalReferralStatusReason> rescheduleStatusReasons
			= this.externalReferralScheduler.findRescheduleStatusReasons();
		mav.addObject(RESCHEDULE_STATUS_REASONS_MODEL_KEY,
				rescheduleStatusReasons);
		List<ProviderAssignment> providerAssignments
			= this.externalReferralScheduler.findProviderAssignments(
					facility, medicalFacility, date);
		mav.addObject(PROVIDER_ASSIGNMENTS_MODEL_KEY, providerAssignments);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	// Returns model and view to redisplay screen to reschedule external
	// referrals
	private ModelAndView prepareRedisplayMav(
			final ScheduleAuthorizedExternalReferralForm
				scheduleAuthorizedExternalReferralForm,
			final BindingResult result, final Facility facility,
			final MedicalFacility medicalFacility, final Offender offender,
			final Date date) {
		ModelAndView mav = this.prepareEditMav(
				scheduleAuthorizedExternalReferralForm, facility,
				medicalFacility, offender, date);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ SCHEDULE_AUTHORIZED_EXTERNAL_REFERRAL_FORM_MODEL_KEY, result);
		return mav;
	}
	
	/* Init binders. */
	
	/**
	 * Registers property editors.
	 * 
	 * @param binder web data binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(ExternalReferral.class,
				this.externalReferralPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(ExternalReferralStatusReason.class,
				this.externalReferralStatusReasonPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(ProviderAssignment.class,
				this.providerAssignmentPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Date.class, "date", 
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "time", 
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
	}
}