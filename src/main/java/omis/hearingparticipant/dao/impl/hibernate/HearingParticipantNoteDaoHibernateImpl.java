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
package omis.hearingparticipant.dao.impl.hibernate;

import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.hearingparticipant.dao.HearingParticipantNoteDao;
import omis.hearingparticipant.domain.HearingParticipant;
import omis.hearingparticipant.domain.HearingParticipantNote;

/**
 * Hearing Participant Note DAO Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 16, 2018)
 *@since OMIS 3.0
 *
 */
public class HearingParticipantNoteDaoHibernateImpl
		extends GenericHibernateDaoImpl<HearingParticipantNote>
		implements HearingParticipantNoteDao {
	
	private static final String FIND_QUERY_NAME = "findHearingParticipantNote";
	
	private static final String FIND_EXCLUDING_QUERY_NAME =
			"findHearingParticipantNoteExcluding";
	
	private static final String FIND_BY_HEARING_PARTICIPANT_QUERY_NAME =
			"findHearingParticipantNotesByHearingParticipant";
	
	private static final String DESCRIPTION_PARAM_NAME = "description";
	
	private static final String DATE_PARAM_NAME = "date";
	
	private static final String HEARING_PARTICIPANT_PARAM_NAME =
			"hearingParticipant";
	
	private static final String HEARING_PARTICIPANT_NOTE_PARAM_NAME =
			"hearingParticipantNote";
	
	/**
	 * @param sessionFactory - Session Factory
	 * @param entityName - String entity name
	 */
	protected HearingParticipantNoteDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}

	/**{@inheritDoc} */
	@Override
	public HearingParticipantNote find(final String description,
			final Date date, final HearingParticipant hearingParticipant) {
		HearingParticipantNote note = (HearingParticipantNote)
				this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(HEARING_PARTICIPANT_PARAM_NAME,
						hearingParticipant)
				.uniqueResult();
		
		return note;
	}

	/**{@inheritDoc} */
	@Override
	public HearingParticipantNote findExcluding(final String description,
			final Date date, final HearingParticipant hearingParticipant,
			final HearingParticipantNote hearingParticipantNoteExcluded) {
		HearingParticipantNote note = (HearingParticipantNote)
				this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(DESCRIPTION_PARAM_NAME, description)
				.setTimestamp(DATE_PARAM_NAME, date)
				.setParameter(HEARING_PARTICIPANT_PARAM_NAME,
						hearingParticipant)
				.setParameter(HEARING_PARTICIPANT_NOTE_PARAM_NAME,
						hearingParticipantNoteExcluded)
				.uniqueResult();
		
		return note;
	}

	/**{@inheritDoc} */
	@Override
	public List<HearingParticipantNote> findByHearingParticipant(
			final HearingParticipant hearingParticipant) {
		@SuppressWarnings("unchecked")
		List<HearingParticipantNote> notes =
				this.getSessionFactory().getCurrentSession().getNamedQuery(
						FIND_BY_HEARING_PARTICIPANT_QUERY_NAME)
				.setParameter(HEARING_PARTICIPANT_PARAM_NAME,
						hearingParticipant)
				.list();
		
		return notes;
	}

}
