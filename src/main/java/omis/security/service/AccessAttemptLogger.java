package omis.security.service;

import java.util.Date;

import omis.user.domain.UserAccount;

/**
 * Service to log access attempts.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 6, 2014)
 * @since OMIS 3.0
 */
public interface AccessAttemptLogger {

	/**
	 * Logs a successful access attempt.
	 * 
	 * @param username username
	 * @param date date
	 * @param remoteAddress remote IP
	 * @param remoteHost remote host name
	 * @param userAgent user agent
	 */
	void logSuccess(String username, Date date, String remoteAddress,
			String remoteHost, String userAgent);
	
	/**
	 * Logs a failed access attempt.
	 * 
	 * @param username username
	 * @param date date
	 * @param remoteAddress remote IP
	 * @param remoteHost remote host name
	 * @param userAgent user agent
	 */
	void logFailure(String username, Date date, String remoteAddress,
			String remoteHost, String userAgent);
	
	/**
	 * Increases access attempts of user account. Disable user account if
	 * maximum number of allowed attempts are made.
	 * 
	 * @param userAccount user account
	 * @return updated user account
	 */
	UserAccount increaseAccessAttempt(final UserAccount userAccount);

	/**
	 * Returns user account by username.
	 * 
	 * @param username
	 * @return user account by username
	 */
	UserAccount findByUsername(String username);

	/**
	 * Returns whether user account exists with username.
	 * 
	 * @param username username
	 * @return whether user account exists with username
	 */
	boolean userAccountExists(String username);
	
	/**
	 * Resets access attempt of user account.
	 * 
	 * @param userAccount user account the access attempt of which to reset
	 * @return updated user account
	 */
	UserAccount resetAttempt(UserAccount userAccount);
}