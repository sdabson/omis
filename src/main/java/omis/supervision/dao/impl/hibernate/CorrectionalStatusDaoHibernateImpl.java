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
import omis.supervision.dao.CorrectionalStatusDao;
import omis.supervision.domain.CorrectionalStatus;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for correctional statuses.
 * 
 * @author Stephen Abson
 * @author Jason Nelson
 * @version 0.1.0 (Oct 16, 2013)
 * @since OMIS 3.0
 */
public class CorrectionalStatusDaoHibernateImpl
		extends GenericHibernateDaoImpl<CorrectionalStatus>
		implements CorrectionalStatusDao {

	/* Queries. */
	
	private static final String FIND_ALL_QUERY_NAME
			= "findCorrectionalStatuses";
	
	private static final String FIND_QUERY_NAME = "findCorrectionalStatus";
	
	private static final String FIND_ALLOWED_CORRECTIONAL_STATUSES_QUERY_NAME 
		= "findAllowedCorrectionalStatuses";
	
	/* Parameters. */
	
	private static final String NAME_PARAM_NAME = "name";
	
	private static final String FROM_CORRECTIONAL_STATUS_PARAM_NAME 
		= "fromCorrectionalStatus";

	/**
	 * Instantiates Hibernate implementation of data access object for
	 * correctional statuses.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public CorrectionalStatusDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<CorrectionalStatus> findAll() {
		@SuppressWarnings("unchecked")
		List<CorrectionalStatus> statuses = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return statuses;
	}

	/** {@inheritDoc} */
	@Override
	public CorrectionalStatus find(final String name) {
		CorrectionalStatus correctionalStatus = (CorrectionalStatus)
				this.getSessionFactory().getCurrentSession()
					.getNamedQuery(FIND_QUERY_NAME)
					.setParameter(NAME_PARAM_NAME, name)
					.uniqueResult();
		return correctionalStatus;
	}

	@Override
	public List<CorrectionalStatus> findAllowedCorrectionalStatuses(
			CorrectionalStatus fromCorrectionalStatus) {
		@SuppressWarnings("unchecked")
		List<CorrectionalStatus> correctionalStatuses = this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_ALLOWED_CORRECTIONAL_STATUSES_QUERY_NAME)
			.setParameter(FROM_CORRECTIONAL_STATUS_PARAM_NAME, 
					fromCorrectionalStatus).list();
		return correctionalStatuses;
	}
}