package omis.workrestriction.domain.impl;

import omis.workrestriction.domain.WorkRestrictionCategory;

/**
 * WorkRestrictionCategoryImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
public class WorkRestrictionCategoryImpl implements WorkRestrictionCategory {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private Boolean valid;
	
	/**
	 * Default Constructor
	 */
	public WorkRestrictionCategoryImpl() {
		//Default
	}
	
	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/**{@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**{@inheritDoc} */
	@Override
	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof WorkRestrictionCategory)){
			return false;
		}
		
		WorkRestrictionCategory that = (WorkRestrictionCategory) obj;
		
		if(this.getName() == null){
			throw new IllegalStateException("Name Required.");
		}
		if(this.getValid() == null){
			throw new IllegalStateException("Valid required");
		}
		
		if(!this.getName().equals(that.getName())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getName() == null){
			throw new IllegalStateException("Name Required.");
		}
		if(this.getValid() == null){
			throw new IllegalStateException("Valid required");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		
		return hashCode;
	}
}
