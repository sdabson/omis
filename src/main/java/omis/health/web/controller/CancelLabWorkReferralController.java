package omis.health.web.controller;

import omis.beans.factory.PropertyEditorFactory;
import omis.facility.domain.Facility;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.LabWorkReferralStatusReason;
import omis.health.exception.LabWorkReferralCancellationException;
import omis.health.exception.WrongLabWorkReferralStatusReasonException;
import omis.health.service.CancelLabWorkReferralService;
import omis.health.validator.CancelLabWorkReferralFormValidator;
import omis.health.web.controller.delegate.HealthControllerDelegate;
import omis.health.web.form.CancelLabWorkReferralForm;
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
 * Cancel lab work referral controller.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (Aug 20, 2014)
 * @since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/health/referral/labWork")
@PreAuthorize("hasRole('ADMIN')"
		+ " or hasRole('HEALTH_ADMIN')" 
		+ " or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class CancelLabWorkReferralController {

	/* View names. */
	
	private static final String EDIT_VIEW_NAME
		= "health/referral/labWork/cancel/edit";
	
	/* Model keys. */
	
	private static final String CANCEL_LAB_WORK_REFERRAL_FORM_MODEL_KEY
		= "cancelLabWorkReferralForm";
	
	private static final String FACILITY_MODEL_KEY = "facility";

	private static final String STATUS_REASONS_MODEL_KEY = "statusReasons";
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("healthControllerDelegate")
	private HealthControllerDelegate healthControllerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("labWorkReferralPropertyEditorFactory")
	private PropertyEditorFactory labWorkReferralPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labWorkReferralStatusReasonPropertyEditorFactory")
	private PropertyEditorFactory
	labWorkReferralStatusReasonPropertyEditorFactory;
	
	/* Services. */
	
	@Autowired
	@Qualifier("cancelLabWorkReferralService")
	private CancelLabWorkReferralService cancelLabWorkReferralService;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("cancelLabWorkReferralFormValidator")
	private CancelLabWorkReferralFormValidator 
	cancelLabWorkReferralFormValidator;
	
	/**
	 * Instantiates a default instance of cancel lab work referral controller.
	 */
	public CancelLabWorkReferralController() {
		//Default constructor.
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows the cancel lab work referral form with proper options to cancel
	 * the specified lab work referral.
	 * 
	 * @param labWorkReferral lab work referral
	 * @return model and view to show the screen for canceling a lab work 
	 * referral
	 */
	@RequestMapping(value = "/cancel.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('LAB_WORK_REFERRAL_CANCEL')")
	public ModelAndView edit(@RequestParam(value = "labWorkReferral", 
			required = true) final LabWorkReferral labWorkReferral) {
		CancelLabWorkReferralForm form = new CancelLabWorkReferralForm();
		form.setStatusReason(labWorkReferral.getStatusReason());
		return this.prepareEditMav(form, 
				labWorkReferral.getOffenderAppointmentAssociation()
				.getOffender(), labWorkReferral
				.getOffenderAppointmentAssociation().getAppointment()
				.getFacility());
	}
	
	/**
	 * Cancels the specified lab work referral with information from the
	 * specified form.
	 * 
	 * @param labWorkReferral lab work referral
	 * @param form cancel lab work referral form
	 * @param result binding result
	 * @return model and view
	 * @throws LabWorkReferralCancellationException if the lab work 
	 * referral already has another status reason
	 * @throws WrongLabWorkReferralStatusReasonException if the status reason
	 * is not for cancellation
	 */
	@RequestMapping(value = "/cancel.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('LAB_WORK_REFERRAL_CANCEL')")
	public ModelAndView update(@RequestParam(value = "labWorkReferral", 
			required = true) final LabWorkReferral labWorkReferral,
			final CancelLabWorkReferralForm form, final BindingResult result) 
		throws LabWorkReferralCancellationException, 
		WrongLabWorkReferralStatusReasonException {
		final Facility facility = labWorkReferral
				.getOffenderAppointmentAssociation().getAppointment()
				.getFacility();
		final Offender offender = labWorkReferral
				.getOffenderAppointmentAssociation().getOffender();
		this.cancelLabWorkReferralFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareEditMav(form, offender, facility);
			mav.addObject(BindingResult.MODEL_KEY_PREFIX 
					+ CANCEL_LAB_WORK_REFERRAL_FORM_MODEL_KEY, result);
			return mav;
		}
		this.cancelLabWorkReferralService.cancel(labWorkReferral, 
				form.getStatusReason());
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(facility, null, 
						labWorkReferral.getOffenderAppointmentAssociation()
						.getAppointment().getDate(),
						labWorkReferral.getOffenderAppointmentAssociation()
						.getAppointment().getDate(), null, null));
	}
	
	/*
	 * Prepares a model and view to cancel a lab work referral.
	 * 
	 * @param form cancel lab work referral form
	 * @param offender offender
	 * @param facility facility
	 * @return model and view with the added attributes to cancel a lab work
	 * referral
	 */
	private ModelAndView prepareEditMav(final CancelLabWorkReferralForm form,
			final Offender offender, final Facility facility) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(CANCEL_LAB_WORK_REFERRAL_FORM_MODEL_KEY, form);
		mav.addObject(FACILITY_MODEL_KEY, facility);
		mav.addObject(STATUS_REASONS_MODEL_KEY, 
				this.cancelLabWorkReferralService
				.findCancellationStatusReasons());
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				LabWorkReferral.class,
				this.labWorkReferralPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				LabWorkReferralStatusReason.class,
				this.labWorkReferralStatusReasonPropertyEditorFactory
				.createPropertyEditor());
	}
}