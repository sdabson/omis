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
package omis.staff.web.form;

import java.io.Serializable;

/**
 * Form for staff titles.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 5, 2014)
 * @since OMIS 3.0
 */
public class StaffTitleForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private Short sortOrder;
	
	private Boolean valid;
	
	/** Instantiates a form for staff titles. */
	public StaffTitleForm() {
		// Default instantiation
	}

	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Returns the sort order.
	 * 
	 * @return sort order
	 */
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/**
	 * Sets the sort order.
	 * 
	 * @param sortOrder sort order
	 */
	public void setSortOrder(final Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * Returns whether the staff title is valid.
	 * 
	 * @return whether staff title is valid
	 */
	public Boolean getValid() {
		return this.valid;
	}

	/**
	 * Sets whether the staff title is valid.
	 * 
	 * @param valid whether staff title is valid
	 */
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}
}