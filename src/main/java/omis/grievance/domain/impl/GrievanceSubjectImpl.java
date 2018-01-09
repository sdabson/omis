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
import omis.grievance.domain.GrievanceSubject;

/**
 * Implementation of grievance subject.
 *
 * @author Yidong Li
 * @author Stephen Abson
 * @version 0.0.1 (May 20, 2015)
 * @since OMIS 3.0
 */
public class GrievanceSubjectImpl implements GrievanceSubject {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Boolean valid;
	private Boolean medical;
	private GrievanceDispositionLevel startLevel;

	/** Constructor. */
	public GrievanceSubjectImpl() {
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
	public Boolean getMedical() {
		return this.medical;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setMedical(final Boolean medical) {
		this.medical = medical;
	}
	
	/** {@inheritDoc} */
	@Override
	public GrievanceDispositionLevel getStartLevel() {
		return this.startLevel;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setStartLevel(final GrievanceDispositionLevel startLevel) {
		this.startLevel = startLevel;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GrievanceSubject)) {
			return false;
		}
		GrievanceSubject that = (GrievanceSubject) obj;
		if (this.getName()==null) {
			throw new IllegalStateException("Name required");
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
		if (this.getName()==null) {
			throw new IllegalStateException("Name required");
		}
		int hashCode = 14;	
		hashCode = 29 * hashCode + this.getName().hashCode();
		return hashCode;
	}
}