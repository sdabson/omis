package omis.facility.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;

/**
 * Complex data access object.
 * 
 * @author Joel Norris 
 * @version 0.1.1 (June 6, 2017)
 * @since OMIS 3.0
 */
public interface ComplexDao extends GenericDao<Complex> {
	
	/**
	 * Finds all complexes by the specified facility.
	 * 
	 * @param facility facility
	 * @return list of complexes
	 */
	List<Complex> findByFacility(Facility facility);

	/**
	 * Returns the complex with the matching name and facility.
	 * 
	 * @param name name
	 * @param facility facility
	 * @return matching complex
	 */
	Complex find(String name, Facility facility);
	
	/**
	 * Returns the complex with the matching name and facility, excluding the
	 * specified complex.
	 * 
	 * @param name name
	 * @param facility facility
	 * @param complex complex to exclude
	 * @return matching complex
	 */
	Complex findExcluding(String name, Facility facility, Complex complex);
}
