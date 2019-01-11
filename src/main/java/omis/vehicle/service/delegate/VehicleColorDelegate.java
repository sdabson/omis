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
import omis.vehicle.dao.VehicleColorDao;
import omis.vehicle.domain.VehicleColor;
import omis.vehicle.exception.VehicleColorExistsException;

/**
 * Delegate for vehicle color.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Dec 26, 2018)
 * @since OMIS 3.0
 */
public class VehicleColorDelegate {
	private final InstanceFactory<VehicleColor>
	vehicleColorInstanceFactory;
	private final VehicleColorDao vehicleColorDao;
	
	/**
	 * Instantiates a delegate for vehicle color.
	 * 
	 * @param vehicleColorInstanceFactory instance factory for vehicle color
	 * @param vehicleColorDao vehicle color data access object
	 *
	 */
	public VehicleColorDelegate(
		final InstanceFactory<VehicleColor> vehicleColorInstanceFactory,
		final VehicleColorDao vehicleColorDao) {
		this.vehicleColorInstanceFactory = vehicleColorInstanceFactory;
		this.vehicleColorDao = vehicleColorDao;
	}

	/**
	 * Creates and persists a vehicle color
	 * 
	 * @param name color name
	 * @param valid valid
	 * @return vehicle color
	 * @throws VehicleColorExistsException 
	 */
	public VehicleColor create(
		final String name, final Boolean valid) throws VehicleColorExistsException {
		if(this.vehicleColorDao.find(name)!=null) {
			throw new VehicleColorExistsException(
			"this color already exists.");
		}
		VehicleColor vehicleColor 
		= this.vehicleColorInstanceFactory.createInstance();
		vehicleColor.setName(name);
		vehicleColor.setValid(valid);
		return this.vehicleColorDao.makePersistent(vehicleColor);
	}
	
	/**
	 * Find all vehicle colors
	 * 
	 * @return all vehicle colors
	 *
	 */
	public List<VehicleColor> findColors() {
		return this.vehicleColorDao.findAll();
	}
}