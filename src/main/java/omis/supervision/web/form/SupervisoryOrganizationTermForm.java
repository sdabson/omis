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
package omis.supervision.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.supervision.domain.SupervisoryOrganization;

/**
 * Form for supervisory organization terms.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 9, 2014)
 * @since OMIS 3.0
 */
public class SupervisoryOrganizationTermForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private SupervisoryOrganization supervisoryOrganization;
	
	private Date startDate;
	
	private Date startTime;
	
	private Date endDate;
	
	private Date endTime;
	
	/** Instantiates a form for supervisory organization terms. */
	public SupervisoryOrganizationTermForm() {
		// Default instantiation
	}

	/**
	 * Returns the supervisory organization.
	 * 
	 * @return supervisory organization
	 */
	public SupervisoryOrganization getSupervisoryOrganization() {
		return this.supervisoryOrganization;
	}

	/**
	 * Sets the supervisory organization.
	 * 
	 * @param supervisoryOrganization supervisory organization
	 */
	public void setSupervisoryOrganization(
			final SupervisoryOrganization supervisoryOrganization) {
		this.supervisoryOrganization = supervisoryOrganization;
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
	 * Returns start time.
	 * 
	 * @return start time
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	/**
	 * Sets start time.
	 * 
	 * @param startTime start time
	 */
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
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

	/**
	 * Returns end time.
	 * 
	 * @return end time
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * Sets end time.
	 * 
	 * @param endTime end time
	 */
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}
}