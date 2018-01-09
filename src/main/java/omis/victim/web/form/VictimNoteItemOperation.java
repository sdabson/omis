package omis.victim.web.form;

/**
 * Operation to perform on victim note item.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 17, 2015)
 * @since OMIS 3.0
 */
public enum VictimNoteItemOperation {

	CREATE,
	
	UPDATE,
	
	REMOVE,
	
	REMOVE_ASSOCIATION;
	
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