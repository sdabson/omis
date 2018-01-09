package omis.workrestriction.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.offender.domain.Offender;
import omis.workrestriction.domain.WorkRestriction;
import omis.workrestriction.domain.WorkRestrictionCategory;

/**
 * WorkRestrictionDao.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 16, 2016)
 *@since OMIS 3.0
 *
 */
public interface WorkRestrictionDao extends GenericDao<WorkRestriction> {
	
	/**
	 * Finds and returns a work restriction with specified parameters
	 * @param offender - Offender 
	 * @param category - work restriction category
	 * @return work restriction with specified parameters
	 */
	WorkRestriction find(Offender offender, WorkRestrictionCategory category);
	
	/**
	 * Finds and returns a work restriction with specified parameters excluding 
	 * specified work restriction
	 * @param excludedWorkRestriction
	 * @param offender - Offender 
	 * @param category - work restriction category
	 * @return work restriction with specified parameters excluding specified
	 * work restriction
	 */
	WorkRestriction findExcluding(WorkRestriction excludedWorkRestriction, 
			Offender offender, WorkRestrictionCategory category);
	
	/**
	 * Finds and returns a list of all work restrictions by specified offender
	 * @param offender - offender
	 * @return list of all work restrictions by specified offender
	 */
	List<WorkRestriction> findByOffender(Offender offender);
}
