package omis.communitysupervision.dao;

import omis.communitysupervision.domain.CommunitySupervisionOffice;
import omis.dao.GenericDao;
import omis.location.domain.Location;

/**
 * Data access object for community supervision office.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 13, 2017)
 * @since OMIS 3.0
 */
public interface CommunitySupervisionOfficeDao 
		extends GenericDao<CommunitySupervisionOffice> {

	/**
	 * Returns the community supervision office matching the specified location,
	 * name and telephone number.
	 * 
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @return community supervision office
	 */
	CommunitySupervisionOffice find(Location location, String name, 
			Long telephoneNumber);
	
	/**
	 * Returns the community supervision office matching the specified location,
	 * name and telephone number, excluding the specified community supervision
	 * office.
	 * 
	 * @param location location
	 * @param name name
	 * @param telephoneNumber telephone number
	 * @param excludedCommunitySupervisionOffice excluded community supervision 
	 * office
	 * @return community supervision office
	 */
	CommunitySupervisionOffice findExcluding(Location location, 
			String name, Long telephoneNumber, 
			CommunitySupervisionOffice 
				excludedCommunitySupervisionOffice);
}
