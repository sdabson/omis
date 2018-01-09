package omis.facility.service;

import java.util.List;

import omis.facility.domain.Facility;
import omis.facility.domain.Level;

/**
 * Level service.
 * 
 * @author Joel Norris
 * @version 0.1.1 (Feb 25, 2015)
 * @since OMIS 3.0
 */
public interface LevelService {

	/**
	 * Saves the specified level.
	 * 
	 * @param level level
	 * @return the level
	 */
	Level save(Level level);
	
	/**
	 * Finds all levels in the specified facility.
	 * 
	 * @param facility facility
	 * @return list of levels
	 */
	List<Level> findByFacility(Facility facility);

	/**
	 * Removes the specified level.
	 * 
	 * @param level level
	 */
	void remove(Level level);
}