package omis.courtcasecondition.web.form;

import java.util.Date;

/**
 * CourtCaseConditionDateRangeForm.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 30, 2017)
 *@since OMIS 3.0
 *
 */
public class CourtCaseConditionDateRangeForm {
	
	private Date effectiveDate;
	
	private Date startDate;
	
	private Date endDate;
	
	private Boolean usingEffectiveDate;
	
	
	/**
	 * Default constructor for CourtCaseConditionDateRangeForm
	 */
	public CourtCaseConditionDateRangeForm() {
	}

	/**
	 * Returns the effective date
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	/**
	 * Sets the effective date
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Returns the start date (
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start Date
	 * @param startDate the startDate to set
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date
	 * @param endDate the endDate to set
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns using effective date
	 * (true = effective date, false = using date range)
	 * @return the usingEffectiveDate
	 */
	public Boolean getUsingEffectiveDate() {
		return this.usingEffectiveDate;
	}

	/**
	 * Sets using effective date
	 * (true = effective date, false = using date range)
	 * @param usingEffectiveDate the usingEffectiveDate to set
	 */
	public void setUsingEffectiveDate(final Boolean usingEffectiveDate) {
		this.usingEffectiveDate = usingEffectiveDate;
	}
	
	
	
}
