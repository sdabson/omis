package omis.detainernotification.domain.impl;

import omis.detainernotification.domain.DetainerInterpretation;
import omis.detainernotification.domain.DetainerType;

/**
 * DetainerTypeImpl.java
 * 
 * @author Annie Jacques
 * @author Joel Norris
 * @version 0.1.1 (May 25, 2016)
 * @since OMIS 3.0
 */
public class DetainerTypeImpl implements DetainerType {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private Boolean valid;
	
	private Short sortOrder;
	
	private DetainerInterpretation interpretation;
	
	/* Constructors */
	
	/**
	 * Instantiates a default instance of detainer type.
	 */
	public DetainerTypeImpl(){
		//Nothing
	}
		
	/* Getters and Setters */
	
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
	public Boolean getValid() {
		return valid;
	}

	/** {@inheritDoc} */
	@Override
	public void setValid(final Boolean valid) {
		this.valid = valid;
	}

	/** {@inheritDoc} */
	@Override
	public Short getSortOrder() {
		return this.sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public void setSortOrder(final Short sortOrder) {
		this.sortOrder = sortOrder;
	}

	/** {@inheritDoc} */
	@Override
	public DetainerInterpretation getInterpretation() {
		return this.interpretation;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setInterpretation(final DetainerInterpretation interpretation) {
		this.interpretation = interpretation;
	}
	
	/* Equals/Hashcode */
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof DetainerType)){
			return false;
		}
		
		DetainerType that = (DetainerType) obj;
		
		if (this.getName() == null) {
			throw new IllegalStateException("Name required.");
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
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getName().hashCode();
	
		return hashCode;
	}
}