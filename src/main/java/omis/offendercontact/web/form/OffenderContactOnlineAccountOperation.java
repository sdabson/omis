package omis.offendercontact.web.form;

/**
 * Offender contact online account operation.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public enum OffenderContactOnlineAccountOperation {

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