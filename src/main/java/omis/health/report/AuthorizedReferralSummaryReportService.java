package omis.health.report;

import java.util.List;

import omis.facility.domain.Facility;
import omis.offender.domain.Offender;

/**
 * Report service for authorized referral summaries.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 15, 2014)
 * @since OMIS 3.0
 */
public interface AuthorizedReferralSummaryReportService {

	/**
	 * Returns unscheduled authorized referral summaries by facility.
	 * 
	 * @param facility facility
	 * @return unscheduled authorized referral summaries by facility
	 */
	List<AuthorizedReferralSummary> findUnscheduledByFacility(
			Facility facility);
	
	/**
	 * Returns unscheduled authorized referral summaries by offender.
	 * 
	 * @param offender offender
	 * @return unscheduled authorized referral summaries by offender
	 */
	List<AuthorizedReferralSummary> findUnscheduledByOffender(
			Offender offender);
}