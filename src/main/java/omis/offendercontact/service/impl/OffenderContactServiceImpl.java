package omis.offendercontact.service.impl;

import java.util.List;

import omis.address.domain.Address;
import omis.address.domain.AddressUnitDesignator;
import omis.address.domain.BuildingCategory;
import omis.address.domain.StreetSuffix;
import omis.address.domain.ZipCode;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.AddressUnitDesignatorDelegate;
import omis.address.service.delegate.StreetSuffixDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.contact.domain.Contact;
import omis.contact.domain.OnlineAccount;
import omis.contact.domain.OnlineAccountHost;
import omis.contact.domain.TelephoneNumber;
import omis.contact.domain.TelephoneNumberCategory;
import omis.contact.domain.component.PoBox;
import omis.contact.service.delegate.ContactDelegate;
import omis.contact.service.delegate.OnlineAccountDelegate;
import omis.contact.service.delegate.OnlineAccountHostDelegate;
import omis.contact.service.delegate.TelephoneNumberDelegate;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.offendercontact.service.OffenderContactService;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;

/**
 * Implementation of service for offender contacat.
 *
 * @author Josh Divine
 * @version 0.1.0 (Dec 13, 2016)
 * @since OMIS 3.0
 */
public class OffenderContactServiceImpl implements OffenderContactService {
	
	private ContactDelegate contactDelegate;
	
	private AddressDelegate addressDelegate;
	
	private TelephoneNumberDelegate telephoneNumberDelegate;
	
	private OnlineAccountDelegate onlineAccountDelegate;
	
	private CityDelegate cityDelegate;
	
	private StateDelegate stateDelegate;
	
	private ZipCodeDelegate zipCodeDelegate;
	
	private CountryDelegate countryDelegate;
	
	private StreetSuffixDelegate streetSuffixDelegate;
	
	private AddressUnitDesignatorDelegate addressUnitDesignatorDelegate;
	
	private OnlineAccountHostDelegate onlineAccountHostDelegate;
	
	/**
	 * Constructor
	 * @param contactDelegate delegate for contacts
	 * @param addressDelegate delegate for addresses
	 * @param telephoneNumberDelegate delegate for telephone numbers
	 * @param onlineAccountDelegate delegate for online accounts
	 * @param cityDelegate delegate for cities
	 * @param stateDelegate delegate for states
	 * @param zipCodeDelegate delegate for zip codes
	 * @param countryDelegate delegate for countries
	 * @param streetSuffixDelegate delegate for street suffixes
	 * @param addressUnitDesignatorDelegate delegate for address unit 
	 * 	designators
	 */
	public OffenderContactServiceImpl(final ContactDelegate contactDelegate,
			final AddressDelegate addressDelegate,
			final TelephoneNumberDelegate telephoneNumberDelegate,
			final OnlineAccountDelegate onlineAccountDelegate,
			final CityDelegate cityDelegate,
			final StateDelegate stateDelegate,
			final ZipCodeDelegate zipCodeDelegate,
			final CountryDelegate countryDelegate,
			final StreetSuffixDelegate streetSuffixDelegate,
			final AddressUnitDesignatorDelegate addressUnitDesignatorDelegate,
			final OnlineAccountHostDelegate onlineAccountHostDelegate) {
		this.contactDelegate = contactDelegate;
		this.addressDelegate = addressDelegate;
		this.telephoneNumberDelegate = telephoneNumberDelegate;
		this.onlineAccountDelegate = onlineAccountDelegate;
		this.cityDelegate = cityDelegate;
		this.stateDelegate = stateDelegate;
		this.zipCodeDelegate = zipCodeDelegate;
		this.countryDelegate = countryDelegate;
		this.streetSuffixDelegate = streetSuffixDelegate;
		this.addressUnitDesignatorDelegate = addressUnitDesignatorDelegate;
		this.onlineAccountHostDelegate = onlineAccountHostDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public Contact changeContact(final Offender offender, 
			final Address mailingAddress, final PoBox poBox) {
		Contact contact = this.contactDelegate.find(offender);
		if (contact != null)
		{
			try {
				return this.contactDelegate.update(contact, mailingAddress, poBox);
			} catch (DuplicateEntityFoundException e) {
				throw new AssertionError("Duplicate contact exists", e);
			}
		} else {
			try {
				return this.contactDelegate.create(offender, mailingAddress, poBox);
			} catch (DuplicateEntityFoundException e) {
				throw new AssertionError("Duplicate contact exists", e);
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public Address findMailingAddress(final Offender offender) {
		Contact contact = this.contactDelegate.find(offender);
		if (contact != null) {
			return contact.getMailingAddress();
		} else {
			return null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public PoBox findPoBox(final Offender offender) {
		Contact contact = this.contactDelegate.find(offender);
		if (contact != null) {
			return contact.getPoBox();
		} else {
			return null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public TelephoneNumber createTelephoneNumber(final Offender offender, 
			final Long value, final Integer extension, final Boolean primary, 
			final TelephoneNumberCategory category, final Boolean active) 
					throws DuplicateEntityFoundException {
		Contact contact = this.contactDelegate.find(offender);
		if (contact == null) {
			try {
				contact = this.contactDelegate.create(offender, null, null);
			} catch (DuplicateEntityFoundException e) {
				throw new AssertionError("Contact exists", e);
			}
		}
		return this.telephoneNumberDelegate.create(
				contact, value, extension, primary, active, category);
	}

	/** {@inheritDoc} */
	@Override
	public TelephoneNumber updateTelephoneNumber(
			final TelephoneNumber telephoneNumber, final Long value, 
			final Integer extension, final Boolean primary, 
			final TelephoneNumberCategory category, final Boolean active) 
					throws DuplicateEntityFoundException {
		return this.telephoneNumberDelegate.update(
				telephoneNumber, value, extension, primary, active, category);
	}

	/** {@inheritDoc} */
	@Override
	public void removeTelephoneNumber(final TelephoneNumber telephoneNumber) {
		this.telephoneNumberDelegate.remove(telephoneNumber);
	}

	/** {@inheritDoc} */
	@Override
	public OnlineAccount createOnlineAccount(final Offender offender, 
			final String name, final OnlineAccountHost host, 
			final Boolean primary, final Boolean active) 
					throws DuplicateEntityFoundException {
		Contact contact = this.contactDelegate.find(offender);
		if (contact == null) {
			try {
				contact = this.contactDelegate.create(offender, null, null);
			} catch (DuplicateEntityFoundException e) {
				throw new AssertionError("Contact exists", e);
			}
		}
		return this.onlineAccountDelegate.create(contact, name, active, primary,
				host);
	}

	/** {@inheritDoc} */
	@Override
	public OnlineAccount updateOnlineAccount(final OnlineAccount onlineAccount, 
			final String name, final OnlineAccountHost host, 
			final Boolean primary, final Boolean active) 
					throws DuplicateEntityFoundException {
		return this.onlineAccountDelegate.update(onlineAccount, name, active, 
				primary, host);
	}

	/** {@inheritDoc} */
	@Override
	public void removeOnlineAccount(final OnlineAccount onlineAccount) {
		this.onlineAccountDelegate.remove(onlineAccount);
	}

	/** {@inheritDoc} */
	@Override
	public List<TelephoneNumber> findTelephoneNumbers(final Offender offender) {
		Contact contact = this.contactDelegate.find(offender);
		return this.telephoneNumberDelegate.findByContact(contact);
	}

	/** {@inheritDoc} */
	@Override
	public List<OnlineAccount> findOnlineAccounts(final Offender offender) {
		Contact contact = this.contactDelegate.find(offender);
		return this.onlineAccountDelegate.findByContact(contact);
	}

	/** {@inheritDoc} */
	@Override
	public List<Address> findAddressesByQuery(final String query) {
		return this.addressDelegate.findAddressesByValue(query);
	}

	/** {@inheritDoc} */
	@Override
	public Address createAddress(final String number, final ZipCode zipCode) 
					throws DuplicateEntityFoundException {
		return this.addressDelegate.findOrCreate(number, null, null,
				BuildingCategory.HOUSE, zipCode);
	}

	/** {@inheritDoc} */
	@Override
	public List<StreetSuffix> findStreetSuffixes() {
		return this.streetSuffixDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<AddressUnitDesignator> findAddressUnitDesignators() {
		return this.addressUnitDesignatorDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<Country> findCountries() {
		return this.countryDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<State> findStates(final Country country) {
		return this.stateDelegate.findByCountry(country);
	}

	/** {@inheritDoc} */
	@Override
	public List<City> findCitiesByState(final State state) {
		return this.cityDelegate.findByState(state);
	}

	/** {@inheritDoc} */
	@Override
	public List<City> findCitiesByCountryWithoutState(final Country country) {
		return this.cityDelegate.findByCountryWithoutState(country);
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasStates(final Country country) {
		return this.stateDelegate.countByCountry(country) > 0;
	}

	/** {@inheritDoc} */
	@Override
	public City createCity(final String name, final State state, 
			final Country country) throws DuplicateEntityFoundException {
		return this.cityDelegate.create(name, true, state, country);
	}

	/** {@inheritDoc} */
	@Override
	public ZipCode createZipCode(final String value, final String extension, 
			final City city) throws DuplicateEntityFoundException {
		return this.zipCodeDelegate.create(city, value, extension, true);
	}

	/** {@inheritDoc} */
	@Override
	public List<ZipCode> findZipCodesByCity(final City city) {
		return this.zipCodeDelegate.findByCity(city);
	}

	/** {@inheritDoc} */
	@Override
	public List<OnlineAccountHost> findOnlineAccountHosts() {
		return this.onlineAccountHostDelegate.findAll();
	}

		
}
