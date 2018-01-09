package omis.victim.web.controller;

import java.util.List;

import omis.beans.factory.PropertyEditorFactory;
import omis.person.domain.Person;
import omis.victim.report.VictimNoteReportService;
import omis.victim.report.VictimNoteSummary;
import omis.victim.web.controller.delegate.VictimSummaryModelDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller to report victim notes.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 24, 2015)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/victim/note")
public class ReportVictimNotesController {
	
	/* Property editor factories. */
	
	private static final String VIEW_NAME = "victim/note/list";
	
	private static final String ACTION_MENU_VIEW_NAME
		= "victim/note/includes/victimNotesActionMenu";
	
	/* Model keys. */

	private static final String NOTE_SUMMARIES_MODEL_KEY = "noteSummaries";

	@Autowired
	@Qualifier("personPropertyEditorFactory")
	private PropertyEditorFactory personPropertyEditorFactory;

	/* Report services. */
	
	@Autowired
	@Qualifier("victimNoteReportService")
	private VictimNoteReportService victimNoteReportService;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("victimSummaryModelDelegate")
	private VictimSummaryModelDelegate victimSummaryModelDelegate;
	
	/* Constructors. */
	
	/** Instantiates controller to report victim notes. */
	public ReportVictimNotesController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Lists victim notes.
	 * 
	 * @param victim victim for whom to list notes
	 * @return screen to list victim notes
	 */
	@PreAuthorize("hasRole('ADMIN') or hasRole('VICTIM_NOTE_LIST')")
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list(
			@RequestParam(value = "victim", required = true)
				final Person victim) {
		List<VictimNoteSummary> summaries = this.victimNoteReportService
				.summarizeByVictim(victim);
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(NOTE_SUMMARIES_MODEL_KEY, summaries);
		this.victimSummaryModelDelegate.add(mav.getModelMap(), victim);
		return mav;
	}
	
	/* Action menus. */
	
	/**
	 * Shows action menu for victim notes.
	 * 
	 * @param victim victim
	 * @return action menu for victim notes
	 */
	@RequestMapping(
			value = "/victimNotesActionMenu.html", method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "victim", required = true)
				final Person victim) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		this.victimSummaryModelDelegate.add(mav.getModelMap(), victim);
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
		binder.registerCustomEditor(Person.class,
				this.personPropertyEditorFactory.createPropertyEditor());
	}
}