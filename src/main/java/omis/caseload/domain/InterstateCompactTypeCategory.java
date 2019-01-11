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
package omis.caseload.domain;

import java.io.Serializable;

/**
 * Interstate compact type category.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 6, 2018)
 * @since OMIS 3.0
 */
public interface InterstateCompactTypeCategory extends Serializable {

	/**
	 * Sets the ID of the interstate compact type category.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID of the interstate compact type category.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the name.
	 * 
	 * @param name name
	 */
	void setName(String name);
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	String getName();
	
	/**
	 * Sets whether the interstate compact correctional status is valid.
	 * 
	 * @param valid valid
	 */
	void setValid(Boolean valid);
	
	/**
	 * Returns whether the interstate compact correctional status is valid.
	 * 
	 * return valid
	 */
	Boolean getValid();
}