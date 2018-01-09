package omis.violationevent.web.form;

import java.util.Date;

import omis.violationevent.domain.ViolationEventNote;

/**
 * ViolationEventNoteItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 19, 2017)
 *@since OMIS 3.0
 *
 */
public class ViolationEventNoteItem {
	
	private ViolationEventNote violationEventNote;
	
	private Date date;
	
	private String description;
	
	private ViolationEventItemOperation itemOperation;
	
	/**
	 * 
	 */
	public ViolationEventNoteItem() {
	}
	
	/**
	 * Returns the violationEventNote
	 * @return violationEventNote - ViolationEventNote
	 */
	public ViolationEventNote getViolationEventNote() {
		return violationEventNote;
	}

	/**
	 * Sets the violationEventNote
	 * @param violationEventNote - ViolationEventNote
	 */
	public void setViolationEventNote(
			final ViolationEventNote violationEventNote) {
		this.violationEventNote = violationEventNote;
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
	 * Returns the itemOperation
	 * @return itemOperation - ViolationEventItemOperation
	 */
	public ViolationEventItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the itemOperation
	 * @param itemOperation - ViolationEventItemOperation
	 */
	public void setItemOperation(final ViolationEventItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	
	
}
