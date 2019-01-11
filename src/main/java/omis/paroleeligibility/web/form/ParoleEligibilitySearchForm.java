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
package omis.paroleeligibility.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * Parole Eligibility search form.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Nov 27, 2018)
 *@since OMIS 3.0
 *
 */
public class ParoleEligibilitySearchForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Boolean singleDate;
	
	private Date date;
	
	private Date startDate;
	
	private Date endDate;
	
	/**
	 * Default constructor for Parole Eligibility Search Form.
	 */
	public ParoleEligibilitySearchForm() {
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
	 * Return the single date value.
	 * 
	 * @return single date
	 */
	public Boolean getSingleDate() {
		return this.singleDate;
	}

	/**
	 * Sets the single date value.
	 * 
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
