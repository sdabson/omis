package omis.user.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;
import omis.person.domain.Suffix;
import omis.security.domain.AccessAttempt;
import omis.user.domain.UserAccount;

/**
 * Service for user account management related operations.
 * 
 * @author Stephen Abson
 * @version 0.2.0 (March 7, 2016)
 * @since OMIS 3.0
 */
public interface UserAccountService {
	
	/**
	 * Returns a user account by username.
	 * 
	 * @param username username of user account 
	 * @return user account with specified username
	 */
	UserAccount findByUsername(String username);

	/**
	 * Returns all user accounts.
	 * 
	 * @return all user accounts
	 */
	List<UserAccount> findAll();
	
	/**
	 * Returns person name suffixes.
	 * 
	 * @return person name suffixes 
	 */
	List<Suffix> findSuffixes();
	
	/**
	 * Returns all user accounts for the specified user.
	 * 
	 * @param user user whose accounts to return
	 * @return all user accounts for specified user
	 */
	List<UserAccount> findByUser(Person user);
	
	/**
	 * Returns whether a user account with the specified username exists.
	 * 
	 * @param username username of account the existence of which to determine
	 * @return whether user account with username exists
	 */
	boolean userAccountExists(String username);
	
	/**
	 * Creates person.
	 * 
	 * @param lastName last name
	 * @param firstName first name
	 * @param middleName middle name
	 * @param suffix suffix
	 * @return created person
	 * @throws DuplicateEntityFoundException if person exists
	 */
	Person createPerson(String lastName, String firstName, String middleName,
			String suffix)
					throws DuplicateEntityFoundException;
	
	/**
	 * Creates user accounts.
	 * 
	 * @param user user
	 * @param username username
	 * @param password password
	 * @param passwordExpirationDate password expiration date
	 * @param enabled whether account is enabled
	 * @return created user account
	 * @throws DuplicateEntityFoundException if account exists
	 */
	UserAccount create(Person user, String username, String password,
			Date passwordExpirationDate, Boolean enabled)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates user account.
	 * 
	 * <p>If account is enabled, resets attempts.
	 * 
	 * @param userAccount user account
	 * @param password password
	 * @param passwordExpirationDate password expiration date
	 * @param enabled whether account is enabled
	 * @return updated user account
	 */
	UserAccount update(UserAccount userAccount, String password,
			Date passwordExpirationDate, Boolean enabled);
	
	/**
	 * Removes a user account.
	 * 
	 * @param userAccount user account to remove
	 */
	void remove(UserAccount userAccount);

	/**
	 * Returns access attempts by user account.
	 * 
	 * @param account account
	 * @return access attempts by user account
	 */
	List<AccessAttempt> findByUserAccount(UserAccount account);
}