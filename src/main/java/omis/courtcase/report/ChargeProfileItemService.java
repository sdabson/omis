package omis.courtcase.report;

import omis.offender.domain.Offender;

/** 
 * Service for charge profile related operations.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Aug 16, 2017)
 * @since OMIS 3.0 
 */
public interface ChargeProfileItemService {
	
	/** 
	 * Find charge count by offender.
	 * @param offender offender
	 * @return count
	 */
	public Integer findChargeCountByOffender(final Offender offender);

}
