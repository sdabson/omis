package omis.employment.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.employment.domain.EmploymentChangeReason;

/**
 * Employment change reason data access object.
 * 
 * @author Yidong Li
 * @author Joel Norris
 * @author Josh Divine
 * @version 0.1.2 (Dec 14, 2017)
 * @since OMIS 3.0
 */

public interface EmploymentChangeReasonDao 
	extends GenericDao<EmploymentChangeReason> {
	
	/**
	 * Returns a list of employment change reasons.
	 * 
	 * @return list of employment change reasons
	 */
	List<EmploymentChangeReason> findEmploymentChangeReasons( );
	
	/**
	 * Returns matching employment change reason.
	 * 
	 * @param name name
	 * @return matching employment change reason
	 */
	EmploymentChangeReason find(String name);
	
	/**
	 * Returns matching employment change reason.
	 * 
	 * @param name name
	 * @param excludedChangeReason excluded employment change reason
	 * @return matching employment change reason
	 */
	EmploymentChangeReason findExcluding(String name, EmploymentChangeReason excludedChangeReason);
}
