package omis.caseload.web.controller;

import java.util.Date;
import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.caseload.domain.CaseWorkerAssignment;
import omis.caseload.report.CaseloadReportService;
import omis.caseload.report.CaseworkSummary;
import omis.caseload.report.OffenderCaseAssignmentSummary;
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.person.domain.Person;
import omis.user.domain.UserAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

/** Report controller for caseload.
 * @author Ryan Johns
 * @version 0.1.0 (Jun 12, 2017)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/caseload")
@PreAuthorize("hasRole('USER')")
public class ReportCaseloadController {
	/* Redirects. */
	/*private static final String CASELOAD_EDIT_REDIRECT
					= "redirect:/caseload/create.html?caseWorker=%1d";*/
	/* View names. */
	private static final String CASELOAD_LIST_VIEW_NAME = "caseload/list";
	private static final String CASELOADS_ACTION_MENU_VIEW_NAME 
		= "caseload/includes/caseloadsActionMenu";
	private static final String CASELOAD_ROW_ACTION_MENU_VIEW_NAME 
					= "caseload/includes/caseloadRowActionMenu";
	
	/* Model Keys. */
	private static final String CASELOAD_MODEL_KEY = "caseload";
	
	private static final String CASE_WORKER_SUMMARIES_MODEL_KEY  
					= "caseWorkSummaries";
	private static final String OFFICER_CASE_ASSIGNMENT_MODEL_KEY
					= "workerAssignment";
	private static final String OFFENDER_ASSIGNMENT_SUMMARIES_MODEL_KEY 
					= "offenderSummaries";
	private static final String EFFECTIVE_DATE_MODEL_KEY = "effectiveDate";
	private static final String USER_ACCOUNT_MODEL_KEY
					= "userAccount";
	
	/* Services */
	@Autowired
	private CaseloadReportService caseloadReportService;
	
	/* Property editor factories. */
	@Autowired
	private PropertyEditorFactory caseWorkerAssignmentPropertyEditorFactory;
	
	@Autowired	
	private CustomDateEditorFactory customDateEditorFactory;
	/* Delegates */
	
	/** Returns model and view of list of caseload summaries.
	 * @return model and view list */
	@RequestContentMapping(nameKey = "caseloadCaseworkerListingScreenName",
					descriptionKey 
						= "caseloadCaseworkerListingScreenDescription",
					messageBundle = "omis.caseload.msgs.caseload",
					screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ADMIN') or hasRole('CASELOAD_LIST')")
	public ModelAndView list() {		
		Person currentWorker = this.caseloadReportService
						.findUserAccountByUsername(
								SecurityContextHolder.getContext()
								.getAuthentication().getName()).getUser();
		Date effectiveDate = new Date();
		final ModelAndView mav;
		
		mav = new ModelAndView(CASELOAD_LIST_VIEW_NAME);
		//Caseload caseload = caseWorkerAssignment.getCaseload();	
		mav.addObject(CASE_WORKER_SUMMARIES_MODEL_KEY, 
						this.caseloadReportService
						.findByCaseWorkerAndDate(currentWorker, 
								new Date()));
		
		mav.addObject(EFFECTIVE_DATE_MODEL_KEY, effectiveDate);
		
		return mav;
	}
	
	/** Returns model and view of caseload row action menu.
	 * @param workerAssignment - worker assignment.
	 * @return model and view */
	@RequestMapping(value = "/caseloadRowActionMenu.html", 
					method = RequestMethod.GET)
	public ModelAndView caseloadRowActionMenu(
					@RequestParam(value = "workerAssignment", required = true)
					final CaseWorkerAssignment workerAssignment) {
		Date effectiveDate = new Date();
		ModelMap map = new ModelMap();
		List<CaseworkSummary> summaries = 
						this.caseloadReportService
							.findCaseWorkerSummariesByCase(
						workerAssignment.getCaseload(), effectiveDate);
		if (summaries.size() > 0) {
			map.addAttribute(CASE_WORKER_SUMMARIES_MODEL_KEY, summaries);
		}
		List<OffenderCaseAssignmentSummary> offenderSummaries = 
						this.caseloadReportService
						.findOffenderCaseAssignmentSummariesByCaseloadAndDate(
						workerAssignment.getCaseload(), effectiveDate);
		if (offenderSummaries.size() > 0) {
			map.addAttribute(OFFENDER_ASSIGNMENT_SUMMARIES_MODEL_KEY, 
							offenderSummaries);
		}
		map.addAttribute(CASELOAD_MODEL_KEY, workerAssignment.getCaseload());
		map.addAttribute(OFFICER_CASE_ASSIGNMENT_MODEL_KEY, workerAssignment);
		return new ModelAndView(CASELOAD_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/** Returns model and view of caseloads action menu.
	 * @return model and view. */
	@RequestMapping(value = "caseloadsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView caseloadsActionMenu() {
		ModelMap map = new ModelMap();
		final ModelAndView mav;
		final UserAccount currentUser = this.retrieveUserAccount();
		map.put(OFFICER_CASE_ASSIGNMENT_MODEL_KEY, this.caseloadReportService
				.findByCaseWorkerAndDate(currentUser.getUser(), new Date()));
		map.put(USER_ACCOUNT_MODEL_KEY, currentUser);
		mav = new ModelAndView(CASELOADS_ACTION_MENU_VIEW_NAME, map);
		return mav;
	}
	
	/* Init binder. */
	
	/** Init binder.
	 * @param binder web data binder. */
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(CaseWorkerAssignment.class, 
						this.caseWorkerAssignmentPropertyEditorFactory
						.createPropertyEditor());
		binder.registerCustomEditor(Date.class, 
						this.customDateEditorFactory
							.createCustomDateOnlyEditor(true));
	}
	
	
	/** Retrieves the current users account.
	 * @return current user */
	private UserAccount retrieveUserAccount() {
		UserAccount userAccount = (UserAccount) RequestContextHolder
						.getRequestAttributes()
						.getAttribute(USER_ACCOUNT_MODEL_KEY,
						RequestAttributes.SCOPE_REQUEST);
		if (userAccount == null) {
			String username = SecurityContextHolder.getContext()
							.getAuthentication().getName();
			userAccount = 
					this.caseloadReportService.findUserAccountByUsername(
							username);
			RequestContextHolder.getRequestAttributes()
							.setAttribute(USER_ACCOUNT_MODEL_KEY, userAccount,
									RequestAttributes.SCOPE_REQUEST);
		}
		return userAccount;
	}
}
