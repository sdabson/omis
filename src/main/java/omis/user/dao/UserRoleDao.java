package omis.user.dao;

import java.util.Collection;
import java.util.List;

import omis.dao.GenericDao;
import omis.user.domain.UserGroup;
import omis.user.domain.UserRole;

/**
 * Data access object for user roles.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Sept 27, 2013)
 * @since OMIS 3.0
 */
public interface UserRoleDao
		extends GenericDao<UserRole> {

	/**
	 * Returns user roles granted by membership of the specified user groups.
	 * 
	 * @param userGroups user groups the granted roles of which to return
	 * @return roles granted by membership of groups
	 */
	List<UserRole> findByUserGroups(Collection<UserGroup> userGroups);
	
	/**
	 * Returns the highest sort order value.
	 * 
	 * @return highest sort order value
	 */
	short findMaxSortOrder();

	/**
	 * Returns roles by group.
	 * 
	 * @param group group
	 * @return roles by group
	 */
	List<UserRole> findByGroups(UserGroup group);
}