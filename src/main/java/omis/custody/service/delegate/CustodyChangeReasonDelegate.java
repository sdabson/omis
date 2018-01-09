package omis.custody.service.delegate;

import java.util.List;

import omis.custody.dao.CustodyChangeReasonDao;
import omis.custody.domain.CustodyChangeReason;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Custody Change Reason Delegate
 * 
 * @author Josh Divine 
 * @version 0.1.0 (December 2, 2016)
 * @since OMIS 3.0
 */
public class CustodyChangeReasonDelegate {

	private CustodyChangeReasonDao custodyChangeReasonDao;
	
	private InstanceFactory<CustodyChangeReason> 
		custodyChangeReasonInstanceFactory;
	
	/**
	 * Instantiates a custody change reason service implementation with the
	 * specified data access object.
	 * 
	 * @param custodyChangeReasonDao custody change reason DAO
	 */
	public CustodyChangeReasonDelegate(
			final CustodyChangeReasonDao custodyChangeReasonDao,
			final InstanceFactory<CustodyChangeReason> 
				custodyChangeReasonInstanceFactory) {
		this.custodyChangeReasonDao = custodyChangeReasonDao;
		this.custodyChangeReasonInstanceFactory 
			= custodyChangeReasonInstanceFactory;
		
	}

	/**
	 * Create new custody change reason
	 * @param name custody change reason name
	 * @return custody change reason
	 * @throws DuplicateEntityFoundException
	 */
	public CustodyChangeReason create(final String name) 
			throws DuplicateEntityFoundException {
		if (this.custodyChangeReasonDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		CustodyChangeReason custodyChangeReason 
			= custodyChangeReasonInstanceFactory.createInstance();
		custodyChangeReason.setName(name);
		custodyChangeReason.setValid(true);
		return this.custodyChangeReasonDao.makePersistent(custodyChangeReason);
	}

	/**
	 * Removes the specified custody change reason
	 * @param custodyChangeReason custody change reason
	 */
	public void remove(final CustodyChangeReason custodyChangeReason) {
		this.custodyChangeReasonDao.makeTransient(custodyChangeReason);
	}

	/**
	 * Finds the custody change reason with the specified name
	 * @param name name
	 * @return custody change reason
	 */
	public CustodyChangeReason find(final String name) {
		return this.custodyChangeReasonDao.find(name);
	}
	
	/**
	 * Finds all custody change reasons
	 * @return list of custody change reasons
	 */
	public List<CustodyChangeReason> findAll() {
		return this.custodyChangeReasonDao.findAll();
	}
}
