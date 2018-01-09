package omis.contact.dao;

import java.util.List;

import omis.contact.domain.OnlineAccountHost;
import omis.dao.GenericDao;

/**
 * Online account host data access object.
 * 
 * @author Yidong Li
 * @version 0.1.0 (July 29, 2015)
 * @since OMIS 3.0
 */

public interface OnlineAccountHostDao extends GenericDao<OnlineAccountHost> {
	/**
	 * Returns all online account hosts.
	 * 
	 * @return a list of online account host
	 */
	List<OnlineAccountHost> find();
}
