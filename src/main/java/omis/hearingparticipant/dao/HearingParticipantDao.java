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
package omis.hearingparticipant.dao;

import omis.boardhearing.domain.BoardHearing;
import omis.dao.GenericDao;
import omis.hearingparticipant.domain.HearingParticipant;
import omis.hearingparticipant.domain.HearingParticipantCategory;
import omis.person.domain.Person;

/**
 * Hearing Participant Data Access Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 12, 2018)
 *@since OMIS 3.0
 *
 */
public interface HearingParticipantDao extends GenericDao<HearingParticipant> {
	
	/**
	 * Returns the Hearing Participant with the specified properties.
	 * 
	 * @param boardHearing - Board Hearing
	 * @param person - Person
	 * @param category - Hearing Participant Category
	 * @return Hearing Participant with the specified properties.
	 */
	HearingParticipant find(BoardHearing boardHearing, Person person,
			HearingParticipantCategory category);
	
	/**
	 * Returns a Hearing Participant with the specified properties excluding
	 * the specified Hearing Participant.
	 * 
	 * @param boardHearing - Board Hearing
	 * @param person - Person
	 * @param category - Hearing Participant Category
	 * @param hearingParticipantExcluded - Hearing Participant to exclude
	 * @return Hearing Participant with the specified properties excluding
	 * the specified Hearing Participant.
	 */
	HearingParticipant findExcluding(BoardHearing boardHearing, Person person,
			HearingParticipantCategory category,
			HearingParticipant hearingParticipantExcluded);
	
}
