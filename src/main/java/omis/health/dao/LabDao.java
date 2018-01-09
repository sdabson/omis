package omis.health.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.health.domain.Lab;
import omis.location.domain.Location;

/**
 * Lab data access object.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (May 7, 2014)
 * @since OMIS 3.0
 */
public interface LabDao extends GenericDao<Lab> {

	/**
	 * Returns a list of valid labs.
	 * 
	 * @return list of labs
	 */
	List<Lab> findLabs();
	
	/**
	 * Returns a list of valid labs with the specified location.
	 * 
	 * @param location location
	 * @return list of labs
	 */
	List<Lab> findLabsAtLocation(Location location);
	
	/**
	* Returns labs.
	* 
	* @return labs
	*/	
	List<Lab> findResultLabs();
}