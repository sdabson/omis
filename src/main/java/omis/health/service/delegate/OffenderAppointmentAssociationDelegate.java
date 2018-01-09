package omis.health.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.facility.domain.Facility;
import omis.health.dao.HealthAppointmentDao;
import omis.health.dao.OffenderAppointmentAssociationDao;
import omis.health.domain.HealthAppointment;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Delegate for offender appointment associations.
 *
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.0 (Apr 28, 2014)
 * @since OMIS 3.0
 */
public class OffenderAppointmentAssociationDelegate {

	/* Data access objects. */

	private final HealthAppointmentDao healthAppointmentDao;

	private final OffenderAppointmentAssociationDao
	offenderAppointmentAssociationDao;

	/* Instance Factories */

	private final InstanceFactory<OffenderAppointmentAssociation>
	offenderAppointmentAssociationInstanceFactory;

	private final InstanceFactory<HealthAppointment>
	healthAppointmentInstanceFactory;

	/* Component retrievers. */

	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Instantiates a delegate for offender appointment associations.
	 *
	 * @param offenderAppointmentAssociationInstanceFactory instance factory
	 * for offender appointment associations
	 * @param healthAppointmentInstanceFactory instance factory for health
	 * appointments
	 * @param auditComponentRetriever retriever of audit components
	 * @param healthAppointmentDao health appointment dao
	 * @param offenderAppointmentAssociationDao offender appointment
	 * association dao
	 */
	public OffenderAppointmentAssociationDelegate(
			final InstanceFactory<OffenderAppointmentAssociation>
				offenderAppointmentAssociationInstanceFactory,
			final InstanceFactory<HealthAppointment>
				healthAppointmentInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever,
			final HealthAppointmentDao healthAppointmentDao,
			final OffenderAppointmentAssociationDao
			offenderAppointmentAssociationDao) {
		this.offenderAppointmentAssociationInstanceFactory
			= offenderAppointmentAssociationInstanceFactory;
		this.healthAppointmentInstanceFactory
			= healthAppointmentInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
		this.healthAppointmentDao = healthAppointmentDao;
		this.offenderAppointmentAssociationDao =
				offenderAppointmentAssociationDao;
	}

	/**
	 * Creates an returns an offender appointment association.
	 *
	 * <p>Persists entities.
	 *
	 * @param offender offender
	 * @param date date
	 * @param startTime start time
	 * @param endTime end time
	 * @return offender appointment association
	 */
	public OffenderAppointmentAssociation create(
			final Offender offender, final Date date,
			final Facility facility, final Date startTime,
			final Date endTime) {
		final HealthAppointment appointment
			= this.healthAppointmentInstanceFactory.createInstance();
		appointment.setDate(date);
		appointment.setStartTime(startTime);
		appointment.setEndTime(endTime);
		appointment.setFacility(facility);

		this.healthAppointmentDao.makePersistent(appointment);
		final OffenderAppointmentAssociation association
			= this.offenderAppointmentAssociationInstanceFactory
				.createInstance();
		association.setAppointment(appointment);
		association.setOffender(offender);
		association.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		association.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.offenderAppointmentAssociationDao.makePersistent(association);
		return association;
	}
	
	/**
	 * Persists changes to an existing offender appointment association.
	 * 
	 * @param offenderAppointmentAssociation offender appointment association
	 * to update
	 * @return updated offender appointment association
	 */
	public OffenderAppointmentAssociation update(
			final OffenderAppointmentAssociation
				offenderAppointmentAssociation) {
		return this.offenderAppointmentAssociationDao
				.makePersistent(offenderAppointmentAssociation);
	}
}