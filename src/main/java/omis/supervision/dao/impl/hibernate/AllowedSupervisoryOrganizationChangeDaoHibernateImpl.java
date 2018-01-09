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
package omis.supervision.dao.impl.hibernate;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.supervision.dao.AllowedSupervisoryOrganizationChangeDao;
import omis.supervision.domain.AllowedSupervisoryOrganizationChange;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.SupervisoryOrganization;

import java.util.List;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for allowed change of
 * supervisory organization.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 17, 2014)
 * @since OMIS 3.0
 */
public class AllowedSupervisoryOrganizationChangeDaoHibernateImpl
		extends GenericHibernateDaoImpl<AllowedSupervisoryOrganizationChange>
		implements AllowedSupervisoryOrganizationChangeDao {

	/* Queries. */
	
	private static final String FIND_ALLOWED_FOR_CHANGE
		= "findAllowedSupervisoryOrganizationsForChange";
	
	/* Parameters. */
	
	private static final String CORRECTIONAL_STATUS_PARAM_NAME
		= "correctionalStatus";
	
	private static final String SUPERVISORY_ORGANIZATION_PARAM_NAME
		= "supervisoryOrganization";
	
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * allowed change of supervisory organization.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public AllowedSupervisoryOrganizationChangeDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<SupervisoryOrganization> findAllowedForChange(
			final CorrectionalStatus correctionalStatus,
			final SupervisoryOrganization supervisoryOrganization) {
		@SuppressWarnings("unchecked")
		List<SupervisoryOrganization> organizations
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALLOWED_FOR_CHANGE)
				.setParameter(CORRECTIONAL_STATUS_PARAM_NAME,
						correctionalStatus)
				.setParameter(SUPERVISORY_ORGANIZATION_PARAM_NAME,
						supervisoryOrganization)
				.list();
		return organizations;
	}
}