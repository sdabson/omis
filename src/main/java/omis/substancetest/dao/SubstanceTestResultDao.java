package omis.substancetest.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.substance.domain.Substance;
import omis.substancetest.domain.SubstanceTest;
import omis.substancetest.domain.SubstanceTestResult;

/**
 * Substance Test Result DAO.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 7, 2013)
 * @since OMIS 3.0
 */
public interface SubstanceTestResultDao  
	extends GenericDao<SubstanceTestResult> {

	/**
	 * Return the substance test result with the specified property values.
	 * 
	 * @param substanceTest substance test
	 * @param substance substance
	 * @return substance test result with specified property values;
	 * {@code null} if no such result exists
	 */
	SubstanceTestResult find(SubstanceTest substanceTest,
			Substance substance);

	/**
	 * Removes results by substance test excluding the ones specified.
	 * 
	 * @param substanceTest substance test
	 * @param excludingResults results to exclude from deletion.
	 */
	void removeBySubstanceTestExcluding(SubstanceTest substanceTest,
			SubstanceTestResult... excludingResults);

	/**
	 * Returns the substance test result with the specified substance test and
	 * substance, excluding the specified substance test result.
	 * 
	 * @param result substance test result
	 * @param substance substance
	 * @param substanceTest substance test
	 * @return substance test result
	 */
	SubstanceTestResult findExcluding(SubstanceTestResult result, 
			Substance substance, SubstanceTest substanceTest);

	/**
	 * Returns a list of substance test results for the specified substance
	 * test.
	 * 
	 * @param substanceTest substance test
	 * @return list of substance test results
	 */
	List<SubstanceTestResult> findBySubstanceTest(SubstanceTest substanceTest);
}