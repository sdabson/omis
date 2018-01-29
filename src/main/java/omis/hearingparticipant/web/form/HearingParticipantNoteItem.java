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
import java.util.Date;
import omis.hearingparticipant.domain.HearingParticipantNote;

/**
 * Hearing Participant Note Item.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 18, 2018)
 *@since OMIS 3.0
 *
 */
public class HearingParticipantNoteItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private HearingParticipantNote hearingParticipantNote;
	
	private String description;
	
	private Date date;
	
	private HearingParticipantItemOperation itemOperation;
	
	/**
	 * Default constructor for Hearing Participant Note Item. 
	 */
	public HearingParticipantNoteItem() {
	}

	/**
	 * Returns the hearingParticipantNote.
	 * @return hearingParticipantNote - HearingParticipantNote
	 */
	public HearingParticipantNote getHearingParticipantNote() {
		return this.hearingParticipantNote;
	}

	/**
	 * Sets the hearingParticipantNote.
	 * @param hearingParticipantNote - HearingParticipantNote
	 */
	public void setHearingParticipantNote(
			final HearingParticipantNote hearingParticipantNote) {
		this.hearingParticipantNote = hearingParticipantNote;
	}

	/**
	 * Returns the description.
	 * @return description - String
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 * @param description - String
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Returns the date.
	 * @return date - Date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 * @param date - Date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the itemOperation.
	 * @return itemOperation - HearingParticipantItemOperation
	 */
	public HearingParticipantItemOperation getItemOperation() {
		return this.itemOperation;
	}

	/**
	 * Sets the itemOperation.
	 * @param itemOperation - HearingParticipantItemOperation
	 */
	public void setItemOperation(
			final HearingParticipantItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	
	
}
