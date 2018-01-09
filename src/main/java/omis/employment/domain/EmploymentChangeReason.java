package omis.employment.domain;

import java.io.Serializable;

/**
 * Employment Change Reason
 * 
 * @author: Yidong Li
 * @version: 0.1.0 (Jan 30, 2015)
 * @since: OMIS 3.0
 */
public interface EmploymentChangeReason extends Serializable{

	/**
	 * Returns the employment change reason id.
	 * 
	 * @return employment change reason id
	 */
	Long getId();
	
	/**
	 * Sets the employment change reason id
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Sets the employment change reason name.
	 * 
	 * @param name employment change reason name
	 */
	void setName(String name);
	
	/**
	 * Gets the employment change reason.
	 * 
	 * @return employment change reason name
	 */
	String getName();
	
	/**
	 * Sets sort order
	 * 
	 * @param sortOrder sort order
	 */
	void setSortOrder(Short sortOrder);
	
	/**
	 * Gets sort order.
	 * 
	 * @return sortOrder sort order
	 */
	Short getSortOrder();
	
	/**
	 * Sets if this employment change reason is valid or not
	 * 
	 * @param valid valid or invalid
	 */
	void setValid(Boolean valid);
	
	/**
	 * Gets if this employment change reason is valid or invalid.
	 * 
	 * @return valid valid or invalid
	 */
	Boolean getValid();
	
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