package omis.grievance.dao;

import omis.dao.GenericDao;
import omis.grievance.domain.GrievanceLocation;

/**
 * Data access object for grievance locations.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 11, 2015)
 * @since OMIS 3.0
 */
public interface GrievanceLocationDao
		extends GenericDao<GrievanceLocation> {

	/**
	 * Returns grievance location.
	 * 
	 * <p>Returns {@code null} if location is not found.
	 * 
	 * @param name name
	 * @return grievance location
	 */
	GrievanceLocation find(String name);
}