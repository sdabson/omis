package omis.person.service;

import java.util.List;

import omis.country.domain.Country;
import omis.region.domain.City;
import omis.region.domain.State;

/**
 * Person fields service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 16, 2015)
 * @since OMIS 3.0
 */
@Deprecated
public interface PersonFieldsService {

	/**
	 * Returns a list of states that correspond to the specified country.
	 * 
	 * @param country country
	 * @return list of states
	 */
	List<State> findStatesByCountry(Country country);

	/**
	 * Returns a list of cities that correspond to the specified state.
	 * 
	 * @param state state
	 * @return list of cities
	 */
	List<City> findCitiesByState(State state);
	
	/**
	 * Returns a list of countries.
	 * 
	 * @return list of countries
	 */
	List<Country> findCountries();
}