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

import java.util.List;
import org.hibernate.SessionFactory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.earlyreleasetracking.dao.EarlyReleaseRequestExternalOppositionDao;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestExternalOpposition;
import omis.earlyreleasetracking.domain.ExternalOppositionPartyCategory;

/**
 * Early Release Request External Opposition Dao Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestExternalOppositionDaoHibernateImpl
		extends GenericHibernateDaoImpl<EarlyReleaseRequestExternalOpposition>
		implements EarlyReleaseRequestExternalOppositionDao {
	
	private static final String FIND_QUERY_NAME =
			"findEarlyReleaseRequestExternalOpposition";
	
	private static final String FIND_EXCLUDING_QUERY_NAME =
			"findEarlyReleaseRequestExternalOppositionExcluding";
	
	private static final String FIND_BY_EARLY_RELEASE_REQUEST =
			"findEarlyReleaseRequestExternalOppositionsByEarlyReleaseRequest";
	
	private static final String EARLY_RELEASE_REQUEST_PARAM_NAME =
			"earlyReleaseRequest";
	
	private static final String PARTY_PARAM_NAME = "party";
	
	private static final String EARLY_RELEASE_REQUEST_EXT_OPP_PARAM_NAME =
			"earlyReleaseRequestExternalOpposition";
	
	/**
	 * @param sessionFactory Session Factory
	 * @param entityName Entity Name
	 */
	public EarlyReleaseRequestExternalOppositionDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	
	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestExternalOpposition find(
			final EarlyReleaseRequest earlyReleaseRequest,
			final ExternalOppositionPartyCategory party) {
		EarlyReleaseRequestExternalOpposition opposition =
				(EarlyReleaseRequestExternalOpposition) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(EARLY_RELEASE_REQUEST_PARAM_NAME,
						earlyReleaseRequest)
				.setParameter(PARTY_PARAM_NAME, party)
				.uniqueResult();
		
		return opposition;
	}
	
	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestExternalOpposition findExcluding(
			final EarlyReleaseRequest earlyReleaseRequest,
			final ExternalOppositionPartyCategory party,
			final EarlyReleaseRequestExternalOpposition
				earlyReleaseRequestExternalOppositionExcluding) {
		EarlyReleaseRequestExternalOpposition opposition =
				(EarlyReleaseRequestExternalOpposition) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(EARLY_RELEASE_REQUEST_PARAM_NAME,
						earlyReleaseRequest)
				.setParameter(PARTY_PARAM_NAME, party)
				.setParameter(EARLY_RELEASE_REQUEST_EXT_OPP_PARAM_NAME,
						earlyReleaseRequestExternalOppositionExcluding)
				.uniqueResult();
		
		return opposition;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<EarlyReleaseRequestExternalOpposition>
				findByEarlyReleaseRequest(final EarlyReleaseRequest
						earlyReleaseRequest) {
		@SuppressWarnings("unchecked")
		List<EarlyReleaseRequestExternalOpposition> oppositions =
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_EARLY_RELEASE_REQUEST)
				.setParameter(EARLY_RELEASE_REQUEST_PARAM_NAME,
						earlyReleaseRequest)
				.list();
		
		return oppositions;
	}
}
