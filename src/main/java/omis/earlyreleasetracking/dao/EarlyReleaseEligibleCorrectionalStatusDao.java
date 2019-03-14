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
import omis.earlyreleasetracking.domain.EarlyReleaseEligibleCorrectionalStatus;
import omis.supervision.domain.CorrectionalStatus;

/**
 * Early Release Eligible Correctional Status Data Access Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Mar 11, 2019)
 *@since OMIS 3.0
 *
 */
public interface EarlyReleaseEligibleCorrectionalStatusDao
		extends GenericDao<EarlyReleaseEligibleCorrectionalStatus> {
	
	/**
	 * Returns a Early Release Eligible Correctional Status with the specified
	 * Correctional Status.
	 * 
	 * @param correctionalStatus Correctional Status
	 * @return Early Release Eligible Correctional Status with the specified
	 * Correctional Status
	 */
	EarlyReleaseEligibleCorrectionalStatus find(
			CorrectionalStatus correctionalStatus);
	
	/**
	 * Returns a Early Release Eligible Correctional Status with the specified
	 * Correctional Status excluding the given Early Release Eligible
	 * Correctional Status.
	 * 
	 * @param correctionalStatus Correctional Status
	 * @param earlyReleaseEligibleCorrectionalStatusExcluding Early Release
	 * Eligible Correctional Status to exclude
	 * @return Early Release Eligible Correctional Status with the specified
	 * Correctional Status excluding the given Early Release Eligible
	 * Correctional Status
	 */
	EarlyReleaseEligibleCorrectionalStatus findExcluding(
			CorrectionalStatus correctionalStatus,
			EarlyReleaseEligibleCorrectionalStatus
				earlyReleaseEligibleCorrectionalStatusExcluding);
	
	/**
	 * Returns a list of all valid Early Release Eligible Correctional
	 * Statuses.
	 * 
	 * @return List of all valid Early Release Eligible Correctional
	 * Statuses.
	 */
	List<EarlyReleaseEligibleCorrectionalStatus> findAllStatuses();
	
}
