package omis.financial.web.form;

import java.io.Serializable;
import java.util.List;

import omis.document.web.form.DocumentTagItem;

/**
 * Financial Profile Form.
 * 
 *@author Josh Divine 
 *@version 0.1.0 (November 21, 2016)
 *@since OMIS 3.0
 *
 */
public class FinancialProfileForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<FinancialAssetItem> financialAssetItems;
	
	private List<FinancialLiabilityItem> financialLiabilityItems;
	
	private List<RecurringDeductionItem> recurringDeductionItems;
	
	private List<FinancialDocumentItem> financialDocumentAssociationItems;
	
	private List<DocumentTagItem> documentTagItems;
	
	/**
	 * Constructor
	 */
	public FinancialProfileForm() {
		
	}

	/**
	 * @return the financial asset items
	 */
	public List<FinancialAssetItem> getFinancialAssetItems() {
		return this.financialAssetItems;
	}

	/**
	 * @param financialAssetItems the financial asset items to set
	 */
	public void setFinancialAssetItems(final List<FinancialAssetItem> 
		financialAssetItems) {
		this.financialAssetItems = financialAssetItems;
	}

	/**
	 * @return the financial liability items
	 */
	public List<FinancialLiabilityItem> getFinancialLiabilityItems() {
		return this.financialLiabilityItems;
	}

	/**
	 * @param financialLiabilityItems the financial liability items to set
	 */
	public void setFinancialLiabilityItems(final List<FinancialLiabilityItem> 
		financialLiabilityItems) {
		this.financialLiabilityItems = financialLiabilityItems;
	}
	
	/**
	 * @return the recurring deduction items
	 */
	public List<RecurringDeductionItem> getRecurringDeductionItems() {
		return this.recurringDeductionItems;
	}

	/**
	 * @param set recurring deduction
	 */
	public void setRecurringDeductionItems(final List<RecurringDeductionItem> 
		recurringDeductionItems) {
		this.recurringDeductionItems = recurringDeductionItems;
	}

	/**
	 * @return the financialDocumentItems
	 */
	public List<FinancialDocumentItem> getFinancialDocumentAssociationItems() {
		return financialDocumentAssociationItems;
	}

	/**
	 * @param financialDocumentItems the financialDocumentItems to set
	 */
	public void setFinancialDocumentAssociationItems(List<FinancialDocumentItem> financialDocumentAssociationItems) {
		this.financialDocumentAssociationItems = financialDocumentAssociationItems;
	}
	
	/**
	 * @return the documentTagItems
	 */
	public List<DocumentTagItem> getDocumentTagItems() {
		return documentTagItems;
	}
	
	/**
	 * @param documentTagItems the documentTagItems to set
	 */
	public void setDocumentTagItems(List<DocumentTagItem> documentTagItems) {
		this.documentTagItems = documentTagItems;
	}
	
}
