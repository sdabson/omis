package omis.religion.service.delegate;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.religion.dao.ReligionGroupDao;
import omis.religion.domain.ReligionGroup;

/**
 * Delegate for religion groups.
 *
 * @author Josh Divine
 * @version 0.1.0 (Jul 26, 2017)
 * @since OMIS 3.0
 *
 */
public class ReligionGroupDelegate {

	public final ReligionGroupDao religionGroupDao;

	public final InstanceFactory<ReligionGroup> religionGroupInstanceFactory;
	
	/**
	 * Construct for religion group delegate.
	 * 
	 * @param religionGroupDao religion group data access object
	 * @param religionGroupInstanceFactory religion group instance factory
	 */
	public ReligionGroupDelegate(final ReligionGroupDao religionGroupDao, 
			final InstanceFactory<ReligionGroup> religionGroupInstanceFactory) {
		this.religionGroupDao = religionGroupDao;
		this.religionGroupInstanceFactory = religionGroupInstanceFactory;
	}
	
	/**
	 * Creates a new religion group.
	 * 
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return religion group
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public ReligionGroup create(final String name, final Short sortOrder,
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.religionGroupDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Religion group already exists.");
		}
		ReligionGroup group = this.religionGroupInstanceFactory
				.createInstance();
		populateReligionGroup(group, name, sortOrder, valid);
		return this.religionGroupDao.makePersistent(group);
	}
	
	/**
	 * Updates a religion group.
	 * 
	 * @param group religion group
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return religion group
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public ReligionGroup create(final ReligionGroup group, final String name, 
			final Short sortOrder, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.religionGroupDao.findExcluding(name, group) != null) {
			throw new DuplicateEntityFoundException(
					"Religion group already exists.");
		}
		populateReligionGroup(group, name, sortOrder, valid);
		return this.religionGroupDao.makePersistent(group);
	}
	
	/**
	 * Removes a religion group.
	 * 
	 * @param group religion group
	 */
	public void remove(final ReligionGroup group) {
		this.religionGroupDao.makeTransient(group);
	}

	// Populates a religion group
	private void populateReligionGroup(final ReligionGroup group, 
			final String name, final Short sortOrder, final Boolean valid) {
		group.setName(name);
		group.setSortOrder(sortOrder);
		group.setValid(valid);
	}	
}
