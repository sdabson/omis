package omis.health.report;

import java.util.List;

import omis.facility.domain.Facility;
import omis.offender.domain.Offender;

/**
 * Report service for external referral requests. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Oct 1, 2014)
 * @since OMIS 3.0
 */
public interface ExternalReferralRequestReportService {

	/**
	 * Returns summaries for external referral requests by facility.
	 * 
	 * @param facility facility
	 * @return summaries for external referral requests by facility
	 */
	List<ExternalReferralRequestSummary> findByFacility(Facility facility);
	
	/**
	 * Returns summaries for external referral requests by offender.
	 * 
	 * @param offender offender
	 * @return summaries for external referral requests by offender
	 */
	List<ExternalReferralRequestSummary> findByOffender(Offender offender);
}
