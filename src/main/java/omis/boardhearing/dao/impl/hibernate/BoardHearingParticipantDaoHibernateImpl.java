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

import java.util.List;
import org.hibernate.SessionFactory;
import omis.boardhearing.dao.BoardHearingParticipantDao;
import omis.boardhearing.domain.BoardHearing;
import omis.boardhearing.domain.BoardHearingParticipant;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.paroleboardmember.domain.ParoleBoardMember;

/**
 * Board Hearing Participants DAO Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 29, 2017)
 *@since OMIS 3.0
 *
 */
public class BoardHearingParticipantDaoHibernateImpl
		extends GenericHibernateDaoImpl<BoardHearingParticipant>
		implements BoardHearingParticipantDao {
	
	private static final String FIND_QUERY_NAME = "findBoardHearingParticipant";
	
	private static final String FIND_EXCLUDING_QUERY_NAME =
			"findBoardHearingParticipantExcluding";
	
	private static final String FIND_BY_HEARING_QUERY_NAME =
			"findBoardHearingParticipantsByHearing";
	
	private static final String BOARD_MEMBER_PARAM_NAME = "boardMember";

	private static final String BOARD_HEARING_PARAM_NAME = "hearing";
	
	private static final String BOARD_HEARING_PARTICIPANT_PARAM_NAME =
			"boardHearingParticipant";
	
	/**
	 * @param sessionFactory - Session Factory
	 * @param entityName - String Entity Name
	 */
	protected BoardHearingParticipantDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public BoardHearingParticipant find(final ParoleBoardMember boardMember,
			final BoardHearing hearing) {
		BoardHearingParticipant boardHearingParticipant =
				(BoardHearingParticipant) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(BOARD_MEMBER_PARAM_NAME, boardMember)
				.setParameter(BOARD_HEARING_PARAM_NAME, hearing)
				.uniqueResult();
		
		return boardHearingParticipant;
	}

	/**{@inheritDoc} */
	@Override
	public BoardHearingParticipant findExcluding(
			final ParoleBoardMember boardMember, final BoardHearing hearing,
			final BoardHearingParticipant boardHearingParticipantExcluded) {
		BoardHearingParticipant boardHearingParticipant =
				(BoardHearingParticipant) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(BOARD_MEMBER_PARAM_NAME, boardMember)
				.setParameter(BOARD_HEARING_PARAM_NAME, hearing)
				.setParameter(BOARD_HEARING_PARTICIPANT_PARAM_NAME,
						boardHearingParticipantExcluded)
				.uniqueResult();
		
		return boardHearingParticipant;
	}

	/**{@inheritDoc} */
	@Override
	public List<BoardHearingParticipant> findByHearing(
			final BoardHearing hearing) {
		@SuppressWarnings("unchecked")
		List<BoardHearingParticipant> participants = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_BY_HEARING_QUERY_NAME)
				.setParameter(BOARD_HEARING_PARAM_NAME, hearing)
				.list();
		
		return participants;
	}

}
