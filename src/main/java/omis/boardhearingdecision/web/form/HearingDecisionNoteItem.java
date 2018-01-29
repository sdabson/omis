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
package omis.boardhearingdecision.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.boardhearingdecision.domain.HearingDecisionNote;

/**
 * Hearing decision note item.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 23, 2018)
 * @since OMIS 3.0
 */
public class HearingDecisionNoteItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private HearingDecisionNote hearingDecisionNote;
	
	private Date date;
	
	private String value;
	
	private HearingDecisionNoteItemOperation operation;
	
	/** Instantiates a default note on a board hearing decision form. */
	public HearingDecisionNoteItem() {
		// Default instantiation
	}

	/**
	 * Returns the hearing decision note.
	 * 
	 * @return hearing decision note
	 */
	public HearingDecisionNote getHearingDecisionNote() {
		return hearingDecisionNote;
	}

	/**
	 * Sets the board hearing decision note.
	 * 
	 * @param hearingDecisionNote hearing decision note
	 */
	public void setHearingDecisionNote(
			final HearingDecisionNote hearingDecisionNote) {
		this.hearingDecisionNote = hearingDecisionNote;
	}

	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value value
	 */
	public void setValue(final String value) {
		this.value = value;
	}

	/**
	 * Returns the hearing decision note item operation.
	 * 
	 * @return hearing decision note item operation
	 */
	public HearingDecisionNoteItemOperation getOperation() {
		return operation;
	}

	/**
	 * Sets the hearing decision note item operation.
	 * 
	 * @param operation hearing decision note item operation
	 */
	public void setOperation(final HearingDecisionNoteItemOperation operation) {
		this.operation = operation;
	}
}
