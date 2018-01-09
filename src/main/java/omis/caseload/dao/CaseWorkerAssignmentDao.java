package omis.caseload.dao;

import java.util.Date;
import java.util.List;

import omis.caseload.domain.CaseWorkerAssignment;
import omis.caseload.domain.Caseload;
import omis.dao.GenericDao;
import omis.person.domain.Person;

/**
 * Data access object for case worker assignment.
 *
 * @author Sheronda Vaughn
 * @author Ryan Johns
 * @version 0.1.1 (Jun 20, 2017)
 * @since OMIS 3.0
 */
public interface CaseWorkerAssignmentDao 
				extends GenericDao<CaseWorkerAssignment> {

	/**
	 * Returns the case worker assignment.
	 *
	 * @param worker worker
	 * @param caseload caseload
	 * @param startDate - start date.
	 * @param endDate - end date.
	 * @return case worker assignment
	 */
	CaseWorkerAssignment find(Person worker, Caseload caseload, 
					Date startDate, Date endDate);

	/**
	 * Returns the case worker assignment excluding the one in view.
	 *
	 * @param assignment assignment
	 * @param worker worker
	 * @param caseload caseload
	 * @param startDate start date.
	 * @param endDate end date.
	 * @return case worker assignment
	 */
	CaseWorkerAssignment findExcluding(CaseWorkerAssignment assignment, 
					Person worker, Caseload caseload, Date startDate, 
					Date endDate);
	
	/**
	 * Returns a list of case worker assignments within a given date range.
	 *
	 * @param startDate start date.
	 * @param endDate end date.
	 * @param caseload caseload
	 * @param user person assigned.
	 * @return list of case worker assignment
	 */
	List<CaseWorkerAssignment> 
					findCaseAssignmentsWithinDateRange(
							Date startDate, Date endDate, 
							Caseload caseload, Person user);

	/**
	 * Returns a list of case worker assignments within a given date range 
	 * excluding the one in view.
	 *
	 * @param caseWorkerAssignment case worker assignment
	 * @param startDate start date.
	 * @param endDate end date.
	 * @param caseload caseload
	 * @param worker worker
	 * @return list of case worker assignments
	 */
	List<CaseWorkerAssignment> 
					findCaseAssignmentsByCaseCatWithnDatesExclding(
					CaseWorkerAssignment caseWorkerAssignment,
					Date startDate, Date endDate, 
					Caseload caseload, Person worker);

	/**
	 * Returns a list of case worker assignments.
	 *
	 * @param caseload caseload
	 * @param effectiveDate effective date
	 * @return list of case worker assignments
	 */
	List<CaseWorkerAssignment> findAllWorkerAssignments(
					Caseload caseload, Date effectiveDate);

	/**
	 * Returns a case worker assignment.
	 *
	 * @param caseWorker case worker
	 * @param effectiveDate effective date
	 * @return case worker assignment
	 */
	CaseWorkerAssignment findSupervisoryCaseWorkerAssignmentByCaseWorkerAndDate(
					Person caseWorker, Date effectiveDate);	
}