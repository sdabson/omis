package omis.dna.report;

import omis.offender.domain.Offender;

/** Service for dna profile item related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 15, 2016)
 * @since OMIS 3.0 */
public interface DnaProfileItemReportService {
	/** Finds dna count by offender.
	 * @param offender - offender.
	 * @return count. */
	Integer findDnaCountByOffender(Offender offender);
}
