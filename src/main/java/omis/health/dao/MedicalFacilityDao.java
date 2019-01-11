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
package omis.health.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.health.domain.MedicalFacility;
import omis.organization.domain.Organization;

/**
 * Data access object for medical facilities.
 * 
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.1.0 (Oct 26, 2018)
 * @since OMIS 3.0
 */
public interface MedicalFacilityDao
		extends GenericDao<MedicalFacility> {

	/**
	 * Returns hospitals.
	 * 
	 * @return hospitals
	 */
	List<MedicalFacility> findHospitals();

	/**
	 * Returns hospitals by organization.
	 * 
	 * @param organization organization
	 * @return hospitals by organization
	 */
	List<MedicalFacility> findHospitalsByOrganization(
			Organization organization);
	
	/**
	 * Search medical facility by name.
	 * 
	 * @param name, facility name
	 * @param abbreviation abbreviation
	 * @param location location
	 * @param active active
	 * @param hospital, is a hospital or not
	 * @param telephoneNumber telephone number
	 * @return created medical facility
	 */
	MedicalFacility findMedicalFacilityByName(String name);
}