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
package omis.paroleeligibility.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.paroleeligibility.domain.AppearanceCategory;

/**
 * Appearance category data access object.
 *
 * @author Trevor Isles
 * @version 0.1.0 (Nov 8, 2017)
 * @since OMIS 3.0
 */
public interface AppearanceCategoryDao extends GenericDao<AppearanceCategory> {

	/**
	 * Finds all appearance categories.
	 * 
	 * @return all appearance categories
	 */
	List<AppearanceCategory> findAppearanceCategories();

	/**
	 * Finds an appearance category
	 * 
	 * @param name name of the appearance category
	 * @param valid whether the appearance category is valid
	 * @return appearance category
	 */
	AppearanceCategory find(String name);
	
	/**
	 * Finds an appearance category, not including the specified appearance 
	 * category
	 * 
	 * @param appearanceCategory appearance category to exclude
	 * @param name name of the appearance category
	 * @param valid whether an appearance category is valid
	 * @return appearance category, not including the specified appearance
	 * category
	 */
	AppearanceCategory findExcluding(AppearanceCategory appearanceCategory,
			String name);
	
}
