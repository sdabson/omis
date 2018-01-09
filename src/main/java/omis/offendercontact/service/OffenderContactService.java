package omis.offendercontact.service;

import java.util.List;

import omis.address.domain.Address;
import omis.address.domain.AddressUnitDesignator;
import omis.address.domain.StreetSuffix;
import omis.address.domain.ZipCode;
import omis.contact.domain.Contact;
import omis.contact.domain.OnlineAccount;
import omis.contact.domain.OnlineAccountHost;
import omis.contact.domain.TelephoneNumber;
import omis.contact.domain.TelephoneNumberCategory;
import omis.contact.domain.component.PoBox;
import omis.country.domain.Country;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Service for offender contact.
 * 
 * @author Josh Divine
 * @author Joel Norris
 * @version 0.1.0 (Dec 12, 2016)
 * @since OMIS 3.0
 */
public interface OffenderContactService {

	
	/**
	 * Change contact for offender.
	 * 
	 * @param offender offender
	 * @param mailingAddress mailing address
	 * @param poBox po box
	 * @return contact
	 */
	Contact changeContact(Offender offender, Address mailingAddress, 
			PoBox poBox); 
	
	/**
	 * Find the mailing address for an offender.
	 * 
	 * @param offender offender
	 * @return address
	 */
	Address findMailingAddress(Offender offender); 
	
	/**
	 * Find the po box for an offender.
	 * 
	 * @param offender offender
	 * @return po box
	 */
	PoBox findPoBox(Offender offender);

	/**
	 * Create a new telephone number.
	 * 
	 * @param offender offender
	 * @param value telephone number
	 * @param extension extension
	 * @param primary primary
	 * @param category telephone number category
	 * @param active active
	 * @return telephone number
	 * @throws DuplicateEntityFoundException thrown when duplicate entity 
	 * exists
	 */
	TelephoneNumber createTelephoneNumber(Offender offender, Long value, 
			Integer extension, Boolean primary, 
			TelephoneNumberCategory category, Boolean active) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates and existing telephone number.
	 * 
	 * @param telephoneNumber existing telephone number
	 * @param value telephone number
	 * @param extension extension
	 * @param primary primary
	 * @param category telephone number category
	 * @param active active
	 * @return telephone number
	 * @throws DuplicateEntityFoundException thrown when duplicate entity 
	 * exists
	 */
	TelephoneNumber updateTelephoneNumber(TelephoneNumber telephoneNumber, 
			Long value, Integer extension, Boolean primary, 
			TelephoneNumberCategory category, Boolean active) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified telephone number.
	 * 
	 * @param telephoneNumber telephone number
	 */
	void removeTelephoneNumber(TelephoneNumber telephoneNumber);
	
	/**
	 * Create a new online account for an offender.
	 * 
	 * @param offender offender
	 * @param name name
	 * @param host host
	 * @param primary primary
	 * @param active active
	 * @return online account
	 * @throws DuplicateEntityFoundException thrown when duplicate entity 
	 * exists
	 */
	OnlineAccount createOnlineAccount(Offender offender, String name, 
			OnlineAccountHost host, Boolean primary, Boolean active) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Update an existing online account.
	 * 
	 * @param onlineAccount online account
	 * @param name name
	 * @param host host
	 * @param primary primary
	 * @param active active
	 * @return online account
	 * @throws DuplicateEntityFoundException thrown when duplicate entity 
	 * exists
	 */
	OnlineAccount updateOnlineAccount(OnlineAccount onlineAccount, 
			String name, OnlineAccountHost host, Boolean primary, 
			Boolean active) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Remove the specified online account.
	 * 
	 * @param onlineAccount online account
	 */
	void removeOnlineAccount(OnlineAccount onlineAccount);
	
	/**
	 * Find all telephone numbers for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of telephone numbers
	 */
	List<TelephoneNumber> findTelephoneNumbers(Offender offender); 
	
	/**
	 * Find all online accounts for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of online accounts
	 */
	List<OnlineAccount> findOnlineAccounts(Offender offender);
	
	/**
	 * Find addresses by the specified query string.
	 * 
	 * @param query query
	 * @return list of addresses
	 */
	List<Address> findAddressesByQuery(String query);
	
	/**
	 * Create a new address.
	 * 
	 * @param number number
	 * @param zipCode zip code
	 * @return address
	 * @throws DuplicateEntityFoundException thrown when duplicate entity 
	 * exists
	 */
	Address createAddress(String number, ZipCode zipCode) 
					throws DuplicateEntityFoundException;
	
	/**
	 * Finds all street suffixes.
	 * 
	 * @return list of street suffixes
	 */
	List<StreetSuffix> findStreetSuffixes();
	
	/**
	 * Finds all address unit designators.
	 * 
	 * @return list of address unit designators
	 */
	List<AddressUnitDesignator> findAddressUnitDesignators();
	
	/**
	 * Find all countries
	 * @return list of countries
	 */
	List<Country> findCountries();
	
	/**
	 * Find all states for the specified country.
	 * 
	 * @param country country
	 * @return list of states
	 */
	List<State> findStates(Country country);
	
	/**
	 * Find all cities for the specified state.
	 * 
	 * @param state state
	 * @return list of cities
	 */
	List<City> findCitiesByState(State state);
	
	/**
	 * Find all cities for the specified country that have no state.
	 * 
	 * @param country country
	 * @return list of cities
	 */
	List<City> findCitiesByCountryWithoutState(Country country);
	
	/**
	 * Returns whether or not the specified country has states.
	 * 
	 * @param country country
	 * @return boolean
	 */
	boolean hasStates(Country country);
	
	/**
	 * Creates a new city.
	 * 
	 * @param name name 
	 * @param state state
	 * @param country country
	 * @return city
	 * @throws DuplicateEntityFoundException thrown when duplicate entity 
	 * exists
	 */
	City createCity(String name, State state, Country country) 
			throws DuplicateEntityFoundException;
	
	/**
	 * Creates a new zip code.
	 * 
	 * @param value zip code
	 * @param extension extension
	 * @param city city
	 * @return zip code
	 * @throws DuplicateEntityFoundException thrown when duplicate entity 
	 * exists
	 */
	ZipCode createZipCode(String value, String extension, City city) 
			throws DuplicateEntityFoundException;
	
	/**
	 * Find all zip codes for the specified city.
	 * 
	 * @param city city
	 * @return list of zip codes
	 */
	List<ZipCode> findZipCodesByCity(City city);

	/**
	 * Returns online account hosts.
	 * 
	 * @return online account hosts
	 */
	List<OnlineAccountHost> findOnlineAccountHosts();
	
}
