package omis.specialneed.web.form;

/**
 * Special need note item operation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 31, 2016)
 * @since OMIS 3.0
 */
public enum SpecialNeedNoteItemOperation {

	/**
	 * Creates a special need note.
	 */
	CREATE,
	
	/**
	 * Updates a special need note.
	 */
	UPDATE,
	
	/**
	 * Removes a special need note.
	 */
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