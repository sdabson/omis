package omis.tierdesignation.dao;

import omis.dao.GenericDao;
import omis.tierdesignation.domain.TierChangeReason;

/**
 * Data access object for Offender Tier Designation Change Reason entities.
 * @author Jason Nelson
 * @version 0.1.0 (Sept 20, 2012)
 * @since OMIS 3.0
 * @see TierChangeReason
 */
public interface TierChangeReasonDao
		extends GenericDao<TierChangeReason> {
	
	/**
	 * Returns the tier change reason with the specified name.
	 * 
	 * @param name name
	 * @return tier change reason
	 */
	TierChangeReason find(String name);
	
	/**
	 * Returns the tier change reason with the specified name, excluding the 
	 * specified tier change reason.
	 * 
	 * @param name name
	 * @param excludedTierChangeReason excluded tier change reason
	 * @return tier change reason
	 */
	TierChangeReason findExcluding(String name, 
			TierChangeReason excludedTierChangeReason);
}