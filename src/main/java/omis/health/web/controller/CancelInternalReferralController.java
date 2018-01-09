package omis.health.web.controller;

import omis.beans.factory.PropertyEditorFactory;
import omis.facility.domain.Facility;
import omis.health.domain.InternalReferral;
import omis.health.domain.InternalReferralStatusReason;
import omis.health.exception.InternalReferralCancellationException;
import omis.health.exception.WrongInternalReferralStatusReasonException;
import omis.health.service.CancelInternalReferralService;
import omis.health.validator.CancelInternalReferralFormValidator;
import omis.health.web.controller.delegate.HealthControllerDelegate;
import omis.health.web.form.CancelInternalReferralForm;
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

/** Cancel internal referral.
 * @author Ryan Johns
 * @author Stephen Abson
 * @version 0.1.0 (May 13, 2014)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/health/referral/internal")
@PreAuthorize("hasRole('ADMIN')"
		+" or hasRole('HEALTH_ADMIN')" 
		+" or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class CancelInternalReferralController {
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME
		= "health/referral/internal/cancel/edit";
	
	/* Model keys. */
	
	private static final String CANCEL_INTERNAL_REFERAL_FORM_MODEL_KEY
		= "cancelInternalReferralForm";

	private static final String FACILITY_MODEL_KEY = "facility";

	private static final String STATUS_REASONS_MODEL_KEY = "statusReasons";

	/* Helpers. */
	
	@Autowired
	private HealthControllerDelegate healthControllerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Property editor factories. */
	
	@Autowired
	private PropertyEditorFactory internalReferralPropertyEditorFactory;

	@Autowired
	private PropertyEditorFactory
	internalReferralStatusReasonPropertyEditorFactory;
	
	/* Services. */
	
	@Autowired
	private CancelInternalReferralService cancelInternalReferralService;
	
	/* Validators. */
	
	@Autowired
	private CancelInternalReferralFormValidator
	cancelInternalReferralFormValidator;
	
	/* Constructors. */
	
	/** Instantiates a controller to cancel internal referrals. */
	public CancelInternalReferralController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows form to cancel internal referral.
	 * 
	 * @param internalReferral internal referral to cancel
	 * @return form to cancel internal referral
	 */
	@RequestMapping(value = "/cancel.html", method =  RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('INTERNAL_REFERRAL_CANCEL')")
	public ModelAndView edit(
			@RequestParam(value = "internalReferral", required = true)
				final InternalReferral internalReferral) {
		CancelInternalReferralForm cancelInternalReferralForm
			= new CancelInternalReferralForm();
		cancelInternalReferralForm.setStatusReason(
				internalReferral.getStatusReason());
		return this.prepareEditMav(cancelInternalReferralForm,
				internalReferral.getOffenderAppointmentAssociation()
					.getOffender(),
				internalReferral.getOffenderAppointmentAssociation()
					.getAppointment().getFacility());
	}
	
	/** Cancels internal referral.
	 * @param internalReferral internal referral.
	 * @param cancelInternalReferralForm form to cancel internal referrals
	 * @param result binding result
	 * @param facility facility.
	 * @return referral center. 
	 * @throws WrongInternalReferralStatusReasonException if the status reason
	 * is not for cancellation 
	 * @throws InternalReferralCancellationException if the internal referral
	 * is not scheduled
	 */
	@RequestMapping(value = "/cancel.html", method =  RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or hasRole('INTERNAL_REFERRAL_CANCEL')")
	public ModelAndView update(
			@RequestParam(value = "internalReferral", required = true)
				final InternalReferral internalReferral,
			final CancelInternalReferralForm cancelInternalReferralForm,
			final BindingResult result)
							throws InternalReferralCancellationException,
							WrongInternalReferralStatusReasonException {
		final Facility facility = internalReferral
				.getOffenderAppointmentAssociation()
					.getAppointment().getFacility();
		final Offender offender = internalReferral
				.getOffenderAppointmentAssociation().getOffender();
		this.cancelInternalReferralFormValidator
			.validate(cancelInternalReferralForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareEditMav(cancelInternalReferralForm,
					offender, facility);
			mav.addObject(BindingResult.MODEL_KEY_PREFIX
					+ CANCEL_INTERNAL_REFERAL_FORM_MODEL_KEY, result);
			return mav;
		}
		this.cancelInternalReferralService.cancel(internalReferral,
				cancelInternalReferralForm.getStatusReason());
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(facility, null,
						internalReferral
							.getOffenderAppointmentAssociation()
								.getAppointment().getDate(),
						internalReferral
							.getOffenderAppointmentAssociation()
								.getAppointment().getDate(), null, null));
	}

	/* Helper methods. */
	
	// Prepares model and view to cancel internal referral
	private ModelAndView prepareEditMav(
			final CancelInternalReferralForm cancelInternalReferralForm,
			final Offender offender, final Facility facility) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(CANCEL_INTERNAL_REFERAL_FORM_MODEL_KEY,
				cancelInternalReferralForm);
		mav.addObject(FACILITY_MODEL_KEY, facility);
		mav.addObject(STATUS_REASONS_MODEL_KEY,
				this.cancelInternalReferralService
					.findCancellationStatusReasons());
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}

	/* Init binder. */
	
	/** Init Binder.
	 * @param binder binder. */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(InternalReferral.class,
				this.internalReferralPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(InternalReferralStatusReason.class,
				this.internalReferralStatusReasonPropertyEditorFactory
				.createPropertyEditor());
	}
}
