package omis.health.service;

import java.util.List;

import omis.health.domain.InternalReferral;
import omis.health.domain.InternalReferralStatusReason;
import omis.health.exception.InternalReferralCancellationException;
import omis.health.exception.WrongInternalReferralStatusReasonException;

/**
 * Service to cancel internal referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 24, 2014)
 * @since OMIS 3.0
 */
public interface CancelInternalReferralService {

	/**
	 * Cancels internal referral.
	 * 
	 * @param internalReferral internal referral
	 * @param cancellationStatusReason cancellation status reason
	 * @return cancelled internal referral
	 * @throws InternalReferralCancellationException if the external referral
	 * is not scheduled
	 * @throws WrongInternalReferralStatusReasonException if the status reason
	 * is not a cancellation type
	 */
	InternalReferral cancel(InternalReferral internalReferral,
			InternalReferralStatusReason cancellationStatusReason)
				throws InternalReferralCancellationException,
					WrongInternalReferralStatusReasonException;
	
	/**
	 * Returns cancellation status reasons.
	 * 
	 * @return cancellation status reasons
	 */
	List<InternalReferralStatusReason> findCancellationStatusReasons();
}