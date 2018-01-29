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

import org.hibernate.SessionFactory;
import omis.boardhearing.domain.BoardHearing;
import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.hearingparticipant.dao.HearingParticipantDao;
import omis.hearingparticipant.domain.HearingParticipant;
import omis.hearingparticipant.domain.HearingParticipantCategory;
import omis.person.domain.Person;

/**
 * Hearing Participant DAO Hibernate Implementation.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 16, 2018)
 *@since OMIS 3.0
 *
 */
public class HearingParticipantDaoHibernateImpl
		extends GenericHibernateDaoImpl<HearingParticipant>
		implements HearingParticipantDao {
	
	private static final String FIND_QUERY_NAME = "findHearingParticipant";
	
	private static final String FIND_EXCLUDING_QUERY_NAME =
			"findHearingParticipantExcluding";
	
	private static final String BOARD_HEARING_PARAM_NAME = "boardHearing";
	
	private static final String CATEGORY_PARAM_NAME = "category";
	
	private static final String PERSON_PARAM_NAME = "person";
	
	private static final String HEARING_PARTICIPANT_PARAM_NAME =
			"hearingParticipant";
	
	/**
	 * @param sessionFactory - Session Factory
	 * @param entityName - String entity name
	 */
	protected HearingParticipantDaoHibernateImpl(
			final SessionFactory sessionFactory, final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/**{@inheritDoc} */
	@Override
	public HearingParticipant find(final BoardHearing boardHearing,
			final Person person, final HearingParticipantCategory category) {
		HearingParticipant hearingParticipant =
				(HearingParticipant) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_QUERY_NAME)
				.setParameter(BOARD_HEARING_PARAM_NAME, boardHearing)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(PERSON_PARAM_NAME, person)
				.uniqueResult();
		
		return hearingParticipant;
	}

	/**{@inheritDoc} */
	@Override
	public HearingParticipant findExcluding(final BoardHearing boardHearing,
			final Person person, final HearingParticipantCategory category,
			final HearingParticipant hearingParticipantExcluded) {
		HearingParticipant hearingParticipant =
				(HearingParticipant) this.getSessionFactory()
				.getCurrentSession().getNamedQuery(FIND_EXCLUDING_QUERY_NAME)
				.setParameter(BOARD_HEARING_PARAM_NAME, boardHearing)
				.setParameter(CATEGORY_PARAM_NAME, category)
				.setParameter(PERSON_PARAM_NAME, person)
				.setParameter(HEARING_PARTICIPANT_PARAM_NAME,
						hearingParticipantExcluded)
				.uniqueResult();
		
		return hearingParticipant;
	}

}
