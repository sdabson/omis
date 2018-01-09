package omis.tierdesignation.dao;

import omis.dao.GenericDao;
import omis.tierdesignation.domain.TierLevel;

/**
 * Data access object for Offender Tier Designation Level entities.
 * @author Jason Nelson
 * @version 0.1.0 (Sept 20, 2012)
 * @since OMIS 3.0
 * @see TierLevel
 */
public interface TierLevelDao
		extends GenericDao<TierLevel> {

	/**
	 * Returns the tier level with the specified name.
	 * 
	 * @param name name
	 * @return tier level
	 */
	TierLevel find(String name);
	
	/**
	 * Returns the tier level with the specified name, excluding the specified 
	 * tier level.
	 * 
	 * @param name name
	 * @param excludedTierLevel excluded tier level
	 * @return tier level
	 */
	TierLevel findExcluding(String name, 
			TierLevel excludedTierLevel);
}