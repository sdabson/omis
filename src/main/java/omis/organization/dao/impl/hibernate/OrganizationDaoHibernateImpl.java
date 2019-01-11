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
package omis.organization.dao.impl.hibernate;

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.organization.dao.OrganizationDao;
import omis.organization.domain.Organization;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for organizations.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (July 29, 2015)
 * @since OMIS 3.0
 */
public class OrganizationDaoHibernateImpl
		extends GenericHibernateDaoImpl<Organization>
		implements OrganizationDao {
	
	/* Query names. */
	
	private static final String FIND_ALL_QUERY_NAME = "findOrganizations";

	private static final String FIND_BY_PARENT_QUERY_NAME
		= "findOrganizationsByParent";

	private static final String FIND_BY_NAME_QUERY_NAME
		= "findOrganizationByName";

	private static final String FIND_BY_PARTIAL_NAME_QUERY_NAME
		= "findOrganizationsByPartialName";
	
	private static final String FIND_QUERY_NAME = "findOrganization";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findOrganizationExcluding";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String PARTIAL_NAME_PARAM_NAME = "partialName";

	private static final String PARENT_PARAM_NAME = "parent";

	private static final String EXCLUDED_ORGANIZATIONS_PARAM_NAME
		= "excludedOrganizations";

	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * organizations.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public OrganizationDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<Organization> findAll() {
		@SuppressWarnings("unchecked")
		List<Organization> organizations =
				getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return organizations;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Organization> findByParent(final Organization parent) {
		@SuppressWarnings("unchecked")
		List<Organization> organizations = 
				getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_PARENT_QUERY_NAME)
				.setParameter(PARENT_PARAM_NAME, parent).list();
		return organizations;
	}

	/** {@inheritDoc} */
	@Override
	public Organization findByName(final String name) {
		Organization organization = (Organization)
				getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_NAME_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name).uniqueResult();
		return organization;
	}

	/** {@inheritDoc} */
	@Override
	public List<Organization> findByPartialName(final String partialName) {
		@SuppressWarnings("unchecked")
		List<Organization> organizations = getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_BY_PARTIAL_NAME_QUERY_NAME)
				.setParameter(PARTIAL_NAME_PARAM_NAME, partialName).list();
		return organizations;
	}

	/** {@inheritDoc} */
	@Override
	public Organization find(final String name) {
		Organization organization = (Organization) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name).uniqueResult();
		return organization;
	}

	/** {@inheritDoc} */
	@Override
	public Organization findExcluding(final String name,
			final Organization... excludedOrganizations) {
		Organization organization = (Organization) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.setParameterList(EXCLUDED_ORGANIZATIONS_PARAM_NAME,
						excludedOrganizations).uniqueResult();
		return organization;
	}
}