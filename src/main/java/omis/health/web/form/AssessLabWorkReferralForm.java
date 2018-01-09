package omis.health.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import omis.health.domain.ProviderLevel;

/**
 * Form to assess a lab work referral.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jul 25, 2014)
 * @since OMIS 3.0
 */
public class AssessLabWorkReferralForm 
	implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String notes;
	
	private ReferralType followUpReferralType;
	
	private Boolean followUpAsap;
	
	private Boolean followUpLabsRequired;
	
	private ProviderLevel followUpRequestProviderLevel;
	
	private String followUpRequestNotes;
	
	private List<LabWorkAssessmentItem> labWorkAssessmentItems =
			new ArrayList<LabWorkAssessmentItem>();
	
	/**
	 * Instantiates a default instance of assess lab work referral form.
	 */
	public AssessLabWorkReferralForm() {
		//Default constructor.
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
	 * Returns the referral type.
	 * 
	 * @return follow up referral type
	 */
	public ReferralType getFollowUpReferralType() {
		return this.followUpReferralType;
	}

	/**
	 * Sets the referral type.
	 * 
	 * @param followUpReferralType referral type
	 */
	public void setFollowUpReferralType(final ReferralType followUpReferralType) {
		this.followUpReferralType = followUpReferralType;
	}

	/**
	 * Returns whether or not to follow up as soon as possible.
	 * 
	 * @return follow up A.S.A.P.
	 */
	public Boolean getFollowUpAsap() {
		return this.followUpAsap;
	}

	/**
	 * Sets whether or not to follow up as soon as possible.
	 * 
	 * @param followUpAsap follow up A.S.A.P.
	 */
	public void setFollowUpAsap(final Boolean followUpAsap) {
		this.followUpAsap = followUpAsap;
	}

	/**
	 * Returns whether follow up labs are required.
	 * 
	 * @return follow up labs required
	 */
	public Boolean getFollowUpLabsRequired() {
		return this.followUpLabsRequired;
	}

	/**
	 * Sets whether follow up labs are required.
	 * 
	 * @param followUpLabsRequired follow up lab required
	 */
	public void setFollowUpLabsRequired(final Boolean followUpLabsRequired) {
		this.followUpLabsRequired = followUpLabsRequired;
	}

	/**
	 * Returns what the provider level of the follow up request should be.
	 * 
	 * @return follow up request provider level
	 */
	public ProviderLevel getFollowUpRequestProviderLevel() {
		return this.followUpRequestProviderLevel;
	}

	/**
	 * Sets what the provider level of the follow up request should be.
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
	 * Returns the lab work assessment items.
	 * 
	 * @return lab work assessment items
	 */
	public List<LabWorkAssessmentItem> getLabWorkAssessmentItems() {
		return this.labWorkAssessmentItems;
	}

	/**
	 * Sets the lab work assessment items.
	 * 
	 * @param labWorkAssessmentItems lab work assessment items
	 */
	public void setLabWorkAssessmentItems(
			final List<LabWorkAssessmentItem> labWorkAssessmentItems) {
		this.labWorkAssessmentItems = labWorkAssessmentItems;
	}
}