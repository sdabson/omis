package omis.warrant.dao;

import omis.dao.GenericDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantRelease;

/**
 * WarrantReleaseDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public interface WarrantReleaseDao extends GenericDao<WarrantRelease> {
	
	/**
	 * Finds and returns a WarrantRelease by specified Warrant
	 * @param warrant - Warrant
	 * @return WarrantRelease found by specified Warrant
	 */
	public WarrantRelease find(Warrant warrant);
	
	/**
	 * Finds and returns a WarrantRelease by specified Warrant excluding
	 * specified WarrantRelease
	 * @param warrant - Warrant
	 * @param warrantReleaseExlcluded - WarrantRelease to exclude from search
	 * @return WarrantRelease found by specified Warrant excluding specified
	 * WarrantRelease
	 */
	public WarrantRelease findExcluding(Warrant warrant,
			WarrantRelease warrantReleaseExlcluded);
	
}
