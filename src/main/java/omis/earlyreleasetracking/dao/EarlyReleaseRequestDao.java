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

import java.util.Date;
import omis.dao.GenericDao;
import omis.docket.domain.Docket;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;

/**
 * Early Release Request Data Access Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public interface EarlyReleaseRequestDao
		extends GenericDao<EarlyReleaseRequest> {
	
	/**
	 * Finds a Early Release Request with the specified properties.
	 * 
	 * @param docket Docket
	 * @param requestDate Request Date
	 * @return Early Release Request with the specified properties.
	 */
	EarlyReleaseRequest find(Docket docket, Date requestDate);

	/**
	 * Finds a Early Release Request with the specified properties excluding the
	 * given Early Release Request.
	 * 
	 * @param docket Docket
	 * @param requestDate Request Date
	 * @param earlyReleaseRequestExcluding Early Release Request to exclude
	 * @return Early Release Request with the specified properties excluding the
	 * given Early Release Request.
	 */
	EarlyReleaseRequest findExcluding(Docket docket, Date requestDate,
			EarlyReleaseRequest earlyReleaseRequestExcluding);
}
