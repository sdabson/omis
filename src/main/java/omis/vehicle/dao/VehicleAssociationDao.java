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
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.vehicle.domain.VehicleAssociation;
import omis.vehicle.domain.VehicleColor;
import omis.vehicle.domain.VehicleMake;
import omis.vehicle.domain.VehicleModel;

/**
 * Vehicle association data access object.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (Jul 22, 2014)
 * @since OMIS 3.0
 */
public interface VehicleAssociationDao extends GenericDao<VehicleAssociation> {

	/**
	 * Returns true if an vehicle association is found with the specified
	 * properties.
	 * 
	 * @param offender offender
	 * @param dateRange date range
	 * @param vehicleModel vehicle model
	 * @param vehicleColor vehicle color
	 * @param year year
	 * @param vehicleMake vehicle make
	 * @return vehicle found or not
	 */
	VehicleAssociation find(Offender offender, DateRange dateRange,
		VehicleModel vehicleModel, VehicleColor vehicleColor, Integer year,
		VehicleMake vehicleMake);

	/**
	 * Returns true if not the excluded vehicle association.
	 * 
	 * @param dateRange date range
	 * @param vehicleModel vehicle model
	 * @param vehicleColor vehicle color
	 * @param year year
	 * @param comments comments
	 * @param vehicleMake vehicle make
	 * @param offender offender
	 * @param vehicleAssociation vehicle association
	 * @return vehicle association found or not; {@code null} if no such 
	 * vehicle association exists
	 */
	
	VehicleAssociation findExcluding(VehicleModel vehicleModel, 
		VehicleColor vehicleColor, Integer year, DateRange dateRange, 
		String comments, VehicleAssociation vehicleAssociation, 
		VehicleMake vehicleMake, Offender offender); 

	/**
	 * Returns true if not the excluded vehicle association for "save" button.
	 * 
	 * @param dateRange date range
	 * @param vehicleModel vehicle model
	 * @param vehicleMake vehicle make
	 * @param vehicleColor vehicle color
	 * @param year year
	 * @param comments comments
	 * @param offender offender
	 * @return vehicle association found or not; {@code null} if no such 
	 * vehicle association exists
	 */
	
	VehicleAssociation findExcludingSave(VehicleModel vehicleModel, 
		VehicleColor vehicleColor, Integer year, DateRange dateRange, 
		String comments, VehicleMake vehicleMake, Offender offender); 	
	
	/** Returns list of vehicle association by offender.
	 * @param offender offender.
	 * @return list of vehicle association. */
	List<VehicleAssociation> findByOffender(Offender offender);
}