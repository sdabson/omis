package omis.caseload.dao;

import java.util.Date;
import java.util.List;

import omis.caseload.domain.Caseload;
import omis.caseload.domain.OffenderCaseAssignment;
import omis.dao.GenericDao;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;

/**
 * Data access object for offender case assignment.
 *
 * @author Sheronda Vaughn
 * @author Ryan Johns
 * @version 0.1.1 (Jun 23, 2017)
 * @since OMIS 3.0
 */
public interface OffenderCaseAssignmentDao 
				extends GenericDao<OffenderCaseAssignment> {

	/**
	 * Returns an offender case assignment.
	 *
	 * @param offender offender
	 * @param caseload caseload
	 * @return offender case assignment
	 */
	OffenderCaseAssignment find(Offender offender, Caseload caseload);

	/**
	 * Returns an offender case assignment within a given date range.
	 *
	 * @param dateRange date range
	 * @param caseload caseload
	 * @param offender offender
	 * @return offender case assignment
	 */
	List<OffenderCaseAssignment> findWithinDateRange(DateRange dateRange, 
					Caseload caseload, Offender offender);

	/**
	 * Returns an offender case assignment excluding the one in view.
	 *
	 * @param offender offender
	 * @param assignment assignment
	 * @return offender case assignment
	 */
	OffenderCaseAssignment findExcluding(Offender offender,  
					OffenderCaseAssignment assignment);

	/**
	 * Returns a list of offender case worker assignments within a given 
	 * date range excluding the one in view.
	 *
	 * @param dateRange date range
	 * @param offender offender
	 * @param assignment assignment
	 * @return list of offender case assignments
	 */
	List<OffenderCaseAssignment> findWithinDateRangeExcluding(
					DateRange dateRange, Offender offender, 
					OffenderCaseAssignment assignment);

	/**
	 * Returns a list of offender case assignments.
	 *
	 * @param caseload caseload
	 * @param effectiveDate effective date
	 * @return list of offender case assignments
	 */
	List<OffenderCaseAssignment> findAllCaseAssignments(
					Caseload caseload, Date effectiveDate);	
	
	/**
	 * Returns a list of offender case assignments by caseload and date range.
	 *
	 * @param caseload - caseload
	 * @param startDate - start date
	 * @param endDate - end date
	 * @return offender case assignments.
	 */
	List<OffenderCaseAssignment> findAllCaseAssignmentsByCaseloadAndDateRange(
					Caseload caseload, Date startDate, Date endDate);
	
	/** Finds supervisory case assignment by offender and date. 
	 * @param offender - offender
	 * @param effectiveDate - effective date.
	 * @return  offender case assignment. */
	OffenderCaseAssignment findSupervisoryCaseAssignmentByOffenderAndDate(
					Offender offender, Date effectiveDate);
}