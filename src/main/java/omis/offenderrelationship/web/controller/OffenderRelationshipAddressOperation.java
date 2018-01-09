package omis.offenderrelationship.web.controller;

/**
 * Operation for offender relationship address.
 *
 * @author Yidong Li
 * @version 0.0.1 (Feb 29, 2016)
 * @since OMIS 3.0
 */
public enum OffenderRelationshipAddressOperation {

	/** Use current address. */
	CURRENT,
	
	/** Use existing address. */
	EXISTING,
	
	/** Create new address. */
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