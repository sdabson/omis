package omis.religion.dao;

import omis.dao.GenericDao;
import omis.religion.domain.ReligiousAccommodation;
import omis.religion.domain.ReligiousAccommodationAuthorization;
import omis.religion.domain.ReligiousPreference;

/**
 * Data access object for religious accommodation authorizations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 23, 2014)
 * @since OMIS 3.0
 */
public interface ReligiousAccommodationAuthorizationDao
		extends GenericDao<ReligiousAccommodationAuthorization> {

	/**
	 * Returns the authorization for accommodation of the religious preference.
	 * 
	 * <p>If no authorization exists (the accommodation is not authorized),
	 * return {@code null}.
	 * 
	 * @param preference religious preference
	 * @param accommodation accommodation
	 * @return authorization for accommodation of preference; {@code null}
	 * if no authorized
	 */
	ReligiousAccommodationAuthorization find(ReligiousPreference preference,
			ReligiousAccommodation accommodation);
	
	/**
	 * Removes religious accommodation authorizations for religious preference.
	 * 
	 * @param preference preference
	 * @return number of records removed
	 */
	int removeByPreference(ReligiousPreference preference);
}