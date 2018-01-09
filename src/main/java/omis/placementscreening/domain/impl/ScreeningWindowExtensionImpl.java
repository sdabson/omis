package omis.placementscreening.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.placementscreening.domain.ReferralScreening;
import omis.placementscreening.domain.ScreeningWindowExtension;

/** Implementation of Screening window extension.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 12, 2014)
 * @since OMIS 3.0 */
public class ScreeningWindowExtensionImpl implements ScreeningWindowExtension {
	private static final long serialVersionUID = 1L;
	private static final String REQUEST_DATE_REQUIRED = 
			"Request date required.";
	private static final String REFERRAL_SCREENING_REQUIRED =
			"Referral screening required.";
	private static final int[] HASH_NUMS = {7, 11, 13};
	private Long id;
	private Boolean approved;
	private ReferralScreening referralScreening;
	private Date requestDate;
	private Date decisionDate;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
//--------------------------------Constructors----------------------------------
	/** Default constructor. */
	public ScreeningWindowExtensionImpl() { /* Do nothing. */ }
//-----------------------------------Getters------------------------------------
	/** {@inheritDoc} */
	@Override
	public Long getId() { 
		return this.id; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getApproved() { 
		return this.approved; 
	}
	
	/** {@inheritDoc} */
	@Override
	public ReferralScreening getReferralScreening() {
		return this.referralScreening; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getRequestDate() { 
		return this.requestDate; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getDecisionDate() { 
		return this.decisionDate; 
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
	public void setApproved(final Boolean approved) { 
		this.approved = approved; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setReferralScreening(
			final ReferralScreening referralScreening) {
		this.referralScreening = referralScreening;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setRequestDate(final Date requestDate) {
		this.requestDate = requestDate;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDecisionDate(final Date decisionDate) {
		this.decisionDate = decisionDate; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
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
			if (obj instanceof ScreeningWindowExtension) {
				ScreeningWindowExtension that = (ScreeningWindowExtension) obj;
				this.checkState();
				if (this.getReferralScreening()
						.equals(that.getReferralScreening())
						&& this.getRequestDate()
							.equals(that.getRequestDate())) {
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
				+ HASH_NUMS[index++] * this.getReferralScreening().hashCode()
				+ HASH_NUMS[index++] * this.getRequestDate().hashCode();
	}
	
	/* Checks state. */
	private void checkState() {
		if (this.getRequestDate() == null) {
			throw new IllegalStateException(REQUEST_DATE_REQUIRED);
		}
		if (this.getReferralScreening() == null) {
			throw new IllegalStateException(REFERRAL_SCREENING_REQUIRED);
		}
	}
}
