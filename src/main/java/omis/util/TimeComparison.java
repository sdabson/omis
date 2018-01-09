package omis.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/** Utility to run comparison between dates.
 * @author Ryan Johns
 * @version 0.1.0 (Jan 15, 2016)
 * @since OMIS 3.0 */
public final class TimeComparison {
	
	/* Constructor. */
	private TimeComparison() { }
	
	/** Calculates elapsed years from {@code startDate} to {@code endDate}.
	 * @param startDate - start date.
	 * @param endDate - end date.
	 * @return elapsed years. */
	public static long elapsedYears(final Date startDate, 
			final Date endDate) {
		LocalDate start = TimeComparison.dateToLocalDate(startDate);
		LocalDate end = TimeComparison.dateToLocalDate(endDate);
		return TimeComparison.elapsedYears(start, end);
	}
	
	/** Calculates elapsed years from {@code start} to {@code end}.
	 * @param start - start.
	 * @param end - end.
	 * @return elapsed years. */
	public static long elapsedYears(final LocalDate start, 
			final LocalDate end) {
		Period period = Period.between(start, end);
		return period.getYears();
	}
	
	/** Calculates elapsed months from {@code startDate} to {@code endDate}.
	 * @param startDate - start date.
	 * @param endDate - end date.
	 * @return elapsed months. */
	public static long elapsedMonths(final Date startDate, 
			final Date endDate) {
		LocalDate start = TimeComparison.dateToLocalDate(startDate);
		LocalDate end = TimeComparison.dateToLocalDate(endDate);
		return TimeComparison.elapsedMonths(start, end);
	}
		
	/** Calculates elapsed months from {@code start} to {@code end}.
	 * @param start - start.
	 * @param end - end.
	 * @return elapsed months. */
	public static long elapsedMonths(final LocalDate start, 
			final LocalDate end) {
		Period period = Period.between(start, end);
		return period.toTotalMonths();
	}
	
	/** Calculates elapsed days from {@code startDate} to {@code endDate}.
	 * @param startDate - start date.
	 * @param endDate - end date.
	 * @return elapsed days. */
	public static long elapsedDays(final Date startDate, final Date endDate) {
		LocalDate start = TimeComparison.dateToLocalDate(startDate);
		LocalDate end = TimeComparison.dateToLocalDate(endDate);
		return TimeComparison.elapsedDays(start, end);
	}
	
	/** Calculates elapsed days from {@code start} to {@code end}.
	 * @param start - start date.
	 * @param end - end date.
	 * @return elapsed days. */
	public static long elapsedDays(final LocalDate start, final LocalDate end) {
		return ChronoUnit.DAYS.between(start, end);
	}
	
	/* Convert date to local date. */
	private static LocalDate dateToLocalDate(final Date date) {
		LocalDate result = Instant.ofEpochMilli(date.getTime()).atZone(
				ZoneId.systemDefault()).toLocalDate();
		return result;
	}
}
