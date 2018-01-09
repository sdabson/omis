package omis.workassignment.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.workassignment.dao.WorkAssignmentChangeReasonDao;
import omis.workassignment.domain.WorkAssignmentChangeReason;

/**
 * Delegate for work assignment change reason.
 *
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.0.3 (Jul 18, 2017)
 * @since OMIS 3.0
 */
public class WorkAssignmentChangeReasonDelegate {
	/* Resources. */
	private final WorkAssignmentChangeReasonDao workAssignmentChangeReasonDao;
	private final InstanceFactory<WorkAssignmentChangeReason> 
	workAssignmentChangeReasonInstanceFactory;
	
	/* Constructors. */
	/**
	 * Instantiates delegate for work assignment change reason.
	 * 
	 * @param workAssignmentChangeReasonDao work assignment change reason dao
	 */
	public WorkAssignmentChangeReasonDelegate(
		final WorkAssignmentChangeReasonDao workAssignmentChangeReasonDao,
		final InstanceFactory<WorkAssignmentChangeReason> 
			workAssignmentChangeReasonInstanceFactory) {
		this.workAssignmentChangeReasonDao = workAssignmentChangeReasonDao;
		this.workAssignmentChangeReasonInstanceFactory = 
				workAssignmentChangeReasonInstanceFactory;
	}

	/* Methods. */
	/**
	 * Returns work assignment categories.
	 * 
	 * @return work assignment categories
	 */
	public List<WorkAssignmentChangeReason> find() {
		return this.workAssignmentChangeReasonDao.findAll();
	}
	
	/**
	 * Creates a work assignment change reason.
	 * @param name name
	 * @param valid valid
	 * @return work assignment change reason
	 * @throws DuplicateEntityFoundException if duplicate entity found
	 */
	public WorkAssignmentChangeReason create(final String name, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.workAssignmentChangeReasonDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Work assignment "
					+ "change reason already exists");
		}
		WorkAssignmentChangeReason workAssignmentChangeReason = 
				this.workAssignmentChangeReasonInstanceFactory.createInstance();
		populateWorkAssignmentChangeReason(workAssignmentChangeReason, name, 
				valid);
		return this.workAssignmentChangeReasonDao.makePersistent(
				workAssignmentChangeReason);
	}
	
	/**
	 * Updates a work assignment change reason.
	 * @param workAssignmentChangeReason work assignment change reason
	 * @param name name
	 * @param valid valid
	 * @return work assignment change reason
	 * @throws DuplicateEntityFoundException if duplicate entity found
	 */
	public WorkAssignmentChangeReason update(
			final WorkAssignmentChangeReason workAssignmentChangeReason, 
			final String name, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.workAssignmentChangeReasonDao.findExcluding(name, 
				workAssignmentChangeReason) != null) {
			throw new DuplicateEntityFoundException("Work assignment "
					+ "change reason already exists");
		}
		populateWorkAssignmentChangeReason(workAssignmentChangeReason, name, 
				valid);
		return this.workAssignmentChangeReasonDao.makePersistent(
				workAssignmentChangeReason);
	}
	
	/**
	 * Removes a work assignment change reason.
	 *  
	 * @param workAssignmentChangeReason work assignment change reason
	 */
	public void remove(WorkAssignmentChangeReason workAssignmentChangeReason) {
		this.workAssignmentChangeReasonDao.makeTransient(
				workAssignmentChangeReason);
	}

	// Populates a work assignment change reason
	private void populateWorkAssignmentChangeReason(
			final WorkAssignmentChangeReason workAssignmentChangeReason,
			final String name, final Boolean valid) {
		workAssignmentChangeReason.setName(name);
		workAssignmentChangeReason.setValid(valid);
	}
}