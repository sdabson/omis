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

import omis.supervision.domain.AllowedCorrectionalStatusChange;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.PlacementTermChangeAction;

/**
 * Implementation of allowed change of correctional status. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 17, 2014)
 * @since OMIS 3.0
 */
public class AllowedCorrectionalStatusChangeImpl
		implements AllowedCorrectionalStatusChange {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private PlacementTermChangeAction action;
	
	private CorrectionalStatus fromCorrectionalStatus;
	
	private CorrectionalStatus toCorrectionalStatus;
	
	/** Implementation of allowed change of correctional status. */
	public AllowedCorrectionalStatusChangeImpl() {
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
	public void setFromCorrectionalStatus(
			final CorrectionalStatus fromCorrectionalStatus) {
		this.fromCorrectionalStatus = fromCorrectionalStatus;
	}

	/** {@inheritDoc} */
	@Override
	public CorrectionalStatus getFromCorrectionalStatus() {
		return this.fromCorrectionalStatus;
	}

	/** {@inheritDoc} */
	@Override
	public void setToCorrectionalStatus(
			final CorrectionalStatus toCorrectionalStatus) {
		this.toCorrectionalStatus = toCorrectionalStatus;
	}

	/** {@inheritDoc} */
	@Override
	public CorrectionalStatus getToCorrectionalStatus() {
		return this.toCorrectionalStatus;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof AllowedCorrectionalStatusChange)) {
			return false;
		}
		AllowedCorrectionalStatusChange that
			= (AllowedCorrectionalStatusChange) obj;
		if (this.getAction() == null) {
			throw new IllegalStateException("Action required");
		}
		if (!this.getAction().equals(that.getAction())) {
			return false;
		}
		if (this.getFromCorrectionalStatus() != null) {
			if (!this.getFromCorrectionalStatus().equals(
					that.getFromCorrectionalStatus())) {
				return false;
			}
		} else if (that.getFromCorrectionalStatus() != null) {
			return false;
		}
		if (this.getToCorrectionalStatus() != null) {
			if (!this.getToCorrectionalStatus().equals(
					that.getToCorrectionalStatus())) {
				return false;
			}
		} else if (that.getToCorrectionalStatus() != null) {
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
		if (this.getFromCorrectionalStatus() != null) {
			hashCode = 31  * this.getFromCorrectionalStatus().hashCode()
					+ hashCode;
		}
		if (this.getToCorrectionalStatus() != null) {
			hashCode = 33 * this.getToCorrectionalStatus().hashCode()
					+ hashCode;
		}
		return hashCode;
	}
}