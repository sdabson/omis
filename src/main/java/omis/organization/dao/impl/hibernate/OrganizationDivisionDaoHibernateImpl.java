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

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.organization.dao.OrganizationDivisionDao;
import omis.organization.domain.Organization;
import omis.organization.domain.OrganizationDivision;

/**
 * Hibernate implementation of data access object for organization divisions.
 *
 * @author Sheronda Vaughn
 * @author Stephen Abson
 * @version 0.1.0 (Apr 18, 2016)
 * @since OMIS 3.0
 */
public class OrganizationDivisionDaoHibernateImpl 
		extends GenericHibernateDaoImpl<OrganizationDivision>
		implements OrganizationDivisionDao {
	
	/* Query names. */
	
	private static final String FIND_QUERY_NAME = "findOrganizationDivision";
	
	private static final String FIND_BY_ORGANIZATION_QUERY_NAME
		= "findOrganizationDivisionsByOrganization";
	
	/* Parameter names. */
	
	private static final String ORGANIZATION_PARAM_NAME = "organization";
	
	private static final String NAME_PARAM_NAME = "name";
	
	/* Constructors. */

	/**
	 * Instantiates Hibernate implementation of data access object organization 
	 * division.
	 * 
	 * @param sessionFactory
	 * @param entityName
	 */
	public OrganizationDivisionDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public OrganizationDivision find(
			final Organization organization, final String name) {
		OrganizationDivision division
			= (OrganizationDivision) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(ORGANIZATION_PARAM_NAME, organization)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return division;
	}

	/** {@inheritDoc} */
	@Override
	public List<OrganizationDivision> findByOrganization(
			final Organization organization) {
		@SuppressWarnings("unchecked")
		List<OrganizationDivision> divisions = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(
						FIND_BY_ORGANIZATION_QUERY_NAME)
				.setParameter(ORGANIZATION_PARAM_NAME, organization)
				.list();
		return divisions;
	}
}