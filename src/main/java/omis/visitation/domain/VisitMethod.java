package omis.visitation.domain;

/**
 * Visit method.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 23, 2016)
 * @since OMIS 3.0
 */
public enum VisitMethod {

	/** Physically present. */
	PHYSICALLY_PRESENT,
	
	/** Video. */
	VIDEO;
	
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