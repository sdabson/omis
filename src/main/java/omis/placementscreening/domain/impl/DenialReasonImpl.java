package omis.placementscreening.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.placementscreening.domain.DenialCategory;
import omis.placementscreening.domain.DenialReason;
import omis.placementscreening.domain.ReferralScreeningDecision;

/** Implementation of denial reason.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 3, 2014)
 * @since OMIS 3.0 */
public class DenialReasonImpl implements DenialReason {
	private static final long serialVersionUID = 1L;
	private static final String DENIAL_CATEGORY_REQUIRED = 
			"Denial category required.";
	private static final String REFERRAL_SCREENING_DECISION_REQUIRED =
			"Referral screening decision required.";
	private static final String NOTES_REQUIRED = "Notes required.";
	private static final int[] HASH_NUMS = {2, 5, 11, 17};
	private Long id;
	private DenialCategory denialCategory;
	private String notes;
	private ReferralScreeningDecision referralScreeningDecision;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
//--------------------------------Constructors----------------------------------
	/** Default constructor. */
	public DenialReasonImpl() { /* Do nothing. */ }
//-----------------------------------Getters------------------------------------
	/** {@inheritDoc} */
	@Override
	public Long getId() { 
		return this.id; 
	}
	
	/** {@inheritDoc} */
	@Override
	public DenialCategory getDenialCategory() { 
		return this.denialCategory; 
	}
	
	/** {@inheritDoc} */
	@Override
	public ReferralScreeningDecision getReferralScreeningDecision() {
		return this.referralScreeningDecision; 
	}
	
	/** {@inheritDoc} */
	@Override
	public String getNotes() { 
		return this.notes; 
	}
	
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() { 
		return this.creationSignature; 
	}
	
	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}
//-----------------------------------Setters------------------------------------
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) { 
		this.id = id; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDenialCategory(
			final DenialCategory denailCategory) {
		this.denialCategory = denailCategory; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setReferralScreeningDecision(
			final ReferralScreeningDecision referralScreeningDecision) {
		this.referralScreeningDecision = referralScreeningDecision;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setNotes(final String notes) { 
		this.notes = notes; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(
			final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}
//------------------------------------------------------------------------------
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		
		if (this == obj) {
			result = true;
		} else {
			if (obj instanceof DenialReason) {
				DenialReason that = (DenialReason) obj;
				this.checkState();
				if (this.getDenialCategory().equals(that.getDenialCategory())
						&& this.getReferralScreeningDecision()
							.equals(that.getReferralScreeningDecision())
						&& this.getNotes().equals(that.getNotes())) {
					result = true; 
				}
			}
		}
		return result;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		int index = 0;
		this.checkState();
		return HASH_NUMS[index]
				+ HASH_NUMS[index++] * this.getDenialCategory().hashCode() 
				+ HASH_NUMS[index++] 
						* this.getReferralScreeningDecision().hashCode()
				+ HASH_NUMS[index++] * this.getNotes().hashCode();
	}
	
	/* Checks state. */
	private void checkState() {
		if (this.getDenialCategory() == null) {
			throw new IllegalStateException(DENIAL_CATEGORY_REQUIRED);
		}
		
		if (this.getReferralScreeningDecision() == null) {
			throw new IllegalStateException(
					REFERRAL_SCREENING_DECISION_REQUIRED);
		}
		
		if (this.getNotes() == null) {
			throw new IllegalStateException(NOTES_REQUIRED);
		}
	}
}
