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
package omis.organization.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.organization.domain.Organization;

/**
 * Data access object for organizations.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (July 29, 2015)
 * @since OMIS 3.0
 */
public interface OrganizationDao
		extends GenericDao<Organization> {

	/**
	 * Returns the children of a parent organization by name.
	 * 
	 * @param parent parent organization of children to return
	 * @return children of parent organization
	 */
	List<Organization> findByParent(Organization parent);
	
	/**
	 * Returns the organization with the specified name.
	 * 
	 * @param name name of organization to return
	 * @return organization with specified name; {@code null} if no organization
	 * was found with the specified name
	 */
	Organization findByName(String name);
	
	/**
	 * Returns organizations with names matching the specified partial name
	 * ordered by name.
	 * 
	 * @param partialName partial name of organizations to return
	 * @return organizations with names matching partial name
	 */
	List<Organization> findByPartialName(String partialName);

	/**
	 * Returns organization.
	 * 
	 * @param name name of organization
	 * @return organization or {@code null} if not found
	 */
	Organization find(String name);

	/**
	 * Returns organization not excluded.
	 * 
	 * @param name name of organization
	 * @param excludedOrganizatiosn organizations to exclude
	 * @return organization not excluded
	 */
	Organization findExcluding(String name,
			Organization... excludedOrganizations);
}