package omis.family.web.controller;

/**
 * Operation for location address.
 *
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.0.1 (Feb 26, 2015)
 * @since OMIS 3.0
 */
public enum FamilyAddressOperation {
	
	/** Use existing address. */
	EXISTING,
	
	/** Create new address. */
	NEW,
	
	/** Use current address. */
	CURRENT;
	
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