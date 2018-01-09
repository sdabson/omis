package omis.tierdesignation.dao;

import omis.dao.GenericDao;
import omis.tierdesignation.domain.TierSource;

/**
 * Data access object for Offender Tier Designation SpecialNeedSource entities.
 * @author Jason Nelson
 * @version 0.1.0 (Sept 20, 2012)
 * @since OMIS 3.0
 * @see TierSource
 */
public interface TierSourceDao
		extends GenericDao<TierSource> {
	
	/**
	 * Returns the tier source with the specified name.
	 * 
	 * @param name name
	 * @return tier source
	 */
	TierSource find(String name);
	
	/**
	 * Returns the tier source with the specified name, excluding the specified 
	 * tier source.
	 * 
	 * @param name name
	 * @param excludedTierSource excluded tier source
	 * @return tier source
	 */
	TierSource findExcluding(String name, 
			TierSource excludedTierSource);
}