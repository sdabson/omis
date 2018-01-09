package omis.physicalfeature.domain;

import java.io.Serializable;

/**
 * Feature classification.
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public interface FeatureClassification extends Serializable {

	/**
	 * Returns the id of the feature classification.
	 * @return id Long
	 */
	Long getId();

	/**
	 * Sets the id of the feature classification.
	 * @param id id
	 */
	void setId(Long id);

	/**
	 * Return the name of the feature classification.
	 * @return name String
	 */
	String getName();

	/**
	 * Sets the name of the feature classification.
	 * @param name name
	 */
	void setName(String name);

	/**
	 * Returns the description of the feature classification.
	 * @return description
	 */
	String getDescripiton();

	/**
	 * Sets the description of the feature classification.
	 * @param descripiton description
	 */
	void setDescripiton(String descripiton);

	/**
	 * Returns the sort order of the feature classification.
	 * @return sort order
	 */
	Integer getSortOrder();

	/**
	 * Sets the sort order of the feature classification.
	 * @param sortOrder sort order
	 */
	void setSortOrder(Integer sortOrder);
	
	/**
	 * Returns the valid status of the feature classification.
	 * @return valid Boolean
	 */
	Boolean getValid();

	/**
	 * Sets the valid status of the feature classification.
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