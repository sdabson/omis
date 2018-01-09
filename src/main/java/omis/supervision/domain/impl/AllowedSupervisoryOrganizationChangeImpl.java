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

import omis.supervision.domain.AllowedSupervisoryOrganizationChange;
import omis.supervision.domain.PlacementTermChangeAction;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Implementation of allowed supervisory organization change. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 17, 2014)
 * @since OMIS 3.0
 */
public class AllowedSupervisoryOrganizationChangeImpl
		implements AllowedSupervisoryOrganizationChange {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private PlacementTermChangeAction action;
	
	private SupervisoryOrganization fromSupervisoryOrganization;
	
	private SupervisoryOrganization toSupervisoryOrganization;
	
	/**
	 * Instantiates an implementation of allowed change of supervisory
	 * organization.
	 */
	public AllowedSupervisoryOrganizationChangeImpl() {
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
	public void setAction(
			final PlacementTermChangeAction action) {
		this.action = action;
	}

	/** {@inheritDoc} */
	@Override
	public PlacementTermChangeAction getAction() {
		return this.action;
	}

	/** {@inheritDoc} */
	@Override
	public void setFromSupervisoryOrganization(
			final SupervisoryOrganization fromSupervisoryOrganization) {
		this.fromSupervisoryOrganization = fromSupervisoryOrganization;
	}

	/** {@inheritDoc} */
	@Override
	public SupervisoryOrganization getFromSupervisoryOrganization() {
		return this.fromSupervisoryOrganization;
	}

	/** {@inheritDoc} */
	@Override
	public void setToSupervisoryOrganization(
			final SupervisoryOrganization toSupervisoryOrganization) {
		this.toSupervisoryOrganization = toSupervisoryOrganization;
	}

	/** {@inheritDoc} */
	@Override
	public SupervisoryOrganization getToSupervisoryOrganization() {
		return this.toSupervisoryOrganization;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AllowedSupervisoryOrganizationChange)) {
			return false;
		}
		AllowedSupervisoryOrganizationChange that
			= (AllowedSupervisoryOrganizationChange) obj;
		if (this.getAction() == null) {
			throw new IllegalStateException("Action required");
		}
		if (!this.getAction().equals(that.getAction())) {
			return false;
		}
		if (this.getFromSupervisoryOrganization() != null) {
			if (!this.getFromSupervisoryOrganization().equals(
					that.getFromSupervisoryOrganization())) {
				return false;
			}
		} else if (that.getFromSupervisoryOrganization() != null) {
			return false;
		}
		if (this.getToSupervisoryOrganization() != null) {
			if (!this.getToSupervisoryOrganization().equals(
					that.getToSupervisoryOrganization())) {
				return false;
			}
		} else if (that.getToSupervisoryOrganization() != null) {
			return false;
		}
		return false;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getAction() == null) {
			throw new IllegalStateException("Action required");
		}
		int hashCode = 14;
		hashCode = 29 * this.getAction().hashCode() + hashCode;
		if (this.getFromSupervisoryOrganization() != null) {
			hashCode = 31 * this.getFromSupervisoryOrganization().hashCode()
					+ hashCode;
		}
		if (this.getToSupervisoryOrganization() != null) {
			hashCode = 33 * this.getToSupervisoryOrganization().hashCode()
					+ hashCode;
		}
		return hashCode;
	}
}