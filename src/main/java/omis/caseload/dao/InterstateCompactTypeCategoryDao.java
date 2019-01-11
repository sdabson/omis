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

import omis.caseload.domain.InterstateCompactTypeCategory;
import omis.dao.GenericDao;

/**
 * Data access object for interstate compact type category.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public interface InterstateCompactTypeCategoryDao 
		extends GenericDao<InterstateCompactTypeCategory> {

	/**
	 * Returns the interstate compact type category for the specified name.
	 * 
	 * @param name name
	 * @return interstate compact type category
	 */
	InterstateCompactTypeCategory find(String name);
	
	/**
	 * Returns the interstate compact type category for the specified name 
	 * excluding the specified interstate compact type category.
	 * 
	 * @param name name
	 * @param excludedCategory excluded interstate compact type category
	 * @return interstate compact type category
	 */
	InterstateCompactTypeCategory findExcluding(String name, 
			InterstateCompactTypeCategory excludedCategory);
	
	/**
	 * Returns a list of interstate compact type categories.
	 * 
	 * @return list of interstate compact type categories
	 */
	List<InterstateCompactTypeCategory> findActive();
}