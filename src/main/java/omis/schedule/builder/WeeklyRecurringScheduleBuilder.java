package omis.schedule.builder;

import java.util.Date;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import omis.datatype.DateRange;
import omis.datatype.DayOfWeek;
import omis.instance.factory.InstanceFactory;
import omis.schedule.domain.SchedulableEvent;
import omis.util.DateManipulator;


/**
 * Weekly schedule builder.
 * 
 * <p>Allows the building of a schedule on specified days of the week recurring
 * at specified weekly intervals.
 * 
 * @author Stephen Abson
 * @version 0.1.12 (July 9, 2013)
 * @since OMIS 3.0
 */
public final class WeeklyRecurringScheduleBuilder
		extends AbstractRecurringScheduleBuilder {

	// Instantiates a weekly recurring schedule builder with the required
	// properties
	private WeeklyRecurringScheduleBuilder(
			final DateRange eventDateRange,
			final DateRange recurrenceDateRange,
			final AbstractBuilderImpl builderImpl) {
		super(eventDateRange, recurrenceDateRange, builderImpl);
	}
	
	/**
	 * Instantiates and returns a weekly recurring schedule builder for the
	 * specified days of the week occurring every nth week.
	 * 
	 * @param eventDateRange date range of the event to be scheduled
	 * @param recurrenceDateRange particular period during which event is
	 * to occur
	 * @param daysOfWeek days of week of which event is to occur
	 * @param nthWeek frequency of occurrence
	 * @return weekly schedule builder for events recurring on specified
	 * days every nth week
	 * @throws IllegalArgumentException if nthWeek is zero or daysOfWeek is
	 * {@code null} or empty
	 */
	// TODO: Test - when does a week start? SUN or recurrence start date?
	public static WeeklyRecurringScheduleBuilder create(
			final DateRange eventDateRange, final DateRange recurrenceDateRange,
			final int nthWeek, final EnumSet<DayOfWeek> daysOfWeek) {
		if (nthWeek == 0) {
			throw new IllegalArgumentException(
					"nth week must be greater than zero");
		}
		if (daysOfWeek == null || daysOfWeek.isEmpty()) {
			throw new IllegalArgumentException(
					"Days of week on which the event is to be scheduled must "
							+ "be specified");
		}
		return new WeeklyRecurringScheduleBuilder(
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
				int year = current.getYear();
				int weekOfYear = current.getWeekOfYear();
				int week = 1;
				while (current.isLessThanOrEqualTo(end.getDate())) {
					if (current.getWeekOfYear() == (weekOfYear + 1)
							|| (current.getYear() == (year + 1)
								&& current.isActualFirstWeekOfYear())) {
						year = current.getYear();
						weekOfYear = current.getWeekOfYear();
						week++;
					}
					if (week % nthWeek == 0) {
						if (daysOfWeek.contains(current.getDayOfWeek())) {
							T event = instanceFactory.createInstance();
							event.setDateRange(getDateRangeForDateAtTimes(
									current.getDate(), eventDateRange));
							results.add(event);
						}
					}
					current.changeDate(1);
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
				int year = current.getYear();
				int weekOfYear = current.getWeekOfYear();
				int week = 1;
				int occurrenceCount = 0;
				while (occurrenceCount < occurrences) {
					if (current.getWeekOfYear() == (weekOfYear + 1)
							|| (current.getYear() == (year + 1)
								&& current.isActualFirstWeekOfYear())) {
						year = current.getYear();
						weekOfYear = current.getWeekOfYear();
						week++;
					}
					if (week % nthWeek == 0) {
						if (daysOfWeek.contains(current.getDayOfWeek())) {
							occurrenceCount++;
						}
					}
					if (occurrenceCount < occurrences) {
						current.changeDate(1);
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