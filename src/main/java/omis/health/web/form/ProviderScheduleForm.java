package omis.health.web.form;

import java.util.Date;
import java.util.List;

/** Form for provider daily schedule.
 * @author Ryan Johns
 * @version 0.1.0 (May 30, 2014)
 * @since OMIS 3.0 */
public class ProviderScheduleForm {
	private Date sundayStartTime;
	private Date sundayEndTime;
	private Date mondayStartTime;
	private Date mondayEndTime;
	private Date tuesdayStartTime;
	private Date tuesdayEndTime;
	private Date wednesdayStartTime;
	private Date wednesdayEndTime;
	private Date thursdayStartTime;
	private Date thursdayEndTime;
	private Date fridayStartTime;
	private Date fridayEndTime;
	private Date saturdayStartTime;
	private Date saturdayEndTime;
	private List<IrregularScheduleDayItemForm> irregularScheduleDays;

	/** Constructor. */
	public ProviderScheduleForm() { }

	/** Gets Sunday start time.
	 * @return Sunday start time. */
	public Date getSundayStartTime() { return this.sundayStartTime; }

	/** Gets Sunday end time.
	 * @return Sunday end time. */
	public Date getSundayEndTime() { return this.sundayEndTime; }

	/** Gets Monday start time.
	 * @return Monday start time. */
	public Date getMondayStartTime() { return this.mondayStartTime; }

	/** Gets Monday end time.
	 * @return Monday end time. */
	public Date getMondayEndTime() { return this.mondayEndTime; }

	/** Gets Tuesday start time.
	 * @return Tuesday start time. */
	public Date getTuesdayStartTime() { return this.tuesdayStartTime; }

	/** Gets Tuesday end time.
	 * @return Tuesday end time. */
	public Date getTuesdayEndTime() { return this.tuesdayEndTime; }

	/** Gets Wednesday start time.
	 * @return Wednesday start time. */
	public Date getWednesdayStartTime() { return this.wednesdayStartTime; }

	/** Gets Wednesday end time.
	 * @return Wednesday end time. */
	public Date getWednesdayEndTime() { return this.wednesdayEndTime; }

	/** Gets Thursday start time.
	 * @return Thursday start time. */
	public Date getThursdayStartTime() { return this.thursdayStartTime; }

	/** Gets Thursday end time.
	 * @return Thursday end time. */
	public Date getThursdayEndTime() { return this.thursdayEndTime; }

	/** Gets Friday start time.
	 * @return Friday start time. */
	public Date getFridayStartTime() { return this.fridayStartTime; }

	/** Gets Friday end time.
	 * @return Friday end time. */
	public Date getFridayEndTime() { return this.fridayEndTime; }

	/** Gets Saturday start time.
	 * @return Saturday star time. */
	public Date getSaturdayStartTime() { return this.saturdayStartTime; }

	/** Gets Saturday end time.
	 * @return Saturday end time. */
	public Date getSaturdayEndTime() { return this.saturdayEndTime; }

	/** Gets irregular schedule days.
	 * @return irregularScheduleDays. */
	public List<IrregularScheduleDayItemForm> getIrregularScheduleDays() {
		return this.irregularScheduleDays;
	}

	/** Sets irregular schedule days.
	 * @param irregularScheduleDays irregular schedule days. */
	public void setIrregularScheduleDays(
			final List<IrregularScheduleDayItemForm> irregularScheduleDays) {
		this.irregularScheduleDays = irregularScheduleDays;
	}

	/** Sets Saturday end time.
	 * @param saturdayEndTime Saturday end time. */
	public void setSaturdayEndTime(final Date saturdayEndTime) {
		this.saturdayEndTime = saturdayEndTime;
	}

	/** Sets Saturday start time.
	 * @param saturdayStartTime Saturday start time. */
	public void setSaturdayStartTime(final Date saturdayStartTime) {
		this.saturdayStartTime = saturdayStartTime;
	}

	/** Sets Friday end time.
	 * @param fridayEndTime Friday end time. */
	public void setFridayEndTime(final Date fridayEndTime) {
		this.fridayEndTime = fridayEndTime;
	}

	/** Sets Friday start time.
	 * @param fridayStartTime Friday start time. */
	public void setFridayStartTime(final Date  fridayStartTime) {
		this.fridayStartTime = fridayStartTime;
	}

	/** Sets Thursday end time.
	 * @param thursdayEndTime Thursday end time. */
	public void setThursdayEndTime(final Date thursdayEndTime) {
		this.thursdayEndTime = thursdayEndTime;
	}

	/** Sets Thursday start time.
	 * @param thursdayStartTime Thursday start time. */
	public void setThursdayStartTime(final Date thursdayStartTime) {
		this.thursdayStartTime = thursdayStartTime;
	}

	/** Sets Wednesday end time.
	 * @param wednesdayEndTime Wednesday end time. */
	public void setWednesdayEndTime(final Date wednesdayEndTime) {
		this.wednesdayEndTime = wednesdayEndTime;
	}

	/** Sets Wednesday start time.
	 * @param wednesdayStartTime Wednesday start time. */
	public void setWednesdayStartTime(final Date wednesdayStartTime) {
		this.wednesdayStartTime = wednesdayStartTime;
	}

	/** Sets Tuesday end time.
	 * @param tuesdayEndTime Tuesday end time. */
	public void setTuesdayEndTime(final Date tuesdayEndTime) {
		this.tuesdayEndTime = tuesdayEndTime;
	}

	/** Sets Tuesday start time.
	 * @param tuesdayStartTime Tuesday start time. */
	public void setTuesdayStartTime(final Date tuesdayStartTime) {
		this.tuesdayStartTime = tuesdayStartTime;
	}

	/** Sets Monday end time.
	 * @param mondayEndTime Monday end time. */
	public void setMondayEndTime(final Date mondayEndTime) {
		this.mondayEndTime = mondayEndTime;
	}

	/** Sets Monday start time.
	 * @param mondayStartTime Monday start time. */
	public void setMondayStartTime(final Date mondayStartTime) {
		this.mondayStartTime = mondayStartTime;
	}

	/** Sets Sunday end time.
	 * @param sundayEndTime Sunday end time. */
	public void setSundayEndTime(final Date sundayEndTime) {
		this.sundayEndTime = sundayEndTime;
	}

	/** Sets Sunday start time.
	 * @param sundayStartTime time Sunday start time. */
	public void setSundayStartTime(final Date sundayStartTime) {
		this.sundayStartTime = sundayStartTime;
	}

}
