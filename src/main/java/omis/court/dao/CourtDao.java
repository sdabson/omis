package omis.court.dao;

import omis.court.domain.Court;
import omis.court.domain.CourtCategory;
import omis.dao.GenericDao;
import omis.location.domain.Location;

/**
 * Data access object for courts.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (May 28, 2013)
 * @since OMIS 3.0
 */
public interface CourtDao
		extends GenericDao<Court> {

	/**
	 * Returns the court with the specified name, category and location.
	 * 
	 * @param name name
	 * @param category court category
	 * @param location location
	 * @return court
	 */
	Court find(String name, CourtCategory category, Location location);
	
	/**
	 * Returns the court with the specified name, category and location 
	 * excluding the specified court.
	 * 
	 * @param name name
	 * @param category court category
	 * @param location location
	 * @param excludedCourt court to exclude
	 * @return court
	 */
	Court findExcluding(String name, CourtCategory category, Location location, 
			Court excludedCourt);
}