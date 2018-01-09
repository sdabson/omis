package omis.grievance.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceDisposition;
import omis.grievance.domain.GrievanceDispositionLevel;

/**
 * Data access object for grievance dispositions.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 8, 2015)
 * @since OMIS 3.0
 */
public interface GrievanceDispositionDao
		extends GenericDao<GrievanceDisposition> {

	/**
	 * Returns dispositions by grievance.
	 * 
	 * @param grievance grievance
	 * @return dispositions by grievance
	 */
	List<GrievanceDisposition> findByGrievance(Grievance grievance);
	
	/**
	 * Finds grievance disposition.
	 * 
	 * @param grievance grievance
	 * @param level level
	 * @return grievance disposition
	 */
	GrievanceDisposition find(Grievance grievance,
			GrievanceDispositionLevel level);
	
	/**
	 * Finds grievance disposition with specified dispositions excluded.
	 * 
	 * @param grievance grievance
	 * @param level level
	 * @param excludedDispositions dispositions to exclude
	 * @return grievance disposition
	 */
	GrievanceDisposition findExcluding(Grievance grievance,
			GrievanceDispositionLevel level, GrievanceDisposition...
			excludedDispositions);
}