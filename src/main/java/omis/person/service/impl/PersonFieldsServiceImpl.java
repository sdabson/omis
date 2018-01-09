package omis.person.service.impl;

import java.util.List;

import omis.country.dao.CountryDao;
import omis.country.domain.Country;
import omis.person.service.PersonFieldsService;
import omis.region.dao.CityDao;
import omis.region.dao.StateDao;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Person fields service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 16, 2015)
 * @since OMIS 3.0
 */
@Deprecated
public class PersonFieldsServiceImpl implements PersonFieldsService {

	/* Data access objects. */
	
	private StateDao stateDao;
	
	private CityDao cityDao;
	
	private CountryDao countryDao;
	
	/**
	 * Instantiates an instance of person fields service with the specified
	 * data access objects.
	 * 
	 * @param stateDao state data access object
	 * @param cityDao city data access object
	 * @param countryDao country data access object
	 */
	public PersonFieldsServiceImpl(final StateDao stateDao,
			final CityDao cityDao, final CountryDao countryDao) {
		this.stateDao = stateDao;
		this.cityDao = cityDao;
		this.countryDao = countryDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<State> findStatesByCountry(final Country country) {
		return this.stateDao.findByCountry(country);
	}

	/** {@inheritDoc} */
	@Override
	public List<City> findCitiesByState(final State state) {
		return this.cityDao.findByState(state);
	}

	/** {@inheritDoc} */
	@Override
	public List<Country> findCountries() {
		return this.countryDao.findAll();
	}
}