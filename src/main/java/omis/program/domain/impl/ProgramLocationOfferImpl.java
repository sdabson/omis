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

import omis.location.domain.Location;
import omis.program.domain.Program;
import omis.program.domain.ProgramLocationOffer;

/**
 * Implementation offering program at facility.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Apr 12, 2016)
 * @since OMIS 3.0
 */
public class ProgramLocationOfferImpl
		implements ProgramLocationOffer {

	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Program program;

	private Location location;
	
	/** Instantiates implementation offering program at location. */
	public ProgramLocationOfferImpl() {
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
	public void setLocation(final Location location) {
		this.location = location;
	}

	/** {@inheritDoc} */
	@Override
	public Location getLocation() {
		return this.location;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof ProgramLocationOffer)) {
			return false;
		}
		ProgramLocationOffer that = (ProgramLocationOffer) obj;
		if (this.getProgram() == null) {
			throw new IllegalStateException("Program required");
		}
		if (!this.getProgram().equals(that.getProgram())) {
			return false;
		}
		if (this.getLocation() == null) {
			throw new IllegalStateException("Location required");
		}
		if (!this.getLocation().equals(that.getLocation())) {
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
		if (this.getLocation() == null) {
			throw new IllegalStateException("Location required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getProgram().hashCode();
		hashCode = 29 * hashCode + this.getLocation().hashCode();
		return hashCode;
	}
}