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

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import omis.boardhearingdecision.dao.HearingDecisionNoteDao;
import omis.boardhearingdecision.domain.BoardHearingDecision;
import omis.boardhearingdecision.domain.HearingDecisionNote;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;

/**
 * Hibernate implementation of the hearing decision note data access object.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 22, 2018)
 * @since OMIS 3.0
 */
public class HearingDecisionNoteDaoHibernateImpl 
		extends GenericHibernateDaoImpl<HearingDecisionNote>
		implements HearingDecisionNoteDao {

	/* Queries. */
	
	private static final String FIND_QUERY_NAME = "findHearingDecisionNote";
	
	private static final String FIND_EXCLUDING_QUERY_NAME = 
			"findHearingDecisionNoteExcluding";
	
	private static final String FIND_BY_BOARD_DECISION_QUERY_NAME = 
			"findHearingDecisionNoteByBoardDecision";
	
	/* Parameters. */
	
	private static final String BOARD_DECISION_PARAM_NAME = "boardDecision";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String EXCLUDED_HEARING_DECISION_NOTE_PARAM_NAME = 
			"excludedHearingDecisionNote";

	/** Instantiates a hibernate implementation of the data access object for 
	 *  board hearing decision.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public HearingDecisionNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public HearingDecisionNote find(final BoardHearingDecision boardDecision, 
			final Date date, final String description) {
		HearingDecisionNote hearingDecisionNote = (HearingDecisionNote) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_QUERY_NAME)
				.setParameter(BOARD_DECISION_PARAM_NAME, boardDecision)
				.setParameter(DATE_PARAM_NAME, date)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.uniqueResult();
		return hearingDecisionNote;
	}

	/**{@inheritDoc} */
	@Override
	public HearingDecisionNote findExcluding(
			final BoardHearingDecision boardDecision, final Date date, 
			final String description,
			final HearingDecisionNote excludedHearingDecisionNote) {
		HearingDecisionNote hearingDecisionNote = (HearingDecisionNote) this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(BOARD_DECISION_PARAM_NAME, boardDecision)
				.setParameter(DATE_PARAM_NAME, date)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setParameter(EXCLUDED_HEARING_DECISION_NOTE_PARAM_NAME, 
						excludedHearingDecisionNote)
				.uniqueResult();
		return hearingDecisionNote;
	}

	/**{@inheritDoc} */
	@Override
	public List<HearingDecisionNote> findByBoardDecision(
			final BoardHearingDecision boardDecision) {
		@SuppressWarnings("unchecked")
		List<HearingDecisionNote> hearingDecisionNotes = this
				.getSessionFactory().getCurrentSession()
				.getNamedQuery(FIND_BY_BOARD_DECISION_QUERY_NAME)
				.setParameter(BOARD_DECISION_PARAM_NAME, boardDecision)
				.list();
		return hearingDecisionNotes;
	}
}