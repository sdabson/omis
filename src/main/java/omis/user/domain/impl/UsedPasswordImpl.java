package omis.user.domain.impl;

import java.util.Date;

import omis.user.domain.UsedPassword;
import omis.user.domain.UserAccount;

/**
 * Implementation of used password.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 28, 2014)
 * @since OMIS 3.0
 */
public class UsedPasswordImpl
		implements UsedPassword {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private UserAccount userAccount;
	
	private String value;
	
	private Date dateChanged;

	/** Instantiates an implementation of user password. */
	public UsedPasswordImpl() {
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
	public void setValue(final String value) {
		this.value = value;
	}

	/** {@inheritDoc} */
	@Override
	public String getValue() {
		return this.value;
	}

	/** {@inheritDoc} */
	@Override
	public void setDateChanged(final Date dateChanged) {
		this.dateChanged = dateChanged;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDateChanged() {
		return this.dateChanged;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof UsedPassword)) {
			return false;
		}
		UsedPassword that = (UsedPassword) obj;
		if (this.getUserAccount() == null) {
			throw new IllegalStateException("User account required");
		}
		if (!this.getUserAccount().equals(that.getUserAccount())) {
			return false;
		}
		if (this.getValue() == null) {
			throw new IllegalStateException("Password required");
		}
		if (!this.getValue().equals(that.getValue())) {
			return false;
		}
		if (this.getDateChanged() == null) {
			throw new IllegalStateException("Date changed required");
		}
		if (!this.getDateChanged().equals(that.getDateChanged())) {
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
		if (this.getValue() == null) {
			throw new IllegalStateException("Password required");
		}
		if (this.getDateChanged() == null) {
			throw new IllegalStateException("Date changed required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getUserAccount().hashCode();
		hashCode = 29 * hashCode + this.getValue().hashCode();
		hashCode = 29 * hashCode + this.getDateChanged().hashCode();
		return hashCode;
	}
}