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
package omis.hearingparticipant.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.boardhearing.domain.BoardHearing;
import omis.person.domain.Person;

/**
 * Hearing Participant.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 12, 2018)
 *@since OMIS 3.0
 *
 */
public interface HearingParticipant extends Creatable, Updatable {
	
	/**
	 * Returns the ID of the  Hearing Participant.
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the ID of the  Hearing Participant.
	 * @param id - Long
	 */
	void setId(Long id);
	
	/**
	 * Returns the BoardHearing for the Hearing Participant.
	 * @return boardHearing - Board Hearing
	 */
	BoardHearing getBoardHearing();
	
	/**
	 * Sets the BoardHearing for the Hearing Participant.
	 * @param boardHearing - Board Hearing
	 */
	void setBoardHearing(BoardHearing boardHearing);
	
	/**
	 * Returns the Person for the Hearing Participant.
	 * @return person - Person
	 */
	Person getPerson();
	
	/**
	 * Sets the Person for the Hearing Participant.
	 * @param person - Person
	 */
	void setPerson(Person person);
	
	/**
	 * Returns the BoardApproved for the Hearing Participant.
	 * @return boardApproved - Boolean
	 */
	Boolean getBoardApproved();
	
	/**
	 * Sets the BoardApproved for the Hearing Participant.
	 * @param boardApproved - Boolean
	 */
	void setBoardApproved(Boolean boardApproved);
	
	/**
	 * Returns the Witness for the Hearing Participant.
	 * @return witness - Boolean
	 */
	Boolean getWitness();
	
	/**
	 * Sets the Witness for the Hearing Participant.
	 * @param witness - Boolean
	 */
	void setWitness(Boolean witness);
	
	/**
	 * Returns the FacilityApproved for the Hearing Participant.
	 * @return facilityApproved - Boolean
	 */
	Boolean getFacilityApproved();
	
	/**
	 * Sets the FacilityApproved for the Hearing Participant.
	 * @param facilityApproved - Boolean
	 */
	void setFacilityApproved(Boolean facilityApproved);
	
	/**
	 * Returns the Comments for the Hearing Participant.
	 * @return comments - String
	 */
	String getComments();
	
	/**
	 * Sets the Comments for the Hearing Participant.
	 * @param comments - String
	 */
	void setComments(String comments);
	
	/**
	 * Returns the Category for the Hearing Participant.
	 * @return category - HearingParticipantCategory
	 */
	HearingParticipantCategory getCategory();
	
	/**
	 * Sets the Category for the Hearing Participant.
	 * @param category - HearingParticipantCategory
	 */
	void setCategory(HearingParticipantCategory category);
	
	/**
	 * Returns the Intent for the Hearing Participant.
	 * @return intent - HearingParticipantIntentCategory
	 */
	HearingParticipantIntentCategory getIntent();
	
	/**
	 * Sets the Intent for the Hearing Participant.
	 * @param intent - HearingParticipantIntentCategory
	 */
	void setIntent(HearingParticipantIntentCategory intent);
	
	/** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();
}
