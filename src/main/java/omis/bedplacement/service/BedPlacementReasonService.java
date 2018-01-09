package omis.bedplacement.service;

import java.util.List;

import omis.bedplacement.domain.BedPlacementReason;

/**
 * Service for movement reason.
 * 
 * @author Joel Norris 
 * @version 0.1.0 (Apr, 04 2013)
 * @since OMIS 3.0
 */
public interface BedPlacementReasonService {

	/**
	 * Returns the movement reason with the specified id.
	 * 
	 * @param id id
	 * @return the movement reason
	 */
	BedPlacementReason findById(Long id);
	
	/**
	 * Saved the specified movement reason.
	 * 
	 * @param movementReason movement reason
	 * @return the movement reason.
	 */
	BedPlacementReason save(BedPlacementReason movementReason);
	
	/**
	 * Removes the specified movement reason.
	 * 
	 * @param movementReason movement reason.
	 */
	void remove(BedPlacementReason movementReason);
	
	/**
	 * Finds all movement reasons and returns a list.
	 * 
	 * @return list of movement reasons.
	 */
	List<BedPlacementReason> findAll();
}