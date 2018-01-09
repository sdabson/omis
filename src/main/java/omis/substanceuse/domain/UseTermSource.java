package omis.substanceuse.domain;

/**
 * Use term source.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 16, 2016)
 * @since OMIS 3.0
 */
public enum UseTermSource {
	
	/** Self report */
	SELF_REPORT,
	
	/** Prescription */
	PRESCRIPTION,
	
	/** Pre Sentence Investigation */
	PSI;
	
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
