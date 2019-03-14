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

import omis.dao.GenericDao;
import omis.response.domain.Response;

/**
 * Data access object for response.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Feb 26, 2019)
 * @since OMIS 3.0
 */
public interface ResponseDao extends GenericDao<Response> {

	/**
	 * Returns the response with the specified description.
	 * 
	 * @param description description
	 * @return response
	 */
	Response find(String description);
	
	/**
	 * Returns the response with the specified description excluding the 
	 * specified response.
	 * 
	 * @param description description
	 * @param excludedResponse excluded response
	 * @return response
	 */
	Response findExcluding(String description, Response excludedResponse);
}