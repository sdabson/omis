package omis.offender.web.form;

/**
 * Value of flag item for form to create offender.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 11, 2014)
 * @since OMIS 3.0
 */
public enum CreateOffenderFlagItemValue {

	/** Yes. */
	YES,
	
	/** No. */
	NO,
	
	/** Unknown. */
	UNKNOWN;
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name();
	}
}
