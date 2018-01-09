package omis.facility.service;

import omis.facility.domain.RoomDetail;

/**
 * @author Joel Norris
 * @version 0.1.0 (Feb, 11 2013)
 * @since OMIS 3.0
 */
public interface RoomDetailService {
	
	/**
	 * Saves the specified room detail.
	 * 
	 * @param roomDetail room detail
	 * @return the room detail
	 */
	RoomDetail save(RoomDetail roomDetail);
}