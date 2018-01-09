package omis.supervisionfee.web.form;

/**
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Oct 28, 2014)
 * @since OMIS 3.0
 */
public enum SupervisionFeeRequirementItemOperation {
	
	/**
	 * Creates a supervision fee requirement.
	 */
	CREATE,
	
	/**
	 * Updates a supervision fee requirement.
	 */
	UPDATE,
	
	/**
	 * Removes a supervision fee requirement.
	 */
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
