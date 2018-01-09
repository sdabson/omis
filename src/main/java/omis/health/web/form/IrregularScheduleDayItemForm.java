package omis.health.web.form;

import java.util.Date;

import omis.datatype.DateRange;
import omis.datatype.EntityPersistenceState;
import omis.health.domain.IrregularScheduleDay;

/** Irregular Schedule day form.
 * @author Ryan Johns
 * @version 0.1.0 (Jun 3, 2014)
 * @since OMIS 3.0 */
public class IrregularScheduleDayItemForm {
	private IrregularScheduleDay irregularScheduleDayItem;
	private Date startTime;
	private Date endTime;
	private Date day;
	private EntityPersistenceState entityPersistenceState;

	/** Constructor. */
	public IrregularScheduleDayItemForm() { }


	/** Gets irregular schedule day item.
	 * @return irregular schedule day item. */
	public IrregularScheduleDay getIrregularScheduleDayItem() {
		return this.irregularScheduleDayItem;
	}

	/** Gets start time.
	 * @return start time. */
	public Date getStartTime() { return this.startTime; }

	/** Gets end time.
	 * @return end time. */
	public Date getEndTime() { return this.endTime; }

	/** Gets day.
	 * @return day day. */
	public Date getDay() { return this.day; }

	/** Gets is removed.
	 * @return persistence state. */
	public boolean getIsRemoved() {
		return (this.checkPersistenceState() == EntityPersistenceState.REMOVED);
	}


	/** Sets start time.
	 * @param startTime start time. */
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	/** Sets end time.
	 * @param endTime end time. */
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	/** Sets day.
	 * @param day day. */
	public void setDay(final Date day) {
		this.day = day;
	}

	/** Sets irregular schedule day item.
	 * @param irregularScheduleDayItem irregular schedule day item. */
	public void setIrregularScheduleDayItem(
			final IrregularScheduleDay irregularScheduleDayItem) {
		this.irregularScheduleDayItem = irregularScheduleDayItem;
	}

	/** Checks persistent state.
	 * @return persistentState. */
	public EntityPersistenceState checkPersistenceState() {
		if (this.entityPersistenceState == null) {
			if (this.irregularScheduleDayItem != null
					|| this.getDay() != null || this.getEndTime() != null
					|| this.getStartTime() != null) {
				if (this.irregularScheduleDayItem != null
						&& (this.getDay() == null &&
						this.getStartTime() == null &&
						this.getEndTime() == null)) {
					this.entityPersistenceState =
							EntityPersistenceState.REMOVED;
				} else if (this.irregularScheduleDayItem != null
						&& this.getDay().equals(
						this.getIrregularScheduleDayItem().getDay())
						&& this.startTimeEndTimeTimeRangeEquals(
								this.getStartTime(), this.getEndTime(),
								this.getIrregularScheduleDayItem()
								.getTimeRange())) {
					this.entityPersistenceState =
							EntityPersistenceState.UNCHANGED;
				} else if (this.irregularScheduleDayItem == null) {
					this.entityPersistenceState =
							EntityPersistenceState.NEW;
				}else {
					this.entityPersistenceState =
							EntityPersistenceState.CHANGED;
				}
			}
		}
		return this.entityPersistenceState;
	}

	private boolean startTimeEndTimeTimeRangeEquals(final Date startTime, final Date endTime,
			final DateRange dateRange) {
		final boolean dateRangeStartNull = (dateRange == null ||
				dateRange.getStartDate() == null);
		final boolean dateRangeEndNull = (dateRange == null ||
				dateRange.getEndDate() == null);

		boolean result = true;

		if (startTime != null && !dateRangeStartNull) {
			if (!startTime.equals(dateRange.getStartDate())) {
				result = false;
			}
		} else if ((startTime != null && dateRangeStartNull) ||
				(startTime == null && !dateRangeStartNull)) {
			result = false;
		}

		if (endTime != null && !dateRangeEndNull) {
			if (!endTime.equals(dateRange.getEndDate())) {
				return false;
			}
		} else if ((endTime != null && dateRangeEndNull) ||
				(endTime == null && !dateRangeEndNull)) {
			result = false;
		}

		return result;
	}
}
