package omis.caseload.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.caseload.dao.OffenderCaseAssignmentDao;
import omis.caseload.domain.Caseload;
import omis.caseload.domain.CaseworkCategory;
import omis.caseload.domain.OffenderCaseAssignment;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;

/** Delegate for offender case assignment related operations.
 * @author Ryan Johns
 * @version 0.1.0 (May 17, 2017)
 * @since OMIS 3.0 */
public class OffenderCaseAssignmentDelegate {

	/* DAOs */
	private final OffenderCaseAssignmentDao offenderCaseAssignmentDao;
	
	/* Instance factory. */
	private final InstanceFactory<OffenderCaseAssignment> 
					offenderCaseAssignmentInstanceFactory;
	
	/* Helpers. */
	private final AuditComponentRetriever auditComponentRetriever;
	
	/** Constructor.
	 * @param offenderCaseAssignmentDao - offender case assignment dao. 
	 * @param offenderCaseAssignmentInstanceFactory - offender case assignment
	 * instance factory.
	 * @param auditComponentRetriever - audit component retriever. */
	public OffenderCaseAssignmentDelegate(
					final OffenderCaseAssignmentDao offenderCaseAssignmentDao,
					final InstanceFactory<OffenderCaseAssignment> 
						offenderCaseAssignmentInstanceFactory,
					final AuditComponentRetriever auditComponentRetriever) {
		this.offenderCaseAssignmentDao = offenderCaseAssignmentDao;
		this.offenderCaseAssignmentInstanceFactory 
			= offenderCaseAssignmentInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Returns a new offender case assignment.
	 *
	 * @param offender offender
	 * @param caseload caseload
	 * @param startDate - start date.
	 * @param endDate - end date.
	 * @return new offender case assignment
	 * @throws DuplicateEntityFoundException - when offender case 
	 * assignment exists.
	 * @throws DateConflictException - when offender is assigned on another 
	 * caseload during this daterange.
	 */
	public OffenderCaseAssignment create(
					final Offender offender, final Caseload caseload, 
					final Date startDate, final Date endDate) 
						throws DuplicateEntityFoundException,
						DateConflictException {
		final DateRange dateRange = new DateRange(startDate, endDate);
		if (this.offenderCaseAssignmentDao.find(
						offender, caseload) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate offender case assignment found.");
		} else {
			if (this.offenderCaseAssignmentDao.findWithinDateRange(
							dateRange, caseload, offender).size()  > 0) {
				throw new DateConflictException(
						"Offender case assignment found "
						+ "within this date range.");
			}
		}
		OffenderCaseAssignment assignment 
							= this.offenderCaseAssignmentInstanceFactory
							.createInstance();		
		assignment.setOffender(offender);
		assignment.setCaseload(caseload);
		assignment.setDateRange(dateRange);
		assignment.setCreationSignature(new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		assignment.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		return this.offenderCaseAssignmentDao.makePersistent(assignment);
	}
	
	/**
	 * Returns an updated offender case assignment.
	 * @param assignment assignment
	 * @param offender offender
	 * @param caseload caseload
	 * @param startDate - start date.
	 * @param endDate - end date.
	 * @return edited offender case assignment
	 * @throws DuplicateEntityFoundException - when offender case assignment 
	 * exists.
	 * @throws DateConflictException - when offender assignment conflicts with 
	 * another daterange.
	 */
	public OffenderCaseAssignment update(
					final OffenderCaseAssignment assignment,
					final Offender offender, final Caseload caseload, 
					final Date startDate, final Date endDate) 
		throws DuplicateEntityFoundException,
		DateConflictException {	
		final DateRange dateRange = new DateRange(startDate, endDate);
		if (this.offenderCaseAssignmentDao.findExcluding(
						offender, assignment) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate offender case assignment found.");
		} else {
			if (this.offenderCaseAssignmentDao.findWithinDateRangeExcluding(
							dateRange, offender, assignment)
							.size()  > 0) {
				throw new DateConflictException(
						"Offender case assignment found "
						+ "within this date range.");
			}
		}
		assignment.setOffender(offender);
		assignment.setDateRange(dateRange);
		assignment.setCaseload(caseload);
		assignment.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.offenderCaseAssignmentDao.makePersistent(assignment);
	}
	
	/**
	 * Removes an offender case assignment.
	 *
	 * @param offenderCaseAssignment offender case assignment
	 */
	public void remove(
					final OffenderCaseAssignment offenderCaseAssignment) {
		this.offenderCaseAssignmentDao.makeTransient(offenderCaseAssignment);
	}
	
	/**
	 * Returns a list of offender case assignments by caseload.
	 *
	 * @param caseload caseload
	 * @param effectiveDate effective date
	 * @return list of assignments
	 */
	public List<OffenderCaseAssignment> findOffenderCaseAssignmentsByCaseload(
					final Caseload caseload, final Date effectiveDate) {
		return this.offenderCaseAssignmentDao.findAllCaseAssignments(
				caseload, effectiveDate);
	}
	
	/**
	 * Returns a list of offender case assignments.
	 *
	 * @param caseload caseload
	 * @param startDate start date
	 * @param endDate end date
	 * @return list of offender case assignments
	 */
	public List<OffenderCaseAssignment> 
					findOffenderCaseAssignmentsByCaseloadAndDateRange(
							final Caseload caseload,
							final Date startDate, final Date endDate) {
		return this.offenderCaseAssignmentDao
				.findAllCaseAssignmentsByCaseloadAndDateRange(
						caseload, startDate, endDate);		
	}	
	
	/**
	 * Returns a new offender case assignment and updates existing 
	 * offender case assignments end date.
	 * @param caseload - caseload.
	 * @param reassignDate - reassign date.
	 * @param offender - offender.
	 * @return new offender case assignment
	 */
	public OffenderCaseAssignment reassign(final Caseload caseload, 
					final Date reassignDate,
					final Offender offender) {
		OffenderCaseAssignment newAssignment  
						= this.offenderCaseAssignmentInstanceFactory
						.createInstance();	
		if (CaseworkCategory.SUPERVISORY.equals(caseload.getCategory())) {
			//update current assignments end date
			final OffenderCaseAssignment offenderCaseAssignment 
							= this.offenderCaseAssignmentDao
								.findSupervisoryCaseAssignmentByOffenderAndDate(
										offender, reassignDate);
			if (offenderCaseAssignment.getDateRange().getEndDate() == null) {
				DateRange dateRange = new DateRange();
				dateRange.setEndDate(reassignDate);
				dateRange.setStartDate(
								offenderCaseAssignment.getDateRange()
								.getStartDate());
				offenderCaseAssignment.setDateRange(dateRange);
				offenderCaseAssignment.setUpdateSignature(new UpdateSignature(
								this.auditComponentRetriever
								.retrieveUserAccount(),
								this.auditComponentRetriever.retrieveDate()));	
				this.offenderCaseAssignmentDao.makePersistent(
								offenderCaseAssignment);
			}
		}
		
			//create new assignment	
		DateRange newDateRange = new DateRange();
		newDateRange.setStartDate(reassignDate);
		newDateRange.setEndDate(null);
		newAssignment.setOffender(offender);
		newAssignment.setCaseload(caseload);
		newAssignment.setDateRange(newDateRange);
		newAssignment.setCreationSignature(new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		newAssignment.setUpdateSignature(new UpdateSignature(
							this.auditComponentRetriever.retrieveUserAccount(),
							this.auditComponentRetriever.retrieveDate()));	
		
		return this.offenderCaseAssignmentDao.makePersistent(newAssignment);
	}
	
	/**
	 * Returns temporary transfered offender case assignment.
	 * @param from from
	 * @param to to
	 * @param startDate start date
	 * @param endDate end date
	 * @param offenderCaseAssignment offender case assignment
	 * @return temporary transfered offender
	 */
	public OffenderCaseAssignment temporaryTransfer(final Caseload from, 
					final Caseload to, final Date startDate, 
					final Date endDate, 
					final OffenderCaseAssignment offenderCaseAssignment) {
		OffenderCaseAssignment temporaryAssignment 
						= this.offenderCaseAssignmentInstanceFactory
						.createInstance();
		
		//if the current assignments end date is null
		if (offenderCaseAssignment.getDateRange().getEndDate() == null) {
			DateRange dateRange = new DateRange();
			//set that end date to the temp start date
			dateRange.setEndDate(startDate);
			//keep the current start date as is
			dateRange.setStartDate(
							offenderCaseAssignment.getDateRange()
							.getStartDate());
			//set current assignment to the date range above
			offenderCaseAssignment.setDateRange(dateRange);
			//set the current caseload as the from caseload
			offenderCaseAssignment.setCaseload(from);
			//update current caseload signature
			offenderCaseAssignment.setUpdateSignature(new UpdateSignature(
							this.auditComponentRetriever.retrieveUserAccount(),
							this.auditComponentRetriever.retrieveDate()));	
			this.offenderCaseAssignmentDao.makePersistent(
							offenderCaseAssignment);
		}
		DateRange dateRange = new DateRange();
		//set the temp start date to start date being passed in
		dateRange.setStartDate(startDate);
		//set the temp end date to the end date being passed in
		dateRange.setEndDate(endDate);
		temporaryAssignment.setOffender(offenderCaseAssignment.getOffender());
		//set the temp date range to date range above
		temporaryAssignment.setDateRange(dateRange);
		//set the temp caseload to the new caseload
		temporaryAssignment.setCaseload(to);
		temporaryAssignment.setCreationSignature(new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		temporaryAssignment.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));	
		
		return this.offenderCaseAssignmentDao.makePersistent(
				temporaryAssignment);
	}
}
