package omis.education.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.education.domain.InstituteCategory;

/**
 * InstituteCategory.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public class InstituteCategoryImpl implements omis.education.domain.InstituteCategory {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private Short sortOrder;
	
	private Boolean valid;
	
	private CreationSignature creationSignature;
	
	public InstituteCategoryImpl(){
		//Default Constructor
	}
	
	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
		
	}
	
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
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
	public Short getSortOrder() {
		return this.sortOrder;
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
	public void setSortOrder(Short sortOrder) {
		this.sortOrder = sortOrder;
	}
	

	/**{@inheritDoc} */
	@Override
	public void setValid(Boolean valid){
		this.valid = valid;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof InstituteCategory)){
			return false;
		}
		
		InstituteCategory that 
			= (InstituteCategory) obj;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (this.getSortOrder() == null) {
			throw new IllegalStateException("Sort Order required.");
		}
		if (this.getValid() == null) {
			throw new IllegalStateException("Valid required.");
		}
		
		if (!this.getName().equals(that.getName())) {
			return false;
		}
		
		return true;
	}
	
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
		}
		if (this.getSortOrder() == null) {
			throw new IllegalStateException("Sort Order required.");
		}
		if (this.getValid() == null) {
			throw new IllegalStateException("Valid required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
	
		return hashCode;
	}

}
