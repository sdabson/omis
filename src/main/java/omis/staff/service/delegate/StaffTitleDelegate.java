package omis.staff.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.staff.dao.StaffTitleDao;
import omis.staff.domain.StaffTitle;

/**
 * Staff title delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Nov 9, 2017)
 * @since OMIS 3.0
 */
public class StaffTitleDelegate {

	/* Data access objects. */
	
	private final StaffTitleDao staffTitleDao;
	
	/* Instance factories. */
	
	private final InstanceFactory<StaffTitle> staffTitleInstanceFactory;
	
	/**
	 * Instantiates an implementation of staff title delegate with the specified 
	 * data access object and instance factory.
	 * 
	 * @param staffTitleDao staff title data access object
	 * @param staffTitleInstanceFactory staff title instance factory
	 */
	public StaffTitleDelegate(final StaffTitleDao staffTitleDao,
			final InstanceFactory<StaffTitle> 
					staffTitleInstanceFactory) {
		this.staffTitleDao = staffTitleDao;
		this.staffTitleInstanceFactory = staffTitleInstanceFactory;
		
	}

	/**
	 * Creates a new staff title.
	 * 
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return staff title
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public StaffTitle create(final String name, final Short sortOrder, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.staffTitleDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Staff title already exists.");
		}
		StaffTitle staffTitle = this.staffTitleInstanceFactory.createInstance();
		populateStaffTitle(staffTitle, name, sortOrder, valid);
		return this.staffTitleDao.makePersistent(staffTitle);
	}
	
	/**
	 * Updates an existing staff title.
	 * 
	 * @param staffTitle staff title
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return staff title
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public StaffTitle update(final StaffTitle staffTitle, 
			final String name, final Short sortOrder, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.staffTitleDao.findExcluding(name, staffTitle) != null) {
			throw new DuplicateEntityFoundException(
					"Staff title already exists.");
		}
		populateStaffTitle(staffTitle, name, sortOrder, valid);
		return this.staffTitleDao.makePersistent(staffTitle);
	}
	
	/**
	 * Removes the specified staff title.
	 * 
	 * @param staffTitle staff title
	 */
	public void remove(final StaffTitle staffTitle) {
		this.staffTitleDao.makeTransient(staffTitle);
	}
	
	/**
	 * Returns staff titles.
	 * 
	 * @return staff titles
	 */
	public List<StaffTitle> findAll() {
		return this.staffTitleDao.findAll();
	}
	
	/**
	 * Returns the highest sort order.
	 * 
	 * @return highest sort order
	 */
	public short findHighestSortOrder() {
		return this.staffTitleDao.findHighestSortOrder();
	}
	
	// Populates a staff assignment
	private void populateStaffTitle(final StaffTitle staffTitle, 
			final String name, final Short sortOrder, final Boolean valid) {
		staffTitle.setName(name);
		staffTitle.setSortOrder(sortOrder);
		staffTitle.setValid(valid);
	}
}
