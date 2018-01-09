package omis.facility.service;

import omis.facility.domain.Room;

/**
 * @author Joel Norris
 * @version 0.1.0 (Feb, 08 2013)
 * @since OMIS 3.0
 */
public interface RoomService {
	
	/**
	 * Finds the room with the specified ID.
	 * 
	 * @param id id
	 * @return the room
	 */
	Room findById(Long id);
	
	/**
	 * Saves the specified room.
	 * 
	 * @param room room
	 * @return the room
	 */
	Room save(Room room);

	/**
	 * Removes the specified room.
	 * 
	 * @param room room
	 */
	void remove(Room room);

	/**
	 *  Returns an integer with the amount of beds in the specified room. 0 is
	 * returned if none found.
	 * 
	 * @param room room
	 * @return Integer
	 */
	Integer countBedsByRoom(Room room);
}