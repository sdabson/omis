package omis.presentenceinvestigation.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociationCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskAssociationUsageCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskSource;
import omis.task.domain.TaskTemplate;

/**
 * PresentenceInvestigationTaskSourceImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 23, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationTaskSourceImpl
		implements PresentenceInvestigationTaskSource {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private TaskTemplate taskTemplate;
	
	private PresentenceInvestigationTaskAssociationUsageCategory usage;
	
	private PresentenceInvestigationTaskAssociationCategory category;
	
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
	public TaskTemplate getTaskTemplate() {
		return this.taskTemplate;
	}

	/**{@inheritDoc} */
	@Override
	public void setTaskTemplate(final TaskTemplate taskTemplate) {
		this.taskTemplate = taskTemplate;
	}

	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationTaskAssociationUsageCategory getUsage() {
		return this.usage;
	}

	/**{@inheritDoc} */
	@Override
	public void setUsage(
			final PresentenceInvestigationTaskAssociationUsageCategory usage) {
		this.usage = usage;
	}

	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationTaskAssociationCategory getCategory() {
		return this.category;
	}

	/**{@inheritDoc} */
	@Override
	public void setCategory(
			final PresentenceInvestigationTaskAssociationCategory category) {
		this.category = category;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof PresentenceInvestigationTaskSource)){
			return false;
		}
		
		PresentenceInvestigationTaskSource that = (PresentenceInvestigationTaskSource) obj;
		
		if(this.getTaskTemplate() == null){
			throw new IllegalStateException("TaskTemplate required.");
		}
		if(this.getUsage() == null){
			throw new IllegalStateException("Usage required.");
		}
		if(this.getCategory() == null){
			throw new IllegalStateException("Category required.");
		}
		
		if(!this.getTaskTemplate().equals(that.getTaskTemplate())){
			return false;
		}
		if(!this.getUsage().equals(that.getUsage())){
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
		if(this.getTaskTemplate() == null){
			throw new IllegalStateException("TaskTemplate required.");
		}
		if(this.getUsage() == null){
			throw new IllegalStateException("Usage required.");
		}
		if(this.getCategory() == null){
			throw new IllegalStateException("Category required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getTaskTemplate().hashCode();
		hashCode = 29 * hashCode + this.getUsage().hashCode();
		hashCode = 29 * hashCode + this.getCategory().hashCode();
		
		return hashCode;
	}
}
