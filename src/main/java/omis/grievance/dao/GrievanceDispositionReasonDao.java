package omis.grievance.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.grievance.domain.GrievanceDispositionReason;
import omis.grievance.domain.GrievanceDispositionStatus;

/**
 * Data access object for grievance disposition reasons.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 11, 2015)
 * @since OMIS 3.0
 */
public interface GrievanceDispositionReasonDao
		extends GenericDao<GrievanceDispositionReason> {

	/**
	 * Returns grievance disposition reason.
	 * 
	 * @param name name
	 * @return grievance disposition reason
	 */
	GrievanceDispositionReason find(String name);
	
	/**
	 * Returns grievance disposition reasons by status.
	 * 
	 * @param status status
	 * @return grievance disposition reasons by status
	 */
	List<GrievanceDispositionReason> findByStatus(
			GrievanceDispositionStatus status);
}