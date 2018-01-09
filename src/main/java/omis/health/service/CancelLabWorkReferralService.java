package omis.health.service;

import java.util.List;

import omis.health.domain.LabWorkReferral;
import omis.health.domain.LabWorkReferralStatusReason;
import omis.health.exception.LabWorkReferralCancellationException;
import omis.health.exception.WrongLabWorkReferralStatusReasonException;

/**
 * Service to cancel lab work referrals.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 20, 2014)
 * @since OMIS 3.0
 *
 */
public interface CancelLabWorkReferralService {

	/**
	 * Returns a list of lab work referral status reasons with a health 
	 * appointment status of {@code CANCELLED}.
	 * 
	 * @return list of relevant lab work referral status reasons
	 */
	List<LabWorkReferralStatusReason> findCancellationStatusReasons();

	/**
	 * Cancels the specified lab work referral with the specified lab work
	 * referral status reason.
	 * 
	 * @param labWorkReferral lab work referral
	 * @param statusReason lab work referral status reason
	 * @return cancelled lab work referral
	 * @throws LabWorkReferralCancellationException lab work referral
	 * cancellation exception
	 * @throws WrongLabWorkReferralStatusReasonException wrong lab work
	 * referral status reason exception
	 */
	LabWorkReferral cancel(LabWorkReferral labWorkReferral,
			LabWorkReferralStatusReason statusReason)
		throws LabWorkReferralCancellationException, 
		WrongLabWorkReferralStatusReasonException;
}