package omis.health.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.InternalReferralStatusReason;

/**
 * Data access object for internal referral status reasons.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 20, 2014)
 * @since OMIS 3.0
 */
public interface InternalReferralStatusReasonDao
		extends GenericDao<InternalReferralStatusReason> {

	/**
	 * Returns reasons for internal referral appointment statuses by
	 * appointment status.
	 * 
	 * @param appointmentStatus appointment status
	 * @return reasons for internal referral appointment statuses by
	 * appointment status
	 */
	List<InternalReferralStatusReason> findByAppointmentStatus(
			HealthAppointmentStatus appointmentStatus);
	
	/**
	 * Returns the reasons for the kept appointment status.
	 * 
	 * @return reason for kept appointment status
	 */
	InternalReferralStatusReason findKeptStatusReason();
}