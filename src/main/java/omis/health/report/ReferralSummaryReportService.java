package omis.health.report;

import java.util.Date;
import java.util.List;

import omis.facility.domain.Facility;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.HealthRequest;
import omis.health.exception.HealthRequestFollowsUpMultipleReferralsException;
import omis.offender.domain.Offender;

/**
 * Report service for referral summaries.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 9, 2014)
 * @since OMIS 3.0
 */
public interface ReferralSummaryReportService {

	/**
	 * Returns the internal referral that resulted in the action request.
	 * 
	 * <p>If no such referral exists, return {@code null}.
	 * 
	 * @param actionRequest action request
	 * @param effectiveDate effective date
	 * @return referral that resulted in action request
	 */
	ReferralSummary findInternalForActionRequest(HealthRequest actionRequest,
			Date effectiveDate);
	
	/**
	 * Returns the external referral that resulted in the action request.
	 * 
	 * <p>If no such referral exists, return {@code null}.
	 * 
	 * @param actionRequest action request
	 * @param effectiveDate effective date
	 * @return referral that resulted in action request
	 */
	ReferralSummary findExternalForActionRequest(HealthRequest actionRequest,
			Date effectiveDate);
	
	/**
	 * Returns the referral that resulted in the action request.
	 * 
	 * <p>If no such referral exists, return {@code null}.
	 * 
	 * @param actionRequest action request
	 * @param effectiveDate effective date
	 * @return referral that resulted in action request
	 * @throws HealthRequestFollowsUpMultipleReferralsException if more than
	 * one referral resulted in the action request
	 */
	ReferralSummary findForActionRequest(HealthRequest actionRequest,
			Date effectiveDate)
					throws HealthRequestFollowsUpMultipleReferralsException;
	/**
	 * Returns referral summaries by facility.
	 * 
	 * @param facility facility
	 * @param startDate start date
	 * @param endDate end date
	 * @param types referral types
	 * @param statuses appointment statuses
	 * @param effectiveDate effective date
	 * @return referral summaries by facility
	 */
	List<ReferralSummary> findByFacility(Facility facility,
			Date startDate, Date endDate,
			ReferralType[] types, HealthAppointmentStatus[] statuses,
			Date effectiveDate);
	
	/**
	 * Returns referral summaries by offender.
	 * 
	 * @param offender offender
	 * @param startDate start date
	 * @param endDate end date
	 * @param types referral types
	 * @param statuses appointment statuses
	 * @param effectiveDate effective date
	 * @return referral summaries by offender
	 */
	List<ReferralSummary> findByOffender(Offender offender,
			Date startDate, Date endDate,
			ReferralType[] types, HealthAppointmentStatus[] statuses,
			Date effectiveDate);
}