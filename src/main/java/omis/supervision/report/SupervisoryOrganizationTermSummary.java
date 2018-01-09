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
 * Summary of supervisory organization term.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Nov 14, 2014)
 * @since OMIS 3.0
 */
public class SupervisoryOrganizationTermSummary
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final Integer offenderNumber;
	
	private final String supervisoryOrganizationName;
	
	private final Long locationCount;
	
	private final Date startDate;
	
	private final Date endDate;
	
	private final String startReasonName;
	
	private final String endReasonName;
	
	private final Long dayCount;
	
	/**
	 * Instantiates a summary of supervisory organization term.
	 * 
	 * @param id ID
	 * @param offenderLastName last name of offender
	 * @param offenderFirstName first name of offender
	 * @param offenderMiddleName middle name of offender
	 * @param offenderNumber offender number
	 * @param supervisoryOrganizationName name of supervisory organization
	 * @param locationCount number of locations of offender during term
	 * @param startDate start date
	 * @param endDate end date
	 * @param startReasonName name of start reason
	 * @param endReasonName name of end reason
	 * @param effectiveDate effective date
	 */
	public SupervisoryOrganizationTermSummary(
			final Long id,
			final String offenderLastName,
			final String offenderFirstName,
			final String offenderMiddleName,
			final Integer offenderNumber,
			final String supervisoryOrganizationName,
			final Long locationCount,
			final Date startDate, final Date endDate,
			final String startReasonName, final String endReasonName,
			final Date effectiveDate) {
		this.id = id;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderNumber = offenderNumber;
		this.supervisoryOrganizationName = supervisoryOrganizationName;
		this.locationCount = locationCount;
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
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Returns the offender last name.
	 * 
	 * @return offender last name
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}
	
	/**
	 * Returns the offender first name.
	 * 
	 * @return offender first name
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}
	
	/**
	 * Returns offender middle name.
	 * 
	 * @return offender middle name
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
	 * Returns name of supervisory organization.
	 * 
	 * @return name of supervisory organization
	 */
	public String getSupervisoryOrganizationName() {
		return this.supervisoryOrganizationName;
	}
	
	/**
	 * Returns number of locations of offender during term.
	 * 
	 * @return number of locations of offender during term
	 */
	public Long getLocationCount() {
		return this.locationCount;
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
	 * Returns start reason name.
	 * 
	 * @return start reason name
	 */
	public String getStartReasonName() {
		return this.startReasonName;
	}
	
	/**
	 * Returns end reason name.
	 * 
	 * @return end reason name
	 */
	public String getEndReasonName() {
		return this.endReasonName;
	}
	
	/**
	 * Returns number of days supervised by organization.
	 * 
	 * @return number of days supervised by organization
	 */
	public Long getDayCount() {
		return this.dayCount;
	}
}