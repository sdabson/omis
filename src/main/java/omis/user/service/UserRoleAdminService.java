package omis.user.service;

import java.util.Collection;
import java.util.List;

import omis.user.domain.UserGroup;
import omis.user.domain.UserRole;
import omis.user.domain.UserRoleAssignment;

/**
 * Service for user roles.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Sept 27, 2013)
 * @since OMIS 3.0
 */
public interface UserRoleAdminService {
	
	/**
	 * Returns all user roles.
	 * 
	 * @return all user roles
	 */
	List<UserRole> findAll();
	
	/**
	 * Saves a user role.
	 * 
	 * @param userRole user role to save
	 * @return saved user role
	 */
	UserRole save(UserRole userRole);
	
	/**
	 * Removes a user role.
	 * 
	 * @param userRole user role to remove
	 */
	void remove(UserRole userRole);
	
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
	 * Returns groups.
	 * 
	 * @return groups
	 */
	List<UserGroup> findGroups();

	/**
	 * Returns groups by role.
	 * 
	 * @param role role
	 * @return groups by role
	 */
	List<UserGroup> findGroupsByRole(UserRole role);

	/**
	 * Removes groups assignment to role.
	 * 
	 * @param role role
	 * @return number of role assignments removed
	 */
	int clearGroups(UserRole role);
	
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