package omis.victim.web.form;

/**
 * Operation performed on victim contact telephone number.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 28, 2015)
 * @since OMIS 3.0
 */
public enum VictimContactTelephoneNumberItemOperation {

	/** Create. */
	CREATE,
	
	/** Update. */
	UPDATE,
	
	/** Remove. */
	REMOVE;
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * <p>See {@link Enum#name()}
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}