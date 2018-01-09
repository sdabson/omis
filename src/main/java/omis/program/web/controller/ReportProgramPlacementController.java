package omis.program.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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
import omis.program.domain.ProgramPlacement;
import omis.program.report.ProgramPlacementReportService;
import omis.program.report.ProgramPlacementSummary;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;

/**
 * Controller to report program placements.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/program")
@PreAuthorize("hasRole('USER')")
public class ReportProgramPlacementController {

	/* View names. */
	
	private static final String VIEW_NAME = "program/list";
	
	private static final String ACTION_MENU_VIEW_NAME
		= "program/includes/programPlacementsActionMenu";
	
	/* Model keys. */
	
	private static final String PROGRAM_PLACEMENT_SUMMARIES_MODEL_KEY
		= "programPlacementSummaries";

	private static final String PROGRAM_PLACEMENT_MODEL_KEY
		= "programPlacement";

	private static final String OFFENDER_MODEL_KEY = "offender";
	
	/* Report services. */
	
	@Autowired
	@Qualifier("programPlacementReportService")
	private ProgramPlacementReportService programPlacementReportService;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("programPlacementPropertyEditorFactory")
	private PropertyEditorFactory programPlacementPropertyEditorFactory;
	
/* Report names. */
	
	private static final String PROGRAM_PLACEMENT_LISTING_REPORT_NAME 
		= "/Placement/ProgramPlacements/Program_Placement_Listing";

	private static final String PROGRAM_PLACEMENT_DETAILS_REPORT_NAME 
		= "/Placement/ProgramPlacements/Program_Placement_Details";

	/* Report parameter names. */
	
	private static final String PROGRAM_PLACEMENT_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String PROGRAM_PLACEMENT_DETAILS_ID_REPORT_PARAM_NAME 
		= "PROGRAM_PLACEMENT_ID";

	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/* Constructors. */
	
	/** Instantiates controller to report program placements. */
	public ReportProgramPlacementController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */

	/**
	 * Screen to list program placements by offender.
	 * 
	 * @param offender offender
	 * @return screen to list program placements by offender
	 */
	@RequestMapping("/list.html")
	@PreAuthorize("hasRole('PROGRAM_PLACEMENT_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		Date effectiveDate = new Date();
		List<ProgramPlacementSummary> summaries
			= this.programPlacementReportService.summarizeByOffender(
					offender, effectiveDate);
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(PROGRAM_PLACEMENT_SUMMARIES_MODEL_KEY, summaries);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/* Action menus. */
	
	/**
	 * Shows program placements action menu.
	 * 
	 * @param offender offender
	 * @param programPlacement program placement
	 * @return program placements action menu
	 */
	@RequestMapping(
			value = "/programPlacementsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showProgramPlacementsActionMenu(
			@RequestParam(value = "offender", required = false)
				final Offender offender,
			@RequestParam(value = "programPlacement", required = false)
				final ProgramPlacement programPlacement) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		mav.addObject(PROGRAM_PLACEMENT_MODEL_KEY, programPlacement);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		return mav;
	}
	
	/* Reports. */
	
	/**
	 * Returns the report for the specified offenders program placements.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/programPlacementListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PROGRAM_PLACEMENT_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProgramPlacementListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(PROGRAM_PLACEMENT_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				PROGRAM_PLACEMENT_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified program placement.
	 * 
	 * @param programPlacement program placement
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/programPlacementDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PROGRAM_PLACEMENT_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportProgramPlacementDetails(@RequestParam(
			value = "programPlacement", required = true)
			final ProgramPlacement programPlacement,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(PROGRAM_PLACEMENT_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(programPlacement.getId()));
		byte[] doc = this.reportRunner.runReport(
				PROGRAM_PLACEMENT_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* Init Binders. */
	
	/**
	 * Registers custom editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void registerCustomEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(ProgramPlacement.class,
				this.programPlacementPropertyEditorFactory
					.createPropertyEditor());
	}
}