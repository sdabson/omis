package omis.address.dao;

import java.util.List;

import omis.address.domain.Address;
import omis.address.domain.ZipCode;
import omis.dao.GenericDao;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Data access object for addresses.
 * 
 * @author Stephen Abson
 * @author Sheronda Vaughn
 * @author Ryan Johns
 * @version 0.1.1 (Jan 25, 2016)
 * @since OMIS 3.0
 */
public interface AddressDao
		extends GenericDao<Address> {

	/**
	 * Returns the address.
	 * 
	 * @param value value
	 * @param zipCode zip code
	 * @return address
	 */
	Address find(String value, ZipCode zipCode);

	/**
	 * Returns the address, excluding the address in view.
	 * 
	 * @param value value
	 * @param zipCode zipCode
	 * @param address address
	 * @return address
	 */
	Address findExcluding(String value, ZipCode zipCode,
			Address address);
	
	/** Finds addresses by address fields.
	 * @param streetNumber - street number.
	 * @param streetName - street name.
	 * @param streetSuffix - street suffix.
	 * @return list of address results. */
	@Deprecated
	List<Address> findByAddressFields(
			final String streetNumber, final String streetName, 
			final String streetSuffix);
	
	/** Finds addresses by address fields.
	 * @param streetNumber - street number.
	 * @param streetName - street name.
	 * @param streetSuffix - street suffix.
	 * @param cityName - city name.
	 * @param stateName - state name.
	 * @param zipCode - zip code. 
	 * @return list of address results. */
	@Deprecated
	List<Address> findByAddressFields(
			final String streetNumber, final String streetName, 
			final String streetSuffix, final String cityName, 
			final String stateName, final String zipCode);
	
	/**
	 * Returns addresses by specified value, city name, state name, and zip code.
	 * 
	 * @param value value
	 * @param city city
	 * @param state state
	 * @param zipCode zip code
	 * @return list of address results
	 */
	List<Address> findByAddressFields(String value, ZipCode zipCode, City city, State state);

	/**
	 * Returns addresses by rough match of value.
	 * 
	 * @param value value
	 * @return list of addresses
	 */
	@Deprecated
	List<Address> findAddressesByValue(String value);
	
	/**
	 * Returns addresses by rough match of value.
	 * 
	 * @param value value
	 * @return list of addresses
	 */
	List<Address> findAddressesByValue(String value, int maxResults);

}