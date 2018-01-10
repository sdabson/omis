package omis.offender.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.report.ActivitySummaryReportService;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.user.domain.UserAccount;
import omis.user.service.UserAccountService;
import omis.web.profile.ProfileItem;
import omis.web.profile.ProfileItemRegistry;

/**
 * Offender profile controller.
 * 
 * @author Stephen Abson
 * @author Ryan Johns
 * @author Josh Divine
 * @author Annie Wahl
 * @author Sierra Rosales
 * @version 0.1.3 (Nov 1, 2017)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/offender")
@PreAuthorize("hasRole('USER')")
public class OffenderProfileController {

	/* Views. */
	
	private static final String PROFILE_VIEW_NAME = "offender/profile";
	
	private static final String PROFILE_ITEM_VIEW_NAME =
			"offender/includes/profileItem";
	
	private static final String PROFILE_SAFETY_ACTION_MENU_VIEW_NAME
		= "offender/includes/offenderProfileSafetyActionMenu";
	
	private static final String PROFILE_BASIC_INFORMATION_ACTION_MENU_VIEW_NAME
		= "offender/includes/offenderProfileBasicInformationActionMenu";
	
	private static final String PROFILE_LEGAL_ACTION_MENU_VIEW_NAME
		= "offender/includes/offenderProfileLegalActionMenu";
	
	private static final String PROFILE_CASE_MANAGEMENT_ACTION_MENU_VIEW_NAME
		= "offender/includes/offenderProfileCaseManagementActionMenu";
	
	private static final String PROFILE_HEALTH_ACTION_MENU_VIEW_NAME
		= "offender/includes/offenderProfileHealthActionMenu";
	
	private static final String PROFILE_PLACEMENT_ACTION_MENU_VIEW_NAME
		= "offender/includes/offenderProfilePlacementActionMenu";
	
	/* Model keys. */
	
	private static final String PROFILE_ITEM_REGISTRY_MODEL_KEY =
			"profileItemRegistry";
	
	private static final String REPORTS_LIST_VIEW_NAME_MODEL_KEY =
			"reportsListView";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
//	private static final String ACTIVITY_SUMMARIES_MODEL_KEY =
//			"activitySummaries";
	
	/* Report names. */
	
	private static final String SAFETY_DETAILS_REPORT_NAME 
		= "/Safety/Safety_Summary_Redacted";

	private static final String BASIC_INFO_SHEET_REPORT_NAME 
		= "/BasicInformation/Demographics/Offender_File_Cover_Sheet";

	private static final String BASIC_INFO_SHEET_REDACTED_REPORT_NAME 
		= "/BasicInformation/Demographics/Offender_File_Cover_Sheet_Redacted";

	private static final String BASIC_INFO_FACILITY_REPORT_NAME 
		= "/BasicInformation/Demographics/Basic_Information_Sheet_Facility";

	private static final String BASIC_INFO_FACILITY_REDACTED_REPORT_NAME 
		= "/BasicInformation/Demographics/"
				+ "Basic_Information_Sheet_Facility_Redacted";

	private static final String BASIC_INFO_ALT_SEC_REPORT_NAME 
		= "/BasicInformation/Demographics/Basic_Information_Sheet_Alt_Secure";

	private static final String BASIC_INFO_ALT_SEC_REDACTED_REPORT_NAME 
		= "/BasicInformation/Demographics/"
				+ "Basic_Information_Sheet_Alt_Secure_Redacted";

	private static final String BASIC_INFO_PROBATION_PAROLE_REPORT_NAME 
		= "/BasicInformation/Demographics/"
				+ "Basic_Information_Sheet_Probation_Parole";

	private static final String
		BASIC_INFO_PROBATION_PAROLE_REDACTED_REPORT_NAME 
			= "/BasicInformation/Demographics/"
					+ "Basic_Information_Sheet_Probation_Parole_Redacted";
	
	private static final String CJIN_BACKGROUND_CHECK_REPORT_NAME 
        = "/Specialty/Background_Check/CJIN_Background_Check_Request";
	
	private static final String WANTED_POSTER_REPORT_NAME 
		= "/BasicInformation/Demographics/Wanted_Poster";
	
	private static final String OFFENDER_CONTACT_REPORT_NAME 
		= "/CaseManagement/Offender_Contact_Report";
	
	private static final String SIGN_UP_REPORT_NAME
		= "/CaseManagement/P_P_Sign_Up/Sign_Up_Packet";	
	
	private static final String INSTITUTIONAL_CASE_PLAN_REPORT_NAME 
		= "/CaseManagement/Institutional_Case_Plan_Header";
	
	private static final String TRANSITIONAL_CASE_PLAN_REPORT_NAME 
		= "/CaseManagement/Transitional_Case_Plan_Header";	
	
	private static final String CLINICAL_SERVICES_REFFERAL_FORM_REPORT_NAME 
		= "/Health/Clinical_Services_Referral_Form_OMIS_Version";
	
	private static final String PROVIDER_BILLING_MEMO_REPORT_NAME
		= "/Health/Provider_Billing_Memo_OMIS_Version";

	private static final String DENIAL_DOC_BILLING_RESPONSIBILITY_REPORT_NAME
		= "/Health/Denial_of_DOC_Billing_Responsibility";
	
	private static final String RESPONSIBILITY_FOR_MEDICAL_BILL_REPORT_NAME
		= "/Health/Responsibility_for_Medical_Bill";
	
	private static final String BASIC_DOOR_CARD_REPORT_NAME 
		= "/Placement/BedAssignment/Basic_Door_Card_OC";
	
	private static final String DETAILED_DOOR_CARD_REPORT_NAME 
		= "/Placement/BedAssignment/Detailed_Door_Card_OC";
	
	private static final String PHTTP_DOOR_CARD_REPORT_NAME 
		= "/Placement/BedAssignment/PHTTP_Door_Card";
	
	private static final String PRISON_INTAKE_REPORT_NAME 
		= "/Placement/Intake/Prison_Intake_Report";
	
	private static final String INTERNAL_EXTERNAL_MOVEMENT_REPORT_NAME 
		= "/Placement/Internal_and_External_Movements";
	
	/* Report parameter names. */
	
	private static final String DOC_ID_REPORT_PARAM_NAME = "DOC_ID";
	
	private static final String USER_ID_REPORT_PARAM_NAME = "USER_ID";
	
	/* Services. */
	
	@Autowired
	private UserAccountService userAccountService;
	
	@Autowired
	@Qualifier("activitySummaryReportService")
	private ActivitySummaryReportService activitySummaryReportService;
	
	/* Property editor factories. */
	
	@Autowired
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/* Profile item registry. */
	
	@Autowired
	@Qualifier("basicInformationProfileItemRegistry")
	private ProfileItemRegistry basicInformationProfileItemRegistry;
	
	@Autowired
	@Qualifier("placementProfileItemRegistry")
	private ProfileItemRegistry placementProfileItemRegistry;
	
	@Autowired
	@Qualifier("legalProfileItemRegistry")
	private ProfileItemRegistry legalProfileItemRegistry;
	
	@Autowired
	@Qualifier("caseManagementProfileItemRegistry")
	private ProfileItemRegistry caseManagementProfileItemRegistry;
	
	@Autowired
	@Qualifier("safetyProfileItemRegistry")
	private ProfileItemRegistry safetyProfileItemRegistry;
	
	@Autowired
	@Qualifier("complianceProfileItemRegistry")
	private ProfileItemRegistry complianceProfileItemRegistry;
	
	@Autowired
	@Qualifier("healthProfileItemRegistry")
	private ProfileItemRegistry healthProfileItemRegistry;
	
	@Autowired
	@Qualifier("relationshipsProfileItemRegistry")
	private ProfileItemRegistry relationshipsProfileItemRegistry;
	
	@Autowired
	@Qualifier("boardOfPardonsAndParoleProfileItemRegistry")
	private ProfileItemRegistry boardOfPardonsAndParoleProfileItemRegistry;

	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Constructor. */
	
	/** Instantiates a default offender profile controller. */
	public OffenderProfileController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows offender profile.
	 * 
	 * @param offender - Offender
	 * @return offender profile
	 */
	@RequestMapping("/profile.html")
	@PreAuthorize("hasRole('OFFENDER_PROFILE_VIEW') or hasRole('ADMIN')")
	public ModelAndView profile(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(PROFILE_VIEW_NAME, map);
	}
	
	/* AJAX views */
	
	/**
	 * Displays the view for the Basic Information Profile for the specified
	 * Offender.
	 * 
	 * @param offender - Offender
	 * @return ModelAndView - view for the Basic Information Profile for the
	 * specified Offender 
	 */
	@RequestMapping(value = "/basicInformationProfile.html",
			method = RequestMethod.GET)
	public ModelAndView displayBasicInformationProfileItem(@RequestParam(
			value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		Date currentDate = new Date();
		UserAccount userAccount = this.userAccountService.findByUsername(
				SecurityContextHolder.getContext()
					.getAuthentication().getName());
		this.buildProfileItems(basicInformationProfileItemRegistry, map, 
				offender, userAccount, currentDate);
		map.addAttribute(PROFILE_ITEM_REGISTRY_MODEL_KEY,
				this.basicInformationProfileItemRegistry);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(REPORTS_LIST_VIEW_NAME_MODEL_KEY,
				"profileBasicInformationReportsList");
		
		return new ModelAndView(PROFILE_ITEM_VIEW_NAME, map);
	}
	
	/**
	 * Displays the view for Placement profile for the specified offender.
	 * 
	 * @param offender - Offender
	 * @return ModelAndView - view for Placement profile for the specified
	 * offender
	 */
	@RequestMapping(value = "/placementProfile.html",
			method = RequestMethod.GET)
	public ModelAndView displayPlacementProfileItem(@RequestParam(
			value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		Date currentDate = new Date();
		UserAccount userAccount = this.userAccountService.findByUsername(
				SecurityContextHolder.getContext()
					.getAuthentication().getName());
		this.buildProfileItems(this.placementProfileItemRegistry, map, 
				offender, userAccount, currentDate);
		map.addAttribute(PROFILE_ITEM_REGISTRY_MODEL_KEY,
				this.placementProfileItemRegistry);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(REPORTS_LIST_VIEW_NAME_MODEL_KEY,
				"profilePlacementReportsList");
		
		return new ModelAndView(PROFILE_ITEM_VIEW_NAME, map);
	}
	
	/**
	 * Displays the view for Legal profile for the specified Offender.
	 * 
	 * @param offender - Offender
	 * @return ModelAndView - view for Legal profile for the specified Offender
	 */
	@RequestMapping(value = "/legalProfile.html",
			method = RequestMethod.GET)
	public ModelAndView displayLegalProfileItem(@RequestParam(
			value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		Date currentDate = new Date();
		UserAccount userAccount = this.userAccountService.findByUsername(
				SecurityContextHolder.getContext()
					.getAuthentication().getName());
		this.buildProfileItems(this.legalProfileItemRegistry, map, 
				offender, userAccount, currentDate);
//		map.addAttribute(ACTIVITY_SUMMARIES_MODEL_KEY,
//				this.activitySummaryReportService
//				.findLegalActivitySummariesByOffender(offender));
		map.addAttribute(PROFILE_ITEM_REGISTRY_MODEL_KEY,
				this.legalProfileItemRegistry);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(REPORTS_LIST_VIEW_NAME_MODEL_KEY,
				"profileLegalReportsList");
		
		return new ModelAndView(PROFILE_ITEM_VIEW_NAME, map);
	}
	
	/**
	 * Displays the view for Case Management profile for the specified Offender.
	 * 
	 * @param offender - Offender
	 * @return ModelAndView - view for Case Management profile for the specified
	 * Offender
	 */
	@RequestMapping(value = "/caseManagementProfile.html",
			method = RequestMethod.GET)
	public ModelAndView displayCaseManagementProfileItem(@RequestParam(
			value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		Date currentDate = new Date();
		UserAccount userAccount = this.userAccountService.findByUsername(
				SecurityContextHolder.getContext()
					.getAuthentication().getName());
		this.buildProfileItems(this.caseManagementProfileItemRegistry, map, 
				offender, userAccount, currentDate);
		map.addAttribute(PROFILE_ITEM_REGISTRY_MODEL_KEY,
				this.caseManagementProfileItemRegistry);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(REPORTS_LIST_VIEW_NAME_MODEL_KEY,
				"profileCaseManagementReportsList");
		
		return new ModelAndView(PROFILE_ITEM_VIEW_NAME, map);
	}
	
	/**
	 * Displays the view for Safety profile for the specified Offender.
	 * 
	 * @param offender - Offender
	 * @return ModelAndView - view for Safety profile for the specified Offender
	 */
	@RequestMapping(value = "/safetyProfile.html",
			method = RequestMethod.GET)
	public ModelAndView displaySafetyProfileItem(@RequestParam(
			value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		Date currentDate = new Date();
		UserAccount userAccount = this.userAccountService.findByUsername(
				SecurityContextHolder.getContext()
					.getAuthentication().getName());
		this.buildProfileItems(this.safetyProfileItemRegistry, map, 
				offender, userAccount, currentDate);
		map.addAttribute(PROFILE_ITEM_REGISTRY_MODEL_KEY,
				this.safetyProfileItemRegistry);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(REPORTS_LIST_VIEW_NAME_MODEL_KEY,
				"profileSafetyReportsList");
		
		return new ModelAndView(PROFILE_ITEM_VIEW_NAME, map);
	}
	
	/**
	 * Displays the view for Compliance profile for the specified Offender.
	 * 
	 * @param offender - Offender
	 * @return ModelAndView - view for Compliance profile for the specified
	 * Offender
	 */
	@RequestMapping(value = "/complianceProfile.html",
			method = RequestMethod.GET)
	public ModelAndView displayComplianceProfileItem(@RequestParam(
			value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		Date currentDate = new Date();
		UserAccount userAccount = this.userAccountService.findByUsername(
				SecurityContextHolder.getContext()
					.getAuthentication().getName());
		this.buildProfileItems(this.complianceProfileItemRegistry, map, 
				offender, userAccount, currentDate);
		map.addAttribute(PROFILE_ITEM_REGISTRY_MODEL_KEY,
				this.complianceProfileItemRegistry);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(PROFILE_ITEM_VIEW_NAME, map);
	}
	
	/**
	 * Displays the view for Health profile for the specified Offender.
	 * 
	 * @param offender - Offender
	 * @return ModelAndView - view for Health profile for the specified Offender
	 */
	@RequestMapping(value = "/healthProfile.html",
			method = RequestMethod.GET)
	public ModelAndView displayHealthProfileItem(@RequestParam(
			value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		Date currentDate = new Date();
		UserAccount userAccount = this.userAccountService.findByUsername(
				SecurityContextHolder.getContext()
					.getAuthentication().getName());
		this.buildProfileItems(this.healthProfileItemRegistry, map, 
				offender, userAccount, currentDate);
		map.addAttribute(PROFILE_ITEM_REGISTRY_MODEL_KEY,
				this.healthProfileItemRegistry);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(REPORTS_LIST_VIEW_NAME_MODEL_KEY,
				"profileHealthReportsList");
		
		return new ModelAndView(PROFILE_ITEM_VIEW_NAME, map);
	}
	
	/**
	 * Displays the view for Relationships profile for the specified Offender.
	 * 
	 * @param offender - Offender
	 * @return ModelAndView - view for Relationships profile for the specified
	 * Offender
	 */
	@RequestMapping(value = "/relationshipsProfile.html",
			method = RequestMethod.GET)
	public ModelAndView displayRelationshipsProfileItem(@RequestParam(
			value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		Date currentDate = new Date();
		UserAccount userAccount = this.userAccountService.findByUsername(
				SecurityContextHolder.getContext()
					.getAuthentication().getName());
		this.buildProfileItems(this.relationshipsProfileItemRegistry, map, 
				offender, userAccount, currentDate);
		map.addAttribute(PROFILE_ITEM_REGISTRY_MODEL_KEY,
				this.relationshipsProfileItemRegistry);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(PROFILE_ITEM_VIEW_NAME, map);
	}
	
	/**
	 * Displays the view for Board of Pardons And Parole profile for the
	 * specified Offender.
	 * 
	 * @param offender - Offender
	 * @return ModelAndView - view for Board of Pardons And Parole profile for
	 * the specified Offender
	 */
	@RequestMapping(value = "/boardOfPardonsAndParoleProfile.html",
			method = RequestMethod.GET)
	public ModelAndView displayBoardPardonsParoleProfileItem(@RequestParam(
			value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		Date currentDate = new Date();
		UserAccount userAccount = this.userAccountService.findByUsername(
				SecurityContextHolder.getContext()
					.getAuthentication().getName());
		this.buildProfileItems(this.boardOfPardonsAndParoleProfileItemRegistry,
				map, offender, userAccount, currentDate);
		map.addAttribute(PROFILE_ITEM_REGISTRY_MODEL_KEY,
				this.boardOfPardonsAndParoleProfileItemRegistry);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(PROFILE_ITEM_VIEW_NAME, map);
	}
	
	//TODO: remove action menus
	/* Action Menus */
	
	/**
	 * Displays the safety action menu for the specified offender's profile. 
	 *  
	 * @param offender offender
	 * @return model and view for profile action menu
	 */
	@RequestMapping(value = "/profileSafetyActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayProfileSafetyActionMenu(@RequestParam(
			value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(PROFILE_SAFETY_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the basic information action menu for the specified offender's 
	 * profile. 
	 *  
	 * @param offender offender
	 * @return model and view for profile action menu
	 */
	@RequestMapping(value = "/profileBasicInformationActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayProfileBasicInformationActionMenu(@RequestParam(
			value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(PROFILE_BASIC_INFORMATION_ACTION_MENU_VIEW_NAME,
				map);
	}
	
	/**
	 * Displays the legal action menu for the specified offender's profile. 
	 *  
	 * @param offender offender
	 * @return model and view for profile action menu
	 */
	@RequestMapping(value = "/profileLegalActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayProfileLegalActionMenu(@RequestParam(
			value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(PROFILE_LEGAL_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the case management action menu for the specified offender's 
	 * profile. 
	 *  
	 * @param offender offender
	 * @return model and view for profile action menu
	 */
	@RequestMapping(value = "/profileCaseManagementActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayProfileCaseManagementActionMenu(@RequestParam(
			value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(PROFILE_CASE_MANAGEMENT_ACTION_MENU_VIEW_NAME,
				map);
	}
	
	/**
	 * Displays the health action menu for the specified offender's profile. 
	 *  
	 * @param offender offender
	 * @return model and view for profile action menu
	 */
	@RequestMapping(value = "/profileHealthActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayProfileHealthActionMenu(@RequestParam(
			value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(PROFILE_HEALTH_ACTION_MENU_VIEW_NAME,
				map);
	}
	
	/**
	 * Displays the placement action menu for the specified offender's profile. 
	 *  
	 * @param offender offender
	 * @return model and view for profile action menu
	 */
	@RequestMapping(value = "/profilePlacementActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayProfilePlacementActionMenu(@RequestParam(
			value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(PROFILE_PLACEMENT_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the report for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileSafetyDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileSafetyDetails(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				SAFETY_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileBasicInfoSheetReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("(hasRole('OFFENDER_VIEW') and hasRole('OFFENDER_SSN_VIEW')) "
			+ "or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileBasicInfoSheet(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				BASIC_INFO_SHEET_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified offender redacted version.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileBasicInfoSheetRedactedReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileBasicInfoSheetRedacted(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				BASIC_INFO_SHEET_REDACTED_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the wanted poster report for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileWantedPosterReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileWantedPoster(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				WANTED_POSTER_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the basic info altSec report for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileBasicInfoAltSecReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("(hasRole('OFFENDER_VIEW') and hasRole('OFFENDER_SSN_VIEW')) "
			+ "or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileBasicInfoAltSec(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				BASIC_INFO_ALT_SEC_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the basic info altSec redacted report for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileBasicInfoAltSecRedactedReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileBasicInfoAltSecRedacted(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				BASIC_INFO_ALT_SEC_REDACTED_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the basic info facility report for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileBasicInfoFacilityReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("(hasRole('OFFENDER_VIEW') and hasRole('OFFENDER_SSN_VIEW')) "
			+ "or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileBasicInfoFacility(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				BASIC_INFO_FACILITY_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the basic info facility redacted report for the specified
	 * offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileBasicInfoFacilityRedactedReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileBasicInfoFacilityRedacted(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				BASIC_INFO_FACILITY_REDACTED_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the basic info probation & parole report for the specified
	 * offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileBasicInfoProbationParoleReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("(hasRole('OFFENDER_VIEW') and hasRole('OFFENDER_SSN_VIEW')) "
			+ "or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileBasicInfoProbationParole(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				BASIC_INFO_PROBATION_PAROLE_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the basic info probation & parole redacted report for the
	 * specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value =
				"/profileBasicInfoProbationParoleRedactedReport.html",
				method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []>
		reportProfileBasicInfoProbationParoleRedacted(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				BASIC_INFO_PROBATION_PAROLE_REDACTED_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
		/**
		 * Returns the cjin background check report for the
		 * specified offender.
		 * @param offender offender
		 * @param reportFormat report format
		 * @return response entity with report
		 */
		@RequestMapping(value =
					"/profileBasicInfocjinBackgroundCheckReport.rtf",
					method = RequestMethod.GET)
		@PreAuthorize("(hasRole('OFFENDER_VIEW') and hasRole('OFFENDER_SSN_VIEW')) "
				+ "or hasRole('ADMIN')")
		public ResponseEntity<byte []>
			reportProfileBasicInfoCJINBackgroundCheck(
				@RequestParam(value = "offender", required = true)
				final Offender offender,
				@RequestParam(value = "reportFormat", required = true)
				final ReportFormat reportFormat) {
				UserAccount userAccount = this.findCurrentUserAccount();
			Map<String, String> reportParamMap = new HashMap<String, String>();
			reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
					Long.toString(offender.getOffenderNumber()));
			reportParamMap.put(USER_ID_REPORT_PARAM_NAME, 
					Long.toString(userAccount.getId()));
			byte[] doc = this.reportRunner.runReport(
					CJIN_BACKGROUND_CHECK_REPORT_NAME,
					reportParamMap, reportFormat);
			return this.reportControllerDelegate.constructReportResponseEntity(
					doc, reportFormat);
			}
	
	/**
	 * Returns the offender contact report for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileOffenderContactReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileOffenderContact(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				OFFENDER_CONTACT_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the offender institutional case plan cover sheet report for the
	 * specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileInstitutionalCasePlanReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileInstitutionalCasePlan(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				INSTITUTIONAL_CASE_PLAN_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the offender transitional case plan cover sheet report for the
	 * specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileTransitionalCasePlanReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileTransitionalCasePlan(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				TRANSITIONAL_CASE_PLAN_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the probation and parole sign up packet for the specified
	 * offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileSignUpReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileSignUp(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				SIGN_UP_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}	
	/**
	 * Returns the clinical services referral form for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileClinicalServicesReferralFormReport.rtf",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileClinicalServicesReferralForm(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				CLINICAL_SERVICES_REFFERAL_FORM_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the provider billing memo for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileProviderBillingMemoReport.rtf",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileProviderBillingMemo(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				PROVIDER_BILLING_MEMO_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the denial of DOC billing responsibility for the specified 
	 * offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileDenialDOCBillingResponsibilityReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileDenialDOCBillingResponsibility(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				DENIAL_DOC_BILLING_RESPONSIBILITY_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the responsibility for medical bill for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileResponsibilityForMedicalBillReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileResponsibilityForMedicalBill(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				RESPONSIBILITY_FOR_MEDICAL_BILL_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the basic door card for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileBasicDoorCardReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileBasicDoorCard(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				BASIC_DOOR_CARD_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the detailed door card for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileDetailedDoorCardReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileDetailedDoorCard(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				DETAILED_DOOR_CARD_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the phttp door card for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profilePhttpDoorCardReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfilePhttpDoorCard(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				PHTTP_DOOR_CARD_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the prison intake report for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profilePrisonIntakeReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("(hasRole('OFFENDER_VIEW') and hasRole('OFFENDER_SSN_VIEW')) "
			+ "or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfilePrisonIntake(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				PRISON_INTAKE_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the internal and external movement report for the specified
	 * offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/profileInternalExternalMovementReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENDER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProfileInternalExternalMove(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				INTERNAL_EXTERNAL_MOVEMENT_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	// Returns whether the current user has the specified role
	private boolean hasRole(final String role) {
		for (GrantedAuthority authority 
				: SecurityContextHolder.getContext().getAuthentication()
					.getAuthorities()) {
			if (role.equals(authority.getAuthority())) {
				return true;
			}
		}
		return false;
	}
	
	// Builds profile items
	private void buildProfileItems(
			final ProfileItemRegistry profileItemRegistry,
			final ModelMap modelMap, final Offender offender, 
			final UserAccount userAccount, final Date currentDate) {
		for (ProfileItem item : profileItemRegistry.getItems()) {
			if (item.getEnabled()) {
				for (String requiredAuthorization
						: item.getRequiredAuthorizations()) {
					if (hasRole(requiredAuthorization)) {
						item.build(modelMap, offender, userAccount,
								currentDate);
						break;
					}
				}
			}
		}
	}
	
	private UserAccount findCurrentUserAccount() {
	      return this.userAccountService.findByUsername(
			SecurityContextHolder.getContext().getAuthentication()
			.getName());
	}
	
	/* Init binder. */
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
	}
}