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
package omis.staff.service;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.staff.domain.StaffTitle;

/**
 * Service for staff titles.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 6, 2014)
 * @since OMIS 3.0
 */
public interface StaffTitleService {

	/**
	 * Returns staff titles.
	 * 
	 * @return staff titles
	 */
	List<StaffTitle> findAll();
	
	/**
	 * Creates a new staff title.
	 * 
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid whether staff title is valid
	 * @return new staff title
	 * @throws DuplicateEntityFoundException if the staff titles exists
	 */
	StaffTitle create(String name, Short sortOrder, Boolean valid)
			throws DuplicateEntityFoundException;
	
	/**
	 * Updates a staff title,
	 * 
	 * @param staffTitle staff title to update
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid whether staff title is valid
	 * @return updated staff title
	 * @throws DuplicateEntityFoundException if the staff titles exists
	 */
	StaffTitle update(StaffTitle staffTitle, String name, Short sortOrder,
			Boolean valid)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes a staff title.
	 * 
	 * @param staffTitle staff title to remove
	 */
	void remove(StaffTitle staffTitle);
	
	/**
	 * Returns the highest sort order.
	 * 
	 * @return highest sort order
	 */
	short findHighestSortOrder();
}