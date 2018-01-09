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

import omis.grievance.domain.GrievanceDispositionLevel;
import omis.grievance.domain.GrievanceDispositionStatus;
import omis.grievance.domain.GrievanceLocation;

/**
 * Implementation of grievance disposition status.
 *
 * @author Yidong Li
 * @author Stephen Abson
 * @version 0.0.2 (Oct 3, 2015)
 * @since OMIS 3.0
 */
public class GrievanceDispositionStatusImpl 
	implements GrievanceDispositionStatus {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Short sortOrder;
	private Boolean valid;
	private GrievanceDispositionLevel level;
	private Boolean closed;
	private Boolean pending;

	/** Constructor. */
	public GrievanceDispositionStatusImpl() {
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
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public void setSortOrder(final Short sortOrder) {
		this.sortOrder = sortOrder;
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
	public void setLevel(final GrievanceDispositionLevel level) {
		this.level = level;
	}

	/** {@inheritDoc} */
	@Override
	public GrievanceDispositionLevel getLevel() {
		return this.level;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setClosed(final Boolean closed) {
		this.closed = closed;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getClosed() {
		return this.closed;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setPending(final Boolean pending) {
		this.pending = pending;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getPending() {
		return this.pending;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GrievanceLocation)) {
			return false;
		}
		GrievanceLocation that = (GrievanceLocation) obj;
		if (this.getName()==null) {
			throw new IllegalStateException("Name required");
		}
		if (this.getSortOrder()==null) {
			throw new IllegalStateException("Sort order required");
		}	
		if (this.getValid()==null) {
			throw new IllegalStateException("Valid required");
		}	
		if (this.getLevel()==null) {
			throw new IllegalStateException("Level required");
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
		if (this.getSortOrder()==null) {
			throw new IllegalStateException("Sort order required");
		}	
		if (this.getValid()==null) {
			throw new IllegalStateException("Valid required");
		}	
		if (this.getLevel()==null) {
			throw new IllegalStateException("Level required");
		}	
		hashCode += 29 * this.getName().hashCode();
		hashCode += 29 * this.getSortOrder().hashCode();
		hashCode += 29 * this.getValid().hashCode();
		hashCode += 29 * this.getLevel().hashCode();
		return hashCode;
	}
}