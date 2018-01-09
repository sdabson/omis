package omis.health.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.dao.FacilityDao;
import omis.facility.domain.Facility;
import omis.health.dao.HealthAppointmentDao;
import omis.health.dao.LabDao;
import omis.health.dao.LabWorkCategoryDao;
import omis.health.dao.LabWorkDao;
import omis.health.dao.OffenderAppointmentAssociationDao;
import omis.health.dao.ProviderAssignmentDao;
import omis.health.domain.HealthAppointment;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkResults;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.service.LabWorkManager;
import omis.instance.factory.InstanceFactory;
import omis.location.domain.Location;
import omis.offender.domain.Offender;

/**
 * Lab work scheduler implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 5, 2014)
 * @since OMIS 3.0
 */
public class LabWorkManagerImpl implements LabWorkManager {
	
	/* Data access objects. */
	
	private LabDao labDao;
	
	private LabWorkDao labWorkDao;
	
	private HealthAppointmentDao healthAppointmentDao;
	
	private OffenderAppointmentAssociationDao offenderAppointmentAssociationDao;
	
	private LabWorkCategoryDao labWorkCategoryDao;
	
	private ProviderAssignmentDao providerAssignmentDao;
	
	private FacilityDao facilityDao;
	
	/* Instance factories. */
	
	private InstanceFactory<LabWork> labWorkInstanceFactory;
	
	private InstanceFactory<OffenderAppointmentAssociation> 
	offenderAppointmentAssociationInstanceFactory;
	
	private InstanceFactory<HealthAppointment> 
	healthAppointmentInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates an instance of lab work scheduler with the specified
	 * data access objects and instance factories.
	 * 
	 * @param labDao lab data access object
	 * @param labWorkDao lab work data access object
	 * @param healthAppointmentDao health appointment data access object
	 * @param offenderAppointmentAssociationDao offender appointment association
	 * data access object
	 * @param labWorkCategoryDao lab work category data access object
	 * @param providerAssignmentDao provider assignment data access object
	 * @param labWorkRequirementDao lab work requirement data access object
	 * @param facilityDao facility data access object
	 * @param labWorkInstanceFactory lab work instance factory
	 * @param offenderAppointmentAssociationInstanceFactory offender appointment
	 * association instance factory
	 * @param healthAppointmentInstanceFactory health appointment instance
	 * factory 
	 * @param auditComponentRetriever audit component retriever
	 */
	public LabWorkManagerImpl(final LabDao labDao, 
			final LabWorkDao labWorkDao,
			final HealthAppointmentDao healthAppointmentDao,
			final OffenderAppointmentAssociationDao 
			offenderAppointmentAssociationDao, 
			final LabWorkCategoryDao labWorkCategoryDao,
			final ProviderAssignmentDao providerAssignmentDao,
			final FacilityDao facilityDao,
			final InstanceFactory<LabWork> labWorkInstanceFactory,
			final InstanceFactory<OffenderAppointmentAssociation>
			offenderAppointmentAssociationInstanceFactory, 
			final InstanceFactory<HealthAppointment> 
			healthAppointmentInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.labDao = labDao;
		this.labWorkDao = labWorkDao;
		this.healthAppointmentDao = healthAppointmentDao;
		this.offenderAppointmentAssociationDao = 
				offenderAppointmentAssociationDao;
		this.labWorkCategoryDao = labWorkCategoryDao;
		this.providerAssignmentDao = providerAssignmentDao;
		this.facilityDao = facilityDao;
		this.labWorkInstanceFactory = labWorkInstanceFactory;
		this.offenderAppointmentAssociationInstanceFactory = 
				offenderAppointmentAssociationInstanceFactory;
		this.healthAppointmentInstanceFactory = 
				healthAppointmentInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/** {@inheritDoc} */
	@Override
	public LabWork scheduleLabWork(final Facility facility, 
			final Offender offender, final LabWorkCategory category, 
			final Lab sampleLab, final Date sampleDate, 
			final Boolean sampleTaken, final String sampleNotes, 
			final LabWorkResults results, final LabWorkOrder order, 
			final LabWorkSampleRestrictions sampleRestrictions, 
			final String schedulingNotes)
		throws DuplicateEntityFoundException {
		final HealthAppointment appointment;
		if (sampleTaken != null && sampleTaken) {
			appointment = this.createHealthAppointment(null, 
					null, HealthAppointmentStatus.KEPT, null, sampleDate, 
					facility);
		} else {
			appointment = this.createHealthAppointment(null, 
					null, null, null, sampleDate, facility);
		}
		OffenderAppointmentAssociation association =
				this.createOffenderAppointmentAssociation(appointment, 
						offender);
		LabWork labWork = this.createLabWork(sampleLab, sampleNotes, results, 
				order, schedulingNotes, sampleRestrictions, association, 
				category, sampleTaken);
		return labWork;
	}

	/** {@inheritDoc} */
	@Override
	public LabWork requestReschedule(final LabWork labWork) {
		labWork.setRescheduleRequired(true);
		return labWork;
	}

	/** {@inheritDoc} */
	@Override
	public LabWork reschedule(final LabWork labWork, final Date date, 
			final Date starttime, final String notes) {
		//TODO Determine what to do with associated lab work
		throw new UnsupportedOperationException("Unsupported operation");
	}

	/** {@inheritDoc} */
	@Override
	public List<Lab> findLabs() {
		return this.labDao.findLabs();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LabWorkCategory> findLabWorkCategories() {
		return this.labWorkCategoryDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<Lab> findLabsAtLocation(final Location location) {
		return this.labDao.findLabsAtLocation(location);
	}
	
	/** {@inheritDoc} */
	@Override
	public LabWork updateLabWork(final LabWork labWork, 
			final LabWorkCategory category, final Lab sampleLab, 
			final Date sampleDate, final Boolean sampleTaken,
			final String sampleNotes, final LabWorkResults results, 
			final LabWorkOrder order, 
			final LabWorkSampleRestrictions sampleRestrictions, 
			final String schedulingNotes) 
		throws DuplicateEntityFoundException {
		if (this.labWorkDao.findExcluding(labWork, 
				labWork.getOffenderAppointmentAssociation(),
				category, sampleLab, results.getLab()).size() > 0) {
			throw new DuplicateEntityFoundException(
					"Lab work already exists");
		}
		labWork.getOffenderAppointmentAssociation().getAppointment()
		.setDate(sampleDate);
		if (sampleTaken) {
			labWork.getOffenderAppointmentAssociation().getAppointment()
				.setStatus(HealthAppointmentStatus.KEPT);
		} else {
			labWork.getOffenderAppointmentAssociation().getAppointment()
			.setStatus(null);
		}
		populateLabWork(labWork, labWork.getOffenderAppointmentAssociation(), 
				schedulingNotes, order, sampleLab, sampleRestrictions, results, 
				sampleNotes, category, sampleTaken);
		this.updateHealthAppointment(
				labWork.getOffenderAppointmentAssociation().getAppointment(), 
				sampleDate, null);
		labWork.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return labWork;
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeLabWork(final LabWork labWork) {
		this.labWorkDao.makeTransient(labWork);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findProviders(
			final Facility facility, final Date date) {
		return this.providerAssignmentDao.findByFacility(facility, date);
	}
	
	/** {@inheritDoc} */
	@Override
	public Facility findFacility(final Location location) {
		return this.facilityDao.findFacilityByLocation(location);
	}

	/** {@inheritDoc} */
	@Override
	public List<LabWork> findLabWorks(final Offender offender, 
			final Date date) {
		return this.labWorkDao.findByOffenderAndDate(offender, date);
	}
	
	/*
	 * Creates a new lab work.
	 */
	private LabWork createLabWork(final Lab sampleLab,
			final String sampleNotes, final LabWorkResults results, 
			final LabWorkOrder order, final String schedulingNotes, 
			final LabWorkSampleRestrictions restrictions,
			final OffenderAppointmentAssociation association,
			final LabWorkCategory labWorkCategory, final Boolean sampleTaken) 
		throws DuplicateEntityFoundException {
		if (this.labWorkDao.find(association) != null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		LabWork labWork = this.labWorkInstanceFactory.createInstance();
		this.populateLabWork(labWork, association, schedulingNotes, order, 
				sampleLab, restrictions, results, sampleNotes, labWorkCategory,
				sampleTaken);
		labWork.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.saveOrUpdateLabWork(labWork);
	}
	
	/*
	 * Persists the specified lab work.
	 */
	private LabWork saveOrUpdateLabWork(final LabWork labWork) {
		labWork.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.labWorkDao.makePersistent(labWork);
	}
	
	/*
	 * Creates a new offender appointment association.
	 */
	private OffenderAppointmentAssociation createOffenderAppointmentAssociation(
			final HealthAppointment appointment, final Offender offender) {
		OffenderAppointmentAssociation association =
				this.offenderAppointmentAssociationInstanceFactory
				.createInstance();
		association.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		this.saveOrUpdateOffenderAppointmentAssociation(association, 
				appointment, offender);
		return association;
	}
	
	/*
	 * Creates a new health appointment.
	 */
	private HealthAppointment createHealthAppointment(final Date startTime, 
			final Date endTime, final HealthAppointmentStatus status,
			final Date timeKept, final Date date, final Facility facility) {
		HealthAppointment appointment = this.healthAppointmentInstanceFactory
				.createInstance();
		appointment.setFacility(facility);
		this.saveOrUpdateHealthAppointment(appointment, startTime, date);
		return appointment;
	}
	
	/*
	 * Updates the specified health appointment with the specified date and
	 * start time.
	 */
	private HealthAppointment updateHealthAppointment(
			final HealthAppointment appointment, final Date date,
			final Date startTime) {
		return this.saveOrUpdateHealthAppointment(appointment, startTime, date);
	}
	
	/*
	 * Saves or updates a health appointment with the specified properties.
	 */
	private HealthAppointment saveOrUpdateHealthAppointment(
			final HealthAppointment appointment, final Date startTime,
			final Date date) {
		//TODO create find method in DAO and corresponding named query
		appointment.setStartTime(startTime);
		appointment.setDate(date);
		return this.healthAppointmentDao.makePersistent(appointment);
	}
	
	/*
	 * Saves or updates the specified offender appointment association with the
	 * specified properties. 
	 */
	private OffenderAppointmentAssociation 
	saveOrUpdateOffenderAppointmentAssociation(
			final OffenderAppointmentAssociation association,
			final HealthAppointment appointment, final Offender offender) {
		//TODO create find method in DAO and corresponding named query
		association.setAppointment(appointment);
		association.setOffender(offender);
		association.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.offenderAppointmentAssociationDao.makePersistent(
				association);
	}
	
	/*
	 * Populates the specified lab work with the specified properties.
	 */
	private LabWork populateLabWork(final LabWork labWork, 
			final OffenderAppointmentAssociation appointment,
			final String schedulingNotes, final LabWorkOrder order,
			final Lab sampleLab, 
			final LabWorkSampleRestrictions sampleRestrictions,
			final LabWorkResults results, final String sampleNotes,			
			final LabWorkCategory category, final Boolean sampleTaken) {
		labWork.setSchedulingNotes(schedulingNotes);
		labWork.setOrder(order);
		labWork.setSampleLab(sampleLab);
		labWork.setSampleRestrictions(sampleRestrictions);
		labWork.setResults(results);
		labWork.setSampleNotes(sampleNotes);
		labWork.setLabWorkCategory(category);
		labWork.setSampleTaken(sampleTaken);
		labWork.setOffenderAppointmentAssociation(appointment);
		return labWork;
	}
}