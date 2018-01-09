package omis.contact.service;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.domain.ZipCode;
import omis.contact.domain.Contact;
import omis.contact.domain.OnlineAccount;
import omis.contact.domain.OnlineAccountCategory;
import omis.contact.domain.OnlineAccountHost;
import omis.contact.domain.TelephoneNumber;
import omis.contact.domain.TelephoneNumberCategory;
import omis.contact.domain.component.PoBox;
import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;

/**
 * Contact Service.
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (April 3, 2015)
 * @since: OMIS 3.0
 */

public interface ContactService {
	
	/** Create new contact
	 * @param person person. 
	 * @param mailingAddress mailing address.
	 * @param poBox po box
	 * @return contact */
	Contact create(Person person, Address mailingAddress, PoBox poBox) 
		throws DuplicateEntityFoundException;
	
	/** Update an existing contact
	 * @param contact contact. 
	 * @param mailingAddress mailing address.
	 * @param poBox po box
	 * @return contact */
	Contact update(Contact contact, Address mailingAddress, PoBox poBox) 
		throws DuplicateEntityFoundException;
	
	/** Remove an existing contact
	 * @param contact contact.  */
	void remove(Contact contact); 
	
	/** Find existing contact by person
	 * @param person person. 
	 * @return contact */
	Contact findByPerson(Person person);
	
	/** Create a new telephone number
	 * @param contact contact. 
	 * @param value value.
	 * @param extension extension
	 * @param primary primary phone or not.
	 * @param active active phone or not.
	 * @param vcategory telephone number category.
	 * @return new telephone number */
	TelephoneNumber addTelephoneNumber(Contact contact, Long value, 
		Integer extension, Boolean primary, Boolean active, 
		TelephoneNumberCategory category) throws DuplicateEntityFoundException;
	
	/** Update an existing telephone number
	 * @param telephoneNumber existing telephone number. 
	 * @param value telephone number.
	 * @param extension extension
	 * @param primary primary phone or not.
	 * @param active active phone or not.
	 * @param vcategory telephone number category.
	 * @return updated telephone number */
	TelephoneNumber updateTelephoneNumber(TelephoneNumber telephoneNumber, 
		Long value, Integer extension, Boolean primary, 
		Boolean active,	TelephoneNumberCategory category) 
		throws DuplicateEntityFoundException;
	
	/** Remove an existing telephone number
	 * @param telephoneNumber telephone number. */
	void removeTelephoneNumber(TelephoneNumber telephoneNumber);
	
	/** Create a new address
	 * @param value value
	 * @param designator designator.
	 * @param coordinate coordinate
	 * @param buildingCategory building category.
	 * @param zipCode zip code
	 * @return new address */
	Address createAddress(String value, String designator, String coordinate, 
		BuildingCategory buildingCategory,ZipCode zipCode)
		throws DuplicateEntityFoundException;
	
	/** Update a new address
	 * @param address address
	 * @param value value 
	 * @param designator designator.
	 * @param coordinate coordinate
	 * @param buildingCategory building category.
	 * @param zipCode zip code
	 * @return updated address */
	Address updateAddress(Address address, String value, String designator, 
			String coordinate, BuildingCategory buildingCategory, 
			ZipCode zipCode) throws DuplicateEntityFoundException;
	
	/** Remove an existing address
	 * @param address address. */
	void removeAddress(Address address);
	
	/** Create an online account
	 * @param contact contact. 
	 * @param name name
	 * @param active active
	 * @param host online account host
	 * @param primary primary online account or not
	 * @return new online account */
	OnlineAccount addOnlineAccount(Contact contact, String name, Boolean active,
		OnlineAccountHost host, Boolean primary) 
		throws DuplicateEntityFoundException;
	
	/** Update an online account
	 * @param onlineAccount online account. 
	 * @param name name
	 * @param active active
	 * @param host online account host
	 * @param primary primary online account or not
	 * @return updated online account */
	OnlineAccount updateOnlineAccount(OnlineAccount onlineAccount, String name, 
		Boolean active,	OnlineAccountHost host, Boolean primary) 
		throws DuplicateEntityFoundException;
	
	/** Remove an online account
	 * @param onlineAccount online account. */
	void removeOnlineAccount(OnlineAccount onlineAccount);
	
	/** Find all online account categories
	 * @return a list of online account category  */
	OnlineAccountCategory[] findOnlineAccountCategories();
}