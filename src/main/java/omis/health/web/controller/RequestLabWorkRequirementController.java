package omis.health.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.HealthRequest;
import omis.health.domain.Lab;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkRequirementRequest;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.exception.HealthRequestFollowsUpMultipleReferralsException;
import omis.health.report.ReferralSummaryReportService;
import omis.health.service.LabWorkRequirementService;
import omis.health.validator.RequestLabWorkRequirementFormValidator;
import omis.health.web.controller.delegate.HealthControllerDelegate;
import omis.health.web.controller.delegate.ReferralSummaryControllerDelegate;
import omis.health.web.controller.delegate.ReferralTypeResolverDelegate;
import omis.health.web.form.LabWorkRequirementRequestItem;
import omis.health.web.form.LabWorkRequirementRequestOperation;
import omis.health.web.form.ReferralType;
import omis.health.web.form.RequestLabWorkRequirementForm;
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
 * Controller to request lab work requirements.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 4, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/labWork/requirement/request")
@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class RequestLabWorkRequirementController {
	
	/* View names. */
	
	private static final String EDIT_VIEW_NAME
		= "health/labWork/requirement/request/edit";

	private static final String REQUEST_ITEM_VIEW_NAME
		= "health/labWork/requirement/request/includes/requestTableRow";
	
	/* Model keys. */
	
	private static final String REQUEST_LAB_WORK_REQUIREMENT_FORM_MODEL_KEY
		= "requestLabWorkRequirementForm";

	private static final String FACILITY_MODEL_KEY = "facility";

	private static final String LAB_WORK_CATEGORIES_MODEL_KEY
		= "labWorkCategories";

	private static final String LABS_MODEL_KEY = "labs";

	private static final String CURRENT_INDEX_MODEL_KEY = "currentIndex";
	
	private static final String PROVIDER_ASSIGNMENTS_MODEL_KEY
		= "providerAssignments";

	private static final String HEALTH_REQUEST_MODEL_KEY = "healthRequest";
	
	/* Properties. */
	
	private static final String LAB_WORK_REQUIREMENT_REQUEST_ITEMS_PROPERTY_KEY
		= "labWorkRequirementRequestItems";

	/* Services. */
	
	@Autowired
	@Qualifier("labWorkRequirementService")
	private LabWorkRequirementService labWorkRequirementService;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("healthControllerDelegate")
	private HealthControllerDelegate healthControllerDelegate;
	
	@Autowired
	@Qualifier("referralTypeResolverDelegate")
	private ReferralTypeResolverDelegate referralTypeResolverDelegate;
	
	@Autowired
	@Qualifier("referralSummaryControllerDelegate")
	private ReferralSummaryControllerDelegate referralSummaryControllerDelegate;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("labWorkRequirementRequestPropertyEditorFactory")
	private PropertyEditorFactory
	labWorkRequirementRequestPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labWorkCategoryPropertyEditorFactory")
	private PropertyEditorFactory labWorkCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labPropertyEditorFactory")
	private PropertyEditorFactory labPropertyEditorFactory;
	
	@Autowired
	@Qualifier("healthRequestPropertyEditorFactory")
	private PropertyEditorFactory healthRequestPropertyEditorFactory;
	
	@Autowired
	@Qualifier("facilityPropertyEditorFactory")
	private PropertyEditorFactory facilityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("providerAssignmentPropertyEditorFactory")
	private PropertyEditorFactory providerAssignmentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("requestLabWorkRequirementFormValidator")
	private RequestLabWorkRequirementFormValidator
	requestLabWorkRequirementFormValidator;
	
	/* Report service. */

	@Autowired
	@Qualifier("referralSummaryReportService")
	private ReferralSummaryReportService referralSummaryReportService;
	
	/* Constructors. */
	
	/** Instantiates a controller to request lab work requirements. */
	public RequestLabWorkRequirementController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays form for editing lab work requirement requests for health
	 * request.
	 * 
	 * @param healthRequest health request
	 * @return screen to edit lab work requirement requests for health request
	 * @throws HealthRequestFollowsUpMultipleReferralsException if the request
	 * follows up multiple referrals
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.GET)
	@PreAuthorize(
			"hasRole('ADMIN') or hasRole('LAB_WORK_REQUIREMENT_REQUEST_VIEW')")
	public ModelAndView edit(
			@RequestParam(value = "healthRequest", required = true)
				final HealthRequest healthRequest)
					throws HealthRequestFollowsUpMultipleReferralsException {
		if (healthRequest.getLabsRequired() == null
				|| !healthRequest.getLabsRequired()) {
			throw new IllegalArgumentException(
					"Labs not required for health request"); 
		}
		List<LabWorkRequirementRequest> requests
			= this.labWorkRequirementService
				.findRequestsByHealthRequest(healthRequest);
		RequestLabWorkRequirementForm requestLabWorkRequirementForm
			= new RequestLabWorkRequirementForm();
		for (LabWorkRequirementRequest request : requests) {
			LabWorkRequirementRequestItem item
				= new LabWorkRequirementRequestItem();
			item.setRequest(request);
			if (request.getOrder() != null) {
				item.setSampleOrderedBy(request.getOrder().getByAssignment());
				item.setSampleOrderedDate(request.getOrder().getDate());
			}
			item.setCategory(request.getCategory());
			item.setSampleDate(request.getSampleDate());
			item.setResultsLab(request.getResultsLab());
			item.setSampleLab(request.getSampleLab());
			if (request.getSampleRestrictions() != null) {
				item.setNoLeakySampleRestriction(
						request.getSampleRestrictions().getNoLeaky());
				item.setNothingPerOralSampleRestriction(
						request.getSampleRestrictions().getNothingPerOral());
				item.setNoMedsSampleRestriction(
						request.getSampleRestrictions().getNoMeds());
			}
			item.setSchedulingNotes(request.getSchedulingNotes());
			item.setOperation(LabWorkRequirementRequestOperation.UPDATE);
			requestLabWorkRequirementForm.getLabWorkRequirementRequestItems()
				.add(item);
		}
		Date currentDate = new Date();
		return this.prepareEditMav(requestLabWorkRequirementForm,
				healthRequest.getOffender(), healthRequest.getFacility(),
				healthRequest, currentDate);
	}

	/**
	 * Updates lab work requirement requests for health request.
	 * 
	 * @param healthRequest health request
	 * @param requestLabWorkRequirementForm form form requesting lab work
	 * requirements
	 * @param result binding result
	 * @return redirect to facility health center
	 * @throws DuplicateEntityFoundException if an attempt is made to request
	 * and existing requirement
	 * @throws HealthRequestFollowsUpMultipleReferralsException if the request
	 * follows up more than one referral and the screen is redisplayed
	 */
	@RequestMapping(value = "/edit.html", method = RequestMethod.POST)
	@PreAuthorize(
			"hasRole('ADMIN') or hasRole('LAB_WORK_REQUIREMENT_REQUEST_EDIT')")
	public ModelAndView update(
			@RequestParam(value = "healthRequest", required = true)
				final HealthRequest healthRequest,
			final RequestLabWorkRequirementForm requestLabWorkRequirementForm,
			final BindingResult result) throws DuplicateEntityFoundException, HealthRequestFollowsUpMultipleReferralsException {
		this.requestLabWorkRequirementFormValidator
			.validate(requestLabWorkRequirementForm, result);
		Date currentDate = new Date();
		if (result.hasErrors()) {
			return this.prepareRedisplayMav(requestLabWorkRequirementForm,
					result, healthRequest.getOffender(),
					healthRequest.getFacility(), healthRequest, currentDate);
		}
		for (LabWorkRequirementRequestItem item
				: requestLabWorkRequirementForm
					.getLabWorkRequirementRequestItems()) {
			if (item.getOperation() != null) {
				if (LabWorkRequirementRequestOperation.CREATE.equals(
						item.getOperation())) {
					this.labWorkRequirementService.request(healthRequest,
							new LabWorkOrder(
									item.getSampleOrderedBy(),
									item.getSampleOrderedDate()),
							item.getCategory(), item.getSampleDate(),
							item.getSampleLab(), item.getResultsLab(),
							new LabWorkSampleRestrictions(
									item.getNothingPerOralSampleRestriction(),
									item.getNoLeakySampleRestriction(),
									item.getNoMedsSampleRestriction()),
							item.getSchedulingNotes());
				} else if (LabWorkRequirementRequestOperation.UPDATE.equals(
						item.getOperation())) {
					this.labWorkRequirementService.update(item.getRequest(),
							new LabWorkOrder(
									item.getSampleOrderedBy(),
									item.getSampleOrderedDate()),
							item.getCategory(), item.getSampleDate(),
							item.getSampleLab(), item.getResultsLab(),
							new LabWorkSampleRestrictions(
									item.getNothingPerOralSampleRestriction(),
									item.getNoLeakySampleRestriction(),
									item.getNoMedsSampleRestriction()),
							item.getSchedulingNotes());
				} else if (LabWorkRequirementRequestOperation.REMOVE.equals(
						item.getOperation())) {
					LabWorkRequirementRequest request = item.getRequest();
					if (request != null) {
						this.labWorkRequirementService.remove(request);
					}
				} else {
					throw new UnsupportedOperationException(
							"Unknown operation: " + item.getOperation());
				}
			}
		}
		ReferralType referralType = this.referralTypeResolverDelegate
				.resolveCategory(healthRequest.getCategory());
		return new ModelAndView(
				this.healthControllerDelegate
					.prepareFacilityCenterRedirectWithParameter(
								healthRequest.getFacility(), 
								null, null, null, referralType, null));
	}

	/* AJAX invokable methods. */
	
	/**
	 * Displays fields to add request item for index.
	 * 
	 * @param facility facility
	 * @param currentIndex current index
	 * @return model and view displaying fields to add request item
	 */
	@RequestMapping(value = "/addRequestItem.html", method = RequestMethod.GET)
	@PreAuthorize(
			"hasRole('ADMIN') or hasRole('LAB_WORK_REQUIREMENT_REQUEST_EDIT')")
	public ModelAndView addRequestItem(
			@RequestParam(value = "facility", required = true)
				final Facility facility,
			@RequestParam(value = "operation", required = true)
				final LabWorkRequirementRequestOperation operation,
			@RequestParam(value = "currentIndex", required = true)
				final int currentIndex) {
		Date currentDate = new Date();
		ModelAndView mav = new ModelAndView(REQUEST_ITEM_VIEW_NAME);
		mav.addObject(CURRENT_INDEX_MODEL_KEY, currentIndex);
		this.addItemPropertyValues(mav.getModelMap(), facility, currentDate);
		LabWorkRequirementRequestItem labWorkRequirementRequestItem
			= new LabWorkRequirementRequestItem();
		labWorkRequirementRequestItem.setOperation(
				LabWorkRequirementRequestOperation.CREATE);
		List<LabWorkRequirementRequestItem> labWorkRequirementRequestItems
			= new ArrayList<LabWorkRequirementRequestItem>();
		for (int index = 0; index < currentIndex; index++) {
			labWorkRequirementRequestItems.add(null);
		}
		labWorkRequirementRequestItems.add(labWorkRequirementRequestItem);
		Map<String, List<LabWorkRequirementRequestItem>>
			requestLabWorkRequirementForm
				= new HashMap<String, List<LabWorkRequirementRequestItem>>();
		requestLabWorkRequirementForm.put(
				LAB_WORK_REQUIREMENT_REQUEST_ITEMS_PROPERTY_KEY,
				labWorkRequirementRequestItems);
		mav.addObject(REQUEST_LAB_WORK_REQUIREMENT_FORM_MODEL_KEY,
				requestLabWorkRequirementForm);
		return mav;
	}
	
	/* Helper methods. */
	
	// Prepares model and view to edit
	private ModelAndView prepareEditMav(
			final RequestLabWorkRequirementForm requestLabWorkRequirementForm,
			final Offender offender, final Facility facility,
			final HealthRequest healthRequest, final Date date)
					throws HealthRequestFollowsUpMultipleReferralsException {
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(REQUEST_LAB_WORK_REQUIREMENT_FORM_MODEL_KEY,
				requestLabWorkRequirementForm);
		mav.addObject(FACILITY_MODEL_KEY, facility);
		this.addItemPropertyValues(mav.getModelMap(), facility, date);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		mav.addObject(CURRENT_INDEX_MODEL_KEY,
				requestLabWorkRequirementForm
					.getLabWorkRequirementRequestItems().size());
		this.referralSummaryControllerDelegate.addOriginalForActionRequest(
				mav.getModelMap(), healthRequest, date);
		mav.addObject(HEALTH_REQUEST_MODEL_KEY, healthRequest);
		return mav;
	}
	
	// Prepares model and view to redisplay edit screen
	private ModelAndView prepareRedisplayMav(
			final RequestLabWorkRequirementForm requestLabWorkRequirementForm,
			final BindingResult result, final Offender offender,
			final Facility facility, final HealthRequest healthRequest,
			final Date date)
					throws HealthRequestFollowsUpMultipleReferralsException {
		ModelAndView mav = this.prepareEditMav(
				requestLabWorkRequirementForm, offender, facility,
				healthRequest, date);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ REQUEST_LAB_WORK_REQUIREMENT_FORM_MODEL_KEY, result);
		return mav;
	}
	
	// Adds values required to edit properties to model map
	private void addItemPropertyValues(final ModelMap modelMap,
			final Facility facility, final Date date) {
		List<LabWorkCategory> labWorkCategories
			= this.labWorkRequirementService.findLabWorkCategories();
		modelMap.addAttribute(LAB_WORK_CATEGORIES_MODEL_KEY,
				labWorkCategories);
		List<Lab> labs = this.labWorkRequirementService
				.findLabsForLocation(facility.getLocation());
		modelMap.addAttribute(LABS_MODEL_KEY, labs);
		List<ProviderAssignment> providerAssignments
			= this.labWorkRequirementService.findProviders(facility, date);
		modelMap.addAttribute(PROVIDER_ASSIGNMENTS_MODEL_KEY,
				providerAssignments);
	}
	
	/* Init binder. */
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(LabWorkRequirementRequest.class,
				this.labWorkRequirementRequestPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(LabWorkCategory.class,
				this.labWorkCategoryPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Lab.class,
				this.labPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(HealthRequest.class,
				this.healthRequestPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Facility.class,
				this.facilityPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(ProviderAssignment.class,
				this.providerAssignmentPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
	}
}