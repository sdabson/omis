/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.datatype;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import omis.util.DateManipulator;

/**
 * A date range.
 * 
 * <p>The date range guarantees that the start date (if not {@code null}) is
 * never greater than the end date (if not {@code null}). Methods which set the
 * start and end dates will throw an {@code IllegalArgumentException} if an
 * attempt is made to violate this rule.
 * 
 * <p>Deep copies of the specified start and end dates are made and maintained.
 * 
 * @author Stephen Abson
 * @version 0.1.8 (Jan 28, 2015)
 * @since OMIS 3.0
 */
public class DateRange implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// Length of a day, in ms
	private static final long DAY_LENGTH = 24 * 60 * 60 * 1000;
	
	/*
	 * Store dates in milliseconds (since Jan 1, 1970) and perform deep copies
	 * when they are accessed - this is to ensure that the date is only
	 * manipulated via the object interface and not, say, a local reference.
	 */
	
	private Long startDate;
	
	private Long endDate;
	
	/* Constructors */
	
	/** Instantiates a default date range. */
	public DateRange() {
		// Default instantiation
	}
	
	/**
	 * Instantiates a date range with the specified start and end dates.
	 * 
	 * @param startDate start date
	 * @param endDate end date
	 * @throws IllegalArgumentException if the start date occurs after the
	 * end date
	 */
	public DateRange(final Date startDate, final Date endDate) {
		if (startDate != null && endDate != null) {
			if (startDate.compareTo(endDate) > 0) {
				throw new IllegalArgumentException(
						"Start date cannot occur after end date");
			}
		}
		if (startDate != null) {
			this.startDate = startDate.getTime();
		} else {
			this.startDate = null;
		}
		if (endDate != null) {
			this.endDate = endDate.getTime();
		} else {
			this.endDate = null;
		}
	}
	
	/**
	 * Instantiates a date range with the specified start and end dates in
	 * milliseconds since 1 Jan 1970.
	 * 
	 * @param startDate start date in milliseconds
	 * @param endDate end date in milliseconds
	 * @throws IllegalArgumentException if the start date occurs after the
	 * end date
	 */
	public DateRange(final Long startDate, final Long endDate) {
		if (startDate != null && endDate != null) {
			if (startDate > endDate) {
				throw new IllegalArgumentException(
					"Start date cannot occur after end date");
			}
		}
		if (startDate != null) {
			this.startDate = new Long(startDate);
		} else {
			this.startDate = null;
		}
		if (endDate != null) {
			this.endDate = new Long(endDate);
		} else {
			this.endDate = null;
		}
	}
	
	/* Exported methods */
	
	/**
	 * Returns a date range from the date/time pairs.
	 * 
	 * <p>A date in a date/time pair may be {@code null} - if it is, the time
	 * must also be {@code null} otherwise an {@code IllegalArgumentException}
	 * will be thrown. A time in the date/time pair may be {@code null} even
	 * if the date is not. In this case a default time of midnight will be used.
	 * 
	 * <p>Time information of the date in the date/time pair will be discarded.
	 * 
	 * @param startDate date of start date/time pair
	 * @param startTime time of start date/time pair
	 * @param endDate date of end date/time pair
	 * @param endTime time of end date/time pair
	 * @throws IllegalArgumentException if a date in a date/time pair is
	 * {@code null} but a time is not
	 * @return date range from date/time pairs
	 */
	public static DateRange createFromDateTimes(final Date startDate,
			final Date startTime, final Date endDate, final Date endTime) {
		
		// NB - DateManipulator#getDateAtTimeOfDay(Date, Date) discards
		// time information of day 
		final Date startDateTime;
		if (startDate != null) {
			startDateTime = DateManipulator.getDateAtTimeOfDay(
					startDate, startTime);
		} else if (startTime != null) {
			throw new IllegalArgumentException(
					"Start time not allowed without start date");
		} else {
			startDateTime = null;
		}
		final Date endDateTime;
		if (endDate != null) {
			endDateTime = DateManipulator.getDateAtTimeOfDay(endDate, endTime);
		} else if (endTime != null) {
			throw new IllegalArgumentException(
					"End time not allowed without end date");
		} else {
			endDateTime = null;
		}
		return new DateRange(startDateTime, endDateTime);
	}
	
	/**
	 * Sets the start date to a deep copy of the specified start date.
	 * 
	 * @param startDate start date
	 * @throws IllegalArgumentException if the new start date occurs after the
	 * end date
	 */
	public void setStartDate(final Date startDate) {
		if (startDate != null) {
			if (this.getEndDate() != null
					&& startDate.compareTo(this.getEndDate()) > 0) {
				throw new IllegalArgumentException(
						"Start date cannot occur after end date");
			}
			this.startDate = startDate.getTime();
		} else {
			this.startDate = null;
		}
	}
	
	/**
	 * Sets the end date to a deep copy of the specified end date.
	 * 
	 * @param endDate end date
	 * @throws IllegalArgumentException if the new end date occurs before the
	 * start date
	 */
	public void setEndDate(final Date endDate) {
		if (endDate != null) {
			if (this.getStartDate() != null
					&& endDate.compareTo(this.getStartDate()) < 0) {
				throw new IllegalArgumentException(
						"End date cannot occur before start date");
			}
			this.endDate = endDate.getTime();
		} else {
			this.endDate = null;
		}
	}
	
	/**
	 * Returns a deep copy of the start date.
	 * 
	 * @return deep copy of start date
	 */
	public Date getStartDate() {
		if (this.startDate != null) {
			return new Date(this.startDate);
		} else {
			return null;
		}
	}
	
	/**
	 * Returns a deep copy of the end date.
	 * 
	 * @return deep copy of end date
	 */
	public Date getEndDate() {
		if (this.endDate != null) {
			return new Date(this.endDate);
		} else {
			return null;
		}
	}
	
	/**
	 * Returns whether the start and end dates are in the same day.
	 * 
	 * <p>The test compares the year, month and day of both the start and
	 * end dates.
	 * @return whether two dates are in same day
	 */
	public boolean isSameDay() {
		return new DateManipulator(
				this.getStartDate()).isSameDay(this.getEndDate());
	}
	
	/**
	 * Returns whether {@code this} occurs within the specified date range.
	 * 
	 * @param dateRange date range
	 * @return whether {@code this} occurs within {@code dateRange}
	 */
	public boolean occursWithin(final DateRange dateRange) {
		if (DateRange.dateIsGreaterThanOrEqualTo(this.getStartDate(),
					dateRange.getStartDate())
				&& (DateRange.dateIsLessThanOrEqualTo(this.getEndDate(),
					dateRange.getEndDate()))) {
			
			// TODO: This should be optional - SA
			if (((this.getStartDate() != null || dateRange.getEndDate() != null)
					&& DateRange.datesAreEqual(
							this.getStartDate(), dateRange.getEndDate()))
				|| ((this.getEndDate() != null
						|| dateRange.getStartDate() != null)
					&& DateRange.datesAreEqual(
							this.getEndDate(), dateRange.getStartDate()))) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns whether {@code dateRange1} occurs within {@code dateRange2}.
	 * 
	 * <p>If a date range or both its start and end date are {@code null}, the
	 * date range is assumed to span forever. Forever is assumed to fit within
	 * forever so this method will return {@code true} if both date ranges
	 * span forever.
	 * 
	 * @param dateRange1 first date range
	 * @param dateRange2 second date range
	 * @return whether {@code dateRange1} occurs within {@code dateRange2}
	 */
	public static boolean occursWithin(
			final DateRange dateRange1, final DateRange dateRange2) {
		if ((DateRange.isForever(dateRange1)
				&& DateRange.isForever(dateRange2))
				|| DateRange.isForever(dateRange2)) {
			return true;
		} else {
			if (!DateRange.isForever(dateRange1)
					&& dateRange1.occursWithin(dateRange2)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * Returns whether {@code this} and the specified date range overlap.
	 * 
	 * <p>This method assumes that there are no {@code null} start or end dates
	 * of either date range.
	 * 
	 * @param dateRange date range
	 * @return whether {@code this} and {@code dateRange} overlap
	 */
	public boolean overlaps(final DateRange dateRange) {
		if ((this.getStartDate().compareTo(dateRange.getEndDate()) <= 0)
			&& (this.getEndDate().compareTo(
					dateRange.getStartDate()) >= 0)) {
			
			// TODO: This should be optional - SA
			if (this.getStartDate().equals(dateRange.getEndDate())
					|| this.getEndDate().equals(dateRange.getStartDate())) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns the earliest moment in the start date of the range.
	 * 
	 * <p>Note that this point in time might fall before the beginning of the
	 * range.
	 * 
	 * <p>This method assumes that there are no {@code null} start or end dates
	 * of either date range.
	 * 
	 * @return earliest moment in start date of range
	 */
	public Date findEarliestTimeInStartDate() {
		DateManipulator manipulator = new DateManipulator(this.getStartDate());
		manipulator.setToEarliestTimeInDay();
		return manipulator.getDate();
	}
	
	/**
	 * Returns the latest moment in the end date of the range.
	 * 
	 * <p>Note that this point in time might fall after the end of the range.
	 * 
	 * @return latest moment in end date of range
	 */
	public Date findLatestTimeInEndDate() {
		DateManipulator manipulator = new DateManipulator(this.getEndDate());
		manipulator.setToLatestTimeInDay();
		return manipulator.getDate();
	}
	
	/**
	 * Returns a list of earliest moments in each day of the date range. This
	 * list is sorted by day from earliest to latest.
	 * 
	 * <p>Note that the earliest day returned may have a timestamp before the
	 * start date of the range.
	 * 
	 * @return earliest moments of each day of date range
	 */
	public List<Date> findEarliestTimesInEachDay() {
		DateManipulator current = new DateManipulator(this.getStartDate());
		current.setToEarliestTimeInDay();
		List<Date> dates = new ArrayList<Date>();
		while (current.isLessThan(this.findLatestTimeInEndDate())) {
			dates.add(current.getDate());
			current.changeDate(1);
		}
		Collections.sort(dates);
		return dates;
	}
	
	/**
	 * Returns a deep copy of the specified date range.
	 * 
	 * @param dateRange date range of which to produce a deep copy
	 * @return deep copy of specified date range
	 */
	public static DateRange deepCopy(final DateRange dateRange) {
		return new DateRange(dateRange.getStartDate(), dateRange.getEndDate());
	}
	
	/**
	 * Returns a date range for the earliest and latest moments of the
	 * specified date.
	 * 
	 * @param date date the range of which to determine
	 * @return daily range of the specified date
	 */
	public static DateRange findDailyRange(final Date date) {
		DateManipulator start = new DateManipulator(date);
		start.setToEarliestTimeInDay();
		DateManipulator end = new DateManipulator(date);
		end.setToLatestTimeInDay();
		return new DateRange(start.getDate(), end.getDate());
	}
	
	/**
	 * Returns a date range for the earliest and latest moments of the week
	 * of the specified date.
	 * 
	 * @param date date the weekly range of which to determine
	 * @return weekly range of the week of the specified date
	 */
	public static DateRange findWeeklyRange(final Date date) {
		DateManipulator start = new DateManipulator(date);
		start.setToEarliestTimeInWeek();
		DateManipulator end = new DateManipulator(date);
		end.setToLatestTimeInWeek();
		return new DateRange(start.getDate(), end.getDate());
	}
	
	/**
	 * Returns a date range from the earliest moment of the first week of the
	 * month of the specified date to the latest moment of the last week of the
	 * specified month.
	 * 
	 * @param date date the week of month range of which to determine
	 * @return week of month range of the specified date
	 */
	public static DateRange findWeekOfMonthRange(final Date date) {
		DateManipulator start = new DateManipulator(date);
		start.setToEarliestTimeInWeekOfMonth();
		DateManipulator end = new DateManipulator(date);
		end.setToLatestTimeInWeekOfMonth();
		return new DateRange(start.getDate(), end.getDate());
	}
	
	/**
	 * Returns the range of dates for the range of times of the specified day.
	 * 
	 * @param day day
	 * @param timeRange range of times
	 * @return range of dates for range of times on day
	 */
	public static DateRange findTimeRangeOnDay(final Date day,
			final DateRange timeRange) {
		DateManipulator date = new DateManipulator(day);
		date.setToTimeOfDate(timeRange.getStartDate());
		Date startDate = date.getDate();
		date.setToTimeOfDate(timeRange.getEndDate());
		Date endDate = date.getDate();
		return new DateRange(startDate, endDate);
	}
	
	/**
	 * Returns the specified range of dates as date ranges at specific times.
	 * 
	 * @param dateRange range of dates to return
	 * @param timeRange times at which to return ranges
	 * @return range of dates as date ranges at specific times
	 */
	public static List<DateRange> findDailyRangeAtTimeRange(
			final DateRange dateRange, final DateRange timeRange) {
		
		// Minor optimizations
		final Date startTime = timeRange.getStartDate();
		final Date endTime = timeRange.getEndDate(); 
		
		// Builds results
		DateManipulator current = new DateManipulator(dateRange.getStartDate());
		current.setToTimeOfDate(startTime);
		DateManipulator end = new DateManipulator(dateRange.getEndDate());
		end.setToTimeOfDate(endTime);
		List<DateRange> result = new ArrayList<DateRange>();
		while (current.isLessThanOrEqualTo(end.getDate())) {
			DateRange rangeAtTimes = new DateRange();
			rangeAtTimes.setStartDate(current.getDate());
			current.setToTimeOfDate(endTime);
			rangeAtTimes.setEndDate(current.getDate());
			result.add(rangeAtTimes);
			current.changeDate(1);
			
			// This might be a bit suspect
			current.setToTimeOfDate(startTime);
		}
		return result;
	}
	
	/**
	 * Returns the range of times between the first and last millisecond
	 * of the specified date.
	 * 
	 * @param date date
	 * @return range of times in date
	 */
	public static DateRange findTimeRangeOfDate(final Date date) {
		DateManipulator manipulator = new DateManipulator(date);
		manipulator.setToEarliestTimeInDay();
		Date startDate = manipulator.getDate();
		manipulator.setToLatestTimeInDay();
		Date endDate = manipulator.getDate();
		return new DateRange(startDate, endDate);
	}
	
	/**
	 * Returns the range of dates within a year of the date.
	 * 
	 * @param date date
	 * @return range of dates within year of date
	 */
	public static DateRange findYearlyRange(final Date date) {
		DateManipulator manipulator = new DateManipulator(date);
		manipulator.changeYear(-1);
		final Date startDate = manipulator.getDate();
		manipulator.setDate(date);
		manipulator.changeYear(1);
		final Date endDate = manipulator.getDate();
		return new DateRange(startDate, endDate);
	}
	
	/**
	 * Returns whether date range is active on date.
	 * 
	 * <p>If both the start and end date are {@code null}, the date range
	 * is active regardless of the date given.
	 * 
	 * @param date date
	 * @return whether date range is active on date
	 */
	public boolean isActive(final Date date) {
		if (this.getStartDate() == null && this.getEndDate() == null) {
			return true;
		} else if (this.getStartDate() != null && this.getEndDate() == null) {
			return this.getStartDate().compareTo(date) <= 0;
		} else if (this.getEndDate() != null && this.getStartDate() == null) {
			return this.getEndDate().compareTo(date) >= 0;
		} else {
			return this.getStartDate().compareTo(date) <= 0
					&& this.getEndDate().compareTo(date) >= 0;
		}
	}
	
	/**
	 * Returns {@code true} if {@code this} spans the entirety of time.
	 * 
	 * @return {@code true} if {@code this} spans the entirety of time;
	 * {@code false} otherwise
	 */
	public boolean isForever() {
		if (this.getStartDate() == null && this.getEndDate() == null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns whether the date range is forever.
	 * 
	 * <p>Returns {@code true} if the date range is {@code null}.
	 * 
	 * @param dateRange date range
	 * @return whether date range is forever
	 */
	public static boolean isForever(final DateRange dateRange) {
		if (dateRange != null) {
			return dateRange.isForever();
		} else {
			return true;
		}
	}
	
	/**
	 * Returns start date of date range.
	 * 
	 * <p>Returns {@code null} if {@code dateRange} is {@code null}.
	 * 
	 * @param dateRange date range
	 * @return start date of date range
	 */
	public static Date getStartDate(final DateRange dateRange) {
		if (dateRange != null) {
			return dateRange.getStartDate();
		} else {
			return null;
		}
	}
	
	/**
	 * Returns end date of date range.
	 * 
	 * <p>Returns {@code null} if {@code dateRange} is {@code null}.
	 * 
	 * @param dateRange date range
	 * @return end date of date range
	 */
	public static Date getEndDate(final DateRange dateRange) {
		if (dateRange != null) {
			return dateRange.getEndDate();
		} else {
			return null;
		}
	}
	
	/**
	 * Returns a duplicate {@code dateRange} with the start date set
	 * to {@code startDate} (other properties are unaltered).
	 * 
	 * <p>This method is null safe.
	 * 
	 * @param dateRange date range
	 * @param startDate start date
	 * @return duplicate of {@code dateRange} with start date set
	 */
	public static DateRange adjustStartDate(final DateRange dateRange,
			final Date startDate) {
		if (dateRange != null) {
			return new DateRange(startDate, dateRange.getEndDate());
		} else {
			return new DateRange(startDate, null);
		}
	}
	
	/**
	 * Returns a duplicate of {@code dateRange} with the end date set
	 * to {@code endDate} (other properties are unaltered).
	 * 
	 * <p>This method is null safe.
	 * 
	 * @param dateRange date range
	 * @param endDate end date
	 * @return duplicate of {@code dateRange} with start date set 
	 */
	public static DateRange adjustEndDate(final DateRange dateRange,
			final Date endDate) {
		if (dateRange != null) {
			return new DateRange(dateRange.getStartDate(), endDate);
		} else {
			return new DateRange(null, endDate);
		}
	}
	
	/**
	 * Returns whether the start date of the date range is equal to the date.
	 * 
	 * <p>This method is null safe - if both date range and date are
	 * {@code null}, {@code true} will be returned.
	 * 
	 * @param dateRange date range
	 * @param date date
	 * @return whether start date of date range is equal to date
	 */
	public static boolean startDateEquals(final DateRange dateRange,
			final Date date) {
		if (dateRange == null) {
			return date == null;
		} else if (date != null) {
			if (dateRange.getStartDate() == null) {
				return false;
			} else {
				return dateRange.getStartDate().equals(date);
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Returns whether the start dates of the date ranges are equal.
	 * 
	 * <p>A {@code null} start date or date range is equal to the other
	 * date range or start date being {@code null}.
	 * 
	 * @param dateRange1 first date range
	 * @param dateRange2 second date range
	 * @return whether start dates of date ranges are equal
	 */
	public static boolean startDatesAreEqual(
			final DateRange dateRange1, final DateRange dateRange2) {
		Date startDate2;
		if (dateRange2 != null) {
			startDate2 = dateRange2.getStartDate();
		} else {
			startDate2 = null;
		}
		return DateRange.startDateEquals(dateRange1, startDate2);
	}
	
	/**
	 * Returns whether the end dates of the date ranges are equal.
	 * 
	 * <p>A {@code null} end date or date range is equal to the other
	 * date range or end date being {@code null}.
	 * 
	 * @param dateRange1 first date range
	 * @param dateRange2 second date range
	 * @return whether end dates of date ranges are equal
	 */
	public static boolean endDatesAreEqual(
			final DateRange dateRange1, final DateRange dateRange2) {
		Date endDate2;
		if (dateRange2 != null) {
			endDate2 = dateRange2.getEndDate();
		} else {
			endDate2 = null;
		}
		return DateRange.endDateEquals(dateRange1, endDate2);
	}
	
	/**
	 * Returns whether the end date of the date range is equal to the date.
	 * 
	 * <p>This method is null safe - if both date range and date are
	 * {@code null}, {@code true} will be returned.
	 * 
	 * @param dateRange date range
	 * @param date date
	 * @return whether end date of date range is equal to date
	 */
	public static boolean endDateEquals(final DateRange dateRange,
			final Date date) {
		if (dateRange == null) {
			return date == null;
		} else if (date != null) {
			if (dateRange.getEndDate() == null) {
				return false;
			} else {
				return dateRange.getEndDate().equals(date);
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Returns whether date ranges are equal.
	 * 
	 * @param dateRange1 first date range
	 * @param dateRange2 second date range
	 * @return whether date ranges are equal
	 */
	public static boolean areEqual(
			final DateRange dateRange1, final DateRange dateRange2) {
		if (dateRange1 != null && dateRange1.equals(dateRange2)) {
			return true;
		} else if (dateRange2 != null) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Returns an inclusive count of days in a date range.
	 * 
	 * <p>If either date falls on a day, the day is counted.
	 * 
	 * @param startDate start date
	 * @param endDate end date
	 * @return inclusive count days days in a date range
	 */
	public static long countDaysInclusively(
			final Date startDate, final Date endDate) {
		DateManipulator startMan = new DateManipulator(startDate);
		startMan.setToEarliestTimeInDay();
		DateManipulator endMan = new DateManipulator(endDate);
		endMan.setToLatestTimeInDay();
		return (endMan.getDate().getTime() / DAY_LENGTH)
				- (startMan.getDate().getTime() / DAY_LENGTH);
	}
	
	/**
	 * Returns an exact count of days in a date range.
	 * 
	 * <p>This is the number of 24 hour instances between the range.
	 * 
	 * @param startDate start date
	 * @param endDate end date
	 * @return exact count of days in a date range
	 * @throws IllegalArgumentException if {@code startDate} is greater than
	 * {@code endDate}
	 * @throws NullPointerException if either {@code startDate} or
	 * {@code endDate} is {@code null}
	 */
	public static long countDaysExactly(
			final Date startDate, final Date endDate) {
		
		// Verifies that start date and end date are not null and that end date
		// is on or after start date
		Objects.requireNonNull(startDate, "Start date required");
		Objects.requireNonNull(endDate, "End date required");
		if (startDate.after(endDate)) {
			throw new IllegalArgumentException(
					"Start date must be before or on end date");
		}
		
		// Performs calculation
		return (DateRange.getOffsettedTime(endDate) / DAY_LENGTH)
				- (DateRange.getOffsettedTime(startDate) / DAY_LENGTH);
	}
	
	/**
	 * Returns an exact count of days in {@code this}.
	 * 
	 * <p>This is the number of 24 hour instances between the range.
	 * 
	 * @return an exact count of days in {@code this}
	 * is greater than {@code endDate} of {@code this}
	 * @throws NullPointerException if either {@code startDate} of {@code this}
	 * {@code endDate} of {@code this} is {@code null}
	 */
	public long countDaysExactly() {
		return DateRange.countDaysExactly(this.getStartDate(),
				this.getEndDate());
	}
	
	/**
	 * Returns an exact count of years in a date range.
	 * 
	 * <p>The exact count of years is the number of full years between the
	 * dates.
	 * 
	 * @param startDate start date
	 * @param endDate end date
	 * @return exact count of years in a date range
	 * @throws IllegalArgumentException if {@code startDate} is greater than
	 * {@code endDate}
	 * @throws NullPointerException if either {@code startDate} or
	 * {@code endDate} is {@code null}
	 */
	public static long countYearsExactly(
			final Date startDate, final Date endDate) {
		
		// Verifies that start date and end date are not null and that end date
		// is on or after start date
		Objects.requireNonNull(startDate, "Start date required");
		Objects.requireNonNull(endDate, "End date required");
		if (startDate.after(endDate)) {
			throw new IllegalArgumentException(
					"Start date must be before or on end date");
		}
		
		// Bypasses time check if month of end date is greater than that of
		// start date or months are equals but end day is great than start day
		GregorianCalendar startCal = new GregorianCalendar();
		startCal.setTime(startDate);
		int startDay = startCal.get(GregorianCalendar.DAY_OF_MONTH);
		int startMonth = startCal.get(GregorianCalendar.MONTH);
		int startYear = startCal.get(GregorianCalendar.YEAR);
		GregorianCalendar endCal = new GregorianCalendar();
		endCal.setTime(endDate);
		int endDay = endCal.get(GregorianCalendar.DAY_OF_MONTH);
		int endMonth = endCal.get(GregorianCalendar.MONTH);
		int endYear = endCal.get(GregorianCalendar.YEAR);
		if (endMonth > startMonth
				|| (endMonth == startMonth && endDay > startDay)) {
			
			// Years are complete factoring in only dates
			return endYear - startYear;
		} else if (endMonth == startMonth && endDay == startDay) {
			
			// Factors in time if month and day are equal - time of day of
			// end date must be greater or equal to that of start date
			int startHour = startCal.get(GregorianCalendar.HOUR_OF_DAY);
			int startMinute = startCal.get(GregorianCalendar.MINUTE);
			int startSecond = startCal.get(GregorianCalendar.SECOND);
			int startMillisecond = startCal.get(GregorianCalendar.MILLISECOND);
			int endHour = endCal.get(GregorianCalendar.HOUR_OF_DAY);
			int endMinute = endCal.get(GregorianCalendar.MINUTE);
			int endSecond = endCal.get(GregorianCalendar.SECOND);
			int endMillisecond = endCal.get(GregorianCalendar.MILLISECOND);
			if (endHour > startHour
					|| (((endHour == startHour && endMinute > startMinute)
							|| (((endHour == startHour
										&& endMinute == startMinute
										&& endSecond > startSecond)
									|| (endHour == startHour
											&& endMinute == startMinute
											&& endSecond == startSecond
											&& endMillisecond
												>= startMillisecond)))))) {
				
				// Years are complete factoring in times
				return endYear - startYear;
			} else {
				
				// Years are not complete if end time is before start time on
				// same month and day
				return (endYear - startYear) - 1;
			}
		} else {
			
			// Deducts one year as the end date does not start on the same day
			// of the month of greater than the start date - ergo, the year
			// isn't a full one
			return (endYear - startYear) - 1;
		}
	}
	
	/**
	 * Returns an exact count of years in {@code this}.
	 * 
	 * <p>The exact count of years is the number of full years between the
	 * start and end date of {@code this}.
	 * 
	 * @return exact count of years in {@code this}
	 * @throws NullPointerException if either {@code startDate} of {@code this}
	 * {@code endDate} of {@code this} is {@code null}
	 */
	public long countYearsExactly() {
		return DateRange.countYearsExactly(
				this.getStartDate(), this.getEndDate());
	}
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DateRange)) {
			return false;
		}
		DateRange that = (DateRange) obj;
		if (this.getStartDate() != null) {
			if (!this.getStartDate().equals(that.getStartDate())) {
				return false;
			}
		} else if (that.getStartDate() != null) {
			return false;
		}
		if (this.getEndDate() != null) {
			if (!this.getEndDate().equals(that.getEndDate())) {
				return false;
			}
		} else if (that.getEndDate() != null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Returns a hash code for the date range.
	 * 
	 * @return hash code
	 */
	@Override
	public int hashCode() {
		int hashCode = 14;
		if (this.getStartDate() != null) {
			hashCode = 29 * hashCode + this.getStartDate().hashCode();
		}
		if (this.getEndDate() != null) {
			hashCode = 31 * hashCode + this.getEndDate().hashCode();
		}
		return hashCode;
	}
	
	/**
	 * Returns a string containing the start and end date of the date range.
	 * @return string containing start and end date
	 */
	@Override
	public String toString() {
		return this.getStartDate() + " to " + this.getEndDate(); 
	}
	
	/* Helper methods */
	
	// Returns true if two dates are equal. Null safe.
	private static boolean datesAreEqual(final Date date1, final Date date2) {
		if (date1 == null && date2 == null) {
			return true;
		} else if (date1 != null && date2 != null) {
			return date1.equals(date2);
		} else {
			return false;
		}
	}
	
	// Returns true if date1 is greater than or equal to date2. Null safe.
	private static boolean dateIsGreaterThanOrEqualTo(
			final Date date1, final Date date2) {
		if (date1 != null) {
			if (date2 == null) {
				return true;
			} else {
				return date1.compareTo(date2) >= 0;
			}
		} else {
			return date2 == null;
		}
	}
	
	// Returns true if date1 is less than or equal to date2. Null safe.
	private static boolean dateIsLessThanOrEqualTo(
			final Date date1, final Date date2) {
		if (date1 != null) {
			if (date2 == null) {
				return true;
			} else {
				return date1.compareTo(date2) <= 0;
			}
		} else {
			return date2 == null;
		}
	}
	
	// Returns time in MS with time zone/DST offset added
	private static long getOffsettedTime(final Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		int offset = cal.get(GregorianCalendar.ZONE_OFFSET);
		int dstOffset = cal.get(GregorianCalendar.DST_OFFSET);
		return cal.getTime().getTime() + offset + dstOffset;
	}
}