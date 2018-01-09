package omis.placementscreening.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.demographics.domain.Sex;
import omis.facility.domain.Facility;
import omis.placementscreening.domain.ProgramCategory;
import omis.placementscreening.domain.ReferralScreeningCenter;

/** Implementation of referral screening facility.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 12, 2014)
 * @since OMIS 3.0 */
public class ReferralScreeningCenterImpl 
	implements ReferralScreeningCenter {
	private static final long serialVersionUID = 1L;
	private static final String ORDER_REQUIRED = "Order required.";
	private static final String PROGRAM_CATEGORY_REQUIRED = 
			"Program category required.";
	private static final String SEX_REQUIRED = "Sex designation required";
	private static final String FACILITY_REQUIRED = "Facility required.";
	private static final int[] HASH_NUMS = {7, 11, 13, 15, 17};
	private Long id;
	private ProgramCategory programCategory;
	private Integer order;
	private Facility facility;
	private Sex sex;
	private Boolean valid;
	private CreationSignature creationSignature;
//--------------------------------Constructors----------------------------------
	/** Default constructor. */
	public ReferralScreeningCenterImpl() { /* Do nothing. */ }
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
	public Integer getOrder() { 
		return this.order; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Facility getFacility() {
		return this.facility; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}
	
	/** {@inheritDoc} */
	@Override
	public Sex getSex() { 
		return this.sex; 
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
	public void setOrder(final Integer order) { 
		this.order = order; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setFacility(final Facility facility) {
		this.facility = facility;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setSex(final Sex sex) { 
		this.sex = sex; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) { 
		this.valid = valid; 
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
	public int hashCode() {
		int index = 0;
		this.checkState();
		return HASH_NUMS[index]
				+ HASH_NUMS[index++] * this.getOrder().hashCode()
				+ HASH_NUMS[index++] * this.getFacility().hashCode()
				+ HASH_NUMS[index++] * this.getProgramCategory().ordinal()
				+ HASH_NUMS[index++] * this.getSex().hashCode();
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		
		if (this == obj) {
			result = true;
		} else {
			if (obj instanceof ReferralScreeningCenter) {
				ReferralScreeningCenter that = 
						(ReferralScreeningCenter) obj;
				this.checkState();
				
				if (this.getOrder().equals(that.getOrder()) 
						&& this.getProgramCategory() 
						== that.getProgramCategory()
						&& this.getFacility().equals(that.getFacility())
						&& this.getSex().equals(that.getSex())) {
					result = true;
				}
			}
		}
		return result;
	}
	
	/* Checks state. */
	private void checkState() {
		if (this.getOrder() == null) {
			throw new IllegalStateException(ORDER_REQUIRED);
		}
		
		if (this.getProgramCategory() == null) {
			throw new IllegalStateException(PROGRAM_CATEGORY_REQUIRED);
		}
		
		if (this.getFacility() == null) {
			throw new IllegalStateException(FACILITY_REQUIRED);
		}
		
		if (this.getSex() == null) {
			throw new IllegalStateException(SEX_REQUIRED);
		}
	}
}
