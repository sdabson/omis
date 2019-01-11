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
package omis.caseload.service.delegate;

import java.util.List;

import omis.caseload.dao.InterstateCompactCorrectionalStatusDao;
import omis.caseload.domain.InterstateCompactCorrectionalStatus;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.supervision.domain.CorrectionalStatus;

/**
 * Interstate compact correctional status delegate.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Sep 7, 2018)
 * @since OMIS 3.0
 */
public class InterstateCompactCorrectionalStatusDelegate {

	/* DAOs */
	private final InterstateCompactCorrectionalStatusDao 
			interstateCompactCorrectionalStatusDao;
	
	/* Instance factory. */
	private final InstanceFactory<InterstateCompactCorrectionalStatus> 
			interstateCompactCorrectionalStatusInstanceFactory;
	
	/**
	 * Instantiates an implementation of interstate compact correctional status 
	 * delegate with the specified data access object and instance factory.
	 * 
	 * @param interstateCompactCorrectionalStatusDao interstate compact 
	 * correctional status data access object
	 * @param interstateCompactCorrectionalStatusInstanceFactory interstate 
	 * compact correctional status instance factory
	 */
	public InterstateCompactCorrectionalStatusDelegate(
			final InterstateCompactCorrectionalStatusDao 
					interstateCompactCorrectionalStatusDao,
			final InstanceFactory<InterstateCompactCorrectionalStatus> 
					interstateCompactCorrectionalStatusInstanceFactory) {
		this.interstateCompactCorrectionalStatusDao = 
				interstateCompactCorrectionalStatusDao;
		this.interstateCompactCorrectionalStatusInstanceFactory = 
				interstateCompactCorrectionalStatusInstanceFactory;
	}
	
	/**
	 * Creates a new interstate compact correctional status.
	 * 
	 * @param correctionalStatus correctional status
	 * @param valid valid
	 * @return interstate compact correctional status
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public InterstateCompactCorrectionalStatus create(
			final CorrectionalStatus correctionalStatus, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.interstateCompactCorrectionalStatusDao.find(correctionalStatus)
				!= null) {
			throw new DuplicateEntityFoundException(
					"Interstate compact correctional status already exists.");
		}
		InterstateCompactCorrectionalStatus interstateCompactCorrectionalStatus = 
				this.interstateCompactCorrectionalStatusInstanceFactory
				.createInstance();
		populateInterstateCompactCorrectionalStatus(
				interstateCompactCorrectionalStatus, correctionalStatus, valid);
		return this.interstateCompactCorrectionalStatusDao.makePersistent(
				interstateCompactCorrectionalStatus);
	}
	
	/**
	 * Updates an existing interstate compact correctional status.
	 * 
	 * @param interstateCompactCorrectionalStatus interstate compact 
	 * correctional status
	 * @param correctionalStatus correctional status
	 * @param valid valid
	 * @return interstate compact correctional status
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public InterstateCompactCorrectionalStatus update(
			final InterstateCompactCorrectionalStatus 
					interstateCompactCorrectionalStatus,
			final CorrectionalStatus correctionalStatus, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.interstateCompactCorrectionalStatusDao.findExcluding(
				correctionalStatus, interstateCompactCorrectionalStatus) != null) {
			throw new DuplicateEntityFoundException(
					"Interstate compact correctional status already exists.");
		}
		populateInterstateCompactCorrectionalStatus(
				interstateCompactCorrectionalStatus, correctionalStatus, valid);
		return this.interstateCompactCorrectionalStatusDao.makePersistent(
				interstateCompactCorrectionalStatus);
	}

	/**
	 * Removes the specified interstate compact correctional status.
	 * 
	 * @param interstateCompactCorrectionalStatus interstate compact 
	 * correctional status
	 */
	public void remove(
			final InterstateCompactCorrectionalStatus 
					interstateCompactCorrectionalStatus) {
		this.interstateCompactCorrectionalStatusDao.makeTransient(
				interstateCompactCorrectionalStatus);
	}

	/**
	 * Returns a list of interstate compact correctional statuses.
	 * 
	 * @return list of interstate compact correctional statuses
	 */
	public List<InterstateCompactCorrectionalStatus> findActive() {
		return this.interstateCompactCorrectionalStatusDao.findActive();
	}
	
	//Populates an interstate compact correctional status
	private void populateInterstateCompactCorrectionalStatus(
			final InterstateCompactCorrectionalStatus 
					interstateCompactCorrectionalStatus,
			final CorrectionalStatus correctionalStatus, final Boolean valid) {
		interstateCompactCorrectionalStatus.setCorrectionalStatus(
				correctionalStatus);
		interstateCompactCorrectionalStatus.setValid(valid);
	}
}