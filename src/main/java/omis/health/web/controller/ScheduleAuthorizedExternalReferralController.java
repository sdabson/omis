package omis.health.web.controller;

import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.ExternalReferral;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.health.exception.ProviderScheduleException;
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
 * Controller to schedule authorized external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 2, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/referral/external")
@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class ScheduleAuthorizedExternalReferralController {
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME
		= "health/referral/external/authorized/schedule/edit";

	/* Model keys. */
	
	private static final String FACILITY_MODEL_KEY = "facility";
	
	private static final String 
		SCHEDULE_AUTHORIZED_EXTERNAL_REFERRAL_FORM_MODEL_KEY
			= "scheduleAuthorizedExternalReferralForm";

	private static final String OPERATION_MODEL_KEY = "operation";

	private static final String AUTHORIZATION_MODEL_KEY = "authorization";

	private static final String PROVIDER_ASSIGNMENTS_MODEL_KEY
		= "providerAssignments";
	
	/* Validators. */
	
	@Autowired
	@Qualifier("scheduleAuthorizedExternalReferralFormValidator")
	private ScheduleAuthorizedExternalReferralFormValidator
	scheduleAuthorizedExternalReferralFormValidator;

	/* Property editor factories. */
	
	@Autowired
	@Qualifier("externalReferralAuthorizationPropertyEditorFactory")
	private PropertyEditorFactory
	externalReferralAuthorizationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("externalReferralPropertyEditorFactory")
	private PropertyEditorFactory externalReferralPropertyEditorFactory;
	
	@Autowired
	@Qualifier("providerAssignmentPropertyEditorFactory")
	private PropertyEditorFactory providerAssignmentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Services. */
	
	@Autowired
	@Qualifier("externalReferralScheduler")
	private ExternalReferralScheduler externalReferralScheduler;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("healthControllerDelegate")
	private HealthControllerDelegate healthControllerDelegate;
	
	/* Constructor. */
	
	/** Instantiates a controller to schedule external referrals. */
	public ScheduleAuthorizedExternalReferralController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays screen to schedule an authorized external referral.
	 * 
	 * @param authorization authorization of external referral
	 * @return screen to schedule authorized external referral
	 */
	@RequestMapping(
			value = "/scheduleAuthorized.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('HEALTH_ADMIN')"
			+ " or hasRole('AUTHORIZED_EXTERNAL_REFERRAL_CREATE')")
	public ModelAndView createFromAuthorization(
			@RequestParam(value = "authorization", required = true)
				final ExternalReferralAuthorization authorization) {
		ScheduleAuthorizedExternalReferralForm
			scheduleAuthorizedExternalReferralForm
				= new ScheduleAuthorizedExternalReferralForm();
		scheduleAuthorizedExternalReferralForm.setStatusReasonRequired(false);
		scheduleAuthorizedExternalReferralForm.setProviderAssignment(
				authorization.getRequest().getProviderAssignment());
		Date currentDate = new Date();
		ModelAndView mav = this.prepareEditMav(
				scheduleAuthorizedExternalReferralForm,
				authorization.getRequest().getOffender(),
				authorization.getRequest().getFacility(),
				authorization.getRequest().getMedicalFacility(),
				currentDate);
		mav.addObject(AUTHORIZATION_MODEL_KEY, authorization);
		mav.addObject(OPERATION_MODEL_KEY,
				ScheduleExternalReferralOperation.SCHEDULE);
		return mav;
	}
	
	/**
	 * Displays screen to edit schedule of authorized external referral.
	 * 
	 * @param externalReferral external referral of which to edit schedule
	 * @return screen to edit schedule of authorized external referral
	 */
	@RequestMapping(
			value = "/authorized/editSchedule.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('HEALTH_ADMIN')"
			+ " or hasRole('AUTHORIZED_EXTERNAL_REFERRAL_EDIT')"
			+ " or hasRole('AUTHORIZED_EXTERNAL_REFERRAL_VIEW')")
	public ModelAndView edit(
			@RequestParam(value = "externalReferral", required = true)
				final ExternalReferral externalReferral) {
		ScheduleAuthorizedExternalReferralForm
			scheduleAuthorizedExternalReferralForm
				= new ScheduleAuthorizedExternalReferralForm();
		scheduleAuthorizedExternalReferralForm.setStatusReasonRequired(false);
		scheduleAuthorizedExternalReferralForm.setDate(
				externalReferral.getOffenderAppointmentAssociation()
					.getAppointment().getDate());
		scheduleAuthorizedExternalReferralForm.setTime(
				externalReferral.getOffenderAppointmentAssociation()
					.getAppointment().getStartTime());
		scheduleAuthorizedExternalReferralForm.setSchedulingNotes(
				externalReferral.getSchedulingNotes());
		ProviderAssignment providerAssignment = this.externalReferralScheduler
				.findProviderAssignment(externalReferral);
		scheduleAuthorizedExternalReferralForm.setProviderAssignment(
				providerAssignment);
		ModelAndView mav = this.prepareEditMav(
				scheduleAuthorizedExternalReferralForm,
				externalReferral.getAuthorization().getRequest().getOffender(),
				externalReferral.getAuthorization().getRequest().getFacility(),
				externalReferral.getAuthorization().getRequest()
					.getMedicalFacility(),
				externalReferral.getOffenderAppointmentAssociation()
					.getAppointment().getDate());
		mav.addObject(OPERATION_MODEL_KEY,
				ScheduleExternalReferralOperation.EDIT);
		mav.addObject(AUTHORIZATION_MODEL_KEY,
				externalReferral.getAuthorization());
		return mav;
	}
	
	/**
	 * Schedules a new authorized external referral.
	 * 
	 * @param authorization authorization of external referral
	 * @return redirect to facility referral center
	 * @throws DuplicateEntityFoundException if the external referral
	 * exists
	 */
	@RequestMapping(
			value = "/scheduleAuthorized.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('HEALTH_ADMIN')"
			+ " or hasRole('AUTHORIZED_EXTERNAL_REFERRAL_CREATE')")
	public ModelAndView saveFromAuthorization(
			@RequestParam(value = "authorization", required = true)
				final ExternalReferralAuthorization authorization,
			final ScheduleAuthorizedExternalReferralForm
				scheduleAuthorizedExternalReferralForm,
			final BindingResult result) throws DuplicateEntityFoundException {
		this.scheduleAuthorizedExternalReferralFormValidator
			.validate(scheduleAuthorizedExternalReferralForm, result);
		if (result.hasErrors()) {
			Date currentDate = new Date();
			ModelAndView mav = this.prepareRedisplayMav(
					scheduleAuthorizedExternalReferralForm,
					result, authorization.getRequest().getOffender(),
					authorization.getRequest().getFacility(),
					authorization.getRequest().getMedicalFacility(),
					currentDate);
			mav.addObject(AUTHORIZATION_MODEL_KEY, authorization);
			mav.addObject(OPERATION_MODEL_KEY,
					ScheduleExternalReferralOperation.SCHEDULE);
			return mav;
		}
		this.externalReferralScheduler.schedule(
				authorization,
				scheduleAuthorizedExternalReferralForm.getProviderAssignment(),
				scheduleAuthorizedExternalReferralForm.getDate(),
				scheduleAuthorizedExternalReferralForm.getTime(),
				scheduleAuthorizedExternalReferralForm.getSchedulingNotes());
		return new ModelAndView(
				this.healthControllerDelegate
					.prepareFacilityCenterRedirectWithParameter(
						authorization.getRequest().getFacility(),
						authorization.getRequest().getOffender(),
						null, null, null, null));
	}

	/**
	 * Updates the schedule of a scheduled authorized external referral.
	 * 
	 * @param externalReferral external referral
	 * @param scheduleAuthorizedExternalReferralForm form from which to
	 * update external referral
	 * @param result result
	 * @return redirect to referral center
	 * @throws DuplicateEntityFoundException if the external referral
	 * exists
	 * @throws ProviderScheduleException if the provider does not work on the
	 * date
	 */
	@RequestMapping(
			value = "/authorized/editSchedule.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('HEALTH_ADMIN')"
			+ " or hasRole('AUTHORIZED_EXTERNAL_REFERRAL_EDIT')")
	public ModelAndView update(
			@RequestParam(value = "externalReferral", required = true)
				final ExternalReferral externalReferral,
			final ScheduleAuthorizedExternalReferralForm
				scheduleAuthorizedExternalReferralForm,
			final BindingResult result)
					throws DuplicateEntityFoundException,
					ProviderScheduleException {
		this.scheduleAuthorizedExternalReferralFormValidator
			.validate(scheduleAuthorizedExternalReferralForm, result);
		if (result.hasErrors()) {
			Date currentDate = new Date();
			ModelAndView mav = this.prepareRedisplayMav(
					scheduleAuthorizedExternalReferralForm, result,
					externalReferral.getAuthorization().getRequest()
						.getOffender(),
					externalReferral.getAuthorization().getRequest()
						.getFacility(),
					externalReferral.getAuthorization().getRequest()
						.getMedicalFacility(),
					currentDate);
			mav.addObject(OPERATION_MODEL_KEY,
				ScheduleExternalReferralOperation.EDIT);
			return mav;
		}
		this.externalReferralScheduler.update(externalReferral,
				scheduleAuthorizedExternalReferralForm.getProviderAssignment(),
				scheduleAuthorizedExternalReferralForm.getDate(),
				scheduleAuthorizedExternalReferralForm.getTime(),
				scheduleAuthorizedExternalReferralForm.getSchedulingNotes());
		return new ModelAndView(
				this.healthControllerDelegate
					.prepareFacilityCenterRedirectWithParameter(
						externalReferral.getAuthorization().getRequest()
							.getFacility(),
						externalReferral.getAuthorization().getRequest()
							.getOffender(),
						null, null, null, null));
	}
	
	/* Helper methods. */
	
	// Prepares model and view to edit scheduled authorized external referral
	private ModelAndView prepareEditMav(
			final ScheduleAuthorizedExternalReferralForm
				scheduleAuthorizedExternalReferralForm,
			final Offender offender, final Facility facility,
			final MedicalFacility medicalFacility, final Date date) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(SCHEDULE_AUTHORIZED_EXTERNAL_REFERRAL_FORM_MODEL_KEY,
				scheduleAuthorizedExternalReferralForm);
		mav.addObject(FACILITY_MODEL_KEY, facility);
		List<ProviderAssignment> providerAssignments
			= this.externalReferralScheduler.findProviderAssignments(
					facility, medicalFacility, date);
		mav.addObject(PROVIDER_ASSIGNMENTS_MODEL_KEY, providerAssignments);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	// Prepares model and view to redisplay form to edit scheduled
	// authorized external referral
	private ModelAndView prepareRedisplayMav(
			final ScheduleAuthorizedExternalReferralForm
			scheduleAuthorizedExternalReferralForm,
			final BindingResult result, final Offender offender,
			final Facility facility, final MedicalFacility medicalFacility,
			final Date date) {
		ModelAndView mav = this.prepareEditMav(
				scheduleAuthorizedExternalReferralForm, offender,
				facility, medicalFacility, date);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ SCHEDULE_AUTHORIZED_EXTERNAL_REFERRAL_FORM_MODEL_KEY,
				result);
		return mav;
	}
	
	/* Init binder. */
	
	/**
	 * Binds property editors. 
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(ExternalReferralAuthorization.class,
				this.externalReferralAuthorizationPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(ExternalReferral.class,
				this.externalReferralPropertyEditorFactory
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