package omis.health.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.LabWorkReferralStatusReason;

/**
 * Lab work referral status reason data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 12, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkReferralStatusReasonDao 
extends GenericDao<LabWorkReferralStatusReason> {

	/**
	 * Returns a list of lab work referral status reasons that are
	 * applicable to the specified health appointment status.
	 * 
	 * @param appointmentStatus health appointment status
	 * @return list of relevant lab work referral status reasons
	 */
	List<LabWorkReferralStatusReason> findByAppointmentStatus(
			HealthAppointmentStatus appointmentStatus);

	/**
	 * Returns the lab work referral status reason with the health appointment
	 * status {@code KEPT}.
	 * 
	 * @return lab work referral status reason
	 */
	LabWorkReferralStatusReason findKeptStatusReason();
}