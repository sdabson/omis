package omis.workassignment.web.form;

/**
 * Operation to perform on work assignment note item.
 *
 * @author Yidong Li
 * @version 0.0.1 (Sept 7, 2016)
 * @since OMIS 3.0
 */
public enum WorkAssignmentNoteItemOperation {

	CREATE,
	
	UPDATE,
	
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