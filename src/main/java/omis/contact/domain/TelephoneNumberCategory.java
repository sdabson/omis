package omis.contact.domain;

/**
 * Telephone number category. 
 *
 * @author Yidong Li
 * @version 0.0.1 (April 1, 2015)
 * @since OMIS 3.0
 */
public enum TelephoneNumberCategory {
	/** Telephone number is cell phone number. */
	CELL,
	/** Telephone number is home phone number. */
	HOME,
	/** Telephone number is work phone number. */
	WORK,
	/** Telephone number is fax number. */
	FAX,
	/**  Number of a friend or family member that do not have a phone. */
	MESSAGE_PHONE;
	
	/**
	 * Returns {@code this.name()}
	 * 
	 * <p>See {@link Enum#name()}
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}