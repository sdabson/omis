package omis.region.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.region.domain.County;
import omis.region.domain.State;

/**
 * Data access object for counties.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Feb 6, 2013)
 * @since OMIS 3.0
 */
public interface CountyDao
		extends GenericDao<County> {

	/**
	 * Returns all counties for the specified State.
	 * 
	 * @param state State whose counties to return
	 * @return counties for specified State
	 */
	List<County> findByState(State state);
}