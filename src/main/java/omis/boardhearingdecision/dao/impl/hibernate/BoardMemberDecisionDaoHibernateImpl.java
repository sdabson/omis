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

import java.util.List;

import org.hibernate.SessionFactory;

import omis.boardhearing.domain.BoardHearingParticipant;
import omis.boardhearingdecision.dao.BoardMemberDecisionDao;
import omis.boardhearingdecision.domain.BoardHearingDecision;
import omis.boardhearingdecision.domain.BoardMemberDecision;
import omis.boardhearingdecision.domain.HearingDecisionReason;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of the board member decision data access object.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public class BoardMemberDecisionDaoHibernateImpl 
		extends GenericHibernateDaoImpl<BoardMemberDecision>
		implements BoardMemberDecisionDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = "findBoardMemberDecision";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findBoardMemberDecisionExcluding";
	
	private static final String FIND_BY_BOARD_DECISION_QUERY_NAME = 
			"findBoardMemberDecisionByBoardDecision";
	
	/* Parameters. */
	
	private static final String BOARD_DECISION_PARAM_NAME = "boardDecision";
	
	private static final String BOARD_HEARING_PARTICIPANT_PARAM_NAME = 
			"boardHearingParticipant";
	
	private static final String DECISION_REASON_PARAM_NAME = "decisionReason";
	
	private static final String EXCLUDED_BOARD_MEMBER_DECISION_PARAM_NAME = 
			"excludedBoardMemberDecision";

	/** Instantiates a hibernate implementation of the data access object for 
	 *  board hearing decision.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public BoardMemberDecisionDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public BoardMemberDecision find(final BoardHearingDecision boardDecision, 
			final BoardHearingParticipant boardHearingParticipant,
			final HearingDecisionReason decisionReason) {
		BoardMemberDecision boardMemberDecision = (BoardMemberDecision) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(BOARD_DECISION_PARAM_NAME, boardDecision)
				.setParameter(BOARD_HEARING_PARTICIPANT_PARAM_NAME, 
						boardHearingParticipant)
				.setParameter(DECISION_REASON_PARAM_NAME, decisionReason)
				.uniqueResult();
		return boardMemberDecision;
	}

	/**{@inheritDoc} */
	@Override
	public BoardMemberDecision findExcluding(
			final BoardHearingDecision boardDecision,
			final BoardHearingParticipant boardHearingParticipant, 
			final HearingDecisionReason decisionReason,
			final BoardMemberDecision excludedBoardMemberDecision) {
		BoardMemberDecision boardMemberDecision = (BoardMemberDecision) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(BOARD_DECISION_PARAM_NAME, boardDecision)
				.setParameter(BOARD_HEARING_PARTICIPANT_PARAM_NAME, 
						boardHearingParticipant)
				.setParameter(DECISION_REASON_PARAM_NAME, decisionReason)
				.setParameter(EXCLUDED_BOARD_MEMBER_DECISION_PARAM_NAME, 
						excludedBoardMemberDecision)
				.uniqueResult();
		return boardMemberDecision;
	}

	/**{@inheritDoc} */
	@Override
	public List<BoardMemberDecision> findByBoardDecision(
			final BoardHearingDecision boardDecision) {
		@SuppressWarnings("unchecked")
		List<BoardMemberDecision> boardMemberDecisions = this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_BOARD_DECISION_QUERY_NAME)
				.setParameter(BOARD_DECISION_PARAM_NAME, boardDecision)
				.list();
		return boardMemberDecisions;
	}
}