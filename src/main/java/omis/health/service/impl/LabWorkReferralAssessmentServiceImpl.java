package omis.health.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.health.dao.HealthAppointmentDao;
import omis.health.dao.LabDao;
import omis.health.dao.LabWorkDao;
import omis.health.dao.LabWorkReferralAssociationDao;
import omis.health.dao.LabWorkReferralDao;
import omis.health.dao.LabWorkReferralStatusReasonDao;
import omis.health.dao.OffenderAppointmentAssociationDao;
import omis.health.dao.ProviderLevelDao;
import omis.health.domain.HealthAppointment;
import omis.health.domain.HealthRequest;
import omis.health.domain.HealthRequestCategory;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.health.domain.ProviderLevel;
import omis.health.domain.component.LabWorkResults;
import omis.health.exception.FollowUpException;
import omis.health.exception.LabWorkReferralAssessmentException;
import omis.health.service.LabWorkReferralAssessmentService;
import omis.health.service.delegate.HealthRequestDelegate;
import omis.location.domain.Location;

/**
 * Lab work referral assessment service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 14, 2014)
 * @since OMIS 3.0
 */
public class LabWorkReferralAssessmentServiceImpl 
	implements LabWorkReferralAssessmentService {

	/* Data access objects. */
	
	private LabWorkReferralDao labWorkReferralDao;
	
	private LabWorkDao labWorkDao;
	
	private LabDao labDao;
	
	private ProviderLevelDao providerLevelDao;
	
	private LabWorkReferralStatusReasonDao labWorkReferralStatusReasonDao;
	
	private LabWorkReferralAssociationDao labWorkReferralAssociationDao;
	
	private OffenderAppointmentAssociationDao offenderAppointmentAssociationDao;
	
	private HealthAppointmentDao healthAppointmentDao;
	
	/* Component retrievers. */
	
	private AuditComponentRetriever auditComponentRetriever;
	
	/* Helpers. */
	
	private HealthRequestDelegate healthRequestDelegate;
	
	/**
	 * Instantiates a lab work referral assessment service with the specified
	 * data access objects and component retriever.
	 * 
	 * @param labWorkReferralDao lab work referral data access object
	 * @param labWorkDao lab work data access object
	 * @param labDao lab data access object
	 * @param providerLevelDao provider level data access object
	 * @param labWorkReferralStatusReasonDao lab work referral status reason
	 * data access object
	 * @param auditComponentRetriever audit component retriever
	 */
	public LabWorkReferralAssessmentServiceImpl(
			final LabWorkReferralDao labWorkReferralDao,
			final LabWorkDao labWorkDao,
			final LabDao labDao, 
			final ProviderLevelDao providerLevelDao,
			final LabWorkReferralStatusReasonDao labWorkReferralStatusReasonDao,
			final LabWorkReferralAssociationDao labWorkReferralAssociationDao,
			final OffenderAppointmentAssociationDao 
			offenderAppointmentAssociationDao,
			final HealthAppointmentDao healthAppointmentDao,
			final AuditComponentRetriever auditComponentRetriever,
			final HealthRequestDelegate healthRequestDelegate) {
		this.labWorkReferralDao = labWorkReferralDao;
		this.labWorkDao = labWorkDao;
		this.labDao = labDao;
		this.providerLevelDao = providerLevelDao;
		this.labWorkReferralStatusReasonDao = labWorkReferralStatusReasonDao;
		this.labWorkReferralAssociationDao = labWorkReferralAssociationDao;
		this.offenderAppointmentAssociationDao = 
				offenderAppointmentAssociationDao;
		this.healthAppointmentDao = healthAppointmentDao;
		this.auditComponentRetriever = auditComponentRetriever;
		this.healthRequestDelegate = healthRequestDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public LabWorkReferral assess(final LabWorkReferral labWorkReferral,
			final String notes) 
		throws DuplicateEntityFoundException, 
		LabWorkReferralAssessmentException {
		if (labWorkReferral.getStatusReason() != null) {
			throw new LabWorkReferralAssessmentException(
					"Referral already assessed");
		}
		if (this.labWorkReferralDao.findExcluding(labWorkReferral, 
				labWorkReferral.getOffenderAppointmentAssociation()) 
				!= null) {
			throw new DuplicateEntityFoundException("Duplicate lab work"
					+ " referral found.");
		}
		labWorkReferral.setStatusReason(this.labWorkReferralStatusReasonDao
				.findKeptStatusReason());
		labWorkReferral.setAssessmentNotes(notes);
		labWorkReferral.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.labWorkReferralDao.makePersistent(labWorkReferral);
	}

	/** {@inheritDoc} */
	@Override
	public LabWork updateLabWork(final LabWorkReferral labWorkReferral,
			final LabWork labWork, final Boolean sampleTaken, 
			final LabWorkResults labWorkResults) {
		labWork.setResults(labWorkResults);
		labWork.setSampleTaken(sampleTaken);
		labWork.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(), 
				this.auditComponentRetriever.retrieveDate()));
		return this.labWorkDao.makePersistent(labWork);
	}

	/** {@inheritDoc} */
	@Override
	public List<Lab> findLabsAtLocations(final Location location) {
		return this.labDao.findLabsAtLocation(location);
	}

	/** {@inheritDoc} */
	@Override
	public List<Lab> findLabs() {
		return this.labDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<LabWork> findLabWorks(LabWorkReferral labWorkReferral) {
		return this.labWorkDao.findLabWorkByReferral(labWorkReferral);
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderLevel> findFollowUpProviderLevels() {
		return this.providerLevelDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public HealthRequest requestLabFollowUp(
			final LabWorkReferral labWorkReferral,
			final Date date, final boolean asap, final String notes)
			throws DuplicateEntityFoundException, FollowUpException {
		if (labWorkReferral.getActionRequest() != null) {
			throw new FollowUpException("Referral has followup");
		}
		HealthRequest request = this.healthRequestDelegate
				.createOpenLabWorkRequest(labWorkReferral
						.getOffenderAppointmentAssociation().getOffender(), 
						labWorkReferral.getOffenderAppointmentAssociation()
						.getAppointment().getFacility(), date, asap, 
						null, notes);
		labWorkReferral.setActionRequest(request);
		return request;
	}

	/** {@inheritDoc} */
	@Override
	public HealthRequest requestInternalFollowUp(
			final LabWorkReferral labWorkReferral, final Date date,
			final HealthRequestCategory category, final Boolean labsRequired, 
			final boolean asap, final ProviderLevel providerLevel, 
			final String notes)
			throws DuplicateEntityFoundException, FollowUpException {
		if (labWorkReferral.getActionRequest() != null) {
			throw new FollowUpException("Referral has followup");
		}
		HealthRequest request = this.healthRequestDelegate.createOpen(
				labWorkReferral.getOffenderAppointmentAssociation()
				.getOffender(), labWorkReferral
				.getOffenderAppointmentAssociation().getAppointment()
				.getFacility(), labsRequired, date, category, asap, null, 
				notes);
		labWorkReferral.setActionRequest(request);
		return request;
	}

	/** {@inheritDoc} */
	@Override
	public void removeLabWork(LabWorkReferral labWorkReferral, LabWork labWork) {
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
}