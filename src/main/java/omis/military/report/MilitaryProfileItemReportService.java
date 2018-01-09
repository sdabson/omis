package omis.military.report;

import omis.offender.domain.Offender;

/** Report service for military profile item.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 16, 2016)
 * @since OMIS 3.0 */
public interface MilitaryProfileItemReportService {
	/** Find existence of service term by offender.
	 * @param offender - offender.
	 * @return true if service terms exist. */
	boolean findMilitaryServiceTermExistenceByOffender(Offender offender);

}
