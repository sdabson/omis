package omis.content;

/**
 * Request content type.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jan 11, 2013)
 * @since OMIS 3.0
 */
public enum RequestContentType {

	/** Listing screen. */
	LISTING_SCREEN("Listing", "List of records"),
	
	/** Detail screen. */
	DETAIL_SCREEN("Detail", "Record details"),
	
	/** Form submit. */
	FORM_SUBMIT("Form submit", "Processes a form submission"),
	
	/** AJAX request. */
	AJAX_REQUEST("AJAX Request", "AJAX Request"),
	
	/** Profile screen. */
	PROFILE_SCREEN("Profile", "Profile screen"),
	
	/** Other. */
	OTHER("Other", "Other");
	
	private final String name;
	
	private final String description;
	
	// Instantiates a screen type with the specified name and description
	private RequestContentType(final String name, final String description) {
		this.name = name;
		this.description = description;
	}
	
	/**
	 * Returns the request content type name.
	 * 
	 * @return request content type name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the request content type description.
	 * 
	 * @return request content type description
	 */
	public String getDescription() {
		return this.description;
	}
}