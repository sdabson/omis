package omis.caseload.report;

import java.util.Date;
import java.util.List;

import omis.caseload.domain.CaseWorkerAssignment;
import omis.caseload.domain.Caseload;
import omis.caseload.domain.OffenderCaseAssignment;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.user.domain.UserAccount;

/**
 * Caseload report service.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 29, 2016)
 * @since OMIS 3.0
 */
public interface CaseloadReportService {
	
	/**
	 * List summary of case work summary by a person based on an
	 * effective date.
	 *
	 *
	 * @param person person
	 * @param effectiveDate effective date
	 * @return case work summary
	 */
	List<CaseworkSummary> findByCaseWorkerAndDate(Person person, 
					Date effectiveDate);
	
	/**
	 * Summarizes case work summary by case worker assignment.
	 *
	 *
	 * @param caseWorkerAssignment case worker assignment
	 * @return summary of case work*/
	CaseworkSummary summarize(CaseWorkerAssignment caseWorkerAssignment);
	
	/** List summary of offender case assignments based on caseload.
	 * @param caseload caseload
	 * @param effectiveDate effective date
	 * @return list of offender case assignments */
	List<OffenderCaseAssignmentSummary> 
					findOffenderCaseAssignmentSummariesByCaseloadAndDate(
							Caseload caseload, Date effectiveDate);	
	
	/** Lists caseworker summaries by case.
	 * @param caseload - caseload.
	 * @param effectiveDate - effective date.
	 * @return caseworker summaries. */
	List<CaseworkSummary> findCaseWorkerSummariesByCase(
					Caseload caseload, Date effectiveDate);	
	
	
	/** Search for caseworker summaries by name or username.
	 * @param searchQuery - search query.
	 * @param effectiveDate - effective date.
	 * @return list of case work summaries. */
	List<CaseworkSummary> findCaseworkSummariesBySearchCriteria(
					String searchQuery, Date effectiveDate);
	
	/** Has caseload contact.
	 * @param offenderCaseAssignment - offender case assignment.
	 * @return true if caseload contact exists. */
	Boolean hasCaseloadContact(OffenderCaseAssignment offenderCaseAssignment);
	
	/** Finds user account by username.
	 * @param username - username.
	 * @return user account. */
	UserAccount findUserAccountByUsername(String username);
	
	/**
	 * Finds supervisory case worker assignment by person and date.
	 *
	 * @param caseWorker case worker
	 * @param effectiveDate effective
	 * @return case worker assignment*/
	CaseWorkerAssignment findSupervisoryCaseWorkerAssignmentBy(
					Person caseWorker, Date effectiveDate);
	
	/** Find supervisory case worker assignment by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date.
	 * @return supervisory case worker assignment. */  
	CaseworkSummary findSupervisoryCaseWorkerAssignmentByOffenderAndDate(
						Offender offender, Date effectiveDate);
	
	
	/** Finds case worker assignments by offender and date.
	 * @param offender - offender
	 * @param effectiveDate - effective date.
	 * @return case worker assignments. */
	List<CaseworkSummary> findCasewokerAssignmentsByOffenderAndDate(
				Offender offender, Date effectiveDate);
	
	
	
	/** List summary of case work summary by a person base on a 
	 * specific start and end dates.
	 * @param person person
	 * @param startDate start date
	 * @param endDate end date
	 * @param caseloadName - caseload name.
	 * @param caseworkCategory - casework category.
	 * @return case work summary
	 
	List<CaseworkSummary> findByCaseWorkerAndDates(Person person,
					Date startDate, Date endDate, String caseloadName, 
					CaseworkCategory caseworkCategory);*/
	
	/** Supervisory case work by a person based on 
	 * effective date.
	 * @param person person
	 * @param effectiveDate effective date
	 * @return supervisor case work summary 
	CaseworkSummary findSupervisoryCaseWorkByCaseWorkerAndDate(
					Person person, Date effectiveDate);*/
	
	/** List summary of case work by last name.
	 * @param lastName last name
	 * @param effectiveDate effective date
	 * @return list of case work summaries 
	List<CaseworkSummary> findCaseWorkSummaryByLastName(
					String lastName, Date effectiveDate);*/
	
	/** List summary of case work by last and first name.
	 * @param lastName last name
	 * @param firstName first name
	 * @param effectiveDate effective date
	 * @return list of case work summaries 
	List<CaseworkSummary>findCaseWorkSummaryByLastFirstName(
					String lastName, String firstName, Date effectiveDate);*/
	
	/** List summary of case work by user name.
	 * @param userName user name
	 * @param effectiveDate effective date
	 * @return list of case work summaries  
	List<CaseworkSummary> findCaseWorkerSummariesByUsername(
					String userName, Date effectiveDate);*/
	
	/** List summary of case work by caseload name.
	 * @param caseloadName caseload name
	 * @param effectiveDate effective date
	 * @return list of case work summaries
	List<CaseworkSummary> findCaseWorkerSummaryByCaseloadName(
					String caseloadName, Date effectiveDate); */
	
	/** List summary of offender case assignments by caseload and date range.
	 * @param caseload caseload
	 * @param startDate start date
	 * @param endDate end date
	 * @return list of offender case assignments 
	List<OffenderCaseAssignmentSummary> 
					findOffenderCaseAssignmentSummariesByCaseloadAndDateRange(
							Caseload caseload, Date startDate, Date endDate);*/
	
	/** List summary of caseload contacts by offender and caseload.
	 * @param offender offender
	 * @param caseload caseload
	 * @return list of caseload contacts 
	List<ContactSummary> findContactSummaryByOffender(
					Offender offender, Caseload caseload);*/
	
	/** Finds case worker assignments by first and last name.
	 * @param firstName - first name.
	 * @param lastName - last name.
	 * @param effectiveDate - effective date.
	 * @return list of case worker assignments. 
	List<CaseworkSummary> findCaseworkerSummariesByFirstLast(
					String firstName, String lastName, Date effectiveDate);*/
	
	/** Finds case worker assignment by first middle last.
	 * @param firstName - first name.
	 * @param middleName - middle name.
	 * @param lastName - last name.
	 * @param effectiveDate - effective date.
	 * @return list of casework assignments. 
	List<CaseworkSummary> findCaseworkerSummariesByFirstMiddleLast(
					String firstName, String middleName, String lastName, 
					Date effectiveDate);*/
	
	/** Finds case worker assignments by offender and date.
	 * @param offender - offender.
	 * @param effectiveDate - effective date.
	 * @return caseworker assignments. 
	List<CaseWorkerAssignment> findCaseWorkerAssignmentByOffenderAndDate(
					Offender offender, Date effectiveDate);*/
	
	/**
	 * Finds a list of case worker assignments by caseload.
	 *
	 * @param caseload caseload
	 * @param effectiveDate effective date
	 * @return list of case worker assignments
	 
	List<CaseWorkerAssignment> findCaseWorkerAssignmentsByCaseload(
					Caseload caseload, Date effectiveDate);*/
}