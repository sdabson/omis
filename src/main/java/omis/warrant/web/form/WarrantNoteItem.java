package omis.warrant.web.form;

import java.util.Date;

import omis.warrant.domain.WarrantNote;

/**
 * WarrantNoteItem.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantNoteItem {
	
	private WarrantNote warrantNote;
	
	private String note;
	
	private Date date;
	
	private WarrantItemOperation itemOperation;
	
	/**
	 * 
	 */
	public WarrantNoteItem() {
	}

	/**
	 * Returns the warrantNote
	 * @return warrantNote - WarrantNote
	 */
	public WarrantNote getWarrantNote() {
		return warrantNote;
	}

	/**
	 * Sets the warrantNote
	 * @param warrantNote - WarrantNote
	 */
	public void setWarrantNote(final WarrantNote warrantNote) {
		this.warrantNote = warrantNote;
	}

	/**
	 * Returns the note
	 * @return note - String
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Sets the note
	 * @param note - String
	 */
	public void setNote(final String note) {
		this.note = note;
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
	 * @return itemOperation - WarrantItemOperation
	 */
	public WarrantItemOperation getItemOperation() {
		return itemOperation;
	}

	/**
	 * Sets the itemOperation
	 * @param itemOperation - WarrantItemOperation
	 */
	public void setItemOperation(final WarrantItemOperation itemOperation) {
		this.itemOperation = itemOperation;
	}
	
	
	
}
