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

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceLocation;
import omis.grievance.domain.GrievanceSubject;
import omis.grievance.domain.GrievanceUnit;
import omis.offender.domain.Offender;

/**
 * Data access object for grievances.
 *
 * @author Stephen Abson
 * @version 0.0.2 (Oct 2, 2015)
 * @since OMIS 3.0
 */
public interface GrievanceDao
		extends GenericDao<Grievance> {

	/**
	 * Finds grievances by offender.
	 * 
	 * @param offender offender
	 * @return grievances by offender
	 */
	List<Grievance> findByOffender(Offender offender);
	
	/**
	 * Finds grievance.
	 * 
	 * @param offender offender
	 * @param location location
	 * @param unit unit
	 * @param openedDate openedDate
	 * @param subject subject
	 * @param grievanceNumber grievance number
	 * @return grievance
	 */
	Grievance find(Offender offender, GrievanceLocation location,
			GrievanceUnit unit, Date openedDate,
			GrievanceSubject subject, Integer grievanceNumber);
	
	/**
	 * Finds grievance with specified grievances excluded.
	 * 
	 * @param offender offender
	 * @param location location
	 * @param unit unit
	 * @param openedDate opened date
	 * @param subject subject
	 * @param grievanceNumber grievance number
	 * @param excludedGrievances grievances to exclude
	 * @return grievances
	 */
	Grievance findExcluding(Offender offender, GrievanceLocation location,
			GrievanceUnit unit, Date openedDate, GrievanceSubject subject,
			Integer grievanceNumber, Grievance... excludedGrievances);
	
	/**
	 * Returns highest grievance number.
	 * 
	 * <p>Returns zero if no grievances exist.
	 * 
	 * @return highest grievance number; zero if no grievances exist
	 */
	int findMaxGrievanceNumber();
}