package omis.demographics.domain;

/**
 * Dominant side.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 24, 2013)
 * @since OMIS 3.0
 */
public enum DominantSide {
	
	/** Left. */
	LEFT("Left"),
	
	/** Right. */
	RIGHT("Right"),
	
	/** Both. */
	BOTH("Both");
	
	private final String name;
	
	// Instantiates with the specified name
	private DominantSide(final String name) {
		this.name = name;
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}
}