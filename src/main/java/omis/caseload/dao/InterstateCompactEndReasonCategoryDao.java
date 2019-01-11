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
package omis.caseload.dao;

import java.util.List;

import omis.caseload.domain.InterstateCompactEndReasonCategory;
import omis.dao.GenericDao;

/**
 * Data access object for interstate compact end reason category.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public interface InterstateCompactEndReasonCategoryDao 
		extends GenericDao<InterstateCompactEndReasonCategory> {

	/**
	 * Returns the interstate compact end reason category for the specified name.
	 * 
	 * @param name name
	 * @return interstate compact end reason category
	 */
	InterstateCompactEndReasonCategory find(String name);
	
	/**
	 * Returns the interstate compact end reason category for the specified name 
	 * excluding the specified interstate compact end reason category.
	 * 
	 * @param name name
	 * @param excludedEndReason excluded interstate compact end reason category
	 * @return interstate compact end reason category
	 */
	InterstateCompactEndReasonCategory findExcluding(String name, 
			InterstateCompactEndReasonCategory excludedEndReason);
	
	/**
	 * Returns a list of interstate compact end reason categories.
	 * 
	 * @return list of interstate compact end reason categories
	 */
	List<InterstateCompactEndReasonCategory> findActive();
}