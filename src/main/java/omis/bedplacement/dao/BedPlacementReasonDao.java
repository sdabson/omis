package omis.bedplacement.dao;

import omis.bedplacement.domain.BedPlacementReason;
import omis.dao.GenericDao;

/**
 * Data access object for movement reason.
 * 
 * @author Joel Norris 
 * @author Josh Divine
 * @version 0.1.1 (Aug 2, 2017)
 * @since OMIS 3.0
 */
public interface BedPlacementReasonDao extends GenericDao<BedPlacementReason> {

	/**
	 * Returns the bed placement reason with the specified name.
	 * 
	 * @param name name
	 * @return bed placement reason
	 */
	BedPlacementReason find(String name);
	
	/**
	 * Returns the bed placement reason with the specified name excluding the 
	 * specified bed placement reason.
	 * 
	 * @param name name
	 * @param excludedReason excluded bed placement reason
	 * @return bed placement reason
	 */
	BedPlacementReason findExcluding(String name, 
			BedPlacementReason excludedReason);
}
