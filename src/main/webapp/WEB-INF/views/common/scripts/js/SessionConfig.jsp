// Define config namespace
if (typeof config === 'undefined') {
	var config = new Object();
}

/**
 * Stored session properties.
 * @author Stephen Abson
 * @version 0.1.0 (June 11, 2012)
 * @since OMIS 3.0
 */
config.SessionConfig = new function() {
	
	/**
	 * Returns the username of the current user.
	 * @return current user username
	 */
	this.getUsername = function() {
	  return "${username}";
	};
	
	/**
	 * Returns the ID of the user account.
	 * @return user account ID.
	 */
	this.getUserAccountId = function() {
	  return "${userAccountId}";
	};
	
	/**
	 * Returns a label for the user account.
	 * @return label for user account
	 */
	this.getUserAccountLabel = function() {
	  return "${userAccountLabel}";
	};
};