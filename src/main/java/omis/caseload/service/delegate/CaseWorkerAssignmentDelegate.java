package omis.caseload.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.caseload.dao.CaseWorkerAssignmentDao;
import omis.caseload.domain.CaseWorkerAssignment;
import omis.caseload.domain.Caseload;
import omis.caseload.domain.CaseworkCategory;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;

/** Delegate for caseworker assignment related operations.
 * @author Ryan Johns
 * @version 0.1.0 (May 17, 2017)
 * @since OMIS 3.0 */
public class CaseWorkerAssignmentDelegate {

	/* Daos */
	private final CaseWorkerAssignmentDao caseWorkerAssignmentDao;
	
	/* Instance factories. */
	private final InstanceFactory<CaseWorkerAssignment> 
					caseWorkerAssignmentInstanceFactory;
	
	/* Audit component retriever. */
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Constructor.
	 * @param caseWorkerAssignmentDao - caseworker assignment dao. 
	 * @param caseWorkerAssignmentInstanceFactory - caseworker assignment 
	 * instance factory.
	 * @param auditComponentRetriever - audit component retriever. */
	public CaseWorkerAssignmentDelegate(
					final CaseWorkerAssignmentDao caseWorkerAssignmentDao,
					final InstanceFactory<CaseWorkerAssignment> 
						caseWorkerAssignmentInstanceFactory,
					final AuditComponentRetriever auditComponentRetriever) {
		this.caseWorkerAssignmentDao = caseWorkerAssignmentDao;
		this.caseWorkerAssignmentInstanceFactory 
			= caseWorkerAssignmentInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/**
	 * Returns a new case worker assignment.
	 *
	 * @param caseload caseload
	 * @param worker worker
	 * @param startDate - start date.
	 * @param endDate - end date.
	 * @return new case worker assignment
	 * @throws DuplicateEntityFoundException - when caseworker assignment 
	 * exists.
	 * @throws DateConflictException - when supervisory caseworker assignment 
	 * exits for worker on date. 
	 * daterange exists.
	 */
	public CaseWorkerAssignment create(
					final Caseload caseload, final Person worker,
					final Date startDate, final Date endDate)
							throws DuplicateEntityFoundException,
							DateConflictException {
		if (this.caseWorkerAssignmentDao.find(
						worker, caseload, startDate, endDate) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate case worker assignment found");
		}
		if (CaseworkCategory.SUPERVISORY.equals(caseload.getCategory())) {
			if (this.caseWorkerAssignmentDao
							.findCaseAssignmentsWithinDateRange(
							startDate, endDate, caseload, worker).size()  > 0) {
				throw new DateConflictException(
					"Case worker assignment found within this date range.");
			}
		}
		
		
		CaseWorkerAssignment caseWorkerAssignment 
						= this.caseWorkerAssignmentInstanceFactory
							.createInstance();
		caseWorkerAssignment.setCaseload(caseload);
		caseWorkerAssignment.setWorker(worker);
		caseWorkerAssignment.setDateRange(new DateRange(startDate, endDate));
		caseWorkerAssignment.setCreationSignature(new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		caseWorkerAssignment.setUpdateSignature(new UpdateSignature(
							this.auditComponentRetriever.retrieveUserAccount(),
							this.auditComponentRetriever.retrieveDate()));
		
		return this.caseWorkerAssignmentDao.makePersistent(
				caseWorkerAssignment);
	}
	
	/**
	 * Returns an updated case worker assignment.
	 *
	 * @param caseWorkerAssignment case worker assignment
	 * @param caseload caseload
	 * @param worker worker
	 * @param startDate - start date.
	 * @param endDate - end date.
	 * @return edited case worker assignment
	 * @throws DuplicateEntityFoundException - when duplicate caseworker 
	 * assignment exists.
	 * @throws DateConflictException - when caseworker assignment with give 
	 * caseload and daterange overlaps.
	 */
	public CaseWorkerAssignment update(
					final CaseWorkerAssignment caseWorkerAssignment,
					final Caseload caseload, final Person worker, 
					final Date startDate, final Date endDate) 
					throws DuplicateEntityFoundException,
					DateConflictException {
		DateRange dateRange = new DateRange(startDate, endDate);
		if (this.caseWorkerAssignmentDao.findExcluding(
						caseWorkerAssignment, worker, caseload, 
						DateRange.getStartDate(dateRange), 
						DateRange.getEndDate(dateRange)) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate case worker assignment found.");
		} else {
			if (this.caseWorkerAssignmentDao
							.findCaseAssignmentsByCaseCatWithnDatesExclding(
							caseWorkerAssignment, 
							DateRange.getStartDate(dateRange), 
							DateRange.getEndDate(dateRange), caseload, 
							worker).size()  > 0) {
				throw new DateConflictException(
						"Case worker assignment found within this date range.");
			}
		}
		caseWorkerAssignment.setCaseload(caseload);
		caseWorkerAssignment.setWorker(worker);
		caseWorkerAssignment.setDateRange(dateRange);
		caseWorkerAssignment.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.caseWorkerAssignmentDao.makePersistent(
				caseWorkerAssignment);
	}
	
	/**
	 * Removes a case worker assignment.
	 *
	 * @param caseWorkerAssignment case worker assignment
	 */
	public void remove(
					final CaseWorkerAssignment caseWorkerAssignment) {
		this.caseWorkerAssignmentDao.makeTransient(caseWorkerAssignment);
	}
	
	/**
	 * Returns list of case worker assignments by caseload.
	 *
	 * @param caseload caseload
	 * @param effectiveDate effective date
	 * @return list of assignments
	 */
	public List<CaseWorkerAssignment> findCaseWorkerAssignmentsByCaseload(
					final Caseload caseload, final Date effectiveDate) {
		return this.caseWorkerAssignmentDao.findAllWorkerAssignments(
				caseload, effectiveDate);
	}
	
	/**
	 * Returns a case worker assignment.
	 * @param caseWorker case worker
	 * @param effectiveDate effective Date
	 * @return case worker assignment
	 */
	public CaseWorkerAssignment findSupervisoryCaseWorkerAssignment(
					final Person caseWorker, final Date effectiveDate) {
		return this.caseWorkerAssignmentDao
				.findSupervisoryCaseWorkerAssignmentByCaseWorkerAndDate(
						caseWorker, effectiveDate);
	}
	
	/**
	 * Returns a reassigned case worker assignment officer.
	 *
	 *
	 * @param caseWorkerAssignment case worker assignment
	 * @param reassignDate reassign date
	 * @param staffMember staff member
	 * @return reassigned officer
	 */
	public CaseWorkerAssignment reassignOfficer(
					final CaseWorkerAssignment caseWorkerAssignment, 
					final Date reassignDate, 
					final Person staffMember) {		
		
		if (caseWorkerAssignment.getDateRange().getEndDate() 
						== null) {				
			
			DateRange dateRange = new DateRange(
							caseWorkerAssignment.getDateRange().getStartDate(), 
							reassignDate);
			caseWorkerAssignment.setDateRange(dateRange);
			caseWorkerAssignment.getWorker();
			caseWorkerAssignment.setUpdateSignature(new UpdateSignature(
							this.auditComponentRetriever.retrieveUserAccount(),
							this.auditComponentRetriever.retrieveDate()));	
			this.caseWorkerAssignmentDao.makePersistent(caseWorkerAssignment);
		}
		
		CaseWorkerAssignment newAssignment 
						= this.caseWorkerAssignmentInstanceFactory
						.createInstance();
		DateRange newDateRange = new DateRange();
		newDateRange.setStartDate(reassignDate);
		if (newAssignment.getDateRange().getEndDate() != null) {
			newAssignment.getDateRange().setEndDate(newDateRange.getEndDate());
		}
		newAssignment.setCaseload(caseWorkerAssignment.getCaseload());
		newAssignment.setDateRange(newDateRange);
		newAssignment.setWorker(staffMember);

		newAssignment.setCreationSignature(new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		newAssignment.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));	
		return this.caseWorkerAssignmentDao.makePersistent(
				newAssignment);
	}
}
