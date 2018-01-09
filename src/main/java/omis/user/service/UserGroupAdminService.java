package omis.user.service;

import java.util.List;

import omis.user.domain.UserAccount;
import omis.user.domain.UserGroup;
import omis.user.domain.UserRole;
import omis.user.domain.UserRoleAssignment;

/**
 * Service for user groups.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Sept 27, 2013)
 * @since OMIS 3.0
 */
public interface UserGroupAdminService {
	
	/**
	 * Returns all user groups.
	 * 
	 * @return all user groups
	 */
	List<UserGroup> findAll();
	
	/**
	 * Saves a user group.
	 * 
	 * @param userGroup user group to save.
	 * @return saved user group
	 */
	UserGroup save(UserGroup userGroup);
	
	/**
	 * Removes the user group.
	 * 
	 * @param userGroup user group to remove
	 */
	void remove(UserGroup userGroup);
	
	/**
	 * Returns the highest sort order value.
	 * 
	 * @return highest sort order value
	 */
	short findMaxSortOrder();
	
	/**
	 * Returns groups with user account as member.
	 * 
	 * @param userAccount user account
	 * @return groups with user account as member
	 */
	List<UserGroup> findWithMember(UserAccount userAccount);

	/**
	 * Returns roles.
	 * 
	 * @return roles
	 */
	List<UserRole> findRoles();

	/**
	 * Returns roles by group.
	 * 
	 * @param group group
	 * @return roles by group
	 */
	List<UserRole> findRolesByGroup(UserGroup group);

	/**
	 * Returns members of group.
	 * 
	 * @param group group
	 * @return members of group
	 */
	List<UserAccount> findMembers(UserGroup group);

	/**
	 * Removes roles assigned to group.
	 * 
	 * @param group group
	 * @return number of role assignments removed
	 */
	int clearRoles(UserGroup group);

	/**
	 * Assigns user role to user group.
	 * 
	 * @param userGroup user group
	 * @param userRole user role
	 * @return assignment
	 */
	UserRoleAssignment assignUserRole(UserGroup userGroup, UserRole userRole);

	/**
	 * Returns whether user group is assigned to user role.
	 * 
	 * @param userGroup user group
	 * @param userRole user role
	 * @return whether user group is assigned to user role
	 */
	boolean isAssignedUserRole(UserGroup userGroup, UserRole userRole);

	/**
	 * Removes user role assignment.
	 * 
	 * @param userGroup user group
	 * @param userRole user role
	 * @return whether assignment was removed
	 */
	boolean removeUserRoleAssignment(UserGroup userGroup, UserRole userRole);
}