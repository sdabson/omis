package omis.health.web.form;

/**
 * 
 * @author Yidong Li
 * @version 0.1.0 (Nov 18, 2014)
 * @since OMIS 3.0
 */
public enum ResolveLabWorkItemOperation {
	
	/**
	 * Updates a lab work.
	 */
	UPDATE,
	
	/**
	 * Removes a lab work.
	 */
	REMOVE;

	/**
	 * Returns the name of {@code this}.
	 * 
	 * <p>This is done by returning {@code this.name()}. 
	 * 
	 * @return name of {@code this}
	 */
	public String getName() {
		return this.name();
	}
}
