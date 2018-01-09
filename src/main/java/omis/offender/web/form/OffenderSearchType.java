package omis.offender.web.form;

/**
 * Type of offender search.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 10, 2015)
 * @since OMIS 3.0
 */
public enum OffenderSearchType {

	/** Search by name. */
	NAME,
	
	/** Search by offender number. */
	OFFENDER_NUMBER,
	
	/** Search by social security number. */
	SOCIAL_SECURITY_NUMBER,
	
	/** Search by birth date. */
	BIRTH_DATE;
	
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