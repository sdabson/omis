package omis.disciplinaryCode.web.form;

import java.util.Date;

/**
 * CodeDateForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 11, 2016)
 *@since OMIS 3.0
 *
 */
public class CodeDateForm {
	
	private Date effectiveDate;
	
	private Date fromDate;
	
	private Date toDate;
	
	private Boolean usingEffectiveDate;
	
	
	/**
	 * Default constructor
	 */
	public CodeDateForm() {
	}

	/**
	 * Returns the effective date
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * Sets the effective date
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Returns the from date (start date)
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * Sets the from date (start date)
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * Returns the to date (end date)
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * Sets the to date (end date)
	 * @param toDate the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * Returns using effective date (true = effective date, false = using date range)
	 * @return the usingEffectiveDate
	 */
	public Boolean getUsingEffectiveDate() {
		return usingEffectiveDate;
	}

	/**
	 * Sets using effective date (true = effective date, false = using date range)
	 * @param usingEffectiveDate the usingEffectiveDate to set
	 */
	public void setUsingEffectiveDate(Boolean usingEffectiveDate) {
		this.usingEffectiveDate = usingEffectiveDate;
	}
	
	
	
}
