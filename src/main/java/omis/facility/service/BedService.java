package omis.facility.service;

import java.util.List;

import omis.facility.domain.Bed;
import omis.facility.domain.Room;

/**
 * @author Joel Norris 
 * @version 0.1.0 (Feb, 08 2013)
 * @since OMIS 3.0
 */
public interface BedService {

	/**
	 * Returns the bed with the specified ID.
	 * 
	 * @param id id
	 * @return the bed
	 */
	Bed findById(Long id);
	
	/**
	 * Saves the specified bed.
	 * 
	 * @param bed bed
	 * @return the bed
	 */
	Bed save(Bed bed);

	/**
	 * Finds all beds for the specified room.
	 * 
	 * @param room room
	 * @return list of beds
	 */
	List<Bed> findByRoom(Room room);

	/**
	 * Removes the specified bed.
	 * 
	 * @param bed bed
	 */
	void remove(Bed bed);
	
}
