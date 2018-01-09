package omis.separationneed.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.separationneed.dao.SeparationNeedRemovalReasonDao;
import omis.separationneed.domain.SeparationNeedRemovalReason;

/**
 * Delegate for separation need removal reasons.
 *
 * @author Josh Divine
 * @version 0.0.1 (Aug 7, 2017)
 * @since OMIS 3.0
 */
public class SeparationNeedRemovalReasonDelegate {

	private final SeparationNeedRemovalReasonDao separationNeedRemovalReasonDao;
	
	private final InstanceFactory<SeparationNeedRemovalReason> 
			separationNeedRemovalReasonInstanceFactory;
	
	/**
	 * Instantiates delegate for separation need removal reasons.
	 * 
	 * @param separationNeedRemovalReasonDao separation need removal reason data 
	 * access object
	 * @param separationNeedRemovalReasonInstanceFactory separation need removal 
	 * reason instance factory
	 */
	public SeparationNeedRemovalReasonDelegate(
			final SeparationNeedRemovalReasonDao separationNeedRemovalReasonDao,
			final InstanceFactory<SeparationNeedRemovalReason> 
				separationNeedRemovalReasonInstanceFactory) {
		this.separationNeedRemovalReasonDao = separationNeedRemovalReasonDao;
		this.separationNeedRemovalReasonInstanceFactory = 
				separationNeedRemovalReasonInstanceFactory;
	}
	
	/**
	 * Creates a new separation need removal reason.
	 * 
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return separation need removal reason
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	public SeparationNeedRemovalReason create(final String name, 
			final Short sortOrder, final Boolean valid) 
			throws DuplicateEntityFoundException {
		if (this.separationNeedRemovalReasonDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Separation need removal reason already exists.");
		}
		SeparationNeedRemovalReason reason = this
				.separationNeedRemovalReasonInstanceFactory.createInstance();
		populateSeparationNeedRemovalReason(reason, name, sortOrder, valid);
		return this.separationNeedRemovalReasonDao.makePersistent(reason);
	}
	
	/**
	 * Updates an existing separation need removal reason.
	 * 
	 * @param separationNeedRemovalReason separation need removal reason
	 * @param name name
	 * @param sortOrder sort order
	 * @param valid valid
	 * @return separation need removal reason
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	public SeparationNeedRemovalReason update(
			final SeparationNeedRemovalReason separationNeedRemovalReason,
			final String name, final Short sortOrder, final Boolean valid) 
			throws DuplicateEntityFoundException {
		if (this.separationNeedRemovalReasonDao.findExcluding(name, 
				separationNeedRemovalReason) != null) {
			throw new DuplicateEntityFoundException(
					"Separation need removal reason already exists.");
		}
		populateSeparationNeedRemovalReason(separationNeedRemovalReason, name, sortOrder, valid);
		return this.separationNeedRemovalReasonDao.makePersistent(
				separationNeedRemovalReason);
	}

	/**
	 * Removes the specified separation need removal reason.
	 * 
	 * @param separationNeedRemovalReason separation need removal reason
	 */
	public void remove(
			final SeparationNeedRemovalReason separationNeedRemovalReason) {
		this.separationNeedRemovalReasonDao.makeTransient(
				separationNeedRemovalReason);
	}
	
	/**
	 * Returns removal reasons.
	 * 
	 * @return list of removal reasons
	 */
	public List<SeparationNeedRemovalReason> findAll() {
		return this.separationNeedRemovalReasonDao.findAll();
	}
	
	// Populates a separation need removal reason
	private void populateSeparationNeedRemovalReason(
			final SeparationNeedRemovalReason separationNeedRemovalReason,
			final String name, final Short sortOrder, final Boolean valid) {
		separationNeedRemovalReason.setName(name);
		separationNeedRemovalReason.setSortOrder(sortOrder);
		separationNeedRemovalReason.setValid(valid);
	}
}
