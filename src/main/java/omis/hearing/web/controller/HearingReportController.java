package omis.hearing.web.controller;

import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import omis.hearing.domain.Hearing;
import omis.hearing.report.HearingSummary;
import omis.hearing.report.HearingSummaryReportService;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;

/**
 * HearingReportController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Dec 28, 2016)
 *@since OMIS 3.0
 *
 */

@Controller
@RequestMapping("/hearing/")
@PreAuthorize("hasRole('USER')")
public class HearingReportController {
	
	/* View names */
	
	private static final String LIST_VIEW_NAME = "/hearing/list";
	
	private static final String HEARINGS_ACTION_MENU_VIEW_NAME =
			"/hearing/includes/hearingsActionMenu";
	
	private static final String HEARINGS_ROW_ACTION_MENU_VIEW_NAME =
			"/hearing/includes/hearingsRowActionMenu";
	
	/* Model Keys */
	
	private static final String OFFENDER_MODEL_KEY = "offender";

	private static final String HEARING_SUMMARIES_MODEL_KEY = "hearingSummaries";
	
	private static final String HEARING_MODEL_KEY = "hearing";
	
	/* Service */
	
	@Autowired
	@Qualifier("hearingSummaryReportService")
	private HearingSummaryReportService hearingSummaryReportService;
	
	/* Property editors */
	
	@Autowired
	@Qualifier("hearingPropertyEditorFactory")
	private PropertyEditorFactory hearingPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/* Delegates */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;

	/**
	 * Default constructor for HearingsReportController
	 */
	public HearingReportController() {
	}
	
	/**
	 * Returns model and view for hearings list
	 * @param offender - offender for which hearings are being listed
	 * @return ModelAndView - model and view for hearings list
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole ('HEARING_LIST')")
	public ModelAndView list(@RequestParam(value = "offender", required = true)
		final Offender offender){
		
		ModelMap map = new ModelMap();
		
		List<HearingSummary> summaries = this.hearingSummaryReportService
				.findByOffender(offender);
		
		map.addAttribute(HEARING_SUMMARIES_MODEL_KEY, summaries);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	
	/**
	 * Dispays model and view for hearings action menu
	 * @param offender
	 * @return ModelAndView - model and view for hearings action menu
	 */
	@RequestMapping(value = "/hearingsActionMenu.html", 
			method = RequestMethod.GET)
	public ModelAndView displayHearingsActionMenu(@RequestParam
			(value = "offender", required = true) final Offender offender){
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		
		return new ModelAndView(HEARINGS_ACTION_MENU_VIEW_NAME, map);
	}
		
	/**
	 * Displays model and view for hearings row action menu
	 * @param hearing - hearing associated with the row
	 * @return ModelAndView - model and view for hearings row action menu
	 */
	@RequestMapping(value = "/hearingsRowActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView displayHearingsRowActionMenu(@RequestParam
			(value = "hearing", required = true) final Hearing hearing){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(HEARING_MODEL_KEY, hearing);
		
		return new ModelAndView(HEARINGS_ROW_ACTION_MENU_VIEW_NAME, map);
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
		binder.registerCustomEditor(
				Hearing.class, this.hearingPropertyEditorFactory
				.createPropertyEditor());
	}
}
