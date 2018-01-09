package omis.separationneed.service.delegate;

import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.separationneed.dao.SeparationNeedReasonDao;
import omis.separationneed.domain.SeparationNeedReason;

/**
 * Delegate for separation need reasons.
 *
 * @author Josh Divine
 * @version 0.0.1 (Aug 7, 2017)
 * @since OMIS 3.0
 */
public class SeparationNeedReasonDelegate {

	private final SeparationNeedReasonDao separationNeedReasonDao;
	
	private final InstanceFactory<SeparationNeedReason> 
		separationNeedReasonInstanceFactory;

	/**
	 * Instantiates a delegate for separation need reasons.
	 * @param separationNeedReasonDao
	 * @param separationNeedReasonInstanceFactory
	 */
	public SeparationNeedReasonDelegate(
			final SeparationNeedReasonDao separationNeedReasonDao,
			final InstanceFactory<SeparationNeedReason> 
				separationNeedReasonInstanceFactory) {
		this.separationNeedReasonDao = separationNeedReasonDao;
		this.separationNeedReasonInstanceFactory = 
				separationNeedReasonInstanceFactory;
	}
	
	/**
	 * Creates a new separation need reason.
	 * 
	 * @param name name
	 * @param valid valid
	 * @return separation need reason
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	public SeparationNeedReason create(final String name, final Boolean valid) 
			throws DuplicateEntityFoundException {
		if (this.separationNeedReasonDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Separation need reason already exists.");
		}
		SeparationNeedReason separationNeedReason = 
				this.separationNeedReasonInstanceFactory.createInstance();
		populateSeparationNeedReason(separationNeedReason, name, valid);
		return this.separationNeedReasonDao.makePersistent(
				separationNeedReason);
	}
	
	/**
	 * Updates an existing separation need reason.
	 * 
	 * @param separationNeedReason separation need reason
	 * @param name name
	 * @param valid valid
	 * @return separation need reason
	 * @throws DuplicateEntityFoundException if entity already exists
	 */
	public SeparationNeedReason update(
			final SeparationNeedReason separationNeedReason, final String name, 
			final Boolean valid) throws DuplicateEntityFoundException {
		if (this.separationNeedReasonDao.find(name) != null) {
			throw new DuplicateEntityFoundException(
					"Separation need reason already exists.");
		}
		populateSeparationNeedReason(separationNeedReason, name, valid);
		return this.separationNeedReasonDao.makePersistent(
				separationNeedReason);
	}

	/**
	 * Removes the specified separation need reason.
	 * 
	 * @param separationNeedReason separation need reason
	 */
	public void remove(final SeparationNeedReason separationNeedReason) {
		this.separationNeedReasonDao.makeTransient(separationNeedReason);
	}
	
	/**
	 * Returns a list of separation need reasons.
	 * 
	 * @return list of separation need reasons
	 */
	public List<SeparationNeedReason> findAll() {
		return this.separationNeedReasonDao.findAll();
	}
	
	// Populates a separation need reason
	private void populateSeparationNeedReason(
			final SeparationNeedReason separationNeedReason, final String name,
			final Boolean valid) {
		separationNeedReason.setName(name);
		separationNeedReason.setValid(valid);
	}
}
