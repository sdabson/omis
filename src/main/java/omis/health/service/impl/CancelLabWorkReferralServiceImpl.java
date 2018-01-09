package omis.health.service.impl;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.UpdateSignature;
import omis.health.dao.LabWorkReferralDao;
import omis.health.dao.LabWorkReferralStatusReasonDao;
import omis.health.domain.HealthAppointmentStatus;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.LabWorkReferralStatusReason;
import omis.health.exception.LabWorkReferralCancellationException;
import omis.health.exception.WrongLabWorkReferralStatusReasonException;
import omis.health.service.CancelLabWorkReferralService;

/**
 * Implementation of cancel lab work referral service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 20, 2014)
 * @since OMIS 3.0
 *
 */
public class CancelLabWorkReferralServiceImpl 
	implements CancelLabWorkReferralService {

	/* Data access objects. */
	
	private LabWorkReferralDao labWorkReferralDao;
	
	private LabWorkReferralStatusReasonDao labWorkReferralStatusReasonDao;
	
	/* Component retrievers. */
	private AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates a cancel lab work referral service implementation with the
	 * specified data access object(s) and component retriever(s).
	 * @param labWorkReferralDao lab work referral data access object
	 * @param labWorkReferralStatusReasonDao lab work referral status reason
	 * data access object
	 * @param auditComponentRetriever audit component retriever
	 */
	public CancelLabWorkReferralServiceImpl(
			final LabWorkReferralDao labWorkReferralDao,
			final LabWorkReferralStatusReasonDao labWorkReferralStatusReasonDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.labWorkReferralDao = labWorkReferralDao;
		this.labWorkReferralStatusReasonDao = labWorkReferralStatusReasonDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LabWorkReferralStatusReason> findCancellationStatusReasons() {
		return this.labWorkReferralStatusReasonDao.findByAppointmentStatus(
					HealthAppointmentStatus.CANCELLED);
	}

	/** {@inheritDoc} */
	@Override
	public LabWorkReferral cancel(final LabWorkReferral labWorkReferral,
			final LabWorkReferralStatusReason statusReason)
		throws LabWorkReferralCancellationException,
		WrongLabWorkReferralStatusReasonException {
		if (labWorkReferral.getStatusReason() != null) {
			throw new LabWorkReferralCancellationException(
					"Status Reason already set to: " 
							+ labWorkReferral.getStatusReason().getName());
		}
		if (!HealthAppointmentStatus.CANCELLED.equals(statusReason
				.getAppointmentStatus())) {
			throw new WrongLabWorkReferralStatusReasonException(
					"Cannot cancel with status type: "
						+ statusReason.getAppointmentStatus().getName());
		}
		labWorkReferral.setStatusReason(statusReason);
		labWorkReferral.getOffenderAppointmentAssociation().getAppointment()
			.setStatus(statusReason.getAppointmentStatus());
		labWorkReferral.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.labWorkReferralDao.makePersistent(labWorkReferral);
	}

}