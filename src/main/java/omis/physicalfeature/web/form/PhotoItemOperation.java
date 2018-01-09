package omis.physicalfeature.web.form;

/**
 * Photo item operation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 24, 2016)
 * @since OMIS 3.0
 */
public enum PhotoItemOperation {

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