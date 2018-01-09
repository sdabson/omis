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
package omis.grievance.report;

import java.io.Serializable;
import java.util.Date;

import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceDisposition;
import omis.grievance.domain.GrievanceSubject;
import omis.offender.domain.Offender;
import omis.person.domain.PersonName;

/**
 * Summary of grievance.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 22, 2015)
 * @since OMIS 3.0
 */
public class GrievanceSummary
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final Long offenderId;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final String offenderSuffix;
	
	private final Integer offenderNumber;
	
	private final Integer grievanceNumber;
	
	private final Date openedDate;
	
	private final String subjectName;
	
	private final String statusName;
	
	private final String description;

	/**
	 * 
	 * @param id ID
	 * @param offenderId ID of offender
	 * @param offenderLastName last name of offender
	 * @param offenderFirstName first name of offender
	 * @param offenderMiddleName middle name of offender
	 * @param offenderSuffix suffix of offender
	 * @param offenderNumber offender number
	 * @param grievanceNumber grievance number
	 * @param openedDate opened date
	 * @param subjectName name of subject
	 * @param coordinatorLevelDisposition coordinator level disposition
	 * @param wardenLevelDisposition warden level disposition
	 * @param fhaLevelDisposition FHA level disposition
	 * @param directoryLevelDisposition director level disposition
	 * @param description description
	 * @param effectiveDate effective date
	 */
	public GrievanceSummary(
			final Long id,
			final Long offenderId,
			final String offenderLastName,
			final String offenderFirstName,
			final String offenderMiddleName,
			final String offenderSuffix,
			final Integer offenderNumber,
			final Integer grievanceNumber,
			final Date openedDate,
			final String subjectName,
			final String coordinatorLevelDispositionStatusName,
			final String wardenLevelDispositionStatusName,
			final String fhaLevelDispositionStatusName,
			final String directorLevelDispositionStatusName,
			final String description,
			final Date effectiveDate) {
		this.id = id;
		this.offenderId = offenderId;
		this.offenderLastName = offenderLastName;
		this.offenderFirstName = offenderFirstName;
		this.offenderMiddleName = offenderMiddleName;
		this.offenderSuffix = offenderSuffix;
		this.offenderNumber = offenderNumber;
		this.grievanceNumber = grievanceNumber;
		this.openedDate = openedDate;
		this.subjectName = subjectName;
		if (directorLevelDispositionStatusName != null) {
			this.statusName = directorLevelDispositionStatusName;
		} else if (fhaLevelDispositionStatusName != null) {
			this.statusName = fhaLevelDispositionStatusName;
		} else if (wardenLevelDispositionStatusName != null) {
			this.statusName = wardenLevelDispositionStatusName;
		} else if (coordinatorLevelDispositionStatusName != null) {
			this.statusName = coordinatorLevelDispositionStatusName;
		} else {
			this.statusName = null;
		}
		this.description = description;
	}
	
	/**
	 * Summary of grievance.
	 * 
	 * @param grievance grievance
	 * @param offender offender
	 * @param offenderName name of offender
	 * @param subject subject
	 * @param coordinatorLevelDisposition coordinator level disposition
	 * @param wardenLevelDisposition warden level disposition
	 * @param fhaLevelDisposition FHA level disposition
	 * @param directoryLevelDisposition director level disposition
	 * @param effectiveDate effective date
	 */
	public GrievanceSummary(
			final Grievance grievance,
			final Offender offender,
			final PersonName offenderName,
			final GrievanceSubject subject,
			final GrievanceDisposition coordinatorLevelDisposition,
			final GrievanceDisposition wardenLevelDisposition,
			final GrievanceDisposition fhaLevelDisposition,
			final GrievanceDisposition directorLevelDisposition,
			final Date effectiveDate) {
		this.id = grievance.getId();
		this.offenderId = offender.getId();
		this.offenderLastName = offenderName.getLastName();
		this.offenderFirstName = offenderName.getFirstName();
		this.offenderMiddleName = offenderName.getMiddleName();
		this.offenderSuffix = offenderName.getSuffix();
		this.offenderNumber = offender.getOffenderNumber();
		this.grievanceNumber = grievance.getGrievanceNumber();
		this.openedDate = grievance.getOpenedDate();
		this.subjectName = subject.getName();
		if (directorLevelDisposition != null
				&& directorLevelDisposition.getStatus() != null) {
			this.statusName = directorLevelDisposition.getStatus().getName();
		} else if (fhaLevelDisposition != null
				&& fhaLevelDisposition.getStatus() != null) {
			this.statusName = fhaLevelDisposition.getStatus().getName();
		} else if (wardenLevelDisposition != null
				&& wardenLevelDisposition.getStatus() != null) {
			this.statusName = wardenLevelDisposition.getStatus().getName();
		} else if (coordinatorLevelDisposition != null
				&& coordinatorLevelDisposition.getStatus() != null) {
			this.statusName = coordinatorLevelDisposition.getStatus().getName();
		} else {
			this.statusName = null;
		}
		this.description = grievance.getDescription();
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
	 * Returns ID of offender.
	 * 
	 * @return ID of offender
	 */
	public Long getOffenderId() {
		return this.offenderId;
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
	 * Returns suffix of offender.
	 * 
	 * @return suffix of offender
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
	 * Returns grievance number.
	 * 
	 * @return grievance number
	 */
	public Integer getGrievanceNumber() {
		return grievanceNumber;
	}

	/**
	 * Returns date opened.
	 * 
	 * @return date opened
	 */
	public Date getOpenedDate() {
		return this.openedDate;
	}

	/**
	 * Returns name of subject also know as type.
	 * 
	 * @return name of subject also know as type
	 */
	public String getSubjectName() {
		return this.subjectName;
	}

	/**
	 * Returns status name.
	 * 
	 * @return status name
	 */
	public String getStatusName() {
		return this.statusName;
	}

	/**
	 * Returns description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}
}