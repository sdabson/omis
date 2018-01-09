package omis.schedule.builder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import omis.datatype.DateRange;
import omis.instance.factory.InstanceFactory;
import omis.schedule.domain.SchedulableEvent;
import omis.util.DateManipulator;


/**
 * Daily schedule builder.
 * 
 * <p>Allows the building of a recurring schedule on a daily basis. A daily
 * basis might be every nth day or every weekday for a given period.
 * 
 * @author Stephen Abson
 * @version 0.1.11 (July 9, 2013)
 * @since OMIS 3.0
 */
public final class DailyRecurringScheduleBuilder
		extends AbstractRecurringScheduleBuilder {
		
	// Instantiates with the specified event and recurrence date ranges
	private DailyRecurringScheduleBuilder(final DateRange eventDateRange,
			final DateRange recurrenceDateRange,
			final AbstractBuilderImpl builderImpl) {
		super(eventDateRange, recurrenceDateRange, builderImpl);
	}
	
	/**
	 * Instantiates and returns a daily recurring schedule builder for
	 * the nth day of a particular period.
	 * 
	 * @param eventDateRange date range of the event to be scheduled every
	 * nth day
	 * @param recurrenceDateRange particular period during which the event is
	 * to occur
	 * @param nthDay nth day on which to schedule the recurring event
	 * @return nth day daily recurring event schedule builder
	 * @throws IllegalArgumentException if the nth day is zero or less
	 */
	public static DailyRecurringScheduleBuilder createForNthDay(
			final DateRange eventDateRange, final DateRange recurrenceDateRange,
			final int nthDay) {
		if (nthDay  <= 0) {
			throw new IllegalArgumentException(
					"nth day must be greater than zero");
		}
		return new DailyRecurringScheduleBuilder(
				eventDateRange, recurrenceDateRange,
				new AbstractBuilderImpl() {
				
					/** {@inheritDoc} */
					@Override
					protected <T extends SchedulableEvent> Set<T> build(
							final InstanceFactory<T> instanceFactory) {
						DateManipulator current = new DateManipulator(
								recurrenceDateRange.getStartDate());
						current.setToEarliestTimeInDay();
						DateManipulator end = new DateManipulator(
								recurrenceDateRange.getEndDate());
						end.setToLatestTimeInDay();
						Set<T> results = new HashSet<T>();
						while (current.isLessThanOrEqualTo(end.getDate())) {
							T event = instanceFactory.createInstance();
							event.setDateRange(getDateRangeForDateAtTimes(
									current.getDate(), eventDateRange));
							results.add(event);
							current.changeDate(nthDay);
						}
						return results;
					}
			
					/** {@inheritDoc} */
					@Override
					protected Date getEndDateAfterOccurrences(
							final int occurrences) {
						if (occurrences <= 0) {
							throw new IllegalArgumentException(
									"Occurrences must be greater than zero");
						}
						DateManipulator current = new DateManipulator(
								recurrenceDateRange.getStartDate());
						current.setToEarliestTimeInDay();
				
						/* Subtract 1 from the calculated number of days
						 * (nthDay * occurrences) as the start date counts
						 * as one of the days (day zero if you like). */
						current.changeDate((nthDay * occurrences) - 1);
				
						// Add time date of current recurrence end date
						// if latter is set - see javadoc for this method
						if (recurrenceDateRange.getEndDate() != null) {
							current.setToTimeOfDate(recurrenceDateRange
									.getEndDate());
						} else {
							current.setToEarliestTimeInDay();
						}
						return current.getDate();
					}
				});
	}
	
	/**
	 * Instantiates and returns a daily recurring schedule builder for every
	 * week day of a particular period.
	 * 
	 * <p>Time information attached to the recurrence date range will be
	 * ignored.
	 * 
	 * @param eventDateRange date range of the event to be scheduled every
	 * week day
	 * @param recurrenceDateRange particular period during which the event is to
	 * occur
	 * @return weekday recurring event schedule builder
	 */
	public static DailyRecurringScheduleBuilder createEveryWeekday(
			final DateRange eventDateRange,
			final DateRange recurrenceDateRange) {
		return new DailyRecurringScheduleBuilder(
				eventDateRange, recurrenceDateRange,
				new AbstractBuilderImpl() {
			
					/** {@inheritDoc} */
					@Override
					protected <T extends SchedulableEvent> Set<T> build(
							final InstanceFactory<T> instanceFactory) {
						DateManipulator current = new DateManipulator(
								recurrenceDateRange.getStartDate());
						current.setToEarliestTimeInDay();
						DateManipulator end = new DateManipulator(
								recurrenceDateRange.getEndDate());
						end.setToLatestTimeInDay();
						Set<T> results = new HashSet<T>();
						while (current.isLessThanOrEqualTo(end.getDate())) {
							if (current.isWeekday()) {
								T event = instanceFactory.createInstance();
								event.setDateRange(getDateRangeForDateAtTimes(
										current.getDate(), eventDateRange));
								results.add(event);
							}
							current.changeDate(1);
						}
						return results;
					}
			
					/** {@inheritDoc} */
					@Override
					protected Date getEndDateAfterOccurrences(
							final int occurrences) {
						if (occurrences <= 0) {
							throw new IllegalArgumentException(
									"Occurrences must be greater than zero");
						}
						DateManipulator current = new DateManipulator(
								recurrenceDateRange.getStartDate());
						current.setToEarliestTimeInDay();
						int i = 0;
						while (i < occurrences) {
							if (current.isWeekday()) {
								i++;
							}
							current.changeDate(1);
						}
						
						// Add time date of current recurrence end date if
						// latter is set - see javadoc for this method
						if (recurrenceDateRange.getEndDate() != null) {
							current.setToTimeOfDate(
									recurrenceDateRange.getEndDate());
						} else {
							current.setToEarliestTimeInDay();
						}
						return current.getDate();
					}
				});
	}
}