package omis.violationevent.report;

import omis.offender.domain.Offender;

/**
 * Violation event profile item service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 13, 2017)
 * @since OMIS 3.0
 */
public interface ViolationEventProfileItemService {

	/**
	 * Find violation event count by offender.
	 *
	 * @param offender offender
	 * @return count
	 */
	Integer findViolationEventCountByOffender(Offender offender);
}