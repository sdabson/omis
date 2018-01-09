package omis.courtcase.report;

import java.util.List;

import omis.person.domain.Person;

/**
 * Report service for court case summary.
 * 
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.1 (Aug 15, 2017)
 * @since OMIS 3.0
 */
public interface CourtCaseSummaryReportService {

	/**
	 * Returns all court cases with the specified person.
	 * 
	 * @param person person
	 * @return list of court case summaries
	 */
	List<CourtCaseSummary> summarizeByPerson(Person person);
}