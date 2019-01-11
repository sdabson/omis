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

import omis.datatype.DateRange;
import omis.supervision.domain.PlacementStatus;

/**
 * Summary of placement term.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class PlacementTermSummary
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final String offenderSuffix;
	
	private final Integer offenderNumber;
	
	private final String supervisoryOrganizationName;
	
	private final String correctionalStatusName;
	
	private final Date startDate;
	
	private final Date endDate;
	
	private final Long dayCount;
	
	private final String startChangeReasonName;
	
	private final String endChangeReasonName;
	
	private final PlacementStatus status;
	
	private final Date statusStartDate;
	
	private final Date statusEndDate;
	
	private final Long statusDayCount;
	
	/**
	 * Instantiates summary of placement term.
	 * 
	 * @param id ID
	 * @param offenderLastName last name of offender
	 * @param offenderFirstName first name of offender
	 * @param offenderMiddleName middle name of offender
	 * @param offenderSuffix suffix of offender name
	 * @param offenderNumber offender number
	 * @param supervisoryOrganizationName name of supervisory organization
	 * @param correctionalStatusName name of correctional status
	 * @param startDate start date
	 * @param endDate end date
	 * @param startChangeReasonName name of start change reason
	 * @param endChangeReasonName name of end change reason
	 * @param status status
	 * @param statusStartDate status start date
	 * @param statusEndDate status end date
	 * @param effectiveDate effective date
	 */
	public PlacementTermSummary(
			final Long id,
			final String offenderLastName,
			final String offenderFirstName,
			final String offenderMiddleName,
			final String offenderSuffix,
			final Integer offenderNumber,
			final String supervisoryOrganizationName,
			final String correctionalStatusName,
			final Date startDate,
			final Date endDate,
			final String startChangeReasonName,
			final String endChangeReasonName,
			final PlacementStatus status,
			final Date statusStartDate,
			final Date statusEndDate,
			final Date effectiveDate) {
		this.id = id;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderSuffix = offenderSuffix;
		this.offenderNumber = offenderNumber;
		this.supervisoryOrganizationName = supervisoryOrganizationName;
		this.correctionalStatusName = correctionalStatusName;
		this.startDate = startDate;
		this.endDate = endDate;
		final Date diffDate;
		if (endDate != null) {
			diffDate = endDate;
		} else {
			diffDate = effectiveDate;
		}
		if (startDate.equals(diffDate) || startDate.before(diffDate)) {
			this.dayCount = DateRange.countDaysExactly(startDate, diffDate);
		} else {
			this.dayCount = -DateRange.countDaysExactly(diffDate, startDate);
		}
		this.startChangeReasonName = startChangeReasonName;
		this.endChangeReasonName = endChangeReasonName;
		this.status = status;
		this.statusStartDate = statusStartDate;
		this.statusEndDate = statusEndDate;
		if (statusStartDate != null || statusEndDate != null) {
			final Date statusStartDateToUse;
			final Date statusEndDateToUse;
			if (statusStartDate != null) {
				statusStartDateToUse = statusStartDate;
			} else {
				statusStartDateToUse = effectiveDate;
			}
			if (statusEndDate != null) {
				statusEndDateToUse = statusEndDate;
			} else {
				statusEndDateToUse = effectiveDate;
			}
			if (statusStartDateToUse.equals(statusEndDateToUse)
					|| statusStartDateToUse.before(statusEndDateToUse)) {
				this.statusDayCount = DateRange.countDaysExactly(
						statusStartDateToUse, statusEndDateToUse);
			} else {
				this.statusDayCount = -DateRange.countDaysExactly(
						statusEndDateToUse, statusStartDateToUse);
			}
		} else {
			this.statusDayCount = null;
		}
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
	 * Returns offender suffix.
	 * 
	 * @return offender suffix
	 */
	public String getOffenderSuffix() {
		return this.offenderSuffix;
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
	 * Returns name of supervisory organization.
	 * 
	 * @return name of supervisory organization
	 */
	public String getSupervisoryOrganizationName() {
		return this.supervisoryOrganizationName;
	}
	
	/**
	 * Returns the name of the correctional status.
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
	 * Returns day count.
	 * 
	 * @return day count
	 */
	public Long getDayCount() {
		return this.dayCount;
	}
	
	/**
	 * Returns name of start change reason.
	 * 
	 * @return name of start change reason
	 */
	public String getStartChangeReasonName() {
		return this.startChangeReasonName;
	}
	
	/**
	 * Returns name of end change reason.
	 * 
	 * @return name of end change reason
	 */
	public String getEndChangeReasonName() {
		return this.endChangeReasonName;
	}
	
	/**
	 * Returns status.
	 * 
	 * @return status
	 */
	public PlacementStatus getStatus() {
		return this.status;
	}
	
	/**
	 * Returns status start date.
	 * 
	 * @return status start date
	 */
	public Date getStatusStartDate() {
		return this.statusStartDate;
	}
	
	/**
	 * Returns status end date.
	 * 
	 * @return status end date
	 */
	public Date getStatusEndDate() {
		return this.statusEndDate;
	}
	
	/**
	 * Returns status day count.
	 * 
	 * @return status day count
	 */
	public Long getStatusDayCount() {
		return this.statusDayCount;
	}
}