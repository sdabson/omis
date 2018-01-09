package omis.health.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import omis.health.domain.ProviderLevel;

/**
 * Form to assess internal referrals.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Apr 30, 2014)
 * @since OMIS 3.0
 */
public class AssessInternalReferralForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date time;
	
	private String notes;
	
	private ReferralType followUpReferralType;
	
	private Boolean followUpAsap;
	
	private Boolean followUpLabsRequired;
	
	private ProviderLevel followUpRequestProviderLevel;
	
	private String followUpRequestNotes;
	
	private List<LabWorkAppointmentItem> labWork
		= new ArrayList<LabWorkAppointmentItem>();
	
	/** Instantiates form to assess internal referrals. */
	public AssessInternalReferralForm() {
		// Default instantiation
	}

	/**
	 * Returns the time.
	 * 
	 * @return time
	 */
	public Date getTime() {
		return this.time;
	}

	/**
	 * Sets the time.
	 * 
	 * @param time time
	 */
	public void setTime(final Date time) {
		this.time = time;
	}

	/**
	 * Returns the notes.
	 * 
	 * @return notes
	 */
	public String getNotes() {
		return this.notes;
	}

	/**
	 * Sets the notes.
	 * 
	 * @param notes notes
	 */
	public void setNotes(final String notes) {
		this.notes = notes;
	}

	/**
	 * Returns the follow up referral type.
	 * 
	 * @return follow up referral type
	 */
	public ReferralType getFollowUpReferralType() {
		return this.followUpReferralType;
	}

	/**
	 * Sets the follow up referral type.
	 * 
	 * @param followUpReferralType follow up referral type
	 */
	public void setFollowUpReferralType(
			final ReferralType followUpReferralType) {
		this.followUpReferralType = followUpReferralType;
	}

	/**
	 * Returns whether the follow up should be ASAP.
	 * 
	 * @return whether follow up should be ASAP
	 */
	public Boolean getFollowUpAsap() {
		return this.followUpAsap;
	}

	/**
	 * Sets whether the follow up should be ASAP.
	 * 
	 * @param followUpAsap whether follow up should be ASAP
	 */
	public void setFollowUpAsap(final Boolean followUpAsap) {
		this.followUpAsap = followUpAsap;
	}

	/**
	 * Return whether follow up labs are required.
	 * 
	 * @return whether follow up labs are required
	 */
	public Boolean getFollowUpLabsRequired() {
		return this.followUpLabsRequired;
	}

	/**
	 * Sets whether follow up labs required.
	 * 
	 * @param followUpLabsRequired whether follow up labs are required
	 */
	public void setFollowUpLabsRequired(final Boolean followUpLabsRequired) {
		this.followUpLabsRequired = followUpLabsRequired;
	}

	/**
	 * Returns the follow up request provider level.
	 * 
	 * @return follow up request provider level
	 */
	public ProviderLevel getFollowUpRequestProviderLevel() {
		return this.followUpRequestProviderLevel;
	}

	/**
	 * Sets the follow up request provider level.
	 * 
	 * @param followUpRequestProviderLevel follow up request provider level 
	 */
	public void setFollowUpRequestProviderLevel(
			final ProviderLevel followUpRequestProviderLevel) {
		this.followUpRequestProviderLevel = followUpRequestProviderLevel;
	}

	/**
	 * Returns the follow up request notes.
	 * 
	 * @return follow up request notes
	 */
	public String getFollowUpRequestNotes() {
		return this.followUpRequestNotes;
	}

	/**
	 * Sets the follow up request notes.
	 * 
	 * @param followUpRequestNotes follow up request notes
	 */
	public void setFollowUpRequestNotes(final String followUpRequestNotes) {
		this.followUpRequestNotes = followUpRequestNotes;
	}

	/**
	 * Returns the lab work.
	 * 
	 * @return lab work appointment items
	 */
	public List<LabWorkAppointmentItem> getLabWork() {
		return this.labWork;
	}

	/**
	 * Sets the lab work.
	 * 
	 * @param labWork lab work appointment items
	 */
	public void setLabWork(final List<LabWorkAppointmentItem> labWork) {
		this.labWork = labWork;
	}
}