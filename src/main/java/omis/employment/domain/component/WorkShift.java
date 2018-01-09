package omis.employment.domain.component;

import java.io.Serializable;
import java.util.Date;

import omis.employment.domain.WorkShiftFrequency;
import omis.employment.domain.component.WorkShiftDays;

/**
 * Work shift
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (Feb 2, 2015)
 * @since: OMIS 3.0
 */
public class WorkShift implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date scheduleStartTime;
	private Date scheduleEndTime;
	private Boolean timesMayVary;
	private WorkShiftDays workShiftDays;
	private WorkShiftFrequency workShiftFrequency;

	/** Instantiates a work shift. */
	public WorkShift() {
		// Do nothing
	}
	
	/**
	 * Returns the schedule start time.
	 * 
	 * @return schedule start time
	 */
	public Date getScheduleStartTime(){
		return this.scheduleStartTime;
	}

	/**
	 * Sets the schedule start time.
	 * @param  scheduleStartTime schedule start time 
	 */
	public void setScheduleStartTime(final Date scheduleStartTime){
		this.scheduleStartTime = scheduleStartTime;
	}
	
	/**
	 * Returns the schedule end time.
	 * 
	 * @return schedule end time
	 */
	public Date getScheduleEndTime(){
		return this.scheduleEndTime;
	}

	/**
	 * Sets the schedule end time.
	 * @param  scheduleEndTime schedule end time 
	 */
	public void setScheduleEndTime(final Date scheduleEndTime){
		this.scheduleEndTime = scheduleEndTime;
	}

	/**
	 * Gets the value of if times may vary or not.
	 * 
	 * @return timesMayVary times may vary
	 */
	public Boolean getTimesMayVary(){
		return this.timesMayVary;
	}
	
	/**
	 * Sets if times may vary or not.
	 * 
	 * @param  timesMayVary times may vary
	 */
	public void setTimesMayVary(final Boolean timesMayVary){
		this.timesMayVary = timesMayVary;
	}
	
	/**
	 * Sets the work shift frequency.
	 * 
	 * @param workShiftFrequency work shift frequency
	 */
	public void setWorkShiftFrequency(final WorkShiftFrequency workShiftFrequency){
		this.workShiftFrequency = workShiftFrequency;
	}
	
	/**
	 * Gets the work shift frequency.
	 * 
	 * @return  workShiftFrequency work shift frequency
	 */
	public WorkShiftFrequency getWorkShiftFrequency( ){
		return this.workShiftFrequency;
	}
	
	/**
	 * Sets which day works or off.
	 * 
	 * @param workShiftDays work shift days
	 */
	public void setWorkShiftDays(final WorkShiftDays workShiftDays){
		this.workShiftDays = workShiftDays;
	}
	
	/**
	 * Gets the info of which day works or off.
	 * 
	 * @return  workShiftDays work shift days
	 */
	public WorkShiftDays getWorkShiftDays( ){
		return this.workShiftDays;
	}
}