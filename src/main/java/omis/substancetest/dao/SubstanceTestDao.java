package omis.substancetest.dao;

import omis.dao.GenericDao;
import omis.substancetest.domain.SubstanceTest;
import omis.substancetest.domain.SubstanceTestSample;

/**
 * Substance Test DAO.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 7, 2013)
 * @since OMIS 3.0
 */
public interface SubstanceTestDao 
	extends GenericDao<SubstanceTest> {

	/**
	 * Returns the substance test with the specified sample.
	 * 
	 * @param sample sample
	 * @return substance test
	 */
	SubstanceTest find(SubstanceTestSample sample);

	/**
	 * Returns the substance test with the specified sample, excluding the
	 * specified substance test.
	 * 
	 * @param substanceTest substance test
	 * @param sample substance test sample
	 * @return substance test
	 */
	SubstanceTest findExcluding(SubstanceTest substanceTest,
			SubstanceTestSample sample);
}