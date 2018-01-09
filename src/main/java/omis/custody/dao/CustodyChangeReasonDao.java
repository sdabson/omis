package omis.custody.dao;

import omis.custody.domain.CustodyChangeReason;
import omis.dao.GenericDao;

/**
 * Database access objects for custody change reason.
 * @author Joel Norris 
 * @version 0.1.0 (Mar, 07 2013)
 * @since OMIS 3.0
 */
public interface CustodyChangeReasonDao 
		extends GenericDao<CustodyChangeReason> {
	
	/**
	 * Finds the custody change reason with the specified name
	 * @param name custody change reason name
	 * @return custody change reason
	 */
	CustodyChangeReason find(String name);
	
	/**
	 * Finds the custody change reason with the specified name excluding the 
	 * specified custody change reason
	 * @param name custody change reason name
	 * @param excludedCustodyChangeReason custody change reason to exclude
	 * @return custody change reason
	 */
	CustodyChangeReason findExcluding(String name, 
			CustodyChangeReason excludedCustodyChangeReason);
}