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
import omis.earlyreleasetracking.dao.EarlyReleaseRequestInternalApprovalDao;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestInternalApproval;

/**
 * Early Release Request Internal Approval Dao Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseRequestInternalApprovalDaoHibernateImpl
		extends GenericHibernateDaoImpl<EarlyReleaseRequestInternalApproval>
		implements EarlyReleaseRequestInternalApprovalDao {
	
	private static final String FIND_QUERY_NAME =
			"findEarlyReleaseRequestInternalApproval";
	
	private static final String FIND_EXCLUDING_QUERY_NAME =
			"findEarlyReleaseRequestInternalApprovalExcluding";
	
	private static final String FIND_BY_EARLY_RELEASE_REQUEST =
			"findEarlyReleaseRequestInternalApprovalsByEarlyReleaseRequest";
	
	private static final String EARLY_RELEASE_REQUEST_PARAM_NAME =
			"earlyReleaseRequest";
	
	private static final String NAME_PARAM_KEY = "name";

	private static final String EARLY_RELEASE_REQUEST_INTERN_APPVL_PARAM_NAME =
			"earlyReleaseRequestInternalApproval";
	
	/**
	 * @param sessionFactory Session Factory
	 * @param entityName Entity Name
	 */
	public EarlyReleaseRequestInternalApprovalDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	
	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestInternalApproval find(
			final EarlyReleaseRequest earlyReleaseRequest, final String name) {
		EarlyReleaseRequestInternalApproval approval =
				(EarlyReleaseRequestInternalApproval) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(EARLY_RELEASE_REQUEST_PARAM_NAME,
						earlyReleaseRequest)
				.setParameter(NAME_PARAM_KEY, name)
				.uniqueResult();
		
		return approval;
	}

	/** {@inheritDoc} */
	@Override
	public EarlyReleaseRequestInternalApproval findExcluding(
			final EarlyReleaseRequest earlyReleaseRequest, final String name,
			final EarlyReleaseRequestInternalApproval
				earlyReleaseRequestInternalApprovalExcluding) {
		EarlyReleaseRequestInternalApproval approval =
				(EarlyReleaseRequestInternalApproval) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(EARLY_RELEASE_REQUEST_PARAM_NAME,
						earlyReleaseRequest)
				.setParameter(NAME_PARAM_KEY, name)
				.setParameter(EARLY_RELEASE_REQUEST_INTERN_APPVL_PARAM_NAME, 
						earlyReleaseRequestInternalApprovalExcluding)
				.uniqueResult();
		
		return approval;
	}

	/** {@inheritDoc} */
	@Override
	public List<EarlyReleaseRequestInternalApproval> findByEarlyReleaseRequest(
			final EarlyReleaseRequest earlyReleaseRequest) {
		@SuppressWarnings("unchecked")
		List<EarlyReleaseRequestInternalApproval> approvals =
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_BY_EARLY_RELEASE_REQUEST)
				.setParameter(EARLY_RELEASE_REQUEST_PARAM_NAME,
						earlyReleaseRequest)
				.list();
		
		return approvals;
	}
}
