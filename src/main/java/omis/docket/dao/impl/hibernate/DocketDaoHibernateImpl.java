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
package omis.docket.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.court.domain.Court;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.docket.dao.DocketDao;
import omis.docket.domain.Docket;
import omis.person.domain.Person;

/**
 * Hibernate implementation of data access object for dockets.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class DocketDaoHibernateImpl
		extends GenericHibernateDaoImpl<Docket>
		implements DocketDao {
	
	/* Queries. */
	
	private static final String FIND_QUERY_NAME = "findDocket";
	
	private static final String FIND_EXCLUDING_QUERY_NAME
		= "findDocketExcluding";

	private static final String FIND_BY_PERSON_QUERY_NAME = "findDocketsByPerson";
	
	/* Parameters. */
	
	private static final String PERSON_PARAM_NAME = "person";
	
	private static final String COURT_PARAM_NAME = "court";
	
	private static final String VALUE_PARAM_NAME = "value";
	
	private static final String EXCLUDED_PARAM_NAME = "excludedDockets";

	/* Constructors. */
	
	/**
	 * Instantiates Hibernate implementation of data access object for dockets.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public DocketDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/* Method implementations. */
	
	/** {@inheritDoc} */
	@Override
	public Docket find(
			final Person person, final Court court, final String value) {
		Docket docket = (Docket) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.setParameter(COURT_PARAM_NAME, court)
				.setParameter(VALUE_PARAM_NAME, value)
				.uniqueResult();
		return docket;
	}

	/** {@inheritDoc} */
	@Override
	public Docket findExcluding(
			final Person person, final Court court, final String value,
			final Docket... excludedDockets) {
		Docket docket = (Docket) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.setParameter(COURT_PARAM_NAME, court)
				.setParameter(VALUE_PARAM_NAME, value)
				.setParameterList(EXCLUDED_PARAM_NAME, excludedDockets)
				.uniqueResult();
		return docket;
	}

	/**{@inheritDoc} */
	@Override
	public List<Docket> findByPerson(final Person person) {
		@SuppressWarnings("unchecked")
		List<Docket> dockets = this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_PERSON_QUERY_NAME)
				.setParameter(PERSON_PARAM_NAME, person)
				.list();
		return dockets;
	}
}