package omis.tierdesignation.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.tierdesignation.dao.TierChangeReasonDao;
import omis.tierdesignation.domain.TierChangeReason;

/**
 * Delegate for tier change reasons.
 *
 * @author Josh Divine
 * @version 0.0.1 (Jul 19, 2017)
 * @since OMIS 3.0
 */
public class TierChangeReasonDelegate {

	/* Instance factories. */
	
	private final InstanceFactory<TierChangeReason>
	tierChangeReasonInstanceFactory;

	/* Data access objects. */
	
	private final TierChangeReasonDao tierChangeReasonDao;
	
	/* Constructors. */
	
	/**
	 * Instantiates delegate for tier change reasons.
	 * 
	 * @param tierChangeReasonInstanceFactory instance factory for tier change 
	 * reasons
	 * @param tierChangeReasonDao data access object for tier change reasons
	 */
	public TierChangeReasonDelegate(
			final InstanceFactory<TierChangeReason> 
				tierChangeReasonInstanceFactory,
			final TierChangeReasonDao tierChangeReasonDao) {
		this.tierChangeReasonDao = tierChangeReasonDao;
		this.tierChangeReasonInstanceFactory = tierChangeReasonInstanceFactory;
	}
	
	/* Methods. */
	/**
	 * Returns tier change reasons.
	 * 
	 * @return tier change reasons
	 */
	public List<TierChangeReason> findChangeReasons() {
		return this.tierChangeReasonDao.findAll();
	}
	
	/**
	 * Creates a tier change reason.
	 * 
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return tier change reason
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public TierChangeReason create(final String name, final Short sortOrder, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.tierChangeReasonDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Tier change reason already"
					+ " exists");
		}
		TierChangeReason tierChangeReason = this.tierChangeReasonInstanceFactory
				.createInstance();
		populateTierChangeReason(tierChangeReason, name, sortOrder, valid);
		return this.tierChangeReasonDao.makePersistent(tierChangeReason);
	}
	
	/**
	 * Updates a tier change reason.
	 * 
	 * @param tierChangeReason tier change reason
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return tier change reason
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	public TierChangeReason update(final TierChangeReason tierChangeReason,
			final String name, final Short sortOrder, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.tierChangeReasonDao.findExcluding(name, tierChangeReason) != 
				null) {
			throw new DuplicateEntityFoundException("Tier change reason already"
					+ " exists");
		}
		populateTierChangeReason(tierChangeReason, name, sortOrder, valid);
		return this.tierChangeReasonDao.makePersistent(tierChangeReason);
	}

	/**
	 * Removes a tier change reason.
	 * 
	 * @param tierChangeReason tier change reason
	 */
	public void remove(final TierChangeReason tierChangeReason) {
		this.tierChangeReasonDao.makeTransient(tierChangeReason);
	}
	
	// Populates a tier change reason
	private void populateTierChangeReason(
			final TierChangeReason tierChangeReason, final String name,
			final Short sortOrder, final Boolean valid) {
		tierChangeReason.setName(name);
		tierChangeReason.setSortOrder(sortOrder);
		tierChangeReason.setValid(valid);
	}
}
