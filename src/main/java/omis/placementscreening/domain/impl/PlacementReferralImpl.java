package omis.placementscreening.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.facility.domain.Facility;
import omis.offender.domain.Offender;
import omis.placementscreening.domain.PlacementReferral;
import omis.placementscreening.domain.ProgramCategory;
import omis.placementscreening.domain.component.ReferralSource;

/** Implementation of program referral.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 3, 2014)
 * @since OMIS 3.0 */
public class PlacementReferralImpl implements PlacementReferral {
	private static final long serialVersionUID = 1L;
	private static final String OFFENDER_REQUIRED = "Offender required.";
	private static final String DATE_REQUIRED = "Date required.";
	private static final String PROGRAM_CATEGORY_REQUIRED = 
			"Program category required.";
	private static final String FACILITY_REQUIRED = "Facility required.";
	private static final String REFERRAL_SOURCE_REQUIRED = 
			"Referral source required.";
	private static final int[] HASH_NUMS = {2, 3, 5, 7, 11, 13};
	private Long id;
	private Offender offender;
	private Date date;
	private ProgramCategory programCategory;
	private ReferralSource referralSource;
	private Facility facility;
	private CreationSignature creationSignature;
//--------------------------------Constructors----------------------------------
	/** Default constructor. */
	public PlacementReferralImpl() { /* Do nothing. */ }
//-----------------------------------Getters------------------------------------
	/** {@inheritDoc} */
	@Override
	public Long getId() { 
		return this.id; 
	}
	
	/** {@inheritDoc} */
	@Override
	public ProgramCategory getProgramCategory() { 
		return this.programCategory; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Offender getOffender() { 
		return this.offender; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Date getDate() { 
		return this.date; 
	}
	
	/** {@inheritDoc} */
	@Override
	public ReferralSource getReferralSource() { 
		return this.referralSource; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Facility getFacility() { 
		return this.facility; 
	}
	 
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature; 
	}
//-----------------------------------Setters------------------------------------
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) { 
		this.id = id; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setProgramCategory(final ProgramCategory programCategory) {
		this.programCategory = programCategory; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setOffender(final Offender offender) { 
		this.offender = offender; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDate(final Date date) { 
		this.date = date; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setReferralSource(final ReferralSource referralSource) {
		this.referralSource = referralSource;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setFacility(final Facility facility) {
		this.facility = facility;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature; 
	}
//------------------------------------------------------------------------------
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		
		if (this == obj) {
			result = true;
		} else {
			if (obj instanceof PlacementReferral) {
				PlacementReferral that = (PlacementReferral) obj;
				this.checkState();
				if (this.getOffender().equals(that.getOffender())
						&& this.getDate().equals(that.getDate())
						&& this.getProgramCategory() 
						== that.getProgramCategory()
						&& this.getReferralSource()
							.equals(that.getReferralSource())
						&& this.getFacility().equals(that.getFacility())) {
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
				+ HASH_NUMS[index++] * this.getOffender().hashCode()
				+ HASH_NUMS[index++] * this.getDate().hashCode()
				+ HASH_NUMS[index++] * this.getReferralSource().hashCode()
				+ HASH_NUMS[index++] * this.getFacility().hashCode();
	}
	
	/* Checks state. */
	private void checkState() {
		if (this.getOffender() == null) {
			throw new IllegalStateException(OFFENDER_REQUIRED);
		}
		
		if (this.getDate() == null) { 
			throw new IllegalStateException(DATE_REQUIRED);
		}
		
		if (this.getProgramCategory() == null) {
			throw new IllegalStateException(PROGRAM_CATEGORY_REQUIRED);
		}
		
		if (this.getReferralSource() == null) {
			throw new IllegalStateException(REFERRAL_SOURCE_REQUIRED);
		}
		
		if (this.getFacility() == null) {
			throw new IllegalStateException(FACILITY_REQUIRED);
		}
	}
}
