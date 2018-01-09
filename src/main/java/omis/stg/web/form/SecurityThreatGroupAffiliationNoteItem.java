package omis.stg.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.stg.domain.SecurityThreatGroupAffiliationNote;

/**
 * Security threat group affiliation note item.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Nov 17, 2016)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupAffiliationNoteItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private SecurityThreatGroupAffiliationNote affiliationNote;
	
	private Date date;
	
	private String note;
	
	private SecurityThreatGroupAffiliationNoteItemOperation operation;
	
	/**
	 * Instantiates a default instance of security threat group affiliation note item.
	 */
	public SecurityThreatGroupAffiliationNoteItem() {
		//Default constructor.
	}
	
	/**
	 * Returns the security threat group affiliation note.
	 * 
	 * @return security threat group affiliation note
	 */
	public SecurityThreatGroupAffiliationNote getAffiliationNote() {
		return this.affiliationNote;
	}
	
	/**
	 * Sets the security threat group affiliation note.
	 * 
	 * @param affiliationNote security threat group affiliation note
	 */
	public void setAffiliationNote(
			final SecurityThreatGroupAffiliationNote affiliationNote) {
		this.affiliationNote = affiliationNote;
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
	 * Returns the security threat group affiliation note item operation.
	 * 
	 * @return security threat group affiliation note item operation
	 */
	public SecurityThreatGroupAffiliationNoteItemOperation getOperation() {
		return this.operation;
	}

	/**
	 * Sets the security threat group affiliation note item operation.
	 * 
	 * @param operation security threat group affiliation note item operation
	 */
	public void setOperation(
			final SecurityThreatGroupAffiliationNoteItemOperation operation) {
		this.operation = operation;
	}

}
