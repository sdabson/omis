package omis.grievance.dao;

import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.grievance.domain.Grievance;
import omis.grievance.domain.GrievanceLocation;
import omis.grievance.domain.GrievanceSubject;
import omis.grievance.domain.GrievanceUnit;
import omis.offender.domain.Offender;

/**
 * Data access object for grievances.
 *
 * @author Stephen Abson
 * @version 0.0.2 (Oct 2, 2015)
 * @since OMIS 3.0
 */
public interface GrievanceDao
		extends GenericDao<Grievance> {

	/**
	 * Finds grievances by offender.
	 * 
	 * @param offender offender
	 * @return grievances by offender
	 */
	List<Grievance> findByOffender(Offender offender);
	
	/**
	 * Finds grievance.
	 * 
	 * @param offender offender
	 * @param location location
	 * @param unit unit
	 * @param openedDate openedDate
	 * @param subject subject
	 * @param grievanceNumber grievance number
	 * @return grievance
	 */
	Grievance find(Offender offender, GrievanceLocation location,
			GrievanceUnit unit, Date openedDate,
			GrievanceSubject subject, Integer grievanceNumber);
	
	/**
	 * Finds grievance with specified grievances excluded.
	 * 
	 * @param offender offender
	 * @param location location
	 * @param unit unit
	 * @param openedDate opened date
	 * @param subject subject
	 * @param grievanceNumber grievance number
	 * @param excludedGrievances grievances to exclude
	 * @return grievances
	 */
	Grievance findExcluding(Offender offender, GrievanceLocation location,
			GrievanceUnit unit, Date openedDate, GrievanceSubject subject,
			Integer grievanceNumber, Grievance... excludedGrievances);
	
	/**
	 * Returns highest grievance number.
	 * 
	 * <p>Returns zero if no grievances exist.
	 * 
	 * @return highest grievance number; zero if no grievances exist
	 */
	int findMaxGrievanceNumber();
}