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

import omis.caseload.domain.InterstateCompactCorrectionalStatus;
import omis.dao.GenericDao;
import omis.supervision.domain.CorrectionalStatus;

/**
 * Data access object for interstate compact correctional status.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public interface InterstateCompactCorrectionalStatusDao 
		extends GenericDao<InterstateCompactCorrectionalStatus> {

	/**
	 * Returns the interstate compact correctional status for the specified 
	 * correctional status.
	 * 
	 * @param correctionalStatus correctional status
	 * @return interstate compact correctional status
	 */
	InterstateCompactCorrectionalStatus find(
			CorrectionalStatus correctionalStatus);
	
	/**
	 * Returns the interstate compact correctional status for the specified 
	 * correctional status excluding the specified interstate compact 
	 * correctional status.
	 * 
	 * @param correctionalStatus correctional status
	 * @param excludedStatus excluded interstate compact correctional status
	 * @return interstate compact correctional status
	 */
	InterstateCompactCorrectionalStatus findExcluding(
			CorrectionalStatus correctionalStatus, 
			InterstateCompactCorrectionalStatus excludedStatus);
	
	/**
	 * Returns a list of interstate compact correctional statuses.
	 * 
	 * @return list of interstate compact correctional statuses
	 */
	List<InterstateCompactCorrectionalStatus> findActive();
}