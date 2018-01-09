package omis.financial.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.financial.domain.RecurringDeduction;
import omis.financial.domain.RecurringDeductionCategory;
import omis.financial.domain.RecurringDeductionFrequency;

/**
 * Recurring deduction item.
 * 
 *@author Yidong Li
 *@version 0.1.0 (May 11, 2017)
 *@since OMIS 3.0
 *
 */
public class RecurringDeductionItem implements Serializable  {

	private static final long serialVersionUID = 1L;

	private ItemOperation operation;
	
	private RecurringDeduction recurringDeduction;
	
	private RecurringDeductionCategory category;
	
	private String description;
	
	private Date reportedDate;
	
	private String amount;
	
	private RecurringDeductionFrequency frequency;
	
	/**
	 * Constructor
	 */
	public RecurringDeductionItem() {
		
	}

	/**
	 * @return the operation
	 */
	public ItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(ItemOperation operation) {
		this.operation = operation;
	}

	/**
	 * @return the recurring deduction
	 */
	public RecurringDeduction getRecurringDeduction() {
		return this.recurringDeduction;
	}

	/**
	 * @param set recurring deduction
	 */
	public void setRecurringDeduction(RecurringDeduction recurringDeduction) {
		this.recurringDeduction = recurringDeduction;
	}

	/**
	 * @return the category
	 */
	public RecurringDeductionCategory getCategory() {
		return this.category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(RecurringDeductionCategory category) {
		this.category = category;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the reportedDate
	 */
	public Date getReportedDate() {
		return this.reportedDate;
	}

	/**
	 * @param reportedDate the reportedDate to set
	 */
	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}

	/**
	 * @return the amount
	 */
	public String getAmount() {
		return this.amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	/**
	 * @return the frequency
	 */
	public RecurringDeductionFrequency getFrequency() {
		return this.frequency;
	}

	/**
	 * @param set the frequency
	 */
	public void setFrequency(RecurringDeductionFrequency frequency) {
		this.frequency = frequency;
	}
}
