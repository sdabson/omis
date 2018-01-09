package omis.health.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.domain.HealthRequest;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderLevel;
import omis.health.domain.component.LabWorkResults;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.exception.FollowUpException;
import omis.health.exception.LabWorkReferralAssessmentException;
import omis.health.exception.ProviderException;
import omis.health.service.LabWorkReferralAssessmentService;
import omis.health.validator.AssessLabWorkReferralFormValidator;
import omis.health.web.controller.delegate.HealthControllerDelegate;
import omis.health.web.controller.delegate.ReferralTypeResolverDelegate;
import omis.health.web.form.AssessLabWorkReferralForm;
import omis.health.web.form.LabWorkAssessmentItem;
import omis.health.web.form.ReferralType;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
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
 * Controller for assessing lab work referrals.
 * 
 * @author Joel Norris
 * @author Stephen Abson
 * @version 0.1.0 (Jul 25, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/health/referral/labWork")
@PreAuthorize("hasRole('ADMIN')" +
		" or hasRole('HEALTH_ADMIN')" +
		" or (hasRole('USER') and hasRole('HEALTH_MODULE'))")
public class AssessLabWorkReferralController {

	/* View names. */
	
	//TODO: Use this or lose this -JNorris
	@SuppressWarnings("unused")
	private static final String HEALTH_REFERRAL_CENTER_VIEW_NAME 
		= "/health/referral/referralCenter";
	
	private static final String EDIT_VIEW_NAME 
		= "/health/referral/labWork/assess/edit";
	
	/* Redirects. */
	
	private static final String REQUEST_REQUIRED_LAB_WORK_REDIRECT
		= "redirect:/health/labWork/requirement/request/edit.html"
				+ "?healthRequest=%d";
	
	/* Model keys. */
	
	private static final String LAB_WORK_ASSESSMENT_ITEM_INDEX_MODEL_KEY
		= "labWorkAssessmentItemIndex";
	
	private static final String LAB_WORK_REFERRAL_MODEL_KEY
		= "labWorkReferral";
	
	private static final String FOLLOW_UP_PROVIDER_LEVELS_MODEL_KEY
		= "followUpRequestProviderLevels";
	
	private static final String FOLLOW_UP_REFERRAL_TYPES_MODEL_KEY
		= "followUpReferralTypes";
	
	private static final String WEEK_START_DATE_MODEL_KEY = "weekStartDate";
	
	private static final String ASSESS_LAB_WORK_REFERRAL_FORM_MODEL_KEY
		= "assessLabWorkReferralForm";
	
	private static final String FACILITY_MODEL_KEY = "facility";
	
	private static final String LABS_MODEL_KEY = "labs";
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("healthControllerDelegate")
	private HealthControllerDelegate healthControllerDelegate;
	
	/* Services. */
	
	@Autowired
	@Qualifier("labWorkReferralAssessmentService")
	private LabWorkReferralAssessmentService labWorkReferralAssessmentService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("providerLevelPropertyEditorFactory")
	private PropertyEditorFactory providerLevelPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labWorkReferralPropertyEditorFactory")
	private PropertyEditorFactory 
	labWorkReferralPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labPropertyEditorFactory")
	private PropertyEditorFactory labPropertyEditorFactory;
	
	@Autowired
	@Qualifier("labWorkPropertyEditorFactory")
	private PropertyEditorFactory labWorkPropertyEditorFactory;
	
	@Autowired
	@Qualifier("providerAssignmentPropertyEditorFactory")
	private PropertyEditorFactory providerAssignmentPropertyEditorFactory;
	
	@Autowired
	@Qualifier("facilityPropertyEditorFactory")
	private PropertyEditorFactory facilityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("referralTypeResolverDelegate")
	private ReferralTypeResolverDelegate referralTypeResolverDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("assessLabWorkReferralFormValidator")
	private AssessLabWorkReferralFormValidator 
	assessLabWorkReferralFormValidator;
	
	/* URL invokable methods. */
	
	/**
	 * Displays screen to assess lab work referral.
	 * 
	 * @param labWorkReferral lab work referral
	 * @return model and view 
	 */
	@RequestMapping(value = "/assess.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')"
			+ " or hasRole('LAB_WORK_REFERRAL_ASSESSMENT_EDIT')"
			+ " or hasRole('HEALTH_ADMIN')")
	public ModelAndView edit(@RequestParam(value = "labWorkReferral", 
		required = true) final LabWorkReferral labWorkReferral) {
		ModelMap map = new ModelMap();
		AssessLabWorkReferralForm form = new AssessLabWorkReferralForm();
		form.setNotes(labWorkReferral.getAssessmentNotes());
		if (labWorkReferral.getActionRequest() != null) {
			final HealthRequest actionRequest = labWorkReferral
					.getActionRequest();
			form.setFollowUpReferralType(
					this.referralTypeResolverDelegate.resolveCategory(
							actionRequest.getCategory()));
			form.setFollowUpAsap(actionRequest.getAsap());
			form.setFollowUpLabsRequired(actionRequest.getLabsRequired());
			form.setFollowUpRequestProviderLevel(actionRequest
					.getProviderLevel());
			form.setFollowUpRequestNotes(actionRequest.getNotes());
		}
		List<LabWork> labWorks = this.labWorkReferralAssessmentService
				.findLabWorks(labWorkReferral);
		List<LabWorkAssessmentItem> items =
				new ArrayList<LabWorkAssessmentItem>();
		if (labWorks.size() != 0) {
			int count = 0;
			for (LabWork labWork : labWorks) {
				LabWorkAssessmentItem item = new LabWorkAssessmentItem();
				item.setProcess(true);
				item.setLabWork(labWork);
				item.setSampleTaken(labWork.getSampleTaken());
				item.setSchedulingNotes(labWork.getSchedulingNotes());
				item.setSampleNotes(labWork.getSampleNotes());
				item.setLabWorkCategory(labWork.getLabWorkCategory());
				item.setSampleLab(labWork.getSampleLab());
				item.setSampleDate(labWork.getOffenderAppointmentAssociation()
						.getAppointment().getDate());
				item.setSampleTaken(labWork.getSampleTaken());
				if (labWork.getOrder() != null) {
					item.setByProvider(labWork.getOrder().getByAssignment());
					item.setOrderDate(labWork.getOrder().getDate());
				}
				LabWorkResults results = labWork.getResults();
				if (results != null) {
					item.setResultsDate(results.getDate());
					item.setResultsLab(results.getLab());
					item.setResultsNotes(results.getNotes());
				}
				LabWorkSampleRestrictions sampleRestrictions = labWork
						.getSampleRestrictions();
				if (sampleRestrictions != null) {
					item.setNoLeaky(sampleRestrictions.getNoLeaky());
					item.setNoMeds(sampleRestrictions.getNoMeds());
					item.setNothingPerOral(sampleRestrictions
							.getNothingPerOral());
				}
				items.add(count, item);
				count++;
			}
			form.setLabWorkAssessmentItems(items);
		}
		this.setCurrentLabWorkAssessmentIndex(form, map);
		map.addAttribute(LAB_WORK_REFERRAL_MODEL_KEY, labWorkReferral);
		map.addAttribute(ASSESS_LAB_WORK_REFERRAL_FORM_MODEL_KEY, form);
		return this.prepareEditMav(map, labWorkReferral
				.getOffenderAppointmentAssociation().getOffender(),
				labWorkReferral.getOffenderAppointmentAssociation()
				.getAppointment().getFacility());
	}
	
	/**
	 * Updates assessment information of the lab work referral.
	 * 
	 * @param labWorkReferral lab work referral
	 * @param form assess lab work referral form
	 * @param result binding result
	 * @return model and view
	 * @throws DuplicateEntityFoundException if an identical follow up or
	 * lab work referral exists.
	 * @throws ProviderException
	 * @throws LabWorkReferralAssessmentException if the lab work referral
	 * has already been assessed.
	 */
	@RequestMapping(value = "/assess.html", method = RequestMethod.POST)
	public ModelAndView update(@RequestParam(value = "labWorkReferral", 
			required = true) final LabWorkReferral labWorkReferral,
			final AssessLabWorkReferralForm form, final BindingResult result) 
		throws DuplicateEntityFoundException, ProviderException, 
		LabWorkReferralAssessmentException {
		this.assessLabWorkReferralFormValidator.validate(form, result);
		if (result.hasErrors()) {
			ModelMap map = new ModelMap();
			map.addAttribute(ASSESS_LAB_WORK_REFERRAL_FORM_MODEL_KEY, form);
			this.setCurrentLabWorkAssessmentIndex(form, map);
			return this.prepareEditMav(map, labWorkReferral
					.getOffenderAppointmentAssociation().getOffender(),
					labWorkReferral.getOffenderAppointmentAssociation()
					.getAppointment().getFacility());
		}
		this.labWorkReferralAssessmentService.assess(labWorkReferral, 
				form.getNotes());
		Date currentDate = new Date();
		
		String redirectUrl = null;
		
		// Do not allow editing of existing action requests
		if (labWorkReferral.getActionRequest() == null
				&& form.getFollowUpReferralType()
					!= null) {
			final boolean asap;
			if (form.getFollowUpAsap() != null
					&& form.getFollowUpAsap()) {
				asap = true;
			} else {
				asap = false;
			}
			final HealthRequest followUpRequest;
			if (ReferralType.LAB.equals(form
					.getFollowUpReferralType())) {
				try {
					followUpRequest = labWorkReferralAssessmentService
							.requestLabFollowUp(
									labWorkReferral, currentDate, asap,
									form.getFollowUpRequestNotes());
				} catch (FollowUpException e) {
					throw new RuntimeException(
							"Logically impossible condition has arisen: "
									+ e.getMessage(), e);
				}
			} else {
				boolean labsRequired;
				if (form.getFollowUpLabsRequired() != null) {
					labsRequired = form.getFollowUpLabsRequired(); 
				} else {
					labsRequired = false;
				}
				try {
					followUpRequest = labWorkReferralAssessmentService
							.requestInternalFollowUp(
									labWorkReferral, currentDate,
									this.referralTypeResolverDelegate
										.resolveToRequestCategory(
											form.getFollowUpReferralType()),
											labsRequired, asap, form
											.getFollowUpRequestProviderLevel(),
											form.getFollowUpRequestNotes());
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
		for (LabWorkAssessmentItem item : form.getLabWorkAssessmentItems()) {
			if (item.getProcess() != null && item.getProcess()) {
				this.labWorkReferralAssessmentService.updateLabWork(
						labWorkReferral, item.getLabWork(), 
						item.getSampleTaken(), 
						new LabWorkResults(item.getResultsLab(), 
								item.getResultsDate(), item.getResultsNotes()));
			} else {
				this.labWorkReferralAssessmentService.removeLabWork(labWorkReferral, 
						item.getLabWork());
			}
		}
		if (redirectUrl != null) {
			return new ModelAndView(redirectUrl);
		} else {
			return new ModelAndView(
					this.healthControllerDelegate
						.prepareFacilityCenterRedirectWithParameter(
								labWorkReferral
								.getOffenderAppointmentAssociation()
								.getAppointment().getFacility(), null, 
								labWorkReferral
								.getOffenderAppointmentAssociation()
								.getAppointment().getDate(),
								labWorkReferral
								.getOffenderAppointmentAssociation()
								.getAppointment().getDate(), null, null));
		}
	}
	
	/* Helper methods. */
	
	/*
	 * Prepares a model and view object with attributes required for an edit
	 * screen to assess a lab work referral.
	 *  
	 * @param map model map
	 * @param offender offender
	 * @param facility facility
	 * @return model and view for assessing a lab work referral
	 */
	private ModelAndView prepareEditMav(final ModelMap map,
			final Offender offender, final Facility facility) {
		map.addAttribute(FOLLOW_UP_PROVIDER_LEVELS_MODEL_KEY, 
				this.labWorkReferralAssessmentService
				.findFollowUpProviderLevels());
		map.addAttribute(FOLLOW_UP_REFERRAL_TYPES_MODEL_KEY,
				ReferralType.supportedValues());
		map.addAttribute(WEEK_START_DATE_MODEL_KEY, DateRange.findWeeklyRange(
				new Date()).getStartDate());
		map.addAttribute(LABS_MODEL_KEY, this.labWorkReferralAssessmentService
				.findLabs());
		map.addAttribute(FACILITY_MODEL_KEY, facility);
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(EDIT_VIEW_NAME, map);
	}
	
	/*
	 * Sets the lab work assessment item index according to the number of lab
	 * work assessment items present on the specified assess lab work referral
	 * form.
	 *  
	 * @param form assess lab work referral form
	 * @param map model map
	 * @return model map with lab work assessment item index attribute added
	 */
	private ModelMap setCurrentLabWorkAssessmentIndex(
			final AssessLabWorkReferralForm form, final ModelMap map) {
		final int currentIndex;
		if (form.getLabWorkAssessmentItems() == null) {
			currentIndex = 0;
		} else {
			currentIndex = form.getLabWorkAssessmentItems().size();
		}
		map.addAttribute(LAB_WORK_ASSESSMENT_ITEM_INDEX_MODEL_KEY, currentIndex);
		return map;
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Lab.class,
				this.labPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				LabWork.class,
				this.labWorkPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				ProviderAssignment.class,
				this.providerAssignmentPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				"time",
				this.customDateEditorFactory
				.createCustomTimeOnlyEditor(true));
		binder.registerCustomEditor(
				ProviderLevel.class,
				this.providerLevelPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				LabWorkReferral.class,
				this.labWorkReferralPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Facility.class,
				this.facilityPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(
				Date.class, 
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
}