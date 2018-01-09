package omis.address.service.impl;

import java.util.List;

import omis.address.dao.AddressUnitDesignatorDao;
import omis.address.dao.ZipCodeDao;
import omis.address.domain.Address;
import omis.address.domain.AddressUnitDesignator;
import omis.address.domain.StreetSuffix;
import omis.address.domain.ZipCode;
import omis.address.service.AddressService;
import omis.address.service.delegate.AddressDelegate;
import omis.address.service.delegate.StreetSuffixDelegate;
import omis.country.dao.CountryDao;
import omis.country.domain.Country;
import omis.exception.DuplicateEntityFoundException;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;

/**
 * Implementation of service for addresses.
 * 
 * @author Stephen Abson
 * @author Ryan Johns
 * @version 0.1.1 (Jan 25, 2016)
 * @since OMIS 3.0
 */
public class AddressServiceImpl
		implements AddressService {
	
	private final AddressDelegate addressDelegate;
	
	private final StreetSuffixDelegate streetSuffixDelegate;
	
	private final AddressUnitDesignatorDao addressUnitDesignatorDao;
	
	private final CountryDao countryDao;
	
	private final StateDelegate stateDelegate;
	
	private final CityDelegate cityDelegate;
	
	private final ZipCodeDao zipCodeDao;
	
	/**
	 * Instantiates an implementation of service for address.
	 * 
	 * @param addressDelegate delegate for addresses
	 * @param streetSuffixDao data access object for street suffixes
	 * @param addressUnitDesignatorDao = data access object for address unit
	 * designators
	 * @param countryDao data access object for countries
	 * @param stateDelegate delegate for States
	 * @param cityDelegate delegate for cities
	 * @param zipCodeDao data access object for ZIP codes
	 */
	public AddressServiceImpl(
			final AddressDelegate addressDelegate,
			final StreetSuffixDelegate streetSuffixDelegate,
			final AddressUnitDesignatorDao addressUnitDesignatorDao,
			final CountryDao countryDao,
			final StateDelegate stateDelegate,
			final CityDelegate cityDelegate,
			final ZipCodeDao zipCodeDao) {
		this.addressDelegate = addressDelegate;
		this.streetSuffixDelegate = streetSuffixDelegate;
		this.addressUnitDesignatorDao = addressUnitDesignatorDao;
		this.countryDao = countryDao;
		this.stateDelegate = stateDelegate;
		this.cityDelegate = cityDelegate;
		this.zipCodeDao = zipCodeDao;
	}

	/** {@inheritDoc} */
	@Override
	public Address create(final String value, final ZipCode zipCode)
					throws DuplicateEntityFoundException {
		return this.addressDelegate.findOrCreate(
				value, null, null, null, zipCode);
	}

	/** {@inheritDoc} */
	@Override
	public Address update(
			final Address address, final String value,
			final ZipCode zipCode)
					throws DuplicateEntityFoundException {
		return this.addressDelegate.update(
				address, value, null, null, null,  zipCode);
	}
	
	/** {@inheritDoc} */
	@Override
	public void remove(final Address address) {
		this.addressDelegate.remove(address);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Address> findAll() {
		return this.addressDelegate.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Address> findByQuery(final String query) {
		return this.addressDelegate.findAddressesByValue(query);
	}

	/** {@inheritDoc} */
	@Override
	public List<StreetSuffix> findStreetSuffixes() {
		return this.streetSuffixDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<AddressUnitDesignator> findUnitDesignators() {
		return this.addressUnitDesignatorDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<Country> findCountries() {
		return this.countryDao.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ZipCode> findZipCodesByCity(final City city) {
		return this.zipCodeDao.findInCity(city);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ZipCode> findZipCodesByState(final State state) {
		return this.zipCodeDao.findInStates(state);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<State> findStatesByCountry(final Country country) {
		return this.stateDelegate.findByCountry(country);
	}

	/** {@inheritDoc} */
	@Override
	public boolean hasStates(final Country country) {
		return this.stateDelegate.countByCountry(country) > 0;
	}

	/** {@inheritDoc} */
	@Override
	public List<City> findCitiesByCountry(final Country country) {
		return this.cityDelegate.findByCountry(country);
	}

	/** {@inheritDoc} */
	@Override
	public List<City> findCitiesByState(final State state) {
		return this.cityDelegate.findByState(state);
	}
}