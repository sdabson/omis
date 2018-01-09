package omis.audit.domain;

import java.util.Date;

import omis.user.domain.UserAccount;

/**
 * Abstract implementation of the signature of an auditable action.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 26, 2012)
 * @since OMIS 3.0
 */
public abstract class AbstractSignature
		implements Signature {

	private static final long serialVersionUID = 1L;
	
	private UserAccount userAccount;
	
	private Long date;
	
	/** Instantiates a default signature. */
	protected AbstractSignature() {
		// Do nothing
	}
	
	/**
	 * Instantiates a signature with the specified properties.
	 * 
	 * @param userAccount account of user the performed auditable action
	 * @param date when auditable action was performed
	 */
	protected AbstractSignature(final UserAccount userAccount,
			final Date date) {
		this.userAccount = userAccount;
		if (date != null) {
			this.date = date.getTime();
		} else {
			this.date = null;
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	/** {@inheritDoc} */
	@Override
	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		if (this.date != null) {
			return new Date(this.date);
		} else {
			return null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		if (date != null) {
			this.date = date.getTime();
		} else {
			this.date = null;
		}
	}
	
	/**
	 * Compares {@code this} and {@code object} for equality.
	 * 
	 * <p>Properties whose values are {@code null} are skipped in the
	 * comparison. Thus, no properties are assumed to be mandatory.
	 * 
	 * @param object reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code object} are equal;
	 * {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof Signature)) {
			return false;
		}
		Signature that = (Signature) object;
		if (this.getUserAccount() != null) {
			if (!this.getUserAccount().equals(that.getUserAccount())) {
				return false;
			}
		} else if (that.getUserAccount() != null) {
			return false;
		}
		if (this.getDate() != null) {
			if (!this.getDate().equals(that.getDate())) {
				return false;
			}
		} else if (that.getDate() != null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns an hash code for {@code this}.
	 * 
	 * <p>Properties whose values are {@code null} do not form part of the hash
	 * code. Thus, no properties are assumed to be mandatory.
	 * 
	 * @return hash code 
	 */
	@Override
	public int hashCode() {
		int hashCode = 14;
		if (this.getUserAccount() != null) {
			hashCode = 29 * hashCode
					+ this.getUserAccount().getUsername().hashCode();
		}
		if (this.getDate() != null) {
			hashCode = 29 * hashCode + this.getDate().hashCode();
		}
		return hashCode;
	}
	
	/**
	 * Returns a string representation of the signature containing the username
	 * of the account of the user that performed the auditable action and
	 * when the action was performed.
	 * 
	 * @return string containing username and date
	 */
	@Override
	public String toString() {
		return String.format("%s at %s", this.getUserAccount().getUsername(),
				this.getDate());
	}
}