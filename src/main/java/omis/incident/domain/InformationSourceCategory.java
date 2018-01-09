package omis.incident.domain;

/**
 * Information source category.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 10, 2015)
 * @since OMIS 3.0
 */
public enum InformationSourceCategory {
	
	/** Staff. */
	STAFF,
	
	/** Offender. */
	OFFENDER,
	
	/** Other. */
	OTHER,
	
	/** Anonymous */
	ANONYMOUS;
	
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