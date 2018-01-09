package omis.victim.report;

import java.util.List;

import omis.person.domain.Person;

/**
 * Report service for victims.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 29, 2015)
 * @since OMIS 3.0
 */
public interface VictimReportService {

	/**
	 * Finds victims by name.
	 * 
	 * @param lastName last name
	 * @param firstName first name
	 * @return victims by name
	 */
	List<VictimSummary> findByName(String lastName, String firstName);

	/**
	 * Summarizes victim.
	 * 
	 * @param victim victim
	 * @return summary of victim
	 */
	VictimSummary summarizeVictim(Person victim);
}