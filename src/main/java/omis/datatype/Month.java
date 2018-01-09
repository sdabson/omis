package omis.datatype;

import java.util.Calendar;

/**
 * Month.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public enum Month {

	/** January. */
	JANUARY(Calendar.JANUARY),
	
	/** February. */
	FEBRUARY(Calendar.FEBRUARY),
	
	/** March. */
	MARCH(Calendar.MARCH),
	
	/** April. */
	APRIL(Calendar.APRIL),
	
	/** May. */
	MAY(Calendar.MAY),
	
	/** June. */
	JUNE(Calendar.JUNE),
	
	/** July. */
	JULY(Calendar.JULY),
	
	/** August. */
	AUGUST(Calendar.AUGUST),
	
	/** September. */
	SEPTEMBER(Calendar.SEPTEMBER),
	
	/** October. */
	OCTOBER(Calendar.OCTOBER),
	
	/** November. */
	NOVEMBER(Calendar.NOVEMBER),
	
	/** December. */
	DECEMBER(Calendar.DECEMBER);
	
	private final int calendarMonth;
	
	// Instantiates month
	private Month(final int calendarMonth) {
		this.calendarMonth = calendarMonth;
	}
	
	/**
	 * Returns calendar month.
	 * 
	 * @return calendar month
	 */
	public int getCalendarMonth() {
		return this.calendarMonth;
	}
	
	/**
	 * Returns {@code this.name()}.
	 * 
	 * @return {@code this.name()}
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns month with calendar month.
	 * 
	 * <p>Returns {@code null} if no such month exists.
	 * 
	 * @param calendarMonth calendar month
	 */
	public static Month findByCalendarMonth(final int calendarMonth) {
		for (Month month : Month.values()) {
			if (month.getCalendarMonth() == calendarMonth) {
				return month;
			}
		}
		return null;
	}
}