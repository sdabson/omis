package omis.health.dao;

import omis.dao.GenericDao;
import omis.health.domain.HealthAppointment;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.offender.domain.Offender;

/**
 * Offender appointment association data access object.
 *
 * @author Joel Norris
 * @author Ryan Johns
 * @version 0.1.0 (Apr 15, 2014)
 * @since OMIS 3.0
 */
public interface OffenderAppointmentAssociationDao
	extends GenericDao<OffenderAppointmentAssociation> {

	/** Find offender appointment association by business keys.
	 * @param offender offender.
	 * @param healthAppointment health appointment.
	 * @return offenderAppointmentAssociation offender appointment
	 * association. */
	OffenderAppointmentAssociation find(Offender offender,
	HealthAppointment healthAppointment);

	/** Find offender appointment association by business key excluding.
	 * @param offender offender.
	 * @param healthAppointment health appointment.
	 * @param exclude exclude.
	 * @return offenderAppointmentAssociation offender appointment
	 * association. */
	OffenderAppointmentAssociation findExcluding(Offender offender,
			HealthAppointment healthAppointment,
			OffenderAppointmentAssociation exclude);
}