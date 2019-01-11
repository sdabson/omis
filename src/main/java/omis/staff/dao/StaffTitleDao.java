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
package omis.staff.dao;

import omis.dao.GenericDao;
import omis.staff.domain.StaffTitle;

/**
 * Data access object for staff titles.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Oct 21, 2013)
 * @since OMIS 3.0
 */
public interface StaffTitleDao
		extends GenericDao<StaffTitle> {

	/**
	 * Returns the highest sort order.
	 * 
	 * @return highest sort order
	 */
	short findHighestSortOrder();
	
	/**
	 * Returns staff title with name.
	 * 
	 * @param name name
	 * @return staff title with name or {@code null} if no staff title exists
	 * with the name.
	 */
	StaffTitle find(String name);
	
	/**
	 * Returns the staff title with the name excluding staff titles specified.
	 * 
	 * @param name name
	 * @param excludedStaffTitles staff titles to exclude
	 * @return staff title with name or {@code null} if no staff title exists
	 * with the name
	 */
	StaffTitle findExcluding(String name, StaffTitle... excludedStaffTitles);
}