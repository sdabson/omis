package omis.demographics.domain;

import java.io.Serializable;

/**
 * Eye color.
 * @author Stephen Abson
 * @version 0.1.1 (Dec 23, 2012)
 * @since OMIS 3.0
 */
public interface EyeColor extends Serializable {

	/**
	 * Sets the ID of the eye color.
	 * @param id eye color ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID of the eye color.
	 * @return eye color ID
	 */
	Long getId();
	
	/**
	 * Sets the eye color name.
	 * @param name eye color name
	 */
	void setName(String name);

	/**
	 * Returns the eye color name.
	 * @return eye color name
	 */
	String getName();


	/**
	 * Sets whether the eye color is valid.
	 * @param valid whether eye color is valid
	 */
	void setValid(Boolean valid);

	/**
	 * Returns whether the eye color is valid.
	 * @return whether eye color is valid
	 */
	Boolean getValid();

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
	 * Returns a hash name for the object.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash name. If
	 * a mandatory property that is used in the hash name is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash name
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash name is {@code null}
	 */
	@Override
	int hashCode();
	
	/**
	 * Returns a string representation of the eye color including the name
	 * and whether the eye color is valid.
	 * @return string containing name and whether eye color is valid
	 */
	@Override
	String toString();
}