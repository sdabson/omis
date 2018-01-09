package omis.user.dao;

import omis.dao.GenericDao;
import omis.user.domain.UserAccount;
import omis.user.domain.UserGroup;
import omis.user.domain.UserGroupMembership;

/**
 * Data access object for memberships of user groups.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface UserGroupMembershipDao
		extends GenericDao<UserGroupMembership> {
	
	/**
	 * Deletes memberships by user group.
	 * 
	 * @param userGroup user group
	 * @return number of deleted memberships
	 */
	int deleteByUserGroup(UserGroup userGroup);
	
	/**
	 * Deletes memberships by user account.
	 * 
	 * @param userAccount user account
	 * @return number of deleted memberships
	 */
	int deleteByUserAccount(UserAccount userAccount);
}
