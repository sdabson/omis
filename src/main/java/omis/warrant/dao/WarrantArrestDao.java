package omis.warrant.dao;

import omis.dao.GenericDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantArrest;

/**
 * WarrantArrestDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public interface WarrantArrestDao extends GenericDao<WarrantArrest> {
	
	/**
	 * Finds and returns a WarrantArrest with specified Warrant
	 * @param warrant - Warrant
	 * @return WarrantArrest with specified Warrant
	 */
	public WarrantArrest find(Warrant warrant);
	
	/**
	 * Finds and returns a WarrantArrest with specified Warrant excluding
	 * specified WarrantArrest
	 * @param warrant - Warrant
	 * @param warrantArrestExcluded - WarrantArrest to exclude from search
	 * @return WarrantArrest with specified Warrant excluding
	 * specified WarrantArrest
	 */
	public WarrantArrest findExcluding(Warrant warrant,
			WarrantArrest warrantArrestExcluded);
	
}
