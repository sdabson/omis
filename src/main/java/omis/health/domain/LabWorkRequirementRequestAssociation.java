package omis.health.domain;

import java.io.Serializable;

/**
 * Associates a lab work requirement for a request for a lab work requirement.
 * 
 * <p>If this association exists for a requirement request, the request has
 * been scheduled.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jun 3, 2014)
 * @since OMIS 3.0
 */
public interface LabWorkRequirementRequestAssociation
		extends Serializable {

	/**
	 * Sets the ID.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID.
	 * 
	 * @return ID
	 */
	Long getId();
	
	/**
	 * Sets the request.
	 * 
	 * @param request request
	 */
	void setRequest(LabWorkRequirementRequest request);
	
	/**
	 * Returns the request.
	 * 
	 * @return request
	 */
	LabWorkRequirementRequest getRequest();
	
	/**
	 * Sets the requirement.
	 * 
	 * @param requirement requirement
	 */
	void setRequirement(LabWorkRequirement requirement);
	
	/**
	 * Returns the requirement.
	 * 
	 * @return requirement
	 */
	LabWorkRequirement getRequirement();
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
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
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();
}