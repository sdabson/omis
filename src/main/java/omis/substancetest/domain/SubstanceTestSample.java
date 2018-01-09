package omis.substancetest.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.offender.domain.Offender;
import omis.offender.domain.OffenderAssociable;
import omis.person.domain.Person;

/**
 * Substance test sample.
 * 
 * @author Joel Norris
 * @version 0.1.1 (Apr 29, 2015)
 * @since OMIS 3.0
 */
public interface SubstanceTestSample 
	extends OffenderAssociable, Creatable, Updatable {
	
	/**
	 * Return the id of the test sample.
	 * 
	 * @return the id
	 */
	Long getId();

	/**
	 * Set the id of the test sample.
	 * 
	 * @param id the id to set
	 */
	void setId(Long id);

	/**
	 * Return the offender of the test sample.
	 * 
	 * @return the offender
	 */
	Offender getOffender();

	/**
	 * Set the offender of the test sample.
	 * 
	 * @param offender the offender to set
	 */
	void setOffender(Offender offender);

	/**
	 * Return the collection date of the sample.
	 * 
	 * @return the collectionDate
	 */
	Date getCollectionDate();

	/**
	 * Set the collection date of the sample.
	 * 
	 * @param collectionDate the collectionDate to set
	 */
	void setCollectionDate(Date collectionDate);

	/**
	 * Return the employee that collected the sample.
	 * 
	 * @return the collection employee
	 */
	Person getCollectionEmployee();

	/**
	 * Set the employee that collected the sample.
	 * 
	 * @param collectionEmployee the collectionEmployee to set
	 */
	void setCollectionEmployee(Person collectionEmployee);

	/**
	 * Return the collection method of the sample.
	 * 
	 * @return the collectionMethod
	 */
	SampleCollectionMethod getSampleCollectionMethod();

	/**
	 * Set the collection method of the sample.
	 * 
	 * @param sampleCollectionMethod the collectionMethod to set
	 */
	void setSampleCollectionMethod(
			SampleCollectionMethod sampleCollectionMethod);
	
	/**
	 * Return the substance test reason of the substance test.
	 * 
	 * @return the substanceTestReason
	 */
	SubstanceTestReason getSubstanceTestReason();

	/**
	 * Set the substance test reason of the substance test.
	 * 
	 * @param substanceTestReason the substanceTestReason to set
	 */
	void setSubstanceTestReason(SubstanceTestReason substanceTestReason);
	
	/**
	 * Returns the random value of the substance test sample.
	 * @return random
	 */
	Boolean getRandom();

	/**
	 * Sets the random value of the substance test sample.
	 * @param random random
	 */
	void setRandom(final Boolean random);
	
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
