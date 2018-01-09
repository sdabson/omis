package omis.country.dao;

import java.util.List;

import omis.country.domain.Country;
import omis.dao.GenericDao;

/**
 * Data access object for countries.
 * @author Stephen Abson
 * @version 0.1.0 (Feb 6, 2013)
 * @since OMIS 3.0
 */
public interface CountryDao
		extends GenericDao<Country> {
	/**
	 * Returns all countries.
	 * 
	 * @return all countries
	 */
	List<Country> findAll();
	
	/**
	 * Returns country.
	 * 
	 * @param name name
	 * @return country
	 */
	Country find(String name);
}