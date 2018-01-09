package omis.warrant.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.condition.domain.Condition;
import omis.courtcase.domain.CourtCase;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantCauseViolation;

/**
 * WarrantCause.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantCauseViolationImpl implements WarrantCauseViolation {
	
	private static final long serialVersionUID = 1L;
	
	private CourtCase cause;
	
	private Condition condition;
	
	private String description;
	
	private Warrant warrant;
	
	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**{@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/**{@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
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
	public void setId(final Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public Warrant getWarrant() {
		return this.warrant;
	}

	/**{@inheritDoc} */
	@Override
	public void setWarrant(final Warrant warrant) {
		this.warrant = warrant;
	}

	/**{@inheritDoc} */
	@Override
	public CourtCase getCause() {
		return this.cause;
	}

	/**{@inheritDoc} */
	@Override
	public void setCause(final CourtCase cause) {
		this.cause = cause;
	}
	
	/**{@inheritDoc} */
	@Override
	public Condition getCondition() {
		return this.condition;
	}

	/**{@inheritDoc} */
	@Override
	public void setCondition(final Condition condition) {
		this.condition = condition;
	}
	
	/**{@inheritDoc} */
	@Override
	public String getDescription() {
		return this.description;
	}
	
	/**{@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof WarrantCauseViolation)){
			return false;
		}
		
		WarrantCauseViolation that = (WarrantCauseViolation) obj;
		
		if(this.getWarrant() == null){
			throw new IllegalStateException("Warrant required.");
		}
		if(this.getCause() == null){
			throw new IllegalStateException("Cause required.");
		}
		if(this.getCondition() == null){
			throw new IllegalStateException("Condition required.");
		}
		
		if(!this.getWarrant().equals(that.getWarrant())){
			return false;
		}
		if(!this.getCause().equals(that.getCause())){
			return false;
		}

		if(!this.getCondition().equals(that.getCondition())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getWarrant() == null){
			throw new IllegalStateException("Warrant required.");
		}
		if(this.getCause() == null){
			throw new IllegalStateException("Cause required.");
		}
		if(this.getCondition() == null){
			throw new IllegalStateException("Condition required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getWarrant().hashCode();
		hashCode = 29 * hashCode + this.getCause().hashCode();
		hashCode = 29 * hashCode + this.getCondition().hashCode();
		
		return hashCode;
	}
}
