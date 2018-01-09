package omis.region.dao;

import java.util.List;

import omis.country.domain.Country;
import omis.dao.GenericDao;
import omis.region.domain.State;

/**
 * Data access object for states.
 * 
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.1.0 (Feb 6, 2013)
 * @since OMIS 3.0
 */
public interface StateDao
		extends GenericDao<State> {

	/**
	 * Returns all States in the specified country.
	 * @param country country the States of which to return
	 * @return States in specified country
	 */
	List<State> findByCountry(Country country);

	/**
	 * Returns the home state.
	 * 
	 * @return state
	 */
	State findHomeState();
	
	/**
	 * Returns States in home country.
	 * 
	 * @return States in home country
	 */
	List<State> findInHomeCountry();
	
	/**
	 * Counts States by country.
	 * 
	 * @param country country
	 * @return number of States in country
	 */
	long countByCountry(Country country);
	
	/**
	 * Returns State.
	 * 
	 * @param name name
	 * @param country country
	 * @return return State
	 */
	State find(String name, Country country);
}