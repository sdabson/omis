package omis.web.function;

import java.util.Date;

import omis.datatype.DateRange;

/**
 * Function determines whether a date range is active for a given date.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Dec 16, 2013)
 * @since OMIS 3.0
 */
public final class DateRangeActiveFunction {

	// Prevent instantiation
	private DateRangeActiveFunction() {
		throw new AssertionError("Accidental instantiation");
	}
	
	/**
	 * Returns whether the date range is active on the given date.
	 * 
	 * <p>If the date range is {@code null}, it is considered active.
	 * 
	 * @param dateRange date range
	 * @param date date
	 * @return whether date range is active on date
	 */
	public static boolean isDateRangeActive(
			final DateRange dateRange, final Date date) {
		if (dateRange != null) {
			return dateRange.isActive(date);
		} else {
			return true;	
		}
	}
}