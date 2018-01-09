package omis.hearing.report;

import omis.offender.domain.Offender;

/**
 * Violation profile item service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public interface ViolationProfileItemService {
	
	/**
	 * Finds violation summary by offender.
	 *
	 *
	 * @param offender offender
	 * @return violation summary
	 */
	ViolationProfileSummary findViolationSummaryByOffender(Offender offender);
}