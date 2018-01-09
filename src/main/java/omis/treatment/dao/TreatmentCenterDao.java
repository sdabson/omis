package omis.treatment.dao;

import omis.dao.GenericDao;
import omis.location.domain.Location;
import omis.treatment.domain.TreatmentCenter;

/**
 * Data access object for treatment center.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 13, 2017)
 * @since OMIS 3.0
 */
public interface TreatmentCenterDao 
		extends GenericDao<TreatmentCenter> {

	/**
	 * Returns the treatment center matching the specified location, name and 
	 * telephone number.
	 * 
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @return treatment center
	 */
	TreatmentCenter find(Location location, String name, 
			Long telephoneNumber);
	
	/**
	 * Returns the treatment center matching the specified location, name and 
	 * telephone number, excluding the specified treatment center.
	 * 
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @param excludedTreatmentCenter excluded treatment center
	 * @return treatment center
	 */
	TreatmentCenter findExcluding(Location location, 
			String name, Long telephoneNumber, 
			TreatmentCenter 
				excludedTreatmentCenter);
}
