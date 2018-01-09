package omis.financial.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import omis.dao.GenericDao;
import omis.financial.domain.RecurringDeduction;
import omis.financial.domain.RecurringDeductionCategory;
import omis.financial.domain.RecurringDeductionFrequency;
import omis.offender.domain.Offender;

/**
 * Recurring deduction dao.
 * 
 *@author Yidong Li
 *@version 0.1.0 (May 10, 2017)
 *@since OMIS 3.0
 *
 */

public interface RecurringDeductionDao extends 
	GenericDao<RecurringDeduction> {
	/**
	 * Finds a recurring deduction
	 * @param offender offender
	 * @param category recurring deduction category
	 * @param description description
	 * @param reportedDate reported date
	 * @param amount amount
	 * @param frequency recurring deduction frequency
	 * @return recurring deduction
	 */
	RecurringDeduction find(Offender offender, 
		RecurringDeductionCategory category, String description, 
		Date reportedDate, BigDecimal amount, 
		RecurringDeductionFrequency frequency);
	
	/**
	 * Finds a recurring deduction with the specified name excluding
	 * the specified recurring deduction
	 * @param category recurring deduction category
	 * @param description description
	 * @param reportedDate reported date
	 * @param amount amount
	 * @param frequency recurring deduction frequency
	 * @param excludedRecurringDeduction excluded recurring deduction
	 * @return recurring deduction
	 */
	RecurringDeduction findExcluding(RecurringDeductionCategory category, 
		String description, Date reportedDate, BigDecimal amount, 
		RecurringDeductionFrequency frequency, 
		RecurringDeduction excludedRecurringDeduction);
	
	/**
	 * Finds a recurring deduction with the specified name excluding
	 * the specified recurring deduction
	 * @param offender offender
	 * @return list of recurring deduction
	 */
	List<RecurringDeduction> findAll(Offender offender);
}
