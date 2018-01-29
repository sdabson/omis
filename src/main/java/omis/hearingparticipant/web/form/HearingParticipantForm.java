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
package omis.hearingparticipant.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import omis.hearingparticipant.domain.HearingParticipantCategory;
import omis.hearingparticipant.domain.HearingParticipantIntentCategory;
import omis.person.domain.Person;

/**
 * Hearing Participant Form.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 18, 2018)
 *@since OMIS 3.0
 *
 */
public class HearingParticipantForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private HearingParticipantCategory category;
	
	private Person person;
	
	private Boolean boardApproved;
	
	private Boolean witnessed;
	
	private Boolean facilityApproved;
	
	private HearingParticipantIntentCategory intent;
	
	private String comments;
	
	private List<HearingParticipantNoteItem> hearingParticipantNoteItems =
			new ArrayList<HearingParticipantNoteItem>();
	
	/**
	 * Default constructor for Hearing Participant Form.
	 */
	public HearingParticipantForm() {
	}

	/**
	 * Returns the category.
	 * @return category - HearingParticipantCategory
	 */
	public HearingParticipantCategory getCategory() {
		return this.category;
	}

	/**
	 * Sets the category.
	 * @param category - HearingParticipantCategory
	 */
	public void setCategory(final HearingParticipantCategory category) {
		this.category = category;
	}

	/**
	 * Returns the person.
	 * @return person - Person
	 */
	public Person getPerson() {
		return this.person;
	}

	/**
	 * Sets the person.
	 * @param person - Person
	 */
	public void setPerson(final Person person) {
		this.person = person;
	}

	/**
	 * Returns the boardApproved.
	 * @return boardApproved - Boolean
	 */
	public Boolean getBoardApproved() {
		return this.boardApproved;
	}

	/**
	 * Sets the boardApproved.
	 * @param boardApproved - Boolean
	 */
	public void setBoardApproved(final Boolean boardApproved) {
		this.boardApproved = boardApproved;
	}

	/**
	 * Returns the witnessed.
	 * @return witnessed - Boolean
	 */
	public Boolean getWitnessed() {
		return this.witnessed;
	}

	/**
	 * Sets the witnessed.
	 * @param witnessed - Boolean
	 */
	public void setWitnessed(final Boolean witnessed) {
		this.witnessed = witnessed;
	}

	/**
	 * Returns the intent.
	 * @return intent - HearingParticipantIntentCategory
	 */
	public HearingParticipantIntentCategory getIntent() {
		return this.intent;
	}

	/**
	 * Sets the intent.
	 * @param intent - HearingParticipantIntentCategory
	 */
	public void setIntent(final HearingParticipantIntentCategory intent) {
		this.intent = intent;
	}

	/**
	 * Returns the hearingParticipantNoteItems.
	 * @return hearingParticipantNoteItems - List of HearingParticipantNoteItems
	 */
	public List<HearingParticipantNoteItem> getHearingParticipantNoteItems() {
		return this.hearingParticipantNoteItems;
	}

	/**
	 * Sets the hearingParticipantNoteItems.
	 * @param hearingParticipantNoteItems - List of HearingParticipantNoteItems
	 */
	public void setHearingParticipantNoteItems(
			final List<HearingParticipantNoteItem>
				hearingParticipantNoteItems) {
		this.hearingParticipantNoteItems = hearingParticipantNoteItems;
	}

	/**
	 * Returns the facilityApproved.
	 * @return facilityApproved - Boolean
	 */
	public Boolean getFacilityApproved() {
		return this.facilityApproved;
	}

	/**
	 * Sets the facilityApproved.
	 * @param facilityApproved - Boolean
	 */
	public void setFacilityApproved(final Boolean facilityApproved) {
		this.facilityApproved = facilityApproved;
	}

	/**
	 * Returns the comments.
	 * @return comments - String
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * Sets the comments.
	 * @param comments - String
	 */
	public void setComments(final String comments) {
		this.comments = comments;
	}
}
