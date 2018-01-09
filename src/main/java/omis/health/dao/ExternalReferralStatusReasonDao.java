package omis.health.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.health.domain.ExternalReferralStatusReason;
import omis.health.domain.HealthAppointmentStatus;

/**
 * Data access object for external referral status reasons.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 24, 2014)
 * @since OMIS 3.0
 */
public interface ExternalReferralStatusReasonDao
		extends GenericDao<ExternalReferralStatusReason> {

	/**
	 * Returns reasons for external referral appointment statuses by
	 * appointment status.
	 * 
	 * @param appointmentStatus appointment status
	 * @return reasons for external referral appointment statuses by
	 * appointment status
	 */
	List<ExternalReferralStatusReason> findByAppointmentStatus(
			HealthAppointmentStatus appointmentStatus);
	
	/**
	 * Returns the reasons for the kept appointment status.
	 * 
	 * @return reason for kept appointment status
	 */
	ExternalReferralStatusReason findKeptStatusReason();
}