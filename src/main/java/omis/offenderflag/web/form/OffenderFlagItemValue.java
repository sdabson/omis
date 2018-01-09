package omis.offenderflag.web.form;

/**
 * Value of an offender flag item.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 10, 2014)
 * @since OMIS 3.0
 */
public enum OffenderFlagItemValue {

	/** Yes. */
	YES,
	
	/** No. */
	NO,
	
	/** Unknown. */
	UNKNOWN,
	
	/** Not set. */
	NOT_SET;
	
	/**
	 * Returns the {@code this.name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}