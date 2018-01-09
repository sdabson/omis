package omis.placementscreening.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.placementscreening.domain.PlacementReferral;
import omis.placementscreening.domain.ReferralScreening;
import omis.placementscreening.domain.ReferralScreeningCenter;
import omis.placementscreening.domain.ScreeningLevelCategory;

/** Implementation of referral screening.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 3, 2014)
 * @since OMIS 3.0 */
public class ReferralScreeningImpl implements ReferralScreening {
	private static final String PROGRAM_REFERRAL_REQUIRED = 
			"Program referral required.";
	private static final String SCREENING_ORDER_REQUIRED = 
			"Screening order required.";
	private static final String SCREENING_FACILITY_REQUIRED =
			"Screening facility required.";
	private static final String SCREENING_LEVEL_CATEGORY_REQUIRED =
			"Screening level required.";
	private static final int[] HASH_NUMS = {2, 3, 5, 7, 11};
	private static final long serialVersionUID = 1L;
	private Long id;
	private ReferralScreeningCenter referralScreeningCenter;
	private PlacementReferral placementReferral;
	private Integer screeningOrder;
	private ScreeningLevelCategory screeningLevelCategory;
	private DateRange screeningWindow;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	

//--------------------------------Constructors----------------------------------
	/** Default constructor. */
	public ReferralScreeningImpl() { /* Do nothing. */ }
//-----------------------------------Getters------------------------------------
	/** {@inheritDoc} */
	@Override
	public Long getId() { 
		return this.id; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer getScreeningOrder() { 
		return this.screeningOrder; 
	}

	/** {@inheritDoc} */
	@Override
	public PlacementReferral getPlacementReferral() {
		return this.placementReferral; 
	}
	
	/** {@inheritDoc} */
	@Override
	public ReferralScreeningCenter getReferralScreeningCenter() {
		return this.referralScreeningCenter;
	}
	
	/** {@inheritDoc} */
	@Override
	public ScreeningLevelCategory getScreeningLevelCategory() { 
		return this.screeningLevelCategory;
	}
	
	/** {@inheritDoc} */
	@Override
	public DateRange getScreeningWindow() { 
		return this.screeningWindow; 
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
	public void setScreeningOrder(final Integer screeningOrder) {
		this.screeningOrder = screeningOrder;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setPlacementReferral(
			final PlacementReferral placementReferral) {
		this.placementReferral = placementReferral;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setReferralScreeningCenter(
			final ReferralScreeningCenter referralScreeningCenter) {
		this.referralScreeningCenter = referralScreeningCenter;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setScreeningLevelCategory(
			final ScreeningLevelCategory screeningLevelCategory) {
		this.screeningLevelCategory = screeningLevelCategory;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setScreeningWindow(final DateRange screeningWindow) { 
		this.screeningWindow = screeningWindow; 
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
			if (obj instanceof ReferralScreening) {
				ReferralScreening that = (ReferralScreening) obj;
				this.checkState();
				if (this.getPlacementReferral().equals(
						that.getPlacementReferral())
						&& this.getScreeningOrder()
							.equals(that.getScreeningOrder())
						&& this.getReferralScreeningCenter()
							.equals(that.getReferralScreeningCenter())
						&& this.getScreeningLevelCategory()
							.equals(that.getScreeningLevelCategory())) {
					result = false;
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
				+ HASH_NUMS[index++] * this.getPlacementReferral().hashCode()
				+ HASH_NUMS[index++] * this.getScreeningOrder().hashCode()
				+ HASH_NUMS[index++] 
						* this.getReferralScreeningCenter().hashCode()
				+ HASH_NUMS[index++] 
						* this.getScreeningLevelCategory().hashCode();
	}
	
	/* Checks state. */
	private void checkState() {
		if (this.getPlacementReferral() == null) {
			throw new IllegalStateException(PROGRAM_REFERRAL_REQUIRED);
		}
		
		if (this.getScreeningOrder() == null) {
			throw new IllegalStateException(SCREENING_ORDER_REQUIRED);
		}
		
		if (this.getReferralScreeningCenter() == null) {
			throw new IllegalStateException(
					SCREENING_FACILITY_REQUIRED);
		}
		
		if (this.getScreeningLevelCategory() == null) {
			throw new IllegalStateException(
					SCREENING_LEVEL_CATEGORY_REQUIRED);
		}
	}
}
