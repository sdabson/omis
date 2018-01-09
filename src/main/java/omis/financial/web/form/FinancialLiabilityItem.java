package omis.financial.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.financial.domain.FinancialLiability;
import omis.financial.domain.FinancialLiabilityCategory;

/**
 * Financial Liability Item.
 * 
 *@author Josh Divine 
 *@version 0.1.0 (November 22, 2016)
 *@since OMIS 3.0
 *
 */
public class FinancialLiabilityItem implements Serializable  {

	private static final long serialVersionUID = 1L;

	private ItemOperation operation;
	
	private FinancialLiability financialLiability;
	
	private FinancialLiabilityCategory category;
	
	private String description;
	
	private Date reportedDate;
	
	private String amount;
	
	/**
	 * Constructor
	 */
	public FinancialLiabilityItem() {
		
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
	 * @return the financial liability
	 */
	public FinancialLiability getFinancialLiability() {
		return this.financialLiability;
	}

	/**
	 * @param financialLiability the financial liability to set
	 */
	public void setFinancialLiability(FinancialLiability financialLiability) {
		this.financialLiability = financialLiability;
	}

	/**
	 * @return the category
	 */
	public FinancialLiabilityCategory getCategory() {
		return this.category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(FinancialLiabilityCategory category) {
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
	
}
