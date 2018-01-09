package omis.health.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.dao.HealthAppointmentDao;
import omis.health.dao.InternalReferralDao;
import omis.health.dao.InternalReferralReasonDao;
import omis.health.dao.InternalReferralStatusReasonDao;
import omis.health.dao.LabDao;
import omis.health.dao.LabWorkCategoryDao;
import omis.health.dao.LabWorkDao;
import omis.health.dao.LabWorkRequirementDao;
import omis.health.dao.LabWorkRequirementRequestDao;
import omis.health.dao.OffenderAppointmentAssociationDao;
import omis.health.dao.ProviderAssignmentDao;
import omis.health.dao.ProviderInternalReferralAssociationDao;
import omis.health.dao.ProviderLevelDao;
import omis.health.dao.ProviderScheduleDao;
import omis.health.domain.HealthAppointment;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.HealthRequest;
import omis.health.domain.HealthRequestStatus;
import omis.health.domain.InternalReferral;
import omis.health.domain.InternalReferralReason;
import omis.health.domain.InternalReferralStatusReason;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkRequirement;
import omis.health.domain.LabWorkRequirementRequest;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderInternalReferralAssociation;
import omis.health.domain.ProviderLevel;
import omis.health.domain.ProviderSchedule;
import omis.health.domain.ReferralLocationDesignator;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkResults;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.exception.InternalReferralException;
import omis.health.exception.ProviderScheduleException;
import omis.health.service.InternalReferralScheduler;
import omis.health.service.delegate.OffenderAppointmentAssociationDelegate;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Implementation of inside referral scheduler.
 *
 * @author Stephen Abson
 * @author Ryan Johns
 * @author Joel Norris
 * @version 0.1.0 (Apr 13, 2014)
 * @since OMIS 3.0
 */
public class InternalReferralSchedulerImpl
		implements InternalReferralScheduler {

	private final OffenderAppointmentAssociationDelegate
	offenderAppointmentAssociationDelegate;

	/* Instance factories. */
	
	private final InstanceFactory<ProviderInternalReferralAssociation>
	providerInternalReferralAssociationInstanceFactory;

	private InstanceFactory<HealthAppointment> 
	healthAppointmentInstanceFactory;

	private final InstanceFactory<InternalReferral>
	internalReferralInstanceFactory;
	
	private InstanceFactory<OffenderAppointmentAssociation> 
	offenderAppointmentAssociationInstanceFactory;
	
	private InstanceFactory<LabWorkRequirement>
	labWorkRequirementInstanceFactory;
	
	private InstanceFactory<LabWork> labWorkInstanceFactory;
	
	/* Data access objects. */

	private final ProviderInternalReferralAssociationDao
	providerInternalReferralAssociationDao;

	private final ProviderScheduleDao
	providerScheduleDao;

	private final ProviderAssignmentDao providerAssignmentDao;

	private final InternalReferralReasonDao internalReferralReasonDao;

	private final HealthAppointmentDao healthAppointmentDao;

	private final ProviderLevelDao providerLevelDao;

	private final InternalReferralStatusReasonDao
	internalReferralStatusReasonDao;
	
	private final InternalReferralDao internalReferralDao;
	
	private final LabWorkDao labWorkDao;
	
	private final LabWorkRequirementDao labWorkRequirementDao;
	
	private final OffenderAppointmentAssociationDao 
	offenderAppointmentAssociationDao;
	
	private final LabWorkRequirementRequestDao labWorkRequirementRequestDao;
	
	private final LabDao labDao;
	
	private final LabWorkCategoryDao labWorkCategoryDao;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Instantiates an instance of internal referral scheduler with the
	 * specified data access object and component retriever.
	 *
	 * @param internalReferralDao internal referral data access object
	 * @param offenderAppointmentAssociationDelegate offender appointment
	 * association delegate
	 * @param providerInternalReferralAssociationInstanceFactory provider
	 * internal referral association instance factory
	 * @param internalReferralInstanceFactory internal referral instance
	 * factory
	 * @param providerInternalReferralAssociationDao provider internal referral
	 * association data access object
	 * @param providerAssignmentDao provider assignment data access object
	 * @param internalReferralReasonDao internal referral reason data access
	 * object
	 * @param healthAppointmentDao health appointment data access object
	 * @param providerLevelDao data access object for provider levels
	 * @param providerScheduleDao provider schedule data access object
	 * @param labWorkRequirementDao lab work requirement data access object
	 * @param labWorkDao lab work data access object
	 * @param internalReferralStatusReasonDao data access object for
	 * internal referral status reasons
	 * @param labDao lab data access object,
	 * @param labWorkCategoryDao lab work category data access object
	 * @param auditComponentRetriever audit component retriever
	 */
	public InternalReferralSchedulerImpl(
			final InternalReferralDao internalReferralDao,
			final OffenderAppointmentAssociationDelegate
				offenderAppointmentAssociationDelegate,
			final InstanceFactory<ProviderInternalReferralAssociation>
				providerInternalReferralAssociationInstanceFactory,
			final InstanceFactory<InternalReferral>
			internalReferralInstanceFactory,
			final InstanceFactory<HealthAppointment> 
			healthAppointmentInstanceFactory,
			final InstanceFactory<OffenderAppointmentAssociation> 
			offenderAppointmentAssociationInstanceFactory,
			final InstanceFactory<LabWork> labWorkInstanceFactory,
			final InstanceFactory<LabWorkRequirement>
			labWorkRequirementInstanceFactory,
			final ProviderInternalReferralAssociationDao
			providerInternalReferralAssociationDao,
			final ProviderAssignmentDao providerAssignmentDao,
			final InternalReferralReasonDao internalReferralReasonDao,
			final HealthAppointmentDao healthAppointmentDao,
			final ProviderLevelDao providerLevelDao,
			final ProviderScheduleDao providerScheduleDao,
			final InternalReferralStatusReasonDao
			internalReferralStatusReasonDao,
			final LabWorkRequirementDao labWorkRequirementDao,
			final LabWorkDao labWorkDao,
			final OffenderAppointmentAssociationDao 
			offenderAppointmentAssociationDao,
			final LabWorkRequirementRequestDao labWorkRequirementRequestDao,
			final LabDao labDao, final LabWorkCategoryDao labWorkCategoryDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.internalReferralDao = internalReferralDao;
		this.offenderAppointmentAssociationDelegate
			= offenderAppointmentAssociationDelegate;
		this.providerInternalReferralAssociationInstanceFactory
			= providerInternalReferralAssociationInstanceFactory;
		this.healthAppointmentInstanceFactory 
			= healthAppointmentInstanceFactory;
		this.offenderAppointmentAssociationInstanceFactory
			= offenderAppointmentAssociationInstanceFactory;
		this.labWorkInstanceFactory = labWorkInstanceFactory;
		this.labWorkRequirementInstanceFactory = 
				labWorkRequirementInstanceFactory;
		this.providerInternalReferralAssociationDao
			= providerInternalReferralAssociationDao;
		this.internalReferralInstanceFactory = internalReferralInstanceFactory;
		this.providerAssignmentDao = providerAssignmentDao;
		this.internalReferralReasonDao = internalReferralReasonDao;
		this.healthAppointmentDao = healthAppointmentDao;
		this.providerLevelDao = providerLevelDao;
		this.providerScheduleDao = providerScheduleDao;
		this.internalReferralStatusReasonDao = internalReferralStatusReasonDao;
		this.labWorkRequirementDao = labWorkRequirementDao;
		this.labWorkDao = labWorkDao;
		this.offenderAppointmentAssociationDao = 
				offenderAppointmentAssociationDao;
		this.labWorkRequirementRequestDao = labWorkRequirementRequestDao;
		this.labDao = labDao;
		this.labWorkCategoryDao = labWorkCategoryDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/** {@inheritDoc} */
	@Override
	public InternalReferral schedule(final Offender offender,
			final Facility facility, final Date date,
			final InternalReferralReason reason,
			final ProviderLevel providerLevel,
			final ProviderAssignment providerAssignment,
			final ReferralLocationDesignator locationDesignator,
			final String notes)
		throws DuplicateEntityFoundException, ProviderScheduleException,
		DateConflictException {

		if (this.internalReferralDao.find(offender, date, null,
				providerAssignment) != null) {
			throw new DuplicateEntityFoundException("Duplicate referral found");
		}

		if (!this.isProviderScheduled(providerAssignment, date)) {
			throw new ProviderScheduleException("Provider not scheduled on this"
					+ " date");
		}

		final OffenderAppointmentAssociation offenderAppointmentAssociation
			= this.offenderAppointmentAssociationDelegate
				.create(offender, date, facility, null, null);
		final ProviderInternalReferralAssociation
		providerInsideReferralAssociation = this
			.providerInternalReferralAssociationInstanceFactory
			.createInstance();

		InternalReferral referral
			= this.internalReferralInstanceFactory.createInstance();
		referral.setOffenderAppointmentAssociation(
				offenderAppointmentAssociation);
		referral.setCreationSignature(new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		referral.setUpdateSignature(new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		referral.setReason(reason);
		referral.setLocationDesignator(locationDesignator);
		referral.setSchedulingNotes(notes);
		referral.setProviderLevel(providerLevel);
		referral = this.internalReferralDao.makePersistent(referral);

		providerInsideReferralAssociation.setProviderAssignment(
			providerAssignment);
		providerInsideReferralAssociation.setInternalReferral(referral);
		providerInsideReferralAssociation.setPrimary(true);
		this.providerInternalReferralAssociationDao.makePersistent(
			providerInsideReferralAssociation);

		return referral;
	}

	/** {@inheritDoc} */
	@Override
	public LabWork scheduleLabWork(final InternalReferral internalReferral, 
			final LabWorkCategory labWorkCategory, final Lab sampleLab, 
			final Date sampleDate, final Boolean sampleTaken, 
			final String sampleNotes, final LabWorkResults results, 
			final LabWorkOrder order, 
			final LabWorkSampleRestrictions sampleRestrictions, 
			final String schedulingNotes)
		throws DuplicateEntityFoundException {
		OffenderAppointmentAssociation appointmentAssociation =
				internalReferral.getOffenderAppointmentAssociation();
		HealthAppointment appointment = 
				this.createHealthAppointment(sampleDate, appointmentAssociation
						.getAppointment().getFacility());
		OffenderAppointmentAssociation association =
				this.createOffenderAppointmentAssociation(appointment, 
						appointmentAssociation.getOffender());
		LabWork labWork = this.createLabWork(sampleLab, sampleDate, sampleTaken,
				results, schedulingNotes, sampleNotes, order, association, 
				sampleRestrictions, labWorkCategory);
		this.createLabWorkRequirement(labWork, appointmentAssociation);
		return labWork;
	}
	
	/** {@inheritDoc} */
	@Override
	public LabWork updateLabWork(final InternalReferral internalReferral, 
			final LabWork labWork, final LabWorkCategory labWorkCategory, 
			final Lab sampleLab, final Date sampleDate, 
			final Boolean sampleTaken, final String sampleNotes, 
			final LabWorkResults results, final LabWorkOrder order, 
			final LabWorkSampleRestrictions sampleRestrictions, 
			final String schedulingNotes)
		throws DuplicateEntityFoundException {
		if (this.labWorkDao.findExcluding(labWork, 
				labWork.getOffenderAppointmentAssociation(),
				labWorkCategory, sampleLab, results.getLab()).size() > 0) {
			throw new DuplicateEntityFoundException("Duplicate lab work found");
		}
		LabWork updatedLabWorklab = this.populateLabWork(labWork, 
				labWork.getOffenderAppointmentAssociation(), labWorkCategory, 
				sampleLab, sampleTaken, sampleNotes, results, order, 
				sampleRestrictions, schedulingNotes); 
		updatedLabWorklab.getOffenderAppointmentAssociation().getAppointment()
				.setDate(sampleDate);
		this.saveOrUpdateLabWork(updatedLabWorklab);
		LabWorkRequirement labWorkRequirement = 
				this.labWorkRequirementInstanceFactory.createInstance();
		labWorkRequirement.setLabWork(updatedLabWorklab);
		labWorkRequirement.setOffenderAppointmentAssociation(internalReferral
				.getOffenderAppointmentAssociation());
		if (this.labWorkRequirementDao.find(labWorkRequirement
				.getOffenderAppointmentAssociation(), updatedLabWorklab) 
				== null) {
			this.labWorkRequirementDao.makePersistent(labWorkRequirement);
		}
		return updatedLabWorklab;
	}
	
	/** {@inheritDoc} */
	@Override
	public InternalReferral scheduleFromRequest(final HealthRequest request,
			final Date date, final InternalReferralReason reason,
			final ProviderLevel providerLevel,
			final ProviderAssignment providerAssignment,
			final ReferralLocationDesignator locationDesignator,
			final String notes)
					throws DuplicateEntityFoundException,
					ProviderScheduleException, DateConflictException {

		if (this.internalReferralDao.find(request.getOffender(), date, null,
				providerAssignment) != null) {
			throw new DuplicateEntityFoundException("Duplicate referral found");
		}

		if (!this.isProviderScheduled(providerAssignment, date)) {
			throw new ProviderScheduleException("Provider not scheduled on this"
					+ " date");
		}


		final OffenderAppointmentAssociation offenderAppointmentAssociation
		= this.offenderAppointmentAssociationDelegate
			.create(request.getOffender(), date,
					request.getFacility(), null, null);
		final ProviderInternalReferralAssociation
		providerInsideReferralAssociation = this
			.providerInternalReferralAssociationInstanceFactory
			.createInstance();

		InternalReferral referral
			= this.internalReferralInstanceFactory.createInstance();
		referral.setOffenderAppointmentAssociation(
				offenderAppointmentAssociation);
		referral.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		referral.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		referral.setReason(reason);
		referral.setProviderLevel(providerLevel);
		referral.setLocationDesignator(locationDesignator);
		referral.setSchedulingNotes(notes);
		referral = this.internalReferralDao.makePersistent(referral);

		providerInsideReferralAssociation.setProviderAssignment(
				providerAssignment);
		providerInsideReferralAssociation.setInternalReferral(referral);
		providerInsideReferralAssociation.setPrimary(true);
		this.providerInternalReferralAssociationDao.makePersistent(
				providerInsideReferralAssociation);

		request.setStatus(HealthRequestStatus.SCHEDULED);

		return referral;
	}

	/** {@inheritDoc} */
	@Override
	public InternalReferral updateSchedule(
			final InternalReferral internalReferral,
			final Date date, final InternalReferralReason reason,
			final ProviderLevel providerLevel,
			final ProviderAssignment providerAssignment,
			final ReferralLocationDesignator locationDesignator,
			final String notes) {

		internalReferral.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		internalReferral.getOffenderAppointmentAssociation().getAppointment()
			.setDate(date);
		internalReferral.setReason(reason);
		internalReferral.setProviderLevel(providerLevel);
		internalReferral.setLocationDesignator(locationDesignator);
		internalReferral.setSchedulingNotes(notes);
		final ProviderInternalReferralAssociation
			providerInsideReferralAssociation =
		this.providerInternalReferralAssociationDao
			.findPrimaryByReferral(internalReferral);;
		providerInsideReferralAssociation
			.setProviderAssignment(providerAssignment);
		return this.internalReferralDao.makePersistent(internalReferral);
	}

	/** {@inheritDoc} */
	@Override
	public void requestReschedule(final InternalReferral referral) {
		referral.setRescheduleRequired(true);
	}

	/** {@inheritDoc} */
	@Override
	public ProviderInternalReferralAssociation assignAdditionalProviders(
			final InternalReferral referral,
			final ProviderAssignment providerAssignment)
		throws DuplicateEntityFoundException {
		final ProviderInternalReferralAssociation association
			= this.providerInternalReferralAssociationInstanceFactory
				.createInstance();
		association.setInternalReferral(referral);
		association.setProviderAssignment(providerAssignment);
		return association;
	}

	/** {@inheritDoc} */
	@Override
	public InternalReferral reschedule(final InternalReferral internalReferral,
			final InternalReferralStatusReason statusReason,
			final Date date, final InternalReferralReason reason,
			final ProviderLevel providerLevel,
			final ProviderAssignment providerAssignment, final String notes,
			final ReferralLocationDesignator referralLocationDesignator)
			throws DuplicateEntityFoundException, ProviderScheduleException,
			DateConflictException, InternalReferralException {

		// Sets status reason of old referral
		if (statusReason == null) {
			throw new InternalReferralException("Status reason required");
		}
		if (!HealthAppointmentStatus.RESCHEDULED.equals(
				statusReason.getAppointmentStatus())) {
			throw new InternalReferralException("Status must be rescheduled");
		}
		internalReferral.setStatusReason(statusReason);
		internalReferral.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.internalReferralDao.makePersistent(internalReferral);
		
		if (this.internalReferralDao.find(internalReferral
				.getOffenderAppointmentAssociation().getOffender(), date, null,
				providerAssignment) != null) {
			throw new DuplicateEntityFoundException("Duplicate referral found");
		}

		if (!this.isProviderScheduled(providerAssignment, date)) {
			throw new ProviderScheduleException("Provider not scheduled on this"
					+ " date");
		}

		final ProviderInternalReferralAssociation
		providerInsideReferralAssociation = this
			.providerInternalReferralAssociationInstanceFactory
			.createInstance();

		final OffenderAppointmentAssociation old = internalReferral
				.getOffenderAppointmentAssociation();
		old.getAppointment().setStatus(HealthAppointmentStatus.RESCHEDULED);
		final OffenderAppointmentAssociation offenderAppointmentAssociation
			= this.offenderAppointmentAssociationDelegate
			.create(old.getOffender(), date,
					old.getAppointment().getFacility(), null, null);

		InternalReferral referral
			= this.internalReferralInstanceFactory.createInstance();

		referral.setSchedulingNotes(notes);
		referral.setLocationDesignator(referralLocationDesignator);
		referral.setOffenderAppointmentAssociation(
				offenderAppointmentAssociation);
		referral.setReason(reason);
		referral.setProviderLevel(providerLevel);
		referral.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		referral.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));

		referral = this.internalReferralDao.makePersistent(referral);

		providerInsideReferralAssociation.setInternalReferral(referral);
		providerInsideReferralAssociation.setProviderAssignment(
				providerAssignment);
		providerInsideReferralAssociation.setPrimary(true);


		this.providerInternalReferralAssociationDao.makePersistent(
				providerInsideReferralAssociation);


		return referral;
	}

	/** {@inheritDoc} */
	@Override
	public HealthAppointment findHealthAppointmentByInternalReferral(
			final InternalReferral internalReferral) {
		return this.healthAppointmentDao.findByInternalReferral(
				internalReferral);
	}

	/** {@inheritDoc} */
	@Override
	public ProviderAssignment findProviderByInternalReferral(
			final InternalReferral internalReferral) {
		return this.providerAssignmentDao.findByInternalReferral(
				internalReferral).get(0);

	}

	/** {@inheritDoc} */
	@Override
	public void removeProvider(
			final ProviderInternalReferralAssociation
				providerInsideReferralAssociation) {
		// TODO Remove provider from internal referral - SA
		// Prevent removal of primary provider
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeLabWork(final InternalReferral internalReferral, 
			final LabWork labWork) {
		OffenderAppointmentAssociation appointmentAssociaiton =
				internalReferral.getOffenderAppointmentAssociation();
		if (this.labWorkRequirementDao
				.findByLabWorkExcluding(labWork, appointmentAssociaiton)
				.size() > 0) {
			this.labWorkRequirementDao.makeTransient(this.labWorkRequirementDao
					.find(appointmentAssociaiton, labWork));
		} else {
			this.labWorkRequirementDao.makeTransient(this.labWorkRequirementDao
					.find(appointmentAssociaiton, labWork));
			this.labWorkDao.makeTransient(labWork);
		}
	}

	/** {@inheritDoc} */
	@Override
	public List<InternalReferralReason> findReasons() {
		return this.internalReferralReasonDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderLevel> findProviderLevels() {
		return this.providerLevelDao.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findInternalProviderAssignmentsForFacility(
			final Facility facility, final Date date) {
		return this.providerAssignmentDao.findInternalByFacilityOnDate(
				facility, date);
	}

	/** {@inheritDoc} */
	@Override
	public List<InternalReferralStatusReason> findRescheduleStatusReasons() {
		return this.internalReferralStatusReasonDao
				.findByAppointmentStatus(HealthAppointmentStatus.RESCHEDULED);
	}

	/** {@inheritDoc} */
	@Override
	public List<LabWork> findLabWorks(final InternalReferral internalReferral) {
		return this.labWorkDao.findRequiredLabWork(
				internalReferral.getOffenderAppointmentAssociation());
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LabWorkRequirement> findLabWorkRequirements(
			final InternalReferral internalReferral) {
		return this.labWorkRequirementDao.findByOffenderAppointmentAssociaiton(
				internalReferral.getOffenderAppointmentAssociation());
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LabWorkRequirementRequest> findLabWorkRequirementRequests(
			final HealthRequest healthRequest) {
		return this.labWorkRequirementRequestDao
				.findByHealthRequest(healthRequest);
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
	
	/* Helper methods. */

	private boolean isProviderScheduled(
			final ProviderAssignment providerAssignment,
			final Date date) {
		final ProviderSchedule providerSchedule
			= this.providerScheduleDao.findByAssignment(providerAssignment);

		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		DateRange dayOfWeekDateRange;

		if (dayOfWeek == Calendar.MONDAY) {
			dayOfWeekDateRange = providerSchedule.getMondayTimeRange();
		} else if (dayOfWeek == Calendar.TUESDAY) {
			dayOfWeekDateRange = providerSchedule.getTuesdayTimeRange();
		} else if (dayOfWeek == Calendar.WEDNESDAY) {
			dayOfWeekDateRange = providerSchedule.getWednesdayTimeRange();
		} else if (dayOfWeek == Calendar.THURSDAY) {
			dayOfWeekDateRange = providerSchedule.getThursdayTimeRange();
		} else if (dayOfWeek == Calendar.FRIDAY) {
			dayOfWeekDateRange = providerSchedule.getFridayTimeRange();
		} else if (dayOfWeek == Calendar.SATURDAY) {
			dayOfWeekDateRange = providerSchedule.getSaturdayTimeRange();
		} else {
			dayOfWeekDateRange = providerSchedule.getSundayTimeRange();
		}

		if (dayOfWeekDateRange != null) {
			return true;
		} else {
			return false;
		}

	}
	
	/*
	 * Creates a new health appointment.
	 */
	private HealthAppointment createHealthAppointment(final Date date, 
			final Facility facility) {
		HealthAppointment appointment = this.healthAppointmentInstanceFactory
				.createInstance();
		this.saveOrUpdateHealthAppointment(appointment, date, 
				facility);
		return appointment;
	}
	
	/*
	 * Saves or updates a health appointment with the specified properties.
	 */
	private HealthAppointment saveOrUpdateHealthAppointment(
			final HealthAppointment appointment, final Date date, 
			final Facility facility) {
		//TODO - JNo create find method in DAO and corresponding named query
//		if (this.healthAppointmentDao.find() != null) {
//			throw new DuplicateEntityFoundException("Duplicate entity found");
//		}
		appointment.setDate(date);
		appointment.setFacility(facility);
		return this.healthAppointmentDao.makePersistent(appointment);
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
	 * Saves or updates the specified offender appointment association with the
	 * specified properties. 
	 */
	private OffenderAppointmentAssociation 
	saveOrUpdateOffenderAppointmentAssociation(
			final OffenderAppointmentAssociation association,
			final HealthAppointment appointment, final Offender offender) {
		//TODO - JNo create find method in DAO and corresponding named query
//		if (this.offenderAppointmentAssociationDao.find(appointment) != null) {
//			throw new DuplicateEntityFoundException("Duplicate entity found");
//		}
		association.setAppointment(appointment);
		association.setOffender(offender);
		association.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.offenderAppointmentAssociationDao.makePersistent(
				association);
	}
	
	/*
	 * Creates a new lab work.
	 */
	private LabWork createLabWork(final Lab sampleLab, final Date sampleDate,
			final Boolean sampleTaken, final LabWorkResults results, 
			final String schedulingNotes, final String sampleNotes, 
			final LabWorkOrder order,
			final OffenderAppointmentAssociation association,
			final LabWorkSampleRestrictions sampleRestrictions,
			final LabWorkCategory labWorkCategory) 
		throws DuplicateEntityFoundException {
		if (this.labWorkDao.find(association) != null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		LabWork labWork = this.labWorkInstanceFactory.createInstance();
		this.populateLabWork(labWork, association, labWorkCategory, sampleLab, 
				sampleTaken, sampleNotes, results, order, 
				sampleRestrictions, schedulingNotes);
		labWork.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.saveOrUpdateLabWork(labWork);
	}
	
	/*
	 * Populates the specified lab work with the specified properties.
	 */
	private LabWork populateLabWork(final LabWork labWork, 
			final OffenderAppointmentAssociation appointment,
			final LabWorkCategory labWorkCategory, final Lab sampleLab,
			final Boolean sampleTaken, final String sampleNotes, 
			final LabWorkResults results, final LabWorkOrder order, 
			final LabWorkSampleRestrictions sampleRestrictions, 
			final String schedulingNotes) {
		labWork.setSampleLab(sampleLab);
		labWork.setResults(results);
		labWork.setSampleRestrictions(sampleRestrictions);
		labWork.setSampleTaken(sampleTaken);
		labWork.setOrder(order);
		labWork.setSchedulingNotes(schedulingNotes);
		labWork.setSampleNotes(sampleNotes);
		labWork.setLabWorkCategory(labWorkCategory);
		labWork.setOffenderAppointmentAssociation(appointment);
		return labWork;
	}
	
	/*
	 * Creates a new lab work requirement with the specified offender 
	 * appointment association and category.
	 */
	private LabWorkRequirement createLabWorkRequirement(final LabWork labWork,
			final OffenderAppointmentAssociation appointmentAssociation) 
		throws DuplicateEntityFoundException {
		LabWorkRequirement labWorkRequirement = this
				.labWorkRequirementInstanceFactory.createInstance();
		if (this.labWorkRequirementDao.find(appointmentAssociation, labWork) 
				!= null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		this.populateLabWorkRequirement(labWorkRequirement, labWork, 
				appointmentAssociation);
		return this.labWorkRequirementDao.makePersistent(labWorkRequirement);
	}
	
	/*
	 * Sets the specified lab work, and offender appointment association on the
	 * specified lab work requirement.
	 */
	private LabWorkRequirement populateLabWorkRequirement(
			final LabWorkRequirement labWorkRequirement,
			final LabWork labWork, final OffenderAppointmentAssociation 
			offenderAppointmentAssociation) {
		labWorkRequirement.setLabWork(labWork);
		labWorkRequirement.setOffenderAppointmentAssociation(
				offenderAppointmentAssociation);
		return labWorkRequirement;
	}
}