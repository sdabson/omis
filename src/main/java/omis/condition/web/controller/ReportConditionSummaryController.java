package omis.condition.web.controller;

import java.util.Date;
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

import omis.condition.report.AgreementSummary;
import omis.condition.report.ConditionSummaryReportService;
import omis.offender.beans.factory.OffenderPropertyEditorFactory;
import omis.offender.domain.Offender;
import omis.offender.web.controller.delegate.OffenderSummaryModelDelegate;

 /**Controller to report Condition Summaries
 * @author Jonny Santy
 * @version 0.1.0 (July 8, 2015)
 * @since OMIS 3.0
 */
@Controller
@RequestMapping("/condition")
@PreAuthorize("hasRole('USER')")
public class ReportConditionSummaryController {

	/* View names. */
	
	private static final String LIST_VIEW_NAME = "/condition/list";
	
	/* Action menu view names. */
	
	//has no action menu
	
	/* Model keys. */

	private static final String AGREEMENT_SUMMARIES_MODEL_KEY
		= "agreementSummaries";
	
	private static final String EFFECTIVE_DATE_KEY = "effectiveDate";

	private static final String OFFENDER_MODEL_KEY = "offender";
	

	/* Controller Model Delegates */

	@Autowired
	@Qualifier("offenderSummaryModelDelegate")
	private OffenderSummaryModelDelegate offenderSummaryModelDelegate;
	
	/* Services. */
	@Autowired
	@Qualifier("conditionSummaryReportService")
	private ConditionSummaryReportService conditionSummaryReportService;
	
	/* Property editor factories. */
	@Autowired
	@Qualifier("offenderPropertyEditorFactory")
	private OffenderPropertyEditorFactory offenderPropertyEditorFactory;
	
	
	/* Constructor. */
	/**
	 * Instantiation controller to report security threat group affiliations.
	 */
	public ReportConditionSummaryController() {
		// Default instantiation
	}
	
	/**
	 * Displays a list of security threat group affiliations by offender.
	 * 
	 * @param offender offender
	 * @param effectiveDate effectiveDate
	 * @return screen to display list of security threat group affiliations by
	 * offender
	 */
	@RequestMapping(value = "/list.html", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CONDITION_LIST') or hasRole('ADMIN')")
	public ModelAndView list(
			@RequestParam(value = "offender", required = true)
			final Offender offender,
			@RequestParam(value = "date", required = false) Date effectiveDate) {
		ModelMap map = new ModelMap();
		if(effectiveDate==null){
			effectiveDate = new Date();
		}
		
		//Get Agreement Summaries related to the passed in Offender
		List<AgreementSummary> agreementSummaries =
				this.conditionSummaryReportService.findByOffender(offender,
				effectiveDate);
		
		map.addAttribute(OFFENDER_MODEL_KEY, offender);
		map.addAttribute(EFFECTIVE_DATE_KEY, effectiveDate);
		map.addAttribute(AGREEMENT_SUMMARIES_MODEL_KEY, 
				agreementSummaries);
		this.offenderSummaryModelDelegate.add(map, offender);
		return new ModelAndView(LIST_VIEW_NAME, map);
	}
	
	/* Init binder. */
	
	/**
	 * Sets up init binder.
	 * 
	 * @param binder init binder
	 */
	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
		binder.registerCustomEditor(Offender.class, 
				this.offenderPropertyEditorFactory
				.createOffenderPropertyEditor());
	}
}
