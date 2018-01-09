package omis.health.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.dao.HealthAppointmentDao;
import omis.health.dao.HealthRequestDao;
import omis.health.dao.LabDao;
import omis.health.dao.LabWorkCategoryDao;
import omis.health.dao.LabWorkDao;
import omis.health.dao.LabWorkReferralAssociationDao;
import omis.health.dao.LabWorkReferralDao;
import omis.health.dao.LabWorkReferralStatusReasonDao;
import omis.health.dao.OffenderAppointmentAssociationDao;
import omis.health.dao.ProviderAssignmentDao;
import omis.health.domain.HealthAppointment;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.HealthRequest;
import omis.health.domain.HealthRequestStatus;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.LabWorkReferralAssociation;
import omis.health.domain.LabWorkReferralStatusReason;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkResults;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.service.LabWorkReferralScheduler;
import omis.instance.factory.InstanceFactory;
import omis.location.domain.Location;
import omis.offender.domain.Offender;

/**
 * Lab work referral scheduler implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 7, 2014)
 * @since OMIS 3.0
 */
public class LabWorkReferralSchedulerImpl 
	implements LabWorkReferralScheduler {
	
	/* Data access objects. */
	
	private LabWorkReferralDao labWorkReferralDao;
	
	private OffenderAppointmentAssociationDao offenderAppointmentAssociationDao;
	
	private HealthAppointmentDao healthAppointmentDao;
	
	private LabWorkDao labWorkDao;
	
	private LabDao labDao;
	
	private LabWorkCategoryDao labWorkCategoryDao;
	
	private ProviderAssignmentDao providerAssignmentDao;
	
	private LabWorkReferralStatusReasonDao labWorkReferralStatusReasonDao;
	
	private HealthRequestDao healthRequestDao;
	
	private LabWorkReferralAssociationDao labWorkReferralAssociationDao;
	
	/* Instance factories. */
	
	private InstanceFactory<HealthAppointment> healthAppointmentInstanceFactory;
	
	private InstanceFactory<OffenderAppointmentAssociation>
	offenderAppointmentAssociationInstanceFactory;
	
	private InstanceFactory<LabWorkReferral>
	labWorkReferralInstanceFactory;
	
	private InstanceFactory<LabWork>
	labWorkInstanceFactory;
	
	private InstanceFactory<LabWorkReferralAssociation>
	labWorkReferralAssociationInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates a lab work referral scheduler with the specified data
	 * access objects, instance factories and component retrievers.
	 * 
	 * @param labWorkReferralDao lab work referral data access object
	 * @param offenderAppointmentAssociationDao offender appointment association
	 * data access object
	 * @param healthAppointmentDao health appointment data access object
	 * @param labWorkDao lab work data access object
	 * @param labDao lab data access object
	 * @param labWorkCategoryDao lab work category data access object
	 * @param providerAssignmentDao provider assignment data access object
	 * @param labWorkReferralStatusReasonDao lab work referral status reason
	 * data access object
	 * @param healthRequestDao health request data access object
	 * @param labWorkReferralAssociationDao lab work referral association data
	 * access object
	 * @param healthAppointmentInstanceFactory health appointment instance 
	 * factory
	 * @param offenderAppointmentAssociationInstanceFactory offender appointment
	 * association instance factory
	 * @param labWorkReferralInstanceFactory lab work referral instance factory
	 * @param labWorkInstanceFactory lab work instance factory
	 * @param labWorkReferralAssociationInstanceFactory lab work referral
	 * association instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public LabWorkReferralSchedulerImpl(
			final LabWorkReferralDao labWorkReferralDao,
			final OffenderAppointmentAssociationDao 
			offenderAppointmentAssociationDao, 
			final HealthAppointmentDao healthAppointmentDao,
			final LabWorkDao labWorkDao,
			final LabDao labDao,
			final LabWorkCategoryDao labWorkCategoryDao,
			final ProviderAssignmentDao providerAssignmentDao,
			final LabWorkReferralStatusReasonDao labWorkReferralStatusReasonDao,
			final HealthRequestDao healthRequestDao,
			final LabWorkReferralAssociationDao labWorkReferralAssociationDao,
			final InstanceFactory<HealthAppointment> 
			healthAppointmentInstanceFactory,
			final InstanceFactory<OffenderAppointmentAssociation>
			offenderAppointmentAssociationInstanceFactory,
			final InstanceFactory<LabWorkReferral>
			labWorkReferralInstanceFactory,
			final InstanceFactory<LabWork>
			labWorkInstanceFactory,
			final InstanceFactory<LabWorkReferralAssociation>
			labWorkReferralAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.labWorkReferralDao = labWorkReferralDao;
		this.offenderAppointmentAssociationDao 
			= offenderAppointmentAssociationDao;
		this.healthAppointmentDao = healthAppointmentDao;
		this.labWorkDao = labWorkDao;
		this.labDao = labDao;
		this.labWorkCategoryDao = labWorkCategoryDao;
		this.providerAssignmentDao = providerAssignmentDao;
		this.labWorkReferralStatusReasonDao = labWorkReferralStatusReasonDao;
		this.healthRequestDao = healthRequestDao;
		this.labWorkReferralAssociationDao = labWorkReferralAssociationDao;
		this.healthAppointmentInstanceFactory 
			= healthAppointmentInstanceFactory;
		this.offenderAppointmentAssociationInstanceFactory
			= offenderAppointmentAssociationInstanceFactory;
		this.labWorkReferralInstanceFactory = labWorkReferralInstanceFactory;
		this.labWorkInstanceFactory = labWorkInstanceFactory;
		this.labWorkReferralAssociationInstanceFactory = 
				labWorkReferralAssociationInstanceFactory; 
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/** {@inheritDoc} */
	@Override
	public LabWorkReferral schedule(final Facility facility, 
			final Offender offender, final Date sampleDate, final String notes) 
		throws DuplicateEntityFoundException {
		HealthAppointment healthAppointment = 
				this.healthAppointmentInstanceFactory.createInstance();
		this.populateHealthAppointment(healthAppointment, sampleDate, facility);
		this.healthAppointmentDao.makePersistent(healthAppointment);
		if (this.offenderAppointmentAssociationDao.find(offender, 
				healthAppointment) != null) {
			throw new DuplicateEntityFoundException("Duplicate offender" 
				+ " appointment association found.");
		}
		OffenderAppointmentAssociation offenderAppointmentAssociation = 
				this.offenderAppointmentAssociationInstanceFactory
				.createInstance();
		offenderAppointmentAssociation.setCreationSignature(
				new CreationSignature(this.auditComponentRetriever
						.retrieveUserAccount(), this.auditComponentRetriever
						.retrieveDate()));
		this.populateOffenderAppointmentAssociaiton(
				offenderAppointmentAssociation, offender, healthAppointment);
		this.offenderAppointmentAssociationDao.makePersistent(
				offenderAppointmentAssociation);
		if (this.labWorkReferralDao.find(
				offenderAppointmentAssociation) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate lab work referral found");
		}
		LabWorkReferral labWorkReferral = this.labWorkReferralInstanceFactory
				.createInstance();
		labWorkReferral.setOffenderAppointmentAssociation(
				offenderAppointmentAssociation);
		labWorkReferral.setSchedulingNotes(notes);
		labWorkReferral.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		labWorkReferral.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.labWorkReferralDao.makePersistent(labWorkReferral);
	}

	/** {@inheritDoc} */
	@Override
	public LabWorkReferral scheduleFromRequest(
			final HealthRequest healthRequest, final Date sampleDate, 
			final String notes) 
		throws DuplicateEntityFoundException {
		HealthAppointment healthAppointment =
				this.healthAppointmentInstanceFactory.createInstance();
		this.populateHealthAppointment(healthAppointment, sampleDate, 
				healthRequest.getFacility());
		this.healthAppointmentDao.makePersistent(healthAppointment);
		if (this.offenderAppointmentAssociationDao.find(
				healthRequest.getOffender(), 
				healthAppointment) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate offender appointment association found.");
		}
		OffenderAppointmentAssociation offenderAppointmentAssociation =
				this.offenderAppointmentAssociationInstanceFactory
				.createInstance();
		this.populateOffenderAppointmentAssociaiton(
				offenderAppointmentAssociation, healthRequest.getOffender(), 
				healthAppointment);
		offenderAppointmentAssociation.setCreationSignature(
				new CreationSignature(this.auditComponentRetriever
						.retrieveUserAccount(), this.auditComponentRetriever
						.retrieveDate()));
		this.offenderAppointmentAssociationDao.makePersistent(
				offenderAppointmentAssociation);
		if (this.labWorkReferralDao.find(
				offenderAppointmentAssociation) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate lab work referral found");
		}
		LabWorkReferral labWorkReferral = this.labWorkReferralInstanceFactory
				.createInstance();
		labWorkReferral.setOffenderAppointmentAssociation(
				offenderAppointmentAssociation);
		labWorkReferral.setSchedulingNotes(notes);
		labWorkReferral.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		labWorkReferral.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		this.labWorkReferralDao.makePersistent(labWorkReferral);
		healthRequest.setStatus(HealthRequestStatus.SCHEDULED);
		this.healthRequestDao.makePersistent(healthRequest);
		return labWorkReferral;
	}

	/** {@inheritDoc} */
	@Override
	public LabWorkReferral update(final LabWorkReferral labWorkReferral,
			final Date sampleDate, final String notes) 
		throws DuplicateEntityFoundException {
		OffenderAppointmentAssociation association = labWorkReferral
				.getOffenderAppointmentAssociation(); 
		HealthAppointment appointment = association.getAppointment();
		this.saveOrUpdateHealthAppointment(appointment, null, sampleDate);
		if (this.offenderAppointmentAssociationDao.findExcluding(association
				.getOffender(), appointment, association) != null) {
			throw new DuplicateEntityFoundException("Duplicate offender" 
				+ " appointment association found");
		}
		association.setAppointment(appointment);
		this.offenderAppointmentAssociationDao.makePersistent(association);
		labWorkReferral.setSchedulingNotes(notes);
		labWorkReferral.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		this.labWorkReferralDao.makePersistent(labWorkReferral);
		return labWorkReferral;
	}

	/** {@inheritDoc} */
	@Override
	public LabWork addLabWork(final LabWorkReferral labWorkReferral,
			final LabWorkCategory category, final Date sampleDate,
			final Lab sampleLab, final String sampleNotes, 
			final Boolean sampleTaken, final LabWorkOrder order, 
			final LabWorkSampleRestrictions sampleRestrictions,
			final String schedulingNotes) throws DuplicateEntityFoundException {
		Offender offender = labWorkReferral.getOffenderAppointmentAssociation()
				.getOffender();
		HealthAppointment appointment = this.healthAppointmentInstanceFactory
				.createInstance();
		appointment.setDate(sampleDate);
		appointment.setFacility(labWorkReferral
				.getOffenderAppointmentAssociation().getAppointment()
				.getFacility());
		this.healthAppointmentDao.makePersistent(appointment);
		if (this.offenderAppointmentAssociationDao
				.find(offender, appointment) != null) {
			throw new DuplicateEntityFoundException(
					"Offender appointment association already exists");
		}
		OffenderAppointmentAssociation appointmentAssociation = 
				this.offenderAppointmentAssociationInstanceFactory
				.createInstance();
		appointmentAssociation.setAppointment(appointment);
		appointmentAssociation.setOffender(offender);
		appointmentAssociation.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		appointmentAssociation.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.offenderAppointmentAssociationDao.makePersistent(
				appointmentAssociation);
		LabWork labWork = this.labWorkInstanceFactory.createInstance();
		if (this.labWorkDao.findLabWork(appointmentAssociation, category, 
				sampleLab, null) != null) {
			throw new DuplicateEntityFoundException("Duplicate lab work found");
		}
		this.populateLabWork(labWork, appointmentAssociation, schedulingNotes, 
				order, sampleLab, sampleRestrictions, null, sampleNotes, 
				category, sampleTaken);
		labWork.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		this.labWorkDao.makePersistent(labWork);
		if (this.labWorkReferralAssociationDao.find(
				labWork, labWorkReferral) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate lab work referral association found.");
		}
		LabWorkReferralAssociation referralAssociation = 
				this.labWorkReferralAssociationInstanceFactory.createInstance();
		referralAssociation.setLabWork(labWork);
		referralAssociation.setReferral(labWorkReferral);
		this.labWorkReferralAssociationDao.makePersistent(referralAssociation);
		return labWork;
	}
	
	/** {@inheritDoc} */
	@Override
	public LabWork updateLabWork(final LabWorkReferral labWorkReferral,
			final LabWork labWork, final LabWorkCategory category, 
			final Date sampleDate, final Lab sampleLab, 
			final String sampleNotes, final Boolean sampleTaken,
			final LabWorkOrder order,
			final LabWorkSampleRestrictions sampleRestrictions, 
			final String schedulingNotes) throws DuplicateEntityFoundException {
		HealthAppointment appointment = labWork
				.getOffenderAppointmentAssociation().getAppointment(); 
		appointment.setDate(sampleDate);
		this.healthAppointmentDao.makePersistent(appointment);
		final Lab resultsLab;
		if (labWork.getResults() != null) {
			resultsLab = labWork.getResults().getLab();
		} else {
			resultsLab = null;
		}
		if (this.labWorkDao.findExcluding(labWork, 
				labWork.getOffenderAppointmentAssociation(), category, 
				sampleLab, resultsLab).size() > 0) {
			throw new DuplicateEntityFoundException("Duplicate lab work found");
		}
		this.populateLabWork(labWork, 
				labWork.getOffenderAppointmentAssociation(), schedulingNotes, 
				order, sampleLab, sampleRestrictions, null, sampleNotes, 
				category, sampleTaken);
		return this.labWorkDao.makePersistent(labWork);
	}

	/** {@inheritDoc} */
	@Override
	public void removeLabWork(
			final LabWorkReferral labWorkReferral, final LabWork labWork) {
		this.labWorkReferralAssociationDao.makeTransient(this
				.labWorkReferralAssociationDao.find(
						labWork, labWorkReferral));
		OffenderAppointmentAssociation appointmentAssociation = labWork
				.getOffenderAppointmentAssociation();
		this.labWorkDao.makeTransient(labWork);
		if (labWorkReferral.getOffenderAppointmentAssociation() 
				== appointmentAssociation) {
			this.labWorkReferralDao.makeTransient(labWorkReferral);
		}		
		HealthAppointment healthAppointment = appointmentAssociation
				.getAppointment();
		this.offenderAppointmentAssociationDao.makeTransient(
				appointmentAssociation);
		this.healthAppointmentDao.makeTransient(healthAppointment);
	}

	/** {@inheritDoc} */
	@Override
	public List<Lab> findLabsAtLocation(final Location location) {
		return this.labDao.findLabsAtLocation(location);
	}

	/** {@inheritDoc} */
	@Override
	public List<LabWorkCategory> findLabWorkCategories() {
		return this.labWorkCategoryDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findProviders(
			final Facility facility, final Date date) {
		return this.providerAssignmentDao.findByFacility(facility, date);
	}

	/** {@inheritDoc} */
	@Override
	public List<LabWork> findLabWorks(
			final LabWorkReferral labWorkReferral) {
		List<LabWork> labWorks = this.labWorkDao
				.findLabWorkByReferral(labWorkReferral);
		return labWorks;
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final LabWorkReferral labWorkReferral) {
		this.labWorkReferralDao.makeTransient(labWorkReferral);
	}

	/** {@inheritDoc} */
	@Override
	public List<Lab> findLabs() {
		return this.labDao.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LabWorkReferralStatusReason> findRescheduleStatusReasons() {
		return this.labWorkReferralStatusReasonDao.findByAppointmentStatus(
				HealthAppointmentStatus.RESCHEDULED);
	}
	
	/* Helper methods. */
	

	/*
	 * Populates the specified offender appointment association with the 
	 * specified health appointment, offender, and an update signature from the
	 * audit component retriever.
	 *  
	 * @param offenderAppointmentAssociation offender appointment association
	 * @param offender offender
	 * @param healthAppointment health appointment
	 * @return offender appointment association
	 */
	private OffenderAppointmentAssociation 
	populateOffenderAppointmentAssociaiton(final OffenderAppointmentAssociation 
			offenderAppointmentAssociation, final Offender offender, 
			final HealthAppointment healthAppointment) {
		offenderAppointmentAssociation.setAppointment(healthAppointment);
		offenderAppointmentAssociation.setOffender(offender);
		offenderAppointmentAssociation.setUpdateSignature(
				new UpdateSignature(this.auditComponentRetriever
						.retrieveUserAccount(), this.auditComponentRetriever
						.retrieveDate()));
		return offenderAppointmentAssociation;
	}
	
	/*
	 * Populates the specified health appointment with the specified date and
	 * facility.
	 *  
	 * @param healthAppointment health appointment
	 * @param date date
	 * @param facility facility
	 * @return health appointment
	 */
	private HealthAppointment populateHealthAppointment(
			final HealthAppointment healthAppointment, final Date date, 
			final Facility facility) {
		healthAppointment.setDate(date);
		healthAppointment.setFacility(facility);
		return healthAppointment;
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
		labWork.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return labWork;
	}

	/*
	 * Saves or updates a health appointment with the specified properties.
	 */
	private HealthAppointment saveOrUpdateHealthAppointment(
			final HealthAppointment appointment, final Date startTime,
			final Date date) {
		if (startTime != null) {
			appointment.setStartTime(startTime);
		}
		appointment.setDate(date);
		return this.healthAppointmentDao.makePersistent(appointment);
	}
}