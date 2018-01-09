package omis.user.domain;

import java.io.Serializable;
import java.util.Date;

import omis.person.domain.Person;

/**
 * User account.
 * @author Stephen Abson
 * @version 0.1.3 (Jan 8, 2013)
 * @since OMIS 3.0
 */
public interface UserAccount
		extends Serializable {
	
	/**
	 * Sets the ID.
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Returns the user.
	 * @return user
	 */
	Person getUser();

	/**
	 * Sets the user.
	 * @param user user
	 */
	void setUser(Person user);

	/**
	 * Returns the unique username.
	 * @return unique username
	 */
	String getUsername();

	/**
	 * Sets the unique username.
	 * @param username unique username
	 */
	void setUsername(String username);

	/**
	 * Returns the password.
	 * @return password
	 */
	String getPassword();

	/**
	 * Sets the password.
	 * @param password password
	 */
	void setPassword(String password);
	
	/**
	 * Returns whether the user account is enabled.
	 * @return whether user account is enabled
	 */
	Boolean getEnabled();

	/**
	 * Sets whether the user account is enabled.
	 * @param enabled whether user account is enabled
	 */
	void setEnabled(Boolean enabled);
	
	/**
	 * Returns the password expiration date.
	 * 
	 * @return password expiration date
	 */
	Date getPasswordExpirationDate();
	
	/**
	 * Sets the password expiration date.
	 * 
	 * @param passwordExpirationDate password expiration date
	 */
	void setPasswordExpirationDate(Date passwordExpirationDate);
	
	/**
	 * Returns the login attempt.
	 * <p>
	 * This represents the number of failed attempts to login using this account
	 * since the last successful login. Once a successful login is made, this
	 * field should be set to {@code 0}.
	 * @return login attempt
	 */
	Integer getAttempt();
	
	/**
	 * Sets the login attempt.
	 * <p>
	 * This represents the number of failed attempts to login using this account
	 * since the last successful login. Once a successful login is made, this
	 * field should be set to {@code 0}.
	 * @param attempt login attempt
	 */
	void setAttempt(Integer attempt);
	
	/** Increases the login attempt (by {@code 1}). */
	void incrementAttempt();
	
	/** Resets the login attempt (to {@code 0}). */
	void resetAttempt();
	
	/** Enables the user account. */
	void enable();
	
	/** Disables the user account. */
	void disable();
	
	/**
	 * Compares {@code this} and {@code o} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code o} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null}
	 */ 
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
	
	/**
	 * Returns the user name.
	 * @return username
	 */
	@Override
	String toString();
}