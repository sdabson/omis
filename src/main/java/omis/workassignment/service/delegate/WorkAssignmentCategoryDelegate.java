package omis.workassignment.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.workassignment.dao.WorkAssignmentCategoryDao;
import omis.workassignment.domain.WorkAssignmentCategory;
import omis.workassignment.domain.WorkAssignmentGroup;

/**
 * Delegate for work assignment category.
 *
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.0.3 (Jul 18, 2017)
 * @since OMIS 3.0
 */
public class WorkAssignmentCategoryDelegate {
	/* Resources. */
	private final WorkAssignmentCategoryDao workAssignmentCategoryDao;
	private final InstanceFactory<WorkAssignmentCategory> 
			workAssignmentCategoryInstanceFactory;
	
	/* Constructors. */
	/**
	 * Instantiates delegate for fence restriction.
	 * 
	 * @param fenceRestrictionDao fence restriction dao
	 */
	public WorkAssignmentCategoryDelegate(
		final WorkAssignmentCategoryDao workAssignmentCategoryDao,
		final InstanceFactory<WorkAssignmentCategory> 
		workAssignmentCategoryInstanceFactory) {
		this.workAssignmentCategoryDao = workAssignmentCategoryDao;
		this.workAssignmentCategoryInstanceFactory = 
				workAssignmentCategoryInstanceFactory;
	}

	/* Methods. */
	/**
	 * Returns work assignment categories.
	 * 
	 * @return work assignment categories
	 */
	public List<WorkAssignmentCategory> find() {
		return this.workAssignmentCategoryDao.findAll();
	}
	
	/**
	 * Creates a work assignment category.
	 * 
	 * @param name name
	 * @param valid valid
	 * @param workAssignmentGroup work assignment group
	 * @return work assignment category
	 * @throws DuplicateEntityFoundException if duplicate entity found
	 */
	public WorkAssignmentCategory create(final String name, final Boolean valid,
			final WorkAssignmentGroup workAssignmentGroup) 
					throws DuplicateEntityFoundException {
		if (this.workAssignmentCategoryDao.find(name, workAssignmentGroup) != 
				null) {
			throw new DuplicateEntityFoundException("Work assignment category "
					+ "already exists");
		}
		WorkAssignmentCategory workAssignmentCategory = 
				this.workAssignmentCategoryInstanceFactory.createInstance();
		populateWorkAssignmentCategory(workAssignmentCategory, name, valid, 
				workAssignmentGroup);
		return this.workAssignmentCategoryDao.makePersistent(
				workAssignmentCategory);
	}
	
	/**
	 * Updates a work assignment category.
	 * 
	 * @param workAssignmentCategory work assignment category
	 * @param name name
	 * @param valid valid
	 * @param workAssignmentGroup work assignment group
	 * @return work assignment category
	 * @throws DuplicateEntityFoundException if duplicate entity found
	 */
	public WorkAssignmentCategory update(
			final WorkAssignmentCategory workAssignmentCategory, 
			final String name, final Boolean valid, 
			final WorkAssignmentGroup workAssignmentGroup) 
					throws DuplicateEntityFoundException {
		if (this.workAssignmentCategoryDao.findExcluding(name, 
				workAssignmentGroup, workAssignmentCategory) != null) {
			throw new DuplicateEntityFoundException("Work assignment category "
					+ "already exists");
		}
		populateWorkAssignmentCategory(workAssignmentCategory, name, valid, 
				workAssignmentGroup);
		return this.workAssignmentCategoryDao.makePersistent(
				workAssignmentCategory);
	}
	
	/**
	 * Removes a work assignment category.
	 * 
	 * @param workAssignmentCategory work assignment category
	 */
	public void remove(final WorkAssignmentCategory workAssignmentCategory) {
		this.workAssignmentCategoryDao.makeTransient(workAssignmentCategory);
	}

	// Populates a work assignment category
	private void populateWorkAssignmentCategory(
			final WorkAssignmentCategory workAssignmentCategory, 
			final String name, final Boolean valid, 
			final WorkAssignmentGroup workAssignmentGroup) {
		workAssignmentCategory.setGroup(workAssignmentGroup);
		workAssignmentCategory.setName(name);
		workAssignmentCategory.setValid(valid);
	}
	
}