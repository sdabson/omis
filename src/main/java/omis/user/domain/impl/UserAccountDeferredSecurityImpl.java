package omis.user.domain.impl;

import java.util.Date;

import omis.person.domain.Person;
import omis.user.domain.UserAccount;

/** Implementation of user account.
 * <p>Defers Password, enabled, and attempts implementation to alternative 
 * means. </p>
 * 
 * @author Ryan Johns
 * @version 0.1.0 (May 23, 2016)
 * @since OMIS 3.0 */ 
public class UserAccountDeferredSecurityImpl implements UserAccount {
	public static final long serialVersionUID = 1l;
	private Long id;
	private String username;
	private Person user;
	
	private static final String EXPIRATION_DATE_NOT_SUPPORTED_MSG 
		= "Expiration date not supported";
	private static final String PASSWORD_MANAGEMENT_NOT_SUPPORTED_MSG 
		= "Password management not supported";
	private static final String ENABLED_NOT_SUPPORTED_MSG 
		= "Enabled not supported"; 
	private static final String ATTEMPT_MANAGEMENT_NOT_SUPPORTED_MSG
		= "Attempt management not supported";
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setUsername(final String username) {
		this.username = username;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setUser(final Person user) {
		this.user = user;
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() { 
		return this.id;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getUsername() {
		return this.username; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Person getUser() {
		return this.user;
	}
	
	/** <p><b>Unsupported</b> {@inheritDoc}</p> */
	@Override
	public void setPassword(final String password) {
		throw new UnsupportedOperationException(
				PASSWORD_MANAGEMENT_NOT_SUPPORTED_MSG);
	}
	
	/** <p><b>Unsupported</b> {@inheritDoc}</p> */
	@Override
	public void setPasswordExpirationDate(final Date expirationDate) {
		throw new UnsupportedOperationException(
				EXPIRATION_DATE_NOT_SUPPORTED_MSG);
	}
	
	
	/** <p><b>Unsupported</b> {@inheritDoc}</p> */
	@Override
	public String getPassword() {
		throw new UnsupportedOperationException(
				PASSWORD_MANAGEMENT_NOT_SUPPORTED_MSG);
	}
	
	/** <p><b>Unsupported</b> {@inheritDoc}</p> */
	@Override
	public Date getPasswordExpirationDate() {
		throw new UnsupportedOperationException(
				PASSWORD_MANAGEMENT_NOT_SUPPORTED_MSG);
	}
	
	/** <p><b>Unsupported</b> {@inhertiDoc}</p> */
	@Override
	public Integer getAttempt() {
		throw new UnsupportedOperationException(
				ENABLED_NOT_SUPPORTED_MSG);
	}
	
	/** <p><b>Unsupported</b> {@inheritDoc}</p> */
	@Override
	public Boolean getEnabled() {
		throw new UnsupportedOperationException(
				ENABLED_NOT_SUPPORTED_MSG);
	}
	
	/** <p><b>Unsupported</b> {@inheritDoc}</p> */
	@Override
	public void enable() {
		throw new UnsupportedOperationException(
				ENABLED_NOT_SUPPORTED_MSG);
	}
	
	/** <p><b>Unsupported</b> {@inheritDoc}</p> */
	@Override
	public void disable() {
		throw new UnsupportedOperationException(ENABLED_NOT_SUPPORTED_MSG);
	}
	
	/** <p><b>Unsupported</b> {@inheritDoc}</p> */
	@Override
	public void setEnabled(final Boolean enabled) {
		throw new UnsupportedOperationException(ENABLED_NOT_SUPPORTED_MSG);
	}
	
	/** <p><b>Unsupported</b> {@inheritDoc}</p> */
	@Override
	public void setAttempt(final Integer attempt) {
		throw new UnsupportedOperationException(
				ATTEMPT_MANAGEMENT_NOT_SUPPORTED_MSG);
	}
	
	/** <p><b>Unsupported</b> {@inheritDoc}</p> */
	@Override
	public void incrementAttempt() {
		throw new UnsupportedOperationException(
				ATTEMPT_MANAGEMENT_NOT_SUPPORTED_MSG);
	}
	
	/** <p><b>Unsupported</b> {@inheritDoc}</p> */
	@Override
	public void resetAttempt() {
		throw new UnsupportedOperationException(
				ATTEMPT_MANAGEMENT_NOT_SUPPORTED_MSG);
	}
		
}
