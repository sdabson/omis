package omis.warrant.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantArrest;
import omis.warrant.domain.WarrantCancellation;
import omis.warrant.domain.WarrantReasonCategory;
import omis.warrant.domain.WarrantRelease;
import omis.warrant.report.WarrantReportService;
import omis.warrant.report.WarrantSummary;
import omis.warrant.service.WarrantService;

/**
 * WarrantReportController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/warrant/")
@PreAuthorize("hasRole('USER')")
public class WarrantReportController {
	
	/* Views Names */
	
	private static final String LIST_VIEW_NAME = "/warrant/list";
	
	private static final String WARRANTS_ACTION_MENU_VIEW_NAME =
			"/warrant/includes/warrantsActionMenu";
	
	private static final String WARRANTS_ROW_ACTION_MENU_VIEW_NAME =
			"/warrant/includes/warrantsRowActionMenu";
	
	/* Model Keys */
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String WARRANT_SUMMARIES_MODEL_KEY = "warrantSummaries";
	
	private static final String WARRANT_MODEL_KEY = "warrant";
	
	private static final String WARRANT_RELEASE_MODEL_KEY = "warrantRelease";
	
	private static final String WARRANT_CANCELLATION_MODEL_KEY =
			"warrantCancellation";
	
	private static final String WARRANT_ARREST_MODEL_KEY = "warrantArrest";
	
	private static final String WARRANT_REASON_CATEGORIES =
			"warrantReasonCategories";
	
	/* Service */
	
	@Autowired
	@Qualifier("warrantReportService")
	private WarrantReportService warrantReportService;
	
	@Autowired
	@Qualifier("warrantService")
	private WarrantService warrantService;
	
	/* Property editor factories */
	
	@Autowired
	@Qualifier("warrantPropertyEditorFactory")
	private PropertyEditorFactory warrantPropertyEditorFactory;
	
	@Autowired
	@Qualifier("warrantReleasePropertyEditorFactory")
	private PropertyEditorFactory warrantReleasePropertyEditorFactory;
	
	@Autowired
	@Qualifier("warrantCancellationPropertyEditorFactory")
	private PropertyEditorFactory warrantCancellationPropertyEditorFactory;
	
	@Autowired
	@Qualifier("warrantArrestPropertyEditorFactory")
	private PropertyEditorFactory warrantArrestPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/* Delegates */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/**
	 * Default constructor for the WarrantReportController
	 */
	public WarrantReportController() {
	}
	
	/* Views */
	
	/**
	 * Displays the Warrant list screen
	 * @param offender - Offender
	 * @return ModelAndView - Warrant list screen
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole ('WARRANT_LIST')")
	public ModelAndView list(@RequestParam(value = "offender", required = true)
		final Offender offender){
		
		List<WarrantSummary> warrantSummaries = this.warrantReportService
				.findByOffender(offender);
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(WARRANT_SUMMARIES_MODEL_KEY, warrantSummaries);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/* Action menus */
	
	/**
	 * Displays the Warrants action menu
	 * @param offender - Offender
	 * @return ModelAndView - Warrants action menu
	 */
	@RequestMapping(value="/warrantsActionMenu.html", method=RequestMethod.GET)
	public ModelAndView displayWarrantsActionMenu(
			@RequestParam(value = "offender", required = true)
			final Offender offender){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(WARRANT_REASON_CATEGORIES,
				WarrantReasonCategory.values());
		
		return new ModelAndView(WARRANTS_ACTION_MENU_VIEW_NAME, map);
		
		
	}
	
	/**
	 * Displays the Warrants row action menu
	 * @param warrant - Warrant
	 * @param warrantRelease - WarrantRelease of the specified warrant
	 * @param warrantCancellation - WarrantCancellation of the specified warrant
	 * @param warrantArrest - WarrantArrest of the specified warrant
	 * @return ModelAndView - Warrants row action menu
	 */
	@RequestMapping(value="/warrantsRowActionMenu.html", method=RequestMethod.GET)
	public ModelAndView displayWarrantsRowActionMenu(
			@RequestParam(value="warrant", required=true)
				final Warrant warrant,
			@RequestParam(value="warrantRelease", required=true)
				final WarrantRelease warrantRelease,
			@RequestParam(value="warrantCancellation", required=true)
				final WarrantCancellation warrantCancellation,
			@RequestParam(value="warrantArrest", required=true)
				final WarrantArrest warrantArrest){
		ModelMap map = new ModelMap();
		
		map.addAttribute(WARRANT_MODEL_KEY, warrant);
		map.addAttribute(WARRANT_RELEASE_MODEL_KEY, warrantRelease);
		map.addAttribute(WARRANT_CANCELLATION_MODEL_KEY, warrantCancellation);
		map.addAttribute(WARRANT_ARREST_MODEL_KEY, warrantArrest);
		
		return new ModelAndView(WARRANTS_ROW_ACTION_MENU_VIEW_NAME, map);
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
		binder.registerCustomEditor(Warrant.class,
				this.warrantPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(WarrantRelease.class,
				this.warrantReleasePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(WarrantCancellation.class,
				this.warrantCancellationPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(WarrantArrest.class,
				this.warrantArrestPropertyEditorFactory.createPropertyEditor());
	}
}
