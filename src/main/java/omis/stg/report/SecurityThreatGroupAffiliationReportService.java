package omis.stg.report;

import java.util.Date;
import java.util.List;

import omis.offender.domain.Offender;

/**
 * Report service for security threat group affiliations.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 16, 2015)
 * @since OMIS 3.0
 */
public interface SecurityThreatGroupAffiliationReportService {

	/**
	 * Returns summaries of security threat group affiliations by offender.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date; used to determine which
	 * affiliations are active
	 * @return summaries of security threat group affiliations by offender
	 */
	List<SecurityThreatGroupAffiliationSummary> summarizeByOffender(
			Offender offender, Date effectiveDate);
}