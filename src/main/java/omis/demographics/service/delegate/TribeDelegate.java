package omis.demographics.service.delegate;

import java.util.List;

import omis.demographics.dao.TribeDao;
import omis.demographics.domain.Tribe;

/**
 * TribeDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 10, 2016)
 *@since OMIS 3.0
 *
 */
public class TribeDelegate {
	
	public final TribeDao tribeDao;

	/**
	 * Contructor for TribeDelegate
	 * @param tribeDao
	 */
	public TribeDelegate(TribeDao tribeDao) {
		this.tribeDao = tribeDao;
	}
	
	/**
	 * Returns a list of all Tribes
	 * @return List of all Tribes
	 */
	public List<Tribe> findAll() {
		return this.tribeDao.findAll();
	}
	
}
