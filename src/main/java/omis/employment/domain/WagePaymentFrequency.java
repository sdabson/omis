package omis.employment.domain;

/**
 * Wage Payment Frequency. 
 *
 * @author Yidong Li
 * @version 0.0.1 (Jan 30, 2015)
 * @since OMIS 3.0
 */
public enum WagePaymentFrequency {
	/** Wage is paid by hour. */
	HOUR,
	/** Wage is paid by week. */
	WEEK,
	/** Wage is paid by month. */
	MONTH,
	/** Wage is paid by job. */
	JOB;
	
	/**
	 * Returns <code>this.name()</code>.
	 * 
	 * @return <code>this.name()</code>
	 */
	public String getName() {
		return this.name();
	}
}