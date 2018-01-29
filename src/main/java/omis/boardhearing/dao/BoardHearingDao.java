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
package omis.boardhearing.dao;

import omis.boardhearing.domain.BoardHearing;
import omis.dao.GenericDao;
import omis.paroleeligibility.domain.ParoleEligibility;

/**
 * Board Hearing Data Access Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Dec 28, 2017)
 *@since OMIS 3.0
 *
 */
public interface BoardHearingDao extends GenericDao<BoardHearing> {
	
	
	/**
	 * Returns a Board Hearing with the specified Parole Eligibility.
	 * 
	 * @param paroleEligibility - Parole Eligibility
	 * @return Board Hearing with the specified Parole Eligibility.
	 */
	BoardHearing find(ParoleEligibility paroleEligibility);
	
	/**
	 * Returns a Board Hearing with the specified Parole Eligibility excluding
	 * the specified Board Hearing.
	 * 
	 * @param paroleEligibility - Parole Eligibility
	 * @param boardHearingExcluded - Board Hearing to exclude
	 * @return Board Hearing with the specified Parole Eligibility excluding
	 * the specified Board Hearing.
	 */
	BoardHearing findExcluding(ParoleEligibility paroleEligibility,
			BoardHearing boardHearingExcluded);
	
}
