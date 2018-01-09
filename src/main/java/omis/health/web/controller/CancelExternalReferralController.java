package omis.health.web.controller;

import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.facility.domain.Facility;
import omis.health.domain.ExternalReferral;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.ExternalReferralStatusReason;
import omis.health.exception.ExternalReferralCancellationException;
import omis.health.exception.WrongExternalReferralStatusReasonException;
import omis.health.service.CancelExternalReferralService;
import omis.health.validator.CancelExternalReferralFormValidator;
import omis.health.web.controller.delegate.HealthControllerDelegate;
import omis.health.web.form.CancelExternalReferralForm;
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
 * Controller to cancel external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 25, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/referral/external")
@PreAuthorize("hasRole('ADMIN')" 
		+ " or hasRole('HEALTH_ADMIN')" 
		+ " or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class CancelExternalReferralController {
	
	/* Views. */
	
	private static final String EDIT_VIEW_NAME
		= "health/referral/external/cancel/edit";
	
	/* Model keys. */
	
	private static final String CANCEL_EXTERNAL_REFERRAL_FORM_MODEL_KEY
		= "cancelExternalReferralForm";

	private static final String FACILITY_MODEL_KEY = "facility";

	private static final String CANCELLATION_STATUS_REASONS_MODEL_KEY
		= "cancellationStatusReasons";

	private static final String AUTHORIZATION_MODEL_KEY = "authorization";
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("healthControllerDelegate")
	private HealthControllerDelegate healthControllerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Property editor factory. */
	
	@Autowired
	@Qualifier("externalReferralPropertyEditorFactory")
	private PropertyEditorFactory externalReferralPropertyEditorFactory;
	
	@Autowired
	@Qualifier("externalReferralStatusReasonPropertyEditorFactory")
	private PropertyEditorFactory
	externalReferralStatusReasonPropertyEditorFactory;
	
	/* Services. */
	
	@Autowired
	@Qualifier("cancelExternalReferralService")
	private CancelExternalReferralService cancelExternalReferralService;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("cancelExternalReferralFormValidator")
	private CancelExternalReferralFormValidator
	cancelExternalReferralFormValidator;
	
	/* Constructors. */
	
	/** Instantiates a controller to cancel external referrals. */
	public CancelExternalReferralController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays screen to cancel external referral.
	 * 
	 * @param externalReferral external referral to cancel
	 * @return model and view to cancel external referral
	 */
	@RequestMapping(value = "/cancel.html", method = RequestMethod.GET)
	public ModelAndView edit(
		@RequestParam(value = "externalReferral", required = true)
			final ExternalReferral externalReferral) {
		CancelExternalReferralForm cancelExternalReferralForm
			= new CancelExternalReferralForm();
		ModelAndView mav = this.prepareEditMav(cancelExternalReferralForm,
				externalReferral.getAuthorization(),
				externalReferral.getAuthorization().getRequest().getFacility(),
				externalReferral.getAuthorization().getRequest().getOffender());
		return mav;
	}
	
	/**
	 * Cancels external referral.
	 * 
	 * @param externalReferral external referral to cancel
	 * @param cancelExternalReferralForm form from which to take information
	 * with which to cancel external referral
	 * @param result binding result
	 * @return redirect to facility referral center
	 * @throws ExternalReferralCancellationException if external referral
	 * is not scheduled
	 * @throws WrongExternalReferralStatusReasonException if the status reason
	 * is not for canceling
	 */
	@RequestMapping(value = "/cancel.html", method = RequestMethod.POST)
	public ModelAndView update(
		@RequestParam(value = "externalReferral", required = true)
			final ExternalReferral externalReferral,
		final CancelExternalReferralForm cancelExternalReferralForm,
		final BindingResult result)
					throws ExternalReferralCancellationException,
						WrongExternalReferralStatusReasonException {
		this.cancelExternalReferralFormValidator.validate(
				cancelExternalReferralForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplayMav(
					cancelExternalReferralForm, result,
					externalReferral.getAuthorization(),
					externalReferral.getAuthorization().getRequest()
					.getFacility(),
				externalReferral.getAuthorization().getRequest()
					.getOffender());
			return mav;
		}
		this.cancelExternalReferralService.cancel(externalReferral,
				cancelExternalReferralForm.getCancellationStatusReason());
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(
						externalReferral.getAuthorization().getRequest()
							.getFacility(), null,
						externalReferral.getOffenderAppointmentAssociation()
						.getAppointment().getDate(),
						externalReferral.getOffenderAppointmentAssociation()
						.getAppointment().getDate(), null, null));
	}
	
	/* Helper methods. */
	
	// Prepares model and view to cancel external referral
	private ModelAndView prepareEditMav(
			final CancelExternalReferralForm cancelExternalReferralForm,
			final ExternalReferralAuthorization authorization,
			final Facility facility, final Offender offender) {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(AUTHORIZATION_MODEL_KEY, authorization);
		mav.addObject(CANCEL_EXTERNAL_REFERRAL_FORM_MODEL_KEY,
				cancelExternalReferralForm);
		mav.addObject(FACILITY_MODEL_KEY, facility);
		List<ExternalReferralStatusReason> cancellationStatusReasons
			= this.cancelExternalReferralService
				.findCancellationStatusReasons();
		mav.addObject(CANCELLATION_STATUS_REASONS_MODEL_KEY,
				cancellationStatusReasons);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	// Prepares model and view to redisplay screen to cancel external referral
	private ModelAndView prepareRedisplayMav(
			final CancelExternalReferralForm cancelExternalReferralForm,
			final BindingResult result,
			final ExternalReferralAuthorization authorization,
			final Facility facility, final Offender offender) {
		ModelAndView mav = this.prepareEditMav(cancelExternalReferralForm,
				authorization, facility, offender);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
					+ CANCEL_EXTERNAL_REFERRAL_FORM_MODEL_KEY, result);
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
	}
}