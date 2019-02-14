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
package omis.earlyreleasetracking.dao;

import java.util.List;
import omis.dao.GenericDao;
import omis.earlyreleasetracking.domain.EarlyReleaseStatusCategory;

/**
 * Early Release Status Category Data Acess Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public interface EarlyReleaseStatusCategoryDao
		extends GenericDao<EarlyReleaseStatusCategory> {
	
	/**
	 * Finds an Early Release Status Category with the specified Name.
	 * 
	 * @param name name
	 * @return Early Release Status Category with the specified Name.
	 */
	EarlyReleaseStatusCategory find(String name);
	
	/**
	 * Finds an Early Release Status Category with the specified Name
	 * excluding the specified Early Release Status Category.
	 * 
	 * @param name name
	 * @param earlyReleaseStatusCategoryExcluding Early Release Status Category
	 * to exclude
	 * @return Early Release Status Category with the specified Name
	 * excluding the specified Early Release Status Category.
	 */
	EarlyReleaseStatusCategory findExcluding(String name,
			EarlyReleaseStatusCategory
				earlyReleaseStatusCategoryExcluding);
	
	/**
	 * Returns a list of all valid Early Release Status Categories.
	 * 
	 * @return List of all valid Early Release Status Categories.
	 */
	List<EarlyReleaseStatusCategory> findAllCategories();
}
