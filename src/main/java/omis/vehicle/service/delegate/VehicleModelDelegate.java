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
import omis.vehicle.dao.VehicleModelDao;
import omis.vehicle.domain.VehicleMake;
import omis.vehicle.domain.VehicleModel;
import omis.vehicle.exception.VehicleMakeExistsException;
import omis.vehicle.exception.VehicleModelExistsException;

/**
 * Delegate for vehicle model.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 26, 2018)
 * @since OMIS 3.0
 */
public class VehicleModelDelegate {
	private final InstanceFactory<VehicleModel>
	vehicleModelInstanceFactory;
	private final VehicleModelDao vehicleModelDao;
	
	/**
	 * Instantiates a delegate for vehicle model.
	 * 
	 * @param vehicleModelInstanceFactory instance factory for vehicle model
	 * @param vehicleModelDao vehicle model data access object
	 *
	 */
	public VehicleModelDelegate(
		final InstanceFactory<VehicleModel> vehicleModelInstanceFactory,
		final VehicleModelDao vehicleModelDao) {
		this.vehicleModelInstanceFactory = vehicleModelInstanceFactory;
		this.vehicleModelDao = vehicleModelDao;
	}

	/**
	 * Creates and persists a vehicle make
	 * 
	 * @param name vehicle make name
	 * @param valid valid
	 * @param vehicleMake vehicle make
	 * @return vehicle make
	 * @throws VehicleMakeExistsException 
	 */
	public VehicleModel create(final VehicleMake vehicleMake,
		final String name, final Boolean valid) throws VehicleModelExistsException {
		if(this.vehicleModelDao.find(vehicleMake, name)!=null) {
			throw new VehicleModelExistsException(
			"This vehicle make already exists");
		}
		VehicleModel vehicleModel
		= this.vehicleModelInstanceFactory.createInstance();
		vehicleModel.setName(name);
		vehicleModel.setValid(valid);
		vehicleModel.setVehicleMake(vehicleMake);
		return this.vehicleModelDao.makePersistent(vehicleModel);
	}
	
	/**
	 * Find a vehicle model by make
	 * 
	 * @param name vehicle make name
	 * @param valid valid
	 * @param vehicleMake vehicle make
	 * @return vehicle make
	 * @throws VehicleMakeExistsException 
	 */
	public List<VehicleModel> findVehicleMoldelByMake(final VehicleMake vehicleMake) {
		return this.vehicleModelDao.findVehicleMoldelByMake(vehicleMake);
	}
}