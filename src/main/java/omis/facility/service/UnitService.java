package omis.facility.service;

import java.util.List;

import omis.facility.domain.Facility;
import omis.facility.domain.Unit;

/**
 * Unit service.
 * 
 * @author Joel Norris
 * @version 0.1.1 (Feb 25, 2015)
 * @since OMIS 3.0
 */
public interface UnitService {
	
	/**
	 * Saves the specified unit.
	 * 
	 * @param unit unit
	 * @return the unit
	 */
	Unit save(Unit unit);
	
	/**
	 * Returns a list of all units for a specified facility.
	 * 
	 * @param facility facility 
	 * @return list of units
	 */
	List<Unit> findByFacility(Facility facility);

	/**
	 * Removes the specified unit.
	 * 
	 * @param unit unit
	 */
	void remove(Unit unit);

}
