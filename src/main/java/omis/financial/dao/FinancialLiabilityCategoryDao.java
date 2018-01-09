package omis.financial.dao;

import omis.dao.GenericDao;
import omis.financial.domain.FinancialLiabilityCategory;

/**
 * Financial Liability Category Dao.
 * 
 *@author Josh Divine 
 *@version 0.1.0 (November 21, 2016)
 *@since OMIS 3.0
 *
 */
public interface FinancialLiabilityCategoryDao extends 
	GenericDao<FinancialLiabilityCategory> {

	/**
	 * Finds a financial liability category with the specified name
	 * @param name financial liability category name
	 * @return financial liability category
	 */
	FinancialLiabilityCategory find(String name);
	
	/**
	 * Finds a financial liability category with the specified name excluding
	 * the specified financial liability category
	 * @param name
	 * @param financialLiabilityCategory
	 * @return
	 */
	FinancialLiabilityCategory findExcluding(String name, 
			FinancialLiabilityCategory financialLiabilityCategory);
}
