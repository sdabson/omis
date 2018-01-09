package omis.presentenceinvestigation.web.form;

import java.util.Date;

import omis.presentenceinvestigation.domain.OtherPertinentInformationSectionSummaryNote;

/**
 * OtherPertinentInformationSectionSummaryNoteItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public class OtherPertinentInformationSectionSummaryNoteItem {
	
	private OtherPertinentInformationSectionSummaryNote
		otherPertinentInformationSectionSummaryNote;
	
	private String description;
	
	private Date date;
	
	private PresentenceInvestigationItemOperation itemOperation;
	
	/**
	 * 
	 */
	public OtherPertinentInformationSectionSummaryNoteItem() {
	}

	/**
	 * Returns the otherPertinentInformationSectionSummaryNote
	 * @return otherPertinentInformationSectionSummaryNote -
	 * OtherPertinentInformationSectionSummaryNote
	 */
	public OtherPertinentInformationSectionSummaryNote
			getOtherPertinentInformationSectionSummaryNote() {
		return otherPertinentInformationSectionSummaryNote;
	}

	/**
	 * Sets the otherPertinentInformationSectionSummaryNote
	 * @param otherPertinentInformationSectionSummaryNote -
	 * OtherPertinentInformationSectionSummaryNote
	 */
	public void setOtherPertinentInformationSectionSummaryNote(
			final OtherPertinentInformationSectionSummaryNote
				otherPertinentInformationSectionSummaryNote) {
		this.otherPertinentInformationSectionSummaryNote =
				otherPertinentInformationSectionSummaryNote;
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
