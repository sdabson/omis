package omis.user.web.form;

import java.io.Serializable;

/**
 * Form to search for users.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 6, 2015)
 * @since OMIS 3.0
 */
public class UserSearchForm
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String lastName;
	
	private String firstName;
	
	private UserSearchType type;
	
	/** Instantiates form to search for users. */
	public UserSearchForm() {
		// Default instantiation
	}

	/**
	 * Sets username.
	 * 
	 * @param username username
	 */
	public void setUsername(final String username) {
		this.username = username;
	}
	
	/**
	 * Returns username.
	 * 
	 * @return username
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * Sets last name.
	 * 
	 * @param lastName last name
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Returns last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * Sets first name.
	 * 
	 * @param firstName first name
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
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
	 * Sets search type.
	 * 
	 * @param type search type
	 */
	public void setType(final UserSearchType type) {
		this.type = type;
	}
	
	/**
	 * Returns search type.
	 * 
	 * @return search type
	 */
	public UserSearchType getType() {
		return this.type;
	}
}