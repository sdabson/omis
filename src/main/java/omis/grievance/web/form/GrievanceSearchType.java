package omis.grievance.web.form;

/**
 * Grievance search type.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jan 6, 2016)
 * @since OMIS 3.0
 */
public enum GrievanceSearchType {

	/** Offender. */
	OFFENDER,
	
	/** Location. */
	LOCATION;
	
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