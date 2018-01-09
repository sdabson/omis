package omis.offendercontact.web.form;

/**
 * Offender contact telephone number operation.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public enum OffenderContactTelephoneNumberOperation {

	/** Create. */
	CREATE,
	
	/** Update. */
	UPDATE,
	
	/** Remove. */
	REMOVE;
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}