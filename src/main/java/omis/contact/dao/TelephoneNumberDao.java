package omis.contact.dao;

import java.util.List;

import omis.contact.domain.Contact;
import omis.contact.domain.TelephoneNumber;
import omis.dao.GenericDao;

/**
 * Telephone number data access object.
 * 
 * @author Yidong Li
 * @version 0.1.0 (April 2, 2014)
 * @since OMIS 3.0
 */

public interface TelephoneNumberDao extends GenericDao<TelephoneNumber> {
	/**
	 * Returns Telephone number if it already exists.
	 * 
	 * @param contact contact
	 * @param telephoneNumber telephone number
	 * @return telephoneNumber telephone number
	 */
	TelephoneNumber find(Contact contact, Long telephoneNumber);
	
	/**
	 * Returns a telephone number if there is one 
	 * 
	 * @param excludedTelephoneNumber telephone number to be excluded
	 * @param value telephone number
	 * @param contact contact
	 * @return telephone number
	 */
	TelephoneNumber findTelephoneNumberExcluding(
		TelephoneNumber excludedTelephoneNumber, Contact contact, Long value);
	
	/**
	 * Returns telephone numbers by contact 
	 * 
	 * @param contact contact
	 * @return telephone numbers
	 */
	List<TelephoneNumber> findByContact(Contact contact);
}
