package omis.grievance.dao;

import omis.dao.GenericDao;
import omis.grievance.domain.GrievanceSubject;

/**
 * Data access object for grievance subjects.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 11, 2015)
 * @since OMIS 3.0
 */
public interface GrievanceSubjectDao
		extends GenericDao<GrievanceSubject> {

	/**
	 * Returns grievance subject.
	 * 
	 * <p>Returns {@code null} if subject is not found.
	 * 
	 * @param name name
	 * @return grievance subject
	 */
	GrievanceSubject find(String name);
}