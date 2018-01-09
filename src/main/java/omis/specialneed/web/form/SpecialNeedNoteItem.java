package omis.specialneed.web.form;

import java.util.Date;

import omis.specialneed.domain.SpecialNeed;
import omis.specialneed.domain.SpecialNeedNote;

/**
 * Special need note item.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 31, 2016)
 * @since OMIS 3.0
 */
public class SpecialNeedNoteItem {
	
	private Date date;
	
	private String value;
	
	private SpecialNeedNote specialNeedNote;
	
	private SpecialNeedNoteItemOperation operation;
	
	private SpecialNeed specialNeed;

	/** Instantiates an implementation of special need note form. */
	public SpecialNeedNoteItem() {
		// Default constructor.
	}

	/**
	 * Gets the date of the special need note.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the date of the special need note.
	 *
	 * @param date date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Gets the value of the special need note.
	 *
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets the value of the special need note.
	 *
	 * @param value value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the specialNeedNote of the special need note.
	 *
	 * @return the specialNeedNote
	 */
	public SpecialNeedNote getSpecialNeedNote() {
		return this.specialNeedNote;
	}

	/**
	 * Sets the specialNeedNote of the special need note.
	 *
	 * @param specialNeedNote special need note
	 */
	public void setSpecialNeedNote(SpecialNeedNote specialNeedNote) {
		this.specialNeedNote = specialNeedNote;
	}

	/**
	 * Gets the operation of the special need note.
	 *
	 * @return the operation
	 */
	public SpecialNeedNoteItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the operation of the special need note.
	 *
	 * @param operation operation
	 */
	public void setOperation(SpecialNeedNoteItemOperation operation) {
		this.operation = operation;
	}

	/**
	 * Gets the specialNeed of the special need note.
	 *
	 * @return the specialNeed
	 */
	public SpecialNeed getSpecialNeed() {
		return this.specialNeed;
	}

	/**
	 * Sets the specialNeed of the special need note.
	 *
	 * @param specialNeed special need
	 */
	public void setSpecialNeed(SpecialNeed specialNeed) {
		this.specialNeed = specialNeed;
	}
}