package omis.victim.web.form;

/**
 * Operation for victim contact online account form item.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 10, 2015)
 * @since OMIS 3.0
 */
public enum VictimContactOnlineAccountItemOperation {

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