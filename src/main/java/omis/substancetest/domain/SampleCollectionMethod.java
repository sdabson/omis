package omis.substancetest.domain;

import java.io.Serializable;

/**
 * Collection Method.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 4, 2013)
 * @since OMIS 3.0
 */
public interface SampleCollectionMethod extends Serializable {

	/**
	 * Returns the id of the collection method.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Sets the id of the collection method.
	 * 
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Returns the name of the collection method.
	 * 
	 * @return the name
	 */
	String getName();

	/**
	 * Sets the name of the collection method.
	 * 
	 * @param name the name to set
	 */
	void setName(String name);

	/**
	 * Returns the description of the collection method.
	 * 
	 * @return the description
	 */
	String getDescription();

	/**
	 * Sets the description of the collection method.
	 * 
	 * @param description the description to set
	 */
	void setDescription(String description);
	
	/**
	 * Return the valid status of the collection method.
	 * 
	 * @return valid boolean
	 */
	Boolean getValid();
	
	/**
	 * Set the valid value of the collection method.
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
