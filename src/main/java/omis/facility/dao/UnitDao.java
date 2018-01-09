/**
 * 
 */
package omis.facility.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.Unit;

/**
 * Unit data access object.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 08 2013)
 * @since OMIS 3.0
 */
public interface UnitDao extends GenericDao<Unit> {

	/**
	 * Returns a list of all units for the specified facility.
	 * 
	 * @param facility facility
	 * @return list of units
	 */
	List<Unit> findByFacility(Facility facility);

	/**
	 * Returns a list of all units for the specified complex.
	 * 
	 * @param complex complex
	 * @return list of units
	 */
	List<Unit> findByComplex(Complex complex);

	/**
	 * Returns the unit with the specified facility and complex.
	 * 
	 * @param facility facility
	 * @param complex complex
	 * @return list of units
	 */
	List<Unit> findUnits(Facility facility, Complex complex);

	/**
	 * Returns the unit with the specified facility and name.
	 * 
	 * @param name name
	 * @param facility facility
	 * @return matching unit
	 */
	Unit find(String name, Facility facility);
	
	/**
	 * Returns the unit with the specified name and facility, excluding the
	 * specified unit.
	 * 
	 * @param name name
	 * @param facility facility
	 * @param unit unit to exclude
	 * @return matching unit
	 */
	Unit findExcluding(String name, Facility facility, Unit unit);
}
