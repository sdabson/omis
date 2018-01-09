package omis.offense.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.offense.domain.Offense;
import omis.offense.domain.OffenseClassification;

/**
 * Offense Dao.
 * 
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.2 (Sept 15, 2017)
 * @since OMIS 3.0
 */
public interface OffenseDao extends GenericDao<Offense> {

	/**
	 * Returns the offense with the specified name, short name, classification, 
	 * violation code. 
	 * 
	 * @param name name
	 * @param shortName short name
	 * @param classification offense classification
	 * @param violationCode violation code
	 * @return offense
	 */
	Offense find(String name, String shortName,
			OffenseClassification classification, String violationCode);
	
	/**
	 * Returns the offense with the specified name, short name, classification 
	 * and violation code excluding the specified offense.
	 * 
	 * @param name name
	 * @param shortName short name
	 * @param classification offense classification
	 * @param violationCode violation code
	 * @param excludedOffense excluded offense
	 * @return offense
	 */
	Offense findExcluding(String name, String shortName,
			OffenseClassification classification, String violationCode, 
			Offense excludedOffense);
	
	/**
	 * Returns the offenses that match the specified query.
	 * 
	 * @param query query
	 * @return offenses
	 * 
 	 * @deprecated Use {@code findByQuery(String, int)} as this method has been 
	 * hard code limited to a maximum of 25 results.
	 */
	@Deprecated
	List<Offense> findByQuery(String query);
	
	/**
	 * Returns the offenses that match the specified query.
	 * 
	 * @param query query
	 * @param maxResults max results to return
	 * @return offenses
	 */
	List<Offense> findByQuery(String query, int maxResults);
}
