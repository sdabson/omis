package omis.disciplinaryCode.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.disciplinaryCode.domain.DisciplinaryCode;

/**
 * DisciplinaryCodeImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Sep 1, 2017)
 *@since OMIS 3.0
 *
 */
public class DisciplinaryCodeImpl implements DisciplinaryCode {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String value;
	
	private String description;
	
	private String extendedDescription;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Default Constructor
	 */
	public DisciplinaryCodeImpl() {
	}
	
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
		return this.updateSignature;
	}

	/**{@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/**{@inheritDoc} */
	@Override
	public String getValue() {
		return this.value;
	}

	/**{@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public void setValue(final String value) {
		this.value = value;
	}

	/**{@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/**{@inheritDoc} */
	@Override
	public String getExtendedDescription() {
		return this.extendedDescription;
	}

	/**{@inheritDoc} */
	@Override
	public void setExtendedDescription(final String extendedDescription) {
		this.extendedDescription = extendedDescription;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof DisciplinaryCode)){
			return false;
		}
		
		DisciplinaryCode that = (DisciplinaryCode) obj;
		
		if(this.getValue() == null){
			throw new IllegalStateException("Value required.");
		}
		if(this.getDescription() == null){
			throw new IllegalStateException("Description required.");
		}
		
		
		if(!this.getValue().equals(that.getValue())){
			return false;
		}
		
		return true;
	}
		
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getValue() == null){
			throw new IllegalStateException("Value required.");
		}
		if(this.getDescription() == null){
			throw new IllegalStateException("Description required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getValue().hashCode();
		
		return hashCode;
		
	}
}
