package omis.schedule.web.form;

/**
 * Parts of time.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 29, 2013)
 * @since OMIS 3.0
 */
public enum TimePart {

	/** Hour. */
	HOUR(60 * 60 * 1000), 
	
	/** Minute. */
	MINUTE(60 * 1000),
	
	/** Second. */
	SECOND(1000),
	
	/** Millisecond. */
	MILLISECOND(1);
	
	private final int unitSize;
	
	// Instantiates a time part
	private TimePart(final int unitSize) {
		this.unitSize = unitSize;
	}
	
	/**
	 * Given the components of a time, calculates the time in the smallest
	 * units.
	 * 
	 * @param hours hours
	 * @param minutes minutes
	 * @param seconds seconds
	 * @param milliseconds milliseconds
	 * @return calculated time in smallest units
	 */
	public static int calculate(final int hours, final int minutes,
			final int seconds, final int milliseconds) {
		int result;
		result = hours * HOUR.unitSize;
		result = result + (minutes * MINUTE.unitSize);
		result = result + (seconds * SECOND.unitSize);
		result = result + (milliseconds * MILLISECOND.unitSize);
		return result;
	}
	
	/**
	 * Given a value of smallest units, calculates the time value of the time
	 * part.
	 * 
	 * @param value
	 * @return
	 */
	public int calculate(final int value) {
		return value / this.unitSize;
	}
	
	/**
	 * Returns whether the time part is the smallest unit
	 * 
	 * @return whether time part is smallest unit
	 */
	public boolean isSmallestUnit() {
		return this.unitSize == 1; 
	}
}