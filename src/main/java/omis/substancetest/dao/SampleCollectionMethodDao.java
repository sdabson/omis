package omis.substancetest.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.substancetest.domain.SampleCollectionMethod;

/**
 * Sample Collection Method DAO.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 7, 2013)
 * @since OMIS 3.0
 */
public interface SampleCollectionMethodDao 
	extends GenericDao<SampleCollectionMethod> {

	/**
	 * Return sample collection methods whose valid value is true.
	 * 
	 * @return list of valid sample collection methods
	 */
	List<SampleCollectionMethod> findValid();
}
