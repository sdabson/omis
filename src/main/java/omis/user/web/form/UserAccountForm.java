package omis.user.web.form;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import omis.user.domain.UserGroup;

/**
 * Form for user accounts.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Sept 26, 2013)
 * @since OMIS 3.0
 */
public class UserAccountForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Boolean allowUser;
	
	private String lastName;
	
	private String firstName;
	
	private String middleName;
	
	private String suffix;
	
	private String username;
	
	private Boolean allowPassword;
	
	private String newPassword;
	
	private String confirmPassword;
	
	private Boolean enabled;
	
	private Date passwordExpirationDate;
	
	private Boolean passwordNeverExpires;
	
	private Boolean allowGroups;
	
	private Set<UserGroup> groups = new HashSet<UserGroup>();

	/** Instantiates a default user account form. */
	public UserAccountForm() {
		// Default instantiation
	}

	/**
	 * Returns whether user is allowed to be edited.
	 * 
	 * @return whether user is allowed to be edited
	 */
	public Boolean getAllowUser() {
		return this.allowUser;
	}
	
	/**
	 * Sets whether user is allowed to be edited.
	 * 
	 * @param allowUser whether user is allowed to be edited
	 */
	public void setAllowUser(final Boolean allowUser) {
		this.allowUser = allowUser;
	}
	
	/**
	 * Returns the last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets the last name.
	 * 
	 * @param lastName last name
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns first name.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets the last name.
	 * 
	 * @param firstName first name
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the middle name.
	 * 
	 * @return middle name
	 */
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * Sets the middle name.
	 * 
	 * @param middleName middle name
	 */
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Returns suffix.
	 * 
	 * @return suffix
	 */
	public String getSuffix() {
		return this.suffix;
	}

	/**
	 * Sets the suffix.
	 * 
	 * @param suffix suffix
	 */
	public void setSuffix(final String suffix) {
		this.suffix = suffix;
	}

	/**
	 * Returns the username of the account.
	 * 
	 * @return username of account
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Sets the username of the account.
	 * 
	 * @param username username of account
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * Returns whether password is allowed.
	 * 
	 * @return whether password is allowed
	 */
	public Boolean getAllowPassword() {
		return this.allowPassword;
	}

	/**
	 * Sets whether password is allowed.
	 * 
	 * @param allowPassword whether password is allowed
	 */
	public void setAllowPassword(final Boolean allowPassword) {
		this.allowPassword = allowPassword;
	}

	/**
	 * Returns the new password for the account.
	 * 
	 * @return new password for account
	 */
	public String getNewPassword() {
		return this.newPassword;
	}

	/**
	 * Sets the new password for the account.
	 * 
	 * @param newPassword new password for account
	 */
	public void setNewPassword(final String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * Returns the confirmation of the new password for the account.
	 * 
	 * @return confirmation of new password for account
	 */
	public String getConfirmPassword() {
		return this.confirmPassword;
	}

	/**
	 * Sets the confirmation of the new password for the account.
	 * 
	 * @param confirmPassword confirmation of new password for account
	 */
	public void setConfirmPassword(final String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * Returns whether the account is enabled.
	 * 
	 * @return whether account is enabled
	 */
	public Boolean getEnabled() {
		return this.enabled;
	}

	/**
	 * Sets whether the account is enabled.
	 * 
	 * @param enabled whether account is enabled
	 */
	public void setEnabled(final Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Returns the password expiration date.
	 * 
	 * @return password expiration date
	 */
	public Date getPasswordExpirationDate() {
		return this.passwordExpirationDate;
	}

	/**
	 * Sets the password expiration date.
	 * 
	 * @param passwordExpirationDate password expiration date
	 */
	public void setPasswordExpirationDate(
			final Date passwordExpirationDate) {
		this.passwordExpirationDate = passwordExpirationDate;
	}

	/**
	 * Returns whether the password does not expire.
	 * 
	 * @return whether password does not expire
	 */
	public Boolean getPasswordNeverExpires() {
		return this.passwordNeverExpires;
	}

	/**
	 * Sets whether the password does not expire.
	 * 
	 * @param passwordNeverExpires whether password does not expire
	 */
	public void setPasswordNeverExpires(
			final Boolean passwordNeverExpires) {
		this.passwordNeverExpires = passwordNeverExpires;
	}

	/**
	 * Returns whether groups are allowed.
	 * 
	 * @return whether groups are allowed
	 */
	public Boolean getAllowGroups() {
		return this.allowGroups;
	}

	
	/**
	 * Sets whether groups are allowed.
	 * 
	 * @param allowGroups whether groups are allowed
	 */
	public void setAllowGroups(final Boolean allowGroups) {
		this.allowGroups = allowGroups;
	}

	/**
	 * Returns the groups of which the account is a member.
	 * 
	 * @return groups of which account is a member
	 */
	public Set<UserGroup> getGroups() {
		return this.groups;
	}

	/**
	 * Sets the groups of which the account is a member.
	 * 
	 * @param groups groups of which account is a member
	 */
	public void setGroups(final Set<UserGroup> groups) {
		this.groups = groups;
	}

	/**
	 * Replaces the groups of which the account is a member.
	 * 
	 * @param groups groups of which to set the account as an exclusive member
	 */
	public void replaceGroups(final Collection<UserGroup> groups) {
		this.groups.clear();
		this.groups.addAll(groups);
	}
}