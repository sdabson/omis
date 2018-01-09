package omis.education.web.form;

/**
 * ItemOperation.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 27, 2016)
 *@since OMIS 3.0
 *
 */
public enum ItemOperation {

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
