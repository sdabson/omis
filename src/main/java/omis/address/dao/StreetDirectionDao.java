package omis.address.dao;

import java.util.List;

import omis.address.domain.StreetDirection;
import omis.dao.GenericDao;

/**
 * Data access object for street direction.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 6, 2013)
 * @since OMIS 3.0
 */
public interface StreetDirectionDao
		extends GenericDao<StreetDirection> {

	/**
	 * Returns all pre-directions.
	 * 
	 * @return all pre-directions
	 */
	List<StreetDirection> findAllPreDirections();
	
	/**
	 * Returns all post-directions.
	 * 
	 * @return all post-directions
	 */
	List<StreetDirection> findAllPostDirections();
}