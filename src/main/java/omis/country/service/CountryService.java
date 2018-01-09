package omis.country.service;

import java.util.List;

import omis.country.domain.Country;

/**
 * Service for countries.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Sep 6, 2013)
 * @since OMIS 3.0
 */
public interface CountryService {

	/**
	 * Returns all countries.
	 * 
	 * @return all countries
	 */
	List<Country> findAll();
}