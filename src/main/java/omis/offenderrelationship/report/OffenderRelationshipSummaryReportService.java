package omis.offenderrelationship.report;

import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;

/**
 * Summary report service for offender relationships.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jan 27, 2016)
 * @since OMIS 3.0
 */
public interface OffenderRelationshipSummaryReportService {

	/**
	 * Summarize list of offender relationships by offender.
	 * 
	 * @param offender offender 
	 * @param effectiveDate effective date
	 * @return list
	 */
	List<OffenderRelationshipSummary> summarize(Offender offender, 
		Date effectiveDate);
	
	/**
	 * Summarize list of offender relations by name.
	 * 
	 * @param lastName last name
	 * @param firstName first name
	 * @param effectiveDate effective date
	 * @return list
	 */
	List<OffenderRelationshipSummary> summarizeByName(String lastName, 
		String firstName, Date effectiveDate);
}