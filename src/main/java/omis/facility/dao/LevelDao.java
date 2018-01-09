/**
 * 
 */
package omis.facility.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Level;
import omis.facility.domain.Section;
import omis.facility.domain.Unit;

/**
 * Level data access object.
 * 
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.1 (Aug 2, 2017)
 * @since OMIS 3.0
 */
public interface LevelDao extends GenericDao<Level> {

	/**
	 * Finds all levels in a specified facility.
	 * 
	 * @param facility facility
	 * @return list of facility's levels
	 */
	List<Level> findByFacility(Facility facility);

	/**
	 * Returns a list of levels for the specified complex.
	 * 
	 * @param complex complex
	 * @return list of complex's levels
	 */
	List<Level> findByComplex(Complex complex);

	/**
	 * Returns a list of levels for the specified unit.
	 * 
	 * @param unit unit
	 * @return list of unit's levels
	 */
	List<Level> findByUnit(Unit unit);

	/**
	 * Returns a list of levels for the specified section.
	 * 
	 * @param section section
	 * @return list of section's levels
	 */
	List<Level> findBySection(Section section);
	
	/**
	 * Returns a list of levels that contain the specified properties.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param section section
	 * @return list of matching levels
	 */
	List<Level> findLevels(Facility facility, Complex complex, Unit unit, 
			Section section);
	
	/**
	 * Returns the level that matches the specified name and facility.
	 *  
	 * @param name name
	 * @param facility facility
	 * @return level
	 */
	Level find(String name, Facility facility);
	
	/**
	 * Returns the level that matches the specified name and facility excluding 
	 * the specified level.
	 *  
	 * @param name name
	 * @param facility facility
	 * @param excludedLevel excluded level
	 * @return level
	 */
	Level findExcluding(String name, Facility facility, Level excludedLevel);
}