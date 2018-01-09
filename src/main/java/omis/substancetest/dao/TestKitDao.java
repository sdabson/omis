package omis.substancetest.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.substancetest.domain.SampleCollectionMethod;
import omis.substancetest.domain.TestKit;

/**
 * Test kit data access object.
 * 
 * @author Joel Norris
 * @version 0.1.1 (April 20, 2015)
 * @since OMIS 3.0
 */
public interface TestKitDao  
	extends GenericDao<TestKit> {

	/**
	 * Return test kits with the specified sample collection method.
	 * 
	 * @param sampleCollectionMethod sample collection method
	 * @return test kit
	 */
	List<TestKit> findBySampleCollectionMethod(
			SampleCollectionMethod sampleCollectionMethod);
}
