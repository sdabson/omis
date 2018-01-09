package omis.health.service.impl;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.UpdateSignature;
import omis.health.dao.InternalReferralDao;
import omis.health.dao.InternalReferralStatusReasonDao;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.InternalReferral;
import omis.health.domain.InternalReferralStatusReason;
import omis.health.exception.InternalReferralCancellationException;
import omis.health.exception.WrongInternalReferralStatusReasonException;
import omis.health.service.CancelInternalReferralService;

/**
 * Implementation of service to cancel internal referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 24, 2014)
 * @since OMIS 3.0
 */
public class CancelInternalReferralServiceImpl
		implements CancelInternalReferralService {

	private InternalReferralDao internalReferralDao;
	
	private InternalReferralStatusReasonDao internalReferralStatusReasonDao;
	
	private AuditComponentRetriever auditComponentRetriever;

	/**
	 * Instantiates an implementation of service to cancel internal referrals.
	 * 
	 * @param internalReferralDao data access object for internal referrals
	 * @param internalReferralStatusReasonDao data access object for internal
	 * referral status reasons
	 * @param auditComponentRetriever audit component retriever
	 */
	public CancelInternalReferralServiceImpl(
			final InternalReferralDao internalReferralDao,
			final InternalReferralStatusReasonDao
				internalReferralStatusReasonDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.internalReferralDao = internalReferralDao;
		this.internalReferralStatusReasonDao = internalReferralStatusReasonDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** {@inheritDoc} */
	@Override
	public InternalReferral cancel(
			final InternalReferral internalReferral,
			final InternalReferralStatusReason cancellationStatusReason)
					throws InternalReferralCancellationException,
						WrongInternalReferralStatusReasonException {
		if (internalReferral.getStatusReason() != null) {
			throw new InternalReferralCancellationException(
					"Status reason already set to: "
						+ internalReferral.getStatusReason().getName());
		}
		if (!HealthAppointmentStatus.CANCELLED.equals(
				cancellationStatusReason.getAppointmentStatus())) {
			throw new WrongInternalReferralStatusReasonException(
					"Cannot cancel with status type: "
						+ cancellationStatusReason.getAppointmentStatus()
							.getName());
		}
		internalReferral.setStatusReason(cancellationStatusReason);
		internalReferral.getOffenderAppointmentAssociation().getAppointment()
			.setStatus(cancellationStatusReason.getAppointmentStatus());
		internalReferral.setUpdateSignature(new UpdateSignature(
				auditComponentRetriever.retrieveUserAccount(),
				auditComponentRetriever.retrieveDate()));
		return this.internalReferralDao.makePersistent(internalReferral);
	}

	/** {@inheritDoc} */
	@Override
	public List<InternalReferralStatusReason> findCancellationStatusReasons() {
		return this.internalReferralStatusReasonDao
				.findByAppointmentStatus(HealthAppointmentStatus.CANCELLED);
	}
}