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
package omis.supervision.report;

import java.io.Serializable;
import java.util.Date;

/**
 * Summary of correctional status term. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Nov 17, 2014)
 * @since OMIS 3.0
 */
public class CorrectionalStatusTermSummary
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final Integer offenderNumber;
	
	private final String correctionalStatusName;
	
	private final Date startDate;
	
	private final Date endDate;
	
	private final String startReasonName;
	
	private final String endReasonName;
	
	private final Long dayCount;
	
	/**
	 * Instantiates a summary of correctional status term.
	 * 
	 * @param id ID
	 * @param offenderLastName last name of offender
	 * @param offenderFirstName first name of offender
	 * @param offenderMiddleName middle name of offender
	 * @param offenderNumber offender number
	 * @param correctionalStatusName name of correctional status
	 * @param startDate start date
	 * @param endDate end date
	 * @param startReasonName name of start reason
	 * @param endReasonName name of end reason
	 * @param effectiveDate effective date
	 */
	public CorrectionalStatusTermSummary(
			final Long id,
			final String offenderLastName,
			final String offenderFirstName,
			final String offenderMiddleName,
			final Integer offenderNumber,
			final String correctionalStatusName,
			final Date startDate,
			final Date endDate,
			final String startReasonName,
			final String endReasonName,
			final Date effectiveDate) {
		this.id = id;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.correctionalStatusName = correctionalStatusName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startReasonName = startReasonName;
		this.endReasonName = endReasonName;
		final Date diffDate;
		final long dayLength = 24 * 60 * 60 * 1000;
		if (this.endDate != null) {
			diffDate = this.endDate;
		} else {
			diffDate = effectiveDate;
		}
		this.dayCount = (diffDate.getTime() / dayLength)
				- (this.startDate.getTime() / dayLength);
	}
	
	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Returns last name of offender.
	 * 
	 * @return last name of offender
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}
	
	/**
	 * Returns first name of offender.
	 * 
	 * @return first name of offender
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}
	
	/**
	 * Returns middle name of offender.
	 * 
	 * @return middle name of offender
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}
	
	/**
	 * Returns offender number.
	 * 
	 * @return offender number
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/**
	 * Returns name of correctional status.
	 * 
	 * @return name of correctional status
	 */
	public String getCorrectionalStatusName() {
		return this.correctionalStatusName;
	}
	
	/**
	 * Returns start date.
	 * 
	 * @return start date
	 */
	public Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * Returns end date.
	 * 
	 * @return end date
	 */
	public Date getEndDate() {
		return this.endDate;
	}
	
	/**
	 * Returns name of start reason.
	 * 
	 * @return name of start reason
	 */
	public String getStartReasonName() {
		return this.startReasonName;
	}
	
	/**
	 * Returns name of end reason.
	 * 
	 * @return name of end reason
	 */
	public String getEndReasonName() {
		return this.endReasonName;
	}
	
	/**
	 * Returns count of days.
	 * 
	 * @return count of days
	 */
	public Long getDayCount() {
		return this.dayCount;
	}
}