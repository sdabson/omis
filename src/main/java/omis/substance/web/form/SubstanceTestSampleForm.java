package omis.substance.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.person.domain.Person;
import omis.substancetest.domain.SampleCollectionMethod;
import omis.substancetest.domain.SampleRequest;
import omis.substancetest.domain.SampleRequestStatusReason;
import omis.substancetest.domain.SubstanceTestReason;

/**
 * Substance test sample form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 20, 2013)
 * @since OMIS 3.0
 */
public class SubstanceTestSampleForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Date collectionDate;
	
	private Date collectionTime;
	
	private Person collectionEmployee;
	
	private SampleCollectionMethod sampleCollectionMethod;
	
	private SubstanceTestReason substanceTestReason;
	
	private Boolean random;
	
	private SampleRequestStatusReason statusReason;
	
	private Boolean taken;
	
	private SampleRequest request;
	
	private Boolean saveAndContinue;
	
	/**
	 * Instantiates a default instance of substance test sample form.
	 */
	public SubstanceTestSampleForm() {
		//Default constructor.
	}

	/**
	 * Return the collection date for the substance test sample form.
	 * @return the collectionDate
	 */
	public Date getCollectionDate() {
		return this.collectionDate;
	}

	/**
	 * Set the collection date for the substance test sample form.
	 * @param collectionDate the collectionDate to set
	 */
	public void setCollectionDate(final Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	/**
	 * Return the collection time of the substance test sample form.
	 * @return the collection time
	 */
	public Date getCollectionTime() {
		return this.collectionTime;
	}

	/**
	 * Set the collection time of the substance test sample form.
	 * @param collectionTime the collectionTime to set
	 */
	public void setCollectionTime(final Date collectionTime) {
		this.collectionTime = collectionTime;
	}

	/**
	 * Return the collection employee for the substance test sample form.
	 * @return the collectionEmployee
	 */
	public Person getCollectionEmployee() {
		return this.collectionEmployee;
	}

	/**
	 * Set the collection employee for the substance test sample form.
	 * @param collectionEmployee the collectionEmployee to set
	 */
	public void setCollectionEmployee(final Person collectionEmployee) {
		this.collectionEmployee = collectionEmployee;
	}

	/**
	 * Return the sample collection method for the substance test sample form.
	 * @return the sampleCollectionMethod
	 */
	public SampleCollectionMethod getSampleCollectionMethod() {
		return this.sampleCollectionMethod;
	}

	/**
	 * Set the sample collection method for the substance test sample form.
	 * @param sampleCollectionMethod the sampleCollectionMethod to set
	 */
	public void setSampleCollectionMethod(
			final SampleCollectionMethod sampleCollectionMethod) {
		this.sampleCollectionMethod = sampleCollectionMethod;
	}	

	/**
	 * Return the substance test reason of the substance test sample form.
	 * @return the substanceTestReason
	 */
	public SubstanceTestReason getSubstanceTestReason() {
		return this.substanceTestReason;
	}

	/**
	 * Set the substance test reason of the substance test sample form.
	 * @param substanceTestReason the substanceTestReason to set
	 */
	public void setSubstanceTestReason(
			final SubstanceTestReason substanceTestReason) {
		this.substanceTestReason = substanceTestReason;
	}
	
	/**
	 * Returns the random value of the substance test sample form.
	 * @return random
	 */
	public Boolean getRandom() {
		return this.random;
	}
	
	/**
	 * Sets the random value of the substance test sample form.
	 * @param random random
	 */
	public void setRandom(final Boolean random) {
		this.random = random;
	}

	/**
	 * Returns the sample status reason.
	 * 
	 * @return sample status reason
	 */
	public SampleRequestStatusReason getStatusReason() {
		return this.statusReason;
	}

	/**
	 * Sets the sample status reason.
	 * 
	 * @param statusReason sample status reason
	 */
	public void setStatusReason(
			final SampleRequestStatusReason statusReason) {
		this.statusReason = statusReason;
	}

	/**
	 * Returns whether taken applies.
	 * 
	 * @return whether taken applies
	 */
	public Boolean getTaken() {
		return this.taken;
	}

	/**
	 * Sets whether taken applies.
	 * 
	 * @param taken taken
	 */
	public void setTaken(Boolean taken) {
		this.taken = taken;
	}

	/**
	 * Returns the sample request.
	 * 
	 * @return sample request
	 */
	public SampleRequest getRequest() {
		return this.request;
	}

	/**
	 * Sets the sample request.
	 * 
	 * @param request request
	 */
	public void setRequest(final SampleRequest request) {
		this.request = request;
	}

	/**
	 * Returns save and continue.
	 * 
	 * @return save and continue
	 */
	public Boolean getSaveAndContinue() {
		return this.saveAndContinue;
	}

	/**
	 * Sets save and continue.
	 * 
	 * @param saveAndContinue save and continue
	 */
	public void setSaveAndContinue(final Boolean saveAndContinue) {
		this.saveAndContinue = saveAndContinue;
	}
}