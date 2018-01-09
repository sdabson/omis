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