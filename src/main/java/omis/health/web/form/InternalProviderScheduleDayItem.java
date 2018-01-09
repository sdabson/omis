package omis.health.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * Internal provider schedule day item.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 18, 2014)
 * @since OMIS 3.0
 */
public class InternalProviderScheduleDayItem
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date date;
	
	private Date startTime;
	
	private Date endTime;
	
	private Integer scheduledApppointments;
	
	/** Instantiates an internal provider schedule day item. */
	public InternalProviderScheduleDayItem() {
		// Default instantiation
	}

	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the start time.
	 * 
	 * @return start time
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	/**
	 * Sets the start time.
	 * 
	 * @param startTime start time
	 */
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * Returns the end time.
	 * 
	 * @return end time
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * Sets the end time.
	 * 
	 * @param endTime end time
	 */
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * Returns the number of scheduled appointments.
	 * 
	 * @return number of scheduled appointments
	 */
	public Integer getScheduledApppointments() {
		return this.scheduledApppointments;
	}

	/**
	 * Sets the number of scheduled appointments.
	 * 
	 * @param scheduledApppointments number of scheduled appointments
	 */
	public void setScheduledApppointments(
			final Integer scheduledApppointments) {
		this.scheduledApppointments = scheduledApppointments;
	}
}