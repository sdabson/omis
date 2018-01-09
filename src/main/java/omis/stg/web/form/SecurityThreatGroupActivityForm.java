package omis.stg.web.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import omis.person.domain.Person;
import omis.stg.domain.SecurityThreatGroupActivity;

/**
 * Form for security threat group activities.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Dec 15, 2016)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupActivityForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<SecurityThreatGroupActivityInvolvementItem> involvementItems;
	
	private List<SecurityThreatGroupActivityNoteItem> noteItems;
	
	private SecurityThreatGroupActivity activity;
	
	private Person reportedBy;
	
	private Date reportDate;
	
	private String summary;
	
	/** Instantiates a form for security threat group activities. */
	public SecurityThreatGroupActivityForm() {
		// Default instantiation
	}
		
	/**
	 * Return involvement items
	 * 
	 * @return involvement items
	 */
	public List<SecurityThreatGroupActivityInvolvementItem> getInvolvementItems() {
		return involvementItems;
	}

	/**
	 * Sets involvement items
	 * 
	 * @param involvementItems
	 */
	public void setInvolvementItems(List<SecurityThreatGroupActivityInvolvementItem> 
		involvementItems) {
		this.involvementItems = involvementItems;
	}

	/**
	 * Returns activity notes
	 * @return note items
	 */
	public List<SecurityThreatGroupActivityNoteItem> getNoteItems() {
		return noteItems;
	}

	/**
	 * Sets activity notes
	 * 
	 * @param noteItems
	 */
	public void setNoteItems(List<SecurityThreatGroupActivityNoteItem> noteItems) {
		this.noteItems = noteItems;
	}

	/**
	 * Returns the person reporting.
	 * 
	 * @return reported by
	 */
	public Person getReportedBy() {
		return this.reportedBy;
	}
	
	/**
	 * Sets the person reporting.
	 * 
	 * @return reported by
	 */
	public void setReportedBy(final Person reportedBy) {
		this.reportedBy = reportedBy;
	}
	
	/**
	 * Returns the report date.
	 * 
	 * @return report date
	 */
	public Date getReportDate() {
		return this.reportDate;
	}
	
	/**
	 * Sets the report date.
	 * 
	 * @return report date
	 */
	public void setReportDate(final Date reportDate) {
		this.reportDate = reportDate;
	}
	
	/**
	 * Returns the activity summary.
	 * 
	 * @return activity summary
	 */
	public String getSummary() {
		return this.summary;
	}
	
	/**
	 * Sets the activity summary.
	 * 
	 * @return activity summary
	 */
	public void setSummary(final String summary) {
		this.summary = summary;
	}

	/**
	 * @return the activity
	 */
	public SecurityThreatGroupActivity getActivity() {
		return this.activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(SecurityThreatGroupActivity activity) {
		this.activity = activity;
	}
	
}
