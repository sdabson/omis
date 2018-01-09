package omis.victim.web.controller.delegate;

import java.util.Map;

import omis.contact.report.ContactReportService;
import omis.person.domain.Person;
import omis.victim.report.VictimReportService;
import omis.victim.report.VictimSummary;

/**
 * Delegate to manage victim summaries in a model.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 26, 2015)
 * @since OMIS 3.0
 */
public class VictimSummaryModelDelegate {

	/* Model keys. */
	
	private static final String VICTIM_SUMMARY_MODEL_KEY = "victimSummary";
	private static final String CONTACT_SUMMARY_MODEL_KEY = "contactSummary";
	
	/* Report services. */
	
	private final VictimReportService victimReportService;
	private final ContactReportService contactReportService;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate to manage victim summaries in a model.
	 * 
	 * @param victimReportService report service for victims
	 */
	public VictimSummaryModelDelegate(
			final VictimReportService victimReportService,
			final ContactReportService contactReportService) {
		this.victimReportService = victimReportService;
		this.contactReportService = contactReportService;
	}
	
	/* Model management methods. */
	
	/**
	 * Adds victim summary to model.
	 * 
	 * @param modelMap model map
	 * @param victim victim
	 */
	public void add(
			final Map<String, Object> modelMap, final Person victim) {
		VictimSummary victimSummary = this.victimReportService
				.summarizeVictim(victim);
		modelMap.put(VICTIM_SUMMARY_MODEL_KEY, victimSummary);
		modelMap.put(CONTACT_SUMMARY_MODEL_KEY, this.contactReportService.summarizeByPerson(victim));
	}
}