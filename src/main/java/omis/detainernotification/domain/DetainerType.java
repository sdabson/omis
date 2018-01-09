package omis.detainernotification.domain;

import java.io.Serializable;

/**
 * Detainer type.
 *
 * @author Sheronda Vaughn
 * @author Joel Norris
 * @version 0.1.1 (May 25, 2016)
 * @since OMIS 3.0
 */
public interface DetainerType extends Serializable {

	/**
	 * Sets the id of the detainer type.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the id of the detainer type.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the name of the detainer type.
	 * 
	 * @param name name
	 */
	void setName(String name);
	
	/**
	 * Returns the name of the detainer type.
	 * 
	 * @return name
	 */
	String getName();
	
	/**
	 * Sets whether valid applies.
	 * 
	 * @param valid valid
	 */
	void setValid(Boolean valid);
	
	/**
	 * Returns whether valid applies.
	 * 
	 * @return valid
	 */
	Boolean getValid();
	
	/**
	 * Sets the sort order.
	 * 
	 * @param sortOrder sort order
	 */
	void setSortOrder(Short sortOrder);
	
	/**
	 * Returns the sort order.
	 * 
	 * @param sortOrder sort order
	 * @return
	 */
	Short getSortOrder();
	
	/**
	 * Sets the detainer interpretation.
	 * 
	 * @param interpretation detainer interpretation
	 */
	void setInterpretation(DetainerInterpretation interpretation);
	
	/**
	 * Returns the detainer interpretation.
	 * 
	 * @return interpretation detainer interpretation
	 */
	DetainerInterpretation getInterpretation();
	
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
