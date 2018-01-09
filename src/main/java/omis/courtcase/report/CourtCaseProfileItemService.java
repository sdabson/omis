package omis.courtcase.report;

import omis.offender.domain.Offender;

/** Service for court case profile related operations.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 14, 2016)
 * @since OMIS 3.0 */
public interface CourtCaseProfileItemService {
	/** Find court case count by offender.
	 * @param offender - offender.
	 * @return count. */
	public Integer findCourtCaseCountByOffender(final Offender offender);

}
