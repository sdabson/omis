package omis.user.web.form;

import java.io.Serializable;

/**
 * Allows an old password to be captured as well as a new and verification
 * of a new password.
 * 
 * <p>Such information is required to change a password.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 10, 2012)
 * @since OMIS 3.0
 */
public class PasswordChangeForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String oldPassword;
	
	private String newPassword;
	
	private String confirmedNewPassword;

	/** Instantiates a new password change form. */
	public PasswordChangeForm() {
		// Do nothing
	}
	
	/**
	 * Returns the old password.
	 * 
	 * @return old password
	 */
	public String getOldPassword() {
		return this.oldPassword;
	}

	/**
	 * Sets the old password.
	 * 
	 * @param oldPassword old password
	 */
	public void setOldPassword(final String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * Returns the new password.
	 * 
	 * @return new password
	 */
	public String getNewPassword() {
		return this.newPassword;
	}

	/**
	 * Sets the new password.
	 * 
	 * @param newPassword new password
	 */
	public void setNewPassword(final String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * Returns the confirmation of the new password.
	 * 
	 * @return confirmation of new password
	 */
	public String getConfirmedNewPassword() {
		return this.confirmedNewPassword;
	}

	/**
	 * Sets the confirmation of the new password.
	 * 
	 * @param confirmedNewPassword confirmation of new password
	 */
	public void setConfirmedNewPassword(final String confirmedNewPassword) {
		this.confirmedNewPassword = confirmedNewPassword;
	}	
}