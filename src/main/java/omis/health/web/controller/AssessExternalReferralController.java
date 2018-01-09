package omis.health.web.controller;

import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.ExternalReferral;
import omis.health.domain.HealthRequest;
import omis.health.domain.ProviderLevel;
import omis.health.exception.ExternalReferralAssessmentException;
import omis.health.exception.FollowUpException;
import omis.health.service.ExternalReferralAssessmentService;
import omis.health.validator.AssessExternalReferralFormValidator;
import omis.health.web.controller.delegate.HealthControllerDelegate;
import omis.health.web.controller.delegate.ReferralTypeResolverDelegate;
import omis.health.web.form.AssessExternalReferralForm;
import omis.health.web.form.ReferralType;
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
 * Controller to assess external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 23, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/referral/external")
@PreAuthorize("hasRole('ADMIN')" 
		+ " or hasRole('HEALTH_ADMIN')" 
		+ " or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class AssessExternalReferralController {
	
	/* Redirects. */
	
	private static final String REQUEST_REQUIRED_LAB_WORK_REDIRECT
		= "redirect:/health/labWork/requirement/request/edit.html"
				+ "?healthRequest=%d";

	/* View names. */
	
	private static final String EDIT_VIEW_NAME
		= "health/referral/external/assess/edit";
	
	/* Model keys. */
	
	private static final String FACILITY_MODEL_KEY = "facility";

	private static final String ASSESS_EXTERNAL_REFERRAL_FORM_MODEL_KEY
		= "assessExternalReferralForm";
	
	private static final String FOLLOW_UP_REFERRAL_TYPES_MODEL_KEY
		= "followUpReferralTypes";

	private static final String FOLLOW_UP_REQUEST_PROVIDER_LEVELS_MODEL_KEY
		= "followUpRequestProviderLevels";

	private static final String AUTHORIZATION_MODEL_KEY = "authorization";
	
	/* Services. */
	
	@Autowired
	@Qualifier("externalReferralAssessmentService")
	private ExternalReferralAssessmentService externalReferralAssessmentService;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("assessExternalReferralFormValidator")
	private AssessExternalReferralFormValidator
	assessExternalReferralFormValidator;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("healthControllerDelegate")
	private HealthControllerDelegate healthControllerDelegate;
	
	@Autowired
	@Qualifier("referralTypeResolverDelegate")
	private ReferralTypeResolverDelegate referralTypeResolverDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("externalReferralPropertyEditorFactory")
	private PropertyEditorFactory externalReferralPropertyEditorFactory;
	
	@Autowired
	@Qualifier("providerLevelPropertyEditorFactory")
	private PropertyEditorFactory providerLevelPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Constructor. */
	
	/** Instantiates a controller to assess external referrals. */
	public AssessExternalReferralController() {
		// Default instantiation
	}

	/* URL invokable methods. */
	
	/**
	 * Displays screen to assess an external referral.
	 * 
	 * @param externalReferral external referral
	 * @param update whether to update assessment information
	 * @return screen to assess an external referral
	 */
	@RequestMapping(value = "/assess.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')"
			+ " or hasRole('EXTERNAL_REFERRAL_ASSESSMENT_EDIT')"
			+ " or hasRole('EXTERNAL_REFERRAL_ASSESSMENT_VIEW')"
			+ " or hasRole('HEALTH_ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "externalReferral", required = true)
				final ExternalReferral externalReferral,
			@RequestParam(value = "update", required = false)
				final Boolean update) {
		AssessExternalReferralForm assessExternalReferralForm
			= new AssessExternalReferralForm();
		if (this.externalReferralAssessmentService
				.isResolved(externalReferral)) {
			assessExternalReferralForm.setTime(externalReferral
					.getOffenderAppointmentAssociation().getAppointment()
					.getTimeKept());
			assessExternalReferralForm.setNotes(
					externalReferral.getAssessmentNotes());
			assessExternalReferralForm.setReportedDate(
					externalReferral.getReportedDate());
		}
		ModelAndView mav = this.prepareEditMav(assessExternalReferralForm,
				externalReferral.getAuthorization().getRequest().getOffender(),
				externalReferral.getAuthorization().getRequest().getFacility());
		mav.addObject(AUTHORIZATION_MODEL_KEY,
				externalReferral.getAuthorization());
		return mav;
	}
	
	/**
	 * Updates an external referral.
	 * 
	 * @param externalReferral external referral to update
	 * @param update whether to update assessment information
	 * @param assessExternalReferralForm form from which to get values with
	 * which to update
	 * @param result binding results
	 * @return redirect to facility referral center
	 * @throws DuplicateEntityFoundException if an identical follow up request
	 * exists
	 * @throws ExternalReferralAssessmentException if an attempt to assess an
	 * assessed referral is made
	 */
	@RequestMapping(value = "/assess.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')"
			+ " or hasRole('EXTERNAL_REFERRAL_ASSESSMENT_EDIT')"
			+ " or hasRole('HEALTH_ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "externalReferral", required = true)
				final ExternalReferral externalReferral,
			@RequestParam(value = "update", required = false)
				final Boolean update,
			final AssessExternalReferralForm assessExternalReferralForm,
			final BindingResult result)
					throws ExternalReferralAssessmentException,
						DuplicateEntityFoundException {
		this.assessExternalReferralFormValidator.validate(
				assessExternalReferralForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedispayMav(
					assessExternalReferralForm, result,
					externalReferral.getAuthorization().getRequest()
						.getOffender(),
					externalReferral.getAuthorization().getRequest()
						.getFacility());
			mav.addObject(AUTHORIZATION_MODEL_KEY,
					externalReferral.getAuthorization());
			return mav;
		}
		if (update != null && update) {
			this.externalReferralAssessmentService.update(externalReferral,
					assessExternalReferralForm.getTime(),
					assessExternalReferralForm.getNotes(),
					assessExternalReferralForm.getReportedDate());
		} else {
			this.externalReferralAssessmentService.assess(externalReferral,
				assessExternalReferralForm.getTime(),
				assessExternalReferralForm.getNotes(),
				assessExternalReferralForm.getReportedDate());
		}
		
		Date currentDate = new Date();
		
		String redirectUrl = null;
		
		// Do not allow editing of existing action requests
		if (externalReferral.getActionRequest() == null
				&& assessExternalReferralForm.getFollowUpReferralType()
					!= null) {
			final boolean asap;
			if (assessExternalReferralForm.getFollowUpAsap() != null
					&& assessExternalReferralForm.getFollowUpAsap()) {
				asap = true;
			} else {
				asap = false;
			}
			final HealthRequest followUpRequest;
			if (ReferralType.LAB.equals(assessExternalReferralForm
					.getFollowUpReferralType())) {
				try {
					followUpRequest = externalReferralAssessmentService
							.requestLabFollowUp(
									externalReferral, currentDate, asap,
									assessExternalReferralForm
										.getFollowUpRequestNotes());
				} catch (FollowUpException e) {
					throw new RuntimeException(
							"Logically impossible condition has arisen: "
											+ e.getMessage(), e);
				}
			} else {
				boolean labsRequired;
				if (assessExternalReferralForm.getFollowUpLabsRequired()
							!= null) {
					labsRequired = assessExternalReferralForm
							.getFollowUpLabsRequired(); 
				} else {
					labsRequired = false;
				}
				try {
					followUpRequest = externalReferralAssessmentService
							.requestFollowUp(
									externalReferral, currentDate,
									this.referralTypeResolverDelegate
										.resolveToRequestCategory(
												assessExternalReferralForm
												.getFollowUpReferralType()),
											labsRequired, asap,
											assessExternalReferralForm
											.getFollowUpRequestProviderLevel(),
											assessExternalReferralForm
												.getFollowUpRequestNotes());
				} catch (FollowUpException e) {
					throw new RuntimeException(
							"Logically impossible condition has arisen: "
									+ e.getMessage(), e);
				}
				if (labsRequired) {
					redirectUrl = String.format(
							REQUEST_REQUIRED_LAB_WORK_REDIRECT,
							followUpRequest.getId());
				}
			}
		}
		if (redirectUrl != null) {
			return new ModelAndView(redirectUrl);
		} else {
			return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(
						externalReferral.getAuthorization().getRequest()
							.getFacility(), null,
						externalReferral.getOffenderAppointmentAssociation()
							.getAppointment().getDate(),
						externalReferral.getOffenderAppointmentAssociation()
							.getAppointment().getDate(), null, null));
		}
	}
	
	// Prepares and returns model and view to assess external referrals
	private ModelAndView prepareEditMav(
			final AssessExternalReferralForm assessExternalReferralForm,
			final Offender offender, final Facility facility) {
		List<ProviderLevel> followUpProviderLevels
			= this.externalReferralAssessmentService
				.findFollowUpProviderLevels();
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(FACILITY_MODEL_KEY, facility);
		mav.addObject(ASSESS_EXTERNAL_REFERRAL_FORM_MODEL_KEY,
				assessExternalReferralForm);
		mav.addObject(FOLLOW_UP_REFERRAL_TYPES_MODEL_KEY,
				ReferralType.supportedValues());
		mav.addObject(FOLLOW_UP_REQUEST_PROVIDER_LEVELS_MODEL_KEY,
				followUpProviderLevels);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	// Prepares and returns model and view to redisplay form to assess
	// external referrals
	private ModelAndView prepareRedispayMav(
			final AssessExternalReferralForm assessExternalReferralForm,
			final BindingResult result, final Offender offender,
			final Facility facility) {
		ModelAndView mav = this.prepareEditMav(
				assessExternalReferralForm, offender, facility);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ ASSESS_EXTERNAL_REFERRAL_FORM_MODEL_KEY, result);
		return mav;
	}
	
	/* Init binder. */
	
	/** Registers property editors. */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(ExternalReferral.class,
				this.externalReferralPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(ProviderLevel.class,
				this.providerLevelPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Date.class, "reportedDate",
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Date.class, "time",
				this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
	}
}
