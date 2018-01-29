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
package omis.boardhearing.web.form;

import java.io.Serializable;
import java.util.Date;
import omis.boardhearing.domain.BoardHearingNote;

/**
 * Board Hearing Note Item.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Jan 2, 2018)
 *@since OMIS 3.0
 *
 */
public class BoardHearingNoteItem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private BoardHearingNote boardHearingNote;
	
	private Date date;
	
	private String description;
	
	private BoardHearingItemOperation itemOperation;
	
	/**
	 * Default Constructor for Board Hearing Note Item.
	 */
	public BoardHearingNoteItem() {
	}

	/**
	 * Returns the boardHearingNote.
	 * @return boardHearingNote - BoardHearingNote
	 */
	public BoardHearingNote getBoardHearingNote() {
		return this.boardHearingNote;
	}

	/**
	 * Sets the boardHearingNote.
	 * @param boardHearingNote - BoardHearingNote
	 */
	public void setBoardHearingNote(final BoardHearingNote boardHearingNote) {
		this.boardHearingNote = boardHearingNote;
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
	 * Returns the itemOperation.
	 * @return itemOperation - BoardHearingItemOperation
	 */
	public BoardHearingItemOperation getItemOperation() {
		return this.itemOperation;
	}

	/**
	 * Sets the itemOperation.
	 * @param itemOperation - BoardHearingItemOperation
	 */
	public void setItemOperation(
			final BoardHearingItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	
	
	
}
