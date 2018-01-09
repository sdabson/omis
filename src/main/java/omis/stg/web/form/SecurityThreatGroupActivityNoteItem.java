package omis.stg.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.stg.domain.SecurityThreatGroupActivityNote;

public class SecurityThreatGroupActivityNoteItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private SecurityThreatGroupActivityNote activityNote;
	
	private Date date;
	
	private String value;
	
	private SecurityThreatGroupActivityNoteItemOperation operation;
	
	public SecurityThreatGroupActivityNoteItemOperation getOperation() {
		return operation;
	}

	public void setOperation(SecurityThreatGroupActivityNoteItemOperation operation) {
		this.operation = operation;
	}

	/**
	 * Instantiates a default instance of security threat group activity 
	 * involvement item.
	 */
	public SecurityThreatGroupActivityNoteItem() {
		//Default constructor.
	}

	/** Returns the security threat group activity note.
	 * @return activityNote
	 */
	public SecurityThreatGroupActivityNote getActivityNote() {
		return activityNote;
	}

	/** Sets the security threat group activity note.
	 * @param activityNote
	 */
	public void setActivityNote(SecurityThreatGroupActivityNote activityNote) {
		this.activityNote = activityNote;
	}

	/** Returns the date.
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/** Sets the date.
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/** Returns the security threat group activity value.
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/** Sets the security threat group activity value.
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
