package omis.supervisionfee.domain;

import omis.audit.domain.Creatable;

/**
 * Reasons for supervisionFeeRequirement.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 27, 2014)
 * @since OMIS 3.0
 */
public interface SupervisionFeeRequirementReason extends Creatable {

	/**
	 * Returns the id of the supervision fee requirement reason.
	 * 
	 * @return id
	 */
	Long getId(); 
	
	/**
	 * Sets the id of the supervision fee requirement reason.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the name of the supervision fee requirement reason.
	 * 
	 * @return name
	 */
	String getName();
	
	/**
	 * Sets the name of the supervision fee requirement reason.
	 * 
	 * @param name name
	 */
	void setName(String name);
	
	/**
	 * Returns whether {@code this} is valid.
	 * 
	 * @return valid
	 */
	Boolean getValid();
	
	/**
	 * Sets whether {@code this} is valid.
	 * 
	 * @param valid valid
	 */
	void setValid(Boolean valid);

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
