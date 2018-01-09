package omis.specialneed.domain;

import java.io.Serializable;

/**
 * Special need category.
 * 
 * @author Joel Norris 
 * @author Sheronda Vaughn
 * @version 0.1.1 (Sep 01, 2016)
 * @since OMIS 3.0
 */
public interface SpecialNeedCategory extends Serializable {

	/**
	 * Returns the id of the category.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Sets the id of the category.
	 * 
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Returns the name of the category.
	 * @return the name
	 */
	String getName();

	/**
	 * Sets the name of the category.
	 * 
	 * @param name the name to set
	 */
	void setName(String name);
	
	/**
	 * Returns the valid value of the category.
	 * 
	 * @return the valid
	 */
	Boolean getValid();

	/**
	 * sets the valid value of the category.
	 * 
	 * @param valid the valid to set
	 */
	void setValid(Boolean valid);
	
	/**
	 * Returns the classification of the special need category.
	 *
	 *
	 * @return the classification
	 */
	SpecialNeedClassification getClassification();
	
	/**
	 * Sets the classification of the special need category.
	 *
	 *
	 * @param classification classification
	 */
	void setClassification(SpecialNeedClassification classification);
	
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