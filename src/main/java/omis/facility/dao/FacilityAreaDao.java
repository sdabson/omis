package omis.facility.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.facility.domain.Complex;
import omis.facility.domain.Facility;
import omis.facility.domain.FacilityArea;

/**
 * Facility area data access object.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sept 14, 2016)
 * @since OMIS 3.0
 */
public interface FacilityAreaDao extends GenericDao<FacilityArea> {
	
	/**
	 * Returns facility areas for the specified facility.
	 * 
	 * @param facility facility
	 * @return list of facility areas
	 */
	List<FacilityArea> findFacilityAreasByFacility(Facility facility);
	
	/**
	 * Returns facility areas for the specified complex.
	 * 
	 * @param complex complex
	 * @return list of facility areas
	 */
	List<FacilityArea> findFacilityAreasByComplex(Complex complex);
}