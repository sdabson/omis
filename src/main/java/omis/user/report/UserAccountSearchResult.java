package omis.user.report;

import java.io.Serializable;

/**
 * Result of user account search.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 28, 2014)
 * @since OMIS 3.0
 */
public final class UserAccountSearchResult
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String username;
	
	private final Long userId;
	
	private final String lastName;
	
	private final String firstName;
	
	/**
	 * Instantiates a result of user account search.
	 * 
	 * @param id ID of user account
	 * @param username user name
	 * @param userId ID of user
	 * @param lastName last name
	 * @param firstName first name
	 */
	public UserAccountSearchResult(final Long id, final String username,
			final Long userId, final String lastName, final String firstName) {
		this.id = id;
		this.username = username;
		this.userId = userId;
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	/**
	 * Returns the ID of the user account.
	 * 
	 * @return ID of user account
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Returns the username.
	 * 
	 * @return username username
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * Returns the ID of the user.
	 * 
	 * @return ID of user
	 */
	public Long getUserId() {
		return this.userId;
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
	 * Returns the first name.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return this.firstName;
	}
}