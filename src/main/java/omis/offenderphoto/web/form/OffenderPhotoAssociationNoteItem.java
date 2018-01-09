package omis.offenderphoto.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.offenderphoto.domain.OffenderPhotoAssociationNote;

/**
 * Offender photo association note item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Dec 15, 2016)
 * @since OMIS 3.0
 */
public class OffenderPhotoAssociationNoteItem 
	implements Serializable {

	private static final long serialVersionUID = 1L;
	private OffenderPhotoAssociationNote note;
	private String value;
	private Date date;
	private OffenderPhotoAssociationNoteItemOperation operation;
	
	/**
	 * Instantiates a default instance of offender photo association note item.
	 */
	public OffenderPhotoAssociationNoteItem() {
		//Default constructor.
	}
	
	/**
	 * Instantiates an instance of offender photo association note item with the
	 * specified values.
	 * 
	 * @param note
	 * @param value
	 * @param date
	 * @param operation
	 */
	public OffenderPhotoAssociationNoteItem(
			final OffenderPhotoAssociationNote note,
			final String value, final Date date,
			final OffenderPhotoAssociationNoteItemOperation operation) {
		this.note = note;
		this.value = value;
		this.date = date;
		this.operation = operation;
	}

	/**
	 * Returns the offender photo association note.
	 * 
	 * @return offender photo association note
	 */
	public OffenderPhotoAssociationNote getNote() {
		return this.note;
	}

	/**
	 * Sets the offender photo association note.
	 * 
	 * @param note note
	 */
	public void setNote(final OffenderPhotoAssociationNote note) {
		this.note = note;
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
	public void setValue(String value) {
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
	 * Returns the offender photo association note item operation.
	 * 
	 * @return offender photo association note item operation
	 */
	public OffenderPhotoAssociationNoteItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the offender photo association note item operation.
	 * 
	 * @param operation offender photo association note item operation
	 */
	public void setOperation(
			final OffenderPhotoAssociationNoteItemOperation operation) {
		this.operation = operation;
	}
}