package omis.visitation.web.form;

import java.util.Date;

import omis.visitation.domain.VisitMethod;
import omis.visitation.domain.VisitationAssociation;

/**
 * Visit Form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 19, 2013)
 * @since OMIS 3.0
 */
public class VisitForm {
	
	private VisitationAssociation visitationAssociation;
	
	private Date date;
	
	private Date startTime;
	
	private Date endTime;
	
	private String badgeNumber;
	
	private Boolean deniedByStaff;
	
	private Boolean refusedByOffender;
	
	private VisitMethod visitMethod;
	
	private String notes;
	
	/** Instantiates a default instance of visit form. */
	public VisitForm() {
		//Default constructor.
	}

	/**
	 * Return the visitation association of the visit form.
	 * @return visitation association
	 */
	public VisitationAssociation getVisitationAssociation() {
		return this.visitationAssociation;
	}

	/**
	 * Set the visitation association of the visit form.
	 * @param visitationAssociation the visitationAssociation to set
	 */
	public void setVisitationAssociation(
			final VisitationAssociation visitationAssociation) {
		this.visitationAssociation = visitationAssociation;
	}

	/**
	 * Return the date of the visit form.
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Set the date of the visit form.
	 * @param date date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Return the start date of the visit form.
	 * @return the startTime
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	/**
	 * Set the start date of the visit form.
	 * @param startTime start time
	 */
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * Return the end date of the visit form.
	 * @return the endTime
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * Set the end date of the visit form.
	 * @param endTime end time
	 */
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * Return the badge number of the visit form.
	 * @return badge number
	 */
	public String getBadgeNumber() {
		return this.badgeNumber;
	}

	/**
	 * Set the badge number of the visit form.
	 * @param badgeNumber badge number
	 */
	public void setBadgeNumber(final String badgeNumber) {
		this.badgeNumber = badgeNumber;
	}

	/**
	 * Returns whether denied by staff applies.
	 * 
	 * @return denied by staff
	 */
	public Boolean getDeniedByStaff() {
		return this.deniedByStaff;
	}

	/**
	 * Sets whether denied by staff applies.
	 * 
	 * @param deniedByStaff denied by staff
	 */
	public void setDeniedByStaff(final Boolean deniedByStaff) {
		this.deniedByStaff = deniedByStaff;
	}

	/**
	 * Returns whether refused by offender applies.
	 * 
	 * @return refused by offender
	 */
	public Boolean getRefusedByOffender() {
		return this.refusedByOffender;
	}

	/**
	 * Sets whether refused by offender.
	 * 
	 * @param refusedByOffender refused by offender
	 */
	public void setRefusedByOffender(final Boolean refusedByOffender) {
		this.refusedByOffender = refusedByOffender;
	}

	/**
	 * Returns the visit method.
	 * 
	 * @return sets the visit method
	 */
	public VisitMethod getVisitMethod() {
		return this.visitMethod;
	}

	/**
	 * Sets the visit method.
	 * 
	 * @param visitMethod visit method
	 */
	public void setVisitMethod(final VisitMethod visitMethod) {
		this.visitMethod = visitMethod;
	}

	/**
	 * Returns notes.
	 * 
	 * @return notes
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * Sets notes.
	 * 
	 * @param notes notes
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
	}
}