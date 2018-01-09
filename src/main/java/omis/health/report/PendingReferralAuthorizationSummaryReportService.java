package omis.health.report;

import java.util.List;

import omis.facility.domain.Facility;
import omis.offender.domain.Offender;

/**
 * Report service for summaries of pending referral authorizations.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Sep 3, 2014)
 * @since OMIS 3.0
 */
public interface PendingReferralAuthorizationSummaryReportService {

	/**
	 * Returns summaries of pending referral authorizations by facility.
	 * 
	 * @param facility facility
	 * @return summaries of pending referral authorizations by facility
	 */
	List<PendingReferralAuthorizationSummary> findByFacility(Facility facility);
	
	/**
	 * Returns summaries of pending referral authorizations by offender.
	 * 
	 * @param offender offender
	 * @return summaries of pending referral authorizations by offender
	 */
	List<PendingReferralAuthorizationSummary> findByOffender(Offender offender);
}