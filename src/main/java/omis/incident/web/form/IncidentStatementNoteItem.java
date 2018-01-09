package omis.incident.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.incident.domain.IncidentStatementNote;

/**
 * Incident report note item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sep 7, 2015)
 * @since OMIS 3.0
 */
public class IncidentStatementNoteItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String value;
	
	private Date date;
	
	private IncidentStatementNote note;
	
	private IncidentStatementNoteItemOperation operation;
	
	/**
	 * Instantiates a default instance of incident report note item.
	 */
	public IncidentStatementNoteItem() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of incident report note item with the specified
	 * value, date, and note.
	 * 
	 * @param value note value
	 * @param date date
	 * @param note incident report note
	 * @param operation incident report note item operation
	 */
	public IncidentStatementNoteItem(final String value, final Date date,
			final IncidentStatementNote note,
			final IncidentStatementNoteItemOperation operation) {
		this.value = value;
		this.date = date;
		this.note = note;
		this.operation = operation;
	}

	/**
	 * Returns the value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value value
	 */
	public void setValue(final String value) {
		this.value = value;
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
	 * Returns the incident report note.
	 * 
	 * @return incident report note
	 */
	public IncidentStatementNote getNote() {
		return this.note;
	}

	/**
	 * Sets the incident report note.
	 * 
	 * @param note incident report note
	 */
	public void setNote(final IncidentStatementNote note) {
		this.note = note;
	}

	/**
	 * Returns the incident report note item operation.
	 * 
	 * @return incident report note item operation
	 */
	public IncidentStatementNoteItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the incident report note item operation.
	 * 
	 * @param operation incident report note item operation
	 */
	public void setOperation(final IncidentStatementNoteItemOperation operation) {
		this.operation = operation;
	}
}