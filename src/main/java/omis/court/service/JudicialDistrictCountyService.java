package omis.court.service;

import java.util.List;

import omis.court.domain.JudicialDistrict;
import omis.court.domain.JudicialDistrictCounty;

/**
 * Service for judicial district counties.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 28, 2013)
 * @since OMIS 3.0
 */
public interface JudicialDistrictCountyService {

	/**
	 * Returns the district counties for the specified district.
	 * 
	 * @param district district the district counties of which to return
	 * @return district counties of district
	 */
	List<JudicialDistrictCounty> findByDistrict(JudicialDistrict district);
}