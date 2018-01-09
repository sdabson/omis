package omis.health.service;

import java.util.List;

import omis.health.domain.ExternalReferral;
import omis.health.domain.ExternalReferralStatusReason;
import omis.health.exception.ExternalReferralCancellationException;
import omis.health.exception.WrongExternalReferralStatusReasonException;

/**
 * Service to cancel external referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 24, 2014)
 * @since OMIS 3.0
 */
public interface CancelExternalReferralService {

	/**
	 * Cancels external referral.
	 * 
	 * @param externalReferral external referral to cancel
	 * @param cancellationStatusReason status reason for cancellation
	 * @return cancelled external referral
	 * @throws ExternalReferralCancellationException if the external
	 * referral is not scheduled
	 * @throws WrongExternalReferralStatusReasonException if the
	 * cancellation status reason does not have a cancel status
	 */
	ExternalReferral cancel(ExternalReferral externalReferral,
			ExternalReferralStatusReason cancellationStatusReason)
				throws ExternalReferralCancellationException,
				WrongExternalReferralStatusReasonException;
	
	/**
	 * Returns cancellation status reasons.
	 * 
	 * @return cancellation status reasons
	 */
	List<ExternalReferralStatusReason> findCancellationStatusReasons();
}