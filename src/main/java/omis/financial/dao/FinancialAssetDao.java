package omis.financial.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.financial.domain.FinancialAsset;
import omis.financial.domain.FinancialAssetCategory;
import omis.offender.domain.Offender;

/**
 * Financial Asset Dao.
 * 
 *@author Josh Divine 
 *@version 0.1.0 (November 21, 2016)
 *@since OMIS 3.0
 *
 */
public interface FinancialAssetDao extends GenericDao<FinancialAsset> {

	/**
	 * Finds a financial asset
	 * @param offender offender
	 * @param category financial asset category
	 * @param description description
	 * @param reportedDate reported date
	 * @param amount amount
	 * @return Financial asset
	 */
	FinancialAsset find(Offender offender, FinancialAssetCategory category, 
			String description, Date reportedDate, BigDecimal amount);
	
	/**
	 * Finds a financial asset excluding the specified financial asset
	 * @param offender offender
	 * @param category financial asset category
	 * @param description description
	 * @param reportedDate reported date
	 * @param amount amount
	 * @return Financial asset
	 * @return FInancial asset
	 */
	FinancialAsset findExcluding(Offender offender, 
			FinancialAssetCategory category, String description, 
			Date reportedDate, BigDecimal amount, 
			FinancialAsset excludedFinancialAsset);
	
	/**
	 * Finds an offenders financial assets
	 * @param offender offender
	 * @return List of financial assets
	 */
	List<FinancialAsset> findByOffender(Offender offender);
	
	/**
	 * Finds the asset sum for an offender
	 * @param offender offender
	 * @return total asset amount
	 */
	BigDecimal findAssetSumByOffender(Offender offender);
	
}
