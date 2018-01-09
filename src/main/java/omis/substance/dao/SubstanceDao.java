package omis.substance.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.substance.domain.Substance;

/**
 * Susbtance DAO.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 19, 2013)
 * @since OMIS 3.0
 */
public interface SubstanceDao extends GenericDao<Substance> {

	/**
	 * Return valid substances.
	 * 
	 * @return list of substances
	 */
	List<Substance> findValid();

	/**
	 * Returns a valid, testable substances.
	 * 
	 * @return list of substances
	 */
	List<Substance> findTestable();
}
