package omis.victim.web.form;

/**
 * Operation to be performed on victim contact address.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Aug 28, 2015)
 * @since OMIS 3.0
 */
public enum VictimContactAddressOperation {

	/** Use current address (do not change). */
	USE_CURRENT,
	
	/** Use existing address (change to existing). */
	USE_EXISTING,
	
	/** Create new address (change to new). */
	CREATE_NEW;
	
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