package omis.physicalfeature.domain;

import java.io.Serializable;

/**
 * Physical feature.
 * @author Joel Norris
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public interface PhysicalFeature extends Serializable {

	/**
	 * Returns the id of the physical feature.
	 * @return id Long
	 */
	Long getId();

	/**
	 * Sets the id of the physical feature.
	 * @param id id
	 */
	void setId(Long id);

	/**
	 * Returns the name of the physical feature.
	 * @return name String
	 */
	String getName();

	/**
	 * Sets the name of the physical feature.
	 * @param name name
	 */
	void setName(String name);

	/**
	 * Returns the feature classification of the physical feature.
	 * @return featureClassification FeatureClassification
	 */
	FeatureClassification getFeatureClassification();

	/**
	 * Sets the feature classification of the physical feature.
	 * @param featureClassification feature classification
	 */
	void setFeatureClassification(FeatureClassification 
			featureClassification);

	/**
	 * Returns the valid status of the physical feature.
	 * @return valid Boolean
	 */
	Boolean getValid();

	/**
	 * Sets the valid status of the physical feature.
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