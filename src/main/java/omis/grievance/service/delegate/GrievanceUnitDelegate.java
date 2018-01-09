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
package omis.grievance.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.grievance.dao.GrievanceUnitDao;
import omis.grievance.domain.GrievanceUnit;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for grievance units.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Feb 25, 2016)
 * @since OMIS 3.0
 */
public class GrievanceUnitDelegate {

	/* Instance factory. */
	
	private final InstanceFactory<GrievanceUnit> grievanceUnitInstanceFactory;
	
	/* Data access objects. */
	
	private final GrievanceUnitDao grievanceUnitDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for grievance units.
	 * 
	 * @param grievanceUnitInstanceFactory instance factory for grievance units
	 * @param grievanceUnitDao data access object for grievance units
	 */
	public GrievanceUnitDelegate(
			final InstanceFactory<GrievanceUnit> grievanceUnitInstanceFactory,
			final GrievanceUnitDao grievanceUnitDao) {
		this.grievanceUnitInstanceFactory = grievanceUnitInstanceFactory;
		this.grievanceUnitDao = grievanceUnitDao;
	}
	
	/* Methods. */
	
	public GrievanceUnit create(
			final String name, final Boolean valid, final Short sortOrder)
				throws DuplicateEntityFoundException {
		if (this.grievanceUnitDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Grievance unit exists");
		}
		GrievanceUnit unit = this.grievanceUnitInstanceFactory
				.createInstance();
		unit.setName(name);
		unit.setValid(valid);
		unit.setSortOrder(sortOrder);
		return this.grievanceUnitDao.makePersistent(unit);
	}
	
	/**
	 * Returns grievance units.
	 * 
	 * @return grievance units
	 */
	public List<GrievanceUnit> findAll() {
		return this.grievanceUnitDao.findAll();
	}
}