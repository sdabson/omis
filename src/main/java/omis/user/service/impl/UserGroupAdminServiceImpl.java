package omis.user.service.impl;

import java.util.List;

import omis.instance.factory.InstanceFactory;
import omis.user.dao.UserAccountDao;
import omis.user.dao.UserGroupDao;
import omis.user.dao.UserRoleAssignmentDao;
import omis.user.dao.UserRoleDao;
import omis.user.domain.UserAccount;
import omis.user.domain.UserGroup;
import omis.user.domain.UserRole;
import omis.user.domain.UserRoleAssignment;
import omis.user.service.UserGroupAdminService;

/**
 * Implementation of service for user groups.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Sept 27, 2013)
 * @since OMIS 3.0
 */
public class UserGroupAdminServiceImpl
		implements UserGroupAdminService {
	
	private final UserGroupDao userGroupDao;
	
	private final UserRoleDao userRoleDao;
	
	private final UserAccountDao userAccountDao;
	
	private final InstanceFactory<UserRoleAssignment>
	userRoleAssignmentInstanceFactory;
	
	private final UserRoleAssignmentDao userRoleAssignmentDao;
	
	/**
	 * Instantiates an implementation of service for user groups.
	 * 
	 * @param userGroupDao data access object for user groups
	 * @param userRoleDao data access object for user roles
	 * @param userAccountDao data access object for user accounts
	 * @param userRoleAssignmentInstanceFactory instance factory for user
	 * role assignments
	 * @param userRoleAssignmentDao data access object for user role
	 * assignments
	 */
	public UserGroupAdminServiceImpl(
			final UserGroupDao userGroupDao,
			final UserRoleDao userRoleDao,
			final UserAccountDao userAccountDao,
			final InstanceFactory<UserRoleAssignment>
				userRoleAssignmentInstanceFactory,
			final UserRoleAssignmentDao userRoleAssignmentDao) {
		this.userGroupDao = userGroupDao;
		this.userRoleDao = userRoleDao;
		this.userAccountDao = userAccountDao;
		this.userRoleAssignmentInstanceFactory
			= userRoleAssignmentInstanceFactory;
		this.userRoleAssignmentDao = userRoleAssignmentDao;
	}

	/** {@inheritDoc} */
	@Override
	public List<UserGroup> findAll() {
		return this.userGroupDao.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public UserGroup save(final UserGroup userGroup) {
		return this.userGroupDao.makePersistent(userGroup);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final UserGroup userGroup) {
		this.userGroupDao.makeTransient(userGroup);
	}

	/** {@inheritDoc} */
	@Override
	public short findMaxSortOrder() {
		return this.userGroupDao.findMaxSortOrder();
	}

	/** {@inheritDoc} */
	@Override
	public List<UserGroup> findWithMember(final UserAccount userAccount) {
		return this.userGroupDao.findByUserAccount(userAccount);
	}

	/** {@inheritDoc} */
	@Override
	public List<UserRole> findRoles() {
		return this.userRoleDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<UserRole> findRolesByGroup(final UserGroup group) {
		return this.userRoleDao.findByGroups(group);
	}

	/** {@inheritDoc} */
	@Override
	public List<UserAccount> findMembers(final UserGroup group) {
		return this.userAccountDao.findByGroup(group);
	}

	/** {@inheritDoc} */
	@Override
	public int clearRoles(final UserGroup group) {
		return this.userRoleAssignmentDao.deleteByUserGroup(group);
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