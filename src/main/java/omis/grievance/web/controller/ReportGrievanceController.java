package omis.grievance.web.controller;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import omis.beans.factory.PropertyEditorFactory;
import omis.beans.factory.spring.CustomDateEditorFactory;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceLocation;
import omis.grievance.domain.GrievanceSubject;
import omis.grievance.report.GrievanceReportService;
import omis.grievance.report.GrievanceSummary;
import omis.grievance.web.form.GrievanceSearchForm;
import omis.grievance.web.form.GrievanceSearchType;
import omis.grievance.web.validator.GrievanceSearchFormValidator;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderSummary;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.util.StringUtility;

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

/**
 * Controller to report grievances.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 22, 2015)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/grievance")
@PreAuthorize("hasRole('USER')")
public class ReportGrievanceController {
	
	/* View names. */
	
	private static final String VIEW_NAME = "grievance/list";
	
	/* Search result view names. */

	private static final String OFFENDERS_VIEW_NAME
		= "grievance/json/offenders";
	
	/* Action menu view names. */
	
	private static final String ACTION_MENU_VIEW_NAME
		= "grievance/includes/grievancesActionMenu";
	
	/* Model keys. */
	
	private static final String SUMMAIRES_MODEL_KEY = "grievanceSummaries";

	private static final String OFFENDER_MODEL_KEY = "offender";

	private static final String LOCATION_MODEL_KEY = "location";

	private static final String GRIEVANCE_MODEL_KEY = "grievance";

	private static final String FORM_MODEL_KEY = "grievanceSearchForm";

	private static final String SEARCH_TYPES_MODEL_KEY = "grievanceSearchTypes";

	private static final String OFFENDER_SUMMARIES_MODEL_KEY
		= "offenderSummaries";

	private static final String GRIEVANCE_LOCATIONS_MODEL_KEY
		= "grievanceLocations";

	private static final String SUBJECTS_MODEL_KEY = "subjects";
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("datePropertyEditorFactory")
	private CustomDateEditorFactory customDateEditorFactory;
	
	@Autowired
	@Qualifier("grievanceLocationPropertyEditorFactory")
	private PropertyEditorFactory grievanceLocationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("grievancePropertyEditorFactory")
	private PropertyEditorFactory grievancePropertyEditorFactory;
	
	/* Report services. */
	
	@Autowired
	@Qualifier("grievanceReportService")
	private GrievanceReportService grievanceReportService;
	
	/* Validators. */
	
	@Autowired
	@Qualifier("grievanceSearchFormValidator")
	private GrievanceSearchFormValidator grievanceSearchFormValidator;
	
	/* Report names. */
	
	private static final String GRIEVANCE_LISTING_REPORT_NAME 
		= "/CaseManagement/Grievances/Grievance_Listing";

	private static final String GRIEVANCE_DETAILS_REPORT_NAME 
		= "/CaseManagement/Grievances/Grievance_Details";
	
	private static final String GRIEVANCE_HISTORY_DETAILS_REPORT_NAME 
	= "/CaseManagement/Grievances/Grievance_Detail_History";

	/* Report parameter names. */
	
	private static final String GRIEVANCE_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String GRIEVANCE_DETAILS_ID_REPORT_PARAM_NAME 
		= "GR_ID";
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;

	/* Constructors. */
	
	/** Instantiates controller to report grievances. */
	public ReportGrievanceController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays summaries of grievances by offender.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return summaries of grievances by offender
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('GRIEVANCE_LIST')")
	@RequestMapping(value = "/listByOffender.html", method = RequestMethod.GET)
	public ModelAndView listByOffender(
			@RequestParam(value = "offender", required = true)
				final Offender offender,
			@RequestParam(value = "effectiveDate", required = false)
				final Date effectiveDate) {
		List<GrievanceSummary> summaries;
		if (effectiveDate != null) {
			summaries = this.grievanceReportService
				.summarizeByOffender(offender, effectiveDate);
		} else {
			summaries = this.grievanceReportService
				.summarizeByOffender(offender, new Date());
		}
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(SUMMAIRES_MODEL_KEY, summaries);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/**
	 * Displays summaries of grievances by location.
	 * 
	 * @param location location
	 * @param effectiveDate effective date
	 * @return summaries of grievances by location
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('GRIEVANCE_LIST')")
	@RequestMapping(value = "/listByLocation.html", method = RequestMethod.GET)
	public ModelAndView listByLocation(
			@RequestParam(value = "location", required = true)
				final GrievanceLocation location,
			@RequestParam(value = "effectiveDate", required = false)
				final Date effectiveDate) {
		List<GrievanceSummary> summaries;
		if (effectiveDate != null) {
			summaries = this.grievanceReportService
					.summarizeByLocation(location, effectiveDate);
		} else {
			summaries = this.grievanceReportService
					.summarizeByLocation(location, new Date());
		}
		List<GrievanceSubject> subjects = this.grievanceReportService
				.findGrievanceSubjects();
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(SUMMAIRES_MODEL_KEY, summaries);
		mav.addObject(LOCATION_MODEL_KEY, location);
		mav.addObject(SUBJECTS_MODEL_KEY, subjects);
		return mav;
	}
	
	/**
	 * Shows screen to search grievances.
	 * 
	 * @param defaultEffectiveDate default effective date
	 * @return screen to search for grievances
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('GRIEVANCE_LIST')")
	@RequestMapping(value = "/search.html", method = RequestMethod.GET)
	public ModelAndView search(
			@RequestParam(value = "defaultEffectiveDate", required = false)
				final Date defaultEffectiveDate) {
		List<GrievanceLocation> grievanceLocations
			= this.grievanceReportService.findGrievanceLocations();
		GrievanceSearchForm grievanceSearchForm = new GrievanceSearchForm();
		grievanceSearchForm.setType(GrievanceSearchType.OFFENDER);
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(GRIEVANCE_LOCATIONS_MODEL_KEY,
				grievanceLocations);
		mav.addObject(FORM_MODEL_KEY, grievanceSearchForm);
		mav.addObject(SEARCH_TYPES_MODEL_KEY, GrievanceSearchType.values());
		return mav;
	}
	
	/**
	 * Search grievances.
	 * 
	 * @param grievanceSearchForm form to search for grievances
	 * @param result binding result
	 * @param defaultEffectiveDate default effective date
	 * @return grievance search
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('GRIEVANCE_LIST')")
	@RequestMapping(value = "/search.html", method = RequestMethod.POST)
	public ModelAndView search(
			final GrievanceSearchForm grievanceSearchForm,
			final BindingResult result,
			@RequestParam(value = "defaultEffectiveDate", required = false)
				final Date defaultEffectiveDate) {
		List<GrievanceLocation> grievanceLocations
			= this.grievanceReportService.findGrievanceLocations();
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		this.grievanceSearchFormValidator.validate(grievanceSearchForm, result);
		if (!result.hasErrors()) {
			if (grievanceSearchForm.getType() != null) {
				Date effectiveDate;
				if (grievanceSearchForm.getEffectiveDate() != null) {
					effectiveDate = grievanceSearchForm.getEffectiveDate();
				} else if (defaultEffectiveDate != null) {
					effectiveDate = defaultEffectiveDate;
				} else {
					effectiveDate = new Date();
				}
				List<GrievanceSummary> summaries;
				if (GrievanceSearchType.OFFENDER
						.equals(grievanceSearchForm.getType())) {
					summaries = this.grievanceReportService
							.summarizeByOffender(grievanceSearchForm
									.getOffender(), effectiveDate);
					mav.addObject(OFFENDER_MODEL_KEY,
							grievanceSearchForm.getOffender());
					this.offenderSummaryModelDelegate.add(
							mav.getModelMap(),
							grievanceSearchForm.getOffender());
				} else if (GrievanceSearchType.LOCATION
						.equals(grievanceSearchForm.getType())) {
					summaries = this.grievanceReportService
							.summarizeByLocation(grievanceSearchForm
									.getLocation(), effectiveDate);
					mav.addObject(LOCATION_MODEL_KEY,
							grievanceSearchForm.getLocation());
				} else {
					throw new UnsupportedOperationException(String.format(
							"%s grievance search not supported",
							grievanceSearchForm.getType()));
				}
				mav.addObject(SUMMAIRES_MODEL_KEY, summaries);
			} else {
				throw new IllegalArgumentException("Search type required");
			}
		} else {
			mav.addObject(FORM_MODEL_KEY + BindingResult.MODEL_KEY_PREFIX,
					grievanceSearchForm);
		}
		mav.addObject(GRIEVANCE_LOCATIONS_MODEL_KEY,
				grievanceLocations);
		mav.addObject(FORM_MODEL_KEY, grievanceSearchForm);
		mav.addObject(SEARCH_TYPES_MODEL_KEY, GrievanceSearchType.values());
		return mav;
	}
	
	/* AJAX invokable methods. */
	
	/**
	 * Shows location search results.
	 * 
	 * @return location search results
	 */
	@RequestMapping(value = "/findLocation.json", method = RequestMethod.POST)
	public ModelAndView showLocationsSearch() {
		
		// TODO: Fix - locations should be selected via a drop down.
		// And AJAX call and look up by query should not be necessary as there
		// are not enough grievance locations to warrant a text search...
		throw new RuntimeException("Wrong method!");
	}
	
	/**
	 * Shows offender search results
	 * 
	 * @param query query
	 * @return offender search results
	 */
	@RequestMapping(value = "/findOffender.json", method = RequestMethod.GET)
	public ModelAndView showOffendersSearch(
			@RequestParam(value = "term", required = false)
				final String query) {
		List<OffenderSummary> offenderSummaries;
		if (StringUtility.hasContent(query)) {
			offenderSummaries = this.grievanceReportService
					.searchOffenders(query);
		} else {
			offenderSummaries = Collections.emptyList();
		}
		ModelAndView mav = new ModelAndView(OFFENDERS_VIEW_NAME);
		mav.addObject(OFFENDER_SUMMARIES_MODEL_KEY, offenderSummaries);
		return mav;
	}
	
	/* Reports. */
	
	/**
	 * Returns the report for the specified offenders grievances.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/grievanceListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('GRIEVANCE_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportGrievanceListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(GRIEVANCE_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				GRIEVANCE_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified grievance.
	 * 
	 * @param grievance grievance
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/grievanceDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('GRIEVANCE_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportGrievanceDetails(@RequestParam(
			value = "grievance", required = true)
			final Grievance grievance,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(GRIEVANCE_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(grievance.getId()));
		byte[] doc = this.reportRunner.runReport(
				GRIEVANCE_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the history report for the specified grievance.
	 * 
	 * @param grievance grievance
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/grievanceDetailHistoryReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('GRIEVANCE_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportGrievanceDetailHistory(@RequestParam(
			value = "grievance", required = true)
			final Grievance grievance,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(GRIEVANCE_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(grievance.getId()));
		byte[] doc = this.reportRunner.runReport(
				GRIEVANCE_HISTORY_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* Action menus. */
	
	/**
	 * Shows action menu for grievances or individual grievance item.
	 * 
	 * @param offender offender
	 * @param location location
	 * @param grievance grievance
	 * @return action menu for grievances
	 */
	@RequestMapping(
			value = "/grievancesActionMenu.html", method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "offender", required = false)
				final Offender offender,
			@RequestParam(value = "location", required = false)
				final GrievanceLocation location,
			@RequestParam(value = "grievance", required = false)
				final Grievance grievance) {
		List<GrievanceSubject> subjects
			= this.grievanceReportService.findGrievanceSubjects();
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		mav.addObject(LOCATION_MODEL_KEY, location);
		mav.addObject(GRIEVANCE_MODEL_KEY, grievance);
		mav.addObject(SUBJECTS_MODEL_KEY, subjects);
		return mav;
	}
	
	/* Init binders. */
	
	/**
	 * Registers property editors.
	 * 
	 * @param binder web data binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
		binder.registerCustomEditor(Date.class,
				this.customDateEditorFactory
					.createCustomDateOnlyEditor(true));
		binder.registerCustomEditor(Grievance.class,
				this.grievancePropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(GrievanceLocation.class,
				this.grievanceLocationPropertyEditorFactory
					.createPropertyEditor());
	}
}