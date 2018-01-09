package omis.person.report;

import omis.person.domain.Person;

/**
 * Report service for person summary.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 16, 2016)
 * @since OMIS 3.0
 */
public interface PersonSummaryReportService {

	/**
	 * Summarizes the specified person.
	 * 
	 * @param person person
	 * @return person summary
	 */
	PersonSummary summarize(Person person); 
}