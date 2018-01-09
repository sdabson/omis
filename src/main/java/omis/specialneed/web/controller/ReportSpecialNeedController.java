package omis.specialneed.web.controller;

import java.util.Date;
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
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.report.web.controller.delegate.ReportControllerDelegate;
import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedClassification;
import omis.specialneed.report.SpecialNeedReportService;
import omis.specialneed.report.SpecialNeedSummary;
import omis.specialneed.service.SpecialNeedService;

/**
 * Controller for special need.
 * 
 * @author Joel Norris
 * @author Sheronda Vaughn
 * @author Josh Divine
 * @version 0.1.2 (Nov 2, 2017)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/specialNeed")
@PreAuthorize("hasRole('USER')")
public class ReportSpecialNeedController {
	
	/* View names */
	
	private static final String LIST_VIEW_NAME = "specialNeed/list";
	
	private static final String SPECIAL_NEEDS_ACTION_MENU_VIEW_NAME 
		= "specialNeed/includes/specialNeedsActionMenu";

	private static final String SPECIAL_NEEDS_ROW_ACTION_MENU_VIEW_NAME
		= "specialNeed/includes/specialNeedsRowActionMenu";
	
	/* Model Keys. */
	
	private static final String SPECIAL_NEED_SUMMARY_MODEL_KEY 
		= "specialNeedSummaries";
	
	private static final String OFFENDER_NUMBER_MODEL_KEY = "offenderNumber";
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String SPECIAL_NEED_MODEL_KEY = "specialNeed";
	
	private static final String CLASSIFICATION_MODEL_KEY = "classification";
	
	private static final String CLASSIFICATIONS_MODEL_KEY = "classifications";
	
	/* Services. */
	
	@Autowired
	@Qualifier("specialNeedService")
	private SpecialNeedService specialNeedService;
	
	@Autowired
	@Qualifier("specialNeedReportService")
	private SpecialNeedReportService specialNeedReportService;
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("specialNeedPropertyEditorFactory")
	private PropertyEditorFactory specialNeedPropertyEditorFactory;
	
	@Autowired
	@Qualifier("classificationPropertyEditorFactory")
	private PropertyEditorFactory classificationPropertyEditorFactory;
	
	/* Helpers. */

	@Autowired
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Report names. */
	
	private static final String SPECIAL_NEED_LISTING_REPORT_NAME 
		= "/Safety/SpecialManagementDesignation/Special_Management_Designation_Listing";

	private static final String SPECIAL_NEED_DETAILS_REPORT_NAME 
		= "/Safety/SpecialManagementDesignation/Special_Management_Designation_Details";

	/* Report parameter names. */
	
	private static final String SPECIAL_NEED_LISTING_ID_REPORT_PARAM_NAME 
		= "DOC_ID";

	private static final String SPECIAL_NEED_DETAILS_ID_REPORT_PARAM_NAME 
		= "SM_ID";
	
	/* Report runners. */
	
	@Autowired
	@Qualifier("reportRunner")
	private ReportRunner reportRunner;
	
	/* Controller delegates. */
	
	@Autowired
	@Qualifier("reportControllerDelegate")
	private ReportControllerDelegate reportControllerDelegate;
	
	/**
	 * Instantiates a default instance of report special need controller.
	 */
	public ReportSpecialNeedController() {
		//Default constructor.
	}
	
	/**
	 * Lists the special needs for the specified offender.
	 * @param offender offender
	 * @return the model and view
	 */
	@RequestContentMapping(nameKey = "specialNeedListScreenName",
		descriptionKey = "specialNeedListScreenDescription",
		messageBundle = "omis.specialneed.msgs.specialNeed",
		screenType = RequestContentType.LISTING_SCREEN)
	@RequestMapping(value = "list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('SPECIAL_NEED_LIST') or hasRole('ADMIN')")
	public ModelAndView list(@RequestParam(
		value = "offender", required = true) 
		final Offender offender, 
		@RequestParam(value = "specialNeedClassification", required = false)
		final SpecialNeedClassification classification,
		@RequestParam(value = "effectiveDate", required = false)
		final Date effectiveDate) {
		ModelMap map = new ModelMap();
		Date queryDate = new Date();
		if (effectiveDate != null) {
			queryDate = effectiveDate;			
		}
		List<SpecialNeedSummary> specialNeedSummaries 
			= this.specialNeedReportService.summarizeByOffender(offender, 
					queryDate);
		map.addAttribute(SPECIAL_NEED_SUMMARY_MODEL_KEY, specialNeedSummaries);
		map.addAttribute(CLASSIFICATION_MODEL_KEY, classification);
		map.addAttribute("effectiveDate", effectiveDate);
		map.addAttribute(OFFENDER_NUMBER_MODEL_KEY, 
				String.valueOf(offender.getOffenderNumber()));
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/**
	 * Displays the special needs action menu.
	 * 
	 * @return model and view for special needs action menu
	 */
	@RequestMapping(value = "specialNeedsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView specialNeedsActionMenu(
			@RequestParam(value = "offender",required = true)
			final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(CLASSIFICATIONS_MODEL_KEY, this.specialNeedService
				.findClassifications());
		return new ModelAndView(SPECIAL_NEEDS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Displays the special needs row action menu.
	 * 
	 * @param specialNeed special need
	 * @return model and view for special needs row action menu
	 */
	@RequestMapping(value = "specialNeedsRowActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView specialNeedsRowActionMenu(
			@RequestParam(value = "specialNeed", required = true)
			final SpecialNeed specialNeed) {
		ModelMap map = new ModelMap();
		map.addAttribute(SPECIAL_NEED_MODEL_KEY, specialNeed);		
		map.addAttribute(CLASSIFICATION_MODEL_KEY, 
				specialNeed.getClassification());
		return new ModelAndView(SPECIAL_NEEDS_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the report for the specified offenders special needs.
	 * 
	 * @param offender offender
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/specialNeedListingReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('SPECIAL_NEED_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportSpecialNeedListing(@RequestParam(
			value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(SPECIAL_NEED_LISTING_ID_REPORT_PARAM_NAME,
				Long.toString(offender.getOffenderNumber()));
		byte[] doc = this.reportRunner.runReport(
				SPECIAL_NEED_LISTING_REPORT_NAME,
				reportParamMap, reportFormat);
		return this.reportControllerDelegate.constructReportResponseEntity(
				doc, reportFormat);
	}
	
	/**
	 * Returns the report for the specified special need.
	 * 
	 * @param specialNeed special need
	 * @param reportFormat report format
	 * @return response entity with report
	 */
	@RequestMapping(value = "/specialNeedDetailsReport.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('SPECIAL_NEED_LIST') or hasRole('ADMIN')")
	public ResponseEntity<byte []> reportSpecialNeedDetails(@RequestParam(
			value = "specialNeed", required = true)
			final SpecialNeed specialNeed,
			@RequestParam(value = "reportFormat", required = true)
			final ReportFormat reportFormat) {
		Map<String, String> reportParamMap = new HashMap<String, String>();
		reportParamMap.put(SPECIAL_NEED_DETAILS_ID_REPORT_PARAM_NAME,
				Long.toString(specialNeed.getId()));
		byte[] doc = this.reportRunner.runReport(
				SPECIAL_NEED_DETAILS_REPORT_NAME,
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
				SpecialNeed.class,
				this.specialNeedPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				SpecialNeedClassification.class, 
				this.classificationPropertyEditorFactory
				.createPropertyEditor());
		
		binder.registerCustomEditor(
				Offender.class,
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
	}
}