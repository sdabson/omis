package omis.visitation.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * Visitor Log Dates Form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 2, 2013)
 * @since OMIS 3.0
 */
public class VisitorLogForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Boolean singleDate;
	
	private Date date;
	
	private Date startDate;
	
	private Date endDate;

	/** Instantiates a default instance of change visitor log dates form. */
	public VisitorLogForm() {
		//Default constructor.
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
	 * Return the single date value of the change visitor log dates form.
	 * @return single date
	 */
	public Boolean getSingleDate() {
		return this.singleDate;
	}

	/**
	 * Sets the single date value of the change visitor log dates form.
	 * @param singleDate single date
	 */
	public void setSingleDate(final Boolean singleDate) {
		this.singleDate = singleDate;
	}

	/**
	 * Returns the start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start date.
	 * 
	 * @param startDate start date
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date.
	 * 
	 * @param endDate end date
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
}