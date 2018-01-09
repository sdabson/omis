package omis.courtcase.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.courtcase.domain.CourtCaseNote;

public class CourtCaseNoteItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CourtCaseNote courtCaseNote;
	
	private Date date;
	
	private String value;
	
	private CourtCaseNoteItemOperation operation;
	
	/** Instantiates a default note on a court case form. */
	public CourtCaseNoteItem() {
		// Default instantiation
	}

	/**
	 * Returns the court case note.
	 * 
	 * @return court case note
	 */
	public CourtCaseNote getCourtCaseNote() {
		return courtCaseNote;
	}

	/**
	 * Sets the court case note.
	 * 
	 * @param courtCaseNote court case note
	 */
	public void setCourtCaseNote(CourtCaseNote courtCaseNote) {
		this.courtCaseNote = courtCaseNote;
	}

	/**
	 * Returns the date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * Returns the value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return value;
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
	 * Returns the operation.
	 * 
	 * @return operation
	 */
	public CourtCaseNoteItemOperation getOperation() {
		return operation;
	}

	/**
	 * Sets the operation.
	 * 
	 * @param operation operation
	 */
	public void setOperation(CourtCaseNoteItemOperation operation) {
		this.operation = operation;
	}
}
