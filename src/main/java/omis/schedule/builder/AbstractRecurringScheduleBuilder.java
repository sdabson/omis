package omis.schedule.builder;

import java.util.Date;
import java.util.Set;

import omis.datatype.DateRange;
import omis.instance.factory.InstanceFactory;
import omis.schedule.domain.SchedulableEvent;
import omis.util.DateManipulator;


/**
 * Builder for a recurring schedule.
 * 
 * @author Stephen Abson
 * @version 0.1.11 (July 9, 2013)
 * @since OMIS 3.0
 * @see DailyRecurringScheduleBuilder
 * @see WeeklyRecurringScheduleBuilder
 * @see MonthlyRecurringScheduleBuilder
 */
public abstract class AbstractRecurringScheduleBuilder
		implements ScheduleBuilder {
	
	private final DateRange eventDateRange;
	
	private final DateRange recurrenceDateRange;

	// Implementation to use
	private final AbstractBuilderImpl builderImpl;
	
	/**
	 * Instantiates an abstract recurring schedule builder with the specified
	 * properties.
	 * 
	 * <p>Typically, a builder will not expose a public constructor but will
	 * allow instantiation from a static method. The static method will be
	 * specific to a particular schedule building algorithm (nth day, per day
	 * of week, etc). The builder algorithm will be contained in a composited
	 * builder implementation.
	 * 
	 * @param eventDateRange event date range
	 * @param recurrenceDateRange recurrence date range
	 * @param builderImpl builder implementation
	 * @see AbstractBuilderImpl
	 */
	protected AbstractRecurringScheduleBuilder(
			final DateRange eventDateRange,
			final DateRange recurrenceDateRange,
			final AbstractBuilderImpl builderImpl) {
		this.eventDateRange = eventDateRange;
		this.recurrenceDateRange = recurrenceDateRange;
		this.builderImpl = builderImpl;
	}
	
	/**
	 * Returns <b>the actual</b> event date range.
	 * 
	 * @return <b>actual</b> event date range
	 */
	protected DateRange getEventDateRange() {
		return this.eventDateRange;
	}
	
	/**
	 * Returns <b>the actual</b> recurrence date range.
	 * 
	 * @return <b>actual</b> recurrence date range
	 */
	protected DateRange getRecurrenceDateRange() {
		return this.recurrenceDateRange;
	}
	
	/**
	 * Returns the builder implementation.
	 * 
	 * @return builder implementation
	 */
	protected AbstractBuilderImpl getBuilderImpl() {
		return this.builderImpl;
	}
	
	/** {@inheritDoc} */
	@Override
	public <T extends SchedulableEvent> Set<T> build(
			final InstanceFactory<T> instanceFactory) {
		return this.builderImpl.build(instanceFactory);
	}
	
	/**
	 * Sets the end date of the date range of the event recurrences after
	 * a project number of occurrences.
	 * 
	 * <p>The time of the end date of the current recurrence date range will be
	 * retained if set. Otherwise, no time information will be added to the 
	 * date range.
	 * 
	 * <p>It is <b>not</b> required that this method check for a sensible
	 * maximum number of occurrences. The client should do this before invoking
	 * the method. If a subclass's implementation of this method does check for
	 * a sensible maximum number of occurrences, the subclass itself should
	 * document this behavior. Unless otherwise noted, clients should assume
	 * that this method does not check for a sensible maximum number of
	 * occurrences. The absence of such a check on the part of the client
	 * may result in a recursive loop. 
	 * 
	 * @param occurrences number of projected occurrences to after which to
	 * set the recurrence end date
	 * @throws IllegalArgumentException if zero or less occurrences are
	 * attempted
	 */
	public void setEndAfterOccurrences(final int occurrences) {
		
		// Can mutate _actual_ date range - see Javadoc - [SA]
		this.getRecurrenceDateRange().setEndDate(this.getBuilderImpl()
				.getEndDateAfterOccurrences(occurrences));
	}
	
	/**
	 * Returns the time component of the specified date in milliseconds
	 * or {@code null} if the specified date is {@code null}.
	 * 
	 * @param date date the time component of which to extract
	 * @return time component of specified date in milliseconds
	 */
	protected static Long getTime(final Date date) {
		if (date != null) {
			return date.getTime() % (1000 * 60 * 60 * 24);
		} else {
			return null;
		}
	}
	
	/**
	 * Returns a date range for the given date at the given times.
	 * 
	 * @param date day of the date range
	 * @param timeRange times at which the date range should fall
	 * @return date range for date at times
	 */
	protected static DateRange getDateRangeForDateAtTimes(
			final Date date, final DateRange timeRange) {
		
		// The following is only as safe as how well DateRange sticks to its
		// contract of _not_ allowing startDate > endDate
		DateManipulator manipulator = new DateManipulator(date);
		manipulator.setToTimeOfDate(timeRange.getStartDate());
		Date startDate = manipulator.getDate();
		manipulator.setToTimeOfDate(timeRange.getEndDate());
		return new DateRange(startDate, manipulator.getDate());
	}
	
	/**
	 * Allows composited build functionality between different builder type
	 * instances. The purpose of this class is to encapsulate a schedule
	 * building algorithm.
	 * 
	 * @author Stephen Abson
	 * @version 0.1.1 (Dec 18, 2012)
	 * @since OMIS 3.0
	 */
	protected abstract static class AbstractBuilderImpl {
		
		/**
		 * Perform actual build. 
		 * @param <T> type of event the schedule of which to build
		 * @param instanceFactory instance factory
		 * @return schedule
		 */
		protected abstract <T extends SchedulableEvent> Set<T> build(
				final InstanceFactory<T> instanceFactory);
		
		/**
		 * Returns the end date after the given number of occurrences of
		 * an event according to a particular schedule.
		 * 
		 * @param occurrences number of occurrences from which to calculate
		 * end date
		 * @return end date after
		 * @throws IllegalStateException if zero or less occurrences are
		 * attempted
		 */
		protected abstract Date getEndDateAfterOccurrences(
				final int occurrences);
	}
}