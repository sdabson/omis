package omis.victim.report;

import java.util.List;

import omis.person.domain.Person;

/**
 * Report service for victim notes.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 24, 2015)
 * @since OMIS 3.0
 */
public interface VictimNoteReportService {

	/**
	 * Returns summaries of victim notes by victim.
	 * 
	 * @param victim victim
	 * @return summaries of victim notes by victim
	 */
	List<VictimNoteSummary> summarizeByVictim(Person victim);
}