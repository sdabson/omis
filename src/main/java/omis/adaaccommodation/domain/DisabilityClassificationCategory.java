package omis.adaaccommodation.domain;

import omis.audit.domain.Creatable;

/**
* ADA disability classification category. 
*
* @author Sheronda Vaughn
* @version 0.1.0 (Jul 14, 2015)
* @since OMIS 3.0
*/
public interface DisabilityClassificationCategory 
	extends Creatable {
	
	/**
	 * Returns the ID of the ADA disability classification category.
	 * 
	 * @return id
	 */
	Long getId(); 
	
	/**
	 * Sets the ID of the ADA disability classification category.
	 * 
	 * @param id ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the name of the ADA disability classification category.
	 * 
	 * @return name
	 */
	String getName();
	
	/**
	 * Sets the name of the ADA disability classification category.
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