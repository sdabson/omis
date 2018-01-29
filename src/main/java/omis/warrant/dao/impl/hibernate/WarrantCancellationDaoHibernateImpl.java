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
import omis.warrant.dao.WarrantCancellationDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantCancellation;

/**
 * Warrant cancellation data access object hibernate implementation.
 * 
 *@author Annie Jacques
 *@author Joel Norris
 *@version 0.1.1 (January 25, 2018)
 *@since OMIS 3.0
 *
 */
public class WarrantCancellationDaoHibernateImpl
	extends GenericHibernateDaoImpl<WarrantCancellation>
		implements WarrantCancellationDao {
	
	private static final String FIND_WARRANT_CANCELLATION_QUERY_NAME =
			"findWarrantCancellation";
	
	private static final String FIND_WARRANT_CANCELLATION_EXCLUDING_QUERY_NAME =
			"findWarrantCancellationExcluding";
	
	private static final String WARRANT_PARAM_NAME = "warrant";
	
	private static final String WARRANT_CANCELLATION_PARAM_NAME =
			"warrantCancellation";
	
	/**
	 * Instantiates a warrant cancellation data access object with the specified session
	 * factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public WarrantCancellationDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public WarrantCancellation find(final Warrant warrant) {
		WarrantCancellation warrantCancellation = (WarrantCancellation)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_CANCELLATION_QUERY_NAME)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.uniqueResult();
		
		return warrantCancellation;
	}

	/**{@inheritDoc} */
	@Override
	public WarrantCancellation findExcluding(final Warrant warrant,
			final WarrantCancellation warrantCancellationExcluding) {
		WarrantCancellation warrantCancellation = (WarrantCancellation)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_CANCELLATION_EXCLUDING_QUERY_NAME)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.setParameter(WARRANT_CANCELLATION_PARAM_NAME,
						warrantCancellationExcluding)
				.uniqueResult();
		
		return warrantCancellation;
	}
}
