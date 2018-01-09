package omis.custody.report;

import java.util.Date;

import omis.offender.domain.Offender;

/**
 * Custody review summary item service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jun 8, 2017)
 * @since OMIS 3.0
 */
public interface CustodyReviewSummaryItemService {
	
	/**
	 * Summarize custody review by offender and date.
	 *
	 *
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return custody review summary
	 */
	CustodySummary findCustodyReviewSummaryByOffenderAndDate(
			Offender offender, Date effectiveDate);		
}