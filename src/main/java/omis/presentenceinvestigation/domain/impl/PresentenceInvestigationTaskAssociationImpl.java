package omis.presentenceinvestigation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociation;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskSource;
import omis.task.domain.Task;

/**
 * PresentenceInvestigationTaskAssociationImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 11, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationTaskAssociationImpl
		implements PresentenceInvestigationTaskAssociation {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Task task;
	
	private PresentenceInvestigationRequest presentenceInvestigationRequest;
	
	private PresentenceInvestigationTaskSource taskSource;
	
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
	public Task getTask() {
		return this.task;
	}

	/**{@inheritDoc} */
	@Override
	public void setTask(final Task task) {
		this.task = task;
	}
	
	/**
	 * Returns the presentenceInvestigationRequest
	 * @return presentenceInvestigationRequest - PresentenceInvestigationRequest
	 */
	public PresentenceInvestigationRequest getPresentenceInvestigationRequest() {
		return presentenceInvestigationRequest;
	}

	/**
	 * Sets the presentenceInvestigationRequest
	 * @param presentenceInvestigationRequest - PresentenceInvestigationRequest
	 */
	public void setPresentenceInvestigationRequest(
			final PresentenceInvestigationRequest
				presentenceInvestigationRequest) {
		this.presentenceInvestigationRequest = presentenceInvestigationRequest;
	}

	/**
	 * Returns the taskSource
	 * @return taskSource - PresentenceInvestigationTaskSource
	 */
	public PresentenceInvestigationTaskSource getTaskSource() {
		return taskSource;
	}

	/**
	 * Sets the taskSource
	 * @param taskSource - PresentenceInvestigationTaskSource
	 */
	public void setTaskSource(
			final PresentenceInvestigationTaskSource taskSource) {
		this.taskSource = taskSource;
	}

	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof PresentenceInvestigationTaskAssociation)){
			return false;
		}
		
		PresentenceInvestigationTaskAssociation that =
				(PresentenceInvestigationTaskAssociation) obj;
		
		if(this.getTask() == null){
			throw new IllegalStateException("Task required.");
		}
		if(this.getTaskSource() == null){
			throw new IllegalStateException("TaskSource required.");
		}
		if(this.getPresentenceInvestigationRequest() == null){
			throw new IllegalStateException(
					"PresentenceInvestigationRequest required.");
		}
		
		if(!this.getTask().equals(that.getTask())){
			return false;
		}
		if(!this.getTaskSource().equals(that.getTaskSource())){
			return false;
		}
		if(!this.getPresentenceInvestigationRequest().equals(
				that.getPresentenceInvestigationRequest())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getTask() == null){
			throw new IllegalStateException("Task required.");
		}
		if(this.getTaskSource() == null){
			throw new IllegalStateException("TaskSource required.");
		}
		if(this.getPresentenceInvestigationRequest() == null){
			throw new IllegalStateException(
					"PresentenceInvestigationRequest required.");
		}
		
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getTask().hashCode();
		hashCode = 29 * hashCode + this.getTaskSource().hashCode();
		hashCode = 29 * hashCode +
				this.getPresentenceInvestigationRequest().hashCode();
		
		return hashCode;
	}
	
	
}
