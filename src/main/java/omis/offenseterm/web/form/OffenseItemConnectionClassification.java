package omis.offenseterm.web.form;

/**
 * Classification of how offense items are connected.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public enum OffenseItemConnectionClassification {

	/** Classifies initial offense item. */
	INITIAL,
	
	/**
	 * Classifies offense item that is concurrently connected to another
	 * offense item.
	 */
	CONCURRENT,
	
	/**
	 * Classifies offense item that is consecutively connected to another
	 * offense item on the same docket.
	 */
	CONSECUTIVE,
	
	/**
	 * Classifies offense item that is consecutively connected to another
	 * offense item on a different docket.
	 */
	CONSECUTIVE_OTHER_DOCKET;
	
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