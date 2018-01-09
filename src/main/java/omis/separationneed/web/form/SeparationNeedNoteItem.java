package omis.separationneed.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.separationneed.domain.SeparationNeedNote;

/**
 * Separation need note item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 20, 2015)
 * @since OMIS 3.0
 */
public class SeparationNeedNoteItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String note;
	
	private Date date;
	
	private SeparationNeedNote separationNeedNote;
	
	private SeparationNeedNoteItemOperation operation;
	
	/**
	 * Instantiates a default instance of separation need note item.
	 */
	public SeparationNeedNoteItem() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of separation need note item with the specified
	 * note, date, and separation need note.
	 * 
	 * @param note note
	 * @param date date
	 * @param separationNeedNote separation need note
	 * @param operation separation need note item operation
	 */
	public SeparationNeedNoteItem(final String note, final Date date,
			final SeparationNeedNote separationNeedNote,
			final SeparationNeedNoteItemOperation operation) {
		this.note = note;
		this.date = date;
		this.separationNeedNote = separationNeedNote;
		this.operation = operation;
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
	 * Returns the separation need note.
	 * 
	 * @return separation need note
	 */
	public SeparationNeedNote getSeparationNeedNote() {
		return this.separationNeedNote;
	}

	/**
	 * Sets the separation need note.
	 * 
	 * @param separationNeedNote separation need now
	 */
	public void setSeparationNeedNote(
			final SeparationNeedNote separationNeedNote) {
		this.separationNeedNote = separationNeedNote;
	}

	/**
	 * Returns the separation need note item operation.
	 * 
	 * @return separation need note item operation
	 */
	public SeparationNeedNoteItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the separation need note item operation.
	 * 
	 * @param operation separation need note item operation
	 */
	public void setOperation(
			final SeparationNeedNoteItemOperation operation) {
		this.operation = operation;
	}
}