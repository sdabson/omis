package omis.presentenceinvestigation.web.form;

import java.util.ArrayList;
import java.util.List;


/**
 * FinancialSectionSummaryForm.java
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (July 14, 2017)
 *@since OMIS 3.0
 *
 */

public class FinancialSectionSummaryForm {
	
	private String text;
	
	private List<FinancialSectionSummaryNoteItem> 
		financialSectionSummaryNoteItems
		= new ArrayList<FinancialSectionSummaryNoteItem>();
	
	private List<FinancialSectionSummaryDocumentAssociationItem> 
		financialDocumentAssociationItems
		= new ArrayList<FinancialSectionSummaryDocumentAssociationItem>();
	
	/** Instantiates an implementation of FinancialSectionSummaryForm */
	public FinancialSectionSummaryForm() {
		// Default constructor
	}
	
	/**
	 * Returns the text
	 * @return text - String
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets the text
	 * @param text - String
	 */
	public void setText(final String text) {
		this.text = text;
	}

	/**
	 * Returns the financialSectionSummaryDocumentAssociationItems
	 * @return financialSectionSummaryDocumentAssociationItems -
	 * List<FinancialSectionSummaryDocumentAssociationItem>
	 */
	public List<FinancialSectionSummaryDocumentAssociationItem>
			getFinancialSectionSummaryDocumentAssociationItems() {
		return financialDocumentAssociationItems;
	}

	/**
	 * Sets the financialSectionSummaryDocumentAssociationItems
	 * @param financialSectionSummaryDocumentAssociationItems -
	 * List<FinancialDocumentItem>
	 */
	public void setFinancialSectionSummaryDocumentAssociationItems(
			final List<FinancialSectionSummaryDocumentAssociationItem>
				financialSectionSummaryDocumentAssociationItems) {
		this.financialDocumentAssociationItems =
				financialSectionSummaryDocumentAssociationItems;
	}
	
	/**
	 * Returns the financialSectionSummaryNoteItems
	 * @return financialSectionSummaryNoteItems -
	 * List<FinancialSectionSummaryNoteItem>
	 */
	public List<FinancialSectionSummaryNoteItem> 
			getFinancialSectionSummaryNoteItems() {
		return financialSectionSummaryNoteItems;
	}
	
	/**
	 * Sets the financialSectionSummaryNoteItems
	 * @param financialSectionSummaryNoteItems -
	 * List<FinancialSectionSummaryNoteItem>
	 */
	public void setFinancialSectionSummaryNoteItems(
			final List<FinancialSectionSummaryNoteItem>
				financialSectionSummaryNoteItems) {
		this.financialSectionSummaryNoteItems 
			= financialSectionSummaryNoteItems;
	}

}
