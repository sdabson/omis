package omis.health.service;

import omis.user.domain.UserAccount;

/** Referral center service.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 8, 2014)
 * @since OMIS 3.0 */
public interface ReferralCenterService {

	/** find user account by username.
	 * @param username username.
	 * @return user account. */
	UserAccount findUserAccountByUsername(String username);
}
