package omis.violationevent.domain.component;

import java.io.Serializable;
import java.util.Date;

/**
 * Event.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 17, 2017)
 *@since OMIS 3.0
 *
 */
public class Event implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Date date;
	
	private String details;
	
	
	/**
	 * Default Constructor for Event 
	 */
	public Event() {
	}


	/**
	 * Constructor for Event 
	 * @param date - Date
	 * @param details - String
	 */
	public Event(final Date date, final String details) {
		this.date = date;
		this.details = details;
	}


	/**
	 * Returns the date of the event
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * Sets the date of the event
	 * @param date the date to set
	 */
	public void setDate(final Date date) {
		this.date = date;
	}


	/**
	 * Returns the details of the event
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}


	/**
	 * Sets the details of the event
	 * @param details the details to set
	 */
	public void setDetails(final String details) {
		this.details = details;
	}
}
