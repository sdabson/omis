package omis.security.service.impl;

import java.util.Date;

import omis.instance.factory.InstanceFactory;
import omis.security.dao.AccessAttemptDao;
import omis.security.domain.AccessAttempt;
import omis.security.service.AccessAttemptLogger;
import omis.user.domain.UserAccount;
import omis.user.service.delegate.UserAccountDelegate;

/**
 * Implementation of service to log access attempts.
 * 
 * @author Stephen Abson
 * @version 0.2.0 (March 8, 2016)
 * @since OMIS 3.0
 */
public class AccessAttemptLoggerImpl
		implements AccessAttemptLogger {

	private final AccessAttemptDao accessAttemptDao;
	
	private final InstanceFactory<AccessAttempt> accessAttemptInstanceFactory;
	
	private final UserAccountDelegate userAccountDelegate;
	
	/**
	 * Instantiates an implementation of service to log access attempts.
	 * 
	 * @param accessAttemptDao data access object for access attempts
	 * @param accessAttemptInstanceFactory instance factory for access attempts
	 * @param userAccountDelegate delegate for user accounts
	 */
	public AccessAttemptLoggerImpl(
			final AccessAttemptDao accessAttemptDao,
			final InstanceFactory<AccessAttempt> accessAttemptInstanceFactory,
			final UserAccountDelegate userAccountDelegate) {
		this.accessAttemptDao = accessAttemptDao;
		this.accessAttemptInstanceFactory = accessAttemptInstanceFactory;
		this.userAccountDelegate = userAccountDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public void logSuccess(final String username, final Date date,
			final String remoteAddress, final String remoteHost,
			final String userAgent) {
		logAttempt(username, date, remoteAddress, remoteHost, true, userAgent);
	}

	/** {@inheritDoc} */
	@Override
	public void logFailure(final String username, final Date date,
			final String remoteAddress, final String remoteHost,
			final String userAgent) {
		logAttempt(username, date, remoteAddress, remoteHost, false, userAgent);
	}

	// Logs an attempt
	private void logAttempt(final String username, final Date date,
			final String remoteAddress, final String remoteHost,
			final Boolean success, final String userAgent) {
		AccessAttempt attempt
				= this.accessAttemptInstanceFactory.createInstance();
		attempt.setUsername(username);
		attempt.setDate(date);
		attempt.setRemoteAddress(remoteAddress);
		attempt.setRemoteHost(remoteHost);
		attempt.setSuccess(success);
		attempt.setUserAgent(userAgent);
		this.accessAttemptDao.makePersistent(attempt);
	}
	
	/** {@inheritDoc} */
	@Override
	public UserAccount increaseAccessAttempt(final UserAccount userAccount) {
		return this.userAccountDelegate.increaseAccessAttempt(userAccount);
	}

	/** {@inheritDoc} */
	@Override
	public UserAccount findByUsername(final String username) {
		return this.userAccountDelegate.findByUsername(username);
	}

	/** {@inheritDoc} */
	@Override
	public boolean userAccountExists(final String username) {
		return this.userAccountDelegate.findByUsername(username) != null;
	}

	/** {@inheritDoc} */
	@Override
	public UserAccount resetAttempt(final UserAccount userAccount) {
		return this.userAccountDelegate.resetAttempt(userAccount);
	}
}
