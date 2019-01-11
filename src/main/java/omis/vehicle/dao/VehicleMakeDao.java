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

package omis.vehicle.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.vehicle.domain.VehicleMake;

/**
 * vehicle make data access object.
 * 
 * @author Ryan Johns
 * @author Yidong Li
 * @version 0.1.0 (Jul 23, 2014)
 * @since OMIS 3.0
 */

public interface VehicleMakeDao extends GenericDao<VehicleMake> {
	/**
	 * Returns a list of vehicle make.
	 * 
	 * @return list of vehicle make
	 */
	List<VehicleMake> findVehicleMake();
	
	/**
	 * Find a vehicle make.
	 * @param name vehicle maker name
	 * @return a existing vehicle make
	 */
	VehicleMake find(String name);
}
