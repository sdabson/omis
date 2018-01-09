package omis.warrant.dao;

import omis.dao.GenericDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantCancellation;

/**
 * WarrantCancellationDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public interface WarrantCancellationDao extends GenericDao<WarrantCancellation> {
	
	/**
	 * Finds and returns a WarrantCancellation by specified Warrant
	 * @param warrant - Warrant
	 * @return WarrantCancellation by specified Warrant
	 */
	public WarrantCancellation find(Warrant warrant);

	/**
	 * Finds and returns a WarrantCancellation by specified Warrant excluding
	 * specified WarrantCancellation
	 * @param warrant - Warrant
	 * @param warrantCancellationExcluding - WarrantCancellation to exclude from
	 * search
	 * @return WarrantCancellation by specified Warrant excluding specified
	 * WarrantCancellation
	 */
	public WarrantCancellation findExcluding(Warrant warrant,
			WarrantCancellation warrantCancellationExcluding);
	
}
