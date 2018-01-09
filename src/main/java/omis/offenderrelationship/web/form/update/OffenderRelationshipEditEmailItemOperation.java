package omis.offenderrelationship.web.form.update;

/**
 * Operation to perform on offender relationship email item.
 *
 * @author Yidong Li
 * @version 0.0.1 (Feb 9, 2016)
 * @since OMIS 3.0
 */
public enum OffenderRelationshipEditEmailItemOperation {
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