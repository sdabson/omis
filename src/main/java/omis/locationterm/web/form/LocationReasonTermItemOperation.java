package omis.locationterm.web.form;

/**
 * Operation for location reason term item.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public enum LocationReasonTermItemOperation {

	/** Create. */
	CREATE,
	
	/** Update. */
	UPDATE,
	
	/** Remove. */
	REMOVE;
	
	/**
	 * Returns {@link Enum#name()}.
	 * 
	 * @return {@link Enum#name()}
	 */
	public String getName() {
		return this.name();
	}
}