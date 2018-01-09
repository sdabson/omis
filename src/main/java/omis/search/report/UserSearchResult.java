package omis.search.report;

/** User person name search result.
 * @author Joel Norris
 * @author Ryan Johns
 * @version 0.1.1 (Oct 09, 2013)
 * @since OMIS 3.0 */
public class UserSearchResult extends PersonSearchResult {
	private static final long serialVersionUID = 1L;
	private final Long userId;
	private final String username;

	/** Default constructor.
	 * @param userId user id.
	 * @param personId person Id.
	 * @param nameId name id.
	 * @param firstName first name.
	 * @param middleName middle name.
	 * @param lastName last name. 
	 * @param username username */
	public UserSearchResult(final Long userId, final long personId,
			final Long nameId, final String firstName, final String middleName,
			final String lastName, final String suffixName, 
			final String username) {
		super(nameId, personId, firstName, middleName, lastName, suffixName);
		this.userId = userId;
		this.username = username;
	}

	/** gets user id.
	 * @return user id. */
	public Long getUserId() { return this.userId; }
	
	/** Gets username.
	 * @return username. */
	public String getUsername( ) {return this.username; }
}
