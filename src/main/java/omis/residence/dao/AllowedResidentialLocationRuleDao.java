/**
 * 
 */
package omis.residence.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.location.domain.Location;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.residence.domain.AllowedResidentialLocationRule;
import omis.residence.domain.ResidenceStatus;

/**
 * Data access object allowed residential location rule.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb 19, 2015)
 * @since  OMIS 3.0
 */
public interface AllowedResidentialLocationRuleDao extends
		GenericDao<AllowedResidentialLocationRule> {

	/**
	 * Returns a list of all allowed locations.
	 * 
	 * @param residenceStatus residence status
	 * @return list of allowed locations
	 */
	List<Location> findAllowedLocations(ResidenceStatus residenceStatus);

	/**
	 * Returns a list of all allowed locations within specified state.
	 * 
	 * @param state state
	 * @param residenceStatus residence status
	 * @return list of allowed locations
	 */
	List<Location> findAllowedLocationsInState(State state, 
			ResidenceStatus residenceStatus);

	/**
	 * Returns a list of all allowed locations within specified city.
	 * 
	 * @param city city
	 * @param residenceStatus residence status
	 * @return list of allowed locations
	 */
	List<Location> findAllowedLocationsInCity(City city, 
			ResidenceStatus residenceStatus);

	/**
	 * Returns allowed residential location rule.
	 * 
	 * @param location location
	 * @param residenceStatus residence status
	 * @return allowed residential location rule
	 */
	AllowedResidentialLocationRule find(Location location,
			ResidenceStatus residenceStatus);

	/**
	 * Returns list of locations within status.
	 * 
	 * @param location location
	 * @param residenceStatus residence status
	 * @return locations
	 */
	List<Location> findLocations(Location location, 
			ResidenceStatus residenceStatus);
	
	/**
	 * Returns allowed residential location rule.
	 * 
	 * @param location location
	 * @param residenceStatus residence status
	 * @param excludedRule excluded rule
	 * @return allowed residential location rule
	 */
	AllowedResidentialLocationRule findExcluding(Location location,
			ResidenceStatus residenceStatus, 
			AllowedResidentialLocationRule excludedRule);
}
