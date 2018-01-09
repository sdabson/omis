package omis.grievance.domain;

/**
 * Category of grievance disposition reason.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 7, 2015)
 * @since OMIS 3.0
 */
public enum GrievanceDispositionReasonCategory {

	/** Denied. */
	DENIED,
	
	/** Granted. */
	GRANTED,
	
	/** Partially granted. */
	PARTIALLY_GRANTED;
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
}