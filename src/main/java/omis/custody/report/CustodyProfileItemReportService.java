package omis.custody.report;

import omis.offender.domain.Offender;

/** Service for custody profile item related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 14, 2016)
 * @since OMIS 3.0 */
public interface CustodyProfileItemReportService {
	/** Finds custody count by offender.
	 * @param offender - offender. */
	Integer findCustodyCountByOffender(Offender offender);
}
