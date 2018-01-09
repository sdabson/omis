package omis.util;

import java.util.Date;
import java.util.GregorianCalendar;

import omis.datatype.DayOfWeek;
import omis.datatype.WeekOfMonth;


/**
 * Wrapper class for manipulating a date.
 * 
 * @author Stephen Abson
 * @version 0.1.3 (April 12, 2012)
 * @since OMIS 3.0
 */
public class DateManipulator {

	// Internal representation/implementation
	private GregorianCalendar cal = new GregorianCalendar();
	
	/**
	 * Instantiates a date manipulator with a copy of the specified date.
	 * 
	 * @param date date with which to instantiate manipulator
	 */
	public DateManipulator(final Date date) {
		this.cal.setTime(date);
	}
	
	/**
	 * Instantiates a date manipulator with year, month and day.
	 * 
	 * @param year year
	 * @param month month
	 * @param day day
	 */
	public DateManipulator(final int year, final int month, final int day) {
		this.cal.set(year, month, day);
	}
	
	/**
	 * Returns the date at the specified time of the specified day.
	 * 
	 * <p>Midnight will be used for a {@code null} time. 
	 * 
	 * @param day day
	 * @param time time
	 * @return date at specified time of specified day
	 */
	public static Date getDateAtTimeOfDay(final Date day, final Date time) {
		DateManipulator manipulator = new DateManipulator(day);
		if (time != null) {
			manipulator.setToTimeOfDate(time);
		} else {
			manipulator.setToTimeOfDate(new Date(0L));
		}
		return manipulator.getDate();
	}
	
	/**
	 * Returns the a copy of the date.
	 * 
	 * @return copy of date
	 */
	public Date getDate() {
		return this.cal.getTime();
	}
	
	/**
	 * Sets the date to a copy of the date provided.
	 * 
	 * @param date date to copy
	 */
	public void setDate(final Date date) {
		this.cal.setTime(date);
	}
	
	/**
	 * Changes the date by increasing the date by the specified number of days.
	 * 
	 * <p>Time information is preserved.
	 * 
	 * @param amount number of days by which to change the date; can be positive
	 * or negative
	 */
	public void changeDate(final int amount) {
		this.cal.add(GregorianCalendar.DATE, amount);
	}
	
	/**
	 * Changes the year increasing the year by the specified number of years.
	 * 
	 * <p>Time information is preserved.
	 * 
	 * @param amount amount by which to change year; can be positive or negative
	 */
	public void changeYear(final int amount) {
		this.cal.add(GregorianCalendar.YEAR, amount);
	}
	
	/**
	 * Changes the date by increasing the month by the specified number of
	 * months.
	 * 
	 * <p>Time information is preserved.
	 * @param amount number of months by which to change the date; can be
	 * positive or negative
	 */
	public void changeMonth(final int amount) {
		this.cal.add(GregorianCalendar.MONTH, amount);
	}
	
	/**
	 * Changes the hour of day by the specified amount.
	 * 
	 * @param amount hours by which to change the hour of day; can be positive
	 * or negative
	 */
	public void changeHourOfDay(final int amount) {
		this.cal.add(GregorianCalendar.HOUR_OF_DAY, amount);
	}
	
	/**
	 * Changes the minute by the specified amount.
	 * 
	 * @param amount minutes by which to change the minute; can be positive or
	 * negative
	 */
	public void changeMinute(final int amount) {
		this.cal.add(GregorianCalendar.MINUTE, amount);
	}
	
	/**
	 * Changes the second by the specified amount.
	 * 
	 * @param amount seconds by which to change the second; can be positive or
	 * negative
	 */
	public void changeSecond(final int amount) {
		this.cal.add(GregorianCalendar.SECOND, amount);
	}
	
	/**
	 * Changes the millisecond by the specified amount.
	 * 
	 * @param amount milliseconds by which to change the millisecond; can
	 * be positive or negative
	 */
	public void changeMillisecond(final int amount) {
		this.cal.add(GregorianCalendar.MILLISECOND, amount);
	}
	
	/**
	 * Sets the hour of day.
	 * 
	 * @param hourOfDay hour of day
	 */
	public void setHourOfDay(final int hourOfDay) {
		this.cal.set(GregorianCalendar.HOUR_OF_DAY, hourOfDay);
	}
	
	/**
	 * Determines whether the date occurs later than the specified date.
	 * 
	 * @param date date from which to make the determination
	 * @return whether date occurs later than specified date
	 */
	public boolean isGreatedThan(final Date date) {
		return this.cal.getTime().getTime() > date.getTime();
	}
	
	/**
	 * Determines whether the date occurs later than or during the specified
	 * date.
	 * 
	 * @param date date from which to make the determination
	 * @return whether date occurs later or during specified date
	 */
	public boolean isGreaterThanOrEqualTo(final Date date) {
		return this.cal.getTime().getTime() >= date.getTime();
	}
	
	/**
	 * Determines whether the date occurs during the specified date.
	 * 
	 * @param date date from which to make the determination
	 * @return whether date occurs during specified date
	 */
	public boolean isEqualTo(final Date date) {
		return this.cal.getTime().getTime() == date.getTime();
	}
	
	/**
	 * Determines whether the date occurs before or during the specified date.
	 * 
	 * @param date date from which to make the determination
	 * @return whether date occurs before or during specified date
	 */
	public boolean isLessThanOrEqualTo(final Date date) {
		return this.cal.getTime().getTime() <= date.getTime();
	}
	
	/**
	 * Determines whether the date occurs before the specified date.
	 * 
	 * @param date date from which to make the determination
	 * @return whether date occurs before specified date
	 */
	public boolean isLessThan(final Date date) {
		return this.cal.getTime().getTime() < date.getTime();
	}

	/**
	 * Sets the time of the date.
	 * 
	 * @param hourOfDay hour of day
	 * @param minute minute
	 * @param second second
	 * @param millisecond millisecond
	 */
	public void setTime(final int hourOfDay, final int minute, final int second,
			final int millisecond) {
		this.cal.set(GregorianCalendar.HOUR_OF_DAY, hourOfDay);
		this.cal.set(GregorianCalendar.MINUTE, minute);
		this.cal.set(GregorianCalendar.SECOND, second);
		this.cal.set(GregorianCalendar.MILLISECOND, millisecond);
	}
	
	/** Sets the time to the earliest possible for the current date. */
	public void setToEarliestTimeInDay() {
		this.setTime(
				this.cal.getActualMinimum(GregorianCalendar.HOUR_OF_DAY),
				this.cal.getActualMinimum(GregorianCalendar.MINUTE),
				this.cal.getActualMinimum(GregorianCalendar.SECOND),
				this.cal.getActualMinimum(GregorianCalendar.MILLISECOND)
		);
	}
	
	/** Sets the date to the earliest possible for the current week. */
	public void setToEarliestTimeInWeek() {
		this.setToEarliestTimeInDay();
		this.cal.add(GregorianCalendar.DAY_OF_WEEK, -this.cal.get(
				GregorianCalendar.DAY_OF_WEEK) + GregorianCalendar.SUNDAY);
	}
	
	/**
	 * Sets the date to the earliest possible in the week of the current month.
	 */
	public void setToEarliestTimeInWeekOfMonth() {
		this.setToEarliestTimeInDay();
		this.cal.add(GregorianCalendar.DAY_OF_MONTH,
				-this.cal.get(GregorianCalendar.DAY_OF_MONTH));
		this.cal.add(GregorianCalendar.DAY_OF_WEEK,
				(-this.cal.get(GregorianCalendar.DAY_OF_WEEK))
				+ this.cal.getActualMinimum(GregorianCalendar.DAY_OF_WEEK));
	}
	
	/** Sets the time to the latest possible for the current date. */
	public void setToLatestTimeInDay() {
		this.setTime(
				this.cal.getActualMaximum(GregorianCalendar.HOUR_OF_DAY),
				this.cal.getActualMaximum(GregorianCalendar.MINUTE),
				this.cal.getActualMaximum(GregorianCalendar.SECOND),
				this.cal.getActualMaximum(GregorianCalendar.MILLISECOND)
		);
	}
	
	/** Sets the date to the latest possible for the current week. */
	public void setToLatestTimeInWeek() {
		this.setToLatestTimeInDay();
		this.cal.add(GregorianCalendar.DAY_OF_WEEK, -this.cal.get(
				GregorianCalendar.DAY_OF_WEEK) + GregorianCalendar.SATURDAY);
	}
	
	/**
	 * Sets the date to the latest possible in the week of the current month.
	 */
	public void setToLatestTimeInWeekOfMonth() {
		this.setToLatestTimeInDay();
		this.cal.add(GregorianCalendar.DAY_OF_MONTH,
				this.cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH)
				- this.cal.get(GregorianCalendar.DAY_OF_MONTH));
		this.cal.add(GregorianCalendar.DAY_OF_WEEK,
				this.cal.getActualMaximum(GregorianCalendar.DAY_OF_WEEK)
				- this.cal.get(GregorianCalendar.DAY_OF_WEEK));
	}
	
	/**
	 * Sets the time to that of the specified date. Date information of the
	 * specified date is ignored.
	 * 
	 * @param date date whose time component to use to set the time of the date
	 */
	public void setToTimeOfDate(final Date date) {
		GregorianCalendar that = new GregorianCalendar();
		that.setTime(date);
		this.cal.set(GregorianCalendar.HOUR_OF_DAY,
				that.get(GregorianCalendar.HOUR_OF_DAY));
		this.cal.set(GregorianCalendar.MINUTE,
				that.get(GregorianCalendar.MINUTE));
		this.cal.set(GregorianCalendar.SECOND,
				that.get(GregorianCalendar.SECOND));
		this.cal.set(GregorianCalendar.MILLISECOND,
				that.get(GregorianCalendar.MILLISECOND));
	}
	
	/**
	 * Returns whether the date is a weekday (Mon to Fri).
	 * 
	 * @return whether date is a weekday (Mon to Fri)
	 */
	public boolean isWeekday() {
		return !this.isWeekendDay();
	}
	
	/**
	 * Returns whether the date is a weekend day (Sat or Sun).
	 * 
	 * @return whether date is a weekend day (Sat or Sun)
	 */
	public boolean isWeekendDay() {
		switch(this.cal.get(GregorianCalendar.DAY_OF_WEEK)) {
			case GregorianCalendar.SATURDAY:
			case GregorianCalendar.SUNDAY:
				return true;
			default:
				return false;
		}
	}
	
	/**
	 * Returns the year of the date.
	 * 
	 * @return year of date
	 */
	public int getYear() {
		return this.cal.get(GregorianCalendar.YEAR);
	}
	
	/**
	 * Returns the month of the date.
	 * 
	 * @return month of date
	 */
	public int getMonth() {
		return this.cal.get(GregorianCalendar.MONTH);
	}
	
	/**
	 * Returns the day of month of the date.
	 * 
	 * @return day of month of date
	 */
	public int getDayOfMonth() {
		return this.cal.get(GregorianCalendar.DAY_OF_MONTH);
	}
	
	/**
	 * Returns the hour of day.
	 * 
	 * @return hour of day
	 */
	public int getHour() {
		return this.cal.get(GregorianCalendar.HOUR_OF_DAY);
	}
	
	/**
	 * Safely sets the day of the month of the date.
	 * 
	 * <p>Safety is guaranteed by throwing an {@code IllegalArgumentException}
	 * if the day of the month is not within the perimissible range of the
	 * current month of the date.
	 * 
	 * @param dayOfMonth day of month of date
	 * @throws IllegalArgumentException if the day of months is not within
	 * the permissible range of the current month of the date
	 */
	public void safelySetDayOfMonth(final int dayOfMonth) {
		if (!this.isDayOfMonthValid(dayOfMonth)) {
			throw new IllegalArgumentException(
					"Day of month " + dayOfMonth + " is invalid for month "
					+ this.getMonth() + " of year " + this.getYear());
		}
		this.cal.set(GregorianCalendar.DAY_OF_MONTH, dayOfMonth);
	}
	
	/**
	 * Returns whether the day of the month specified is valid for the current
	 * date.
	 * 
	 * @param dayOfMonth day of month
	 * @return whether day of month is valid for current date
	 */
	public boolean isDayOfMonthValid(final int dayOfMonth) {
		final int actualMinDayOfMonth = this.cal.getActualMinimum(
				GregorianCalendar.DAY_OF_MONTH);
		final int actualMaxDayOfMonth = this.cal.getActualMaximum(
				GregorianCalendar.DAY_OF_MONTH);
		return dayOfMonth >= actualMinDayOfMonth
				&& dayOfMonth <= actualMaxDayOfMonth;
	}
	
	/**
	 * Returns the day of week of the date.
	 * 
	 * @return day of week of date
	 */
	public DayOfWeek getDayOfWeek() {
		return DayOfWeek.findByGregorianCalendarDayOfWeek(
				this.cal.get(GregorianCalendar.DAY_OF_WEEK));
	}
	
	/**
	 * Returns the week of the year of the date.
	 * 
	 * @return week of year of date
	 */
	public int getWeekOfYear() {
		return this.cal.get(GregorianCalendar.WEEK_OF_YEAR);
	}
	
	/**
	 * Returns whether the week of the year of the date is the first week of
	 * the year.
	 * 
	 * @return whether week of year of date is first week of year
	 */
	public boolean isActualFirstWeekOfYear() {
		return this.getWeekOfYear()
				== this.cal.getActualMinimum(GregorianCalendar.WEEK_OF_YEAR);
	}
	
	/**
	 * Returns whether the week of the year of the date is the last week of
	 * the year.
	 * 
	 * @return whether week of year of date is last week of year
	 */
	public boolean isActualLastWeekOfYear() {
		return this.getWeekOfYear()
				== this.cal.getActualMaximum(GregorianCalendar.WEEK_OF_YEAR);
	}
	
	/**
	 * Compares wrapped date with {@code date} to determine whether or not
	 * they represent the same day. The test compares the year, month and day
	 * of each date.
	 * 
	 * @param date date with which to compare the wrapped date
	 * @return {@code true} if the the wrapped date is on the same day as
	 * {@code date}; {@code false} otherwise
	 */
	public boolean isSameDay(final Date date) {
		GregorianCalendar endCal = new GregorianCalendar();
		endCal.setTime(date);
		if (this.cal.get(GregorianCalendar.YEAR)
				!= endCal.get(GregorianCalendar.YEAR)) {
			return false;
		}
		if (this.cal.get(GregorianCalendar.MONTH)
				!= endCal.get(GregorianCalendar.MONTH)) {
			return false;
		}
		if (this.cal.get(GregorianCalendar.DAY_OF_MONTH)
				!= endCal.get(GregorianCalendar.DAY_OF_MONTH)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns the week of the month of the date.
	 * 
	 * @return week of month
	 */
	public int getWeekOfMonth() {
		return this.cal.get(GregorianCalendar.WEEK_OF_MONTH);
	}
	
	/**
	 * Returns whether or not the specified week of the month is valid for the
	 * month of the date.
	 * 
	 * @param weekOfMonth week of month
	 * @return whether week of month is valid for month of date
	 */
	public boolean isValidWeekOfMonth(final int weekOfMonth) {
		return weekOfMonth >= this.cal.getActualMinimum(
				GregorianCalendar.WEEK_OF_MONTH) && weekOfMonth
				<= this.cal.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
	}
	
	/**
	 * Sets the date to the specified week of the current month of the date.
	 * 
	 * @param weekOfMonth week of month
	 * @throws IllegalArgumentException if an unknown week of month is specified
	 */
	public void setToWeekOfMonth(final WeekOfMonth weekOfMonth) {
		int actualWeekOfMonth;
		switch (weekOfMonth) {
			case FIRST:
				actualWeekOfMonth = 1;
				break;
			case SECOND:
				actualWeekOfMonth = 2;
				break;
			case THIRD:
				actualWeekOfMonth = 2 + 1;
				break;
			case FOURTH:
				actualWeekOfMonth = 2 + 2;
				break;
			case FIFTH:
				actualWeekOfMonth = 2 + 2 + 1;
				break;
			case LAST:
				actualWeekOfMonth = this.cal.getActualMaximum(
					GregorianCalendar.WEEK_OF_MONTH);
				break;
			default:
				throw new IllegalArgumentException("Unknow week of month "
						+ weekOfMonth);
		}
		this.cal.set(GregorianCalendar.WEEK_OF_MONTH, actualWeekOfMonth);
	}
	
	/**
	 * Returns whether the day of the current week is in the current month.
	 * 
	 * @param dayOfWeek day of week to check for in current month
	 * @return whether specified day of week is in current month
	 */
	public boolean isDayOfWeekInCurrentMonth(final DayOfWeek dayOfWeek) {
		GregorianCalendar projectedCal = new GregorianCalendar();
		projectedCal.setTime(this.cal.getTime());
		int originalMonth = projectedCal.get(GregorianCalendar.MONTH);
		projectedCal.set(GregorianCalendar.DAY_OF_WEEK,
				dayOfWeek.getGregorianCalendarDayOfWeek());
		int newMonth = projectedCal.get(GregorianCalendar.MONTH);
		return originalMonth == newMonth;
	}
	
	/**
	 * Sets the day of the week.
	 * 
	 * @param dayOfWeek day of week
	 */
	public void setToDayOfWeek(final DayOfWeek dayOfWeek) {
		this.cal.set(GregorianCalendar.DAY_OF_WEEK,
				dayOfWeek.getGregorianCalendarDayOfWeek());
	}
	
	/**
	 * Returns the number of days in a week.
	 * 
	 * @return number of days in a week
	 */
	public int getDaysInWeek() {
		return this.cal.getActualMaximum(GregorianCalendar.DAY_OF_WEEK);
	}
	
	/**
	 * Returns the number of hours in a day.
	 * 
	 * @return number of hours in a day
	 */
	public int getHoursInDay() {
		return this.cal.getActualMaximum(GregorianCalendar.HOUR_OF_DAY);
	}
	
	/**
	 * Returns the earliest date.
	 * 
	 * <p>If two dates are the same and are the earliest provided, the first
	 * date encountered will be returned.
	 * 
	 * <p>If any of the dates are {@code null}, return {@code null}.
	 * 
	 * @param first first date to compare
	 * @param others optional other dates to compare
	 * @return earliest date
	 */
	public static Date findEarliest(final Date first, final Date... others) {
		if (first == null) {
			return null;
		}
		Date earliest = first;
		for (Date other : others) {
			if (other != null) {
				if (other.compareTo(earliest) < 0) {
					earliest = other;
				}
			} else {
				return null;
			}
		}
		return earliest;
	}
	
	/**
	 * Returns the latest date.
	 * 
	 * <p>If two dates are the same and are the latest provided, the first
	 * date encountered will be returned.
	 * 
	 * <p>If any of the dates are {@code null}, return {@code null}.
	 * 
	 * @param first first date to compare
	 * @param others optional other dates to compare
	 * @return latest date
	 */
	public static Date findLatest(final Date first, final Date... others) {
		if (first == null) {
			return null;
		}
		Date latest = first;
		for (Date other : others) {
			if (other != null) {
				if (other.compareTo(latest) > 0) {
					latest = other;
				}
			} else {
				return null;
			}
		}
		return latest;
	}
}