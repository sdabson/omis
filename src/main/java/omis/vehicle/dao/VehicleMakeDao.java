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
}
