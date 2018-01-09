package omis.country.service.impl;

import java.util.List;

import omis.country.dao.CountryDao;
import omis.country.domain.Country;
import omis.country.service.CountryService;

/**
 * Implementation of service for countries.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 6, 2013)
 * @since OMIS 3.0
 */
public class CountryServiceImpl
		implements CountryService {
	
	private final CountryDao countryDao;

	/**
	 * Instantiates an implementation of service for countries with the
	 * specified resources.
	 * 
	 * @param countryDao data access object for countries
	 */
	public CountryServiceImpl(final CountryDao countryDao) {
		this.countryDao = countryDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Country> findAll() {
		return this.countryDao.findAll();
	}
}