package omis.contact.dao;

import java.util.List;

import omis.contact.domain.Contact;
import omis.contact.domain.OnlineAccount;
import omis.contact.domain.OnlineAccountHost;
import omis.dao.GenericDao;

/**
 * Online account data access object.
 * 
 * @author Yidong Li
 * @version 0.1.0 (July 27, 2015)
 * @since OMIS 3.0
 */

public interface OnlineAccountDao extends GenericDao<OnlineAccount> {
	/**
	 * Returns online account if it already exists.
	 * 
	 * @param contact contact
	 * @param name name
	 * @param host host
	 * @return new online account
	 */
	OnlineAccount find(Contact contact, String name, OnlineAccountHost host);
	
	/**
	 * Returns a online account if it already exists 
	 * 
	 * @param excludedOnlineAccount online account to be excluded
	 * @param name name
	 * @param host online account host
	 * @return updated online account
	 */
	OnlineAccount findOnlineAccountExcluding(OnlineAccount excludedOnlineAccount, 
		String name, OnlineAccountHost host);
	
	/**
	 * Find online accounts by contact 
	 * 
	 * @param contact contact
	 * @return a list of online account
	 */
	List<OnlineAccount> findByContact(Contact contact);
}
