package omis.substancetest.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.substancetest.domain.SubstanceTestResultValue;

/**
 * Substance Test Result Option DAO.
 * @author Joel Norris
 * @version 0.1.0 (Jul 2, 2013)
 * @since OMIS 3.0
 */
public interface SubstanceTestResultValueDao 
	extends GenericDao<SubstanceTestResultValue> {

	/**
	 * Returns a list of all valid substance test result options.
	 * 
	 * @return list of valid substance test result option
	 */
	List<SubstanceTestResultValue> findAll();
}
