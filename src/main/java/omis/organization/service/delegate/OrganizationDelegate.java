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
import omis.organization.dao.OrganizationDao;
import omis.organization.domain.Organization;
import omis.organization.exception.OrganizationExistsException;

/**
 * Delegate for organizations.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 29, 2015)
 * @since OMIS 3.0
 */
public class OrganizationDelegate {

	/* Data access objects. */
	
	private final OrganizationDao organizationDao;
	
	/* Instance factories. */
	
	private final InstanceFactory<Organization> organizationInstanceFactory;
	
	/* Constructors. */
	
	/**
	 * Delegate for organizations.
	 * 
	 * @param organizationDao data access object for organizations
	 * @param organizationInstanceFactory instance factory for organizations
	 */
	public OrganizationDelegate(
			final OrganizationDao organizationDao,
			final InstanceFactory<Organization> organizationInstanceFactory) {
		this.organizationDao = organizationDao;
		this.organizationInstanceFactory = organizationInstanceFactory;
	}
	
	/* Methods. */
	
	/**
	 * Creates organization.
	 * 
	 * @param name name
	 * @param alias alias
	 * @param parent parent
	 * @return created organization
	 * @throws OrganizationExistsException if organization exists
	 */
	public Organization create(final String name, final String alias,
			final Organization parent) throws OrganizationExistsException {
		if (this.organizationDao.find(name) != null) {
			throw new OrganizationExistsException("Organization exists");
		}
		Organization organization = this.organizationInstanceFactory
				.createInstance();
		this.populateOrganization(organization, name, alias, parent);
		return this.organizationDao.makePersistent(organization);
	}
	
	/**
	 * Updates organization.
	 * 
	 * @param organization organization to update
	 * @param name name
	 * @param alias alias
	 * @param parent parent
	 * @return updated organization
	 * @throws OrganizationExistsException if organization exists
	 */
	public Organization update(final Organization organization,
			final String name, final String alias, final Organization parent)
				throws OrganizationExistsException {
		if (this.organizationDao.findExcluding(name, organization) != null) {
			throw new OrganizationExistsException("Organization exists");
		}
		this.populateOrganization(organization, name, alias, parent);
		return this.organizationDao.makePersistent(organization);
	}
	
	/**
	 * Removes organization.
	 * 
	 * @param organization organization to remove
	 */
	public void remove(final Organization organization) {
		this.organizationDao.makeTransient(organization);
	}
	
	/**
	 * Returns organizations by partial name.
	 * 
	 * @param partialName partial name
	 * @return organizations by partial name
	 */
	public List<Organization> findByPartialName(final String partialName) {
		return this.organizationDao.findByPartialName(partialName);
	}
	
	/**
	 * Returns organization by name.
	 * 
	 * @param name name
	 * @return organization by name
	 */
	public Organization findByName(final String name) {
		return this.organizationDao.find(name);
	}
	
	/* Helper methods. */
	
	// Populates organization
	private void populateOrganization(final Organization organization,
			final String name, final String alias, final Organization parent) {
		organization.setName(name);
		organization.setAlias(alias);
		organization.setParent(parent);
	}
}