package omis.presentenceinvestigation.domain.impl;

import omis.presentenceinvestigation.domain.PresentenceInvestigationCategory;

/**
 * PresentenceInvestigationCategoryImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 23, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationCategoryImpl
		implements PresentenceInvestigationCategory {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private Boolean valid;
	
	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/**{@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/**{@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/**{@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof PresentenceInvestigationCategory)){
			return false;
		}
		
		PresentenceInvestigationCategory that =
				(PresentenceInvestigationCategory) obj;
		
		if(this.getName() == null){
			throw new IllegalStateException("Name required.");
		}
		if(this.getValid() == null){
			throw new IllegalStateException("Valid required.");
		}
		
		if(!this.getName().equals(that.getName())){
			return false;
		}
		if(!this.getValid().equals(that.getValid())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getName() == null){
			throw new IllegalStateException("Name required.");
		}
		if(this.getValid() == null){
			throw new IllegalStateException("Valid required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
		hashCode = 29 * hashCode + this.getValid().hashCode();
		
		return hashCode;
	}
	
}
