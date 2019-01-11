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
package omis.caseload.dao.impl.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;

import omis.caseload.dao.InterstateCompactCorrectionalStatusDao;
import omis.caseload.domain.InterstateCompactCorrectionalStatus;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.supervision.domain.CorrectionalStatus;

/**
 * Hibernate implementation of data access object for interstate compact 
 * correctional status.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public class InterstateCompactCorrectionalStatusDaoHibernateImpl
		extends GenericHibernateDaoImpl<InterstateCompactCorrectionalStatus> 
		implements InterstateCompactCorrectionalStatusDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = 
			"findInterstateCompactCorrectionalStatus";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findInterstateCompactCorrectionalStatusExcluding";
	
	private static final String FIND_ACTIVE_QUERY_NAME = 
			"findInterstateCompactCorrectionalStatuses";
	
	/* Parameters. */
	
	private static final String CORRECTIONAL_STATUS_PARAM_NAME = 
			"correctionalStatus";
	
	private static final String 
			EXCLUDED_INTERSTATE_COMPACT_CORRECTIONAL_STATUS_PARAM_NAME = 
					"excludedInterstateCompactCorrectionalStatus";	
	
	/** 
	 * Instantiates a hibernate implementation of interstate compact 
	 * correctional status data access object.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public InterstateCompactCorrectionalStatusDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public InterstateCompactCorrectionalStatus find(
			final CorrectionalStatus correctionalStatus) {
		InterstateCompactCorrectionalStatus interstateCompactCorrectionalStatus 
				= (InterstateCompactCorrectionalStatus) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(CORRECTIONAL_STATUS_PARAM_NAME, 
						correctionalStatus)
				.uniqueResult();
		return interstateCompactCorrectionalStatus;
	}

	/** {@inheritDoc} */
	@Override
	public InterstateCompactCorrectionalStatus findExcluding(
			CorrectionalStatus correctionalStatus,
			InterstateCompactCorrectionalStatus excludedStatus) {
		InterstateCompactCorrectionalStatus interstateCompactCorrectionalStatus 
				= (InterstateCompactCorrectionalStatus) this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(CORRECTIONAL_STATUS_PARAM_NAME, 
						correctionalStatus)
				.setParameter(
						EXCLUDED_INTERSTATE_COMPACT_CORRECTIONAL_STATUS_PARAM_NAME, 
						excludedStatus)
				.uniqueResult();
		return interstateCompactCorrectionalStatus;
	}

	/** {@inheritDoc} */
	@Override
	public List<InterstateCompactCorrectionalStatus> findActive() {
		@SuppressWarnings("unchecked")
		List<InterstateCompactCorrectionalStatus> statuses = this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_ACTIVE_QUERY_NAME)
				.list();
		return statuses;
	}
}