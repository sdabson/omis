package omis.employment.domain;

/**
 * Wage Payment Frequency. 
 *
 * @author Yidong Li
 * @version 0.0.1 (Jan 30, 2015)
 * @since OMIS 3.0
 */
public enum WorkShiftFrequency {
	/** Work shift is day time. */
	DAYS,
	/** Work shift start about midnight and last until early morning. */
	GRAVEYARD,
	/** Work shift is night time. */
	NIGHTS,
	/** Work shift is swing. */
	SWING,
	/** Work shift could be any... */
	VARIOUS;
	
	/**
	 * Returns <code>this.name()</code>.
	 * 
	 * @return <code>this.name()</code>
	 */
	public String getName() {
		return this.name();
	}
}