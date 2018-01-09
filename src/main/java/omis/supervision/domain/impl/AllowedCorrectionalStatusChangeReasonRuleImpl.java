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

import omis.supervision.domain.AllowedCorrectionalStatusChangeReasonRule;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.PlacementTermChangeReason;

/**
 * Implementation of rule to allow correctional status change reason.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jan 12, 2015)
 * @since OMIS 3.0
 */
public class AllowedCorrectionalStatusChangeReasonRuleImpl
		implements AllowedCorrectionalStatusChangeReasonRule {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private CorrectionalStatus fromCorrectionalStatus;
	
	private CorrectionalStatus toCorrectionalStatus;
	
	private PlacementTermChangeReason changeReason;

	/**
	 * Instantiates implementation of rule to allow correctional status change.
	 */
	public AllowedCorrectionalStatusChangeReasonRuleImpl() {
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
	public void setChangeReason(
			final PlacementTermChangeReason changeReason) {
		this.changeReason = changeReason;
	}

	/** {@inheritDoc} */
	@Override
	public PlacementTermChangeReason getChangeReason() {
		return this.changeReason;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AllowedCorrectionalStatusChangeReasonRule)) {
			return false;
		}
		AllowedCorrectionalStatusChangeReasonRule that
			= (AllowedCorrectionalStatusChangeReasonRule) obj;
		if (this.getFromCorrectionalStatus() == null) {
			throw new IllegalStateException(
					"From correctional status required");
		}
		if (!this.getFromCorrectionalStatus().equals(
				that.getFromCorrectionalStatus())) {
			return false;
		}
		if (this.getToCorrectionalStatus() == null) {
			throw new IllegalStateException(
					"To correctional status required");
		}
		if (!this.getToCorrectionalStatus().equals(
				that.getToCorrectionalStatus())) {
			return false;
		}
		if (this.getChangeReason() == null) {
			throw new IllegalStateException("Change reason required");
		}
		if (!this.getChangeReason().equals(that.getChangeReason())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getFromCorrectionalStatus() == null) {
			throw new IllegalStateException("Correctional status required");
		}
		if (this.getChangeReason() == null) {
			throw new IllegalStateException("Change reason required");
		}
		if (this.getToCorrectionalStatus() == null) {
			throw new IllegalStateException(
					"To correctional status required");
		}
		int hashCode = 14;
		hashCode = 31 * hashCode + this.getFromCorrectionalStatus().hashCode();
		hashCode = 33 * hashCode + this.getToCorrectionalStatus().hashCode();
		hashCode = 29 * hashCode + this.getChangeReason().hashCode();
		return hashCode;
	}
}