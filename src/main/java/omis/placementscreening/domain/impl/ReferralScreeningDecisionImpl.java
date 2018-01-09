package omis.placementscreening.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.placementscreening.domain.ReferralScreening;
import omis.placementscreening.domain.ReferralScreeningDecision;
import omis.placementscreening.domain.ScreeningDecisionCategory;

/** Implementation for referral screening decision.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 3, 2014)
 * @since OMIS 3.0 */
public class ReferralScreeningDecisionImpl 
	implements ReferralScreeningDecision {
	private static final long serialVersionUID = 1L;
	private static final String REFERRAL_SCREENING_REQUIRED = 
			"Referral screening required"; 
	private static final String DATE_REQUIRED = "Date required";
	private static final String SCREENING_DECISION_CATEGORY_REQUIRED = 
			"Screening decision category required";
	private static final int[] HASH_NUMS = {7, 11, 13, 15}; 
	private Long id;
	private String notes;
	private Date date;
	private ReferralScreening referralScreening;
	private ScreeningDecisionCategory screeningDecisionCategory;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	

//--------------------------------Constructors----------------------------------
	/** Default constructor. */
	public ReferralScreeningDecisionImpl() { /* Do nothing. */ }
//-----------------------------------Getters------------------------------------
	/** {@inheritDoc} */
	@Override
	public Long getId() { 
		return this.id; 
	}
	
	/** {@inheritDoc} */
	@Override
	public String getNotes() { 
		return this.notes; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getDate() { 
		return this.date; 
	}

	/** {@inheritDoc} */
	@Override
	public ReferralScreening getReferralScreening() {
		return this.referralScreening; 
	}
	
	/** {@inheritDoc} */
	@Override
	public ScreeningDecisionCategory getScreeningDecisionCategory() {
		return this.screeningDecisionCategory;
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
	public void setNotes(final String notes) { 
		this.notes = notes; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) { 
		this.date = date; 
	}

	/** {@inheritDoc} */
	@Override
	public void setReferralScreening(
			final ReferralScreening referralScreening) {
		this.referralScreening = referralScreening;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setScreeningDecisionCategory(
			final ScreeningDecisionCategory screeningDecisionCategory) {
		this.screeningDecisionCategory = screeningDecisionCategory;
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
	public int hashCode() {
		int index = 0;
		this.checkState();
		return HASH_NUMS[index]
				+ HASH_NUMS[index++] * this.getReferralScreening().hashCode()
				+ HASH_NUMS[index++] * this.getDate().hashCode()
				+ HASH_NUMS[index++] * this.getScreeningDecisionCategory()
					.hashCode();
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		
		if (this == obj) {
			result = true;
		}
		
		if (obj instanceof ReferralScreeningDecision) {
			ReferralScreeningDecision that = (ReferralScreeningDecision) obj;
			this.checkState();
			if (this.getReferralScreening().equals(that.getReferralScreening())
				&& this.getDate().equals(that.getDate())
				&& this.getScreeningDecisionCategory()
					.equals(that.getScreeningDecisionCategory())) {
				result = true;
			}
		}
		
		return result;
	}
	
	/* Checks state. */
	private void checkState() {
		if (this.getReferralScreening() == null) {
			throw new IllegalStateException(REFERRAL_SCREENING_REQUIRED);
		}
		
		if (this.getDate() == null) {
			throw new IllegalStateException(DATE_REQUIRED);
		}
		
		if (this.getScreeningDecisionCategory() == null) {
			throw new IllegalStateException(
					SCREENING_DECISION_CATEGORY_REQUIRED);
		}
	}
}
