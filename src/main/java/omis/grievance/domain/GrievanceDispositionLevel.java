package omis.grievance.domain;

/**
 * Level of grievance disposition.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 7, 2015)
 * @since OMIS 3.0
 */
public enum GrievanceDispositionLevel {

	/** Coordinator. */
	COORDINATOR(1),
	
	/** Warden. */
	WARDEN(2),
	
	/** Facility Health Administrator. */
	FHA(2),
	
	/** Director. */
	DIRECTOR(3);
	
	// Used to determine order of levels
	private final int order;
	
	// Instantiates level with an order
	private GrievanceDispositionLevel(final int order) {
		this.order = order;
	}
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns whether {@code this} comes before {@code level}.
	 * 
	 * @param level level
	 * @return whether {@code this} comes before {@code level}
	 */
	public boolean isBefore(final GrievanceDispositionLevel level) {
		return this.order < level.order;
	}
	
	/**
	 * Returns whether {@code this} comes after {@code level}.
	 * 
	 * @param level level
	 * @return whether {@code this} comes after {@code level}
	 */
	public boolean isAfter(final GrievanceDispositionLevel level) {
		return this.order > level.order;
	}
}