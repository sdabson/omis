package omis.health.service.impl;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import omis.datatype.DateRange;
import omis.datatype.DayOfWeek;
import omis.datatype.EntityPersistenceState;
import omis.exception.DateConflictException;
import omis.health.dao.IrregularScheduleDayDao;
import omis.health.dao.ProviderScheduleDao;
import omis.health.domain.IrregularScheduleDay;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderSchedule;
import omis.health.service.ProviderScheduleManager;
import omis.health.web.form.IrregularScheduleDayItemForm;
import omis.instance.factory.InstanceFactory;

/**
 * Provider Schedule Manager Implementation.
 *
 * @author Joel Norris
 * @author Ryan Johns
 * @version 0.1.1 (Jun 8, 2014)
 * @since OMIS 3.0
 */
public class ProviderScheduleManagerImpl  implements ProviderScheduleManager {
	final private static String IRREGULAR_SCHEDULE_DAY_CONFLICT_KEY =
			"irregularScheduleDay.day.confilct";

	/* Data Access Objects */

	final private ProviderScheduleDao providerScheduleDao;

	final private IrregularScheduleDayDao irregularScheduleDayDao;

	/* Instance factories */

	final private InstanceFactory<ProviderSchedule>
	providerScheduleInstanceFactory;

	final private InstanceFactory<IrregularScheduleDay>
	irregularScheduleDayInstanceFactory;

	/** Constructor.
	 * @param providerScheduleDao provider schedule dao.
	 * @param irregularScheduleDayDao irregular schedule day dao.
	 * @param providerScheduleInstanceFactory instance factory.
	 * @param irregularScheduleDayInstanceFactory instance factory. */
	public ProviderScheduleManagerImpl(
			final ProviderScheduleDao providerScheduleDao,
			final IrregularScheduleDayDao irregularScheduleDayDao,
			final InstanceFactory<ProviderSchedule>
				providerScheduleInstanceFactory,
			final InstanceFactory<IrregularScheduleDay>
				irregularScheduleDayInstanceFactory) {
		this.providerScheduleDao = providerScheduleDao;
		this.irregularScheduleDayDao = irregularScheduleDayDao;
		this.providerScheduleInstanceFactory = providerScheduleInstanceFactory;
		this.irregularScheduleDayInstanceFactory =
				irregularScheduleDayInstanceFactory;

	}

	/** {@inheritDoc} */
	@Override
	public void schedule(final ProviderAssignment providerAssignment,
			final DayOfWeek dayOfWeek, final DateRange timeRange) {
		ProviderSchedule schedule = this.providerScheduleDao
				.findByAssignment(providerAssignment);
		if (schedule == null) {
			schedule = this.providerScheduleInstanceFactory.createInstance();
			schedule.setProviderAssignment(providerAssignment);
		}
		if (dayOfWeek.equals(DayOfWeek.SUNDAY)) {
			schedule.setSundayTimeRange(timeRange);
		}
		if (dayOfWeek.equals(DayOfWeek.MONDAY)) {
			schedule.setMondayTimeRange(timeRange);
		}
		if (dayOfWeek.equals(DayOfWeek.TUESDAY)) {
			schedule.setTuesdayTimeRange(timeRange);
		}
		if (dayOfWeek.equals(DayOfWeek.WEDNESDAY)) {
			schedule.setWednesdayTimeRange(timeRange);
		}
		if (dayOfWeek.equals(DayOfWeek.THURSDAY)) {
			schedule.setThursdayTimeRange(timeRange);
		}
		if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
			schedule.setFridayTimeRange(timeRange);
		}
		if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
			schedule.setSaturdayTimeRange(timeRange);
		}
		this.providerScheduleDao.makePersistent(schedule);
	}

	/** {@inheritDoc} */
	@Override
	public void scheduleIrregularDay(
			final ProviderAssignment providerAssignment,
			final Date day, final DateRange timeRange) {
		final IrregularScheduleDay irregularScheduleDay = this
				.irregularScheduleDayInstanceFactory.createInstance();
		irregularScheduleDay.setProviderAssignment(providerAssignment);
		irregularScheduleDay.setDay(day);
		irregularScheduleDay.setTimeRange(timeRange);
		this.irregularScheduleDayDao.makePersistent(irregularScheduleDay);
	}

	/** {@inheritDoc} */
	@Override
	public void removeDailySchedule(final ProviderAssignment assignment,
			final DayOfWeek dayOfWeek) {
		final ProviderSchedule schedule = this.providerScheduleDao
				.findByAssignment(assignment);
		if (schedule != null) {
			if (dayOfWeek.equals(DayOfWeek.SUNDAY)) {
				schedule.setSundayTimeRange(null);
			}
			if (dayOfWeek.equals(DayOfWeek.MONDAY)) {
				schedule.setMondayTimeRange(null);
			}
			if (dayOfWeek.equals(DayOfWeek.TUESDAY)) {
				schedule.setTuesdayTimeRange(null);
			}
			if (dayOfWeek.equals(DayOfWeek.WEDNESDAY)) {
				schedule.setWednesdayTimeRange(null);
			}
			if (dayOfWeek.equals(DayOfWeek.THURSDAY)) {
				schedule.setThursdayTimeRange(null);
			}
			if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
				schedule.setFridayTimeRange(null);
			}
			if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
				schedule.setSaturdayTimeRange(null);
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public void removeSchedule(final ProviderAssignment assignment) {
		final ProviderSchedule schedule = this.providerScheduleDao.findByAssignment(
				assignment);
		if (schedule != null) {
			this.providerScheduleDao.makeTransient(schedule);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void removeIrregularScheduleDay(
			final ProviderAssignment assignment, final Date day) {
		final IrregularScheduleDay irregularScheduleDay =
				this.irregularScheduleDayDao.find(assignment, day);
		if (irregularScheduleDay != null) {
			this.irregularScheduleDayDao.makeTransient(irregularScheduleDay);
		}
	}

	/** {@inheritDoc} */
	@Override
	public ProviderSchedule findRegularScheduleByProvider(
			final ProviderAssignment providerAssignment) {
		return this.providerScheduleDao.findByAssignment(providerAssignment);
	}

	/** {@inheritDoc} */
	@Override
	public DateRange findTimeRangeOnDay(final ProviderAssignment assignment,
			final Date day) {
		final GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(day);
		final IrregularScheduleDay irregularScheduleDay =
				this.irregularScheduleDayDao.find(assignment, day);
		if (irregularScheduleDay != null) {
			return irregularScheduleDay.getTimeRange();
		} else {
			final ProviderSchedule schedule = this.providerScheduleDao
					.findByAssignment(assignment);
			if (schedule != null) {
				final int calDayOfWeek = calendar.get(GregorianCalendar.DAY_OF_WEEK);
				if (calDayOfWeek == GregorianCalendar.SUNDAY) {
					return schedule.getSundayTimeRange();
				}
				if (calDayOfWeek == GregorianCalendar.MONDAY) {
					return schedule.getMondayTimeRange();
				}
				if (calDayOfWeek == GregorianCalendar.TUESDAY) {
					return schedule.getTuesdayTimeRange();
				}
				if (calDayOfWeek == GregorianCalendar.WEDNESDAY) {
					return schedule.getWednesdayTimeRange();
				}
				if (calDayOfWeek == GregorianCalendar.THURSDAY) {
					return schedule.getThursdayTimeRange();
				}
				if (calDayOfWeek == GregorianCalendar.FRIDAY) {
					return schedule.getFridayTimeRange();
				}
				if (calDayOfWeek == GregorianCalendar.SATURDAY) {
					return schedule.getSaturdayTimeRange();
				}
			}
		}
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public List<IrregularScheduleDay>
	findIrregularScheduleDaysByProviderAndDateRange(
				final ProviderAssignment providerAssignment,
				final Date startDate, final Date endDate) {
		return this.irregularScheduleDayDao.findByProviderAndDateRange(
				providerAssignment, startDate, endDate);
	}

	@Override
	public void saveIrregularScheduleDayItems(
			final ProviderAssignment providerAssignment,
			final List<IrregularScheduleDayItemForm> list)
					throws DateConflictException {
		if (list != null) {
			for (final IrregularScheduleDayItemForm form: list) {
				if (form.checkPersistenceState() ==
						EntityPersistenceState.NEW) {
					if (this.irregularScheduleDayDao.find(providerAssignment,
							form.getDay()) != null) {
						throw new DateConflictException(
								IRREGULAR_SCHEDULE_DAY_CONFLICT_KEY);
					}
					this.scheduleIrregularDay(
							providerAssignment,
							form.getDay(),
							new DateRange(form.getStartTime(),
									form.getEndTime()));
				} else if (form.checkPersistenceState() ==
						EntityPersistenceState.REMOVED) {
					this.irregularScheduleDayDao.makeTransient(
							form.getIrregularScheduleDayItem());
				} else if (form.checkPersistenceState() ==
						EntityPersistenceState.CHANGED) {
					final IrregularScheduleDay that = this.irregularScheduleDayDao.find(providerAssignment,
							form.getDay());
					if ( that != null && !that.equals(
							form.getIrregularScheduleDayItem())) {
						throw new DateConflictException(
								IRREGULAR_SCHEDULE_DAY_CONFLICT_KEY);
					}

					form.getIrregularScheduleDayItem().setDay(form.getDay());
					if (form.getIrregularScheduleDayItem().getTimeRange()
							== null) {
						form.getIrregularScheduleDayItem()
						.setTimeRange(new DateRange());
					}
					form.getIrregularScheduleDayItem().getTimeRange()
					.setStartDate(form.getStartTime());
					form.getIrregularScheduleDayItem().getTimeRange()
					.setEndDate(form.getEndTime());
				}
			}
		}
	}
}