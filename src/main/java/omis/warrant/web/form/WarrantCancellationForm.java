package omis.warrant.web.form;

import java.util.Date;

import omis.person.domain.Person;

/**
 * WarrantCancellationForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantCancellationForm {
	
	private Date date;
	
	private String comment;
	
	private Person clearedBy;
	
	private Date clearedByDate;
	
	/**
	 * 
	 */
	public WarrantCancellationForm() {
	}

	/**
	 * Returns the date
	 * @return date - Date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date
	 * @param date - Date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the comment
	 * @return comment - String
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment
	 * @param comment - String
	 */
	public void setComment(final String comment) {
		this.comment = comment;
	}

	/**
	 * Returns the clearedBy
	 * @return clearedBy - Person
	 */
	public Person getClearedBy() {
		return clearedBy;
	}

	/**
	 * Sets the clearedBy
	 * @param clearedBy - Person
	 */
	public void setClearedBy(final Person clearedBy) {
		this.clearedBy = clearedBy;
	}

	/**
	 * Returns the clearedByDate
	 * @return clearedByDate - Date
	 */
	public Date getClearedByDate() {
		return clearedByDate;
	}

	/**
	 * Sets the clearedByDate
	 * @param clearedByDate - Date
	 */
	public void setClearedByDate(final Date clearedByDate) {
		this.clearedByDate = clearedByDate;
	}
	
	
	
}
