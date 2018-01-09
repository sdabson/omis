package omis.staff.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.organization.domain.OrganizationDivision;
import omis.staff.domain.StaffAssignment;
import omis.staff.domain.StaffTitle;
import omis.staff.report.StaffSearchResult;
import omis.staff.report.StaffSearchSummary;
import omis.staff.report.StaffSearchSummaryReportService;
import omis.staff.web.form.StaffSearchForm;
import omis.staff.web.validator.StaffSearchFormValidator;
import omis.supervision.domain.SupervisoryOrganization;


/** Search for staff related searches.
 * @author Ryan Johns
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 16, 2014)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/staffSearch")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class StaffSearchController {
	
	/* View names. */
	private static final String VIEW_NAME = "staff/editSearch";
	
	private static final String SEARCH_RESULTS_ACTION_MENU_VIEW_NAME
		= "staff/includes/searchResultsActionMenu";
	
	private static final String SEARCH_CRITERIA_ACTION_MENU_VIEW_NAME
		= "staff/includes/searchCriteriaActionMenu";
	
	/* Model keys. */
	private static final String STAFF_SEARCH_FORM_MODEL_KEY = "staffSearchForm";
	
	private static final String STAFF_SEARCH_SUMMARIES_MODEL_KEY 
		= "searchSummaries";
	
	private static final String ORGANIZATIONS_MODEL_KEY = "organizations";
	
	private static final String DIVISIONS_MODEL_KEY = "divisions";
	
	/* Validators. */
	
	@Autowired
	@Qualifier("staffSearchFormValidator")
	private StaffSearchFormValidator staffSearchFormValidator;
	
	/* Report services. */
	
	@Autowired
	@Qualifier("staffSearchSummaryReportServce")
	private StaffSearchSummaryReportService staffSearchSummaryReportService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("supervisoryOrganizationPropertyEditorFactory")
	private PropertyEditorFactory supervisoryOrganizationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("organizationDivisionPropertyEditorFactory")
	private PropertyEditorFactory organizationDivisionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("staffTitlePropertyEditorFactory")
	private PropertyEditorFactory staffTitlePropertyEditorFactory;
		
	/** returns list of staff given name search criteria.
	 * @param searchCriteria search criteria.
	 * @param date date on which the staff is active.
	 * @return collection of person search results.
	 * @throws IOException */
	@RequestMapping("/searchByNonSpecified.json")
	@ResponseBody
	public List<StaffSearchResult> searchByNonSpecified(
			@RequestParam(value = "searchCriteria", required = true)
				final String searchCriteria,
			@RequestParam(value = "date", required = false) final Date date)
					throws IOException {
		final Date myDate;
		if (date == null) {
			myDate = new Date();
		} else {
			myDate = date;
		}

		return this.staffSearchSummaryReportService
				.findPersonNamesByUnspecified(searchCriteria, myDate);
	}

	/** Returns JSON staff.
	 * @param staffAssignment staff assignment.
	 * @return staffAssignment. */
	@RequestMapping("staffAssignment.json")
	@ResponseBody
	public StaffSearchResult findStaffAssignmentById(
			@RequestParam(value = "staff", required = true) final
			StaffAssignment staffAssignment) {
		return this.staffSearchSummaryReportService.findById(staffAssignment
				.getId());
	}
	
	/** Returns current user staff assignment.
	 * 
	 * @return staffAssignment. */
	@RequestMapping("currentStaffAssignment.json")
	@ResponseBody
	public StaffSearchResult findCurrentUserStaffAssingmen() {
		return this.staffSearchSummaryReportService.findPersonNameByUsername(
		SecurityContextHolder.getContext().getAuthentication()
		.getName(), new Date());
	}
	
	/**
	 * Returns staff search form.
	 * 
	 * @return model and view of staff search form
	 */
	@RequestMapping(value = "/staffSearch.html", method = RequestMethod.GET)
	public ModelAndView searchStaff() {
		StaffSearchForm staffSearchForm = new StaffSearchForm();
		
		return this.prepareMav(staffSearchForm);
	}
	
	/**
	 * Returns the staff search results. 
	 * 
	 * @param staffSearchForm staff search form
	 * @param result result
	 * @return model and view
	 */
	@RequestMapping(value = "/staffSearch.html", method = RequestMethod.POST)
	public ModelAndView search(final StaffSearchForm staffSearchForm, 
			final BindingResult result) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		this.staffSearchFormValidator.validate(staffSearchForm, result);
		if (result.hasErrors()) {
			mav = this.prepareRedisplayMav(staffSearchForm, result);
			return mav;			
		}
		
		List<StaffSearchSummary> searchSummaries = 
				new ArrayList<StaffSearchSummary>();
		searchSummaries = this.staffSearchSummaryReportService.search(
				staffSearchForm.getFirstName(), 
				staffSearchForm.getLastName(),
				staffSearchForm.getWorkLocation(),
				staffSearchForm.getDivision());
		
		mav.addObject(STAFF_SEARCH_SUMMARIES_MODEL_KEY, searchSummaries);
		mav.addObject(ORGANIZATIONS_MODEL_KEY, 
				this.staffSearchSummaryReportService
				.findAllSupervisoryOrganizations());
		mav.addObject(DIVISIONS_MODEL_KEY, this.staffSearchSummaryReportService
				.findAllOrganizationDivisions());
		mav.addObject(STAFF_SEARCH_FORM_MODEL_KEY, staffSearchForm);
		return mav;
	}
	
	/**
	 * Search criteria action menu.
	 * 	
	 * @return model and view
	 */
	@RequestMapping("/searchCriteriaActionMenu.html")
	public ModelAndView showSearchCriteriaActionMenu() {
		ModelAndView mav = new ModelAndView(
				SEARCH_CRITERIA_ACTION_MENU_VIEW_NAME);	
		return mav;
	}
	
	/**
	 * Search results action menu.
	 * 
	 * @return model and view
	 */
	@RequestMapping("/searchResultsActionMenu.html")
	public ModelAndView showSearchResultsActionMenu() {
		ModelAndView mav = new ModelAndView(
				SEARCH_RESULTS_ACTION_MENU_VIEW_NAME);		
		return mav;	
	}	
	
	/* Helper methods. */
	
	// Returns model and view to search for staff
	private ModelAndView prepareMav(final StaffSearchForm staffSearchForm) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(STAFF_SEARCH_FORM_MODEL_KEY, staffSearchForm);
		mav.addObject(ORGANIZATIONS_MODEL_KEY, 
				this.staffSearchSummaryReportService
				.findAllSupervisoryOrganizations());
		mav.addObject(DIVISIONS_MODEL_KEY, this.staffSearchSummaryReportService
				.findAllOrganizationDivisions());

		return mav;
	}
	
	//Returns model and view of redisplayed staff search
	private ModelAndView prepareRedisplayMav(final StaffSearchForm form,
			final BindingResult result) {
		ModelAndView mav = this.prepareMav(form);
		mav.addObject(BindingResult.MODEL_KEY_PREFIX
				+ STAFF_SEARCH_FORM_MODEL_KEY, result);
		return mav;
	}
	
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(SupervisoryOrganization.class, 
				this.supervisoryOrganizationPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(StaffTitle.class,
				this.staffTitlePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(OrganizationDivision.class, 
				this.organizationDivisionPropertyEditorFactory
				.createPropertyEditor());
	}
}