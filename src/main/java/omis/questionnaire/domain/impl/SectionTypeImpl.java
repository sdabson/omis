package omis.questionnaire.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.questionnaire.domain.SectionType;

/**
 * SectionTypeImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 7, 2016)
 *@since OMIS 3.0
 *
 */
public class SectionTypeImpl implements SectionType {

	private static final long serialVersionUID = 1L;

	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	private Long id;
	
	private String description;
	
	private Boolean valid;
	
	

	/**{@inheritDoc} */
	@Override
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setUpdateSignature(UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**{@inheritDoc} */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/**{@inheritDoc} */
	@Override
	public Boolean getValid() {
		return this.valid;
	}

	/**{@inheritDoc} */
	@Override
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if(this == obj){
			return true;
		}
		if(!(obj instanceof SectionType)){
			return false;
		}
		
		SectionType that = (SectionType) obj;
		
		if(this.getDescription() == null){
			throw new IllegalStateException("Description Required");
		}
		if(this.getValid() == null){
			throw new IllegalStateException("Valid Required");
		}
		
		if(!this.getDescription().equals(that.getDescription())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getDescription() == null){
			throw new IllegalStateException("Description Required");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		
		return hashCode;
	}
}
