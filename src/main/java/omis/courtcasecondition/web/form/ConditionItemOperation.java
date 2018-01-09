package omis.courtcasecondition.web.form;

/**
 * Special Condition item operation.
 * 
 * @author Jonny Santy
 * @author Annie Jacques
 * @version 0.1.1 (Jun 2, 2017)
 * @since OMIS 3.0
 */
public enum ConditionItemOperation {

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