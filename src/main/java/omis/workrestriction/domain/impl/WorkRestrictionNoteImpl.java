package omis.workrestriction.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.workrestriction.domain.WorkRestriction;
import omis.workrestriction.domain.WorkRestrictionNote;

/**
 * WorkRestrictionNoteImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Sep 25, 2016)
 *@since OMIS 3.0
 *
 */
public class WorkRestrictionNoteImpl implements WorkRestrictionNote {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private WorkRestriction workRestriction;
	
	private Date date;
	
	private String value;
	
	private CreationSignature creationSignature;
	
	private UpdateSignature updateSignature; 
	
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
	public void setId(Long id) {
		this.id = id;
	}

	/**{@inheritDoc} */
	@Override
	public WorkRestriction getWorkRestriction() {
		return this.workRestriction;
	}

	/**{@inheritDoc} */
	@Override
	public void setWorkRestriction(WorkRestriction workRestriction) {
		this.workRestriction = workRestriction;
	}

	/**{@inheritDoc} */
	@Override
	public Date getDate() {
		return this.date;
	}

	/**{@inheritDoc} */
	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	/**{@inheritDoc} */
	@Override
	public String getValue() {
		return this.value;
	}

	/**{@inheritDoc} */
	@Override
	public void setValue(String value) {
		this.value = value;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof WorkRestrictionNote)){
			return false;
		}
		
		WorkRestrictionNote that = (WorkRestrictionNote) obj;
		
		if(this.getValue() == null){
			throw new IllegalStateException("Value required.");
		}
		if(this.getWorkRestriction() == null){
			throw new IllegalStateException("Work Restriction required.");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date required.");
		}
		
		if(!this.getValue().equals(that.getValue())){
			return false;
		}
		if(!this.getWorkRestriction().equals(that.getWorkRestriction())){
			return false;
		}
		if(!this.getDate().equals(that.getDate())){
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
		if(this.getWorkRestriction() == null){
			throw new IllegalStateException("Work Restriction required.");
		}
		if(this.getDate() == null){
			throw new IllegalStateException("Date required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getValue().hashCode();
		hashCode = 29 * hashCode + this.getWorkRestriction().hashCode();
		hashCode = 29 * hashCode + this.getDate().hashCode();
		
		return hashCode;
	}
}
