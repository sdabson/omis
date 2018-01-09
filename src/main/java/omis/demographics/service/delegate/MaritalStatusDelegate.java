package omis.demographics.service.delegate;

import java.util.List;

import omis.demographics.dao.MaritalStatusDao;
import omis.demographics.domain.MaritalStatus;

/**
 * MaritalStatusDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 10, 2016)
 *@since OMIS 3.0
 *
 */
public class MaritalStatusDelegate {
	
	public final MaritalStatusDao maritalStatusDao;

	/**
	 * Contructor for MaritalStatusDelegate
	 * @param maritalStatusDao
	 */
	public MaritalStatusDelegate(MaritalStatusDao maritalStatusDao) {
		this.maritalStatusDao = maritalStatusDao;
	}
	
	/**
	 * Returns a list of all MaritalStatuss
	 * @return List of all MaritalStatuss
	 */
	public List<MaritalStatus> findAll() {
		return this.maritalStatusDao.findAll();
	}
	
}
