package omis.vehicle.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.vehicle.domain.VehicleMake;
import omis.vehicle.domain.VehicleModel;

/**
 * vehicle model data access object.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (Jul 23, 2014)
 * @since OMIS 3.0
 */
public interface VehicleModelDao extends GenericDao<VehicleModel> {

	/**
	 * Returns a list of vehicle models with the specified make.
	 * 
	 * @param vehiclemake vehicle make
	 * @return list of vehicle model
	 */
	List<VehicleModel> findVehicleMoldelByMake(VehicleMake vehicleMake);
}