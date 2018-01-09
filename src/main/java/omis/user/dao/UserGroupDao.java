package omis.user.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.user.domain.UserAccount;
import omis.user.domain.UserGroup;
import omis.user.domain.UserRole;

/**
 * Data access object for user groups.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Sept 27, 2013)
 * @since OMIS 3.0
 */
public interface UserGroupDao
		extends GenericDao<UserGroup> {

	/**
	 * Returns the highest sort order value.
	 * 
	 * @return highest sort order value
	 */
	short findMaxSortOrder();
	
	/**
	 * Returns groups by user account.
	 * 
	 * @param userAccount user account
	 * @return groups by user account
	 */
	List<UserGroup> findByUserAccount(UserAccount userAccount);

	/**
	 * Returns groups by role.
	 * 
	 * @param role role
	 * @return groups by role
	 */
	List<UserGroup> findByRole(UserRole role);
}