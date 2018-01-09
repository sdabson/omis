package omis.presentenceinvestigation.web.form;

import java.util.Date;

import omis.presentenceinvestigation.domain.FinancialSectionSummaryNote;

/**
 * FinancialSectionSummaryNoteItem
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (July 20, 2017)
 *@since OMIS 3.0
 *
 */
public class FinancialSectionSummaryNoteItem {

private FinancialSectionSummaryNote financialSectionSummaryNote;
	
	private String description;
	
	private Date date;
	
	private PresentenceInvestigationItemOperation itemOperation;
	
	/**
	 * 
	 */
	public FinancialSectionSummaryNoteItem() {
	}

	/**
	 * Returns the financialSectionSummaryNote
	 * @return financialSectionSummaryNote - FinancialSectionSummaryNote
	 */
	public FinancialSectionSummaryNote getFinancialSectionSummaryNote() {
		return financialSectionSummaryNote;
	}

	/**
	 * Sets the financialSectionSummaryNote
	 * @param financialSectionSummaryNote - FinancialSectionSummaryNote
	 */
	public void setFinancialSectionSummaryNote(
			final FinancialSectionSummaryNote financialSectionSummaryNote) {
		this.financialSectionSummaryNote = financialSectionSummaryNote;
	}

	/**
	 * Returns the description
	 * @return description - String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description
	 * @param description - String
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Returns the date
	 * @return date - Date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date
	 * @param date - Date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the itemOperation
	 * @return itemOperation - PresentenceInvestigationItemOperation
	 */
	public PresentenceInvestigationItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the itemOperation
	 * @param itemOperation - PresentenceInvestigationItemOperation
	 */
	public void setItemOperation(
			final PresentenceInvestigationItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
}
