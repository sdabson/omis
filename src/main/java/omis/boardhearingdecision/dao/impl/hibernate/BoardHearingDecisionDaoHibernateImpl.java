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
package omis.boardhearingdecision.dao.impl.hibernate;

import org.hibernate.SessionFactory;

import omis.boardhearing.domain.BoardHearing;
import omis.boardhearingdecision.dao.BoardHearingDecisionDao;
import omis.boardhearingdecision.domain.BoardHearingDecision;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of the board hearing decision data access object.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public class BoardHearingDecisionDaoHibernateImpl 
		extends GenericHibernateDaoImpl<BoardHearingDecision>
		implements BoardHearingDecisionDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = "findBoardHearingDecision";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findBoardHearingDecisionExcluding";
	
	/* Parameters. */
	
	private static final String HEARING_PARAM_NAME = "hearing";
	
	private static final String EXCLUDED_BOARD_DECISION_PARAM_NAME = 
			"excludedBoardDecision";

	/** Instantiates a hibernate implementation of the data access object for 
	 *  board hearing decision.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public BoardHearingDecisionDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public BoardHearingDecision find(final BoardHearing hearing) {
		BoardHearingDecision boardHearingDecision = (BoardHearingDecision) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(HEARING_PARAM_NAME, hearing)
				.uniqueResult();
		return boardHearingDecision;
	}

	/**{@inheritDoc} */
	@Override
	public BoardHearingDecision findExcluding(final BoardHearing hearing, 
			final BoardHearingDecision excludedBoardDecision) {
		BoardHearingDecision boardHearingDecision = (BoardHearingDecision) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(HEARING_PARAM_NAME, hearing)
				.setParameter(EXCLUDED_BOARD_DECISION_PARAM_NAME, 
						excludedBoardDecision)
				.uniqueResult();
		return boardHearingDecision;
	}
}