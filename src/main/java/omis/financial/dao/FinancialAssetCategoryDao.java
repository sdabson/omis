package omis.financial.dao;

import omis.dao.GenericDao;
import omis.financial.domain.FinancialAssetCategory;

/**
 * Financial Asset Category Dao.
 * 
 *@author Josh Divine 
 *@version 0.1.0 (November 21, 2016)
 *@since OMIS 3.0
 *
 */
public interface FinancialAssetCategoryDao extends 
	GenericDao<FinancialAssetCategory> {

	/**
	 * Finds a financial asset category with the specified name
	 * @param name financial asset category name
	 * @return financial asset category
	 */
	FinancialAssetCategory find(String name);
	
	/**
	 * Finds a financial asset category with the specified name excluding
	 * the specified financial asset category
	 * @param name
	 * @param financialAssetCategory
	 * @return
	 */
	FinancialAssetCategory findExcluding(String name, 
			FinancialAssetCategory financialAssetCategory);
	
}
