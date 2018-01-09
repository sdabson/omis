package omis.locationterm.dao;

import omis.dao.GenericDao;
import omis.locationterm.domain.LocationTermChangeAction;

/**
 * Data access object for location term change actions.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Mar 6, 2015)
 * @since OMIS 3.0
 */
public interface LocationTermChangeActionDao
		extends GenericDao<LocationTermChangeAction> {

	/**
	 * Returns location term change action.
	 * 
	 * <p>Returns {@code null} if not found.
	 * 
	 * @param name name
	 * @return location term change action; {@code null} if not found
	 */
	LocationTermChangeAction find(String name);
}