package omis.courtcase.web.controller;

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
import omis.courtcase.domain.Charge;
import omis.courtcase.report.ChargeSummary;
import omis.courtcase.report.ChargeSummaryReportService;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;

/**
 * Controller for displaying charges.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Aug 11, 2017)
 * @since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/courtCase")
@PreAuthorize("hasRole('USER')")
public class ReportChargeController {

/* View names. */
	
	private static final String LIST_VIEW_NAME = "courtCase/listCharges";
	
	/* Model names. */
	
	private static final String CHARGE_SUMMARIES_MODEL_NAME = 
			"chargeSummaries";
	
	private static final String DEFENDANT_MODEL_KEY = "defendant";
	
	/* Report services. */
	
	@Autowired
	@Qualifier("chargeSummaryReportService")
	private ChargeSummaryReportService chargeSummaryReportService;
	
	/* Property editors. */
	
	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;
	
	@Autowired
	@Qualifier("chargePropertyEditorFactory")
	private PropertyEditorFactory chargePropertyEditorFactory;

	/* Helpers. */
	@Autowired 
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Report names. */
	
	private static final String CHARGE_LISTING_REPORT_NAME 
		= "/Legal/Charges/Charge_Listing";

	private static final String CHARGE_DETAILS_REPORT_NAME 
		= "/Legal/Charges/Charge_Details";

	/* Report parameter names. */
	
	private static final String CHARGE_LISTING_ID_REPORT_PARAM_NAME 
		= "PERSON_ID";

	private static final String CHARGE_DETAILS_ID_REPORT_PARAM_NAME 
		= "CHARGE_ID";
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/* Constructors. */
	
	/**
	 * Instantiates a default instance of report charge controller.
	 */
	public ReportChargeController() {
		//Default constructor.
	}
	
	/* URL invokable methods. */
		
	/**
	 * Shows a listing screen of charge summary objects.
	 * 
	 * @param defendant defendant
	 * @return model and view to present a list of court case summaries
	 */
	@RequestMapping(value = "/listCharges.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole('CHARGE_LIST')")
	public ModelAndView list(@RequestParam(value = "defendant", required = true)
			final Person defendant) {
		ModelMap map = new ModelMap();
		List<ChargeSummary> chargeSummaries = this.chargeSummaryReportService
				.summarizePendingChargesByOffender(defendant);
		map.addAttribute(CHARGE_SUMMARIES_MODEL_NAME, chargeSummaries);
		this.offenderSummaryModelDelegate.add(map, (Offender) defendant);
		map.addAttribute(DEFENDANT_MODEL_KEY, defendant);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/* Reports. */
	
	/**
	 * Returns the report for the specified defendants pending charges.
	 * 
	 * @param defendant offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/chargeListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('CHARGE_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportChargeListing(@RequestParam(
			value = "defendant", required = true)
			final Person defendant,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(CHARGE_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(defendant.getId()));
		byte[] doc = this.reportRunner.runReport(
				CHARGE_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified charge.
	 * 
	 * @param charge charge
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/chargeDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('CHARGE_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportChargeDetails(@RequestParam(
			value = "charge", required = true)
			final Charge charge,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(CHARGE_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(charge.getId()));
		byte[] doc = this.reportRunner.runReport(
				CHARGE_DETAILS_REPORT_NAME,
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
				Person.class,
				this.personPropertyEditorFactory
				.createPropertyEditor());
		binder.registerCustomEditor(Charge.class,
				this.chargePropertyEditorFactory.createPropertyEditor());
	}
}
