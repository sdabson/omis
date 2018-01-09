package omis.user.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.person.domain.Person;
import omis.user.domain.UserAccount;
import omis.user.domain.UserGroup;

/**
 * Data access object for user account.
 * 
 * @author Stephen Abson
 * @version 0.1.5 (April 2, 2013)
 * @since OMIS 3.0
 */
public interface UserAccountDao
		extends GenericDao<UserAccount> {

	/**
	 * Returns all user accounts of the specified user.
	 * 
	 * @param user user whose accounts to return
	 * @return accounts of specified user
	 */
	List<UserAccount> findByUser(Person user);
	
	/**
	 * Returns the user account with the specified username.
	 * 
	 * @param username username of account to return
	 * @param caseSensitive whether the username is case sensitive
	 * @return user account with specified username
	 */
	UserAccount findByUsername(String username, boolean caseSensitive);
	
	/**
	 * Returns the user account with the specified case sensitive username.
	 * 
	 * @param username username of account to return
	 * @return user account with specified username
	 */
	UserAccount findByUsername(String username);
	
	/**
	 * Returns whether a user account with the specified username exists.
	 * 
	 * @param username username of account the existence of which to determine
	 * @param caseSensitive whether the username is case sensitive
	 * @return whether user account with username exists
	 */
	boolean userAccountExists(String username, boolean caseSensitive);

	/**
	 * Searches and returns user accounts.
	 * 
	 * @param query query with which to search
	 * @return user accounts matching query
	 */
	List<UserAccount> search(String query);

	/**
	 * Searches and returns user accounts.
	 * 
	 * <p>Case insensitive.
	 * 
	 * @param query query with which to search
	 * @return user accounts matching query
	 */
	List<UserAccount> searchCaseInsensitive(String query);

	/**
	 * Returns users by group.
	 * 
	 * @param group group
	 * @return users by group
	 */
	List<UserAccount> findByGroup(UserGroup group);
}