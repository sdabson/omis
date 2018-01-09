package omis.substancetest.domain;

import java.io.Serializable;

import omis.substance.domain.Substance;

/**
 * Test Kit.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 11, 2013)
 * @since OMIS 3.0
 */
public interface TestKitParameter extends Serializable {

	/**
	 * Return the id of the test kit parameter.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Set the id of the test kit parameter.
	 * 
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Return the substance of the test kit parameter.
	 * 
	 * @return the substance
	 */
	Substance getSubstance();

	/**
	 * Set the substance of the test kit parameter.
	 * 
	 * @param substance the substance to set
	 */
	void setSubstance(Substance substance);

	/**
	 * Return the test kit of the test kit parameter.
	 * 
	 * @return the testKit
	 */
	TestKit getTestKit();

	/**
	 * Set the test kit of the test kit parameter.
	 * 
	 * @param testKit the testKit to set
	 */
	void setTestKit(TestKit testKit);
	
	/**
	 * Returns the default result value.
	 * 
	 * @return default result value
	 */
	SubstanceTestResultValue getDefaultResultValue();
	
	/**
	 * Sets the default result value.
	 * 
	 * @param defaultResultValue default result value
	 */
	void setDefaultResultValue(SubstanceTestResultValue defaultResultValue);
	
	/**
	 * Return the valid status of the test kit parameter.
	 * 
	 * @return valid boolean
	 */
	Boolean getValid();
	
	/**
	 * Set the valid value of the test kit parameter.
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