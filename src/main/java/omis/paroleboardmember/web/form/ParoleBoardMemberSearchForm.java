package omis.paroleboardmember.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * Parole board member search form.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 14, 2017)
 * @since OMIS 3.0
 */
public class ParoleBoardMemberSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;


	private Boolean singleDate;
	
	private Date date;
	
	private Date startDate;
	
	private Date endDate;

	/** Instantiates a default instance of parole board member search form. */
	public ParoleBoardMemberSearchForm() {
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
