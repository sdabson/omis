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
import omis.earlyreleasetracking.domain.EarlyReleaseJudgeResponseCategory;

/**
 * Early Release Judge Response Category Data Access Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public interface EarlyReleaseJudgeResponseCategoryDao
		extends GenericDao<EarlyReleaseJudgeResponseCategory> {
	
	/**
	 * Finds an Early Release Judge Response Category with the specified Name.
	 * 
	 * @param name name
	 * @return Early Release Judge Response Category with the specified Name.
	 */
	EarlyReleaseJudgeResponseCategory find(String name);
	
	/**
	 * Finds an Early Release Judge Response Category with the specified Name
	 * excluding the specified Early Release Judge Response Category.
	 * 
	 * @param name name
	 * @param earlyReleaseJudgeResponseCategoryExcluding Early Release Judge
	 * Response Category to exclude
	 * @return Early Release Judge Response Category with the specified Name
	 * excluding the specified Early Release Judge Response Category.
	 */
	EarlyReleaseJudgeResponseCategory findExcluding(String name,
			EarlyReleaseJudgeResponseCategory
				earlyReleaseJudgeResponseCategoryExcluding);
	
	/**
	 * Returns a list of all valid Early Release Judge Response Categories.
	 * 
	 * @return List of all valid Early Release Judge Response Categories.
	 */
	List<EarlyReleaseJudgeResponseCategory> findAllCategories();
}
