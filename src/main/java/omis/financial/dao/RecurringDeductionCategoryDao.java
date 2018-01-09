package omis.financial.dao;

import omis.dao.GenericDao;
import omis.financial.domain.RecurringDeductionCategory;

/**
 * Recurring Deduction Category Dao.
 * 
 *@author Yidong Li
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */

public interface RecurringDeductionCategoryDao extends 
	GenericDao<RecurringDeductionCategory> {
	/**
	 * Finds a specific recurring deduction category by name
	 * @param name name
	 * @return A recurring deduction category with specified name
	 */
	RecurringDeductionCategory find(String name);
	
	/**
	 * Finds a recurring deduction category with the specified name excluding
	 * the specified recurring deduction category
	 * @param name
	 * @param recurringDeductionCategory
	 * @return
	 */
	RecurringDeductionCategory findExcluding(String name, 
		RecurringDeductionCategory recurringDeductionCategory);
}