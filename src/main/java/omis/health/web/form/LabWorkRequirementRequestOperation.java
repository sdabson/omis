package omis.health.web.form;

/**
 * Operation to be performed on lab work requirement request.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 4, 2014)
 * @since OMIS 3.0
 */
public enum LabWorkRequirementRequestOperation {

	/** Create. */
	CREATE,
	
	/** Update. */
	UPDATE,
	
	/** Remove. */
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