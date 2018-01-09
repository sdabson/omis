package omis.health.service;

import java.util.Date;
import java.util.List;

import omis.datatype.DateRange;
import omis.datatype.DayOfWeek;
import omis.exception.DateConflictException;
import omis.health.domain.IrregularScheduleDay;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderSchedule;
import omis.health.web.form.IrregularScheduleDayItemForm;

/**
 * Manages provider schedules.
 *
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.1 (Jun 5, 2014)
 * @since OMIS 3.0
 */
public interface ProviderScheduleManager {

	/**
	 * Schedules regular provider schedule on day of week.
	 *
	 * <p>
	 * If a schedule for the provider does not exist, a new one will be created.
	 * Otherwise, the existing provider schedule will be updates.
	 *
	 * @param providerAssignment
	 *            provider assignment for which to schedule
	 * @param dayOfWeek
	 *            day of week
	 * @param timeRange
	 *            time range to schedule for provider on day of week
	 */
	void schedule(ProviderAssignment providerAssignment, DayOfWeek dayOfWeek,
			DateRange timeRange);

	/**
	 * Schedules an irregular day for the provider.
	 *
	 * @param providerAssignment
	 *            provider assignment
	 * @param day
	 *            day
	 * @param timeRange
	 *            time range
	 */
	void scheduleIrregularDay(ProviderAssignment providerAssignment, Date day,
			DateRange timeRange);

	/**
	 * Removes the daily schedule for the provider during the assignment.
	 *
	 * <p>
	 * If the provider does not have a daily schedule for the day during the
	 * assignment, this method does nothing.
	 *
	 * @param assignment
	 *            assignment
	 * @param dayOfWeek
	 *            day of week
	 */
	void removeDailySchedule(ProviderAssignment assignment, DayOfWeek dayOfWeek);

	/**
	 * Removes entire schedule for provider during assignment.
	 *
	 * <p>
	 * If the provider does not have a schedule during the assignment, this
	 * method does nothing.
	 *
	 * @param assignment
	 *            assignment
	 */
	void removeSchedule(ProviderAssignment assignment);

	/**
	 * Removes the irregular schedule day for the provider during the
	 * assignment.
	 *
	 * <p>
	 * If the day is not an irregular schedule day for the provider during the
	 * assignment, this method does nothing.
	 *
	 * @param assignment
	 *            assignment
	 * @param day
	 *            day
	 */
	void removeIrregularScheduleDay(ProviderAssignment assignment, Date day);

	/**
	 * Returns provider regular weekly schedule for the provider.
	 *
	 * @param providerAssignment
	 *            provider assignment.
	 * @return provider regular schedule.
	 */
	ProviderSchedule findRegularScheduleByProvider(
			ProviderAssignment providerAssignment);

	/**
	 * Returns the range of scheduled times for the provider during the
	 * assignment on the day.
	 *
	 * <p>
	 * If the provider has irregularly scheduled times on the day, this time
	 * range will be returned. If the offender has a regular schedule on this
	 * day and no irregularly scheduled times, this time range will be returned.
	 *
	 * <p>
	 * If the providers does not have scheduled times during the assignment on
	 * the day, this method returns {@code null}.
	 *
	 * @param assignment
	 *            assignment
	 * @param day
	 *            day
	 * @return schedule times for provider during assignment on day;
	 *         {@code null} if no such times exist.
	 */
	DateRange findTimeRangeOnDay(ProviderAssignment assignment, Date day);

	/** Returns irregular schedules for date range.
	 * @param providerAssignment provider assignment
	 * @param startDate start date.
	 * @param endDate end date.
	 * @return irregular schedule days. */
	List<IrregularScheduleDay> findIrregularScheduleDaysByProviderAndDateRange(
			ProviderAssignment providerAssignment,
			Date startDate, Date endDate);

	/** Saves list of irregular Schedules.
	 * @param providerAssignment provider to schedule.
	 * @param list list of irregular schedule days.
	 * @throws DateConflictException */
	void saveIrregularScheduleDayItems(
			final ProviderAssignment providerAssignment,
			final List<IrregularScheduleDayItemForm> list)
					throws DateConflictException;
}