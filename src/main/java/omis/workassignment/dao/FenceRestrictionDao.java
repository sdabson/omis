package omis.workassignment.dao;

import java.util.List;

import omis.dao.GenericDao;
import omis.workassignment.domain.FenceRestriction;

/**
 * Data access object for fence restriction.
 * 
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.1.2 (Jul 18, 2017)
 * @since OMIS 3.0
 */
public interface FenceRestrictionDao
		extends GenericDao<FenceRestriction> {
	/**
	 * Returns a list of fence restriction.
	 * 
	 * @return a list of fence restriction
	 */
	List<FenceRestriction> findAll();
	
	/**
	 * Returns the fence restriction with the specified name.
	 * 
	 * @param name name
	 * @return fence restriction with the specified name
	 */
	FenceRestriction find(String name);
	
	/**
	 * Returns the fence restriction with the specified name, excluding the 
	 * specified fence restriction.
	 * 
	 * @param name name
	 * @param excludedFenceRestriction excluded fence restriction
	 * @return fence restriction with the specified name, excluding the 
	 * specified fence restriction
	 */
	FenceRestriction findExcluding(String name, 
			FenceRestriction excludedFenceRestriction);
}