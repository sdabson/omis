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
package omis.offenseterm.service;

import java.util.List;

import omis.court.domain.Court;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;

/**
* Service to manage offense term dockets.
*
*
* @author Josh Divine
* @version 0.0.1
* @since OMIS 3.0
*/
public interface OffenseTermDocketService {

	/**
	 * Updates a docket.
	 * 
	 * @param docket docket
	 * @param court court 
	 * @param docketValue docket value
	 * @return docket
	 * @throws DocketExistsException if docket already exists
	 */
	Docket update(Docket docket, Court court, String docketValue) 
			throws DocketExistsException;
	
	/**
	 * Returns a list of courts.
	 * 
	 * @return list of courts
	 */
	List<Court> findCourts();
}
