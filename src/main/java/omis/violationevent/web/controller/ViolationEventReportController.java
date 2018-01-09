package omis.violationevent.web.controller;

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
import omis.violationevent.domain.ViolationEvent;
import omis.violationevent.domain.ViolationEventCategory;
import omis.violationevent.report.ViolationEventSummary;
import omis.violationevent.report.ViolationEventSummaryReportService;

/**
 * ViolationEventReportController.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 19, 2017)
 *@since OMIS 3.0
 *
 */
@Controller
@RequestMapping("/violationEvent/")
@PreAuthorize("hasRole('USER')")
public class ViolationEventReportController {
	
	/* View Names */
	
	private static final String LIST_VIEW_NAME = "/violationEvent/list";
	
	private static final String VIOLATION_EVENTS_ACTION_MENU_VIEW_NAME =
			"/violationEvent/includes/violationEventsActionMenu";
	
	private static final String VIOLATION_EVENTS_ROW_ACTION_MENU_VIEW_NAME =
			"/violationEvent/includes/violationEventsRowActionMenu";
	
	/* Model Keys */
	
	private static final String OFFENDER_MODEL_KEY = "offender";
	
	private static final String VIOLATION_EVENT_MODEL_KEY = "violationEvent";
	
	private static final String VIOLATION_EVENT_SUMMARIES_MODEL_KEY =
			"violationEventSummaries";
	
	private static final String CATEGORIES_MODEL_KEY = "categories";
	
	/* Service */
	
	@Autowired
	@Qualifier("violationEventSummaryReportService")
	private ViolationEventSummaryReportService
						violationEventSummaryReportService;
	
	/* Property Editor Factories */
	
	@Autowired
	@Qualifier("violationEventPropertyEditorFactory")
	private PropertyEditorFactory violationEventPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/* Delegates */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	
	/**
	 * Default constructor for ViolationEventReport Controller
	 */
	public ViolationEventReportController() {
	}
	
	
	
	/* Views */
	
	/**
	 * Returns the view for the ViolationEvents list screen for the specified
	 * offender
	 * @param offender - Offender
	 * @return ModelAndView - view for the ViolationEvents list screen
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN') or hasRole ('VIOLATION_EVENT_LIST')")
	public ModelAndView list(@RequestParam(value = "offender", required = true)
		final Offender offender){
		
		ModelMap map = new ModelMap();
		
		List<ViolationEventSummary> summaries =
				this.violationEventSummaryReportService.findByOffender(offender);
		
		map.addAttribute(VIOLATION_EVENT_SUMMARIES_MODEL_KEY, summaries);
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(map, offender);
		
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/**
	 * Returns the view for the violation events action menu
	 * @param offender - Offender
	 * @return ModelAndView - view for the violation events action menu
	 */
	@RequestMapping(value="/violationEventsActionMenu.html",
			method= RequestMethod.GET)
	public ModelAndView displayViolationEventsActionMenu(
			@RequestParam(value = "offender", required = true)
			final Offender offender){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(CATEGORIES_MODEL_KEY, ViolationEventCategory.values());
		
		return new ModelAndView(VIOLATION_EVENTS_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns the view for the violation events row action menu
	 * @param violationEvent - ViolationEvent
	 * @return ModelAndView - view for the violation events action menu
	 */
	@RequestMapping(value="/violationEventsRowActionMenu.html",
			method= RequestMethod.GET)
	public ModelAndView displayViolationEventsRowActionMenu(
			@RequestParam(value = "violationEvent", required = true)
			final ViolationEvent violationEvent){
		
		ModelMap map = new ModelMap();
		
		map.addAttribute(VIOLATION_EVENT_MODEL_KEY, violationEvent);
		
		return new ModelAndView(VIOLATION_EVENTS_ROW_ACTION_MENU_VIEW_NAME, map);
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
				ViolationEvent.class,
				this.violationEventPropertyEditorFactory
				.createPropertyEditor());
	}
}
