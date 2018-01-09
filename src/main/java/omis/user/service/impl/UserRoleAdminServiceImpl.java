package omis.user.service.impl;

import java.util.Collection;
import java.util.List;

import omis.instance.factory.InstanceFactory;
import omis.user.dao.UserGroupDao;
import omis.user.dao.UserRoleAssignmentDao;
import omis.user.dao.UserRoleDao;
import omis.user.domain.UserGroup;
import omis.user.domain.UserRole;
import omis.user.domain.UserRoleAssignment;
import omis.user.service.UserRoleAdminService;

/**
 * Implementation of service for user roles.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (Sept 27, 2013)
 * @since OMIS 3.0
 */
public class UserRoleAdminServiceImpl
		implements UserRoleAdminService {
	
	private final UserRoleDao userRoleDao;
	
	private final UserGroupDao userGroupDao;
	
	private final InstanceFactory<UserRoleAssignment>
	userRoleAssignmentInstanceFactory;
	
	private final UserRoleAssignmentDao userRoleAssignmentDao;

	/**
	 * Instantiates an implementation of service for user roles.
	 * 
	 * @param userRoleDao data access object for user roles
	 * @param userGroupDao data access object for user groups
	 * @param userRoleAssignmentInstanceFactory instance factory for
	 * user role assignments
	 * @param userRoleAssignmentDao data access object for user role assignments
	 */
	public UserRoleAdminServiceImpl(
			final UserRoleDao userRoleDao,
			final UserGroupDao userGroupDao,
			final InstanceFactory<UserRoleAssignment>
				userRoleAssignmentInstanceFactory,
			final UserRoleAssignmentDao userRoleAssignmentDao) {
		this.userRoleDao = userRoleDao;
		this.userGroupDao = userGroupDao;
		this.userRoleAssignmentInstanceFactory
			= userRoleAssignmentInstanceFactory;
		this.userRoleAssignmentDao = userRoleAssignmentDao;
	}

	/** {@inheritDoc} */
	@Override
	public List<UserRole> findAll() {
		return this.userRoleDao.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public UserRole save(final UserRole userRole) {
		return this.userRoleDao.makePersistent(userRole);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final UserRole userRole) {
		this.userRoleDao.makeTransient(userRole);
	}

	/** {@inheritDoc} */
	@Override
	public List<UserRole> findByUserGroups(
			final Collection<UserGroup> userGroups) {
		return this.userRoleDao.findByUserGroups(userGroups);
	}

	/** {@inheritDoc} */
	@Override
	public short findMaxSortOrder() {
		return this.userRoleDao.findMaxSortOrder();
	}

	/** {@inheritDoc} */
	@Override
	public List<UserGroup> findGroups() {
		return this.userGroupDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<UserGroup> findGroupsByRole(final UserRole role) {
		return this.userGroupDao.findByRole(role);
	}

	/** {@inheritDoc} */
	@Override
	public int clearGroups(final UserRole role) {
		return this.userRoleAssignmentDao.deleteByUserRole(role);
	}

	/** {@inheritDoc} */
	@Override
	public UserRoleAssignment assignUserRole(
			final UserGroup userGroup, final UserRole userRole) {
		UserRoleAssignment assignment = this.userRoleAssignmentInstanceFactory
				.createInstance();
		assignment.setUserGroup(userGroup);
		assignment.setUserRole(userRole);
		return this.userRoleAssignmentDao.makePersistent(assignment);
	}

	/** {@inheritDoc} */
	@Override
	public boolean isAssignedUserRole(
			final UserGroup userGroup, final UserRole userRole) {
		if (this.userRoleAssignmentDao.find(userGroup, userRole) != null) {
			return true;
		} else {
			return false;
		}
	}

	/** {@inheritDoc} */
	@Override
	public boolean removeUserRoleAssignment(
			final UserGroup userGroup, final UserRole userRole) {
		UserRoleAssignment assignment = this.userRoleAssignmentDao
				.find(userGroup, userRole);
		if (assignment != null) {
			this.userRoleAssignmentDao.makeTransient(assignment);
			return true;
		} else {
			return false;
		}
	}
}