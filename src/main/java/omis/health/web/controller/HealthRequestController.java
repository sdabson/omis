package omis.health.web.controller;

import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.HealthRequest;
import omis.health.domain.HealthRequestCategory;
import omis.health.domain.ProviderLevel;
import omis.health.exception.HealthRequestException;
import omis.health.report.HealthRequestReportService;
import omis.health.report.HealthRequestSummary;
import omis.health.service.HealthRequestService;
import omis.health.validator.HealthRequestFormValidator;
import omis.health.web.controller.delegate.HealthControllerDelegate;
import omis.health.web.controller.delegate.ReferralTypeResolverDelegate;
import omis.health.web.form.HealthRequestForm;
import omis.health.web.form.ReferralType;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/** Health request controller for health request related operations.
 * @author Ryan Johns (May 02, 2014)
 * @author Stephen Abson
 * @author Joel Norris
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/health/request")
@PreAuthorize("hasRole('ADMIN')" 
		+" or hasRole('HEALTH_ADMIN')"
		+" or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class HealthRequestController {

	/* Redirects. */

	private static final String SCHEDULE_INTERNAL_HEALTH_REQUEST_REDIRECT
		= "redirect:/health/referral/internal/scheduleFromRequest.html"
				+ "?facility=%1$d&request=%2$d";

	private static final String SCHEDULE_EXTERNAL_HEALTH_REQUEST_REDIRECT
		= "redirect:/health/referral/external/scheduleFromRequest.html"
				+ "?facility=%1$d&request=%2$d";
	
	private static final String SCHEDULE_LAB_WORK_HEALTH_REQUEST_REDIRECT
	 	= "redirect:/health/referral/labWork/scheduleFromRequest.html"
	 			+ "?facility=%1$d&request=%2$d";
	
	private static final String LIST_REDIRECT
		= "redirect:/health/request/list.html?facility=%d&resolvedOnly=%s"
				+ "&referralType=%s";
	
	/* View names. */

	private static final String EDIT_VIEW_NAME = "health/request/edit";

	private static final String LIST_VIEW_NAME = "health/request/list";
	
	/* Model keys. */

	private static final String HEALTH_REQUEST_FORM_MODEL_KEY
		= "healthRequestForm";

	private static final String CATEGORIES_MODEL_KEY = "categories";

	private static final String HEALTH_REQUEST_MODEL_KEY = "request";

	private static final String FACILITY_MODEL_KEY = "facility";

	private static final String PROVIDER_LEVELS_MODEL_KEY = "providerLevels";

	private static final String HEALTH_REQUEST_SUMMARIES_MODEL_KEY
		= "healthRequestSummaries";

	private static final String REFERRAL_TYPE_MODEL_KEY = "referralType";

	/* Property editor factories. */

	@Autowired
	private PropertyEditorFactory healthRequestPropertyEditorFactory;

	@Autowired
	private PropertyEditorFactory providerLevelPropertyEditorFactory;
	
	@Autowired
	private PropertyEditorFactory facilityPropertyEditorFactory;
	
	@Autowired
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/* Helpers. */

	@Autowired
	private HealthControllerDelegate healthControllerDelegate;

	@Autowired
	private ReferralTypeResolverDelegate referralTypeResolverDelegate;

	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;

	/* Validators. */

	@Autowired
	private HealthRequestFormValidator healthRequestFormValidator;

	/* Services. */

	@Autowired
	private HealthRequestService healthRequestService;
	
	/* Report services. */
	
	@Autowired
	private HealthRequestReportService healthRequestReportService;
	
	/* Constructors. */
	
	/** Instantiates a default controller for health requests. */
	public HealthRequestController() {
		// Default instantiation
	}

	/* URL invokable methods. */

	/** Redirects based on the type of referral to be scheduled.
	 * @param healthRequest health request.
	 * @return model and view redirect.
	 */
	@RequestMapping(value = "/schedule.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" 
			+" or hasRole('HEALTH_ADMIN')"
			+" or hasRole('HEALTH_REQUEST_SCHEDULE')")
	public ModelAndView scheduleHealthRequest(
			@RequestParam(value = "request", required = true)
			final HealthRequest healthRequest) {
		return new ModelAndView(String.format(
				this.getHealthRequestCategoryRedirects(
						healthRequest.getCategory()),
						healthRequest.getFacility().getId(),
						healthRequest.getId()));
	}

	/** Cancels request.
	 * @param healthRequest health request.
	 * @return referral center. */
	@RequestContentMapping(nameKey = "cancelHealthRequestScreenName",
			descriptionKey = "cancelHealthRequestScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/cancel.html", method =  RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" 
			+" or hasRole('HEALTH_ADMIN')"
			+" or hasRole('HEALTH_REQUEST_CANCEL')")
	public ModelAndView cancel(@RequestParam(value = "request", required = true)
		final HealthRequest healthRequest) {
		this.healthRequestService.cancel(healthRequest);
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(
						healthRequest.getFacility(),
						null, null, null, ReferralType.ALL, null));
	}

	/**
	 * Displays a form to create a health request.
	 *
	 * @return form to create health request
	 */
	@RequestContentMapping(nameKey = "createHealthRequestScreenName",
			descriptionKey = "createHealthRequestScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html", method =  RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')"
			+" or hasRole('HEALTH_ADMIN')"
			+" or hasRole('HEALTH_REQUEST_VIEW')")
	public ModelAndView create(
			@RequestParam(value = "facility", required = true)
				final Facility facility,
			@RequestParam(value = "defaultOffender", required = false)
				final Offender defaultOffender) {
		final HealthRequestForm healthRequestForm = new HealthRequestForm();
		final ModelAndView mav = this.prepareEditMav(healthRequestForm, facility);
		if (defaultOffender != null) {
			this.offenderSummaryModelDelegate.add(
					mav.getModelMap(), defaultOffender);
			healthRequestForm.setOffenderRequired(false);
		} else {
			healthRequestForm.setOffenderRequired(true);
		}
		return mav;
	}
	
	/**
	 * Displays a form to edit a health request.
	 *
	 * @param request request to edit
	 * @return form to edit health request
	 */
	@RequestContentMapping(nameKey = "editHealthRequestScreenName",
			descriptionKey = "editHealthRequestScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/edit.html", method =  RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')"
			+" or hasRole('HEALTH_ADMIN')"
			+" or hasRole('HEALTH_REQUEST_VIEW')")
	public ModelAndView edit(
			@RequestParam(value = "request", required = true)
				final HealthRequest request) {
		final HealthRequestForm healthRequestForm = new HealthRequestForm();
		healthRequestForm.setAsap(request.getAsap());
		healthRequestForm.setCategory(request.getCategory());
		healthRequestForm.setLabsRequired(request.getLabsRequired());
		healthRequestForm.setNotes(request.getNotes());
		healthRequestForm.setProviderLevel(request.getProviderLevel());
		final ModelAndView mav = this.prepareEditMav(
				healthRequestForm, request.getFacility());
		this.offenderSummaryModelDelegate.add(mav.getModelMap(),
				request.getOffender());
		mav.addObject(HEALTH_REQUEST_MODEL_KEY, request);
		return mav;
	}

	/**
	 * Saves health request.
	 * 
	 * @param facility facility
	 * @param offender offender
	 * @param healthRequestForm form for health request
	 * @param result binding result
	 * @return redirect to facility referral center
	 * @throws DuplicateEntityFoundException if the health request exists
	 */
	@RequestContentMapping(nameKey = "saveHealthRequestScreenName",
			descriptionKey = "saveHealthRequestScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/create.html", method =  RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')"
			+" or (hasRole('HEALTH_ADMIN')"
			+" or hasRole('HEALTH_REQUEST_EDIT'))")
	public ModelAndView save(
			@RequestParam(value = "facility", required = true)
				final Facility facility,
			@RequestParam(value = "defaultOffender", required = false)
				final Offender defaultOffender,
			final HealthRequestForm healthRequestForm,
			final BindingResult result)
				throws DuplicateEntityFoundException {
		this.healthRequestFormValidator.validate(healthRequestForm, result);
		if (result.hasErrors()) {
			final ModelAndView mav = this.prepareRedisplayMav(
					healthRequestForm, result, facility);
			if (defaultOffender != null) {
				this.offenderSummaryModelDelegate.add(
						mav.getModelMap(), defaultOffender);
			}
			return mav;
		}
		final Offender offender;
		if (defaultOffender != null) {
			offender = defaultOffender;
		} else {
			offender = healthRequestForm.getOffender();
		}
		final Date currentDate = new Date();
		final boolean asap;
		if (healthRequestForm.getAsap() != null
				&& healthRequestForm.getAsap()) {
			asap = true;
		} else {
			asap = false;
		}
		this.healthRequestService.request(
			offender, facility, currentDate,
			healthRequestForm.getLabsRequired(),
			healthRequestForm.getCategory(), asap,
			healthRequestForm.getProviderLevel(),
			healthRequestForm.getNotes());
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(
					facility, offender, null, null, ReferralType.ALL, null));
	}
	
	/**
	 * Updates health request.
	 *
	 * @param request request to update
	 * @param healthRequestForm form for health requests
	 * @param result results
	 * @return redirect to facility referral center
	 * @throws HealthRequestException if the health request is not open
	 * @throws DuplicateEntityFoundException if the health request exists
	 */
	@RequestContentMapping(nameKey = "updateHealthRequestScreenName",
			descriptionKey = "updateHealthRequestScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/edit.html", method =  RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')"
			+" or (hasRole('HEALTH_ADMIN')"
			+" or hasRole('HEALTH_REQUEST_EDIT'))")
	public ModelAndView update(
			@RequestParam(value = "request", required = true)
				final HealthRequest request,
			final HealthRequestForm healthRequestForm,
			final BindingResult result)
					throws DuplicateEntityFoundException,
						HealthRequestException {
		this.healthRequestFormValidator.validate(healthRequestForm, result);
		if (result.hasErrors()) {
			final ModelAndView mav = this.prepareRedisplayMav(
					healthRequestForm, result, request.getFacility());
			this.offenderSummaryModelDelegate.add(
					mav.getModelMap(), request.getOffender());
			mav.addObject(HEALTH_REQUEST_MODEL_KEY, request);
			return mav;
		}
		final boolean asap;
		if (healthRequestForm.getAsap() != null
				&& healthRequestForm.getAsap()) {
			asap = true;
		} else {
			asap = false;
		}
		this.healthRequestService.update(request,
				healthRequestForm.getCategory(),
				healthRequestForm.getLabsRequired(), asap,
				healthRequestForm.getProviderLevel(),
				healthRequestForm.getNotes());
		return new ModelAndView(this.healthControllerDelegate
				.prepareFacilityCenterRedirectWithParameter(
						request.getFacility(), request.getOffender(),
						null, null, ReferralType.ALL, null));
	}

	/**
	 * Displays a screen listing health requests.
	 * 
	 * @param facility facility
	 * @param resolvedOnly whether to show only resolved requests
	 * @param referralType requests for referral types to show
	 * @return screen listing health requests
	 */
	@RequestContentMapping(nameKey = "listHealthRequestScreenName",
			descriptionKey = "listHealthRequestScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/list.html", method =  RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')"
			+ " or hasRole('HEALTH_ADMIN')"
			+ " or hasRole('HEALTH_REQUEST_VIEW')")
	public ModelAndView list(
			@RequestParam(value = "facility", required = true)
				final Facility facility,
			@RequestParam(value = "resolvedOnly", required = false)
				final Boolean resolvedOnly,
			@RequestParam(value = "referralType", required = false)
				final ReferralType referralType) {
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		final List<HealthRequestSummary> healthRequestSummaries;
		final HealthRequestCategory[] categories;
		if (ReferralType.ALL.equals(referralType) || referralType == null) {
			categories = HealthRequestCategory.values();
		} else {
			categories = new HealthRequestCategory[] {
				this.referralTypeResolverDelegate
					.resolveToRequestCategory(referralType)
			};
		}
		final Date currentDate = new Date();
		if (resolvedOnly != null && resolvedOnly) {
			healthRequestSummaries = this.healthRequestReportService
					.findResolvedByCategory(facility, currentDate, categories);
		} else {
			healthRequestSummaries = this.healthRequestReportService
					.findByCategory(facility, currentDate, categories);
		}
		mav.addObject(HEALTH_REQUEST_SUMMARIES_MODEL_KEY,
				healthRequestSummaries);
		mav.addObject(FACILITY_MODEL_KEY, facility);
		mav.addObject(REFERRAL_TYPE_MODEL_KEY, referralType);
		return mav;
	}
	
	/**
	 * Removes a health request.
	 * 
	 * @param healthRequest health request to delete
	 * @param resolvedOnly whether to show only resolved requests in redirect
	 * @param referralType requests for referral types to show in redirect
	 * @return redirect to screen to list health requests
	 */
	@RequestContentMapping(nameKey = "removeHealthRequestScreenName",
			descriptionKey = "removeHealthRequestScreenDescription",
			messageBundle = "omis.health.msgs.health",
			screenType = RequestContentType.DETAIL_SCREEN)
	@RequestMapping(value = "/remove.html", method =  RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')"
			+ " or hasRole('HEALTH_ADMIN')"
			+ " or hasRole('HEALTH_REQUEST_REMOVE')")
	public ModelAndView remove(
			@RequestParam(value = "healthRequest", required = true)
				final HealthRequest healthRequest,
			@RequestParam(value = "resolvedOnly", required = false)
				final Boolean resolvedOnly,
			@RequestParam(value = "referralType", required = false)
				final ReferralType referralType) {
		final Facility facility = healthRequest.getFacility();
		this.healthRequestService.remove(healthRequest);
		return new ModelAndView(String.format(LIST_REDIRECT,
				facility.getId(), resolvedOnly, referralType.getName()));
	}
	
	/* Helper methods. */

	/* Resolves appropriate redirect per Health request. */
	private String getHealthRequestCategoryRedirects(
			final HealthRequestCategory healthRequestCategory)
					throws UnsupportedOperationException {
		if (HealthRequestCategory.INTERNAL_MEDICAL
				.equals(healthRequestCategory)) {
			return SCHEDULE_INTERNAL_HEALTH_REQUEST_REDIRECT;
		} else if (HealthRequestCategory.EXTERNAL_MEDICAL
				.equals(healthRequestCategory)) {
			return SCHEDULE_EXTERNAL_HEALTH_REQUEST_REDIRECT;
		} else if (HealthRequestCategory.LAB.equals(healthRequestCategory)) {
			return SCHEDULE_LAB_WORK_HEALTH_REQUEST_REDIRECT;
		} else {
			throw new UnsupportedOperationException(
				"Malformed health request");
		}
	}

	// Prepared model and view to edit health requests
	private ModelAndView prepareEditMav(
			final HealthRequestForm healthRequestForm,
			final Facility facility) {
		final List<ProviderLevel> providerLevels
			= this.healthRequestService.findProviderLevels();
		final ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(HEALTH_REQUEST_FORM_MODEL_KEY, healthRequestForm);
		mav.addObject(CATEGORIES_MODEL_KEY,
				HealthRequestCategory.supportedValues());
		mav.addObject(FACILITY_MODEL_KEY, facility);
		mav.addObject(PROVIDER_LEVELS_MODEL_KEY, providerLevels);
		return mav;
	}

	// Prepares model and view to redisplay health requests
	private ModelAndView prepareRedisplayMav(
			final HealthRequestForm healthRequestForm,
			final BindingResult result,
			final Facility facility) {
		final ModelAndView mav = this.prepareEditMav(
				healthRequestForm, facility);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ HEALTH_REQUEST_FORM_MODEL_KEY, result);
		return mav;
	}

	/* Init binder. */

	/** Init Binder.
	 * @param binder binder */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(HealthRequest.class,
				this.healthRequestPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(ProviderLevel.class,
				this.providerLevelPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Facility.class,
				this.facilityPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
	}
}