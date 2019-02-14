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
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestInternalApproval;

/**
 * Early Release Request Internal Approval Data Access Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public interface EarlyReleaseRequestInternalApprovalDao
		extends GenericDao<EarlyReleaseRequestInternalApproval> {
	
	/**
	 * Finds a Early Release Request Internal Approval by the specified
	 * properties.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @param name Name
	 * @return Early Release Request Internal Approval found by the specified
	 * properties.
	 */
	EarlyReleaseRequestInternalApproval find(
			EarlyReleaseRequest earlyReleaseRequest, String name);
	
	/**
	 * Finds a Early Release Request Internal Approval by the specified
	 * properties excluding the given Early Release Internal Approval.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @param name Name
	 * @param earlyReleaseRequestInternalApprovalExcluding Early Release
	 * Request Internal Approval to exclude
	 * @return Early Release Request Internal Approval found by the specified
	 * properties excluding the given Early Release Internal Approval.
	 */
	EarlyReleaseRequestInternalApproval findExcluding(
			EarlyReleaseRequest earlyReleaseRequest, String name,
			EarlyReleaseRequestInternalApproval
				earlyReleaseRequestInternalApprovalExcluding);
	
	/**
	 * Returns a list of Early Request Internal Approvals by the specified
	 * Early Release Request.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @return List of Early Request Internal Approvals by the specified
	 * Early Release Request.
	 */
	List<EarlyReleaseRequestInternalApproval> findByEarlyReleaseRequest(
			EarlyReleaseRequest earlyReleaseRequest);
}
