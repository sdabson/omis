package omis.workrestriction.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import omis.beans.factory.PropertyEditorFactory;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.workrestriction.domain.WorkRestriction;
import omis.workrestriction.report.WorkRestrictionReportService;
import omis.workrestriction.report.WorkRestrictionSummary;

/**
 * WorkRestrictionReportController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 17, 2016)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/workRestriction/")
@PreAuthorize("hasRole('USER')")
public class WorkRestrictionReportController {
	
	/* View Names */
	
	private static final String LIST_VIEW_NAME = "/workRestriction/list";
	
	private static final String WORK_RESTRICTIONS_ACTION_MENU_VIEW_NAME 
		= "/workRestriction/includes/workRestrictionsActionMenu";

	private static final String WORK_RESTRICTIONS_ROW_ACTION_MENU_VIEW_NAME 
		= "/workRestriction/includes/workRestrictionsRowActionMenu";
	
	/* Model Keys */
	
	private static final String WORK_RESTRICTION_SUMMARIES_MODEL_KEY
		= "summaries";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String WORK_RESTRICTION_MODEL_KEY = "workRestriction";
	
	/* Service */
	
	@Autowired
	@Qualifier("workRestrictionReportService")
	private WorkRestrictionReportService reportService;
	
	/* Property Editors */
	
	@Autowired
	@Qualifier("workRestrictionPropertyEditorFactory")
	private PropertyEditorFactory workRestrictionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/* Report names. */
	
	private static final String WORK_RESTRICTION_LISTING_REPORT_NAME 
		= "/Safety/WorkRestrictions/Work_Restrictions_Listing";

	private static final String WORK_RESTRICTION_DETAILS_REPORT_NAME 
		= "/Safety/WorkRestrictions/Work_Restrictions_Details";

	/* Report parameter names. */
	
	private static final String WORK_RESTRICTION_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String WORK_RESTRICTION_DETAILS_ID_REPORT_PARAM_NAME 
		= "WORK_RESTRICTION_ID";
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;

	/* Helpers */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Constructor */
	
	/**
	 * Default contructor for WorkRestrictionReportController
	 */
	public WorkRestrictionReportController() {
		//Nada
	}
	
	
	/* Model And Views */
	
	/**
	 * Displays a list of work restriction summaries by specified offender
	 * @param offender - Offender
	 * @return ModelAndView to display list of work restriction summaries
	 */
	@RequestMapping(value = "list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole ('WORK_RESTRICTION_LIST')")
	public ModelAndView list(@RequestParam(value="offender", required=true)
			final Offender offender){
		List<WorkRestrictionSummary> summaries 
			= this.reportService.summariesByOffender(offender);
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(WORK_RESTRICTION_SUMMARIES_MODEL_KEY, summaries);
		this.offenderSummaryModelDelegate.add(map, offender);
		
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/* Action Menu Views */
	
	/**
	 * Displays action menu for work restrictions
	 * @param offender - offender
	 * @return ModelAndView of action menu for work restrictions
	 */
	@RequestMapping(value="/workRestrictionsActionMenu.html", 
			method=RequestMethod.GET)
	public ModelAndView displayWorkRestrictionsActionMenu(
			@RequestParam(value="offender", required=true)
			final Offender offender){
		
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(WORK_RESTRICTIONS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays action menu for work restriction rows
	 * @param workRestriction - work restriction of current row
	 * @return ModelAndView for action menu for current work restriction row
	 */
	@RequestMapping(value="/workRestrictionsRowActionMenu.html", 
			method=RequestMethod.GET)
	public ModelAndView displayWorkRestrictionsRowActionMenu(
			@RequestParam(value = "workRestriction", 
			required = true) final WorkRestriction workRestriction){
		
		ModelMap map = new ModelMap();
		map.addAttribute(WORK_RESTRICTION_MODEL_KEY, workRestriction);
		
		
		return new ModelAndView(
				WORK_RESTRICTIONS_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the report for the specified offenders work restrictions.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/workRestrictionListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('WORK_RESTRICTION_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportWorkRestrictionsListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(WORK_RESTRICTION_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				WORK_RESTRICTION_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified security threat group affiliation.
	 * 
	 * @param workRestriction security threat group affiliation
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/workRestrictionDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('WORK_RESTRICTION_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportWorkRestrictionsDetails(@RequestParam(
			value = "workRestriction", required = true)
			final WorkRestriction workRestriction,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(WORK_RESTRICTION_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(workRestriction.getId()));
		byte[] doc = this.reportRunner.runReport(
				WORK_RESTRICTION_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* InitBinder */
	
	/**
	 * Sets up and registers property editors
	 * 
	 * @param binder - web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder){
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(WorkRestriction.class, 
				this.workRestrictionPropertyEditorFactory
				.createPropertyEditor());
	}
	
	
	
	
	
	
}
