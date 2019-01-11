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
package omis.paroleeligibility.web.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.paroleeligibility.domain.ParoleEligibility;
import omis.paroleeligibility.report.ParoleEligibilityReportService;
import omis.paroleeligibility.report.ParoleEligibilitySummary;
import omis.paroleeligibility.web.form.ParoleEligibilitySearchForm;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;

/**
 * Controller to report parole eligibilities.
 *
 * @author Trevor Isles
 * @author Josh Divine
 * @author Annie Wahl
 * @author Sierra Rosales
 * @version 0.1.3 (May 31, 2018)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/paroleEligibility")
@PreAuthorize("hasRole('USER')")
public class ReportParoleEligibilityController {
	
	/* View names. */
	
	private static final String LIST_VIEW_NAME = "paroleEligibility/list";
	
	private static final String LIST_UNRESOLVED_VIEW_NAME = 
			"paroleEligibility/listUnresolved";
	
	private static final String LIST_UNSCHEDULED_VIEW_NAME =
			"paroleEligibility/listUnscheduled";
	
	/* Action menu view names. */
	
	private static final String ELIGIBILITIES_ACTION_MENU_VIEW_NAME = 
			"paroleEligibility/includes/eligibilitiesActionMenu";
	
	private static final String
		UNRESOLVED_ELIGIBILITIES_ROW_ACTION_MENU_VIEW_NAME =
			"paroleEligibility/includes/unresolvedEligibilitiesRowActionMenu";
	
	private static final String
		UNRESOLVED_ELIGIBILITIES_ACTION_MENU_VIEW_NAME =
			"paroleEligibility/includes/unresolvedEligibilitiesActionMenu";
	
	private static final String
		UNSCHEDULED_ELIGIBILITIES_ROW_ACTION_MENU_VIEW_NAME =
			"paroleEligibility/includes/unresolvedEligibilitiesRowActionMenu";

	private static final String
		UNSCHEDULED_ELIGIBILITIES_ACTION_MENU_VIEW_NAME =
			"paroleEligibility/includes/unresolvedEligibilitiesActionMenu";
	
	/* Model keys. */
	
	private static final String PAROLE_ELIGIBILITY_SUMMARIES_MODEL_KEY
		= "paroleEligibilitySummaries";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String ELIGIBILITY_MODEL_KEY = "eligibility";
	
	private static final String HEARING_ANALYSIS_MODEL_KEY = "hearingAnalysis";
	
	private static final String BOARD_HEARING_MODEL_KEY = "boardHearing";
	
	private static final String FORM_MODEL_KEY = "paroleEligibilitySearchForm";
		
	/* Services. */
	
	@Autowired
	@Qualifier("paroleEligibilityReportService")
	private ParoleEligibilityReportService paroleEligibilityReportService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("paroleEligibilityPropertyEditorFactory")
	private PropertyEditorFactory paroleEligibilityPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Report names. */
	
	private static final String PAROLE_ELIGIBILITY_LISTING_REPORT_NAME = 
			"/BOPP/ParoleEligibility/Parole_Eligibility_Listing"; 
	
	private static final String PAROLE_ELIGIBILITY_DETAIL_REPORT_NAME = 
			"/BOPP/ParoleEligibility/Parole_Eligibility_Details"; 
	
	/* Report parameter names. */
	
	private static final String DOC_ID_REPORT_PARAM_NAME = "DOC_ID";
	
	private static final String PAROLE_ELIGIBILITY_DETAIL_REPORT_PARAM_NAME = 
			"ELIGIBILITY_ID";	
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Constructor. */
	
	/**
	 * Instantiation controller to report parole eligibilities.
	 */
	public ReportParoleEligibilityController() {
		// Default instantiation
	}
	
	/**
	 * Displays a list of parole eligibility by offender.
	 * 
	 * @param offender offender
	 * @return screen to display list of parole eligibilities by offender
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PAROLE_ELIGIBILITY_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		List<ParoleEligibilitySummary> paroleEligibilitySummaries
			= this.paroleEligibilityReportService.findByOffender(offender);
		mav.addObject(PAROLE_ELIGIBILITY_SUMMARIES_MODEL_KEY, 
				paroleEligibilitySummaries);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/**
	 * Displays a list of unresolved parole eligibilities.
	 * 
	 * @param date date
	 * @param startDate start date
	 * @param endDate end date
	 * 
	 * @return screen to display list of unresolved parole eligibilities
	 */
	@RequestMapping(value = "/listUnresolved.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PAROLE_ELIGIBILITY_LIST') or hasRole('ADMIN')")
	public ModelAndView listUnresolved(
			@RequestParam(value = "date", required = false)
				final Date date,
			@RequestParam(value = "startDate", required = false)
				final Date startDate,
			@RequestParam(value = "endDate", required = false)
				final Date endDate) {
		ModelAndView mav = new ModelAndView(LIST_UNRESOLVED_VIEW_NAME);
		List<ParoleEligibilitySummary> paroleEligibilitySummaries;
		ParoleEligibilitySearchForm form = new ParoleEligibilitySearchForm();
		if (date != null) {
			form.setDate(date);
			form.setSingleDate(false);
			paroleEligibilitySummaries = this
					.paroleEligibilityReportService
					.findUnresolvedEligibilitySummariesByDate(date);
		} else if (startDate != null || endDate != null) {
			form.setStartDate(startDate);
			form.setEndDate(endDate);
			form.setSingleDate(false);
			paroleEligibilitySummaries = this
					.paroleEligibilityReportService
					.findUnresolvedEligibilitySummariesByDateRange(
							startDate, endDate);
		} else {
			form.setSingleDate(false);
			Calendar futureCal = Calendar.getInstance();
			futureCal.add(Calendar.MONTH, 1);
			
			paroleEligibilitySummaries = this
					.paroleEligibilityReportService
					.findUnresolvedEligibilitySummariesByDateRange(
							null, futureCal.getTime());
		}
		mav.addObject(FORM_MODEL_KEY, form);
		mav.addObject(PAROLE_ELIGIBILITY_SUMMARIES_MODEL_KEY, 
				paroleEligibilitySummaries);
		return mav;
	}
	
	/**
	 * Displays a list of unscheduled parole eligibilities.
	 * 
	 * @param date date
	 * @param startDate start date
	 * @param endDate end date
	 * 
	 * @return screen to display list of unscheduled parole eligibilities
	 */
	@RequestMapping(value = "/listUnscheduled.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('PAROLE_ELIGIBILITY_LIST') or hasRole('ADMIN')")
	public ModelAndView listUnscheduled(
			@RequestParam(value = "date", required = false)
				final Date date,
			@RequestParam(value = "startDate", required = false)
				final Date startDate,
			@RequestParam(value = "endDate", required = false)
				final Date endDate) {
		ModelAndView mav = new ModelAndView(LIST_UNSCHEDULED_VIEW_NAME);
		List<ParoleEligibilitySummary> paroleEligibilitySummaries;
		ParoleEligibilitySearchForm form = new ParoleEligibilitySearchForm();
		if (date != null) {
			form.setDate(date);
			form.setSingleDate(false);
			paroleEligibilitySummaries = this
					.paroleEligibilityReportService
					.findUnscheduledEligibilitySummariesByDate(date);
		} else if (startDate != null || endDate != null) {
			form.setStartDate(startDate);
			form.setEndDate(endDate);
			form.setSingleDate(false);
			paroleEligibilitySummaries = this
					.paroleEligibilityReportService
					.findUnscheduledEligibilitySummariesByDateRange(
							startDate, endDate);
		} else {
			form.setSingleDate(false);
			Calendar futureCal = Calendar.getInstance();
			futureCal.add(Calendar.MONTH, 1);
			paroleEligibilitySummaries = this
					.paroleEligibilityReportService
					.findUnscheduledEligibilitySummariesByDateRange(
							null, futureCal.getTime());
		}
		mav.addObject(FORM_MODEL_KEY, form);
		mav.addObject(PAROLE_ELIGIBILITY_SUMMARIES_MODEL_KEY, 
				paroleEligibilitySummaries);
		return mav;
	}
	
	/**
	 * Displays filtered list of unscheduled Parole Eligibilities.
	 * 
	 * @param form parole eligibility search form
	 * @param bindingResult binding result
	 * @return Model and view filtered list of unscheduled Parole Eligibilities.
	 */
	@RequestMapping(value = "/listUnscheduled.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('PAROLE_ELIGIBILITY_LIST') or hasRole('ADMIN')")
	public ModelAndView updateListUnscheduled(
			final ParoleEligibilitySearchForm form,
			final BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView(LIST_UNSCHEDULED_VIEW_NAME);
		List<ParoleEligibilitySummary> paroleEligibilitySummaries;
		if (form.getSingleDate()) {
			form.setStartDate(null);
			form.setEndDate(null);
			paroleEligibilitySummaries = this
					.paroleEligibilityReportService
					.findUnscheduledEligibilitySummariesByDate(form.getDate());
		} else {
			form.setDate(null);
			paroleEligibilitySummaries = this
					.paroleEligibilityReportService
					.findUnscheduledEligibilitySummariesByDateRange(
							form.getStartDate(), form.getEndDate());
		}
		mav.addObject(FORM_MODEL_KEY, form);
		mav.addObject(PAROLE_ELIGIBILITY_SUMMARIES_MODEL_KEY, 
				paroleEligibilitySummaries);
		return mav;
	}
	
	/**
	 * Displays filtered list of unresolved Parole Eligibilities.
	 * 
	 * @param form parole eligibility search form
	 * @param bindingResult binding result
	 * @return Model and view filtered list of unresolved Parole Eligibilities.
	 */
	@RequestMapping(value = "/listUnresolved.html",
			method = RequestMethod.POST)
	@PreAuthorize("hasRole('PAROLE_ELIGIBILITY_LIST') or hasRole('ADMIN')")
	public ModelAndView updateListUnresolved(
			final ParoleEligibilitySearchForm form,
			final BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView(LIST_UNRESOLVED_VIEW_NAME);
		List<ParoleEligibilitySummary> paroleEligibilitySummaries;
		if (form.getSingleDate()) {
			form.setStartDate(null);
			form.setEndDate(null);
			paroleEligibilitySummaries = this
					.paroleEligibilityReportService
					.findUnresolvedEligibilitySummariesByDate(form.getDate());
		} else {
			form.setDate(null);
			paroleEligibilitySummaries = this
					.paroleEligibilityReportService
					.findUnresolvedEligibilitySummariesByDateRange(
							form.getStartDate(), form.getEndDate());
		}
		mav.addObject(FORM_MODEL_KEY, form);
		mav.addObject(PAROLE_ELIGIBILITY_SUMMARIES_MODEL_KEY, 
				paroleEligibilitySummaries);
		return mav;
	}
	
	/**
	 * Returns the report for the specified offenders parole eligibility.
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/paroleEligibilityListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PAROLE_ELIGIBILITY_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportParoleEligibilityListing(
			@RequestParam(value = "offender", required = true) 
			final Offender offender, 
			@RequestParam(value = "reportFormat", required = true) 
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DOC_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				PAROLE_ELIGIBILITY_LISTING_REPORT_NAME, reportParamMap, 
				reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(doc, 
				reportFormat);
	}
	
	/**
	 * Returns the report for the specified parole eligibility.
	 * @param eligibility eligibility
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/paroleEligibilityDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PAROLE_ELIGIBILITY_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportParoleEligibilityDetails(
			@RequestParam(value = "eligibility", required = true) 
			final ParoleEligibility eligibility, 
			@RequestParam(value = "reportFormat", required = true) 
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(PAROLE_ELIGIBILITY_DETAIL_REPORT_PARAM_NAME,
				Long.toString(eligibility.getId()));
		byte[] doc = this.reportRunner.runReport(
				PAROLE_ELIGIBILITY_DETAIL_REPORT_NAME, reportParamMap, 
				reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(doc, 
				reportFormat);
	}
	
	/* Action menus. */
	
	/**
	 * Returns eligibilities action menu.
	 * 
	 * @param offender offender
	 * @param eligibility parole eligibility
	 * @return eligibilities action menu
	 */
	@RequestMapping(
			value = "/eligibilitiesActionMenu.html", method = RequestMethod.GET)
	public ModelAndView showEligibilitiesActionMenu(
			@RequestParam(value = "offender", required = false)
				final Offender offender,
			@RequestParam(value = "eligibility", required = false)
				final ParoleEligibility eligibility) {
		ModelAndView mav = new ModelAndView(
				ELIGIBILITIES_ACTION_MENU_VIEW_NAME);
		if (offender != null) {
			mav.addObject(OFFENDER_MODEL_KEY, offender);
		}
		if (eligibility != null) {
			mav.addObject(ELIGIBILITY_MODEL_KEY, eligibility);
			mav.addObject(HEARING_ANALYSIS_MODEL_KEY, 
					this.paroleEligibilityReportService
					.findHearingAnalysisByParoleEligibility(eligibility));
			mav.addObject(BOARD_HEARING_MODEL_KEY, 
					this.paroleEligibilityReportService
					.findBoardHearingByParoleEligibility(eligibility));
		}			
		return mav;
	}
	
	/**
	 * Returns eligibilities row action menu.
	 *
	 * @param eligibility parole eligibility
	 * @return eligibilities action menu
	 */
	@RequestMapping(value = "/unresolvedEligibilitiesRowActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView showUnresolvedEligibilitiesRowActionMenu(
			@RequestParam(value = "eligibility", required = true)
				final ParoleEligibility eligibility) {
		ModelAndView mav = new ModelAndView(
				UNRESOLVED_ELIGIBILITIES_ROW_ACTION_MENU_VIEW_NAME);
		mav.addObject(ELIGIBILITY_MODEL_KEY, eligibility);
		mav.addObject(HEARING_ANALYSIS_MODEL_KEY, this
				.paroleEligibilityReportService
				.findHearingAnalysisByParoleEligibility(eligibility));
		mav.addObject(BOARD_HEARING_MODEL_KEY, this
				.paroleEligibilityReportService
				.findBoardHearingByParoleEligibility(eligibility));
		return mav;
	}
	
	/**
	 * Returns unresolved eligibilities action menu.
	 *
	 * @return unresolved eligibilities action menu
	 */
	@RequestMapping(value = "/unresolvedEligibilitiesActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView showUnresolvedEligibilitiesActionMenu() {
		ModelAndView mav = new ModelAndView(
				UNRESOLVED_ELIGIBILITIES_ACTION_MENU_VIEW_NAME);
		return mav;
	}
	
	/**
	 * Returns unscheduled eligibilities row action menu.
	 *
	 * @param eligibility parole eligibility
	 * @return unscheduled eligibilities action menu
	 */
	@RequestMapping(value = "/unscheduledEligibilitiesRowActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView showUnscheduledEligibilitiesRowActionMenu(
			@RequestParam(value = "eligibility", required = true)
				final ParoleEligibility eligibility) {
		ModelAndView mav = new ModelAndView(
				UNSCHEDULED_ELIGIBILITIES_ROW_ACTION_MENU_VIEW_NAME);
		mav.addObject(ELIGIBILITY_MODEL_KEY, eligibility);
		mav.addObject(HEARING_ANALYSIS_MODEL_KEY, this
				.paroleEligibilityReportService
				.findHearingAnalysisByParoleEligibility(eligibility));
		mav.addObject(BOARD_HEARING_MODEL_KEY, this
				.paroleEligibilityReportService
				.findBoardHearingByParoleEligibility(eligibility));
		return mav;
	}
	
	/**
	 * Returns unscheduled eligibilities action menu.
	 *
	 * @return unscheduled eligibilities action menu
	 */
	@RequestMapping(value = "/unscheduledEligibilitiesActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView showUnscheduledEligibilitiesActionMenu() {
		ModelAndView mav = new ModelAndView(
				UNSCHEDULED_ELIGIBILITIES_ACTION_MENU_VIEW_NAME);
		return mav;
	}
	
	/* Init binder. */
	
	/**
	 * Registers custom editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(ParoleEligibility.class, 
				this.paroleEligibilityPropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Date.class,
							this.customDateEditorFactory
							.createCustomDateOnlyEditor(true));
	}
}