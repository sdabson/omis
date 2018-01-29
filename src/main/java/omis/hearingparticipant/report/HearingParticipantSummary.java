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
package omis.hearingparticipant.report;

import java.io.Serializable;
import omis.hearingparticipant.domain.HearingParticipantCategory;

/**
 * Hearing Participant Summary.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 17, 2018)
 *@since OMIS 3.0
 *
 */
public class HearingParticipantSummary implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long hearingParticipantId;
	
	private final String lastName;
	
	private final String firstName;
	
	private final String middleName;
	
	private final HearingParticipantCategory category;
	
	private final Boolean facilityApproved;
	
	private final Boolean boardApproved;

	/**
	 * @param hearingParticipantId - Long Hearing Participant ID
	 * @param lastName - String last name
	 * @param firstName - String first name
	 * @param middleName - String middle name
	 * @param category - Hearing Participant Category
	 * @param facilityApproved - Boolean facility Approved
	 * @param boardApproved - Boolean Board Approved
	 */
	public HearingParticipantSummary(final Long hearingParticipantId,
			final String lastName, final String firstName,
			final String middleName, final HearingParticipantCategory category,
			final Boolean facilityApproved, final Boolean boardApproved) {
		this.hearingParticipantId = hearingParticipantId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.category = category;
		this.facilityApproved = facilityApproved;
		this.boardApproved = boardApproved;
	}
	
	/**
	 * Returns the hearingParticipantId.
	 * @return hearingParticipantId - Long
	 */
	public Long getHearingParticipantId() {
		return this.hearingParticipantId;
	}
	
	/**
	 * Returns the lastName.
	 * @return lastName - String
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Returns the firstName.
	 * @return firstName - String
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Returns the middleName.
	 * @return middleName - String
	 */
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * Returns the category.
	 * @return category - HearingParticipantCategory
	 */
	public HearingParticipantCategory getCategory() {
		return this.category;
	}

	/**
	 * Returns the facilityApproved.
	 * @return facilityApproved - Boolean
	 */
	public Boolean getFacilityApproved() {
		return this.facilityApproved;
	}

	/**
	 * Returns the boardApproved.
	 * @return boardApproved - Boolean
	 */
	public Boolean getBoardApproved() {
		return this.boardApproved;
	}
}
