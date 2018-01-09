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

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.supervision.dao.PlacementTermChangeActionDao;
import omis.supervision.domain.PlacementTermChangeAction;

/**
 * Hibernate implementation of data access object for action to change
 * placement term. 
 *
 * @author Stephen Abson
 * @version 0.0.1 (Dec 17, 2014)
 * @since OMIS 3.0
 */
public class PlacementTermChangeActionDaoHibernateImpl
		extends GenericHibernateDaoImpl<PlacementTermChangeAction>
		implements PlacementTermChangeActionDao {

	/* Query names. */
	
	private static final String FIND_QUERY_NAME
		= "findPlacementTermChangeAction";
	
	/* Parameter names. */
	
	private static final String NAME_PARAM_NAME = "name";

	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of data access object for
	 * action to change placement term.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public PlacementTermChangeActionDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public PlacementTermChangeAction find(final String name) {
		PlacementTermChangeAction changeAction
			= (PlacementTermChangeAction) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(NAME_PARAM_NAME, name)
				.uniqueResult();
		return changeAction;
	}
}