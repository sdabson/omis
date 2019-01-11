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
import omis.caseload.domain.OfficerCaseAssignment;
import omis.caseload.domain.OfficerCaseAssignmentNote;

/**
 * Implementation of officer case assignment note.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 6, 2018)
 * @since OMIS 3.0
 */
public class OfficerCaseAssignmentNoteImpl
		implements OfficerCaseAssignmentNote {

	private static final long serialVersionUID = 1L;

	private Long id;

	private OfficerCaseAssignment officerCaseAssignment;
	
	private String description;
	
	private Date date;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Constructor.
	 */
	public OfficerCaseAssignmentNoteImpl() {
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
	public void setDescription(final String description) {
		this.description = description;
	}

	/** {@inheritDoc} */
	@Override
	public String getDescription() {
		return description;
	}

	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
	}

	/** {@inheritDoc} */
	@Override
	public Date getDate() {
		return date;
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
		if (!(obj instanceof OfficerCaseAssignmentNote)) {
			return false;
		}
		OfficerCaseAssignmentNote that = (OfficerCaseAssignmentNote) obj;
		if (this.getOfficerCaseAssignment() == null) {
			throw new IllegalStateException("Officer case assignment required.");
		}
		if (!this.getOfficerCaseAssignment().equals(
				that.getOfficerCaseAssignment())) {
			return false;
		}
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required.");
		}
		if (!this.getDescription().equals(that.getDescription())) {
			return false;
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		if (!this.getDate().equals(that.getDate())) {
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
		if (this.getDescription() == null) {
			throw new IllegalStateException("Description required.");
		}
		if (this.getDate() == null) {
			throw new IllegalStateException("Date required.");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getOfficerCaseAssignment().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		return hashCode;
	}	
}