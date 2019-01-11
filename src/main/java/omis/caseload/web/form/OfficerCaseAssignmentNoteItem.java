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
package omis.caseload.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.caseload.domain.OfficerCaseAssignmentNote;

/**
 * Officer case assignment note item.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 10, 2018)
 * @since OMIS 3.0
 */
public class OfficerCaseAssignmentNoteItem implements Serializable {

	private static final long serialVersionUID = 1L;

private OfficerCaseAssignmentNote officerCaseAssignmentNote;
	
	private Date date;
	
	private String value;
	
	private ItemOperation operation;
	
	/** Instantiates a default note on a officer case assignment form. */
	public OfficerCaseAssignmentNoteItem() {
		// Default instantiation
	}

	/**
	 * Returns the officer case assignment note.
	 * 
	 * @return officer case assignment note
	 */
	public OfficerCaseAssignmentNote getOfficerCaseAssignmentNote() {
		return officerCaseAssignmentNote;
	}

	/**
	 * Sets the officer case assignment note.
	 * 
	 * @param officerCaseAssignmentNote officer case assignment note
	 */
	public void setOfficerCaseAssignmentNote(
			final OfficerCaseAssignmentNote officerCaseAssignmentNote) {
		this.officerCaseAssignmentNote = officerCaseAssignmentNote;
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
	 * Returns the item operation.
	 * 
	 * @return item operation
	 */
	public ItemOperation getOperation() {
		return operation;
	}

	/**
	 * Sets the item operation.
	 * 
	 * @param operation item operation
	 */
	public void setOperation(final ItemOperation operation) {
		this.operation = operation;
	}
}