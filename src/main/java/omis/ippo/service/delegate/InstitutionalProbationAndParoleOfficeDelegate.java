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
package omis.ippo.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.ippo.dao.InstitutionalProbationAndParoleOfficeDao;
import omis.ippo.domain.InstitutionalProbationAndParoleOffice;
import omis.location.domain.Location;

/**
 * Delegate for institutional probation and parole office.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (September 25, 2018)
 * @since OMIS 3.0
 */

public class InstitutionalProbationAndParoleOfficeDelegate {

	/* Resources. */
	
	private final InstitutionalProbationAndParoleOfficeDao 
		institutionalProbationAndParoleOfficeDao;
	
	private final InstanceFactory<InstitutionalProbationAndParoleOffice> 
		institutionalProbationAndParoleOfficeInstanceFactory;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for managing institutional probation and parole
	 * office.
	 * 
	 * @param institutionalProbationAndParoleOfficeDao data access object for 
	 * institutional probation and parole office
	 * @param institutionalProbationAndParoleOfficeInstanceFactory instance 
	 * factory for institutional probation and parole offices
	 */
	public InstitutionalProbationAndParoleOfficeDelegate(
			final InstitutionalProbationAndParoleOfficeDao 
				institutionalProbationAndParoleOfficeDao,
			final InstanceFactory<InstitutionalProbationAndParoleOffice> 
				institutionalProbationAndParoleOfficeInstanceFactory) {
		this.institutionalProbationAndParoleOfficeDao 
			= institutionalProbationAndParoleOfficeDao;
		this.institutionalProbationAndParoleOfficeInstanceFactory 
			= institutionalProbationAndParoleOfficeInstanceFactory;
	}

	/**
	 * Creates a new institutional probation and parole office.
	 * 
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @return created institutional probation and parole office
	 * @throws DuplicateEntityFoundException thrown when a duplicate 
	 * institutional probation and parole office is found
	 */
	public InstitutionalProbationAndParoleOffice create(Location location, 
			String name, Long telephoneNumber) 
					throws DuplicateEntityFoundException {
		if (this.institutionalProbationAndParoleOfficeDao.find(location, name, 
				telephoneNumber) != null) {
			throw new DuplicateEntityFoundException("Duplicate institutional "
					+ "probation and office found");
		}
		InstitutionalProbationAndParoleOffice 
				institutionalProbationAndParoleOffice 
			= this.institutionalProbationAndParoleOfficeInstanceFactory
			.createInstance();
		institutionalProbationAndParoleOffice.setLocation(location);
		institutionalProbationAndParoleOffice.setName(name);
		institutionalProbationAndParoleOffice.setTelephoneNumber(
				telephoneNumber);
		return this.institutionalProbationAndParoleOfficeDao.makePersistent(
				institutionalProbationAndParoleOffice);
	}
	
	/**
	 * Updates the specified institutional probation and parole office.
	 * 
	 * @param institutionalProbationAndParoleOffice institutional probation and 
	 * parole office
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @return updated institutional probation and parole office
	 * @throws DuplicateEntityFoundException thrown when a duplicate 
	 * institutional probation and parole is found
	 */
	public InstitutionalProbationAndParoleOffice update(
			InstitutionalProbationAndParoleOffice 
				institutionalProbationAndParoleOffice, Location location, 
				String name, Long telephoneNumber) 
						throws DuplicateEntityFoundException {
		if (this.institutionalProbationAndParoleOfficeDao.findExcluding(location, 
				name, telephoneNumber, institutionalProbationAndParoleOffice) 
				!= null) {
			throw new DuplicateEntityFoundException("Duplicate institutional "
					+ "probation and office found");
		}
		institutionalProbationAndParoleOffice.setLocation(location);
		institutionalProbationAndParoleOffice.setName(name);
		institutionalProbationAndParoleOffice.setTelephoneNumber(
				telephoneNumber);
		return this.institutionalProbationAndParoleOfficeDao.makePersistent(
				institutionalProbationAndParoleOffice);
	}
	
	/**
	 * Removes the specified institutional probation and parole office
	 * 
	 * @param institutionalProbationAndParoleOffice institutional probation and 
	 * parole office
	 */
	public void remove(InstitutionalProbationAndParoleOffice 
			institutionalProbationAndParoleOffice) {
		this.institutionalProbationAndParoleOfficeDao.makeTransient(
				institutionalProbationAndParoleOffice);
	}

	/**
	 * Returns a list of all institutional probation and parole offices.
	 * 
	 * @return list of all institutional probation and parole offices
	 */
	public List<InstitutionalProbationAndParoleOffice> findAll() {
		return this.institutionalProbationAndParoleOfficeDao.findAll();
	}
	
}
