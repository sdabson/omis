package omis.offenderrelationship.report;

import java.util.List;

import omis.offender.domain.Offender;

/**
 * Offender relation report service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 11, 2015)
 * @since OMIS 3.0
 */
public interface OffenderRelationshipsReportService {

	/**
	 * Returns a list of all offender relationship summaries for the
	 * specified offender.
	 * 
	 * @param offender offender
	 * @return list of offender relationship summaries
	 */
	List<OffenderRelationshipsSummary> findByOffender(Offender offender);
}