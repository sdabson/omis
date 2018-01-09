package omis.presentenceinvestigation.web.form;

import java.util.Date;

import omis.presentenceinvestigation.domain.JailAdjustmentSectionSummaryNote;

/**
 * JailAdjustmentSectionSummaryNoteItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 27, 2017)
 *@since OMIS 3.0
 *
 */
public class JailAdjustmentSectionSummaryNoteItem {
	
	private JailAdjustmentSectionSummaryNote jailAdjustmentSectionSummaryNote;
	
	private String description;
	
	private Date date;
	
	private PresentenceInvestigationItemOperation itemOperation;
	
	/**
	 * 
	 */
	public JailAdjustmentSectionSummaryNoteItem() {
	}

	/**
	 * Returns the jailAdjustmentSectionSummaryNote
	 * @return jailAdjustmentSectionSummaryNote - JailAdjustmentSectionSummaryNote
	 */
	public JailAdjustmentSectionSummaryNote getJailAdjustmentSectionSummaryNote() {
		return jailAdjustmentSectionSummaryNote;
	}

	/**
	 * Sets the jailAdjustmentSectionSummaryNote
	 * @param jailAdjustmentSectionSummaryNote - JailAdjustmentSectionSummaryNote
	 */
	public void setJailAdjustmentSectionSummaryNote(
			final JailAdjustmentSectionSummaryNote
					jailAdjustmentSectionSummaryNote) {
		this.jailAdjustmentSectionSummaryNote = jailAdjustmentSectionSummaryNote;
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
	 * @return itemOperation - JailAdjustmentSectionSummaryNoteItemOperation
	 */
	public PresentenceInvestigationItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the itemOperation
	 * @param itemOperation - JailAdjustmentSectionSummaryNoteItemOperation
	 */
	public void setItemOperation(
			final PresentenceInvestigationItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	
	
}
