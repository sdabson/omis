package omis.offenderrelationship.report;
import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;

/**
 * Service for offender relationship.
 * 
* @author Yidong Li
 * @version 0.1.0 (Jan 12, 2016)
 * @since OMIS 3.0
 */
public interface OffenderRelationReportService {
	/**
	 * Returns offender relationship summary for specific offender.
	 * 
	 * @param offender offender
	 * @param currentDate current date
	 * @return profile association for offender
	 */
	List<OffenderRelationSummary> summarizeByOffender(Offender offender, 
		Date currentDate);
}