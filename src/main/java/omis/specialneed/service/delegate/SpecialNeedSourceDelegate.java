package omis.specialneed.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.specialneed.dao.SpecialNeedSourceDao;
import omis.specialneed.domain.SpecialNeedSource;

/**
 * Special need source delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public class SpecialNeedSourceDelegate {

	/* Data access objects. */
	
	private final SpecialNeedSourceDao specialNeedSourceDao;
	

	/* Instance factories. */
	
	private final InstanceFactory<SpecialNeedSource> 
			specialNeedSourceInstanceFactory;
	
	/** Instantiates an implementation of special need delegate with 
	 * the specified date access object and instance factory.
	 * 
	 * @param specialNeedSourceDao special need source data access object
	 * @param specialNeedSourceInstanceFactory special need source instance 
	 * factory
	 */
	public SpecialNeedSourceDelegate(
			final SpecialNeedSourceDao specialNeedSourceDao,
			final InstanceFactory<SpecialNeedSource> 
				specialNeedSourceInstanceFactory) {
		this.specialNeedSourceDao = specialNeedSourceDao;
		this.specialNeedSourceInstanceFactory = 
				specialNeedSourceInstanceFactory; 
	}
	
	/**
	 * Searches for all special need sources.
	 *
	 *
	 * @return list of special need sources
	 */
	public List<SpecialNeedSource> findSources() {
		return this.specialNeedSourceDao.findAll();
	}
	
	/**
	 * Creates a special need source.
	 * 
	 * @param name name
	 * @param valid valid
	 * @return special need source
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public SpecialNeedSource create(final String name, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.specialNeedSourceDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Special need source "
					+ "already exists");
		}
		SpecialNeedSource specialNeedSource = 
				this.specialNeedSourceInstanceFactory.createInstance();
		populateSpecialNeedSource(specialNeedSource, name, valid);
		return this.specialNeedSourceDao.makePersistent(specialNeedSource);
	}
	
	/**
	 * Updates a special need source.
	 * 
	 * @param specialNeedSource special need source
	 * @param name name
	 * @param valid valid
	 * @return special need source
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public SpecialNeedSource update(final SpecialNeedSource specialNeedSource, 
			final String name, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.specialNeedSourceDao.findExcluding(name, specialNeedSource) != 
				null) {
			throw new DuplicateEntityFoundException("Special need source "
					+ "already exists");
		}
		populateSpecialNeedSource(specialNeedSource, name, 
				valid);
		return this.specialNeedSourceDao.makePersistent(specialNeedSource);		
	}

	/**
	 * Removes a special need source.
	 * 
	 * @param specialNeedSource special need source
	 */
	public void remove(final SpecialNeedSource specialNeedSource) {
		this.specialNeedSourceDao.makeTransient(specialNeedSource);
	}

	// Populates a special need source
	private void populateSpecialNeedSource(
			final SpecialNeedSource specialNeedSource, final String name, 
			final Boolean valid) {
		specialNeedSource.setName(name);
		specialNeedSource.setValid(valid);
	}
}
