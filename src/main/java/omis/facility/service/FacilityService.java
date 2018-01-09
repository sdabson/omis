package omis.facility.service;

import java.util.List;

import omis.facility.domain.Facility;
import omis.location.domain.Location;

/**
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 08 2013)
 * @since OMIS 3.0
 */
public interface FacilityService {

	/**
	 * Finds the facility with the specified id.
	 * 
	 * @param id id
	 * @return the facility
	 */
	Facility findById(Long id);
	
	/**
	 * Saves the facility with the specified id.
	 * 
	 * @param facility facility
	 * @return the facility
	 */
	Facility save(Facility facility);

	/**
	 * Returns a list of all facilities. If no facilities are found, an empty
	 * list is returned.
	 * 
	 * @return list of facilities.
	 */
	List<Facility> findAll();

	/**
	 * Removes the specified facility.
	 * 
	 * @param facility facility
	 */
	void remove(Facility facility);
	
	/**
	 * Finds the facility with the specified location.
	 * 
	 * @param location location
	 * @return facility
	 */
	Facility findFacilityByLocation(Location location);
}