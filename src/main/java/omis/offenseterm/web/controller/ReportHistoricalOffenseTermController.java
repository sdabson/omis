package omis.offenseterm.web.controller;

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
import omis.contact.web.controller.delegate.ContactSummaryModelDelegate;
import omis.conviction.domain.Conviction;
import omis.offender.domain.Offender;
import omis.offender.report.OffenderReportService;
import omis.offender.report.OffenderSummary;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;
import omis.person.domain.Person;
import omis.sentence.domain.Sentence;
import omis.sentence.report.SentenceReportService;
import omis.sentence.report.SentenceSummary;

/**
 * Controller to report historical offense terms.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Controller
@PreAuthorize("hasRole('USER')")
@RequestMapping("/offenseTerm")
public class ReportHistoricalOffenseTermController {
	
	/* View names */
	
	private static final String VIEW_NAME
		= "offenseTerm/listHistoricalOffenseTerms";
	
	/* Action menus. */
	
	private static final String HISTORICAL_OFFENSE_TERMS_ACTION_MENU_VIEW_NAME
		= "offenseTerm/includes/historicalOffenseTermsActionMenu";
	
	/* Model keys */
	
	private static final String INACTIVE_SUMMARIES_MODEL_KEY
		= "inactiveSentenceSummaries";
	
	private static final String CONVICTION_MODEL_KEY = "conviction";
	
	private static final String SENTENCE_MODEL_KEY = "sentence";
	
	/* Report services */
	
	@Autowired
	@Qualifier("sentenceReportService")
	private SentenceReportService sentenceReportService;
	
	@Autowired
	@Qualifier("offenderReportService")
	private OffenderReportService offenderReportService;

	/* Property editor factories */
	
	@Autowired
	@Qualifier("convictionPropertyEditorFactory")
	private PropertyEditorFactory convictionPropertyEditorFactory;
	
	@Autowired
	@Qualifier("sentencePropertyEditorFactory")
	private PropertyEditorFactory sentencePropertyEditorFactory;
	
	/* Helpers. */
	
	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	@Autowired
	@Qualifier("contactSummaryModelDelegate")
	private ContactSummaryModelDelegate contactSummaryModelDelegate;
	
	/* Constructors */
	
	/** Instantiates controller to report historical offenses. */
	public ReportHistoricalOffenseTermController() {
		// Default instantiation
	}
	
	/* URL invokable methods */
	
	/**
	 * Shows screen to list historical offense terms.
	 * 
	 * @param conviction conviction
	 * @return screen to list historical offense terms
	 */
	@RequestMapping(
			value = "/listHistoricalOffenseTerms.html",
			method = RequestMethod.GET)
	@PreAuthorize("hasRole('OFFENSE_TERM_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "conviction", required = true)
				final Conviction conviction) {
		List<SentenceSummary> inactiveSentenceSummaries
			= this.sentenceReportService
				.summarizeInactiveSentencesByConviction(conviction);
		Person person = conviction.getCourtCase().getDocket().getPerson();
		OffenderSummary offenderSummary = this.offenderReportService
				.summarizeIfOffender(person);
		ModelAndView mav = new ModelAndView(VIEW_NAME);
		mav.addObject(INACTIVE_SUMMARIES_MODEL_KEY, inactiveSentenceSummaries);
		mav.addObject(CONVICTION_MODEL_KEY, conviction);
		if (offenderSummary != null) {

			// TODO - Add report service method that returns offender from
			// person - SA
			this.offenderSummaryModelDelegate.add(mav.getModelMap(),
						(Offender) person);
		} else {
			this.contactSummaryModelDelegate.add(mav.getModelMap(), person);
		}
		return mav;
	}
	
	/* Action menus */
	
	/**
	 * Shows action menu for historical offense terms.
	 * 
	 * @param sentence sentence
	 * @param conviction conviction
	 * @return action menu for historical offense terms
	 */
	@RequestMapping(
			value = "/historicalOffenseTermsActionMenu.html",
			method = RequestMethod.GET)
	public ModelAndView showHistoricalOffenseTermsActionMenu(
			@RequestParam(value = "sentence", required = false)
				final Sentence sentence,
			@RequestParam(value = "conviction", required = false)
				final Conviction conviction) {
		ModelAndView mav = new ModelAndView(
				HISTORICAL_OFFENSE_TERMS_ACTION_MENU_VIEW_NAME);
		mav.addObject(SENTENCE_MODEL_KEY, sentence);
		mav.addObject(CONVICTION_MODEL_KEY, conviction);
		return mav;
	}
	
	/* Init binders */
	
	/**
	 * Registers property editors.
	 * 
	 * @param binder data binder
	 */
	@InitBinder
	protected void registerPropertyEditorFactories(
			final WebDataBinder binder) {
		binder.registerCustomEditor(Conviction.class,
				this.convictionPropertyEditorFactory.createPropertyEditor());
		binder.registerCustomEditor(Sentence.class,
				this.sentencePropertyEditorFactory.createPropertyEditor());
	}
}