package omis.health.report;

import java.util.List;

import omis.facility.domain.Facility;

/** Service to report referrals.
 * @author Ryan Johns
 * @version 0.1.1 (Apr 30, 2014)
 * @since OMIS 3.0 */
public interface InternalReferralReportService {

	/** Returns scheduled referrals for the facility.
	 * @param facility facility.
	 * @return scheduled referrals for the facility. */
	List<ScheduledInternalReferralSummary> findScheduledInternalReferrals(
			Facility facility);

	/** Returns resolved referrals for the facility.
	 * @param facility facility.
	 * @return resolved referrals for the facility. */
	List<ResolvedInternalReferralSummary> findResolvedInternalReferrals(Facility facility);
}
