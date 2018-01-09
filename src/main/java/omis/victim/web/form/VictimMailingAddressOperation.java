package omis.victim.web.form;

/**
 * Operation for victim mailing address.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public enum VictimMailingAddressOperation {

	/** Use existing. */
	USE_EXISTING,
	
	/** Create new. */
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