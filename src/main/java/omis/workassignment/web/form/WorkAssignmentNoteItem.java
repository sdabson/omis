package omis.workassignment.web.form;

import java.io.Serializable;
import java.util.Date;

/**
 * Work assignment note item.
 * 
 * @author Yidong Li
 * @version 0.1.0 (Sept 7, 2016)
 * @since OMIS 3.0
 */
public class WorkAssignmentNoteItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String note;
	private Date date;
	private WorkAssignmentNoteItemOperation operation;
	
	/**
	 * Instantiates a default instance of work assignment notes.
	 */
	public WorkAssignmentNoteItem() {
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
	 * @param note
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
	 * @param date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}
	
	/**
	 * Returns the operation.
	 * 
	 * @return operation
	 */
	public WorkAssignmentNoteItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date
	 */
	public void setOperation(final WorkAssignmentNoteItemOperation operation) {
		this.operation = operation;
	}
}