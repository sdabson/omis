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
import omis.grievance.domain.GrievanceDispositionLevel;
import omis.grievance.domain.GrievanceDispositionStatus;

/**
 * Data access object for grievance disposition statuses.
 *
 * @author Stephen Abson
 * @version 0.0.2 (Oct 3, 2015)
 * @since OMIS 3.0
 */
public interface GrievanceDispositionStatusDao 
		extends GenericDao<GrievanceDispositionStatus> {

	/**
	 * Returns statuses by levels.
	 * 
	 * @param levels levels
	 * @return statuses by levels
	 */
	List<GrievanceDispositionStatus> findByLevels(
			GrievanceDispositionLevel... levels);

	/**
	 * Returns grievance disposition status.
	 * 
	 * @param name name
	 * @param level level
	 * @return grievance disposition status
	 */
	GrievanceDispositionStatus find(
			String name, GrievanceDispositionLevel level);

	/**
	 * Returns grievance disposition statuses by levels and whether closed.
	 * 
	 * @param closed whether closed
	 * @param levels levels
	 * @return grievance disposition statuses by levels and whether closed
	 */
	List<GrievanceDispositionStatus> findByLevelsAndWhetherClosed(
			boolean closed, GrievanceDispositionLevel... levels);
	
	/**
	 * Returns pending status for level.
	 * 
	 * @param level level
	 * @return pending status for level
	 */
	GrievanceDispositionStatus findPendingForLevel(
			GrievanceDispositionLevel level);
}