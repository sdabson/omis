package omis.substancetest.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.substancetest.domain.TestKit;
import omis.substancetest.domain.TestKitParameter;

/**
 * Test Kit Parameter DAO.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 11, 2013)
 * @since OMIS 3.0
 */
public interface TestKitParameterDao  
	extends GenericDao<TestKitParameter> {

	/**
	 * Return a list of test kit parameters with the specified test kit.
	 * 
	 * @param testKit test kit
	 * @return test kit parameter
	 */
	List<TestKitParameter> findByTestKit(TestKit testKit);
}
