package omis.detainernotification.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.detainernotification.domain.DetainerNote;

/**
 * Detainer note item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (May 16, 2017)
 * @since OMIS 3.0
 */
public class DetainerNoteItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String value;
	private Date date;
	private DetainerNote note;
	private DetainerNoteItemOperation operation;
	
	/**
	 * Instantiates a default instance of detainer note item.
	 */
	public DetainerNoteItem() {
		//Default constructor.
	}
	
	/**
	 * Instantiates a populated instance of detainer note item.
	 * 
	 * @param id id
	 * @param value value
	 * @param date date
	 * @param note detainer note
	 */
	public DetainerNoteItem(final Long id, final String value, final Date date,
			final DetainerNote note,
			final DetainerNoteItemOperation operation) {
		this.id = id;
		this.value = value;
		this.date = date;
		this.note = note;
		this.operation = operation;
	}

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	public void setId(final Long id) {
		this.id = id;
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
	 * Returns the detainer note.
	 * 
	 * @return detainer note
	 */
	public DetainerNote getNote() {
		return this.note;
	}

	/**
	 * Sets the detainer note.
	 * 
	 * @param note detainer note
	 */
	public void setNote(final DetainerNote note) {
		this.note = note;
	}

	/**
	 * Returns the detainer note item operation.
	 * 
	 * @return detainer note item operation
	 */
	public DetainerNoteItemOperation getOperation() {
		return operation;
	}

	/**
	 * Sets the detainer note item operation.
	 * 
	 * @param operation detainer note item operation
	 */
	public void setOperation(final DetainerNoteItemOperation operation) {
		this.operation = operation;
	}
}