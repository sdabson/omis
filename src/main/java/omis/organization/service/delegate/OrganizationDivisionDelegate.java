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
package omis.organization.service.delegate;

import java.util.List;

import omis.instance.factory.InstanceFactory;
import omis.organization.dao.OrganizationDivisionDao;
import omis.organization.domain.Organization;
import omis.organization.domain.OrganizationDivision;
import omis.organization.exception.OrganizationDivisionExistsException;

/**
 * Delegate for organization division.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Aug 23, 2018)
 * @since OMIS 3.0
 */
public class OrganizationDivisionDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<OrganizationDivision>
	organizationDivisionInstanceFactory;
	
	/* Data access objects. */
	
	private final OrganizationDivisionDao organizationDivisionDao;
	
	/* Constructors. */
	
	/**
	 * Delegate for organization division.
	 * 
	 * @param organizationDivisionInstanceFactory instance factory for
	 * organization division
	 * @param organizationDivisionDao data access object for organization
	 * division
	 */
	public OrganizationDivisionDelegate(
			final InstanceFactory<OrganizationDivision>
			organizationDivisionInstanceFactory,
			final OrganizationDivisionDao organizationDivisionDao) {
		this.organizationDivisionInstanceFactory
			= organizationDivisionInstanceFactory;
		this.organizationDivisionDao = organizationDivisionDao;
	}
	
	/* Methods. */
	
	/**
	 * Creates organization division.
	 * 
	 * @param organization organization
	 * @param name name
	 * @param active whether active
	 * @return organization division
	 * @throws OrganizationDivisionExistsException if organization division
	 * exists
	 */
	public OrganizationDivision create(
			final Organization organization,
			final String name,
			final Boolean active)
				throws OrganizationDivisionExistsException {
		if (this.organizationDivisionDao.find(organization, name) != null) {
			throw new OrganizationDivisionExistsException(
					"Organization division exists");
		}
		OrganizationDivision division = this.organizationDivisionInstanceFactory
				.createInstance();
		division.setOrganization(organization);
		division.setName(name);
		division.setActive(active);
		return this.organizationDivisionDao.makePersistent(division);
	}
	
	/**
	 * Returns divisions by organizations.
	 * 
	 * @param organization organization
	 * @return divisions by organization
	 */
	public List<OrganizationDivision> findByOrganization(
			final Organization organization) {
		return this.organizationDivisionDao.findByOrganization(organization);
	}
}