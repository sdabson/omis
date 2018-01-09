package omis.custody.dao;

import omis.custody.domain.CustodyLevel;
import omis.custody.domain.CustodyOverride;
import omis.custody.domain.CustodyReview;
import omis.dao.GenericDao;

/**
 * Database access objects for custody override.
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 18 2013)
 * @since OMIS 3.0
 */
public interface CustodyOverrideDao extends GenericDao<CustodyOverride> {

	/**
	 * Returns the override for the specified custody review.
	 * @param custodyReview custody review
	 * @return the custody override
	 */
	CustodyOverride findByReview(final CustodyReview custodyReview);
	
	/**
	 * Finds custody override for the specified custody review and level
	 * @param custodyReview custody review
	 * @param custodyLevel custody level
	 * @return custody override
	 */
	CustodyOverride find(CustodyReview custodyReview, CustodyLevel custodyLevel);
	
	/**
	 * Finds custody override for the specified custody review and level 
	 * excluding the specified custody override
	 * @param custodyReview custody review
	 * @param custodyLevel custody level
	 * @param excludedCustodyOverride custody override to exclude
	 * @return custody override
	 */
	CustodyOverride findExcluding(CustodyReview custodyReview, 
			CustodyLevel custodyLevel, CustodyOverride excludedCustodyOverride);
}