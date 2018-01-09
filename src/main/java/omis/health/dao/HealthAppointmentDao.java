package omis.health.dao;

import omis.dao.GenericDao;
import omis.health.domain.HealthAppointment;
import omis.health.domain.InternalReferral;

/**
 * Health Appointment Data Access Object.
 *
 * @author Joel Norris
 * @author Ryan Johns
 * @version 0.1.1 (May 8, 2014)
 * @since OMIS 3.0
 */
public interface HealthAppointmentDao
	extends GenericDao<HealthAppointment> {

	/** find health appointment by internal referral.
	 * @param internalReferral internal referral.
	 * @return health appointment. */
	HealthAppointment findByInternalReferral(
			InternalReferral internalReferral);
}