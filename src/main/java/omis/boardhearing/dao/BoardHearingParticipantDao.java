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
package omis.boardhearing.dao;

import java.util.List;
import omis.boardhearing.domain.BoardHearing;
import omis.boardhearing.domain.BoardHearingParticipant;
import omis.dao.GenericDao;
import omis.paroleboardmember.domain.ParoleBoardMember;

/**
 * Board Hearing Participant Data Access Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 28, 2017)
 *@since OMIS 3.0
 *
 */
public interface BoardHearingParticipantDao
		extends GenericDao<BoardHearingParticipant> {
	
	
	/**
	 * Returns a Board Hearing Participant with the specified properties.
	 * 
	 * @param boardMember - Parole Board Member
	 * @param hearing - Board Hearing
	 * @return Board Hearing Participant with the specified properties.
	 */
	BoardHearingParticipant find(ParoleBoardMember boardMember,
			BoardHearing hearing);
	
	/**
	 * Returns a Board Hearing Participant with the specified properties
	 * excluding the given Board Hearing Participant.
	 * 
	 * @param boardMember - Parole Board Member
	 * @param hearing - Board Hearing
	 * @param boardHearingParticipantExcluded - Board Hearing Participant to
	 * exclude
	 * @return Board Hearing Participant with the specified properties
	 * excluding the given Board Hearing Participant.
	 */
	BoardHearingParticipant findExcluding(ParoleBoardMember boardMember,
			BoardHearing hearing,
			BoardHearingParticipant boardHearingParticipantExcluded);
	
	/**
	 * Returns a list of Board Hearing Participants for the specified
	 * Board Hearing.
	 * 
	 * @param hearing - Board Hearing
	 * @return List of Board Hearing Participants for the specified
	 * Board Hearing.
	 */
	List<BoardHearingParticipant> findByHearing(BoardHearing hearing);
}
