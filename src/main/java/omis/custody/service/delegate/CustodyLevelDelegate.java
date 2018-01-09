package omis.custody.service.delegate;

import java.util.List;

import omis.custody.dao.CustodyLevelDao;
import omis.custody.domain.CustodyLevel;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * Custody Level Delegate
 * 
 * @author Josh Divine 
 * @version 0.1.0 (December 2, 2016)
 * @since OMIS 3.0
 */
public class CustodyLevelDelegate {
	
	private CustodyLevelDao custodyLevelDao;
	
	private InstanceFactory<CustodyLevel> custodyLevelInstanceFactory;
	
	/**
	 * Instantiates a custody level service implementation with the specified
	 * data access object.
	 * 
	 * @param custodyLevelDao custody level DAO
	 */
	public CustodyLevelDelegate(final CustodyLevelDao custodyLevelDao, 
			final InstanceFactory<CustodyLevel> custodyLevelInstanceFactory) {
		this.custodyLevelDao = custodyLevelDao;
		this.custodyLevelInstanceFactory = custodyLevelInstanceFactory;
	}


	/**
	 * Creates a custody level
	 * @param name custody level name
	 * @return custody level
	 * @throws DuplicateEntityFoundException
	 */
	public CustodyLevel create(final String name) 
			throws DuplicateEntityFoundException {
		if (this.custodyLevelDao.find(name) != null) {
			throw new DuplicateEntityFoundException("Duplicate entity found");
		}
		CustodyLevel custodyLevel = this.custodyLevelInstanceFactory
				.createInstance();
		custodyLevel.setName(name);
		custodyLevel.setValid(true);
		return this.custodyLevelDao.makePersistent(custodyLevel);
	}

	/**
	 * Removes specified custody level
	 * @param custodyLevel custody level
	 */
	public void remove(final CustodyLevel custodyLevel) {
		this.custodyLevelDao.makeTransient(custodyLevel);
	}
	
	/**
	 * Finds the custody level with the specified name
	 * @param name name
	 * @return custody level
	 */
	public CustodyLevel find(final String name) {
		return this.custodyLevelDao.find(name);
	}
	
	/**
	 * Finds all custody levels
	 * @return list of custody levels
	 */
	public List<CustodyLevel> findAll() {
		return this.custodyLevelDao.findAll();
	}
}
