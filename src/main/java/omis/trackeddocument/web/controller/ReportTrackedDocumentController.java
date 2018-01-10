package omis.trackeddocument.web.controller;

import java.util.ArrayList;
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
import omis.docket.domain.Docket;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;
import omis.trackeddocument.report.DocketDocumentReceivalSummary;
import omis.trackeddocument.report.DocketDocumentTrackingReportService;
import omis.trackeddocument.service.DocumentTrackingService;

/**
 * Controller for tracked document report.
 * 
 * @author Yidong Li
 * @version 0.1.5 (Jan 8, 2018)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/trackedDocumentReport")
@PreAuthorize("hasRole('USER')")
public class ReportTrackedDocumentController {
	/* views */
	private static final String LIST_VIEW_NAME = "trackedDocument/list";
	private static final String 
		TRACKED_DOCUMENT_LIST_SCREEN_ACTION_MENU_VIEW_NAME
		= "trackedDocument/includes/trackedDocumentListScreenActionMenu";
	private static final String 
		TRACKED_DOCUMENT_LIST_ROW_ACTION_MENU_VIEW_NAME
		= "trackedDocument/includes/trackedDocumentListRowActionMenu";
	
	/* model keys */
	private static final String DOCKET_MODEL_KEY = "docket";
	private static final String DOCKET_DOCUMENT_RECEIVAL_SUMMARIES_MODEL_KEY
	= "docketDocumentReceivalSummaries";
	private static final String OFFENDER_MODEL_KEY
	= "offender";
		
	/* Message bundles. */
				
	/* Redirects. */
	private static final String LIST_REDIRECT
		= "redirect:/trackedDocumentReport/list.html?offender=%d";
	
	/* Property editor. */
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	@Autowired
	@Qualifier("docketPropertyEditorFactory")
	private PropertyEditorFactory docketPropertyEditorFactory;
		
	/* Services. */
	@Autowired
	@Qualifier("documentTrackingService")
	private DocumentTrackingService documentTrackingService;
	
	@Autowired
	@Qualifier("docketDocumentTrackingReportService")
	private DocketDocumentTrackingReportService
	docketDocumentTrackingReportService;
	
	/* Delegate */
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Constructor. */
	/** Instantiates a default tracked document report controller. */
	public ReportTrackedDocumentController() {
		// Default instantiation
	}
	
	/**
	 * Displays a list of tracked document.
	 * 
	 * @param offender offender
	 * @return view to display the list of tracked documents
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('TRACKED_DOCUMENT_LIST') or hasRole('ADMIN')")
	public ModelAndView list(@RequestParam(value = "offender", required = true) 
		final Offender offender) {
		List<DocketDocumentReceivalSummary> docketDocumentReceivalSummaries
			= new ArrayList<DocketDocumentReceivalSummary>();
		docketDocumentReceivalSummaries 
		= this.docketDocumentTrackingReportService.summarizedByDefendant(
			(Person) offender);
		ModelAndView mav = new ModelAndView(LIST_VIEW_NAME);
		mav.addObject(DOCKET_DOCUMENT_RECEIVAL_SUMMARIES_MODEL_KEY,
			docketDocumentReceivalSummaries);
		mav.addObject(OFFENDER_MODEL_KEY, offender);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), offender);
		return mav;
	}
	
	/**
	 * Removes existing tracked document receival related to certain docket.
	 * 
	 * @param offender offender
	 * @return redirect to list screen
	 */
	@RequestMapping("/remove.html")
	@PreAuthorize("hasRole('TRACKED_DOCUMENT_REMOVE') or hasRole('ADMIN')")
	public ModelAndView remove(
		@RequestParam(value = "docket", required = true)
			final Docket docket) {
		Offender offender = (Offender) docket.getPerson();
		this.documentTrackingService.removeByDocket(docket);
		return new ModelAndView(String.format(LIST_REDIRECT, offender.getId()));
	}	
	
	/**
	 * Returns a view for document tracking list screen action menu pertaining
	 * to the specified offender.
	 * 
	 * @param offender offender
	 * @return model and view for document tracking list screen action menu
	 */
	@RequestMapping(value = "/trackedDocumentListScreenActionMenu.html",
	method = RequestMethod.GET)
	public ModelAndView trackedDocumentListScreenActionMenu(@RequestParam(
		value = "offender",	required = true) final Offender offender) {
		ModelMap map = new ModelMap();
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		return new ModelAndView(
			TRACKED_DOCUMENT_LIST_SCREEN_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Returns a view for document tracking list screen row action menu 
	 * pertaining to the specified docket.
	 * 
	 * @param docket docket
	 * @return model and view for document tracking row action menu
	 */
	@RequestMapping(value = "/trackedDocumentListRowActionMenu.html",
	method = RequestMethod.GET)
	public ModelAndView trackedDocumentListRowActionMenu(@RequestParam(
		value = "docket",	required = true) final Docket docket) {
		ModelMap map = new ModelMap();
		map.addAttribute(DOCKET_MODEL_KEY, docket);
		return new ModelAndView(
			TRACKED_DOCUMENT_LIST_ROW_ACTION_MENU_VIEW_NAME, map);
	}
	
	/**
	 * Sets up and registers property editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class,
			this.offenderPropertyEditorFactory.createOffenderPropertyEditor());
		binder.registerCustomEditor(Docket.class,
			this.docketPropertyEditorFactory.createPropertyEditor());
	}	
}