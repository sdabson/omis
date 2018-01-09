package omis.violationevent.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.violationevent.domain.ViolationEvent;
import omis.violationevent.domain.ViolationEventNote;

/**
 * ViolationEventNoteImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jan 17, 2017)
 *@since OMIS 3.0
 *
 */
public class ViolationEventNoteImpl implements ViolationEventNote {

	private static final long serialVersionUID = 1L;
	
	private String description;
	
	private Date date;
	
	private ViolationEvent violationEvent;
	
	private Long id;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature;
	
	/**
	 * Default Constructor for ViolationEventNoteImpl
	 */
	public ViolationEventNoteImpl() {
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
	public String getDescription() {
		return this.description;
	}

	/**{@inheritDoc} */
	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	/**{@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/**{@inheritDoc} */
	@Override
	public void setDate(final Date date) {
		this.date = date;
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
		if(!(obj instanceof ViolationEventNote)){
			return false;
		}
		
		ViolationEventNote that = (ViolationEventNote) obj;
		
		if(this.getViolationEvent() == null){
			throw new IllegalStateException("ViolationEvent required.");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date required.");
		}
		if(this.getDescription() == null){
			throw new IllegalStateException("Description required.");
		}
		
		if(!this.getViolationEvent().equals(that.getViolationEvent())){
			return false;
		}
		if(!this.getDate().equals(that.getDate())){
			return false;
		}
		if(!this.getDescription().equals(that.getDescription())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getViolationEvent() == null){
			throw new IllegalStateException("ViolationEvent required.");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date required.");
		}
		if(this.getDescription() == null){
			throw new IllegalStateException("Description required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getViolationEvent().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		hashCode = 29 * hashCode + this.getDescription().hashCode();
		
		return hashCode;
	}
}
