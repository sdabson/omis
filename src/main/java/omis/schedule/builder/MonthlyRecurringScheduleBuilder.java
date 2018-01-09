package omis.schedule.builder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import omis.datatype.DateRange;
import omis.datatype.DayOfWeek;
import omis.datatype.WeekOfMonth;
import omis.instance.factory.InstanceFactory;
import omis.schedule.domain.SchedulableEvent;
import omis.util.DateManipulator;

/**
 * Monthly schedule builder.
 * 
 * <p>Allows a schedule to be built either on a the <tt>n</tt>th day of every
 * <tt>m</tt> months or a specific day of the week every <tt>m</tt> months.
 * 
 * @author Stephen Abson
 * @version 0.1.12 (July 9, 2013)
 * @since OMIS 3.0
 */
public final class MonthlyRecurringScheduleBuilder
		extends AbstractRecurringScheduleBuilder {
	
	// Instantiates a monthly recurring schedule builder with the required
	// properties
	private MonthlyRecurringScheduleBuilder(final DateRange eventDateRange,
			final DateRange recurrenceDateRange,
			final AbstractBuilderImpl builderImpl) {
		super(eventDateRange, recurrenceDateRange, builderImpl);
	}

	/**
	 * Instantiates and returns a builder that will build a schedule every;
	 * <tt>n</tt>th day of every <tt>m</tt> months that falls between the range
	 * of {@code recurrenceDateRange} at the time range specified in
	 * {@code eventDateRange}.
	 * 
	 * <p>Time information in {@code recurrenceDateRange} and date information
	 * in {@code eventDateRange} will be ignored.
	 * 
	 * <p>If there is no <tt>n</tt>th day of a selected month, <em>the event for
	 * that month will be skipped</em>. This applies both to when the schedule
	 * is built (by calling the {@code build} method) and when the end date is
	 * set after a number of projected occurrences (by calling the
	 * {@code getEndDateAfterOccurrences} method). The latter method will always
	 * produce the end date required in order for the number of occurrences
	 * of the event to be scheduled.
	 * 
	 * @param eventDateRange time range of events to be scheduled; date part
	 * of range ignored
	 * @param recurrenceDateRange date range during which events are to be
	 * scheduled; time part of range ignored 
	 * @param nthDay <tt>n</tt>th day of month on which to schedule event
	 * @param m every <tt>m</tt> month in which to schedule event
	 * @return builder for schedule on <tt>n</tt>th day of every <tt>m</tt>
	 * month
	 * @throws IllegalStateException if {@code nthDay} is zero or less
	 */
	public static MonthlyRecurringScheduleBuilder createForNthDay(
			final DateRange eventDateRange, final DateRange recurrenceDateRange,
			final int nthDay, final int m) {
		
		if (nthDay <= 0) {
			throw new IllegalArgumentException(
					"nthDay must be greater than zero");
		}
		return new MonthlyRecurringScheduleBuilder(
				eventDateRange, recurrenceDateRange,
				new AbstractBuilderImpl() {
			
			/** {@inheritDoc} */
			@Override
			protected <T extends SchedulableEvent> Set<T> build(
					final InstanceFactory<T> instanceFactory) {
				
				DateManipulator current = new DateManipulator(
						recurrenceDateRange.getStartDate());
				current.setToEarliestTimeInDay();
				int actualMonth = 1;
				Set<T> results = new HashSet<T>();
				while (current.isLessThanOrEqualTo(
						recurrenceDateRange.findLatestTimeInEndDate())) {
					
					// Could this be done without actualMonth? Changing
					// month by "m" each time? [SA - Feb 10, 2012]
					if (actualMonth % m == 0
							
							// According to javadoc skip if day of month is
							// invalid for month
							&& current.isDayOfMonthValid(nthDay)) {
					
						/* Two checks are required - one to make sure that the
						 * first event is not scheduled before the recurrence
						 * range and one to make sure that the last event is not
						 * scheduled after - time is not included in this
						 * calculation */
						if (current.isGreaterThanOrEqualTo(
								recurrenceDateRange
									.findEarliestTimeInStartDate())
							&& current.isLessThanOrEqualTo(
								recurrenceDateRange.findLatestTimeInEndDate())) {
							
							// TODO: Why is this here and not before if block?
							current.safelySetDayOfMonth(nthDay);
							T event = instanceFactory.createInstance();
							event.setDateRange(getDateRangeForDateAtTimes(
									current.getDate(), eventDateRange));
							results.add(event);
						}
					}
					current.changeMonth(1);
					actualMonth++;
				}
				return results;
			}

			/** {@inheritDoc} */
			@Override
			protected Date getEndDateAfterOccurrences(final int occurrences) {
				if (occurrences <= 0) {
					throw new IllegalArgumentException(
							"Occurrences must be greater than zero");
				}
				DateManipulator current = new DateManipulator(
						recurrenceDateRange.getStartDate());
				current.setToEarliestTimeInDay();
				int occurrenceCount = 0;
				int actualMonth = 1;
				
				// Selection algorithm matches (for the most part) that of
				// the method "build" with the exception that no check should
				// be made to make sure that the current iteration of the date
				// is less than the recurrence date range as this purpose
				// of this method is to set that exact date
				while (occurrenceCount < occurrences) {
					if (actualMonth % m == 0
							&& current.isDayOfMonthValid(nthDay)) {
						if (current.isGreaterThanOrEqualTo(
								recurrenceDateRange
									.findEarliestTimeInStartDate())) {
							occurrenceCount++;
							if (occurrenceCount >= occurrences) {
								break;
							}
						}
					}
					current.changeMonth(1);
					actualMonth++;
				}
				
				// Add time date of current recurrence end date if latter is set
				// See javadoc for this method
				if (recurrenceDateRange.getEndDate() != null) {
					current.setToTimeOfDate(recurrenceDateRange.getEndDate());
				} else {
					current.setToEarliestTimeInDay();
				}
				return current.getDate();
			}
		});
	}
	
	/**
	 * Instantiates and returns a builder that will build a schedule of events
	 * on a specific day of a specific week of every <tt>m</tt> months.
	 * 
	 * <p>Time information in {@code recurrenceDateRange} and date information
	 * in {@code eventDateRange} will be ignored.
	 * 
	 * <p>If the specified day of the week is not found in the specified week of
	 * a selected month, the event for that month will be skipped. This applies
	 * to both {@code build} and {@code getEndDateAfterOccurrences}.
	 * 
	 * @param eventDateRange time range of event
	 * @param recurrenceDateRange date range of recurrence of event
	 * @param dayOfWeek day of week on which to schedule event
	 * @param weekOfMonth week of month on which to schedule event
	 * @param m schedule the event every <tt>m</tt> months
	 * @return builder of a schedule of events on each {@code dayOfWeek} of
	 * every {@nthWeek} of every {@code m} months
	 * @throw IllegalArgumentException if an unknown week of month was specified
	 */
	public static MonthlyRecurringScheduleBuilder createForDayOfNthWeek(
			final DateRange eventDateRange, final DateRange recurrenceDateRange,
			final DayOfWeek dayOfWeek, final WeekOfMonth weekOfMonth,
			final int m) {
		return new MonthlyRecurringScheduleBuilder(
				eventDateRange, recurrenceDateRange,
				new AbstractBuilderImpl() {

			/** {@inheritDoc} */
			@Override
			protected <T extends SchedulableEvent> Set<T> build(
					final InstanceFactory<T> instanceFactory) {
				DateManipulator current = new DateManipulator(
						recurrenceDateRange.findEarliestTimeInStartDate());
				int actualMonth = 1;
				Set<T> results = new HashSet<T>();
				while (current.isLessThanOrEqualTo(
						recurrenceDateRange.findLatestTimeInEndDate())) {
					
					// Could this be done without actualMonth? I don't see a way
					if (actualMonth % m == 0) {
						
						// Need to know whether the day of the week for
						// the week of the month is in the same month, if not
						// an event should _NOT_ be created for that month
						current.setToWeekOfMonth(weekOfMonth);
						if (current.isDayOfWeekInCurrentMonth(dayOfWeek)) {
							
							current.setToDayOfWeek(dayOfWeek);
							if (current.isGreaterThanOrEqualTo(
									recurrenceDateRange
										.findEarliestTimeInStartDate())
								&& current.isLessThanOrEqualTo(
									recurrenceDateRange
										.findLatestTimeInEndDate())) {
								T event = instanceFactory.createInstance();
								event.setDateRange(getDateRangeForDateAtTimes(
										current.getDate(), eventDateRange));
								results.add(event);
							}
						}
					}
					current.changeMonth(1);
					actualMonth++;
				}
				return results;
			}

			// TODO: This doesn't work... the calculation is incorrect... - SA
			// something is missing from the algorithm
			/** {@inheritDoc} */
			@Override
			protected Date getEndDateAfterOccurrences(final int occurrences) {
				if (occurrences <= 0) {
					throw new IllegalArgumentException(
							"Occurrences must be greater than zero");
				}
				DateManipulator current = new DateManipulator(
						recurrenceDateRange.findEarliestTimeInStartDate());
				int occurrenceCount = 0;
				int actualMonth = 1;
				
				// Selection algorithm matches (for the most part) that of
				// the method "build" with the exception that no check should
				// be made to make sure that the current iteration of the date
				// is less than the recurrence date range as this purpose
				// of this method is to set that exact date
				while (occurrenceCount < occurrences) {
					if (actualMonth % m == 0) {
						current.setToWeekOfMonth(weekOfMonth);
						if (current.isDayOfWeekInCurrentMonth(dayOfWeek)) {
							current.setToDayOfWeek(dayOfWeek);
							if (current.isGreaterThanOrEqualTo(
									recurrenceDateRange
										.findEarliestTimeInStartDate())) {
								occurrenceCount++;
							}
						}
					}
					if (occurrenceCount < occurrences) {
						current.changeMonth(1);
						actualMonth++;
					}
				}
				
				// Add time date of current recurrence end date if latter is set
				// See javadoc for this method
				if (recurrenceDateRange.getEndDate() != null) {
					current.setToTimeOfDate(recurrenceDateRange.getEndDate());
				} else {
					current.setToEarliestTimeInDay();
				}
				return current.getDate();
			}
		});
	}
}