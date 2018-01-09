package omis.physicalfeature.web.controller;

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
import omis.physicalfeature.report.PhysicalFeatureSummaryReportService;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.physicalfeature.domain.PhysicalFeatureAssociation;
import omis.physicalfeature.report.PhysicalFeatureSummary;

/**
 * Physical feature report controller.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Sep 6, 2016)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/physicalFeature")
@PreAuthorize("hasRole('USER')")
public class PhysicalFeatureReportController {
	
	/* View names. */
	
	private static final String LIST_VIEW_NAME = "physicalFeature/list";
	
	/* Model keys. */
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String OFFENDER_NUMBER_MODEL_KEY = "offenderNumber";
	
	private static final String PHYSICAL_FEATURE_SUMMARIES_MODEL_KEY = 
			"physicalFeatureSummaries";

	/* Report names. */
	
	private static final String PHYSICAL_FEATURE_LISTING_REPORT_NAME 
		= "/BasicInformation/ScarsMarksTattoos/Scars_Marks_Tattoos_Listing";
	
	private static final String PHYSICAL_FEATURE_LISTING_TEXT_REPORT_NAME
		= "/BasicInformation/ScarsMarksTattoos/Scars_Marks_Tattoos_Listing_Text";
	
	private static final String PHYSICAL_FEATURE_DETAILS_REPORT_NAME 
		= "/BasicInformation/ScarsMarksTattoos/Scars_Marks_Tattoos_Details";
	
	/* Report parameter names. */
	
	private static final String PHYSICAL_FEATURE_LISTING_ID_REPORT_PARAM_NAME
		= "DOC_ID";
	
	private static final String PHYSICAL_FEATURE_DETAILS_ID_REPORT_PARAM_NAME
		= "PHYS_FEAT_ASSOC_ID";

	/* Services */
	
	@Autowired
	@Qualifier("physicalFeatureSummaryReportService")
	private PhysicalFeatureSummaryReportService 
		physicalFeatureSummaryReportService;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	OffenderPropertyEditorFactory offenderPropertyEditorFactory;

	@Autowired
	@Qualifier("physicalFeatureAssociationPropertyEditorFactory")
	private PropertyEditorFactory 
	physicalFeatureAssociationPropertyEditorFactory;
	
	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/* Constructors. */
	
	/** Instantiates an implementation of PhysicalFeatureReportController. */
	public PhysicalFeatureReportController() {
		// Default constructor.
	}

	/* Model and views. */
	
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('PHYSICAL_FEATURE_LIST')")
	public ModelAndView list(@RequestParam(value = "offender", required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		List<PhysicalFeatureSummary> summaries 
			= this.physicalFeatureSummaryReportService.findByOffender(offender);
		map.addAttribute(PHYSICAL_FEATURE_SUMMARIES_MODEL_KEY, summaries);
		map.addAttribute(OFFENDER_NUMBER_MODEL_KEY, 
				String.valueOf(offender.getOffenderNumber()));
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}	

	/* Reports. */
	
	/**
	 * Returns the report for the specified offender.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/physicalFeatureListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PHYSICAL_FEATURE_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportPhysicalFeatureListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(PHYSICAL_FEATURE_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				PHYSICAL_FEATURE_LISTING_REPORT_NAME,
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
	@RequestMapping(value = "/physicalFeatureListingTextReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PHYSICAL_FEATURE_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportPhysicalFeatureListingText(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(PHYSICAL_FEATURE_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				PHYSICAL_FEATURE_LISTING_TEXT_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	/**
	 * Returns the report for the specified offender physical feature association.
	 * 
	 * @param physicalFeatureAssociation offender physical feature association
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/physicalFeatureDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('PHYSICAL_FEATURE_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportPhysicalFeatureDetails(@RequestParam(
			value = "physicalFeatureAssociation", required = true)
			final PhysicalFeatureAssociation physicalFeatureAssociation,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(PHYSICAL_FEATURE_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(physicalFeatureAssociation.getId()));
		byte[] doc = this.reportRunner.runReport(
				PHYSICAL_FEATURE_DETAILS_REPORT_NAME,
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
		
		binder.registerCustomEditor(
				PhysicalFeatureAssociation.class,
				this.physicalFeatureAssociationPropertyEditorFactory
				.createPropertyEditor());
		
	}
}