package omis.residence.web.form;

/**
 * Existing residence operation.
 *
 * @author Josh Divine
 * @version 0.0.1 (Sept 19, 2017)
 * @since OMIS 3.0
 *
 */
public enum ExistingResidenceOperation {
	
	/** Sets the existing residence to historical. */
	HISTORICAL,
	
	/** Sets the existing residence to secondary. */
	SECONDARY;

	
	/**
	 * Returns the instance name.
	 * 
	 * @return instance name
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
