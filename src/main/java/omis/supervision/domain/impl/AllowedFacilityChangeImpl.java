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
package omis.supervision.domain.impl;

import omis.facility.domain.Facility;
import omis.supervision.domain.AllowedFacilityChange;
import omis.supervision.domain.PlacementTermChangeAction;

/**
 *  Implementation of allowed facility change.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 16, 2014)
 * @since OMIS 3.0
 */
public class AllowedFacilityChangeImpl
		implements AllowedFacilityChange {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private PlacementTermChangeAction action;
	
	private Facility fromFacility;
	
	private Facility toFacility;
	
	/** Instantiates an implementation of allowed facility change. */
	public AllowedFacilityChangeImpl() {
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
	public void setAction(final PlacementTermChangeAction action) {
		this.action = action;
	}

	/** {@inheritDoc} */
	@Override
	public PlacementTermChangeAction getAction() {
		return this.action;
	}

	/** {@inheritDoc} */
	@Override
	public void setFromFacility(final Facility fromFacility) {
		this.fromFacility = fromFacility;
	}

	/** {@inheritDoc} */
	@Override
	public Facility getFromFacility() {
		return this.fromFacility;
	}

	/** {@inheritDoc} */
	@Override
	public void setToFacility(final Facility toFacility) {
		this.toFacility = toFacility;
	}

	/** {@inheritDoc} */
	@Override
	public Facility getToFacility() {
		return this.toFacility;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof AllowedFacilityChange)) {
			return false;
		}
		AllowedFacilityChange that = (AllowedFacilityChange) obj;
		if (this.getAction() == null) {
			throw new IllegalStateException("Action required");
		}
		if (!this.getAction().equals(that.getAction())) {
			return false;
		}
		if (this.getFromFacility() != null) {
			if (!this.getFromFacility().equals(that.getFromFacility())) {
				return false;
			}
		} else if (that.getFromFacility() != null) {
			return false;
		}
		if (this.getToFacility() != null) {
			if (this.getToFacility().equals(that.getToFacility())) {
				return false;
			}
		} else if (that.getToFacility() != null) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getAction() == null) {
			throw new IllegalStateException("Action required");
		}
		int hashCode = 14;
		hashCode = 29 * this.getAction().hashCode() + hashCode;
		if (this.getFromFacility() != null) {
			hashCode = 31 * this.getFromFacility().hashCode() + hashCode;
		}
		if (this.getToFacility() != null) {
			hashCode = 33 * this.getToFacility().hashCode() + hashCode;
		}
		return hashCode;
	}
}
