package omis.demographics.service.delegate;

import java.util.List;

import omis.demographics.dao.ComplexionDao;
import omis.demographics.domain.Complexion;

/**
 * ComplexionDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 10, 2016)
 *@since OMIS 3.0
 *
 */
public class ComplexionDelegate {
	
	public final ComplexionDao complexionDao;

	/**
	 * Contructor for ComplexionDelegate
	 * @param complexionDao
	 */
	public ComplexionDelegate(ComplexionDao complexionDao) {
		this.complexionDao = complexionDao;
	}
	
	/**
	 * Returns a list of all Complexions
	 * @return List of all Complexions
	 */
	public List<Complexion> findAll() {
		return this.complexionDao.findAll();
	}
	
}
