package omis.probationterm.web.controller;

import java.util.Date;
import java.util.List;

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

import omis.beans.factory.PropertyEditorFactory;
import omis.courtcase.domain.CourtCase;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.probationterm.domain.ProbationTerm;
import omis.probationterm.report.ProbationTermReportService;
import omis.probationterm.report.ProbationTermSummary;

/**
 * Controller for probation terms.
 *
 * @author Josh Divine
 * @version 0.0.1 (May 24, 2017)
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/probationTerm")
public class ReportProbationTermController {

	/* View names. */
	
	private static final String VIEW_NAME = "probationTerm/list";
	
	/* Action menus. */
	
	private static final String ACTION_MENU_VIEW_NAME
		= "probationTerm/includes/probationTermsActionMenu";

	/* Model keys. */

	private static final String COURT_CASE_MODEL_KEY = "courtCase";
	
	private static final String PROBATION_TERM_MODEL_KEY = "probationTerm";
	
	private static final String OFFENDER_MODEL_KEY = "offender"; 
	
	private static final String PROBATION_TERM_SUMMARIES_MODEL_KEY 
		= "probationTermSummaries";
	
	/* Services. */
	
	/* Property editor factories. */
	
	@Autowired
	@Qualifier("courtCasePropertyEditorFactory")
	private PropertyEditorFactory courtCasePropertyEditorFactory;
	
	@Autowired
	@Qualifier("probationTermPropertyEditorFactory")
	private PropertyEditorFactory probationTermPropertyEditorFactory;
	
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	/* Validators. */
	
	/* Report services. */
	
	@Autowired
	@Qualifier("probationTermReportService")
	private ProbationTermReportService probationTermReportService;
	
	/* Helpers. */

	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Constructors. */
	
	/** Instantiates controller for probation terms. */
	public ReportProbationTermController() {
		// Default instantiation
	}
	
	/* URL invokable methods. */
	
	/**
	 * Shows screen that lists
	 * 
	 * @param courtCase courtCase
	 * @param offender offender
	 * @return screen that lists probation terms
	 */
	@PreAuthorize("hasRole('PROBATION_TERM_LIST') or hasRole('ADMIN')")
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	public ModelAndView list(
			@RequestParam(value = "courtCase", required = false)
			final CourtCase courtCase,
			@RequestParam(value = "offender", required = false)
				final Offender offender) {
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		List<ProbationTermSummary> probationTermSummaries;
		Offender ofndr;
		if (offender != null) {
			probationTermSummaries = this.probationTermReportService
					.summarizeByOffender(offender, new Date());
			ofndr = offender;
		} else if(courtCase != null) {
			probationTermSummaries = this.probationTermReportService
					.summarizeByCourtCase(courtCase);
			ofndr = (Offender)courtCase.getDocket().getPerson();
		} else {
			throw new RuntimeException(
					"Must provide an offender or a court case.");
		}
		mav.addObject(OFFENDER_MODEL_KEY, ofndr);
		mav.addObject(COURT_CASE_MODEL_KEY, courtCase);
		mav.addObject(PROBATION_TERM_SUMMARIES_MODEL_KEY, 
				probationTermSummaries);
		this.offenderSummaryModelDelegate.add(mav.getModelMap(), ofndr);
		return mav;
	}
	
	/* Action menus. */
	
	/**
	 * Shows action menu for probation terms.
	 * 
	 * @param probationTerm probation term
	 * @param courtCase court case
	 * @return action menu for probation terms
	 */
	@RequestMapping(value = "/probationTermsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showActionMenu(
			@RequestParam(value = "probationTerm", required = false)
				final ProbationTerm probationTerm,
			@RequestParam(value = "courtCase", required = false)
				final CourtCase courtCase) {
		ModelAndView mav = new ModelAndView(ACTION_MENU_VIEW_NAME);
		mav.addObject(PROBATION_TERM_MODEL_KEY, probationTerm);
		mav.addObject(COURT_CASE_MODEL_KEY, courtCase);
		return mav;
	}
	
	/* Init binders. */
	
	/**
	 * Registers custom editors.
	 * 
	 * @param binder web binder
	 */
	@InitBinder
	protected void registerCustomEditors(final WebDataBinder binder) {
		binder.registerCustomEditor(CourtCase.class,
				this.courtCasePropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(ProbationTerm.class, 
				this.probationTermPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
	}
}
