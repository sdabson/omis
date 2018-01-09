package omis.offenderrelationship.web.form;

/**
 * Type of offender relationship search.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jun 24, 2015)
 * @since OMIS 3.0
 */
public enum OffenderRelationshipSearchType {

	/** Name. */
	NAME,
	
	/** Offender Number. */
	OFFENDER_NUMBER,
	
	/** Social Security Number. */
	SOCIAL_SECURITY_NUMBER,
	
	/** Birth date. */
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