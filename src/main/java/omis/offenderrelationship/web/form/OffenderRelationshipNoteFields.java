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
package omis.offenderrelationship.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.relationship.domain.RelationshipNoteCategory;

/**
 * Fields for offender relationship notes.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Jan 9, 2018)
 * @since OMIS 3.0
 */
public class OffenderRelationshipNoteFields
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private RelationshipNoteCategory category;
	
	private Date date;
	
	private String value;
	
	/**
	 * Instantiates fields for offender relationship notes.
	 */
	public OffenderRelationshipNoteFields() {
		// Default instantiation
	}
	
	/**
	 * Sets category.
	 * 
	 * @param category category
	 */
	public void setCategory(final RelationshipNoteCategory category) {
		this.category = category;
	}
	
	/**
	 * Returns category.
	 * 
	 * @return category
	 */
	public RelationshipNoteCategory getCategory() {
		return this.category;
	}
	
	/**
	 * Sets date.
	 * 
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}
	
	/**
	 * Returns date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}
	
	/**
	 * Sets value.
	 * 
	 * @param value value
	 */
	public void setValue(final String value) {
		this.value = value;
	}
	
	/**
	 * Returns value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return this.value;
	}
}