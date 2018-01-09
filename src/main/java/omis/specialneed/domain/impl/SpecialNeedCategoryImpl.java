package omis.specialneed.domain.impl;

import omis.specialneed.domain.SpecialNeedCategory;
import omis.specialneed.domain.SpecialNeedClassification;

/**
 * Implementation of special need category.
 * 
 * @author Joel Norris
 * @author Sheronda Vaughn
 * @version 0.1.0 (Feb, 14 2013)
 * @since OMIS 3.0
 */
public class SpecialNeedCategoryImpl implements SpecialNeedCategory {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String name;
	
	private Boolean valid;
	
	private SpecialNeedClassification classification;
	
	/** Instantiates a default instance of special need category
	 * implementation.
	 */
	public SpecialNeedCategoryImpl() {
		//empty constructor
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}

	/** {@inheritDoc} */
	@Override
	public void setName(final String name) {
		this.name = name;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}

	/** {@inheritDoc} */
	@Override
	public SpecialNeedClassification getClassification() {
		return this.classification;
	}

	/** {@inheritDoc} */
	@Override
	public void setClassification(
			final SpecialNeedClassification classification) {
		this.classification = classification;
	}	
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if(this == obj) {
			return true;
		}
		if(!(obj instanceof SpecialNeedCategory)) {
			return false;
		}
		
		SpecialNeedCategory that = (SpecialNeedCategory) obj;
		if(this.getName() == null) {
			throw new IllegalStateException("Category name required.");			
		}
		if(!this.getName().equals(that.getName())) {
			return false;
		}
		if(this.getClassification() == null) {
			throw new IllegalStateException("Classification required.");
		}
		if(!this.getClassification().equals(that.getClassification())) {
			return false;
		}
		return false;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getName() == null) {
			throw new IllegalStateException("Category name required.");			
		}
		if(this.getClassification() == null) {
			throw new IllegalStateException("Classification required.");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getName().hashCode();
		hashCode = 29 * hashCode + this.getClassification().hashCode();
		
		return hashCode;
	}
}