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
package omis.boardhearing.dao.impl.hibernate;

import org.hibernate.SessionFactory;
import omis.boardhearing.dao.BoardHearingDao;
import omis.boardhearing.domain.BoardHearing;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.paroleeligibility.domain.ParoleEligibility;

/**
 * Board Hearing DAO Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 29, 2017)
 *@since OMIS 3.0
 *
 */
public class BoardHearingDaoHibernateImpl extends
		GenericHibernateDaoImpl<BoardHearing> implements BoardHearingDao {
	
	private static final String FIND_QUERY_NAME = "findBoardHearing";
	
	private static final String FIND_EXCLUDING_QUERY_NAME =
			"findBoardHearingExcluding";
	
	private static final String PAROLE_ELIGIBILITY_PARAM_NAME =
			"paroleEligibility";
	
	private static final String BOARD_HEARING_PARAM_NAME = "boardHearing";
	
	/**
	 * @param sessionFactory - Session Factory
	 * @param entityName - String Entity Name
	 */
	protected BoardHearingDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public BoardHearing find(final ParoleEligibility paroleEligibility) {
		BoardHearing boardHearing = (BoardHearing) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(PAROLE_ELIGIBILITY_PARAM_NAME, paroleEligibility)
				.uniqueResult();
		
		return boardHearing;
	}

	/**{@inheritDoc} */
	@Override
	public BoardHearing findExcluding(final ParoleEligibility paroleEligibility,
			final BoardHearing boardHearingExcluded) {
		BoardHearing boardHearing = (BoardHearing) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(PAROLE_ELIGIBILITY_PARAM_NAME, paroleEligibility)
				.setParameter(BOARD_HEARING_PARAM_NAME, boardHearingExcluded)
				.uniqueResult();
		
		return boardHearing;
	}

}
