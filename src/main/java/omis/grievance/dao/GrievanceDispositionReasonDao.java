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
package omis.grievance.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.grievance.domain.GrievanceDispositionReason;
import omis.grievance.domain.GrievanceDispositionStatus;

/**
 * Data access object for grievance disposition reasons.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 11, 2015)
 * @since OMIS 3.0
 */
public interface GrievanceDispositionReasonDao
		extends GenericDao<GrievanceDispositionReason> {

	/**
	 * Returns grievance disposition reason.
	 * 
	 * @param name name
	 * @return grievance disposition reason
	 */
	GrievanceDispositionReason find(String name);
	
	/**
	 * Returns grievance disposition reasons by status.
	 * 
	 * @param status status
	 * @return grievance disposition reasons by status
	 */
	List<GrievanceDispositionReason> findByStatus(
			GrievanceDispositionStatus status);
}