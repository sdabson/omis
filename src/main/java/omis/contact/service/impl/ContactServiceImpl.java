package omis.contact.service.impl;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.domain.ZipCode;
import omis.address.service.delegate.AddressDelegate;
import omis.contact.domain.Contact;
import omis.contact.domain.OnlineAccount;
import omis.contact.domain.OnlineAccountCategory;
import omis.contact.domain.OnlineAccountHost;
import omis.contact.domain.TelephoneNumber;
import omis.contact.domain.TelephoneNumberCategory;
import omis.contact.domain.component.PoBox;
import omis.contact.service.ContactService;
import omis.contact.service.delegate.ContactDelegate;
import omis.contact.service.delegate.OnlineAccountDelegate;
import omis.contact.service.delegate.TelephoneNumberDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;

/**
 * Implementation of contact service.
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (April 3, 2015)
 * @since: OMIS 3.0
 */

public class ContactServiceImpl implements ContactService {
	/**
	 * Instantiates an instance of contact service 
	 * @param contactDelegate contact delegate
	 * @param telephoneNumberDelegate telephone number delegate
	 * @param addressDelegate address delegate
	 * @param onlineAccountDelegate online account delegate
	 * @return contact contact
	 */
	private final ContactDelegate contactDelegate;
	private final TelephoneNumberDelegate telephoneNumberDelegate;
	private final AddressDelegate addressDelegate;
	private final OnlineAccountDelegate onlineAccountDelegate;
	
/* Audit Component Retriever */
	
	public ContactServiceImpl(final ContactDelegate contactDelegate,
		final TelephoneNumberDelegate telephoneNumberDelegate,
		final AddressDelegate addressDelegate,
		final OnlineAccountDelegate onlineAccountDelegate) {
			this.contactDelegate = contactDelegate;
			this.telephoneNumberDelegate = telephoneNumberDelegate;
			this.addressDelegate = addressDelegate;
			this.onlineAccountDelegate = onlineAccountDelegate;
	}	

	/** {@inheritDoc} */
	@Override
	public Contact create(final Person person, final Address mailingAddress, 
		final PoBox poBox) throws DuplicateEntityFoundException {
		return this.contactDelegate.create(person, mailingAddress, poBox);
	}
	
	/** {@inheritDoc} */
	@Override
	public Contact update(final Contact contact, final Address mailingAddress, 
		final PoBox poBox) throws DuplicateEntityFoundException {
		return this.contactDelegate.update(contact, mailingAddress, poBox);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final Contact contact) {
		this.contactDelegate.remove(contact); 
	}
	
	/** {@inheritDoc} */
	@Override
	public Contact findByPerson(final Person person) {
		return this.contactDelegate.find(person);
	}
	
	/** {@inheritDoc} */
	@Override
	public TelephoneNumber addTelephoneNumber(final Contact contact, 
		final Long value, final Integer extension, final Boolean primary, 
		final Boolean active, final TelephoneNumberCategory category) 
			throws DuplicateEntityFoundException{
			return this.telephoneNumberDelegate.create(contact, value, extension, 
			primary, active, category);
	}
	
	/** {@inheritDoc} */
	@Override
	public TelephoneNumber updateTelephoneNumber(
		final TelephoneNumber telephoneNumber, final Long value, 
		final Integer extension, final Boolean primary,	final Boolean active,	
		final TelephoneNumberCategory category)
			throws DuplicateEntityFoundException {
		return this.telephoneNumberDelegate.update(telephoneNumber, value, 
			extension, primary, active, category);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeTelephoneNumber(final TelephoneNumber telephoneNumber){
		this.telephoneNumberDelegate.remove(telephoneNumber);
		
	}
	
	/** {@inheritDoc} */
	@Override
	public Address createAddress(final String value, final String designator, 
		final String coordinate, final BuildingCategory buildingCategory, final ZipCode zipCode) 
			throws DuplicateEntityFoundException{
			return this.addressDelegate.findOrCreate(value, designator, coordinate, 
				buildingCategory, zipCode);
	}
	
	/** {@inheritDoc} */
	@Override
	public Address updateAddress(final Address address, final String value, 
		final String designator, final String coordinate, 
		final BuildingCategory buildingCategory, final ZipCode zipCode) 
		throws DuplicateEntityFoundException {
		return this.addressDelegate.update(address, value, designator, 
			coordinate, buildingCategory, zipCode);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeAddress(final Address address){
		this.addressDelegate.remove(address);
	}
	
	/** {@inheritDoc} */
	@Override
	public OnlineAccount addOnlineAccount(final Contact contact, 
		final String name, final Boolean active, final OnlineAccountHost host,
		final Boolean primary) 
		throws DuplicateEntityFoundException{
		return this.onlineAccountDelegate.create(contact, name, active, primary, 
			host);
	}
	
	/** {@inheritDoc} */
	@Override
	public OnlineAccount updateOnlineAccount(final OnlineAccount onlineAccount, 
		final String name, final Boolean active, final OnlineAccountHost host, 
		final Boolean primary) 
		throws DuplicateEntityFoundException {
		return this.onlineAccountDelegate.update(onlineAccount, name, active, 
			primary, host);
	}
	
	/** {@inheritDoc} */
	@Override
	public void removeOnlineAccount(final OnlineAccount onlineAccount){
		this.onlineAccountDelegate.remove(onlineAccount);
	}
	
	/** {@inheritDoc} */
	@Override
	public OnlineAccountCategory[] findOnlineAccountCategories(){
		return OnlineAccountCategory.values();
	}
}