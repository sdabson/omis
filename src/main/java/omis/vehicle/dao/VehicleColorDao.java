package omis.vehicle.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.vehicle.domain.VehicleColor;

/**
 * vehicle color data access object.
 * 
 * @author Ryan Johns
 * @author Yidong Li
 * @version 0.1.0 (Jul 23, 2014)
 * @since OMIS 3.0
 */

public interface VehicleColorDao extends GenericDao<VehicleColor> {
	/**
	 * Returns a list of vehicle color.
	 * 
	 * @return list of vehicle color
	 */
	List<VehicleColor> findColors( );
}
