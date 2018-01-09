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

import omis.organization.domain.impl.OrganizationImpl;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * Implementation of organization that provides correctional supervision.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Oct 16, 2013)
 * @since OMIS 3.0
 */
public class SupervisoryOrganizationImpl
		extends OrganizationImpl
		implements SupervisoryOrganization {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a default implementation of organization that provides
	 * correctional supervision.
	 */
	public SupervisoryOrganizationImpl() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof SupervisoryOrganization)) {
			return false;
		}
		SupervisoryOrganization that = (SupervisoryOrganization) obj;
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		return hashCode;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return this.getName();
	}
}