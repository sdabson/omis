package omis.health.web.controller;

import java.util.Date;
import java.util.List;

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
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.ExternalReferralAuthorization;
import omis.health.domain.ExternalReferralAuthorizationRequest;
import omis.health.domain.ExternalReferralAuthorizationStatus;
import omis.health.domain.ExternalReferralMedicalPanelReviewDecisionStatus;
import omis.health.domain.ExternalReferralReason;
import omis.health.domain.HealthRequest;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.health.exception.HealthRequestException;
import omis.health.exception.HealthRequestFollowsUpMultipleReferralsException;
import omis.health.exception.ProviderException;
import omis.health.exception.ReferralAuthorizationException;
import omis.health.report.UnitReportService;
import omis.health.service.ExternalReferralAuthorizationService;
import omis.health.validator.RequestExternalReferralAuthorizationFormValidator;
import omis.health.web.controller.delegate.HealthControllerDelegate;
import omis.health.web.controller.delegate.ReferralSummaryControllerDelegate;
import omis.health.web.form.ReferralType;
import omis.health.web.form.RequestExternalReferralAuthorizationForm;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;

/**
 * Controller to request authorization for external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 27, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/referral/external/authorization")
@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class RequestExternalReferralAuthorizationController {
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME
		= "health/referral/external/authorization/request/edit";
	
	private static final String MEDICAL_FACILITY_OPTIONS_VIEW_NAME
		= "health/medicalFacility/includes/options";
	
	private static final String 
		EXTERNAL_PENDING_AUTHORIZATION_ROW_ACTION_MENU_VIEW =
		"/health/referral/includes/externalPendingAuthorizationRowActionMenu";
	
	/* Model keys. */
	
	private static final String
	REQUEST_EXTERNAL_REFERRAL_AUTHORIZATION_FORM_MODEL_KEY
		= "requestExternalReferralAuthorizationForm";

	private static final String FACILITY_MODEL_KEY = "facility";

	private static final String PROVIDER_ASSIGNMENTS_MODEL_KEY
		= "providerAssignments";

	private static final String REASONS_MODEL_KEY = "reasons";
	
	private static final String STATUSES_MODEL_KEY = "statuses";

	private static final String MEDICAL_FACILITIES_MODEL_KEY
		= "medicalFacilities";

	private static final String HEALTH_REQUEST_MODEL_KEY = "healthRequest";

	private static final String MEDICAL_PANEL_REVIEW_DECISION_STATUSES_MODEL_KEY
		= "medicalPanelReviewDecisionStatuses";

	private static final String REFERRED_BY_PROVIDER_ASSIGNMENTS_MODEL_KEY
		= "referredByProviderAssignments";
	
	private static final String UNIT_ABBREVIATION_MODEL_KEY
		= "unitAbbreviation";
	
	/* Services. */
	
	@Autowired
	@Qualifier("externalReferralAuthorizationService")
	private ExternalReferralAuthorizationService
	externalReferralAuthorizationService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("healthRequestPropertyEditorFactory")
	private PropertyEditorFactory healthRequestPropertyEditorFactory;
	
	@Autowired
	@Qualifier("providerAssignmentPropertyEditorFactory")
	private PropertyEditorFactory providerAssignmentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("externalReferralReasonPropertyEditorFactory")
	private PropertyEditorFactory externalReferralReasonPropertyEditorFactory;
	
	@Autowired
	@Qualifier("externalReferralAuthorizationRequestPropertyEditorFactory")
	private PropertyEditorFactory
	externalReferralAuthorizationRequestPropertyEditorFactory;
	
	@Autowired
	@Qualifier("facilityPropertyEditorFactory")
	private PropertyEditorFactory facilityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("medicalFacilityPropertyEditorFactory")
	private PropertyEditorFactory medicalFacilityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("requestExternalReferralAuthorizationFormValidator")
	private RequestExternalReferralAuthorizationFormValidator
	requestExternalReferralAuthorizationFormValidator;
	
	/* Report service. */
	
	@Autowired
	@Qualifier("unitReportService")
	private UnitReportService unitReportService;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("healthControllerDelegate")
	private HealthControllerDelegate healthControllerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("referralSummaryControllerDelegate")
	private ReferralSummaryControllerDelegate referralSummaryControllerDelegate;
	
	/* Constructors. */
	
	/**
	 * Instantiates a controller to request authorization for external
	 * referrals.
	 */
	public RequestExternalReferralAuthorizationController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays a screen to request authorization for external referrals from
	 * health request.
	 * 
	 * @param healthRequest health request
	 * @return screen to request authorization for external referrals
	 * @throws HealthRequestFollowsUpMultipleReferralsException if the request
	 * follows up multiple referrals
	 */
	@RequestMapping(
			value = "/request/createFromRequest.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_CREATE')")
	public ModelAndView createFromHealthRequest(
			@RequestParam(value = "healthRequest", required = true)
				final HealthRequest healthRequest)
					throws HealthRequestFollowsUpMultipleReferralsException {
		RequestExternalReferralAuthorizationForm
			requestExternalReferralAuthorizationForm
				= new RequestExternalReferralAuthorizationForm();
		requestExternalReferralAuthorizationForm.setOffenderRequired(false);
		Date currentDate = new Date();
		ModelAndView mav;
		try {
			mav = this.prepareEditMav(
					requestExternalReferralAuthorizationForm,
					healthRequest.getFacility(), null, currentDate);
		} catch (ProviderException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		mav.addObject(HEALTH_REQUEST_MODEL_KEY, healthRequest);
		this.addOffenderSummary(mav.getModelMap(),
				healthRequest.getOffender(), currentDate);
		this.referralSummaryControllerDelegate.addOriginalForActionRequest(
				mav.getModelMap(), healthRequest, currentDate);
		return mav;
	}
	
	/**
	 * Displays a screen to request authorization for external referrals.
	 * 
	 * @param facility facility
	 * @param defaultOffender default offender
	 * @return screen to request authorization for external referrals
	 */
	@RequestMapping(value = "/create.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_CREATE')")
	public ModelAndView create(
			@RequestParam(value = "facility", required = true)
				final Facility facility,
			@RequestParam(value = "defaultOffender", required = false)
				final Offender defaultOffender) {
		RequestExternalReferralAuthorizationForm
		requestExternalReferralAuthorizationForm
				= new RequestExternalReferralAuthorizationForm();
		requestExternalReferralAuthorizationForm.setOffenderRequired(true);
		requestExternalReferralAuthorizationForm.setOffender(defaultOffender);
		Date currentDate = new Date();
		ModelAndView mav;
		try {
			mav = this.prepareEditMav(
					requestExternalReferralAuthorizationForm,
					facility, null, currentDate);
		} catch (ProviderException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		if (defaultOffender != null) {
			this.addOffenderSummary(mav.getModelMap(), defaultOffender,
					currentDate);
		}
		return mav;
	}
	
	/**
	 * Displays screen to edit external referral authorization request.
	 * 
	 * @param request external referral authorization request to edit
	 * @return screen to edit external referral authorization request
	 */
	@RequestMapping(value = "/request/edit.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_EDIT')" +
			" or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_VIEW')")
	public ModelAndView edit(
			@RequestParam(value = "request", required = true)
				final ExternalReferralAuthorizationRequest request) {
		RequestExternalReferralAuthorizationForm
		requestExternalReferralAuthorizationForm
			= new RequestExternalReferralAuthorizationForm();
		requestExternalReferralAuthorizationForm
			.setMedicalFacility(request.getMedicalFacility());
		requestExternalReferralAuthorizationForm
			.setProviderAssignment(request.getProviderAssignment());
		requestExternalReferralAuthorizationForm
			.setReason(request.getReason());
		requestExternalReferralAuthorizationForm.setReasonNotes(
				request.getReasonNotes());
		requestExternalReferralAuthorizationForm
			.setReferredByProviderAssignment(
					request.getReferredByProviderAssignment());
		requestExternalReferralAuthorizationForm.setReferredDate(
				request.getReferredDate());
		requestExternalReferralAuthorizationForm.setOffenderRequired(false);
		if (this.externalReferralAuthorizationService
				.hasAuthorization(request)) {
			ExternalReferralAuthorization authorization
				= this.externalReferralAuthorizationService
					.findAuthorization(request);
			requestExternalReferralAuthorizationForm.setAuthorize(true);
			requestExternalReferralAuthorizationForm.setDecisionDate(
					authorization.getDecisionDate());
			requestExternalReferralAuthorizationForm.setAuthorizedBy(
					authorization.getAuthorizedBy());
			if (authorization.getAuthorizedBy() != null) {
				requestExternalReferralAuthorizationForm.setAuthorizedByText(
						String.format("%s, %s",
					authorization.getAuthorizedBy().getName().getLastName(),
					authorization.getAuthorizedBy().getName().getFirstName()));
			}
			requestExternalReferralAuthorizationForm.setStatus(
					authorization.getStatus());
			if (ExternalReferralAuthorizationStatus.PANEL_REVIEW_REQUIRED
					.equals(authorization.getStatus())
					&& authorization.getPanelReviewDecision() != null) {
				requestExternalReferralAuthorizationForm.setReviewDate(
						authorization.getPanelReviewDecision().getReviewDate());
				requestExternalReferralAuthorizationForm
						.setMedicalPanelReviewDecisionStatus(
								authorization.getPanelReviewDecision()
									.getStatus());
			}
			
		} else {
			requestExternalReferralAuthorizationForm.setAuthorize(false);
		}
		Date currentDate = new Date();
		ModelAndView mav;
		try {
			mav = this.prepareEditMav(
					requestExternalReferralAuthorizationForm,
					request.getFacility(), request.getMedicalFacility(),
					currentDate);
		} catch (ProviderException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		this.addOffenderSummary(mav.getModelMap(),
				request.getOffender(), currentDate);
		return mav;
	}
	
	/**
	 * Request authorization for external referral from health request.
	 * 
	 * @param healthRequest health request
	 * @param requestExternalReferralAuthorizationForm form to request
	 * authorization for external referral
	 * @param result binding result
	 * @return redirect to facility referral center for external referrals
	 * @throws DuplicateEntityFoundException if request exists
	 * @throws ReferralAuthorizationException if referral cannot be authorized
	 * @throws HealthRequestException if the health request is already resolved
	 * by an external referral
	 * @throws HealthRequestFollowsUpMultipleReferralsException if the request
	 * follows up multiple referrals
	 */
	@RequestMapping(
			value = "/request/createFromRequest.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_CREATE')")
	public ModelAndView saveFromHealthRequest(
			@RequestParam(value = "healthRequest", required = true)
				final HealthRequest healthRequest,
			final RequestExternalReferralAuthorizationForm
				requestExternalReferralAuthorizationForm,
			final BindingResult result)
					throws DuplicateEntityFoundException,
					ReferralAuthorizationException, HealthRequestException {
		Date currentDate = new Date();
		Facility facility = healthRequest.getFacility();
		this.requestExternalReferralAuthorizationFormValidator
			.validate(requestExternalReferralAuthorizationForm, result);
		if (result.hasErrors()) {
			ModelAndView mav;
			try {
				mav = this.prepareRedisplayMav(
						requestExternalReferralAuthorizationForm, result,
						facility,
						requestExternalReferralAuthorizationForm
							.getMedicalFacility(),
						currentDate);
			} catch (ProviderException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
			mav.addObject(HEALTH_REQUEST_MODEL_KEY, healthRequest);
			this.addOffenderSummary(mav.getModelMap(),
					healthRequest.getOffender(), currentDate);
			this.referralSummaryControllerDelegate.addOriginalForActionRequest(
					mav.getModelMap(), healthRequest, currentDate);
			return mav;
		}
		ExternalReferralAuthorizationRequest request
			= this.externalReferralAuthorizationService
				.requestAuthorizationFromHealthRequest(
					healthRequest, currentDate,
					requestExternalReferralAuthorizationForm
						.getProviderAssignment(),
					requestExternalReferralAuthorizationForm.getMedicalFacility(),
					requestExternalReferralAuthorizationForm.getReason(),
					requestExternalReferralAuthorizationForm.getReasonNotes(),
					requestExternalReferralAuthorizationForm
						.getReferredByProviderAssignment(),
					requestExternalReferralAuthorizationForm.getReferredDate());
		if (requestExternalReferralAuthorizationForm.getAuthorize() != null
				&& requestExternalReferralAuthorizationForm.getAuthorize()) {
			this.createAuthorization(
					request, requestExternalReferralAuthorizationForm);
		}
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(
						healthRequest.getFacility(), null,
						null, null, ReferralType.EXTERNAL_MEDICAL, null));
	}
	
	/**
	 * Request authorization for external referral.
	 *  
	 * @param facility facility
	 * @param defaultOffender default offender
	 * @param requestExternalReferralAuthorizationForm form to request
	 * authorization for external referral
	 * @param result binding result
	 * @return redirect to facility referral center for external referrals
	 * @throws DuplicateEntityFoundException if request exists
	 * @throws ReferralAuthorizationException if referral cannot be authorized
	 */
	@RequestMapping(
			value = "/create.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')"+
			" or hasRole('HEALTH_ADMIN')" +
		    " or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_CREATE')")
	public ModelAndView save(
			@RequestParam(value = "facility", required = true)
				final Facility facility,
			@RequestParam(value = "defaultOffender", required = false)
				final Offender defaultOffender,
			final RequestExternalReferralAuthorizationForm
				requestExternalReferralAuthorizationForm,
			final BindingResult result)
					throws DuplicateEntityFoundException,
					ReferralAuthorizationException {
		Date currentDate = new Date();
		this.requestExternalReferralAuthorizationFormValidator
			.validate(requestExternalReferralAuthorizationForm, result);
		if (result.hasErrors()) {
			ModelAndView mav;
			try {
				mav = this.prepareRedisplayMav(
						requestExternalReferralAuthorizationForm, result,
						facility,
						requestExternalReferralAuthorizationForm
							.getMedicalFacility(), currentDate);
			} catch (ProviderException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
			if (defaultOffender != null) {
				this.addOffenderSummary(mav.getModelMap(), defaultOffender,
						currentDate);
			}
			return mav;
		}
		ExternalReferralAuthorizationRequest request
			= this.externalReferralAuthorizationService.requestAuthorization(
				requestExternalReferralAuthorizationForm.getOffender(),
				currentDate, requestExternalReferralAuthorizationForm
					.getProviderAssignment(),
				requestExternalReferralAuthorizationForm
					.getMedicalFacility(),
				facility, requestExternalReferralAuthorizationForm
					.getReason(),
				requestExternalReferralAuthorizationForm.getReasonNotes(),
				requestExternalReferralAuthorizationForm
					.getReferredByProviderAssignment(),
				requestExternalReferralAuthorizationForm.getReferredDate());
		if (requestExternalReferralAuthorizationForm.getAuthorize() != null
				&& requestExternalReferralAuthorizationForm.getAuthorize()) {
			this.createAuthorization(
					request, requestExternalReferralAuthorizationForm);
		}
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(facility,
						null, null, null, ReferralType.EXTERNAL_MEDICAL, null));
	}
	
	/**
	 * Updates an external referral authorization request.
	 * 
	 * @param request request to update
	 * @param requestExternalReferralAuthorizationForm form from which to
	 * update request
	 * @param result binding results
	 * @return redirect to facility referral center for external referrals
	 * @throws DuplicateEntityFoundException if request exists
	 * @throws ReferralAuthorizationException if referral cannot be authorized
	 */
	@RequestMapping(value = "/request/edit.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('EXTERNAL_REFERRAL_AUTHORIZATION_REQUEST_EDIT')")
	public ModelAndView update(
			@RequestParam(value = "request", required = true)
				final ExternalReferralAuthorizationRequest request,
			final RequestExternalReferralAuthorizationForm
				requestExternalReferralAuthorizationForm,
			final BindingResult result)
					throws DuplicateEntityFoundException,
					ReferralAuthorizationException  {
		Date currentDate = new Date();
		this.requestExternalReferralAuthorizationFormValidator
			.validate(requestExternalReferralAuthorizationForm, result);
		if (result.hasErrors()) {
			ModelAndView mav;
			try {
				mav = this.prepareRedisplayMav(
						requestExternalReferralAuthorizationForm, result,
						request.getFacility(),
						requestExternalReferralAuthorizationForm
							.getMedicalFacility(), currentDate);
			} catch (ProviderException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
			this.addOffenderSummary(mav.getModelMap(),
					request.getOffender(), currentDate);
			return mav;
		}
		this.externalReferralAuthorizationService.updateRequest(request,
				requestExternalReferralAuthorizationForm
					.getProviderAssignment(),
				requestExternalReferralAuthorizationForm.getMedicalFacility(),
				requestExternalReferralAuthorizationForm.getReason(),
				requestExternalReferralAuthorizationForm.getReasonNotes(),
				requestExternalReferralAuthorizationForm
					.getReferredByProviderAssignment(),
				requestExternalReferralAuthorizationForm.getReferredDate());
		if (requestExternalReferralAuthorizationForm.getAuthorize() != null
				&& requestExternalReferralAuthorizationForm.getAuthorize()) {
			if (this.externalReferralAuthorizationService
					.hasAuthorization(request)) {
				ExternalReferralAuthorization authorization
					= this.externalReferralAuthorizationService
						.findAuthorization(request);
				this.updateAuthorization(authorization,
						requestExternalReferralAuthorizationForm);
			} else {
				this.createAuthorization(
						request, requestExternalReferralAuthorizationForm);
			}
		} else if (this.externalReferralAuthorizationService
				.hasAuthorization(request)) {
			this.externalReferralAuthorizationService
				.removeRequestAuthorization(request);
		}
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(
						request.getFacility(),
						null, null, null, ReferralType.EXTERNAL_MEDICAL, null));
	}
	
	/* AJAX invokable methods. */

	/**
	 * Returns options for medical facilities for provider.
	 * 
	 * @param providerAssignment assignment of provider
	 * @return options for medical facilities for provider
	 * @throws ProviderException if the provider is not external
	 */
	@RequestMapping(
			value = "/findMedicalFacilitiesByProvider.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('HEALTH_ADMIN')")
	public ModelAndView findMedicalFacilitiesForProvider(
			@RequestParam(value = "providerAssignment", required = true)
				final ProviderAssignment providerAssignment)
						throws ProviderException {
		List<MedicalFacility> medicalFacilities
			= this.externalReferralAuthorizationService
				.findProviderMedicalFacilities(providerAssignment);
		ModelAndView mav = new ModelAndView(MEDICAL_FACILITY_OPTIONS_VIEW_NAME);
		mav.addObject(MEDICAL_FACILITIES_MODEL_KEY, medicalFacilities);
		return mav;
	}
	
	/**
	 * Displays action menu for pending authorization rows.
	 * 
	 * @param pendingAuthorization pending authorization
	 * @return action menu for external scheduled rows
	 */
	@RequestContentMapping(
			nameKey = "externalPendingAuthorizationRowActionMenuName",
			descriptionKey = "externalPendingAuthorizationRowActionMenuName",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.AJAX_REQUEST)
	@RequestMapping(value = "/pendingAuthorizationRowActionMenu.html",
		method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" +
			" or hasRole('HEALTH_ADMIN')" +
			" or hasRole('REFERRAL_CENTER')")
	public ModelAndView externalPendingAuthorizationRowActionMenu(
			@RequestParam(value = "pendingAuthorization", required = true)
				final ExternalReferralAuthorizationRequest 
					pendingAuthorization) {
		ModelMap map = new ModelMap();
		map.put("pendingAuthorization", pendingAuthorization);
		return new ModelAndView(EXTERNAL_PENDING_AUTHORIZATION_ROW_ACTION_MENU_VIEW,
				map);
	}
	
	/* Helper methods. */
	
	// Prepares edit model and view
	private ModelAndView prepareEditMav(
			final RequestExternalReferralAuthorizationForm
				requestExternalReferralAuthorizationForm,
			final Facility facility,
			final MedicalFacility medicalFacility,
			final Date date) throws ProviderException {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(REQUEST_EXTERNAL_REFERRAL_AUTHORIZATION_FORM_MODEL_KEY,
				requestExternalReferralAuthorizationForm);
		mav.addObject(FACILITY_MODEL_KEY, facility);
		List<ProviderAssignment> referredByProviderAssignments
			= this.externalReferralAuthorizationService
				.findProviderAssignmentsByFacility(facility, date);
		mav.addObject(REFERRED_BY_PROVIDER_ASSIGNMENTS_MODEL_KEY,
				referredByProviderAssignments);
		if (medicalFacility != null) {
			List<ProviderAssignment> providerAssignments
				= this.externalReferralAuthorizationService
					.findProviderAssignmentsByMedicalFacility(
						medicalFacility, date);
			mav.addObject(PROVIDER_ASSIGNMENTS_MODEL_KEY, providerAssignments);
		}
		List<ExternalReferralReason> reasons = this
				.externalReferralAuthorizationService.findReasons();
		mav.addObject(REASONS_MODEL_KEY, reasons);
		mav.addObject(STATUSES_MODEL_KEY,
				ExternalReferralAuthorizationStatus.values());
		mav.addObject(MEDICAL_PANEL_REVIEW_DECISION_STATUSES_MODEL_KEY,
				ExternalReferralMedicalPanelReviewDecisionStatus.values());
		List<MedicalFacility> medicalFacilities
			= this.externalReferralAuthorizationService
					.findMedicalFacilities();
		mav.addObject(MEDICAL_FACILITIES_MODEL_KEY, medicalFacilities);
		return mav;
	}
	
	// Prepares model and view to redisplay edit screen
	private ModelAndView prepareRedisplayMav(
			final RequestExternalReferralAuthorizationForm
			requestExternalReferralAuthorizationForm,
			final BindingResult result,
			final Facility facility,
			final MedicalFacility medicalFacility,
			final Date date) throws ProviderException {
		ModelAndView mav = this.prepareEditMav(
				requestExternalReferralAuthorizationForm, facility,
				medicalFacility, date);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ REQUEST_EXTERNAL_REFERRAL_AUTHORIZATION_FORM_MODEL_KEY,
				result);
		return mav;
	}
	
	// Saves an external referral authorization request
	private ExternalReferralAuthorization createAuthorization(
			final ExternalReferralAuthorizationRequest request,
			final RequestExternalReferralAuthorizationForm
				requestExternalReferralAuthorizationForm)
						throws ReferralAuthorizationException {
		final ExternalReferralAuthorization authorization;
		if (requestExternalReferralAuthorizationForm.getStatus() != null) {
			if (ExternalReferralAuthorizationStatus.APPROVED
					.equals(requestExternalReferralAuthorizationForm
							.getStatus())) {
				authorization = this.externalReferralAuthorizationService
					.authorizeRequest(request,
						requestExternalReferralAuthorizationForm
							.getDecisionDate(),
						requestExternalReferralAuthorizationForm
							.getAuthorizedBy(),
						requestExternalReferralAuthorizationForm
							.getAuthorizationNotes());		
			} else if (ExternalReferralAuthorizationStatus
					.INTERNAL_ALTERNATIVE_TREATMENT
					.equals(requestExternalReferralAuthorizationForm
							.getStatus())) {
				authorization = this.externalReferralAuthorizationService
					.approveInternalReferralAlternativeTreatmentFromRequest(
						request,
						requestExternalReferralAuthorizationForm
							.getDecisionDate(),
						requestExternalReferralAuthorizationForm
							.getAuthorizedBy(),
						requestExternalReferralAuthorizationForm
							.getAuthorizationNotes());
			} else if (ExternalReferralAuthorizationStatus
					.EXTERNAL_ALTERNATIVE_TREATMENT
					.equals(requestExternalReferralAuthorizationForm
							.getStatus())) {
				authorization = this.externalReferralAuthorizationService
					.approveExternalReferralAlternativeTreatmentFromRequest(
						request,
						requestExternalReferralAuthorizationForm
							.getDecisionDate(),
						requestExternalReferralAuthorizationForm
							.getAuthorizedBy(),
						requestExternalReferralAuthorizationForm
							.getAuthorizationNotes());	
			} else if (ExternalReferralAuthorizationStatus
					.PANEL_REVIEW_REQUIRED.equals(
							requestExternalReferralAuthorizationForm
								.getStatus())) {
				authorization =
						this.externalReferralAuthorizationService
							.requireRequestReview(request,
								requestExternalReferralAuthorizationForm
									.getDecisionDate(),
								requestExternalReferralAuthorizationForm
									.getAuthorizedBy(),
								requestExternalReferralAuthorizationForm
									.getAuthorizationNotes());
				if (requestExternalReferralAuthorizationForm.getReviewDate()
						!= null
					&& requestExternalReferralAuthorizationForm
						.getMedicalPanelReviewDecisionStatus() != null) {
					this.externalReferralAuthorizationService
						.reviewAuthorization(authorization,
								requestExternalReferralAuthorizationForm
									.getReviewDate(),
								requestExternalReferralAuthorizationForm
									.getMedicalPanelReviewDecisionStatus());
				} else if ((requestExternalReferralAuthorizationForm
						.getReviewDate() != null
					&& requestExternalReferralAuthorizationForm
						.getMedicalPanelReviewDecisionStatus() == null)
						|| (requestExternalReferralAuthorizationForm
								.getReviewDate() == null
					&& requestExternalReferralAuthorizationForm
						.getMedicalPanelReviewDecisionStatus() != null)) {
					throw new ReferralAuthorizationException(
							"Decision status must be set with review date");
				}
			} else {
				throw new UnsupportedOperationException(
						"Unsupported operation: "
								+ requestExternalReferralAuthorizationForm
									.getStatus());
			}
		} else {
			throw new RuntimeException("Status not set");
		}
		return authorization;
	}
	
	// Updates external referral authorization
	private ExternalReferralAuthorization updateAuthorization(
			final ExternalReferralAuthorization authorization,
			final RequestExternalReferralAuthorizationForm
			requestExternalReferralAuthorizationForm)
					throws ReferralAuthorizationException {
		final ExternalReferralAuthorization updatedAuthorization;
		if (requestExternalReferralAuthorizationForm.getStatus() != null) {
			if (ExternalReferralAuthorizationStatus.APPROVED
					.equals(requestExternalReferralAuthorizationForm
							.getStatus())) {
				updatedAuthorization = this.externalReferralAuthorizationService
					.authorize(authorization,
						requestExternalReferralAuthorizationForm
							.getDecisionDate(),
						requestExternalReferralAuthorizationForm
							.getAuthorizedBy(),
						requestExternalReferralAuthorizationForm
							.getAuthorizationNotes());		
			} else if (ExternalReferralAuthorizationStatus
						.INTERNAL_ALTERNATIVE_TREATMENT
					.equals(requestExternalReferralAuthorizationForm
							.getStatus())) {
				updatedAuthorization = this.externalReferralAuthorizationService
					.approveInternalReferralAlternativeTreatment(
							authorization,
						requestExternalReferralAuthorizationForm
							.getDecisionDate(),
						requestExternalReferralAuthorizationForm
							.getAuthorizedBy(),
						requestExternalReferralAuthorizationForm
							.getAuthorizationNotes());
			} else if (ExternalReferralAuthorizationStatus
						.EXTERNAL_ALTERNATIVE_TREATMENT
				.equals(requestExternalReferralAuthorizationForm
						.getStatus())) {
				updatedAuthorization = this.externalReferralAuthorizationService
					.approveExternalReferralAlternativeTreatment(
						authorization,
					requestExternalReferralAuthorizationForm
						.getDecisionDate(),
					requestExternalReferralAuthorizationForm
						.getAuthorizedBy(),
					requestExternalReferralAuthorizationForm
						.getAuthorizationNotes());
			} else if (ExternalReferralAuthorizationStatus
					.PANEL_REVIEW_REQUIRED.equals(
							requestExternalReferralAuthorizationForm
								.getStatus())) {
				updatedAuthorization =
						this.externalReferralAuthorizationService
							.requireReview(authorization,
								requestExternalReferralAuthorizationForm
									.getDecisionDate(),
								requestExternalReferralAuthorizationForm
									.getAuthorizedBy(),
								requestExternalReferralAuthorizationForm
									.getAuthorizationNotes());
				if (requestExternalReferralAuthorizationForm.getReviewDate()
						!= null
					&& requestExternalReferralAuthorizationForm.getStatus()
						!= null) {
					this.externalReferralAuthorizationService
						.reviewAuthorization(authorization,
								requestExternalReferralAuthorizationForm
									.getReviewDate(),
								requestExternalReferralAuthorizationForm
									.getMedicalPanelReviewDecisionStatus());
				} else if (requestExternalReferralAuthorizationForm
						.getReviewDate() != null
					|| requestExternalReferralAuthorizationForm
						.getMedicalPanelReviewDecisionStatus() != null) {
					throw new ReferralAuthorizationException(
							"Decision status must be set with review date");
				}
			} else {
				throw new UnsupportedOperationException(
						"Unsupported operation: "
								+ requestExternalReferralAuthorizationForm
									.getStatus());
			}
		} else {
			throw new RuntimeException("Status not set");
		}
		return updatedAuthorization;
	}
	
	/* Adds offender summary to model and view. */
	private void addOffenderSummary(final ModelMap map,
			final Offender offender, final Date date) {
		this.offenderSummaryModelDelegate.add(map, offender);
		String unitAbbreviation = this.unitReportService
				.findUnitAbbreviation(offender, date);
		map.addAttribute(UNIT_ABBREVIATION_MODEL_KEY, unitAbbreviation);
	}
	
	/* Init binder. */
	
	/**
	 * Registers property editors.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(HealthRequest.class,
				this.healthRequestPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(ProviderAssignment.class,
				this.providerAssignmentPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(ExternalReferralReason.class,
				this.externalReferralReasonPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(ExternalReferralAuthorizationRequest.class,
				this.externalReferralAuthorizationRequestPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Facility.class,
				this.facilityPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(MedicalFacility.class,
				this.medicalFacilityPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(Person.class,
				this.personPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
	}
}