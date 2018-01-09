package omis.substancetest.domain;

import java.io.Serializable;

/**
 * Test Kit.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 11, 2013)
 * @since OMIS 3.0
 */
public interface TestKit extends Serializable {

	/**
	 * Return the id of the test kit.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Set the id of the test kit.
	 * 
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Return the name of the test kit.
	 * 
	 * @return the name
	 */
	String getName();

	/**
	 * Set the name of the test kit.
	 * 
	 * @param name the name to set
	 */
	void setName(String name);
	
	/**
	 * Return the sample collection method of the test kit.
	 * 
	 * @return the sampleCollectionMethod
	 */
	SampleCollectionMethod getSampleCollectionMethod();

	/**
	 * Set the sample collection method of the test kit.
	 * 
	 * @param sampleCollectionMethod the sampleCollectionMethod to set
	 */
	void setSampleCollectionMethod(
			SampleCollectionMethod sampleCollectionMethod);
	
	/**
	 * Return the valid status of the test kit.
	 * 
	 * @return valid boolean
	 */
	Boolean getValid();
	
	/**
	 * Set the valid value of the test kit.
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