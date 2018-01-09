package omis.substancetest.domain;

import java.io.Serializable;

import omis.substance.domain.Substance;

/**
 * Crime Lab Result.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jan 2, 2014)
 * @since OMIS 3.0
 */
public interface LabResult extends Serializable {

	/**
	 * Returns the id of the crime lab result.
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id of the crime lab result.
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the substance of the crime lab result.
	 * @return substance
	 */
	Substance getSubstance();
	
	/**
	 * Sets the substance of the crime lab result.
	 * @param substance substance
	 */
	void setSubstance(Substance substance);
	
	/**
	 * Returns the substance test result value.
	 * 
	 * @return substance test result value
	 */
	SubstanceTestResultValue getValue();
	
	/**
	 * Sets the substance test result value.
	 * 
	 * @param value substance test result value
	 */
	void setValue(SubstanceTestResultValue value);
	
	/**
	 * Returns the substance test of the crime lab result.
	 * @return substance test
	 */
	SubstanceTest getSubstanceTest();
	
	/**
	 * Sets the substance test of the crime lab result.
	 * @param substanceTest substance test
	 */
	void setSubstanceTest(SubstanceTest substanceTest);
	
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