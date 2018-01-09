package omis.location.service.impl;

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
import omis.country.domain.Country;
import omis.country.service.delegate.CountryDelegate;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.location.service.LocationService;
import omis.location.service.delegate.LocationDelegate;
import omis.organization.domain.Organization;
import omis.organization.service.delegate.OrganizationDelegate;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;

/**
 * Implementation of service for locations.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (July 29, 2015)
 * @since OMIS 3.0
 */
public class LocationServiceImpl
		implements LocationService {

	private final LocationDelegate locationDelegate;
	
	private final OrganizationDelegate organizationDelegate;
	
	private final StreetSuffixDelegate streetSuffixDelegate;
	
	private final AddressUnitDesignatorDelegate addressUnitDesignatorDelegate;
	
	private final StateDelegate stateDelegate;
	
	private final CityDelegate cityDelegate;
	
	private final ZipCodeDelegate zipCodeDelegate;
	
	private final CountryDelegate countryDelegate;
	
	private final AddressDelegate addressDelegate;

	/**
	 * Instantiates an implementation of service for locations.
	 * 
	 * @param locationDelegate delegate for locations
	 * @param contactDelegate delegate for contacts
	 * @param streetSuffixDelegate delegate for street suffixes
	 * @param addressUnitDesignatorDelegate delegate for address unit
	 * designators
	 * @param stateDelegate delegate for States
	 * @param cityDelegate delegate for cities
	 * @param zipCodeDelegate delegate for ZIP codes
	 * @param countryDelegate delegate for countries
	 * @param addressDelegate delegate for addresses
	 */
	public LocationServiceImpl(
			final LocationDelegate locationDelegate,
			final OrganizationDelegate organizationDelegate,
			final StreetSuffixDelegate streetSuffixDelegate,
			final AddressUnitDesignatorDelegate addressUnitDesignatorDelegate,
			final StateDelegate stateDelegate,
			final CityDelegate cityDelegate,
			final ZipCodeDelegate zipCodeDelegate,
			final CountryDelegate countryDelegate,
			final AddressDelegate addressDelegate) {
		this.locationDelegate = locationDelegate;
		this.organizationDelegate = organizationDelegate;
		this.streetSuffixDelegate = streetSuffixDelegate;
		this.addressUnitDesignatorDelegate = addressUnitDesignatorDelegate;
		this.stateDelegate = stateDelegate;
		this.cityDelegate = cityDelegate;
		this.zipCodeDelegate = zipCodeDelegate;
		this.countryDelegate = countryDelegate;
		this.addressDelegate = addressDelegate;
	}

	/** {@inheritDoc} */
	@Override
	public List<Location> findByOrganization(
			final Organization organization) {
		return this.locationDelegate.findByOrganization(organization);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Location> findAll() {
		return this.locationDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final Location location) {
		this.locationDelegate.remove(location);
	}

	/** {@inheritDoc} */
	@Override
	public Organization findOrganization(final String name) {
		return this.organizationDelegate.findByName(name);
	}

	/** {@inheritDoc} */
	@Override
	public Organization createOrganization(final String name) 
			throws DuplicateEntityFoundException {
		return this.organizationDelegate.create(name, null, null);
	}

	/** {@inheritDoc} */
	@Override
	public Location create(
			final Organization organization, final DateRange dateRange,
			final Address address) throws DuplicateEntityFoundException {
		return this.locationDelegate.create(organization, dateRange, address);
	}

	/** {@inheritDoc} */
	@Override
	public Location update(final Location location,
			final Organization organization,
			final DateRange dateRange, final Address address)
					throws DuplicateEntityFoundException {
		return this.locationDelegate.update(
				location, organization, dateRange, address);
	}

	/** {@inheritDoc} */
	@Override
	public List<Organization> findOrganizationsByPartialName(
			final String partialName) {
		return this.organizationDelegate.findByPartialName(partialName);
	}

	/** {@inheritDoc} */
	@Override
	public List<State> findStates(final Country country) {
		return this.stateDelegate.findByCountry(country);
	}

	/** {@inheritDoc} */
	@Override
	public List<City> findCitiesByState(State state) {
		return this.cityDelegate.findByState(state);
	}

	/** {@inheritDoc} */
	@Override
	public List<City> findCitiesByCountry(
			final Country country) {
		return this.cityDelegate.findByCountry(country);
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasStates(
			final Country country) {
		return this.stateDelegate.countByCountry(country) > 0;
	}

	/** {@inheritDoc} */
	@Override
	public List<ZipCode> findZipCodes(final City city) {
		return this.zipCodeDelegate.findByCity(city);
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
	public List<Address> findAddressesByQuery(final String query) {
		return this.addressDelegate.findAddressesByValue(query);
	}

	/** {@inheritDoc} */
	@Override
	public Address createAddress(
			final String number, final ZipCode zipCode)
					throws DuplicateEntityFoundException {
		return this.addressDelegate.findOrCreate(number, null, null,
				BuildingCategory.HOUSE, zipCode);
	}

	/** {@inheritDoc} */
	@Override
	public City createCity(
			final String name, final State state, final Country country)
					throws DuplicateEntityFoundException {
		return this.cityDelegate.create(name, true, state, country);
	}

	/** {@inheritDoc} */
	@Override
	public ZipCode createZipCode(
			final String value, final String extension, final City city)
					throws DuplicateEntityFoundException {
		return this.zipCodeDelegate.create(city, value, extension, true);
	}
}