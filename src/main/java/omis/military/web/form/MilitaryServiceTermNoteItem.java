package omis.military.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.military.domain.MilitaryServiceTermNote;

/**
 * Military service term note item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 19, 2015)
 * @since OMIS 3.0
 */
public class MilitaryServiceTermNoteItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private MilitaryServiceTermNote serviceTermNote;
	
	private Date date;
	
	private String note;
	
	private MilitaryServiceTermNoteItemOperation operation;
	
	/**
	 * Instantiates a default instance of military service term note item.
	 */
	public MilitaryServiceTermNoteItem() {
		//Default constructor.
	}
	
	/**
	 * Returns the military service term note.
	 * 
	 * @return military service term note
	 */
	public MilitaryServiceTermNote getServiceTermNote() {
		return this.serviceTermNote;
	}
	
	/**
	 * Sets the military service term note.
	 * 
	 * @param serviceTermNote military service term note
	 */
	public void setServiceTermNote(
			final MilitaryServiceTermNote serviceTermNote) {
		this.serviceTermNote = serviceTermNote;
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
	 * Returns the military service term note item operation.
	 * 
	 * @return military service term note item operation
	 */
	public MilitaryServiceTermNoteItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the military service term note item operation.
	 * 
	 * @param operation military service term note item operation
	 */
	public void setOperation(
			final MilitaryServiceTermNoteItemOperation operation) {
		this.operation = operation;
	}
}