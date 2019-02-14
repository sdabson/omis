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
package omis.earlyreleasetracking.dao.impl.hibernate;

import java.util.Date;
import org.hibernate.SessionFactory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.docket.domain.Docket;
import omis.earlyreleasetracking.dao.EarlyReleaseRequestDao;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;

/**
 * Early Release Request Dao Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestDaoHibernateImpl
		extends GenericHibernateDaoImpl<EarlyReleaseRequest>
		implements EarlyReleaseRequestDao {
	
	private static final String FIND_QUERY_NAME = "findEarlyReleaseRequest";
	
	private static final String FIND_EXCLUDING_QUERY_NAME =
			"findEarlyReleaseRequestExcluding";
	
	private static final String DOCKET_PARAM_NAME = "docket";
	
	private static final String REQUEST_DATE_PARAM_NAME = "requestDate";
	
	private static final String EARLY_RELEASE_REQUEST_PARAM_NAME =
			"earlyReleaseRequest";
	
	/**
	 * @param sessionFactory Session Factory
	 * @param entityName Entity Name
	 */
	public EarlyReleaseRequestDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequest find(final Docket docket,
			final Date requestDate) {
		EarlyReleaseRequest earlyReleaseRequest = (EarlyReleaseRequest)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(DOCKET_PARAM_NAME, docket)
				.setParameter(REQUEST_DATE_PARAM_NAME, requestDate)
				.uniqueResult();
		
		return earlyReleaseRequest;
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequest findExcluding(final Docket docket,
			final Date requestDate,
			final EarlyReleaseRequest earlyReleaseRequestExcluding) {
		EarlyReleaseRequest earlyReleaseRequest = (EarlyReleaseRequest)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(DOCKET_PARAM_NAME, docket)
				.setParameter(REQUEST_DATE_PARAM_NAME, requestDate)
				.setParameter(EARLY_RELEASE_REQUEST_PARAM_NAME,
						earlyReleaseRequestExcluding)
				.uniqueResult();
		
		return earlyReleaseRequest;
	}

}
