package omis.workassignment.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.workassignment.dao.FenceRestrictionDao;
import omis.workassignment.domain.FenceRestriction;

/**
 * Delegate for fence restriction.
 *
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.0.3 (Jul 18, 2017)
 * @since OMIS 3.0
 */
public class FenceRestrictionDelegate {
	/* Resources. */
	private final FenceRestrictionDao fenceRestrictionDao;
	private final InstanceFactory<FenceRestriction> fenceRestrictionInstanceFactory;
	
	/* Constructors. */
	/**
	 * Instantiates delegate for fence restriction.
	 * 
	 * @param fenceRestrictionDao fence restriction dao
	 */
	public FenceRestrictionDelegate(
		final FenceRestrictionDao fenceRestrictionDao,
		final InstanceFactory<FenceRestriction> fenceRestrictionInstanceFactory) {
		this.fenceRestrictionDao = fenceRestrictionDao;
		this.fenceRestrictionInstanceFactory = fenceRestrictionInstanceFactory;
	}

	/* Methods. */
	/**
	 * Returns addresses.
	 * 
	 * @return addresses
	 */
	public List<FenceRestriction> find() {
		return this.fenceRestrictionDao.findAll();
	}
	
	/**
	 * Creates a fence restriction.
	 * 
	 * @param name name
	 * @param valid valid
	 * @return fence restriction
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	public FenceRestriction create(final String name, final Boolean valid) 
			throws DuplicateEntityFoundException {
		if (this.fenceRestrictionDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Fence restriction already "
					+ "exists");
		}
		FenceRestriction fenceRestrion = this.fenceRestrictionInstanceFactory
				.createInstance();
		populateFenceRestriction(fenceRestrion, name, valid);
		return this.fenceRestrictionDao.makePersistent(fenceRestrion);
	}

	/**
	 * Updates an existing fence restriction.
	 * 
	 * @param fenceRestriction fence restriction
	 * @param name name
	 * @param valid valid
	 * @return fence restriction
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	public FenceRestriction update(final FenceRestriction fenceRestriction,
			final String name, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.fenceRestrictionDao.findExcluding(name, fenceRestriction) != 
				null) {
			throw new DuplicateEntityFoundException("Fence restriction already "
					+ "exists");
		}
		populateFenceRestriction(fenceRestriction, name, valid);
		return this.fenceRestrictionDao.makePersistent(fenceRestriction);
	}
	
	/**
	 * Removes a fence restriction.
	 * 
	 * @param fenceRestriction fence restriction
	 */
	public void remove(final FenceRestriction fenceRestriction) {
		this.fenceRestrictionDao.makeTransient(fenceRestriction);
	}
	
	// Populates fence restriction
	private void populateFenceRestriction(final FenceRestriction fenceRestrion, 
			final String name, final Boolean valid) {
		fenceRestrion.setName(name);
		fenceRestrion.setValid(valid);
	}
	
}