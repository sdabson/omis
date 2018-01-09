package omis.health.service.impl;

import omis.health.service.ReferralCenterService;
import omis.user.dao.UserAccountDao;
import omis.user.domain.UserAccount;

/** Implementation of referral center service.
 * @author Ryan Johns
 * @version 0.1.0 (Apr 8, 2014)
 * @since OMIS 3.0 */
public class ReferralCenterServiceImpl implements ReferralCenterService {
	private final UserAccountDao userAccountDao;


	/** Constructor.
	 * @param userAccountDao user account dao. */
	public ReferralCenterServiceImpl(final UserAccountDao userAccountDao) {
		this.userAccountDao = userAccountDao;
	}

	/** {@inheritDoc} */
	@Override
	public UserAccount findUserAccountByUsername(final String username) {
		return this.userAccountDao.findByUsername(username);
	}

}
