package omis.need.domain;

/**
 * Objective source.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 15, 2015)
 * @since OMIS 3.0
 */
public enum ObjectiveSource {

	/**
	 * Court order.
	 */
	COURT_ORDER,
	
	/**
	 * Staff.
	 */
	STAFF,
	
	/**
	 * Assessment.
	 */
	ASSESSMENT;
	
	/**
	 * Returns the name of {@code this}.
	 * 
	 * <p>This is done by returning {@code this.name()}.
	 * 
	 * @return name of {@code this}
	 */
	public String getName() {
		return this.name();
	}
}