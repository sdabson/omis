package omis.user.domain.impl;

import omis.user.domain.UserAccount;
import omis.user.domain.UserGroup;
import omis.user.domain.UserGroupMembership;

/**
 * Implementation of membership of user in group.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class UserGroupMembershipImpl
		implements UserGroupMembership {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private UserAccount userAccount;
	
	private UserGroup userGroup;
	
	/** Instantiates implementation of membership of user in group. */
	public UserGroupMembershipImpl() {
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
	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	/** {@inheritDoc} */
	@Override
	public UserAccount getUserAccount() {
		return this.userAccount;
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
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof UserGroupMembership)) {
			return false;
		}
		UserGroupMembership that = (UserGroupMembership) obj;
		if (this.getUserAccount() == null) {
			throw new IllegalStateException("User account required");
		}
		if (!this.getUserAccount().equals(that.getUserAccount())) {
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
		if (this.getUserAccount() == null) {
			throw new IllegalStateException("User account required");
		}
		if (this.getUserGroup() == null) {
			throw new IllegalStateException("User group required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getUserAccount().hashCode();
		hashCode = 29 * hashCode + this.getUserGroup().hashCode();
		return hashCode;
	}
}