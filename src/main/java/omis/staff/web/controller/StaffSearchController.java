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
package omis.staff.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.organization.domain.OrganizationDivision;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
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
 * @author Sierra Haynes
 * @version 0.1.0 (Jul 16, 2014)
 * @since OMIS 3.0 */
@Controller
@RequestMapping("/staffSearch")
@PreAuthorize("hasRole('USER')")
public class StaffSearchController {
	
	/* View names. */
	private static final String VIEW_NAME = "staff/editSearch";
	
	private static final String SEARCH_RESULTS_ACTION_MENU_VIEW_NAME
		= "staff/includes/searchResultsActionMenu";
	
	private static final String SEARCH_RESULTS_ROW_ACTION_MENU_VIEW_NAME
		= "staff/includes/searchResultsRowActionMenu";	
	
	private static final String SEARCH_CRITERIA_ACTION_MENU_VIEW_NAME
		= "staff/includes/searchCriteriaActionMenu";
	
	/* Model keys. */
	private static final String STAFF_SEARCH_FORM_MODEL_KEY = "staffSearchForm";
	
	private static final String STAFF_SEARCH_SUMMARIES_MODEL_KEY 
		= "searchSummaries";
	
	private static final String STAFF_ASSIGNMENT_MODEL_KEY = "staffAssignment";
	
	private static final String ORGANIZATIONS_MODEL_KEY = "organizations";
	
	private static final String DIVISIONS_MODEL_KEY = "divisions";
	
	/* Report names. */
	
	private static final String STAFF_ASSIGNMENT_DETAILS_REPORT_NAME
		= "/StaffAssignments/Staff_Assignment_Details"; 
	
	/* Report parameter names. */
	
	private static final String STAFF_ASSIGNMENT_DETAILS_ID_REPORT_PARAM_NAME
		= "STAFF_ASSIGN_ID"; 	
	
	/* Validators. */
	
	@Autowired
	@Qualifier("staffSearchFormValidator")
	private StaffSearchFormValidator staffSearchFormValidator;
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
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
	
	@Autowired
	@Qualifier("staffAssignmentPropertyEditorFactory")
	private PropertyEditorFactory staffAssignmentPropertyEditorFactory;
		
	/** returns list of staff given name search criteria.
	 * @param searchCriteria search criteria.
	 * @param date date on which the staff is active.
	 * @return collection of person search results. */
	@RequestMapping("/searchByNonSpecified.json")
	@ResponseBody
	public List<StaffSearchResult> searchByNonSpecified(
			@RequestParam(value = "searchCriteria", required = true)
				final String searchCriteria,
			@RequestParam(value = "date", required = false) final Date date) {
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
	
	/**
	 * Search results row action menu.
	 * 
	 * @param staffAssignment staff assignment
	 * @return model and view
	 */
	@RequestMapping("/searchResultsRowActionMenu.html")
	public ModelAndView showSearchResultsRowActionmenu(
			@RequestParam(value = "staffAssignment", required = true)
			final StaffAssignment staffAssignment) {		
		ModelMap map = new ModelMap();
		map.addAttribute(STAFF_ASSIGNMENT_MODEL_KEY, staffAssignment);
		return new ModelAndView(
				SEARCH_RESULTS_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the report for the specified staff assignment.
	 * 
	 * @param staffAssignment staff assignment
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/staffAssignmentDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('STAFF_ASSIGNMENT_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportStaffAssignmentDetails(@RequestParam(
			value = "staffAssignment", required = true)
			final StaffAssignment staffAssignment,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(STAFF_ASSIGNMENT_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(staffAssignment.getId()));
		byte[] doc = this.reportRunner.runReport(
				STAFF_ASSIGNMENT_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
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
		binder.registerCustomEditor(StaffAssignment.class, 
				this.staffAssignmentPropertyEditorFactory 
				.createPropertyEditor());
	}
}