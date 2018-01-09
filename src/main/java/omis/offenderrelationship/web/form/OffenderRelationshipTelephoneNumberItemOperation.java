package omis.offenderrelationship.web.form;

/**
 * Operation to perform on offender relationship telephone number item.
 *
 * @author Yidong Li
 * @version 0.0.1 (March 4, 2016)
 * @since OMIS 3.0
 */
public enum OffenderRelationshipTelephoneNumberItemOperation {
	/**
	 * Create.
	 */
	CREATE,
	
	/**
	 * Update.
	 */
	UPDATE,
	
	/**
	 * Remove.
	 */
	REMOVE,
	
	/**
	 * Edit.
	 */
	EDIT;
	
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