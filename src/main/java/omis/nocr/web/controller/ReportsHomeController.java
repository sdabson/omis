package omis.nocr.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Reports home controller.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Dec 12, 2016)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/nocr")
@PreAuthorize("hasRole('USER')")
public class ReportsHomeController {

	/* Views. */
	
	private static final String HOME_VIEW_NAME = "nocr/home";
	
	private static final String BASIC_INFO_ACTION_MENU_VIEW_NAME 
		= "nocr/basicInformation/includes/basicInformationActionMenu";
	
	private static final String ALT_IDENTITY_ACTION_MENU_VIEW_NAME
		= "nocr/basicInformation/includes/alternativeIdentitiesActionMenu";
	
	private static final String DEMOGRAPHICS_ACTION_MENU_VIEW_NAME
		= "nocr/basicInformation/includes/demographicsActionMenu";

	private static final String DNA_ACTION_MENU_VIEW_NAME
		= "nocr/basicInformation/includes/dnaActionMenu";
	
	private static final String FLAGS_ACTION_MENU_VIEW_NAME
		= "nocr/basicInformation/includes/flagsActionMenu";
	
	private static final String ID_NUMBERS_ACTION_MENU_VIEW_NAME
		= "nocr/basicInformation/includes/identificationNumbersActionMenu";
	
	private static final String LEGAL_NAME_AND_IDENTITY_ACTION_MENU_VIEW_NAME
		= "nocr/basicInformation/includes/legalNameAndIdentityActionMenu";
	
	private static final String MILITARY_ACTION_MENU_VIEW_NAME
		= "nocr/basicInformation/includes/militaryActionMenu";
	
	private static final String MUGSHOTS_ACTION_MENU_VIEW_NAME
	    = "nocr/basicInformation/includes/mugshotsActionMenu"; 
	
	private static final String RELIGIOUS_PREFERENCES_ACTION_MENU_VIEW_NAME
		= "nocr/basicInformation/includes/religiousPreferencesActionMenu";
	
	private static final String SCARS_MARKS_AND_TATTOS_ACTION_MENU_VIEW_NAME
		= "nocr/basicInformation/includes/scarsMarksAndTattoosActionMenu";
	
	private static final String CASE_MANAGEMENT_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/caseManagementActionMenu";
	
	private static final String ASSESSMENTS_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/assessmentsActionMenu";
	
	private static final String BOARD_HEARINGS_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/boardHearingsActionMenu";
	
	private static final String CASE_MANAGER_AND_OFFICER_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/caseManagerAndOfficerActionMenu";
	
	private static final String CASE_NOTES_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/caseNotesActionMenu";
	
	private static final String CONDITIONS_OF_SUPERVISION_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/conditionsOfSupervisionActionMenu";
	
	private static final String CUSTODY_REVIEW_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/custodyReviewActionMenu";
	
	private static final String EDUCATION_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/educationActionMenu";
	
	private static final String EMPLOYMENT_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/employmentActionMenu";
	
	private static final String GOALS_AND_OBJECTIVES_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/goalsAndObjectivesActionMenu";
	
	private static final String GRIEVANCE_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/grievanceActionMenu";
	
	private static final String NEEDS_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/needsActionMenu";
	
	private static final String REFERRALS_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/referralsActionMenu";
	
	private static final String RESIDENCE_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/residenceActionMenu";
	
	private static final String SERVICES_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/servicesActionMenu";
	
	private static final String STRENGTHS_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/strengthsActionMenu";
	
	private static final String TREATMENT_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/treatmentActionMenu";
	
	private static final String VEHICLES_ACTION_MENU_VIEW_NAME
		= "nocr/caseManagement/includes/vehiclesActionMenu";
	
	private static final String COMPLIANCE_ACTION_MENU_VIEW_NAME
		= "nocr/compliance/includes/complianceActionMenu";
	
	private static final String DISCIPLINARY_HEARING_ACTION_MENU_VIEW_NAME
		= "nocr/compliance/includes/disciplinaryHearingActionMenu";
	
	private static final String REPORTS_OF_VIOLATION_ACTION_MENU_VIEW_NAME
		= "nocr/compliance/includes/reportsOfViolationActionMenu";
	
	private static final String REQUIREMENTS_TO_REGISTER_ACTION_MENU_VIEW_NAME
		= "nocr/compliance/includes/requirementsToRegisterActionMenu";
	
	private static final String RESTITUTION_ACTION_MENU_VIEW_NAME
		= "nocr/compliance/includes/restitutionActionMenu";
	
	private static final String SANCTIONS_AND_INTERVENTIONS_ACTION_MENU_VIEW_NAME
		= "nocr/compliance/includes/sanctionsAndInterventionsActionMenu";
	
	private static final String SUBSTANCE_HISTORY_ACTION_MENU_VIEW_NAME
		= "nocr/compliance/includes/substanceHistoryActionMenu";
	
	private static final String SUBSTANCE_TESTS_ACTION_MENU_VIEW_NAME
		= "nocr/compliance/includes/substanceTestsActionMenu";
	
	private static final String HEALTH_ACTION_MENU_VIEW_NAME
		= "nocr/health/includes/healthActionMenu";
	
	private static final String ALLERGIES_ACTION_MENU_VIEW_NAME
		= "nocr/health/includes/allergiesActionMenu";
	
	private static final String LAB_WORK_ACTION_MENU_VIEW_NAME
		= "nocr/health/includes/labWorkActionMenu";
	
	private static final String EXTERNAL_APPOINTMENTS_ACTION_MENU_VIEW_NAME
		= "nocr/health/includes/externalAppointmentsActionMenu";
	
	private static final String INTERNAL_APPOINTMENTS_ACTION_MENU_VIEW_NAME
		= "nocr/health/includes/internalAppointmentsActionMenu";
	
	private static final String LEGAL_ACTION_MENU_VIEW_NAME
		= "nocr/legal/includes/legalActionMenu";
	
	private static final String COURT_CASES_ACTION_MENU_VIEW_NAME
		= "nocr/legal/includes/courtCasesActionMenu";
	
	private static final String COURT_DOCUMENTS_ACTION_MENU_VIEW_NAME
		= "nocr/legal/includes/courtDocumentsActionMenu";
	
	private static final String COURT_ORDERED_CONDITIONS_ACTION_MENU_VIEW_NAME
		= "nocr/legal/includes/courtOrderedConditionsActionMenu";
	
	private static final String DETAINERS_AND_NOTIFICATIONS_ACTION_MENU_VIEW_NAME
		= "nocr/legal/includes/detainersAndNotificationsActionMenu";
	
	private static final String MISDEMEANOR_CITATIONS_ACTION_MENU_VIEW_NAME
		= "nocr/legal/includes/misdemeanorCitationsActionMenu";
	
	private static final String OFFENSES_ACTION_MENU_VIEW_NAME
		= "nocr/legal/includes/offensesActionMenu";
	
	private static final String PENDING_CHARGES_ACTION_MENU_VIEW_NAME
		= "nocr/legal/includes/pendingChargesActionMenu";
	
	private static final String PRESENTENCE_INVESTIGATIONS_ACTION_MENU_VIEW_NAME
		= "nocr/legal/includes/presentenceInvestigationsActionMenu";
	
	private static final String SENTENCE_CALCULATIONS_ACTION_MENU_VIEW_NAME
		= "nocr/legal/includes/sentenceCalculationsActionMenu";

	private static final String PLACEMENT_ACTION_MENU_VIEW_NAME
		= "nocr/placement/includes/placementActionMenu";
	
	private static final String BED_ASSIGNMENT_ACTION_MENU_VIEW_NAME
		= "nocr/placement/includes/bedAssignmentActionMenu";
	
	private static final String COMMIT_STATUS_ACTION_MENU_VIEW_NAME
		= "nocr/placement/includes/commitStatusActionMenu";
	
	private static final String CORRECTIONAL_STATUS_ACTION_MENU_VIEW_NAME
		= "nocr/placement/includes/correctionalStatusActionMenu";
	
	private static final String LOCATION_ACTION_MENU_VIEW_NAME
		= "nocr/placement/includes/locationActionMenu";
	
	private static final String PLACEMENT_SUB_ACTION_MENU_VIEW_NAME
		= "nocr/placement/includes/placementSubActionMenu";
	
	private static final String PROGRAM_PLACEMENT_ACTION_MENU_VIEW_NAME
		= "nocr/placement/includes/programPlacementActionMenu";
	
	private static final String SCREENING_ACTION_MENU_VIEW_NAME
		= "nocr/placement/includes/screeningActionMenu";
	
	private static final String WORK_ASSIGNMENTS_ACTION_MENU_VIEW_NAME
		= "nocr/placement/includes/workAssignmentsActionMenu";
	
	private static final String RELATIONSHIPS_ACTION_MENU_VIEW_NAME
		= "nocr/relationships/includes/relationshipsActionMenu";
	
	private static final String CRIMINAL_ASSOCIATES_ACTION_MENU_VIEW_NAME
		= "nocr/relationships/includes/criminalAssociatesActionMenu";
	
	private static final String FAMILY_ACTION_MENU_VIEW_NAME
		= "nocr/relationships/includes/familyActionMenu";
	
	private static final String VICTIMS_ACTION_MENU_VIEW_NAME
		= "nocr/relationships/includes/victimsActionMenu";
	
	private static final String VISITATION_ACTION_MENU_VIEW_NAME
		= "nocr/relationships/includes/visitationActionMenu";
	
	private static final String SAFETY_ACTION_MENU_VIEW_NAME
		= "nocr/safety/includes/safetyActionMenu";
	
	private static final String ADA_ACCOMMODATIONS_ACTION_MENU_VIEW_NAME
		= "nocr/safety/includes/adaAccommodationsActionMenu";
	
	private static final String ALERTS_ACTION_MENU_VIEW_NAME
		= "nocr/safety/includes/alertsActionMenu";
	
	private static final String CAUTIONS_ACTION_MENU_VIEW_NAME
		= "nocr/safety/includes/cautionsActionMenu";
	
	private static final String INCIDENTS_ACTION_MENU_VIEW_NAME
		= "nocr/safety/includes/incidentsActionMenu";
	
	private static final String SECURITY_THREAT_GROUPS_ACTION_MENU_VIEW_NAME
		= "nocr/safety/includes/securityThreatGroupsActionMenu";
	
	private static final String SEPARATION_NEEDS_ACTION_MENU_VIEW_NAME
		= "nocr/safety/includes/separationNeedsActionMenu";
	
	private static final String SPECIAL_MANAGEMENT_DESIGNATIONS_ACTION_MENU_VIEW_NAME
		= "nocr/safety/includes/specialManagementDesignationsActionMenu";
	
	private static final String TIER_DESIGNATIONS_ACTION_MENU_VIEW_NAME
		= "nocr/safety/includes/tierDesignationsActionMenu";
	
	private static final String WORK_RESTRICTIONS_ACTION_MENU_VIEW_NAME
		= "nocr/safety/includes/workRestrictionsActionMenu";
	
	
	/* Constructor. */
	
	/** Instantiates a default reports home controller. */
	public ReportsHomeController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows non offender-centric reports.
	 * 
	 * @return non offender-centric reports
	 */
	@RequestMapping("/home.html")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ModelAndView home() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(HOME_VIEW_NAME, modelMap);
	}
	
	/* Basic Information */
	
	/**
	 * Shows action menu for basic information.
	 * 
	 * @return basic information action menu
	 */
	@RequestMapping("/basicInformationActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView basicInformationActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(BASIC_INFO_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for alternative identities.
	 * 
	 * @return alternative identities action menu
	 */
	@RequestMapping("/alternativeIdentitiesActionMenu.html")
	@PreAuthorize("hasRole('OFFENDER_ALT_IDENTITY_VIEW') or hasRole('ADMIN')")
	public ModelAndView alternativeIdentitiesActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(ALT_IDENTITY_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for demographics.
	 * 
	 * @return demographics action menu
	 */
	@RequestMapping("/demographicsActionMenu.html")
	@PreAuthorize("hasRole('OFFENDER_DEMOGRAPHICS_VIEW') or hasRole('ADMIN')")
	public ModelAndView demographicsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(DEMOGRAPHICS_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for dna.
	 * 
	 * @return dna action menu
	 */
	@RequestMapping("/dnaActionMenu.html")
	@PreAuthorize("hasRole('DNA_VIEW') or hasRole('ADMIN')")
	public ModelAndView dnaActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(DNA_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for flags.
	 * 
	 * @return flags action menu
	 */
	@RequestMapping("/flagsActionMenu.html")
	@PreAuthorize("hasRole('OFFENDER_FLAG_VIEW') or hasRole('ADMIN')")
	public ModelAndView flagsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(FLAGS_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for identification numbers.
	 * 
	 * @return identification numbers action menu
	 */
	@RequestMapping("/identificationNumbersActionMenu.html")
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ModelAndView identificationNumbersActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(ID_NUMBERS_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for legal name and identity.
	 * 
	 * @return legal name and identity action menu
	 */
	@RequestMapping("/legalNameAndIdentityActionMenu.html")
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ModelAndView legalNameAndIdentityActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(LEGAL_NAME_AND_IDENTITY_ACTION_MENU_VIEW_NAME,
				modelMap);
	}
	
	/**
	 * Shows action menu for military.
	 * 
	 * @return military action menu
	 */
	@RequestMapping("/militaryActionMenu.html")
	@PreAuthorize("hasRole('MILITARY_LIST') or hasRole('ADMIN')")
	public ModelAndView militaryActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(MILITARY_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for mugshots.
	 * 
	 * @return mugshots action menu
	 */
	@RequestMapping("/mugshotsActionMenu.html")
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ModelAndView mugshotsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(MUGSHOTS_ACTION_MENU_VIEW_NAME, modelMap);
	} 
	
	/**
	 * Shows action menu for religious preferences.
	 * 
	 * @return religious preferences action menu
	 */
	@RequestMapping("/religiousPreferenceActionMenu.html")
	@PreAuthorize("hasRole('RELIGIOUS_PREFERENCE_VIEW') or hasRole('ADMIN')")
	public ModelAndView religiousPreferenceActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(RELIGIOUS_PREFERENCES_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}
	
	/**
	 * Shows action menu for scars, marks and tattoos.
	 * 
	 * @return scars, marks and tattoos action menu
	 */
	@RequestMapping("/scarsMarksAndTattoosActionMenu.html")
	@PreAuthorize("hasRole('PHYSICAL_FEATURE_VIEW') or hasRole('ADMIN')")
	public ModelAndView scarsMarksAndTattoosActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(SCARS_MARKS_AND_TATTOS_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}
	
	/* Case Management */
	
	/**
	 * Shows action menu for case management.
	 * 
	 * @return case management action menu
	 */
	@RequestMapping("/caseManagementActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView caseManagementActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(CASE_MANAGEMENT_ACTION_MENU_VIEW_NAME,
				modelMap);
	}
	
	/**
	 * Shows action menu for assessments.
	 * 
	 * @return assessments action menu
	 */
	@RequestMapping("/assessmentsActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView assessmentsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(ASSESSMENTS_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for board hearings.
	 * 
	 * @return board hearings action menu
	 */
	@RequestMapping("/boardHearingsActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView boardHearingsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(BOARD_HEARINGS_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for case manager and officer.
	 * 
	 * @return case manager and officer action menu
	 */
	@RequestMapping("/caseManagerAndOfficerActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView caseManagerAndOfficerActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(CASE_MANAGER_AND_OFFICER_ACTION_MENU_VIEW_NAME,
				modelMap);
	}
	
	/**
	 * Shows action menu for case notes.
	 * 
	 * @return case notes action menu
	 */
	@RequestMapping("/caseNotesActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView caseNotesActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(CASE_NOTES_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for conditions of supervision.
	 * 
	 * @return conditions of supervision action menu
	 */
	@RequestMapping("/conditionsOfSupervisionActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView conditionsOfSupervisionActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(CONDITIONS_OF_SUPERVISION_ACTION_MENU_VIEW_NAME,
				modelMap);
	}
	
	/**
	 * Shows action menu for custody review.
	 * 
	 * @return custody review action menu
	 */
	@RequestMapping("/custodyReviewActionMenu.html")
	@PreAuthorize("hasRole('CUSTODY_REVIEW_VIEW') or hasRole('ADMIN')")
	public ModelAndView custodyReviewActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(CUSTODY_REVIEW_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for education.
	 * 
	 * @return education action menu
	 */
	@RequestMapping("/educationActionMenu.html")
	@PreAuthorize("hasRole('EDUCATION_VIEW') or hasRole('ADMIN')")
	public ModelAndView educationActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(EDUCATION_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for employment.
	 * 
	 * @return employment action menu
	 */
	@RequestMapping("/employmentActionMenu.html")
	@PreAuthorize("hasRole('EMPLOYMENT_TERM_VIEW') or hasRole('ADMIN')")
	public ModelAndView employmentActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(EMPLOYMENT_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for goals and objectives.
	 * 
	 * @return goals and objectives action menu
	 */
	@RequestMapping("/goalsAndObjectivesActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView goalsAndObjectivesActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(GOALS_AND_OBJECTIVES_ACTION_MENU_VIEW_NAME,
				modelMap);
	}
	
	/**
	 * Shows action menu for grievance.
	 * 
	 * @return grievance action menu
	 */
	@RequestMapping("/grievanceActionMenu.html")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ModelAndView grievanceActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(GRIEVANCE_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for needs.
	 * 
	 * @return needs action menu
	 */
	@RequestMapping("/needsActionMenu.html")
	@PreAuthorize("hasRole('NEED_MODULE') or hasRole('ADMIN')")
	public ModelAndView needsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(NEEDS_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for referrals.
	 * 
	 * @return referrals action menu
	 */
	@RequestMapping("/referralsActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView referralsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(REFERRALS_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for residence.
	 * 
	 * @return residence action menu
	 */
	@RequestMapping("/residenceActionMenu.html")
	@PreAuthorize("hasRole('RESIDENCE_TERM_LIST') "
			+ "or hasRole('NON_RESIDENCE_TERM_LIST') or hasRole('ADMIN')")
	public ModelAndView residenceActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(RESIDENCE_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for services.
	 * 
	 * @return services action menu
	 */
	@RequestMapping("/servicesActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView servicesActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(SERVICES_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for strengths.
	 * 
	 * @return strengths action menu
	 */
	@RequestMapping("/strengthsActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView strengthsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(STRENGTHS_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for treatment.
	 * 
	 * @return treatment action menu
	 */
	@RequestMapping("/treatmentActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView treatmentActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(TREATMENT_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for vehicles.
	 * 
	 * @return vehicles action menu
	 */
	@RequestMapping("/vehiclesActionMenu.html")
	@PreAuthorize("hasRole('VEHICLE_VIEW') or hasRole('ADMIN')")
	public ModelAndView vehiclesActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(VEHICLES_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/* Compliance */

	/**
	 * Shows action menu for compliance.
	 * 
	 * @return compliance action menu
	 */
	@RequestMapping("/complianceActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView complianceActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(COMPLIANCE_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for disciplinary hearing.
	 * 
	 * @return disciplinary hearing action menu
	 */
	@RequestMapping("/disciplinaryHearingActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView disciplinaryHearingActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(DISCIPLINARY_HEARING_ACTION_MENU_VIEW_NAME,
				modelMap);
	}
	
	/**
	 * Shows action menu for reports of violation.
	 * 
	 * @return reports of violation action menu
	 */
	@RequestMapping("/reportsOfViolationActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView reportsOfViolationActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(REPORTS_OF_VIOLATION_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}
	
	/**
	 * Shows action menu for requirements to register.
	 * 
	 * @return requirements to register action menu
	 */
	@RequestMapping("/requirementsToRegisterActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView requirementsToRegisterActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(REQUIREMENTS_TO_REGISTER_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}
	
	/**
	 * Shows action menu for restitution.
	 * 
	 * @return restitution action menu
	 */
	@RequestMapping("/restitutionActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView restitutionActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(RESTITUTION_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for sanctions and interventions.
	 * 
	 * @return sanctions and interventions action menu
	 */
	@RequestMapping("/sanctionsAndInterventionsActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView sanctionsAndInterventionsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(
				SANCTIONS_AND_INTERVENTIONS_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for substance history.
	 * 
	 * @return substance history action menu
	 */
	@RequestMapping("/substanceHistoryActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView substanceHistoryActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(SUBSTANCE_HISTORY_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}
	
	/**
	 * Shows action menu for substance tests.
	 * 
	 * @return substance tests action menu
	 */
	@RequestMapping("/substanceTestsActionMenu.html")
	@PreAuthorize("hasRole('SUBSTANCE_TEST_VIEW') or hasRole('ADMIN')")
	public ModelAndView substanceTestsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(SUBSTANCE_TESTS_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}
	
	/* Health */
	
	/**
	 * Shows action menu for health.
	 * 
	 * @return health action menu
	 */
	@RequestMapping("/healthActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView healthActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(HEALTH_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for allergies.
	 * 
	 * @return allergies action menu
	 */
	@RequestMapping("/allergiesActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView allergiesActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(ALLERGIES_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for lab work.
	 * 
	 * @return lab work action menu
	 */
	@RequestMapping("/labWorkActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView labWorkActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(LAB_WORK_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for external appointments.
	 * 
	 * @return external appointments action menu
	 */
	@RequestMapping("/externalAppointmentsActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView externalAppointmentsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(EXTERNAL_APPOINTMENTS_ACTION_MENU_VIEW_NAME,
				modelMap);
	}

	/**
	 * Shows action menu for internal appointments.
	 * 
	 * @return internal appointments action menu
	 */
	@RequestMapping("/internalAppointmentsActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView internalAppointmentsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(INTERNAL_APPOINTMENTS_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}
	
	/* Legal */
	
	/**
	 * Shows action menu for legal.
	 * 
	 * @return legal action menu
	 */
	@RequestMapping("/legalActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView legalActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(LEGAL_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for court cases.
	 * 
	 * @return court cases action menu
	 */
	@RequestMapping("/courtCasesActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView courtCasesActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(COURT_CASES_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for court documents.
	 * 
	 * @return court documents action menu
	 */
	@RequestMapping("/courtDocumentsActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView courtDocumentsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(COURT_DOCUMENTS_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}

	/**
	 * Shows action menu for court ordered conditions.
	 * 
	 * @return court ordered conditions action menu
	 */
	@RequestMapping("/courtOrderedConditionsActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView courtOrderedConditionsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(COURT_ORDERED_CONDITIONS_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}

	/**
	 * Shows action menu for detainers and notifications.
	 * 
	 * @return detainers and notifications action menu
	 */
	@RequestMapping("/detainersAndNotificationsActionMenu.html")
	@PreAuthorize("hasRole('DETAINER_NOTIFICATION_VIEW') or hasRole('ADMIN')")
	public ModelAndView detainersAndNotificationsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(
				DETAINERS_AND_NOTIFICATIONS_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for offenses.
	 * 
	 * @return offenses action menu
	 */
	@RequestMapping("/offensesActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView offensesActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(OFFENSES_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for misdemeanor citations.
	 * 
	 * @return misdemeanor citations action menu
	 */
	@RequestMapping("/misdemeanorCitationsActionMenu.html")
	@PreAuthorize("hasRole('MISDEMEANOR_CITATION_VIEW') or hasRole('ADMIN')")
	public ModelAndView misdemeanorCitationsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(MISDEMEANOR_CITATIONS_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}

	/**
	 * Shows action menu for pending charges.
	 * 
	 * @return pending charges action menu
	 */
	@RequestMapping("/pendingChargesActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView pendingChargesActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(PENDING_CHARGES_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}

	/**
	 * Shows action menu for presentence investigations.
	 * 
	 * @return presentence investigations action menu
	 */
	@RequestMapping("/presentenceInvestigationsActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView presentenceInvestigationsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(
				PRESENTENCE_INVESTIGATIONS_ACTION_MENU_VIEW_NAME, modelMap);
	}
	
	/**
	 * Shows action menu for sentence calculations.
	 * 
	 * @return sentence calculations action menu
	 */
	@RequestMapping("/sentenceCalculationsActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView sentenceCalculationsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(SENTENCE_CALCULATIONS_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}
	
	/* Placement */
	
	/**
	 * Shows action menu for placement.
	 * 
	 * @return placement action menu
	 */
	@RequestMapping("/placementActionMenu.html")
	@PreAuthorize("hasRole('PLACEMENT_PROFILE_VIEW') or hasRole('ADMIN')")
	public ModelAndView placementActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(PLACEMENT_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for bed assignment.
	 * 
	 * @return bed assignment action menu
	 */
	@RequestMapping("/bedAssignmentActionMenu.html")
	@PreAuthorize("hasRole('BED_PLACEMENT_VIEW') or hasRole('ADMIN')")
	public ModelAndView bedAssignmentActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(BED_ASSIGNMENT_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for commit status.
	 * 
	 * @return commit status action menu
	 */
	@RequestMapping("/commitStatusActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView commitStatusActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(COMMIT_STATUS_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for correctional status.
	 * 
	 * @return correctional status action menu
	 */
	@RequestMapping("/correctionalStatusActionMenu.html")
	@PreAuthorize("hasRole('CORRECTIONAL_STATUS_TERM_VIEW') or hasRole('ADMIN')")
	public ModelAndView correctionalStatusActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(CORRECTIONAL_STATUS_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}

	/**
	 * Shows action menu for location.
	 * 
	 * @return location action menu
	 */
	@RequestMapping("/locationActionMenu.html")
	@PreAuthorize("hasRole('LOCATION_TERM_VIEW') or hasRole('ADMIN')")
	public ModelAndView locationActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(LOCATION_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for sub placement.
	 * 
	 * @return sub placement action menu
	 */
	@RequestMapping("/placementSubActionMenu.html")
	@PreAuthorize("hasRole('PLACEMENT_TERM_VIEW') or hasRole('ADMIN')")
	public ModelAndView placementSubActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(PLACEMENT_SUB_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for program placement.
	 * 
	 * @return program placement action menu
	 */
	@RequestMapping("/programPlacementActionMenu.html")
	@PreAuthorize("hasRole('PROGRAM_PLACEMENT_CHANGE_VIEW') or hasRole('ADMIN')")
	public ModelAndView programPlacementActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(PROGRAM_PLACEMENT_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}

	/**
	 * Shows action menu for screening.
	 * 
	 * @return screening action menu
	 */
	@RequestMapping("/screeningActionMenu.html")
	@PreAuthorize("hasRole('NOCR_VIEW') or hasRole('ADMIN')")
	public ModelAndView screeningActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(SCREENING_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for work assignments.
	 * 
	 * @return work assignments action menu
	 */
	@RequestMapping("/workAssignmentsActionMenu.html")
	@PreAuthorize("hasRole('WORK_ASSIGNMENT_VIEW') or hasRole('ADMIN')")
	public ModelAndView workAssignmentsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(WORK_ASSIGNMENTS_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}
	
	/* Relationships */
	
	/**
	 * Shows action menu for relationships.
	 * 
	 * @return relationships action menu
	 */
	@RequestMapping("/relationshipsActionMenu.html")
	@PreAuthorize("hasRole('OFFENDER_RELATIONSHIP_VIEW') or hasRole('ADMIN')")
	public ModelAndView relationshipsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(RELATIONSHIPS_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for criminal associates.
	 * 
	 * @return criminal associates action menu
	 */
	@RequestMapping("/criminalAssociatesActionMenu.html")
	@PreAuthorize("hasRole('CRIMINAL_ASSOCIATION_VIEW') or hasRole('ADMIN')")
	public ModelAndView criminalAssociatesActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(CRIMINAL_ASSOCIATES_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}

	/**
	 * Shows action menu for family.
	 * 
	 * @return family action menu
	 */
	@RequestMapping("/familyActionMenu.html")
	@PreAuthorize("hasRole('FAMILY_ASSOCIATION_VIEW') or hasRole('ADMIN')")
	public ModelAndView familyActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(FAMILY_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for victims.
	 * 
	 * @return victims action menu
	 */
	@RequestMapping("/victimsActionMenu.html")
	@PreAuthorize("hasRole('VICTIM_ASSOCIATION_VIEW') or hasRole('ADMIN')")
	public ModelAndView victimsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(VICTIMS_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for visitation.
	 * 
	 * @return visitation action menu
	 */
	@RequestMapping("/visitationActionMenu.html")
	@PreAuthorize("hasRole('VISIT_LIST') or hasRole('ADMIN')")
	public ModelAndView visitationActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(VISITATION_ACTION_MENU_VIEW_NAME, modelMap);
	}
		
	/* Safety */
	
	/**
	 * Shows action menu for safety.
	 * 
	 * @return safety action menu
	 */
	@RequestMapping("/safetyActionMenu.html")
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ModelAndView safetyActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(SAFETY_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for ADA accommodations.
	 * 
	 * @return ADA accommodations action menu
	 */
	@RequestMapping("/adaAccommodationsActionMenu.html")
	@PreAuthorize("hasRole('ADA_ACCOMMODATION_VIEW') or "
			+ "hasRole('ADA_ACCOMMODATION_ISSUANCE_VIEW') or hasRole('ADMIN')")
	public ModelAndView adaAccommodationsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(ADA_ACCOMMODATIONS_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}

	/**
	 * Shows action menu for alerts.
	 * 
	 * @return alerts action menu
	 */
	@RequestMapping("/alertsActionMenu.html")
	@PreAuthorize("hasRole('ALERT_VIEW') or hasRole('ADMIN')")
	public ModelAndView alertsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(ALERTS_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for cautions.
	 * 
	 * @return cautions action menu
	 */
	@RequestMapping("/cautionsActionMenu.html")
	@PreAuthorize("hasRole('CAUTION_VIEW') or hasRole('ADMIN')")
	public ModelAndView cautionsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(CAUTIONS_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for incidents.
	 * 
	 * @return incidents action menu
	 */
	@RequestMapping("/incidentsActionMenu.html")
	@PreAuthorize("hasRole('INCIDENT_STATEMENT_VIEW') or hasRole('ADMIN')")
	public ModelAndView incidentsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(INCIDENTS_ACTION_MENU_VIEW_NAME, modelMap);
	}

	/**
	 * Shows action menu for security threat groups.
	 * 
	 * @return security threat groups action menu
	 */
	@RequestMapping("/securityThreatGroupsActionMenu.html")
	@PreAuthorize("hasRole('STG_AFFILIATION_VIEW') or hasRole('ADMIN')")
	public ModelAndView securityThreatGroupsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(SECURITY_THREAT_GROUPS_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}

	/**
	 * Shows action menu for separation needs.
	 * 
	 * @return separation needs action menu
	 */
	@RequestMapping("/separationNeedsActionMenu.html")
	@PreAuthorize("hasRole('SEPARATION_NEED_VIEW') or hasRole('ADMIN')")
	public ModelAndView separationNeedsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(SEPARATION_NEEDS_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}

	/**
	 * Shows action menu for special management designations.
	 * 
	 * @return special management designations action menu
	 */
	@RequestMapping("/specialManagementDesignationsActionMenu.html")
	@PreAuthorize("hasRole('SPECIAL_NEED_VIEW') or hasRole('ADMIN')")
	public ModelAndView specialManagementDesignationsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(
				SPECIAL_MANAGEMENT_DESIGNATIONS_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}

	/**
	 * Shows action menu for tier designations.
	 * 
	 * @return tier designations action menu
	 */
	@RequestMapping("/tierDesignationsActionMenu.html")
	@PreAuthorize("hasRole('TIER_DESIGNATION_VIEW') or hasRole('ADMIN')")
	public ModelAndView tierDesignationsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(TIER_DESIGNATIONS_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}

	/**
	 * Shows action menu for work restrictions.
	 * 
	 * @return work restrictions action menu
	 */
	@RequestMapping("/workRestrictionsActionMenu.html")
	@PreAuthorize("hasRole('WORK_RESTRICTION_VIEW') or hasRole('ADMIN')")
	public ModelAndView workRestrictionsActionMenu() {
		ModelMap modelMap = new ModelMap();
		return new ModelAndView(WORK_RESTRICTIONS_ACTION_MENU_VIEW_NAME, 
				modelMap);
	}
	
}
