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

import omis.supervision.domain.AllowedSupervisoryOrganizationRule;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Implementation of rule allowing supervisory organization for correctional
 * status. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 4, 2014)
 * @since OMIS 3.0
 */
public class AllowedSupervisoryOrganizationRuleImpl
		implements AllowedSupervisoryOrganizationRule {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private CorrectionalStatus correctionalStatus;
	
	private SupervisoryOrganization supervisoryOrganization;
	
	/**
	 * Instantiates implementation of rule allowing supervisory organization
	 * for correctional status.
	 */
	public AllowedSupervisoryOrganizationRuleImpl() {
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
	public void setCorrectionalStatus(
			final CorrectionalStatus correctionalStatus) {
		this.correctionalStatus = correctionalStatus;
	}

	/** {@inheritDoc} */
	@Override
	public CorrectionalStatus getCorrectionalStatus() {
		return this.correctionalStatus;
	}

	/** {@inheritDoc} */
	@Override
	public void setSupervisoryOrganization(
			final SupervisoryOrganization supervisoryOrganization) {
		this.supervisoryOrganization = supervisoryOrganization;
	}

	/** {@inheritDoc} */
	@Override
	public SupervisoryOrganization getSupervisoryOrganization() {
		return this.supervisoryOrganization;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AllowedSupervisoryOrganizationRule)) {
			return false;
		}
		AllowedSupervisoryOrganizationRule that
			= (AllowedSupervisoryOrganizationRule) obj;
		if (this.getCorrectionalStatus() == null) {
			throw new IllegalStateException("Correctional status required");
		}
		if (!this.getCorrectionalStatus().equals(
				that.getCorrectionalStatus())) {
			return false;
		}
		if (this.getSupervisoryOrganization() == null) {
			throw new IllegalStateException(
					"Supervisory organization required");
		}
		if (!this.getSupervisoryOrganization().equals(
				that.getSupervisoryOrganization())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getCorrectionalStatus() == null) {
			throw new IllegalStateException("Correctional status required");
		}
		if (this.getSupervisoryOrganization() == null) {
			throw new IllegalStateException(
					"Supervisory organization required");
		}
		int hashCode = 14;
		hashCode = 29 * this.getCorrectionalStatus().hashCode() + hashCode;
		hashCode = 29 * this.getSupervisoryOrganization().hashCode() + hashCode;
		return hashCode;
	}
}