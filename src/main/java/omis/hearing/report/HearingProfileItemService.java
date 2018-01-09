package omis.hearing.report;

import omis.offender.domain.Offender;

/**
 * Hearing profile item summary.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jun 26, 2017)
 * @since OMIS 3.0
 */
public interface HearingProfileItemService {
	
	/**
	 * Finds hearing profile item summary by offender.
	 *
	 *
	 * @param offender offender
	 * @return hearing profile
	 */
	HearingProfileItemSummary findHearingProfileItemSummaryByOffender(
			Offender offender);
}