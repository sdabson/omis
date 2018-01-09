package omis.conviction.report;

import java.util.List;

import omis.person.domain.Person;

/**
 * Conviction summary report service.
 * @author Josh Divine
 * @version 0.1.0 (May 1, 2017)
 * @since OMIS 3.0
 */
public interface ConvictionSummaryReportService {

	/**
	 * Returns conviction summaries for the specified person.
	 * 
	 * @param person person
	 * @return conviction summaries
	 */
	List<ConvictionSummary> summarizeByPerson(Person person);
	
}
