package omis.demographics.domain;

import java.io.Serializable;

/**
 * Hair color.
 * @author Stephen Abson
 * @version 0.1.1 (Dec 23, 2012)
 * @since OMIS 3.0
 */
public interface HairColor extends Serializable {

	/**
	 * Sets the ID of the hair color.
	 * @param id hair color ID
	 */
	void setId(Long id);
	
	/**
	 * Returns the ID of the hair color.
	 * @return hair color ID
	 */
	Long getId();
	
	/**
	 * Sets the hair color name.
	 * @param name hair color name
	 */
	void setName(String name);

	/**
	 * Returns the hair color name.
	 * @return hair color name
	 */
	String getName();

	/**
	 * Sets whether the hair color is valid.
	 * @param valid whether hair color is valid
	 */
	void setValid(Boolean valid);

	/**
	 * Returns whether the hair color is valid.
	 * @return valid whether hair color is valid
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
	 * Returns a hash code for the object.
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
	
	/**
	 * Returns a string representation of the hair color including the name
	 * and whether the hair color is valid.
	 * @return string containing the hair color name and whether the hair color
	 * is valid
	 */
	@Override
	String toString();
}