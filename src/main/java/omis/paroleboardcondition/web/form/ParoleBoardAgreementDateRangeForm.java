/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.paroleboardcondition.web.form;

import java.util.Date;

/**
 * Parole Board Condition Date Range Form.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 19, 2017)
 *@since OMIS 3.0
 *
 */
public class ParoleBoardAgreementDateRangeForm {
	
	private Date effectiveDate;
	
	private Date startDate;
	
	private Date endDate;
	
	private Boolean usingEffectiveDate;
	
	/**
	 * Default constructor for ParoleBoardAgreementDateRangeForm. 
	 */
	public ParoleBoardAgreementDateRangeForm() {
	}
	
	/**
	 * Returns the effective date.
	 * @return the effectiveDate
	 */
	public Date getEffectiveDate() {
		return this.effectiveDate;
	}

	/**
	 * Sets the effective date.
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(final Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Returns the start date.
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * Sets the start Date.
	 * @param startDate the startDate to set
	 */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Returns the end date.
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * Sets the end date.
	 * @param endDate the endDate to set
	 */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Returns using effective date.
	 * (true = effective date, false = using date range)
	 * @return the usingEffectiveDate
	 */
	public Boolean getUsingEffectiveDate() {
		return this.usingEffectiveDate;
	}

	/**
	 * Sets using effective date.
	 * (true = effective date, false = using date range)
	 * @param usingEffectiveDate the usingEffectiveDate to set
	 */
	public void setUsingEffectiveDate(final Boolean usingEffectiveDate) {
		this.usingEffectiveDate = usingEffectiveDate;
	}
	
}
