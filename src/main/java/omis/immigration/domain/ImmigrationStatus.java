package omis.immigration.domain;

import java.io.Serializable;

/**
 * Immigration status.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Jan 11, 2013)
 * @since OMIS 3.0
 */
public interface ImmigrationStatus
		extends Serializable {

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
	 * Sets the name.
	 * @param name name
	 */
	void setName(String name);
	
	/**
	 * Returns the name.
	 * @return name
	 */
	String getName();
	
	/**
	 * Sets the description.
	 * @param description description
	 */
	void setDescription(String description);
	
	/**
	 * Returns the description.
	 * @return description
	 */
	String getDescription();
	
	/**
	 * Sets whether the status is valid.
	 * @param valid whether status is valid
	 */
	void setValid(Boolean valid);
	
	/**
	 * Returns whether the status is valid.
	 * @return whether status is valid
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
	
	/**
	 * Returns a string representation of the immigration status including the
	 * name and description.
	 * @return string representation of status including name and description
	 */
	@Override
	String toString();
}