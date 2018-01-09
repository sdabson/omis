package omis.offendercontact.web.form;

/**
 * Operation for offender contact mailing address.
 *
 * @author Josh Divine
 * @version 0.0.1
 * @since OMIS 3.0
 */
public enum OffenderContactMailingAddressOperation {

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