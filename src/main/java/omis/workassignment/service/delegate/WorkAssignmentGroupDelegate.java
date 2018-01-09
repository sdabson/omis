package omis.workassignment.service.delegate;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.workassignment.dao.WorkAssignmentGroupDao;
import omis.workassignment.domain.WorkAssignmentGroup;

/**
 * Delegate for work assignment change reason.
 *
 * @author Josh Divine
 * @version 0.0.1 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public class WorkAssignmentGroupDelegate {

	/* Resources. */
	private final WorkAssignmentGroupDao workAssignmentGroupDao;
	private final InstanceFactory<WorkAssignmentGroup> 
		workAssignmentGroupInstanceFactory;
	
	/* Constructors. */
	/**
	 * Instantiates delegate for work assignment change reason.
	 * 
	 * @param workAssignmentChangeReasonDao work assignment change reason dao
	 */
	public WorkAssignmentGroupDelegate(
			final WorkAssignmentGroupDao workAssignmentGroupDao,
			final InstanceFactory<WorkAssignmentGroup> 
				workAssignmentGroupInstanceFactory) {
		this.workAssignmentGroupDao = workAssignmentGroupDao;
		this.workAssignmentGroupInstanceFactory = 
				workAssignmentGroupInstanceFactory;
	}
	
	/* Methods. */
	/**
	 * Creates a work assignment group.
	 * 
	 * @param name name
	 * @param valid valid
	 * @return work assignment group
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public WorkAssignmentGroup create(final String name, final Boolean valid) 
			throws DuplicateEntityFoundException {
		if (this.workAssignmentGroupDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Work assignment group "
					+ "already exists");
		}
		WorkAssignmentGroup workAssignmentGroup = 
				this.workAssignmentGroupInstanceFactory.createInstance();
		populateWorkAssignmentGroup(workAssignmentGroup, name, valid);
		return this.workAssignmentGroupDao.makePersistent(workAssignmentGroup);
	}

	/**
	 * Updates a work assignment group.
	 * 
	 * @param workAssignmentGroup work assignment group
	 * @param name name
	 * @param valid valid
	 * @return work assignment group
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public WorkAssignmentGroup update(
			final WorkAssignmentGroup workAssignmentGroup, final String name, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.workAssignmentGroupDao.findExcluding(name, workAssignmentGroup) 
				!= null) {
			throw new DuplicateEntityFoundException("Work assignment group "
					+ "already exists");
		}
		populateWorkAssignmentGroup(workAssignmentGroup, name, valid);
		return this.workAssignmentGroupDao.makePersistent(workAssignmentGroup);
	}
	
	/**
	 * Removes a work assignment group.
	 * 
	 * @param workAssignmentGroup work assignment group
	 */
	public void remove(final WorkAssignmentGroup workAssignmentGroup) {
		this.workAssignmentGroupDao.makeTransient(workAssignmentGroup);
	}
	
	// Populates a work assignment group
	private void populateWorkAssignmentGroup(
			final WorkAssignmentGroup workAssignmentGroup, final String name,
			final Boolean valid) {
		workAssignmentGroup.setName(name);
		workAssignmentGroup.setValid(valid);
	}
}
