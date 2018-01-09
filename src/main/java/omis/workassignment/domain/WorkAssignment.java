package omis.workassignment.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.OffenderAssociable;

/**
 * WorkAssignment.
 * @author Yidong Li
 * @version 0.1.0 (Aug 17, 2016)
 * @since OMIS 3.0
 */
public interface WorkAssignment
		extends Creatable, Updatable, OffenderAssociable {
	/**
	 * Sets the ID.
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets fenceRestriction.
	 * 
	 * @param fenceRestriction fence restriction
	 */
	void setFenceRestriction(FenceRestriction fenceRestriction);
	
	/**
	 * Returns the fenceRestriction.
	 * 
	 * @return fenceRestriction fence restriction
	 */
	FenceRestriction getFenceRestriction();
	
	/**
	 * Sets the work assignment category.
	 * @param category work assignment category
	 */
	void setCategory(WorkAssignmentCategory category);
	
	/**
	 * Returns the work assignment category.
	 * @return work assignment category
	 */
	WorkAssignmentCategory getCategory();
	
	/**
	 * Sets the work assignment change reason.
	 * 
	 * @param changeReason work assignment change reason
	 */
	void setChangeReason(WorkAssignmentChangeReason changeReason);
	
	/**
	 * Returns the work assignment change reason.
	 * 
	 * @return changeReason work assignment change reason
	 */
	WorkAssignmentChangeReason getChangeReason();
	
	/**
	 * Sets the assignedDate.
	 * @param assignedDate assigned date
	 */
	void setAssignedDate(Date assignedDate);
	
	/**
	 * Returns the assignedDate. 
	 * @return assignedDate assigned date
	 */
	Date getAssignedDate();
	
	/**
	 * Sets the terminationDate.
	 * @param terminationDate termination date
	 */
	void setTerminationDate(Date terminationDate);
	
	/**
	 * Returns the terminationDate.
	 * @return terminationDate termination date
	 */
	Date getTerminationDate();
	
	/**
	 * Sets the comments.
	 * @param comments comments
	 */
	void setComments(String comments);
	
	/**
	 * Returns the comments.
	 * @return notes
	 */
	String getComments();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}