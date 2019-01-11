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

package omis.vehicle.service.delegate;

import java.util.List;

import omis.instance.factory.InstanceFactory;
import omis.vehicle.dao.VehicleMakeDao;
import omis.vehicle.domain.VehicleMake;
import omis.vehicle.exception.VehicleMakeExistsException;

/**
 * Delegate for vehicle make.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 26, 2018)
 * @since OMIS 3.0
 */
public class VehicleMakeDelegate {
	private final InstanceFactory<VehicleMake>
	vehicleMakeInstanceFactory;
	private final VehicleMakeDao vehicleMakeDao;
	
	/**
	 * Instantiates a delegate for vehicle make.
	 * 
	 * @param vehicleMakeInstanceFactory instance factory for vehicle make
	 * @param vehicleMakeDao vehicle make data access object
	 *
	 */
	public VehicleMakeDelegate(
		final InstanceFactory<VehicleMake> vehicleMakeInstanceFactory,
		final VehicleMakeDao vehicleMakeDao) {
		this.vehicleMakeInstanceFactory = vehicleMakeInstanceFactory;
		this.vehicleMakeDao = vehicleMakeDao;
	}

	/**
	 * Creates and persists a vehicle make
	 * 
	 * @param name vehicle make name
	 * @param valid valid
	 * @return vehicle make
	 * @throws VehicleMakeExistsException 
	 */
	public VehicleMake create(
		final String name, final Boolean valid) throws VehicleMakeExistsException {
		if(this.vehicleMakeDao.find(name)!=null) {
			throw new VehicleMakeExistsException(
			"This vehicle make already exists");
		}
		VehicleMake vehicleMake 
		= this.vehicleMakeInstanceFactory.createInstance();
		vehicleMake.setName(name);
		vehicleMake.setValid(valid);
		return this.vehicleMakeDao.makePersistent(vehicleMake);
	}
	
	/**
	 * Find all vehicle makes
	 * 
	 * @return a list of vehicle makes
	 *
	 */
	public List<VehicleMake> findVehicleMakes() {
		return this.vehicleMakeDao.findAll();
	}
}