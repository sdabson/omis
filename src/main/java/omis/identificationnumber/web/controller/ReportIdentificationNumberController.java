package omis.identificationnumber.web.controller;

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
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.identificationnumber.domain.IdentificationNumber;
import omis.identificationnumber.report.IdentificationNumberReportService;
import omis.identificationnumber.report.IdentificationNumberSummary;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;

/**
 * Controller to report identification numbers.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/identificationNumber")
public class ReportIdentificationNumberController {

	/* View names */
	
	private static final String VIEW_NAME = "identificationNumber/list";
	
	private static final String ACTION_MENU_VIEW_NAME
		= "identificationNumber/includes/identificationNumbersActionMenu";
	
	/* Model keys */
	
	private static final String SUMMARIES_MODEL_KEY
		= "identificationNumberSummaries";

	private static final String OFFENDER_MODEL_KEY = "offender";

	private static final String IDENTIFICATION_NUMBER_MODEL_KEY
		= "identificationNumber";
	
	/* Property editor factories */
	
	@Autowired
	@Qualifier("identificationNumberPropertyEditorFactory")
	private PropertyEditorFactory identificationNumberPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	/* Report services */
	
	@Autowired
	@Qualifier("identificationNumberReportService")
	private IdentificationNumberReportService identificationNumberReportService;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Report names. */
	
	private static final String IDENTIFICATION_NUMBER_LISTING_REPORT_NAME 
		= "/BasicInformation/IdentificationNumbers/Identification_Numbers_Listing";

	private static final String IDENTIFICATION_NUMBER_DETAILS_REPORT_NAME 
		= "/BasicInformation/IdentificationNumbers/Identification_Number_Details";

	/* Report parameter names. */
	
	private static final String IDENTIFICATION_NUMBER_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String IDENTIFICATION_NUMBER_DETAILS_ID_REPORT_PARAM_NAME 
		= "ID_NUM_ID";
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;

	/* Constructors */
	
	/** Instantiates controller to report identification numbers. */
	public ReportIdentificationNumberController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays list of identification numbers.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return screen displaying list of identification numbers
	 */
	@PreAuthorize("hasRole('IDENTIFICATION_NUMBER_LIST') or hasRole('ADMIN')")
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "effectiveDate", required = false)
				final Date effectiveDate) {
		Date effectiveOrCurrentDate;
		if (effectiveDate != null) {
			effectiveOrCurrentDate = effectiveDate;
		} else {
			effectiveOrCurrentDate = new Date();
		}
		List<IdentificationNumberSummary> identificationNumberSummaries
			= this.identificationNumberReportService.summarizeByOffenderOnDate(
					offender, effectiveOrCurrentDate);
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(SUMMARIES_MODEL_KEY, identificationNumberSummaries);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/* Action menus */
	
	/**
	 * Shows action menu.
	 * 
	 * @param offender offender
	 * @param identificationNumber identification number
	 * @return action menu
	 */
	@RequestMapping(value = "/identificationNumbersActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "offender", required = false)
				final Offender offender,
			@RequestParam(value = "identificationNumber", required = false)
				final IdentificationNumber identificationNumber) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		mav.addObject(IDENTIFICATION_NUMBER_MODEL_KEY, identificationNumber);
		return mav;
	}
	
	/* Reports. */
	
	/**
	 * Returns the report for the specified offenders identification numbers.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/identificationNumberListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('IDENTIFICATION_NUMBER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportIdentificationNumberListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(IDENTIFICATION_NUMBER_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				IDENTIFICATION_NUMBER_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified identification number.
	 * 
	 * @param identificationNumber identification number
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/identificationNumberDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('IDENTIFICATION_NUMBER_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportIdentificationNumberDetails(@RequestParam(
			value = "identificationNumber", required = true)
			final IdentificationNumber identificationNumber,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(IDENTIFICATION_NUMBER_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(identificationNumber.getId()));
		byte[] doc = this.reportRunner.runReport(
				IDENTIFICATION_NUMBER_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* Init binders */
	
	/**
	 * Registers property editors.
	 * 
	 * @param webBinder data binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder webBinder) {
		webBinder.registerCustomEditor(IdentificationNumber.class,
				this.identificationNumberPropertyEditorFactory
					.createPropertyEditor());
		webBinder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		webBinder.registerCustomEditor(Date.class,
				this.customDateEditorFactory.createCustomDateOnlyEditor(true));
	}
}