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

import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import omis.boardhearing.dao.BoardHearingNoteDao;
import omis.boardhearing.domain.BoardHearing;
import omis.boardhearing.domain.BoardHearingNote;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Board Hearing Note DAO Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 29, 2017)
 *@since OMIS 3.0
 *
 */
public class BoardHearingNoteDaoHibernateImpl
		extends GenericHibernateDaoImpl<BoardHearingNote>
		implements BoardHearingNoteDao {
	
	private static final String FIND_QUERY_NAME = "findBoardHearingNote";
	
	private static final String FIND_EXCLUDING_QUERY_NAME =
			"findBoardHearingNoteExcluding";
	
	private static final String FIND_BY_HEARING_QUERY_NAME =
			"findBoardHearingNotesByHearing";
	
	private static final String BOARD_HEARING_PARAM_NAME = "hearing";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String BOARD_HEARING_NOTE_PARAM_NAME =
			"boardHearingNote";
	
	/**
	 * @param sessionFactory - Session Factory
	 * @param entityName - String Entity Name
	 */
	protected BoardHearingNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public BoardHearingNote find(final BoardHearing hearing,
			final String description, final Date date) {
		BoardHearingNote boardHearingNote = (BoardHearingNote)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(BOARD_HEARING_PARAM_NAME, hearing)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.uniqueResult();
		
		return boardHearingNote;
	}

	/**{@inheritDoc} */
	@Override
	public BoardHearingNote findExcluding(final BoardHearing hearing,
			final String description, final Date date,
			final BoardHearingNote boardHearingNoteExcluded) {
		BoardHearingNote boardHearingNote = (BoardHearingNote)
				this.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(BOARD_HEARING_PARAM_NAME, hearing)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(BOARD_HEARING_NOTE_PARAM_NAME,
						boardHearingNoteExcluded)
				.uniqueResult();
		
		return boardHearingNote;
	}

	/**{@inheritDoc} */
	@Override
	public List<BoardHearingNote> findByHearing(final BoardHearing hearing) {
		@SuppressWarnings("unchecked")
		List<BoardHearingNote> boardHearingNotes = this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_BY_HEARING_QUERY_NAME)
				.setParameter(BOARD_HEARING_PARAM_NAME, hearing)
				.list();
		
		return boardHearingNotes;
	}

}
