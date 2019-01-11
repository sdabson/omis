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

import omis.dao.GenericDao;
/* import omis.datatype.DateRange;
import omis.vehicle.domain.VehicleColor;
import omis.vehicle.domain.VehicleModel; */
import omis.vehicle.domain.VehicleOwnerAssociation;
import omis.vehicle.domain.VehicleAssociation;

/**
 * Vehicle owner association data access object.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Aug 4, 2014)
 * @since OMIS 3.0
 */
public interface VehicleOwnerAssociationDao 
	extends GenericDao<VehicleOwnerAssociation> {
	/** Find the owner by the vehicle association.
     * @param vehicleAssociation vehicle association.  
     * @return persisted vehicle owner association */
	VehicleOwnerAssociation findOwnerByVehicleAssociation(VehicleAssociation 
		vehicleAssociation);
}