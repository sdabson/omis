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
package omis.offendercontact.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import omis.address.domain.Address;
import omis.address.domain.BuildingCategory;
import omis.address.domain.ZipCode;
import omis.address.exception.ZipCodeExistsException;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.ZipCodeDelegate;
import omis.contact.domain.Contact;
import omis.contact.domain.OnlineAccount;
import omis.contact.domain.OnlineAccountHost;
import omis.contact.domain.TelephoneNumber;
import omis.contact.domain.TelephoneNumberCategory;
import omis.contact.domain.component.PoBox;
import omis.contact.exception.ContactExistsException;
import omis.contact.exception.OnlineAccountExistsException;
import omis.contact.exception.TelephoneNumberExistsException;
import omis.contact.service.delegate.ContactDelegate;
import omis.contact.service.delegate.OnlineAccountDelegate;
import omis.contact.service.delegate.OnlineAccountHostDelegate;
import omis.contact.service.delegate.TelephoneNumberDelegate;
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.offendercontact.service.OffenderContactService;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.exception.CityExistsException;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.residence.domain.NonResidenceTerm;
import omis.residence.domain.ResidenceCategory;
import omis.residence.domain.ResidenceStatus;
import omis.residence.domain.ResidenceTerm;
import omis.residence.exception.NonResidenceTermConflictException;
import omis.residence.exception.NonResidenceTermExistsException;
import omis.residence.exception.ResidenceTermConflictException;
import omis.residence.exception.ResidenceTermExistsException;
import omis.residence.service.delegate.NonResidenceTermDelegate;
import omis.residence.service.delegate.ResidenceTermDelegate;

/**
 * Implementation of service for offender contact.
 *
 * @author Josh Divine
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @version 0.1.1 (Oct 2, 2018)
 * @since OMIS 3.0
 */
public class OffenderContactServiceImpl implements OffenderContactService {
	
	private final ContactDelegate contactDelegate;
	
	private final AddressDelegate addressDelegate;
	
	private final TelephoneNumberDelegate telephoneNumberDelegate;
	
	private final OnlineAccountDelegate onlineAccountDelegate;
	
	private final CityDelegate cityDelegate;
	
	private final StateDelegate stateDelegate;
	
	private final ZipCodeDelegate zipCodeDelegate;
	
	private final CountryDelegate countryDelegate;
	
	private final OnlineAccountHostDelegate onlineAccountHostDelegate;
	
	private final ResidenceTermDelegate residenceTermDelegate;
	
	private final NonResidenceTermDelegate nonResidenceTermDelegate;
	
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
	 * @param residenceTermDelegate delegate for residence terms
	 * @param nonResidenceTermDelegate delegate for non-residence terms
	 */
	public OffenderContactServiceImpl(final ContactDelegate contactDelegate,
			final AddressDelegate addressDelegate,
			final TelephoneNumberDelegate telephoneNumberDelegate,
			final OnlineAccountDelegate onlineAccountDelegate,
			final CityDelegate cityDelegate,
			final StateDelegate stateDelegate,
			final ZipCodeDelegate zipCodeDelegate,
			final CountryDelegate countryDelegate,
			final OnlineAccountHostDelegate onlineAccountHostDelegate,
			final ResidenceTermDelegate residenceTermDelegate,
			final NonResidenceTermDelegate nonResidenceTermDelegate) {
		this.contactDelegate = contactDelegate;
		this.addressDelegate = addressDelegate;
		this.telephoneNumberDelegate = telephoneNumberDelegate;
		this.onlineAccountDelegate = onlineAccountDelegate;
		this.cityDelegate = cityDelegate;
		this.stateDelegate = stateDelegate;
		this.zipCodeDelegate = zipCodeDelegate;
		this.countryDelegate = countryDelegate;
		this.onlineAccountHostDelegate = onlineAccountHostDelegate;
		this.residenceTermDelegate = residenceTermDelegate;
		this.nonResidenceTermDelegate = nonResidenceTermDelegate;
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
			} catch (ContactExistsException e) {
				throw new AssertionError("Duplicate contact exists", e);
			}
		} else {
			try {
				return this.contactDelegate.create(offender, mailingAddress, poBox);
			} catch (ContactExistsException e) {
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
					throws TelephoneNumberExistsException, ContactExistsException {
		Contact contact = this.contactDelegate.find(offender);
		if (contact == null) {
			try {
				contact = this.contactDelegate.create(offender, null, null);
			} catch (ContactExistsException e) {
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
					throws TelephoneNumberExistsException {
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
			final Boolean primary, final Boolean active) throws OnlineAccountExistsException {
		Contact contact = this.contactDelegate.find(offender);
		if (contact == null) {
			try {
				contact = this.contactDelegate.create(offender, null, null);
			} catch (ContactExistsException e) {
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
			final Boolean primary, final Boolean active) throws OnlineAccountExistsException  {
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
	public Address createAddress(final String number, final ZipCode zipCode) {
		return this.addressDelegate.findOrCreate(number, null, null,
				BuildingCategory.HOUSE, zipCode);
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

	/** {@inheritDoc} 
	 * @throws CityExistsException */
	@Override
	public City createCity(final String name, final State state, 
			final Country country) throws CityExistsException  {
		return this.cityDelegate.create(name, true, state, country);
	}

	/** {@inheritDoc} 
	 * @throws ZipCodeExistsException */
	@Override
	public ZipCode createZipCode(final String value, final String extension, 
			final City city) throws ZipCodeExistsException  {
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

	/** {@inheritDoc} */
	@Override
	public ResidenceTerm changePrimaryResidence(
			final Offender offender, final Address address,
			final Date effectiveDate)
					throws ResidenceTermConflictException,
						NonResidenceTermConflictException {
		
		// Checks parameters
		Objects.requireNonNull(offender, "Offender required");
		Objects.requireNonNull(address, "Address required");
		Objects.requireNonNull(effectiveDate, "Effective date required");
		
		// Returns existing residence term unmodified if address matches
		// Otherwise ends active residence term on effective date with effective
		// date if end date of former is null; if not null, throw exception
		ResidenceTerm residenceTerm = this.residenceTermDelegate
				.findResidenceTerm(offender, effectiveDate,
						ResidenceCategory.PRIMARY, ResidenceStatus.RESIDENT);
		if (residenceTerm != null) {
			if (residenceTerm.getAddress().equals(address)) {
				return residenceTerm;
			} else if (DateRange.getEndDate(
						residenceTerm.getDateRange()) == null) {
				try {
					this.residenceTermDelegate.updateResidenceTerm(
						residenceTerm,
						DateRange.adjustEndDate(
								residenceTerm.getDateRange(), effectiveDate),
						residenceTerm.getCategory(), residenceTerm.getAddress(),
						residenceTerm.getStatus(), residenceTerm.getConfirmed(),
						residenceTerm.getNotes(),
						residenceTerm.getVerificationSignature());
				} catch (ResidenceTermExistsException e) {
					
					// Logically impossible
					throw new AssertionError(
							"Logically impossible condition", e);
				}
			} else {
				throw new ResidenceTermConflictException(
						"End date of existing residence term must be null");
			}
		}
		
		// Finds homeless term that's active on effective date and ends it if
		// end date is null; if not null, throw exception
		List<NonResidenceTerm> activeHomelessTerms
			= this.nonResidenceTermDelegate
				.findByPersonWithStatusOnDate(
						offender, ResidenceStatus.HOMELESS, effectiveDate);
		if (activeHomelessTerms.size() == 1) {
			NonResidenceTerm activeHomelessTerm = activeHomelessTerms.get(0);
			if (DateRange.getEndDate(
					activeHomelessTerm.getDateRange()) == null) {
				try {
					this.nonResidenceTermDelegate.updateHomelessTerm(
							activeHomelessTerm,
							DateRange.adjustEndDate(
									activeHomelessTerm.getDateRange(),
									effectiveDate),
							activeHomelessTerm.getCity(),
							activeHomelessTerm.getState(),
							activeHomelessTerm.getNotes(),
							activeHomelessTerm.getConfirmed());
				} catch (NonResidenceTermExistsException e) {
					
					// Logically impossible
					throw new AssertionError(
							"Logically impossible condition", e);
				}
			} else {
				throw new NonResidenceTermConflictException(
						"End date of existing homeless term must be null");
			}
		} else if (activeHomelessTerms.size() != 0) {
			throw new AssertionError(
					"Only one active homeless term allowed on date");
		}
		
		// Returns new primary residence on date
		try {
			return this.residenceTermDelegate.createResidenceTerm(offender,
					new DateRange(effectiveDate, null),
					ResidenceCategory.PRIMARY, address,
					ResidenceStatus.RESIDENT, false, null, null);
		} catch (ResidenceTermExistsException e) {
			
			// Logically impossible
			throw new AssertionError("Logically impossible condition", e);
		}
	}

	/** {@inheritDoc} */
	@Override
	public ResidenceTerm findPrimaryResidence(
			final Offender offender, final Date effectiveDate) {
		return this.residenceTermDelegate
				.findResidenceTerm(offender, effectiveDate,
						ResidenceCategory.PRIMARY, ResidenceStatus.RESIDENT);
	}

	/** {@inheritDoc} */
	@Override
	public State findHomeState() {
		return this.stateDelegate.findHomeState();
	}		
}