package omis.conviction.domain;

/**
 * Offense severity.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 26, 2017)
 * @since OMIS 3.0
 */
public enum OffenseSeverity {

	/* Offense severity is a felony */
	FELONY,
	/* Offense severity is a misdemeanor */
	MISDEMEANOR;
	
	/**
	 * Returns <code>this.name()</code>.
	 * 
	 * @return <code>this.name()</code>
	 */
	public String getName() {
		return this.name();
	}
	
}
