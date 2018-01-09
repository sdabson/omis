package omis.commitstatus.report;

import java.util.Date;

import omis.commitstatus.domain.CommitStatusTerm;
import omis.offender.domain.Offender;

/**
 * Report service for commit status profile items.
 *
 * @author Yidong Li
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface CommitStatusTermProfileItemReportService {

	/**
	 * Returns count of commit status term effective on date.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return commit status term effective on date
	 */
	CommitStatusTerm findCommitStatusTermCountByOffenderAndDate(
			Offender offender, Date effectiveDate);
}