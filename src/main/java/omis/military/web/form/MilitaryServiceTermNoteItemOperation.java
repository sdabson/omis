package omis.military.web.form;

/**
 * Military service term note item operation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0
 */
public enum MilitaryServiceTermNoteItemOperation {

	/** Create. */
	CREATE,
	
	/** Update. */
	UPDATE,
	
	/** Remove. */
	REMOVE;
	
	/**
	 * Returns the name of the {@code this}.
	 * 
	 * @return name of {@code this}
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	@Override
	public String toString() {
		return this.name();
	}
}