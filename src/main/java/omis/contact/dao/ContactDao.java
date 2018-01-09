package omis.contact.dao;

import omis.contact.domain.Contact;
import omis.dao.GenericDao;
import omis.person.domain.Person;

/**
 * contact data access object.
 * 
 * @author Yidong Li
 * @version 0.1.0 (April 2, 2014)
 * @since OMIS 3.0
 */

public interface ContactDao extends GenericDao<Contact> {
	/**
	 * Returns the contact if a contact is found by person.
	 * 
	 * @param person person
	 * @return contact contact
	 */
	Contact find(Person person);
	
	/**
	 * Returns a contact by person
	 * 
	 * @param person person
	 * @return a contact
	 */
	Contact findByPerson(Person person);
	
	/**
	 * Returns a contacts if there is one 
	 * 
	 * @param mailingAddress mailing address
	 * @param excludedContact contact to be excluded
	 * @param poBox po Box
	 * @return contact
	 */
	Contact findContactExcluding(Contact excludedContact, Person person);
}
