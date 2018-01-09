package omis.health.service.impl;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.UpdateSignature;
import omis.health.dao.ExternalReferralDao;
import omis.health.dao.ExternalReferralStatusReasonDao;
import omis.health.domain.ExternalReferral;
import omis.health.domain.ExternalReferralStatusReason;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.exception.ExternalReferralCancellationException;
import omis.health.exception.WrongExternalReferralStatusReasonException;
import omis.health.service.CancelExternalReferralService;

/**
 * Implementation of service to cancel external referral service.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 24, 2014)
 * @since OMIS 3.0
 */
public class CancelExternalReferralServiceImpl
		implements CancelExternalReferralService {

	private final ExternalReferralDao externalReferralDao;
	
	private final ExternalReferralStatusReasonDao 
	externalReferralStatusReasonDao;

	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates implementation of service to cancel external referral
	 * service.
	 * 
	 * @param externalReferralDao data access object for external referrals
	 * @param externalReferralStatusReasonDao data access object for
	 * external referral status reasons
	 * @param auditComponentRetriever audit component retriever
	 */
	public CancelExternalReferralServiceImpl(
			final ExternalReferralDao externalReferralDao,
			final ExternalReferralStatusReasonDao
				externalReferralStatusReasonDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.externalReferralDao = externalReferralDao;
		this.externalReferralStatusReasonDao = externalReferralStatusReasonDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/** {@inheritDoc} */
	@Override
	public ExternalReferral cancel(
			final ExternalReferral externalReferral,
			final ExternalReferralStatusReason cancellationStatusReason)
					throws ExternalReferralCancellationException,
						WrongExternalReferralStatusReasonException {
		if (externalReferral.getStatusReason() != null) {
			throw new ExternalReferralCancellationException(
					"Status reason already set to: "
						+ externalReferral.getStatusReason().getName());
		}
		if (!HealthAppointmentStatus.CANCELLED.equals(
				cancellationStatusReason.getAppointmentStatus())) {
			throw new WrongExternalReferralStatusReasonException(
					"Cannot cancel with status type: "
						+ cancellationStatusReason.getAppointmentStatus()
							.getName());
		}
		externalReferral.setStatusReason(cancellationStatusReason);
		externalReferral.getOffenderAppointmentAssociation().getAppointment()
			.setStatus(cancellationStatusReason.getAppointmentStatus());
		externalReferral.setUpdateSignature(new UpdateSignature(
				auditComponentRetriever.retrieveUserAccount(),
				auditComponentRetriever.retrieveDate()));
		return this.externalReferralDao.makePersistent(externalReferral);
	}

	/** {@inheritDoc} */
	@Override
	public List<ExternalReferralStatusReason> findCancellationStatusReasons() {
		return this.externalReferralStatusReasonDao
				.findByAppointmentStatus(HealthAppointmentStatus.CANCELLED);
	}
}