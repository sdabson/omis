package omis.employment.web.controller;

/**
 * Operation for employment address.
 *
 * @author Yidong Li
 * @version 0.0.1 (Oct 5, 2016)
 * @since OMIS 3.0
 */
public enum EmploymentAddressOperation {

	/** Use current address */
	CURRENT,
	
	/** Use existing address */
	EXISTING,
	
	/** Create new address */
	NEW;
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * <p>See {@link Enum#name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}