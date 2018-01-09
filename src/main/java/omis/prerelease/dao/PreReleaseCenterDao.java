package omis.prerelease.dao;

import omis.dao.GenericDao;
import omis.location.domain.Location;
import omis.prerelease.domain.PreReleaseCenter;

/**
 * Data access object for prerelease center.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 13, 2017)
 * @since OMIS 3.0
 */
public interface PreReleaseCenterDao 
		extends GenericDao<PreReleaseCenter> {

	/**
	 * Returns the prerelease center matching the specified location, name and 
	 * telephone number.
	 * 
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @return prerelease center
	 */
	PreReleaseCenter find(Location location, String name, 
			Long telephoneNumber);
	
	/**
	 * Returns the prerelease center matching the specified location, name and 
	 * telephone number, excluding the specified prerelease center.
	 * 
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @param excludedPreReleaseCenter excluded prerelease center
	 * @return prerelease center
	 */
	PreReleaseCenter findExcluding(Location location, 
			String name, Long telephoneNumber, 
			PreReleaseCenter 
				excludedPreReleaseCenter);
}
