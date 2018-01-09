package omis.health.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.health.domain.ExternalReferralStatusReason;
import omis.health.domain.ProviderAssignment;

/**
 * Form to schedule an authorized external referral.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 2, 2014)
 * @since OMIS 3.0
 */
public class ScheduleAuthorizedExternalReferralForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date date;
	
	private Date time;
	
	private ProviderAssignment providerAssignment;
	
	private String schedulingNotes;
	
	private boolean statusReasonRequired;
	
	private ExternalReferralStatusReason statusReason;
	
	/** Instantiates a form to schedule an authorized external referral. */
	public ScheduleAuthorizedExternalReferralForm() {
		// Default instantiation
	}

	/**
	 * Returns the appointment date.
	 * 
	 * @return appointment date
	 */
	public Date getDate() {
		return this.date;
	}

	/**
	 * Sets the appointment date.
	 * 
	 * @param date appointment date
	 */
	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * Returns time.
	 * 
	 * @return time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * Sets time.
	 * 
	 * @param time time
	 */
	public void setTime(final Date time) {
		this.time = time;
	}

	/**
	 * Returns provider assignment.
	 * 
	 * @return provider assignment
	 */
	public ProviderAssignment getProviderAssignment() {
		return this.providerAssignment;
	}

	/**
	 * Sets provider assignment.
	 * 
	 * @param providerAssignment provider assignment
	 */
	public void setProviderAssignment(
			final ProviderAssignment providerAssignment) {
		this.providerAssignment = providerAssignment;
	}

	/**
	 * Returns the scheduling notes.
	 * 
	 * @return scheduling notes
	 */
	public String getSchedulingNotes() {
		return this.schedulingNotes;
	}

	/**
	 * Sets the scheduling notes.
	 * 
	 * @param schedulingNotes scheduling notes
	 */
	public void setSchedulingNotes(final String schedulingNotes) {
		this.schedulingNotes = schedulingNotes;
	}

	/**
	 * Returns whether status reason is required.
	 * 
	 * @return whether status reason is required
	 */
	public boolean getStatusReasonRequired() {
		return this.statusReasonRequired;
	}

	/**
	 * Sets whether status reason is required.
	 * 
	 * @param statusReasonRequired whether status reason is required
	 */
	public void setStatusReasonRequired(final boolean statusReasonRequired) {
		this.statusReasonRequired = statusReasonRequired;
	}

	/**
	 * Returns the status reason.
	 * 
	 * @return status reason
	 */
	public ExternalReferralStatusReason getStatusReason() {
		return this.statusReason;
	}

	/**
	 * Sets the status reason.
	 * 
	 * @param statusReason status reason
	 */
	public void setStatusReason(
			final ExternalReferralStatusReason statusReason) {
		this.statusReason = statusReason;
	}
}