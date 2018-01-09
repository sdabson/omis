package omis.financial.domain;

import java.math.BigDecimal;
import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.OffenderAssociable;

/**
 * Recurring deduction.
 *
 * @author Yidong Li
 * @version 0.0.1 (May 9, 2017)
 * @since OMIS 3.0
 */

public interface RecurringDeduction extends Creatable, Updatable, OffenderAssociable {
	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the recurring deduction category.
	 * 
	 * @return recurring deduction category
	 */
	RecurringDeductionCategory getCategory();
	
	/**
	 * Sets the recurring deduction category.
	 * 
	 * @param recurringDeductionCategory recurring deduction category
	 */
	void setCategory(RecurringDeductionCategory recurringDeductionCategory);
	
	/**
	 * Returns the recurring deduction frequency.
	 * 
	 * @return recurring deduction frequency
	 */
	RecurringDeductionFrequency getFrequency();
	
	/**
	 * Sets the recurring deduction frequency.
	 * 
	 * @param recurringDeductionFrequency recurring deduction frequency
	 */
	void setFrequency(RecurringDeductionFrequency recurringDeductionFrequency);
	
	/**
	 * Returns the description.
	 * 
	 * @return description
	 */
	String getDescription();
	
	/**
	 * Sets the description.
	 * 
	 * @param description description
	 */
	void setDescription(String description);
	
	/**
	 * Returns the amount.
	 * 
	 * @return amount
	 */
	BigDecimal getAmount();
	
	/**
	 * Sets the amount.
	 * 
	 * @param amount amount
	 */
	void setAmount(BigDecimal amount);
	
	/**
	 * Returns the reported date.
	 * 
	 * @return reported date
	 */
	Date getReportedDate();
	
	/**
	 * Sets the reported date.
	 * 
	 * @param reportedDate reported date
	 */
	void setReportedDate(Date reportedDate);
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);	
	
	/**
	 * Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}