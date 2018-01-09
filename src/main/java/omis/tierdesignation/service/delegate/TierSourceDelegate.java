package omis.tierdesignation.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.tierdesignation.dao.TierSourceDao;
import omis.tierdesignation.domain.TierSource;

/**
 * Delegate for tier sources.
 *
 * @author Josh Divine
 * @version 0.0.1 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public class TierSourceDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<TierSource> tierSourceInstanceFactory;

	/* Data access objects. */
	
	private final TierSourceDao tierSourceDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for tier sources.
	 * 
	 * @param tierSourceInstanceFactory instance factory for tier sources
	 * @param tierSourceDao data access object for tier sources
	 */
	public TierSourceDelegate(
			final InstanceFactory<TierSource> tierSourceInstanceFactory,
			final TierSourceDao tierSourceDao) {
		this.tierSourceDao = tierSourceDao;
		this.tierSourceInstanceFactory = tierSourceInstanceFactory;
	}
	
	/* Methods. */

	/**
	 * Returns tier sources.
	 * 
	 * @return tier sources
	 */
	public List<TierSource> findSources() {
		return this.tierSourceDao.findAll();
	}
	
	/**
	 * Creates a tier source.
	 * 
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return tier source
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public TierSource create(final String name, final Short sortOrder, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.tierSourceDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Tier source already exists");
		}
		TierSource tierSource = this.tierSourceInstanceFactory
				.createInstance();
		populateTierSource(tierSource, name, sortOrder, valid);
		return this.tierSourceDao.makePersistent(tierSource);
	}
	
	/**
	 * Updates a tier source.
	 * 
	 * @param tierSource tier source
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return tier source
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public TierSource update(final TierSource tierSource, final String name, 
			final Short sortOrder, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.tierSourceDao.findExcluding(name, tierSource) != null) {
			throw new DuplicateEntityFoundException("Tier source already exists");
		}
		populateTierSource(tierSource, name, sortOrder, valid);
		return this.tierSourceDao.makePersistent(tierSource);
	}

	/**
	 * Removes a tier source.
	 * 
	 * @param tierSource tier source
	 */
	public void remove(final TierSource tierSource) {
		this.tierSourceDao.makeTransient(tierSource);
	}
	
	// Populates a tier change reason
	private void populateTierSource(
			final TierSource tierSource, final String name,
			final Short sortOrder, final Boolean valid) {
		tierSource.setName(name);
		tierSource.setSortOrder(sortOrder);
		tierSource.setValid(valid);
	}
}
