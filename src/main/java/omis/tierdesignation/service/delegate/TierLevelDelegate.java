package omis.tierdesignation.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.tierdesignation.dao.TierLevelDao;
import omis.tierdesignation.domain.TierLevel;

/**
 * Delegate for tier levels.
 *
 * @author Josh Divine
 * @version 0.0.1 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public class TierLevelDelegate {
	
	/* Instance factories. */
	
	private final InstanceFactory<TierLevel> tierLevelInstanceFactory;

	/* Data access objects. */
	
	private final TierLevelDao tierLevelDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for tier levels.
	 * 
	 * @param tierLevelInstanceFactory instance factory for tier levels
	 * @param tierLevelDao data access object for tier levels
	 */
	public TierLevelDelegate(
			final InstanceFactory<TierLevel> tierLevelInstanceFactory,
			final TierLevelDao tierLevelDao) {
		this.tierLevelDao = tierLevelDao;
		this.tierLevelInstanceFactory = tierLevelInstanceFactory;
	}
	
	/* Methods. */

	/**
	 * Returns tier levels.
	 * 
	 * @return tier levels
	 */
	public List<TierLevel> findLevels() {
		return this.tierLevelDao.findAll();
	}
	
	/**
	 * Creates a tier level.
	 * 
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return tier level
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public TierLevel create(final String name, final Short sortOrder, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.tierLevelDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Tier level already exists");
		}
		TierLevel tierLevel = this.tierLevelInstanceFactory
				.createInstance();
		populateTierLevel(tierLevel, name, sortOrder, valid);
		return this.tierLevelDao.makePersistent(tierLevel);
	}
	
	/**
	 * Updates a tier level.
	 * 
	 * @param tierLevel tier level
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return tier level
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public TierLevel update(final TierLevel tierLevel, final String name, 
			final Short sortOrder, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.tierLevelDao.findExcluding(name, tierLevel) != null) {
			throw new DuplicateEntityFoundException("Tier level already exists");
		}
		populateTierLevel(tierLevel, name, sortOrder, valid);
		return this.tierLevelDao.makePersistent(tierLevel);
	}

	/**
	 * Removes a tier level.
	 * 
	 * @param tierLevel tier level
	 */
	public void remove(final TierLevel tierLevel) {
		this.tierLevelDao.makeTransient(tierLevel);
	}
	
	// Populates a tier change reason
	private void populateTierLevel(
			final TierLevel tierLevel, final String name,
			final Short sortOrder, final Boolean valid) {
		tierLevel.setName(name);
		tierLevel.setSortOrder(sortOrder);
		tierLevel.setValid(valid);
	}
}
