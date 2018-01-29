/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General License for more details.
 *
 * You should have received a copy of the GNU General License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.hearingparticipant.service;

import java.util.Date;
import java.util.List;
import omis.boardhearing.domain.BoardHearing;
import omis.exception.DuplicateEntityFoundException;
import omis.hearingparticipant.domain.HearingParticipant;
import omis.hearingparticipant.domain.HearingParticipantCategory;
import omis.hearingparticipant.domain.HearingParticipantIntentCategory;
import omis.hearingparticipant.domain.HearingParticipantNote;
import omis.person.domain.Person;

/**
 * Hearing Participant Service.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 16, 2018)
 *@since OMIS 3.0
 *
 */
public interface HearingParticipantService {
	
	/**
	 * Creates a Hearing Participant with the specified properties.
	 * 
	 * @param boardHearing - Board Hearing
	 * @param person - Person
	 * @param category - Hearing Participant Category
	 * @param boardApproved - Boolean Board Approved
	 * @param witness - Boolean Witness
	 * @param facilityApproved - Boolean Facility Approved
	 * @param intent - Hearing Participant Intent Category
	 * @param comments - String comments
	 * @return Newly created Hearing Participant
	 * @throws DuplicateEntityFoundException - When a Hearing Participant
	 * already exists with the given category for the specified Person.
	 */
	HearingParticipant createHearingParticipant(BoardHearing boardHearing,
			Person person, HearingParticipantCategory category,
			Boolean boardApproved, Boolean witness,
			Boolean facilityApproved,
			HearingParticipantIntentCategory intent,
			String comments)
				throws DuplicateEntityFoundException;
	
	/**
	 * Updates a Hearing Participant with the specified properties.
	 * 
	 * @param hearingParticipant - Hearing Participant to update
	 * @param person - Person
	 * @param category - Hearing Participant Category
	 * @param boardApproved - Boolean Board Approved
	 * @param witness - Boolean Witness
	 * @param facilityApproved - Boolean Facility Approved
	 * @param intent - Hearing Participant Intent Category
	 * @param comments - String comments
	 * @return Updated Hearing Participant
	 * @throws DuplicateEntityFoundException - When a Hearing Participant
	 * already exists with the given category for the specified Person.
	 */
	HearingParticipant updateHearingParticipant(
			HearingParticipant hearingParticipant, Person person,
			HearingParticipantCategory category,
			Boolean boardApproved, Boolean witness,
			Boolean facilityApproved,
			HearingParticipantIntentCategory intent,
			String comments)
				throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified Hearing Participant.
	 * 
	 * @param hearingParticipant - Hearing Participant to remove
	 */
	void removeHearingParticipant(HearingParticipant hearingParticipant);
	
	/**
	 * Creates a Hearing Participant Note with the given properties.
	 * 
	 * @param hearingParticipant - Hearing Participant
	 * @param description - String description
	 * @param date - Date
	 * @return Newly created Hearing Participant Note
	 * @throws DuplicateEntityFoundException - When a Hearing Participant Note
	 * already exists with the given date and description for the specified
	 * Hearing Participant.
	 */
	HearingParticipantNote createHearingParticipantNote(
			HearingParticipant hearingParticipant,
			String description, Date date)
				throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified Hearing Participant Note with the given
	 * properties.
	 * 
	 * @param hearingParticipantNote - Hearing Participant Note to update
	 * @param hearingParticipant - Hearing Participant
	 * @param description - String description
	 * @param date - Date
	 * @return Updated Hearing Participant Note
	 * @throws DuplicateEntityFoundException - When a Hearing Participant Note
	 * already exists with the given date and description for the specified
	 * Hearing Participant.
	 */
	HearingParticipantNote updateHearingParticipantNote(
			HearingParticipantNote hearingParticipantNote,
			HearingParticipant hearingParticipant,
			String description, Date date)
				throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified Hearing Participant Note.
	 * 
	 * @param hearingParticipantNote - Hearing Participant Note to remove.
	 */
	void removeHearingParticipantNote(
			HearingParticipantNote hearingParticipantNote);
	
	/**
	 * Returns a list of all Hearing Participant Notes for the specified
	 * Hearing Participant.
	 * 
	 * @param hearingParticipant - Hearing Participant
	 * @return List of all Hearing Participant Notes for the specified
	 * Hearing Participant.
	 */
	List<HearingParticipantNote> findHearingParticipantNotesByParticipant(
				HearingParticipant hearingParticipant);
	
	/**
	 * Returns a list of all HearingParticipantIntentCategorys.
	 * @return List of all HearingParticipantIntentCategorys
	 */
	List<HearingParticipantIntentCategory>
		findHearingParticipantIntentCategories();
}
