package omis.workassignment.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.workassignment.dao.WorkAssignmentDao;
import omis.workassignment.domain.FenceRestriction;
import omis.workassignment.domain.WorkAssignment;
import omis.workassignment.domain.WorkAssignmentCategory;
import omis.workassignment.domain.WorkAssignmentChangeReason;

/**
 * Delegate for managing work assignment.
 *
 * @author Yidong Li
 * @version 0.0.2 (Aug 18, 2016)
 * @since OMIS 3.0
 */
public class WorkAssignmentDelegate {
	/* Resources. */
	private final InstanceFactory<WorkAssignment> workAssignmentInstanceFactory;
	private final AuditComponentRetriever auditComponentRetriever;
	private final WorkAssignmentDao workAssignmentDao;
	
	/* Constructors. */
	/**
	 * Instantiates delegate for managing work assignment.
	 * 
	 * @param workAssignmentInstanceFactory instance factory for work assignment
	 * @param auditComponentRetriever audit component retriever
	 */
	public WorkAssignmentDelegate(
		final InstanceFactory<WorkAssignment> workAssignmentInstanceFactory,
		final AuditComponentRetriever auditComponentRetriever,
		final WorkAssignmentDao workAssignmentDao) {
		this.workAssignmentInstanceFactory = workAssignmentInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
		this.workAssignmentDao = workAssignmentDao;
	}

	/* Methods. */
	/**
	 * Creates work assignment.
	 * 
	 * @param offender offender
	 * @param fenceRestriction fence restriction
	 * @param category work assignment category
	 * @param changeReason work assignment change reason
	 * @param asignedDate assigned date
	 * @param terminationDate termination date
	 * @param comments comments
	 * @return work assignment
	 */
	public WorkAssignment create(final Offender offender, final FenceRestriction 
		fenceRestriction, final WorkAssignmentCategory category, 
		final WorkAssignmentChangeReason changeReason,	final Date assignedDate, 
		final Date terminationDate, final String comments, final Boolean endExisting) 
		throws DuplicateEntityFoundException {
		if (this.workAssignmentDao.findExistingWorkAssignment(offender, category, 
			assignedDate)!=null) {
				throw new DuplicateEntityFoundException("Work Assignment Already"
					+ " Exist.");
		}
		
		if(endExisting==true){
			List<WorkAssignment> existingWorkAssignments 
				= this.workAssignmentDao.findExistingWorkAssignmentByDate(
					offender, assignedDate);
			for(WorkAssignment existingWorkAssignment : existingWorkAssignments){
				existingWorkAssignment.setTerminationDate(assignedDate);
				workAssignmentDao.makePersistent(existingWorkAssignment);
			}
		}

		WorkAssignment workAssignment = this.workAssignmentInstanceFactory.createInstance();
		workAssignment.setCreationSignature(new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		workAssignment.setUpdateSignature(new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		workAssignment.setAssignedDate(assignedDate);
		workAssignment.setFenceRestriction(fenceRestriction);
		workAssignment.setComments(comments);
		workAssignment.setOffender(offender);
		workAssignment.setTerminationDate(terminationDate);
		workAssignment.setCategory(category);
		workAssignment.setChangeReason(changeReason);
		return this.workAssignmentDao.makePersistent(workAssignment);
	}
	
	/**
	 * Updates work assignment.
	 * 
	 * @param workAssignment work assignment
	 * @param fenceRestriction fence restriction
	 * @param category work assignment category
	 * @param changeReason work assignment change reason
	 * @param assignedDate assigned date
	 * @param terminationDate termination date
	 * @param comments comments
	 * @return work assignment
	 */
	public WorkAssignment update(final WorkAssignment workAssignment, 
		final FenceRestriction fenceRestriction, final WorkAssignmentCategory 
		category, final WorkAssignmentChangeReason changeReason, final Date 
		assignedDate, final Date terminationDate, final String comments,
		final Boolean endExisting) 
		throws DuplicateEntityFoundException {
		if (this.workAssignmentDao.findExcludedExistingWorkAssignment(
			workAssignment, workAssignment.getOffender(), category, assignedDate)
				!=null) {
			throw new DuplicateEntityFoundException("Work Assignment Already"
				+ " Exist.");
		}
		
		if(endExisting==true){
			List<WorkAssignment> existingWorkAssignments 
				= this.workAssignmentDao.findExistingWorkAssignmentByDate(
					workAssignment.getOffender(), assignedDate);
			for(WorkAssignment existingWorkAssignment : existingWorkAssignments){
				existingWorkAssignment.setTerminationDate(assignedDate);
				workAssignmentDao.makePersistent(existingWorkAssignment);
			}
		}
		
		workAssignment.setAssignedDate(assignedDate);
		workAssignment.setFenceRestriction(fenceRestriction);
		workAssignment.setComments(comments);
		workAssignment.setTerminationDate(terminationDate);
		workAssignment.setCategory(category);
		workAssignment.setChangeReason(changeReason);
		workAssignment.setUpdateSignature(new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		return this.workAssignmentDao.makePersistent(workAssignment);
	}
	
	/**
	 * Removes work assignment.
	 * 
	 * @param workAssignment work assignment
	 */
	public void remove(final WorkAssignment workAssignment) {
		this.workAssignmentDao.makeTransient(workAssignment);
	}
	
	/**
	 * Returns a list of work assignments.
	 * @param offender offender
	 * @return a list of work assignments.
	 */
	public List<WorkAssignment> findByOffender(final Offender offender) {
		return this.workAssignmentDao.findByOffender(offender);
	}
}