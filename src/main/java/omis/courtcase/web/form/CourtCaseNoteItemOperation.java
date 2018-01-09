package omis.courtcase.web.form;

/**
 * Charge operation.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 24, 2017)
 * @since OMIS 3.0
 */
public enum CourtCaseNoteItemOperation {

	/** Create. */
	CREATE,
	/** Edit. */
	EDIT,
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