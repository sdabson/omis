package omis.health.service.delegate;

import java.util.Calendar;
import java.util.Date;

import omis.datatype.DateRange;
import omis.health.dao.IrregularScheduleDayDao;
import omis.health.dao.ProviderScheduleDao;
import omis.health.domain.IrregularScheduleDay;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderSchedule;

/** Provider schedule delegate.
 * @author Ryan Johns
 * @version 0.1.0 (Jul 15, 2014)
 * @since OMIS 3.0 */
public class ProviderScheduleChecker {
	final private ProviderScheduleDao providerScheduleDao;
	final private IrregularScheduleDayDao irregularScheduleDayDao;

	/** Constructor.
	 * @param providerScheduleDao provider schedule dao.
	 * @param irregularScheduleDayDao irregular schedule day dao. */
	public ProviderScheduleChecker(
			final ProviderScheduleDao providerScheduleDao,
			final IrregularScheduleDayDao irregularScheduleDayDao) {
		this.providerScheduleDao = providerScheduleDao;
		this.irregularScheduleDayDao = irregularScheduleDayDao;
	}

	/** Checks whether provider is scheduled.
	 * @param providerAssignment provider assignment to evaluate service.
	 * @param date date to check against. */
	public boolean isProviderScheduled(
			final ProviderAssignment providerAssignment, final Date date) {
		final ProviderSchedule providerSchedule =
				this.providerScheduleDao.findByAssignment(providerAssignment);

	final Calendar calendar = Calendar.getInstance();
	calendar.setTime(date);
	final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	DateRange dayOfWeekDateRange;
	final IrregularScheduleDay irregularScheduleDay =
			this.irregularScheduleDayDao.find(providerAssignment, date);

	if (irregularScheduleDay != null) {
		if (irregularScheduleDay.getTimeRange() != null) {
			if (irregularScheduleDay.getTimeRange().isActive(date)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	if (dayOfWeek == Calendar.MONDAY) {
		dayOfWeekDateRange = providerSchedule.getMondayTimeRange();
	} else if (dayOfWeek == Calendar.TUESDAY) {
		dayOfWeekDateRange = providerSchedule.getTuesdayTimeRange();
	} else if (dayOfWeek == Calendar.WEDNESDAY) {
		dayOfWeekDateRange = providerSchedule.getWednesdayTimeRange();
	} else if (dayOfWeek == Calendar.THURSDAY) {
		dayOfWeekDateRange = providerSchedule.getThursdayTimeRange();
	} else if (dayOfWeek == Calendar.FRIDAY) {
		dayOfWeekDateRange = providerSchedule.getFridayTimeRange();
	} else if (dayOfWeek == Calendar.SATURDAY) {
		dayOfWeekDateRange = providerSchedule.getSaturdayTimeRange();
	} else {
		dayOfWeekDateRange = providerSchedule.getSundayTimeRange();
	}

	if (dayOfWeekDateRange != null) {
		return true;
	} else {
		return false;
	}

	}
}
