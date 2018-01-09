package omis.user.dao;

import omis.dao.GenericDao;
import omis.user.domain.UserGroup;
import omis.user.domain.UserRole;
import omis.user.domain.UserRoleAssignment;

/**
 * Data access object for user role assignments.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public interface UserRoleAssignmentDao
		extends GenericDao<UserRoleAssignment> {

	/**
	 * Deletes assignments by user role.
	 * 
	 * @param userRole user role
	 * @return number of deleted assignments
	 */
	int deleteByUserRole(UserRole userRole);
	
	/**
	 * Deletes assignments by user group.
	 * 
	 * @param userGroup user group
	 * @return number of deleted assignments
	 */
	int deleteByUserGroup(UserGroup userGroup);

	/**
	 * Returns assignment.
	 * 
	 * @param userGroup group
	 * @param userRole role
	 * @return assignment
	 */
	UserRoleAssignment find(UserGroup userGroup, UserRole userRole);
}