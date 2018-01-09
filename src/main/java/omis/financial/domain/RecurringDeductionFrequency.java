package omis.financial.domain;

/**
 * Recurring deduction frequency. 
 *
 * @author Yidong Li
 * @version 0.0.1 (may 9, 2017)
 * @since OMIS 3.0
 */
 
public enum RecurringDeductionFrequency {
	WEEKLY,
	MONTHLY,
	YEARLY;
	
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