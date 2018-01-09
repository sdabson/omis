/**
 * 
 */
package omis.separationneed.dao;

import omis.dao.GenericDao;
import omis.separationneed.domain.SeparationNeedReason;

/**
 * Data access object for separation need reason.
 * 
 * @author Joel Norris
 * @author Josh Divine 
 * @version 0.1.1 (Aug 7, 2017)
 * @since OMIS 3.0
 */
public interface SeparationNeedReasonDao 
	extends GenericDao<SeparationNeedReason> {
	
	/**
	 * Returns the separation need reason that matches the specified name.
	 * 
	 * @param name name
	 * @return separation need reason
	 */
	SeparationNeedReason find(String name);
	
	/**
	 * Returns the separation need reason that matches the specified name 
	 * excluding the specified reason.
	 * 
	 * @param name name
	 * @param excludedReason excluded separation need reason
	 * @return separation need reason
	 */
	SeparationNeedReason findExcluding(String name, 
			SeparationNeedReason excludedReason);
}