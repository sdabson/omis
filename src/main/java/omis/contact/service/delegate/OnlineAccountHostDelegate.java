package omis.contact.service.delegate;

import java.util.List;

import omis.contact.dao.OnlineAccountHostDao;
import omis.contact.domain.OnlineAccountHost;

/**
 * Delegate for online account hosts.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 10, 2015)
 * @since OMIS 3.0
 */
public class OnlineAccountHostDelegate {

	/* Resources. */
	
	private final OnlineAccountHostDao onlineAccountHostDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for online account hosts.
	 * 
	 * @param onlineAccountHostDao data access object for online account hosts
	 */
	public OnlineAccountHostDelegate(
			final OnlineAccountHostDao onlineAccountHostDao) {
		this.onlineAccountHostDao = onlineAccountHostDao;
	}
	
	/* Methods. */
	
	/**
	 * Returns online account hosts.
	 * 
	 * @return online account hosts
	 */
	public List<OnlineAccountHost> findAll() {
		return this.onlineAccountHostDao.findAll();
	}
}