package omis.user.domain.impl;

import omis.user.domain.UserGroup;
import omis.user.domain.UserRole;
import omis.user.domain.UserRoleAssignment;

/**
 * Assignment of user role to group.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class UserRoleAssignmentImpl
		implements UserRoleAssignment {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private UserRole userRole;
	
	private UserGroup userGroup;
	
	/** Instantiates implementation of assignment of user role to group. */
	public UserRoleAssignmentImpl() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setUserRole(final UserRole userRole) {
		this.userRole = userRole;
	}

	/** {@inheritDoc} */
	@Override
	public UserRole getUserRole() {
		return this.userRole;
	}

	/** {@inheritDoc} */
	@Override
	public void setUserGroup(final UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	/** {@inheritDoc} */
	@Override
	public UserGroup getUserGroup() {
		return this.userGroup;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof UserRoleAssignment)) {
			return false;
		}
		UserRoleAssignment that = (UserRoleAssignment) obj;
		if (this.getUserRole() == null) {
			throw new IllegalStateException("User role required");
		}
		if (!this.getUserRole().equals(that.getUserRole())) {
			return false;
		}
		if (this.getUserGroup() == null) {
			throw new IllegalStateException("User group required");
		}
		if (!this.getUserGroup().equals(that.getUserGroup())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getUserRole() == null) {
			throw new IllegalStateException("User role required");
		}
		if (this.getUserGroup() == null) {
			throw new IllegalStateException("User group required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getUserRole().hashCode();
		hashCode = 29 * hashCode + this.getUserGroup().hashCode();
		return hashCode;
	}
}