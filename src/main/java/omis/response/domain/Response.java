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
package omis.response.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Response.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Feb 26, 2019)
 * @since OMIS 3.0
 */
public interface Response extends Creatable, Updatable {

	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the description.
	 * 
	 * @param description description
	 */
	void setDescription(String description);
	
	/**
	 * Returns the description.
	 * 
	 * @return description
	 */
	String getDescription();
	
	/**
	 * Sets the grid.
	 * 
	 * @param grid grid
	 */
	void setGrid(Grid grid);
	
	/**
	 * Returns the grid.
	 * 
	 * @return grid
	 */
	Grid getGrid();
	
	/**
	 * Sets the category.
	 * 
	 * @param category category
	 */
	void setCategory(ResponseCategory category);
	
	/**
	 * Returns the category.
	 * 
	 * @return category
	 */
	ResponseCategory getCategory();
	
	/**
	 * Sets the level.
	 * 
	 * @param level level
	 */
	void setLevel(ResponseLevel level);
	
	/**
	 * Returns the level.
	 * 
	 * @return level
	 */
	ResponseLevel getLevel();
	
	/**
	 * Sets whether the grid is valid or not.
	 * 
	 * @param valid whether the grid is valid or not
	 */
	void setValid(Boolean valid);
	
	/**
	 * Returns whether the grid is valid or not.
	 * 
	 * @return whether the grid is valid or not
	 */
	Boolean getValid();
}