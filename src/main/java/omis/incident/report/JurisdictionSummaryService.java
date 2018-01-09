package omis.incident.report;

import java.util.List;

/**
 * Jurisdiction summary service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 3, 2015)
 * @since OMIS 3.0
 */
public interface JurisdictionSummaryService {

	/**
	 * Returns a summary of all jurisdictions.
	 * 
	 * @return list of jurisdiction summaries
	 */
	List<JurisdictionSummary> summarizeJurisdictions();
}