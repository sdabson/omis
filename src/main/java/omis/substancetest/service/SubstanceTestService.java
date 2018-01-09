package omis.substancetest.service;

import java.util.Date;
import java.util.List;

import omis.exception.DuplicateEntityFoundException;
import omis.person.domain.Person;
import omis.substance.domain.Substance;
import omis.substance.domain.SubstanceLab;
import omis.substancetest.domain.LabResult;
import omis.substancetest.domain.SampleCollectionMethod;
import omis.substancetest.domain.SubstanceTest;
import omis.substancetest.domain.SubstanceTestResult;
import omis.substancetest.domain.SubstanceTestResultValue;
import omis.substancetest.domain.SubstanceTestSample;
import omis.substancetest.domain.TestKit;
import omis.substancetest.domain.TestKitParameter;

/**
 * Substance test service.
 * 
 * @author Joel Norris
 * @version 0.1.1 (Apr 15, 2015)
 * @since OMIS 3.0
 */
public interface SubstanceTestService {
	
	/**
	 * Save the specified substance test.
	 * 
	 * @param substanceTest substance test
	 * @return substance test
	 */
	SubstanceTest save(SubstanceTest substanceTest);
	
	/**
	 * Return a list of all substance tests.
	 * 
	 * @return list of substance tests.
	 */
	List<SubstanceTest> findAll();

	/**
	 * Find the substance test with the specified sample.
	 * 
	 * @param sample sample
	 * @return substance test
	 */
	SubstanceTest findTestBySample(SubstanceTestSample sample);
	
	/**
	 * Creates a substance test for the specified substance test sample.
	 * 
	 * @param sample substance test sample
	 * @param resultDate result date
	 * @param labInvolved whether  lab is involved
	 * @param labResultDate  lab result date
	 * @param labRequestDate  lab request date
	 * @param notes notes
	 * @param substanceLab substance lab
	 * @param privateLabJustification private lab justification
	 * @param authorizingStaff authorizing staff
	 * @return newly created substance test
	 * @throws DuplicateEntityFoundException thrown when a duplicate substance
	 * test is found
	 */
	SubstanceTest create(SubstanceTestSample sample, Date resultDate, 
			Boolean labInvolved, Date labResultDate,
			Date labRequestDate,String notes, SubstanceLab lab,
			String privateLabJustification, Person authorizingStaff)
		throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified substance test.
	 * 
	 * @param substanceTest substance test
	 * @param sample substance test sample
	 * @param resultDate result date
	 * @param labInvolved whether  lab is involved
	 * @param labResultDate  lab result date
	 * @param labRequestDate  lab request date
	 * @param notes notes
	 * @param lab substance lab
	 * @param authorizingStaff authorizing staff
	 * @return updated substance test
	 * @throws DuplicateEntityFoundException thrown when a duplicate substance
	 * test is found
	 */
	SubstanceTest update(SubstanceTest substanceTest, SubstanceTestSample sample, 
			Date resultDate, Boolean labInvolved, Date labResultDate, 
			Date labRequestDate, String notes, SubstanceLab lab,
			String privateLabJustification, Person authorizingStaff)
		throws DuplicateEntityFoundException;
	
	/**
	 * Creates a substance test result for the specified substance test.
	 * 
	 * @param substanceTest substance test
	 * @param substance substance
	 * @param value substance test result value
	 * @param substanceUseAdmission whether admission to use of substance
	 * applies
	 * @param admissionPriorToTest whether admission occurred prior to test
	 * @return newly created substance test result.
	 * @throws DuplicateEntityFoundException thrown when a duplicate substance
	 * test result is found
	 */
	SubstanceTestResult addResult(SubstanceTest substanceTest, 
			Substance substance, SubstanceTestResultValue value, 
			Boolean substanceUseAdmission, Boolean admissionPriorToTest)
		throws DuplicateEntityFoundException;
	
	/**
	 * Creates a new  lab result for the specified substance test.
	 * 
	 * @param substanceTest substance test
	 * @param substance substance
	 * @param value substance test result value
	 * @return newly created  lab result
	 * @throws DuplicateEntityFoundException thrown when a duplicate  lab
	 * result is found
	 */
	LabResult addLabResult(SubstanceTest substanceTest, 
			Substance substance, SubstanceTestResultValue value)
		throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified substance test result.
	 * 
	 * @param result substance test result
	 * @param substance substance
	 * @param value substance test result value
	 * @param substanceUseAdmission whether admission to use of substance
	 * applies
	 * @param admissionPriorToTest whether admission occured prior to test
	 * @return updated substance test result
	 * @throws DuplicateEntityFoundException thrown when a duplicate substance
	 * test result is found
	 */
	SubstanceTestResult updateResult(SubstanceTestResult result, 
			Substance substance, SubstanceTestResultValue value, 
			Boolean substanceUseAdmission, Boolean admissionPriorToTest)
		throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified  lab result.
	 * 
	 * @param result  lab result
	 * @param substance substance
	 * @param value substance test result value
	 * @return updated  lab result
	 * @throws DuplicateEntityFoundException thrown when a duplicate  lab
	 * result is found
	 */
	LabResult updateLabResult(LabResult result, 
			Substance substance, SubstanceTestResultValue value)
		throws DuplicateEntityFoundException;

	/**
	 * Remove the specified substance test.
	 * 
	 * @param substanceTest substance test
	 */
	void remove(SubstanceTest substanceTest);
	
	/**
	 * Removes the specified substance test result.
	 * 
	 * @param result substance test result
	 */
	void removeResult(SubstanceTestResult result);
	
	/**
	 * Removes the specified  lab result.
	 * 
	 * @param result  lab result
	 */
	void removeLabResult(LabResult result);
	
	/**
	 * Returns a list of substance test results for the specified substance 
	 * test.
	 * 
	 * @param substanceTest substance test
	 * @return list of substance test results
	 */
	List<SubstanceTestResult> findResultsBySubstanceTest(
			SubstanceTest substanceTest);
	
	/**
	 * Returns a list of  lab results for the specified substance test.
	 * 
	 * @param substanceTest substance test
	 * @return list of  lab results
	 */
	List<LabResult> findLabResultsBySubstanceTest(
			SubstanceTest substanceTest);
	
	/**
	 * Returns a list of substance test result values.
	 * 
	 * @return list of substance test result values
	 */
	List<SubstanceTestResultValue> findResultValues();
	
	/**
	 * Returns a list of all test kits for the specified sample collection 
	 * method.
	 * @param method sample collection method
	 * @return list of test kits
	 */
	List<TestKit> findKitsBySampleCollectionMethod(
			SampleCollectionMethod method);
	
	/**
	 * Returns a list of test kit parameters for the specified test kit.
	 * 
	 * @param testKit test kit
	 * @return list of test kit parameters
	 */
	List<TestKitParameter> findParametersByTestKit(TestKit testKit);

	/**
	 * Returns the result for the specified substance test and substance.
	 * 
	 * @param substanceTest substance test
	 * @param substance substance
	 * @return substance test result
	 */
	SubstanceTestResult findResultBySubstanceAndTest(
			SubstanceTest substanceTest, Substance substance);

	/**
	 * Returns the  lab result for the specified substance test and
	 * substance.
	 * 
	 * @param substanceTest substance test
	 * @param substance substance
	 * @return  lab result
	 */
	LabResult findLabResultBySubstanceAndTest(
			SubstanceTest substanceTest, Substance substance);

	/**
	 * Removes all substance test results for the specified substance test, 
	 * except for the specified test results. 
	 * 
	 * @param substanceTest substance test
	 * @param persistedResults persisted substance test results
	 */
	void removeResultsBySubstanceTestExcluding(SubstanceTest substanceTest,
			SubstanceTestResult[] persistedResults);

	/**
	 * Removes all  lab results for the specified substance test, except
	 * the specified  lab results.
	 * 
	 * @param substanceTest substance test
	 * @param persistedResults persisted  lab results
	 */
	void removeLabResultBySubstanceTestExcluding(
			SubstanceTest substanceTest, LabResult[] persistedResults);

	/**
	 * Returns a list of substances.
	 * 
	 * @return list of substances
	 */
	List<Substance> findSubstances();

	/**
	 * Removes all lab results for the specified substance test.
	 * 
	 * @param substanceTest substance test
	 */
	void removeLabResultsBySubstanceTest(SubstanceTest substanceTest);

	/**
	 * Returns substance labs.
	 * 
	 * @return list of substance labs
	 */
	List<SubstanceLab> findSubstanceLabs();
}