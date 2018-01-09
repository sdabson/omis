package omis.bedplacement.service.delegate;

import java.util.List;

import omis.bedplacement.dao.BedPlacementReasonDao;
import omis.bedplacement.domain.BedPlacementReason;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Bed placement reason delegate.
 *
 * @author Josh Divine
 * @version 0.1.0 (Aug 2, 2017)
 * @since OMIS 3.0
 */
public class BedPlacementReasonDelegate {

	private final BedPlacementReasonDao bedPlacementReasonDao;
	
	private final InstanceFactory<BedPlacementReason> 
		bedPlacementReasonInstanceFactory;
	
	/**
	 * Constructor for bed placement reason delegate.
	 * 
	 * @param bedPlacementReasonDao bed placement reason data access object
	 * @param bedPlacementReasonInstanceFactory bed placement reason instance 
	 * factory
	 */
	public BedPlacementReasonDelegate(
			final BedPlacementReasonDao bedPlacementReasonDao,
			final InstanceFactory<BedPlacementReason>
				bedPlacementReasonInstanceFactory) {
		this.bedPlacementReasonDao = bedPlacementReasonDao;
		this.bedPlacementReasonInstanceFactory = 
				bedPlacementReasonInstanceFactory;
	}
	
	/**
	 * Creates a new bed placement reason.
	 * 
	 * @param name name 
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return bed placement reason
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	public BedPlacementReason create(final String name, final Integer sortOrder, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.bedPlacementReasonDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"The bed placement reason already exists.");
		}
		BedPlacementReason reason = this.bedPlacementReasonInstanceFactory
				.createInstance();
		populateBedPlacementReason(reason, name, sortOrder, valid);
		return this.bedPlacementReasonDao.makePersistent(reason);
	}
	
	/**
	 * Updates an existing bed placement reason.
	 * 
	 * @param reason bed placement reason
	 * @param name name 
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return bed placement reason
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	public BedPlacementReason update(final BedPlacementReason reason,
			final String name, final Integer sortOrder, final Boolean valid) 
					throws DuplicateEntityFoundException {
		if (this.bedPlacementReasonDao.findExcluding(name, reason) != null) {
			throw new DuplicateEntityFoundException(
					"The bed placement reason already exists.");
		}
		populateBedPlacementReason(reason, name, sortOrder, valid);
		return this.bedPlacementReasonDao.makePersistent(reason);
	}

	/**
	 * Removes the specified bed placement reason.
	 * 
	 * @param reason bed placement reason
	 */
	public void remove(final BedPlacementReason reason) {
		this.bedPlacementReasonDao.makeTransient(reason);
	}
	
	/**
	 * Returns all the bed placement reasons.
	 * 
	 * @return bed placement reasons
	 */
	public List<BedPlacementReason> findAll() {
		return this.bedPlacementReasonDao.findAll();
	}
	
	// Populates a bed placement reason
	private void populateBedPlacementReason(final BedPlacementReason reason, 
			final String name, final Integer sortOrder, final Boolean valid) {
		reason.setName(name);
		reason.setSortOrder(sortOrder);
		reason.setValid(valid);
	}
}
