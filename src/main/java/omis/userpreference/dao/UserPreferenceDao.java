package omis.userpreference.dao;

import omis.dao.GenericDao;
import omis.user.domain.UserAccount;
import omis.userpreference.domain.UserPreference;

/**
 * User preference data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 20, 2015)
 * @since OMIS 3.0
 */
public interface UserPreferenceDao extends GenericDao<UserPreference> {

	/**
	 * Returns the user preference for the specified user account.
	 * 
	 * @param userAccount user account
	 * @return user preference
	 */
	UserPreference findByAccount(UserAccount userAccount);
}