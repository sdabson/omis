package omis.country.service.delegate;

import java.util.List;

import omis.country.dao.CountryDao;
import omis.country.domain.Country;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Delegate for countries.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Sep 14, 2015)
 * @since OMIS 3.0
 */
public class CountryDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<Country> countryInstanceFactory;
	
	/* DAOs. */
	
	private final CountryDao countryDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for countries.
	 * 
	 * @param countryInstanceFactory instance factory for countries
	 * @param countryDao data access object for countries
	 */
	public CountryDelegate(
			final InstanceFactory<Country> countryInstanceFactory,
			final CountryDao countryDao) {
		this.countryInstanceFactory = countryInstanceFactory;
		this.countryDao = countryDao;
	}
	
	/* Methods. */
	
	/**
	 * Returns countries.
	 * 
	 * @return countries
	 */
	public List<Country> findAll() {
		return this.countryDao.findAll();
	}
	
	/**
	 * Creates country.
	 * 
	 * @param name name
	 * @param abbreviation abbreviation
	 * @param valid whether valid
	 * @return created country
	 * @throws DuplicateEntityFoundException if country exists
	 */
	public Country create(
			final String name, final String abbreviation, final Boolean valid)
				throws DuplicateEntityFoundException {
		if (this.countryDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Country exists");
		}
		return this.createImpl(name, abbreviation, valid);
	}
	
	/**
	 * Returns country.
	 * 
	 * <p>If country does not exist, creates and returns new country.
	 * 
	 * @param name name
	 * @param abbreviation abbreviation
	 * @param valid whether valid
	 * @return country
	 */
	public Country findOrCreate(
			final String name,
			final String abbreviation,
			final Boolean valid) {
		Country country = this.countryDao.find(name);
		if (country != null) {
			return country;
		} else {
			return this.createImpl(name, abbreviation, valid);
		}
	}
	
	// Creates country
	private Country createImpl(
			final String name,
			final String abbreviation,
			final Boolean valid) {
		Country country = this.countryInstanceFactory.createInstance();
		country.setName(name);
		country.setAbbreviation(abbreviation);
		country.setValid(valid);
		return this.countryDao.makePersistent(country);
	}
}