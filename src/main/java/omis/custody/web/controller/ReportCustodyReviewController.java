package omis.custody.web.controller;

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
import omis.content.RequestContentMapping;
import omis.content.RequestContentType;
import omis.custody.domain.CustodyOverride;
import omis.custody.domain.CustodyReview;
import omis.custody.report.CustodyReportService;
import omis.custody.report.CustodySummary;
import omis.custody.service.CustodyReviewService;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;

/**
 * Report controller for custody review.
 * @author Josh Divine 
 * @author Joel Norris
 * @version 0.1.2 (June 7, 2017)
 * @since OMIS 3.0
 */

@Controller
@RequestMapping("/custody")
@PreAuthorize("hasRole('USER')")
public class ReportCustodyReviewController {

	/* View names. */
	
	private static final String LIST_VIEW_NAME = "custody/list";
	
	private static final String CUSTODY_REVIEWS_ACTION_MENU_VIEW_NAME
		= "custody/includes/custodyReviewsActionMenu";

	private static final String CUSTODY_REVIEWS_ROW_ACTION_MENU_VIEW_NAME
		= "custody/includes/custodyReviewsRowActionMenu";
	
	/* Model Keys. */
	
	private static final String CUSTODY_SUMMARIES_MODEL_KEY = 
			"custodySummaries";
	
	private static final String OFFENDER_NUMBER_MODEL_KEY = "offenderNumber";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String CUSTODY_REVIEW_MODEL_KEY = "custodyReview";
	
	private static final String CUSTODY_OVERRIDE_MODEL_KEY = "custodyOverride";
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Services */
	
	@Autowired
	@Qualifier("custodyReviewService")
	private CustodyReviewService custodyReviewService;

	@Autowired
	@Qualifier("custodyReportService")
	private CustodyReportService custodyReportService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;

	@Autowired
	@Qualifier("custodyReviewPropertyEditorFactory")
	private PropertyEditorFactory custodyReviewPropertyEditorFactory;	
	
	@Autowired
	@Qualifier("custodyOverridePropertyEditorFactory")
	private PropertyEditorFactory custodyOverridePropertyEditorFactory;
	
	/* Report names. */
	
	private static final String CUSTODY_REVIEW_LISTING_REPORT_NAME 
		= "/Placement/CustodyReviews/Custody_Review_Listing";

	private static final String CUSTODY_REVIEW_DETAILS_REPORT_NAME 
		= "/Placement/CustodyReviews/Custody_Review_Details";

	/* Report parameter names. */
	
	private static final String CUSTODY_REVIEW_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String CUSTODY_REVIEW_DETAILS_ID_REPORT_PARAM_NAME 
		= "CUST_REV_ID";
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/** Default instantiation */
	public ReportCustodyReviewController() {
		//Empty constructor
	}
	
	/**
	 * Lists the custody reviews for the specified offender.
	 * 
	 * @param offender offender
	 * @return model and view
	 */
	@RequestContentMapping(nameKey = "custodyReviewListScreenName",
			descriptionKey = "custodyReviewListScreenDescription",
			messageBundle = "omis.custody.msgs.custodyReview",
			screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CUSTODY_REVIEW_LIST') or hasRole('ADMIN')")
	public ModelAndView list(@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		List<CustodySummary> custodySummaries = this.custodyReportService
				.findReviewSummaries(offender);
		map.addAttribute(CUSTODY_SUMMARIES_MODEL_KEY, custodySummaries);
		map.addAttribute(OFFENDER_NUMBER_MODEL_KEY, String.valueOf(offender
				.getOffenderNumber()));
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}

	/**
	 * Returns a view of the custody reviews action menu.
	 * 
	 * @param offender offender
	 * @return model and view
	 */
	@RequestMapping(value = "/custodyReviewsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView custodyReviewsActionMenu(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(CUSTODY_REVIEWS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns a view of the custody reviews row action menu.
	 * 
	 * @param custodyReview custody review
	 * @return model and view
	 */ 
	@RequestMapping(value = "/custodyReviewsRowActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView custodyReviewsRowActionMenu(
			@RequestParam(value = "custodySumamries", required = true)
				final CustodyReview custodyReview) {
		ModelMap map = new ModelMap();
		CustodyOverride custodyOverride = this.custodyReviewService
				.findOverrideByReview(custodyReview);
		map.addAttribute(CUSTODY_REVIEW_MODEL_KEY, custodyReview);
		map.addAttribute(CUSTODY_OVERRIDE_MODEL_KEY, custodyOverride);
		return new ModelAndView(CUSTODY_REVIEWS_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/* Reports. */
	
	/**
	 * Returns the report for the specified offenders custody reviews.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/custodyReviewListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('CUSTODY_REVIEW_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportCustodyReviewListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(CUSTODY_REVIEW_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				CUSTODY_REVIEW_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified custody review.
	 * 
	 * @param custodyReview custody review
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/custodyReviewDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('CUSTODY_REVIEW_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportCustodyReviewDetails(@RequestParam(
			value = "custodyReview", required = true)
			final CustodyReview custodyReview,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(CUSTODY_REVIEW_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(custodyReview.getId()));
		byte[] doc = this.reportRunner.runReport(
				CUSTODY_REVIEW_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				CustodyReview.class,
				this.custodyReviewPropertyEditorFactory
				.createPropertyEditor());
	
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		
		binder.registerCustomEditor(
				CustodyOverride.class,
				this.custodyOverridePropertyEditorFactory
				.createPropertyEditor());
	}
}
