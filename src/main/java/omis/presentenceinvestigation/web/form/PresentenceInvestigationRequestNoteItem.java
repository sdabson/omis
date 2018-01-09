package omis.presentenceinvestigation.web.form;

import java.util.Date;

import omis.presentenceinvestigation.domain.PresentenceInvestigationRequestNote;

/**
 * PresentenceInvestigationRequestNoteItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 16, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationRequestNoteItem {
	
	private PresentenceInvestigationRequestNote
		presentenceInvestigationRequestNote;
	
	private String description;
	
	private Date date;
	
	private PresentenceInvestigationItemOperation itemOperation;
	
	/**
	 * 
	 */
	public PresentenceInvestigationRequestNoteItem() {
	}

	/**
	 * Returns the presentenceInvestigationRequestNote
	 * @return presentenceInvestigationRequestNote -
	 * PresentenceInvestigationRequestNote
	 */
	public PresentenceInvestigationRequestNote
			getPresentenceInvestigationRequestNote() {
		return presentenceInvestigationRequestNote;
	}

	/**
	 * Sets the presentenceInvestigationRequestNote
	 * @param presentenceInvestigationRequestNote -
	 * PresentenceInvestigationRequestNote
	 */
	public void setPresentenceInvestigationRequestNote(
			final PresentenceInvestigationRequestNote
				presentenceInvestigationRequestNote) {
		this.presentenceInvestigationRequestNote =
				presentenceInvestigationRequestNote;
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
