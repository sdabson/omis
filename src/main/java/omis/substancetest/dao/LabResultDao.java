package omis.substancetest.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.substance.domain.Substance;
import omis.substancetest.domain.LabResult;
import omis.substancetest.domain.SubstanceTest;

/**
 * Crime Lab Result DAO.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jan 2, 2014)
 * @since OMIS 3.0
 */
public interface LabResultDao 
	extends GenericDao<LabResult> {

	/**
	 * Returns a list of crime lab results for the specified substance test.
	 * 
	 * @param substanceTest substance test
	 * @return list of crime lab results
	 */
	List<LabResult> findBySubstanceTest(
			SubstanceTest substanceTest);

	/**
	 * Removes crime lab results by substance test excluding the ones specified.
	 * 
	 * @param substanceTest substance test
	 * @param excludingResults excluding results
	 */
	void removeCrimeLabResultBySubstanceTestExcluding(
			SubstanceTest substanceTest, LabResult... excludingResults);

	/**
	 * Return the crime lab result with the specified properties.
	 * 
	 * @param substanceTest substance test
	 * @param substance substance
	 * @return crime lab result
	 */
	LabResult find(SubstanceTest substanceTest, Substance substance);

	/**
	 * Return the crime lab result with the specified properties, excluding the
	 * specified crime lab result.
	 * 
	 * @param result crime lab result
	 * @param substance substance
	 * @param substanceTest substance test
	 * @return crime lab result
	 */
	LabResult findExcluding(LabResult result, Substance substance,
			SubstanceTest substanceTest);

	/**
	 * Removes all crime lab results for the specified substance test.
	 * 
	 * @param substanceTest substance test
	 */
	void removeResultsBySubstanceTest(SubstanceTest substanceTest);
}