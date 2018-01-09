package omis.health.datatype;

/**
 * Level of urgency of health related response.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 1, 2013)
 * @since OMIS 3.0
 */
public enum HealthResponseUrgencyLevel {
	
	/** Routine emergency level. */
	ROUTINE("Routine"),
	
	/** To be dealt with immediately. */
	IMMEDIATE("Immediate");
	
	private final String name;
	
	// Instantiates urgency level
	private HealthResponseUrgencyLevel(final String name) {
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
	
	/**
	 * Returns a representation of the urgency level containing the name.
	 * 
	 * @return representation containing name
	 */
	@Override
	public String toString() {
		return this.name;
	}
}