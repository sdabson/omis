package omis.health.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.HealthRequest;
import omis.health.domain.InternalReferral;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkRequirementRequest;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderLevel;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkResults;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.exception.FollowUpException;
import omis.health.exception.InternalReferralAssessmentException;
import omis.health.service.InternalReferralAssessmentService;
import omis.health.validator.AssessInternalReferralFormValidator;
import omis.health.web.controller.delegate.HealthControllerDelegate;
import omis.health.web.controller.delegate.ReferralTypeResolverDelegate;
import omis.health.web.form.AssessInternalReferralForm;
import omis.health.web.form.LabWorkAppointmentItem;
import omis.health.web.form.ReferralType;
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
 * Controller to assess inside referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 30, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/referral/internal")
@PreAuthorize("hasRole('ADMIN')" 
		+ " or hasRole('HEALTH_ADMIN')" 
		+ " or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class AssessInternalReferralController {

	/* View names. */
	
	private static final String EDIT_VIEW_NAME
		= "health/referral/internal/assess/edit";
	
	/* Redirects. */
	
	private static final String REQUEST_REQUIRED_LAB_WORK_REDIRECT
		= "redirect:/health/labWork/requirement/request/edit.html"
				+ "?healthRequest=%d";
	
	/* Model keys. */
	
	private static final String ASSESS_INTERNAL_REFERRAL_FORM_MODEL_KEY
		= "assessInternalReferralForm";

	private static final String FACILITY_MODEL_KEY = "facility";

	private static final String FOLLOW_UP_REFERRAL_TYPES_MODEL_KEY
		= "followUpReferralTypes";

	private static final String INTERNAL_REFERRAL_MODEL_KEY
		= "internalReferral";

	private static final String PROVIDER_ASSIGNMENT_MODEL_KEY
		= "providerAssignment";
	
	private static final String FOLLOW_UP_REQUEST_PROVIDER_LEVELS_MODEL_KEY
		= "followUpRequestProviderLevels";
	
	private static final String PROVIDERS_ON_DATES_MODEL_KEY
		= "providersOnDates";

	private static final String WEEK_START_DATE_MODEL_KEY = "weekStartDate";
	
	private static final String LABS_MODEL_KEY = "labs";
	
	private static final String LAB_WORK_CATEGORIES_MODEL_KEY 
		= "labWorkCategories";
	
	private static final String CURRENT_LAB_WORK_INDEX_MODEL_KEY 
		= "currentLabWorkIndex";
	
	/* Services. */
	
	@Autowired
	@Qualifier("internalReferralAssessmentService")
	private InternalReferralAssessmentService internalReferralAssessmentService;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("referralTypeResolverDelegate")
	private ReferralTypeResolverDelegate referralTypeResolverDelegate;
	
	@Autowired
	@Qualifier("healthControllerDelegate")
	private HealthControllerDelegate healthControllerDelegate;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("assessInternalReferralFormValidator")
	private AssessInternalReferralFormValidator
	assessInternalReferralFormValidator;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("internalReferralPropertyEditorFactory")
	private PropertyEditorFactory internalReferralPropertyEditorFactory;
	
	@Autowired
	@Qualifier("providerLevelPropertyEditorFactory")
	private PropertyEditorFactory providerLevelPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("labWorkCategoryPropertyEditorFactory")
	private PropertyEditorFactory labWorkCategoryPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labPropertyEditorFactory")
	private PropertyEditorFactory labPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labWorkPropertyEditorFactory")
	private PropertyEditorFactory labWorkPropertyEditorFactory;
	
	@Autowired
	@Qualifier("providerAssignmentPropertyEditorFactory")
	private PropertyEditorFactory providerAssignmentPropertyEditorFactory;
	
	// TODO: Remove - see comment in init binder [SA]
	@Autowired
	@Qualifier("labWorkRequirementRequestPropertyEditorFactory")
	private PropertyEditorFactory
	labWorkRequirementRequestPropertyEditorFactory;
	
	/* Constructors. */

	/** Instantiates a controller to assess internal referrals. */
	public AssessInternalReferralController() {
		// Default instantiation
	}

	/* URL invokable methods. */
	
	/**
	 * Displays screen to assess inside referral.
	 * 
	 * @param internalReferral internal referral to assess
	 * @param update whether to update assessment
	 * @return screen to assess inside referral 
	 */
	@RequestMapping(value = "/assess.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')"
			+ " or hasRole('INTERNAL_REFERRAL_ASSESSMENT_EDIT')"
			+ " or hasRole('INTERNAL_REFERRAL_ASSESSMENT_VIEW')"
			+ " or hasRole('HEALTH_ADMIN')")
	public ModelAndView edit(
			@RequestParam(value = "internalReferral", required = true)
				final InternalReferral internalReferral,
			@RequestParam(value = "update", required = false)
				final Boolean update) {
		AssessInternalReferralForm assessInternalReferralForm
				= new AssessInternalReferralForm();
		assessInternalReferralForm.setNotes(internalReferral
				.getAssessmentNotes());
		assessInternalReferralForm.setTime(internalReferral
				.getOffenderAppointmentAssociation()
					.getAppointment().getTimeKept());
		if (internalReferral.getActionRequest() != null) {
			final HealthRequest actionRequest
				= internalReferral.getActionRequest();
			assessInternalReferralForm.setFollowUpReferralType(
					this.referralTypeResolverDelegate
						.resolveCategory(actionRequest.getCategory()));
			assessInternalReferralForm.setFollowUpAsap(
					actionRequest.getAsap());
			assessInternalReferralForm.setFollowUpLabsRequired(
					actionRequest.getLabsRequired());
			assessInternalReferralForm.setFollowUpRequestProviderLevel(
					actionRequest.getProviderLevel());
			assessInternalReferralForm.setFollowUpRequestNotes(
					actionRequest.getNotes());
		}
		
		List<LabWork> labWorks = this.internalReferralAssessmentService
				.findLabWorks(internalReferral);
		List<LabWorkAppointmentItem> items = 
				new ArrayList<LabWorkAppointmentItem>();
		Map<Long, List<ProviderAssignment>> providersOnDates
			= new HashMap<Long, List<ProviderAssignment>>();
		if (labWorks.size() != 0) {
			int count = 0;
			for (LabWork labWork : labWorks) {
				LabWorkAppointmentItem item = new LabWorkAppointmentItem();
				item.setProcess(true);
				item.setLabWork(labWork);
				item.setDate(labWork
						.getOffenderAppointmentAssociation()
						.getAppointment().getDate());
				item.setLabWorkCategory(labWork
						.getLabWorkCategory());
				if (labWork.getOrder() != null) {
					item.setByProvider(labWork.getOrder().getByAssignment());
					item.setOrderDate(labWork.getOrder().getDate());
				}
				item.setSampleLab(labWork.getSampleLab());
				item.setSampleNotes(labWork.getSampleNotes());
				item.setSchedulingNotes(labWork.getSchedulingNotes());
				item.setSampleTaken(labWork.getSampleTaken());
				if (labWork.getSampleRestrictions() != null) {
					item.setNoLeaky(
							labWork.getSampleRestrictions().getNoLeaky());
					item.setNothingPerOral(
							labWork.getSampleRestrictions()
								.getNothingPerOral());
					item.setNoMeds(labWork.getSampleRestrictions()
							.getNoMeds());
				}
				if (labWork.getResults() != null) {
					item.setResultsLab(labWork.getResults().getLab());
					item.setResultsNotes(labWork.getResults().getNotes());
					item.setResultsDate(labWork.getResults().getDate());
				}
				items.add(count, item);
				List<ProviderAssignment> providers = 
						this.internalReferralAssessmentService
						.findProviders(item.getLabWork()
								.getOffenderAppointmentAssociation()
								.getAppointment().getFacility(), 
								item.getOrderDate());
				if (item.getOrderDate() != null) {
					providersOnDates.put(item.getOrderDate().getTime(), 
							providers);
				}
				count++;
			}
			assessInternalReferralForm.setLabWork(items);
		}
		ModelAndView mav = this.prepareEditMav(assessInternalReferralForm,
				internalReferral.getOffenderAppointmentAssociation()
					.getOffender(),
				internalReferral.getOffenderAppointmentAssociation()
					.getAppointment().getFacility());
		this.setCurrentLabWorkIndex(assessInternalReferralForm, 
				mav.getModelMap());
		mav.addObject(LABS_MODEL_KEY, this.internalReferralAssessmentService
				.findLabs());
		mav.addObject(LAB_WORK_CATEGORIES_MODEL_KEY, 
				this.internalReferralAssessmentService
				.findLabWorkCategories());
		mav.addObject(PROVIDERS_ON_DATES_MODEL_KEY, providersOnDates);
		mav.addObject(INTERNAL_REFERRAL_MODEL_KEY, internalReferral);
		ProviderAssignment providerAssignment
			= this.internalReferralAssessmentService
				.findPrimaryProvider(internalReferral);
		mav.addObject(PROVIDER_ASSIGNMENT_MODEL_KEY, providerAssignment);
		return mav;
	}
	
	/**
	 * Updates assessment information of inside referral.
	 * 
	 * @param internalReferral internal referral
	 * @param update whether to update assessment
	 * @param assessInternalReferralForm form containing assessment information
	 * @param result binding result
	 * @return redirect to referral center for facility
	 * @throws DuplicateEntityFoundException if an identical follow up request
	 * exists
	 * @throws InternalReferralAssessmentException if an attempt to assess an
	 * assessed referral is made
	 */
	@RequestMapping(value = "/assess.html", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN')"
			+ " or hasRole('INTERNAL_REFERRAL_ASSESSMENT_EDIT')"
			+ " or hasRole('HEALTH_ADMIN')")
	public ModelAndView update(
			@RequestParam(value = "internalReferral", required = true)
				final InternalReferral internalReferral,
			@RequestParam(value = "update", required = false)
				final Boolean update,
			final AssessInternalReferralForm assessInternalReferralForm,
			final BindingResult result) throws DuplicateEntityFoundException,
				InternalReferralAssessmentException {
		final Offender offender = internalReferral
				.getOffenderAppointmentAssociation().getOffender();
		final Facility facility = internalReferral
				.getOffenderAppointmentAssociation().getAppointment()
					.getFacility();
		this.assessInternalReferralFormValidator
			.validate(assessInternalReferralForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = this.prepareRedisplayMav(
					assessInternalReferralForm, result, offender, facility);
			mav.addObject(INTERNAL_REFERRAL_MODEL_KEY, internalReferral);
			ProviderAssignment providerAssignment
				= this.internalReferralAssessmentService
					.findPrimaryProvider(internalReferral);
			mav.addObject(PROVIDER_ASSIGNMENT_MODEL_KEY, providerAssignment);
			mav.addObject(LABS_MODEL_KEY, this.internalReferralAssessmentService
					.findLabWorkCategories());
			mav.addObject(LAB_WORK_CATEGORIES_MODEL_KEY, 
					this.internalReferralAssessmentService
					.findLabWorks(internalReferral));
			this.setCurrentLabWorkIndex(assessInternalReferralForm, 
					mav.getModelMap());
			return mav;
		}
		if (update != null && update) {
			this.internalReferralAssessmentService.update(internalReferral,
					assessInternalReferralForm.getTime(),
					assessInternalReferralForm.getNotes());
		} else {
			this.internalReferralAssessmentService.assess(internalReferral,
					assessInternalReferralForm.getTime(),
					assessInternalReferralForm.getNotes());
		}
		Date currentDate = new Date();
		
		String redirectUrl = null;
		
		// Do not allow editing of existing action requests
		if (internalReferral.getActionRequest() == null
				&& assessInternalReferralForm.getFollowUpReferralType()
					!= null) {
			final boolean asap;
			if (assessInternalReferralForm.getFollowUpAsap() != null
					&& assessInternalReferralForm.getFollowUpAsap()) {
				asap = true;
			} else {
				asap = false;
			}
			final HealthRequest followUpRequest;
			if (ReferralType.LAB.equals(assessInternalReferralForm
					.getFollowUpReferralType())) {
				try {
					followUpRequest = internalReferralAssessmentService
							.requestLabFollowUp(
									internalReferral, currentDate, asap,
									assessInternalReferralForm
										.getFollowUpRequestNotes());
				} catch (FollowUpException e) {
					throw new RuntimeException(
							"Logically impossible condition has arisen: "
									+ e.getMessage(), e);
				}
			} else {
				boolean labsRequired;
				if (assessInternalReferralForm.getFollowUpLabsRequired()
							!= null) {
					labsRequired = assessInternalReferralForm
							.getFollowUpLabsRequired(); 
				} else {
					labsRequired = false;
				}
				try {
					followUpRequest = internalReferralAssessmentService
							.requestFollowUp(
									internalReferral, currentDate,
									this.referralTypeResolverDelegate
										.resolveToRequestCategory(
											assessInternalReferralForm
												.getFollowUpReferralType()),
											labsRequired, asap,
											assessInternalReferralForm
											.getFollowUpRequestProviderLevel(),
											assessInternalReferralForm
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
		this.updateRequiredLabWork(
				assessInternalReferralForm.getLabWork(),
				internalReferral.getOffenderAppointmentAssociation());
		if (redirectUrl != null) {
			return new ModelAndView(redirectUrl);
		} else {
			return new ModelAndView(
					this.healthControllerDelegate
						.prepareFacilityCenterRedirectWithParameter(facility,
								null, internalReferral
									.getOffenderAppointmentAssociation()
										.getAppointment().getDate(),
								internalReferral
									.getOffenderAppointmentAssociation()
										.getAppointment().getDate(),
								null, null));
		}
	}
	
	// Prepares model and view to editing form to assess internal referral
	private ModelAndView prepareEditMav(
			final AssessInternalReferralForm assessInternalReferralForm,
			final Offender offender,
			final Facility facility) {
		List<ProviderLevel> followUpProviderLevels
			= this.internalReferralAssessmentService
				.findFollowUpProviderLevels();
		ModelAndView mav = new ModelAndView(EDIT_VIEW_NAME);
		mav.addObject(ASSESS_INTERNAL_REFERRAL_FORM_MODEL_KEY,
				assessInternalReferralForm);
		mav.addObject(FACILITY_MODEL_KEY, facility);
		mav.addObject(FOLLOW_UP_REFERRAL_TYPES_MODEL_KEY,
				ReferralType.supportedValues());
		mav.addObject(FOLLOW_UP_REQUEST_PROVIDER_LEVELS_MODEL_KEY,
				followUpProviderLevels);
		mav.addObject(WEEK_START_DATE_MODEL_KEY, DateRange.findWeeklyRange(
				new Date()).getStartDate());
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	// Prepares model and view to redisplay 
	private ModelAndView prepareRedisplayMav(
			final AssessInternalReferralForm assessInternalReferralForm,
			final BindingResult result, final Offender offender,
			final Facility facility) {
		ModelAndView mav = this.prepareEditMav(assessInternalReferralForm,
				offender, facility);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ ASSESS_INTERNAL_REFERRAL_FORM_MODEL_KEY, result);
		return mav;
	}
		
	/*
	 * Sets the current lab work index for use on the specified assess
	 * internal referral form.
	 */
	private ModelMap setCurrentLabWorkIndex(
			final AssessInternalReferralForm form, final ModelMap map) {
		int currentLabWorkIndex;
		if (form.getLabWork() == null) {
			currentLabWorkIndex = 0;
		} else {
			currentLabWorkIndex = form.getLabWork()
					.size(); 
		}
		map.addAttribute(getcurrentLabWorkIndexModelKey(), currentLabWorkIndex);
		return map;
	}
	
	/**
	 * Returns the current lab work index model key.
	 *  
	 * @return current lab index model key
	 */
	private String getcurrentLabWorkIndexModelKey() {
		return CURRENT_LAB_WORK_INDEX_MODEL_KEY;
	}

	/**
	 * Updates required lab work associated with a specified referral 
	 * that is being assessed.
	 * 
	 * @param items lab work appointment items
	 * @param association offenderAppointmentAssociation
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	public void updateRequiredLabWork(final List<LabWorkAppointmentItem> items,
			final OffenderAppointmentAssociation association)
					throws DuplicateEntityFoundException {
			for (LabWorkAppointmentItem item : items) {
				LabWorkResults results = new LabWorkResults(
						item.getResultsLab(), item.getDate(), 
						item.getResultsNotes());  
				LabWorkOrder order = new LabWorkOrder(item.getByProvider(),
						item.getOrderDate());
				LabWorkSampleRestrictions restrictions = 
						new LabWorkSampleRestrictions(item.getNothingPerOral(),
								item.getNoLeaky(), item.getNoMeds());
			if (item.getProcess() != null && item.getProcess()) {
				if(item.getLabWork() != null) {
					this.internalReferralAssessmentService.updateLabWork(
							null, item.getLabWork(), 
							item.getLabWorkCategory(), item.getSampleLab(), 
							item.getDate(), item.getSampleNotes(), 
							item.getSampleTaken(), results, order, restrictions,
							item.getSchedulingNotes());
				} else {
					// This is not a valid call, internal referral being null is
					// guaranteed to throw an NPE.
					// TODO: Someone will need to re-evaluate the underlying 
					// service call before re-enabling this code block 
					//this.internalReferralAssessmentService.scheduleLabWork(
					//		null, item.getLabWorkCategory(), 
					//		item.getSampleLab(), item.getDate(), 
					//		item.getSampleNotes(), item.getSampleTaken(), 
					//		results, order, restrictions, 
					//		item.getSchedulingNotes());
				}
			}else {
				if (item.getLabWork() != null) {
					this.internalReferralAssessmentService.removeLabWork(
							item.getLabWork());
				}
			}
		}
	}
	
	/* Init binder. */
	
	/**
	 * Init Binder.
	 *
	 * @param binder binder
	 */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(InternalReferral.class,
			this.internalReferralPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(ProviderLevel.class,
			this.providerLevelPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Date.class, "time",
			this.customDateEditorFactory.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(Date.class, 
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(
				Lab.class,
				this.labPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				LabWorkCategory.class,
				this.labWorkCategoryPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				LabWork.class,
				this.labWorkPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(ProviderAssignment.class,
				this.providerAssignmentPropertyEditorFactory
					.createPropertyEditor());
		// TODO: Remove - lab work requirement requests are irrelavent when
		// assessing internal referrals. However, requirements are included
		// on lab work items. Lab work fields should be separated and
		// composited on two different form items - one for scheduling
		// (with requirement) and one to assess (without). This is included for
		// the time being to allow validation. [SA]
		binder.registerCustomEditor(LabWorkRequirementRequest.class,
				this.labWorkRequirementRequestPropertyEditorFactory
					.createPropertyEditor());
	}
}