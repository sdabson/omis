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

import java.util.Date;
import java.util.List;
import omis.dao.GenericDao;
import omis.hearingparticipant.domain.HearingParticipant;
import omis.hearingparticipant.domain.HearingParticipantNote;

/**
 * Hearing Participant Note Data Access Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 12, 2018)
 *@since OMIS 3.0
 *
 */
public interface HearingParticipantNoteDao
		extends GenericDao<HearingParticipantNote> {
	
	/**
	 * Returns a Hearing Participant Note with the specified properties.
	 * 
	 * @param description - String
	 * @param date - Date
	 * @param hearingParticipant - Hearing Participant
	 * @return Hearing Participant Note with the specified properties.
	 */
	HearingParticipantNote find(String description, Date date,
			HearingParticipant hearingParticipant);
	
	/**
	 * Returns a Hearing Participant Note with the specified properties
	 * excluding the given Hearing Participant Note.
	 * 
	 * @param description - String
	 * @param date - Date
	 * @param hearingParticipant - Hearing Participant
	 * @param hearingParticipantNoteExcluded - Hearing Participant Note to
	 * exclude
	 * @return Hearing Participant Note with the specified properties
	 * excluding the given Hearing Participant Note.
	 */
	HearingParticipantNote findExcluding(String description, Date date,
			HearingParticipant hearingParticipant,
			HearingParticipantNote hearingParticipantNoteExcluded);
	
	/**
	 * Returns a list of all Hearing Participant Notes for the specified
	 * Hearing Participant.
	 * 
	 * @param hearingParticipant - Hearing Participant
	 * @return List of all Hearing Participant Notes for the specified
	 * Hearing Participant.
	 */
	List<HearingParticipantNote> findByHearingParticipant(
			HearingParticipant hearingParticipant);
	
}
