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
package omis.response.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.response.domain.Grid;

/**
 * Data access object for grid.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Feb 26, 2019)
 * @since OMIS 3.0
 */
public interface GridDao extends GenericDao<Grid> {
	
	/**
	 * Returns the grid that matches the specified name.
	 * 
	 * @param name name
	 * @return grid
	 */
	Grid find(String name);
	
	/**
	 * Returns the grid that matches the specified name excluding the specified 
	 * grid.
	 * 
	 * @param name name
	 * @param excludedGrid excluded grid
	 * @return grid
	 */
	Grid findExcluding(String name, Grid excludedGrid);

	/**
	 * Returns a list of active grid.
	 * 
	 * @return list of active grid
	 */
	List<Grid> findActive();
}