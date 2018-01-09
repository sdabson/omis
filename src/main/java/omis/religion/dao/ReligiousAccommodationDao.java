package omis.religion.dao;

import omis.dao.GenericDao;
import omis.religion.domain.ReligiousAccommodation;

/**
 * Data access object for religious accommodations.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jul 26, 2017)
 * @since OMIS 3.0
 */
public interface ReligiousAccommodationDao
		extends GenericDao<ReligiousAccommodation> {

	/**
	 * Returns the religious accommodation with the specified name.
	 * 
	 * @param name name
	 * @return religious accommodation
	 */
	ReligiousAccommodation find(String name);
	
	/**
	 * Returns the religious accommodation with the specified name excluding the 
	 * specified religious accommodation.
	 * 
	 * @param name name
	 * @param excludedAccommodation excluded religious accommodation
	 * @return religious accommodation
	 */
	ReligiousAccommodation findExcluding(String name, 
			ReligiousAccommodation excludedAccommodation);
}