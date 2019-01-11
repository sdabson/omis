/*
 * OMIS - Offender Management Information System
 * Copyright (C) 2011 - 2017 State of Montana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package omis.offendercontact.service;

import java.util.Date;
import java.util.List;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.address.exception.AddressExistsException;
import omis.address.exception.ZipCodeExistsException;
import omis.contact.domain.Contact;
import omis.contact.domain.OnlineAccount;
import omis.contact.domain.OnlineAccountHost;
import omis.contact.domain.TelephoneNumber;
import omis.contact.domain.TelephoneNumberCategory;
import omis.contact.domain.component.PoBox;
import omis.contact.exception.ContactExistsException;
import omis.contact.exception.OnlineAccountExistsException;
import omis.contact.exception.TelephoneNumberExistsException;
import omis.country.domain.Country;
import omis.offender.domain.Offender;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.exception.CityExistsException;
import omis.residence.domain.ResidenceTerm;
import omis.residence.exception.NonResidenceTermConflictException;
import omis.residence.exception.ResidenceTermConflictException;

/**
 * Service for offender contact.
 * 
 * @author Josh Divine
 * @author Joel Norris
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.1 (Oct 2, 2018)
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
	 * @throws TelephoneNumberExistsException thrown when duplicate entity 
	 * exists
	 * @throws ContactExistsException 
	 */
	TelephoneNumber createTelephoneNumber(Offender offender, Long value, 
			Integer extension, Boolean primary, 
			TelephoneNumberCategory category, Boolean active) 
					throws TelephoneNumberExistsException, ContactExistsException;
	
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
	 * @throws TelephoneNumberExistsException thrown when duplicate entity 
	 * exists
	 */
	TelephoneNumber updateTelephoneNumber(TelephoneNumber telephoneNumber, 
			Long value, Integer extension, Boolean primary, 
			TelephoneNumberCategory category, Boolean active) 
					throws TelephoneNumberExistsException;
	
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
	 * @throws OnlineAccountExistsException thrown when duplicate entity 
	 * exists
	 */
	OnlineAccount createOnlineAccount(Offender offender, String name, 
			OnlineAccountHost host, Boolean primary, Boolean active) 
					throws OnlineAccountExistsException;
	
	/**
	 * Update an existing online account.
	 * 
	 * @param onlineAccount online account
	 * @param name name
	 * @param host host
	 * @param primary primary
	 * @param active active
	 * @return online account
	 * @throws OnlineAccountExistsException thrown when duplicate entity 
	 * exists
	 */
	OnlineAccount updateOnlineAccount(OnlineAccount onlineAccount, 
			String name, OnlineAccountHost host, Boolean primary, 
			Boolean active) 
					throws OnlineAccountExistsException;
	
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
	 * @throws AddressExistsException thrown when duplicate entity 
	 * exists
	 */
	Address createAddress(String number, ZipCode zipCode) 
					throws AddressExistsException;
	 
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
	 * @throws CityExistsException thrown when duplicate entity 
	 * exists
	 */
	City createCity(String name, State state, Country country) 
			throws CityExistsException;
	
	/**
	 * Creates a new zip code.
	 * 
	 * @param value zip code
	 * @param extension extension
	 * @param city city
	 * @return zip code
	 * @throws ZipCodeExistsException thrown when duplicate entity 
	 * exists
	 */
	ZipCode createZipCode(String value, String extension, City city) 
			throws ZipCodeExistsException;
	
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

	/**
	 * Changes primary residence.
	 * 
	 * <p>If the primary residence term on the {@code effectiveDate} has the
	 * address supplied, no action is taken.
	 * 
	 * <p>{@code effectiveDate} will be used as the start date of the new term
	 * if one is created. A newly created residence term will be unconfirmed
	 * and without comments.
	 * 
	 * <p>If another primary residence exists on date, it will be ended with the
	 * {@code effectiveDate}.
	 * 
	 * <p>If a change in residence term is made, any homeless terms active on
	 * the effective date will be ended with the {@code effectiveDate}.
	 * 
	 * @param offender offender
	 * @param address address
	 * @param effectiveDate effective date
	 * @return residence term reflective of change
	 * @throws ResidenceTermConflictException if a conflicting primary residence
	 * exists with a none-null end date
	 * @throws NonResidenceTermConflictException if a conflicting homeless term
	 * exists with a none-null end date
	 */
	ResidenceTerm changePrimaryResidence(Offender offender, Address address,
			Date effectiveDate)
					throws ResidenceTermConflictException,
							NonResidenceTermConflictException;
	/**
	 * Returns primary residence term on date.
	 * 
	 * @param offender offender
	 * @param effectiveDate effective date
	 * @return primary residence term on date
	 */
	ResidenceTerm findPrimaryResidence(Offender offender, Date effectiveDate);
	
	/**
	 * Returns the home state.
	 * 
	 * @return home state
	 */
	State findHomeState();
}