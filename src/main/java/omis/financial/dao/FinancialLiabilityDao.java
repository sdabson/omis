package omis.financial.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.financial.domain.FinancialLiability;
import omis.financial.domain.FinancialLiabilityCategory;
import omis.offender.domain.Offender;

/**
 * Financial Liability Dao.
 * 
 *@author Josh Divine 
 *@version 0.1.0 (November 21, 2016)
 *@since OMIS 3.0
 *
 */
public interface FinancialLiabilityDao extends GenericDao<FinancialLiability> {

	/**
	 * Finds a financial liability
	 * @param offender offender
	 * @param category financial liability category
	 * @param description description
	 * @param reportedDate reported date
	 * @param amount amount
	 * @return Financial liability
	 */
	FinancialLiability find(Offender offender, 
			FinancialLiabilityCategory category, String description, 
			Date reportedDate, BigDecimal amount);
	
	/**
	 * Finds a financial liability excluding the specified financial liability
	 * @param offender offender
	 * @param category financial liability category
	 * @param description description
	 * @param reportedDate reported date
	 * @param amount amount
	 * @param excludedFinancialLiability excluded financial liability
	 * @return Financial liability
	 */
	FinancialLiability findExcluding(Offender offender, 
			FinancialLiabilityCategory category, String description, 
			Date reportedDate, BigDecimal amount, 
			FinancialLiability excludedFinancialLiability);
	
	/**
	 * Finds an offenders financial liabilities
	 * @param offender offender
	 * @return List of financial liabilities
	 */
	List<FinancialLiability> findByOffender(Offender offender);
	
	/**
	 * Finds the liability sum for an offender
	 * @param offender offender
	 * @return total asset amount
	 */
	BigDecimal findLiabilitySumByOffender(Offender offender);
}
