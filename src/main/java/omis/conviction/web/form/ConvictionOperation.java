package omis.conviction.web.form;

/**
 * Operation to be performed during the conviction of a court case.
 * 
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.1.2 (May 9, 2017)
 * @since OMIS 3.0
 */
public enum ConvictionOperation {

	/** Create a new conviction. */
	CREATE,
	
	/** Update an existing conviction. */
	EDIT,
	
	/** Remove conviction. */
	REMOVE;
	
	/**
	 * Returns the constant name.
	 * 
	 * @return constant name
	 */
	public String getConstantName() {
		return this.name();
	}
}