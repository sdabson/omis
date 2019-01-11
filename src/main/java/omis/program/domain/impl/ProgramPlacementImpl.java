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
package omis.program.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.locationterm.domain.LocationTerm;
import omis.offender.domain.Offender;
import omis.program.domain.Program;
import omis.program.domain.ProgramPlacement;
import omis.supervision.domain.PlacementTerm;

/**
 * Implementation of program placement.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 8, 2015)
 * @since OMIS 3.0
 */
public class ProgramPlacementImpl
		implements ProgramPlacement {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Offender offender;

	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;

	private Program program;
	
	private PlacementTerm placementTerm;
	
	private LocationTerm locationTerm;
	
	private DateRange dateRange;

	/** Instantiates implementation of program placement. */
	public ProgramPlacementImpl() {
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
		return this.id;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}

	/** {@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
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
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(
			final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setProgram(final Program program) {
		this.program = program;
	}

	/** {@inheritDoc} */
	@Override
	public Program getProgram() {
		return this.program;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setPlacementTerm(
			final PlacementTerm placementTerm) {
		this.placementTerm = placementTerm;
	}

	/** {@inheritDoc} */
	@Override
	public PlacementTerm getPlacementTerm() {
		return this.placementTerm;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setLocationTerm(final LocationTerm locationTerm) {
		this.locationTerm = locationTerm;
	}
	
	/** {@inheritDoc} */
	@Override
	public LocationTerm getLocationTerm() {
		return this.locationTerm;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDateRange(final DateRange dateRange) {
		this.dateRange = dateRange;
	}

	/** {@inheritDoc} */
	@Override
	public DateRange getDateRange() {
		return this.dateRange;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof ProgramPlacement)) {
			return false;
		}
		ProgramPlacement that = (ProgramPlacement) obj;
		if (this.getProgram() == null) {
			throw new IllegalStateException("Program required");
		}
		if (!this.getProgram().equals(that.getProgram())) {
			return false;
		}
		if (this.getDateRange() != null) {
			if (that.getDateRange() != null) {
				if (this.getDateRange().getStartDate() != null) {
					if (!this.getDateRange().getStartDate().equals(
							that.getDateRange().getStartDate())) {
						return false;
					}
				} else if (that.getDateRange().getStartDate() != null) {
					return false;
				}
				if (this.getDateRange().getEndDate() != null) {
					if (!this.getDateRange().getEndDate().equals(
							that.getDateRange().getEndDate())) {
						return false;
					}
				} else if (that.getDateRange().getEndDate() != null) {
					return false;
				}
			} else {
				return false;
			}
		} else if (that.getDateRange() != null) {
			return false;
		}
		if (this.getPlacementTerm() == null) {
			throw new IllegalStateException("Placement term required");
		}
		if (!this.getPlacementTerm().equals(that.getPlacementTerm())) {
			return false;
		}
		if (this.getLocationTerm() != null) {
			if (!this.getLocationTerm().equals(that.getLocationTerm())) {
				return false;
			}
		} else if (that.getLocationTerm() != null) {
			return false;
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (!this.getOffender().equals(that.getOffender())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getProgram() == null) {
			throw new IllegalStateException("Program required");
		}
		if (this.getOffender() == null) {
			throw new IllegalStateException("Offender required");
		}
		if (this.getPlacementTerm() == null) {
			throw new IllegalStateException("Placement term required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getProgram().hashCode();
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getLocationTerm().hashCode();
		if (this.getDateRange() != null) {
			if (this.getDateRange().getStartDate() != null) {
				hashCode = 31 * hashCode + this.getDateRange().getStartDate()
						.hashCode();
			}
			if (this.getDateRange().getEndDate() != null) {
				hashCode = 33 * hashCode + this.getDateRange().getEndDate()
						.hashCode();
			}
		}
		if (this.getLocationTerm() != null) {
			hashCode = this.getLocationTerm().hashCode();
		}
		return hashCode;
	}
}