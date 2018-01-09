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
package omis.supervision.web.form;

import java.io.Serializable;

import omis.supervision.domain.PlacementTermNote;

/**
 * Form item for placement term notes.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Dec 13, 2017)
 * @since OMIS 3.0
 */
public class PlacementTermNoteItem
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private PlacementTermNoteFields fields;
	
	private PlacementTermNote note;
	
	private PlacementTermNoteItemOperation operation;
	
	/** Instantiates form item for placement term notes. */
	public PlacementTermNoteItem() {
		// Default instantiation
	}
	
	/**
	 * Sets fields.
	 * 
	 * @param fields fields
	 */
	public void setFields(final PlacementTermNoteFields fields) {
		this.fields = fields;
	}
	
	/**
	 * Returns fields.
	 * 
	 * @return fields
	 */
	public PlacementTermNoteFields getFields() {
		return this.fields;
	}
	
	/**
	 * Sets note.
	 * 
	 * @param note note
	 */
	public void setNote(final PlacementTermNote note) {
		this.note = note;
	}
	
	/**
	 * Returns note.
	 * 
	 * @return note
	 */
	public PlacementTermNote getNote() {
		return this.note;
	}
	
	/**
	 * Sets operation.
	 * 
	 * @param operation operation
	 */
	public void setOperation(final PlacementTermNoteItemOperation operation) {
		this.operation = operation;
	}
	
	/**
	 * Returns operation.
	 * 
	 * @return operation
	 */
	public PlacementTermNoteItemOperation getOperation() {
		return this.operation;
	}
}