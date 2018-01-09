package omis.religion.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import omis.beans.factory.PropertyEditorFactory;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.religion.domain.ReligiousPreference;
import omis.religion.report.ReligiousPreferenceReportService;
import omis.religion.report.ReligiousPreferenceSummary;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;

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

/**
 * Controller to report religious preferences.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 27, 2014)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/religion/religiousPreference")
@PreAuthorize("hasRole('USER')")
public class ReportReligiousPreferencesController {

	/* View names. */
	
	private static final String LIST_VIEW_NAME
		= "religion/religiousPreference/list";
	
	/* Action menu view names. */
	
	private static final String ACTION_MENU_VIEW_NAME
		= "religion/religiousPreference/includes"
				+ "/religiousPreferencesActionMenu";

	/* Model keys. */
	
	private static final String RELIGIOUS_PREFERENCE_SUMMARIES_MODEL_KEY
		= "religiousPreferenceSummaries";

	private static final String ACCOMMODATION_NAMES_MODEL_KEY
		= "accommodationNames";

	private static final String OFFENDER_MODEL_KEY = "offender";

	private static final String PREFERENCE_MODEL_KEY = "preference";
	
	/* Report services. */

	@Autowired
	@Qualifier("religiousPreferenceReportService")
	private ReligiousPreferenceReportService religiousPreferenceReportService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("religiousPreferencePropertyEditorFactory")
	private PropertyEditorFactory religiousPreferencePropertyEditorFactory;
	
	/* Report names. */
	
	private static final String RELIGIOUS_PREFERENCE_LISTING_REPORT_NAME 
		= "/BasicInformation/ReligiousPreference/Religious_Preference_Listing";

	private static final String RELIGIOUS_PREFERENCE_DETAILS_REPORT_NAME 
		= "/BasicInformation/ReligiousPreference/Religious_Preference_Details";

	/* Report parameter names. */
	
	private static final String RELIGIOUS_PREFERENCE_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String RELIGIOUS_PREFERENCE_DETAILS_ID_REPORT_PARAM_NAME 
		= "RELIGIOUS_PREF_ID";
	
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
	
	/* Constructors. */
	
	/** Instantiates a controller to report religious preferences. */
	public ReportReligiousPreferencesController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Displays a list of religious preferences.
	 * 
	 * @param offender offender
	 * @return screen to display list of religious preferences
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('RELIGIOUS_PREFERENCE_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		List<ReligiousPreferenceSummary> religiousPreferenceSummaries
			= this.religiousPreferenceReportService
				.findSummariesByOffender(offender);
		List<String> accommodationNames
			= this.religiousPreferenceReportService.findAccommodationNames();
		mav.addObject(RELIGIOUS_PREFERENCE_SUMMARIES_MODEL_KEY,
				religiousPreferenceSummaries);
		mav.addObject(ACCOMMODATION_NAMES_MODEL_KEY, accommodationNames);
		return mav;
	}
	
	/* Actions menus. */
	
	/**
	 * Returns action menu.
	 * 
	 * @param offender offender
	 * @param preference religious preference
	 * @return action menu
	 */
	@RequestMapping(value = "/preferencesActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "offender", required = false)
				final Offender offender,
			@RequestParam(value = "preference", required = false)
				final ReligiousPreference preference) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		if (offender != null) {
			mav.addObject(OFFENDER_MODEL_KEY, offender);
		}
		if (preference != null) {
			mav.addObject(PREFERENCE_MODEL_KEY, preference);
		}
		return mav;
	}
	
	/**
	 * Returns the report for the specified offenders religious preferences.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/religiousPreferenceListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('RELIGIOUS_PREFERENCE_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportReligiousPreferenceListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(RELIGIOUS_PREFERENCE_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				RELIGIOUS_PREFERENCE_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified religious preference.
	 * 
	 * @param preference tier designation
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/religiousPreferenceDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('RELIGIOUS_PREFERENCE_VIEW') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportReligiousPreferenceDetails(@RequestParam(
			value = "preference", required = true)
			final ReligiousPreference preference,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(RELIGIOUS_PREFERENCE_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(preference.getId()));
		byte[] doc = this.reportRunner.runReport(
				RELIGIOUS_PREFERENCE_DETAILS_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/* Init binders. */
	
	/**
	 * Registers property editors.
	 * 
	 * @param binder init binder
	 */
	@InitBinder
	protected void registerPropertyEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(ReligiousPreference.class,
				this.religiousPreferencePropertyEditorFactory
					.createPropertyEditor());
		binder.registerCustomEditor(Offender.class,
				this.offenderPropertyEditorFactory
					.createOffenderPropertyEditor());
	}
}