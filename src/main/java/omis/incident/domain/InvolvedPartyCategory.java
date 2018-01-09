package omis.incident.domain;

/**
 * Involved party category.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Oct 1, 2015)
 * @since OMIS 3.0
 */
public enum InvolvedPartyCategory {

	/** Staff. */
	STAFF,
	
	/** Offender. */
	OFFENDER,
	
	/** Other. */
	OTHER;
	
	/**
	 * Returns the name of the {@code this}.
	 * 
	 * @return name of {@code this}
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	@Override
	public String toString() {
		return this.name();
	}
}