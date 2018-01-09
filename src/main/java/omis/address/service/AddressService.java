package omis.address.service;

import java.util.List;

import omis.address.domain.Address;
import omis.address.domain.AddressUnitDesignator;
import omis.address.domain.StreetSuffix;
import omis.address.domain.ZipCode;
import omis.country.domain.Country;
import omis.exception.DuplicateEntityFoundException;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Service for addresses.
 * 
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.1 (Jan 25, 2016)
 * @since OMIS 3.0
 */
public interface AddressService {

	/**
	 * Returns addresses.
	 * 
	 * @return addresses
	 */
	List<Address> findAll();
	
	/**
	 * Returns street suffixes.
	 * 
	 * @return street suffixes
	 */
	List<StreetSuffix> findStreetSuffixes();
	
	/**
	 * Returns unit designators.
	 * 
	 * @return unit designators
	 */
	List<AddressUnitDesignator> findUnitDesignators();
	
	/**
	 * Returns countries.
	 * 
	 * @return countries
	 */
	List<Country> findCountries();
	
	/**
	 * Returns States by country.
	 * 
	 * @param country country
	 * @return States by country
	 */
	List<State> findStatesByCountry(Country country);
	
	/**
	 * Returns whether country has States.
	 * 
	 * @param country country
	 * @return whether country has States
	 */
	boolean hasStates(Country country);
	
	/**
	 * Returns cities by country.
	 * 
	 * @param country country
	 * @return cities by country
	 */
	List<City> findCitiesByCountry(Country country);
	
	/**
	 * Returns cities by State.
	 * 
	 * @param state State
	 * @return cities by State
	 */
	List<City> findCitiesByState(State state);
	
	/**
	 * Returns ZIP codes by city.
	 * @param city city.
	 * 
	 * @return ZIP codes by city
	 */
	List<ZipCode> findZipCodesByCity(City city);
	
	/**
	 * Returns ZIP codes by State.
	 * 
	 * @param state State
	 * @return ZIP codes by State
	 */
	List<ZipCode> findZipCodesByState(State state);
	
	/**
	 * Creates address.
	 * 
	 * @param value value
	 * @param zipCode ZIP code
	 * @return created address
	 * @throws DuplicateEntityFoundException if address exists
	 */
	Address create(String value,
			ZipCode zipCode)
					throws DuplicateEntityFoundException;
	
	/**
	 * Updates address.
	 * 
	 * @param address address to update
	 * @param value value
	 * @param zipCode ZIP code
	 * @return updated address
	 * @throws DuplicateEntityFoundException id address exists
	 */
	Address update(Address address, String value, ZipCode zipCode)
					throws DuplicateEntityFoundException;
	
	/**
	 * Removes an address.
	 * 
	 * @param address address to remove
	 */
	void remove(Address address);
	
	/** find address by query.
	 * @param query - query string. 
	 * @return list of addresses. */
	List<Address> findByQuery(String query);
}