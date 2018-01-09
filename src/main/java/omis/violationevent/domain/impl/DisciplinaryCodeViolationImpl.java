package omis.violationevent.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.violationevent.domain.DisciplinaryCodeViolation;
import omis.violationevent.domain.ViolationEvent;

/**
 * DisciplinaryCodeViolationImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 17, 2017)
 *@since OMIS 3.0
 *
 */
public class DisciplinaryCodeViolationImpl implements DisciplinaryCodeViolation {

	private static final long serialVersionUID = 1L;
	
	private DisciplinaryCode disciplinaryCode;
	
	private ViolationEvent violationEvent;
	
	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Default Constructor For DisciplinaryCodeViolationImpl
	 */
	public DisciplinaryCodeViolationImpl() {
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
	public DisciplinaryCode getDisciplinaryCode() {
		return this.disciplinaryCode;
	}

	/**{@inheritDoc} */
	@Override
	public void setDisciplinaryCode(final DisciplinaryCode disciplinaryCode) {
		this.disciplinaryCode = disciplinaryCode;
	}

	/**{@inheritDoc} */
	@Override
	public ViolationEvent getViolationEvent() {
		return this.violationEvent;
	}

	/**{@inheritDoc} */
	@Override
	public void setViolationEvent(final ViolationEvent violationEvent) {
		this.violationEvent = violationEvent;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof DisciplinaryCodeViolation)){
			return false;
		}
		
		DisciplinaryCodeViolation that = (DisciplinaryCodeViolation) obj;
		
		if(this.getDisciplinaryCode() == null){
			throw new IllegalStateException("DisciplinaryCode required.");
		}
		if(this.getViolationEvent() == null){
			throw new IllegalStateException("ViolationEvent required.");
		}
		
		if(!this.getDisciplinaryCode().equals(that.getDisciplinaryCode())){
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
		if(this.getDisciplinaryCode() == null){
			throw new IllegalStateException("DisciplinaryCode required.");
		}
		if(this.getViolationEvent() == null){
			throw new IllegalStateException("ViolationEvent required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getDisciplinaryCode().hashCode();
		hashCode = 29 * hashCode + this.getViolationEvent().hashCode();
		
		return hashCode;
	}
	
}
