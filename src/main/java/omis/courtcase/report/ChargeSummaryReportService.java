package omis.courtcase.report;

import java.util.List;

import omis.person.domain.Person;

/**
 * Report service for charge summary.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Aug 6, 2017)
 * @since OMIS 3.0
 */
public interface ChargeSummaryReportService {

	/**
	 * Returns pending charges for the specified person.
	 * 
	 * @param person person
	 * @return pending charges
	 */
	List<ChargeSummary> summarizePendingChargesByOffender(Person person);
}
