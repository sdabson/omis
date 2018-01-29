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
import omis.warrant.dao.WarrantReleaseDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantRelease;

/**
 * Warrant release data access object hibernate implementation.
 * 
 *@author Annie Jacques
 *@author Joel Norris 
 *@version 0.1.1 (January 28, 2018)
 *@since OMIS 3.0
 *
 */
public class WarrantReleaseDaoHibernateImpl
	extends GenericHibernateDaoImpl<WarrantRelease>
		implements WarrantReleaseDao {
	
	private static final String FIND_WARRANT_RELEASE_QUERY_NAME =
			"findWarrantRelease";
	
	private static final String FIND_WARRANT_RELEASE_EXCLUDING_QUERY_NAME =
			"findWarrantReleaseExcluding";
	
	private static final String WARRANT_PARAM_NAME = "warrant";
	
	private static final String WARRANT_RELEASE_PARAM_NAME = "warrantRelease";
	
	/**
	 * Instantiates a warrant release data access object with the specified
	 * session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public WarrantReleaseDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public WarrantRelease find(final Warrant warrant) {
		WarrantRelease warrantRelease = (WarrantRelease) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_RELEASE_QUERY_NAME)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.uniqueResult();
		
		return warrantRelease;
	}

	/**{@inheritDoc} */
	@Override
	public WarrantRelease findExcluding(final Warrant warrant,
			final WarrantRelease warrantReleaseExlcluded) {
		WarrantRelease warrantRelease = (WarrantRelease) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_WARRANT_RELEASE_EXCLUDING_QUERY_NAME)
				.setParameter(WARRANT_PARAM_NAME, warrant)
				.setParameter(WARRANT_RELEASE_PARAM_NAME, warrantReleaseExlcluded)
				.uniqueResult();
		
		return warrantRelease;
	}

}
