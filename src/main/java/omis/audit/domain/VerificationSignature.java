package omis.audit.domain;

import java.util.Date;

import omis.user.domain.UserAccount;

/**
 * Signature for the verification of recorded information.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 26, 2012)
 * @since OMIS 3.0
 */
public class VerificationSignature extends AbstractSignature {

	private static final long serialVersionUID = 1L;

	private Boolean result;
	
	private VerificationMethod method;

	/** Instantiates a default verification signature. */
	public VerificationSignature() {
		// Do nothing
	}
	
	/**
	 * Instantiates a verification signature with the specified mandatory
	 * property values.
	 * 
	 * @param userAccount account of user that performed verification
	 * @param date date of verification
	 * @param result verification result
	 * @param method verification method 
	 */
	public VerificationSignature(final UserAccount userAccount, final Date date,
			final Boolean result, final VerificationMethod method) {
		super(userAccount, date);
		this.result = result;
		this.method = method;
	}
	
	/**
	 * Returns the verification result.
	 * 
	 * @return verification result
	 */
	public Boolean getResult() {
		return this.result;
	}

	/**
	 * Sets the verification result.
	 * 
	 * @param result verification result
	 */
	public void setResult(final Boolean result) {
		this.result = result;
	}

	/**
	 * Returns the method of verification.
	 * 
	 * @return verification method
	 */
	public VerificationMethod getMethod() {
		return this.method;
	}

	/**
	 * Sets the method of verification.
	 * 
	 * @param method verification method
	 */
	public void setMethod(final VerificationMethod method) {
		this.method = method;
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
		if (object == this) {
			return true;
		}
		if (!(object instanceof VerificationSignature)) {
			return false;
		}
		VerificationSignature that = (VerificationSignature) object;
		if (getUserAccount() != null) {
			if (!getUserAccount().equals(that.getUserAccount())) {
				return false;
			}
		} else if (that.getUserAccount() != null) {
			return false;
		}
		if (getDate() != null) {
			if (!getDate().equals(that.getDate())) {
				return false;
			}
		} else if (that.getDate() != null) {
			return false;
		}
		if (this.getResult() != null) {
			if (!this.getResult().equals(that.getResult())) {
				return false;
			}
		} else if (that.getResult() != null) {
			return false;
		}
		if (this.getMethod() != null) {
			if (!this.getMethod().equals(that.getMethod())) {
				return false;
			}
		} else if (that.getMethod() != null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns an hash code for {@code this}.
	 * <p>
	 * Properties whose values are {@code null} do not form part of the hash
	 * code. Thus, no properties are assumed to be mandatory.
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
			hashCode = 29 * hashCode + getDate().hashCode();
		}
		if (this.getResult() != null) {
			hashCode = 29 * hashCode + this.getResult().hashCode();
		}
		if (this.getMethod() != null) {
			hashCode = 29 * hashCode + this.getMethod().hashCode();
		}
		return hashCode;
	}
}