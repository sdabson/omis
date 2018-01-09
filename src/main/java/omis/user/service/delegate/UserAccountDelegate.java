package omis.user.service.delegate;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.user.dao.UserAccountDao;
import omis.user.domain.UserAccount;

/**
 * Delegate to look up user accounts by username.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 10, 2014)
 * @since OMIS 3.0
 */
public class UserAccountDelegate {

	private final UserAccountDao userAccountDao;
	
	private final InstanceFactory<UserAccount> userAccountInstanceFactory;
	
	private final boolean caseSensitiveUsername;
	
	private final boolean allowPassword;
	
	private final int maxAttempts;

	/**
	 * Instantiates a delegate to look up user accounts by username.
	 * 
	 * @param userAccountDao data access object for user accounts
	 * @param userAccountInstanceFactory instance factory for user accounts 
	 * @param caseSensitiveUsername whether username is case sensitive
	 * @param allowPassword whether password is allowed
	 * @param maxAttempts maximum attempts 
	 */
	public UserAccountDelegate(
			final UserAccountDao userAccountDao,
			final InstanceFactory<UserAccount> userAccountInstanceFactory,
			final boolean caseSensitiveUsername,
			final boolean allowPassword,
			final int maxAttempts) {
		this.userAccountDao = userAccountDao;
		this.userAccountInstanceFactory = userAccountInstanceFactory;
		this.caseSensitiveUsername = caseSensitiveUsername;
		this.allowPassword = allowPassword;
		this.maxAttempts = maxAttempts;
	}
	
	/**
	 * Returns a user account by username.
	 * 
	 * @param username username of user account 
	 * @return user account with specified username
	 */
	public UserAccount findByUsername(final String username) {
		return this.userAccountDao.findByUsername(
				username, this.caseSensitiveUsername);
	}
	
	/**
	 * Returns whether a user account with the specified username exists.
	 * 
	 * @param username username of account the existence of which to determine
	 * @return whether user account with username exists
	 */
	public boolean exists(final String username) {
		return this.userAccountDao.userAccountExists(username,
				this.caseSensitiveUsername);
	}
	
	/**
	 * Searches and returns user accounts.
	 * 
	 * @param query query with which to search
	 * @return user accounts matching query
	 */
	public List<UserAccount> search(final String query) {
		if (this.caseSensitiveUsername) {
			return this.userAccountDao.search(query);
		} else {
			return this.userAccountDao.searchCaseInsensitive(query);
		}
	}
	
	/**
	 * Creates user account.
	 * 
	 * @param user user
	 * @param username username
	 * @param password password ignored if {@code allowPassword} is
	 * {@code false}
	 * @param passwordExpirationDate password expiration date ignored if
	 * {@code allowPassword} is {@code false}
	 * @param attempt attempt
	 * @param enabled whether enabled
	 * @return created user account
	 * @throws DuplicateEntityFoundException if user account exists
	 */
	public UserAccount create(final Person user, final String username,
			final String password, final Date passwordExpirationDate,
			final Integer attempt, final Boolean enabled)
				throws DuplicateEntityFoundException {
		if (this.userAccountDao.findByUsername(username, caseSensitiveUsername)
				!= null) {
			throw new DuplicateEntityFoundException("User account exists");
		}
		UserAccount userAccount = this.userAccountInstanceFactory
				.createInstance();
		userAccount.setUser(user);
		userAccount.setUsername(username);
		if (allowPassword) {
			userAccount.setPassword(password);
			userAccount.setPasswordExpirationDate(passwordExpirationDate);
			userAccount.setAttempt(attempt);
			userAccount.setEnabled(enabled);
		}
		
		return this.userAccountDao.makePersistent(userAccount);
	}
	
	/**
	 * Updates user account.
	 * 
	 * @param userAccount user account
	 * @param password password ignored if {@code allowPassword} is
	 * {@code false}
	 * @param passwordExpirationDate password expiration date ignored if
	 * {@code allowPassword} is {@code false}
	 * @param attempt attempt
	 * @param enabled whether enabled
	 * @return update user account
	 */
	public UserAccount update(final UserAccount userAccount,
			final String password, final Date passwordExpirationDate,
			final Integer attempt, final Boolean enabled) {
		if (this.allowPassword) {
			userAccount.setPassword(password);
			userAccount.setPasswordExpirationDate(passwordExpirationDate);
			userAccount.setAttempt(attempt);
			userAccount.setEnabled(enabled);
		}
		
		return this.userAccountDao.makePersistent(userAccount);
	}
	
	/**
	 * Removes user account.
	 * 
	 * @param userAccount user account to remove
	 */
	public void remove(final UserAccount userAccount) {
		this.userAccountDao.makeTransient(userAccount);
	}

	/**
	 * Returns user accounts by user.
	 * 
	 * @param user user
	 * @return user accounts by user
	 */
	public List<UserAccount> findByUser(final Person user) {
		return this.userAccountDao.findByUser(user);
	}
	
	/**
	 * Increases access attempt of user account.
	 * 
	 * @param userAccount user account
	 * @return updated user accounts
	 */
	public UserAccount increaseAccessAttempt(final UserAccount userAccount) {
		if (userAccount.getAttempt() + 1 == this.maxAttempts) {
			userAccount.disable();
		} else {
			userAccount.incrementAttempt();
		}
		return this.userAccountDao.makePersistent(userAccount);
	}
	
	/**
	 * Returns user accounts.
	 * 
	 * @return user accounts
	 */
	public List<UserAccount> findAll() {
		return this.userAccountDao.findAll();
	}

	/**
	 * Resets access attempt of user account.
	 * 
	 * @param userAccount user account the accessa attempt of which to reset
	 * @return updated user account
	 */
	public UserAccount resetAttempt(final UserAccount userAccount) {
		userAccount.resetAttempt();
		return this.userAccountDao.makePersistent(userAccount);
	}
}