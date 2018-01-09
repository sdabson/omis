package omis.substancetest.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.substancetest.domain.SampleCollectionMethod;
import omis.substancetest.domain.SampleRequest;
import omis.substancetest.domain.SampleRequestStatusReason;
import omis.substancetest.domain.SubstanceTest;
import omis.substancetest.domain.SubstanceTestReason;
import omis.substancetest.domain.SubstanceTestSample;
import omis.substancetest.domain.component.SampleRequestResolution;

/**
 * Substance Test Sample Service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 11, 2013)
 * @since OMIS 3.0
 */
public interface SubstanceTestSampleService {
	
	/**
	 * Save the specified substance test sample.
	 * 
	 * @param substanceTestSample substance test sample
	 * @return substance test sample
	 */
	SubstanceTestSample save(SubstanceTestSample substanceTestSample);
	
	/**
	 * Return a list of all substance test samples.
	 * 
	 * @return list of substance test samples
	 */
	List<SubstanceTestSample> findAll();

	/**
	 * Return a list of all substance test samples with the specified
	 * offender.
	 * @param offender offender
	 * @return list of substance test samples
	 */
	List<SubstanceTestSample> findSamplesByOffender(Offender offender);
	
	/**
	 * Creates a substance test sample for the specified offender.
	 * 
	 * @param offender offender
	 * @param collectionDate collection date
	 * @param collectionEmployee collection employee
	 * @param collectionMethod sample collection method
	 * @param reason substance test reason
	 * @param random random
	 * @return substance test sample
	 * @throws DuplicateEntityFoundException thrown when a duplicate substance
	 * test sample is found
	 */
	SubstanceTestSample create(Offender offender, Date collectionDate, 
			Person collectionEmployee, SampleCollectionMethod collectionMethod,
			SubstanceTestReason reason, Boolean random)
		throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified substance test sample.
	 * 
	 * @param substanceTestSample substance test sample
	 * @param offender offender
	 * @param collectionDate collection date
	 * @param collectionEmployee collection employee
	 * @param collectionMethod collection method
	 * @param reason substance test reason
	 * @param random random
	 * @param statusReason sample status reason
	 * @return updated substance test sample
	 * @throws DuplicateEntityFoundException thrown when a duplicate substance
	 * test sample is found
	 */
	SubstanceTestSample update(SubstanceTestSample substanceTestSample,
			Offender offender, Date collectionDate, 
			Person collectionEmployee, SampleCollectionMethod collectionMethod,
			SubstanceTestReason reason, Boolean random,
			SampleRequestStatusReason statusReason)
		throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified substance test sample.
	 * 
	 * @param substanceTestSample substance test sample
	 */
	void remove(SubstanceTestSample substanceTestSample);
	
	/**
	 * Returns a list of sample collection methods.
	 * 
	 * @return list of sample collection methods
	 */
	List<SampleCollectionMethod> findCollectionMethods();
	
	/**
	 * Returns a list of substance test reasons.
	 * 
	 * @return substance test reasons
	 */
	List<SubstanceTestReason> findSubstanceTestReasons();

	/**
	 * Returns the substance test for the specified substance test sample.
	 * 
	 * @param substanceTestSample substance test sample
	 * @return substance test
	 */
	SubstanceTest findSubstanceTestBySample(
			SubstanceTestSample substanceTestSample);

	/**
	 * Removes the specified substance test.
	 * 
	 * @param substanceTest substance test
	 */
	void removeSubstanceTest(SubstanceTest substanceTest);

	/**
	 * Returns sample status reasons with the specified taken value.
	 * 
	 * @return list of sample status reasons
	 */
	List<SampleRequestStatusReason> findStatusReasons();

	/**
	 * Returns the request associated the specified substance test sample.
	 * 
	 * @param substanceTestSample substance test sample
	 * @return substance test sample request
	 */
	SampleRequest findRequestBySample(
			SubstanceTestSample substanceTestSample);
	
	/**
	 * Updates the specified request's resolution.
	 * 
	 * @param request sample request
	 * @param resolution sample request resolution
	 * @return updated sample request and resolution
	 * @throws DuplicateEntityFoundException thrown when a duplicate sample
	 * request is found
	 */
	SampleRequest updateRequestResolution(SampleRequest request,
			SampleRequestResolution resolution)
		throws DuplicateEntityFoundException;
	
	/**
	 * Resolves the specified request by adding a resolution.
	 * 
	 * @param request sample request
	 * @param resolution sample request resolution
	 * @return updated sample request with newly created resolution
	 * @throws DuplicateEntityFoundException thrown when a duplicate sample
	 * request is found
	 */
	SampleRequest resolveRequest(SampleRequest request,
			SampleRequestResolution resolution)
		throws DuplicateEntityFoundException;
	
	
}