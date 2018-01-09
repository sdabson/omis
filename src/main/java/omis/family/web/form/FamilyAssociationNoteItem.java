package omis.family.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * Family association notes.
 * 
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jun 30, 2015)
 * @since OMIS 3.0
 */
public class FamilyAssociationNoteItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String note;
	private Date date;
	private FamilyAssociationNoteItemOperation operation;
	
	/**
	 * Instantiates a default instance of family association notes.
	 */
	public FamilyAssociationNoteItem() {
		//Default constructor.
	}

	/**
	 * Returns the note.
	 * 
	 * @return note
	 */
	public String getNote() {
		return this.note;
	}

	/**
	 * Sets the note.
	 * 
	 * @param note note
	 */
	public void setNote(final String note) {
		this.note = note;
	}

	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}
	
	/**
	 * Returns the operation.
	 * 
	 * @return operation
	 */
	public FamilyAssociationNoteItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the operation.
	 * 
	 * @param operation operation
	 */
	public void setOperation(
			final FamilyAssociationNoteItemOperation operation) {
		this.operation = operation;
	}
}