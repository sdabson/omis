package omis.paroleboarditinerary.web.form;

/**
 * Board meeting site item operation.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Nov 29, 2017)
 * @since OMIS 3.0
 */
public enum BoardMeetingSiteItemOperation {

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