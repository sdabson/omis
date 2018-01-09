package omis.probationterm.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * Form for probation terms.
 * 
 * @author Josh Divine
 * @author Stephen Abson
 * @version 0.0.2 (Nov 21, 2017)
 * @since OMIS 3.0
 */
public class ProbationTermForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date startDate;
	
	private Date terminationDate;
	
	private Integer years;
	
	private Integer months;
	
	private Integer days;
	
	private Integer totalDays;
	
	private Date expirationDate;
	
	private Integer jailCredit;
	
	private Integer sentenceDays;
	
	/**
	 * Instantiates a default probation term form. 
	 */
	public ProbationTermForm() {
		// Default instantiation
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
	 * Returns the termination date.
	 * 
	 * @return termination date
	 */
	public Date getTerminationDate() {
		return this.terminationDate;
	}

	/**
	 * Sets the termination date.
	 * 
	 * @param terminationDate termination date
	 */
	public void setTerminationDate(final Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	/**
	 * Returns the years.
	 * 
	 * @return years
	 */
	public Integer getYears() {
		return this.years;
	}

	/**
	 * Sets the years
	 * 
	 * @param years years
	 */
	public void setYears(final Integer years) {
		this.years = years;
	}

	/**
	 * Returns the months.
	 * 
	 * @return months
	 */
	public Integer getMonths() {
		return this.months;
	}

	/**
	 * Sets the months.
	 * 
	 * @param months months
	 */
	public void setMonths(final Integer months) {
		this.months = months;
	}

	/**
	 * Returns the days.
	 * 
	 * @return days
	 */
	public Integer getDays() {
		return this.days;
	}

	/**
	 * Sets the days.
	 * 
	 * @param days days
	 */
	public void setDays(final Integer days) {
		this.days = days;
	}

	/**
	 * Returns the total days.
	 * 
	 * @return total days
	 */
	public Integer getTotalDays() {
		return this.totalDays;
	}

	/**
	 * Sets the total days.
	 * 
	 * @param totalDays total days
	 */
	public void setTotalDays(final Integer totalDays) {
		this.totalDays = totalDays;
	}

	/**
	 * Returns the expiration date.
	 * 
	 * @return expiration date
	 */
	public Date getExpirationDate() {
		return this.expirationDate;
	}

	/**
	 * Sets the expiration date.
	 * 
	 * @param expirationDate expiration date
	 */
	public void setExpirationDate(final Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Returns the jail time credit.
	 * 
	 * @return jail credit
	 */
	public Integer getJailCredit() {
		return this.jailCredit;
	}

	/**
	 * Sets the jail time credit.
	 * 
	 * @param jailCredit jail credit
	 */
	public void setJailCredit(final Integer jailCredit) {
		this.jailCredit = jailCredit;
	}

	/**
	 * Returns the sentence days.
	 * 
	 * @return sentence days
	 */
	public Integer getSentenceDays() {
		return this.sentenceDays;
	}

	/**
	 * Sets the sentence days.
	 * 
	 * @param sentenceDays sentence days
	 */
	public void setSentenceDays(final Integer sentenceDays) {
		this.sentenceDays = sentenceDays;
	}
}