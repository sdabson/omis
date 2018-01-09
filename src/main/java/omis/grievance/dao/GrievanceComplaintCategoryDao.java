package omis.grievance.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.grievance.domain.GrievanceComplaintCategory;
import omis.grievance.domain.GrievanceSubject;

/**
 * Data access object for grievance complaint categories.
 *
 * @author Stephen Abson
 * @author Yidong Li
 * @version 0.0.1 (May 19, 2015)
 * @since OMIS 3.0
 */
public interface GrievanceComplaintCategoryDao
		extends GenericDao<GrievanceComplaintCategory> {
	/**
	 * Find grievance complaint categories.
	 * @param subject grievance subject
	 * @return list of grievance complaint categories 
	 */
	List<GrievanceComplaintCategory> findBySubject(
		GrievanceSubject subject);

	/**
	 * Returns grievance complaint category.
	 * 
	 * @param name name
	 * @return grievance complaint category
	 */
	GrievanceComplaintCategory find(String name);
}