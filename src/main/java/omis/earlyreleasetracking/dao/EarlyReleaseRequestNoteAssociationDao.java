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
import java.util.List;
import omis.dao.GenericDao;
import omis.earlyreleasetracking.domain.EarlyReleaseRequest;
import omis.earlyreleasetracking.domain.EarlyReleaseRequestNoteAssociation;

/**
 * Early Release Request Note Association Data Acess Object.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Feb 8, 2019)
 *@since OMIS 3.0
 *
 */
public interface EarlyReleaseRequestNoteAssociationDao
		extends GenericDao<EarlyReleaseRequestNoteAssociation> {
	
	/**
	 * Finds a Early Release Request Note Association with the specified
	 * properties.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @param description Description
	 * @param date Date
	 * @return Early Release Request Note Association with the specified
	 * properties.
	 */
	EarlyReleaseRequestNoteAssociation find(
			EarlyReleaseRequest earlyReleaseRequest, String description,
			Date date);

	/**
	 * Finds a Early Release Request Note Association with the specified
	 * properties excluding the given Early Release Request Note Association.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @param description Description
	 * @param date Date
	 * @param earlyReleaseRequestNoteAssociationExcluding Early Release Request
	 * Note Association to exclude
	 * @return Early Release Request Note Association with the specified
	 * properties excluding the given Early Release Request Note Association.
	 */
	EarlyReleaseRequestNoteAssociation findExcluding(
			EarlyReleaseRequest earlyReleaseRequest, String description,
			Date date, EarlyReleaseRequestNoteAssociation
			earlyReleaseRequestNoteAssociationExcluding);
	
	/**
	 * Returns a list of Early Release Request Note Associations by the
	 * specified Early Release Request.
	 * 
	 * @param earlyReleaseRequest Early Release Request
	 * @return List of Early Release Request Note Associations by the
	 * specified Early Release Request.
	 */
	List<EarlyReleaseRequestNoteAssociation> findByEarlyReleaseRequest(
			EarlyReleaseRequest earlyReleaseRequest);
}
