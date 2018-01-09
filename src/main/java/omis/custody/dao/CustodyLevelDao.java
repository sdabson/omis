package omis.custody.dao;

import java.util.Date;

import omis.custody.domain.CustodyLevel;
import omis.dao.GenericDao;
import omis.offender.domain.Offender;

/**
 * Database access objects for custody level.
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 07 2013)
 * @since OMIS 3.0
 */
public interface CustodyLevelDao extends GenericDao<CustodyLevel> {

	/**
	 * Returns the custody level for the specified offender on the specified
	 * date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return custody level
	 */
	CustodyLevel findCustodyLevel(Offender offender, Date date);
	
	/**
	 * Finds the custody level for the specified name
	 * @param name custody level name
	 * @return custody level
	 */
	CustodyLevel find(String name);
	
	/**
	 * Finds the custody level for the specified name, excluding the specified 
	 * custody level
	 * @param name custody level name
	 * @param excludedCustodyLevel custody level to exclude
	 * @return custody level
	 */
	CustodyLevel findExcluding(String name, CustodyLevel excludedCustodyLevel);
}