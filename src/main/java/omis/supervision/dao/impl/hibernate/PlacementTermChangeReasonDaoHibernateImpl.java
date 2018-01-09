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
import omis.supervision.dao.PlacementTermChangeReasonDao;
import omis.supervision.domain.CorrectionalStatus;
import omis.supervision.domain.PlacementTermChangeReason;

import org.hibernate.SessionFactory;

/**
 * Hibernate implementation of data access object for placement term change
 * reasons.
 * 
 * @author Stephen Abson
 * @author Jason Nelson
 * @version 0.1.0 (Oct 17, 2013)
 * @since OMIS 3.0
 */
public class PlacementTermChangeReasonDaoHibernateImpl
		extends GenericHibernateDaoImpl<PlacementTermChangeReason>
		implements PlacementTermChangeReasonDao {

	/* Queries. */
	
	private static final String FIND_ALL_QUERY_NAME
			= "findAllPlacementTermChangeReasons";
	
	private static final String FIND_BY_VALIDITY_QUERY_NAME
			= "findPlacementTermChangeReasons";

	private static final String FIND_ALLOWED_QUERY_NAME
			= "findAllowedCorrectionalStatusChangeReasons";

	private static final String FIND_ALLOWED_START_QUERY_NAME
	= "findAllowedCorrectionalStatusStartChangeReasons";
	
	private static final String FIND_ALLOWED_END_QUERY_NAME
	= "findAllowedCorrectionalStatusEndChangeReasons";
	
	private static final String FIND_QUERY_NAME
			= "findPlacementTermChangeReason";
	
	/* Parameters. */
	
	private static final String START_REASONS_PARAM_NAME = "startReasons";

	private static final String END_REASONS_PARAM_NAME = "endReasons";

	private static final String FROM_CORRECTIONAL_STATUS_PARAM_NAME
		= "fromCorrectionalStatus";

	private static final String TO_CORRECTIONAL_STATUS_PARAM_NAME
		= "toCorrectionalStatus";

	private static final String NAME_PARAM_NAME = "name";

	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * placement term change reasons with the specified resources.
	 * 
	 * @param sessionFactory sessionFactory
	 * @param entityName entityName
	 */
	public PlacementTermChangeReasonDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public List<PlacementTermChangeReason> find(boolean startReasons,
			boolean endReasons) {
		@SuppressWarnings("unchecked")
		List<PlacementTermChangeReason> changeReasons
			= this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_VALIDITY_QUERY_NAME)
				.setBoolean(START_REASONS_PARAM_NAME, startReasons)
				.setBoolean(END_REASONS_PARAM_NAME, endReasons).list();
		return changeReasons;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<PlacementTermChangeReason> findAll() {
		@SuppressWarnings("unchecked")
		List<PlacementTermChangeReason> reasons = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_ALL_QUERY_NAME).list();
		return reasons;
	}

	/** {@inheritDoc} */
	@Override
	public List<PlacementTermChangeReason> findAllowed(
			final CorrectionalStatus fromCorrectionalStatus,
			final CorrectionalStatus toCorrectionalStatus) {
		@SuppressWarnings("unchecked")
		List<PlacementTermChangeReason> reasons = this.getSessionFactory()
			.getCurrentSession().getNamedQuery(FIND_ALLOWED_QUERY_NAME)
			.setParameter(FROM_CORRECTIONAL_STATUS_PARAM_NAME,
					fromCorrectionalStatus)
			.setParameter(TO_CORRECTIONAL_STATUS_PARAM_NAME,
					toCorrectionalStatus)
			.list();
		return reasons;
	}

	/** {@inheritDoc} */
	@Override
	public PlacementTermChangeReason find(final String name) {
		PlacementTermChangeReason reason
			= (PlacementTermChangeReason)
			this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return reason;
	}

	/** {@inheritDoc} */
	@Override
	public List<PlacementTermChangeReason> findAllowedStartChangeReasons(
			CorrectionalStatus fromCorrectionalStatus,
			CorrectionalStatus toCorrectionalStatus) {
		@SuppressWarnings("unchecked")
		List<PlacementTermChangeReason> reasons = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALLOWED_START_QUERY_NAME)
				.setParameter(FROM_CORRECTIONAL_STATUS_PARAM_NAME, 
						fromCorrectionalStatus)
				.setParameter(TO_CORRECTIONAL_STATUS_PARAM_NAME, 
						toCorrectionalStatus)
				.list();
		return reasons;
	}

	/** {@inheritDoc} */
	@Override
	public List<PlacementTermChangeReason> findAllowedEndChangeReasons(
			CorrectionalStatus fromCorrectionalStatus) {
		@SuppressWarnings("unchecked")
		List<PlacementTermChangeReason> reasons = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ALLOWED_END_QUERY_NAME)
				.setParameter(FROM_CORRECTIONAL_STATUS_PARAM_NAME, 
						fromCorrectionalStatus)
				.list();
		return reasons;
	}
}