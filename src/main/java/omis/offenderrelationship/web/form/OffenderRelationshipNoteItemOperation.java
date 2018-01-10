package omis.offenderrelationship.web.form;

/**
 * Operation for offender relationship notes.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Jan 9, 2018)
 * @since OMIS 3.0
 */
public enum OffenderRelationshipNoteItemOperation {

	/** Create. */
	CREATE,
	
	/** Update. */
	UPDATE,
	
	/** Remove. */
	REMOVE;
	
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