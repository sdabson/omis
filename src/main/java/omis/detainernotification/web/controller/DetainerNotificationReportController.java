package omis.detainernotification.web.controller;

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
import omis.detainernotification.domain.Detainer;
import omis.detainernotification.domain.DetainerAgency;
import omis.detainernotification.report.DetainerAgencySummary;
import omis.detainernotification.report.DetainerSummary;
import omis.detainernotification.report.DetainerSummaryReportService;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;

/**
 * DetainerNotificationReportController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 12, 2016)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/detainerNotification/")
@PreAuthorize("hasRole('USER')")
public class DetainerNotificationReportController {
	
	/* View names. */
		
	private static final String LIST_VIEW_NAME = "/detainerNotification/list";
	
	private static final String DETAINER_AGENCY_DETAILS_VIEW_NAME =
			"/detainerNotification/includes/detainerAgencyDetails";
	
	/* Model keys. */
	
	private static final String OFFENDER_MODEL_KEY = "offender";

	private static final String SUMMARIES_MODEL_KEY = "summaries";
	
	private static final String DETAINER_AGENCY_SUMMARY_MODEL_KEY =
			"detainerAgencySummary";
	
	/* Services */
	
	@Autowired
	@Qualifier("detainerSummaryReportService")
	private DetainerSummaryReportService detainerSummaryReportService;
	
	/* Property editors */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("detainerPropertyEditorFactory")
	private PropertyEditorFactory detainerPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("detainerAgencyPropertyEditorFactory")
	private PropertyEditorFactory detainerAgencyPropertyEditorFactory;
	
	/* Report names. */
	
	private static final String DETAINER_NOTIFICATION_LISTING_REPORT_NAME 
		= "/Legal/DetainersAndNotifications/Detainer_and_Notification_Listing";

	private static final String DETAINER_NOTIFICATION_DETAILS_REPORT_NAME 
		= "/Legal/DetainersAndNotifications/Detainer_and_Notification_Details";

	/* Report parameter names. */
	
	private static final String 
		DETAINER_NOTIFICATION_LISTING_ID_REPORT_PARAM_NAME 
			= "DOC_ID";

	private static final String 
		DETAINER_NOTIFICATION_DETAILS_ID_REPORT_PARAM_NAME 
			= "DETAINER_ID";
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/*Constructor.*/
	
	/**
	 * Instantiates default instance of detainer/notification report controller
	 */
	public DetainerNotificationReportController(){
		//Default
	}
	
	/* mavs */
	
	/**
	 * Returns a list view for detainer/notification summaries
	 * 
	 * @param offender - offender
	 * @return model and view for detainer/notification summaries
	 */
	@RequestMapping(value = "list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('DETAINER_NOTIFICATION_LIST')") 
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		List<DetainerSummary> summaries
			= this.detainerSummaryReportService
			.findByOffender(offender);

		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(SUMMARIES_MODEL_KEY, summaries);
		this.offenderSummaryModelDelegate.add(map, offender);
		
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/**
	 * Displays the view for Detainer Agency Details
	 * 
	 * @param detainerAgency - Detainer Agency to summarize
	 * @return ModelAndView - view for Detainer Agency Details
	 */
	@RequestMapping(value = "detainerAgencyDetails.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')" 
			+ " or hasRole('DETAINER_NOTIFICATION_VIEW')") 
	public ModelAndView displayDetainerAgencyDetails(
			@RequestParam(value = "detainerAgency", required = true)
			final DetainerAgency detainerAgency) {
		ModelMap map = new ModelMap();
		DetainerAgencySummary detainerAgencySummary =
				this.detainerSummaryReportService
				.summarizeDetainerAgency(detainerAgency);
		map.addAttribute(DETAINER_AGENCY_SUMMARY_MODEL_KEY,
				detainerAgencySummary);
		
		return new ModelAndView(DETAINER_AGENCY_DETAILS_VIEW_NAME, map);
	}
	
	/* Reports. */
	
	/**
	 * Returns the report for the specified offenders detainer notifications.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/detainerNotificationListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('DETAINER_NOTIFICATION_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportDetainerNotificationListing(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DETAINER_NOTIFICATION_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				DETAINER_NOTIFICATION_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified detainer notification.
	 * 
	 * @param detainer detainer
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/detainerNotificationDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('DETAINER_NOTIFICATION_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportDetainerNotificationDetails(
			@RequestParam(value = "detainer", required = true)
			final Detainer detainer,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(DETAINER_NOTIFICATION_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(detainer.getId()));
		byte[] doc = this.reportRunner.runReport(
				DETAINER_NOTIFICATION_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* initBinder */
		
	/**
	 * Sets up and registers property editors
	 * 
	 * @param binder - web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
		binder.registerCustomEditor(Detainer.class, 
				this.detainerPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(DetainerAgency.class, 
				this.detainerAgencyPropertyEditorFactory.createPropertyEditor());
	}
	
	
	
}
