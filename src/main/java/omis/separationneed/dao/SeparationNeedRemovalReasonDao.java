package omis.separationneed.dao;

import omis.dao.GenericDao;
import omis.separationneed.domain.SeparationNeedRemovalReason;

/**
 * Separation need removal reason data access object.
 * 
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.1 (Aug 7, 2017)
 * @since OMIS 3.0
 */
public interface SeparationNeedRemovalReasonDao
	extends GenericDao<SeparationNeedRemovalReason> {

	/**
	 * Returns the separation need removal reason that matches the specified 
	 * name.
	 * 
	 * @param name name
	 * @return separation need removal reason
	 */
	SeparationNeedRemovalReason find(String name);
	
	/**
	 * Returns the separation need removal reason that matches the specified 
	 * name excluding the specified reason.
	 * 
	 * @param name name
	 * @param excludedReason excluded separation need removal reason
	 * @return separation need removal reason
	 */
	SeparationNeedRemovalReason findExcluding(String name, 
			SeparationNeedRemovalReason excludedReason);
}