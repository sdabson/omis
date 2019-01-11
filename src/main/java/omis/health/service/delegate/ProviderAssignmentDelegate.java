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

import omis.datatype.DateRange;
import omis.facility.domain.Facility;
import omis.health.dao.ProviderAssignmentDao;
import omis.health.domain.MedicalFacility;
import omis.health.domain.ProviderAssignment;
import omis.health.domain.ProviderTitle;
import omis.health.exception.ProviderAssignmentExistsException;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;

/**
 * Delegate for provider assignment.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Oct 24, 2018)
 * @since OMIS 3.0
 */
public class ProviderAssignmentDelegate {
	private final InstanceFactory<ProviderAssignment>
		providerAssignmentInstanceFactory;
	private final ProviderAssignmentDao
		providerAssignmentDao;
	
	/**
	 * Instantiates a delegate for provider assignment.
	 * @param providerAssignmentInstanceFactory
	 * providerAssignmentInstanceFactory
	 * @param providerAssignmentDao providerAssignmentDao
	 * 
	 */
	public ProviderAssignmentDelegate(
		final InstanceFactory<ProviderAssignment>
		providerAssignmentInstanceFactory,
		final ProviderAssignmentDao providerAssignmentDao) {
		this.providerAssignmentInstanceFactory
			= providerAssignmentInstanceFactory;
		this.providerAssignmentDao = providerAssignmentDao;
	}

	/**
	 * Creates and persists a provider assignment.
	 * 
	 * @param provider provider
	 * @param dateRange date range
	 * @param facility facility
	 * @param medicalFacility medical facility
	 * @param title provider title
	 * @param contracted contracted
	 * @throws ProviderAssignmentExistsException if assignment exists
	 */
	public ProviderAssignment create(
		final Person provider, final Facility facility,
		final DateRange dateRange,
		final MedicalFacility medicalFacility,
		final ProviderTitle title,
		final Boolean contracted) throws ProviderAssignmentExistsException {
		if(this.providerAssignmentDao.findExisting(provider, facility,
			title)!=null) {
			throw new ProviderAssignmentExistsException("Provider"
					+ "assignment already exists"); 
		}
		ProviderAssignment providerAssignment
			= this.providerAssignmentInstanceFactory.createInstance();
		providerAssignment.setContracted(contracted);
		providerAssignment.setDateRange(dateRange);
		providerAssignment.setFacility(facility);
		providerAssignment.setMedicalFacility(medicalFacility);
		providerAssignment.setProvider(provider);
		providerAssignment.setTitle(title);
		return this.providerAssignmentDao.makePersistent(
			providerAssignment);
	}
}