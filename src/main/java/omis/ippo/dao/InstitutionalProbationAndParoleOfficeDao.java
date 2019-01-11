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
package omis.ippo.dao;

import omis.dao.GenericDao;
import omis.ippo.domain.InstitutionalProbationAndParoleOffice;
import omis.location.domain.Location;

/**
 * Data access object for institutional probation and parole office.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (September 25, 2018)
 * @since OMIS 3.0
 */

public interface InstitutionalProbationAndParoleOfficeDao 
		extends GenericDao<InstitutionalProbationAndParoleOffice>{
	
	/**
	 * Returns the institutional probation and parole office matching the 
	 * specified location, name and telephone number.
	 * 
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @return institutional probation and parole office
	 */
	InstitutionalProbationAndParoleOffice find(Location location, String name, 
			Long telephoneNumber);
	
	/**
	 * Returns the institutional probation and parole office matching the 
	 * specified location, name and telephone number, excluding the specified 
	 * institutional probation and parole office.
	 * 
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @param excludedInstitutionalProbationAndParoleOffice excluded 
	 * institutional probation and parole office
	 * @return institutional probation and parole office
	 */
	InstitutionalProbationAndParoleOffice findExcluding(Location location, 
			String name, Long telephoneNumber, 
			InstitutionalProbationAndParoleOffice 
				excludedInstitutionalProbationAndParoleOffice);

}
