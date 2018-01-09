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
package omis.paroleeligibility.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.paroleeligibility.domain.ParoleEligibilityNote;

/**
 * Parole eligibility note item.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Dec 5, 2017)
 * @since OMIS 3.0
 */
public class ParoleEligibilityNoteItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ParoleEligibilityNote note;
	
	private String description;
	
	private Date date;
	
	private ParoleEligibilityNoteItemOperation operation;

	/**
	 * Instantiates a default instance of parole eligibility note item.
	 */
	public ParoleEligibilityNoteItem() {
		// Default constructor.
	}
	
	/**
	 * Returns the parole eligibility note.
	 * 
	 * @return parole eligibility note
	 */
	public ParoleEligibilityNote getNote() {
		return this.note;
	}
	
	/**
	 * Sets the parole eligibility note.
	 * 
	 * @param note
	 */
	public void setNote(
			final ParoleEligibilityNote note) {
		this.note = note;
	}
	
	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
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
	 * Returns the note.
	 * 
	 * @return note
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the note.
	 * 
	 * @param note note
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Returns the parole eligibility note item operation.
	 * 
	 * @return parole eligibility note item operation
	 */
	public ParoleEligibilityNoteItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the parole eligibility note item operation.
	 * 
	 * @param operation parole eligibility note item operation
	 */
	public void setOperation(
			final ParoleEligibilityNoteItemOperation operation) {
		this.operation = operation;
	}
	
}
