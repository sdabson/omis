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
import omis.earlyreleasetracking.dao.EarlyReleaseEligibleCorrectionalStatusDao;
import omis.earlyreleasetracking.domain.EarlyReleaseEligibleCorrectionalStatus;
import omis.supervision.domain.CorrectionalStatus;

/**
 * Early Release Eligible Correctional Status Dao Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Mar 11, 2019)
 *@since OMIS 3.0
 *
 */
public class EarlyReleaseEligibleCorrectionalStatusDaoHibernateImpl
		extends GenericHibernateDaoImpl<EarlyReleaseEligibleCorrectionalStatus>
		implements EarlyReleaseEligibleCorrectionalStatusDao {
	
	private static final String FIND_QUERY_NAME =
			"findEarlyReleaseEligibleCorrectionalStatus";
	
	private static final String FIND_EXCLUDING_QUERY_NAME =
			"findEarlyReleaseEligibleCorrectionalStatusExcluding";
	
	private static final String FIND_ALL_QUERY_NAME =
			"findEarlyReleaseEligibleCorrectionalStatuses";
	
	private static final String CORRECTIONAL_STATUS_PARAM_NAME =
			"correctionalStatus";
	
	private static final String ERLY_RLSE_ELGBL_CRRECTNL_STATUS_PARAM_NAME =
			"earlyReleaseEligibleCorrectionalStatus";
	
	/**
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public EarlyReleaseEligibleCorrectionalStatusDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public EarlyReleaseEligibleCorrectionalStatus find(
			final CorrectionalStatus correctionalStatus) {
		EarlyReleaseEligibleCorrectionalStatus
			earlyReleaseEligibleCorrectionalStatus =
			(EarlyReleaseEligibleCorrectionalStatus) this.getSessionFactory()
			.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
			.setParameter(CORRECTIONAL_STATUS_PARAM_NAME, correctionalStatus)
			.uniqueResult();
		
		return earlyReleaseEligibleCorrectionalStatus;
	}
	
	/** {@inheritDoc} */
	@Override
	public EarlyReleaseEligibleCorrectionalStatus findExcluding(
			final CorrectionalStatus correctionalStatus,
			final EarlyReleaseEligibleCorrectionalStatus
				earlyReleaseEligibleCorrectionalStatusExcluding) {
		EarlyReleaseEligibleCorrectionalStatus
			earlyReleaseEligibleCorrectionalStatus =
			(EarlyReleaseEligibleCorrectionalStatus) this.getSessionFactory()
			.getCurrentSession().getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
			.setParameter(CORRECTIONAL_STATUS_PARAM_NAME, correctionalStatus)
			.setParameter(ERLY_RLSE_ELGBL_CRRECTNL_STATUS_PARAM_NAME,
					earlyReleaseEligibleCorrectionalStatusExcluding)
			.uniqueResult();
	
		return earlyReleaseEligibleCorrectionalStatus;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<EarlyReleaseEligibleCorrectionalStatus> findAllStatuses() {
		@SuppressWarnings("unchecked")
		List<EarlyReleaseEligibleCorrectionalStatus> statuses =
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ALL_QUERY_NAME)
				.list();
		
		return statuses;
	}
}
