package omis.violationevent.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.condition.domain.Condition;
import omis.violationevent.domain.ConditionViolation;
import omis.violationevent.domain.ViolationEvent;

/**
 * ConditionViolationImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Feb 15, 2017)
 *@since OMIS 3.0
 *
 */
public class ConditionViolationImpl implements ConditionViolation {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Condition condition;
	
	private ViolationEvent violationEvent;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
		
	/**
	 * Default Constructor for ConditionViolationImpl 
	 */
	public ConditionViolationImpl() {
	}
	
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
	public Condition getCondition() {
		return this.condition;
	}

	/**{@inheritDoc} */
	@Override
	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	/**{@inheritDoc} */
	@Override
	public ViolationEvent getViolationEvent() {
		return this.violationEvent;
	}

	/**{@inheritDoc} */
	@Override
	public void setViolationEvent(ViolationEvent violationEvent) {
		this.violationEvent = violationEvent;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof ConditionViolation)){
			return false;
		}
		
		ConditionViolation that = (ConditionViolation) obj;
		
		if(this.getCondition() == null){
			throw new IllegalStateException("Condition required.");
		}
		if(this.getViolationEvent() == null){
			throw new IllegalStateException("Violation Event required.");
		}
		
		if(!this.getCondition().equals(that.getCondition())){
			return false;
		}
		if(!this.getViolationEvent().equals(that.getViolationEvent())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getCondition() == null){
			throw new IllegalStateException("Condition required.");
		}
		if(this.getViolationEvent() == null){
			throw new IllegalStateException("Violation Event required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getCondition().hashCode();
		hashCode = 29 * hashCode + this.getViolationEvent().hashCode();
		
		return hashCode;
	}
	
	
}
