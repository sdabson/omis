package omis.term.service.delegate;

import java.util.Date;

import omis.term.domain.component.Term;

/**
 * Calculates a term.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 11, 2013)
 * @since OMIS 3.0
 */
public interface TermCalculatorDelegate {

	/**
	 * Calculates a term end date.
	 * 
	 * @param term term
	 * @param commencementDate commencement date
	 * @return end date of term starting on commencement date
	 */
	Date calculate(Term term, Date commencementDate);
	
	/**
	 * Calculates a term end date.
	 * 
	 * @param years term years
	 * @param months term months
	 * @param days term days
	 * @param commencementDate commencement date
	 * @return end date of term starting on commencement date
	 */
	Date calculate(int years, int months, int days, Date commencementDate);
	
	/**
	 * Adds years to a commencement date.
	 * 
	 * @param years years to add
	 * @param commencementDate commencement date
	 * @return date the specified number of years after the commencement date
	 */
	Date addYears(int years, Date commencementDate);
	
	/**
	 * Adds months to a commencement date.
	 * 
	 * @param months months to add
	 * @param commencementDate commencement date
	 * @return date the specified number of months after the commencement date 
	 */
	Date addMonths(int months, Date commencementDate);
	
	/**
	 * Adds days to commencement date.
	 * 
	 * @param days days to add
	 * @param commencementDate commencement date
	 * @return date the specified number of days after the commencement date
	 */
	Date addDays(int days, Date commencementDate);
	
	/**
	 * Calculates totals days of term.
	 * 
	 * <p>A {@code null} value will be assumed to mean {@code 0} for the term
	 * years, months or days.
	 * 
	 * @param term term
	 * @return total days of term
	 */
	int calculateTotalDays(Term term);
}