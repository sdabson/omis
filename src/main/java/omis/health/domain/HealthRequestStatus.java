package omis.health.domain;

/**
 * Status of health request.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 14, 2014)
 * @since OMIS 3.0
 */
public enum HealthRequestStatus {

	/** Open. */
	OPEN,
	
	/** Cancelled. */
	CANCELLED,
	
	/** Scheduled. */
	SCHEDULED;
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name();
	}
}