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
package omis.response.service;

import java.util.List;

import omis.response.domain.Grid;
import omis.response.domain.Response;
import omis.response.domain.ResponseCategory;
import omis.response.domain.ResponseLevel;
import omis.response.exception.ResponseExistsException;

/**
 * Response service.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Feb 26, 2019)
 * @since OMIS 3.0
 */
public interface ResponseService {

	/**
	 * Creates a new response.
	 * 
	 * @param description description
	 * @param grid grid
	 * @param category response category
	 * @param level response level
	 * @param valid valid
	 * @return response
	 * @throws ResponseExistsException thrown if response already exists
	 */
	Response createResponse(String description, Grid grid, 
			ResponseCategory category, ResponseLevel level, Boolean valid) 
					throws ResponseExistsException;
	
	/**
	 * Updates an existing response.
	 * 
	 * @param response response
	 * @param description description
	 * @param level response level
	 * @param valid valid
	 * @return response
	 * @throws ResponseExistsException thrown if response already exists
	 */
	Response updateResponse(Response response, String description, 
			ResponseLevel level, Boolean valid) throws ResponseExistsException;
	
	/**
	 * Removes a response.
	 * 
	 * @param response response
	 */
	void removeResponse(Response response);
	
	/**
	 * Returns a list of grids.
	 * 
	 * @return list of grids
	 */
	List<Grid> findGrids();
	
	/**
	 * Returns a list of response categories.
	 * 
	 * @return list of response categories
	 */
	List<ResponseCategory> findResponseCategories();
	
	/**
	 * Returns a list of response levels.
	 * 
	 * @return list of response levels
	 */
	List<ResponseLevel> findResponseLevels();
}