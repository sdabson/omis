package omis.workrestriction.domain.impl;

import omis.audit.domain.AuthorizationSignature;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.offender.domain.Offender;
import omis.workrestriction.domain.WorkRestriction;
import omis.workrestriction.domain.WorkRestrictionCategory;

/**
 * WorkRestrictionImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
public class WorkRestrictionImpl implements WorkRestriction {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private WorkRestrictionCategory category;
	
	private String notes;
	
	private Offender offender;
	
	private AuthorizationSignature authorizationSignature;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature; 
	
	/**
	 * Default Constructor
	 */
	public WorkRestrictionImpl() {
		//Default
	}
	
	/**{@inheritDoc} */
	@Override
	public void setOffender(Offender offender) {
		this.offender = offender;
	}

	/**{@inheritDoc} */
	@Override
	public Offender getOffender() {
		return this.offender;
	}

	/**{@inheritDoc} */
	@Override
	public void setAuthorizationSignature(AuthorizationSignature authorizationSignature) {
		this.authorizationSignature = authorizationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public AuthorizationSignature getAuthorizationSignature() {
		return this.authorizationSignature;
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
	public WorkRestrictionCategory getCategory() {
		return this.category;
	}

	/**{@inheritDoc} */
	@Override
	public String getNotes() {
		return this.notes;
	}

	/**{@inheritDoc} */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public void setCategory(WorkRestrictionCategory category) {
		this.category = category;
	}

	/**{@inheritDoc} */
	@Override
	public void setNotes(String notes) {
		this.notes = notes;
	}
	

	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof WorkRestriction)){
			return false;
		}
		
		WorkRestriction that = (WorkRestriction) obj;
		
		if(this.getOffender() == null){
			throw new IllegalStateException("Offender required.");
		}
		if(this.getCategory() == null){
			throw new IllegalStateException("Category required.");
		}
		if(this.getAuthorizationSignature() == null){
			throw new IllegalStateException("Authorization signature required.");
		}
		
		if(!this.getOffender().equals(that.getOffender())){
			return false;
		}
		if(!this.getCategory().equals(that.getCategory())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getOffender() == null){
			throw new IllegalStateException("Offender required.");
		}
		if(this.getCategory() == null){
			throw new IllegalStateException("Category required.");
		}
		if(this.getAuthorizationSignature() == null){
			throw new IllegalStateException("Authorization signature required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getOffender().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		
		return hashCode;
	}

}
