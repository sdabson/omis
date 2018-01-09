package omis.adaaccommodation.web.form;

import java.util.Date;

import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.AccommodationNote;

/**
 * Accommodation note item.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 27, 2015)
 * @since OMIS 3.0
 */
public class AccommodationNoteItem {
	
	private Date date;
	
	private String text;
	
	private AccommodationNote accommodationNote;
	
	private AccommodationNoteItemOperation operation;
	
	private Long accommodationNoteId;
	
	private Accommodation accommodation;
	
	/** Instantiates a default instance of the accommodation note form. */
	public AccommodationNoteItem() {
		//Default constructor.
	}

	/**
	 * Return the date of the accommodation note item.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date of the accommodation note item.
	 *
	 * @param date the date to set
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns the text of the accommodation note item.
	 *
	 * @return the text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Sets the text of the accommodation note item.
	 *
	 * @param text the text to set
	 */
	public void setText(final String text) {
		this.text = text;
	}

	/**
	 * Returns the accommodation note of the accommodation note item.
	 *
	 * @return the accommodationNote
	 */
	public AccommodationNote getAccommodationNote() {
		return this.accommodationNote;
	}

	/**
	 * Sets the accommodation note of the accommodation note item.
	 *
	 * @param accommodationNote the accommodationNote to set
	 */
	public void setAccommodationNote(
			final AccommodationNote accommodationNote) {
		this.accommodationNote = accommodationNote;
	}

	/**
	 * Returns the operation of the accommodation note.
	 *
	 * @return the operation
	 */
	public AccommodationNoteItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the operation of the accommodation note.
	 *
	 * @param operation the operation to set
	 */
	public void setOperation(final AccommodationNoteItemOperation operation) {
		this.operation = operation;
	}

	/**
	 * Returns the accommodation note id of the accommodation note item.
	 *
	 * @return the accommodationNoteId
	 */
	public Long getAccommodationNoteId() {
		return this.accommodationNoteId;
	}

	/**
	 * Sets the accommodation note id of the accommodation note item.
	 *
	 * @param accommodationNoteId the accommodationNoteId to set
	 */
	public void setAccommodationNoteId(final Long accommodationNoteId) {
		this.accommodationNoteId = accommodationNoteId;
	}

	/**
	 * Returns the accommodation of the accommodation note item.
	 *
	 * @return the accommodation
	 */
	public Accommodation getAccommodation() {
		return this.accommodation;
	}

	/**
	 * Sets the accommodation of the accommodation note item.
	 *
	 * @param accommodation the accommodation to set
	 */
	public void setAccommodation(final Accommodation accommodation) {
		this.accommodation = accommodation;
	}
}