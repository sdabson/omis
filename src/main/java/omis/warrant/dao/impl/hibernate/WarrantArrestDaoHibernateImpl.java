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


import org.hibernate.SessionFactory;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.warrant.dao.WarrantArrestDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantArrest;

/**
 * Warrant arrest data access object hibernate implementation.
 * 
 *@author Annie Jacques
 *@author Joel Norris
 *@version 0.1.1 (January 25, 2018)
 *@since OMIS 3.0
 *
 */
public class WarrantArrestDaoHibernateImpl
	extends GenericHibernateDaoImpl<WarrantArrest>implements WarrantArrestDao {
	
	private static final String FIND_WARRANT_ARREST_QUERY_NAME = 
			"findWarrantArrest";
	
	private static final String FIND_WARRANT_ARREST_EXCLUDING_QUERY_NAME =
			"findWarrantArrestExcluding";
	
	private static final String WARRANT_PARAM_NAME = "warrant";
	
	private static final String WARRANT_ARREST_PARAM_NAME = "warrantArrest";
	
	/**
	 * Instantiates a warrant arrest data access object with the specified session factory
	 * and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public WarrantArrestDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public WarrantArrest find(final Warrant warrant) {
		WarrantArrest warrantArrest = (WarrantArrest) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_ARREST_QUERY_NAME)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.uniqueResult();
		
		return warrantArrest;
	}

	/**{@inheritDoc} */
	@Override
	public WarrantArrest findExcluding(final Warrant warrant,
			final WarrantArrest warrantArrestExcluded) {
		WarrantArrest warrantArrest = (WarrantArrest) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_ARREST_EXCLUDING_QUERY_NAME)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.setParameter(WARRANT_ARREST_PARAM_NAME, warrantArrestExcluded)
				.uniqueResult();
		
		return warrantArrest;
	}
}
