package omis.placementscreening.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.placementscreening.domain.ApprovalCategory;

/** Implementation of approval category.
 * @author Ryan Johns
 * @version 0.1.0 (Oct 31, 2014)
 * @since OMIS 3.0 */
public class ApprovalCategoryImpl implements ApprovalCategory {
	private static final String NAME_REQUIRED = "Name required.";
	private static final String DESCRIPTION_REQUIRED = "Description required.";
	private static final String VALID_REQUIRED = "Valid required.";
	private static final int[] HASH_NUMS = {2, 3, 5, 7};
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String description;
	private Boolean valid;
	private CreationSignature creationSignature;
//--------------------------Constructors-----------------------------------
	/** default constructor. */
	public ApprovalCategoryImpl() { /* do nothing */ }
//--------------------------Getters----------------------------------------
	/** {@inheritDoc} */
	@Override
	public Long getId() { 
		return this.id; 
	}

	/** {@inheritDoc} */
	@Override
	public String getName() { 
		return this.name; 
	}
	
	/** {@inheritDoc} */
	@Override
	public String getDescription() { 
		return this.description; 
	}
	
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() { 
		return this.creationSignature; 
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getValid() { 
		return this.valid; 
	}

	//----------------------------Setters--------------------------------------
	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) { 
		this.id = id; 
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) { 
		this.name = name; 
	}
	
	/** {@inheritDoc} */
	@Override
	public void setDescription(final String description) { 
		this.description = description;
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
	public boolean equals(final Object obj) {
		boolean result = false;
		
		if (this == obj) {
			result = true;
		} else {
			if (obj instanceof ApprovalCategory) {
				ApprovalCategory that = (ApprovalCategory) obj;
				this.checkState();	
				if (this.getName().equals(that.getName())
						&& this.getDescription().equals(that.getDescription())
						&& this.getValid().equals(that.getValid())) { 
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
		return HASH_NUMS[index]
				+ HASH_NUMS[index++] * this.getName().hashCode()
				+ HASH_NUMS[index++] * this.getDescription().hashCode()
				+ HASH_NUMS[index++] * this.getValid().hashCode();
	}
	
	/* Checks state. */
	private void checkState() {
		if (this.getName() == null) {
			throw new IllegalStateException(NAME_REQUIRED);
		}
		
		if (this.getDescription() == null) {
			throw new IllegalStateException(DESCRIPTION_REQUIRED);
		}
		
		if (this.getValid() == null) {
			throw new IllegalStateException(VALID_REQUIRED);
		}
	}
}
