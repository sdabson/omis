package omis.presentenceinvestigation.domain;

import java.io.Serializable;

import omis.task.domain.TaskTemplateGroup;

/**
 * PresentenceInvestigationTaskGroup.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jun 11, 2017)
 *@since OMIS 3.0
 *
 */
public interface PresentenceInvestigationTaskGroup extends Serializable{
	
	/**
	 * Returns the ID of the PresentenceInvestigationTaskGroup
	 * @return ID
	 */
	public Long getId();
	
	/**
	 * Sets the ID of the PresentenceInvestigationTaskGroup
	 * @param id - Long
	 */
	public void setId(Long id);
	
	/**
	 * Returns the Group for the PresentenceInvestigationTaskGroup
	 * @return group - TaskTemplateGroup
	 */
	public TaskTemplateGroup getGroup();
	
	/**
	 * Sets the Group for the PresentenceInvestigationTaskGroup
	 * @param group - TaskTemplateGroup
	 */
	public void setGroup(TaskTemplateGroup group);
	
	/**
	 * Returns the PresentenceInvestigationCategory for the
	 * PresentenceInvestigationTaskGroup
	 * @return presentenceInvestigationCategory -
	 * PresentenceInvestigationCategory
	 */
	public PresentenceInvestigationCategory getPresentenceInvestigationCategory();
	
	/**
	 * Sets the PresentenceInvestigationCategory for the
	 * PresentenceInvestigationTaskGroup
	 * @param presentenceInvestigationCategory - PresentenceInvestigationCategory
	 */
	public void setPresentenceInvestigationCategory(
			PresentenceInvestigationCategory presentenceInvestigationCategory);
	
	/** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();
	
}
