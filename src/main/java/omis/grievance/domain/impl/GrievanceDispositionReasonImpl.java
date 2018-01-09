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
package omis.grievance.domain.impl;

import omis.grievance.domain.GrievanceDispositionReason;
import omis.grievance.domain.GrievanceDispositionReasonCategory;
import omis.grievance.domain.GrievanceDispositionStatus;

/**
 * Implementation of grievance disposition reason.
 *
 * @author Yidong Li
 * @author Stephen Abson
 * @version 0.0.1 (May 18, 2015)
 * @since OMIS 3.0
 */
public class GrievanceDispositionReasonImpl 
		implements GrievanceDispositionReason {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private GrievanceDispositionStatus status;
	
	private Boolean valid;
	
	private GrievanceDispositionReasonCategory category;

	/** Constructor. */
	public GrievanceDispositionReasonImpl() {
	}
	
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDispositionStatus getStatus() {
		return this.status;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setStatus(final GrievanceDispositionStatus status) {
		this.status = status;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCategory(final GrievanceDispositionReasonCategory category) {
		this.category = category;
	}
		
	/** {@inheritDoc} */
	@Override
	public GrievanceDispositionReasonCategory getCategory() {
		return this.category;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GrievanceDispositionReason)) {
			return false;
		}
		GrievanceDispositionReason that = (GrievanceDispositionReason) obj;
		if (this.getName()==null) {
			throw new IllegalStateException("Name required");
		}
		if (this.getCategory()==null) {
			throw new IllegalStateException("Reason required");
		}	
		if (this.getValid()==null) {
			throw new IllegalStateException("Valid required");
		}	
		
		if (this.getName().equals(that.getName())) {
			return true;
		} else {
			return false;
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int hashCode = 7;
		if (this.getName()==null) {
			throw new IllegalStateException("Name required");
		}
		if (this.getCategory()==null) {
			throw new IllegalStateException("Reason required");
		}	
		if (this.getValid()==null) {
			throw new IllegalStateException("Valid required");
		}	
		hashCode += 29 * this.getName().hashCode();
		hashCode += 29 * this.getCategory().hashCode();
		hashCode += 29 * this.getValid().hashCode();
		return hashCode;
	}
}