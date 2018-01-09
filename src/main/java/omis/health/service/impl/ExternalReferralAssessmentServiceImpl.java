package omis.health.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.health.dao.ExternalReferralDao;
import omis.health.dao.ExternalReferralStatusReasonDao;
import omis.health.dao.ProviderLevelDao;
import omis.health.domain.ExternalReferral;
import omis.health.domain.ExternalReferralStatusReason;
import omis.health.domain.HealthRequest;
import omis.health.domain.HealthRequestCategory;
import omis.health.domain.ProviderLevel;
import omis.health.exception.ExternalReferralAssessmentException;
import omis.health.exception.FollowUpException;
import omis.health.service.ExternalReferralAssessmentService;
import omis.health.service.delegate.HealthRequestDelegate;

/**
 * Implementation of service to assess external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 8, 2014)
 * @since OMIS 3.0
 */
public class ExternalReferralAssessmentServiceImpl
		implements ExternalReferralAssessmentService {

	private final ExternalReferralDao externalReferralDao;
	
	private final ExternalReferralStatusReasonDao
	externalReferralStatusReasonDao;
	
	private final HealthRequestDelegate healthRequestDelegate;
	
	private final ProviderLevelDao providerLevelDao;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates an implementation of service to assess external referrals.
	 * 
	 * @param externalReferralDao data access object for external referrals
	 * @param externalReferralStatusReasonDao data access object for external
	 * referral status reasons
	 * @param healthRequestDelegate delegate for health requests
	 * @param providerLevelDao data access object for provider levels
	 * @param auditComponentRetriever retriever of audit components
	 */
	public ExternalReferralAssessmentServiceImpl(
			final ExternalReferralDao externalReferralDao,
			final ExternalReferralStatusReasonDao
				externalReferralStatusReasonDao,
			final HealthRequestDelegate healthRequestDelegate,
			final ProviderLevelDao providerLevelDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.externalReferralDao = externalReferralDao;
		this.externalReferralStatusReasonDao = externalReferralStatusReasonDao;
		this.healthRequestDelegate = healthRequestDelegate;
		this.providerLevelDao = providerLevelDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public ExternalReferral assess(
			final ExternalReferral externalReferral, final Date timeKept,
			final String notes, final Date reportedDate)
					throws ExternalReferralAssessmentException {
		if (externalReferral.getStatusReason() != null) {
			throw new ExternalReferralAssessmentException(
					"External referral already assessed");
		}
		ExternalReferralStatusReason keptStatusReason
			= this.externalReferralStatusReasonDao.findKeptStatusReason();
		externalReferral.setStatusReason(keptStatusReason);
		externalReferral.getOffenderAppointmentAssociation()
			.getAppointment().setStatus(keptStatusReason
					.getAppointmentStatus());
		return this.update(externalReferral, timeKept, notes, reportedDate);
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferral update(final ExternalReferral externalReferral,
			final Date timeKept, final String notes, final Date reportedDate) {
		externalReferral.getOffenderAppointmentAssociation()
			.getAppointment().setTimeKept(timeKept);
		externalReferral.setAssessmentNotes(notes);
		externalReferral.setReportedDate(reportedDate);
		externalReferral.setUpdateSignature(new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		return this.externalReferralDao.makePersistent(externalReferral);
	}
	
	/** {@inheritDoc} */
	@Override
	public HealthRequest requestFollowUp(
			final ExternalReferral externalReferral,
			final Date date, final HealthRequestCategory category,
			final Boolean labsRequired, final boolean asap,
			final ProviderLevel providerLevel, final String notes)
					throws DuplicateEntityFoundException,
						FollowUpException {
		HealthRequest request = this.healthRequestDelegate.createOpen(
				externalReferral.getOffenderAppointmentAssociation()
					.getOffender(),
				externalReferral.getOffenderAppointmentAssociation()
					.getAppointment().getFacility(), labsRequired,
						date, category, asap, providerLevel, notes);
		externalReferral.setActionRequest(request);
		this.externalReferralDao.makePersistent(externalReferral);
		return request;
	}

	/** {@inheritDoc} */
	@Override
	public HealthRequest requestLabFollowUp(
			final ExternalReferral externalReferral,
			final Date date, final boolean asap,
			final String notes)
			throws DuplicateEntityFoundException,
				FollowUpException {
		HealthRequest request = this.healthRequestDelegate
					.createOpenLabWorkRequest(
						externalReferral.getOffenderAppointmentAssociation()
							.getOffender(),
						externalReferral.getOffenderAppointmentAssociation()
							.getAppointment().getFacility(),
							date, asap, null, notes);
		externalReferral.setActionRequest(request);
		this.externalReferralDao.makePersistent(externalReferral);
		return request;
	}

	/** {@inheritDoc} */
	@Override
	public List<ProviderLevel> findFollowUpProviderLevels() {
		return this.providerLevelDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public boolean isResolved(final ExternalReferral externalReferral) {
		return externalReferral.getStatusReason() != null;
	}
}