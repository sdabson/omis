package omis.presentenceinvestigation.domain.impl;

import omis.presentenceinvestigation.domain.PresentenceInvestigationCategory;
import omis.presentenceinvestigation.domain.PresentenceInvestigationTaskGroup;
import omis.task.domain.TaskTemplateGroup;

/**
 * PresentenceInvestigationTaskGroupImpl.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 11, 2017)
 *@since OMIS 3.0
 *
 */
public class PresentenceInvestigationTaskGroupImpl
		implements PresentenceInvestigationTaskGroup {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private TaskTemplateGroup group;
	
	private PresentenceInvestigationCategory presentenceInvestigationCategory;

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
	public TaskTemplateGroup getGroup() {
		return this.group;
	}

	/**{@inheritDoc} */
	@Override
	public void setGroup(final TaskTemplateGroup group) {
		this.group = group;
	}
	
	/**{@inheritDoc} */
	@Override
	public PresentenceInvestigationCategory getPresentenceInvestigationCategory() {
		return this.presentenceInvestigationCategory;
	}
	
	/**{@inheritDoc} */
	@Override
	public void setPresentenceInvestigationCategory(
			final PresentenceInvestigationCategory
				presentenceInvestigationCategory) {
		this.presentenceInvestigationCategory = presentenceInvestigationCategory;
	}
	
	/**{@inheritDoc}*/
	@Override
	public boolean equals(final Object obj){
		if(this == obj){
			return true;
		}
		if(!(obj instanceof PresentenceInvestigationTaskGroup)){
			return false;
		}
		
		PresentenceInvestigationTaskGroup that =
				(PresentenceInvestigationTaskGroup) obj;
		
		if(this.getGroup() == null){
			throw new IllegalStateException("Group required.");
		}
		if(this.getPresentenceInvestigationCategory() == null){
			throw new IllegalStateException(
					"PresentenceInvestigationCategory required.");
		}
		
		if(!this.getGroup().equals(that.getGroup())){
			return false;
		}
		if(!this.getPresentenceInvestigationCategory().equals(
				that.getPresentenceInvestigationCategory())){
			return false;
		}
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if(this.getGroup() == null){
			throw new IllegalStateException("Group required.");
		}
		if(this.getPresentenceInvestigationCategory() == null){
			throw new IllegalStateException(
					"PresentenceInvestigationCategory required.");
		}
		
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getGroup().hashCode();
		hashCode = 29 * hashCode +
				this.getPresentenceInvestigationCategory().hashCode();
		
		return hashCode;
	}
	
}
