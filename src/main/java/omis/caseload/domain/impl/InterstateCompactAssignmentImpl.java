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
package omis.caseload.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.caseload.domain.InterstateCompactAssignment;
import omis.caseload.domain.InterstateCompactCorrectionalStatus;
import omis.caseload.domain.InterstateCompactEndReasonCategory;
import omis.caseload.domain.InterstateCompactTypeCategory;
import omis.caseload.domain.OfficerCaseAssignment;
import omis.region.domain.State;

/**
 * Implementation of interstate compact assignment.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 6, 2018)
 * @since OMIS 3.0
 */
public class InterstateCompactAssignmentImpl
		implements InterstateCompactAssignment {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private OfficerCaseAssignment officerCaseAssignment;
	
	private InterstateCompactCorrectionalStatus interstateCompactStatus;
	
	private InterstateCompactTypeCategory interstateCompactType;
	
	private InterstateCompactEndReasonCategory endReason;
	
	private State jurisdiction;
	
	private String jurisdictionStateId;

	private Date projectedEndDate;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Constructor.
	 */
	public InterstateCompactAssignmentImpl() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return id;
	}

	/** {@inheritDoc} */
	@Override
	public void setOfficerCaseAssignment(
			final OfficerCaseAssignment officerCaseAssignment) {
		this.officerCaseAssignment = officerCaseAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public OfficerCaseAssignment getOfficerCaseAssignment() {
		return officerCaseAssignment;
	}

	/** {@inheritDoc} */
	@Override
	public void setInterstateCompactStatus(
			final InterstateCompactCorrectionalStatus interstateCompactStatus) {
		this.interstateCompactStatus = interstateCompactStatus;
	}

	/** {@inheritDoc} */
	@Override
	public InterstateCompactCorrectionalStatus getInterstateCompactStatus() {
		return interstateCompactStatus;
	}

	/** {@inheritDoc} */
	@Override
	public void setInterstateCompactType(
			final InterstateCompactTypeCategory interstateCompactType) {
		this.interstateCompactType = interstateCompactType;
	}

	/** {@inheritDoc} */
	@Override
	public InterstateCompactTypeCategory getInterstateCompactType() {
		return interstateCompactType;
	}

	/** {@inheritDoc} */
	@Override
	public void setEndReason(
			final InterstateCompactEndReasonCategory endReason) {
		this.endReason = endReason;
	}

	/** {@inheritDoc} */
	@Override
	public InterstateCompactEndReasonCategory getEndReason() {
		return endReason;
	}

	/** {@inheritDoc} */
	@Override
	public void setJurisdiction(final State jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	/** {@inheritDoc} */
	@Override
	public State getJurisdiction() {
		return jurisdiction;
	}

	/** {@inheritDoc} */
	@Override
	public void setJurisdictionStateId(final String jurisdictionStateId) {
		this.jurisdictionStateId = jurisdictionStateId;
	}

	/** {@inheritDoc} */
	@Override
	public String getJurisdictionStateId() {
		return jurisdictionStateId;
	}

	/** {@inheritDoc} */
	@Override
	public void setProjectedEndDate(final Date projectedEndDate) {
		this.projectedEndDate = projectedEndDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getProjectedEndDate() {
		return projectedEndDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof InterstateCompactAssignment)) {
			return false;
		}
		InterstateCompactAssignment that = (InterstateCompactAssignment) obj;
		if (this.getOfficerCaseAssignment() == null) {
			throw new IllegalStateException("Officer case assignment required.");
		}
		if (!this.getOfficerCaseAssignment().equals(
				that.getOfficerCaseAssignment())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getOfficerCaseAssignment() == null) {
			throw new IllegalStateException("Officer case assignment required.");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getOfficerCaseAssignment().hashCode();
		return hashCode;
	}
}