/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.presentenceinvestigation.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.report.PresentenceInvestigationRequestSummaryReportService;
import omis.presentenceinvestigation.service.PresentenceInvestigationRequestService;
import omis.presentenceinvestigation.web.form.PresentenceInvestigationRequestSearchForm;
import omis.presentenceinvestigation.web.validator.PresentenceInvestigationRequestSearchFormValidator;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.user.domain.UserAccount;

/**
 * Report presentence investigation request controller.
 * 
 * @author Joel Norris
 * @author Annie Wahl
 * @author Josh Divine
 * @author Sierra Haynes
 * @version 0.1.6 (Feb 26, 2019)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/presentenceInvestigation/request")
@PreAuthorize("hasRole('USER')")
public class ReportPresentenceInvestigationRequestController {

	/* View names. */
	
	private static final String LIST_VIEW_NAME 
		= "/presentenceInvestigation/request/list";
	
	private static final String INVESTIGATION_REQUESTS_ACTION_MENU_VIEW_NAME
		= "/presentenceInvestigation/request/includes/"
				+ "presentenceInvestigationRequestsActionMenu";
	

	private static final String INVESTIGATION_REQUESTS_ROW_ACTION_MENU_VIEW_NAME
		= "/presentenceInvestigation/request/includes/"
				+ "presentenceInvestigationRequestsRowActionMenu";
	
	/* Report names. */
	
	private static final String INVESTIGATION_REQUEST_DETAIL_REPORT_NAME 
		= "/Legal/PSI/Pre_Sentence_Investigation";	
	
	private static final String AFFIDAVIT_OF_PECUNIARY_LOSS_REPORT_NAME 
		= "/Legal/PSI/Affidavit_of_Victims_Pecuniary_Loss";	
	
	private static final String PSI_QUESTIONNAIRE_REPORT_NAME 
		= "/Legal/PSI/PSI_Questionnaire";
	
	private static final String INVESTIGATION_ASSIGNMENT_REPORT_NAME 
		= "/Legal/PSI/PSI_Assignments_Report";
	
	private static final String INVESTIGATION_PROGRESS_REPORT_NAME 
		= "/Legal/PSI/PSI_Progress_Summary_Report";
	
	private static final String INVESTIGATION_TASK_REPORT_NAME 
		= "/Legal/PSI/PSI_Task_Assignments";	
	
	private static final String VICTIM_IMPACT_REPORT_NAME 
		= "/Relationships/Victims/Victim_Impact_Statement";
	
	private static final String VICTIM_IMPACT_KID_REPORT_NAME 
		= "/Relationships/Victims/Victim_Impact_for_Kids";
	
	private static final String PSI_REQUEST_DETAILS_REPORT_NAME 
		= "/Legal/PSI/PSI_Request_Details_OC";	
	
	private static final String PSI_REQUEST_LISTING_REPORT_NAME 
		= "/Legal/PSI/PSI_Request_Listing_OC";	
	
	/* Report Parameter names. */
	
	private static final String INVESTIGATION_REQUEST_ID_REPORT_PARAM_NAME 
		= "PSI_REQ_ID";
	
	private static final String PSI_REQUEST_LIST_ID_REPORT_PARAM_NAME 
		= "DOC_ID";
	
	private static final String PSI_QUESTIONNAIRE_ID_REPORT_PARAM_NAME 
		= "PERSON_ID";
	
	private static final String INVESTIGATION_ASSIGNMENT_ID_REPORT_PARAM_NAME 
		= "ASSIGNED_USER_ID";
	
	private static final String INVESTIGATION_PROGRESS_ID_REPORT_PARAM_NAME 
		= "ASSIGNED_USER_ID";
	
	private static final String INVESTIGATION_TASK_ID_REPORT_PARAM_NAME 
		= "ASSIGNED_USER_ID";
		
	/* Model keys. */
	
	private static final String SUMMARIES_MODEL_KEY = "summaries";
	
	private static final String ASSIGNED_USER_MODEL_KEY = "assignedUser";
	
	private static final String PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY 
		= "presentenceInvestigationRequest";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String ON_RETURN_MODEL_KEY = "onReturn";
	
	private static final String USER_ACCOUNT_MODEL_KEY =
			"AuditComponentRetrieverSpringMvcImpl#auditUserAccount";
	
	private static final String UNSUBMITTED_SUMMARIES_MODEL_KEY = 
			"unsubmittedSummaries";
	
	private static final String SUBMITTED_SUMMARIES_MODEL_KEY = 
			"submittedSummaries";
	
	private static final String FORM_MODEL_KEY =
			"presentenceInvestigationRequestSearchForm";
	
	/* Services. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestSummaryReportService")
	private PresentenceInvestigationRequestSummaryReportService
		presentenceInvestigationRequestReportService;
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestService")
	private PresentenceInvestigationRequestService
		presentenceInvestigationRequestService;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestPropertyEditorFactory")
	private PropertyEditorFactory
				presentenceInvestigationRequestPropertyEditorFactory;
	
	@Autowired
	@Qualifier("userAccountPropertyEditorFactory")
	private PropertyEditorFactory userAccountPropertyEditorFactory;
	

	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("presentenceInvestigationRequestSearchFormValidator")
	private PresentenceInvestigationRequestSearchFormValidator
				presentenceInvestigationRequestSearchFormValidator;
	
	/* Constructor. */
	
	/**
	 * Instantiates a default instance of report presentence investigation
	 * request controller.
	 */
	public ReportPresentenceInvestigationRequestController() {
		//Default constructor.
	}
	
	/**
	 * Returns the list view for presentence investigation request summaries.
	 * 
	 * @param assignedUser - userAccount
	 * @param offender - offender
	 * @return model and view for presentence investigation request summaries
	 */
	@RequestMapping(value = "list.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or "
			+ "hasRole('PRESENTENCE_INVESTIGATION_REQUEST_LIST')")
	public ModelAndView list(
			@RequestParam(value = "assignedUser", required = false)
				final UserAccount assignedUser,
			@RequestParam(value = "offender", required = false)
				final Person offender) {
		ModelMap map = new ModelMap();
		if (offender == null && assignedUser != null) {
			PresentenceInvestigationRequestSearchForm form =
					new PresentenceInvestigationRequestSearchForm();
			form.setUserSearch(true);
			map.addAttribute(FORM_MODEL_KEY, form);
			map.addAttribute(ASSIGNED_USER_MODEL_KEY, assignedUser);
			map.addAttribute(UNSUBMITTED_SUMMARIES_MODEL_KEY, this
				.presentenceInvestigationRequestReportService
				.findUnsubmittedPresentenceInvestigationRequestSummariesByUser(
							assignedUser, null, null));
			map.addAttribute(SUBMITTED_SUMMARIES_MODEL_KEY, this
				.presentenceInvestigationRequestReportService
				.findSubmittedPresentenceInvestigationRequestSummariesByUser(
							assignedUser, null, null));
		} else if (offender != null) {
			if (this.presentenceInvestigationRequestService
					.isOffender(offender)) {
				this.offenderSummaryModelDelegate.add(map,
						(Offender) offender);
			}
			map.addAttribute(OFFENDER_MODEL_KEY, offender);
			map.addAttribute(SUMMARIES_MODEL_KEY, this
					.presentenceInvestigationRequestReportService
					.findPresentenceInvestigationRequestSummariesByOffender(
							offender));
			
		} else if (offender == null && assignedUser == null) {
			PresentenceInvestigationRequestSearchForm form =
					new PresentenceInvestigationRequestSearchForm();
			form.setUserSearch(true);
			map.addAttribute(FORM_MODEL_KEY, form);
			UserAccount user = this.retrieveUserAccount();
			map.addAttribute(UNSUBMITTED_SUMMARIES_MODEL_KEY, this
				.presentenceInvestigationRequestReportService
				.findUnsubmittedPresentenceInvestigationRequestSummariesByUser(
							user, null, null));
			map.addAttribute(SUBMITTED_SUMMARIES_MODEL_KEY, this
				.presentenceInvestigationRequestReportService
				.findSubmittedPresentenceInvestigationRequestSummariesByUser(
							user, null, null));
			map.addAttribute(ASSIGNED_USER_MODEL_KEY, user);
		}
		
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/**
	 * Displays a filtered list of Presentence Investigation Requests.
	 * 
	 * @param form - Presentence Investigation Request Form
	 * @param bindingResult - Binding Result
	 * @return Model and View for the filtered list of Presentence Investigation
	 * Requests.
	 */
	@RequestMapping(value = "list.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('ADMIN') or "
			+ "hasRole('PRESENTENCE_INVESTIGATION_REQUEST_LIST')")
	public ModelAndView updateList(
			final PresentenceInvestigationRequestSearchForm form,
			final BindingResult bindingResult) {
		ModelMap map = new ModelMap();
		
		if (form.getUserSearch()) {
			UserAccount user;
			if (form.getUserAccount() == null) {
				user = this.retrieveUserAccount();
			} else {
				user = form.getUserAccount();
			}
			map.addAttribute(ASSIGNED_USER_MODEL_KEY, user);
			map.addAttribute(UNSUBMITTED_SUMMARIES_MODEL_KEY, this
				.presentenceInvestigationRequestReportService
				.findUnsubmittedPresentenceInvestigationRequestSummariesByUser(
						user, form.getStartDate(),
						form.getEndDate()));
			map.addAttribute(SUBMITTED_SUMMARIES_MODEL_KEY, this
				.presentenceInvestigationRequestReportService
				.findSubmittedPresentenceInvestigationRequestSummariesByUser(
						user, form.getStartDate(),
						form.getEndDate()));
		} else {
			map.addAttribute(ASSIGNED_USER_MODEL_KEY,
					this.retrieveUserAccount());
			map.addAttribute(UNSUBMITTED_SUMMARIES_MODEL_KEY, this
				.presentenceInvestigationRequestReportService
				.findUnsubmittedPresentenceInvestigationRequestSummariesByName(
							form.getFirstName(), form.getLastName(),
							form.getStartDate(), form.getEndDate()));
			map.addAttribute(SUBMITTED_SUMMARIES_MODEL_KEY, this
				.presentenceInvestigationRequestReportService
				.findSubmittedPresentenceInvestigationRequestSummariesByName(
						form.getFirstName(), form.getLastName(),
						form.getStartDate(), form.getEndDate()));
		}
		map.addAttribute(FORM_MODEL_KEY, form);
		
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/**
	 * Displays the presentence investigation requests action menu.
	 * 
	 * @param assignedUser - userAccount
	 * @param offender - offender
	 * @param onReturn - String, used to evaluate which list screen to return to
	 * @return model and view for presentence investigation requests action menu
	 */
	@RequestMapping(value = "/presentenceInvestigationRequestsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayInvestigationRequestsActionMenu(
			@RequestParam(value = "assignedUser", required = false)
				final UserAccount assignedUser, 
			@RequestParam(value = "offender", required = false)
				final Person offender,
			@RequestParam(value = "onReturn", required = true) 
				final String onReturn) {
		ModelMap map = new ModelMap();
		map.addAttribute(ASSIGNED_USER_MODEL_KEY, assignedUser);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(ON_RETURN_MODEL_KEY, onReturn);
		return new ModelAndView(
				INVESTIGATION_REQUESTS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the presentence investigation requests row action menu.
	 * 
	 * @param presentenceInvestigationRequest presentence Investigation Request
	 * @param onReturn - String, used to evaluate which list screen to return to
	 * @return model and view for presentence investigation requests row action 
	 * menu
	 */
	@RequestMapping(value="/presentenceInvestigationRequestsRowActionMenu.html",
			method=RequestMethod.GET)
	public ModelAndView displayInvestigationRequestsRowActionMenu(
		@RequestParam(value = "presentenceInvestigationRequest", 
			required = true)
		final PresentenceInvestigationRequest presentenceInvestigationRequest, 
		@RequestParam(value = "onReturn", required = true) 
		final String onReturn) {
			ModelMap map = new ModelMap();
			map.addAttribute(PRESENTENCE_INVESTIGATION_REQUEST_MODEL_KEY, 
						presentenceInvestigationRequest);
			map.addAttribute(ON_RETURN_MODEL_KEY, onReturn);
			return new ModelAndView(
					INVESTIGATION_REQUESTS_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Retrieves the current users account.
	 *
	 *
	 * @return current user
	 */
	private UserAccount retrieveUserAccount() {
		UserAccount userAccount = (UserAccount) RequestContextHolder
				.getRequestAttributes()
					.getAttribute(USER_ACCOUNT_MODEL_KEY,
						RequestAttributes.SCOPE_REQUEST);
		if (userAccount == null) {
			String username = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			userAccount = 
					this.presentenceInvestigationRequestService
					.findUserAccountByUsername(username);
			RequestContextHolder.getRequestAttributes()
				.setAttribute(USER_ACCOUNT_MODEL_KEY, userAccount,
						RequestAttributes.SCOPE_REQUEST);
		}
		return userAccount;
	}
	/**
	 * 
	 * @param offender
	 * @param reportFormat
	 * @return
	 */
	@RequestMapping(value = "/investigationRequestDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PRESENTENCE_INVESTIGATION_REQUEST_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportInvestigationRequestDetails(@RequestParam(
			value = "presentenceInvestigationRequest", required = true)
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(INVESTIGATION_REQUEST_ID_REPORT_PARAM_NAME,
				Long.toString(presentenceInvestigationRequest.getId()));
		byte[] doc = this.reportRunner.runReport(
				INVESTIGATION_REQUEST_DETAIL_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * returns the affidavit of victim's pecuniary loss report for the specified request
	 * @param presentenceInvestigationRequest presentenceInvestigationRequest
	 * @param reportFormat
	 * @return
	 */
	@RequestMapping(value = "/affidavitOfPecuniaryLossReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PRESENTENCE_INVESTIGATION_REQUEST_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportAffidavitOfPecuniaryLoss(@RequestParam(
			value = "presentenceInvestigationRequest", required = true)
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(INVESTIGATION_REQUEST_ID_REPORT_PARAM_NAME,
				Long.toString(presentenceInvestigationRequest.getId()));
		byte[] doc = this.reportRunner.runReport(
				AFFIDAVIT_OF_PECUNIARY_LOSS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/** returns the psi requests for the specified offender
	 * 
	 * @param offender Offender
	 * @param reportFormat
	 * @return
	 */
	@RequestMapping(value = "/pSIRequestListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PRESENTENCE_INVESTIGATION_REQUEST_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportPSIRequestListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(PSI_REQUEST_LIST_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				PSI_REQUEST_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/** returns the details for the specified psi request
	 * 
	 * @param offender
	 * @param reportFormat
	 * @return
	 */
	@RequestMapping(value = "/psiRequestDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PRESENTENCE_INVESTIGATION_REQUEST_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportPSIRequestDetails(@RequestParam(
			value = "presentenceInvestigationRequest", required = true)
			final PresentenceInvestigationRequest presentenceInvestigationRequest,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(INVESTIGATION_REQUEST_ID_REPORT_PARAM_NAME,
				Long.toString(presentenceInvestigationRequest.getId()));
		byte[] doc = this.reportRunner.runReport(
				PSI_REQUEST_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * returns the psi questionnaire for the selected person
	 * @param person
	 * @param reportFormat
	 * @return
	 */
	@RequestMapping(value = "/psiQuestionnaireReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PRESENTENCE_INVESTIGATION_REQUEST_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportPSIQuestionnaire(@RequestParam(
			value = "person", required = true)
			final Person person,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(PSI_QUESTIONNAIRE_ID_REPORT_PARAM_NAME,
				Long.toString(person.getId()));
		byte[] doc = this.reportRunner.runReport(
				PSI_QUESTIONNAIRE_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the Assignment report for the logged in user
	 * @param retrieveUserAccount
	 * @param reportFormat
	 * @return
	 */
	@RequestMapping(value = "/investigationAssignmentReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PRESENTENCE_INVESTIGATION_REQUEST_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportInvestigationAssignment(@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(INVESTIGATION_ASSIGNMENT_ID_REPORT_PARAM_NAME,
				Long.toString(retrieveUserAccount().getId()));
		byte[] doc = this.reportRunner.runReport(
				INVESTIGATION_ASSIGNMENT_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	/**
	 * Returns the Progress report for the logged in user
	 * @param retrieveUserAccount
	 * @param reportFormat
	 * @return
	 */
	@RequestMapping(value = "/investigationProgressReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PRESENTENCE_INVESTIGATION_REQUEST_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportInvestigationProgress(@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(INVESTIGATION_PROGRESS_ID_REPORT_PARAM_NAME,
				Long.toString(retrieveUserAccount().getId()));
		byte[] doc = this.reportRunner.runReport(
				INVESTIGATION_PROGRESS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	/**
	 * Returns the Task report for the logged in user
	 * @param retrieveUserAccount
	 * @param reportFormat
	 * @return
	 */
	@RequestMapping(value = "/investigationTaskReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PRESENTENCE_INVESTIGATION_REQUEST_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportInvestigationTask(@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(INVESTIGATION_TASK_ID_REPORT_PARAM_NAME,
				Long.toString(retrieveUserAccount().getId()));
		byte[] doc = this.reportRunner.runReport(
				INVESTIGATION_TASK_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	/**
	 * Returns the victim impact statement
	 * @param reportFormat
	 * @return
	 */
	@RequestMapping(value = "/victimImpactStatementReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PRESENTENCE_INVESTIGATION_REQUEST_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportVictimImpactStatement(@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		byte[] doc = this.reportRunner.runReport(
				VICTIM_IMPACT_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	/**
	 * Returns the victim impact statement for kids
	 * @param reportFormat
	 * @return
	 */
	@RequestMapping(value = "/victimImpactStatementKidReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PRESENTENCE_INVESTIGATION_REQUEST_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportVictimImpactStatementKids(@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		byte[] doc = this.reportRunner.runReport(
				VICTIM_IMPACT_KID_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				UserAccount.class,
				this.userAccountPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(
				Person.class,
				this.personPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(PresentenceInvestigationRequest.class, 
				this.presentenceInvestigationRequestPropertyEditorFactory.
					createPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory
				.createCustomDateOnlyEditor(true));
	}
}