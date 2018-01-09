package omis.datatype;

import java.util.GregorianCalendar;

/**
 * Day of the week.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (April 8, 2014)
 * @since OMIS 3.0
 */
public enum DayOfWeek {

	/** Sunday. */
	SUNDAY("Sun", "Sunday", GregorianCalendar.SUNDAY),
	
	/** Monday. */
	MONDAY("Mon", "Monday", GregorianCalendar.MONDAY),
	
	/** Tuesday. */
	TUESDAY("Tue", "Tuesday", GregorianCalendar.TUESDAY),
	
	/** Wednesday. */
	WEDNESDAY("Wed", "Wednesday", GregorianCalendar.WEDNESDAY),
	
	/** Thursday. */
	THURSDAY("Thu", "Thursday", GregorianCalendar.THURSDAY),
	
	/** Friday. */
	FRIDAY("Fri", "Friday", GregorianCalendar.FRIDAY),
	
	/** Saturday. */
	SATURDAY("Sat", "Saturday", GregorianCalendar.SATURDAY);
	
	private final String shortName;
	
	private final String name;
	
	private final int gregorianCalendarDayOfWeek;
	
	// Sets the properties of the day of week
	private DayOfWeek(final String shortName, final String name,
			final int gregorianCalendarDayOfWeek) {
		this.shortName = shortName;
		this.name = name;
		this.gregorianCalendarDayOfWeek = gregorianCalendarDayOfWeek;
	}
	
	/**
	 * Returns the short name of the day of the week.
	 * 
	 * <p>The short name of the day of the week is exactly 3 letters long.
	 * 
	 * @return short name of day of week
	 */
	public String getShortName() {
		return this.shortName;
	}
	
	/**
	 * Returns the name of the day of the week.
	 * 
	 * @return name of day of week
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns the integral field of {@link java.util.GregorianCalendar} that
	 * represents the day of week.
	 * 
	 * @return field of {@link java.util.GregorianCalendar} that represents
	 * day of week
	 */
	public int getGregorianCalendarDayOfWeek() {
		return this.gregorianCalendarDayOfWeek;
	}
	
	/**
	 * Returns the day of the week with the specified short name.
	 * 
	 * @param shortName short name of day of week to find
	 * @return day of week with specified short name; {@code null} if a day
	 * of week with specified short name was not found or if specified name
	 * is {@code null} or is equal to {@code ""}
	 */
	public static DayOfWeek findByShortName(final String shortName) {
		if (shortName == null || shortName.length() == 0) {
			return null;
		}
		for (DayOfWeek dayOfWeek : values()) {
			if (dayOfWeek.getShortName().equals(shortName)) {
				return dayOfWeek;
			}
		}
		return null;
	}
	
	/**
	 * Returns the day of the week with the specified name.
	 * 
	 * @param name name of day of week to find
	 * @return day of week with specified name; {@code null} if a day of week
	 * with specified name was not found or if specified name is {@null} or
	 * is equal to {@code ""}
	 */
	public static DayOfWeek findByName(final String name) {
		if (name == null || name.length() == 0) {
			return null;
		}
		for (DayOfWeek dayOfWeek : values()) {
			if (dayOfWeek.getName().equals(name)) {
				return dayOfWeek;
			}
		}
		return null;
	}
	
	/**
	 * Returns the day of the week equivalent to the the specified day of week
	 * field of {@link java.util.GregorianCalendar}.
	 * 
	 * @param gregorianCalendarDayOfWeek Gregorian calendar day of week field
	 * @return day of week with specified Gregorian calendar day of week field;
	 * {@code null} if day of week with specified Gregorian calendar day of week
	 * field does not exist
	 */
	public static DayOfWeek findByGregorianCalendarDayOfWeek(
			final int gregorianCalendarDayOfWeek) {
		for (DayOfWeek dayOfWeek : values()) {
			if (dayOfWeek.getGregorianCalendarDayOfWeek()
					== gregorianCalendarDayOfWeek) {
				return dayOfWeek;
			}
		}
		return null;
	}
}