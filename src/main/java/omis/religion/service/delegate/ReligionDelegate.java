package omis.religion.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.religion.dao.ReligionDao;
import omis.religion.domain.Religion;
import omis.religion.domain.ReligionGroup;

/**
 * ReligionDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 10, 2016)
 *@since OMIS 3.0
 *
 */
public class ReligionDelegate {
	
	public final ReligionDao religionDao;

	public final InstanceFactory<Religion> religionInstanceFactory;
	
	/**
	 * Contructor for ReligionDelegate
	 * @param religionDao
	 */
	public ReligionDelegate(final ReligionDao religionDao,
			final InstanceFactory<Religion> religionInstanceFactory) {
		this.religionDao = religionDao;
		this.religionInstanceFactory = religionInstanceFactory;
	}
	
	/**
	 * Returns a list of all Religions
	 * @return List of all Religions
	 */
	public List<Religion> findAll() {
		return this.religionDao.findAll();
	}
	
	/**
	 * Creates a new religion.
	 * 
	 * @param name name
	 * @param group religion group
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return religion
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public Religion create(final String name, final ReligionGroup group, 
			final Short sortOrder, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.religionDao.find(name, group) != null) {
			throw new DuplicateEntityFoundException("Religion already exists.");
		}
		Religion religion = this.religionInstanceFactory.createInstance();
		populateReligion(religion, name, group, sortOrder, valid);
		return this.religionDao.makePersistent(religion);
	}
	
	/**
	 * Updates a religion.
	 * 
	 * @param religion religion
	 * @param name name
	 * @param group religion group
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return religion
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public Religion update(final Religion religion, final String name, 
			final ReligionGroup group, final Short sortOrder, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.religionDao.findExcluding(name, group, religion) != null) {
			throw new DuplicateEntityFoundException("Religion already exists.");
		}
		populateReligion(religion, name, group, sortOrder, valid);
		return this.religionDao.makePersistent(religion);
	}

	/**
	 * Removes a religion.
	 * 
	 * @param religion religion
	 */
	public void remove(Religion religion) {
		this.religionDao.makeTransient(religion);
	}
	
	// Populates a religion
	private void populateReligion(final Religion religion, final String name, 
			final ReligionGroup group, final Short sortOrder, 
			final Boolean valid) {
		religion.setGroup(group);
		religion.setName(name);
		religion.setSortOrder(sortOrder);
		religion.setValid(valid);
	}
}
