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
package omis.response.web.form;

import java.io.Serializable;

import omis.response.domain.Grid;
import omis.response.domain.ResponseCategory;
import omis.response.domain.ResponseLevel;

/**
 * Response search form.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Mar 6, 2019)
 * @since OMIS 3.0
 */
public class ResponseSearchForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String description;
	
	private Grid grid;
	
	private ResponseCategory category;
	
	private ResponseLevel level;
	
	/** Instantiates form to search responses. */
	public ResponseSearchForm() {
		// Default constructor
	}

	/**
	 * Returns the description.
	 *
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description description
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Returns the grid.
	 *
	 * @return grid
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * Sets the grid.
	 *
	 * @param grid grid
	 */
	public void setGrid(final Grid grid) {
		this.grid = grid;
	}

	/**
	 * Returns the category.
	 *
	 * @return category
	 */
	public ResponseCategory getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category category
	 */
	public void setCategory(final ResponseCategory category) {
		this.category = category;
	}

	/**
	 * Returns the level.
	 *
	 * @return level
	 */
	public ResponseLevel getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level level
	 */
	public void setLevel(final ResponseLevel level) {
		this.level = level;
	}
}