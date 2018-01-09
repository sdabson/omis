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
 * Section data access object.
 * 
 * @author Joel Norris 
 * @author Josh Divine
 * @version 0.1.2 (Aug 3, 2017)
 * @since OMIS 3.0
 */
public interface SectionDao extends GenericDao<Section> {

	/**
	 * Returns a list of all sections for a specified facility.
	 * 
	 * @param facility facility
	 * @return list of facility's sections
	 */
	List<Section> findByFacility(Facility facility);

	/**
	 * Returns a list of sections for the specified complex.
	 * 
	 * @param complex complex
	 * @return list of complex's sections
	 */
	List<Section> findByComplex(Complex complex);

	/**
	 * Returns a list of sections for the specified unit.
	 * 
	 * @param unit unit
	 * @return list of unit's sections
	 */
	List<Section> findByUnit(Unit unit);

	/**
	 * Returns a list of sections for the specified level.
	 * 
	 * @param level level
	 * @return list of level's sections
	 */
	List<Section> findByLevel(Level level);

	/**
	 * Returns a list of sections with the specified properties.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @param unit unit
	 * @param level level
	 * @return list of sections
	 */
	List<Section> findSections(Facility facility, Complex complex, Unit unit,
			Level level);
	
	/**
	 * Returns the section that matches the specified name and facility.
	 *  
	 * @param name name
	 * @param facility facility
	 * @return level
	 */
	Section find(String name, Facility facility);
	
	/**
	 * Returns the section that matches the specified name and facility 
	 * excluding the specified section.
	 *  
	 * @param name name
	 * @param facility facility
	 * @param excludedSection excluded section
	 * @return section
	 */
	Section findExcluding(String name, Facility facility, 
			Section excludedSection);
}