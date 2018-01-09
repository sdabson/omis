package omis.grievance.dao;

import omis.dao.GenericDao;
import omis.grievance.domain.GrievanceUnit;

/**
 * Data access object for grievance units.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 11, 2015)
 * @since OMIS 3.0
 */
public interface GrievanceUnitDao
		extends GenericDao<GrievanceUnit> {

	/**
	 * Returns grievance unit with name.
	 * 
	 * <p>Returns {@code null} if not found.
	 * 
	 * @param name name
	 * @return grievance unit with name
	 */
	GrievanceUnit find(String name);
}