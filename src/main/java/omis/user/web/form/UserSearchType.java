package omis.user.web.form;

/**
 * Type of user search.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 6, 2015)
 * @since OMIS 3.0
 */
public enum UserSearchType {

	/** Name search type. */
	NAME,
	
	/** Username search type. */
	USERNAME;
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * <p>See {@link Enum#name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}