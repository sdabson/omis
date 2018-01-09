package omis.substancetest.domain;

import java.io.Serializable;

import omis.substance.domain.Substance;

/**
 * Substance Test Result.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 4, 2013)
 * @since OMIS 3.0
 */
public interface SubstanceTestResult extends Serializable {
	
	/**
	 * Return the id of the substance test result.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Set the id of the substance test result.
	 * 
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Return the substance of the substance test result.
	 * 
	 * @return the substance
	 */
	Substance getSubstance();

	/**
	 * Set the substance of the substance test result.
	 * 
	 * @param substance the substance to set
	 */
	void setSubstance(Substance substance);

	/**
	 * Return the substance test of the substance test result.
	 * 
	 * @return the substanceTest
	 */
	SubstanceTest getSubstanceTest();

	/**
	 * Set the substance of the substance test result.
	 * 
	 * @param substanceTest the substanceTest to set
	 */
	void setSubstanceTest(SubstanceTest substanceTest);
	
	/**
	 * Return the substance use admission of the substance test result.
	 * 
	 * @return the substance use admission value
	 */
	Boolean getSubstanceUseAdmission();

	/**
	 * Set the substance use admission of the substance test result.
	 * 
	 * @param substanceUseAdmission the substanceUseAdmission to set
	 */
	void setSubstanceUseAdmission(Boolean substanceUseAdmission);
	
	/**
	 * Return the admission before test value of the substance test result.
	 * 
	 * @return the admission prior to test value
	 */
	Boolean getAdmissionPriorToTest();
	
	/**
	 * Set the admission before test value of the substance test result.
	 * 
	 * @param admissionPriorToTest admission prior to test
	 */
	void setAdmissionPriorToTest(Boolean admissionPriorToTest);

	/**
	 * Return the substance test result value.
	 * 
	 * @return substance test result value
	 */
	SubstanceTestResultValue getValue();

	/**
	 * Set the substance test result value.
	 * 
	 * @param value the result to set
	 */
	void setValue(SubstanceTestResultValue value);
	
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