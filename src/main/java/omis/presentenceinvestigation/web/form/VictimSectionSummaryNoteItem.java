package omis.presentenceinvestigation.web.form;

import java.util.Date;

import omis.presentenceinvestigation.domain.VictimSectionSummaryNote;

/**
 * VictimSectionSummaryNoteItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 20, 2017)
 *@since OMIS 3.0
 *
 */
public class VictimSectionSummaryNoteItem {
	
	private VictimSectionSummaryNote victimSectionSummaryNote;
	
	private String description;
	
	private Date date;
	
	private PresentenceInvestigationItemOperation itemOperation;
	
	/**
	 * 
	 */
	public VictimSectionSummaryNoteItem() {
	}

	/**
	 * Returns the victimSectionSummaryNote
	 * @return victimSectionSummaryNote - VictimSectionSummaryNote
	 */
	public VictimSectionSummaryNote getVictimSectionSummaryNote() {
		return victimSectionSummaryNote;
	}

	/**
	 * Sets the victimSectionSummaryNote
	 * @param victimSectionSummaryNote - VictimSectionSummaryNote
	 */
	public void setVictimSectionSummaryNote(
			final VictimSectionSummaryNote victimSectionSummaryNote) {
		this.victimSectionSummaryNote = victimSectionSummaryNote;
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
