package omis.physicalfeature.report;

import omis.offender.domain.Offender;

/** Report service for physical feature profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public interface PhysicalFeatureProfileItemReportService {
	/** find physical feature count by offender.
	 * @param offender - offender.
	 * @return count. */
	Integer findPhysicalFeatureCountByOffender(Offender offender);
}
