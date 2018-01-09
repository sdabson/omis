package omis.specialneed.report;

import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;
import omis.specialneed.domain.SpecialNeed;

/**
 * Special need report service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 9, 2016)
 * @since OMIS 3.0
 */
public interface SpecialNeedReportService {
	
	/**
	 * Summarize special need summary by offender.
	 *
	 *
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return list of special needs
	 */
	List<SpecialNeedSummary> summarizeByOffender(
			Offender offender, Date effectiveDate);
	
	/**
	 * Finds a list of special needs.
	 *
	 *
	 * @return list of special needs.
	 */
	List<SpecialNeed> find();
}