package omis.health.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.domain.Facility;
import omis.health.dao.HealthAppointmentDao;
import omis.health.dao.InternalReferralDao;
import omis.health.dao.InternalReferralStatusReasonDao;
import omis.health.dao.LabDao;
import omis.health.dao.LabWorkCategoryDao;
import omis.health.dao.LabWorkDao;
import omis.health.dao.LabWorkRequirementDao;
import omis.health.dao.OffenderAppointmentAssociationDao;
import omis.health.dao.ProviderAssignmentDao;
import omis.health.dao.ProviderInternalReferralAssociationDao;
import omis.health.dao.ProviderLevelDao;
import omis.health.domain.HealthAppointment;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.HealthRequest;
import omis.health.domain.HealthRequestCategory;
import omis.health.domain.InternalReferral;
import omis.health.domain.InternalReferralReason;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkRequirement;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderInternalReferralAssociation;
import omis.health.domain.ProviderLevel;
import omis.health.domain.ReferralLocationDesignator;
import omis.health.domain.component.LabWorkOrder;
import omis.health.domain.component.LabWorkResults;
import omis.health.domain.component.LabWorkSampleRestrictions;
import omis.health.exception.FollowUpException;
import omis.health.exception.InternalReferralAssessmentException;
import omis.health.exception.ProviderScheduleException;
import omis.health.service.InternalReferralAssessmentService;
import omis.health.service.delegate.HealthRequestDelegate;
import omis.health.service.delegate.ProviderScheduleChecker;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/**
 * Implementation of service to assess internal referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 30, 2014)
 * @since OMIS 3.0
 */
public class InternalReferralAssessmentServiceImpl
		implements InternalReferralAssessmentService {
	
	private final InternalReferralDao internalReferralDao;
	
	private final HealthRequestDelegate healthRequestDelegate;
	
	private final ProviderScheduleChecker 
	providerScheduleChecker;
	
	private final ProviderInternalReferralAssociationDao
	providerInternalReferralAssociationDao;
	
	private final ProviderLevelDao providerLevelDao;

	private final InternalReferralStatusReasonDao
	internalReferralStatusReasonDao;
	
	private final LabWorkDao labWorkDao;
	
	private final HealthAppointmentDao healthAppointmentDao;

	private final OffenderAppointmentAssociationDao 
	offenderAppointmentAssociationDao;

	private final LabWorkRequirementDao labWorkRequirementDao;
	
	private final LabWorkCategoryDao labWorkCategoryDao;
	
	private final ProviderAssignmentDao providerAssignmentDao;
	
	private final LabDao labDao;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	private final InstanceFactory<LabWork>
	labWorkInstanceFactory;

	private final InstanceFactory<OffenderAppointmentAssociation> 
	offenderAppointmentAssociationInstanceFactory;

	private final InstanceFactory<HealthAppointment> 
	healthAppointmentInstanceFactory;

	private InstanceFactory<LabWorkRequirement> 
	labWorkRequirementInstanceFactory;

	
	
	/**
	 * Instantiates in implementation of service to assess internal referrals.
	 * 
	 * @param internalReferralDao data access object for internal referrals
	 * @param healthRequestDelegate delegate for health requests
	 * @param providerInternalReferralAssociationDao data access object for
	 * association of provider to internal referral
	 * @param providerLevelDao data access object for provider levels
	 * @param labWorkDao data access object for lab work
	 * @param internalReferralStatusReasonDao data access object for internal
	 * referral status reasons
	 * @param healthAppointmentDao data access object for health appointments
	 * @param offenderAppointmentAssociationDao data access object for an 
	 * offender appointment association
	 * @param labWorkRequirementDao data access object for lab work requirements
	 * @param auditComponentRetriever retriever for audit components
	 * @param labWorkInstanceFactory 
	 * @param offenderAppointmentAssociationInstanceFactory
	 * @param healthAppointmentInstanceFactory
	 * @param labWorkRequirementInstanceFactory
	 */
	public InternalReferralAssessmentServiceImpl(
			final InternalReferralDao internalReferralDao,
			final HealthRequestDelegate healthRequestDelegate,
			final ProviderScheduleChecker 
			providerScheduleChecker,
			final ProviderInternalReferralAssociationDao
			providerInternalReferralAssociationDao,
			final ProviderLevelDao providerLevelDao,
			final LabWorkDao labWorkDao,
			final InternalReferralStatusReasonDao
			internalReferralStatusReasonDao,
			final HealthAppointmentDao healthAppointmentDao,
			final OffenderAppointmentAssociationDao 
			offenderAppointmentAssociationDao,
			final LabWorkRequirementDao labWorkRequirementDao,
			final LabWorkCategoryDao labWorkCategoryDao,
			final ProviderAssignmentDao providerAssignmentDao,
			final LabDao labDao,
			final AuditComponentRetriever auditComponentRetriever,
			final InstanceFactory<LabWork> labWorkInstanceFactory,
			final InstanceFactory<OffenderAppointmentAssociation>
			offenderAppointmentAssociationInstanceFactory,
			final InstanceFactory<HealthAppointment>
			healthAppointmentInstanceFactory,
			final InstanceFactory<LabWorkRequirement>
			labWorkRequirementInstanceFactory) {
		this.internalReferralDao = internalReferralDao;
		this.healthRequestDelegate = healthRequestDelegate;
		this.providerScheduleChecker = providerScheduleChecker;
		this.providerInternalReferralAssociationDao
			= providerInternalReferralAssociationDao;
		this.providerLevelDao = providerLevelDao;
		this.labWorkDao = labWorkDao;
		this.internalReferralStatusReasonDao
			= internalReferralStatusReasonDao;
		this.healthAppointmentDao = healthAppointmentDao;
		this.offenderAppointmentAssociationDao 
			= offenderAppointmentAssociationDao;
		this.labWorkRequirementDao = labWorkRequirementDao;
		this.labWorkCategoryDao = labWorkCategoryDao;
		this.providerAssignmentDao = providerAssignmentDao;
		this.labDao = labDao;
		this.auditComponentRetriever = auditComponentRetriever;
		this.labWorkInstanceFactory = labWorkInstanceFactory;
		this.offenderAppointmentAssociationInstanceFactory = 
				offenderAppointmentAssociationInstanceFactory;
		this.healthAppointmentInstanceFactory = 
				healthAppointmentInstanceFactory;
		this.labWorkRequirementInstanceFactory = 
				labWorkRequirementInstanceFactory;
		}

	/** {@inheritDoc} */
	@Override
	public InternalReferral assess(final InternalReferral referral,
			final Date time, final String notes)
				throws InternalReferralAssessmentException {
		if (referral.getStatusReason() != null) {
			throw new InternalReferralAssessmentException(
					"Referral already assessed");
		}
		referral.getOffenderAppointmentAssociation().getAppointment()
			.setTimeKept(time);
		referral.setStatusReason(
				this.internalReferralStatusReasonDao.findKeptStatusReason());
		referral.setAssessmentNotes(notes);
		referral.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
		referral.getOffenderAppointmentAssociation().getAppointment()
			.setStatus(HealthAppointmentStatus.KEPT);
		return this.internalReferralDao.makePersistent(referral);
	}

	/** {@inheritDoc} */
	@Override
	public InternalReferral update(final InternalReferral referral,
			final Date time, final String notes) {
		referral.getOffenderAppointmentAssociation().getAppointment()
			.setTimeKept(time);
		referral.setAssessmentNotes(notes);
		referral.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
		return this.internalReferralDao.makePersistent(referral);
	}
	
	/** {@inheritDoc} */
	@Override
	public InternalReferral updateSchedule(
			final InternalReferral internalReferral,
			final Date date, final InternalReferralReason reason,
			final ProviderLevel providerLevel, 
			final ProviderAssignment providerAssignment,
			final ReferralLocationDesignator locationDesignator, 
			final String notes)
			throws DuplicateEntityFoundException, DateConflictException,
			ProviderScheduleException {
		if (internalReferralDao.findExcluding(
				internalReferral.getOffenderAppointmentAssociation(), 
				internalReferral) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate internal referral found");
		}
		internalReferral.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		internalReferral.getOffenderAppointmentAssociation().getAppointment()
			.setDate(date);
		internalReferral.setReason(reason);
		internalReferral.setProviderLevel(providerLevel);
		if (!this.providerScheduleChecker.isProviderScheduled(
				providerAssignment, date)) {
			throw new ProviderScheduleException(
						"Provider not available to be scheduled on this date");
		}
		ProviderInternalReferralAssociation providerInternalReferralAssociation
			= this.providerInternalReferralAssociationDao
				.find(providerAssignment, internalReferral);
		providerInternalReferralAssociation
			.setProviderAssignment(providerAssignment);
		internalReferral.setLocationDesignator(locationDesignator);
		internalReferral.setSchedulingNotes(notes);
		return this.internalReferralDao.makePersistent(internalReferral);
	}
	
	/** {@inheritDoc} */
	@Override
	public HealthRequest requestFollowUp(final InternalReferral referral, 
			final Date date, final HealthRequestCategory category, 
			final Boolean labsRequired, final boolean asap,
			final ProviderLevel providerLevel, final String notes)
		throws DuplicateEntityFoundException, FollowUpException {
		if (referral.getActionRequest() != null) {
			throw new FollowUpException("Referral has followup");
		}
		HealthRequest request = this.healthRequestDelegate.createOpen(
				referral.getOffenderAppointmentAssociation()
					.getOffender(),
				referral.getOffenderAppointmentAssociation()
					.getAppointment().getFacility(), labsRequired,
						date, category, asap, providerLevel, notes);
		referral.setActionRequest(request);
		return request;
	}

	/** {@inheritDoc} */
	@Override
	public LabWork scheduleLabWork(final InternalReferral internalReferral,
			final LabWorkCategory category, final Lab sampleLab, 
			final Date sampleDate, final String sampleNotes, 
			final Boolean sampleTaken, final LabWorkResults results,
			final LabWorkOrder order, 
			final LabWorkSampleRestrictions sampleRestrictions,
			final String schedulingNotes)
		throws DuplicateEntityFoundException {
		HealthAppointment appointment = 
				this.healthAppointmentInstanceFactory.createInstance();
		appointment.setDate(sampleDate);
		appointment.setFacility(internalReferral
				.getOffenderAppointmentAssociation().getAppointment()
				.getFacility());
	    this.healthAppointmentDao.makePersistent(appointment);
		OffenderAppointmentAssociation association = 
			this.offenderAppointmentAssociationInstanceFactory.createInstance();
		association.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
			this.createOffenderAppointmentAssociation(association, appointment, 
					internalReferral.getOffenderAppointmentAssociation()
						.getOffender());
		if (this.labWorkDao.find(association) !=null) {
			throw new DuplicateEntityFoundException(
					"Duplicate lab work found");
		}
		LabWork labWork = this.labWorkInstanceFactory.createInstance();
		labWork.setOffenderAppointmentAssociation(association);
		this.populateLabWork(labWork, category, sampleLab, 
				sampleTaken, sampleNotes, results, order, sampleRestrictions, 
				schedulingNotes);
		labWork.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		this.labWorkDao.makePersistent(labWork);
		LabWorkRequirement requirement = 
	 			this.labWorkRequirementInstanceFactory.createInstance();
	  	requirement.setOffenderAppointmentAssociation(association);
	 	requirement.setLabWork(labWork);
	 	this.labWorkRequirementDao.makePersistent(requirement);
	return labWork;
	}

	/** {@inheritDoc} */
	@Override
	public LabWork updateLabWork(final InternalReferral internalReferral,
			final LabWork labWork, final LabWorkCategory category, 
			final Lab sampleLab, final Date sampleDate, 
			final String sampleNotes, final Boolean sampleTaken,
			final LabWorkResults results, final LabWorkOrder order,
			final LabWorkSampleRestrictions sampleRestrictions, 
			final String schedulingNotes) 
		throws DuplicateEntityFoundException {	
		HealthAppointment appointment = 
		labWork.getOffenderAppointmentAssociation().getAppointment();
		appointment.setDate(sampleDate);
		this.healthAppointmentDao.makePersistent(appointment);
		if (this.labWorkDao.findExcluding(labWork, 
				labWork.getOffenderAppointmentAssociation(), category, 
				sampleLab, results.getLab()).size() > 0 ) {
			throw new DuplicateEntityFoundException(
					"Lab work already exists");
		}		
	   this.populateLabWork(labWork, category, sampleLab, sampleTaken, 
			   sampleNotes, results, order, sampleRestrictions, 
			   schedulingNotes);
		return this.labWorkDao.makePersistent(labWork);
	}

	
	/** {@inheritDoc} */
	@Override
	public HealthRequest requestLabFollowUp(final InternalReferral referral,
			final Date date, final boolean asap,
			final String notes)
		throws DuplicateEntityFoundException, FollowUpException {
		if (referral.getActionRequest() != null) {
			throw new FollowUpException("Referral has followup");
		}
		HealthRequest request = this.healthRequestDelegate
				.createOpenLabWorkRequest(
					referral.getOffenderAppointmentAssociation()
						.getOffender(),
					referral.getOffenderAppointmentAssociation()
						.getAppointment().getFacility(),
						date, asap, null, notes);
		referral.setActionRequest(request);
		return request;
	}

	/** {@inheritDoc} */
	@Override
	public ProviderAssignment findPrimaryProvider(
			final InternalReferral internalReferral) {
		ProviderInternalReferralAssociation association
			= this.providerInternalReferralAssociationDao
				.findPrimaryByReferral(internalReferral);
		return association.getProviderAssignment();
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderLevel> findFollowUpProviderLevels() {
		return this.providerLevelDao.findAll();
	}
	
	/**
	 * Populates the specified lab work with the specified properties.
	 */
	private LabWork populateLabWork(final LabWork labWork, 
			final LabWorkCategory category, final Lab sampleLab,
			final Boolean sampleTaken, final String sampleNotes, 
			final LabWorkResults results, final LabWorkOrder order, 
			final LabWorkSampleRestrictions sampleRestrictions, 
			final String schedulingNotes) {
			labWork.setLabWorkCategory(category);
		  	labWork.setSampleLab(sampleLab);
		 	labWork.setSampleNotes(sampleNotes);
		 	labWork.setSampleTaken(sampleTaken);
		 	labWork.setResults(results);
		 	labWork.setOrder(order);
		 	labWork.setSampleRestrictions(sampleRestrictions);
		 	labWork.setSchedulingNotes(schedulingNotes);
		 	labWork.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
		return labWork;
	}
	
	/**
	 * Creates an offender appointment association. 
	 */
	private OffenderAppointmentAssociation createOffenderAppointmentAssociation(
			final OffenderAppointmentAssociation association, 
			final HealthAppointment appointment, final Offender offender) 
		throws DuplicateEntityFoundException {
	    if (this.offenderAppointmentAssociationDao.find(offender, appointment)
	    		!= null) {
	    	throw new DuplicateEntityFoundException(
	    			"Duplicate offender appointment association found");
	    }
	    association.setOffender(offender);
	    association.setAppointment(appointment);
	    association.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	    this.offenderAppointmentAssociationDao.makePersistent(association);
	    return association;
	}	

	/** {@inheritDoc} */
	@Override
	public List<LabWork> findLabWorks(final InternalReferral internalReferral) {
			return this.labWorkDao.findRequiredLabWork(internalReferral
					.getOffenderAppointmentAssociation());
	}

	/** {@inheritDoc} */
	@Override
	public List<LabWorkCategory> findLabWorkCategories() {
		return this.labWorkCategoryDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderAssignment> findProviders(final Facility facility, 
			final Date date) {
		return this.providerAssignmentDao.findByFacility(facility, date);
	}

	/** {@inheritDoc} */
	@Override
	public void removeLabWork(LabWork labWork) {
		this.labWorkDao.makeTransient(labWork);
	}

	/** {@inheritDoc} */
	@Override
	public List<Lab> findLabs() {
		return this.labDao.findAll();
	}
}