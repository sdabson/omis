package omis.citation.dao;

import omis.citation.domain.MisdemeanorOffense;
import omis.dao.GenericDao;

/**
 * Misdemeanor citation.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Aug 8, 2016)
 * @since OMIS 3.0
 */

public interface MisdemeanorOffenseDao extends GenericDao<MisdemeanorOffense> {

	/**
	 * Returns a list of misdemeanor offenses for the specified offender.
	 * 
	 * @return list of misdemeanor offenses.
	 */
	MisdemeanorOffense find(String name);
	
}
