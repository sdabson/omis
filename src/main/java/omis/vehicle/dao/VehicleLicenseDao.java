package omis.vehicle.dao;

/* import java.util.List; */

import omis.dao.GenericDao;
import omis.vehicle.domain.VehicleLicense;
/* import omis.region.domain.State; */
import omis.vehicle.domain.VehicleAssociation;

/**
 * Vehicle license data access object.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Aug 5, 2014)
 * @since OMIS 3.0
 */
public interface VehicleLicenseDao extends GenericDao<VehicleLicense> {
	
	/** Returns vehicle license by vehicle association.
	 * @param vehicleAssociation, vehicle Association
	 * @return vehicle license. */
	VehicleLicense findLicenseByVehicleAssociation(VehicleAssociation 
		vehicleAssociation);
}