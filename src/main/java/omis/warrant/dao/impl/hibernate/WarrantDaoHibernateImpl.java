/*
 *  OMIS - Offender Management Information System
 *  Copyright (C) 2011 - 2017 State of Montana
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.warrant.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.offender.domain.Offender;
import omis.warrant.dao.WarrantDao;
import omis.warrant.domain.Warrant;

/**
 * WarrantDaoHibernateImpl.java
 * 
 *@author Annie Jacques
 *@author Joel Norris 
 *@version 0.1.1 (January 25, 2018)
 *@since OMIS 3.0
 *
 */
public class WarrantDaoHibernateImpl
	extends GenericHibernateDaoImpl<Warrant> implements WarrantDao {
	
	private static final String FIND_WARRANT_QUERY_NAME = "findWarrant";
	
	private static final String FIND_WARRANT_EXCLUDING_QUERY_NAME =
			"findWarrantExcluding";
	
	private static final String FIND_WARRANTS_BY_OFFENDER_QUERY_NAME =
			"findWarrantsByOffender";
	
	private static final String OFFENDER_PARAM_NAME = "offender";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String WARRANT_PARAM_NAME = "warrant";
	
	/**
	 * Instantiates a warrant data access object with the specified session factory
	 * and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public WarrantDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public Warrant find(final Offender offender, final Date date) {
		Warrant warrant = (Warrant) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(DATE_PARAM_NAME, date)
				.uniqueResult();
		
		return warrant;
	}

	/**{@inheritDoc} */
	@Override
	public Warrant findExcluding(final Offender offender, final Date date,
			final Warrant warrantExcluded) {
		Warrant warrant = (Warrant) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(WARRANT_PARAM_NAME, warrantExcluded)
				.uniqueResult();
		
		return warrant;
	}

	/**{@inheritDoc} */
	@Override
	public List<Warrant> findByOffender(Offender offender) {
		@SuppressWarnings("unchecked")
		List<Warrant> warrants = this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANTS_BY_OFFENDER_QUERY_NAME)
				.setParameter(OFFENDER_PARAM_NAME, offender)
				.list();
		
		return warrants;
	}
}
