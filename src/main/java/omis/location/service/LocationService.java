package omis.location.service;

import java.util.List;

import omis.address.domain.Address;
import omis.address.domain.AddressUnitDesignator;
import omis.address.domain.StreetSuffix;
import omis.address.domain.ZipCode;
import omis.country.domain.Country;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.location.domain.Location;
import omis.organization.domain.Organization;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Service for locations.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (July 29, 2015)
 * @since OMIS 3.0
 */
public interface LocationService {
	
	/**
	 * Returns locations by organization.
	 * 
	 * @param organization organization
	 * @return locations by organization
	 */
	List<Location> findByOrganization(Organization organization);
	
	/**
	 * Returns locations
	 * 
	 * @return locations
	 */
	List<Location> findAll();
	
	/**
	 * Returns organization.
	 * 
	 * @param name name
	 * @return organization
	 */
	Organization findOrganization(String name);
	
	/**
	 * Creates organization.
	 * 
	 * @param name name
	 * @return created organization
	 * @throws DuplicateEntityFoundException if organization exists
	 */
	Organization createOrganization(String name)
			throws DuplicateEntityFoundException;
	
	/**
	 * Creates location.
	 * 
	 * @param organization organization
	 * @param dateRange date range
	 * @param address address
	 * @return created organization
	 * @throws DuplicateEntityFoundException if organization exists
	 */
	Location create(Organization organization, DateRange dateRange,
			Address address) throws DuplicateEntityFoundException;

	/**
	 * Updates location.
	 * 
	 * @param location location to update
	 * @param organization organization
	 * @param dateRange date range
	 * @param address address
	 * @return updated organization
	 * @throws DuplicateEntityFoundException if organization exists
	 */
	Location update(Location location, Organization organization,
			DateRange dateRange, Address address)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes location.
	 * 
	 * @param location location to remove
	 */
	void remove(Location location);

	/**
	 * Returns organizations by partial name.
	 * 
	 * @param partialName part name
	 * @return organizations by partial name
	 */
	List<Organization> findOrganizationsByPartialName(String partialName);

	/**
	 * Returns States by country.
	 * 
	 * @param country country
	 * @return States by country
	 */
	List<State> findStates(Country country);

	/**
	 * Returns cities by State.
	 * 
	 * @param state State
	 * @return cities by State
	 */
	List<City> findCitiesByState(State state);
	
	/**
	 * Returns cities by country.
	 * 
	 * @param country country
	 * @return cities by country
	 */
	List<City> findCitiesByCountry(Country country);
	
	/**
	 * Returns whether country has States.
	 * 
	 * @param country country
	 * @return whether country has States
	 */
	boolean hasStates(Country country);
	
	/**
	 * Returns ZIP codes.
	 * 
	 * @param city city
	 * @return ZIP codes
	 */
	List<ZipCode> findZipCodes(City city);

	/**
	 * Returns street suffixes.
	 * 
	 * @return street suffixes
	 */
	List<StreetSuffix> findStreetSuffixes();

	/**
	 * Returns address unit designators.
	 * 
	 * @return address unit designators
	 */
	List<AddressUnitDesignator> findAddressUnitDesignators();

	/**
	 * Returns countries.
	 * 
	 * @return countries
	 */
	List<Country> findCountries();
	
	/**
	 * Returns addresses by query.
	 * 
	 * @param query query
	 * @return addresses by query
	 */
	List<Address> findAddressesByQuery(String query);
	
	/**
	 * Creates address.
	 * 
	 * @param number number
	 * @param zipCode ZIP code
	 * @return address
	 * @throws DuplicateEntityFoundException if address exists
	 */
	Address createAddress(String number, ZipCode zipCode) throws DuplicateEntityFoundException;
	
	/**
	 * Creates city.
	 * 
	 * @param name name
	 * @param state State
	 * @param country country
	 * @return created city
	 * @throws DuplicateEntityFoundException if city exists
	 */
	City createCity(String name, State state, Country country)
		throws DuplicateEntityFoundException;
	
	/**
	 * Creates ZIP code.
	 * 
	 * @param value value
	 * @param extension extension
	 * @param city city
	 * @return created ZIP code
	 * @throws DuplicateEntityFoundException if ZIP code exists
	 */
	ZipCode createZipCode(String value, String extension, City city)
		throws DuplicateEntityFoundException;
}