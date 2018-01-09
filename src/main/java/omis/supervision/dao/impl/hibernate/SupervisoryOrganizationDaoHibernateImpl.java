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

import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.region.domain.State;
import omis.supervision.dao.SupervisoryOrganizationDao;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.SupervisoryOrganization;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for supervisory organizations.
 * 
 * @author Stephen Abson
 * @author Jason Nelson
 * @version 0.1.0 (Oct 16, 2013)
 * @since OMIS 3.0
 */
public class SupervisoryOrganizationDaoHibernateImpl
		extends GenericHibernateDaoImpl<SupervisoryOrganization>
		implements SupervisoryOrganizationDao {

	/* Queries. */
	
	private static final String FIND_ALL_QUERY_NAME
			= "findAllSupervisoryOrganizationsOrderedByName";
	
	private static final String FIND_FOR_CORRECTIONAL_STATUS_QUERY_NAME
			= "findSupervisoryOrganizationsForCorrectionalStatus";
	
	private static final String FIND_QUERY_NAME
			= "findSupervisoryOrganization";
	
	private static final String FIND_BY_STATE_QUERY_NAME
			= "findSupervisoryOrganizationsByState";
	
	private static final String FIND_FOR_CORRECTIONAL_STATUS_BY_STATE_QUERY_NAME
			= "findSupervisoryOrganizationsForCorrectionalStatusByState";
	
	private static final String FIND_ALLOWED_FOR_PLACEMENT_QUERY_NAME
			= "findSupervisoryOrganizationAllowedForPlacement";
	
	private static final String FIND_ALLOWED_FOR_PLACEMENT_IN_STATE_QUERY_NAME
		= "findSupervisoryOrganizationAllowedForPlacementInState";
	
	/* Parameters. */
	
	private static final String CORRECTIONAL_STATUS_PARAM_NAME
			= "correctionalStatus";

	private static final String NAME_PARAM_NAME = "name";

	private static final String STATE_PARAM_NAME = "state";
	
	/* Constructors. */

	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * supervisory organizations with the specified resources.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public SupervisoryOrganizationDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<SupervisoryOrganization> findAll() {
		@SuppressWarnings("unchecked")
		List<SupervisoryOrganization> organizations = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return organizations;
	}

	/** {@inheritDoc} */
	@Override
	public List<SupervisoryOrganization> findForCorrectionalStatus(
			final CorrectionalStatus correctionalStatus) {
		@SuppressWarnings("unchecked")
		List<SupervisoryOrganization> organizations = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(
					FIND_FOR_CORRECTIONAL_STATUS_QUERY_NAME)
				.setParameter(CORRECTIONAL_STATUS_PARAM_NAME,
						correctionalStatus)
				.list();
		return organizations;
	}

	/** {@inheritDoc} */
	@Override
	public SupervisoryOrganization find(final String name) {
		SupervisoryOrganization supervisoryOrganization
			= (SupervisoryOrganization)
			this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return supervisoryOrganization;
	}

	/** {@inheritDoc} */
	@Override
	public List<SupervisoryOrganization> findByState(final State state) {
		@SuppressWarnings("unchecked")
		List<SupervisoryOrganization> supervisoryOrganizations
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_STATE_QUERY_NAME)
				.setParameter(STATE_PARAM_NAME, state)
				.list();
		return supervisoryOrganizations;
	}

	/** {@inheritDoc} */
	@Override
	public List<SupervisoryOrganization> findByCorrectionalStatusForState(
			final CorrectionalStatus correctionalStatus,
			final State state) {
		@SuppressWarnings("unchecked")
		List<SupervisoryOrganization> supervisoryOrganizations
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_FOR_CORRECTIONAL_STATUS_BY_STATE_QUERY_NAME)
				.setParameter(STATE_PARAM_NAME, state)
				.setParameter(CORRECTIONAL_STATUS_PARAM_NAME,
						correctionalStatus)
				.list();
		return supervisoryOrganizations;
	}

	/** {@inheritDoc} */
	@Override
	public List<SupervisoryOrganization> findAllowedForPlacement() {
		@SuppressWarnings("unchecked")
		List<SupervisoryOrganization> supervisoryOrganizations
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALLOWED_FOR_PLACEMENT_QUERY_NAME)
				.list();
		return supervisoryOrganizations;
	}

	/** {@inheritDoc} */
	@Override
	public List<SupervisoryOrganization> findAllowedForPlacementInState(
			final State state) {
		@SuppressWarnings("unchecked")
		List<SupervisoryOrganization> supervisoryOrganizations
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALLOWED_FOR_PLACEMENT_IN_STATE_QUERY_NAME)
				.setParameter(STATE_PARAM_NAME, state)
				.list();
		return supervisoryOrganizations;
	}
}