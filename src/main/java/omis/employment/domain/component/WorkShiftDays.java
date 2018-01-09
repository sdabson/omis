package omis.employment.domain.component;

import java.io.Serializable;

/**
 * WorkShiftDays
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (Jan 30, 2015)
 * @since: OMIS 3.0
 */
public class WorkShiftDays implements Serializable {
	private static final long serialVersionUID = 1L;
	private Boolean sunday;
	private Boolean monday;
	private Boolean tuesday;
	private Boolean wednesday;
	private Boolean thursday;
	private Boolean friday;
	private Boolean saturday;
	
	/** Instantiates a work shift days. */
	public WorkShiftDays() {
		// Do nothing
	}
	
	public WorkShiftDays(Boolean sunday, Boolean monday, Boolean tuesday,
			Boolean wednesday, Boolean thursday, Boolean friday, 
			Boolean saturday) {
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thursday = thursday;
		this.friday = friday;
		this.saturday = saturday;
		this.sunday = sunday;
	}
	
	/**
	 * Returns if Sunday work or off.
	 * 
	 * @return Sunday sSunday on or off
	 */
	public Boolean getSunday(){
		return this.sunday;
	}
	
	/**
	 * Sets Sunday on or off.
	 * 
	 * @param Sunday Sunday
	 */
	public void setSunday(final Boolean sunday){
		this.sunday = sunday;
	}

	/**
	 * Returns Monday on or off.
	 * 
	 * @return Monday on or off
	 */
	public Boolean getMonday(){
		return this.monday;
	}
	
	/**
	 * Sets Monday on or off.
	 * 
	 * @param Monday Monday
	 */
	public void setMonday(final Boolean monday){
		this.monday = monday;
	}
	
	/**
	 * Returns Tuesday on or off.
	 * 
	 * @return Tuesday on or off
	 */
	public Boolean getTuesday(){
		return this.tuesday;
	}
	
	/**
	 * Sets Tuesday on or off.
	 * 
	 * @param Tuesday Tuesday
	 */
	public void setTuesday(final Boolean tuesday){
		this.tuesday = tuesday;
	}
	
	/**
	 * Returns Wednesday on or off.
	 * 
	 * @return Wednesday
	 */
	public Boolean getWednesday(){
		return this.wednesday;
	}
	
	/**
	 * Sets Wednesday on or off.
	 * 
	 * @param Wednesday Wednesday
	 */
	public void setWednesday(final Boolean wednesday){
		this.wednesday = wednesday;
	}
	
	/**
	 * Returns Thursday on or off.
	 * 
	 * @return Thursday
	 */
	public Boolean getThursday(){
		return this.thursday;
	}
	
	/**
	 * Sets Thursday on or off.
	 * 
	 * @param Thursday
	 */
	public void setThursday(final Boolean thursday){
		this.thursday = thursday;
	}
	
	/**
	 * Returns Friday on or off.
	 * 
	 * @return Friday
	 */
	public Boolean getFriday(){
		return this.friday;
	}
	
	/**
	 * Sets Friday on or off.
	 * 
	 * @param Friday
	 */
	public void setFriday(final Boolean friday){
		this.friday = friday;
	}
	
	/**
	 * Returns Saturday on or off.
	 * 
	 * @return Saturday
	 */
	public Boolean getSaturday(){
		return this.saturday;
	}
	
	/**
	 * Sets Saturday on or off.
	 * 
	 * @param Saturday
	 */
	public void setSaturday(final Boolean saturday){
		this.saturday = saturday;
	}
}