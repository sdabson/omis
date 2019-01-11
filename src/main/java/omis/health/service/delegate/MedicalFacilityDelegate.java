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
package omis.health.service.delegate;

import java.util.List;

import omis.health.dao.MedicalFacilityDao;
import omis.health.domain.MedicalFacility;
import omis.health.exception.MedicalFacilityExistsException;
import omis.instance.factory.InstanceFactory;
import omis.location.domain.Location;
import omis.organization.domain.Organization;

/**
 * Delegate for medical facilities.
 *
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.0.1 (Oct 26, 2018)
 * @since OMIS 3.0
 */
public class MedicalFacilityDelegate {

	/* Resources. */
	
	private final MedicalFacilityDao medicalFacilityDao;
	
	private final InstanceFactory<MedicalFacility>
	medicalFacilityInstanceFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for medical facilities.
	 * 
	 * @param medicalFacilityDao delegate for medical facilities
	 * @param medicalFacilityInstanceFactory medical facility instance
	 * factory
	 */
	public MedicalFacilityDelegate(
			final MedicalFacilityDao medicalFacilityDao,
			final InstanceFactory<MedicalFacility>
			medicalFacilityInstanceFactory) {
		this.medicalFacilityDao = medicalFacilityDao;
		this.medicalFacilityInstanceFactory
			= medicalFacilityInstanceFactory;
	}
	
	/* Methods. */
	
	/**
	 * Returns hospitals.
	 * 
	 * @return hospitals
	 */
	public List<MedicalFacility> findHospitals() {
		return this.medicalFacilityDao.findHospitals();
	}

	/**
	 * Returns hospitals by organization.
	 * 
	 * @param organization organization
	 * @return hospitals by organization
	 */
	public List<MedicalFacility> findHospitalsByOrganization(
			final Organization organization) {
		return this.medicalFacilityDao
				.findHospitalsByOrganization(organization);
	}
	
	/**
	 * Create medical facility.
	 * 
	 * @param name facility name
	 * @param location location
	 * @param abbreviation abbreviation
	 * @param active active
	 * @param hospital, hospital or not
	 * @param telephoneNumber telephone number
	 * @return medicalfacility
	 * @throws MedicalFacilityExistsException if already exists 
	 */
	public MedicalFacility createMedicalFacility(
		final String name, final Location location,
		final String abbreviation, final Boolean active,
		final Boolean hospital, final Long telephoneNumber)
			throws MedicalFacilityExistsException {
		if(this.medicalFacilityDao.findMedicalFacilityByName(name)!=null) {
			throw new MedicalFacilityExistsException("Medical facility"
					+ "already exists");
		}
		MedicalFacility facility = this.medicalFacilityInstanceFactory
			.createInstance();
		facility.setAbbreviation(abbreviation);
		facility.setActive(true);
		facility.setHospital(true);
		facility.setLocation(location);
		facility.setName(name);
		facility.setTelephoneNumber(telephoneNumber);
		return this.medicalFacilityDao.makePersistent(facility);
	}
}